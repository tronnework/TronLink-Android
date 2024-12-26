package com.tron.wallet.db.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.tron.wallet.business.tabdapp.browser.bean.BrowserHistoryBean;
import com.tron.wallet.common.config.TronConfig;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
public class BrowserHistoryBeanDao extends AbstractDao<BrowserHistoryBean, Long> {
    public static final String TABLENAME = "BROWSER_HISTORY_BEAN";

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Timestamp = new Property(1, Long.TYPE, "timestamp", false, "TIMESTAMP");
        public static final Property Url = new Property(2, String.class, "url", false, "URL");
        public static final Property Title = new Property(3, String.class, "title", false, TronConfig.TITLE);
        public static final Property IconUrl = new Property(4, String.class, "iconUrl", false, "ICON_URL");
    }

    @Override
    public final boolean isEntityUpdateable() {
        return true;
    }

    public BrowserHistoryBeanDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public BrowserHistoryBeanDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"BROWSER_HISTORY_BEAN\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"TIMESTAMP\" INTEGER NOT NULL ,\"URL\" TEXT,\"TITLE\" TEXT,\"ICON_URL\" TEXT);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"BROWSER_HISTORY_BEAN\"");
        database.execSQL(sb.toString());
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, BrowserHistoryBean browserHistoryBean) {
        databaseStatement.clearBindings();
        Long id = browserHistoryBean.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindLong(2, browserHistoryBean.getTimestamp());
        String url = browserHistoryBean.getUrl();
        if (url != null) {
            databaseStatement.bindString(3, url);
        }
        String title = browserHistoryBean.getTitle();
        if (title != null) {
            databaseStatement.bindString(4, title);
        }
        String iconUrl = browserHistoryBean.getIconUrl();
        if (iconUrl != null) {
            databaseStatement.bindString(5, iconUrl);
        }
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, BrowserHistoryBean browserHistoryBean) {
        sQLiteStatement.clearBindings();
        Long id = browserHistoryBean.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindLong(2, browserHistoryBean.getTimestamp());
        String url = browserHistoryBean.getUrl();
        if (url != null) {
            sQLiteStatement.bindString(3, url);
        }
        String title = browserHistoryBean.getTitle();
        if (title != null) {
            sQLiteStatement.bindString(4, title);
        }
        String iconUrl = browserHistoryBean.getIconUrl();
        if (iconUrl != null) {
            sQLiteStatement.bindString(5, iconUrl);
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
    public BrowserHistoryBean readEntity(Cursor cursor, int i) {
        Long valueOf = cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i));
        long j = cursor.getLong(i + 1);
        int i2 = i + 2;
        String string = cursor.isNull(i2) ? null : cursor.getString(i2);
        int i3 = i + 3;
        int i4 = i + 4;
        return new BrowserHistoryBean(valueOf, j, string, cursor.isNull(i3) ? null : cursor.getString(i3), cursor.isNull(i4) ? null : cursor.getString(i4));
    }

    @Override
    public void readEntity(Cursor cursor, BrowserHistoryBean browserHistoryBean, int i) {
        browserHistoryBean.setId(cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i)));
        browserHistoryBean.setTimestamp(cursor.getLong(i + 1));
        int i2 = i + 2;
        browserHistoryBean.setUrl(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 3;
        browserHistoryBean.setTitle(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 4;
        browserHistoryBean.setIconUrl(cursor.isNull(i4) ? null : cursor.getString(i4));
    }

    @Override
    public final Long updateKeyAfterInsert(BrowserHistoryBean browserHistoryBean, long j) {
        browserHistoryBean.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override
    public Long getKey(BrowserHistoryBean browserHistoryBean) {
        if (browserHistoryBean != null) {
            return browserHistoryBean.getId();
        }
        return null;
    }

    @Override
    public boolean hasKey(BrowserHistoryBean browserHistoryBean) {
        return browserHistoryBean.getId() != null;
    }
}
