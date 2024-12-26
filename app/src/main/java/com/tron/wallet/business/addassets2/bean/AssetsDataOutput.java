package com.tron.wallet.business.addassets2.bean;
public class AssetsDataOutput extends BaseOutput {
    private AssetsData data;

    public AssetsData getData() {
        return this.data;
    }

    public void setData(AssetsData assetsData) {
        this.data = assetsData;
    }

    @Override
    public String toString() {
        return "AssetsDataOutput(super=" + super.toString() + ", data=" + getData() + ")";
    }

    @Override
    protected boolean canEqual(Object obj) {
        return obj instanceof AssetsDataOutput;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AssetsDataOutput) {
            AssetsDataOutput assetsDataOutput = (AssetsDataOutput) obj;
            if (assetsDataOutput.canEqual(this) && super.equals(obj)) {
                AssetsData data = getData();
                AssetsData data2 = assetsDataOutput.getData();
                return data != null ? data.equals(data2) : data2 == null;
            }
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hashCode = super.hashCode();
        AssetsData data = getData();
        return (hashCode * 59) + (data == null ? 43 : data.hashCode());
    }
}
