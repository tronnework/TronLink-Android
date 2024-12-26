package com.tron.wallet.common.utils.progressmanager;

import android.os.Parcel;
import android.os.Parcelable;
public class ProgressInfo implements Parcelable {
    public static final Parcelable.Creator<ProgressInfo> CREATOR = new Parcelable.Creator<ProgressInfo>() {
        @Override
        public ProgressInfo createFromParcel(Parcel parcel) {
            return new ProgressInfo(parcel);
        }

        @Override
        public ProgressInfo[] newArray(int i) {
            return new ProgressInfo[i];
        }
    };
    private long contentLength;
    private long currentBytes;
    private long eachBytes;
    private boolean finish;
    private long id;
    private long intervalTime;

    @Override
    public int describeContents() {
        return 0;
    }

    public long getContentLength() {
        return this.contentLength;
    }

    public long getCurrentbytes() {
        return this.currentBytes;
    }

    public long getEachBytes() {
        return this.eachBytes;
    }

    public long getId() {
        return this.id;
    }

    public long getIntervalTime() {
        return this.intervalTime;
    }

    public boolean isFinish() {
        return this.finish;
    }

    public void setContentLength(long j) {
        this.contentLength = j;
    }

    public void setCurrentbytes(long j) {
        this.currentBytes = j;
    }

    public void setEachBytes(long j) {
        this.eachBytes = j;
    }

    public void setFinish(boolean z) {
        this.finish = z;
    }

    public void setIntervalTime(long j) {
        this.intervalTime = j;
    }

    public ProgressInfo(long j) {
        this.id = j;
    }

    public int getPercent() {
        if (getCurrentbytes() <= 0 || getContentLength() <= 0) {
            return 0;
        }
        return (int) ((getCurrentbytes() * 100) / getContentLength());
    }

    public long getSpeed() {
        if (getEachBytes() <= 0 || getIntervalTime() <= 0) {
            return 0L;
        }
        return (getEachBytes() * 1000) / getIntervalTime();
    }

    public String toString() {
        return "ProgressInfo{id=" + this.id + ", currentBytes=" + this.currentBytes + ", contentLength=" + this.contentLength + ", eachBytes=" + this.eachBytes + ", intervalTime=" + this.intervalTime + ", finish=" + this.finish + '}';
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.currentBytes);
        parcel.writeLong(this.contentLength);
        parcel.writeLong(this.intervalTime);
        parcel.writeLong(this.eachBytes);
        parcel.writeLong(this.id);
        parcel.writeByte(this.finish ? (byte) 1 : (byte) 0);
    }

    protected ProgressInfo(Parcel parcel) {
        this.currentBytes = parcel.readLong();
        this.contentLength = parcel.readLong();
        this.intervalTime = parcel.readLong();
        this.eachBytes = parcel.readLong();
        this.id = parcel.readLong();
        this.finish = parcel.readByte() != 0;
    }
}
