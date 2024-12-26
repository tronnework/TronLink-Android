package com.tron.wallet.business.confirm.parambuilder.bean;

import android.os.Parcel;
import android.os.Parcelable;
public class DappParam extends BaseParam implements Parcelable {
    public static final Parcelable.Creator<BaseParam> CREATOR = new Parcelable.Creator<BaseParam>() {
        @Override
        public BaseParam createFromParcel(Parcel parcel) {
            return new DappParam(parcel);
        }

        @Override
        public BaseParam[] newArray(int i) {
            return new DappParam[i];
        }
    };
    public static final String Type_712 = "Type_712";
    public static final String Type_Common = "Type_Common";
    private String password;
    private String signStrType;

    @Override
    public int describeContents() {
        return 0;
    }

    public String getPassword() {
        return this.password;
    }

    public String getSignStrType() {
        return this.signStrType;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public void setSignStrType(String str) {
        this.signStrType = str;
    }

    public DappParam() {
    }

    protected DappParam(Parcel parcel) {
        super(parcel);
        this.password = parcel.readString();
        this.signStrType = parcel.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.password);
        parcel.writeString(this.signStrType);
    }
}
