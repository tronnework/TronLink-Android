package com.tron.wallet.db.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.tron.wallet.business.customtokens.bean.CustomTokenStatus;
import com.tron.wallet.business.nft.contract.Contract;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
public class TokenBeanDao extends AbstractDao<TokenBean, Long> {
    public static final String TABLENAME = "TOKEN_BEAN";

    public static class Properties {
        public static final Property TokenId = new Property(0, Long.class, "tokenId", true, "_id");
        public static final Property Type = new Property(1, Integer.TYPE, "type", false, "TYPE");
        public static final Property IsOfficial = new Property(2, Integer.TYPE, "isOfficial", false, "IS_OFFICIAL");
        public static final Property Name = new Property(3, String.class, AppMeasurementSdk.ConditionalUserProperty.NAME, false, "NAME");
        public static final Property ShortName = new Property(4, String.class, "shortName", false, Contract.View.KEY_SHORT_NAME);
        public static final Property Id = new Property(5, String.class, "id", false, TronConfig.ID);
        public static final Property ContractAddress = new Property(6, String.class, "contractAddress", false, Contract.View.KEY_CONTRACT_ADDRESS);
        public static final Property MaincontractAddress = new Property(7, String.class, "maincontractAddress", false, "MAINCONTRACT_ADDRESS");
        public static final Property Balance = new Property(8, Double.TYPE, "balance", false, Event.BALANCE);
        public static final Property BalanceStr = new Property(9, String.class, "balanceStr", false, "BALANCE_STR");
        public static final Property TrxCount = new Property(10, Double.TYPE, "trxCount", false, "TRX_COUNT");
        public static final Property Description = new Property(11, String.class, "description", false, "DESCRIPTION");
        public static final Property LogoUrl = new Property(12, String.class, "logoUrl", false, "LOGO_URL");
        public static final Property Precision = new Property(13, Integer.TYPE, TronConfig.PRECISION, false, "PRECISION");
        public static final Property HomePage = new Property(14, String.class, Contract.View.KEY_HOME_PAGE, false, "HOME_PAGE");
        public static final Property TokenDesc = new Property(15, String.class, "tokenDesc", false, "TOKEN_DESC");
        public static final Property IssueTime = new Property(16, String.class, "issueTime", false, "ISSUE_TIME");
        public static final Property TotalSupply = new Property(17, String.class, CustomTokenStatus.TOTAL_SUPPLY, false, "TOTAL_SUPPLY");
        public static final Property Address = new Property(18, String.class, "address", false, "ADDRESS");
        public static final Property IssueAddress = new Property(19, String.class, "issueAddress", false, "ISSUE_ADDRESS");
        public static final Property Ispendinguploading = new Property(20, Integer.TYPE, "ispendinguploading", false, "ISPENDINGUPLOADING");
        public static final Property Price = new Property(21, Double.TYPE, FirebaseAnalytics.Param.PRICE, false, "PRICE");
        public static final Property Top = new Property(22, Integer.TYPE, "top", false, "TOP");
        public static final Property DoDb = new Property(23, Integer.TYPE, "doDb", false, "DO_DB");
        public static final Property MarketId = new Property(24, Integer.TYPE, "marketId", false, "MARKET_ID");
        public static final Property MarketType = new Property(25, Integer.TYPE, "marketType", false, "MARKET_TYPE");
        public static final Property Time = new Property(26, Integer.TYPE, "time", false, "TIME");
        public static final Property IeoUrl = new Property(27, String.class, "ieoUrl", false, "IEO_URL");
        public static final Property IeoUrlZh = new Property(28, String.class, "ieoUrlZh", false, "IEO_URL_ZH");
        public static final Property YesterdayEarnings = new Property(29, Double.TYPE, "yesterdayEarnings", false, "YESTERDAY_EARNINGS");
        public static final Property TotalEarnings = new Property(30, Double.TYPE, "totalEarnings", false, "TOTAL_EARNINGS");
        public static final Property TotalBalance = new Property(31, Double.TYPE, "totalBalance", false, "TOTAL_BALANCE");
        public static final Property InMainChain = new Property(32, Boolean.TYPE, "inMainChain", false, "IN_MAIN_CHAIN");
        public static final Property InSideChain = new Property(33, Boolean.TYPE, "inSideChain", false, "IN_SIDE_CHAIN");
        public static final Property IsShield = new Property(34, Boolean.TYPE, "isShield", false, "IS_SHIELD");
        public static final Property IsMainChain = new Property(35, Boolean.TYPE, "isMainChain", false, "IS_MAIN_CHAIN");
        public static final Property NodeId = new Property(36, String.class, "nodeId", false, "NODE_ID");
        public static final Property IsInAssets = new Property(37, Boolean.TYPE, "isInAssets", false, "IS_IN_ASSETS");
        public static final Property UsageType = new Property(38, Integer.TYPE, "usageType", false, "USAGE_TYPE");
        public static final Property WalletType = new Property(39, Integer.TYPE, "walletType", false, "WALLET_TYPE");
        public static final Property TokenStatus = new Property(40, Integer.TYPE, "tokenStatus", false, "TOKEN_STATUS");
        public static final Property TransferStatus = new Property(41, Boolean.TYPE, "transferStatus", false, "TRANSFER_STATUS");
        public static final Property UsdPrice = new Property(42, String.class, "usdPrice", false, "USD_PRICE");
        public static final Property CnyPrice = new Property(43, String.class, "cnyPrice", false, "CNY_PRICE");
        public static final Property National = new Property(44, String.class, "national", false, "NATIONAL");
        public static final Property DefiType = new Property(45, Integer.TYPE, "defiType", false, "DEFI_TYPE");
    }

