package com.tron.wallet.business.security.environment.bean;
public class ItemData {
    private int errorTitle;
    private int riskDescribe;
    private int safeDescribe;
    private int title;
    private int waringDescribe;

    public int getErrorTitle() {
        return this.errorTitle;
    }

    public int getRiskDescribe() {
        return this.riskDescribe;
    }

    public int getSafeDescribe() {
        return this.safeDescribe;
    }

    public int getTitle() {
        return this.title;
    }

    public int getWaringDescribe() {
        return this.waringDescribe;
    }

    public void setErrorTitle(int i) {
        this.errorTitle = i;
    }

    public void setRiskDescribe(int i) {
        this.riskDescribe = i;
    }

    public void setSafeDescribe(int i) {
        this.safeDescribe = i;
    }

    public void setTitle(int i) {
        this.title = i;
    }

    public void setWaringDescribe(int i) {
        this.waringDescribe = i;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof ItemData;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ItemData) {
            ItemData itemData = (ItemData) obj;
            return itemData.canEqual(this) && getTitle() == itemData.getTitle() && getErrorTitle() == itemData.getErrorTitle() && getSafeDescribe() == itemData.getSafeDescribe() && getWaringDescribe() == itemData.getWaringDescribe() && getRiskDescribe() == itemData.getRiskDescribe();
        }
        return false;
    }

    public int hashCode() {
        return ((((((((getTitle() + 59) * 59) + getErrorTitle()) * 59) + getSafeDescribe()) * 59) + getWaringDescribe()) * 59) + getRiskDescribe();
    }

    public String toString() {
        return "ItemData(title=" + getTitle() + ", errorTitle=" + getErrorTitle() + ", safeDescribe=" + getSafeDescribe() + ", waringDescribe=" + getWaringDescribe() + ", riskDescribe=" + getRiskDescribe() + ")";
    }
}
