package com.tron.wallet.business.addassets2.bean;

import java.util.List;
public class FollowAssetsInput extends BaseInput {
    private List<String> token10;
    private List<String> token10Cancel;
    private List<String> token20;
    private List<String> token20Cancel;
    private List<String> token721;
    private List<String> token721Cancel;

    public List<String> getToken10() {
        return this.token10;
    }

    public List<String> getToken10Cancel() {
        return this.token10Cancel;
    }

    public List<String> getToken20() {
        return this.token20;
    }

    public List<String> getToken20Cancel() {
        return this.token20Cancel;
    }

    public List<String> getToken721() {
        return this.token721;
    }

    public List<String> getToken721Cancel() {
        return this.token721Cancel;
    }

    public void setToken10(List<String> list) {
        this.token10 = list;
    }

    public void setToken10Cancel(List<String> list) {
        this.token10Cancel = list;
    }

    public void setToken20(List<String> list) {
        this.token20 = list;
    }

    public void setToken20Cancel(List<String> list) {
        this.token20Cancel = list;
    }

    public void setToken721(List<String> list) {
        this.token721 = list;
    }

    public void setToken721Cancel(List<String> list) {
        this.token721Cancel = list;
    }

    @Override
    public String toString() {
        return "FollowAssetsInput(token10=" + getToken10() + ", token20=" + getToken20() + ", token721=" + getToken721() + ", token10Cancel=" + getToken10Cancel() + ", token20Cancel=" + getToken20Cancel() + ", token721Cancel=" + getToken721Cancel() + ")";
    }

    @Override
    protected boolean canEqual(Object obj) {
        return obj instanceof FollowAssetsInput;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FollowAssetsInput) {
            FollowAssetsInput followAssetsInput = (FollowAssetsInput) obj;
            if (followAssetsInput.canEqual(this) && super.equals(obj)) {
                List<String> token10 = getToken10();
                List<String> token102 = followAssetsInput.getToken10();
                if (token10 != null ? token10.equals(token102) : token102 == null) {
                    List<String> token20 = getToken20();
                    List<String> token202 = followAssetsInput.getToken20();
                    if (token20 != null ? token20.equals(token202) : token202 == null) {
                        List<String> token721 = getToken721();
                        List<String> token7212 = followAssetsInput.getToken721();
                        if (token721 != null ? token721.equals(token7212) : token7212 == null) {
                            List<String> token10Cancel = getToken10Cancel();
                            List<String> token10Cancel2 = followAssetsInput.getToken10Cancel();
                            if (token10Cancel != null ? token10Cancel.equals(token10Cancel2) : token10Cancel2 == null) {
                                List<String> token20Cancel = getToken20Cancel();
                                List<String> token20Cancel2 = followAssetsInput.getToken20Cancel();
                                if (token20Cancel != null ? token20Cancel.equals(token20Cancel2) : token20Cancel2 == null) {
                                    List<String> token721Cancel = getToken721Cancel();
                                    List<String> token721Cancel2 = followAssetsInput.getToken721Cancel();
                                    return token721Cancel != null ? token721Cancel.equals(token721Cancel2) : token721Cancel2 == null;
                                }
                                return false;
                            }
                            return false;
                        }
                        return false;
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
        int hashCode = super.hashCode();
        List<String> token10 = getToken10();
        int hashCode2 = (hashCode * 59) + (token10 == null ? 43 : token10.hashCode());
        List<String> token20 = getToken20();
        int hashCode3 = (hashCode2 * 59) + (token20 == null ? 43 : token20.hashCode());
        List<String> token721 = getToken721();
        int hashCode4 = (hashCode3 * 59) + (token721 == null ? 43 : token721.hashCode());
        List<String> token10Cancel = getToken10Cancel();
        int hashCode5 = (hashCode4 * 59) + (token10Cancel == null ? 43 : token10Cancel.hashCode());
        List<String> token20Cancel = getToken20Cancel();
        int hashCode6 = (hashCode5 * 59) + (token20Cancel == null ? 43 : token20Cancel.hashCode());
        List<String> token721Cancel = getToken721Cancel();
        return (hashCode6 * 59) + (token721Cancel != null ? token721Cancel.hashCode() : 43);
    }
}
