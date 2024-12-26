package com.tron.wallet.business.addassets2.bean;
public class NftDataOutput extends BaseOutput {
    private NftData data;

    public NftData getData() {
        return this.data;
    }

    public void setData(NftData nftData) {
        this.data = nftData;
    }

    @Override
    public String toString() {
        return "NftDataOutput(super=" + super.toString() + ", data=" + getData() + ")";
    }

    @Override
    protected boolean canEqual(Object obj) {
        return obj instanceof NftDataOutput;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof NftDataOutput) {
            NftDataOutput nftDataOutput = (NftDataOutput) obj;
            if (nftDataOutput.canEqual(this) && super.equals(obj)) {
                NftData data = getData();
                NftData data2 = nftDataOutput.getData();
                return data != null ? data.equals(data2) : data2 == null;
            }
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hashCode = super.hashCode();
        NftData data = getData();
        return (hashCode * 59) + (data == null ? 43 : data.hashCode());
    }
}
