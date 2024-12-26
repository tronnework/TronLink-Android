package com.tron.wallet.business.confirm.parambuilder.bean;

import android.os.Parcel;
import android.os.Parcelable;
public class WithDrawDelegatedResourceParam extends BaseParam implements Parcelable {
    public static final Parcelable.Creator<WithDrawDelegatedResourceParam> CREATOR = new Parcelable.Creator<WithDrawDelegatedResourceParam>() {
        @Override
        public WithDrawDelegatedResourceParam createFromParcel(Parcel parcel) {
            return new WithDrawDelegatedResourceParam(parcel);
        }

        @Override
        public WithDrawDelegatedResourceParam[] newArray(int i) {
            return new WithDrawDelegatedResourceParam[i];
        }
    };
    private long amount;
    private String extraAddress;

    @Override
    public int describeContents() {
        return 0;
    }

    public long getAmount() {
        return this.amount;
    }

    public String getExtraAddress() {
        return this.extraAddress;
    }

    public void setAmount(long j) {
        this.amount = j;
    }

    public void setExtraAddress(String str) {
        this.extraAddress = str;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof WithDrawDelegatedResourceParam;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof WithDrawDelegatedResourceParam) {
            WithDrawDelegatedResourceParam withDrawDelegatedResourceParam = (WithDrawDelegatedResourceParam) obj;
            if (withDrawDelegatedResourceParam.canEqual(this) && getAmount() == withDrawDelegatedResourceParam.getAmount()) {
                String extraAddress = getExtraAddress();
                String extraAddress2 = withDrawDelegatedResourceParam.getExtraAddress();
                return extraAddress != null ? extraAddress.equals(extraAddress2) : extraAddress2 == null;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        long amount = getAmount();
        String extraAddress = getExtraAddress();
        return ((((int) (amount ^ (amount >>> 32))) + 59) * 59) + (extraAddress == null ? 43 : extraAddress.hashCode());
    }

    public String toString() {
        return "WithDrawDelegatedResourceParam(amount=" + getAmount() + ", extraAddress=" + getExtraAddress() + ")";
    }

    public WithDrawDelegatedResourceParam() {
    }

    protected WithDrawDelegatedResourceParam(Parcel parcel) {
        super(parcel);
        this.amount = parcel.readLong();
        this.extraAddress = parcel.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeLong(this.amount);
        parcel.writeString(this.extraAddress);
    }

    public void readFromParcel(Parcel parcel) {
        this.amount = parcel.readLong();
        this.extraAddress = parcel.readString();
    }
}
