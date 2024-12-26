package com.tron.wallet.db.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.tron.wallet.business.tabdapp.browser.bean.BrowserTabBean;
import com.tron.wallet.common.config.TronConfig;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
public class BrowserTabBeanDao extends AbstractDao<BrowserTabBean, Long> {
    public static final String TABLENAME = "BROWSER_TAB_BEAN";

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Url = new Property(1, String.class, "url", false, "URL");
        public static final Property Title = new Property(2, String.class, "title", false, TronConfig.TITLE);
        public static final Property Icon = new Property(3, String.class, "icon", false, "ICON");
    }

    @Override
    public final boolean isEntityUpdateable() {
        return true;
    }

    public BrowserTabBeanDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public BrowserTabBeanDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"BROWSER_TAB_BEAN\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"URL\" TEXT,\"TITLE\" TEXT,\"ICON\" TEXT);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"BROWSER_TAB_BEAN\"");
        database.execSQL(sb.toString());
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, BrowserTabBean browserTabBean) {
        databaseStatement.clearBindings();
        Long id = browserTabBean.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        String url = browserTabBean.getUrl();
        if (url != null) {
            databaseStatement.bindString(2, url);
        }
        String title = browserTabBean.getTitle();
        if (title != null) {
            databaseStatement.bindString(3, title);
        }
        String icon = browserTabBean.getIcon();
        if (icon != null) {
            databaseStatement.bindString(4, icon);
        }
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, BrowserTabBean browserTabBean) {
        sQLiteStatement.clearBindings();
        Long id = browserTabBean.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String url = browserTabBean.getUrl();
        if (url != null) {
            sQLiteStatement.bindString(2, url);
        }
        String title = browserTabBean.getTitle();
        if (title != null) {
            sQLiteStatement.bindString(3, title);
        }
        String icon = browserTabBean.getIcon();
        if (icon != null) {
            sQLiteStatement.bindString(4, icon);
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
    public BrowserTabBean readEntity(Cursor cursor, int i) {
        Long valueOf = cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i));
        int i2 = i + 1;
        int i3 = i + 2;
        int i4 = i + 3;
        return new BrowserTabBean(valueOf, cursor.isNull(i2) ? null : cursor.getString(i2), cursor.isNull(i3) ? null : cursor.getString(i3), cursor.isNull(i4) ? null : cursor.getString(i4));
    }

    @Override
    public void readEntity(Cursor cursor, BrowserTabBean browserTabBean, int i) {
        browserTabBean.setId(cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i)));
        int i2 = i + 1;
        browserTabBean.setUrl(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 2;
        browserTabBean.setTitle(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 3;
        browserTabBean.setIcon(cursor.isNull(i4) ? null : cursor.getString(i4));
    }

    @Override
    public final Long updateKeyAfterInsert(BrowserTabBean browserTabBean, long j) {
        browserTabBean.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override
    public Long getKey(BrowserTabBean browserTabBean) {
        if (browserTabBean != null) {
            return browserTabBean.getId();
        }
        return null;
    }

    @Override
    public boolean hasKey(BrowserTabBean browserTabBean) {
        return browserTabBean.getId() != null;
    }
}
