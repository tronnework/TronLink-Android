package com.tron.wallet.common.bean.token;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.tron.wallet.business.security.tokencheck.bean.TokenCheckBean;
import java.math.BigDecimal;
import org.greenrobot.greendao.converter.PropertyConverter;
public class TokenBean implements Parcelable {
    public static final Parcelable.Creator<TokenBean> CREATOR = new Parcelable.Creator<TokenBean>() {
        @Override
        public TokenBean createFromParcel(Parcel parcel) {
            return new TokenBean(parcel);
        }

        @Override
        public TokenBean[] newArray(int i) {
            return new TokenBean[i];
        }
    };
    public String address;
    public double balance;
    public String balanceStr;
    private String cnyPrice;
    public String contractAddress;
    public int defiType;
    public String description;
    public int doDb;
    private Object extraData;
    public String homePage;
    public String id;
    public String ieoUrl;
    public String ieoUrlZh;
    public boolean inActivity;
    public boolean inMainChain;
    public boolean inSideChain;
    public boolean isInAssets;
    public boolean isMainChain;
    private boolean isNewAsset;
    public int isOfficial;
    public boolean isSelected;
    public boolean isShield;
    public int ispendinguploading;
    public String issueAddress;
    public String issueTime;
    public String logoUrl;
    public String maincontractAddress;
    public int marketId;
    public int marketType;
    public String name;
    public String national;
    public String nodeId;
    public int precision;
    public double price;
    public String shortName;
    public int time;
    public String tokenDesc;
    public Long tokenId;
    private int tokenStatus;
    public int top;
    public double totalBalance;
    public double totalEarnings;
    public String totalSupply;
    private boolean transferStatus;
    public double trxCount;
    public int type;
    public int usageType;
    private String usdPrice;
    public int walletType;
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
        String str = this.balanceStr;
        return str == null ? "0" : str;
    }

    public String getCnyPrice() {
        return this.cnyPrice;
    }

    public String getContractAddress() {
        return this.contractAddress;
    }

    public int getDefiType() {
        return this.defiType;
    }

    public String getDescription() {
        return this.description;
    }

    public int getDoDb() {
        return this.doDb;
    }

    public Object getExtraData() {
        return this.extraData;
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

    public boolean getIsInAssets() {
        return this.isInAssets;
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

    public int getIspendinguploading() {
        return this.ispendinguploading;
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

    public String getNational() {
        return this.national;
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

    public int getTokenStatus() {
        return this.tokenStatus;
    }

    public int getTop() {
        return this.top;
    }

    public double getTotalBalance() {
        return this.totalBalance;
    }

    public double getTotalEarnings() {
        return this.totalEarnings;
    }

    public String getTotalSupply() {
        return this.totalSupply;
    }

    public boolean getTransferStatus() {
        return this.transferStatus;
    }

    public double getTrxCount() {
        return this.trxCount;
    }

    public int getType() {
        return this.type;
    }

    public int getUsageType() {
        return this.usageType;
    }

    public String getUsdPrice() {
        return this.usdPrice;
    }

    public int getWalletType() {
        return this.walletType;
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

    public boolean isNewAsset() {
        return this.isNewAsset;
    }

    public boolean isShield() {
        return this.isShield;
    }

    public boolean isTransferStatus() {
        return this.transferStatus;
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

    public void setCnyPrice(String str) {
        this.cnyPrice = str;
    }

    public void setContractAddress(String str) {
        this.contractAddress = str;
    }

    public void setDefiType(int i) {
        this.defiType = i;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setDoDb(int i) {
        this.doDb = i;
    }

    public void setExtraData(Object obj) {
        this.extraData = obj;
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

    public void setIsInAssets(boolean z) {
        this.isInAssets = z;
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

    public void setIspendinguploading(int i) {
        this.ispendinguploading = i;
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

    public void setNational(String str) {
        this.national = str;
    }

    public void setNewAsset(boolean z) {
        this.isNewAsset = z;
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

    public void setTokenStatus(int i) {
        this.tokenStatus = i;
    }

    public void setTop(int i) {
        this.top = i;
    }

    public void setTotalBalance(double d) {
        this.totalBalance = d;
    }

    public void setTotalEarnings(double d) {
        this.totalEarnings = d;
    }

    public void setTotalSupply(String str) {
        this.totalSupply = str;
    }

    public void setTransferStatus(boolean z) {
        this.transferStatus = z;
    }

    public void setTrxCount(double d) {
        this.trxCount = d;
    }

    public void setType(int i) {
        this.type = i;
    }

    public void setUsageType(int i) {
        this.usageType = i;
    }

    public void setUsdPrice(String str) {
        this.usdPrice = str;
    }

    public void setWalletType(int i) {
        this.walletType = i;
    }

    public void setYesterdayEarnings(double d) {
        this.yesterdayEarnings = d;
    }

    public TokenBean() {
        this.transferStatus = true;
    }

    @Deprecated
    public TokenBean(UnAddedTokenBean unAddedTokenBean) {
        this.transferStatus = true;
        this.type = unAddedTokenBean.type;
        this.isOfficial = unAddedTokenBean.isOfficial;
        this.name = unAddedTokenBean.name;
        this.shortName = unAddedTokenBean.shortName;
        this.id = unAddedTokenBean.id;
        this.contractAddress = unAddedTokenBean.contractAddress;
        this.balance = unAddedTokenBean.balance;
        this.balanceStr = unAddedTokenBean.balanceStr;
        this.trxCount = unAddedTokenBean.trxCount;
        this.description = unAddedTokenBean.description;
        this.logoUrl = unAddedTokenBean.logoUrl;
        this.precision = unAddedTokenBean.precision;
        this.homePage = unAddedTokenBean.homePage;
        this.tokenDesc = unAddedTokenBean.tokenDesc;
        this.issueTime = unAddedTokenBean.issueTime;
        this.totalSupply = String.valueOf(unAddedTokenBean.totalSupply);
        this.address = unAddedTokenBean.address;
        this.issueAddress = unAddedTokenBean.issueAddress;
        this.price = unAddedTokenBean.price;
        this.marketId = unAddedTokenBean.marketId;
        this.marketType = unAddedTokenBean.marketType;
        this.time = unAddedTokenBean.time;
        this.ieoUrl = unAddedTokenBean.ieoUrl;
        this.ieoUrlZh = unAddedTokenBean.ieoUrlZh;
        this.yesterdayEarnings = unAddedTokenBean.yesterdayEarnings;
        this.totalEarnings = unAddedTokenBean.totalEarnings;
        this.inMainChain = unAddedTokenBean.inMainChain;
        this.inSideChain = unAddedTokenBean.inSideChain;
        this.isShield = unAddedTokenBean.isShield;
    }

    public TokenBean(TokenCheckBean tokenCheckBean) {
        this.transferStatus = true;
        this.type = tokenCheckBean.getType().intValue();
        this.name = tokenCheckBean.getName();
        this.shortName = tokenCheckBean.getShortName();
        this.contractAddress = tokenCheckBean.getContractAddress();
        this.balanceStr = tokenCheckBean.getBalanceStr();
        this.logoUrl = tokenCheckBean.getLogoUrl();
        this.id = "";
        this.precision = tokenCheckBean.getPrecision().intValue();
    }

    protected TokenBean(Parcel parcel) {
        this.transferStatus = true;
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
        this.totalSupply = parcel.readString();
        this.address = parcel.readString();
        this.issueAddress = parcel.readString();
        this.ispendinguploading = parcel.readInt();
        this.price = parcel.readDouble();
        this.top = parcel.readInt();
        this.doDb = parcel.readInt();
        this.marketId = parcel.readInt();
        this.marketType = parcel.readInt();
        this.time = parcel.readInt();
        this.ieoUrl = parcel.readString();
        this.ieoUrlZh = parcel.readString();
        this.yesterdayEarnings = parcel.readDouble();
        this.totalEarnings = parcel.readDouble();
        this.totalBalance = parcel.readDouble();
        this.inMainChain = parcel.readByte() != 0;
        this.inSideChain = parcel.readByte() != 0;
        this.isShield = parcel.readByte() != 0;
        this.nodeId = parcel.readString();
        this.isSelected = parcel.readByte() != 0;
        this.isInAssets = parcel.readByte() != 0;
        this.inActivity = parcel.readByte() != 0;
        this.tokenStatus = parcel.readInt();
        this.transferStatus = parcel.readByte() != 0;
        this.usdPrice = parcel.readString();
        this.cnyPrice = parcel.readString();
        this.national = parcel.readString();
        this.defiType = parcel.readInt();
    }

    public TokenBean(Long l, int i, int i2, String str, String str2, String str3, String str4, String str5, double d, String str6, double d2, String str7, String str8, int i3, String str9, String str10, String str11, String str12, String str13, String str14, int i4, double d3, int i5, int i6, int i7, int i8, int i9, String str15, String str16, double d4, double d5, double d6, boolean z, boolean z2, boolean z3, boolean z4, String str17, boolean z5, int i10, int i11, int i12, boolean z6, String str18, String str19, String str20, int i13) {
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
        this.totalSupply = str12;
        this.address = str13;
        this.issueAddress = str14;
        this.ispendinguploading = i4;
        this.price = d3;
        this.top = i5;
        this.doDb = i6;
        this.marketId = i7;
        this.marketType = i8;
        this.time = i9;
        this.ieoUrl = str15;
        this.ieoUrlZh = str16;
        this.yesterdayEarnings = d4;
        this.totalEarnings = d5;
        this.totalBalance = d6;
        this.inMainChain = z;
        this.inSideChain = z2;
        this.isShield = z3;
        this.isMainChain = z4;
        this.nodeId = str17;
        this.isInAssets = z5;
        this.usageType = i10;
        this.walletType = i11;
        this.tokenStatus = i12;
        this.transferStatus = z6;
        this.usdPrice = str18;
        this.cnyPrice = str19;
        this.national = str20;
        this.defiType = i13;
    }

    public String toString() {
        return "TokenBean{tokenId=" + this.tokenId + ", type=" + this.type + ", isOfficial=" + this.isOfficial + ", name='" + this.name + "', shortName='" + this.shortName + "', id='" + this.id + "', contractAddress='" + this.contractAddress + "', balance=" + this.balance + ", balanceStr=" + this.balanceStr + ", totalbalance=" + this.totalBalance + ", trxCount=" + this.trxCount + ", price=" + this.price + ", top=" + this.top + ", doDb=" + this.doDb + '}';
    }

    public boolean isUsdt() {
        return "TR7NHqjeKQxGTCi8q8ZY4pL8otSzgjLj6t".equals(this.contractAddress);
    }

    public boolean isWTRX() {
        return "TNUC9Qb1rRpS5CbWLmNMxXBjyFoydXjWFR".equals(this.contractAddress);
    }

    public boolean equals(Object obj) {
        if (obj instanceof TokenBean) {
            TokenBean tokenBean = (TokenBean) obj;
            int i = this.type;
            if (i != tokenBean.type) {
                return false;
            }
            return i == 0 ? getBalance() == tokenBean.getBalance() : (TextUtils.isEmpty(this.contractAddress) || TextUtils.isEmpty(tokenBean.getContractAddress()) || this.type == 3) ? this.type == 3 ? TextUtils.equals(this.ieoUrl, tokenBean.getIeoUrl()) && TextUtils.equals(this.ieoUrlZh, tokenBean.getIeoUrlZh()) && this.balance == tokenBean.getBalance() : TextUtils.equals(getId(), tokenBean.getId()) && this.balance == tokenBean.getBalance() : TextUtils.equals(this.contractAddress, tokenBean.getContractAddress()) && getBalance() == tokenBean.getBalance();
        }
        return false;
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
        parcel.writeString(this.totalSupply);
        parcel.writeString(this.address);
        parcel.writeString(this.issueAddress);
        parcel.writeInt(this.ispendinguploading);
        parcel.writeDouble(this.price);
        parcel.writeInt(this.top);
        parcel.writeInt(this.doDb);
        parcel.writeInt(this.marketId);
        parcel.writeInt(this.marketType);
        parcel.writeInt(this.time);
        parcel.writeString(this.ieoUrl);
        parcel.writeString(this.ieoUrlZh);
        parcel.writeDouble(this.yesterdayEarnings);
        parcel.writeDouble(this.totalEarnings);
        parcel.writeDouble(this.totalBalance);
        parcel.writeByte(this.inMainChain ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.inSideChain ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isShield ? (byte) 1 : (byte) 0);
        parcel.writeString(this.nodeId);
        parcel.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isInAssets ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.inActivity ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.tokenStatus);
        parcel.writeByte(this.transferStatus ? (byte) 1 : (byte) 0);
        parcel.writeString(this.usdPrice);
        parcel.writeString(this.cnyPrice);
        parcel.writeString(this.national);
        parcel.writeInt(this.defiType);
    }

    public BigDecimal getBalanceBigDecimal() {
        try {
            return new BigDecimal(this.balanceStr);
        } catch (Exception unused) {
            return new BigDecimal(0);
        }
    }

    public static class BigDecimalConverter implements PropertyConverter<BigDecimal, String> {
        @Override
        public BigDecimal convertToEntityProperty(String str) {
            if (str == null) {
                return new BigDecimal(0);
            }
            try {
                return new BigDecimal(str);
            } catch (Exception unused) {
                return new BigDecimal(0);
            }
        }

        @Override
        public String convertToDatabaseValue(BigDecimal bigDecimal) {
            if (bigDecimal == null) {
                return null;
            }
            return bigDecimal.toPlainString();
        }
    }
}
