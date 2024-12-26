package com.tron.wallet.business.confirm.parambuilder.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.tron.wallet.common.bean.DealSignOutput;
import java.util.ArrayList;
import java.util.List;
public class ParticipateMultisignParam extends BaseParam implements Parcelable {
    public static final Parcelable.Creator<ParticipateMultisignParam> CREATOR = new Parcelable.Creator<ParticipateMultisignParam>() {
        @Override
        public ParticipateMultisignParam createFromParcel(Parcel parcel) {
            return new ParticipateMultisignParam(parcel);
        }

        @Override
        public ParticipateMultisignParam[] newArray(int i) {
            return new ParticipateMultisignParam[i];
        }
    };
    private ArrayList<DealSignOutput.DataBeanX.DataBean.SignatureProgressBean> progress;
    private int threshold;
    private String walletName;

    @Override
    public int describeContents() {
        return 0;
    }

    public List<DealSignOutput.DataBeanX.DataBean.SignatureProgressBean> getProgress() {
        return this.progress;
    }

    public int getThreshold() {
        return this.threshold;
    }

    public String getWalletName() {
        return this.walletName;
    }

    public void setProgress(ArrayList<DealSignOutput.DataBeanX.DataBean.SignatureProgressBean> arrayList) {
        this.progress = arrayList;
    }

    public void setThreshold(int i) {
        this.threshold = i;
    }

    public void setWalletName(String str) {
        this.walletName = str;
    }

    public ParticipateMultisignParam() {
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.walletName);
        parcel.writeTypedList(this.progress);
        parcel.writeInt(this.threshold);
    }

    protected ParticipateMultisignParam(Parcel parcel) {
        super(parcel);
        this.walletName = parcel.readString();
        this.progress = parcel.createTypedArrayList(DealSignOutput.DataBeanX.DataBean.SignatureProgressBean.CREATOR);
        this.threshold = parcel.readInt();
    }
}
