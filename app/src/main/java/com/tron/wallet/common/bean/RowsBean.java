package com.tron.wallet.common.bean;

import android.os.Parcel;
import android.os.Parcelable;
public class RowsBean implements Parcelable {
    public static final Parcelable.Creator<RowsBean> CREATOR = new Parcelable.Creator<RowsBean>() {
        @Override
        public RowsBean createFromParcel(Parcel parcel) {
            return new RowsBean(parcel);
        }

        @Override
        public RowsBean[] newArray(int i) {
            return new RowsBean[i];
        }
    };
    private int fPrecision;
    private String fShortName;
    private String fTokenAddr;
    private String fTokenName;
    private String gain;
    private double highestPrice24h;
    private int id;
    private double lowestPrice24h;
    private long price;
    private int sPrecision;
    private String sShortName;
    private String sTokenAddr;
    private String sTokenName;
    private Long tbId;
    private String unit;
    private long volume;
    private long volume24h;

    @Override
    public int describeContents() {
        return 0;
    }

    public int getFPrecision() {
        return this.fPrecision;
    }

    public String getFShortName() {
        return this.fShortName;
    }

    public String getFTokenAddr() {
        return this.fTokenAddr;
    }

    public String getFTokenName() {
        return this.fTokenName;
    }

    public String getGain() {
        return this.gain;
    }

    public double getHighestPrice24h() {
        return this.highestPrice24h;
    }

    public int getId() {
        return this.id;
    }

    public double getLowestPrice24h() {
        return this.lowestPrice24h;
    }

    public long getPrice() {
        return this.price;
    }

    public int getSPrecision() {
        return this.sPrecision;
    }

    public String getSShortName() {
        return this.sShortName;
    }

    public String getSTokenAddr() {
        return this.sTokenAddr;
    }

    public String getSTokenName() {
        return this.sTokenName;
    }

    public Long getTbId() {
        return this.tbId;
    }

    public String getUnit() {
        return this.unit;
    }

    public long getVolume() {
        return this.volume;
    }

    public long getVolume24h() {
        return this.volume24h;
    }

    public void setFPrecision(int i) {
        this.fPrecision = i;
    }

    public void setFShortName(String str) {
        this.fShortName = str;
    }

    public void setFTokenAddr(String str) {
        this.fTokenAddr = str;
    }

    public void setFTokenName(String str) {
        this.fTokenName = str;
    }

    public void setGain(String str) {
        this.gain = str;
    }

    public void setHighestPrice24h(double d) {
        this.highestPrice24h = d;
    }

    public void setId(int i) {
        this.id = i;
    }

    public void setLowestPrice24h(double d) {
        this.lowestPrice24h = d;
    }

    public void setPrice(long j) {
        this.price = j;
    }

    public void setSPrecision(int i) {
        this.sPrecision = i;
    }

    public void setSShortName(String str) {
        this.sShortName = str;
    }

    public void setSTokenAddr(String str) {
        this.sTokenAddr = str;
    }

    public void setSTokenName(String str) {
        this.sTokenName = str;
    }

    public void setTbId(Long l) {
        this.tbId = l;
    }

    public void setUnit(String str) {
        this.unit = str;
    }

    public void setVolume(long j) {
        this.volume = j;
    }

    public void setVolume24h(long j) {
        this.volume24h = j;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeLong(this.volume);
        parcel.writeString(this.gain);
        parcel.writeLong(this.price);
        parcel.writeInt(this.fPrecision);
        parcel.writeInt(this.sPrecision);
        parcel.writeString(this.fTokenName);
        parcel.writeString(this.sTokenName);
        parcel.writeString(this.fShortName);
        parcel.writeString(this.sShortName);
        parcel.writeString(this.fTokenAddr);
        parcel.writeString(this.sTokenAddr);
        parcel.writeDouble(this.highestPrice24h);
        parcel.writeDouble(this.lowestPrice24h);
        parcel.writeLong(this.volume24h);
        parcel.writeString(this.unit);
    }

    public RowsBean() {
    }

    protected RowsBean(Parcel parcel) {
        this.id = parcel.readInt();
        this.volume = parcel.readLong();
        this.gain = parcel.readString();
        this.price = parcel.readLong();
        this.fPrecision = parcel.readInt();
        this.sPrecision = parcel.readInt();
        this.fTokenName = parcel.readString();
        this.sTokenName = parcel.readString();
        this.fShortName = parcel.readString();
        this.sShortName = parcel.readString();
        this.fTokenAddr = parcel.readString();
        this.sTokenAddr = parcel.readString();
        this.highestPrice24h = parcel.readDouble();
        this.lowestPrice24h = parcel.readDouble();
        this.volume24h = parcel.readLong();
        this.unit = parcel.readString();
    }

    public RowsBean(Long l, int i, long j, String str, long j2, String str2, String str3, String str4, String str5, long j3) {
        this.tbId = l;
        this.id = i;
        this.volume = j;
        this.gain = str;
        this.price = j2;
        this.fShortName = str2;
        this.sShortName = str3;
        this.fTokenAddr = str4;
        this.sTokenAddr = str5;
        this.volume24h = j3;
    }

    public String toString() {
        return "RowsBean{id=" + this.id + ", volume=" + this.volume + ", gain='" + this.gain + "', price=" + this.price + ", fShortName='" + this.fShortName + "', sShortName='" + this.sShortName + "', volume24h=" + this.volume24h + '}';
    }
}
