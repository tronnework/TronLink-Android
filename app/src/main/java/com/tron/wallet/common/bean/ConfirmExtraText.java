package com.tron.wallet.common.bean;

import android.os.Parcel;
import android.os.Parcelable;
public class ConfirmExtraText implements Parcelable {
    public static final Parcelable.Creator<ConfirmExtraText> CREATOR = new Parcelable.Creator<ConfirmExtraText>() {
        @Override
        public ConfirmExtraText createFromParcel(Parcel parcel) {
            return new ConfirmExtraText(parcel);
        }

        @Override
        public ConfirmExtraText[] newArray(int i) {
            return new ConfirmExtraText[i];
        }
    };
    private String left;
    private String right;

    @Override
    public int describeContents() {
        return 0;
    }

    public String getLeft() {
        return this.left;
    }

    public String getRight() {
        return this.right;
    }

    public void setLeft(String str) {
        this.left = str;
    }

    public void setRight(String str) {
        this.right = str;
    }

    public ConfirmExtraText() {
    }

    public ConfirmExtraText(String str, String str2) {
        this.left = str;
        this.right = str2;
    }

    protected ConfirmExtraText(Parcel parcel) {
        this.left = parcel.readString();
        this.right = parcel.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.left);
        parcel.writeString(this.right);
    }
}
