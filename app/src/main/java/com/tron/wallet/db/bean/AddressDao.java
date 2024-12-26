package com.tron.wallet.db.bean;

import android.os.Parcel;
import android.os.Parcelable;
public class AddressDao implements Parcelable {
    public static final Parcelable.Creator<AddressDao> CREATOR = new Parcelable.Creator<AddressDao>() {
        @Override
        public AddressDao createFromParcel(Parcel parcel) {
            return new AddressDao(parcel);
        }

        @Override
        public AddressDao[] newArray(int i) {
            return new AddressDao[i];
        }
    };
    public String address;
    public String description;
    private Long id;
    public transient boolean isScam;
    public transient boolean isSelected;
    public String name;

    @Override
    public int describeContents() {
        return 0;
    }

    public String getAddress() {
        return this.address;
    }

    public String getDescription() {
        return this.description;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public boolean isScam() {
        return this.isScam;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setScam(boolean z) {
        this.isScam = z;
    }

    public void setSelected(boolean z) {
        this.isSelected = z;
    }

    public AddressDao() {
    }

    protected AddressDao(Parcel parcel) {
        this.id = (Long) parcel.readValue(Long.class.getClassLoader());
        this.name = parcel.readString();
        this.address = parcel.readString();
        this.description = parcel.readString();
    }

    public AddressDao(String str, String str2, String str3, Long l) {
        this.name = str;
        this.address = str2;
        this.description = str3;
        this.id = l;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.id);
        parcel.writeString(this.name);
        parcel.writeString(this.address);
        parcel.writeString(this.description);
    }
}
