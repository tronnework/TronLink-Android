package com.tron.wallet.common.bean.user;
public class SystemConfigOutput {
    public int code;
    public DataBean data;
    public String message;

    public class DataBean {
        public String tronscanDappChain;
        public String tronscanUrl;
        public boolean usingCrtFile2020;

        public DataBean() {
        }
    }
}
