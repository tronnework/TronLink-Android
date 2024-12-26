package com.tron.wallet.business.tabdapp.browser.bean;
public class DAppSecBean {
    private String host;
    private DAppSecType secType;

    public String getHost() {
        return this.host;
    }

    public DAppSecType getSecType() {
        return this.secType;
    }

    public void setHost(String str) {
        this.host = str;
    }

    public void setSecType(DAppSecType dAppSecType) {
        this.secType = dAppSecType;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof DAppSecBean;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DAppSecBean) {
            DAppSecBean dAppSecBean = (DAppSecBean) obj;
            if (dAppSecBean.canEqual(this)) {
                String host = getHost();
                String host2 = dAppSecBean.getHost();
                if (host != null ? host.equals(host2) : host2 == null) {
                    DAppSecType secType = getSecType();
                    DAppSecType secType2 = dAppSecBean.getSecType();
                    return secType != null ? secType.equals(secType2) : secType2 == null;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        String host = getHost();
        int hashCode = host == null ? 43 : host.hashCode();
        DAppSecType secType = getSecType();
        return ((hashCode + 59) * 59) + (secType != null ? secType.hashCode() : 43);
    }

    public String toString() {
        return "DAppSecBean(host=" + getHost() + ", secType=" + getSecType() + ")";
    }
}
