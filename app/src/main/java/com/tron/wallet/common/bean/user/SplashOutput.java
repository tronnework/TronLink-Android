package com.tron.wallet.common.bean.user;
public class SplashOutput {
    public String code;
    public DataBean data;

    public static class DataBean {
        public String imagePath;
        public String picType;
        public long timestamp;
        public String url;
    }
}
