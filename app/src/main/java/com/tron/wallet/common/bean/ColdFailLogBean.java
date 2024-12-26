package com.tron.wallet.common.bean;

import android.os.Parcel;
import android.os.Parcelable;
public class ColdFailLogBean implements Parcelable {
    public static final Parcelable.Creator<ColdFailLogBean> CREATOR = new Parcelable.Creator<ColdFailLogBean>() {
        @Override
        public ColdFailLogBean createFromParcel(Parcel parcel) {
            return new ColdFailLogBean(parcel);
        }

        @Override
        public ColdFailLogBean[] newArray(int i) {
            return new ColdFailLogBean[i];
        }
    };
    public String aReturn;
    public String activityName;
    public String address;
    public String error;
    public boolean hasLoad;
    public Long id;
    public String methodName;
    public String status;
    public long time;
    public String transactionStr;

    @Override
    public int describeContents() {
        return 0;
    }

    public String getAReturn() {
        return this.aReturn;
    }

    public String getActivityName() {
        return this.activityName;
    }

    public String getAddress() {
        return this.address;
    }

    public String getError() {
        return this.error;
    }

    public boolean getHasLoad() {
        return this.hasLoad;
    }

    public Long getId() {
        return this.id;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public String getStatus() {
        return this.status;
    }

    public long getTime() {
        return this.time;
    }

    public String getTransactionStr() {
        return this.transactionStr;
    }

    public void setAReturn(String str) {
        this.aReturn = str;
    }

    public void setActivityName(String str) {
        this.activityName = str;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setError(String str) {
        this.error = str;
    }

    public void setHasLoad(boolean z) {
        this.hasLoad = z;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public void setMethodName(String str) {
        this.methodName = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void setTime(long j) {
        this.time = j;
    }

    public void setTransactionStr(String str) {
        this.transactionStr = str;
    }

    public String toString() {
        return "ColdFailLogBean{id=" + this.id + ", activityName='" + this.activityName + "', methodName='" + this.methodName + "', transactionStr='" + this.transactionStr + "', aReturn='" + this.aReturn + "', address='" + this.address + "', hasLoad=" + this.hasLoad + ", time=" + this.time + ", error='" + this.error + "', status='" + this.status + "'}";
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.id);
        parcel.writeString(this.activityName);
        parcel.writeString(this.methodName);
        parcel.writeString(this.transactionStr);
        parcel.writeString(this.aReturn);
        parcel.writeString(this.address);
        parcel.writeByte(this.hasLoad ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.time);
        parcel.writeString(this.error);
        parcel.writeString(this.status);
    }

    public ColdFailLogBean() {
    }

    protected ColdFailLogBean(Parcel parcel) {
        this.id = (Long) parcel.readValue(Long.class.getClassLoader());
        this.activityName = parcel.readString();
        this.methodName = parcel.readString();
        this.transactionStr = parcel.readString();
        this.aReturn = parcel.readString();
        this.address = parcel.readString();
        this.hasLoad = parcel.readByte() != 0;
        this.time = parcel.readLong();
        this.error = parcel.readString();
        this.status = parcel.readString();
    }

    public ColdFailLogBean(Long l, String str, String str2, String str3, String str4, String str5, boolean z, long j, String str6, String str7) {
        this.id = l;
        this.activityName = str;
        this.methodName = str2;
        this.transactionStr = str3;
        this.aReturn = str4;
        this.address = str5;
        this.hasLoad = z;
        this.time = j;
        this.error = str6;
        this.status = str7;
    }
}
