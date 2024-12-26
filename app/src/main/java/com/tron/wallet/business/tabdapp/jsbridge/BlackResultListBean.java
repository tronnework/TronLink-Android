package com.tron.wallet.business.tabdapp.jsbridge;

import com.google.gson.annotations.SerializedName;
import com.tron.wallet.business.pull.PullConstants;
import java.util.List;
public class BlackResultListBean {
    @SerializedName("code")
    private int code;
    @SerializedName("data")
    private List<String> data;
    @SerializedName(PullConstants.RESULT_MESSAGE)
    private String message;

    public int getCode() {
        return this.code;
    }

    public List<String> getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setData(List<String> list) {
        this.data = list;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof BlackResultListBean;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof BlackResultListBean) {
            BlackResultListBean blackResultListBean = (BlackResultListBean) obj;
            if (blackResultListBean.canEqual(this) && getCode() == blackResultListBean.getCode()) {
                String message = getMessage();
                String message2 = blackResultListBean.getMessage();
                if (message != null ? message.equals(message2) : message2 == null) {
                    List<String> data = getData();
                    List<String> data2 = blackResultListBean.getData();
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
        List<String> data = getData();
        return (code * 59) + (data != null ? data.hashCode() : 43);
    }

    public String toString() {
        return "BlackResultListBean(code=" + getCode() + ", message=" + getMessage() + ", data=" + getData() + ")";
    }
}
