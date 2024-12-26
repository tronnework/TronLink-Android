package com.tron.wallet.business.pull.sign;

import com.tron.wallet.business.pull.transfer.TransferParam;
public class SignParam extends TransferParam {
    private String data;
    private String message;
    private String method;
    private String signType;

    public String getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public String getMethod() {
        return this.method;
    }

    public String getSignType() {
        return this.signType;
    }

    public void setData(String str) {
        this.data = str;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setMethod(String str) {
        this.method = str;
    }

    public void setSignType(String str) {
        this.signType = str;
    }

    @Override
    protected boolean canEqual(Object obj) {
        return obj instanceof SignParam;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SignParam) {
            SignParam signParam = (SignParam) obj;
            if (signParam.canEqual(this)) {
                String data = getData();
                String data2 = signParam.getData();
                if (data != null ? data.equals(data2) : data2 == null) {
                    String signType = getSignType();
                    String signType2 = signParam.getSignType();
                    if (signType != null ? signType.equals(signType2) : signType2 == null) {
                        String method = getMethod();
                        String method2 = signParam.getMethod();
                        if (method != null ? method.equals(method2) : method2 == null) {
                            String message = getMessage();
                            String message2 = signParam.getMessage();
                            return message != null ? message.equals(message2) : message2 == null;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        String data = getData();
        int hashCode = data == null ? 43 : data.hashCode();
        String signType = getSignType();
        int hashCode2 = ((hashCode + 59) * 59) + (signType == null ? 43 : signType.hashCode());
        String method = getMethod();
        int hashCode3 = (hashCode2 * 59) + (method == null ? 43 : method.hashCode());
        String message = getMessage();
        return (hashCode3 * 59) + (message != null ? message.hashCode() : 43);
    }

    @Override
    public String toString() {
        return "SignParam(data=" + getData() + ", signType=" + getSignType() + ", method=" + getMethod() + ", message=" + getMessage() + ")";
    }
}
