package com.tron.wallet.db.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.tron.wallet.business.nft.contract.Contract;
import com.tron.wallet.business.tabmy.dealsign.signfailure.SignFailureFragment;
import com.tron.wallet.business.walletmanager.backup.record.BackupRecordBean;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
public class BackupRecordBeanDao extends AbstractDao<BackupRecordBean, Long> {
    public static final String TABLENAME = "BACKUP_RECORD_BEAN";

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property BackupRecordTYpe = new Property(1, Integer.TYPE, "backupRecordTYpe", false, "BACKUP_RECORD_TYPE");
        public static final Property WalletName = new Property(2, String.class, "walletName", false, SignFailureFragment.WALLET_NAME);
        public static final Property WalletAddress = new Property(3, String.class, "walletAddress", false, Contract.View.KEY_WALLET_ADDRESS);
        public static final Property BackupStamp = new Property(4, Long.TYPE, "backupStamp", false, "BACKUP_STAMP");
        public static final Property HasShow = new Property(5, Boolean.TYPE, "hasShow", false, "HAS_SHOW");
    }

    @Override
    public final boolean isEntityUpdateable() {
        return true;
    }

    public BackupRecordBeanDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public BackupRecordBeanDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"BACKUP_RECORD_BEAN\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"BACKUP_RECORD_TYPE\" INTEGER NOT NULL ,\"WALLET_NAME\" TEXT,\"WALLET_ADDRESS\" TEXT,\"BACKUP_STAMP\" INTEGER NOT NULL ,\"HAS_SHOW\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"BACKUP_RECORD_BEAN\"");
        database.execSQL(sb.toString());
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, BackupRecordBean backupRecordBean) {
        databaseStatement.clearBindings();
        Long id = backupRecordBean.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindLong(2, backupRecordBean.getBackupRecordTYpe());
        String walletName = backupRecordBean.getWalletName();
        if (walletName != null) {
            databaseStatement.bindString(3, walletName);
        }
        String walletAddress = backupRecordBean.getWalletAddress();
        if (walletAddress != null) {
            databaseStatement.bindString(4, walletAddress);
        }
        databaseStatement.bindLong(5, backupRecordBean.getBackupStamp());
        databaseStatement.bindLong(6, backupRecordBean.getHasShow() ? 1L : 0L);
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, BackupRecordBean backupRecordBean) {
        sQLiteStatement.clearBindings();
        Long id = backupRecordBean.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindLong(2, backupRecordBean.getBackupRecordTYpe());
        String walletName = backupRecordBean.getWalletName();
        if (walletName != null) {
            sQLiteStatement.bindString(3, walletName);
        }
        String walletAddress = backupRecordBean.getWalletAddress();
        if (walletAddress != null) {
            sQLiteStatement.bindString(4, walletAddress);
        }
        sQLiteStatement.bindLong(5, backupRecordBean.getBackupStamp());
        sQLiteStatement.bindLong(6, backupRecordBean.getHasShow() ? 1L : 0L);
    }

    @Override
    public Long readKey(Cursor cursor, int i) {
        if (cursor.isNull(i)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i));
    }

    @Override
    public BackupRecordBean readEntity(Cursor cursor, int i) {
        Long valueOf = cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i));
        int i2 = cursor.getInt(i + 1);
        int i3 = i + 2;
        String string = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 3;
        return new BackupRecordBean(valueOf, i2, string, cursor.isNull(i4) ? null : cursor.getString(i4), cursor.getLong(i + 4), cursor.getShort(i + 5) != 0);
    }

    @Override
    public void readEntity(Cursor cursor, BackupRecordBean backupRecordBean, int i) {
        backupRecordBean.setId(cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i)));
        backupRecordBean.setBackupRecordTYpe(cursor.getInt(i + 1));
        int i2 = i + 2;
        backupRecordBean.setWalletName(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 3;
        backupRecordBean.setWalletAddress(cursor.isNull(i3) ? null : cursor.getString(i3));
        backupRecordBean.setBackupStamp(cursor.getLong(i + 4));
        backupRecordBean.setHasShow(cursor.getShort(i + 5) != 0);
    }

    @Override
    public final Long updateKeyAfterInsert(BackupRecordBean backupRecordBean, long j) {
        backupRecordBean.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override
    public Long getKey(BackupRecordBean backupRecordBean) {
        if (backupRecordBean != null) {
            return backupRecordBean.getId();
        }
        return null;
    }

    @Override
    public boolean hasKey(BackupRecordBean backupRecordBean) {
        return backupRecordBean.getId() != null;
    }
}
