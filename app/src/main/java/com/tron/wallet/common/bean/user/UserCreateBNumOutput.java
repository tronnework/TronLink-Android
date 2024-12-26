package com.tron.wallet.common.bean.user;
public class UserCreateBNumOutput {
    private int code;
    private DataBean data;
    private String message;

    public static class DataBean {
        private String number;

        public String getNumber() {
            return this.number;
        }

        public void setNumber(String str) {
            this.number = str;
        }
    }

    public int getCode() {
        return this.code;
    }

    public DataBean getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public void setMessage(String str) {
        this.message = str;
    }
}
