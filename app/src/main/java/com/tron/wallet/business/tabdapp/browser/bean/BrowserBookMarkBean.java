package com.tron.wallet.business.tabdapp.browser.bean;

import android.os.Parcel;
import android.os.Parcelable;
import j$.util.Objects;
public class BrowserBookMarkBean implements Parcelable {
    public static final Parcelable.Creator<BrowserBookMarkBean> CREATOR = new Parcelable.Creator<BrowserBookMarkBean>() {
        @Override
        public BrowserBookMarkBean createFromParcel(Parcel parcel) {
            return new BrowserBookMarkBean(parcel);
        }

        @Override
        public BrowserBookMarkBean[] newArray(int i) {
            return new BrowserBookMarkBean[i];
        }
    };
    private boolean anonymous;
    private String iconUrl;
    public Long id;
    private String title;
    private String url;

    @Override
    public int describeContents() {
        return 0;
    }

    public boolean getAnonymous() {
        return this.anonymous;
    }

    public String getIconUrl() {
        return this.iconUrl;
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public int getType() {
        return 1;
    }

    public String getUrl() {
        return this.url;
    }

    public boolean isAnonymous() {
        return this.anonymous;
    }

    public void setAnonymous(boolean z) {
        this.anonymous = z;
    }

    public void setIconUrl(String str) {
        this.iconUrl = str;
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

    public BrowserBookMarkBean(String str, String str2, String str3, boolean z) {
        this.url = str;
        this.title = str2;
        this.iconUrl = str3;
        this.anonymous = z;
    }

    protected BrowserBookMarkBean(Parcel parcel) {
        this.url = parcel.readString();
        this.title = parcel.readString();
        this.iconUrl = parcel.readString();
    }

    public BrowserBookMarkBean(Long l, String str, String str2, String str3, boolean z) {
        this.id = l;
        this.url = str;
        this.title = str2;
        this.iconUrl = str3;
        this.anonymous = z;
    }

    public BrowserBookMarkBean() {
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.url);
        parcel.writeString(this.title);
        parcel.writeString(this.iconUrl);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass() && super.equals(obj)) {
            BrowserBookMarkBean browserBookMarkBean = (BrowserBookMarkBean) obj;
            return Objects.equals(this.id, browserBookMarkBean.id) || Objects.equals(this.url, browserBookMarkBean.url);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.id);
    }
}
