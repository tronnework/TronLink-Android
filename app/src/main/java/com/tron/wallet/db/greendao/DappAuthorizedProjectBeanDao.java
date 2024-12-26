package com.tron.wallet.db.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.tron.wallet.business.nft.contract.Contract;
import com.tron.wallet.db.bean.DappAuthorizedProjectBean;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
public class DappAuthorizedProjectBeanDao extends AbstractDao<DappAuthorizedProjectBean, String> {
    public static final String TABLENAME = "DAPP_AUTHORIZED_PROJECT_BEAN";

    public static class Properties {
        public static final Property Url = new Property(0, String.class, "url", true, "URL");
        public static final Property Name = new Property(1, String.class, AppMeasurementSdk.ConditionalUserProperty.NAME, false, "NAME");
        public static final Property Icon = new Property(2, String.class, "icon", false, "ICON");
        public static final Property WalletAddress = new Property(3, String.class, "walletAddress", false, Contract.View.KEY_WALLET_ADDRESS);
        public static final Property Type = new Property(4, Integer.TYPE, "type", false, "TYPE");
    }

    @Override
    public final boolean isEntityUpdateable() {
        return true;
    }

    public DappAuthorizedProjectBeanDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DappAuthorizedProjectBeanDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"DAPP_AUTHORIZED_PROJECT_BEAN\" (\"URL\" TEXT PRIMARY KEY NOT NULL ,\"NAME\" TEXT,\"ICON\" TEXT,\"WALLET_ADDRESS\" TEXT,\"TYPE\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"DAPP_AUTHORIZED_PROJECT_BEAN\"");
        database.execSQL(sb.toString());
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, DappAuthorizedProjectBean dappAuthorizedProjectBean) {
        databaseStatement.clearBindings();
        String url = dappAuthorizedProjectBean.getUrl();
        if (url != null) {
            databaseStatement.bindString(1, url);
        }
        String name = dappAuthorizedProjectBean.getName();
        if (name != null) {
            databaseStatement.bindString(2, name);
        }
        String icon = dappAuthorizedProjectBean.getIcon();
        if (icon != null) {
            databaseStatement.bindString(3, icon);
        }
        String walletAddress = dappAuthorizedProjectBean.getWalletAddress();
        if (walletAddress != null) {
            databaseStatement.bindString(4, walletAddress);
        }
        databaseStatement.bindLong(5, dappAuthorizedProjectBean.getType());
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, DappAuthorizedProjectBean dappAuthorizedProjectBean) {
        sQLiteStatement.clearBindings();
        String url = dappAuthorizedProjectBean.getUrl();
        if (url != null) {
            sQLiteStatement.bindString(1, url);
        }
        String name = dappAuthorizedProjectBean.getName();
        if (name != null) {
            sQLiteStatement.bindString(2, name);
        }
        String icon = dappAuthorizedProjectBean.getIcon();
        if (icon != null) {
            sQLiteStatement.bindString(3, icon);
        }
        String walletAddress = dappAuthorizedProjectBean.getWalletAddress();
        if (walletAddress != null) {
            sQLiteStatement.bindString(4, walletAddress);
        }
        sQLiteStatement.bindLong(5, dappAuthorizedProjectBean.getType());
    }

    @Override
    public String readKey(Cursor cursor, int i) {
        if (cursor.isNull(i)) {
            return null;
        }
        return cursor.getString(i);
    }

    @Override
    public DappAuthorizedProjectBean readEntity(Cursor cursor, int i) {
        String string = cursor.isNull(i) ? null : cursor.getString(i);
        int i2 = i + 1;
        String string2 = cursor.isNull(i2) ? null : cursor.getString(i2);
        int i3 = i + 2;
        String string3 = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 3;
        return new DappAuthorizedProjectBean(string, string2, string3, cursor.isNull(i4) ? null : cursor.getString(i4), cursor.getInt(i + 4));
    }

    @Override
    public void readEntity(Cursor cursor, DappAuthorizedProjectBean dappAuthorizedProjectBean, int i) {
        dappAuthorizedProjectBean.setUrl(cursor.isNull(i) ? null : cursor.getString(i));
        int i2 = i + 1;
        dappAuthorizedProjectBean.setName(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 2;
        dappAuthorizedProjectBean.setIcon(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 3;
        dappAuthorizedProjectBean.setWalletAddress(cursor.isNull(i4) ? null : cursor.getString(i4));
        dappAuthorizedProjectBean.setType(cursor.getInt(i + 4));
    }

    @Override
    public final String updateKeyAfterInsert(DappAuthorizedProjectBean dappAuthorizedProjectBean, long j) {
        return dappAuthorizedProjectBean.getUrl();
    }

    @Override
    public String getKey(DappAuthorizedProjectBean dappAuthorizedProjectBean) {
        if (dappAuthorizedProjectBean != null) {
            return dappAuthorizedProjectBean.getUrl();
        }
        return null;
    }

    @Override
    public boolean hasKey(DappAuthorizedProjectBean dappAuthorizedProjectBean) {
        return dappAuthorizedProjectBean.getUrl() != null;
    }
}
