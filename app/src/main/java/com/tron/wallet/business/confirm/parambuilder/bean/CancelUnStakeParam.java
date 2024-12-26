package com.tron.wallet.business.confirm.parambuilder.bean;

import android.os.Parcel;
import android.os.Parcelable;
public class CancelUnStakeParam extends BaseParam implements Parcelable {
    public static final Parcelable.Creator<CancelUnStakeParam> CREATOR = new Parcelable.Creator<CancelUnStakeParam>() {
        @Override
        public CancelUnStakeParam createFromParcel(Parcel parcel) {
            return new CancelUnStakeParam(parcel);
        }

        @Override
        public CancelUnStakeParam[] newArray(int i) {
            return new CancelUnStakeParam[i];
        }
    };
    private double amount;
    private long bandwidth;
    private long energy;
    private double withDrawAmount;

    @Override
    public int describeContents() {
        return 0;
    }

    public double getAmount() {
        return this.amount;
    }

    public long getBandwidth() {
        return this.bandwidth;
    }

    public long getEnergy() {
        return this.energy;
    }

    public double getWithDrawAmount() {
        return this.withDrawAmount;
    }

    public void setAmount(double d) {
        this.amount = d;
    }

    public void setBandwidth(long j) {
        this.bandwidth = j;
    }

    public void setEnergy(long j) {
        this.energy = j;
    }

    public void setWithDrawAmount(double d) {
        this.withDrawAmount = d;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof CancelUnStakeParam;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CancelUnStakeParam) {
            CancelUnStakeParam cancelUnStakeParam = (CancelUnStakeParam) obj;
            return cancelUnStakeParam.canEqual(this) && Double.compare(getAmount(), cancelUnStakeParam.getAmount()) == 0 && Double.compare(getWithDrawAmount(), cancelUnStakeParam.getWithDrawAmount()) == 0 && getEnergy() == cancelUnStakeParam.getEnergy() && getBandwidth() == cancelUnStakeParam.getBandwidth();
        }
        return false;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(getAmount());
        long doubleToLongBits2 = Double.doubleToLongBits(getWithDrawAmount());
        long energy = getEnergy();
        long bandwidth = getBandwidth();
        return ((((((((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) + 59) * 59) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 59) + ((int) (energy ^ (energy >>> 32)))) * 59) + ((int) ((bandwidth >>> 32) ^ bandwidth));
    }

    public String toString() {
        return "CancelUnStakeParam(amount=" + getAmount() + ", withDrawAmount=" + getWithDrawAmount() + ", energy=" + getEnergy() + ", bandwidth=" + getBandwidth() + ")";
    }

    public CancelUnStakeParam() {
    }

    protected CancelUnStakeParam(Parcel parcel) {
        super(parcel);
        this.amount = parcel.readDouble();
        this.withDrawAmount = parcel.readDouble();
        this.energy = parcel.readLong();
        this.bandwidth = parcel.readLong();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeDouble(this.amount);
        parcel.writeDouble(this.withDrawAmount);
        parcel.writeLong(this.energy);
        parcel.writeLong(this.bandwidth);
    }
}
