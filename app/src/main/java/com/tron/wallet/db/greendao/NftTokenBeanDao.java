package com.tron.wallet.db.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.tron.wallet.business.addassets2.bean.nft.NftTokenBean;
import com.tron.wallet.business.customtokens.bean.CustomTokenStatus;
import com.tron.wallet.business.nft.contract.Contract;
import com.tron.wallet.common.config.TronConfig;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
public class NftTokenBeanDao extends AbstractDao<NftTokenBean, Long> {
    public static final String TABLENAME = "NFT_TOKEN_BEAN";

    public static class Properties {
        public static final Property Idx = new Property(0, Long.class, "idx", true, "_id");
        public static final Property Type = new Property(1, Integer.TYPE, "type", false, "TYPE");
        public static final Property Top = new Property(2, Integer.TYPE, "top", false, "TOP");
        public static final Property IsOfficial = new Property(3, Integer.TYPE, "isOfficial", false, "IS_OFFICIAL");
        public static final Property Name = new Property(4, String.class, AppMeasurementSdk.ConditionalUserProperty.NAME, false, "NAME");
        public static final Property ShortName = new Property(5, String.class, "shortName", false, Contract.View.KEY_SHORT_NAME);
        public static final Property Id = new Property(6, String.class, "id", false, TronConfig.ID);
        public static final Property ContractAddress = new Property(7, String.class, "contractAddress", false, Contract.View.KEY_CONTRACT_ADDRESS);
        public static final Property Count = new Property(8, Long.TYPE, "count", false, "COUNT");
        public static final Property LogoUrl = new Property(9, String.class, "logoUrl", false, "LOGO_URL");
        public static final Property InMainChain = new Property(10, Boolean.TYPE, "inMainChain", false, "IN_MAIN_CHAIN");
        public static final Property InSideChain = new Property(11, Boolean.TYPE, "inSideChain", false, "IN_SIDE_CHAIN");
        public static final Property MaincontractAddress = new Property(12, String.class, "maincontractAddress", false, "MAINCONTRACT_ADDRESS");
        public static final Property HomePage = new Property(13, String.class, Contract.View.KEY_HOME_PAGE, false, "HOME_PAGE");
        public static final Property TokenDesc = new Property(14, String.class, "tokenDesc", false, "TOKEN_DESC");
        public static final Property IssueTime = new Property(15, String.class, "issueTime", false, "ISSUE_TIME");
        public static final Property IssueAddress = new Property(16, String.class, "issueAddress", false, "ISSUE_ADDRESS");
        public static final Property TotalSupply = new Property(17, String.class, CustomTokenStatus.TOTAL_SUPPLY, false, "TOTAL_SUPPLY");
        public static final Property MarketId = new Property(18, Integer.TYPE, "marketId", false, "MARKET_ID");
        public static final Property WalletAddress = new Property(19, String.class, "walletAddress", false, Contract.View.KEY_WALLET_ADDRESS);
        public static final Property DoDb = new Property(20, Integer.TYPE, "doDb", false, "DO_DB");
        public static final Property TokenStatus = new Property(21, Integer.TYPE, "tokenStatus", false, "TOKEN_STATUS");
        public static final Property TransferStatus = new Property(22, Boolean.TYPE, "transferStatus", false, "TRANSFER_STATUS");
    }

    @Override
    public final boolean isEntityUpdateable() {
        return true;
    }

