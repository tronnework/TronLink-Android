package com.tron.wallet.business.pull.login;

import com.tron.wallet.business.pull.PullResult;
public class LoginResult extends PullResult {
    private String address;

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    @Override
    protected boolean canEqual(Object obj) {
        return obj instanceof LoginResult;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof LoginResult) {
            LoginResult loginResult = (LoginResult) obj;
            if (loginResult.canEqual(this)) {
                String address = getAddress();
                String address2 = loginResult.getAddress();
                return address != null ? address.equals(address2) : address2 == null;
            }
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        String address = getAddress();
        return 59 + (address == null ? 43 : address.hashCode());
    }

    @Override
    public String toString() {
        return "LoginResult(address=" + getAddress() + ")";
    }
}
