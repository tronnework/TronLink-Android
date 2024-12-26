package com.tron.wallet.business.confirm.parambuilder.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.tron.wallet.business.tabdapp.jsbridge.finance.beans.VoteBean;
public class StakeAndVoteParam extends BaseParam implements Parcelable {
    public static final Parcelable.Creator<StakeAndVoteParam> CREATOR = new Parcelable.Creator<StakeAndVoteParam>() {
        @Override
        public StakeAndVoteParam createFromParcel(Parcel parcel) {
            return new StakeAndVoteParam(parcel);
        }

        @Override
        public StakeAndVoteParam[] newArray(int i) {
            return new StakeAndVoteParam[i];
        }
    };
    private double canUseTrxCount;
    private int doFreezeType;
    private double realFreeze;
    private String resourceCount;
    private VoteBean voteBean;

    @Override
    public int describeContents() {
        return 0;
    }

    public double getCanUseTrxCount() {
        return this.canUseTrxCount;
    }

    public int getDoFreezeType() {
        return this.doFreezeType;
    }

    public double getRealFreeze() {
        return this.realFreeze;
    }

    public String getResourceCount() {
        return this.resourceCount;
    }

    public VoteBean getVoteBean() {
        return this.voteBean;
    }

    public void setCanUseTrxCount(double d) {
        this.canUseTrxCount = d;
    }

    public void setDoFreezeType(int i) {
        this.doFreezeType = i;
    }

    public void setRealFreeze(double d) {
        this.realFreeze = d;
    }

    public void setResourceCount(String str) {
        this.resourceCount = str;
    }

    public void setVoteBean(VoteBean voteBean) {
        this.voteBean = voteBean;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof StakeAndVoteParam;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof StakeAndVoteParam) {
            StakeAndVoteParam stakeAndVoteParam = (StakeAndVoteParam) obj;
            if (stakeAndVoteParam.canEqual(this) && getDoFreezeType() == stakeAndVoteParam.getDoFreezeType() && Double.compare(getCanUseTrxCount(), stakeAndVoteParam.getCanUseTrxCount()) == 0 && Double.compare(getRealFreeze(), stakeAndVoteParam.getRealFreeze()) == 0) {
                String resourceCount = getResourceCount();
                String resourceCount2 = stakeAndVoteParam.getResourceCount();
                if (resourceCount != null ? resourceCount.equals(resourceCount2) : resourceCount2 == null) {
                    VoteBean voteBean = getVoteBean();
                    VoteBean voteBean2 = stakeAndVoteParam.getVoteBean();
                    return voteBean != null ? voteBean.equals(voteBean2) : voteBean2 == null;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(getCanUseTrxCount());
        long doubleToLongBits2 = Double.doubleToLongBits(getRealFreeze());
        String resourceCount = getResourceCount();
        int doFreezeType = ((((((getDoFreezeType() + 59) * 59) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 59) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 59) + (resourceCount == null ? 43 : resourceCount.hashCode());
        VoteBean voteBean = getVoteBean();
        return (doFreezeType * 59) + (voteBean != null ? voteBean.hashCode() : 43);
    }

    public String toString() {
        return "StakeAndVoteParam(doFreezeType=" + getDoFreezeType() + ", canUseTrxCount=" + getCanUseTrxCount() + ", realFreeze=" + getRealFreeze() + ", resourceCount=" + getResourceCount() + ", voteBean=" + getVoteBean() + ")";
    }

    public StakeAndVoteParam() {
    }

    protected StakeAndVoteParam(Parcel parcel) {
        super(parcel);
        this.doFreezeType = parcel.readInt();
        this.canUseTrxCount = parcel.readDouble();
        this.realFreeze = parcel.readDouble();
        this.resourceCount = parcel.readString();
        this.voteBean = (VoteBean) parcel.readParcelable(getClass().getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.doFreezeType);
        parcel.writeDouble(this.canUseTrxCount);
        parcel.writeDouble(this.realFreeze);
        parcel.writeString(this.resourceCount);
        parcel.writeParcelable(this.voteBean, i);
    }
}
