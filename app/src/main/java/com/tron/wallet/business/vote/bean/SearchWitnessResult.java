package com.tron.wallet.business.vote.bean;

import android.os.Parcel;
import android.os.Parcelable;
public class SearchWitnessResult implements Parcelable {
    public static final Parcelable.Creator<SearchWitnessResult> CREATOR = new Parcelable.Creator<SearchWitnessResult>() {
        @Override
        public SearchWitnessResult createFromParcel(Parcel parcel) {
            return new SearchWitnessResult(parcel);
        }

        @Override
        public SearchWitnessResult[] newArray(int i) {
            return new SearchWitnessResult[i];
        }
    };
    private int code;
    private WitnessOutput data;
    private String message;

    @Override
    public int describeContents() {
        return 0;
    }

    public int getCode() {
        return this.code;
    }

    public WitnessOutput getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setData(WitnessOutput witnessOutput) {
        this.data = witnessOutput;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    protected SearchWitnessResult(Parcel parcel) {
        this.code = parcel.readInt();
        this.message = parcel.readString();
        this.data = (WitnessOutput) parcel.readParcelable(WitnessOutput.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.code);
        parcel.writeString(this.message);
        parcel.writeParcelable(this.data, i);
    }
}
