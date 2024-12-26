package com.tron.wallet.business.customtokens.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.tron.wallet.common.bean.token.TokenBean;
public class CustomTokenBean implements Parcelable {
    public static final Parcelable.Creator<CustomTokenBean> CREATOR = new Parcelable.Creator<CustomTokenBean>() {
        @Override
        public CustomTokenBean createFromParcel(Parcel parcel) {
            return new CustomTokenBean(parcel);
        }

        @Override
        public CustomTokenBean[] newArray(int i) {
            return new CustomTokenBean[i];
        }
    };
    private TokenBean assetInfo;
    private boolean balanceFunction;
    private String noFunctions;
    private int status;
    private String tokenAddress;
    private boolean transferEvent;

    @Override
    public int describeContents() {
        return 0;
    }

    public TokenBean getAssetInfo() {
        return this.assetInfo;
    }

    public String getNoFunctions() {
        return this.noFunctions;
    }

    public int getStatus() {
        return this.status;
    }

    public String getTokenAddress() {
        return this.tokenAddress;
    }

    public boolean isBalanceFunction() {
        return this.balanceFunction;
    }

    public boolean isTransferEvent() {
        return this.transferEvent;
    }

    public void setAssetInfo(TokenBean tokenBean) {
        this.assetInfo = tokenBean;
    }

    public void setBalanceFunction(boolean z) {
        this.balanceFunction = z;
    }

    public void setNoFunctions(String str) {
        this.noFunctions = str;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public void setTokenAddress(String str) {
        this.tokenAddress = str;
    }

    public void setTransferEvent(boolean z) {
        this.transferEvent = z;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof CustomTokenBean;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CustomTokenBean) {
            CustomTokenBean customTokenBean = (CustomTokenBean) obj;
            if (customTokenBean.canEqual(this) && getStatus() == customTokenBean.getStatus() && isBalanceFunction() == customTokenBean.isBalanceFunction() && isTransferEvent() == customTokenBean.isTransferEvent()) {
                String tokenAddress = getTokenAddress();
                String tokenAddress2 = customTokenBean.getTokenAddress();
                if (tokenAddress != null ? tokenAddress.equals(tokenAddress2) : tokenAddress2 == null) {
                    TokenBean assetInfo = getAssetInfo();
                    TokenBean assetInfo2 = customTokenBean.getAssetInfo();
                    if (assetInfo != null ? assetInfo.equals(assetInfo2) : assetInfo2 == null) {
                        String noFunctions = getNoFunctions();
                        String noFunctions2 = customTokenBean.getNoFunctions();
                        return noFunctions != null ? noFunctions.equals(noFunctions2) : noFunctions2 == null;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int status = (((getStatus() + 59) * 59) + (isBalanceFunction() ? 79 : 97)) * 59;
        int i = isTransferEvent() ? 79 : 97;
        String tokenAddress = getTokenAddress();
        int hashCode = ((status + i) * 59) + (tokenAddress == null ? 43 : tokenAddress.hashCode());
        TokenBean assetInfo = getAssetInfo();
        int hashCode2 = (hashCode * 59) + (assetInfo == null ? 43 : assetInfo.hashCode());
        String noFunctions = getNoFunctions();
        return (hashCode2 * 59) + (noFunctions != null ? noFunctions.hashCode() : 43);
    }

    public String toString() {
        return "CustomTokenBean(tokenAddress=" + getTokenAddress() + ", status=" + getStatus() + ", assetInfo=" + getAssetInfo() + ", balanceFunction=" + isBalanceFunction() + ", transferEvent=" + isTransferEvent() + ", noFunctions=" + getNoFunctions() + ")";
    }

    public CustomTokenBean() {
    }

    protected CustomTokenBean(Parcel parcel) {
        this.tokenAddress = parcel.readString();
        this.status = parcel.readInt();
        this.assetInfo = (TokenBean) parcel.readParcelable(TokenBean.class.getClassLoader());
        this.balanceFunction = parcel.readByte() != 0;
        this.transferEvent = parcel.readByte() != 0;
        this.noFunctions = parcel.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.tokenAddress);
        parcel.writeInt(this.status);
        parcel.writeParcelable(this.assetInfo, i);
        parcel.writeByte(this.balanceFunction ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.transferEvent ? (byte) 1 : (byte) 0);
        parcel.writeString(this.noFunctions);
    }

    public void readFromParcel(Parcel parcel) {
        this.tokenAddress = parcel.readString();
        this.status = parcel.readInt();
        this.assetInfo = (TokenBean) parcel.readParcelable(TokenBean.class.getClassLoader());
        this.balanceFunction = parcel.readByte() != 0;
        this.transferEvent = parcel.readByte() != 0;
        this.noFunctions = parcel.readString();
    }
}
