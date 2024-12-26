package com.tron.wallet.business.tabdapp.home.bean;
public class DappBannerBean extends DappBean {
    private String backgroundColor;
    private String title;

    public String getBackgroundColor() {
        return this.backgroundColor;
    }

    public String getTitle() {
        return this.title;
    }

    public void setBackgroundColor(String str) {
        this.backgroundColor = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    @Override
    public String toString() {
        return "DappBannerBean(title=" + getTitle() + ", backgroundColor=" + getBackgroundColor() + ")";
    }

    public DappBannerBean(String str, String str2) {
        this.title = str;
        this.backgroundColor = str2;
    }

    public DappBannerBean() {
    }

    @Override
    protected boolean canEqual(Object obj) {
        return obj instanceof DappBannerBean;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DappBannerBean) {
            DappBannerBean dappBannerBean = (DappBannerBean) obj;
            if (dappBannerBean.canEqual(this) && super.equals(obj)) {
                String title = getTitle();
                String title2 = dappBannerBean.getTitle();
                if (title != null ? title.equals(title2) : title2 == null) {
                    String backgroundColor = getBackgroundColor();
                    String backgroundColor2 = dappBannerBean.getBackgroundColor();
                    return backgroundColor != null ? backgroundColor.equals(backgroundColor2) : backgroundColor2 == null;
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
        String title = getTitle();
        int hashCode2 = (hashCode * 59) + (title == null ? 43 : title.hashCode());
        String backgroundColor = getBackgroundColor();
        return (hashCode2 * 59) + (backgroundColor != null ? backgroundColor.hashCode() : 43);
    }

    @Override
    public String getName() {
        return getTitle();
    }
}
