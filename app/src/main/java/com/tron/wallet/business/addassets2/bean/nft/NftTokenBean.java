package com.tron.wallet.business.addassets2.bean.nft;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.utils.BigDecimalUtils;
public class NftTokenBean implements Parcelable {
    public static final Parcelable.Creator<NftTokenBean> CREATOR = new Parcelable.Creator<NftTokenBean>() {
        @Override
        public NftTokenBean createFromParcel(Parcel parcel) {
            return new NftTokenBean(parcel);
        }

        @Override
        public NftTokenBean[] newArray(int i) {
            return new NftTokenBean[i];
        }
    };
    private String contractAddress;
    private long count;
    private int doDb;
    private String homePage;
    private String id;
    private Long idx;
    private boolean inMainChain;
    private boolean inSideChain;
    private boolean isNew;
    private int isOfficial;
    private boolean isSelected;
    private String issueAddress;
    private String issueTime;
    private String logoUrl;
    private String maincontractAddress;
    private int marketId;
    private String name;
    private String shortName;
    private String tokenDesc;
    private int tokenStatus;
    private int top;
    private String totalSupply;
    private boolean transferStatus;
    private int type;
    private String walletAddress;

    @Override
    public int describeContents() {
        return 0;
    }

    public String getContractAddress() {
        return this.contractAddress;
    }

    public long getCount() {
        return this.count;
    }

    public int getDoDb() {
        return this.doDb;
    }

    public String getHomePage() {
        return this.homePage;
    }

    public String getId() {
        return this.id;
    }

    public Long getIdx() {
        return this.idx;
    }

    public boolean getInMainChain() {
        return this.inMainChain;
    }

    public boolean getInSideChain() {
        return this.inSideChain;
    }

    public int getIsOfficial() {
        return this.isOfficial;
    }

    public String getIssueAddress() {
        return this.issueAddress;
    }

    public String getIssueTime() {
        return this.issueTime;
    }

    public String getLogoUrl() {
        return this.logoUrl;
    }

    public String getMaincontractAddress() {
        return this.maincontractAddress;
    }

    public int getMarketId() {
        return this.marketId;
    }

    public String getName() {
        return this.name;
    }

    public String getShortName() {
        return this.shortName;
    }

    public String getTokenDesc() {
        return this.tokenDesc;
    }

    public int getTokenStatus() {
        return this.tokenStatus;
    }

    public int getTop() {
        return this.top;
    }

    public String getTotalSupply() {
        return this.totalSupply;
    }

    public boolean getTransferStatus() {
        return this.transferStatus;
    }

    public int getType() {
        return this.type;
    }

    public String getWalletAddress() {
        return this.walletAddress;
    }

    public boolean isInMainChain() {
        return this.inMainChain;
    }

    public boolean isInSideChain() {
        return this.inSideChain;
    }

