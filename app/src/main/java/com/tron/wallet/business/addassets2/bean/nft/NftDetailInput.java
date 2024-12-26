package com.tron.wallet.business.addassets2.bean.nft;

import java.util.List;
public class NftDetailInput {
    private String address;
    private List<String> assetIdList;
    private String tokenAddress;

    public String getAddress() {
        return this.address;
    }

    public List<String> getAssetIdList() {
        return this.assetIdList;
    }

    public String getTokenAddress() {
        return this.tokenAddress;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setAssetIdList(List<String> list) {
        this.assetIdList = list;
    }

    public void setTokenAddress(String str) {
        this.tokenAddress = str;
    }
}
