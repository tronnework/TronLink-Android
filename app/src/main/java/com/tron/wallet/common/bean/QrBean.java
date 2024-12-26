package com.tron.wallet.common.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;
public class QrBean implements Parcelable {
    public static final Parcelable.Creator<QrBean> CREATOR = new Parcelable.Creator<QrBean>() {
        @Override
        public QrBean createFromParcel(Parcel parcel) {
            return new QrBean(parcel);
        }

        @Override
        public QrBean[] newArray(int i) {
            return new QrBean[i];
        }
    };
    private String address;
    private String alpha;
    private int cN;
    private List<String> hexList;
    private int tN;
    private String tokenId;
    private int type;

    @Override
    public int describeContents() {
        return 0;
    }

    public String getAddress() {
        return this.address;
    }

    public String getAlpha() {
        return this.alpha;
    }

    public List<String> getHexList() {
        return this.hexList;
    }

    public String getTokenId() {
        return this.tokenId;
    }

    public int getType() {
        return this.type;
    }

    public int getcN() {
        return this.cN;
    }

    public int gettN() {
        return this.tN;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setAlpha(String str) {
        this.alpha = str;
    }

    public void setHexList(List<String> list) {
        this.hexList = list;
    }

    public void setTokenId(String str) {
        this.tokenId = str;
    }

    public void setType(int i) {
        this.type = i;
    }

    public void setcN(int i) {
        this.cN = i;
    }

    public void settN(int i) {
        this.tN = i;
    }

    public QrBean() {
    }

    protected QrBean(Parcel parcel) {
        this.type = parcel.readInt();
        this.hexList = parcel.createStringArrayList();
        this.address = parcel.readString();
        this.alpha = parcel.readString();
        this.tokenId = parcel.readString();
        this.cN = parcel.readInt();
        this.tN = parcel.readInt();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.type);
        parcel.writeStringList(this.hexList);
        parcel.writeString(this.address);
        parcel.writeString(this.alpha);
        parcel.writeString(this.tokenId);
        parcel.writeInt(this.cN);
        parcel.writeInt(this.tN);
    }
}
