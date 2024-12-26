package com.tron.wallet.business.tabmy.myhome.settings.bean;

import java.io.Serializable;
import java.util.List;
public class MainNodeOutput {
    public int code;
    public List<DataBean> data;
    public String message;

    public static class DataBean implements Serializable {
        public String chainId;
        public String chainName;
        public String eventServer;
        public List<String> fullNode;
        public int isMainChain;
        public String mainChainContract;
        public long serviceTime;
        public String sideChainContract;
        public List<String> solidityNode;

        public String toString() {
            return "DataBean{chainName='" + this.chainName + "', chainId='" + this.chainId + "', sideChainContract='" + this.sideChainContract + "', mainChainContract='" + this.mainChainContract + "', eventServer='" + this.eventServer + "', fullNode=" + this.fullNode + ", solidityNode=" + this.solidityNode + ", isMainChain=" + this.isMainChain + '}';
        }
    }

    public String toString() {
        return "MainNodeOutput{code=" + this.code + ", message='" + this.message + "', data=" + this.data + '}';
    }
}
