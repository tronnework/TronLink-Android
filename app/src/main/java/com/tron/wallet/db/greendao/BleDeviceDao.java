package com.tron.wallet.db.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.tron.wallet.business.walletmanager.importwallet.base.repo.entity.BleRepoDevice;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
public class BleDeviceDao extends AbstractDao<BleRepoDevice, Long> {
    public static final String TABLENAME = "BLE_DEVICE";

    public static class Properties {
        public static final Property Id = new Property(0, Long.TYPE, "id", true, "_id");
        public static final Property Mac = new Property(1, String.class, "mac", false, "MAC");
        public static final Property Name = new Property(2, String.class, AppMeasurementSdk.ConditionalUserProperty.NAME, false, "NAME");
    }

    @Override
    public final boolean isEntityUpdateable() {
        return true;
    }

    public BleDeviceDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public BleDeviceDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"BLE_DEVICE\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,\"MAC\" TEXT UNIQUE ,\"NAME\" TEXT);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"BLE_DEVICE\"");
        database.execSQL(sb.toString());
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, BleRepoDevice bleRepoDevice) {
        databaseStatement.clearBindings();
        databaseStatement.bindLong(1, bleRepoDevice.getId().longValue());
        String mac = bleRepoDevice.getMac();
        if (mac != null) {
            databaseStatement.bindString(2, mac);
        }
        String name = bleRepoDevice.getName();
        if (name != null) {
            databaseStatement.bindString(3, name);
        }
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, BleRepoDevice bleRepoDevice) {
        sQLiteStatement.clearBindings();
        sQLiteStatement.bindLong(1, bleRepoDevice.getId().longValue());
        String mac = bleRepoDevice.getMac();
        if (mac != null) {
            sQLiteStatement.bindString(2, mac);
        }
        String name = bleRepoDevice.getName();
        if (name != null) {
            sQLiteStatement.bindString(3, name);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int i) {
        return Long.valueOf(cursor.getLong(i));
    }

    @Override
    public BleRepoDevice readEntity(Cursor cursor, int i) {
        Long valueOf = Long.valueOf(cursor.getLong(i));
        int i2 = i + 1;
        int i3 = i + 2;
        return new BleRepoDevice(valueOf, cursor.isNull(i2) ? null : cursor.getString(i2), cursor.isNull(i3) ? null : cursor.getString(i3));
    }

    @Override
    public void readEntity(Cursor cursor, BleRepoDevice bleRepoDevice, int i) {
        bleRepoDevice.setId(Long.valueOf(cursor.getLong(i)));
        int i2 = i + 1;
        bleRepoDevice.setMac(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 2;
        bleRepoDevice.setName(cursor.isNull(i3) ? null : cursor.getString(i3));
    }

    @Override
    public final Long updateKeyAfterInsert(BleRepoDevice bleRepoDevice, long j) {
        bleRepoDevice.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override
    public Long getKey(BleRepoDevice bleRepoDevice) {
        if (bleRepoDevice != null) {
            return bleRepoDevice.getId();
        }
        return null;
    }

    @Override
    public boolean hasKey(BleRepoDevice bleRepoDevice) {
        


return true;

//throw new UnsupportedOperationException(
Unsupported for entities with a non-null key");
    }
}
