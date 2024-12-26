package com.tron.wallet.business.tabdapp.browser.bean;
public class DAppVisitHistoryBean {
    private String icon;
    private Long id;
    private long timestamp;
    private String title;
    private String url;

    public String getIcon() {
        return this.icon;
    }

    public Long getId() {
        return this.id;
    }

    public long getTimestamp() {
        return this.timestamp;
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

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public DAppVisitHistoryBean(Long l, String str, String str2, String str3, long j) {
        this.id = l;
        this.url = str;
        this.title = str2;
        this.icon = str3;
        this.timestamp = j;
    }

    public DAppVisitHistoryBean() {
    }
}
