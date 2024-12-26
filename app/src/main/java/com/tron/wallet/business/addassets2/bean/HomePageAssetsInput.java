package com.tron.wallet.business.addassets2.bean;

import java.util.List;
public class HomePageAssetsInput extends BaseInput {
    private List<String> token10;
    private List<String> token20;
    private int type;

    public List<String> getToken10() {
        return this.token10;
    }

    public List<String> getToken20() {
        return this.token20;
    }

    public int getType() {
        return this.type;
    }

    public void setToken10(List<String> list) {
        this.token10 = list;
    }

    public void setToken20(List<String> list) {
        this.token20 = list;
    }

    public void setType(int i) {
        this.type = i;
    }

    @Override
    public String toString() {
        return "HomePageAssetsInput(type=" + getType() + ", token10=" + getToken10() + ", token20=" + getToken20() + ")";
    }

    @Override
    protected boolean canEqual(Object obj) {
        return obj instanceof HomePageAssetsInput;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof HomePageAssetsInput) {
            HomePageAssetsInput homePageAssetsInput = (HomePageAssetsInput) obj;
            if (homePageAssetsInput.canEqual(this) && super.equals(obj) && getType() == homePageAssetsInput.getType()) {
                List<String> token10 = getToken10();
                List<String> token102 = homePageAssetsInput.getToken10();
                if (token10 != null ? token10.equals(token102) : token102 == null) {
                    List<String> token20 = getToken20();
                    List<String> token202 = homePageAssetsInput.getToken20();
                    return token20 != null ? token20.equals(token202) : token202 == null;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hashCode = (super.hashCode() * 59) + getType();
        List<String> token10 = getToken10();
        int hashCode2 = (hashCode * 59) + (token10 == null ? 43 : token10.hashCode());
        List<String> token20 = getToken20();
        return (hashCode2 * 59) + (token20 != null ? token20.hashCode() : 43);
    }
}
