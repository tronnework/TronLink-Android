package com.tron.wallet.business.customtokens.bean;

import com.tron.wallet.business.addassets2.bean.BaseOutput;
public class QueryCustomTokenOutput extends BaseOutput {
    private CustomTokenBean data;

    public CustomTokenBean getData() {
        return this.data;
    }

    public void setData(CustomTokenBean customTokenBean) {
        this.data = customTokenBean;
    }

    @Override
    public String toString() {
        return "QueryCustomTokenOutput(super=" + super.toString() + ", data=" + getData() + ")";
    }

    @Override
    protected boolean canEqual(Object obj) {
        return obj instanceof QueryCustomTokenOutput;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof QueryCustomTokenOutput) {
            QueryCustomTokenOutput queryCustomTokenOutput = (QueryCustomTokenOutput) obj;
            if (queryCustomTokenOutput.canEqual(this) && super.equals(obj)) {
                CustomTokenBean data = getData();
                CustomTokenBean data2 = queryCustomTokenOutput.getData();
                return data != null ? data.equals(data2) : data2 == null;
            }
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hashCode = super.hashCode();
        CustomTokenBean data = getData();
        return (hashCode * 59) + (data == null ? 43 : data.hashCode());
    }
}
