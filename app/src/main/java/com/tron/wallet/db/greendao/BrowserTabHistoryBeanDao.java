package com.tron.wallet.db.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.tron.wallet.business.tabdapp.browser.bean.BrowserTabHistoryBean;
import com.tron.wallet.common.config.TronConfig;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
public class BrowserTabHistoryBeanDao extends AbstractDao<BrowserTabHistoryBean, Long> {
    public static final String TABLENAME = "BROWSER_TAB_HISTORY_BEAN";

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Url = new Property(1, String.class, "url", false, "URL");
        public static final Property DappAuthUrl = new Property(2, String.class, "dappAuthUrl", false, "DAPP_AUTH_URL");
        public static final Property Title = new Property(3, String.class, "title", false, TronConfig.TITLE);
        public static final Property Icon = new Property(4, String.class, "icon", false, "ICON");
        public static final Property TabIndex = new Property(5, Integer.TYPE, "tabIndex", false, "TAB_INDEX");
        public static final Property IsCurrent = new Property(6, Boolean.TYPE, "isCurrent", false, "IS_CURRENT");
        public static final Property IsMain = new Property(7, Boolean.TYPE, "isMain", false, "IS_MAIN");
        public static final Property IsAnonymous = new Property(8, Boolean.TYPE, "isAnonymous", false, "IS_ANONYMOUS");
    }

    @Override
    public final boolean isEntityUpdateable() {
        return true;
    }

    public BrowserTabHistoryBeanDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public BrowserTabHistoryBeanDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"BROWSER_TAB_HISTORY_BEAN\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"URL\" TEXT,\"DAPP_AUTH_URL\" TEXT,\"TITLE\" TEXT,\"ICON\" TEXT,\"TAB_INDEX\" INTEGER NOT NULL ,\"IS_CURRENT\" INTEGER NOT NULL ,\"IS_MAIN\" INTEGER NOT NULL ,\"IS_ANONYMOUS\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"BROWSER_TAB_HISTORY_BEAN\"");
        database.execSQL(sb.toString());
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, BrowserTabHistoryBean browserTabHistoryBean) {
        databaseStatement.clearBindings();
        Long id = browserTabHistoryBean.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        String url = browserTabHistoryBean.getUrl();
        if (url != null) {
            databaseStatement.bindString(2, url);
        }
        String dappAuthUrl = browserTabHistoryBean.getDappAuthUrl();
        if (dappAuthUrl != null) {
            databaseStatement.bindString(3, dappAuthUrl);
        }
        String title = browserTabHistoryBean.getTitle();
        if (title != null) {
            databaseStatement.bindString(4, title);
        }
        String icon = browserTabHistoryBean.getIcon();
        if (icon != null) {
            databaseStatement.bindString(5, icon);
        }
        databaseStatement.bindLong(6, browserTabHistoryBean.getTabIndex());
        databaseStatement.bindLong(7, browserTabHistoryBean.getIsCurrent() ? 1L : 0L);
        databaseStatement.bindLong(8, browserTabHistoryBean.getIsMain() ? 1L : 0L);
        databaseStatement.bindLong(9, browserTabHistoryBean.getIsAnonymous() ? 1L : 0L);
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, BrowserTabHistoryBean browserTabHistoryBean) {
        sQLiteStatement.clearBindings();
        Long id = browserTabHistoryBean.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String url = browserTabHistoryBean.getUrl();
        if (url != null) {
            sQLiteStatement.bindString(2, url);
        }
        String dappAuthUrl = browserTabHistoryBean.getDappAuthUrl();
        if (dappAuthUrl != null) {
            sQLiteStatement.bindString(3, dappAuthUrl);
        }
        String title = browserTabHistoryBean.getTitle();
        if (title != null) {
            sQLiteStatement.bindString(4, title);
        }
        String icon = browserTabHistoryBean.getIcon();
        if (icon != null) {
            sQLiteStatement.bindString(5, icon);
        }
        sQLiteStatement.bindLong(6, browserTabHistoryBean.getTabIndex());
        sQLiteStatement.bindLong(7, browserTabHistoryBean.getIsCurrent() ? 1L : 0L);
        sQLiteStatement.bindLong(8, browserTabHistoryBean.getIsMain() ? 1L : 0L);
        sQLiteStatement.bindLong(9, browserTabHistoryBean.getIsAnonymous() ? 1L : 0L);
    }

    @Override
    public Long readKey(Cursor cursor, int i) {
        if (cursor.isNull(i)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i));
    }

    @Override
    public BrowserTabHistoryBean readEntity(Cursor cursor, int i) {
        Long valueOf = cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i));
        int i2 = i + 1;
        String string = cursor.isNull(i2) ? null : cursor.getString(i2);
        int i3 = i + 2;
        String string2 = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 3;
        String string3 = cursor.isNull(i4) ? null : cursor.getString(i4);
        int i5 = i + 4;
        return new BrowserTabHistoryBean(valueOf, string, string2, string3, cursor.isNull(i5) ? null : cursor.getString(i5), cursor.getInt(i + 5), cursor.getShort(i + 6) != 0, cursor.getShort(i + 7) != 0, cursor.getShort(i + 8) != 0);
    }

    @Override
    public void readEntity(Cursor cursor, BrowserTabHistoryBean browserTabHistoryBean, int i) {
        browserTabHistoryBean.setId(cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i)));
        int i2 = i + 1;
        browserTabHistoryBean.setUrl(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 2;
        browserTabHistoryBean.setDappAuthUrl(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 3;
        browserTabHistoryBean.setTitle(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 4;
        browserTabHistoryBean.setIcon(cursor.isNull(i5) ? null : cursor.getString(i5));
        browserTabHistoryBean.setTabIndex(cursor.getInt(i + 5));
        browserTabHistoryBean.setIsCurrent(cursor.getShort(i + 6) != 0);
        browserTabHistoryBean.setIsMain(cursor.getShort(i + 7) != 0);
        browserTabHistoryBean.setIsAnonymous(cursor.getShort(i + 8) != 0);
    }

    @Override
    public final Long updateKeyAfterInsert(BrowserTabHistoryBean browserTabHistoryBean, long j) {
        browserTabHistoryBean.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override
    public Long getKey(BrowserTabHistoryBean browserTabHistoryBean) {
        if (browserTabHistoryBean != null) {
            return browserTabHistoryBean.getId();
        }
        return null;
    }

    @Override
    public boolean hasKey(BrowserTabHistoryBean browserTabHistoryBean) {
        return browserTabHistoryBean.getId() != null;
    }
}
