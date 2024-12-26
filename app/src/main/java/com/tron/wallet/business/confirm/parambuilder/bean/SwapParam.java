package com.tron.wallet.business.confirm.parambuilder.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.tron.wallet.business.tabswap.bean.SwapConfirmBean;
public class SwapParam extends BaseParam implements Parcelable {
    public static final Parcelable.Creator<BaseParam> CREATOR = new Parcelable.Creator<BaseParam>() {
        @Override
        public BaseParam createFromParcel(Parcel parcel) {
            return new SwapParam(parcel);
        }

        @Override
        public BaseParam[] newArray(int i) {
            return new SwapParam[i];
        }
    };
    private SwapConfirmBean swapConfirmBean;

    @Override
    public int describeContents() {
        return 0;
    }

    public SwapConfirmBean getSwapConfirmBean() {
        return this.swapConfirmBean;
    }

    public void setSwapConfirmBean(SwapConfirmBean swapConfirmBean) {
        this.swapConfirmBean = swapConfirmBean;
    }

    public SwapParam() {
    }

    protected SwapParam(Parcel parcel) {
        super(parcel);
        this.swapConfirmBean = (SwapConfirmBean) parcel.readSerializable();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeSerializable(this.swapConfirmBean);
    }
}
