package com.tron.wallet.business.confirm.parambuilder.bean;

import android.os.Parcel;
import android.os.Parcelable;
public class ManagePermissionGroupParam extends BaseParam implements Parcelable {
    public static final Parcelable.Creator<ManagePermissionGroupParam> CREATOR = new Parcelable.Creator<ManagePermissionGroupParam>() {
        @Override
        public ManagePermissionGroupParam createFromParcel(Parcel parcel) {
            return new ManagePermissionGroupParam(parcel);
        }

        @Override
        public ManagePermissionGroupParam[] newArray(int i) {
            return new ManagePermissionGroupParam[i];
        }
    };
    private String address;
    private boolean isNeedMutliSign;
    private int modifyIndex;
    private String walletName;

    @Override
    public int describeContents() {
        return 0;
    }

    public String getAddress() {
        return this.address;
    }

    public int getModifyIndex() {
        return this.modifyIndex;
    }

    public String getWalletName() {
        return this.walletName;
    }

    public boolean isNeedMutliSign() {
        return this.isNeedMutliSign;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setModifyIndex(int i) {
        this.modifyIndex = i;
    }

    public void setNeedMutliSign(boolean z) {
        this.isNeedMutliSign = z;
    }

    public void setWalletName(String str) {
        this.walletName = str;
    }

    public ManagePermissionGroupParam() {
        setType(5);
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.walletName);
        parcel.writeString(this.address);
        parcel.writeInt(this.modifyIndex);
        parcel.writeByte(this.isNeedMutliSign ? (byte) 1 : (byte) 0);
    }

    protected ManagePermissionGroupParam(Parcel parcel) {
        super(parcel);
        this.walletName = parcel.readString();
        this.address = parcel.readString();
        this.modifyIndex = parcel.readInt();
        this.isNeedMutliSign = parcel.readByte() != 0;
    }
}
