package com.tron.wallet.business.finance.bean;

import com.google.gson.JsonElement;
public class FinanceDataBean {
    private int code;
    private JsonElement data;
    private String message;

    public int getCode() {
        return this.code;
    }

    public JsonElement getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setData(JsonElement jsonElement) {
        this.data = jsonElement;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof FinanceDataBean;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FinanceDataBean) {
            FinanceDataBean financeDataBean = (FinanceDataBean) obj;
            if (financeDataBean.canEqual(this) && getCode() == financeDataBean.getCode()) {
                String message = getMessage();
                String message2 = financeDataBean.getMessage();
                if (message != null ? message.equals(message2) : message2 == null) {
                    JsonElement data = getData();
                    JsonElement data2 = financeDataBean.getData();
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
        JsonElement data = getData();
        return (code * 59) + (data != null ? data.hashCode() : 43);
    }

    public String toString() {
        return "FinanceDataBean(code=" + getCode() + ", message=" + getMessage() + ", data=" + getData() + ")";
    }
}
