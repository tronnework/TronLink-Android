package com.tron.wallet.business.addassets2.bean;
public class BaseInput {
    private String address;

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof BaseInput;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof BaseInput) {
            BaseInput baseInput = (BaseInput) obj;
            if (baseInput.canEqual(this)) {
                String address = getAddress();
                String address2 = baseInput.getAddress();
                return address != null ? address.equals(address2) : address2 == null;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        String address = getAddress();
        return 59 + (address == null ? 43 : address.hashCode());
    }

    public String toString() {
        return "BaseInput(address=" + getAddress() + ")";
    }
}
