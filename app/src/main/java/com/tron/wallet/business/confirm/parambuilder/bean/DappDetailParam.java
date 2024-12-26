package com.tron.wallet.business.confirm.parambuilder.bean;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import com.tron.wallet.business.tabdapp.bean.DappProjectInfoBean;
import com.tron.wallet.common.bean.token.TokenBean;
import org.tron.protos.contract.SmartContractOuterClass;
import org.tron.walletserver.TriggerData;
public class DappDetailParam extends BaseParam implements Parcelable {
    public static final int ACTIVE = 1;
    public static final Parcelable.Creator<BaseParam> CREATOR = new Parcelable.Creator<BaseParam>() {
        @Override
        public BaseParam createFromParcel(Parcel parcel) {
            return new DappDetailParam(parcel);
        }

        @Override
        public BaseParam[] newArray(int i) {
            return new DappDetailParam[i];
        }
    };
    public static final int NOT_ACTIVE = 0;
    private String amount;
    private String assetName;
    private String contractAddress;
    private int contractType;
    private String contractTypeString;
    private DappProjectInfoBean dappProjectInfoBean;
    private boolean isAccount;
    private int isActive;
    private boolean isApprove;
    private boolean isDetail;
    private boolean isTransfer;
    private boolean isUpdatePermission;
    private String note;
    private String ownerAddress;
    private int titleId;
    private String toAddress;
    private int tokenActionType;
    private TokenBean tokenBean;
    private TriggerData triggerData;
    private SmartContractOuterClass.TriggerSmartContract triggerSmartContract;

    @Override
    public int describeContents() {
        return 0;
    }

    public boolean getActive() {
        return this.isActive == 1;
    }

    public String getAmount() {
        return this.amount;
    }

    public String getAssetName() {
        return this.assetName;
    }

    public String getContractAddress() {
        return this.contractAddress;
    }

    public int getContractType() {
        return this.contractType;
    }

    public String getContractTypeString() {
        return this.contractTypeString;
    }

    public DappProjectInfoBean getDappProjectInfoBean() {
        return this.dappProjectInfoBean;
    }

    public int getIsActive() {
        return this.isActive;
    }

    public String getNote() {
        return this.note;
    }

    public String getOwnerAddress() {
        return this.ownerAddress;
    }

    public int getTitleId() {
        return this.titleId;
    }

    public String getToAddress() {
        return this.toAddress;
    }

    public int getTokenActionType() {
        return this.tokenActionType;
    }

    public TokenBean getTokenBean() {
        return this.tokenBean;
    }

    public TriggerData getTriggerData() {
        return this.triggerData;
    }

    public SmartContractOuterClass.TriggerSmartContract getTriggerSmartContract() {
        return this.triggerSmartContract;
    }

    public boolean isAccount() {
        return this.isAccount;
    }

    public boolean isApprove() {
        return this.isApprove;
    }

    public boolean isDetail() {
        return this.isDetail;
    }

    public boolean isTransfer() {
        return this.isTransfer;
    }

    public boolean isUpdatePermission() {
        return this.isUpdatePermission;
    }

    public void setAccount(boolean z) {
        this.isAccount = z;
    }

    public void setActive(boolean z) {
        if (z) {
            this.isActive = 1;
        } else {
            this.isActive = 0;
        }
    }

    public void setAmount(String str) {
        this.amount = str;
    }

    public void setApprove(boolean z) {
        this.isApprove = z;
    }

    public void setAssetName(String str) {
        this.assetName = str;
    }

    public void setContractAddress(String str) {
        this.contractAddress = str;
    }

    public void setContractType(int i) {
        this.contractType = i;
    }

    public void setContractTypeString(String str) {
        this.contractTypeString = str;
    }

    public void setDappProjectInfoBean(DappProjectInfoBean dappProjectInfoBean) {
        this.dappProjectInfoBean = dappProjectInfoBean;
    }

    public void setDetail(boolean z) {
        this.isDetail = z;
    }

    public void setIsActive(int i) {
        this.isActive = i;
    }

    public void setNote(String str) {
        this.note = str;
    }

    public void setOwnerAddress(String str) {
        this.ownerAddress = str;
    }

    public void setTitleId(int i) {
        this.titleId = i;
    }

    public void setToAddress(String str) {
        this.toAddress = str;
    }

    public void setTokenActionType(int i) {
        this.tokenActionType = i;
    }

    public void setTokenBean(TokenBean tokenBean) {
        this.tokenBean = tokenBean;
    }

    public void setTransfer(boolean z) {
        this.isTransfer = z;
    }

    public void setTriggerData(TriggerData triggerData) {
        this.triggerData = triggerData;
    }

