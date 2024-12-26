package com.tron.wallet.business.confirm.parambuilder.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.tron.wallet.common.bean.token.TokenBean;
public class DoDepositParam extends BaseParam implements Parcelable {
    public static final Parcelable.Creator<DoDepositParam> CREATOR = new Parcelable.Creator<DoDepositParam>() {
        @Override
        public DoDepositParam createFromParcel(Parcel parcel) {
            return new DoDepositParam(parcel);
        }

        @Override
        public DoDepositParam[] newArray(int i) {
            return new DoDepositParam[i];
        }
    };
    private int doType;
    private TokenBean tokenBean;
    private String tokenType;

    @Override
    public int describeContents() {
        return 0;
    }

    public int getDoType() {
        return this.doType;
    }

    public TokenBean getTokenBean() {
        return this.tokenBean;
    }

    public String getTokenType() {
        return this.tokenType;
    }

    public void setDoType(int i) {
        this.doType = i;
    }

    public void setTokenBean(TokenBean tokenBean) {
        this.tokenBean = tokenBean;
    }

    public void setTokenType(String str) {
        this.tokenType = str;
    }

    public DoDepositParam() {
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.doType);
        parcel.writeString(this.tokenType);
        parcel.writeParcelable(this.tokenBean, i);
    }

    protected DoDepositParam(Parcel parcel) {
        super(parcel);
        this.doType = parcel.readInt();
        this.tokenType = parcel.readString();
        this.tokenBean = (TokenBean) parcel.readParcelable(TokenBean.class.getClassLoader());
    }
}
