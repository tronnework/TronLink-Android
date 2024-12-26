package com.tron.wallet.db.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import androidx.core.app.NotificationCompat;
import com.google.firebase.messaging.Constants;
import com.tron.wallet.common.bean.ColdFailLogBean;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
public class ColdFailLogBeanDao extends AbstractDao<ColdFailLogBean, Long> {
    public static final String TABLENAME = "COLD_FAIL_LOG_BEAN";

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property ActivityName = new Property(1, String.class, "activityName", false, "ACTIVITY_NAME");
        public static final Property MethodName = new Property(2, String.class, "methodName", false, "METHOD_NAME");
        public static final Property TransactionStr = new Property(3, String.class, "transactionStr", false, "TRANSACTION_STR");
        public static final Property AReturn = new Property(4, String.class, "aReturn", false, "A_RETURN");
        public static final Property Address = new Property(5, String.class, "address", false, "ADDRESS");
        public static final Property HasLoad = new Property(6, Boolean.TYPE, "hasLoad", false, "HAS_LOAD");
        public static final Property Time = new Property(7, Long.TYPE, "time", false, "TIME");
        public static final Property Error = new Property(8, String.class, Constants.IPC_BUNDLE_KEY_SEND_ERROR, false, "ERROR");
        public static final Property Status = new Property(9, String.class, NotificationCompat.CATEGORY_STATUS, false, "STATUS");
    }

    @Override
    public final boolean isEntityUpdateable() {
        return true;
    }

    public ColdFailLogBeanDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public ColdFailLogBeanDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"COLD_FAIL_LOG_BEAN\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"ACTIVITY_NAME\" TEXT,\"METHOD_NAME\" TEXT,\"TRANSACTION_STR\" TEXT,\"A_RETURN\" TEXT,\"ADDRESS\" TEXT,\"HAS_LOAD\" INTEGER NOT NULL ,\"TIME\" INTEGER NOT NULL ,\"ERROR\" TEXT,\"STATUS\" TEXT);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"COLD_FAIL_LOG_BEAN\"");
        database.execSQL(sb.toString());
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, ColdFailLogBean coldFailLogBean) {
        databaseStatement.clearBindings();
        Long id = coldFailLogBean.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        String activityName = coldFailLogBean.getActivityName();
        if (activityName != null) {
            databaseStatement.bindString(2, activityName);
        }
        String methodName = coldFailLogBean.getMethodName();
        if (methodName != null) {
            databaseStatement.bindString(3, methodName);
        }
        String transactionStr = coldFailLogBean.getTransactionStr();
        if (transactionStr != null) {
            databaseStatement.bindString(4, transactionStr);
        }
        String aReturn = coldFailLogBean.getAReturn();
        if (aReturn != null) {
            databaseStatement.bindString(5, aReturn);
        }
        String address = coldFailLogBean.getAddress();
        if (address != null) {
            databaseStatement.bindString(6, address);
        }
        databaseStatement.bindLong(7, coldFailLogBean.getHasLoad() ? 1L : 0L);
        databaseStatement.bindLong(8, coldFailLogBean.getTime());
        String error = coldFailLogBean.getError();
        if (error != null) {
            databaseStatement.bindString(9, error);
        }
        String status = coldFailLogBean.getStatus();
        if (status != null) {
            databaseStatement.bindString(10, status);
        }
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, ColdFailLogBean coldFailLogBean) {
        sQLiteStatement.clearBindings();
        Long id = coldFailLogBean.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String activityName = coldFailLogBean.getActivityName();
        if (activityName != null) {
            sQLiteStatement.bindString(2, activityName);
        }
        String methodName = coldFailLogBean.getMethodName();
        if (methodName != null) {
            sQLiteStatement.bindString(3, methodName);
        }
        String transactionStr = coldFailLogBean.getTransactionStr();
        if (transactionStr != null) {
            sQLiteStatement.bindString(4, transactionStr);
        }
        String aReturn = coldFailLogBean.getAReturn();
        if (aReturn != null) {
            sQLiteStatement.bindString(5, aReturn);
        }
        String address = coldFailLogBean.getAddress();
        if (address != null) {
            sQLiteStatement.bindString(6, address);
        }
        sQLiteStatement.bindLong(7, coldFailLogBean.getHasLoad() ? 1L : 0L);
        sQLiteStatement.bindLong(8, coldFailLogBean.getTime());
        String error = coldFailLogBean.getError();
        if (error != null) {
            sQLiteStatement.bindString(9, error);
        }
        String status = coldFailLogBean.getStatus();
        if (status != null) {
            sQLiteStatement.bindString(10, status);
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
    public ColdFailLogBean readEntity(Cursor cursor, int i) {
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
        boolean z = cursor.getShort(i + 6) != 0;
        long j = cursor.getLong(i + 7);
        int i7 = i + 8;
        int i8 = i + 9;
        return new ColdFailLogBean(valueOf, string, string2, string3, string4, string5, z, j, cursor.isNull(i7) ? null : cursor.getString(i7), cursor.isNull(i8) ? null : cursor.getString(i8));
    }

    @Override
    public void readEntity(Cursor cursor, ColdFailLogBean coldFailLogBean, int i) {
        coldFailLogBean.setId(cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i)));
        int i2 = i + 1;
        coldFailLogBean.setActivityName(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 2;
        coldFailLogBean.setMethodName(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 3;
        coldFailLogBean.setTransactionStr(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 4;
        coldFailLogBean.setAReturn(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 5;
        coldFailLogBean.setAddress(cursor.isNull(i6) ? null : cursor.getString(i6));
        coldFailLogBean.setHasLoad(cursor.getShort(i + 6) != 0);
        coldFailLogBean.setTime(cursor.getLong(i + 7));
        int i7 = i + 8;
        coldFailLogBean.setError(cursor.isNull(i7) ? null : cursor.getString(i7));
        int i8 = i + 9;
        coldFailLogBean.setStatus(cursor.isNull(i8) ? null : cursor.getString(i8));
    }

    @Override
    public final Long updateKeyAfterInsert(ColdFailLogBean coldFailLogBean, long j) {
        coldFailLogBean.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override
    public Long getKey(ColdFailLogBean coldFailLogBean) {
        if (coldFailLogBean != null) {
            return coldFailLogBean.getId();
        }
        return null;
    }

    @Override
    public boolean hasKey(ColdFailLogBean coldFailLogBean) {
        return coldFailLogBean.getId() != null;
    }
}
