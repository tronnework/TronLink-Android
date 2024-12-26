package com.tron.wallet.business.addassets2.bean.asset;

import com.google.gson.Gson;
import com.tron.wallet.common.bean.token.TokenBean;
import java.util.concurrent.CopyOnWriteArrayList;
import org.greenrobot.greendao.converter.PropertyConverter;
public class AssetsHomeData {
    public String address;
    public long id;
    public String nodeId;
    public AssetsHomePriceBean price;
    public CopyOnWriteArrayList<TokenBean> token;
    public String totalCny;
    public double totalTRX;
    public String totalUsd;

    public String getAddress() {
        return this.address;
    }

    public long getId() {
        return this.id;
    }

    public String getNodeId() {
        return this.nodeId;
    }

    public AssetsHomePriceBean getPrice() {
        return this.price;
    }

    public CopyOnWriteArrayList<TokenBean> getToken() {
        return this.token;
    }

    public String getTotalCny() {
        return this.totalCny;
    }

    public double getTotalTRX() {
        return this.totalTRX;
    }

    public String getTotalUsd() {
        return this.totalUsd;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setId(long j) {
        this.id = j;
    }

    public void setNodeId(String str) {
        this.nodeId = str;
    }

    public void setPrice(AssetsHomePriceBean assetsHomePriceBean) {
        this.price = assetsHomePriceBean;
    }

    public void setTotalCny(String str) {
        this.totalCny = str;
    }

    public void setTotalTRX(double d) {
        this.totalTRX = d;
    }

    public void setTotalUsd(String str) {
        this.totalUsd = str;
    }

    public static AssetsHomeData copy(AssetsHomeData assetsHomeData) {
        AssetsHomeData assetsHomeData2 = new AssetsHomeData();
        assetsHomeData2.id = assetsHomeData.id;
        assetsHomeData2.totalTRX = assetsHomeData.totalTRX;
        assetsHomeData2.price = assetsHomeData.price;
        assetsHomeData2.address = assetsHomeData.address;
        assetsHomeData2.nodeId = assetsHomeData.nodeId;
        CopyOnWriteArrayList<TokenBean> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        assetsHomeData2.token = copyOnWriteArrayList;
        CopyOnWriteArrayList<TokenBean> copyOnWriteArrayList2 = assetsHomeData.token;
        if (copyOnWriteArrayList2 != null) {
            copyOnWriteArrayList.addAll(copyOnWriteArrayList2);
        }
        return assetsHomeData2;
    }

    public AssetsHomeData(long j, double d, AssetsHomePriceBean assetsHomePriceBean, String str, String str2, String str3, String str4) {
        this.id = j;
        this.totalTRX = d;
        this.price = assetsHomePriceBean;
        this.totalUsd = str;
        this.totalCny = str2;
        this.address = str3;
        this.nodeId = str4;
    }

    public AssetsHomeData() {
    }

    public static class PriceConverter implements PropertyConverter<AssetsHomePriceBean, String> {
        @Override
        public AssetsHomePriceBean convertToEntityProperty(String str) {
            if (str == null) {
                return null;
            }
            return (AssetsHomePriceBean) new Gson().fromJson(str,  AssetsHomePriceBean.class);
        }

        @Override
        public String convertToDatabaseValue(AssetsHomePriceBean assetsHomePriceBean) {
            if (assetsHomePriceBean == null) {
                return null;
            }
            return new Gson().toJson(assetsHomePriceBean);
        }
    }

    public String toString() {
        return "AssetsHomeData{id=" + this.id + ", totalTRX=" + this.totalTRX + ", price=" + this.price + ", address='" + this.address + "', nodeId='" + this.nodeId + "', token=" + this.token + '}';
    }
}
