package com.tron.wallet.business.confirm.parambuilder.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.tron.wallet.business.mutil.bean.MultiSignPermissionData;
import java.util.HashMap;
public class VoteParam extends BaseParam implements Parcelable {
    public static final Parcelable.Creator<VoteParam> CREATOR = new Parcelable.Creator<VoteParam>() {
        @Override
        public VoteParam createFromParcel(Parcel parcel) {
            return new VoteParam(parcel);
        }

        @Override
        public VoteParam[] newArray(int i) {
            return new VoteParam[i];
        }
    };
    public static final int VOTE_BATCH_CANCEL = 2;
    public static final int VOTE_BATCH_VOTE = 3;
    public static final int VOTE_SINGLE_CANCEL = 1;
    public static final int VOTE_SINGLE_VOTE = 0;
    private int VoteType;
    private HashMap addressWeightMap;
    private String apr;
    private String fromAddress;
    private boolean isFromStakeSuccess;
    private HashMap nameWeightMap;
    private MultiSignPermissionData permissionData;
    private int showVotingTetail;
    private long singleVoteCount;
    private String singleVoteSrName;
    private long votesAvailable;
    private long votesCancelled;

    public int convertToType() {
        int i = this.VoteType;
        return (i == 1 || i == 2) ? 22 : 3;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public HashMap getAddressWeightMap() {
        return this.addressWeightMap;
    }

    public String getApr() {
        return this.apr;
    }

    public String getFromAddress() {
        return this.fromAddress;
    }

    public HashMap getNameWeightMap() {
        return this.nameWeightMap;
    }

    public MultiSignPermissionData getPermissionData() {
        return this.permissionData;
    }

    public int getShowVotingTetail() {
        return this.showVotingTetail;
    }

    public long getSingleVoteCount() {
        return this.singleVoteCount;
    }

    public String getSingleVoteSrName() {
        return this.singleVoteSrName;
    }

    public int getViewType() {
        return this.VoteType;
    }

    public long getVoteCancelled() {
        return this.votesCancelled;
    }

    public long getVotesAvailable() {
        return this.votesAvailable;
    }

    public boolean isFromStakeSuccess() {
        return this.isFromStakeSuccess;
    }

    public void setAddressWeightMap(HashMap hashMap) {
        this.addressWeightMap = hashMap;
    }

    public void setApr(String str) {
        this.apr = str;
    }

    public void setFromAddress(String str) {
        this.fromAddress = str;
    }

    public void setFromStakeSuccess(boolean z) {
        this.isFromStakeSuccess = z;
    }

    public void setNameWeightMap(HashMap hashMap) {
        this.nameWeightMap = hashMap;
    }

    public void setPermissionData(MultiSignPermissionData multiSignPermissionData) {
        this.permissionData = multiSignPermissionData;
    }

    public void setShowVotingTetail(int i) {
        this.showVotingTetail = i;
    }

    public void setSingleVoteCount(long j) {
        this.singleVoteCount = j;
    }

    public void setSingleVoteSrName(String str) {
        this.singleVoteSrName = str;
    }

    public void setVotesAvailable(long j) {
        this.votesAvailable = j;
    }

    public void setVotesCancelled(long j) {
        this.votesCancelled = j;
    }

    public VoteParam() {
        this.showVotingTetail = 0;
    }

    protected VoteParam(Parcel parcel) {
        super(parcel);
        this.showVotingTetail = 0;
        this.nameWeightMap = (HashMap) parcel.readSerializable();
        this.addressWeightMap = (HashMap) parcel.readSerializable();
        this.showVotingTetail = parcel.readInt();
        this.VoteType = parcel.readInt();
        this.fromAddress = parcel.readString();
        this.singleVoteCount = parcel.readLong();
        this.singleVoteSrName = parcel.readString();
        this.votesCancelled = parcel.readLong();
        this.votesAvailable = parcel.readLong();
        this.isFromStakeSuccess = parcel.readInt() == 1;
        this.permissionData = (MultiSignPermissionData) parcel.readParcelable(getClass().getClassLoader());
        this.apr = parcel.readString();
    }

    public void setViewType(int i) {
        this.VoteType = i;
        setType(convertToType());
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeSerializable(this.nameWeightMap);
        parcel.writeSerializable(this.addressWeightMap);
        parcel.writeInt(this.showVotingTetail);
        parcel.writeInt(this.VoteType);
        parcel.writeString(this.fromAddress);
        parcel.writeLong(this.singleVoteCount);
        parcel.writeString(this.singleVoteSrName);
        parcel.writeLong(this.votesCancelled);
        parcel.writeLong(this.votesAvailable);
        parcel.writeInt(this.isFromStakeSuccess ? 1 : 0);
        parcel.writeParcelable(this.permissionData, i);
        parcel.writeString(this.apr);
    }
}
