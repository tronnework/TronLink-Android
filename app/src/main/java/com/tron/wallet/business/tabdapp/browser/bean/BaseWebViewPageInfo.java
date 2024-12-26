package com.tron.wallet.business.tabdapp.browser.bean;

import android.os.Parcel;
import android.os.Parcelable;
import j$.util.Objects;
public class BaseWebViewPageInfo implements Parcelable {
    public static final Parcelable.Creator<BaseWebViewPageInfo> CREATOR = new Parcelable.Creator<BaseWebViewPageInfo>() {
        @Override
        public BaseWebViewPageInfo createFromParcel(Parcel parcel) {
            return new BaseWebViewPageInfo(parcel);
        }

        @Override
        public BaseWebViewPageInfo[] newArray(int i) {
            return new BaseWebViewPageInfo[i];
        }
    };
    private String actualUrl;
    private boolean firstPageLoaded;
    private String iconUrl;
    private String title;
    private transient int type = 1;
    private String url;

    @Override
    public int describeContents() {
        return 0;
    }

    public String getActualUrl() {
        return this.actualUrl;
    }

    public String getIconUrl() {
        return this.iconUrl;
    }

    public String getTitle() {
        return this.title;
    }

    public int getType() {
        return this.type;
    }

    public String getUrl() {
        return this.url;
    }

    public boolean isFirstPageLoaded() {
        return this.firstPageLoaded;
    }

    public void setActualUrl(String str) {
        this.actualUrl = str;
    }

    public void setFirstPageLoaded(boolean z) {
        this.firstPageLoaded = z;
    }

    public void setIconUrl(String str) {
        this.iconUrl = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setType(int i) {
        this.type = i;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public BaseWebViewPageInfo() {
    }

    public BaseWebViewPageInfo(String str, String str2, String str3) {
        this.url = str;
        this.title = str2;
        this.iconUrl = str3;
    }

    public BaseWebViewPageInfo(String str, String str2, String str3, String str4, boolean z) {
        this.url = str;
        this.title = str2;
        this.iconUrl = str3;
        this.actualUrl = str4;
        this.firstPageLoaded = z;
    }

    public BaseWebViewPageInfo(Parcel parcel) {
        this.url = parcel.readString();
        this.title = parcel.readString();
        this.iconUrl = parcel.readString();
        this.actualUrl = parcel.readString();
        this.firstPageLoaded = parcel.readInt() == 1;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.url);
        parcel.writeString(this.title);
        parcel.writeString(this.iconUrl);
        parcel.writeString(this.actualUrl);
        parcel.writeInt(this.firstPageLoaded ? 1 : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BaseWebViewPageInfo baseWebViewPageInfo = (BaseWebViewPageInfo) obj;
        return Objects.equals(this.url, baseWebViewPageInfo.url) && Objects.equals(this.title, baseWebViewPageInfo.title) && Objects.equals(this.iconUrl, baseWebViewPageInfo.iconUrl);
    }

    public int hashCode() {
        return Objects.hash(this.url, this.title, this.iconUrl);
    }

    public String toString() {
        return "BaseWebViewPageInfo{url='" + this.url + "', title='" + this.title + "', iconUrl='" + this.iconUrl + "'}";
    }
}
