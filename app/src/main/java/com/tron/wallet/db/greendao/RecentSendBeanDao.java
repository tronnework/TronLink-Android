package com.tron.wallet.db.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.tron.wallet.common.bean.RecentSendBean;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
public class RecentSendBeanDao extends AbstractDao<RecentSendBean, Long> {
    public static final String TABLENAME = "RECENT_SEND_BEAN";

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property SendAddress = new Property(1, String.class, "sendAddress", false, "SEND_ADDRESS");
        public static final Property ReceiverAddress = new Property(2, String.class, "receiverAddress", false, "RECEIVER_ADDRESS");
        public static final Property Timestamp = new Property(3, Long.class, "timestamp", false, "TIMESTAMP");
        public static final Property TransactionType = new Property(4, Integer.TYPE, "transactionType", false, "TRANSACTION_TYPE");
    }

    @Override
    public final boolean isEntityUpdateable() {
        return true;
    }

    public RecentSendBeanDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public RecentSendBeanDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"RECENT_SEND_BEAN\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"SEND_ADDRESS\" TEXT,\"RECEIVER_ADDRESS\" TEXT,\"TIMESTAMP\" INTEGER,\"TRANSACTION_TYPE\" INTEGER NOT NULL );");
        database.execSQL("CREATE UNIQUE INDEX " + str + "IDX_RECENT_SEND_BEAN_SEND_ADDRESS_RECEIVER_ADDRESS ON \"RECENT_SEND_BEAN\" (\"SEND_ADDRESS\" ASC,\"RECEIVER_ADDRESS\" ASC);");
        database.execSQL("CREATE INDEX " + str + "IDX_RECENT_SEND_BEAN_TIMESTAMP_DESC ON \"RECENT_SEND_BEAN\" (\"TIMESTAMP\" DESC);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"RECENT_SEND_BEAN\"");
        database.execSQL(sb.toString());
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, RecentSendBean recentSendBean) {
        databaseStatement.clearBindings();
        Long id = recentSendBean.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        String sendAddress = recentSendBean.getSendAddress();
        if (sendAddress != null) {
            databaseStatement.bindString(2, sendAddress);
        }
        String receiverAddress = recentSendBean.getReceiverAddress();
        if (receiverAddress != null) {
            databaseStatement.bindString(3, receiverAddress);
        }
        Long timestamp = recentSendBean.getTimestamp();
        if (timestamp != null) {
            databaseStatement.bindLong(4, timestamp.longValue());
        }
        databaseStatement.bindLong(5, recentSendBean.getTransactionType());
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, RecentSendBean recentSendBean) {
        sQLiteStatement.clearBindings();
        Long id = recentSendBean.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String sendAddress = recentSendBean.getSendAddress();
        if (sendAddress != null) {
            sQLiteStatement.bindString(2, sendAddress);
        }
        String receiverAddress = recentSendBean.getReceiverAddress();
        if (receiverAddress != null) {
            sQLiteStatement.bindString(3, receiverAddress);
        }
        Long timestamp = recentSendBean.getTimestamp();
        if (timestamp != null) {
            sQLiteStatement.bindLong(4, timestamp.longValue());
        }
        sQLiteStatement.bindLong(5, recentSendBean.getTransactionType());
    }

    @Override
    public Long readKey(Cursor cursor, int i) {
        if (cursor.isNull(i)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i));
    }

    @Override
    public RecentSendBean readEntity(Cursor cursor, int i) {
        Long valueOf = cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i));
        int i2 = i + 1;
        String string = cursor.isNull(i2) ? null : cursor.getString(i2);
        int i3 = i + 2;
        String string2 = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 3;
        return new RecentSendBean(valueOf, string, string2, cursor.isNull(i4) ? null : Long.valueOf(cursor.getLong(i4)), cursor.getInt(i + 4));
    }

    @Override
    public void readEntity(Cursor cursor, RecentSendBean recentSendBean, int i) {
        recentSendBean.setId(cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i)));
        int i2 = i + 1;
        recentSendBean.setSendAddress(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 2;
        recentSendBean.setReceiverAddress(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 3;
        recentSendBean.setTimestamp(cursor.isNull(i4) ? null : Long.valueOf(cursor.getLong(i4)));
        recentSendBean.setTransactionType(cursor.getInt(i + 4));
    }

    @Override
    public final Long updateKeyAfterInsert(RecentSendBean recentSendBean, long j) {
        recentSendBean.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override
    public Long getKey(RecentSendBean recentSendBean) {
        if (recentSendBean != null) {
            return recentSendBean.getId();
        }
        return null;
    }

    @Override
    public boolean hasKey(RecentSendBean recentSendBean) {
        return recentSendBean.getId() != null;
    }
}
