package com.tron.wallet.business.pull.login;

import com.tron.wallet.business.pull.PullParam;
public class LoginParam extends PullParam {
    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        return "LoginParam()";
    }

    @Override
    protected boolean canEqual(Object obj) {
        return obj instanceof LoginParam;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof LoginParam) && ((LoginParam) obj).canEqual(this);
    }
}
