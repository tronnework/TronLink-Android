package com.tron.wallet.business.tabdapp.browser.bean;

import android.os.Parcel;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import j$.util.Objects;
public class BrowserHistoryBean extends BaseWebViewPageInfo implements MultiItemEntity {
    private String iconUrl;
    public Long id;
    public long timestamp;
    private String title;
    private String url;

    @Override
    public String getIconUrl() {
        return this.iconUrl;
    }

    public Long getId() {
        return this.id;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getUrl() {
        return this.url;
    }

    @Override
    public void setIconUrl(String str) {
        this.iconUrl = str;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    @Override
    public void setTitle(String str) {
        this.title = str;
    }

    @Override
    public void setUrl(String str) {
        this.url = str;
    }

    public BrowserHistoryBean(String str, String str2, String str3) {
        super(str, str2, str3);
        this.timestamp = 0L;
        this.url = str;
        this.title = str2;
        this.iconUrl = str3;
        this.timestamp = System.currentTimeMillis();
    }

    public BrowserHistoryBean(String str, String str2, int i) {
        super(str, str2, "");
        this.timestamp = 0L;
        this.url = str;
        this.title = str2;
        this.iconUrl = "";
        setType(i);
        this.timestamp = System.currentTimeMillis();
    }

    protected BrowserHistoryBean(Parcel parcel) {
        super(parcel);
        this.timestamp = 0L;
    }

    public BrowserHistoryBean(Long l, long j, String str, String str2, String str3) {
        this.id = l;
        this.timestamp = j;
        this.url = str;
        this.title = str2;
        this.iconUrl = str3;
    }

    public BrowserHistoryBean() {
        this.timestamp = 0L;
    }

    @Override
    public int getItemType() {
        return getType();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass() && super.equals(obj)) {
            return Objects.equals(this.url, ((BrowserHistoryBean) obj).url);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.url);
    }
}
