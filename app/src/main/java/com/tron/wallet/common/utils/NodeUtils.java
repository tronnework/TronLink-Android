package com.tron.wallet.common.utils;

import android.os.Looper;
import com.google.gson.Gson;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.tron_base.frame.utils.ThreadPoolManager;
import com.tron.wallet.business.tabmy.myhome.settings.bean.MainNodeOutput;
import com.tron.wallet.common.bean.ChainBean;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.NodeUtils;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletType;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.HttpAPI;
import com.tron.wallet.net.rpc.IGrpcClient;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.tron.walletserver.Wallet;
public class NodeUtils {
    public static void nodeRequest() {
        ArrayList arrayList = new ArrayList();
        Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
        String address = selectedPublicWallet != null ? selectedPublicWallet.getAddress() : "";
        for (String str : WalletUtils.getWalletNames()) {
            Wallet wallet = WalletUtils.getWallet(str);
            if (wallet != null && wallet.getAddress() != null) {
                int typeForNodeInfo = WalletType.getTypeForNodeInfo(wallet);
                HashMap hashMap = new HashMap();
                if (wallet.isShieldedWallet()) {
                    hashMap.put("ztron", Integer.valueOf(typeForNodeInfo));
                } else {
                    hashMap.put(wallet.getAddress(), Integer.valueOf(typeForNodeInfo));
                }
                arrayList.add(hashMap);
            }
        }
        getMainNodes(address, RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(arrayList))).subscribe(new IObserver(new fun1(new RxManager()), "111"));
    }

    public class fun1 implements ICallback<MainNodeOutput> {
        final RxManager val$manager;

        fun1(RxManager rxManager) {
            this.val$manager = rxManager;
        }

        @Override
        public void onResponse(String str, MainNodeOutput mainNodeOutput) {
            if (mainNodeOutput == null || mainNodeOutput.data == null) {
                return;
            }
            if (mainNodeOutput.data.size() > 0) {
                for (int i = 0; i < mainNodeOutput.data.size(); i++) {
                    MainNodeOutput.DataBean dataBean = mainNodeOutput.data.get(i);
                    if (!StringTronUtil.isEmpty(dataBean.chainName) && dataBean.chainName.equals(ChainUtil.NileChain)) {
                        mainNodeOutput.data.get(i).isMainChain = 1;
                    }
                }
            }
            SpAPI.THIS.setMainNodeList(mainNodeOutput.data);
            SpAPI.THIS.setAssetsList(new ArrayList());
            ArrayList<ChainBean> arrayList = new ArrayList();
            boolean z = false;
            for (int i2 = 0; i2 < mainNodeOutput.data.size(); i2++) {
                ChainBean chainBean = new ChainBean(mainNodeOutput.data.get(i2));
                if (!StringTronUtil.isEmpty(chainBean.chainName) && chainBean.chainName.equals(ChainUtil.NileChain)) {
                    chainBean.isMainChain = true;
                }
                ChainBean currentChain = SpAPI.THIS.getCurrentChain();
                if (currentChain != null) {
                    if (currentChain.isMainChain) {
                        if (mainNodeOutput.data.get(i2).isMainChain == 1) {
                            chainBean.isSelect = true;
                            z = true;
                        }
                    } else if (currentChain.chainId.equals(mainNodeOutput.data.get(i2).chainId)) {
                        chainBean.isSelect = true;
                        z = true;
                    }
                }
                if (chainBean.isMainChain) {
                    if (SpAPI.THIS.isCustomFull()) {
                        chainBean.fullNode = SpAPI.THIS.getCustomeFull();
                    }
                    arrayList.add(0, chainBean);
                } else {
                    boolean isDappCustomFull = SpAPI.THIS.isDappCustomFull(IRequest.ENVIRONMENT.toString());
                    boolean isDappChainCustomSol = SpAPI.THIS.isDappChainCustomSol();
                    if (isDappCustomFull) {
                        chainBean.fullNode = SpAPI.THIS.getDappCustomeFull(IRequest.ENVIRONMENT.toString());
                    }
                    if (isDappChainCustomSol) {
                        chainBean.solidityNode = SpAPI.THIS.getDappChainCustomeSolidity();
                    }
                    arrayList.add(chainBean);
                }
            }
            if (!z) {
                for (ChainBean chainBean2 : arrayList) {
                    if (chainBean2 != null && chainBean2.isMainChain) {
                        chainBean2.isSelect = true;
                    }
                }
            }
            SpAPI.THIS.setAllChainJson(arrayList);
            ThreadPoolManager.newInstance().addExecuteTask(new Runnable() {
                @Override
                public final void run() {
                    NodeUtils.1.lambda$onResponse$0();
                }
            });
        }

        public static void lambda$onResponse$0() {
            try {
                IGrpcClient.THIS.initGRpc();
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }

        @Override
        public void onFailure(String str, String str2) {
            NodeUtils.getCacheddata();
        }

        @Override
        public void onSubscribe(Disposable disposable) {
            this.val$manager.add(disposable);
        }
    }

    private static void checkThreadInfo() {
        Thread thread = Looper.getMainLooper().getThread();
        Thread currentThread = Thread.currentThread();
        LogUtils.d("tronlink", "mainThread ID: " + thread.getId());
        LogUtils.d("tronlink", "current Thread ID: " + currentThread.getId());
    }

    public static void getCacheddata() {
        try {
            MainNodeOutput mainNodeOutput = new MainNodeOutput();
            mainNodeOutput.code = 0;
            mainNodeOutput.message = "OK";
            MainNodeOutput.DataBean dataBean = new MainNodeOutput.DataBean();
            dataBean.chainName = "MainChain";
            dataBean.chainId = "";
            dataBean.sideChainContract = "";
            dataBean.mainChainContract = "";
            dataBean.eventServer = "";
            ArrayList arrayList = new ArrayList();
            int testVersion = SpAPI.THIS.getTestVersion();
            String[] strArr = new String[0];
            if (testVersion == 1) {
                strArr = AppContextUtil.getContext().getResources().getStringArray(R.array.main_node_list);
            } else if (testVersion == 2) {
                strArr = AppContextUtil.getContext().getResources().getStringArray(R.array.prorelease_node_list);
            } else if (testVersion == 3) {
                strArr = AppContextUtil.getContext().getResources().getStringArray(R.array.test_node_list);
            } else if (testVersion == 4) {
                strArr = AppContextUtil.getContext().getResources().getStringArray(R.array.nile_node_list);
            } else if (testVersion == 5) {
                strArr = AppContextUtil.getContext().getResources().getStringArray(R.array.shasta_node_list);
            }
            for (String str : strArr) {
                arrayList.add(str);
            }
            dataBean.fullNode = arrayList;
            dataBean.isMainChain = 1;
            MainNodeOutput.DataBean dataBean2 = new MainNodeOutput.DataBean();
            dataBean2.chainName = TronConfig.DApp_CHAIN_NAME;
            dataBean2.chainId = "41E209E4DE650F0150788E8EC5CAFA240A23EB8EB7";
            dataBean2.sideChainContract = "TGKotco6YoULzbYisTBuP6DWXDjEgJSpYz";
            dataBean2.mainChainContract = "TWaPZru6PR5VjgT4sJrrZ481Zgp3iJ8Rfo";
            dataBean2.eventServer = "";
            ArrayList arrayList2 = new ArrayList();
            String[] strArr2 = new String[0];
            if (testVersion == 1) {
                strArr2 = AppContextUtil.getContext().getResources().getStringArray(R.array.main_dapp_node_list);
            } else if (testVersion == 2) {
                strArr2 = AppContextUtil.getContext().getResources().getStringArray(R.array.prorelease_dapp_node_list);
            } else if (testVersion == 3) {
                strArr2 = AppContextUtil.getContext().getResources().getStringArray(R.array.test_dapp_node_list);
            } else if (testVersion == 4) {
                strArr2 = AppContextUtil.getContext().getResources().getStringArray(R.array.nile_dapp_node_list);
            } else if (testVersion == 5) {
                strArr2 = AppContextUtil.getContext().getResources().getStringArray(R.array.shasta_dapp_node_list);
            }
            for (String str2 : strArr2) {
                arrayList2.add(str2);
            }
            dataBean2.fullNode = arrayList2;
            dataBean2.isMainChain = 0;
            mainNodeOutput.data = new ArrayList();
            mainNodeOutput.data.add(dataBean);
            mainNodeOutput.data.add(dataBean2);
            if (mainNodeOutput.data != null) {
                SpAPI.THIS.setMainNodeList(mainNodeOutput.data);
                SpAPI.THIS.setAssetsList(new ArrayList());
                ArrayList<ChainBean> arrayList3 = new ArrayList();
                boolean z = false;
                for (int i = 0; i < mainNodeOutput.data.size(); i++) {
                    ChainBean chainBean = new ChainBean(mainNodeOutput.data.get(i));
                    ChainBean currentChain = SpAPI.THIS.getCurrentChain();
                    if (currentChain != null) {
                        if (currentChain.isMainChain) {
                            if (mainNodeOutput.data.get(i).isMainChain == 1) {
                                chainBean.isSelect = true;
                                z = true;
                            }
                        } else if (currentChain.chainId.equals(mainNodeOutput.data.get(i).chainId)) {
                            chainBean.isSelect = true;
                            z = true;
                        }
                    }
                    if (chainBean.isMainChain) {
                        arrayList3.add(0, chainBean);
                    } else {
                        arrayList3.add(chainBean);
                    }
                }
                if (!z) {
                    for (ChainBean chainBean2 : arrayList3) {
                        if (chainBean2 != null && chainBean2.isMainChain) {
                            chainBean2.isSelect = true;
                        }
                    }
                }
                List<ChainBean> allChainJson = SpAPI.THIS.getAllChainJson();
                if (allChainJson == null || allChainJson.size() == 0) {
                    SpAPI.THIS.setAllChainJson(arrayList3);
                }
                ThreadPoolManager.newInstance().addExecuteTask(new Runnable() {
                    @Override
                    public final void run() {
                        NodeUtils.lambda$getCacheddata$0();
                    }
                });
            }
        } catch (Exception e) {
            SentryUtil.captureException(e);
            LogUtils.e(e);
        }
    }

    public static void lambda$getCacheddata$0() {
        try {
            IGrpcClient.THIS.initGRpc();
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    private static Observable<MainNodeOutput> getMainNodes(String str, RequestBody requestBody) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getMainNodes(str, requestBody).compose(RxSchedulers.io_main());
    }
}
