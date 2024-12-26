package com.tron.wallet.net.rpc;

import android.text.TextUtils;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.bean.ChainBean;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.SpAPI;
import com.tronlinkpro.wallet.R;
public enum IGrpcClient {
    THIS;
    
    private String TagName = "IGrpcClient";
    private GrpcClient rpcCli;

    IGrpcClient() {
    }

    public GrpcClient getCli() {
        LogUtils.i(this.TagName, " getCli");
        if (this.rpcCli == null) {
            initGRpc();
        }
        if (!this.rpcCli.canUseFullNode()) {
            this.rpcCli.connectFullNode();
        }
        return this.rpcCli;
    }

    public GrpcClient getMainCli() {
        LogUtils.i(this.TagName, " getMainCli");
        if (this.rpcCli == null) {
            initGRpc();
        }
        if (!this.rpcCli.canUseFullNode()) {
            this.rpcCli.connectFullNode();
        }
        return this.rpcCli;
    }

    public String getCurrentFullNode() {
        LogUtils.i(this.TagName, "getCurrentFullnode");
        if (this.rpcCli == null) {
            initGRpc();
        }
        GrpcClient grpcClient = this.rpcCli;
        return grpcClient != null ? grpcClient.getCurrentFullNode() : "";
    }

    public void connectFullNode(String str) {
        String str2 = this.TagName;
        StringBuilder sb = new StringBuilder("connectFullNode ");
        sb.append(str);
        sb.append("   ");
        sb.append(this.rpcCli == null);
        LogUtils.i(str2, sb.toString());
        if (this.rpcCli == null) {
            initGRpc(str, null);
        }
        GrpcClient grpcClient = this.rpcCli;
        if (grpcClient != null) {
            grpcClient.connectFullNode(str);
        }
    }

    public boolean getGrpcState() {
        LogUtils.i(this.TagName, " getGrpcState");
        try {
            return this.rpcCli.canUseFullNode();
        } catch (Exception e) {
            SentryUtil.captureException(e);
            LogUtils.e(e);
            return false;
        }
    }

    public void initGRpc() {
        LogUtils.i(this.TagName, " initGRpc");
        GrpcClient grpcClient = this.rpcCli;
        if (grpcClient != null) {
            try {
                grpcClient.shutdown();
            } catch (InterruptedException e) {
                LogUtils.e(e);
            }
        }
        String str = "";
        try {
            if (SpAPI.THIS.isShasta()) {
                str = AppContextUtil.getContext().getResources().getStringArray(R.array.shasta_node_list)[0];
            } else {
                ChainBean currentChain = SpAPI.THIS.getCurrentChain();
                if (currentChain != null && !currentChain.isMainChain) {
                    if (SpAPI.THIS.isDappCustomFull()) {
                        str = SpAPI.THIS.getDappCustomeFull();
                    }
                } else if (SpAPI.THIS.isCustomFull()) {
                    str = SpAPI.THIS.getCustomeFull();
                }
            }
        } catch (Exception e2) {
            LogUtils.e(e2);
        }
        this.rpcCli = new GrpcClient();
        if (StringTronUtil.isEmpty(str)) {
            this.rpcCli.connectFullNode();
        } else {
            this.rpcCli.connectFullNode(str);
        }
    }

    public void initGRpc(String str, String str2) {
        String str3 = this.TagName;
        LogUtils.i(str3, "initGRpc fullnode  solidNode   " + str + "   " + str2);
        if (StringTronUtil.isEmpty(str) && !StringTronUtil.isEmpty(str2)) {
            str = getCurrentFullNode();
            if (TextUtils.isEmpty(str2)) {
                str2 = SpAPI.THIS.getCurrentChain().fullNode;
            }
        }
        if (StringTronUtil.isEmpty(str, str2)) {
            return;
        }
        GrpcClient grpcClient = this.rpcCli;
        if (grpcClient != null) {
            try {
                grpcClient.shutdown();
            } catch (InterruptedException e) {
                LogUtils.e(e);
            }
        } else {
            this.rpcCli = new GrpcClient();
        }
        GrpcClient grpcClient2 = this.rpcCli;
        if (grpcClient2 != null) {
            grpcClient2.connectFullNode(str);
        }
    }
}
