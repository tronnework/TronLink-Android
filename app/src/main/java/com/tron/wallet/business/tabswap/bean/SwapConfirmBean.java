package com.tron.wallet.business.tabswap.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;
public class SwapConfirmBean implements Serializable, Parcelable {
    public static final Parcelable.Creator<SwapConfirmBean> CREATOR = new Parcelable.Creator<SwapConfirmBean>() {
        @Override
        public SwapConfirmBean createFromParcel(Parcel parcel) {
            return new SwapConfirmBean(parcel);
        }

        @Override
        public SwapConfirmBean[] newArray(int i) {
            return new SwapConfirmBean[i];
        }
    };
    private String amountLeft;
    private String amountRight;
    private int direction;
    private String fee;
    private String minReceived;
    private String priceImpact;
    private String rate;
    private String received;
    private String resource;
    private SwapTokenBean tokenFrom;
    private SwapTokenBean tokenTo;
    private String triggerMethod;

    @Override
    public int describeContents() {
        return 0;
    }

    public String getAmountLeft() {
        return this.amountLeft;
    }

    public String getAmountRight() {
        return this.amountRight;
    }

    public int getDirection() {
        return this.direction;
    }

    public String getFee() {
        return this.fee;
    }

    public String getMinReceived() {
        return this.minReceived;
    }

    public String getPriceImpact() {
        return this.priceImpact;
    }

    public String getRate() {
        return this.rate;
    }

    public String getReceived() {
        return this.received;
    }

    public String getResource() {
        return this.resource;
    }

    public SwapTokenBean getTokenFrom() {
        return this.tokenFrom;
    }

    public SwapTokenBean getTokenTo() {
        return this.tokenTo;
    }

    public String getTriggerMethod() {
        return this.triggerMethod;
    }

    public void setAmountLeft(String str) {
        this.amountLeft = str;
    }

    public void setAmountRight(String str) {
        this.amountRight = str;
    }

    public void setDirection(int i) {
        this.direction = i;
    }

    public void setFee(String str) {
        this.fee = str;
    }

    public void setMinReceived(String str) {
        this.minReceived = str;
    }

    public void setPriceImpact(String str) {
        this.priceImpact = str;
    }

    public void setRate(String str) {
        this.rate = str;
    }

    public void setReceived(String str) {
        this.received = str;
    }

    public void setResource(String str) {
        this.resource = str;
    }

    public void setTokenFrom(SwapTokenBean swapTokenBean) {
        this.tokenFrom = swapTokenBean;
    }

    public void setTokenTo(SwapTokenBean swapTokenBean) {
        this.tokenTo = swapTokenBean;
    }

    public void setTriggerMethod(String str) {
        this.triggerMethod = str;
    }

    protected SwapConfirmBean(Parcel parcel) {
        this.fee = "0.3%";
        this.resource = "262 Bandwidth";
        this.amountLeft = parcel.readString();
        this.amountRight = parcel.readString();
        this.rate = parcel.readString();
        this.fee = parcel.readString();
        this.minReceived = parcel.readString();
        this.priceImpact = parcel.readString();
        this.resource = parcel.readString();
        this.triggerMethod = parcel.readString();
        this.received = parcel.readString();
        this.direction = parcel.readInt();
    }

    public SwapConfirmBean() {
        this.fee = "0.3%";
        this.resource = "262 Bandwidth";
    }

    public static SwapConfirmBean fromSwapTokenInfo(SwapTokenInfoBean swapTokenInfoBean) {
        SwapConfirmBean swapConfirmBean = new SwapConfirmBean();
        swapConfirmBean.setPriceImpact(swapTokenInfoBean.getPriceImpact());
        swapConfirmBean.setRate(swapTokenInfoBean.getRate());
        swapConfirmBean.setMinReceived(swapTokenInfoBean.getMinReceived());
        swapConfirmBean.setTriggerMethod(swapTokenInfoBean.getTriggerMethod());
        swapConfirmBean.setReceived(swapTokenInfoBean.getReceived());
        swapConfirmBean.setFee(swapTokenInfoBean.getFee());
        swapConfirmBean.setDirection(swapTokenInfoBean.getDirection());
        return swapConfirmBean;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.amountLeft);
        parcel.writeString(this.amountRight);
        parcel.writeString(this.rate);
        parcel.writeString(this.fee);
        parcel.writeString(this.minReceived);
        parcel.writeString(this.priceImpact);
        parcel.writeString(this.resource);
        parcel.writeString(this.triggerMethod);
        parcel.writeString(this.received);
        parcel.writeInt(this.direction);
    }
}
