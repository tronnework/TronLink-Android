package com.tron.wallet.common.bean;

import android.os.Parcel;
import android.os.Parcelable;
public class ConfirmExtraTitle implements Parcelable {
    public static final Parcelable.Creator<ConfirmExtraTitle> CREATOR = new Parcelable.Creator<ConfirmExtraTitle>() {
        @Override
        public ConfirmExtraTitle createFromParcel(Parcel parcel) {
            return new ConfirmExtraTitle(parcel);
        }

        @Override
        public ConfirmExtraTitle[] newArray(int i) {
            return new ConfirmExtraTitle[i];
        }
    };
    private String confirmTitle;
    private String desTitle;
    private String mutisignTitle;

    @Override
    public int describeContents() {
        return 0;
    }

    public String getConfirmTitle() {
        return this.confirmTitle;
    }

    public String getDesTitle() {
        return this.desTitle;
    }

    public String getMutisignTitle() {
        return this.mutisignTitle;
    }

    public void setConfirmTitle(String str) {
        this.confirmTitle = str;
    }

    public void setDesTitle(String str) {
        this.desTitle = str;
    }

    public void setMutisignTitle(String str) {
        this.mutisignTitle = str;
    }

    public ConfirmExtraTitle() {
    }

    protected ConfirmExtraTitle(Parcel parcel) {
        this.desTitle = parcel.readString();
        this.mutisignTitle = parcel.readString();
        this.confirmTitle = parcel.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.desTitle);
        parcel.writeString(this.mutisignTitle);
        parcel.writeString(this.confirmTitle);
    }
}
