package com.tron.wallet.business.confirm.parambuilder.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.tron.wallet.common.bean.token.TokenBean;
public class DepositWithDrawParam extends BaseParam implements Parcelable {
    public static final Parcelable.Creator<DepositWithDrawParam> CREATOR = new Parcelable.Creator<DepositWithDrawParam>() {
        @Override
        public DepositWithDrawParam createFromParcel(Parcel parcel) {
            return new DepositWithDrawParam(parcel);
        }

        @Override
        public DepositWithDrawParam[] newArray(int i) {
            return new DepositWithDrawParam[i];
        }
    };
    public static final int TYPE_TRC10 = 1;
    public static final int TYPE_TRC20 = 2;
    public static final int TYPE_TRX = 0;
    public String fromAddress;
    public TokenBean tokenBean;
    public String tokenType;

    @Override
    public int describeContents() {
        return 0;
    }

    public String getFromAddress() {
        return this.fromAddress;
    }

    public TokenBean getTokenBean() {
        return this.tokenBean;
    }

    public String getTokenType() {
        return this.tokenType;
    }

    public void setFromAddress(String str) {
        this.fromAddress = str;
    }

    public void setTokenBean(TokenBean tokenBean) {
        this.tokenBean = tokenBean;
    }

    public void setTokenType(String str) {
        this.tokenType = str;
    }

    public DepositWithDrawParam() {
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.tokenType);
        parcel.writeParcelable(this.tokenBean, i);
        parcel.writeString(this.fromAddress);
    }

    public DepositWithDrawParam(Parcel parcel) {
        super(parcel);
        this.tokenType = parcel.readString();
        this.tokenBean = (TokenBean) parcel.readParcelable(TokenBean.class.getClassLoader());
        this.fromAddress = parcel.readString();
    }
}