    public NftTokenBeanDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public NftTokenBeanDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"NFT_TOKEN_BEAN\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"TYPE\" INTEGER NOT NULL ,\"TOP\" INTEGER NOT NULL ,\"IS_OFFICIAL\" INTEGER NOT NULL ,\"NAME\" TEXT,\"SHORT_NAME\" TEXT,\"ID\" TEXT,\"CONTRACT_ADDRESS\" TEXT,\"COUNT\" INTEGER NOT NULL ,\"LOGO_URL\" TEXT,\"IN_MAIN_CHAIN\" INTEGER NOT NULL ,\"IN_SIDE_CHAIN\" INTEGER NOT NULL ,\"MAINCONTRACT_ADDRESS\" TEXT,\"HOME_PAGE\" TEXT,\"TOKEN_DESC\" TEXT,\"ISSUE_TIME\" TEXT,\"ISSUE_ADDRESS\" TEXT,\"TOTAL_SUPPLY\" TEXT,\"MARKET_ID\" INTEGER NOT NULL ,\"WALLET_ADDRESS\" TEXT,\"DO_DB\" INTEGER NOT NULL ,\"TOKEN_STATUS\" INTEGER NOT NULL ,\"TRANSFER_STATUS\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"NFT_TOKEN_BEAN\"");
        database.execSQL(sb.toString());
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, NftTokenBean nftTokenBean) {
        databaseStatement.clearBindings();
        Long idx = nftTokenBean.getIdx();
        if (idx != null) {
            databaseStatement.bindLong(1, idx.longValue());
        }
        databaseStatement.bindLong(2, nftTokenBean.getType());
        databaseStatement.bindLong(3, nftTokenBean.getTop());
        databaseStatement.bindLong(4, nftTokenBean.getIsOfficial());
        String name = nftTokenBean.getName();
        if (name != null) {
            databaseStatement.bindString(5, name);
        }
        String shortName = nftTokenBean.getShortName();
        if (shortName != null) {
            databaseStatement.bindString(6, shortName);
        }
        String id = nftTokenBean.getId();
        if (id != null) {
            databaseStatement.bindString(7, id);
        }
        String contractAddress = nftTokenBean.getContractAddress();
        if (contractAddress != null) {
            databaseStatement.bindString(8, contractAddress);
        }
        databaseStatement.bindLong(9, nftTokenBean.getCount());
        String logoUrl = nftTokenBean.getLogoUrl();
        if (logoUrl != null) {
            databaseStatement.bindString(10, logoUrl);
        }
        databaseStatement.bindLong(11, nftTokenBean.getInMainChain() ? 1L : 0L);
        databaseStatement.bindLong(12, nftTokenBean.getInSideChain() ? 1L : 0L);
        String maincontractAddress = nftTokenBean.getMaincontractAddress();
        if (maincontractAddress != null) {
            databaseStatement.bindString(13, maincontractAddress);
        }
        String homePage = nftTokenBean.getHomePage();
        if (homePage != null) {
            databaseStatement.bindString(14, homePage);
        }
        String tokenDesc = nftTokenBean.getTokenDesc();
        if (tokenDesc != null) {
            databaseStatement.bindString(15, tokenDesc);
        }
        String issueTime = nftTokenBean.getIssueTime();
        if (issueTime != null) {
            databaseStatement.bindString(16, issueTime);
        }
        String issueAddress = nftTokenBean.getIssueAddress();
        if (issueAddress != null) {
            databaseStatement.bindString(17, issueAddress);
        }
        String totalSupply = nftTokenBean.getTotalSupply();
        if (totalSupply != null) {
            databaseStatement.bindString(18, totalSupply);
        }
        databaseStatement.bindLong(19, nftTokenBean.getMarketId());
        String walletAddress = nftTokenBean.getWalletAddress();
        if (walletAddress != null) {
            databaseStatement.bindString(20, walletAddress);
        }
        databaseStatement.bindLong(21, nftTokenBean.getDoDb());
        databaseStatement.bindLong(22, nftTokenBean.getTokenStatus());
        databaseStatement.bindLong(23, nftTokenBean.getTransferStatus() ? 1L : 0L);
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, NftTokenBean nftTokenBean) {
        sQLiteStatement.clearBindings();
        Long idx = nftTokenBean.getIdx();
        if (idx != null) {
            sQLiteStatement.bindLong(1, idx.longValue());
        }
        sQLiteStatement.bindLong(2, nftTokenBean.getType());
        sQLiteStatement.bindLong(3, nftTokenBean.getTop());
        sQLiteStatement.bindLong(4, nftTokenBean.getIsOfficial());
        String name = nftTokenBean.getName();
        if (name != null) {
            sQLiteStatement.bindString(5, name);
        }
        String shortName = nftTokenBean.getShortName();
        if (shortName != null) {
            sQLiteStatement.bindString(6, shortName);
        }
        String id = nftTokenBean.getId();
        if (id != null) {
            sQLiteStatement.bindString(7, id);
        }
        String contractAddress = nftTokenBean.getContractAddress();
        if (contractAddress != null) {
            sQLiteStatement.bindString(8, contractAddress);
        }
        sQLiteStatement.bindLong(9, nftTokenBean.getCount());
        String logoUrl = nftTokenBean.getLogoUrl();
        if (logoUrl != null) {
            sQLiteStatement.bindString(10, logoUrl);
        }
        sQLiteStatement.bindLong(11, nftTokenBean.getInMainChain() ? 1L : 0L);
        sQLiteStatement.bindLong(12, nftTokenBean.getInSideChain() ? 1L : 0L);
        String maincontractAddress = nftTokenBean.getMaincontractAddress();
        if (maincontractAddress != null) {
            sQLiteStatement.bindString(13, maincontractAddress);
        }
        String homePage = nftTokenBean.getHomePage();
        if (homePage != null) {
            sQLiteStatement.bindString(14, homePage);
        }
        String tokenDesc = nftTokenBean.getTokenDesc();
        if (tokenDesc != null) {
            sQLiteStatement.bindString(15, tokenDesc);
        }
        String issueTime = nftTokenBean.getIssueTime();
        if (issueTime != null) {
            sQLiteStatement.bindString(16, issueTime);
        }
        String issueAddress = nftTokenBean.getIssueAddress();
        if (issueAddress != null) {
            sQLiteStatement.bindString(17, issueAddress);
        }
        String totalSupply = nftTokenBean.getTotalSupply();
        if (totalSupply != null) {
            sQLiteStatement.bindString(18, totalSupply);
        }
        sQLiteStatement.bindLong(19, nftTokenBean.getMarketId());
        String walletAddress = nftTokenBean.getWalletAddress();
        if (walletAddress != null) {
            sQLiteStatement.bindString(20, walletAddress);
        }
        sQLiteStatement.bindLong(21, nftTokenBean.getDoDb());
        sQLiteStatement.bindLong(22, nftTokenBean.getTokenStatus());
        sQLiteStatement.bindLong(23, nftTokenBean.getTransferStatus() ? 1L : 0L);
    }

    @Override
    public Long readKey(Cursor cursor, int i) {
        if (cursor.isNull(i)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i));
    }

    @Override
    public NftTokenBean readEntity(Cursor cursor, int i) {
        Long valueOf = cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i));
        int i2 = cursor.getInt(i + 1);
        int i3 = cursor.getInt(i + 2);
        int i4 = cursor.getInt(i + 3);
        int i5 = i + 4;
        String string = cursor.isNull(i5) ? null : cursor.getString(i5);
        int i6 = i + 5;
        String string2 = cursor.isNull(i6) ? null : cursor.getString(i6);
        int i7 = i + 6;
        String string3 = cursor.isNull(i7) ? null : cursor.getString(i7);
        int i8 = i + 7;
        String string4 = cursor.isNull(i8) ? null : cursor.getString(i8);
        long j = cursor.getLong(i + 8);
        int i9 = i + 9;
        String string5 = cursor.isNull(i9) ? null : cursor.getString(i9);
        boolean z = cursor.getShort(i + 10) != 0;
        boolean z2 = cursor.getShort(i + 11) != 0;
        int i10 = i + 12;
        String string6 = cursor.isNull(i10) ? null : cursor.getString(i10);
        int i11 = i + 13;
        String string7 = cursor.isNull(i11) ? null : cursor.getString(i11);
        int i12 = i + 14;
        String string8 = cursor.isNull(i12) ? null : cursor.getString(i12);
        int i13 = i + 15;
        String string9 = cursor.isNull(i13) ? null : cursor.getString(i13);
        int i14 = i + 16;
        String string10 = cursor.isNull(i14) ? null : cursor.getString(i14);
        int i15 = i + 17;
        String string11 = cursor.isNull(i15) ? null : cursor.getString(i15);
        int i16 = i + 19;
        return new NftTokenBean(valueOf, i2, i3, i4, string, string2, string3, string4, j, string5, z, z2, string6, string7, string8, string9, string10, string11, cursor.getInt(i + 18), cursor.isNull(i16) ? null : cursor.getString(i16), cursor.getInt(i + 20), cursor.getInt(i + 21), cursor.getShort(i + 22) != 0);
    }

    @Override
    public void readEntity(Cursor cursor, NftTokenBean nftTokenBean, int i) {
        nftTokenBean.setIdx(cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i)));
        nftTokenBean.setType(cursor.getInt(i + 1));
        nftTokenBean.setTop(cursor.getInt(i + 2));
        nftTokenBean.setIsOfficial(cursor.getInt(i + 3));
        int i2 = i + 4;
        nftTokenBean.setName(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 5;
        nftTokenBean.setShortName(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 6;
        nftTokenBean.setId(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 7;
        nftTokenBean.setContractAddress(cursor.isNull(i5) ? null : cursor.getString(i5));
        nftTokenBean.setCount(cursor.getLong(i + 8));
        int i6 = i + 9;
        nftTokenBean.setLogoUrl(cursor.isNull(i6) ? null : cursor.getString(i6));
        nftTokenBean.setInMainChain(cursor.getShort(i + 10) != 0);
        nftTokenBean.setInSideChain(cursor.getShort(i + 11) != 0);
        int i7 = i + 12;
        nftTokenBean.setMaincontractAddress(cursor.isNull(i7) ? null : cursor.getString(i7));
        int i8 = i + 13;
        nftTokenBean.setHomePage(cursor.isNull(i8) ? null : cursor.getString(i8));
        int i9 = i + 14;
        nftTokenBean.setTokenDesc(cursor.isNull(i9) ? null : cursor.getString(i9));
        int i10 = i + 15;
        nftTokenBean.setIssueTime(cursor.isNull(i10) ? null : cursor.getString(i10));
        int i11 = i + 16;
        nftTokenBean.setIssueAddress(cursor.isNull(i11) ? null : cursor.getString(i11));
        int i12 = i + 17;
        nftTokenBean.setTotalSupply(cursor.isNull(i12) ? null : cursor.getString(i12));
        nftTokenBean.setMarketId(cursor.getInt(i + 18));
        int i13 = i + 19;
        nftTokenBean.setWalletAddress(cursor.isNull(i13) ? null : cursor.getString(i13));
        nftTokenBean.setDoDb(cursor.getInt(i + 20));
        nftTokenBean.setTokenStatus(cursor.getInt(i + 21));
        nftTokenBean.setTransferStatus(cursor.getShort(i + 22) != 0);
    }

    @Override
    public final Long updateKeyAfterInsert(NftTokenBean nftTokenBean, long j) {
        nftTokenBean.setIdx(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override
    public Long getKey(NftTokenBean nftTokenBean) {
        if (nftTokenBean != null) {
            return nftTokenBean.getIdx();
        }
        return null;
    }

    @Override
    public boolean hasKey(NftTokenBean nftTokenBean) {
        return nftTokenBean.getIdx() != null;
    }
}
