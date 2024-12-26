package com.tron.wallet.db.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.tron.wallet.business.addassets2.bean.asset.AssetsHomeData;
import com.tron.wallet.business.addassets2.bean.asset.AssetsHomePriceBean;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
public class AssetsHomeDataDao extends AbstractDao<AssetsHomeData, Long> {
    public static final String TABLENAME = "ASSETS_HOME_DATA";
    private final AssetsHomeData.PriceConverter priceConverter;

    public static class Properties {
        public static final Property Id = new Property(0, Long.TYPE, "id", true, "_id");
        public static final Property TotalTRX = new Property(1, Double.TYPE, "totalTRX", false, "TOTAL_TRX");
        public static final Property Price = new Property(2, String.class, FirebaseAnalytics.Param.PRICE, false, "PRICE");
        public static final Property TotalUsd = new Property(3, String.class, "totalUsd", false, "TOTAL_USD");
        public static final Property TotalCny = new Property(4, String.class, "totalCny", false, "TOTAL_CNY");
        public static final Property Address = new Property(5, String.class, "address", false, "ADDRESS");
        public static final Property NodeId = new Property(6, String.class, "nodeId", false, "NODE_ID");
    }

    @Override
    public final boolean isEntityUpdateable() {
        return true;
    }

    public AssetsHomeDataDao(DaoConfig daoConfig) {
        super(daoConfig);
        this.priceConverter = new AssetsHomeData.PriceConverter();
    }

    public AssetsHomeDataDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.priceConverter = new AssetsHomeData.PriceConverter();
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"ASSETS_HOME_DATA\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,\"TOTAL_TRX\" REAL NOT NULL ,\"PRICE\" TEXT,\"TOTAL_USD\" TEXT,\"TOTAL_CNY\" TEXT,\"ADDRESS\" TEXT,\"NODE_ID\" TEXT);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"ASSETS_HOME_DATA\"");
        database.execSQL(sb.toString());
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, AssetsHomeData assetsHomeData) {
        databaseStatement.clearBindings();
        databaseStatement.bindLong(1, assetsHomeData.getId());
        databaseStatement.bindDouble(2, assetsHomeData.getTotalTRX());
        AssetsHomePriceBean price = assetsHomeData.getPrice();
        if (price != null) {
            databaseStatement.bindString(3, this.priceConverter.convertToDatabaseValue(price));
        }
        String totalUsd = assetsHomeData.getTotalUsd();
        if (totalUsd != null) {
            databaseStatement.bindString(4, totalUsd);
        }
        String totalCny = assetsHomeData.getTotalCny();
        if (totalCny != null) {
            databaseStatement.bindString(5, totalCny);
        }
        String address = assetsHomeData.getAddress();
        if (address != null) {
            databaseStatement.bindString(6, address);
        }
        String nodeId = assetsHomeData.getNodeId();
        if (nodeId != null) {
            databaseStatement.bindString(7, nodeId);
        }
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, AssetsHomeData assetsHomeData) {
        sQLiteStatement.clearBindings();
        sQLiteStatement.bindLong(1, assetsHomeData.getId());
        sQLiteStatement.bindDouble(2, assetsHomeData.getTotalTRX());
        AssetsHomePriceBean price = assetsHomeData.getPrice();
        if (price != null) {
            sQLiteStatement.bindString(3, this.priceConverter.convertToDatabaseValue(price));
        }
        String totalUsd = assetsHomeData.getTotalUsd();
        if (totalUsd != null) {
            sQLiteStatement.bindString(4, totalUsd);
        }
        String totalCny = assetsHomeData.getTotalCny();
        if (totalCny != null) {
            sQLiteStatement.bindString(5, totalCny);
        }
        String address = assetsHomeData.getAddress();
        if (address != null) {
            sQLiteStatement.bindString(6, address);
        }
        String nodeId = assetsHomeData.getNodeId();
        if (nodeId != null) {
            sQLiteStatement.bindString(7, nodeId);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int i) {
        return Long.valueOf(cursor.getLong(i));
    }

    @Override
    public AssetsHomeData readEntity(Cursor cursor, int i) {
        long j = cursor.getLong(i);
        double d = cursor.getDouble(i + 1);
        int i2 = i + 2;
        AssetsHomePriceBean convertToEntityProperty = cursor.isNull(i2) ? null : this.priceConverter.convertToEntityProperty(cursor.getString(i2));
        int i3 = i + 3;
        String string = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 4;
        String string2 = cursor.isNull(i4) ? null : cursor.getString(i4);
        int i5 = i + 5;
        int i6 = i + 6;
        return new AssetsHomeData(j, d, convertToEntityProperty, string, string2, cursor.isNull(i5) ? null : cursor.getString(i5), cursor.isNull(i6) ? null : cursor.getString(i6));
    }

    @Override
    public void readEntity(Cursor cursor, AssetsHomeData assetsHomeData, int i) {
        assetsHomeData.setId(cursor.getLong(i));
        assetsHomeData.setTotalTRX(cursor.getDouble(i + 1));
        int i2 = i + 2;
        assetsHomeData.setPrice(cursor.isNull(i2) ? null : this.priceConverter.convertToEntityProperty(cursor.getString(i2)));
        int i3 = i + 3;
        assetsHomeData.setTotalUsd(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 4;
        assetsHomeData.setTotalCny(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 5;
        assetsHomeData.setAddress(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 6;
        assetsHomeData.setNodeId(cursor.isNull(i6) ? null : cursor.getString(i6));
    }

    @Override
    public final Long updateKeyAfterInsert(AssetsHomeData assetsHomeData, long j) {
        assetsHomeData.setId(j);
        return Long.valueOf(j);
    }

    @Override
    public Long getKey(AssetsHomeData assetsHomeData) {
        if (assetsHomeData != null) {
            return Long.valueOf(assetsHomeData.getId());
        }
        return null;
    }

    @Override
    public boolean hasKey(AssetsHomeData assetsHomeData) {
        


return true;

//throw new UnsupportedOperationException(
Unsupported for entities with a non-null key");
    }
}
