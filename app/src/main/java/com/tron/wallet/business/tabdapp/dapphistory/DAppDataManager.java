package com.tron.wallet.business.tabdapp.dapphistory;

import com.google.gson.annotations.SerializedName;
import com.tron.wallet.business.tabdapp.dapphistory.bean.DAppDataBean;
import java.util.HashMap;
import java.util.Map;
public class DAppDataManager {
    private static Map<String, DAppDataBean> dAppDataBeanCache = new HashMap();
    private static DAppDataManager instance;

    private DAppDataManager() {
    }

    public static DAppDataManager getInstance() {
        if (instance == null) {
            synchronized (DAppDataManager.class) {
                if (instance == null) {
                    instance = new DAppDataManager();
                }
            }
        }
        return instance;
    }

    public void putData(String str, DAppDataBean dAppDataBean) {
        dAppDataBeanCache.put(str, dAppDataBean);
    }

    public DAppDataBean getData(String str) {
        return dAppDataBeanCache.get(str);
    }

    public void clearDataCache() {
        dAppDataBeanCache.clear();
    }

    public boolean collectAble(DAppDataBean dAppDataBean) {
        return (dAppDataBean == null || dAppDataBean.getClassifyId() == 0) ? false : true;
    }

    public Builder getBuilder() {
        return new Builder();
    }

    public class Builder {
        private String address;
        @SerializedName("classify_id")
        private int classifyId;
        @SerializedName("home_url")
        private String homeUrl;
        private String icon;
        private int id;
        @SerializedName("image_url")
        private String imageUrl;
        @SerializedName("is_anonymous")
        private int isAnonymous;
        @SerializedName("is_authorized")
        private int isAuthorized;
        @SerializedName("is_white")
        private int isWhite;
        private String name;
        @SerializedName("horiz_vert")
        private int screenOrder = 1;
        private String slogan;
        private long timeStamp;
        private String title;

        public Builder setAddress(String str) {
            this.address = str;
            return this;
        }

        public Builder setClassifyId(int i) {
            this.classifyId = i;
            return this;
        }

        public Builder setHomeUrl(String str) {
            this.homeUrl = str;
            return this;
        }

        public Builder setIcon(String str) {
            this.icon = str;
            return this;
        }

        public Builder setId(int i) {
            this.id = i;
            return this;
        }

        public Builder setImageUrl(String str) {
            this.imageUrl = str;
            return this;
        }

        public Builder setIsAnonymous(int i) {
            this.isAnonymous = i;
            return this;
        }

        public Builder setIsAuthorized(int i) {
            this.isAuthorized = i;
            return this;
        }

        public Builder setIsWhite(int i) {
            this.isWhite = i;
            return this;
        }

        public Builder setName(String str) {
            this.name = str;
            return this;
        }

        public Builder setScreenOrder(int i) {
            this.screenOrder = i;
            return this;
        }

        public Builder setSlogan(String str) {
            this.slogan = str;
            return this;
        }

        public Builder setTimeStamp(long j) {
            this.timeStamp = j;
            return this;
        }

        public Builder setTitle(String str) {
            this.title = str;
            return this;
        }

        public Builder() {
        }

        public DAppDataBean buildDAppDataBean() {
            DAppDataBean dAppDataBean = new DAppDataBean();
            dAppDataBean.setId(this.id);
            dAppDataBean.setAddress(this.address);
            dAppDataBean.setClassifyId(this.classifyId);
            dAppDataBean.setName(this.name);
            dAppDataBean.setImageUrl(this.imageUrl);
            dAppDataBean.setHomeUrl(this.homeUrl);
            dAppDataBean.setSlogan(this.slogan);
            dAppDataBean.setIsWhite(this.isWhite);
            dAppDataBean.setIsAnonymous(this.isAnonymous);
            dAppDataBean.setScreenOrder(this.screenOrder);
            dAppDataBean.setTitle(this.title);
            dAppDataBean.setIcon(this.icon);
            dAppDataBean.setTimeStamp(this.timeStamp);
            return dAppDataBean;
        }
    }
}
