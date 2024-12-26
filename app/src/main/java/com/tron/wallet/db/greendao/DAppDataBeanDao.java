package com.tron.wallet.db.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.tron.wallet.business.addassets2.AssetsConfig;
import com.tron.wallet.business.tabdapp.dapphistory.bean.DAppDataBean;
import com.tron.wallet.common.config.TronConfig;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
public class DAppDataBeanDao extends AbstractDao<DAppDataBean, Long> {
    public static final String TABLENAME = "DAPP_DATA_BEAN";

    public static class Properties {
        public static final Property IdentifierId = new Property(0, Long.class, "identifierId", true, "_id");
        public static final Property Address = new Property(1, String.class, "address", false, "ADDRESS");
        public static final Property Id = new Property(2, Integer.TYPE, "id", false, TronConfig.ID);
        public static final Property ClassifyId = new Property(3, Integer.TYPE, "classifyId", false, "CLASSIFY_ID");
        public static final Property Name = new Property(4, String.class, AppMeasurementSdk.ConditionalUserProperty.NAME, false, "NAME");
        public static final Property ImageUrl = new Property(5, String.class, "imageUrl", false, "IMAGE_URL");
        public static final Property HomeUrl = new Property(6, String.class, "homeUrl", false, "HOME_URL");
        public static final Property Slogan = new Property(7, String.class, "slogan", false, "SLOGAN");
        public static final Property IsWhite = new Property(8, Integer.TYPE, "isWhite", false, "IS_WHITE");
        public static final Property IsAuthorized = new Property(9, Integer.TYPE, "isAuthorized", false, "IS_AUTHORIZED");
        public static final Property IsAnonymous = new Property(10, Integer.TYPE, "isAnonymous", false, "IS_ANONYMOUS");
        public static final Property ScreenOrder = new Property(11, Integer.TYPE, "screenOrder", false, "SCREEN_ORDER");
        public static final Property Title = new Property(12, String.class, "title", false, TronConfig.TITLE);
        public static final Property Icon = new Property(13, String.class, "icon", false, "ICON");
        public static final Property TimeStamp = new Property(14, Long.TYPE, "timeStamp", false, "TIME_STAMP");
        public static final Property DataType = new Property(15, Integer.TYPE, AssetsConfig.DATA_TYPE, false, "DATA_TYPE");
    }

    @Override
    public final boolean isEntityUpdateable() {
        return true;
    }

