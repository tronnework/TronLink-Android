package com.tron.wallet.business.confirm.parambuilder.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.tron.wallet.common.bean.token.TokenBean;
import org.tron.api.GrpcAPI;
public class TransferParam extends BaseParam implements Parcelable {
    public static final int ACTIVE = 1;
    public static final Parcelable.Creator<TransferParam> CREATOR = new Parcelable.Creator<TransferParam>() {
        @Override
        public TransferParam createFromParcel(Parcel parcel) {
            return new TransferParam(parcel);
        }

        @Override
        public TransferParam[] newArray(int i) {
            return new TransferParam[i];
        }
    };
    public static final int NOT_ACTIVE = 0;
    public static final int TYPE_TRC10 = 1;
    public static final int TYPE_TRC20 = 2;
    public static final int TYPE_TRX = 0;
    public transient GrpcAPI.AccountResourceMessage accountNetMessage;
    private byte[] alpha;
    public String amount;
    public String fromAddress;
    private int isAccountActive;
    public String note;
    public String toAddress;
    public TokenBean tokenBean;
    private String tokenId;
    public String tokenType;

    @Override
    public int describeContents() {
        return 0;
    }

    public boolean getAccountActive() {
        return this.isAccountActive == 1;
    }

    public byte[] getAlpha() {
        return this.alpha;
    }

    public String getAmount() {
        return this.amount;
    }

    public String getFromAddress() {
        return this.fromAddress;
    }

    public String getNote() {
        return this.note;
    }

    public String getToAddress() {
        return this.toAddress;
    }

    public TokenBean getTokenBean() {
        return this.tokenBean;
    }

    public String getTokenId() {
        return this.tokenId;
    }

    public String getTokenType() {
        return this.tokenType;
    }

    public void setAccountActive(boolean z) {
        if (z) {
            this.isAccountActive = 1;
        } else {
            this.isAccountActive = 0;
        }
    }

    public void setAlpha(byte[] bArr) {
        this.alpha = bArr;
    }

    public void setFromAddress(String str) {
        this.fromAddress = str;
    }

    public void setNote(String str) {
        this.note = str;
    }

    public void setTokenBean(TokenBean tokenBean) {
        this.tokenBean = tokenBean;
    }

    public void setTokenId(String str) {
        this.tokenId = str;
    }

    public void setTokenType(String str) {
        this.tokenType = str;
    }

    public TransferParam() {
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.tokenType);
        parcel.writeParcelable(this.tokenBean, i);
        parcel.writeString(this.fromAddress);
        parcel.writeString(this.tokenId);
        parcel.writeByteArray(this.alpha);
        parcel.writeString(this.amount);
        parcel.writeString(this.note);
        parcel.writeString(this.toAddress);
        parcel.writeInt(this.isAccountActive);
    }

    protected TransferParam(Parcel parcel) {
        super(parcel);
        this.tokenType = parcel.readString();
        this.tokenBean = (TokenBean) parcel.readParcelable(TokenBean.class.getClassLoader());
        this.fromAddress = parcel.readString();
        this.tokenId = parcel.readString();
        this.alpha = parcel.createByteArray();
        this.amount = parcel.readString();
        this.note = parcel.readString();
        this.toAddress = parcel.readString();
        this.isAccountActive = parcel.readInt();
    }
}
