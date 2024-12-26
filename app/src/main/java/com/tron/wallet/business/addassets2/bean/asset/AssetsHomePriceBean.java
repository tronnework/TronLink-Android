package com.tron.wallet.business.addassets2.bean.asset;
public class AssetsHomePriceBean {
    public double priceCny;
    public double priceUSD;

    public double getPriceCny() {
        return this.priceCny;
    }

    public double getPriceUSD() {
        return this.priceUSD;
    }

    public void setPriceCny(double d) {
        this.priceCny = d;
    }

    public void setPriceUSD(double d) {
        this.priceUSD = d;
    }

    public AssetsHomePriceBean(double d, double d2) {
        this.priceCny = d;
        this.priceUSD = d2;
    }

    public AssetsHomePriceBean() {
    }
}
