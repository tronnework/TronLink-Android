package com.tron.wallet.common.utils;

import android.content.Context;
import com.google.firebase.perf.network.FirebasePerfOkHttpClient;
import com.google.gson.Gson;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.OkHttpManager;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.SpUtils;
import com.tron.wallet.business.tabmy.myhome.settings.bean.NodeInfoBean;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.net.rpc.IGrpcClient;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
public class ChainUtil {
    public static final String ChainInfo = "chainNodeInfo";
    public static final String ChainNodes = "chainnodes";
    public static final int ENVIRONMENT_NILE = 4;
    public static final int ENVIRONMENT_ONLINE = 1;
    public static final int ENVIRONMENT_PRE = 2;
    public static final int ENVIRONMENT_SHASTA = 5;
    public static final int ENVIRONMENT_TEST = 3;
    public static final String MainChain = "MainChain";
    public static final String MainChain_P2PVersion = "11111";
    public static final String MainDappChain = "MainChain";
    public static final String MainDappChain_P2PVersion = "201909251";
    public static final String NileChain = "NileChain";
    public static final String NileChain_P2PVersion = "201910292";
    public static final String NileChain_VersionName = "Odyssey-v3.6.5-15-g6405454d0";
    public static final String NileChain_VersionNum = "11188";
    public static final String NileDappChain = "NileDappChain";
    public static final String NileDappChain_P2PVersion = "201912031";
    public static final String NileDappChain_VersionName = "Sun-network";
    public static final String NileDappChain_VersionNum = "911";
    public static final String Request_HTTP = "http://";
    public static final String Request_Path = ":8090/wallet/getnodeinfo";
    private static ChainType chainType = ChainType.NULL;

    enum ChainType {
        NULL,
        ONLINE,
        NILE
    }

    public interface onChainInfoInterface {
        void onChainNodeInfo(NodeInfoBean nodeInfoBean);

        void onFail();

        void onLocalTure();
    }

    public static boolean isNileChain() {
        return false;
    }

    private static boolean isCurrentChain(String str) {
        NodeInfoBean nodeInfoBean;
        String currentFullNode = IGrpcClient.THIS.getCurrentFullNode();
        if (currentFullNode != null) {
            Context context = AppContextUtil.getContext();
            HashSet hashSet = (HashSet) SpUtils.getParam(ChainInfo, context, IRequest.ENVIRONMENT.toString() + "_NileChainchainnodes", new HashSet());
            if (currentFullNode.contains(":")) {
                currentFullNode = currentFullNode.substring(0, currentFullNode.indexOf(":"));
            }
            if (hashSet.contains(currentFullNode)) {
                chainType = ChainType.NILE;
                return true;
            } else if (com.tron.tron_base.frame.utils.StringTronUtil.isEmpty(currentFullNode)) {
                chainType = ChainType.NULL;
                return false;
            } else {
                OkHttpClient build = OkHttpManager.getInstance().build();
                try {
                    Response execute = FirebasePerfOkHttpClient.execute(build.newCall(new Request.Builder().url(Request_HTTP + currentFullNode + Request_Path).get().build()));
                    if (execute != null) {
                        try {
                            nodeInfoBean = (NodeInfoBean) new Gson().fromJson(execute.body().string(),  NodeInfoBean.class);
                        } catch (Exception e) {
                            LogUtils.e(e);
                            nodeInfoBean = null;
                        }
                        if (nodeInfoBean != null && nodeInfoBean.getConfigNodeInfo() != null) {
                            NodeInfoBean.ConfigNodeInfoBean configNodeInfo = nodeInfoBean.getConfigNodeInfo();
                            HashSet hashSet2 = new HashSet();
                            if (NileChain_P2PVersion.equals(configNodeInfo.getP2pVersion())) {
                                for (NodeInfoBean.PeerListBean peerListBean : nodeInfoBean.getPeerList()) {
                                    if (!hashSet2.contains(peerListBean.getHost()) && peerListBean.isActive()) {
                                        hashSet2.add(peerListBean.getHost());
                                    }
                                }
                                if (!hashSet2.contains(currentFullNode)) {
                                    hashSet2.add(currentFullNode);
                                }
                                Context context2 = AppContextUtil.getContext();
                                SpUtils.setParam(ChainInfo, context2, IRequest.ENVIRONMENT.toString() + "_" + NileChain + ChainNodes, hashSet2);
                                if (NileChain.equals(str)) {
                                    chainType = ChainType.NILE;
                                    return true;
                                }
                            } else if (NileDappChain_P2PVersion.equals(configNodeInfo.getP2pVersion())) {
                                List<NodeInfoBean.PeerListBean> peerList = nodeInfoBean.getPeerList();
                                HashSet hashSet3 = new HashSet();
                                for (NodeInfoBean.PeerListBean peerListBean2 : peerList) {
                                    if (!hashSet3.contains(peerListBean2.getHost()) && peerListBean2.isActive()) {
                                        hashSet3.add(peerListBean2.getHost());
                                    }
                                }
                                if (!hashSet3.contains(currentFullNode)) {
                                    hashSet3.add(currentFullNode);
                                }
                                Context context3 = AppContextUtil.getContext();
                                SpUtils.setParam(ChainInfo, context3, IRequest.ENVIRONMENT.toString() + "_" + NileDappChain + ChainNodes, hashSet3);
                                if (NileDappChain.equals(str)) {
                                    chainType = ChainType.NILE;
                                    return true;
                                }
                            } else if (MainChain_P2PVersion.equals(configNodeInfo.getP2pVersion())) {
                                List<NodeInfoBean.PeerListBean> peerList2 = nodeInfoBean.getPeerList();
                                HashSet hashSet4 = new HashSet();
                                for (NodeInfoBean.PeerListBean peerListBean3 : peerList2) {
                                    if (!hashSet4.contains(peerListBean3.getHost()) && peerListBean3.isActive()) {
                                        hashSet4.add(peerListBean3.getHost());
                                    }
                                }
                                if (!hashSet4.contains(currentFullNode)) {
                                    hashSet4.add(currentFullNode);
                                }
                                Context context4 = AppContextUtil.getContext();
                                SpUtils.setParam(ChainInfo, context4, IRequest.ENVIRONMENT.toString() + "_MainChain" + ChainNodes, hashSet4);
                                if ("MainChain".equals(str)) {
                                    chainType = ChainType.NILE;
                                    return true;
                                }
                            }
                        }
                    }
                } catch (IOException e2) {
                    LogUtils.e(e2);
                } catch (Exception e3) {
                    LogUtils.e(e3);
                }
            }
        }
        chainType = ChainType.ONLINE;
        return false;
    }

    public static String getChainNameByEnvironment() {
        if (IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.NILETEST) {
            return NileChain;
        }
        if (IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.RELEASE || IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.PRE_RELEASE || IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.SHASTA) {
            return "MainChain";
        }
        IRequest.NET_ENVIRONMENT net_environment = IRequest.ENVIRONMENT;
        IRequest.NET_ENVIRONMENT net_environment2 = IRequest.NET_ENVIRONMENT.TEST;
        return "MainChain";
    }

    public static String getChainNameByEnvInt() {
        return SpAPI.THIS.getTestVersion() != 4 ? "MainChain" : NileChain;
    }
}
