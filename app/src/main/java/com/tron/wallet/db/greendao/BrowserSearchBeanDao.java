package com.tron.wallet.db.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.tron.wallet.business.tabdapp.browser.bean.BrowserSearchBean;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
public class BrowserSearchBeanDao extends AbstractDao<BrowserSearchBean, Long> {
    public static final String TABLENAME = "BROWSER_SEARCH_BEAN";

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Timestamp = new Property(1, Long.TYPE, "timestamp", false, "TIMESTAMP");
        public static final Property Key = new Property(2, String.class, "key", false, "KEY");
    }

    @Override
    public final boolean isEntityUpdateable() {
        return true;
    }

    public BrowserSearchBeanDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public BrowserSearchBeanDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"BROWSER_SEARCH_BEAN\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"TIMESTAMP\" INTEGER NOT NULL ,\"KEY\" TEXT);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"BROWSER_SEARCH_BEAN\"");
        database.execSQL(sb.toString());
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, BrowserSearchBean browserSearchBean) {
        databaseStatement.clearBindings();
        Long id = browserSearchBean.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindLong(2, browserSearchBean.getTimestamp());
        String key = browserSearchBean.getKey();
        if (key != null) {
            databaseStatement.bindString(3, key);
        }
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, BrowserSearchBean browserSearchBean) {
        sQLiteStatement.clearBindings();
        Long id = browserSearchBean.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindLong(2, browserSearchBean.getTimestamp());
        String key = browserSearchBean.getKey();
        if (key != null) {
            sQLiteStatement.bindString(3, key);
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
    public BrowserSearchBean readEntity(Cursor cursor, int i) {
        Long valueOf = cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i));
        long j = cursor.getLong(i + 1);
        int i2 = i + 2;
        return new BrowserSearchBean(valueOf, j, cursor.isNull(i2) ? null : cursor.getString(i2));
    }

    @Override
    public void readEntity(Cursor cursor, BrowserSearchBean browserSearchBean, int i) {
        browserSearchBean.setId(cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i)));
        browserSearchBean.setTimestamp(cursor.getLong(i + 1));
        int i2 = i + 2;
        browserSearchBean.setKey(cursor.isNull(i2) ? null : cursor.getString(i2));
    }

    @Override
    public final Long updateKeyAfterInsert(BrowserSearchBean browserSearchBean, long j) {
        browserSearchBean.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override
    public Long getKey(BrowserSearchBean browserSearchBean) {
        if (browserSearchBean != null) {
            return browserSearchBean.getId();
        }
        return null;
    }

    @Override
    public boolean hasKey(BrowserSearchBean browserSearchBean) {
        return browserSearchBean.getId() != null;
    }
}
