package com.tron.wallet.business.addassets2.bean;
public class MyAssetsOutput extends BaseOutput {
    private AssetsData data;

    public AssetsData getData() {
        return this.data;
    }

    public void setData(AssetsData assetsData) {
        this.data = assetsData;
    }

    @Override
    public String toString() {
        return "MyAssetsOutput(super=" + super.toString() + ", data=" + getData() + ")";
    }

    @Override
    protected boolean canEqual(Object obj) {
        return obj instanceof MyAssetsOutput;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MyAssetsOutput) {
            MyAssetsOutput myAssetsOutput = (MyAssetsOutput) obj;
            if (myAssetsOutput.canEqual(this) && super.equals(obj)) {
                AssetsData data = getData();
                AssetsData data2 = myAssetsOutput.getData();
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
