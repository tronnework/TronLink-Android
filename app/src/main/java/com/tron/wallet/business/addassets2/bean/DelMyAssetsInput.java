package com.tron.wallet.business.addassets2.bean;

import java.util.List;
public class DelMyAssetsInput extends BaseInput {
    private List<String> token721Del;
    private List<String> tokenDel;

    public List<String> getToken721Del() {
        return this.token721Del;
    }

    public List<String> getTokenDel() {
        return this.tokenDel;
    }

    public void setToken721Del(List<String> list) {
        this.token721Del = list;
    }

    public void setTokenDel(List<String> list) {
        this.tokenDel = list;
    }

    @Override
    public String toString() {
        return "DelMyAssetsInput(super=" + super.toString() + ", tokenDel=" + getTokenDel() + ", token721Del=" + getToken721Del() + ")";
    }

    @Override
    protected boolean canEqual(Object obj) {
        return obj instanceof DelMyAssetsInput;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DelMyAssetsInput) {
            DelMyAssetsInput delMyAssetsInput = (DelMyAssetsInput) obj;
            if (delMyAssetsInput.canEqual(this) && super.equals(obj)) {
                List<String> tokenDel = getTokenDel();
                List<String> tokenDel2 = delMyAssetsInput.getTokenDel();
                if (tokenDel != null ? tokenDel.equals(tokenDel2) : tokenDel2 == null) {
                    List<String> token721Del = getToken721Del();
                    List<String> token721Del2 = delMyAssetsInput.getToken721Del();
                    return token721Del != null ? token721Del.equals(token721Del2) : token721Del2 == null;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hashCode = super.hashCode();
        List<String> tokenDel = getTokenDel();
        int hashCode2 = (hashCode * 59) + (tokenDel == null ? 43 : tokenDel.hashCode());
        List<String> token721Del = getToken721Del();
        return (hashCode2 * 59) + (token721Del != null ? token721Del.hashCode() : 43);
    }
}