    public DAppDataBeanDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DAppDataBeanDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"DAPP_DATA_BEAN\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"ADDRESS\" TEXT,\"ID\" INTEGER NOT NULL ,\"CLASSIFY_ID\" INTEGER NOT NULL ,\"NAME\" TEXT,\"IMAGE_URL\" TEXT,\"HOME_URL\" TEXT,\"SLOGAN\" TEXT,\"IS_WHITE\" INTEGER NOT NULL ,\"IS_AUTHORIZED\" INTEGER NOT NULL ,\"IS_ANONYMOUS\" INTEGER NOT NULL ,\"SCREEN_ORDER\" INTEGER NOT NULL ,\"TITLE\" TEXT,\"ICON\" TEXT,\"TIME_STAMP\" INTEGER NOT NULL ,\"DATA_TYPE\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"DAPP_DATA_BEAN\"");
        database.execSQL(sb.toString());
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, DAppDataBean dAppDataBean) {
        databaseStatement.clearBindings();
        Long identifierId = dAppDataBean.getIdentifierId();
        if (identifierId != null) {
            databaseStatement.bindLong(1, identifierId.longValue());
        }
        String address = dAppDataBean.getAddress();
        if (address != null) {
            databaseStatement.bindString(2, address);
        }
        databaseStatement.bindLong(3, dAppDataBean.getId());
        databaseStatement.bindLong(4, dAppDataBean.getClassifyId());
        String name = dAppDataBean.getName();
        if (name != null) {
            databaseStatement.bindString(5, name);
        }
        String imageUrl = dAppDataBean.getImageUrl();
        if (imageUrl != null) {
            databaseStatement.bindString(6, imageUrl);
        }
        String homeUrl = dAppDataBean.getHomeUrl();
        if (homeUrl != null) {
            databaseStatement.bindString(7, homeUrl);
        }
        String slogan = dAppDataBean.getSlogan();
        if (slogan != null) {
            databaseStatement.bindString(8, slogan);
        }
        databaseStatement.bindLong(9, dAppDataBean.getIsWhite());
        databaseStatement.bindLong(10, dAppDataBean.getIsAuthorized());
        databaseStatement.bindLong(11, dAppDataBean.getIsAnonymous());
        databaseStatement.bindLong(12, dAppDataBean.getScreenOrder());
        String title = dAppDataBean.getTitle();
        if (title != null) {
            databaseStatement.bindString(13, title);
        }
        String icon = dAppDataBean.getIcon();
        if (icon != null) {
            databaseStatement.bindString(14, icon);
        }
        databaseStatement.bindLong(15, dAppDataBean.getTimeStamp());
        databaseStatement.bindLong(16, dAppDataBean.getDataType());
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, DAppDataBean dAppDataBean) {
        sQLiteStatement.clearBindings();
        Long identifierId = dAppDataBean.getIdentifierId();
        if (identifierId != null) {
            sQLiteStatement.bindLong(1, identifierId.longValue());
        }
        String address = dAppDataBean.getAddress();
        if (address != null) {
            sQLiteStatement.bindString(2, address);
        }
        sQLiteStatement.bindLong(3, dAppDataBean.getId());
        sQLiteStatement.bindLong(4, dAppDataBean.getClassifyId());
        String name = dAppDataBean.getName();
        if (name != null) {
            sQLiteStatement.bindString(5, name);
        }
        String imageUrl = dAppDataBean.getImageUrl();
        if (imageUrl != null) {
            sQLiteStatement.bindString(6, imageUrl);
        }
        String homeUrl = dAppDataBean.getHomeUrl();
        if (homeUrl != null) {
            sQLiteStatement.bindString(7, homeUrl);
        }
        String slogan = dAppDataBean.getSlogan();
        if (slogan != null) {
            sQLiteStatement.bindString(8, slogan);
        }
        sQLiteStatement.bindLong(9, dAppDataBean.getIsWhite());
        sQLiteStatement.bindLong(10, dAppDataBean.getIsAuthorized());
        sQLiteStatement.bindLong(11, dAppDataBean.getIsAnonymous());
        sQLiteStatement.bindLong(12, dAppDataBean.getScreenOrder());
        String title = dAppDataBean.getTitle();
        if (title != null) {
            sQLiteStatement.bindString(13, title);
        }
        String icon = dAppDataBean.getIcon();
        if (icon != null) {
            sQLiteStatement.bindString(14, icon);
        }
        sQLiteStatement.bindLong(15, dAppDataBean.getTimeStamp());
        sQLiteStatement.bindLong(16, dAppDataBean.getDataType());
    }

    @Override
    public Long readKey(Cursor cursor, int i) {
        if (cursor.isNull(i)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i));
    }

    @Override
    public DAppDataBean readEntity(Cursor cursor, int i) {
        Long valueOf = cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i));
        int i2 = i + 1;
        String string = cursor.isNull(i2) ? null : cursor.getString(i2);
        int i3 = cursor.getInt(i + 2);
        int i4 = cursor.getInt(i + 3);
        int i5 = i + 4;
        String string2 = cursor.isNull(i5) ? null : cursor.getString(i5);
        int i6 = i + 5;
        String string3 = cursor.isNull(i6) ? null : cursor.getString(i6);
        int i7 = i + 6;
        String string4 = cursor.isNull(i7) ? null : cursor.getString(i7);
        int i8 = i + 7;
        String string5 = cursor.isNull(i8) ? null : cursor.getString(i8);
        int i9 = cursor.getInt(i + 8);
        int i10 = cursor.getInt(i + 9);
        int i11 = cursor.getInt(i + 10);
        int i12 = cursor.getInt(i + 11);
        int i13 = i + 12;
        String string6 = cursor.isNull(i13) ? null : cursor.getString(i13);
        int i14 = i + 13;
        return new DAppDataBean(valueOf, string, i3, i4, string2, string3, string4, string5, i9, i10, i11, i12, string6, cursor.isNull(i14) ? null : cursor.getString(i14), cursor.getLong(i + 14), cursor.getInt(i + 15));
    }

    @Override
    public void readEntity(Cursor cursor, DAppDataBean dAppDataBean, int i) {
        dAppDataBean.setIdentifierId(cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i)));
        int i2 = i + 1;
        dAppDataBean.setAddress(cursor.isNull(i2) ? null : cursor.getString(i2));
        dAppDataBean.setId(cursor.getInt(i + 2));
        dAppDataBean.setClassifyId(cursor.getInt(i + 3));
        int i3 = i + 4;
        dAppDataBean.setName(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 5;
        dAppDataBean.setImageUrl(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 6;
        dAppDataBean.setHomeUrl(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 7;
        dAppDataBean.setSlogan(cursor.isNull(i6) ? null : cursor.getString(i6));
        dAppDataBean.setIsWhite(cursor.getInt(i + 8));
        dAppDataBean.setIsAuthorized(cursor.getInt(i + 9));
        dAppDataBean.setIsAnonymous(cursor.getInt(i + 10));
        dAppDataBean.setScreenOrder(cursor.getInt(i + 11));
        int i7 = i + 12;
        dAppDataBean.setTitle(cursor.isNull(i7) ? null : cursor.getString(i7));
        int i8 = i + 13;
        dAppDataBean.setIcon(cursor.isNull(i8) ? null : cursor.getString(i8));
        dAppDataBean.setTimeStamp(cursor.getLong(i + 14));
        dAppDataBean.setDataType(cursor.getInt(i + 15));
    }

    @Override
    public final Long updateKeyAfterInsert(DAppDataBean dAppDataBean, long j) {
        dAppDataBean.setIdentifierId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override
    public Long getKey(DAppDataBean dAppDataBean) {
        if (dAppDataBean != null) {
            return dAppDataBean.getIdentifierId();
        }
        return null;
    }

    @Override
    public boolean hasKey(DAppDataBean dAppDataBean) {
        return dAppDataBean.getIdentifierId() != null;
    }
}
