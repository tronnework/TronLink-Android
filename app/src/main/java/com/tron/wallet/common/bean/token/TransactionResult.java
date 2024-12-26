package com.tron.wallet.common.bean.token;
public class TransactionResult {
    private int code;
    private Object data;
    private String message;

    public int getCode() {
        return this.code;
    }

    public Object getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setData(Object obj) {
        this.data = obj;
    }

    public void setMessage(String str) {
        this.message = str;
    }
}
