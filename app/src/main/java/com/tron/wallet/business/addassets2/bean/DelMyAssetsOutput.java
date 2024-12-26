package com.tron.wallet.business.addassets2.bean;
public class DelMyAssetsOutput extends BaseOutput {
    public boolean data;

    public boolean isData() {
        return this.data;
    }

    public void setData(boolean z) {
        this.data = z;
    }

    @Override
    public String toString() {
        return "DelMyAssetsOutput(super=" + super.toString() + ", data=" + isData() + ")";
    }

    @Override
    protected boolean canEqual(Object obj) {
        return obj instanceof DelMyAssetsOutput;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DelMyAssetsOutput) {
            DelMyAssetsOutput delMyAssetsOutput = (DelMyAssetsOutput) obj;
            return delMyAssetsOutput.canEqual(this) && super.equals(obj) && isData() == delMyAssetsOutput.isData();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (super.hashCode() * 59) + (isData() ? 79 : 97);
    }
}
