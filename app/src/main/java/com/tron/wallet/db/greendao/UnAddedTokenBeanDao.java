package com.tron.wallet.db.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.tron.wallet.business.customtokens.bean.CustomTokenStatus;
import com.tron.wallet.business.nft.contract.Contract;
import com.tron.wallet.common.bean.token.UnAddedTokenBean;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
public class UnAddedTokenBeanDao extends AbstractDao<UnAddedTokenBean, Long> {
    public static final String TABLENAME = "UN_ADDED_TOKEN_BEAN";

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
        public static final Property TotalSupply = new Property(17, Long.TYPE, CustomTokenStatus.TOTAL_SUPPLY, false, "TOTAL_SUPPLY");
        public static final Property Address = new Property(18, String.class, "address", false, "ADDRESS");
        public static final Property IssueAddress = new Property(19, String.class, "issueAddress", false, "ISSUE_ADDRESS");
        public static final Property Price = new Property(20, Double.TYPE, FirebaseAnalytics.Param.PRICE, false, "PRICE");
        public static final Property MarketId = new Property(21, Integer.TYPE, "marketId", false, "MARKET_ID");
        public static final Property MarketType = new Property(22, Integer.TYPE, "marketType", false, "MARKET_TYPE");
        public static final Property Time = new Property(23, Integer.TYPE, "time", false, "TIME");
        public static final Property IeoUrl = new Property(24, String.class, "ieoUrl", false, "IEO_URL");
        public static final Property IeoUrlZh = new Property(25, String.class, "ieoUrlZh", false, "IEO_URL_ZH");
        public static final Property YesterdayEarnings = new Property(26, Double.TYPE, "yesterdayEarnings", false, "YESTERDAY_EARNINGS");
        public static final Property TotalEarnings = new Property(27, Double.TYPE, "totalEarnings", false, "TOTAL_EARNINGS");
        public static final Property IsMainChain = new Property(28, Boolean.TYPE, "isMainChain", false, "IS_MAIN_CHAIN");
        public static final Property InMainChain = new Property(29, Boolean.TYPE, "inMainChain", false, "IN_MAIN_CHAIN");
        public static final Property InSideChain = new Property(30, Boolean.TYPE, "inSideChain", false, "IN_SIDE_CHAIN");
        public static final Property IsShield = new Property(31, Boolean.TYPE, "isShield", false, "IS_SHIELD");
        public static final Property NodeId = new Property(32, String.class, "nodeId", false, "NODE_ID");
    }

    @Override
    public final boolean isEntityUpdateable() {
        return true;
    }

    public UnAddedTokenBeanDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public UnAddedTokenBeanDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"UN_ADDED_TOKEN_BEAN\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"TYPE\" INTEGER NOT NULL ,\"IS_OFFICIAL\" INTEGER NOT NULL ,\"NAME\" TEXT,\"SHORT_NAME\" TEXT,\"ID\" TEXT,\"CONTRACT_ADDRESS\" TEXT,\"MAINCONTRACT_ADDRESS\" TEXT,\"BALANCE\" REAL NOT NULL ,\"BALANCE_STR\" TEXT,\"TRX_COUNT\" REAL NOT NULL ,\"DESCRIPTION\" TEXT,\"LOGO_URL\" TEXT,\"PRECISION\" INTEGER NOT NULL ,\"HOME_PAGE\" TEXT,\"TOKEN_DESC\" TEXT,\"ISSUE_TIME\" TEXT,\"TOTAL_SUPPLY\" INTEGER NOT NULL ,\"ADDRESS\" TEXT,\"ISSUE_ADDRESS\" TEXT,\"PRICE\" REAL NOT NULL ,\"MARKET_ID\" INTEGER NOT NULL ,\"MARKET_TYPE\" INTEGER NOT NULL ,\"TIME\" INTEGER NOT NULL ,\"IEO_URL\" TEXT,\"IEO_URL_ZH\" TEXT,\"YESTERDAY_EARNINGS\" REAL NOT NULL ,\"TOTAL_EARNINGS\" REAL NOT NULL ,\"IS_MAIN_CHAIN\" INTEGER NOT NULL ,\"IN_MAIN_CHAIN\" INTEGER NOT NULL ,\"IN_SIDE_CHAIN\" INTEGER NOT NULL ,\"IS_SHIELD\" INTEGER NOT NULL ,\"NODE_ID\" TEXT);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"UN_ADDED_TOKEN_BEAN\"");
        database.execSQL(sb.toString());
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, UnAddedTokenBean unAddedTokenBean) {
        databaseStatement.clearBindings();
        Long tokenId = unAddedTokenBean.getTokenId();
        if (tokenId != null) {
            databaseStatement.bindLong(1, tokenId.longValue());
        }
        databaseStatement.bindLong(2, unAddedTokenBean.getType());
        databaseStatement.bindLong(3, unAddedTokenBean.getIsOfficial());
        String name = unAddedTokenBean.getName();
        if (name != null) {
            databaseStatement.bindString(4, name);
        }
        String shortName = unAddedTokenBean.getShortName();
        if (shortName != null) {
            databaseStatement.bindString(5, shortName);
        }
        String id = unAddedTokenBean.getId();
        if (id != null) {
            databaseStatement.bindString(6, id);
        }
        String contractAddress = unAddedTokenBean.getContractAddress();
        if (contractAddress != null) {
            databaseStatement.bindString(7, contractAddress);
        }
        String maincontractAddress = unAddedTokenBean.getMaincontractAddress();
        if (maincontractAddress != null) {
            databaseStatement.bindString(8, maincontractAddress);
        }
        databaseStatement.bindDouble(9, unAddedTokenBean.getBalance());
        String balanceStr = unAddedTokenBean.getBalanceStr();
        if (balanceStr != null) {
            databaseStatement.bindString(10, balanceStr);
        }
        databaseStatement.bindDouble(11, unAddedTokenBean.getTrxCount());
        String description = unAddedTokenBean.getDescription();
        if (description != null) {
            databaseStatement.bindString(12, description);
        }
        String logoUrl = unAddedTokenBean.getLogoUrl();
        if (logoUrl != null) {
            databaseStatement.bindString(13, logoUrl);
        }
        databaseStatement.bindLong(14, unAddedTokenBean.getPrecision());
        String homePage = unAddedTokenBean.getHomePage();
        if (homePage != null) {
            databaseStatement.bindString(15, homePage);
        }
        String tokenDesc = unAddedTokenBean.getTokenDesc();
        if (tokenDesc != null) {
            databaseStatement.bindString(16, tokenDesc);
        }
        String issueTime = unAddedTokenBean.getIssueTime();
        if (issueTime != null) {
            databaseStatement.bindString(17, issueTime);
        }
        databaseStatement.bindLong(18, unAddedTokenBean.getTotalSupply());
        String address = unAddedTokenBean.getAddress();
        if (address != null) {
            databaseStatement.bindString(19, address);
        }
        String issueAddress = unAddedTokenBean.getIssueAddress();
        if (issueAddress != null) {
            databaseStatement.bindString(20, issueAddress);
        }
        databaseStatement.bindDouble(21, unAddedTokenBean.getPrice());
        databaseStatement.bindLong(22, unAddedTokenBean.getMarketId());
        databaseStatement.bindLong(23, unAddedTokenBean.getMarketType());
        databaseStatement.bindLong(24, unAddedTokenBean.getTime());
        String ieoUrl = unAddedTokenBean.getIeoUrl();
        if (ieoUrl != null) {
            databaseStatement.bindString(25, ieoUrl);
        }
        String ieoUrlZh = unAddedTokenBean.getIeoUrlZh();
        if (ieoUrlZh != null) {
            databaseStatement.bindString(26, ieoUrlZh);
        }
        databaseStatement.bindDouble(27, unAddedTokenBean.getYesterdayEarnings());
        databaseStatement.bindDouble(28, unAddedTokenBean.getTotalEarnings());
        databaseStatement.bindLong(29, unAddedTokenBean.getIsMainChain() ? 1L : 0L);
        databaseStatement.bindLong(30, unAddedTokenBean.getInMainChain() ? 1L : 0L);
        databaseStatement.bindLong(31, unAddedTokenBean.getInSideChain() ? 1L : 0L);
        databaseStatement.bindLong(32, unAddedTokenBean.getIsShield() ? 1L : 0L);
        String nodeId = unAddedTokenBean.getNodeId();
        if (nodeId != null) {
            databaseStatement.bindString(33, nodeId);
        }
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, UnAddedTokenBean unAddedTokenBean) {
        sQLiteStatement.clearBindings();
        Long tokenId = unAddedTokenBean.getTokenId();
        if (tokenId != null) {
            sQLiteStatement.bindLong(1, tokenId.longValue());
        }
        sQLiteStatement.bindLong(2, unAddedTokenBean.getType());
        sQLiteStatement.bindLong(3, unAddedTokenBean.getIsOfficial());
        String name = unAddedTokenBean.getName();
        if (name != null) {
            sQLiteStatement.bindString(4, name);
        }
        String shortName = unAddedTokenBean.getShortName();
        if (shortName != null) {
            sQLiteStatement.bindString(5, shortName);
        }
        String id = unAddedTokenBean.getId();
        if (id != null) {
            sQLiteStatement.bindString(6, id);
        }
        String contractAddress = unAddedTokenBean.getContractAddress();
        if (contractAddress != null) {
            sQLiteStatement.bindString(7, contractAddress);
        }
        String maincontractAddress = unAddedTokenBean.getMaincontractAddress();
        if (maincontractAddress != null) {
            sQLiteStatement.bindString(8, maincontractAddress);
        }
        sQLiteStatement.bindDouble(9, unAddedTokenBean.getBalance());
        String balanceStr = unAddedTokenBean.getBalanceStr();
        if (balanceStr != null) {
            sQLiteStatement.bindString(10, balanceStr);
        }
        sQLiteStatement.bindDouble(11, unAddedTokenBean.getTrxCount());
        String description = unAddedTokenBean.getDescription();
        if (description != null) {
            sQLiteStatement.bindString(12, description);
        }
        String logoUrl = unAddedTokenBean.getLogoUrl();
        if (logoUrl != null) {
            sQLiteStatement.bindString(13, logoUrl);
        }
        sQLiteStatement.bindLong(14, unAddedTokenBean.getPrecision());
        String homePage = unAddedTokenBean.getHomePage();
        if (homePage != null) {
            sQLiteStatement.bindString(15, homePage);
        }
        String tokenDesc = unAddedTokenBean.getTokenDesc();
        if (tokenDesc != null) {
            sQLiteStatement.bindString(16, tokenDesc);
        }
        String issueTime = unAddedTokenBean.getIssueTime();
        if (issueTime != null) {
            sQLiteStatement.bindString(17, issueTime);
        }
        sQLiteStatement.bindLong(18, unAddedTokenBean.getTotalSupply());
        String address = unAddedTokenBean.getAddress();
        if (address != null) {
            sQLiteStatement.bindString(19, address);
        }
        String issueAddress = unAddedTokenBean.getIssueAddress();
        if (issueAddress != null) {
            sQLiteStatement.bindString(20, issueAddress);
        }
        sQLiteStatement.bindDouble(21, unAddedTokenBean.getPrice());
        sQLiteStatement.bindLong(22, unAddedTokenBean.getMarketId());
        sQLiteStatement.bindLong(23, unAddedTokenBean.getMarketType());
        sQLiteStatement.bindLong(24, unAddedTokenBean.getTime());
        String ieoUrl = unAddedTokenBean.getIeoUrl();
        if (ieoUrl != null) {
            sQLiteStatement.bindString(25, ieoUrl);
        }
        String ieoUrlZh = unAddedTokenBean.getIeoUrlZh();
        if (ieoUrlZh != null) {
            sQLiteStatement.bindString(26, ieoUrlZh);
        }
        sQLiteStatement.bindDouble(27, unAddedTokenBean.getYesterdayEarnings());
        sQLiteStatement.bindDouble(28, unAddedTokenBean.getTotalEarnings());
        sQLiteStatement.bindLong(29, unAddedTokenBean.getIsMainChain() ? 1L : 0L);
        sQLiteStatement.bindLong(30, unAddedTokenBean.getInMainChain() ? 1L : 0L);
        sQLiteStatement.bindLong(31, unAddedTokenBean.getInSideChain() ? 1L : 0L);
        sQLiteStatement.bindLong(32, unAddedTokenBean.getIsShield() ? 1L : 0L);
        String nodeId = unAddedTokenBean.getNodeId();
        if (nodeId != null) {
            sQLiteStatement.bindString(33, nodeId);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int i) {
        if (cursor.isNull(i)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i));
    }

    @Override
    public UnAddedTokenBean readEntity(Cursor cursor, int i) {
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
        long j = cursor.getLong(i + 17);
        int i16 = i + 18;
        String string12 = cursor.isNull(i16) ? null : cursor.getString(i16);
        int i17 = i + 19;
        String string13 = cursor.isNull(i17) ? null : cursor.getString(i17);
        double d3 = cursor.getDouble(i + 20);
        int i18 = cursor.getInt(i + 21);
        int i19 = cursor.getInt(i + 22);
        int i20 = cursor.getInt(i + 23);
        int i21 = i + 24;
        String string14 = cursor.isNull(i21) ? null : cursor.getString(i21);
        int i22 = i + 25;
        String string15 = cursor.isNull(i22) ? null : cursor.getString(i22);
        int i23 = i + 32;
        return new UnAddedTokenBean(valueOf, i2, i3, string, string2, string3, string4, string5, d, string6, d2, string7, string8, i12, string9, string10, string11, j, string12, string13, d3, i18, i19, i20, string14, string15, cursor.getDouble(i + 26), cursor.getDouble(i + 27), cursor.getShort(i + 28) != 0, cursor.getShort(i + 29) != 0, cursor.getShort(i + 30) != 0, cursor.getShort(i + 31) != 0, cursor.isNull(i23) ? null : cursor.getString(i23));
    }

    @Override
    public void readEntity(Cursor cursor, UnAddedTokenBean unAddedTokenBean, int i) {
        unAddedTokenBean.setTokenId(cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i)));
        unAddedTokenBean.setType(cursor.getInt(i + 1));
        unAddedTokenBean.setIsOfficial(cursor.getInt(i + 2));
        int i2 = i + 3;
        unAddedTokenBean.setName(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 4;
        unAddedTokenBean.setShortName(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 5;
        unAddedTokenBean.setId(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 6;
        unAddedTokenBean.setContractAddress(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 7;
        unAddedTokenBean.setMaincontractAddress(cursor.isNull(i6) ? null : cursor.getString(i6));
        unAddedTokenBean.setBalance(cursor.getDouble(i + 8));
        int i7 = i + 9;
        unAddedTokenBean.setBalanceStr(cursor.isNull(i7) ? null : cursor.getString(i7));
        unAddedTokenBean.setTrxCount(cursor.getDouble(i + 10));
        int i8 = i + 11;
        unAddedTokenBean.setDescription(cursor.isNull(i8) ? null : cursor.getString(i8));
        int i9 = i + 12;
        unAddedTokenBean.setLogoUrl(cursor.isNull(i9) ? null : cursor.getString(i9));
        unAddedTokenBean.setPrecision(cursor.getInt(i + 13));
        int i10 = i + 14;
        unAddedTokenBean.setHomePage(cursor.isNull(i10) ? null : cursor.getString(i10));
        int i11 = i + 15;
        unAddedTokenBean.setTokenDesc(cursor.isNull(i11) ? null : cursor.getString(i11));
        int i12 = i + 16;
        unAddedTokenBean.setIssueTime(cursor.isNull(i12) ? null : cursor.getString(i12));
        unAddedTokenBean.setTotalSupply(cursor.getLong(i + 17));
        int i13 = i + 18;
        unAddedTokenBean.setAddress(cursor.isNull(i13) ? null : cursor.getString(i13));
        int i14 = i + 19;
        unAddedTokenBean.setIssueAddress(cursor.isNull(i14) ? null : cursor.getString(i14));
        unAddedTokenBean.setPrice(cursor.getDouble(i + 20));
        unAddedTokenBean.setMarketId(cursor.getInt(i + 21));
        unAddedTokenBean.setMarketType(cursor.getInt(i + 22));
        unAddedTokenBean.setTime(cursor.getInt(i + 23));
        int i15 = i + 24;
        unAddedTokenBean.setIeoUrl(cursor.isNull(i15) ? null : cursor.getString(i15));
        int i16 = i + 25;
        unAddedTokenBean.setIeoUrlZh(cursor.isNull(i16) ? null : cursor.getString(i16));
        unAddedTokenBean.setYesterdayEarnings(cursor.getDouble(i + 26));
        unAddedTokenBean.setTotalEarnings(cursor.getDouble(i + 27));
        unAddedTokenBean.setIsMainChain(cursor.getShort(i + 28) != 0);
        unAddedTokenBean.setInMainChain(cursor.getShort(i + 29) != 0);
        unAddedTokenBean.setInSideChain(cursor.getShort(i + 30) != 0);
        unAddedTokenBean.setIsShield(cursor.getShort(i + 31) != 0);
        int i17 = i + 32;
        unAddedTokenBean.setNodeId(cursor.isNull(i17) ? null : cursor.getString(i17));
    }

    @Override
    public final Long updateKeyAfterInsert(UnAddedTokenBean unAddedTokenBean, long j) {
        unAddedTokenBean.setTokenId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override
    public Long getKey(UnAddedTokenBean unAddedTokenBean) {
        if (unAddedTokenBean != null) {
            return unAddedTokenBean.getTokenId();
        }
        return null;
    }

    @Override
    public boolean hasKey(UnAddedTokenBean unAddedTokenBean) {
        return unAddedTokenBean.getTokenId() != null;
    }
}
