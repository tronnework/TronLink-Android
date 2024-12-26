package com.tron.wallet.db.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.tron.wallet.db.Controller.DappBlackBean;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
public class DappBlackBeanDao extends AbstractDao<DappBlackBean, Long> {
    public static final String TABLENAME = "DAPP_BLACK_BEAN";

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Name = new Property(1, String.class, AppMeasurementSdk.ConditionalUserProperty.NAME, false, "NAME");
    }

    @Override
    public final boolean isEntityUpdateable() {
        return true;
    }

    public DappBlackBeanDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DappBlackBeanDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"DAPP_BLACK_BEAN\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"NAME\" TEXT);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"DAPP_BLACK_BEAN\"");
        database.execSQL(sb.toString());
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, DappBlackBean dappBlackBean) {
        databaseStatement.clearBindings();
        Long id = dappBlackBean.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        String name = dappBlackBean.getName();
        if (name != null) {
            databaseStatement.bindString(2, name);
        }
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, DappBlackBean dappBlackBean) {
        sQLiteStatement.clearBindings();
        Long id = dappBlackBean.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String name = dappBlackBean.getName();
        if (name != null) {
            sQLiteStatement.bindString(2, name);
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
    public DappBlackBean readEntity(Cursor cursor, int i) {
        Long valueOf = cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i));
        int i2 = i + 1;
        return new DappBlackBean(valueOf, cursor.isNull(i2) ? null : cursor.getString(i2));
    }

    @Override
    public void readEntity(Cursor cursor, DappBlackBean dappBlackBean, int i) {
        dappBlackBean.setId(cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i)));
        int i2 = i + 1;
        dappBlackBean.setName(cursor.isNull(i2) ? null : cursor.getString(i2));
    }

    @Override
    public final Long updateKeyAfterInsert(DappBlackBean dappBlackBean, long j) {
        dappBlackBean.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override
    public Long getKey(DappBlackBean dappBlackBean) {
        if (dappBlackBean != null) {
            return dappBlackBean.getId();
        }
        return null;
    }

    @Override
    public boolean hasKey(DappBlackBean dappBlackBean) {
        return dappBlackBean.getId() != null;
    }
}
