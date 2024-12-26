package com.tron.wallet.common.bean;
public class TrxPriceOutput {
    public int code;
    public DataBean data;
    public String message;

    public class DataBean {
        public String price_cny;
        public String price_usd;

        public DataBean() {
        }
    }
}
