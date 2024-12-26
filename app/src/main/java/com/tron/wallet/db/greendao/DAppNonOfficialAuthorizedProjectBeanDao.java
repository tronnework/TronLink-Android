package com.tron.wallet.db.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.tron.wallet.business.nft.contract.Contract;
import com.tron.wallet.db.bean.DAppNonOfficialAuthorizedProjectBean;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
public class DAppNonOfficialAuthorizedProjectBeanDao extends AbstractDao<DAppNonOfficialAuthorizedProjectBean, Long> {
    public static final String TABLENAME = "DAPP_NON_OFFICIAL_AUTHORIZED_PROJECT_BEAN";

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Url = new Property(1, String.class, "url", false, "URL");
        public static final Property Name = new Property(2, String.class, AppMeasurementSdk.ConditionalUserProperty.NAME, false, "NAME");
        public static final Property Icon = new Property(3, String.class, "icon", false, "ICON");
        public static final Property WalletAddress = new Property(4, String.class, "walletAddress", false, Contract.View.KEY_WALLET_ADDRESS);
        public static final Property Type = new Property(5, Integer.TYPE, "type", false, "TYPE");
    }

    @Override
    public final boolean isEntityUpdateable() {
        return true;
    }

    public DAppNonOfficialAuthorizedProjectBeanDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DAppNonOfficialAuthorizedProjectBeanDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"DAPP_NON_OFFICIAL_AUTHORIZED_PROJECT_BEAN\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"URL\" TEXT,\"NAME\" TEXT,\"ICON\" TEXT,\"WALLET_ADDRESS\" TEXT,\"TYPE\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"DAPP_NON_OFFICIAL_AUTHORIZED_PROJECT_BEAN\"");
        database.execSQL(sb.toString());
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, DAppNonOfficialAuthorizedProjectBean dAppNonOfficialAuthorizedProjectBean) {
        databaseStatement.clearBindings();
        Long id = dAppNonOfficialAuthorizedProjectBean.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        String url = dAppNonOfficialAuthorizedProjectBean.getUrl();
        if (url != null) {
            databaseStatement.bindString(2, url);
        }
        String name = dAppNonOfficialAuthorizedProjectBean.getName();
        if (name != null) {
            databaseStatement.bindString(3, name);
        }
        String icon = dAppNonOfficialAuthorizedProjectBean.getIcon();
        if (icon != null) {
            databaseStatement.bindString(4, icon);
        }
        String walletAddress = dAppNonOfficialAuthorizedProjectBean.getWalletAddress();
        if (walletAddress != null) {
            databaseStatement.bindString(5, walletAddress);
        }
        databaseStatement.bindLong(6, dAppNonOfficialAuthorizedProjectBean.getType());
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, DAppNonOfficialAuthorizedProjectBean dAppNonOfficialAuthorizedProjectBean) {
        sQLiteStatement.clearBindings();
        Long id = dAppNonOfficialAuthorizedProjectBean.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String url = dAppNonOfficialAuthorizedProjectBean.getUrl();
        if (url != null) {
            sQLiteStatement.bindString(2, url);
        }
        String name = dAppNonOfficialAuthorizedProjectBean.getName();
        if (name != null) {
            sQLiteStatement.bindString(3, name);
        }
        String icon = dAppNonOfficialAuthorizedProjectBean.getIcon();
        if (icon != null) {
            sQLiteStatement.bindString(4, icon);
        }
        String walletAddress = dAppNonOfficialAuthorizedProjectBean.getWalletAddress();
        if (walletAddress != null) {
            sQLiteStatement.bindString(5, walletAddress);
        }
        sQLiteStatement.bindLong(6, dAppNonOfficialAuthorizedProjectBean.getType());
    }

    @Override
    public Long readKey(Cursor cursor, int i) {
        if (cursor.isNull(i)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i));
    }

    @Override
    public DAppNonOfficialAuthorizedProjectBean readEntity(Cursor cursor, int i) {
        Long valueOf = cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i));
        int i2 = i + 1;
        String string = cursor.isNull(i2) ? null : cursor.getString(i2);
        int i3 = i + 2;
        String string2 = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 3;
        String string3 = cursor.isNull(i4) ? null : cursor.getString(i4);
        int i5 = i + 4;
        return new DAppNonOfficialAuthorizedProjectBean(valueOf, string, string2, string3, cursor.isNull(i5) ? null : cursor.getString(i5), cursor.getInt(i + 5));
    }

    @Override
    public void readEntity(Cursor cursor, DAppNonOfficialAuthorizedProjectBean dAppNonOfficialAuthorizedProjectBean, int i) {
        dAppNonOfficialAuthorizedProjectBean.setId(cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i)));
        int i2 = i + 1;
        dAppNonOfficialAuthorizedProjectBean.setUrl(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 2;
        dAppNonOfficialAuthorizedProjectBean.setName(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 3;
        dAppNonOfficialAuthorizedProjectBean.setIcon(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 4;
        dAppNonOfficialAuthorizedProjectBean.setWalletAddress(cursor.isNull(i5) ? null : cursor.getString(i5));
        dAppNonOfficialAuthorizedProjectBean.setType(cursor.getInt(i + 5));
    }

    @Override
    public final Long updateKeyAfterInsert(DAppNonOfficialAuthorizedProjectBean dAppNonOfficialAuthorizedProjectBean, long j) {
        dAppNonOfficialAuthorizedProjectBean.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override
    public Long getKey(DAppNonOfficialAuthorizedProjectBean dAppNonOfficialAuthorizedProjectBean) {
        if (dAppNonOfficialAuthorizedProjectBean != null) {
            return dAppNonOfficialAuthorizedProjectBean.getId();
        }
        return null;
    }

    @Override
    public boolean hasKey(DAppNonOfficialAuthorizedProjectBean dAppNonOfficialAuthorizedProjectBean) {
        return dAppNonOfficialAuthorizedProjectBean.getId() != null;
    }
}
