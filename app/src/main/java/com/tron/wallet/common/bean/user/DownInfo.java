package com.tron.wallet.common.bean.user;
public class DownInfo {
    private long length;
    private long now;
    private long start;
    private String url;

    public long getLength() {
        return this.length;
    }

    public long getNow() {
        return this.now;
    }

    public long getStart() {
        return this.start;
    }

    public String getUrl() {
        return this.url;
    }

    public void setLength(long j) {
        this.length = j;
    }

    public void setNow(long j) {
        this.now = j;
    }

    public void setStart(long j) {
        this.start = j;
    }

    public void setUrl(String str) {
        this.url = str;
    }
}
