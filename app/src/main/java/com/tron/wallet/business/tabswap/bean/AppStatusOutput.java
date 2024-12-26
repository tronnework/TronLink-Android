package com.tron.wallet.business.tabswap.bean;
public class AppStatusOutput {
    private boolean fina;
    private boolean hideFinancialTab;
    private boolean hideShieldManager;
    private boolean mainland = true;

    public boolean isFina() {
        return this.fina;
    }

    public boolean isHideFinancialTab() {
        return this.hideFinancialTab;
    }

    public boolean isHideShieldManager() {
        return this.hideShieldManager;
    }

    public boolean isMainland() {
        return this.mainland;
    }

    public void setFina(boolean z) {
        this.fina = z;
    }

    public void setHideFinancialTab(boolean z) {
        this.hideFinancialTab = z;
    }

    public void setHideShieldManager(boolean z) {
        this.hideShieldManager = z;
    }

    public void setMainland(boolean z) {
        this.mainland = z;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof AppStatusOutput;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AppStatusOutput) {
            AppStatusOutput appStatusOutput = (AppStatusOutput) obj;
            return appStatusOutput.canEqual(this) && isMainland() == appStatusOutput.isMainland() && isFina() == appStatusOutput.isFina() && isHideFinancialTab() == appStatusOutput.isHideFinancialTab() && isHideShieldManager() == appStatusOutput.isHideShieldManager();
        }
        return false;
    }

    public int hashCode() {
        return (((((((isMainland() ? 79 : 97) + 59) * 59) + (isFina() ? 79 : 97)) * 59) + (isHideFinancialTab() ? 79 : 97)) * 59) + (isHideShieldManager() ? 79 : 97);
    }

    public String toString() {
        return "AppStatusOutput(mainland=" + isMainland() + ", fina=" + isFina() + ", hideFinancialTab=" + isHideFinancialTab() + ", hideShieldManager=" + isHideShieldManager() + ")";
    }
}
