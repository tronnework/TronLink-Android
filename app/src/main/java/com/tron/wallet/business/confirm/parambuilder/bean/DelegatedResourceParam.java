package com.tron.wallet.business.confirm.parambuilder.bean;

import android.os.Parcel;
import android.os.Parcelable;
public class DelegatedResourceParam extends BaseParam implements Parcelable {
    public static final Parcelable.Creator<DelegatedResourceParam> CREATOR = new Parcelable.Creator<DelegatedResourceParam>() {
        @Override
        public DelegatedResourceParam createFromParcel(Parcel parcel) {
            return new DelegatedResourceParam(parcel);
        }

        @Override
        public DelegatedResourceParam[] newArray(int i) {
            return new DelegatedResourceParam[i];
        }
    };
    public static final int RESOURCE_BANDWIDTH = 0;
    public static final int RESOURCE_ENERGY = 1;
    private String extraAddress;
    private boolean isDelegate;
    private long resourceBalance;
    private long resourceBalanceByTrx;
    private int resourceType;

    @Override
    public int describeContents() {
        return 0;
    }

    public String getExtraAddress() {
        return this.extraAddress;
    }

    public long getResourceBalance() {
        return this.resourceBalance;
    }

    public long getResourceBalanceByTrx() {
        return this.resourceBalanceByTrx;
    }

    public int getResourceType() {
        return this.resourceType;
    }

    public boolean isDelegate() {
        return this.isDelegate;
    }

    public void setDelegate(boolean z) {
        this.isDelegate = z;
    }

    public void setExtraAddress(String str) {
        this.extraAddress = str;
    }

    public void setResourceBalance(long j) {
        this.resourceBalance = j;
    }

    public void setResourceBalanceByTrx(long j) {
        this.resourceBalanceByTrx = j;
    }

    public void setResourceType(int i) {
        this.resourceType = i;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof DelegatedResourceParam;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DelegatedResourceParam) {
            DelegatedResourceParam delegatedResourceParam = (DelegatedResourceParam) obj;
            if (delegatedResourceParam.canEqual(this) && isDelegate() == delegatedResourceParam.isDelegate() && getResourceType() == delegatedResourceParam.getResourceType() && getResourceBalance() == delegatedResourceParam.getResourceBalance() && getResourceBalanceByTrx() == delegatedResourceParam.getResourceBalanceByTrx()) {
                String extraAddress = getExtraAddress();
                String extraAddress2 = delegatedResourceParam.getExtraAddress();
                return extraAddress != null ? extraAddress.equals(extraAddress2) : extraAddress2 == null;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int resourceType = (((isDelegate() ? 79 : 97) + 59) * 59) + getResourceType();
        long resourceBalance = getResourceBalance();
        long resourceBalanceByTrx = getResourceBalanceByTrx();
        String extraAddress = getExtraAddress();
        return (((((resourceType * 59) + ((int) (resourceBalance ^ (resourceBalance >>> 32)))) * 59) + ((int) (resourceBalanceByTrx ^ (resourceBalanceByTrx >>> 32)))) * 59) + (extraAddress == null ? 43 : extraAddress.hashCode());
    }

    public String toString() {
        return "DelegatedResourceParam(isDelegate=" + isDelegate() + ", resourceType=" + getResourceType() + ", resourceBalance=" + getResourceBalance() + ", resourceBalanceByTrx=" + getResourceBalanceByTrx() + ", extraAddress=" + getExtraAddress() + ")";
    }

    public DelegatedResourceParam() {
    }

    protected DelegatedResourceParam(Parcel parcel) {
        super(parcel);
        this.isDelegate = parcel.readInt() == 1;
        this.resourceType = parcel.readInt();
        this.resourceBalance = parcel.readLong();
        this.resourceBalanceByTrx = parcel.readLong();
        this.extraAddress = parcel.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.isDelegate ? 1 : 0);
        parcel.writeInt(this.resourceType);
        parcel.writeLong(this.resourceBalance);
        parcel.writeLong(this.resourceBalanceByTrx);
        parcel.writeString(this.extraAddress);
    }

    public void readFromParcel(Parcel parcel) {
        this.isDelegate = parcel.readInt() == 1;
        this.resourceType = parcel.readInt();
        this.resourceBalance = parcel.readLong();
        this.resourceBalanceByTrx = parcel.readLong();
        this.extraAddress = parcel.readString();
    }
}
