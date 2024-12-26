package com.tron.wallet.business.addassets2.bean;
public class FollowAssetsOutput extends BaseOutput {
    public boolean data;

    public boolean isData() {
        return this.data;
    }

    public void setData(boolean z) {
        this.data = z;
    }

    @Override
    public String toString() {
        return "FollowAssetsOutput(super=" + super.toString() + ", data=" + isData() + ")";
    }

    @Override
    protected boolean canEqual(Object obj) {
        return obj instanceof FollowAssetsOutput;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FollowAssetsOutput) {
            FollowAssetsOutput followAssetsOutput = (FollowAssetsOutput) obj;
            return followAssetsOutput.canEqual(this) && super.equals(obj) && isData() == followAssetsOutput.isData();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (super.hashCode() * 59) + (isData() ? 79 : 97);
    }
}
