package com.tron.wallet.common.bean;

import com.tron.wallet.db.SpAPI;
public class Price {
    private float priceCny = 0.0f;
    private float priceUsd = 0.0f;
    private float change_1h = 0.0f;
    private float change_24h = 0.0f;
    private float change_7d = 0.0f;
    public float usdToRmb = 0.0f;

    public float getChange_1h() {
        return this.change_1h;
    }

    public float getChange_24h() {
        return this.change_24h;
    }

    public float getChange_7d() {
        return this.change_7d;
    }

    public void setChange_1h(float f) {
        this.change_1h = f;
    }

    public void setChange_24h(float f) {
        this.change_24h = f;
    }

    public void setChange_7d(float f) {
        this.change_7d = f;
    }

    public void setCnyPrice(float f) {
        this.priceCny = f;
    }

    public void setUsdPrice(float f) {
        this.priceUsd = f;
    }

    public float getPrice() {
        return SpAPI.THIS.isUsdPrice() ? this.priceUsd : this.priceCny;
    }
}
