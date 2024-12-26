package com.tron.wallet.business.addassets2.bean.nft;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
public class NftAssetOutput implements Parcelable {
    public static final Parcelable.Creator<NftAssetOutput> CREATOR = new Parcelable.Creator<NftAssetOutput>() {
        @Override
        public NftAssetOutput createFromParcel(Parcel parcel) {
            return new NftAssetOutput(parcel);
        }

        @Override
        public NftAssetOutput[] newArray(int i) {
            return new NftAssetOutput[i];
        }
    };
    private int code;
    private List<NftTokenBean> data;
    private String message;

    @Override
    public int describeContents() {
        return 0;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setData(List<NftTokenBean> list) {
        this.data = list;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String toString() {
        return "NftAssetOutput{code=" + this.code + ", message='" + this.message + "', data=" + this.data + '}';
    }

    @Nonnull
    public List<NftTokenBean> getData() {
        if (this.data == null) {
            this.data = new ArrayList();
        }
        return this.data;
    }

    public NftAssetOutput() {
    }

    protected NftAssetOutput(Parcel parcel) {
        this.code = parcel.readInt();
        this.message = parcel.readString();
        this.data = parcel.createTypedArrayList(NftTokenBean.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.code);
        parcel.writeString(this.message);
        parcel.writeTypedList(this.data);
    }
}
