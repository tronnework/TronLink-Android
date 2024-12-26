package com.tron.wallet.business.walletmanager.selectwallet.bean;
public class FinanceSelectWalletBean extends SelectWalletBean {
    private String allAsset;
    private String financeAsset;
    private String financeBalance;
    private String projectId;
    private String tokenName;

    public String getAllAsset() {
        return this.allAsset;
    }

    public String getFinanceAsset() {
        return this.financeAsset;
    }

    public String getFinanceBalance() {
        return this.financeBalance;
    }

    public String getProjectId() {
        return this.projectId;
    }

    public String getTokenName() {
        return this.tokenName;
    }

    public void setAllAsset(String str) {
        this.allAsset = str;
    }

    public void setFinanceAsset(String str) {
        this.financeAsset = str;
    }

    public void setFinanceBalance(String str) {
        this.financeBalance = str;
    }

    public void setProjectId(String str) {
        this.projectId = str;
    }

    public void setTokenName(String str) {
        this.tokenName = str;
    }

    @Override
    protected boolean canEqual(Object obj) {
        return obj instanceof FinanceSelectWalletBean;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FinanceSelectWalletBean) {
            FinanceSelectWalletBean financeSelectWalletBean = (FinanceSelectWalletBean) obj;
            if (financeSelectWalletBean.canEqual(this)) {
                String financeAsset = getFinanceAsset();
                String financeAsset2 = financeSelectWalletBean.getFinanceAsset();
                if (financeAsset != null ? financeAsset.equals(financeAsset2) : financeAsset2 == null) {
                    String allAsset = getAllAsset();
                    String allAsset2 = financeSelectWalletBean.getAllAsset();
                    if (allAsset != null ? allAsset.equals(allAsset2) : allAsset2 == null) {
                        String tokenName = getTokenName();
                        String tokenName2 = financeSelectWalletBean.getTokenName();
                        if (tokenName != null ? tokenName.equals(tokenName2) : tokenName2 == null) {
                            String projectId = getProjectId();
                            String projectId2 = financeSelectWalletBean.getProjectId();
                            if (projectId != null ? projectId.equals(projectId2) : projectId2 == null) {
                                String financeBalance = getFinanceBalance();
                                String financeBalance2 = financeSelectWalletBean.getFinanceBalance();
                                return financeBalance != null ? financeBalance.equals(financeBalance2) : financeBalance2 == null;
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

    @Override
    public int hashCode() {
        String financeAsset = getFinanceAsset();
        int hashCode = financeAsset == null ? 43 : financeAsset.hashCode();
        String allAsset = getAllAsset();
        int hashCode2 = ((hashCode + 59) * 59) + (allAsset == null ? 43 : allAsset.hashCode());
        String tokenName = getTokenName();
        int hashCode3 = (hashCode2 * 59) + (tokenName == null ? 43 : tokenName.hashCode());
        String projectId = getProjectId();
        int hashCode4 = (hashCode3 * 59) + (projectId == null ? 43 : projectId.hashCode());
        String financeBalance = getFinanceBalance();
        return (hashCode4 * 59) + (financeBalance != null ? financeBalance.hashCode() : 43);
    }

    @Override
    public String toString() {
        return "FinanceSelectWalletBean(financeAsset=" + getFinanceAsset() + ", allAsset=" + getAllAsset() + ", tokenName=" + getTokenName() + ", projectId=" + getProjectId() + ", financeBalance=" + getFinanceBalance() + ")";
    }
}
