package com.tron.wallet.business.tabdapp.browser.bean;
public class BrowserTabBean {
    private String icon;
    private Long id;
    private String title;
    private String url;

    public String getIcon() {
        return this.icon;
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getUrl() {
        return this.url;
    }

    public void setIcon(String str) {
        this.icon = str;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public BrowserTabBean(Long l, String str, String str2, String str3) {
        this.id = l;
        this.url = str;
        this.title = str2;
        this.icon = str3;
    }

    public BrowserTabBean() {
    }
}
