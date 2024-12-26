package com.tron.wallet.business.mutil.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.StringTronUtil;
public class MultiSignPermissionData implements Parcelable {
    public static final Parcelable.Creator<MultiSignPermissionData> CREATOR = new Parcelable.Creator<MultiSignPermissionData>() {
        @Override
        public MultiSignPermissionData createFromParcel(Parcel parcel) {
            return new MultiSignPermissionData(parcel);
        }

        @Override
        public MultiSignPermissionData[] newArray(int i) {
            return new MultiSignPermissionData[i];
        }
    };
    private boolean VoteWitnessPermission;
    private boolean WithdrawBalancePermission;
    private boolean cancelAllUnfreezePermission;
    private boolean delegateResourcePermission;
    private boolean freezeBalanceV2Permission;
    private boolean stakePermission;
    private boolean transferTRC10Permission;
    private boolean transferTRC20Permission;
    private boolean transferTRXPermission;
    private boolean unDelegateResourcePermission;
    private boolean unStakePermission;
    private boolean unfreezeBalanceV2Permission;
    private boolean withdrawExpireUnfreezePermission;

    @Override
    public int describeContents() {
        return 0;
    }

    public boolean isCancelAllUnfreezePermission() {
        return this.cancelAllUnfreezePermission;
    }

    public boolean isDelegateResourcePermission() {
        return this.delegateResourcePermission;
    }

    public boolean isFreezeBalanceV2Permission() {
        return this.freezeBalanceV2Permission;
    }

    public boolean isStakePermission() {
        return this.stakePermission;
    }

    public boolean isTransferTRC10Permission() {
        return this.transferTRC10Permission;
    }

    public boolean isTransferTRC20Permission() {
        return this.transferTRC20Permission;
    }

    public boolean isTransferTRXPermission() {
        return this.transferTRXPermission;
    }

    public boolean isUnDelegateResourcePermission() {
        return this.unDelegateResourcePermission;
    }

    public boolean isUnStakePermission() {
        return this.unStakePermission;
    }

    public boolean isUnfreezeBalanceV2Permission() {
        return this.unfreezeBalanceV2Permission;
    }

    public boolean isVoteWitnessPermission() {
        return this.VoteWitnessPermission;
    }

    public boolean isWithdrawBalancePermission() {
        return this.WithdrawBalancePermission;
    }

    public boolean isWithdrawExpireUnfreezePermission() {
        return this.withdrawExpireUnfreezePermission;
    }

    public void setCancelAllUnfreezePermission(boolean z) {
        this.cancelAllUnfreezePermission = z;
    }

    public void setDelegateResourcePermission(boolean z) {
        this.delegateResourcePermission = z;
    }

    public void setFreezeBalanceV2Permission(boolean z) {
        this.freezeBalanceV2Permission = z;
    }

    public void setStakePermission(boolean z) {
        this.stakePermission = z;
    }

    public void setTransferTRC10Permission(boolean z) {
        this.transferTRC10Permission = z;
    }

    public void setTransferTRC20Permission(boolean z) {
        this.transferTRC20Permission = z;
    }

    public void setTransferTRXPermission(boolean z) {
        this.transferTRXPermission = z;
    }

    public void setUnDelegateResourcePermission(boolean z) {
        this.unDelegateResourcePermission = z;
    }

    public void setUnStakePermission(boolean z) {
        this.unStakePermission = z;
    }

    public void setUnfreezeBalanceV2Permission(boolean z) {
        this.unfreezeBalanceV2Permission = z;
    }

    public void setVoteWitnessPermission(boolean z) {
        this.VoteWitnessPermission = z;
    }

    public void setWithdrawBalancePermission(boolean z) {
        this.WithdrawBalancePermission = z;
    }

