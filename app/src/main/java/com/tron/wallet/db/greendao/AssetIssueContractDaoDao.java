package com.tron.wallet.db.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.db.bean.AssetIssueContractDao;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
public class AssetIssueContractDaoDao extends AbstractDao<AssetIssueContractDao, Long> {
    public static final String TABLENAME = "ASSET_ISSUE_CONTRACT_DAO";

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Owner_address = new Property(1, String.class, "owner_address", false, "OWNER_ADDRESS");
        public static final Property Name = new Property(2, String.class, AppMeasurementSdk.ConditionalUserProperty.NAME, false, "NAME");
        public static final Property Abbr = new Property(3, String.class, "abbr", false, "ABBR");
        public static final Property Total_supply = new Property(4, String.class, "total_supply", false, "TOTAL_SUPPLY");
        public static final Property Trx_num = new Property(5, String.class, "trx_num", false, "TRX_NUM");
        public static final Property Precision = new Property(6, Integer.TYPE, TronConfig.PRECISION, false, "PRECISION");
        public static final Property Num = new Property(7, String.class, "num", false, "NUM");
        public static final Property Start_time = new Property(8, Long.TYPE, "start_time", false, "START_TIME");
        public static final Property End_time = new Property(9, Long.TYPE, "end_time", false, "END_TIME");
        public static final Property Description = new Property(10, String.class, "description", false, "DESCRIPTION");
        public static final Property Url = new Property(11, String.class, "url", false, "URL");
    }

    @Override
    public final boolean isEntityUpdateable() {
        return true;
    }

    public AssetIssueContractDaoDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public AssetIssueContractDaoDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"ASSET_ISSUE_CONTRACT_DAO\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"OWNER_ADDRESS\" TEXT,\"NAME\" TEXT,\"ABBR\" TEXT,\"TOTAL_SUPPLY\" TEXT,\"TRX_NUM\" TEXT,\"PRECISION\" INTEGER NOT NULL ,\"NUM\" TEXT,\"START_TIME\" INTEGER NOT NULL ,\"END_TIME\" INTEGER NOT NULL ,\"DESCRIPTION\" TEXT,\"URL\" TEXT);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"ASSET_ISSUE_CONTRACT_DAO\"");
        database.execSQL(sb.toString());
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, AssetIssueContractDao assetIssueContractDao) {
        databaseStatement.clearBindings();
        Long id = assetIssueContractDao.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        String owner_address = assetIssueContractDao.getOwner_address();
        if (owner_address != null) {
            databaseStatement.bindString(2, owner_address);
        }
        String name = assetIssueContractDao.getName();
        if (name != null) {
            databaseStatement.bindString(3, name);
        }
        String abbr = assetIssueContractDao.getAbbr();
        if (abbr != null) {
            databaseStatement.bindString(4, abbr);
        }
        String total_supply = assetIssueContractDao.getTotal_supply();
        if (total_supply != null) {
            databaseStatement.bindString(5, total_supply);
        }
        String trx_num = assetIssueContractDao.getTrx_num();
        if (trx_num != null) {
            databaseStatement.bindString(6, trx_num);
        }
        databaseStatement.bindLong(7, assetIssueContractDao.getPrecision());
        String num = assetIssueContractDao.getNum();
        if (num != null) {
            databaseStatement.bindString(8, num);
        }
        databaseStatement.bindLong(9, assetIssueContractDao.getStart_time());
        databaseStatement.bindLong(10, assetIssueContractDao.getEnd_time());
        String description = assetIssueContractDao.getDescription();
        if (description != null) {
            databaseStatement.bindString(11, description);
        }
        String url = assetIssueContractDao.getUrl();
        if (url != null) {
            databaseStatement.bindString(12, url);
        }
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, AssetIssueContractDao assetIssueContractDao) {
        sQLiteStatement.clearBindings();
        Long id = assetIssueContractDao.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String owner_address = assetIssueContractDao.getOwner_address();
        if (owner_address != null) {
            sQLiteStatement.bindString(2, owner_address);
        }
        String name = assetIssueContractDao.getName();
        if (name != null) {
            sQLiteStatement.bindString(3, name);
        }
        String abbr = assetIssueContractDao.getAbbr();
        if (abbr != null) {
            sQLiteStatement.bindString(4, abbr);
        }
        String total_supply = assetIssueContractDao.getTotal_supply();
        if (total_supply != null) {
            sQLiteStatement.bindString(5, total_supply);
        }
        String trx_num = assetIssueContractDao.getTrx_num();
        if (trx_num != null) {
            sQLiteStatement.bindString(6, trx_num);
        }
        sQLiteStatement.bindLong(7, assetIssueContractDao.getPrecision());
        String num = assetIssueContractDao.getNum();
        if (num != null) {
            sQLiteStatement.bindString(8, num);
        }
        sQLiteStatement.bindLong(9, assetIssueContractDao.getStart_time());
        sQLiteStatement.bindLong(10, assetIssueContractDao.getEnd_time());
        String description = assetIssueContractDao.getDescription();
        if (description != null) {
            sQLiteStatement.bindString(11, description);
        }
        String url = assetIssueContractDao.getUrl();
        if (url != null) {
            sQLiteStatement.bindString(12, url);
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
    public AssetIssueContractDao readEntity(Cursor cursor, int i) {
        int i2 = i + 1;
        int i3 = i + 2;
        int i4 = i + 3;
        int i5 = i + 4;
        int i6 = i + 5;
        int i7 = i + 7;
        int i8 = i + 10;
        int i9 = i + 11;
        return new AssetIssueContractDao(cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i)), cursor.isNull(i2) ? null : cursor.getString(i2), cursor.isNull(i3) ? null : cursor.getString(i3), cursor.isNull(i4) ? null : cursor.getString(i4), cursor.isNull(i5) ? null : cursor.getString(i5), cursor.isNull(i6) ? null : cursor.getString(i6), cursor.getInt(i + 6), cursor.isNull(i7) ? null : cursor.getString(i7), cursor.getLong(i + 8), cursor.getLong(i + 9), cursor.isNull(i8) ? null : cursor.getString(i8), cursor.isNull(i9) ? null : cursor.getString(i9));
    }

    @Override
    public void readEntity(Cursor cursor, AssetIssueContractDao assetIssueContractDao, int i) {
        assetIssueContractDao.setId(cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i)));
        int i2 = i + 1;
        assetIssueContractDao.setOwner_address(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 2;
        assetIssueContractDao.setName(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 3;
        assetIssueContractDao.setAbbr(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 4;
        assetIssueContractDao.setTotal_supply(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 5;
        assetIssueContractDao.setTrx_num(cursor.isNull(i6) ? null : cursor.getString(i6));
        assetIssueContractDao.setPrecision(cursor.getInt(i + 6));
        int i7 = i + 7;
        assetIssueContractDao.setNum(cursor.isNull(i7) ? null : cursor.getString(i7));
        assetIssueContractDao.setStart_time(cursor.getLong(i + 8));
        assetIssueContractDao.setEnd_time(cursor.getLong(i + 9));
        int i8 = i + 10;
        assetIssueContractDao.setDescription(cursor.isNull(i8) ? null : cursor.getString(i8));
        int i9 = i + 11;
        assetIssueContractDao.setUrl(cursor.isNull(i9) ? null : cursor.getString(i9));
    }

    @Override
    public final Long updateKeyAfterInsert(AssetIssueContractDao assetIssueContractDao, long j) {
        assetIssueContractDao.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override
    public Long getKey(AssetIssueContractDao assetIssueContractDao) {
        if (assetIssueContractDao != null) {
            return assetIssueContractDao.getId();
        }
        return null;
    }

    @Override
    public boolean hasKey(AssetIssueContractDao assetIssueContractDao) {
        return assetIssueContractDao.getId() != null;
    }
}
