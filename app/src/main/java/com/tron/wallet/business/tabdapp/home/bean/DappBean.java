package com.tron.wallet.business.tabdapp.home.bean;
public class DappBean {
    private boolean anonymous;
    private int classifyId;
    private String clkUrl;
    private String feedbackUrl;
    private String homeUrl;
    private String imageUrl;
    private String intro;
    private String name;

    public int getClassifyId() {
        return this.classifyId;
    }

    public String getClkUrl() {
        return this.clkUrl;
    }

    public String getFeedbackUrl() {
        return this.feedbackUrl;
    }

    public String getHomeUrl() {
        return this.homeUrl;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public String getIntro() {
        return this.intro;
    }

    public String getName() {
        return this.name;
    }

    public boolean isAnonymous() {
        return this.anonymous;
    }

    public void setAnonymous(boolean z) {
        this.anonymous = z;
    }

    public void setClassifyId(int i) {
        this.classifyId = i;
    }

    public void setClkUrl(String str) {
        this.clkUrl = str;
    }

    public void setFeedbackUrl(String str) {
        this.feedbackUrl = str;
    }

    public void setHomeUrl(String str) {
        this.homeUrl = str;
    }

    public void setImageUrl(String str) {
        this.imageUrl = str;
    }

    public void setIntro(String str) {
        this.intro = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof DappBean;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DappBean) {
            DappBean dappBean = (DappBean) obj;
            if (dappBean.canEqual(this) && getClassifyId() == dappBean.getClassifyId() && isAnonymous() == dappBean.isAnonymous()) {
                String name = getName();
                String name2 = dappBean.getName();
                if (name != null ? name.equals(name2) : name2 == null) {
                    String imageUrl = getImageUrl();
                    String imageUrl2 = dappBean.getImageUrl();
                    if (imageUrl != null ? imageUrl.equals(imageUrl2) : imageUrl2 == null) {
                        String homeUrl = getHomeUrl();
                        String homeUrl2 = dappBean.getHomeUrl();
                        if (homeUrl != null ? homeUrl.equals(homeUrl2) : homeUrl2 == null) {
                            String intro = getIntro();
                            String intro2 = dappBean.getIntro();
                            if (intro != null ? intro.equals(intro2) : intro2 == null) {
                                String clkUrl = getClkUrl();
                                String clkUrl2 = dappBean.getClkUrl();
                                if (clkUrl != null ? clkUrl.equals(clkUrl2) : clkUrl2 == null) {
                                    String feedbackUrl = getFeedbackUrl();
                                    String feedbackUrl2 = dappBean.getFeedbackUrl();
                                    return feedbackUrl != null ? feedbackUrl.equals(feedbackUrl2) : feedbackUrl2 == null;
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

    public int hashCode() {
        int classifyId = ((getClassifyId() + 59) * 59) + (isAnonymous() ? 79 : 97);
        String name = getName();
        int hashCode = (classifyId * 59) + (name == null ? 43 : name.hashCode());
        String imageUrl = getImageUrl();
        int hashCode2 = (hashCode * 59) + (imageUrl == null ? 43 : imageUrl.hashCode());
        String homeUrl = getHomeUrl();
        int hashCode3 = (hashCode2 * 59) + (homeUrl == null ? 43 : homeUrl.hashCode());
        String intro = getIntro();
        int hashCode4 = (hashCode3 * 59) + (intro == null ? 43 : intro.hashCode());
        String clkUrl = getClkUrl();
        int hashCode5 = (hashCode4 * 59) + (clkUrl == null ? 43 : clkUrl.hashCode());
        String feedbackUrl = getFeedbackUrl();
        return (hashCode5 * 59) + (feedbackUrl != null ? feedbackUrl.hashCode() : 43);
    }

    public String toString() {
        return "DappBean(classifyId=" + getClassifyId() + ", name=" + getName() + ", imageUrl=" + getImageUrl() + ", homeUrl=" + getHomeUrl() + ", intro=" + getIntro() + ", anonymous=" + isAnonymous() + ", clkUrl=" + getClkUrl() + ", feedbackUrl=" + getFeedbackUrl() + ")";
    }

    public DappBean() {
    }

    public DappBean(int i, String str, String str2, String str3, String str4, boolean z, String str5, String str6) {
        this.classifyId = i;
        this.name = str;
        this.imageUrl = str2;
        this.homeUrl = str3;
        this.intro = str4;
        this.anonymous = z;
        this.clkUrl = str5;
        this.feedbackUrl = str6;
    }
}
