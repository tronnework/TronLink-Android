package com.tron.wallet.db.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.tron.wallet.db.bean.AddressDao;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
public class AddressDaoDao extends AbstractDao<AddressDao, Long> {
    public static final String TABLENAME = "ADDRESS_DAO";

    public static class Properties {
        public static final Property Name = new Property(0, String.class, AppMeasurementSdk.ConditionalUserProperty.NAME, false, "NAME");
        public static final Property Address = new Property(1, String.class, "address", false, "ADDRESS");
        public static final Property Description = new Property(2, String.class, "description", false, "DESCRIPTION");
        public static final Property Id = new Property(3, Long.class, "id", true, "_id");
    }

    @Override
    public final boolean isEntityUpdateable() {
        return true;
    }

    public AddressDaoDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public AddressDaoDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"ADDRESS_DAO\" (\"NAME\" TEXT,\"ADDRESS\" TEXT,\"DESCRIPTION\" TEXT,\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT );");
        database.execSQL("CREATE UNIQUE INDEX " + str + "IDX_ADDRESS_DAO_ADDRESS ON \"ADDRESS_DAO\" (\"ADDRESS\" ASC);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"ADDRESS_DAO\"");
        database.execSQL(sb.toString());
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, AddressDao addressDao) {
        databaseStatement.clearBindings();
        String name = addressDao.getName();
        if (name != null) {
            databaseStatement.bindString(1, name);
        }
        String address = addressDao.getAddress();
        if (address != null) {
            databaseStatement.bindString(2, address);
        }
        String description = addressDao.getDescription();
        if (description != null) {
            databaseStatement.bindString(3, description);
        }
        Long id = addressDao.getId();
        if (id != null) {
            databaseStatement.bindLong(4, id.longValue());
        }
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, AddressDao addressDao) {
        sQLiteStatement.clearBindings();
        String name = addressDao.getName();
        if (name != null) {
            sQLiteStatement.bindString(1, name);
        }
        String address = addressDao.getAddress();
        if (address != null) {
            sQLiteStatement.bindString(2, address);
        }
        String description = addressDao.getDescription();
        if (description != null) {
            sQLiteStatement.bindString(3, description);
        }
        Long id = addressDao.getId();
        if (id != null) {
            sQLiteStatement.bindLong(4, id.longValue());
        }
    }

    @Override
    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 3;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    @Override
    public AddressDao readEntity(Cursor cursor, int i) {
        String string = cursor.isNull(i) ? null : cursor.getString(i);
        int i2 = i + 1;
        int i3 = i + 2;
        int i4 = i + 3;
        return new AddressDao(string, cursor.isNull(i2) ? null : cursor.getString(i2), cursor.isNull(i3) ? null : cursor.getString(i3), cursor.isNull(i4) ? null : Long.valueOf(cursor.getLong(i4)));
    }

    @Override
    public void readEntity(Cursor cursor, AddressDao addressDao, int i) {
        addressDao.setName(cursor.isNull(i) ? null : cursor.getString(i));
        int i2 = i + 1;
        addressDao.setAddress(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 2;
        addressDao.setDescription(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 3;
        addressDao.setId(cursor.isNull(i4) ? null : Long.valueOf(cursor.getLong(i4)));
    }

    @Override
    public final Long updateKeyAfterInsert(AddressDao addressDao, long j) {
        addressDao.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override
    public Long getKey(AddressDao addressDao) {
        if (addressDao != null) {
            return addressDao.getId();
        }
        return null;
    }

    @Override
    public boolean hasKey(AddressDao addressDao) {
        return addressDao.getId() != null;
    }
}
