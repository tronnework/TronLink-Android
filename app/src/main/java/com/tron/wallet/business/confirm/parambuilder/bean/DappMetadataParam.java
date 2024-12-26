package com.tron.wallet.business.confirm.parambuilder.bean;

import android.os.Parcel;
import android.os.Parcelable;
import org.tron.walletserver.TriggerData;
public class DappMetadataParam extends BaseParam implements Parcelable {
    public static final Parcelable.Creator<BaseParam> CREATOR = new Parcelable.Creator<BaseParam>() {
        @Override
        public BaseParam createFromParcel(Parcel parcel) {
            return new DappMetadataParam(parcel);
        }

        @Override
        public BaseParam[] newArray(int i) {
            return new DappMetadataParam[i];
        }
    };
    private String amount;
    private String contractMethodName;
    private TriggerData triggerData;

    @Override
    public int describeContents() {
        return 0;
    }

    public String getAmount() {
        return this.amount;
    }

    public String getContractMethodName() {
        return this.contractMethodName;
    }

    public TriggerData getTriggerData() {
        return this.triggerData;
    }

    public void setAmount(String str) {
        this.amount = str;
    }

    public void setContractMethodName(String str) {
        this.contractMethodName = str;
    }

    public void setTriggerData(TriggerData triggerData) {
        this.triggerData = triggerData;
    }

    public String toString() {
        return "DappMetadataParam(super=" + super.toString() + ", amount=" + getAmount() + ", contractMethodName=" + getContractMethodName() + ", triggerData=" + getTriggerData() + ")";
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof DappMetadataParam;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DappMetadataParam) {
            DappMetadataParam dappMetadataParam = (DappMetadataParam) obj;
            if (dappMetadataParam.canEqual(this)) {
                String amount = getAmount();
                String amount2 = dappMetadataParam.getAmount();
                if (amount != null ? amount.equals(amount2) : amount2 == null) {
                    String contractMethodName = getContractMethodName();
                    String contractMethodName2 = dappMetadataParam.getContractMethodName();
                    if (contractMethodName != null ? contractMethodName.equals(contractMethodName2) : contractMethodName2 == null) {
                        TriggerData triggerData = getTriggerData();
                        TriggerData triggerData2 = dappMetadataParam.getTriggerData();
                        return triggerData != null ? triggerData.equals(triggerData2) : triggerData2 == null;
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
        String amount = getAmount();
        int hashCode = amount == null ? 43 : amount.hashCode();
        String contractMethodName = getContractMethodName();
        int hashCode2 = ((hashCode + 59) * 59) + (contractMethodName == null ? 43 : contractMethodName.hashCode());
        TriggerData triggerData = getTriggerData();
        return (hashCode2 * 59) + (triggerData != null ? triggerData.hashCode() : 43);
    }

    public DappMetadataParam() {
    }

    protected DappMetadataParam(Parcel parcel) {
        super(parcel);
        this.amount = parcel.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.amount);
    }
}
