package com.tron.wallet.business.customtokens.bean;

import com.tron.wallet.business.addassets2.bean.BaseOutput;
public class AddCustomTokenOutput extends BaseOutput {
    private boolean data;

    public boolean isData() {
        return this.data;
    }

    public void setData(boolean z) {
        this.data = z;
    }

    @Override
    public String toString() {
        return "AddCustomTokenOutput(super=" + super.toString() + ", data=" + isData() + ")";
    }

    @Override
    protected boolean canEqual(Object obj) {
        return obj instanceof AddCustomTokenOutput;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AddCustomTokenOutput) {
            AddCustomTokenOutput addCustomTokenOutput = (AddCustomTokenOutput) obj;
            return addCustomTokenOutput.canEqual(this) && super.equals(obj) && isData() == addCustomTokenOutput.isData();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (super.hashCode() * 59) + (isData() ? 79 : 97);
    }
}
