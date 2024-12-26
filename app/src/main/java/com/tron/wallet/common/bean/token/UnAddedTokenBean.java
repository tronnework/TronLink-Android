package com.tron.wallet.common.bean.token;

import android.os.Parcel;
import android.os.Parcelable;
@Deprecated
public class UnAddedTokenBean implements Parcelable {
    public static final Parcelable.Creator<UnAddedTokenBean> CREATOR = new Parcelable.Creator<UnAddedTokenBean>() {
        @Override
        public UnAddedTokenBean createFromParcel(Parcel parcel) {
            return new UnAddedTokenBean(parcel);
        }

        @Override
        public UnAddedTokenBean[] newArray(int i) {
            return new UnAddedTokenBean[i];
        }
    };
    public String address;
    public double balance;
    public String balanceStr;
    public String contractAddress;
    public String description;
    public String homePage;
    public String id;
    public String ieoUrl;
    public String ieoUrlZh;
    public boolean inMainChain;
    public boolean inSideChain;
    public boolean isMainChain;
    public int isOfficial;
    public boolean isSelected;
    public boolean isShield;
    public String issueAddress;
    public String issueTime;
    public String logoUrl;
    public String maincontractAddress;
    public int marketId;
    public int marketType;
    public String name;
    public String nodeId;
    public int precision;
    public double price;
    public String shortName;
    public int time;
    public String tokenDesc;
    public Long tokenId;
    public double totalEarnings;
    public long totalSupply;
    public double trxCount;
    public int type;
    public double yesterdayEarnings;

    @Override
    public int describeContents() {
        return 0;
    }

    public String getAddress() {
        return this.address;
    }

    public double getBalance() {
        return this.balance;
    }

    public String getBalanceStr() {
        return this.balanceStr;
    }

    public String getContractAddress() {
        return this.contractAddress;
    }

    public String getDescription() {
        return this.description;
    }

    public String getHomePage() {
        return this.homePage;
    }

    public String getId() {
        return this.id;
    }

    public String getIeoUrl() {
        return this.ieoUrl;
    }

    public String getIeoUrlZh() {
        return this.ieoUrlZh;
    }

    public boolean getInMainChain() {
        return this.inMainChain;
    }

    public boolean getInSideChain() {
        return this.inSideChain;
    }

    public boolean getIsMainChain() {
        return this.isMainChain;
    }

    public int getIsOfficial() {
        return this.isOfficial;
    }

