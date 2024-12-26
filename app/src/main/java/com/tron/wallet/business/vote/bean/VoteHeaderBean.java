package com.tron.wallet.business.vote.bean;

import android.os.Parcel;
import android.os.Parcelable;
public class VoteHeaderBean implements Parcelable {
    public static final Parcelable.Creator<VoteHeaderBean> CREATOR = new Parcelable.Creator<VoteHeaderBean>() {
        @Override
        public VoteHeaderBean createFromParcel(Parcel parcel) {
            return new VoteHeaderBean(parcel);
        }

        @Override
        public VoteHeaderBean[] newArray(int i) {
            return new VoteHeaderBean[i];
        }
    };
    private long alreadyVotedNumber;
    private double availableTrx;
    private long availableVotingRights;
    private long totalVotingRights;

    @Override
    public int describeContents() {
        return 0;
    }

    public long getAlreadyVotedNumber() {
        return this.alreadyVotedNumber;
    }

    public double getAvailableTrx() {
        return this.availableTrx;
    }

    public long getAvailableVotingRights() {
        return this.availableVotingRights;
    }

    public long getTotalVotingRights() {
        return this.totalVotingRights;
    }

    public void setAlreadyVotedNumber(long j) {
        this.alreadyVotedNumber = j;
    }

    public void setAvailableTrx(double d) {
        this.availableTrx = d;
    }

    public void setAvailableVotingRights(long j) {
        this.availableVotingRights = j;
    }

    public void setTotalVotingRights(long j) {
        this.totalVotingRights = j;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof VoteHeaderBean;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof VoteHeaderBean) {
            VoteHeaderBean voteHeaderBean = (VoteHeaderBean) obj;
            return voteHeaderBean.canEqual(this) && getAvailableVotingRights() == voteHeaderBean.getAvailableVotingRights() && getTotalVotingRights() == voteHeaderBean.getTotalVotingRights() && getAlreadyVotedNumber() == voteHeaderBean.getAlreadyVotedNumber() && Double.compare(getAvailableTrx(), voteHeaderBean.getAvailableTrx()) == 0;
        }
        return false;
    }

    public int hashCode() {
        long availableVotingRights = getAvailableVotingRights();
        long totalVotingRights = getTotalVotingRights();
        long alreadyVotedNumber = getAlreadyVotedNumber();
        long doubleToLongBits = Double.doubleToLongBits(getAvailableTrx());
        return ((((((((int) (availableVotingRights ^ (availableVotingRights >>> 32))) + 59) * 59) + ((int) (totalVotingRights ^ (totalVotingRights >>> 32)))) * 59) + ((int) (alreadyVotedNumber ^ (alreadyVotedNumber >>> 32)))) * 59) + ((int) ((doubleToLongBits >>> 32) ^ doubleToLongBits));
    }

    public String toString() {
        return "VoteHeaderBean(availableVotingRights=" + getAvailableVotingRights() + ", totalVotingRights=" + getTotalVotingRights() + ", alreadyVotedNumber=" + getAlreadyVotedNumber() + ", availableTrx=" + getAvailableTrx() + ")";
    }

    public VoteHeaderBean() {
    }

    protected VoteHeaderBean(Parcel parcel) {
        this.availableVotingRights = parcel.readLong();
        this.totalVotingRights = parcel.readLong();
        this.alreadyVotedNumber = parcel.readLong();
        this.availableTrx = parcel.readDouble();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.availableVotingRights);
        parcel.writeLong(this.totalVotingRights);
        parcel.writeLong(this.alreadyVotedNumber);
        parcel.writeDouble(this.availableTrx);
    }
}
