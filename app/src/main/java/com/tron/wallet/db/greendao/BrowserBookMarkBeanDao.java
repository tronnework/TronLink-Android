package com.tron.wallet.db.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.tron.wallet.business.tabdapp.browser.bean.BrowserBookMarkBean;
import com.tron.wallet.common.config.TronConfig;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
public class BrowserBookMarkBeanDao extends AbstractDao<BrowserBookMarkBean, Long> {
    public static final String TABLENAME = "BROWSER_BOOK_MARK_BEAN";

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Url = new Property(1, String.class, "url", false, "URL");
        public static final Property Title = new Property(2, String.class, "title", false, TronConfig.TITLE);
        public static final Property IconUrl = new Property(3, String.class, "iconUrl", false, "ICON_URL");
        public static final Property Anonymous = new Property(4, Boolean.TYPE, "anonymous", false, "ANONYMOUS");
    }

    @Override
    public final boolean isEntityUpdateable() {
        return true;
    }

    public BrowserBookMarkBeanDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public BrowserBookMarkBeanDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"BROWSER_BOOK_MARK_BEAN\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"URL\" TEXT,\"TITLE\" TEXT,\"ICON_URL\" TEXT,\"ANONYMOUS\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"BROWSER_BOOK_MARK_BEAN\"");
        database.execSQL(sb.toString());
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, BrowserBookMarkBean browserBookMarkBean) {
        databaseStatement.clearBindings();
        Long id = browserBookMarkBean.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        String url = browserBookMarkBean.getUrl();
        if (url != null) {
            databaseStatement.bindString(2, url);
        }
        String title = browserBookMarkBean.getTitle();
        if (title != null) {
            databaseStatement.bindString(3, title);
        }
        String iconUrl = browserBookMarkBean.getIconUrl();
        if (iconUrl != null) {
            databaseStatement.bindString(4, iconUrl);
        }
        databaseStatement.bindLong(5, browserBookMarkBean.getAnonymous() ? 1L : 0L);
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, BrowserBookMarkBean browserBookMarkBean) {
        sQLiteStatement.clearBindings();
        Long id = browserBookMarkBean.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String url = browserBookMarkBean.getUrl();
        if (url != null) {
            sQLiteStatement.bindString(2, url);
        }
        String title = browserBookMarkBean.getTitle();
        if (title != null) {
            sQLiteStatement.bindString(3, title);
        }
        String iconUrl = browserBookMarkBean.getIconUrl();
        if (iconUrl != null) {
            sQLiteStatement.bindString(4, iconUrl);
        }
        sQLiteStatement.bindLong(5, browserBookMarkBean.getAnonymous() ? 1L : 0L);
    }

    @Override
    public Long readKey(Cursor cursor, int i) {
        if (cursor.isNull(i)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i));
    }

    @Override
    public BrowserBookMarkBean readEntity(Cursor cursor, int i) {
        Long valueOf = cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i));
        int i2 = i + 1;
        String string = cursor.isNull(i2) ? null : cursor.getString(i2);
        int i3 = i + 2;
        String string2 = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 3;
        return new BrowserBookMarkBean(valueOf, string, string2, cursor.isNull(i4) ? null : cursor.getString(i4), cursor.getShort(i + 4) != 0);
    }

    @Override
    public void readEntity(Cursor cursor, BrowserBookMarkBean browserBookMarkBean, int i) {
        browserBookMarkBean.setId(cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i)));
        int i2 = i + 1;
        browserBookMarkBean.setUrl(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 2;
        browserBookMarkBean.setTitle(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 3;
        browserBookMarkBean.setIconUrl(cursor.isNull(i4) ? null : cursor.getString(i4));
        browserBookMarkBean.setAnonymous(cursor.getShort(i + 4) != 0);
    }

    @Override
    public final Long updateKeyAfterInsert(BrowserBookMarkBean browserBookMarkBean, long j) {
        browserBookMarkBean.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override
    public Long getKey(BrowserBookMarkBean browserBookMarkBean) {
        if (browserBookMarkBean != null) {
            return browserBookMarkBean.getId();
        }
        return null;
    }

    @Override
    public boolean hasKey(BrowserBookMarkBean browserBookMarkBean) {
        return browserBookMarkBean.getId() != null;
    }
}
