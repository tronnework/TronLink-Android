package com.tron.wallet.db.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.tron.wallet.business.addassets2.bean.nft.NftTypeInfo;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
public class NftTypeInfoDao extends AbstractDao<NftTypeInfo, Void> {
    public static final String TABLENAME = "NFT_TYPE_INFO";

    public static class Properties {
        public static final Property TokenAddress = new Property(0, String.class, "tokenAddress", false, "TOKEN_ADDRESS");
        public static final Property Intro = new Property(1, String.class, "intro", false, "INTRO");
        public static final Property LogoUrl = new Property(2, String.class, "logoUrl", false, "LOGO_URL");
        public static final Property FullName = new Property(3, String.class, "fullName", false, "FULL_NAME");
    }

    @Override
    public Void getKey(NftTypeInfo nftTypeInfo) {
        return null;
    }

    @Override
    public boolean hasKey(NftTypeInfo nftTypeInfo) {
        return false;
    }

    @Override
    public final boolean isEntityUpdateable() {
        return true;
    }

    @Override
    public Void readKey(Cursor cursor, int i) {
        return null;
    }

    @Override
    public final Void updateKeyAfterInsert(NftTypeInfo nftTypeInfo, long j) {
        return null;
    }

    public NftTypeInfoDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public NftTypeInfoDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"NFT_TYPE_INFO\" (\"TOKEN_ADDRESS\" TEXT,\"INTRO\" TEXT,\"LOGO_URL\" TEXT,\"FULL_NAME\" TEXT);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"NFT_TYPE_INFO\"");
        database.execSQL(sb.toString());
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, NftTypeInfo nftTypeInfo) {
        databaseStatement.clearBindings();
        String tokenAddress = nftTypeInfo.getTokenAddress();
        if (tokenAddress != null) {
            databaseStatement.bindString(1, tokenAddress);
        }
        String intro = nftTypeInfo.getIntro();
        if (intro != null) {
            databaseStatement.bindString(2, intro);
        }
        String logoUrl = nftTypeInfo.getLogoUrl();
        if (logoUrl != null) {
            databaseStatement.bindString(3, logoUrl);
        }
        String fullName = nftTypeInfo.getFullName();
        if (fullName != null) {
            databaseStatement.bindString(4, fullName);
        }
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, NftTypeInfo nftTypeInfo) {
        sQLiteStatement.clearBindings();
        String tokenAddress = nftTypeInfo.getTokenAddress();
        if (tokenAddress != null) {
            sQLiteStatement.bindString(1, tokenAddress);
        }
        String intro = nftTypeInfo.getIntro();
        if (intro != null) {
            sQLiteStatement.bindString(2, intro);
        }
        String logoUrl = nftTypeInfo.getLogoUrl();
        if (logoUrl != null) {
            sQLiteStatement.bindString(3, logoUrl);
        }
        String fullName = nftTypeInfo.getFullName();
        if (fullName != null) {
            sQLiteStatement.bindString(4, fullName);
        }
    }

    @Override
    public NftTypeInfo readEntity(Cursor cursor, int i) {
        String string = cursor.isNull(i) ? null : cursor.getString(i);
        int i2 = i + 1;
        int i3 = i + 2;
        int i4 = i + 3;
        return new NftTypeInfo(string, cursor.isNull(i2) ? null : cursor.getString(i2), cursor.isNull(i3) ? null : cursor.getString(i3), cursor.isNull(i4) ? null : cursor.getString(i4));
    }

    @Override
    public void readEntity(Cursor cursor, NftTypeInfo nftTypeInfo, int i) {
        nftTypeInfo.setTokenAddress(cursor.isNull(i) ? null : cursor.getString(i));
        int i2 = i + 1;
        nftTypeInfo.setIntro(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 2;
        nftTypeInfo.setLogoUrl(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 3;
        nftTypeInfo.setFullName(cursor.isNull(i4) ? null : cursor.getString(i4));
    }
}
