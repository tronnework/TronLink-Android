package com.tron.wallet.business.tabdapp.browser.bean;
public class MostVisitDAppBean {
    private boolean anonymous;
    private int flag;
    private String icon;
    private Long id;
    private int score;
    private String title;
    private long updateTimestamp;
    private String url;
    private long visitTimestamp;

    public boolean getAnonymous() {
        return this.anonymous;
    }

    public int getFlag() {
        return this.flag;
    }

    public String getIcon() {
        return this.icon;
    }

    public Long getId() {
        return this.id;
    }

    public int getScore() {
        return this.score;
    }

    public String getTitle() {
        return this.title;
    }

    public long getUpdateTimestamp() {
        return this.updateTimestamp;
    }

    public String getUrl() {
        return this.url;
    }

    public long getVisitTimestamp() {
        return this.visitTimestamp;
    }

    public boolean isAnonymous() {
        return this.anonymous;
    }

    public void setAnonymous(boolean z) {
        this.anonymous = z;
    }

    public void setFlag(int i) {
        this.flag = i;
    }

    public void setIcon(String str) {
        this.icon = str;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public void setScore(int i) {
        this.score = i;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setUpdateTimestamp(long j) {
        this.updateTimestamp = j;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public void setVisitTimestamp(long j) {
        this.visitTimestamp = j;
    }

    public MostVisitDAppBean(Long l, String str, String str2, String str3, boolean z, int i, int i2, long j, long j2) {
        this.id = l;
        this.url = str;
        this.title = str2;
        this.icon = str3;
        this.anonymous = z;
        this.score = i;
        this.flag = i2;
        this.visitTimestamp = j;
        this.updateTimestamp = j2;
    }

    public MostVisitDAppBean() {
    }
}
