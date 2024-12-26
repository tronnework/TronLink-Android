package com.tron.wallet.db.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.tron.wallet.business.addassets2.bean.nft.NftItemInfo;
import com.tron.wallet.business.nft.contract.Contract;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
public class NftItemInfoDao extends AbstractDao<NftItemInfo, Long> {
    public static final String TABLENAME = "NFT_ITEM_INFO";

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property TokenAddress = new Property(1, String.class, "tokenAddress", false, "TOKEN_ADDRESS");
        public static final Property AssetId = new Property(2, String.class, "assetId", false, "ASSET_ID");
        public static final Property Name = new Property(3, String.class, AppMeasurementSdk.ConditionalUserProperty.NAME, false, "NAME");
        public static final Property Intro = new Property(4, String.class, "intro", false, "INTRO");
        public static final Property Price = new Property(5, String.class, FirebaseAnalytics.Param.PRICE, false, "PRICE");
        public static final Property LogoUrl = new Property(6, String.class, "logoUrl", false, "LOGO_URL");
        public static final Property ImageUrl = new Property(7, String.class, "imageUrl", false, "IMAGE_URL");
        public static final Property AssetUri = new Property(8, String.class, "assetUri", false, "ASSET_URI");
        public static final Property WalletAddress = new Property(9, String.class, "walletAddress", false, Contract.View.KEY_WALLET_ADDRESS);
    }

    @Override
    public final boolean isEntityUpdateable() {
        return true;
    }

    public NftItemInfoDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public NftItemInfoDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"NFT_ITEM_INFO\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"TOKEN_ADDRESS\" TEXT,\"ASSET_ID\" TEXT,\"NAME\" TEXT,\"INTRO\" TEXT,\"PRICE\" TEXT,\"LOGO_URL\" TEXT,\"IMAGE_URL\" TEXT,\"ASSET_URI\" TEXT,\"WALLET_ADDRESS\" TEXT);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"NFT_ITEM_INFO\"");
        database.execSQL(sb.toString());
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, NftItemInfo nftItemInfo) {
        databaseStatement.clearBindings();
        Long id = nftItemInfo.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        String tokenAddress = nftItemInfo.getTokenAddress();
        if (tokenAddress != null) {
            databaseStatement.bindString(2, tokenAddress);
        }
        String assetId = nftItemInfo.getAssetId();
        if (assetId != null) {
            databaseStatement.bindString(3, assetId);
        }
        String name = nftItemInfo.getName();
        if (name != null) {
            databaseStatement.bindString(4, name);
        }
        String intro = nftItemInfo.getIntro();
        if (intro != null) {
            databaseStatement.bindString(5, intro);
        }
        String price = nftItemInfo.getPrice();
        if (price != null) {
            databaseStatement.bindString(6, price);
        }
        String logoUrl = nftItemInfo.getLogoUrl();
        if (logoUrl != null) {
            databaseStatement.bindString(7, logoUrl);
        }
        String imageUrl = nftItemInfo.getImageUrl();
        if (imageUrl != null) {
            databaseStatement.bindString(8, imageUrl);
        }
        String assetUri = nftItemInfo.getAssetUri();
        if (assetUri != null) {
            databaseStatement.bindString(9, assetUri);
        }
        String walletAddress = nftItemInfo.getWalletAddress();
        if (walletAddress != null) {
            databaseStatement.bindString(10, walletAddress);
        }
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, NftItemInfo nftItemInfo) {
        sQLiteStatement.clearBindings();
        Long id = nftItemInfo.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String tokenAddress = nftItemInfo.getTokenAddress();
        if (tokenAddress != null) {
            sQLiteStatement.bindString(2, tokenAddress);
        }
        String assetId = nftItemInfo.getAssetId();
        if (assetId != null) {
            sQLiteStatement.bindString(3, assetId);
        }
        String name = nftItemInfo.getName();
        if (name != null) {
            sQLiteStatement.bindString(4, name);
        }
        String intro = nftItemInfo.getIntro();
        if (intro != null) {
            sQLiteStatement.bindString(5, intro);
        }
        String price = nftItemInfo.getPrice();
        if (price != null) {
            sQLiteStatement.bindString(6, price);
        }
        String logoUrl = nftItemInfo.getLogoUrl();
        if (logoUrl != null) {
            sQLiteStatement.bindString(7, logoUrl);
        }
        String imageUrl = nftItemInfo.getImageUrl();
        if (imageUrl != null) {
            sQLiteStatement.bindString(8, imageUrl);
        }
        String assetUri = nftItemInfo.getAssetUri();
        if (assetUri != null) {
            sQLiteStatement.bindString(9, assetUri);
        }
        String walletAddress = nftItemInfo.getWalletAddress();
        if (walletAddress != null) {
            sQLiteStatement.bindString(10, walletAddress);
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
    public NftItemInfo readEntity(Cursor cursor, int i) {
        Long valueOf = cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i));
        int i2 = i + 1;
        String string = cursor.isNull(i2) ? null : cursor.getString(i2);
        int i3 = i + 2;
        String string2 = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 3;
        String string3 = cursor.isNull(i4) ? null : cursor.getString(i4);
        int i5 = i + 4;
        String string4 = cursor.isNull(i5) ? null : cursor.getString(i5);
        int i6 = i + 5;
        String string5 = cursor.isNull(i6) ? null : cursor.getString(i6);
        int i7 = i + 6;
        String string6 = cursor.isNull(i7) ? null : cursor.getString(i7);
        int i8 = i + 7;
        String string7 = cursor.isNull(i8) ? null : cursor.getString(i8);
        int i9 = i + 8;
        int i10 = i + 9;
        return new NftItemInfo(valueOf, string, string2, string3, string4, string5, string6, string7, cursor.isNull(i9) ? null : cursor.getString(i9), cursor.isNull(i10) ? null : cursor.getString(i10));
    }

    @Override
    public void readEntity(Cursor cursor, NftItemInfo nftItemInfo, int i) {
        nftItemInfo.setId(cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i)));
        int i2 = i + 1;
        nftItemInfo.setTokenAddress(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 2;
        nftItemInfo.setAssetId(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 3;
        nftItemInfo.setName(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 4;
        nftItemInfo.setIntro(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 5;
        nftItemInfo.setPrice(cursor.isNull(i6) ? null : cursor.getString(i6));
        int i7 = i + 6;
        nftItemInfo.setLogoUrl(cursor.isNull(i7) ? null : cursor.getString(i7));
        int i8 = i + 7;
        nftItemInfo.setImageUrl(cursor.isNull(i8) ? null : cursor.getString(i8));
        int i9 = i + 8;
        nftItemInfo.setAssetUri(cursor.isNull(i9) ? null : cursor.getString(i9));
        int i10 = i + 9;
        nftItemInfo.setWalletAddress(cursor.isNull(i10) ? null : cursor.getString(i10));
    }

    @Override
    public final Long updateKeyAfterInsert(NftItemInfo nftItemInfo, long j) {
        nftItemInfo.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override
    public Long getKey(NftItemInfo nftItemInfo) {
        if (nftItemInfo != null) {
            return nftItemInfo.getId();
        }
        return null;
    }

    @Override
    public boolean hasKey(NftItemInfo nftItemInfo) {
        return nftItemInfo.getId() != null;
    }
}
