package com.tron.wallet.common.bean;
public class StatInput {
    private long actionTime;
    private int actionType;
    private String address;
    private String amount;
    private int contractType;
    private String tokenId;

    public long getActionTime() {
        return this.actionTime;
    }

    public int getActionType() {
        return this.actionType;
    }

    public String getAddress() {
        return this.address;
    }

    public String getAmount() {
        return this.amount;
    }

    public int getContractType() {
        return this.contractType;
    }

    public String getTokenId() {
        return this.tokenId;
    }

    public void setActionTime(long j) {
        this.actionTime = j;
    }

    public void setActionType(int i) {
        this.actionType = i;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setAmount(String str) {
        this.amount = str;
    }

    public void setContractType(int i) {
        this.contractType = i;
    }

    public void setTokenId(String str) {
        this.tokenId = str;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof StatInput;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof StatInput) {
            StatInput statInput = (StatInput) obj;
            if (statInput.canEqual(this) && getActionType() == statInput.getActionType() && getActionTime() == statInput.getActionTime() && getContractType() == statInput.getContractType()) {
                String address = getAddress();
                String address2 = statInput.getAddress();
                if (address != null ? address.equals(address2) : address2 == null) {
                    String tokenId = getTokenId();
                    String tokenId2 = statInput.getTokenId();
                    if (tokenId != null ? tokenId.equals(tokenId2) : tokenId2 == null) {
                        String amount = getAmount();
                        String amount2 = statInput.getAmount();
                        return amount != null ? amount.equals(amount2) : amount2 == null;
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
        long actionTime = getActionTime();
        int actionType = ((((getActionType() + 59) * 59) + ((int) (actionTime ^ (actionTime >>> 32)))) * 59) + getContractType();
        String address = getAddress();
        int hashCode = (actionType * 59) + (address == null ? 43 : address.hashCode());
        String tokenId = getTokenId();
        int hashCode2 = (hashCode * 59) + (tokenId == null ? 43 : tokenId.hashCode());
        String amount = getAmount();
        return (hashCode2 * 59) + (amount != null ? amount.hashCode() : 43);
    }

    public String toString() {
        return "StatInput(address=" + getAddress() + ", actionType=" + getActionType() + ", actionTime=" + getActionTime() + ", tokenId=" + getTokenId() + ", contractType=" + getContractType() + ", amount=" + getAmount() + ")";
    }
}
