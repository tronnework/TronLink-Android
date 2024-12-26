package com.tron.wallet.business.customtokens.bean;

import com.tron.wallet.business.addassets2.bean.BaseInput;
public class AddCustomTokenInput extends BaseInput {
    private int decimal;
    private String name;
    private String symbol;
    private String tokenAddress;
    private int type;

    public int getDecimal() {
        return this.decimal;
    }

    public String getName() {
        return this.name;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public String getTokenAddress() {
        return this.tokenAddress;
    }

    public int getType() {
        return this.type;
    }

    public void setDecimal(int i) {
        this.decimal = i;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setSymbol(String str) {
        this.symbol = str;
    }

    public void setTokenAddress(String str) {
        this.tokenAddress = str;
    }

    public void setType(int i) {
        this.type = i;
    }

    @Override
    public String toString() {
        return "AddCustomTokenInput(super=" + super.toString() + ", name=" + getName() + ", symbol=" + getSymbol() + ", tokenAddress=" + getTokenAddress() + ", type=" + getType() + ", decimal=" + getDecimal() + ")";
    }

    @Override
    protected boolean canEqual(Object obj) {
        return obj instanceof AddCustomTokenInput;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AddCustomTokenInput) {
            AddCustomTokenInput addCustomTokenInput = (AddCustomTokenInput) obj;
            if (addCustomTokenInput.canEqual(this) && super.equals(obj) && getType() == addCustomTokenInput.getType() && getDecimal() == addCustomTokenInput.getDecimal()) {
                String name = getName();
                String name2 = addCustomTokenInput.getName();
                if (name != null ? name.equals(name2) : name2 == null) {
                    String symbol = getSymbol();
                    String symbol2 = addCustomTokenInput.getSymbol();
                    if (symbol != null ? symbol.equals(symbol2) : symbol2 == null) {
                        String tokenAddress = getTokenAddress();
                        String tokenAddress2 = addCustomTokenInput.getTokenAddress();
                        return tokenAddress != null ? tokenAddress.equals(tokenAddress2) : tokenAddress2 == null;
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
        int hashCode = (((super.hashCode() * 59) + getType()) * 59) + getDecimal();
        String name = getName();
        int hashCode2 = (hashCode * 59) + (name == null ? 43 : name.hashCode());
        String symbol = getSymbol();
        int hashCode3 = (hashCode2 * 59) + (symbol == null ? 43 : symbol.hashCode());
        String tokenAddress = getTokenAddress();
        return (hashCode3 * 59) + (tokenAddress != null ? tokenAddress.hashCode() : 43);
    }
}
