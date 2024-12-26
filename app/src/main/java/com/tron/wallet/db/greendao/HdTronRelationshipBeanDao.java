package com.tron.wallet.db.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.tron.wallet.business.tabmy.dealsign.signfailure.SignFailureFragment;
import com.tron.wallet.db.bean.HdTronRelationshipBean;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
public class HdTronRelationshipBeanDao extends AbstractDao<HdTronRelationshipBean, String> {
    public static final String TABLENAME = "HD_TRON_RELATIONSHIP_BEAN";

    public static class Properties {
        public static final Property CurrentWalletAddress = new Property(0, String.class, "currentWalletAddress", false, "CURRENT_WALLET_ADDRESS");
        public static final Property RelationshipHDAddress = new Property(1, String.class, "relationshipHDAddress", false, "RELATIONSHIP_HDADDRESS");
        public static final Property MnemonicPath = new Property(2, String.class, "mnemonicPath", false, "MNEMONIC_PATH");
        public static final Property WalletName = new Property(3, String.class, "walletName", true, SignFailureFragment.WALLET_NAME);
        public static final Property CreateTime = new Property(4, Long.TYPE, "createTime", false, "CREATE_TIME");
        public static final Property NonHd = new Property(5, Boolean.TYPE, "nonHd", false, "NON_HD");
    }

    @Override
    public final boolean isEntityUpdateable() {
        return true;
    }

    public HdTronRelationshipBeanDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public HdTronRelationshipBeanDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"HD_TRON_RELATIONSHIP_BEAN\" (\"CURRENT_WALLET_ADDRESS\" TEXT,\"RELATIONSHIP_HDADDRESS\" TEXT,\"MNEMONIC_PATH\" TEXT,\"WALLET_NAME\" TEXT PRIMARY KEY NOT NULL ,\"CREATE_TIME\" INTEGER NOT NULL ,\"NON_HD\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"HD_TRON_RELATIONSHIP_BEAN\"");
        database.execSQL(sb.toString());
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, HdTronRelationshipBean hdTronRelationshipBean) {
        databaseStatement.clearBindings();
        String currentWalletAddress = hdTronRelationshipBean.getCurrentWalletAddress();
        if (currentWalletAddress != null) {
            databaseStatement.bindString(1, currentWalletAddress);
        }
        String relationshipHDAddress = hdTronRelationshipBean.getRelationshipHDAddress();
        if (relationshipHDAddress != null) {
            databaseStatement.bindString(2, relationshipHDAddress);
        }
        String mnemonicPath = hdTronRelationshipBean.getMnemonicPath();
        if (mnemonicPath != null) {
            databaseStatement.bindString(3, mnemonicPath);
        }
        String walletName = hdTronRelationshipBean.getWalletName();
        if (walletName != null) {
            databaseStatement.bindString(4, walletName);
        }
        databaseStatement.bindLong(5, hdTronRelationshipBean.getCreateTime());
        databaseStatement.bindLong(6, hdTronRelationshipBean.getNonHd() ? 1L : 0L);
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, HdTronRelationshipBean hdTronRelationshipBean) {
        sQLiteStatement.clearBindings();
        String currentWalletAddress = hdTronRelationshipBean.getCurrentWalletAddress();
        if (currentWalletAddress != null) {
            sQLiteStatement.bindString(1, currentWalletAddress);
        }
        String relationshipHDAddress = hdTronRelationshipBean.getRelationshipHDAddress();
        if (relationshipHDAddress != null) {
            sQLiteStatement.bindString(2, relationshipHDAddress);
        }
        String mnemonicPath = hdTronRelationshipBean.getMnemonicPath();
        if (mnemonicPath != null) {
            sQLiteStatement.bindString(3, mnemonicPath);
        }
        String walletName = hdTronRelationshipBean.getWalletName();
        if (walletName != null) {
            sQLiteStatement.bindString(4, walletName);
        }
        sQLiteStatement.bindLong(5, hdTronRelationshipBean.getCreateTime());
        sQLiteStatement.bindLong(6, hdTronRelationshipBean.getNonHd() ? 1L : 0L);
    }

    @Override
    public String readKey(Cursor cursor, int i) {
        int i2 = i + 3;
        if (cursor.isNull(i2)) {
            return null;
        }
        return cursor.getString(i2);
    }

    @Override
    public HdTronRelationshipBean readEntity(Cursor cursor, int i) {
        String string = cursor.isNull(i) ? null : cursor.getString(i);
        int i2 = i + 1;
        String string2 = cursor.isNull(i2) ? null : cursor.getString(i2);
        int i3 = i + 2;
        String string3 = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 3;
        return new HdTronRelationshipBean(string, string2, string3, cursor.isNull(i4) ? null : cursor.getString(i4), cursor.getLong(i + 4), cursor.getShort(i + 5) != 0);
    }

    @Override
    public void readEntity(Cursor cursor, HdTronRelationshipBean hdTronRelationshipBean, int i) {
        hdTronRelationshipBean.setCurrentWalletAddress(cursor.isNull(i) ? null : cursor.getString(i));
        int i2 = i + 1;
        hdTronRelationshipBean.setRelationshipHDAddress(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 2;
        hdTronRelationshipBean.setMnemonicPath(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 3;
        hdTronRelationshipBean.setWalletName(cursor.isNull(i4) ? null : cursor.getString(i4));
        hdTronRelationshipBean.setCreateTime(cursor.getLong(i + 4));
        hdTronRelationshipBean.setNonHd(cursor.getShort(i + 5) != 0);
    }

    @Override
    public final String updateKeyAfterInsert(HdTronRelationshipBean hdTronRelationshipBean, long j) {
        return hdTronRelationshipBean.getWalletName();
    }

    @Override
    public String getKey(HdTronRelationshipBean hdTronRelationshipBean) {
        if (hdTronRelationshipBean != null) {
            return hdTronRelationshipBean.getWalletName();
        }
        return null;
    }

    @Override
    public boolean hasKey(HdTronRelationshipBean hdTronRelationshipBean) {
        return hdTronRelationshipBean.getWalletName() != null;
    }
}
