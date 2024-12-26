package com.tron.wallet.common.bean.update;
public class UpdateUserCreateBNumOutput {
    private int code;
    private String data;
    private String message;

    public int getCode() {
        return this.code;
    }

    public String getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setData(String str) {
        this.data = str;
    }

    public void setMessage(String str) {
        this.message = str;
    }
}
