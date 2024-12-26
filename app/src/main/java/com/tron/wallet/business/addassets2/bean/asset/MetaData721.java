package com.tron.wallet.business.addassets2.bean.asset;

import android.os.Parcel;
import android.os.Parcelable;
public class MetaData721 implements Parcelable {
    public static final Parcelable.Creator<MetaData721> CREATOR = new Parcelable.Creator<MetaData721>() {
        @Override
        public MetaData721 createFromParcel(Parcel parcel) {
            return new MetaData721(parcel);
        }

        @Override
        public MetaData721[] newArray(int i) {
            return new MetaData721[i];
        }
    };
    private String contractAddress;
    private String description;
    private String image;
    private String name;
    private String tokenId;

    @Override
    public int describeContents() {
        return 0;
    }

    public String getContractAddress() {
        return this.contractAddress;
    }

    public String getDescription() {
        return this.description;
    }

    public String getImage() {
        return this.image;
    }

    public String getName() {
        return this.name;
    }

    public String getTokenId() {
        return this.tokenId;
    }

    public void setContractAddress(String str) {
        this.contractAddress = str;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setTokenId(String str) {
        this.tokenId = str;
    }

    public MetaData721() {
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.image);
        parcel.writeString(this.description);
        parcel.writeString(this.tokenId);
        parcel.writeString(this.contractAddress);
    }

    protected MetaData721(Parcel parcel) {
        this.name = parcel.readString();
        this.image = parcel.readString();
        this.description = parcel.readString();
        this.tokenId = parcel.readString();
        this.contractAddress = parcel.readString();
    }
}
