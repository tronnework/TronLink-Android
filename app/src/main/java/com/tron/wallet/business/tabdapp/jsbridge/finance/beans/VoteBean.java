package com.tron.wallet.business.tabdapp.jsbridge.finance.beans;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;
public class VoteBean implements Parcelable {
    public static final Parcelable.Creator<VoteBean> CREATOR = new Parcelable.Creator<VoteBean>() {
        @Override
        public VoteBean createFromParcel(Parcel parcel) {
            return new VoteBean(parcel);
        }

        @Override
        public VoteBean[] newArray(int i) {
            return new VoteBean[i];
        }
    };
    private boolean isSamsung;
    private long oldTotalVotes;
    private HashMap<String, String> srNameMap;
    private HashMap<String, String> srcOrderedVoteMap;
    private long totalVotes;
    private String walletAddress;

    @Override
    public int describeContents() {
        return 0;
    }

    public long getOldTotalVotes() {
        return this.oldTotalVotes;
    }

    public HashMap<String, String> getSrNameMap() {
        return this.srNameMap;
    }

    public HashMap<String, String> getSrcOrderedVoteMap() {
        return this.srcOrderedVoteMap;
    }

    public long getTotalVotes() {
        return this.totalVotes;
    }

    public String getWalletAddress() {
        return this.walletAddress;
    }

    public boolean isSamsung() {
        return this.isSamsung;
    }

    public void setOldTotalVotes(long j) {
        this.oldTotalVotes = j;
    }

    public void setSamsung(boolean z) {
        this.isSamsung = z;
    }

    public void setSrNameMap(HashMap<String, String> hashMap) {
        this.srNameMap = hashMap;
    }

    public void setSrcOrderedVoteMap(HashMap<String, String> hashMap) {
        this.srcOrderedVoteMap = hashMap;
    }

    public void setTotalVotes(long j) {
        this.totalVotes = j;
    }

    public void setWalletAddress(String str) {
        this.walletAddress = str;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof VoteBean;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof VoteBean) {
            VoteBean voteBean = (VoteBean) obj;
            if (voteBean.canEqual(this) && isSamsung() == voteBean.isSamsung() && getTotalVotes() == voteBean.getTotalVotes() && getOldTotalVotes() == voteBean.getOldTotalVotes()) {
                HashMap<String, String> srcOrderedVoteMap = getSrcOrderedVoteMap();
                HashMap<String, String> srcOrderedVoteMap2 = voteBean.getSrcOrderedVoteMap();
                if (srcOrderedVoteMap != null ? srcOrderedVoteMap.equals(srcOrderedVoteMap2) : srcOrderedVoteMap2 == null) {
                    HashMap<String, String> srNameMap = getSrNameMap();
                    HashMap<String, String> srNameMap2 = voteBean.getSrNameMap();
                    if (srNameMap != null ? srNameMap.equals(srNameMap2) : srNameMap2 == null) {
                        String walletAddress = getWalletAddress();
                        String walletAddress2 = voteBean.getWalletAddress();
                        return walletAddress != null ? walletAddress.equals(walletAddress2) : walletAddress2 == null;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int i = isSamsung() ? 79 : 97;
        long totalVotes = getTotalVotes();
        long oldTotalVotes = getOldTotalVotes();
        HashMap<String, String> srcOrderedVoteMap = getSrcOrderedVoteMap();
        int hashCode = ((((((i + 59) * 59) + ((int) (totalVotes ^ (totalVotes >>> 32)))) * 59) + ((int) (oldTotalVotes ^ (oldTotalVotes >>> 32)))) * 59) + (srcOrderedVoteMap == null ? 43 : srcOrderedVoteMap.hashCode());
        HashMap<String, String> srNameMap = getSrNameMap();
        int hashCode2 = (hashCode * 59) + (srNameMap == null ? 43 : srNameMap.hashCode());
        String walletAddress = getWalletAddress();
        return (hashCode2 * 59) + (walletAddress != null ? walletAddress.hashCode() : 43);
    }

    public String toString() {
        return "VoteBean(srcOrderedVoteMap=" + getSrcOrderedVoteMap() + ", srNameMap=" + getSrNameMap() + ", walletAddress=" + getWalletAddress() + ", isSamsung=" + isSamsung() + ", totalVotes=" + getTotalVotes() + ", oldTotalVotes=" + getOldTotalVotes() + ")";
    }

    public VoteBean() {
    }

    protected VoteBean(Parcel parcel) {
        this.srcOrderedVoteMap = (HashMap) parcel.readSerializable();
        this.srNameMap = (HashMap) parcel.readSerializable();
        this.walletAddress = parcel.readString();
        this.isSamsung = parcel.readByte() != 0;
        this.totalVotes = parcel.readLong();
        this.oldTotalVotes = parcel.readLong();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(this.srcOrderedVoteMap);
        parcel.writeSerializable(this.srNameMap);
        parcel.writeString(this.walletAddress);
        parcel.writeByte(this.isSamsung ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.totalVotes);
        parcel.writeLong(this.oldTotalVotes);
    }
}
