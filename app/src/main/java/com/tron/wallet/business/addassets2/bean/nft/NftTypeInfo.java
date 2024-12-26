package com.tron.wallet.business.addassets2.bean.nft;

import java.util.List;
public class NftTypeInfo {
    private List<NftItemInfo> collectionInfoList;
    private String fullName;
    private String intro;
    private String logoUrl;
    private String tokenAddress;
    private String walletAddress;
    private String word;

    public List<NftItemInfo> getCollectionInfoList() {
        return this.collectionInfoList;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getIntro() {
        return this.intro;
    }

    public String getLogoUrl() {
        return this.logoUrl;
    }

    public String getTokenAddress() {
        return this.tokenAddress;
    }

    public String getWalletAddress() {
        return this.walletAddress;
    }

    public String getWord() {
        return this.word;
    }

    public void setCollectionInfoList(List<NftItemInfo> list) {
        this.collectionInfoList = list;
    }

    public void setFullName(String str) {
        this.fullName = str;
    }

    public void setIntro(String str) {
        this.intro = str;
    }

    public void setLogoUrl(String str) {
        this.logoUrl = str;
    }

    public void setTokenAddress(String str) {
        this.tokenAddress = str;
    }

    public void setWalletAddress(String str) {
        this.walletAddress = str;
    }

    public void setWord(String str) {
        this.word = str;
    }

    public NftTypeInfo(String str, String str2, String str3, String str4) {
        this.tokenAddress = str;
        this.intro = str2;
        this.logoUrl = str3;
        this.fullName = str4;
    }

    public NftTypeInfo() {
    }

    public static NftTypeInfo buildDefault() {
        NftTypeInfo nftTypeInfo = new NftTypeInfo();
        nftTypeInfo.setTokenAddress("");
        nftTypeInfo.setIntro("");
        nftTypeInfo.setLogoUrl("");
        nftTypeInfo.setFullName("");
        return nftTypeInfo;
    }

    public String toString() {
        return "NftTypeInfo{tokenAddress='" + this.tokenAddress + "', intro='" + this.intro + "', logoUrl='" + this.logoUrl + "', fullName='" + this.fullName + "', collectionInfoList=" + this.collectionInfoList + '}';
    }
}
