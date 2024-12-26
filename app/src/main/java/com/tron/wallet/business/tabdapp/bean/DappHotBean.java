package com.tron.wallet.business.tabdapp.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
public class DappHotBean implements Parcelable {
    public static final Parcelable.Creator<DappHotBean> CREATOR = new Parcelable.Creator<DappHotBean>() {
        @Override
        public DappHotBean createFromParcel(Parcel parcel) {
            return new DappHotBean(parcel);
        }

        @Override
        public DappHotBean[] newArray(int i) {
            return new DappHotBean[i];
        }
    };
    private int code;
    private List<String> data;
    private String message;

    @Override
    public int describeContents() {
        return 0;
    }

    public int getCode() {
        return this.code;
    }

    public List<String> getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setData(List<String> list) {
        this.data = list;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.code);
        parcel.writeString(this.message);
        parcel.writeList(this.data);
    }

    public DappHotBean() {
    }

    protected DappHotBean(Parcel parcel) {
        this.code = parcel.readInt();
        this.message = parcel.readString();
        ArrayList arrayList = new ArrayList();
        this.data = arrayList;
        parcel.readList(arrayList, String.class.getClassLoader());
    }

    public static boolean valid(DappHotBean dappHotBean) {
        return (dappHotBean == null || dappHotBean.getData() == null || dappHotBean.getData().size() <= 0) ? false : true;
    }
}
