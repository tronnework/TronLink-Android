package com.tron.wallet.business.confirm.parambuilder.bean;

import android.os.Parcel;
import android.os.Parcelable;
public class UnexpiredProfitParam extends BaseParam implements Parcelable {
    public static final Parcelable.Creator<UnexpiredProfitParam> CREATOR = new Parcelable.Creator<UnexpiredProfitParam>() {
        @Override
        public UnexpiredProfitParam createFromParcel(Parcel parcel) {
            return new UnexpiredProfitParam(parcel);
        }

        @Override
        public UnexpiredProfitParam[] newArray(int i) {
            return new UnexpiredProfitParam[i];
        }
    };
    public String address;
    public String amount;

    @Override
    public int describeContents() {
        return 0;
    }

    public UnexpiredProfitParam() {
    }

    public UnexpiredProfitParam(Parcel parcel) {
        super(parcel);
        this.address = parcel.readString();
        this.amount = parcel.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.address);
        parcel.writeString(this.amount);
    }
}
