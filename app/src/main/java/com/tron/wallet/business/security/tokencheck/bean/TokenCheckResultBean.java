package com.tron.wallet.business.security.tokencheck.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
public class TokenCheckResultBean implements Serializable {
    private int code;
    private CheckListBean data;
    private String message;

    public int getCode() {
        return this.code;
    }

    public CheckListBean getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setData(CheckListBean checkListBean) {
        this.data = checkListBean;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof TokenCheckResultBean;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TokenCheckResultBean) {
            TokenCheckResultBean tokenCheckResultBean = (TokenCheckResultBean) obj;
            if (tokenCheckResultBean.canEqual(this) && getCode() == tokenCheckResultBean.getCode()) {
                String message = getMessage();
                String message2 = tokenCheckResultBean.getMessage();
                if (message != null ? message.equals(message2) : message2 == null) {
                    CheckListBean data = getData();
                    CheckListBean data2 = tokenCheckResultBean.getData();
                    return data != null ? data.equals(data2) : data2 == null;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        String message = getMessage();
        int code = ((getCode() + 59) * 59) + (message == null ? 43 : message.hashCode());
        CheckListBean data = getData();
        return (code * 59) + (data != null ? data.hashCode() : 43);
    }

    public String toString() {
        return "TokenCheckResultBean(code=" + getCode() + ", message=" + getMessage() + ", data=" + getData() + ")";
    }

    public class CheckListBean implements Serializable {
        @JsonProperty("highRiskList")
        private List<TokenCheckBean> highRiskList;
        @JsonProperty("mediumRiskList")
        private List<TokenCheckBean> mediumRiskList;

        public List<TokenCheckBean> getHighRiskList() {
            return this.highRiskList;
        }

        public List<TokenCheckBean> getMediumRiskList() {
            return this.mediumRiskList;
        }

        @JsonProperty("highRiskList")
        public void setHighRiskList(List<TokenCheckBean> list) {
            this.highRiskList = list;
        }

        @JsonProperty("mediumRiskList")
        public void setMediumRiskList(List<TokenCheckBean> list) {
            this.mediumRiskList = list;
        }

        public CheckListBean() {
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof CheckListBean;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof CheckListBean) {
                CheckListBean checkListBean = (CheckListBean) obj;
                if (checkListBean.canEqual(this)) {
                    List<TokenCheckBean> highRiskList = getHighRiskList();
                    List<TokenCheckBean> highRiskList2 = checkListBean.getHighRiskList();
                    if (highRiskList != null ? highRiskList.equals(highRiskList2) : highRiskList2 == null) {
                        List<TokenCheckBean> mediumRiskList = getMediumRiskList();
                        List<TokenCheckBean> mediumRiskList2 = checkListBean.getMediumRiskList();
                        return mediumRiskList != null ? mediumRiskList.equals(mediumRiskList2) : mediumRiskList2 == null;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            List<TokenCheckBean> highRiskList = getHighRiskList();
            int hashCode = highRiskList == null ? 43 : highRiskList.hashCode();
            List<TokenCheckBean> mediumRiskList = getMediumRiskList();
            return ((hashCode + 59) * 59) + (mediumRiskList != null ? mediumRiskList.hashCode() : 43);
        }

        public String toString() {
            return "TokenCheckResultBean.CheckListBean(highRiskList=" + getHighRiskList() + ", mediumRiskList=" + getMediumRiskList() + ")";
        }
    }
}