    public void setTriggerSmartContract(SmartContractOuterClass.TriggerSmartContract triggerSmartContract) {
        this.triggerSmartContract = triggerSmartContract;
    }

    public void setUpdatePermission(boolean z) {
        this.isUpdatePermission = z;
    }

    public String toString() {
        return "DappDetailParam(super=" + super.toString() + ", contractType=" + getContractType() + ", amount=" + getAmount() + ", ownerAddress=" + getOwnerAddress() + ", toAddress=" + getToAddress() + ", tokenBean=" + getTokenBean() + ", contractTypeString=" + getContractTypeString() + ", contractAddress=" + getContractAddress() + ", note=" + getNote() + ", assetName=" + getAssetName() + ", tokenActionType=" + getTokenActionType() + ", triggerData=" + getTriggerData() + ", titleId=" + getTitleId() + ", dappProjectInfoBean=" + getDappProjectInfoBean() + ", isAccount=" + isAccount() + ", isDetail=" + isDetail() + ", isApprove=" + isApprove() + ", isUpdatePermission=" + isUpdatePermission() + ", isTransfer=" + isTransfer() + ", isActive=" + getIsActive() + ", triggerSmartContract=" + getTriggerSmartContract() + ")";
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof DappDetailParam;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DappDetailParam) {
            DappDetailParam dappDetailParam = (DappDetailParam) obj;
            if (dappDetailParam.canEqual(this) && getContractType() == dappDetailParam.getContractType() && getTokenActionType() == dappDetailParam.getTokenActionType() && getTitleId() == dappDetailParam.getTitleId() && isAccount() == dappDetailParam.isAccount() && isDetail() == dappDetailParam.isDetail() && isApprove() == dappDetailParam.isApprove() && isUpdatePermission() == dappDetailParam.isUpdatePermission() && isTransfer() == dappDetailParam.isTransfer() && getIsActive() == dappDetailParam.getIsActive()) {
                String amount = getAmount();
                String amount2 = dappDetailParam.getAmount();
                if (amount != null ? amount.equals(amount2) : amount2 == null) {
                    String ownerAddress = getOwnerAddress();
                    String ownerAddress2 = dappDetailParam.getOwnerAddress();
                    if (ownerAddress != null ? ownerAddress.equals(ownerAddress2) : ownerAddress2 == null) {
                        String toAddress = getToAddress();
                        String toAddress2 = dappDetailParam.getToAddress();
                        if (toAddress != null ? toAddress.equals(toAddress2) : toAddress2 == null) {
                            TokenBean tokenBean = getTokenBean();
                            TokenBean tokenBean2 = dappDetailParam.getTokenBean();
                            if (tokenBean != null ? tokenBean.equals(tokenBean2) : tokenBean2 == null) {
                                String contractTypeString = getContractTypeString();
                                String contractTypeString2 = dappDetailParam.getContractTypeString();
                                if (contractTypeString != null ? contractTypeString.equals(contractTypeString2) : contractTypeString2 == null) {
                                    String contractAddress = getContractAddress();
                                    String contractAddress2 = dappDetailParam.getContractAddress();
                                    if (contractAddress != null ? contractAddress.equals(contractAddress2) : contractAddress2 == null) {
                                        String note = getNote();
                                        String note2 = dappDetailParam.getNote();
                                        if (note != null ? note.equals(note2) : note2 == null) {
                                            String assetName = getAssetName();
                                            String assetName2 = dappDetailParam.getAssetName();
                                            if (assetName != null ? assetName.equals(assetName2) : assetName2 == null) {
                                                TriggerData triggerData = getTriggerData();
                                                TriggerData triggerData2 = dappDetailParam.getTriggerData();
                                                if (triggerData != null ? triggerData.equals(triggerData2) : triggerData2 == null) {
                                                    DappProjectInfoBean dappProjectInfoBean = getDappProjectInfoBean();
                                                    DappProjectInfoBean dappProjectInfoBean2 = dappDetailParam.getDappProjectInfoBean();
                                                    if (dappProjectInfoBean != null ? dappProjectInfoBean.equals(dappProjectInfoBean2) : dappProjectInfoBean2 == null) {
                                                        SmartContractOuterClass.TriggerSmartContract triggerSmartContract = getTriggerSmartContract();
                                                        SmartContractOuterClass.TriggerSmartContract triggerSmartContract2 = dappDetailParam.getTriggerSmartContract();
                                                        return triggerSmartContract != null ? triggerSmartContract.equals(triggerSmartContract2) : triggerSmartContract2 == null;
                                                    }
                                                    return false;
                                                }
                                                return false;
                                            }
                                            return false;
                                        }
                                        return false;
                                    }
                                    return false;
                                }
                                return false;
                            }
                            return false;
                        }
                        return false;
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
        int contractType = ((((((((((((((((getContractType() + 59) * 59) + getTokenActionType()) * 59) + getTitleId()) * 59) + (isAccount() ? 79 : 97)) * 59) + (isDetail() ? 79 : 97)) * 59) + (isApprove() ? 79 : 97)) * 59) + (isUpdatePermission() ? 79 : 97)) * 59) + (isTransfer() ? 79 : 97)) * 59) + getIsActive();
        String amount = getAmount();
        int hashCode = (contractType * 59) + (amount == null ? 43 : amount.hashCode());
        String ownerAddress = getOwnerAddress();
        int hashCode2 = (hashCode * 59) + (ownerAddress == null ? 43 : ownerAddress.hashCode());
        String toAddress = getToAddress();
        int hashCode3 = (hashCode2 * 59) + (toAddress == null ? 43 : toAddress.hashCode());
        TokenBean tokenBean = getTokenBean();
        int hashCode4 = (hashCode3 * 59) + (tokenBean == null ? 43 : tokenBean.hashCode());
        String contractTypeString = getContractTypeString();
        int hashCode5 = (hashCode4 * 59) + (contractTypeString == null ? 43 : contractTypeString.hashCode());
        String contractAddress = getContractAddress();
        int hashCode6 = (hashCode5 * 59) + (contractAddress == null ? 43 : contractAddress.hashCode());
        String note = getNote();
        int hashCode7 = (hashCode6 * 59) + (note == null ? 43 : note.hashCode());
        String assetName = getAssetName();
        int hashCode8 = (hashCode7 * 59) + (assetName == null ? 43 : assetName.hashCode());
        TriggerData triggerData = getTriggerData();
        int hashCode9 = (hashCode8 * 59) + (triggerData == null ? 43 : triggerData.hashCode());
        DappProjectInfoBean dappProjectInfoBean = getDappProjectInfoBean();
        int hashCode10 = (hashCode9 * 59) + (dappProjectInfoBean == null ? 43 : dappProjectInfoBean.hashCode());
        SmartContractOuterClass.TriggerSmartContract triggerSmartContract = getTriggerSmartContract();
        return (hashCode10 * 59) + (triggerSmartContract != null ? triggerSmartContract.hashCode() : 43);
    }

    public DappDetailParam() {
    }

    protected DappDetailParam(Parcel parcel) {
        super(parcel);
        boolean readBoolean;
        boolean readBoolean2;
        this.amount = parcel.readString();
        this.contractType = parcel.readInt();
        this.ownerAddress = parcel.readString();
        this.toAddress = parcel.readString();
        this.contractTypeString = parcel.readString();
        this.contractAddress = parcel.readString();
        this.note = parcel.readString();
        this.assetName = parcel.readString();
        this.tokenActionType = parcel.readInt();
        this.titleId = parcel.readInt();
        if (Build.VERSION.SDK_INT >= 29) {
            readBoolean2 = parcel.readBoolean();
            this.isDetail = readBoolean2;
        } else {
            this.isDetail = parcel.readByte() != 0;
        }
        if (Build.VERSION.SDK_INT >= 29) {
            readBoolean = parcel.readBoolean();
            this.isAccount = readBoolean;
        } else {
            this.isAccount = parcel.readByte() != 0;
        }
        this.isActive = parcel.readInt();
        this.tokenBean = (TokenBean) parcel.readParcelable(TokenBean.class.getClassLoader());
        this.dappProjectInfoBean = (DappProjectInfoBean) parcel.readParcelable(DappProjectInfoBean.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.amount);
        parcel.writeInt(this.contractType);
        parcel.writeString(this.ownerAddress);
        parcel.writeString(this.toAddress);
        parcel.writeString(this.contractTypeString);
        parcel.writeString(this.contractAddress);
        parcel.writeString(this.note);
        parcel.writeString(this.assetName);
        parcel.writeInt(this.tokenActionType);
        parcel.writeInt(this.titleId);
        if (Build.VERSION.SDK_INT >= 29) {
            parcel.writeBoolean(this.isDetail);
        } else {
            parcel.writeByte(this.isDetail ? (byte) 1 : (byte) 0);
        }
        if (Build.VERSION.SDK_INT >= 29) {
            parcel.writeBoolean(this.isAccount);
        } else {
            parcel.writeByte(this.isAccount ? (byte) 1 : (byte) 0);
        }
        parcel.writeInt(this.isActive);
        parcel.writeParcelable(this.tokenBean, i);
        parcel.writeParcelable(this.dappProjectInfoBean, i);
    }
}
