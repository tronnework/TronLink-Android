package com.tron.wallet.business.pull.triggersmartcontract;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import com.tron.wallet.business.pull.PullParam;
public class TriggerSmartContractParam extends PullParam {
    @SerializedName("data")
    private String data;
    @SerializedName(FirebaseAnalytics.Param.METHOD)
    private String method;
    @SerializedName("signType")
    private String signType;

    public String getData() {
        return this.data;
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

    public void setMethod(String str) {
        this.method = str;
    }

    public void setSignType(String str) {
        this.signType = str;
    }

    @Override
    protected boolean canEqual(Object obj) {
        return obj instanceof TriggerSmartContractParam;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TriggerSmartContractParam) {
            TriggerSmartContractParam triggerSmartContractParam = (TriggerSmartContractParam) obj;
            if (triggerSmartContractParam.canEqual(this)) {
                String data = getData();
                String data2 = triggerSmartContractParam.getData();
                if (data != null ? data.equals(data2) : data2 == null) {
                    String method = getMethod();
                    String method2 = triggerSmartContractParam.getMethod();
                    if (method != null ? method.equals(method2) : method2 == null) {
                        String signType = getSignType();
                        String signType2 = triggerSmartContractParam.getSignType();
                        return signType != null ? signType.equals(signType2) : signType2 == null;
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
        String method = getMethod();
        int hashCode2 = ((hashCode + 59) * 59) + (method == null ? 43 : method.hashCode());
        String signType = getSignType();
        return (hashCode2 * 59) + (signType != null ? signType.hashCode() : 43);
    }

    @Override
    public String toString() {
        return "TriggerSmartContractParam(data=" + getData() + ", method=" + getMethod() + ", signType=" + getSignType() + ")";
    }
}
