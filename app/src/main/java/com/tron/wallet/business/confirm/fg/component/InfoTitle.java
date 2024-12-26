package com.tron.wallet.business.confirm.fg.component;

import android.os.Parcel;
import android.os.Parcelable;
public class InfoTitle implements Parcelable {
    public static final Parcelable.Creator<InfoTitle> CREATOR = new Parcelable.Creator<InfoTitle>() {
        @Override
        public InfoTitle createFromParcel(Parcel parcel) {
            return new InfoTitle(parcel);
        }

        @Override
        public InfoTitle[] newArray(int i) {
            return new InfoTitle[i];
        }
    };
    private String subTitle;
    private String subTitleDetail;
    private int title;

    @Override
    public int describeContents() {
        return 0;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public String getSubTitleDetail() {
        return this.subTitleDetail;
    }

    public int getTitle() {
        return this.title;
    }

    public void setSubTitle(String str) {
        this.subTitle = str;
    }

    public void setSubTitleDetail(String str) {
        this.subTitleDetail = str;
    }

    public void setTitle(int i) {
        this.title = i;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof InfoTitle;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof InfoTitle) {
            InfoTitle infoTitle = (InfoTitle) obj;
            if (infoTitle.canEqual(this) && getTitle() == infoTitle.getTitle()) {
                String subTitle = getSubTitle();
                String subTitle2 = infoTitle.getSubTitle();
                if (subTitle != null ? subTitle.equals(subTitle2) : subTitle2 == null) {
                    String subTitleDetail = getSubTitleDetail();
                    String subTitleDetail2 = infoTitle.getSubTitleDetail();
                    return subTitleDetail != null ? subTitleDetail.equals(subTitleDetail2) : subTitleDetail2 == null;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        String subTitle = getSubTitle();
        int title = ((getTitle() + 59) * 59) + (subTitle == null ? 43 : subTitle.hashCode());
        String subTitleDetail = getSubTitleDetail();
        return (title * 59) + (subTitleDetail != null ? subTitleDetail.hashCode() : 43);
    }

    public String toString() {
        return "InfoTitle(title=" + getTitle() + ", subTitle=" + getSubTitle() + ", subTitleDetail=" + getSubTitleDetail() + ")";
    }

    public InfoTitle(int i, String str) {
        this.title = i;
        this.subTitle = str;
    }

    public InfoTitle(Parcel parcel) {
        this.title = parcel.readInt();
        this.subTitle = parcel.readString();
        this.subTitleDetail = parcel.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.title);
        parcel.writeString(this.subTitle);
        parcel.writeString(this.subTitleDetail);
    }
}
