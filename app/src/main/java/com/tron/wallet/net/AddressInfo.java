package com.tron.wallet.net;
public class AddressInfo {
    private String address;
    private int addressType;

    public String getAddress() {
        return this.address;
    }

    public int getAddressType() {
        return this.addressType;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setAddressType(int i) {
        this.addressType = i;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof AddressInfo;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AddressInfo) {
            AddressInfo addressInfo = (AddressInfo) obj;
            if (addressInfo.canEqual(this) && getAddressType() == addressInfo.getAddressType()) {
                String address = getAddress();
                String address2 = addressInfo.getAddress();
                return address != null ? address.equals(address2) : address2 == null;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        String address = getAddress();
        return ((getAddressType() + 59) * 59) + (address == null ? 43 : address.hashCode());
    }

    public String toString() {
        return "AddressInfo(address=" + getAddress() + ", addressType=" + getAddressType() + ")";
    }
}
