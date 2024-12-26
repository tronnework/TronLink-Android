package com.tron.wallet.business.tabdapp.browser.search;
public class SearchDappHistoryBean {
    String content;

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof SearchDappHistoryBean;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SearchDappHistoryBean) {
            SearchDappHistoryBean searchDappHistoryBean = (SearchDappHistoryBean) obj;
            if (searchDappHistoryBean.canEqual(this)) {
                String content = getContent();
                String content2 = searchDappHistoryBean.getContent();
                return content != null ? content.equals(content2) : content2 == null;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        String content = getContent();
        return 59 + (content == null ? 43 : content.hashCode());
    }

    public String toString() {
        return "SearchDappHistoryBean(content=" + getContent() + ")";
    }

    public SearchDappHistoryBean(String str) {
        this.content = str;
    }
}
