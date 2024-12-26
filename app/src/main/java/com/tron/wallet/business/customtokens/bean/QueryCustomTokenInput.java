package com.tron.wallet.business.customtokens.bean;

import com.tron.wallet.business.addassets2.bean.BaseInput;
public class QueryCustomTokenInput extends BaseInput {
    private String tokenAddress;

    public String getTokenAddress() {
        return this.tokenAddress;
    }

    public void setTokenAddress(String str) {
        this.tokenAddress = str;
    }

    @Override
    public String toString() {
        return "QueryCustomTokenInput(super=" + super.toString() + ", tokenAddress=" + getTokenAddress() + ")";
    }

    @Override
    protected boolean canEqual(Object obj) {
        return obj instanceof QueryCustomTokenInput;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof QueryCustomTokenInput) {
            QueryCustomTokenInput queryCustomTokenInput = (QueryCustomTokenInput) obj;
            if (queryCustomTokenInput.canEqual(this) && super.equals(obj)) {
                String tokenAddress = getTokenAddress();
                String tokenAddress2 = queryCustomTokenInput.getTokenAddress();
                return tokenAddress != null ? tokenAddress.equals(tokenAddress2) : tokenAddress2 == null;
            }
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hashCode = super.hashCode();
        String tokenAddress = getTokenAddress();
        return (hashCode * 59) + (tokenAddress == null ? 43 : tokenAddress.hashCode());
    }
}
