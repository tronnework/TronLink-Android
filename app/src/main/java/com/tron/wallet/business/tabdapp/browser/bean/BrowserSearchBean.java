package com.tron.wallet.business.tabdapp.browser.bean;
public class BrowserSearchBean {
    public Long id;
    private String key;
    public long timestamp;

    public Long getId() {
        return this.id;
    }

    public String getKey() {
        return this.key;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public BrowserSearchBean(String str) {
        this.timestamp = 0L;
        this.key = str;
    }

    public BrowserSearchBean(Long l, long j, String str) {
        this.id = l;
        this.timestamp = j;
        this.key = str;
    }

    public BrowserSearchBean() {
        this.timestamp = 0L;
    }
}