    @Override
    public final boolean isEntityUpdateable() {
        return true;
    }

    public TokenBeanDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public TokenBeanDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"TOKEN_BEAN\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"TYPE\" INTEGER NOT NULL ,\"IS_OFFICIAL\" INTEGER NOT NULL ,\"NAME\" TEXT,\"SHORT_NAME\" TEXT,\"ID\" TEXT,\"CONTRACT_ADDRESS\" TEXT,\"MAINCONTRACT_ADDRESS\" TEXT,\"BALANCE\" REAL NOT NULL ,\"BALANCE_STR\" TEXT,\"TRX_COUNT\" REAL NOT NULL ,\"DESCRIPTION\" TEXT,\"LOGO_URL\" TEXT,\"PRECISION\" INTEGER NOT NULL ,\"HOME_PAGE\" TEXT,\"TOKEN_DESC\" TEXT,\"ISSUE_TIME\" TEXT,\"TOTAL_SUPPLY\" TEXT,\"ADDRESS\" TEXT,\"ISSUE_ADDRESS\" TEXT,\"ISPENDINGUPLOADING\" INTEGER NOT NULL ,\"PRICE\" REAL NOT NULL ,\"TOP\" INTEGER NOT NULL ,\"DO_DB\" INTEGER NOT NULL ,\"MARKET_ID\" INTEGER NOT NULL ,\"MARKET_TYPE\" INTEGER NOT NULL ,\"TIME\" INTEGER NOT NULL ,\"IEO_URL\" TEXT,\"IEO_URL_ZH\" TEXT,\"YESTERDAY_EARNINGS\" REAL NOT NULL ,\"TOTAL_EARNINGS\" REAL NOT NULL ,\"TOTAL_BALANCE\" REAL NOT NULL ,\"IN_MAIN_CHAIN\" INTEGER NOT NULL ,\"IN_SIDE_CHAIN\" INTEGER NOT NULL ,\"IS_SHIELD\" INTEGER NOT NULL ,\"IS_MAIN_CHAIN\" INTEGER NOT NULL ,\"NODE_ID\" TEXT,\"IS_IN_ASSETS\" INTEGER NOT NULL ,\"USAGE_TYPE\" INTEGER NOT NULL ,\"WALLET_TYPE\" INTEGER NOT NULL ,\"TOKEN_STATUS\" INTEGER NOT NULL ,\"TRANSFER_STATUS\" INTEGER NOT NULL ,\"USD_PRICE\" TEXT,\"CNY_PRICE\" TEXT,\"NATIONAL\" TEXT,\"DEFI_TYPE\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"TOKEN_BEAN\"");
        database.execSQL(sb.toString());
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, TokenBean tokenBean) {
        databaseStatement.clearBindings();
        Long tokenId = tokenBean.getTokenId();
        if (tokenId != null) {
            databaseStatement.bindLong(1, tokenId.longValue());
        }
        databaseStatement.bindLong(2, tokenBean.getType());
        databaseStatement.bindLong(3, tokenBean.getIsOfficial());
        String name = tokenBean.getName();
        if (name != null) {
            databaseStatement.bindString(4, name);
        }
        String shortName = tokenBean.getShortName();
        if (shortName != null) {
            databaseStatement.bindString(5, shortName);
        }
        String id = tokenBean.getId();
        if (id != null) {
            databaseStatement.bindString(6, id);
        }
        String contractAddress = tokenBean.getContractAddress();
        if (contractAddress != null) {
            databaseStatement.bindString(7, contractAddress);
        }
        String maincontractAddress = tokenBean.getMaincontractAddress();
        if (maincontractAddress != null) {
            databaseStatement.bindString(8, maincontractAddress);
        }
        databaseStatement.bindDouble(9, tokenBean.getBalance());
        String balanceStr = tokenBean.getBalanceStr();
        if (balanceStr != null) {
            databaseStatement.bindString(10, balanceStr);
        }
        databaseStatement.bindDouble(11, tokenBean.getTrxCount());
        String description = tokenBean.getDescription();
        if (description != null) {
            databaseStatement.bindString(12, description);
        }
        String logoUrl = tokenBean.getLogoUrl();
        if (logoUrl != null) {
            databaseStatement.bindString(13, logoUrl);
        }
        databaseStatement.bindLong(14, tokenBean.getPrecision());
        String homePage = tokenBean.getHomePage();
        if (homePage != null) {
            databaseStatement.bindString(15, homePage);
        }
        String tokenDesc = tokenBean.getTokenDesc();
        if (tokenDesc != null) {
            databaseStatement.bindString(16, tokenDesc);
        }
        String issueTime = tokenBean.getIssueTime();
        if (issueTime != null) {
            databaseStatement.bindString(17, issueTime);
        }
        String totalSupply = tokenBean.getTotalSupply();
        if (totalSupply != null) {
            databaseStatement.bindString(18, totalSupply);
        }
        String address = tokenBean.getAddress();
        if (address != null) {
            databaseStatement.bindString(19, address);
        }
        String issueAddress = tokenBean.getIssueAddress();
        if (issueAddress != null) {
            databaseStatement.bindString(20, issueAddress);
        }
        databaseStatement.bindLong(21, tokenBean.getIspendinguploading());
        databaseStatement.bindDouble(22, tokenBean.getPrice());
        databaseStatement.bindLong(23, tokenBean.getTop());
        databaseStatement.bindLong(24, tokenBean.getDoDb());
        databaseStatement.bindLong(25, tokenBean.getMarketId());
        databaseStatement.bindLong(26, tokenBean.getMarketType());
        databaseStatement.bindLong(27, tokenBean.getTime());
        String ieoUrl = tokenBean.getIeoUrl();
        if (ieoUrl != null) {
            databaseStatement.bindString(28, ieoUrl);
        }
        String ieoUrlZh = tokenBean.getIeoUrlZh();
        if (ieoUrlZh != null) {
            databaseStatement.bindString(29, ieoUrlZh);
        }
        databaseStatement.bindDouble(30, tokenBean.getYesterdayEarnings());
        databaseStatement.bindDouble(31, tokenBean.getTotalEarnings());
        databaseStatement.bindDouble(32, tokenBean.getTotalBalance());
        databaseStatement.bindLong(33, tokenBean.getInMainChain() ? 1L : 0L);
        databaseStatement.bindLong(34, tokenBean.getInSideChain() ? 1L : 0L);
        databaseStatement.bindLong(35, tokenBean.getIsShield() ? 1L : 0L);
        databaseStatement.bindLong(36, tokenBean.getIsMainChain() ? 1L : 0L);
        String nodeId = tokenBean.getNodeId();
        if (nodeId != null) {
            databaseStatement.bindString(37, nodeId);
        }
        databaseStatement.bindLong(38, tokenBean.getIsInAssets() ? 1L : 0L);
        databaseStatement.bindLong(39, tokenBean.getUsageType());
        databaseStatement.bindLong(40, tokenBean.getWalletType());
        databaseStatement.bindLong(41, tokenBean.getTokenStatus());
        databaseStatement.bindLong(42, tokenBean.getTransferStatus() ? 1L : 0L);
        String usdPrice = tokenBean.getUsdPrice();
        if (usdPrice != null) {
            databaseStatement.bindString(43, usdPrice);
        }
        String cnyPrice = tokenBean.getCnyPrice();
        if (cnyPrice != null) {
            databaseStatement.bindString(44, cnyPrice);
        }
        String national = tokenBean.getNational();
        if (national != null) {
            databaseStatement.bindString(45, national);
        }
        databaseStatement.bindLong(46, tokenBean.getDefiType());
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, TokenBean tokenBean) {
        sQLiteStatement.clearBindings();
        Long tokenId = tokenBean.getTokenId();
        if (tokenId != null) {
            sQLiteStatement.bindLong(1, tokenId.longValue());
        }
        sQLiteStatement.bindLong(2, tokenBean.getType());
        sQLiteStatement.bindLong(3, tokenBean.getIsOfficial());
        String name = tokenBean.getName();
        if (name != null) {
            sQLiteStatement.bindString(4, name);
        }
        String shortName = tokenBean.getShortName();
        if (shortName != null) {
            sQLiteStatement.bindString(5, shortName);
        }
        String id = tokenBean.getId();
        if (id != null) {
            sQLiteStatement.bindString(6, id);
        }
        String contractAddress = tokenBean.getContractAddress();
        if (contractAddress != null) {
            sQLiteStatement.bindString(7, contractAddress);
        }
        String maincontractAddress = tokenBean.getMaincontractAddress();
        if (maincontractAddress != null) {
            sQLiteStatement.bindString(8, maincontractAddress);
        }
        sQLiteStatement.bindDouble(9, tokenBean.getBalance());
        String balanceStr = tokenBean.getBalanceStr();
        if (balanceStr != null) {
            sQLiteStatement.bindString(10, balanceStr);
        }
        sQLiteStatement.bindDouble(11, tokenBean.getTrxCount());
        String description = tokenBean.getDescription();
        if (description != null) {
            sQLiteStatement.bindString(12, description);
        }
        String logoUrl = tokenBean.getLogoUrl();
        if (logoUrl != null) {
            sQLiteStatement.bindString(13, logoUrl);
        }
        sQLiteStatement.bindLong(14, tokenBean.getPrecision());
        String homePage = tokenBean.getHomePage();
        if (homePage != null) {
            sQLiteStatement.bindString(15, homePage);
        }
        String tokenDesc = tokenBean.getTokenDesc();
        if (tokenDesc != null) {
            sQLiteStatement.bindString(16, tokenDesc);
        }
        String issueTime = tokenBean.getIssueTime();
        if (issueTime != null) {
            sQLiteStatement.bindString(17, issueTime);
        }
        String totalSupply = tokenBean.getTotalSupply();
        if (totalSupply != null) {
            sQLiteStatement.bindString(18, totalSupply);
        }
        String address = tokenBean.getAddress();
        if (address != null) {
            sQLiteStatement.bindString(19, address);
        }
        String issueAddress = tokenBean.getIssueAddress();
        if (issueAddress != null) {
            sQLiteStatement.bindString(20, issueAddress);
        }
        sQLiteStatement.bindLong(21, tokenBean.getIspendinguploading());
        sQLiteStatement.bindDouble(22, tokenBean.getPrice());
        sQLiteStatement.bindLong(23, tokenBean.getTop());
        sQLiteStatement.bindLong(24, tokenBean.getDoDb());
        sQLiteStatement.bindLong(25, tokenBean.getMarketId());
        sQLiteStatement.bindLong(26, tokenBean.getMarketType());
        sQLiteStatement.bindLong(27, tokenBean.getTime());
        String ieoUrl = tokenBean.getIeoUrl();
        if (ieoUrl != null) {
            sQLiteStatement.bindString(28, ieoUrl);
        }
        String ieoUrlZh = tokenBean.getIeoUrlZh();
        if (ieoUrlZh != null) {
            sQLiteStatement.bindString(29, ieoUrlZh);
        }
        sQLiteStatement.bindDouble(30, tokenBean.getYesterdayEarnings());
        sQLiteStatement.bindDouble(31, tokenBean.getTotalEarnings());
        sQLiteStatement.bindDouble(32, tokenBean.getTotalBalance());
        sQLiteStatement.bindLong(33, tokenBean.getInMainChain() ? 1L : 0L);
        sQLiteStatement.bindLong(34, tokenBean.getInSideChain() ? 1L : 0L);
        sQLiteStatement.bindLong(35, tokenBean.getIsShield() ? 1L : 0L);
        sQLiteStatement.bindLong(36, tokenBean.getIsMainChain() ? 1L : 0L);
        String nodeId = tokenBean.getNodeId();
        if (nodeId != null) {
            sQLiteStatement.bindString(37, nodeId);
        }
        sQLiteStatement.bindLong(38, tokenBean.getIsInAssets() ? 1L : 0L);
        sQLiteStatement.bindLong(39, tokenBean.getUsageType());
        sQLiteStatement.bindLong(40, tokenBean.getWalletType());
        sQLiteStatement.bindLong(41, tokenBean.getTokenStatus());
        sQLiteStatement.bindLong(42, tokenBean.getTransferStatus() ? 1L : 0L);
        String usdPrice = tokenBean.getUsdPrice();
        if (usdPrice != null) {
            sQLiteStatement.bindString(43, usdPrice);
        }
        String cnyPrice = tokenBean.getCnyPrice();
        if (cnyPrice != null) {
            sQLiteStatement.bindString(44, cnyPrice);
        }
        String national = tokenBean.getNational();
        if (national != null) {
            sQLiteStatement.bindString(45, national);
        }
        sQLiteStatement.bindLong(46, tokenBean.getDefiType());
    }

    @Override
    public Long readKey(Cursor cursor, int i) {
        if (cursor.isNull(i)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i));
    }

    @Override
    public TokenBean readEntity(Cursor cursor, int i) {
        Long valueOf = cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i));
        int i2 = cursor.getInt(i + 1);
        int i3 = cursor.getInt(i + 2);
        int i4 = i + 3;
        String string = cursor.isNull(i4) ? null : cursor.getString(i4);
        int i5 = i + 4;
        String string2 = cursor.isNull(i5) ? null : cursor.getString(i5);
        int i6 = i + 5;
        String string3 = cursor.isNull(i6) ? null : cursor.getString(i6);
        int i7 = i + 6;
        String string4 = cursor.isNull(i7) ? null : cursor.getString(i7);
        int i8 = i + 7;
        String string5 = cursor.isNull(i8) ? null : cursor.getString(i8);
        double d = cursor.getDouble(i + 8);
        int i9 = i + 9;
        String string6 = cursor.isNull(i9) ? null : cursor.getString(i9);
        double d2 = cursor.getDouble(i + 10);
        int i10 = i + 11;
        String string7 = cursor.isNull(i10) ? null : cursor.getString(i10);
        int i11 = i + 12;
        String string8 = cursor.isNull(i11) ? null : cursor.getString(i11);
        int i12 = cursor.getInt(i + 13);
        int i13 = i + 14;
        String string9 = cursor.isNull(i13) ? null : cursor.getString(i13);
        int i14 = i + 15;
        String string10 = cursor.isNull(i14) ? null : cursor.getString(i14);
        int i15 = i + 16;
        String string11 = cursor.isNull(i15) ? null : cursor.getString(i15);
        int i16 = i + 17;
        String string12 = cursor.isNull(i16) ? null : cursor.getString(i16);
        int i17 = i + 18;
        String string13 = cursor.isNull(i17) ? null : cursor.getString(i17);
        int i18 = i + 19;
        String string14 = cursor.isNull(i18) ? null : cursor.getString(i18);
        int i19 = cursor.getInt(i + 20);
        double d3 = cursor.getDouble(i + 21);
        int i20 = cursor.getInt(i + 22);
        int i21 = cursor.getInt(i + 23);
        int i22 = cursor.getInt(i + 24);
        int i23 = cursor.getInt(i + 25);
        int i24 = cursor.getInt(i + 26);
        int i25 = i + 27;
        String string15 = cursor.isNull(i25) ? null : cursor.getString(i25);
        int i26 = i + 28;
        String string16 = cursor.isNull(i26) ? null : cursor.getString(i26);
        double d4 = cursor.getDouble(i + 29);
        double d5 = cursor.getDouble(i + 30);
        double d6 = cursor.getDouble(i + 31);
        boolean z = cursor.getShort(i + 32) != 0;
        boolean z2 = cursor.getShort(i + 33) != 0;
        boolean z3 = cursor.getShort(i + 34) != 0;
        boolean z4 = cursor.getShort(i + 35) != 0;
        int i27 = i + 36;
        String string17 = cursor.isNull(i27) ? null : cursor.getString(i27);
        boolean z5 = cursor.getShort(i + 37) != 0;
        int i28 = cursor.getInt(i + 38);
        int i29 = cursor.getInt(i + 39);
        int i30 = cursor.getInt(i + 40);
        boolean z6 = cursor.getShort(i + 41) != 0;
        int i31 = i + 42;
        String string18 = cursor.isNull(i31) ? null : cursor.getString(i31);
        int i32 = i + 43;
        String string19 = cursor.isNull(i32) ? null : cursor.getString(i32);
        int i33 = i + 44;
        return new TokenBean(valueOf, i2, i3, string, string2, string3, string4, string5, d, string6, d2, string7, string8, i12, string9, string10, string11, string12, string13, string14, i19, d3, i20, i21, i22, i23, i24, string15, string16, d4, d5, d6, z, z2, z3, z4, string17, z5, i28, i29, i30, z6, string18, string19, cursor.isNull(i33) ? null : cursor.getString(i33), cursor.getInt(i + 45));
    }

    @Override
    public void readEntity(Cursor cursor, TokenBean tokenBean, int i) {
        tokenBean.setTokenId(cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i)));
        tokenBean.setType(cursor.getInt(i + 1));
        tokenBean.setIsOfficial(cursor.getInt(i + 2));
        int i2 = i + 3;
        tokenBean.setName(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 4;
        tokenBean.setShortName(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 5;
        tokenBean.setId(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 6;
        tokenBean.setContractAddress(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 7;
        tokenBean.setMaincontractAddress(cursor.isNull(i6) ? null : cursor.getString(i6));
        tokenBean.setBalance(cursor.getDouble(i + 8));
        int i7 = i + 9;
        tokenBean.setBalanceStr(cursor.isNull(i7) ? null : cursor.getString(i7));
        tokenBean.setTrxCount(cursor.getDouble(i + 10));
        int i8 = i + 11;
        tokenBean.setDescription(cursor.isNull(i8) ? null : cursor.getString(i8));
        int i9 = i + 12;
        tokenBean.setLogoUrl(cursor.isNull(i9) ? null : cursor.getString(i9));
        tokenBean.setPrecision(cursor.getInt(i + 13));
        int i10 = i + 14;
        tokenBean.setHomePage(cursor.isNull(i10) ? null : cursor.getString(i10));
        int i11 = i + 15;
        tokenBean.setTokenDesc(cursor.isNull(i11) ? null : cursor.getString(i11));
        int i12 = i + 16;
        tokenBean.setIssueTime(cursor.isNull(i12) ? null : cursor.getString(i12));
        int i13 = i + 17;
        tokenBean.setTotalSupply(cursor.isNull(i13) ? null : cursor.getString(i13));
        int i14 = i + 18;
        tokenBean.setAddress(cursor.isNull(i14) ? null : cursor.getString(i14));
        int i15 = i + 19;
        tokenBean.setIssueAddress(cursor.isNull(i15) ? null : cursor.getString(i15));
        tokenBean.setIspendinguploading(cursor.getInt(i + 20));
        tokenBean.setPrice(cursor.getDouble(i + 21));
        tokenBean.setTop(cursor.getInt(i + 22));
        tokenBean.setDoDb(cursor.getInt(i + 23));
        tokenBean.setMarketId(cursor.getInt(i + 24));
        tokenBean.setMarketType(cursor.getInt(i + 25));
        tokenBean.setTime(cursor.getInt(i + 26));
        int i16 = i + 27;
        tokenBean.setIeoUrl(cursor.isNull(i16) ? null : cursor.getString(i16));
        int i17 = i + 28;
        tokenBean.setIeoUrlZh(cursor.isNull(i17) ? null : cursor.getString(i17));
        tokenBean.setYesterdayEarnings(cursor.getDouble(i + 29));
        tokenBean.setTotalEarnings(cursor.getDouble(i + 30));
        tokenBean.setTotalBalance(cursor.getDouble(i + 31));
        tokenBean.setInMainChain(cursor.getShort(i + 32) != 0);
        tokenBean.setInSideChain(cursor.getShort(i + 33) != 0);
        tokenBean.setIsShield(cursor.getShort(i + 34) != 0);
        tokenBean.setIsMainChain(cursor.getShort(i + 35) != 0);
        int i18 = i + 36;
        tokenBean.setNodeId(cursor.isNull(i18) ? null : cursor.getString(i18));
        tokenBean.setIsInAssets(cursor.getShort(i + 37) != 0);
        tokenBean.setUsageType(cursor.getInt(i + 38));
        tokenBean.setWalletType(cursor.getInt(i + 39));
        tokenBean.setTokenStatus(cursor.getInt(i + 40));
        tokenBean.setTransferStatus(cursor.getShort(i + 41) != 0);
        int i19 = i + 42;
        tokenBean.setUsdPrice(cursor.isNull(i19) ? null : cursor.getString(i19));
        int i20 = i + 43;
        tokenBean.setCnyPrice(cursor.isNull(i20) ? null : cursor.getString(i20));
        int i21 = i + 44;
        tokenBean.setNational(cursor.isNull(i21) ? null : cursor.getString(i21));
        tokenBean.setDefiType(cursor.getInt(i + 45));
    }

    @Override
    public final Long updateKeyAfterInsert(TokenBean tokenBean, long j) {
        tokenBean.setTokenId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override
    public Long getKey(TokenBean tokenBean) {
        if (tokenBean != null) {
            return tokenBean.getTokenId();
        }
        return null;
    }

    @Override
    public boolean hasKey(TokenBean tokenBean) {
        return tokenBean.getTokenId() != null;
    }
}
