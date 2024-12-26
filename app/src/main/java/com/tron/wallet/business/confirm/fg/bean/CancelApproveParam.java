package com.tron.wallet.business.confirm.fg.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.confirm.parambuilder.bean.DappDetailParam;
import com.tron.wallet.common.bean.token.TokenBean;
import org.tron.protos.contract.SmartContractOuterClass;
public class CancelApproveParam extends BaseParam implements Parcelable {
    public static final int ACTIVE = 1;
    public static final Parcelable.Creator<BaseParam> CREATOR = new Parcelable.Creator<BaseParam>() {
        @Override
        public BaseParam createFromParcel(Parcel parcel) {
            return new CancelApproveParam(parcel);
        }

        @Override
        public BaseParam[] newArray(int i) {
            return new DappDetailParam[i];
        }
    };
    public static final int NOT_ACTIVE = 0;
    private String approveAddress;
    private String approveProjectType;
    private int isActive;
    private boolean isTransfer;
    private String num;
    private String ownerAddress;
    private String projectAddress;
    private String tokenAddress;
    private TokenBean tokenBean;
    private String tokenLogo;
    private String tokenName;
    private String tokenSymbol;
    private String tokenType;
    private String trc721Id;
    private SmartContractOuterClass.TriggerSmartContract triggerSmartContract;

    @Override
    public int describeContents() {
        return 0;
    }

    public boolean getActive() {
        return this.isActive == 1;
    }

    public String getApproveAddress() {
        return this.approveAddress;
    }

    public String getApproveProjectType() {
        return this.approveProjectType;
    }

    public int getIsActive() {
        return this.isActive;
    }

    public String getNum() {
        return this.num;
    }

    public String getOwnerAddress() {
        return this.ownerAddress;
    }

    public String getProjectAddress() {
        return this.projectAddress;
    }

    public String getTokenAddress() {
        return this.tokenAddress;
    }

    public TokenBean getTokenBean() {
        return this.tokenBean;
    }

    public String getTokenLogo() {
        return this.tokenLogo;
    }

    public String getTokenName() {
        return this.tokenName;
    }

    public String getTokenSymbol() {
        return this.tokenSymbol;
    }

    public String getTokenType() {
        return this.tokenType;
    }

    public String getTrc721Id() {
        return this.trc721Id;
    }

    public SmartContractOuterClass.TriggerSmartContract getTriggerSmartContract() {
        return this.triggerSmartContract;
    }

    public boolean isTransfer() {
        return this.isTransfer;
    }

    public void setActive(boolean z) {
        if (z) {
            this.isActive = 1;
        } else {
            this.isActive = 0;
        }
    }

    public void setApproveAddress(String str) {
        this.approveAddress = str;
    }

    public void setApproveProjectType(String str) {
        this.approveProjectType = str;
    }

    public void setIsActive(int i) {
        this.isActive = i;
    }

    public void setNum(String str) {
        this.num = str;
    }

    public void setOwnerAddress(String str) {
        this.ownerAddress = str;
    }

    public void setProjectAddress(String str) {
        this.projectAddress = str;
    }

    public void setTokenAddress(String str) {
        this.tokenAddress = str;
    }

    public void setTokenBean(TokenBean tokenBean) {
        this.tokenBean = tokenBean;
    }

    public void setTokenLogo(String str) {
        this.tokenLogo = str;
    }

    public void setTokenName(String str) {
        this.tokenName = str;
    }

    public void setTokenSymbol(String str) {
        this.tokenSymbol = str;
    }

    public void setTokenType(String str) {
        this.tokenType = str;
    }

    public void setTransfer(boolean z) {
        this.isTransfer = z;
    }

    public void setTrc721Id(String str) {
        this.trc721Id = str;
    }

    public void setTriggerSmartContract(SmartContractOuterClass.TriggerSmartContract triggerSmartContract) {
        this.triggerSmartContract = triggerSmartContract;
    }

