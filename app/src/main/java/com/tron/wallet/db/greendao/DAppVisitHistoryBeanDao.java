package com.tron.wallet.db.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.tron.wallet.business.tabdapp.browser.bean.DAppVisitHistoryBean;
import com.tron.wallet.common.config.TronConfig;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
public class DAppVisitHistoryBeanDao extends AbstractDao<DAppVisitHistoryBean, Long> {
    public static final String TABLENAME = "DAPP_VISIT_HISTORY_BEAN";

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Url = new Property(1, String.class, "url", false, "URL");
        public static final Property Title = new Property(2, String.class, "title", false, TronConfig.TITLE);
        public static final Property Icon = new Property(3, String.class, "icon", false, "ICON");
        public static final Property Timestamp = new Property(4, Long.TYPE, "timestamp", false, "TIMESTAMP");
    }

    @Override
    public final boolean isEntityUpdateable() {
        return true;
    }

    public DAppVisitHistoryBeanDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DAppVisitHistoryBeanDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"DAPP_VISIT_HISTORY_BEAN\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"URL\" TEXT UNIQUE ,\"TITLE\" TEXT,\"ICON\" TEXT,\"TIMESTAMP\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"DAPP_VISIT_HISTORY_BEAN\"");
        database.execSQL(sb.toString());
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, DAppVisitHistoryBean dAppVisitHistoryBean) {
        databaseStatement.clearBindings();
        Long id = dAppVisitHistoryBean.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        String url = dAppVisitHistoryBean.getUrl();
        if (url != null) {
            databaseStatement.bindString(2, url);
        }
        String title = dAppVisitHistoryBean.getTitle();
        if (title != null) {
            databaseStatement.bindString(3, title);
        }
        String icon = dAppVisitHistoryBean.getIcon();
        if (icon != null) {
            databaseStatement.bindString(4, icon);
        }
        databaseStatement.bindLong(5, dAppVisitHistoryBean.getTimestamp());
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, DAppVisitHistoryBean dAppVisitHistoryBean) {
        sQLiteStatement.clearBindings();
        Long id = dAppVisitHistoryBean.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String url = dAppVisitHistoryBean.getUrl();
        if (url != null) {
            sQLiteStatement.bindString(2, url);
        }
        String title = dAppVisitHistoryBean.getTitle();
        if (title != null) {
            sQLiteStatement.bindString(3, title);
        }
        String icon = dAppVisitHistoryBean.getIcon();
        if (icon != null) {
            sQLiteStatement.bindString(4, icon);
        }
        sQLiteStatement.bindLong(5, dAppVisitHistoryBean.getTimestamp());
    }

    @Override
    public Long readKey(Cursor cursor, int i) {
        if (cursor.isNull(i)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i));
    }

    @Override
    public DAppVisitHistoryBean readEntity(Cursor cursor, int i) {
        Long valueOf = cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i));
        int i2 = i + 1;
        String string = cursor.isNull(i2) ? null : cursor.getString(i2);
        int i3 = i + 2;
        String string2 = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 3;
        return new DAppVisitHistoryBean(valueOf, string, string2, cursor.isNull(i4) ? null : cursor.getString(i4), cursor.getLong(i + 4));
    }

    @Override
    public void readEntity(Cursor cursor, DAppVisitHistoryBean dAppVisitHistoryBean, int i) {
        dAppVisitHistoryBean.setId(cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i)));
        int i2 = i + 1;
        dAppVisitHistoryBean.setUrl(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 2;
        dAppVisitHistoryBean.setTitle(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 3;
        dAppVisitHistoryBean.setIcon(cursor.isNull(i4) ? null : cursor.getString(i4));
        dAppVisitHistoryBean.setTimestamp(cursor.getLong(i + 4));
    }

    @Override
    public final Long updateKeyAfterInsert(DAppVisitHistoryBean dAppVisitHistoryBean, long j) {
        dAppVisitHistoryBean.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override
    public Long getKey(DAppVisitHistoryBean dAppVisitHistoryBean) {
        if (dAppVisitHistoryBean != null) {
            return dAppVisitHistoryBean.getId();
        }
        return null;
    }

    @Override
    public boolean hasKey(DAppVisitHistoryBean dAppVisitHistoryBean) {
        return dAppVisitHistoryBean.getId() != null;
    }
}
