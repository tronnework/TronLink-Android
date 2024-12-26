package com.tron.wallet.db.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.tron.wallet.business.addassets2.bean.asset.AssetsHomePriceBean;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
public class AssetsHomePriceBeanDao extends AbstractDao<AssetsHomePriceBean, Void> {
    public static final String TABLENAME = "ASSETS_HOME_PRICE_BEAN";

    public static class Properties {
        public static final Property PriceCny = new Property(0, Double.TYPE, "priceCny", false, "PRICE_CNY");
        public static final Property PriceUSD = new Property(1, Double.TYPE, "priceUSD", false, "PRICE_USD");
    }

    @Override
    public Void getKey(AssetsHomePriceBean assetsHomePriceBean) {
        return null;
    }

    @Override
    public boolean hasKey(AssetsHomePriceBean assetsHomePriceBean) {
        return false;
    }

    @Override
    public final boolean isEntityUpdateable() {
        return true;
    }

    @Override
    public Void readKey(Cursor cursor, int i) {
        return null;
    }

    @Override
    public final Void updateKeyAfterInsert(AssetsHomePriceBean assetsHomePriceBean, long j) {
        return null;
    }

    public AssetsHomePriceBeanDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public AssetsHomePriceBeanDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"ASSETS_HOME_PRICE_BEAN\" (\"PRICE_CNY\" REAL NOT NULL ,\"PRICE_USD\" REAL NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"ASSETS_HOME_PRICE_BEAN\"");
        database.execSQL(sb.toString());
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, AssetsHomePriceBean assetsHomePriceBean) {
        databaseStatement.clearBindings();
        databaseStatement.bindDouble(1, assetsHomePriceBean.getPriceCny());
        databaseStatement.bindDouble(2, assetsHomePriceBean.getPriceUSD());
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, AssetsHomePriceBean assetsHomePriceBean) {
        sQLiteStatement.clearBindings();
        sQLiteStatement.bindDouble(1, assetsHomePriceBean.getPriceCny());
        sQLiteStatement.bindDouble(2, assetsHomePriceBean.getPriceUSD());
    }

    @Override
    public AssetsHomePriceBean readEntity(Cursor cursor, int i) {
        return new AssetsHomePriceBean(cursor.getDouble(i), cursor.getDouble(i + 1));
    }

    @Override
    public void readEntity(Cursor cursor, AssetsHomePriceBean assetsHomePriceBean, int i) {
        assetsHomePriceBean.setPriceCny(cursor.getDouble(i));
        assetsHomePriceBean.setPriceUSD(cursor.getDouble(i + 1));
    }
}
