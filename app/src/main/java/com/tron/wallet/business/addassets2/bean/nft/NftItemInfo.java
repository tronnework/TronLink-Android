package com.tron.wallet.business.addassets2.bean.nft;

import android.os.Parcel;
import android.os.Parcelable;
import com.tron.wallet.business.addassets2.bean.asset.MetaData721;
public class NftItemInfo implements Parcelable {
    public static final Parcelable.Creator<NftItemInfo> CREATOR = new Parcelable.Creator<NftItemInfo>() {
        @Override
        public NftItemInfo createFromParcel(Parcel parcel) {
            return new NftItemInfo(parcel);
        }

        @Override
        public NftItemInfo[] newArray(int i) {
            return new NftItemInfo[i];
        }
    };
    private String assetId;
    private String assetUri;
    private Long id;
    private String imageUrl;
    private String intro;
    private String logoUrl;
    private String name;
    private String price;
    private String tokenAddress;
    private String walletAddress;

    @Override
    public int describeContents() {
        return 0;
    }

    public String getAssetId() {
        return this.assetId;
    }

    public String getAssetUri() {
        return this.assetUri;
    }

    public Long getId() {
        return this.id;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public String getIntro() {
        return this.intro;
    }

    public String getLogoUrl() {
        return this.logoUrl;
    }

    public String getName() {
        return this.name;
    }

    public String getPrice() {
        return this.price;
    }

    public String getTokenAddress() {
        return this.tokenAddress;
    }

    public String getWalletAddress() {
        return this.walletAddress;
    }

    public void setAssetId(String str) {
        this.assetId = str;
    }

    public void setAssetUri(String str) {
        this.assetUri = str;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public void setImageUrl(String str) {
        this.imageUrl = str;
    }

    public void setIntro(String str) {
        this.intro = str;
    }

    public void setLogoUrl(String str) {
        this.logoUrl = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setPrice(String str) {
        this.price = str;
    }

    public void setTokenAddress(String str) {
        this.tokenAddress = str;
    }

    public void setWalletAddress(String str) {
        this.walletAddress = str;
    }

    public String toString() {
        return "NftItemInfo{id=" + this.id + ", tokenAddress='" + this.tokenAddress + "', assetId='" + this.assetId + "', name='" + this.name + "', intro='" + this.intro + "', price='" + this.price + "', logoUrl='" + this.logoUrl + "', imageUrl='" + this.imageUrl + "', assetUri='" + this.assetUri + "', walletAddress='" + this.walletAddress + "'}";
    }

    protected NftItemInfo(Parcel parcel) {
        if (parcel.readByte() == 0) {
            this.id = null;
        } else {
            this.id = Long.valueOf(parcel.readLong());
        }
        this.tokenAddress = parcel.readString();
        this.assetId = parcel.readString();
        this.name = parcel.readString();
        this.intro = parcel.readString();
        this.price = parcel.readString();
        this.logoUrl = parcel.readString();
        this.imageUrl = parcel.readString();
        this.assetUri = parcel.readString();
        this.walletAddress = parcel.readString();
    }

    public NftItemInfo() {
    }

    public NftItemInfo(Long l, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        this.id = l;
        this.tokenAddress = str;
        this.assetId = str2;
        this.name = str3;
        this.intro = str4;
        this.price = str5;
        this.logoUrl = str6;
        this.imageUrl = str7;
        this.assetUri = str8;
        this.walletAddress = str9;
    }

    public MetaData721 build721() {
        MetaData721 metaData721 = new MetaData721();
        metaData721.setName(getName());
        metaData721.setContractAddress(getTokenAddress());
        metaData721.setTokenId(getAssetId());
        metaData721.setImage(getLogoUrl());
        metaData721.setDescription(getIntro());
        return metaData721;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (this.id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(this.id.longValue());
        }
        parcel.writeString(this.tokenAddress);
        parcel.writeString(this.assetId);
        parcel.writeString(this.name);
        parcel.writeString(this.intro);
        parcel.writeString(this.price);
        parcel.writeString(this.logoUrl);
        parcel.writeString(this.imageUrl);
        parcel.writeString(this.assetUri);
        parcel.writeString(this.walletAddress);
    }
}
