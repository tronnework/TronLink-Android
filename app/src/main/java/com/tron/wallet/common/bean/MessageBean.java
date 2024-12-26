package com.tron.wallet.common.bean;

import android.os.Parcel;
import android.os.Parcelable;
public class MessageBean implements Parcelable {
    public static final Parcelable.Creator<MessageBean> CREATOR = new Parcelable.Creator<MessageBean>() {
        @Override
        public MessageBean createFromParcel(Parcel parcel) {
            return new MessageBean(parcel);
        }

        @Override
        public MessageBean[] newArray(int i) {
            return new MessageBean[i];
        }
    };
    public String address;
    public String chainName;
    public String content;
    public String contractType;
    public int direction;
    public String hash;
    public boolean isRead;
    public String proposalId;
    public String state;
    public long time;
    public String title;
    public String url;

    @Override
    public int describeContents() {
        return 0;
    }

    public MessageBean() {
    }

    protected MessageBean(Parcel parcel) {
        this.hash = parcel.readString();
        this.contractType = parcel.readString();
        this.isRead = parcel.readByte() != 0;
        this.time = parcel.readLong();
        this.state = parcel.readString();
        this.content = parcel.readString();
        this.address = parcel.readString();
        this.url = parcel.readString();
        this.chainName = parcel.readString();
        this.proposalId = parcel.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.hash);
        parcel.writeString(this.contractType);
        parcel.writeByte(this.isRead ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.time);
        parcel.writeString(this.state);
        parcel.writeString(this.content);
        parcel.writeString(this.address);
        parcel.writeString(this.url);
        parcel.writeString(this.chainName);
        parcel.writeString(this.proposalId);
    }
}
