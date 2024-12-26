package com.tron.wallet.business.tabmy.myhome;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Process;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.core.CenterPopupView;
import com.lxj.xpopup.enums.PopupAnimation;
import com.lxj.xpopup.util.XPopupUtils;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.business.tabmy.myhome.ChainSettingActivity;
import com.tron.wallet.business.tabmy.myhome.settings.bean.MainNodeOutput;
import com.tron.wallet.business.welcome.WelcomeActivity;
import com.tron.wallet.common.bean.ChainBean;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.ToastUtil;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.databinding.AcChainSettingBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.net.rpc.IGrpcClient;
import com.tronlinkpro.wallet.R;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class ChainSettingActivity extends BaseActivity<EmptyPresenter, EmptyModel> {
    private static final String TAG = "ChainSettingActivity";
    private AcChainSettingBinding binding;
    private List<ChainBean> chainBeans;
    private Dialog dialog;
    ImageView ivMainNetSelect;
    ImageView ivNileChainSelect;
    ImageView ivShastaSelect;
    BasePopupView popupView;
    private RxManager rxManager;

    public enum Chain {
        MainNet,
        ShastaNet,
        NileNet
    }

    @Override
    protected void setLayout() {
        AcChainSettingBinding inflate = AcChainSettingBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        LinearLayout root = inflate.getRoot();
        mappingId();
        setClick();
        setView(root, 1);
        setHeaderBar(getString(R.string.network_setting));
    }

    public void mappingId() {
        this.ivMainNetSelect = this.binding.ivMainnetSelect;
        this.ivShastaSelect = this.binding.ivShastaSelect;
        this.ivNileChainSelect = this.binding.ivNileChainSelect;
    }

    @Override
    protected void processData() {
        List<ChainBean> allChainJson = SpAPI.THIS.getAllChainJson();
        this.chainBeans = allChainJson;
        if (allChainJson != null && allChainJson.size() == 2) {
            boolean z = false;
            boolean z2 = false;
            for (int i = 0; i < this.chainBeans.size(); i++) {
                List<ChainBean> list = this.chainBeans;
                if (list == null || list.get(i) == null || !this.chainBeans.get(i).isMainChain || !"MainChain".equals(this.chainBeans.get(i).chainName)) {
                    List<ChainBean> list2 = this.chainBeans;
                    if (list2 != null && list2.get(i) != null && !this.chainBeans.get(i).isMainChain && TronConfig.DApp_CHAIN_NAME.equals(this.chainBeans.get(i).chainName)) {
                        z = true;
                    }
                } else {
                    z2 = true;
                }
            }
            if (!z || !z2) {
                this.chainBeans = new ArrayList();
                Map<String, MainNodeOutput.DataBean> mainNodeList = SpAPI.THIS.getMainNodeList();
                ChainBean chainBean = new ChainBean(mainNodeList.get(TronConfig.DApp_CHAIN_NAME));
                ChainBean chainBean2 = new ChainBean(mainNodeList.get("MainChain"));
                if (TronConfig.DApp_CHAIN_NAME.equals(SpAPI.THIS.getCurrentChainName())) {
                    chainBean.isSelect = true;
                } else {
                    chainBean2.isSelect = true;
                }
                this.chainBeans.add(chainBean2);
                this.chainBeans.add(chainBean);
                SpAPI.THIS.setAllChainJson(this.chainBeans);
            }
        } else {
            List<ChainBean> list3 = this.chainBeans;
            if (list3 == null || list3.size() == 1) {
                this.chainBeans = new ArrayList();
                Map<String, MainNodeOutput.DataBean> mainNodeList2 = SpAPI.THIS.getMainNodeList();
                ChainBean chainBean3 = new ChainBean(mainNodeList2.get(TronConfig.DApp_CHAIN_NAME));
                ChainBean chainBean4 = new ChainBean(mainNodeList2.get("MainChain"));
                if (TronConfig.DApp_CHAIN_NAME.equals(SpAPI.THIS.getCurrentChainName())) {
                    chainBean3.isSelect = true;
                } else {
                    chainBean4.isSelect = true;
                }
                this.chainBeans.add(chainBean4);
                this.chainBeans.add(chainBean3);
            }
            SpAPI.THIS.setAllChainJson(this.chainBeans);
        }
        if (this.rxManager == null) {
            this.rxManager = new RxManager();
        }
        this.rxManager.on(Event.SWITCH_CHAIN, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$processData$0(obj);
            }
        });
        TouchDelegateUtils.expandViewTouchDelegate(this.ivMainNetSelect, 200, 200, 200, 200);
        TouchDelegateUtils.expandViewTouchDelegate(this.ivShastaSelect, 200, 200, 200, 200);
        TouchDelegateUtils.expandViewTouchDelegate(this.ivNileChainSelect, 200, 200, 200, 200);
    }

    public void lambda$processData$0(Object obj) throws Exception {
        initChainSetting();
    }

    @Override
    public void onResume() {
        super.onResume();
        List<ChainBean> allChainJson = SpAPI.THIS.getAllChainJson();
        this.chainBeans = allChainJson;
        if (allChainJson != null) {
            for (ChainBean chainBean : allChainJson) {
                LogUtils.w("ChainSetting", String.format("chainid = %s, isMainChain = %b", chainBean.chainId, Boolean.valueOf(chainBean.isMainChain)));
            }
        }
        initChainSetting();
    }

    private void initChainSetting() {
        if (IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.RELEASE || IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.PRE_RELEASE) {
            this.ivMainNetSelect.setBackgroundResource(R.mipmap.ic_check_selected);
            this.ivShastaSelect.setBackgroundResource(R.mipmap.ic_check_unselect);
            this.ivNileChainSelect.setBackgroundResource(R.mipmap.ic_check_unselect);
        } else if (IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.SHASTA) {
            this.ivShastaSelect.setBackgroundResource(R.mipmap.ic_check_selected);
            this.ivMainNetSelect.setBackgroundResource(R.mipmap.ic_check_unselect);
            this.ivNileChainSelect.setBackgroundResource(R.mipmap.ic_check_unselect);
        } else if (IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.NILETEST || IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.TEST) {
            this.ivShastaSelect.setBackgroundResource(R.mipmap.ic_check_unselect);
            this.ivMainNetSelect.setBackgroundResource(R.mipmap.ic_check_unselect);
            this.ivNileChainSelect.setBackgroundResource(R.mipmap.ic_check_selected);
        } else {
            this.ivNileChainSelect.setBackgroundResource(R.mipmap.ic_check_unselect);
            this.ivShastaSelect.setBackgroundResource(R.mipmap.ic_check_unselect);
            this.ivMainNetSelect.setBackgroundResource(R.mipmap.ic_check_unselect);
        }
    }

    public class fun1 extends NoDoubleClickListener2 {
        fun1() {
        }

        @Override
        protected void onNoDoubleClick(View view) {
            int id = view.getId();
            if (id != R.id.rl_mainnet) {
                if (id == R.id.rl_nile) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.ChangeNetwork.CLICK_CHANGE_NETWORK_CLICK_ON_DAPP_CHAIN_MAINNET);
                    if (IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.NILETEST || isFinishing()) {
                        return;
                    }
                    showAlertPopWindow(Chain.NileNet);
                    return;
                } else if (id != R.id.rl_shasta) {
                    return;
                } else {
                    AnalyticsHelper.logEvent(AnalyticsHelper.ChangeNetwork.CLICK_CHANGE_NETWORK_CLICK_ON_THE_SHASTA_TESTNET);
                    if (IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.SHASTA || isFinishing()) {
                        return;
                    }
                    showAlertPopWindow(Chain.ShastaNet);
                    return;
                }
            }
            AnalyticsHelper.logEvent(AnalyticsHelper.ChangeNetwork.CLICK_CHANGE_NETWORK_CLICK_ON_TRON_MAINNET);
            if (IRequest.ENVIRONMENT != IRequest.NET_ENVIRONMENT.RELEASE) {
                SpAPI.THIS.setShasta(false);
                resetNodeInfo();
                switchNodeNetWorking(Chain.MainNet);
                if (IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.NILETEST || IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.TEST || IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.PRE_RELEASE || IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.SHASTA) {
                    SpAPI.THIS.setTestVersion(1);
                    switchNodeNetWorking(Chain.MainNet);
                    resetNodeInfo();
                    reStartApp();
                } else {
                    for (int i = 0; i < chainBeans.size(); i++) {
                        if (((ChainBean) chainBeans.get(i)).isMainChain) {
                            ((ChainBean) chainBeans.get(i)).isSelect = true;
                        } else {
                            ((ChainBean) chainBeans.get(i)).isSelect = false;
                        }
                    }
                    SpAPI.THIS.setAllChainJson(chainBeans);
                    switchNodeNetWorking(Chain.MainNet);
                    if (rxManager == null) {
                        rxManager = new RxManager();
                    }
                    rxManager.post(Event.SWITCH_CHAIN, "333");
                    showLoadingDialog();
                    runOnThreeThread(new OnBackground() {
                        @Override
                        public final void doOnBackground() {
                            ChainSettingActivity.1.this.lambda$onNoDoubleClick$0();
                        }
                    });
                }
            }
            rxManager.post(Event.DD, "redDot");
        }

        public void lambda$onNoDoubleClick$0() {
            IGrpcClient.THIS.initGRpc(((ChainBean) chainBeans.get(0)).fullNode, ((ChainBean) chainBeans.get(0)).solidityNode);
            runOnUIThread(new OnMainThread() {
                @Override
                public void doInUIThread() {
                    try {
                        ToastUtil.getInstance().showToast((Activity) ChainSettingActivity.this, String.format(getString(R.string.node_connected_to), getResources().getString(R.string.node_mainnet)));
                    } catch (Exception e) {
                        LogUtils.e(e);
                    }
                    dismissLoadingDialog();
                }
            });
        }
    }

    public void setClick() {
        1 r0 = new fun1();
        this.binding.rlMainnet.setOnClickListener(r0);
        this.binding.rlShasta.setOnClickListener(r0);
        this.binding.rlNile.setOnClickListener(r0);
    }

    public void switchNodeNetWorking(Chain chain) {
        if (Chain.MainNet == chain) {
            SpAPI.THIS.setCurrentChainName("MainChain");
        } else if (Chain.ShastaNet == chain) {
            SpAPI.THIS.setCurrentChainName("MainChain");
        } else if (Chain.NileNet == chain) {
            SpAPI.THIS.setCurrentChainName("MainChain");
        }
    }

    public void resetNodeInfo() {
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
                Intent intent = new Intent(ChainSettingActivity.this, WelcomeActivity.class);
                intent.setFlags(268468224);
                startActivity(intent);
                Process.killProcess(Process.myPid());
            }
        }, 500L);
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        AnalyticsHelper.logEvent(AnalyticsHelper.ChangeNetwork.CLICK_CHANGE_NETWORK_CLICK_BACK);
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxManager rxManager = this.rxManager;
        if (rxManager != null) {
            rxManager.clear();
        }
    }

    public void showAlertPopWindow(final Chain chain) {
        BasePopupView asCustom = new XPopup.Builder(this).maxWidth(XPopupUtils.getScreenWidth(this)).dismissOnTouchOutside(false).popupAnimation(PopupAnimation.NoAnimation).asCustom(new CenterPopupView(this) {
            @Override
            public int getImplLayoutId() {
                return R.layout.network_switch_pop;
            }

            @Override
            public void onCreate() {
                super.onCreate();
                ((TextView) findViewById(R.id.tv_title)).setText(R.string.network_node_setting);
                TextView textView = (TextView) findViewById(R.id.tv_cancle);
                Button button = (Button) findViewById(R.id.btn_confirm);
                button.setText(R.string.switch_net_confirm);
                TextView textView2 = (TextView) findViewById(R.id.tv_content);
                if (Chain.ShastaNet == chain) {
                    textView2.setText(R.string.switch_shasta_pop_desc1);
                } else if (Chain.NileNet == chain) {
                    textView2.setText(R.string.switch_nile_pop_desc1);
                }
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (Chain.ShastaNet == chain) {
                            AnalyticsHelper.logEvent(AnalyticsHelper.ChangeNetwork_s.CLICK_CHANGE_NETWORK_S_CLICK_CANCEL);
                        } else if (Chain.NileNet == chain) {
                            AnalyticsHelper.logEvent(AnalyticsHelper.ChangeNetwork_d.CLICK_CHANGE_NETWORK_D_CLICK_CANCEL);
                        }
                        fun3.this.dialog.dismiss();
                    }
                });
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (Chain.ShastaNet == chain) {
                            AnalyticsHelper.logEvent(AnalyticsHelper.ChangeNetwork_s.CLICK_CHANGE_NETWORK_S_CLICK_TO_CONFIRM_THE_SWITCH);
                        } else if (Chain.NileNet == chain) {
                            AnalyticsHelper.logEvent(AnalyticsHelper.ChangeNetwork_d.CLICK_CHANGE_NETWORK_D_CLICK_TO_CONFIRM_THE_SWITCH);
                        }
                        fun3.this.dialog.dismiss();
                        if (Chain.ShastaNet == chain) {
                            switchNodeNetWorking(chain);
                            SpAPI.THIS.setIP(getResources().getStringArray(R.array.shasta_node_list)[0]);
                            SpAPI.THIS.setShasta(true);
                            for (int i = 0; i < chainBeans.size(); i++) {
                                if (((ChainBean) chainBeans.get(i)).isMainChain) {
                                    ((ChainBean) chainBeans.get(i)).isSelect = true;
                                } else {
                                    ((ChainBean) chainBeans.get(i)).isSelect = false;
                                }
                            }
                            SpAPI.THIS.setAllChainJson(chainBeans);
                            SpAPI.THIS.setLastAllChainJson(chainBeans);
                            SpAPI.THIS.setLastTestVersion(SpAPI.THIS.getTestVersion());
                            SpAPI.THIS.setTestVersion(5);
                            reStartApp();
                        } else if (Chain.NileNet == chain) {
                            switchNodeNetWorking(chain);
                            SpAPI.THIS.setShasta(false);
                            SpAPI.THIS.setTestVersion(4);
                            resetNodeInfo();
                            reStartApp();
                        }
                    }
                });
            }
        });
        this.popupView = asCustom;
        asCustom.show();
    }
}
