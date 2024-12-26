package com.tron.wallet.db.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.tron.wallet.common.bean.RowsBean;
import com.tron.wallet.common.config.TronConfig;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
public class RowsBeanDao extends AbstractDao<RowsBean, Long> {
    public static final String TABLENAME = "ROWS_BEAN";

    public static class Properties {
        public static final Property TbId = new Property(0, Long.class, "tbId", true, TronConfig.ID);
        public static final Property Id = new Property(1, Integer.TYPE, "id", false, "rowsId");
        public static final Property Volume = new Property(2, Long.TYPE, "volume", false, "VOLUME");
        public static final Property Gain = new Property(3, String.class, "gain", false, "GAIN");
        public static final Property Price = new Property(4, Long.TYPE, FirebaseAnalytics.Param.PRICE, false, "PRICE");
        public static final Property FShortName = new Property(5, String.class, "fShortName", false, "F_SHORT_NAME");
        public static final Property SShortName = new Property(6, String.class, "sShortName", false, "S_SHORT_NAME");
        public static final Property FTokenAddr = new Property(7, String.class, "fTokenAddr", false, "F_TOKEN_ADDR");
        public static final Property STokenAddr = new Property(8, String.class, "sTokenAddr", false, "S_TOKEN_ADDR");
        public static final Property Volume24h = new Property(9, Long.TYPE, "volume24h", false, "VOLUME24H");
    }

    @Override
    public final boolean isEntityUpdateable() {
        return true;
    }

    public RowsBeanDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public RowsBeanDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"ROWS_BEAN\" (\"ID\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"rowsId\" INTEGER NOT NULL ,\"VOLUME\" INTEGER NOT NULL ,\"GAIN\" TEXT,\"PRICE\" INTEGER NOT NULL ,\"F_SHORT_NAME\" TEXT,\"S_SHORT_NAME\" TEXT,\"F_TOKEN_ADDR\" TEXT,\"S_TOKEN_ADDR\" TEXT,\"VOLUME24H\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"ROWS_BEAN\"");
        database.execSQL(sb.toString());
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, RowsBean rowsBean) {
        databaseStatement.clearBindings();
        Long tbId = rowsBean.getTbId();
        if (tbId != null) {
            databaseStatement.bindLong(1, tbId.longValue());
        }
        databaseStatement.bindLong(2, rowsBean.getId());
        databaseStatement.bindLong(3, rowsBean.getVolume());
        String gain = rowsBean.getGain();
        if (gain != null) {
            databaseStatement.bindString(4, gain);
        }
        databaseStatement.bindLong(5, rowsBean.getPrice());
        String fShortName = rowsBean.getFShortName();
        if (fShortName != null) {
            databaseStatement.bindString(6, fShortName);
        }
        String sShortName = rowsBean.getSShortName();
        if (sShortName != null) {
            databaseStatement.bindString(7, sShortName);
        }
        String fTokenAddr = rowsBean.getFTokenAddr();
        if (fTokenAddr != null) {
            databaseStatement.bindString(8, fTokenAddr);
        }
        String sTokenAddr = rowsBean.getSTokenAddr();
        if (sTokenAddr != null) {
            databaseStatement.bindString(9, sTokenAddr);
        }
        databaseStatement.bindLong(10, rowsBean.getVolume24h());
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, RowsBean rowsBean) {
        sQLiteStatement.clearBindings();
        Long tbId = rowsBean.getTbId();
        if (tbId != null) {
            sQLiteStatement.bindLong(1, tbId.longValue());
        }
        sQLiteStatement.bindLong(2, rowsBean.getId());
        sQLiteStatement.bindLong(3, rowsBean.getVolume());
        String gain = rowsBean.getGain();
        if (gain != null) {
            sQLiteStatement.bindString(4, gain);
        }
        sQLiteStatement.bindLong(5, rowsBean.getPrice());
        String fShortName = rowsBean.getFShortName();
        if (fShortName != null) {
            sQLiteStatement.bindString(6, fShortName);
        }
        String sShortName = rowsBean.getSShortName();
        if (sShortName != null) {
            sQLiteStatement.bindString(7, sShortName);
        }
        String fTokenAddr = rowsBean.getFTokenAddr();
        if (fTokenAddr != null) {
            sQLiteStatement.bindString(8, fTokenAddr);
        }
        String sTokenAddr = rowsBean.getSTokenAddr();
        if (sTokenAddr != null) {
            sQLiteStatement.bindString(9, sTokenAddr);
        }
        sQLiteStatement.bindLong(10, rowsBean.getVolume24h());
    }

    @Override
    public Long readKey(Cursor cursor, int i) {
        if (cursor.isNull(i)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i));
    }

    @Override
    public RowsBean readEntity(Cursor cursor, int i) {
        int i2 = i + 3;
        int i3 = i + 5;
        int i4 = i + 6;
        int i5 = i + 7;
        int i6 = i + 8;
        return new RowsBean(cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i)), cursor.getInt(i + 1), cursor.getLong(i + 2), cursor.isNull(i2) ? null : cursor.getString(i2), cursor.getLong(i + 4), cursor.isNull(i3) ? null : cursor.getString(i3), cursor.isNull(i4) ? null : cursor.getString(i4), cursor.isNull(i5) ? null : cursor.getString(i5), cursor.isNull(i6) ? null : cursor.getString(i6), cursor.getLong(i + 9));
    }

    @Override
    public void readEntity(Cursor cursor, RowsBean rowsBean, int i) {
        rowsBean.setTbId(cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i)));
        rowsBean.setId(cursor.getInt(i + 1));
        rowsBean.setVolume(cursor.getLong(i + 2));
        int i2 = i + 3;
        rowsBean.setGain(cursor.isNull(i2) ? null : cursor.getString(i2));
        rowsBean.setPrice(cursor.getLong(i + 4));
        int i3 = i + 5;
        rowsBean.setFShortName(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 6;
        rowsBean.setSShortName(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 7;
        rowsBean.setFTokenAddr(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 8;
        rowsBean.setSTokenAddr(cursor.isNull(i6) ? null : cursor.getString(i6));
        rowsBean.setVolume24h(cursor.getLong(i + 9));
    }

    @Override
    public final Long updateKeyAfterInsert(RowsBean rowsBean, long j) {
        rowsBean.setTbId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override
    public Long getKey(RowsBean rowsBean) {
        if (rowsBean != null) {
            return rowsBean.getTbId();
        }
        return null;
    }

    @Override
    public boolean hasKey(RowsBean rowsBean) {
        return rowsBean.getTbId() != null;
    }
}
