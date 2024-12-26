package com.tron.wallet.common.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
public class Token implements Parcelable, Comparable<Token> {
    public static final Parcelable.Creator<Token> CREATOR = new Parcelable.Creator<Token>() {
        @Override
        public Token createFromParcel(Parcel parcel) {
            return new Token(parcel);
        }

        @Override
        public Token[] newArray(int i) {
            return new Token[i];
        }
    };
    private long amount;
    private long id;
    private boolean isSelected;
    private String name;
    private long precision;
    private Price price;
    private double trxAmount;

    @Override
    public int describeContents() {
        return 0;
    }

    public long getAmount() {
        return this.amount;
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public long getPrecision() {
        return this.precision;
    }

    public Price getPrice() {
        return this.price;
    }

    public double getTrxAmount() {
        return this.trxAmount;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setAmount(long j) {
        this.amount = j;
    }

    public void setId(long j) {
        this.id = j;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setPrecision(long j) {
        this.precision = j;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public void setSelected(boolean z) {
        this.isSelected = z;
    }

    public void setTrxAmount(double d) {
        this.trxAmount = d;
    }

    protected Token(Parcel parcel) {
        this.name = parcel.readString();
        this.amount = parcel.readLong();
        this.trxAmount = parcel.readDouble();
        this.id = parcel.readLong();
        this.isSelected = parcel.readByte() != 0;
        this.precision = parcel.readLong();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeLong(this.amount);
        parcel.writeDouble(this.trxAmount);
        parcel.writeLong(this.id);
        parcel.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.precision);
    }

    public Token(String str, long j, long j2) {
        this.name = str;
        this.amount = j;
        this.id = j2;
    }

    public Token(String str, double d, boolean z) {
        this.name = str;
        this.trxAmount = d;
        this.isSelected = z;
    }

    public Token(String str, long j) {
        this.name = str;
        this.amount = j;
    }

    public Token(String str, long j, boolean z) {
        this.name = str;
        this.amount = j;
        this.isSelected = z;
    }

    public Token(String str, long j, boolean z, long j2) {
        this.name = str;
        this.amount = j;
        this.isSelected = z;
        this.id = j2;
    }

    @Override
    public int compareTo(Token token) {
        double pow;
        long precision = getPrecision();
        long precision2 = token.getPrecision();
        double pow2 = precision == 0 ? this.amount : this.amount / Math.pow(10.0d, precision);
        if (precision2 == 0) {
            pow = token.amount;
        } else {
            pow = token.amount / Math.pow(10.0d, precision2);
        }
        double d = pow - pow2;
        if (d > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            return 1;
        }
        return d < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? -1 : 0;
    }
}
