package com.tron.wallet.business.finance.bean;

import java.util.List;
public class FinanceAccountBalanceInput {
    private String projectId;
    private String tokenId;
    private List<String> walletAddress;

    public String getProjectId() {
        return this.projectId;
    }

    public String getTokenId() {
        return this.tokenId;
    }

    public List<String> getWalletAddress() {
        return this.walletAddress;
    }

    public void setProjectId(String str) {
        this.projectId = str;
    }

    public void setTokenId(String str) {
        this.tokenId = str;
    }

    public void setWalletAddress(List<String> list) {
        this.walletAddress = list;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof FinanceAccountBalanceInput;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FinanceAccountBalanceInput) {
            FinanceAccountBalanceInput financeAccountBalanceInput = (FinanceAccountBalanceInput) obj;
            if (financeAccountBalanceInput.canEqual(this)) {
                List<String> walletAddress = getWalletAddress();
                List<String> walletAddress2 = financeAccountBalanceInput.getWalletAddress();
                if (walletAddress != null ? walletAddress.equals(walletAddress2) : walletAddress2 == null) {
                    String tokenId = getTokenId();
                    String tokenId2 = financeAccountBalanceInput.getTokenId();
                    if (tokenId != null ? tokenId.equals(tokenId2) : tokenId2 == null) {
                        String projectId = getProjectId();
                        String projectId2 = financeAccountBalanceInput.getProjectId();
                        return projectId != null ? projectId.equals(projectId2) : projectId2 == null;
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
        List<String> walletAddress = getWalletAddress();
        int hashCode = walletAddress == null ? 43 : walletAddress.hashCode();
        String tokenId = getTokenId();
        int hashCode2 = ((hashCode + 59) * 59) + (tokenId == null ? 43 : tokenId.hashCode());
        String projectId = getProjectId();
        return (hashCode2 * 59) + (projectId != null ? projectId.hashCode() : 43);
    }

    public String toString() {
        return "FinanceAccountBalanceInput(walletAddress=" + getWalletAddress() + ", tokenId=" + getTokenId() + ", projectId=" + getProjectId() + ")";
    }
}
