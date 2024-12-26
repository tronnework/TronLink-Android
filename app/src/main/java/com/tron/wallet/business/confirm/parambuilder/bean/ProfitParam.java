package com.tron.wallet.business.confirm.parambuilder.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.tron.wallet.business.mutil.bean.MultiSignPermissionData;
public class ProfitParam extends BaseParam implements Parcelable {
    public static final Parcelable.Creator<ProfitParam> CREATOR = new Parcelable.Creator<ProfitParam>() {
        @Override
        public ProfitParam createFromParcel(Parcel parcel) {
            return new ProfitParam(parcel);
        }

        @Override
        public ProfitParam[] newArray(int i) {
            return new ProfitParam[i];
        }
    };
    private double amount;
    private MultiSignPermissionData permissionData;
    private String toAddress;

    @Override
    public int describeContents() {
        return 0;
    }

    public double getAmount() {
        return this.amount;
    }

    public MultiSignPermissionData getPermissionData() {
        return this.permissionData;
    }

    public String getToAddress() {
        return this.toAddress;
    }

    public void setAmount(double d) {
        this.amount = d;
    }

    public void setPermissionData(MultiSignPermissionData multiSignPermissionData) {
        this.permissionData = multiSignPermissionData;
    }

    public void setToAddress(String str) {
        this.toAddress = str;
    }

    public ProfitParam() {
    }

    protected ProfitParam(Parcel parcel) {
        super(parcel);
        this.amount = parcel.readDouble();
        this.toAddress = parcel.readString();
        this.permissionData = (MultiSignPermissionData) parcel.readParcelable(getClass().getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeDouble(this.amount);
        parcel.writeString(this.toAddress);
        parcel.writeParcelable(this.permissionData, i);
    }
}
