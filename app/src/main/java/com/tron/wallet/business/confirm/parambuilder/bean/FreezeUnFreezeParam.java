package com.tron.wallet.business.confirm.parambuilder.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.tron.wallet.common.bean.ConfirmExtraText;
import java.util.ArrayList;
public class FreezeUnFreezeParam extends BaseParam implements Parcelable {
    public static final Parcelable.Creator<FreezeUnFreezeParam> CREATOR = new Parcelable.Creator<FreezeUnFreezeParam>() {
        @Override
        public FreezeUnFreezeParam createFromParcel(Parcel parcel) {
            return new FreezeUnFreezeParam(parcel);
        }

        @Override
        public FreezeUnFreezeParam[] newArray(int i) {
            return new FreezeUnFreezeParam[i];
        }
    };
    public static final int TYPE_BRANDWIDTH = 1;
    public static final int TYPE_ENERGY = 0;
    public double canUseTrxCount;
    public int doFreezeType;
    public double realFreeze;
    public int releasedResourceTypeCount;
    public String resourceCount;
    private int stakeVersion;
    public ArrayList<ConfirmExtraText> unfreezeResources;

    public enum TYPE {
        FOR_SELF,
        FOR_OTHER,
        BATCH
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public double getCanUseTrxCount() {
        return this.canUseTrxCount;
    }

    public int getDoFreezeType() {
        return this.doFreezeType;
    }

    public String getResourceCount() {
        return this.resourceCount;
    }

    public int getStakeVersion() {
        return this.stakeVersion;
    }

    public void setCanUseTrxCount(double d) {
        this.canUseTrxCount = d;
    }

    public void setDoFreezeType(int i) {
        this.doFreezeType = i;
    }

    public void setResourceCount(String str) {
        this.resourceCount = str;
    }

    public void setStakeVersion(int i) {
        this.stakeVersion = i;
    }

    public FreezeUnFreezeParam() {
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.doFreezeType);
        parcel.writeDouble(this.canUseTrxCount);
        parcel.writeDouble(this.realFreeze);
        parcel.writeTypedList(this.unfreezeResources);
        parcel.writeInt(this.releasedResourceTypeCount);
        parcel.writeString(this.resourceCount);
        parcel.writeInt(this.stakeVersion);
    }

    protected FreezeUnFreezeParam(Parcel parcel) {
        super(parcel);
        this.doFreezeType = parcel.readInt();
        this.canUseTrxCount = parcel.readDouble();
        this.realFreeze = parcel.readDouble();
        this.unfreezeResources = parcel.createTypedArrayList(ConfirmExtraText.CREATOR);
        this.releasedResourceTypeCount = parcel.readInt();
        this.resourceCount = parcel.readString();
        this.stakeVersion = parcel.readInt();
    }

    public Double getRealFreeze() {
        return Double.valueOf(this.realFreeze);
    }

    public void setRealFreeze(Double d) {
        this.realFreeze = d.doubleValue();
    }
}
