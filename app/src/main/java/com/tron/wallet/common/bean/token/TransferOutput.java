package com.tron.wallet.common.bean.token;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tron.wallet.business.addassets2.AssetsConfig;
import java.util.HashMap;
import java.util.List;
public class TransferOutput implements Parcelable {
    public static final Parcelable.Creator<TransferOutput> CREATOR = new Parcelable.Creator<TransferOutput>() {
        @Override
        public TransferOutput createFromParcel(Parcel parcel) {
            return new TransferOutput(parcel);
        }

        @Override
        public TransferOutput[] newArray(int i) {
            return new TransferOutput[i];
        }
    };
    private int code;
    public HashMap<String, Boolean> contractsMap;
    public List<TransactionHistoryBean> data;
    private int page_size;
    public TokenInfo tokenInfo;
    public int total;

    @Override
    public int describeContents() {
        return 0;
    }

    public int getCode() {
        return this.code;
    }

    public HashMap<String, Boolean> getContractMap() {
        return this.contractsMap;
    }

    public List<TransactionHistoryBean> getData() {
        return this.data;
    }

    public int getPage_size() {
        return this.page_size;
    }

    public TokenInfo getTokenInfo() {
        return this.tokenInfo;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setData(List<TransactionHistoryBean> list) {
        this.data = list;
    }

    public void setPage_size(int i) {
        this.page_size = i;
    }

    public void setTokenInfo(TokenInfo tokenInfo) {
        this.tokenInfo = tokenInfo;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.page_size);
        parcel.writeInt(this.code);
        parcel.writeInt(this.total);
        parcel.writeTypedList(this.data);
    }

    public TransferOutput() {
    }

    protected TransferOutput(Parcel parcel) {
        this.page_size = parcel.readInt();
        this.code = parcel.readInt();
        this.total = parcel.readInt();
        this.data = parcel.createTypedArrayList(TransactionHistoryBean.CREATOR);
    }

    public class TokenInfo {
        @JsonProperty("cheatStatus")
        private Boolean cheatStatus;
        @JsonProperty("tokenAbbr")
        private String tokenAbbr;
        @JsonProperty("tokenCanShow")
        private Integer tokenCanShow;
        @JsonProperty("tokenDecimal")
        private Integer tokenDecimal;
        @JsonProperty("tokenId")
        private String tokenId;
        @JsonProperty("tokenLevel")
        private String tokenLevel;
        @JsonProperty("tokenLogo")
        private String tokenLogo;
        @JsonProperty("tokenName")
        private String tokenName;
        @JsonProperty(AssetsConfig.TOKEN_TYPE)
        private String tokenType;
        @JsonProperty("vip")
        private Boolean vip;

        public TokenInfo() {
        }
    }
}