    public boolean getIsShield() {
        return this.isShield;
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

    public int getMarketType() {
        return this.marketType;
    }

    public String getName() {
        return this.name;
    }

    public String getNodeId() {
        return this.nodeId;
    }

    public int getPrecision() {
        return this.precision;
    }

    public double getPrice() {
        return this.price;
    }

    public String getShortName() {
        return this.shortName;
    }

    public int getTime() {
        return this.time;
    }

    public String getTokenDesc() {
        return this.tokenDesc;
    }

    public Long getTokenId() {
        return this.tokenId;
    }

    public double getTotalEarnings() {
        return this.totalEarnings;
    }

    public long getTotalSupply() {
        return this.totalSupply;
    }

    public double getTrxCount() {
        return this.trxCount;
    }

    public int getType() {
        return this.type;
    }

    public double getYesterdayEarnings() {
        return this.yesterdayEarnings;
    }

    public boolean isInMainChain() {
        return this.inMainChain;
    }

    public boolean isInSideChain() {
        return this.inSideChain;
    }

    public boolean isShield() {
        return this.isShield;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setBalance(double d) {
        this.balance = d;
    }

    public void setBalanceStr(String str) {
        this.balanceStr = str;
    }

    public void setContractAddress(String str) {
        this.contractAddress = str;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setHomePage(String str) {
        this.homePage = str;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setIeoUrl(String str) {
        this.ieoUrl = str;
    }

    public void setIeoUrlZh(String str) {
        this.ieoUrlZh = str;
    }

    public void setInMainChain(boolean z) {
        this.inMainChain = z;
    }

    public void setInSideChain(boolean z) {
        this.inSideChain = z;
    }

    public void setIsMainChain(boolean z) {
        this.isMainChain = z;
    }

    public void setIsOfficial(int i) {
        this.isOfficial = i;
    }

    public void setIsShield(boolean z) {
        this.isShield = z;
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

    public void setMarketType(int i) {
        this.marketType = i;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setNodeId(String str) {
        this.nodeId = str;
    }

    public void setPrecision(int i) {
        this.precision = i;
    }

    public void setPrice(double d) {
        this.price = d;
    }

    public void setShield(boolean z) {
        this.isShield = z;
    }

    public void setShortName(String str) {
        this.shortName = str;
    }

    public void setTime(int i) {
        this.time = i;
    }

    public void setTokenDesc(String str) {
        this.tokenDesc = str;
    }

    public void setTokenId(Long l) {
        this.tokenId = l;
    }

    public void setTotalEarnings(double d) {
        this.totalEarnings = d;
    }

    public void setTotalSupply(long j) {
        this.totalSupply = j;
    }

    public void setTrxCount(double d) {
        this.trxCount = d;
    }

    public void setType(int i) {
        this.type = i;
    }

    public void setYesterdayEarnings(double d) {
        this.yesterdayEarnings = d;
    }

    public String toString() {
        return this.name;
    }

    public UnAddedTokenBean(TokenBean tokenBean) {
        this.type = tokenBean.type;
        this.isOfficial = tokenBean.isOfficial;
        this.name = tokenBean.name;
        this.shortName = tokenBean.shortName;
        this.id = tokenBean.id;
        this.contractAddress = tokenBean.contractAddress;
        this.balance = tokenBean.balance;
        this.balanceStr = tokenBean.balanceStr;
        this.trxCount = tokenBean.trxCount;
        this.description = tokenBean.description;
        this.logoUrl = tokenBean.logoUrl;
        this.precision = tokenBean.precision;
        this.homePage = tokenBean.homePage;
        this.tokenDesc = tokenBean.tokenDesc;
        this.issueTime = tokenBean.issueTime;
        this.address = tokenBean.address;
        this.issueAddress = tokenBean.issueAddress;
        this.price = tokenBean.price;
        this.marketId = tokenBean.marketId;
        this.marketType = tokenBean.marketType;
        this.time = tokenBean.time;
        this.ieoUrl = tokenBean.ieoUrl;
        this.ieoUrlZh = tokenBean.ieoUrlZh;
        this.yesterdayEarnings = tokenBean.yesterdayEarnings;
        this.totalEarnings = tokenBean.totalEarnings;
        this.isMainChain = tokenBean.isMainChain;
        this.inMainChain = tokenBean.inMainChain;
        this.inSideChain = tokenBean.inSideChain;
        this.nodeId = tokenBean.nodeId;
        this.isShield = tokenBean.isShield;
    }

    public UnAddedTokenBean() {
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.tokenId);
        parcel.writeInt(this.type);
        parcel.writeInt(this.isOfficial);
        parcel.writeString(this.name);
        parcel.writeString(this.shortName);
        parcel.writeString(this.id);
        parcel.writeString(this.contractAddress);
        parcel.writeString(this.maincontractAddress);
        parcel.writeDouble(this.balance);
        parcel.writeString(this.balanceStr);
        parcel.writeDouble(this.trxCount);
        parcel.writeString(this.description);
        parcel.writeString(this.logoUrl);
        parcel.writeInt(this.precision);
        parcel.writeString(this.homePage);
        parcel.writeString(this.tokenDesc);
        parcel.writeString(this.issueTime);
        parcel.writeLong(this.totalSupply);
        parcel.writeString(this.address);
        parcel.writeString(this.issueAddress);
        parcel.writeDouble(this.price);
        parcel.writeInt(this.marketId);
        parcel.writeInt(this.marketType);
        parcel.writeInt(this.time);
        parcel.writeString(this.ieoUrl);
        parcel.writeString(this.ieoUrlZh);
        parcel.writeDouble(this.yesterdayEarnings);
        parcel.writeDouble(this.totalEarnings);
        parcel.writeByte(this.isMainChain ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.inMainChain ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.inSideChain ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isShield ? (byte) 1 : (byte) 0);
        parcel.writeString(this.nodeId);
        parcel.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
    }

    protected UnAddedTokenBean(Parcel parcel) {
        this.tokenId = (Long) parcel.readValue(Long.class.getClassLoader());
        this.type = parcel.readInt();
        this.isOfficial = parcel.readInt();
        this.name = parcel.readString();
        this.shortName = parcel.readString();
        this.id = parcel.readString();
        this.contractAddress = parcel.readString();
        this.maincontractAddress = parcel.readString();
        this.balance = parcel.readDouble();
        this.balanceStr = parcel.readString();
        this.trxCount = parcel.readDouble();
        this.description = parcel.readString();
        this.logoUrl = parcel.readString();
        this.precision = parcel.readInt();
        this.homePage = parcel.readString();
        this.tokenDesc = parcel.readString();
        this.issueTime = parcel.readString();
        this.totalSupply = parcel.readLong();
        this.address = parcel.readString();
        this.issueAddress = parcel.readString();
        this.price = parcel.readDouble();
        this.marketId = parcel.readInt();
        this.marketType = parcel.readInt();
        this.time = parcel.readInt();
        this.ieoUrl = parcel.readString();
        this.ieoUrlZh = parcel.readString();
        this.yesterdayEarnings = parcel.readDouble();
        this.totalEarnings = parcel.readDouble();
        this.isMainChain = parcel.readByte() != 0;
        this.inMainChain = parcel.readByte() != 0;
        this.inSideChain = parcel.readByte() != 0;
        this.isShield = parcel.readByte() != 0;
        this.nodeId = parcel.readString();
        this.isSelected = parcel.readByte() != 0;
    }

    public UnAddedTokenBean(Long l, int i, int i2, String str, String str2, String str3, String str4, String str5, double d, String str6, double d2, String str7, String str8, int i3, String str9, String str10, String str11, long j, String str12, String str13, double d3, int i4, int i5, int i6, String str14, String str15, double d4, double d5, boolean z, boolean z2, boolean z3, boolean z4, String str16) {
        this.tokenId = l;
        this.type = i;
        this.isOfficial = i2;
        this.name = str;
        this.shortName = str2;
        this.id = str3;
        this.contractAddress = str4;
        this.maincontractAddress = str5;
        this.balance = d;
        this.balanceStr = str6;
        this.trxCount = d2;
        this.description = str7;
        this.logoUrl = str8;
        this.precision = i3;
        this.homePage = str9;
        this.tokenDesc = str10;
        this.issueTime = str11;
        this.totalSupply = j;
        this.address = str12;
        this.issueAddress = str13;
        this.price = d3;
        this.marketId = i4;
        this.marketType = i5;
        this.time = i6;
        this.ieoUrl = str14;
        this.ieoUrlZh = str15;
        this.yesterdayEarnings = d4;
        this.totalEarnings = d5;
        this.isMainChain = z;
        this.inMainChain = z2;
        this.inSideChain = z3;
        this.isShield = z4;
        this.nodeId = str16;
    }
}
