package com.tron.wallet.common.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;
public class PermissionGroupBean implements Parcelable {
    public static final Parcelable.Creator<PermissionGroupBean> CREATOR = new Parcelable.Creator<PermissionGroupBean>() {
        @Override
        public PermissionGroupBean createFromParcel(Parcel parcel) {
            return new PermissionGroupBean(parcel);
        }

        @Override
        public PermissionGroupBean[] newArray(int i) {
            return new PermissionGroupBean[i];
        }
    };
    private List<PermissionBean> list;
    private String type;

    @Override
    public int describeContents() {
        return 0;
    }

    public List<PermissionBean> getList() {
        return this.list;
    }

    public String getType() {
        return this.type;
    }

    public void setList(List<PermissionBean> list) {
        this.list = list;
    }

    public void setType(String str) {
        this.type = str;
    }

    public static class PermissionBean implements Parcelable {
        public static final Parcelable.Creator<PermissionBean> CREATOR = new Parcelable.Creator<PermissionBean>() {
            @Override
            public PermissionBean createFromParcel(Parcel parcel) {
                return new PermissionBean(parcel);
            }

            @Override
            public PermissionBean[] newArray(int i) {
                return new PermissionBean[i];
            }
        };
        private String display_name_en;
        private String display_name_zh;
        private int id;
        private String name;
        private int type;

        @Override
        public int describeContents() {
            return 0;
        }

        public String getDisplay_name_en() {
            return this.display_name_en;
        }

        public String getDisplay_name_zh() {
            return this.display_name_zh;
        }

        public int getId() {
            return this.id;
        }

        public String getName() {
            return this.name;
        }

        public int getType() {
            return this.type;
        }

        public void setDisplay_name_en(String str) {
            this.display_name_en = str;
        }

        public void setDisplay_name_zh(String str) {
            this.display_name_zh = str;
        }

        public void setId(int i) {
            this.id = i;
        }

        public void setName(String str) {
            this.name = str;
        }

        public void setType(int i) {
            this.type = i;
        }

        public String toString() {
            return "PermissionGroupBean{id='" + this.id + "', name='" + this.name + "', display_name_zh='" + this.display_name_zh + "', display_name_en='" + this.display_name_en + "'}";
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.id);
            parcel.writeInt(this.type);
            parcel.writeString(this.name);
            parcel.writeString(this.display_name_zh);
            parcel.writeString(this.display_name_en);
        }

        public PermissionBean() {
        }

        protected PermissionBean(Parcel parcel) {
            this.id = parcel.readInt();
            this.type = parcel.readInt();
            this.name = parcel.readString();
            this.display_name_zh = parcel.readString();
            this.display_name_en = parcel.readString();
        }
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.list);
        parcel.writeString(this.type);
    }

    public PermissionGroupBean() {
    }

    protected PermissionGroupBean(Parcel parcel) {
        this.list = parcel.createTypedArrayList(PermissionBean.CREATOR);
        this.type = parcel.readString();
    }
}
