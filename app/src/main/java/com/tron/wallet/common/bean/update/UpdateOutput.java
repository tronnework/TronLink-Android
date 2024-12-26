package com.tron.wallet.common.bean.update;
public class UpdateOutput {
    public int code;
    public DataBean data;
    public String msg;

    public static class DataBean {
        public boolean force;
        public String internal_apk_md5;
        public String internal_apk_rsa;
        public String internal_update_url;
        public String internal_update_url_cn;
        public boolean jump_google_play;
        public String statement;
        public String strategy;
        public String title;
        public boolean upgrade;
        public String url;
        public String version;
    }
}
