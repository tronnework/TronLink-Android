package com.tron.wallet.business.customtokens.bean;

import com.tron.wallet.business.addassets2.bean.BaseOutput;
import com.tron.wallet.common.bean.token.TokenBean;
public class SyncCustomTokenOutput extends BaseOutput {
    private TokenBean data;

    public TokenBean getData() {
        return this.data;
    }

    public void setData(TokenBean tokenBean) {
        this.data = tokenBean;
    }

    @Override
    public String toString() {
        return "SyncCustomTokenOutput(super=" + super.toString() + ", data=" + getData() + ")";
    }

    @Override
    protected boolean canEqual(Object obj) {
        return obj instanceof SyncCustomTokenOutput;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SyncCustomTokenOutput) {
            SyncCustomTokenOutput syncCustomTokenOutput = (SyncCustomTokenOutput) obj;
            if (syncCustomTokenOutput.canEqual(this) && super.equals(obj)) {
                TokenBean data = getData();
                TokenBean data2 = syncCustomTokenOutput.getData();
                return data != null ? data.equals(data2) : data2 == null;
            }
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hashCode = super.hashCode();
        TokenBean data = getData();
        return (hashCode * 59) + (data == null ? 43 : data.hashCode());
    }
}
