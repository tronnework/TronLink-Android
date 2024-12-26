package com.tron.wallet.business.tabdapp.browser.bean;

import com.google.gson.annotations.SerializedName;
public class ScamUrlBean {
    @SerializedName("cheat_rul")
    private boolean isScam;

    public boolean isScam() {
        return this.isScam;
    }

    public void setScam(boolean z) {
        this.isScam = z;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof ScamUrlBean;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ScamUrlBean) {
            ScamUrlBean scamUrlBean = (ScamUrlBean) obj;
            return scamUrlBean.canEqual(this) && isScam() == scamUrlBean.isScam();
        }
        return false;
    }

    public int hashCode() {
        return 59 + (isScam() ? 79 : 97);
    }

    public String toString() {
        return "ScamUrlBean(isScam=" + isScam() + ")";
    }
}
