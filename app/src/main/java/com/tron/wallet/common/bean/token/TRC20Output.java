package com.tron.wallet.common.bean.token;

import android.os.Parcel;
import android.os.Parcelable;
public class TRC20Output implements Parcelable {
    public static final Parcelable.Creator<TRC20Output> CREATOR = new Parcelable.Creator<TRC20Output>() {
        @Override
        public TRC20Output createFromParcel(Parcel parcel) {
            return new TRC20Output(parcel);
        }

        @Override
        public TRC20Output[] newArray(int i) {
            return new TRC20Output[i];
        }
    };
    public long amount;
    public String contract_address;
    public long decimals;
    public String exchange_abbr_name;
    public String gain;
    public String git_hub;
    public String home_page;
    public String icon_url;
    public boolean isSelected;
    public String issue_address;
    public String issue_time;
    public String name;
    public String price;
    public double price_trx;
    public String social_media;
    public String symbol;
    public String token_desc;
    public long total_supply;
    public String total_supply_with_decimals;
    public String up_down_percent;
    public String volume;
    public String volume24h;
    public String white_paper;

    @Override
    public int describeContents() {
        return 0;
    }

    protected TRC20Output(Parcel parcel) {
        this.contract_address = parcel.readString();
        this.name = parcel.readString();
        this.symbol = parcel.readString();
        this.total_supply = parcel.readLong();
        this.decimals = parcel.readLong();
        this.icon_url = parcel.readString();
        this.token_desc = parcel.readString();
        this.home_page = parcel.readString();
        this.white_paper = parcel.readString();
        this.social_media = parcel.readString();
        this.git_hub = parcel.readString();
        this.issue_time = parcel.readString();
        this.issue_address = parcel.readString();
        this.price = parcel.readString();
        this.price_trx = parcel.readDouble();
        this.amount = parcel.readLong();
        this.isSelected = parcel.readByte() != 0;
        this.exchange_abbr_name = parcel.readString();
        this.volume = parcel.readString();
        this.up_down_percent = parcel.readString();
        this.volume24h = parcel.readString();
        this.gain = parcel.readString();
        this.total_supply_with_decimals = parcel.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.contract_address);
        parcel.writeString(this.name);
        parcel.writeString(this.symbol);
        parcel.writeLong(this.total_supply);
        parcel.writeLong(this.decimals);
        parcel.writeString(this.icon_url);
        parcel.writeString(this.token_desc);
        parcel.writeString(this.home_page);
        parcel.writeString(this.white_paper);
        parcel.writeString(this.social_media);
        parcel.writeString(this.git_hub);
        parcel.writeString(this.issue_time);
        parcel.writeString(this.issue_address);
        parcel.writeString(this.price);
        parcel.writeDouble(this.price_trx);
        parcel.writeLong(this.amount);
        parcel.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
        parcel.writeString(this.exchange_abbr_name);
        parcel.writeString(this.volume);
        parcel.writeString(this.up_down_percent);
        parcel.writeString(this.volume24h);
        parcel.writeString(this.gain);
        parcel.writeString(this.total_supply_with_decimals);
    }
}