    public String toString() {
        return "CancelApproveParam(super=" + super.toString() + ", ownerAddress=" + getOwnerAddress() + ", tokenBean=" + getTokenBean() + ", tokenAddress=" + getTokenAddress() + ", projectAddress=" + getProjectAddress() + ", tokenLogo=" + getTokenLogo() + ", tokenName=" + getTokenName() + ", tokenSymbol=" + getTokenSymbol() + ", tokenType=" + getTokenType() + ", approveProjectType=" + getApproveProjectType() + ", approveAddress=" + getApproveAddress() + ", num=" + getNum() + ", trc721Id=" + getTrc721Id() + ", isTransfer=" + isTransfer() + ", isActive=" + getIsActive() + ", triggerSmartContract=" + getTriggerSmartContract() + ")";
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof CancelApproveParam;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CancelApproveParam) {
            CancelApproveParam cancelApproveParam = (CancelApproveParam) obj;
            if (cancelApproveParam.canEqual(this) && isTransfer() == cancelApproveParam.isTransfer() && getIsActive() == cancelApproveParam.getIsActive()) {
                String ownerAddress = getOwnerAddress();
                String ownerAddress2 = cancelApproveParam.getOwnerAddress();
                if (ownerAddress != null ? ownerAddress.equals(ownerAddress2) : ownerAddress2 == null) {
                    TokenBean tokenBean = getTokenBean();
                    TokenBean tokenBean2 = cancelApproveParam.getTokenBean();
                    if (tokenBean != null ? tokenBean.equals(tokenBean2) : tokenBean2 == null) {
                        String tokenAddress = getTokenAddress();
                        String tokenAddress2 = cancelApproveParam.getTokenAddress();
                        if (tokenAddress != null ? tokenAddress.equals(tokenAddress2) : tokenAddress2 == null) {
                            String projectAddress = getProjectAddress();
                            String projectAddress2 = cancelApproveParam.getProjectAddress();
                            if (projectAddress != null ? projectAddress.equals(projectAddress2) : projectAddress2 == null) {
                                String tokenLogo = getTokenLogo();
                                String tokenLogo2 = cancelApproveParam.getTokenLogo();
                                if (tokenLogo != null ? tokenLogo.equals(tokenLogo2) : tokenLogo2 == null) {
                                    String tokenName = getTokenName();
                                    String tokenName2 = cancelApproveParam.getTokenName();
                                    if (tokenName != null ? tokenName.equals(tokenName2) : tokenName2 == null) {
                                        String tokenSymbol = getTokenSymbol();
                                        String tokenSymbol2 = cancelApproveParam.getTokenSymbol();
                                        if (tokenSymbol != null ? tokenSymbol.equals(tokenSymbol2) : tokenSymbol2 == null) {
                                            String tokenType = getTokenType();
                                            String tokenType2 = cancelApproveParam.getTokenType();
                                            if (tokenType != null ? tokenType.equals(tokenType2) : tokenType2 == null) {
                                                String approveProjectType = getApproveProjectType();
                                                String approveProjectType2 = cancelApproveParam.getApproveProjectType();
                                                if (approveProjectType != null ? approveProjectType.equals(approveProjectType2) : approveProjectType2 == null) {
                                                    String approveAddress = getApproveAddress();
                                                    String approveAddress2 = cancelApproveParam.getApproveAddress();
                                                    if (approveAddress != null ? approveAddress.equals(approveAddress2) : approveAddress2 == null) {
                                                        String num = getNum();
                                                        String num2 = cancelApproveParam.getNum();
                                                        if (num != null ? num.equals(num2) : num2 == null) {
                                                            String trc721Id = getTrc721Id();
                                                            String trc721Id2 = cancelApproveParam.getTrc721Id();
                                                            if (trc721Id != null ? trc721Id.equals(trc721Id2) : trc721Id2 == null) {
                                                                SmartContractOuterClass.TriggerSmartContract triggerSmartContract = getTriggerSmartContract();
                                                                SmartContractOuterClass.TriggerSmartContract triggerSmartContract2 = cancelApproveParam.getTriggerSmartContract();
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
            return false;
        }
        return false;
    }

    public int hashCode() {
        int isActive = (((isTransfer() ? 79 : 97) + 59) * 59) + getIsActive();
        String ownerAddress = getOwnerAddress();
        int hashCode = (isActive * 59) + (ownerAddress == null ? 43 : ownerAddress.hashCode());
        TokenBean tokenBean = getTokenBean();
        int hashCode2 = (hashCode * 59) + (tokenBean == null ? 43 : tokenBean.hashCode());
        String tokenAddress = getTokenAddress();
        int hashCode3 = (hashCode2 * 59) + (tokenAddress == null ? 43 : tokenAddress.hashCode());
        String projectAddress = getProjectAddress();
        int hashCode4 = (hashCode3 * 59) + (projectAddress == null ? 43 : projectAddress.hashCode());
        String tokenLogo = getTokenLogo();
        int hashCode5 = (hashCode4 * 59) + (tokenLogo == null ? 43 : tokenLogo.hashCode());
        String tokenName = getTokenName();
        int hashCode6 = (hashCode5 * 59) + (tokenName == null ? 43 : tokenName.hashCode());
        String tokenSymbol = getTokenSymbol();
        int hashCode7 = (hashCode6 * 59) + (tokenSymbol == null ? 43 : tokenSymbol.hashCode());
        String tokenType = getTokenType();
        int hashCode8 = (hashCode7 * 59) + (tokenType == null ? 43 : tokenType.hashCode());
        String approveProjectType = getApproveProjectType();
        int hashCode9 = (hashCode8 * 59) + (approveProjectType == null ? 43 : approveProjectType.hashCode());
        String approveAddress = getApproveAddress();
        int hashCode10 = (hashCode9 * 59) + (approveAddress == null ? 43 : approveAddress.hashCode());
        String num = getNum();
        int hashCode11 = (hashCode10 * 59) + (num == null ? 43 : num.hashCode());
        String trc721Id = getTrc721Id();
        int hashCode12 = (hashCode11 * 59) + (trc721Id == null ? 43 : trc721Id.hashCode());
        SmartContractOuterClass.TriggerSmartContract triggerSmartContract = getTriggerSmartContract();
        return (hashCode12 * 59) + (triggerSmartContract != null ? triggerSmartContract.hashCode() : 43);
    }

    public CancelApproveParam() {
    }

    protected CancelApproveParam(Parcel parcel) {
        super(parcel);
        this.ownerAddress = parcel.readString();
        this.tokenAddress = parcel.readString();
        this.projectAddress = parcel.readString();
        this.tokenSymbol = parcel.readString();
        this.tokenName = parcel.readString();
        this.tokenLogo = parcel.readString();
        this.tokenType = parcel.readString();
        this.approveProjectType = parcel.readString();
        this.approveAddress = parcel.readString();
        this.num = parcel.readString();
        this.trc721Id = parcel.readString();
        this.isActive = parcel.readInt();
        this.tokenBean = (TokenBean) parcel.readParcelable(TokenBean.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.ownerAddress);
        parcel.writeString(this.tokenAddress);
        parcel.writeString(this.projectAddress);
        parcel.writeString(this.tokenSymbol);
        parcel.writeString(this.tokenName);
        parcel.writeString(this.tokenLogo);
        parcel.writeString(this.tokenType);
        parcel.writeString(this.approveProjectType);
        parcel.writeString(this.approveAddress);
        parcel.writeString(this.num);
        parcel.writeString(this.trc721Id);
        parcel.writeInt(this.isActive);
        parcel.writeParcelable(this.tokenBean, i);
    }
}
