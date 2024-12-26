package com.tron.wallet.business.finance.bean;
public class SwitchWalletResult {
    String address;
    boolean all;
    String name;

    public String getAddress() {
        return this.address;
    }

    public String getName() {
        return this.name;
    }

    public boolean isAll() {
        return this.all;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setAll(boolean z) {
        this.all = z;
    }

    public void setName(String str) {
        this.name = str;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof SwitchWalletResult;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SwitchWalletResult) {
            SwitchWalletResult switchWalletResult = (SwitchWalletResult) obj;
            if (switchWalletResult.canEqual(this) && isAll() == switchWalletResult.isAll()) {
                String name = getName();
                String name2 = switchWalletResult.getName();
                if (name != null ? name.equals(name2) : name2 == null) {
                    String address = getAddress();
                    String address2 = switchWalletResult.getAddress();
                    return address != null ? address.equals(address2) : address2 == null;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int i = isAll() ? 79 : 97;
        String name = getName();
        int hashCode = ((i + 59) * 59) + (name == null ? 43 : name.hashCode());
        String address = getAddress();
        return (hashCode * 59) + (address != null ? address.hashCode() : 43);
    }

    public String toString() {
        return "SwitchWalletResult(name=" + getName() + ", address=" + getAddress() + ", all=" + isAll() + ")";
    }

    public SwitchWalletResult(String str, String str2) {
        this.name = str;
        this.address = str2;
    }

    public SwitchWalletResult() {
        this.name = "";
        this.address = "";
        this.all = false;
    }
}
