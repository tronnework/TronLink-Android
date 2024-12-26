package com.tron.wallet.business.tabmy.proposals.bean;
public class SrDetailResponse {
    private DataBean data;
    private String success;

    public static class DataBean {
        private int blockReward;

        public int getBlockReward() {
            return this.blockReward;
        }

        public void setBlockReward(int i) {
            this.blockReward = i;
        }
    }

    public DataBean getData() {
        return this.data;
    }

    public String getSuccess() {
        return this.success;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public void setSuccess(String str) {
        this.success = str;
    }
}
