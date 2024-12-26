package com.tron.wallet.db.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.tron.wallet.business.addassets2.bean.KVBean;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
public class KVBeanDao extends AbstractDao<KVBean, Long> {
    public static final String TABLENAME = "KVBEAN";

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Key = new Property(1, String.class, "key", false, "KEY");
        public static final Property Value = new Property(2, String.class, "value", false, "VALUE");
    }

    @Override
    public final boolean isEntityUpdateable() {
        return true;
    }

    public KVBeanDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public KVBeanDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"KVBEAN\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"KEY\" TEXT NOT NULL UNIQUE ,\"VALUE\" TEXT);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"KVBEAN\"");
        database.execSQL(sb.toString());
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, KVBean kVBean) {
        databaseStatement.clearBindings();
        Long id = kVBean.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindString(2, kVBean.getKey());
        String value = kVBean.getValue();
        if (value != null) {
            databaseStatement.bindString(3, value);
        }
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, KVBean kVBean) {
        sQLiteStatement.clearBindings();
        Long id = kVBean.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindString(2, kVBean.getKey());
        String value = kVBean.getValue();
        if (value != null) {
            sQLiteStatement.bindString(3, value);
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
    public KVBean readEntity(Cursor cursor, int i) {
        Long valueOf = cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i));
        String string = cursor.getString(i + 1);
        int i2 = i + 2;
        return new KVBean(valueOf, string, cursor.isNull(i2) ? null : cursor.getString(i2));
    }

    @Override
    public void readEntity(Cursor cursor, KVBean kVBean, int i) {
        kVBean.setId(cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i)));
        kVBean.setKey(cursor.getString(i + 1));
        int i2 = i + 2;
        kVBean.setValue(cursor.isNull(i2) ? null : cursor.getString(i2));
    }

    @Override
    public final Long updateKeyAfterInsert(KVBean kVBean, long j) {
        kVBean.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override
    public Long getKey(KVBean kVBean) {
        if (kVBean != null) {
            return kVBean.getId();
        }
        return null;
    }

    @Override
    public boolean hasKey(KVBean kVBean) {
        return kVBean.getId() != null;
    }
}
