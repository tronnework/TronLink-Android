package com.tron.wallet.business.tokendetail.mvp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.tron.wallet.business.addassets2.bean.BaseOutput;
public class TokenSecureInfoOutput extends BaseOutput {
    private TokenSecureInfoDataBean data;

    public TokenSecureInfoDataBean getData() {
        return this.data;
    }

    public void setData(TokenSecureInfoDataBean tokenSecureInfoDataBean) {
        this.data = tokenSecureInfoDataBean;
    }

    @Override
    protected boolean canEqual(Object obj) {
        return obj instanceof TokenSecureInfoOutput;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TokenSecureInfoOutput) {
            TokenSecureInfoOutput tokenSecureInfoOutput = (TokenSecureInfoOutput) obj;
            if (tokenSecureInfoOutput.canEqual(this)) {
                TokenSecureInfoDataBean data = getData();
                TokenSecureInfoDataBean data2 = tokenSecureInfoOutput.getData();
                return data != null ? data.equals(data2) : data2 == null;
            }
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        TokenSecureInfoDataBean data = getData();
        return 59 + (data == null ? 43 : data.hashCode());
    }

    @Override
    public String toString() {
        return "TokenSecureInfoOutput(data=" + getData() + ")";
    }

    public class TokenSecureInfoDataBean {
        @JsonProperty("blackListType")
        private int blackListType;
        @JsonProperty("hasUrl")
        private int hasUrl;
        @JsonProperty("increaseTotalSupply")
        private int increaseTotalSupply;
        @JsonProperty("isProxy")
        private int isProxy;
        @JsonProperty(FirebaseAnalytics.Param.LEVEL)
        private int level;
        @JsonProperty("openSource")
        private int openSource;

        public int getBlackListType() {
            return this.blackListType;
        }

        public int getHasUrl() {
            return this.hasUrl;
        }

        public int getIncreaseTotalSupply() {
            return this.increaseTotalSupply;
        }

        public int getIsProxy() {
            return this.isProxy;
        }

        public int getLevel() {
            return this.level;
        }

        public int getOpenSource() {
            return this.openSource;
        }

        @JsonProperty("blackListType")
        public void setBlackListType(int i) {
            this.blackListType = i;
        }

        @JsonProperty("hasUrl")
        public void setHasUrl(int i) {
            this.hasUrl = i;
        }

        @JsonProperty("increaseTotalSupply")
        public void setIncreaseTotalSupply(int i) {
            this.increaseTotalSupply = i;
        }

        @JsonProperty("isProxy")
        public void setIsProxy(int i) {
            this.isProxy = i;
        }

        @JsonProperty(FirebaseAnalytics.Param.LEVEL)
        public void setLevel(int i) {
            this.level = i;
        }

        @JsonProperty("openSource")
        public void setOpenSource(int i) {
            this.openSource = i;
        }

        public TokenSecureInfoDataBean() {
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof TokenSecureInfoDataBean;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof TokenSecureInfoDataBean) {
                TokenSecureInfoDataBean tokenSecureInfoDataBean = (TokenSecureInfoDataBean) obj;
                return tokenSecureInfoDataBean.canEqual(this) && getBlackListType() == tokenSecureInfoDataBean.getBlackListType() && getIncreaseTotalSupply() == tokenSecureInfoDataBean.getIncreaseTotalSupply() && getOpenSource() == tokenSecureInfoDataBean.getOpenSource() && getIsProxy() == tokenSecureInfoDataBean.getIsProxy() && getLevel() == tokenSecureInfoDataBean.getLevel() && getHasUrl() == tokenSecureInfoDataBean.getHasUrl();
            }
            return false;
        }

        public int hashCode() {
            return ((((((((((getBlackListType() + 59) * 59) + getIncreaseTotalSupply()) * 59) + getOpenSource()) * 59) + getIsProxy()) * 59) + getLevel()) * 59) + getHasUrl();
        }

        public String toString() {
            return "TokenSecureInfoOutput.TokenSecureInfoDataBean(blackListType=" + getBlackListType() + ", increaseTotalSupply=" + getIncreaseTotalSupply() + ", openSource=" + getOpenSource() + ", isProxy=" + getIsProxy() + ", level=" + getLevel() + ", hasUrl=" + getHasUrl() + ")";
        }
    }
}
