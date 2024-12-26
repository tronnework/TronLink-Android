package com.tron.wallet.business.tabdapp.browser.bean;
public class BrowserTabHistoryBean {
    private String dappAuthUrl;
    private String icon;
    private Long id;
    private boolean isAnonymous;
    private boolean isCurrent;
    private boolean isMain;
    private int tabIndex;
    private String title;
    private String url;

    public String getDappAuthUrl() {
        return this.dappAuthUrl;
    }

    public String getIcon() {
        return this.icon;
    }

    public Long getId() {
        return this.id;
    }

    public boolean getIsAnonymous() {
        return this.isAnonymous;
    }

    public boolean getIsCurrent() {
        return this.isCurrent;
    }

    public boolean getIsMain() {
        return this.isMain;
    }

    public int getTabIndex() {
        return this.tabIndex;
    }

    public String getTitle() {
        return this.title;
    }

    public String getUrl() {
        return this.url;
    }

    public boolean isAnonymous() {
        return this.isAnonymous;
    }

    public boolean isMain() {
        return this.isMain;
    }

    public void setAnonymous(boolean z) {
        this.isAnonymous = z;
    }

    public void setDappAuthUrl(String str) {
        this.dappAuthUrl = str;
    }

    public void setIcon(String str) {
        this.icon = str;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public void setIsAnonymous(boolean z) {
        this.isAnonymous = z;
    }

    public void setIsCurrent(boolean z) {
        this.isCurrent = z;
    }

    public void setIsMain(boolean z) {
        this.isMain = z;
    }

    public void setMain(boolean z) {
        this.isMain = z;
    }

    public void setTabIndex(int i) {
        this.tabIndex = i;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public BrowserTabHistoryBean() {
    }

    public BrowserTabHistoryBean(Long l, String str, String str2, String str3, String str4, int i, boolean z, boolean z2, boolean z3) {
        this.id = l;
        this.url = str;
        this.dappAuthUrl = str2;
        this.title = str3;
        this.icon = str4;
        this.tabIndex = i;
        this.isCurrent = z;
        this.isMain = z2;
        this.isAnonymous = z3;
    }

    public String toString() {
        return "BrowserTabHistoryBean{id=" + this.id + ", url='" + this.url + "', dappAuthUrl='" + this.dappAuthUrl + "', title='" + this.title + "', icon='" + this.icon + "', tabIndex=" + this.tabIndex + ", isCurrent=" + this.isCurrent + ", isAnonymous=" + this.isAnonymous + '}';
    }
}
