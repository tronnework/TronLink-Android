package com.tron.wallet.db.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.tron.wallet.business.tabdapp.browser.bean.MostVisitDAppBean;
import com.tron.wallet.common.config.TronConfig;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
public class MostVisitDAppBeanDao extends AbstractDao<MostVisitDAppBean, Long> {
    public static final String TABLENAME = "MOST_VISIT_DAPP_BEAN";

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Url = new Property(1, String.class, "url", false, "URL");
        public static final Property Title = new Property(2, String.class, "title", false, TronConfig.TITLE);
        public static final Property Icon = new Property(3, String.class, "icon", false, "ICON");
        public static final Property Anonymous = new Property(4, Boolean.TYPE, "anonymous", false, "ANONYMOUS");
        public static final Property Score = new Property(5, Integer.TYPE, FirebaseAnalytics.Param.SCORE, false, "SCORE");
        public static final Property Flag = new Property(6, Integer.TYPE, "flag", false, "FLAG");
        public static final Property VisitTimestamp = new Property(7, Long.TYPE, "visitTimestamp", false, "VISIT_TIMESTAMP");
        public static final Property UpdateTimestamp = new Property(8, Long.TYPE, "updateTimestamp", false, "UPDATE_TIMESTAMP");
    }

    @Override
    public final boolean isEntityUpdateable() {
        return true;
    }

    public MostVisitDAppBeanDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public MostVisitDAppBeanDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"MOST_VISIT_DAPP_BEAN\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"URL\" TEXT UNIQUE ,\"TITLE\" TEXT,\"ICON\" TEXT,\"ANONYMOUS\" INTEGER NOT NULL ,\"SCORE\" INTEGER NOT NULL ,\"FLAG\" INTEGER NOT NULL ,\"VISIT_TIMESTAMP\" INTEGER NOT NULL ,\"UPDATE_TIMESTAMP\" INTEGER NOT NULL );");
        database.execSQL("CREATE UNIQUE INDEX " + str + "IDX_MOST_VISIT_DAPP_BEAN_URL ON \"MOST_VISIT_DAPP_BEAN\" (\"URL\" ASC);");
        database.execSQL("CREATE INDEX " + str + "IDX_MOST_VISIT_DAPP_BEAN_SCORE_DESC ON \"MOST_VISIT_DAPP_BEAN\" (\"SCORE\" DESC);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"MOST_VISIT_DAPP_BEAN\"");
        database.execSQL(sb.toString());
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, MostVisitDAppBean mostVisitDAppBean) {
        databaseStatement.clearBindings();
        Long id = mostVisitDAppBean.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        String url = mostVisitDAppBean.getUrl();
        if (url != null) {
            databaseStatement.bindString(2, url);
        }
        String title = mostVisitDAppBean.getTitle();
        if (title != null) {
            databaseStatement.bindString(3, title);
        }
        String icon = mostVisitDAppBean.getIcon();
        if (icon != null) {
            databaseStatement.bindString(4, icon);
        }
        databaseStatement.bindLong(5, mostVisitDAppBean.getAnonymous() ? 1L : 0L);
        databaseStatement.bindLong(6, mostVisitDAppBean.getScore());
        databaseStatement.bindLong(7, mostVisitDAppBean.getFlag());
        databaseStatement.bindLong(8, mostVisitDAppBean.getVisitTimestamp());
        databaseStatement.bindLong(9, mostVisitDAppBean.getUpdateTimestamp());
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, MostVisitDAppBean mostVisitDAppBean) {
        sQLiteStatement.clearBindings();
        Long id = mostVisitDAppBean.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String url = mostVisitDAppBean.getUrl();
        if (url != null) {
            sQLiteStatement.bindString(2, url);
        }
        String title = mostVisitDAppBean.getTitle();
        if (title != null) {
            sQLiteStatement.bindString(3, title);
        }
        String icon = mostVisitDAppBean.getIcon();
        if (icon != null) {
            sQLiteStatement.bindString(4, icon);
        }
        sQLiteStatement.bindLong(5, mostVisitDAppBean.getAnonymous() ? 1L : 0L);
        sQLiteStatement.bindLong(6, mostVisitDAppBean.getScore());
        sQLiteStatement.bindLong(7, mostVisitDAppBean.getFlag());
        sQLiteStatement.bindLong(8, mostVisitDAppBean.getVisitTimestamp());
        sQLiteStatement.bindLong(9, mostVisitDAppBean.getUpdateTimestamp());
    }

    @Override
    public Long readKey(Cursor cursor, int i) {
        if (cursor.isNull(i)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i));
    }

    @Override
    public MostVisitDAppBean readEntity(Cursor cursor, int i) {
        Long valueOf = cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i));
        int i2 = i + 1;
        String string = cursor.isNull(i2) ? null : cursor.getString(i2);
        int i3 = i + 2;
        String string2 = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 3;
        return new MostVisitDAppBean(valueOf, string, string2, cursor.isNull(i4) ? null : cursor.getString(i4), cursor.getShort(i + 4) != 0, cursor.getInt(i + 5), cursor.getInt(i + 6), cursor.getLong(i + 7), cursor.getLong(i + 8));
    }

    @Override
    public void readEntity(Cursor cursor, MostVisitDAppBean mostVisitDAppBean, int i) {
        mostVisitDAppBean.setId(cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i)));
        int i2 = i + 1;
        mostVisitDAppBean.setUrl(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 2;
        mostVisitDAppBean.setTitle(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 3;
        mostVisitDAppBean.setIcon(cursor.isNull(i4) ? null : cursor.getString(i4));
        mostVisitDAppBean.setAnonymous(cursor.getShort(i + 4) != 0);
        mostVisitDAppBean.setScore(cursor.getInt(i + 5));
        mostVisitDAppBean.setFlag(cursor.getInt(i + 6));
        mostVisitDAppBean.setVisitTimestamp(cursor.getLong(i + 7));
        mostVisitDAppBean.setUpdateTimestamp(cursor.getLong(i + 8));
    }

    @Override
    public final Long updateKeyAfterInsert(MostVisitDAppBean mostVisitDAppBean, long j) {
        mostVisitDAppBean.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override
    public Long getKey(MostVisitDAppBean mostVisitDAppBean) {
        if (mostVisitDAppBean != null) {
            return mostVisitDAppBean.getId();
        }
        return null;
    }

    @Override
    public boolean hasKey(MostVisitDAppBean mostVisitDAppBean) {
        return mostVisitDAppBean.getId() != null;
    }
}