    public void setWithdrawExpireUnfreezePermission(boolean z) {
        this.withdrawExpireUnfreezePermission = z;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof MultiSignPermissionData;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MultiSignPermissionData) {
            MultiSignPermissionData multiSignPermissionData = (MultiSignPermissionData) obj;
            return multiSignPermissionData.canEqual(this) && isTransferTRXPermission() == multiSignPermissionData.isTransferTRXPermission() && isTransferTRC10Permission() == multiSignPermissionData.isTransferTRC10Permission() && isTransferTRC20Permission() == multiSignPermissionData.isTransferTRC20Permission() && isStakePermission() == multiSignPermissionData.isStakePermission() && isUnStakePermission() == multiSignPermissionData.isUnStakePermission() && isVoteWitnessPermission() == multiSignPermissionData.isVoteWitnessPermission() && isWithdrawBalancePermission() == multiSignPermissionData.isWithdrawBalancePermission() && isFreezeBalanceV2Permission() == multiSignPermissionData.isFreezeBalanceV2Permission() && isUnfreezeBalanceV2Permission() == multiSignPermissionData.isUnfreezeBalanceV2Permission() && isWithdrawExpireUnfreezePermission() == multiSignPermissionData.isWithdrawExpireUnfreezePermission() && isDelegateResourcePermission() == multiSignPermissionData.isDelegateResourcePermission() && isUnDelegateResourcePermission() == multiSignPermissionData.isUnDelegateResourcePermission() && isCancelAllUnfreezePermission() == multiSignPermissionData.isCancelAllUnfreezePermission();
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((isTransferTRXPermission() ? 79 : 97) + 59) * 59) + (isTransferTRC10Permission() ? 79 : 97)) * 59) + (isTransferTRC20Permission() ? 79 : 97)) * 59) + (isStakePermission() ? 79 : 97)) * 59) + (isUnStakePermission() ? 79 : 97)) * 59) + (isVoteWitnessPermission() ? 79 : 97)) * 59) + (isWithdrawBalancePermission() ? 79 : 97)) * 59) + (isFreezeBalanceV2Permission() ? 79 : 97)) * 59) + (isUnfreezeBalanceV2Permission() ? 79 : 97)) * 59) + (isWithdrawExpireUnfreezePermission() ? 79 : 97)) * 59) + (isDelegateResourcePermission() ? 79 : 97)) * 59) + (isUnDelegateResourcePermission() ? 79 : 97)) * 59) + (isCancelAllUnfreezePermission() ? 79 : 97);
    }

    public String toString() {
        return "MultiSignPermissionData(transferTRXPermission=" + isTransferTRXPermission() + ", transferTRC10Permission=" + isTransferTRC10Permission() + ", transferTRC20Permission=" + isTransferTRC20Permission() + ", stakePermission=" + isStakePermission() + ", unStakePermission=" + isUnStakePermission() + ", VoteWitnessPermission=" + isVoteWitnessPermission() + ", WithdrawBalancePermission=" + isWithdrawBalancePermission() + ", freezeBalanceV2Permission=" + isFreezeBalanceV2Permission() + ", unfreezeBalanceV2Permission=" + isUnfreezeBalanceV2Permission() + ", withdrawExpireUnfreezePermission=" + isWithdrawExpireUnfreezePermission() + ", delegateResourcePermission=" + isDelegateResourcePermission() + ", unDelegateResourcePermission=" + isUnDelegateResourcePermission() + ", cancelAllUnfreezePermission=" + isCancelAllUnfreezePermission() + ")";
    }

    public MultiSignPermissionData() {
    }

    protected MultiSignPermissionData(Parcel parcel) {
        this.transferTRXPermission = parcel.readByte() != 0;
        this.transferTRC10Permission = parcel.readByte() != 0;
        this.transferTRC20Permission = parcel.readByte() != 0;
        this.stakePermission = parcel.readByte() != 0;
        this.unStakePermission = parcel.readByte() != 0;
        this.VoteWitnessPermission = parcel.readByte() != 0;
        this.WithdrawBalancePermission = parcel.readByte() != 0;
        this.freezeBalanceV2Permission = parcel.readByte() != 0;
        this.unfreezeBalanceV2Permission = parcel.readByte() != 0;
        this.withdrawExpireUnfreezePermission = parcel.readByte() != 0;
        this.delegateResourcePermission = parcel.readByte() != 0;
        this.unDelegateResourcePermission = parcel.readByte() != 0;
        this.cancelAllUnfreezePermission = parcel.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.transferTRXPermission ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.transferTRC10Permission ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.transferTRC20Permission ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.stakePermission ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.unStakePermission ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.VoteWitnessPermission ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.WithdrawBalancePermission ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.freezeBalanceV2Permission ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.unfreezeBalanceV2Permission ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.withdrawExpireUnfreezePermission ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.delegateResourcePermission ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.unDelegateResourcePermission ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.cancelAllUnfreezePermission ? (byte) 1 : (byte) 0);
    }

    public static String[] getPermissionTip(MultiSignPermissionData multiSignPermissionData) {
        String str;
        String str2;
        String[] strArr = new String[2];
        String str3 = "";
        if (multiSignPermissionData == null || (multiSignPermissionData.isTransferTRXPermission() && multiSignPermissionData.isTransferTRC10Permission() && multiSignPermissionData.isTransferTRC20Permission())) {
            str = "";
        } else {
            String str4 = "TRX";
            if (!multiSignPermissionData.isTransferTRXPermission()) {
                str4 = "";
                str3 = "TRX";
            }
            boolean isTransferTRC10Permission = multiSignPermissionData.isTransferTRC10Permission();
            String str5 = TronConfig.TRC10;
            if (isTransferTRC10Permission) {
                if (!StringTronUtil.isEmpty(str4)) {
                    str5 = ", TRC10";
                }
                str2 = str4.concat(str5);
            } else {
                if (!StringTronUtil.isEmpty(str3)) {
                    str5 = ", TRC10";
                }
                str3 = str3.concat(str5);
                str2 = str4;
            }
            if (multiSignPermissionData.isTransferTRC20Permission()) {
                StringBuilder sb = new StringBuilder();
                sb.append(str2);
                sb.append(StringTronUtil.isEmpty(str2) ? "TRC20, TRC721" : ", TRC20, TRC721");
                String str6 = str3;
                str3 = sb.toString();
                str = str6;
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str3);
                sb2.append(StringTronUtil.isEmpty(str3) ? "TRC20, TRC721" : ", TRC20, TRC721");
                str = sb2.toString();
                str3 = str2;
            }
        }
        strArr[0] = str3;
        strArr[1] = str;
        return strArr;
    }

    public static MultiSignPermissionData getOwnerBean() {
        MultiSignPermissionData multiSignPermissionData = new MultiSignPermissionData();
        multiSignPermissionData.setTransferTRC20Permission(true);
        multiSignPermissionData.setTransferTRXPermission(true);
        multiSignPermissionData.setTransferTRC10Permission(true);
        multiSignPermissionData.setStakePermission(true);
        multiSignPermissionData.setUnStakePermission(true);
        multiSignPermissionData.setVoteWitnessPermission(true);
        multiSignPermissionData.setWithdrawBalancePermission(true);
        multiSignPermissionData.setFreezeBalanceV2Permission(true);
        multiSignPermissionData.setUnfreezeBalanceV2Permission(true);
        multiSignPermissionData.setWithdrawExpireUnfreezePermission(true);
        multiSignPermissionData.setDelegateResourcePermission(true);
        multiSignPermissionData.setUnDelegateResourcePermission(true);
        multiSignPermissionData.setCancelAllUnfreezePermission(true);
        return multiSignPermissionData;
    }
}
