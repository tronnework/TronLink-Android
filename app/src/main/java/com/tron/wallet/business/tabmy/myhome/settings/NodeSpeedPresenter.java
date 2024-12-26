package com.tron.wallet.business.tabmy.myhome.settings;

import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Process;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.business.tabmy.myhome.settings.NodeSpeedContract;
import com.tron.wallet.business.tabmy.myhome.settings.NodeSpeedPresenter;
import com.tron.wallet.business.tabmy.myhome.settings.bean.NodeBean;
import com.tron.wallet.business.welcome.WelcomeActivity;
import com.tron.wallet.common.bean.ChainBean;
import com.tron.wallet.common.components.dialog.CustomDialog;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.ToastUtil;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.net.rpc.IGrpcClient;
import com.tronlinkpro.wallet.R;
import java.util.List;
public class NodeSpeedPresenter extends NodeSpeedContract.Presenter {
    private Dialog dialog;

    @Override
    protected void onStart() {
    }

    @Override
    public void initSwitchDialog(int i, final NodeBean nodeBean) {
        Dialog dialog = this.dialog;
        if (dialog == null || !dialog.isShowing()) {
            Dialog dialog2 = this.dialog;
            if (dialog2 != null) {
                dialog2.dismiss();
                this.dialog = null;
            }
            List<ChainBean> allChainJson = SpAPI.THIS.getAllChainJson();
            if (i == 0) {
                SpAPI.THIS.setShasta(false);
                SpAPI.THIS.setCurrentChainName("MainChain");
                switchNodeNetWorking(0);
                if (IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.SHASTA) {
                    resetNodeInfo();
                    SpAPI.THIS.setTestVersion(SpAPI.THIS.getLastTestVersion());
                    SpAPI.THIS.setIP(nodeBean.getAddress());
                    SpAPI.THIS.setIsCustomFull(true);
                    SpAPI.THIS.setCustomFullNode(nodeBean.getAddress());
                    List<ChainBean> lastAllChainJson = SpAPI.THIS.getLastAllChainJson();
                    if (lastAllChainJson != null) {
                        for (int i2 = 0; i2 < lastAllChainJson.size(); i2++) {
                            if (lastAllChainJson.get(i2).isMainChain) {
                                lastAllChainJson.get(i2).isSelect = true;
                            } else {
                                lastAllChainJson.get(i2).isSelect = false;
                            }
                        }
                        SpAPI.THIS.setAllChainJson(lastAllChainJson);
                    }
                    reStartApp();
                    return;
                }
                final List<ChainBean> allChainJson2 = SpAPI.THIS.getAllChainJson();
                if (allChainJson2 != null) {
                    for (int i3 = 0; i3 < allChainJson2.size(); i3++) {
                        if (allChainJson2.get(i3).isMainChain) {
                            allChainJson2.get(i3).isSelect = true;
                        } else {
                            allChainJson2.get(i3).isSelect = false;
                        }
                    }
                    SpAPI.THIS.setAllChainJson(allChainJson2);
                }
                SpAPI.THIS.setCurrentChainName("MainChain");
                if (this.mRxManager == null) {
                    this.mRxManager = new RxManager();
                }
                this.mRxManager.post(Event.SWITCH_CHAIN, "333");
                ((NodeSpeedContract.View) this.mView).showLoadingDialog();
                ((NodeSpeedContract.View) this.mView).runOnThreeThread(new OnBackground() {
                    @Override
                    public final void doOnBackground() {
                        lambda$initSwitchDialog$0(allChainJson2, nodeBean);
                    }
                });
                return;
            }
            CustomDialog.Builder builder = new CustomDialog.Builder(((NodeSpeedContract.View) this.mView).getIContext());
            this.dialog = builder.style(R.style.loading_dialog).cancelTouchout(false).heightDimenRes(-2).widthDimenRes(-1).setGravity(17).view(R.layout.confirm_dialog).build();
            View view = builder.getView();
            TextView textView = (TextView) view.findViewById(R.id.tv_cancle);
            TextView textView2 = (TextView) view.findViewById(R.id.tv_ok);
            TextView textView3 = (TextView) view.findViewById(R.id.tv_content);
            ((TextView) view.findViewById(R.id.tv_title)).setVisibility(View.GONE);
            if (i == 1) {
                textView3.setText(R.string.node_switch_shasta_confirm);
            } else if (i == 2) {
                textView3.setText(R.string.node_switch_dappchain_confirm);
            }
            CheckBox checkBox = (CheckBox) view.findViewById(R.id.ck);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {
                    dialog.dismiss();
                }
            });
            textView2.setOnClickListener(new fun3(i, allChainJson, nodeBean));
            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.show();
        }
    }

    public void lambda$initSwitchDialog$0(List list, final NodeBean nodeBean) {
        if (list != null && list.size() > 0) {
            IGrpcClient.THIS.initGRpc(((ChainBean) list.get(0)).fullNode, ((ChainBean) list.get(0)).solidityNode);
        }
        ((NodeSpeedContract.View) this.mView).runOnUIThread(new OnMainThread() {
            @Override
            public void doInUIThread() {
                try {
                    ToastUtil.getInstance().showToast(((NodeSpeedContract.View) mView).getIContext(), String.format(((NodeSpeedContract.View) mView).getIContext().getString(R.string.node_connected_to), ((NodeSpeedContract.View) mView).getIContext().getResources().getString(R.string.node_mainnet)));
                } catch (Exception e) {
                    LogUtils.e(e);
                }
                ((NodeSpeedContract.View) mView).dismisLoadingDialog();
                ((NodeSpeedContract.View) mView).switchToNode(nodeBean);
            }
        });
    }

    public class fun3 implements View.OnClickListener {
        final List val$allChainJson;
        final int val$index;
        final NodeBean val$nodeBean;

        fun3(int i, List list, NodeBean nodeBean) {
            this.val$index = i;
            this.val$allChainJson = list;
            this.val$nodeBean = nodeBean;
        }

        @Override
        public void onClick(View view) {
            dialog.dismiss();
            int i = this.val$index;
            if (i == 1) {
                switchNodeNetWorking(1);
                SpAPI.THIS.setIP(((NodeSpeedContract.View) mView).getIContext().getResources().getStringArray(R.array.shasta_node_list)[0]);
                SpAPI.THIS.setShasta(true);
                SpAPI.THIS.setLastAllChainJson(SpAPI.THIS.getAllChainJson());
                SpAPI.THIS.setCurrentChainName("MainChain");
                SpAPI.THIS.setLastTestVersion(SpAPI.THIS.getTestVersion());
                SpAPI.THIS.setTestVersion(5);
                List<ChainBean> allChainJson = SpAPI.THIS.getAllChainJson();
                if (allChainJson != null) {
                    for (int i2 = 0; i2 < allChainJson.size(); i2++) {
                        if (allChainJson.get(i2).isMainChain) {
                            allChainJson.get(i2).isSelect = true;
                        } else {
                            allChainJson.get(i2).isSelect = false;
                        }
                    }
                    SpAPI.THIS.setAllChainJson(allChainJson);
                    SpAPI.THIS.setLastAllChainJson(SpAPI.THIS.getAllChainJson());
                }
                try {
                    ToastUtil.getInstance().showToast(((NodeSpeedContract.View) mView).getIContext(), R.string.setting_shasta0);
                } catch (Exception e) {
                    LogUtils.e(e);
                }
                reStartApp();
            } else if (i == 2) {
                switchNodeNetWorking(2);
                SpAPI.THIS.setCurrentChainName(TronConfig.DApp_CHAIN_NAME);
                SpAPI.THIS.setShasta(false);
                if (IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.SHASTA) {
                    List<ChainBean> lastAllChainJson = SpAPI.THIS.getLastAllChainJson();
                    if (lastAllChainJson != null && lastAllChainJson.size() > 0) {
                        for (int i3 = 0; i3 < lastAllChainJson.size(); i3++) {
                            if (!lastAllChainJson.get(i3).isMainChain) {
                                lastAllChainJson.get(i3).isSelect = true;
                                SpAPI.THIS.setIP(lastAllChainJson.get(i3).fullNode);
                            } else {
                                lastAllChainJson.get(i3).isSelect = false;
                            }
                        }
                        SpAPI.THIS.setAllChainJson(lastAllChainJson);
                    }
                } else {
                    if (this.val$allChainJson != null) {
                        for (int i4 = 0; i4 < this.val$allChainJson.size(); i4++) {
                            if (((ChainBean) this.val$allChainJson.get(i4)).isMainChain) {
                                ((ChainBean) this.val$allChainJson.get(i4)).isSelect = false;
                            } else {
                                ((ChainBean) this.val$allChainJson.get(i4)).isSelect = true;
                            }
                        }
                        SpAPI.THIS.setIP(this.val$nodeBean.getIp());
                    }
                    SpAPI.THIS.setAllChainJson(this.val$allChainJson);
                }
                SpAPI.THIS.setTestVersion(SpAPI.THIS.getLastTestVersion());
                ((NodeSpeedContract.View) mView).showLoadingDialog();
                final NodeBean nodeBean = this.val$nodeBean;
                ((NodeSpeedContract.View) mView).runOnThreeThread(new OnBackground() {
                    @Override
                    public final void doOnBackground() {
                        NodeSpeedPresenter.3.this.lambda$onClick$1(nodeBean);
                    }
                });
            }
        }

        public void lambda$onClick$1(final NodeBean nodeBean) {
            if (nodeBean != null) {
                IGrpcClient.THIS.initGRpc(nodeBean.getIp(), "");
            }
            ((NodeSpeedContract.View) mView).runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    NodeSpeedPresenter.3.this.lambda$onClick$0(nodeBean);
                }
            });
        }

        public void lambda$onClick$0(NodeBean nodeBean) {
            if (mRxManager == null) {
                mRxManager = new RxManager();
            }
            mRxManager.post(Event.SWITCH_CHAIN, "333");
            try {
                ToastUtil.getInstance().showToast(((NodeSpeedContract.View) mView).getIContext(), String.format(((NodeSpeedContract.View) mView).getIContext().getString(R.string.node_connected_to), ((NodeSpeedContract.View) mView).getIContext().getString(R.string.node_dappchain)));
            } catch (Exception e) {
                LogUtils.e(e);
            }
            ((NodeSpeedContract.View) mView).dismissLoading();
            if (IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.SHASTA) {
                reStartApp();
            } else {
                ((NodeSpeedContract.View) mView).switchToNode(nodeBean);
            }
        }
    }

    public void switchNodeNetWorking(int i) {
        if (i == 0) {
            SpAPI.THIS.setCurrentChainName("MainChain");
        } else if (i == 1) {
            SpAPI.THIS.setCurrentChainName("MainChain");
        } else if (i == 2) {
            SpAPI.THIS.setCurrentChainName(TronConfig.DApp_CHAIN_NAME);
        }
    }

    private void resetNodeInfo() {
        SpAPI.THIS.setIsCustomFull(false);
        SpAPI.THIS.setIsDappCustomSol(false);
        SpAPI.THIS.setAllChainJson(null);
        SpAPI.THIS.setCurrentChainName("");
        SpAPI.THIS.setNotNileChainNoticeTimes(0);
    }

    public void reStartApp() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(((NodeSpeedContract.View) mView).getIContext(), WelcomeActivity.class);
                intent.setFlags(268468224);
                ((NodeSpeedContract.View) mView).getIContext().startActivity(intent);
                Process.killProcess(Process.myPid());
            }
        }, 500L);
    }
}
