package com.tron.wallet.db.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.tron.wallet.business.nft.contract.Contract;
import com.tron.wallet.business.security.tokencheck.bean.TokenCheckBean;
import com.tron.wallet.common.config.TronConfig;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
public class TokenCheckBeanDao extends AbstractDao<TokenCheckBean, Long> {
    public static final String TABLENAME = "TOKEN_CHECK_BEAN";

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Type = new Property(1, Integer.class, "type", false, "TYPE");
        public static final Property Name = new Property(2, String.class, AppMeasurementSdk.ConditionalUserProperty.NAME, false, "NAME");
        public static final Property ShortName = new Property(3, String.class, "shortName", false, Contract.View.KEY_SHORT_NAME);
        public static final Property ContractAddress = new Property(4, String.class, "contractAddress", false, Contract.View.KEY_CONTRACT_ADDRESS);
        public static final Property TokenId = new Property(5, String.class, "tokenId", false, "TOKEN_ID");
        public static final Property BalanceStr = new Property(6, String.class, "balanceStr", false, "BALANCE_STR");
        public static final Property UsdCount = new Property(7, Double.class, "usdCount", false, "USD_COUNT");
        public static final Property CnyCount = new Property(8, Double.class, "cnyCount", false, "CNY_COUNT");
        public static final Property UsdPrice = new Property(9, String.class, "usdPrice", false, "USD_PRICE");
        public static final Property CnyPrice = new Property(10, String.class, "cnyPrice", false, "CNY_PRICE");
        public static final Property LogoUrl = new Property(11, String.class, "logoUrl", false, "LOGO_URL");
        public static final Property Precision = new Property(12, Integer.class, TronConfig.PRECISION, false, "PRECISION");
        public static final Property BlackListType = new Property(13, Integer.class, "blackListType", false, "BLACK_LIST_TYPE");
        public static final Property IncreaseTotalSupply = new Property(14, Integer.class, "increaseTotalSupply", false, "INCREASE_TOTAL_SUPPLY");
        public static final Property OpenSource = new Property(15, Integer.class, "openSource", false, "OPEN_SOURCE");
        public static final Property IsProxy = new Property(16, Integer.class, "isProxy", false, "IS_PROXY");
        public static final Property Level = new Property(17, String.class, FirebaseAnalytics.Param.LEVEL, false, "LEVEL");
    }

    @Override
    public final boolean isEntityUpdateable() {
        return true;
    }

    public TokenCheckBeanDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public TokenCheckBeanDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"TOKEN_CHECK_BEAN\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"TYPE\" INTEGER,\"NAME\" TEXT,\"SHORT_NAME\" TEXT,\"CONTRACT_ADDRESS\" TEXT,\"TOKEN_ID\" TEXT,\"BALANCE_STR\" TEXT,\"USD_COUNT\" REAL,\"CNY_COUNT\" REAL,\"USD_PRICE\" TEXT,\"CNY_PRICE\" TEXT,\"LOGO_URL\" TEXT,\"PRECISION\" INTEGER,\"BLACK_LIST_TYPE\" INTEGER,\"INCREASE_TOTAL_SUPPLY\" INTEGER,\"OPEN_SOURCE\" INTEGER,\"IS_PROXY\" INTEGER,\"LEVEL\" TEXT);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"TOKEN_CHECK_BEAN\"");
        database.execSQL(sb.toString());
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, TokenCheckBean tokenCheckBean) {
        databaseStatement.clearBindings();
        Long id = tokenCheckBean.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        Integer type = tokenCheckBean.getType();
        if (type != null) {
            databaseStatement.bindLong(2, type.intValue());
        }
        String name = tokenCheckBean.getName();
        if (name != null) {
            databaseStatement.bindString(3, name);
        }
        String shortName = tokenCheckBean.getShortName();
        if (shortName != null) {
            databaseStatement.bindString(4, shortName);
        }
        String contractAddress = tokenCheckBean.getContractAddress();
        if (contractAddress != null) {
            databaseStatement.bindString(5, contractAddress);
        }
        String tokenId = tokenCheckBean.getTokenId();
        if (tokenId != null) {
            databaseStatement.bindString(6, tokenId);
        }
        String balanceStr = tokenCheckBean.getBalanceStr();
        if (balanceStr != null) {
            databaseStatement.bindString(7, balanceStr);
        }
        Double usdCount = tokenCheckBean.getUsdCount();
        if (usdCount != null) {
            databaseStatement.bindDouble(8, usdCount.doubleValue());
        }
        Double cnyCount = tokenCheckBean.getCnyCount();
        if (cnyCount != null) {
            databaseStatement.bindDouble(9, cnyCount.doubleValue());
        }
        String usdPrice = tokenCheckBean.getUsdPrice();
        if (usdPrice != null) {
            databaseStatement.bindString(10, usdPrice);
        }
        String cnyPrice = tokenCheckBean.getCnyPrice();
        if (cnyPrice != null) {
            databaseStatement.bindString(11, cnyPrice);
        }
        String logoUrl = tokenCheckBean.getLogoUrl();
        if (logoUrl != null) {
            databaseStatement.bindString(12, logoUrl);
        }
        Integer precision = tokenCheckBean.getPrecision();
        if (precision != null) {
            databaseStatement.bindLong(13, precision.intValue());
        }
        Integer blackListType = tokenCheckBean.getBlackListType();
        if (blackListType != null) {
            databaseStatement.bindLong(14, blackListType.intValue());
        }
        Integer increaseTotalSupply = tokenCheckBean.getIncreaseTotalSupply();
        if (increaseTotalSupply != null) {
            databaseStatement.bindLong(15, increaseTotalSupply.intValue());
        }
        Integer openSource = tokenCheckBean.getOpenSource();
        if (openSource != null) {
            databaseStatement.bindLong(16, openSource.intValue());
        }
        Integer isProxy = tokenCheckBean.getIsProxy();
        if (isProxy != null) {
            databaseStatement.bindLong(17, isProxy.intValue());
        }
        String level = tokenCheckBean.getLevel();
        if (level != null) {
            databaseStatement.bindString(18, level);
        }
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, TokenCheckBean tokenCheckBean) {
        sQLiteStatement.clearBindings();
        Long id = tokenCheckBean.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        Integer type = tokenCheckBean.getType();
        if (type != null) {
            sQLiteStatement.bindLong(2, type.intValue());
        }
        String name = tokenCheckBean.getName();
        if (name != null) {
            sQLiteStatement.bindString(3, name);
        }
        String shortName = tokenCheckBean.getShortName();
        if (shortName != null) {
            sQLiteStatement.bindString(4, shortName);
        }
        String contractAddress = tokenCheckBean.getContractAddress();
        if (contractAddress != null) {
            sQLiteStatement.bindString(5, contractAddress);
        }
        String tokenId = tokenCheckBean.getTokenId();
        if (tokenId != null) {
            sQLiteStatement.bindString(6, tokenId);
        }
        String balanceStr = tokenCheckBean.getBalanceStr();
        if (balanceStr != null) {
            sQLiteStatement.bindString(7, balanceStr);
        }
        Double usdCount = tokenCheckBean.getUsdCount();
        if (usdCount != null) {
            sQLiteStatement.bindDouble(8, usdCount.doubleValue());
        }
        Double cnyCount = tokenCheckBean.getCnyCount();
        if (cnyCount != null) {
            sQLiteStatement.bindDouble(9, cnyCount.doubleValue());
        }
        String usdPrice = tokenCheckBean.getUsdPrice();
        if (usdPrice != null) {
            sQLiteStatement.bindString(10, usdPrice);
        }
        String cnyPrice = tokenCheckBean.getCnyPrice();
        if (cnyPrice != null) {
            sQLiteStatement.bindString(11, cnyPrice);
        }
        String logoUrl = tokenCheckBean.getLogoUrl();
        if (logoUrl != null) {
            sQLiteStatement.bindString(12, logoUrl);
        }
        Integer precision = tokenCheckBean.getPrecision();
        if (precision != null) {
            sQLiteStatement.bindLong(13, precision.intValue());
        }
        Integer blackListType = tokenCheckBean.getBlackListType();
        if (blackListType != null) {
            sQLiteStatement.bindLong(14, blackListType.intValue());
        }
        Integer increaseTotalSupply = tokenCheckBean.getIncreaseTotalSupply();
        if (increaseTotalSupply != null) {
            sQLiteStatement.bindLong(15, increaseTotalSupply.intValue());
        }
        Integer openSource = tokenCheckBean.getOpenSource();
        if (openSource != null) {
            sQLiteStatement.bindLong(16, openSource.intValue());
        }
        Integer isProxy = tokenCheckBean.getIsProxy();
        if (isProxy != null) {
            sQLiteStatement.bindLong(17, isProxy.intValue());
        }
        String level = tokenCheckBean.getLevel();
        if (level != null) {
            sQLiteStatement.bindString(18, level);
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
    public TokenCheckBean readEntity(Cursor cursor, int i) {
        Long valueOf = cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i));
        int i2 = i + 1;
        Integer valueOf2 = cursor.isNull(i2) ? null : Integer.valueOf(cursor.getInt(i2));
        int i3 = i + 2;
        String string = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 3;
        String string2 = cursor.isNull(i4) ? null : cursor.getString(i4);
        int i5 = i + 4;
        String string3 = cursor.isNull(i5) ? null : cursor.getString(i5);
        int i6 = i + 5;
        String string4 = cursor.isNull(i6) ? null : cursor.getString(i6);
        int i7 = i + 6;
        String string5 = cursor.isNull(i7) ? null : cursor.getString(i7);
        int i8 = i + 7;
        Double valueOf3 = cursor.isNull(i8) ? null : Double.valueOf(cursor.getDouble(i8));
        int i9 = i + 8;
        Double valueOf4 = cursor.isNull(i9) ? null : Double.valueOf(cursor.getDouble(i9));
        int i10 = i + 9;
        String string6 = cursor.isNull(i10) ? null : cursor.getString(i10);
        int i11 = i + 10;
        String string7 = cursor.isNull(i11) ? null : cursor.getString(i11);
        int i12 = i + 11;
        String string8 = cursor.isNull(i12) ? null : cursor.getString(i12);
        int i13 = i + 12;
        Integer valueOf5 = cursor.isNull(i13) ? null : Integer.valueOf(cursor.getInt(i13));
        int i14 = i + 13;
        Integer valueOf6 = cursor.isNull(i14) ? null : Integer.valueOf(cursor.getInt(i14));
        int i15 = i + 14;
        Integer valueOf7 = cursor.isNull(i15) ? null : Integer.valueOf(cursor.getInt(i15));
        int i16 = i + 15;
        Integer valueOf8 = cursor.isNull(i16) ? null : Integer.valueOf(cursor.getInt(i16));
        int i17 = i + 16;
        Integer valueOf9 = cursor.isNull(i17) ? null : Integer.valueOf(cursor.getInt(i17));
        int i18 = i + 17;
        return new TokenCheckBean(valueOf, valueOf2, string, string2, string3, string4, string5, valueOf3, valueOf4, string6, string7, string8, valueOf5, valueOf6, valueOf7, valueOf8, valueOf9, cursor.isNull(i18) ? null : cursor.getString(i18));
    }

    @Override
    public void readEntity(Cursor cursor, TokenCheckBean tokenCheckBean, int i) {
        tokenCheckBean.setId(cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i)));
        int i2 = i + 1;
        tokenCheckBean.setType(cursor.isNull(i2) ? null : Integer.valueOf(cursor.getInt(i2)));
        int i3 = i + 2;
        tokenCheckBean.setName(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 3;
        tokenCheckBean.setShortName(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 4;
        tokenCheckBean.setContractAddress(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 5;
        tokenCheckBean.setTokenId(cursor.isNull(i6) ? null : cursor.getString(i6));
        int i7 = i + 6;
        tokenCheckBean.setBalanceStr(cursor.isNull(i7) ? null : cursor.getString(i7));
        int i8 = i + 7;
        tokenCheckBean.setUsdCount(cursor.isNull(i8) ? null : Double.valueOf(cursor.getDouble(i8)));
        int i9 = i + 8;
        tokenCheckBean.setCnyCount(cursor.isNull(i9) ? null : Double.valueOf(cursor.getDouble(i9)));
        int i10 = i + 9;
        tokenCheckBean.setUsdPrice(cursor.isNull(i10) ? null : cursor.getString(i10));
        int i11 = i + 10;
        tokenCheckBean.setCnyPrice(cursor.isNull(i11) ? null : cursor.getString(i11));
        int i12 = i + 11;
        tokenCheckBean.setLogoUrl(cursor.isNull(i12) ? null : cursor.getString(i12));
        int i13 = i + 12;
        tokenCheckBean.setPrecision(cursor.isNull(i13) ? null : Integer.valueOf(cursor.getInt(i13)));
        int i14 = i + 13;
        tokenCheckBean.setBlackListType(cursor.isNull(i14) ? null : Integer.valueOf(cursor.getInt(i14)));
        int i15 = i + 14;
        tokenCheckBean.setIncreaseTotalSupply(cursor.isNull(i15) ? null : Integer.valueOf(cursor.getInt(i15)));
        int i16 = i + 15;
        tokenCheckBean.setOpenSource(cursor.isNull(i16) ? null : Integer.valueOf(cursor.getInt(i16)));
        int i17 = i + 16;
        tokenCheckBean.setIsProxy(cursor.isNull(i17) ? null : Integer.valueOf(cursor.getInt(i17)));
        int i18 = i + 17;
        tokenCheckBean.setLevel(cursor.isNull(i18) ? null : cursor.getString(i18));
    }

    @Override
    public final Long updateKeyAfterInsert(TokenCheckBean tokenCheckBean, long j) {
        tokenCheckBean.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override
    public Long getKey(TokenCheckBean tokenCheckBean) {
        if (tokenCheckBean != null) {
            return tokenCheckBean.getId();
        }
        return null;
    }

    @Override
    public boolean hasKey(TokenCheckBean tokenCheckBean) {
        return tokenCheckBean.getId() != null;
    }
}