    public boolean isNew() {
        return this.isNew;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public boolean isTransferStatus() {
        return this.transferStatus;
    }

    public void setContractAddress(String str) {
        this.contractAddress = str;
    }

    public void setCount(long j) {
        this.count = j;
    }

    public void setDoDb(int i) {
        this.doDb = i;
    }

    public void setHomePage(String str) {
        this.homePage = str;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setIdx(Long l) {
        this.idx = l;
    }

    public void setInMainChain(boolean z) {
        this.inMainChain = z;
    }

    public void setInSideChain(boolean z) {
        this.inSideChain = z;
    }

    public void setIsOfficial(int i) {
        this.isOfficial = i;
    }

    public void setIssueAddress(String str) {
        this.issueAddress = str;
    }

    public void setIssueTime(String str) {
        this.issueTime = str;
    }

    public void setLogoUrl(String str) {
        this.logoUrl = str;
    }

    public void setMaincontractAddress(String str) {
        this.maincontractAddress = str;
    }

    public void setMarketId(int i) {
        this.marketId = i;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setNew(boolean z) {
        this.isNew = z;
    }

    public void setSelected(boolean z) {
        this.isSelected = z;
    }

    public void setShortName(String str) {
        this.shortName = str;
    }

    public void setTokenDesc(String str) {
        this.tokenDesc = str;
    }

    public void setTokenStatus(int i) {
        this.tokenStatus = i;
    }

    public void setTop(int i) {
        this.top = i;
    }

    public void setTotalSupply(String str) {
        this.totalSupply = str;
    }

    public void setTransferStatus(boolean z) {
        this.transferStatus = z;
    }

    public void setType(int i) {
        this.type = i;
    }

    public void setWalletAddress(String str) {
        this.walletAddress = str;
    }

    public NftTokenBean() {
    }

    protected NftTokenBean(Parcel parcel) {
        this.idx = (Long) parcel.readValue(Long.class.getClassLoader());
        this.type = parcel.readInt();
        this.top = parcel.readInt();
        this.isOfficial = parcel.readInt();
        this.name = parcel.readString();
        this.shortName = parcel.readString();
        this.id = parcel.readString();
        this.contractAddress = parcel.readString();
        this.count = parcel.readLong();
        this.logoUrl = parcel.readString();
        this.inMainChain = parcel.readByte() != 0;
        this.inSideChain = parcel.readByte() != 0;
        this.maincontractAddress = parcel.readString();
        this.homePage = parcel.readString();
        this.tokenDesc = parcel.readString();
        this.issueTime = parcel.readString();
        this.issueAddress = parcel.readString();
        this.totalSupply = parcel.readString();
        this.marketId = parcel.readInt();
        this.walletAddress = parcel.readString();
        this.tokenStatus = parcel.readInt();
        this.transferStatus = parcel.readByte() != 0;
    }

    public NftTokenBean(Long l, int i, int i2, int i3, String str, String str2, String str3, String str4, long j, String str5, boolean z, boolean z2, String str6, String str7, String str8, String str9, String str10, String str11, int i4, String str12, int i5, int i6, boolean z3) {
        this.idx = l;
        this.type = i;
        this.top = i2;
        this.isOfficial = i3;
        this.name = str;
        this.shortName = str2;
        this.id = str3;
        this.contractAddress = str4;
        this.count = j;
        this.logoUrl = str5;
        this.inMainChain = z;
        this.inSideChain = z2;
        this.maincontractAddress = str6;
        this.homePage = str7;
        this.tokenDesc = str8;
        this.issueTime = str9;
        this.issueAddress = str10;
        this.totalSupply = str11;
        this.marketId = i4;
        this.walletAddress = str12;
        this.doDb = i5;
        this.tokenStatus = i6;
        this.transferStatus = z3;
    }

    public static NftTokenBean covertFromTokenBean(TokenBean tokenBean) {
        NftTokenBean nftTokenBean = new NftTokenBean();
        nftTokenBean.setType(5);
        nftTokenBean.setTop(tokenBean.getTop());
        nftTokenBean.setIsOfficial(tokenBean.getIsOfficial());
        nftTokenBean.setName(tokenBean.getName());
        nftTokenBean.setShortName(tokenBean.getShortName());
        nftTokenBean.setId(tokenBean.getId());
        nftTokenBean.setContractAddress(tokenBean.getContractAddress());
        nftTokenBean.setCount(BigDecimalUtils.toBigDecimal(tokenBean.balanceStr).longValueExact());
        nftTokenBean.setLogoUrl(tokenBean.getLogoUrl());
        nftTokenBean.setInMainChain(tokenBean.getInMainChain());
        nftTokenBean.setInSideChain(tokenBean.getInSideChain());
        nftTokenBean.setMaincontractAddress(tokenBean.getMaincontractAddress());
        nftTokenBean.setHomePage(tokenBean.getHomePage());
        nftTokenBean.setTokenDesc(tokenBean.getTokenDesc());
        nftTokenBean.setIssueTime(tokenBean.getIssueTime());
        nftTokenBean.setIssueAddress(tokenBean.getIssueAddress());
        nftTokenBean.setTotalSupply(tokenBean.getTotalSupply());
        nftTokenBean.setMarketId(tokenBean.getMarketId());
        nftTokenBean.setWalletAddress(tokenBean.getAddress());
        nftTokenBean.setSelected(tokenBean.isSelected);
        nftTokenBean.setNew(tokenBean.isNewAsset());
        nftTokenBean.setTokenStatus(tokenBean.getTokenStatus());
        nftTokenBean.setTransferStatus(tokenBean.getTransferStatus());
        return nftTokenBean;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.idx.longValue());
        parcel.writeInt(this.type);
        parcel.writeInt(this.top);
        parcel.writeInt(this.isOfficial);
        parcel.writeString(this.name);
        parcel.writeString(this.shortName);
        parcel.writeString(this.id);
        parcel.writeString(this.contractAddress);
        parcel.writeLong(this.count);
        parcel.writeString(this.logoUrl);
        parcel.writeByte(this.inMainChain ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.inSideChain ? (byte) 1 : (byte) 0);
        parcel.writeString(this.maincontractAddress);
        parcel.writeString(this.homePage);
        parcel.writeString(this.tokenDesc);
        parcel.writeString(this.issueTime);
        parcel.writeString(this.issueAddress);
        parcel.writeString(this.totalSupply);
        parcel.writeInt(this.marketId);
        parcel.writeString(this.walletAddress);
        parcel.writeInt(this.tokenStatus);
        parcel.writeByte(this.transferStatus ? (byte) 1 : (byte) 0);
    }

    public TokenBean convertToTokenBean() {
        TokenBean tokenBean = new TokenBean();
        tokenBean.setType(5);
        tokenBean.setTop(getTop());
        tokenBean.setIsOfficial(getIsOfficial());
        tokenBean.setName(getName());
        tokenBean.setShortName(getShortName());
        tokenBean.setId(getId());
        tokenBean.setContractAddress(getContractAddress());
        tokenBean.setBalance(getCount());
        tokenBean.setBalanceStr(String.valueOf(getCount()));
        tokenBean.setLogoUrl(getLogoUrl());
        tokenBean.setInMainChain(getInMainChain());
        tokenBean.setInSideChain(getInSideChain());
        tokenBean.setMaincontractAddress(getMaincontractAddress());
        tokenBean.setHomePage(getHomePage());
        tokenBean.setTokenDesc(getTokenDesc());
        tokenBean.setIssueTime(getIssueTime());
        tokenBean.setIssueAddress(getIssueAddress());
        tokenBean.setTotalSupply(getTotalSupply());
        tokenBean.setMarketId(getMarketId());
        tokenBean.setAddress(getWalletAddress());
        tokenBean.setExtraData(this);
        tokenBean.setPrice(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        tokenBean.isSelected = isSelected();
        tokenBean.setNewAsset(isNew());
        tokenBean.setTokenStatus(getTokenStatus());
        tokenBean.setTransferStatus(getTransferStatus());
        return tokenBean;
    }
}
