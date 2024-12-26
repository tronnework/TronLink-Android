package com.tron.wallet.common.bean.token;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
public class TransactionBean implements Parcelable {
    public static final Parcelable.Creator<TransactionBean> CREATOR = new Parcelable.Creator<TransactionBean>() {
        @Override
        public TransactionBean createFromParcel(Parcel parcel) {
            return new TransactionBean(parcel);
        }

        @Override
        public TransactionBean[] newArray(int i) {
            return new TransactionBean[i];
        }
    };
    private List<byte[]> bytes;

    @Override
    public int describeContents() {
        return 0;
    }

    public List<byte[]> getBytes() {
        return this.bytes;
    }

    public void setBytes(List<byte[]> list) {
        this.bytes = list;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.bytes);
    }

    public TransactionBean() {
    }

    protected TransactionBean(Parcel parcel) {
        ArrayList arrayList = new ArrayList();
        this.bytes = arrayList;
        parcel.readList(arrayList, byte[].class.getClassLoader());
    }
}
