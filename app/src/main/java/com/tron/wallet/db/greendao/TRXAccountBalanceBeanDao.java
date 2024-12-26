package com.tron.wallet.db.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.tron.wallet.business.pull.PullConstants;
import com.tron.wallet.db.bean.TRXAccountBalanceBean;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
public class TRXAccountBalanceBeanDao extends AbstractDao<TRXAccountBalanceBean, Long> {
    public static final String TABLENAME = "TRXACCOUNT_BALANCE_BEAN";

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Address = new Property(1, String.class, "address", false, "ADDRESS");
        public static final Property TotalTrx = new Property(2, Double.TYPE, "totalTrx", false, "TOTAL_TRX");
        public static final Property TrxNum = new Property(3, Double.TYPE, "trxNum", false, "TRX_NUM");
        public static final Property ChainId = new Property(4, String.class, PullConstants.CHAIN_ID, false, "CHAIN_ID");
        public static final Property AccountType = new Property(5, Integer.TYPE, "accountType", false, "ACCOUNT_TYPE");
        public static final Property NetType = new Property(6, String.class, "netType", false, "NET_TYPE");
    }

    @Override
    public final boolean isEntityUpdateable() {
        return true;
    }

    public TRXAccountBalanceBeanDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public TRXAccountBalanceBeanDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"TRXACCOUNT_BALANCE_BEAN\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"ADDRESS\" TEXT,\"TOTAL_TRX\" REAL NOT NULL ,\"TRX_NUM\" REAL NOT NULL ,\"CHAIN_ID\" TEXT,\"ACCOUNT_TYPE\" INTEGER NOT NULL ,\"NET_TYPE\" TEXT);");
        database.execSQL("CREATE UNIQUE INDEX " + str + "IDX_TRXACCOUNT_BALANCE_BEAN_ADDRESS ON \"TRXACCOUNT_BALANCE_BEAN\" (\"ADDRESS\" ASC);");
        database.execSQL("CREATE UNIQUE INDEX " + str + "IDX_TRXACCOUNT_BALANCE_BEAN_ADDRESS_DESC_NET_TYPE_DESC ON \"TRXACCOUNT_BALANCE_BEAN\" (\"ADDRESS\" DESC,\"NET_TYPE\" DESC);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"TRXACCOUNT_BALANCE_BEAN\"");
        database.execSQL(sb.toString());
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, TRXAccountBalanceBean tRXAccountBalanceBean) {
        databaseStatement.clearBindings();
        Long id = tRXAccountBalanceBean.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        String address = tRXAccountBalanceBean.getAddress();
        if (address != null) {
            databaseStatement.bindString(2, address);
        }
        databaseStatement.bindDouble(3, tRXAccountBalanceBean.getTotalTrx());
        databaseStatement.bindDouble(4, tRXAccountBalanceBean.getTrxNum());
        String chainId = tRXAccountBalanceBean.getChainId();
        if (chainId != null) {
            databaseStatement.bindString(5, chainId);
        }
        databaseStatement.bindLong(6, tRXAccountBalanceBean.getAccountType());
        String netType = tRXAccountBalanceBean.getNetType();
        if (netType != null) {
            databaseStatement.bindString(7, netType);
        }
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, TRXAccountBalanceBean tRXAccountBalanceBean) {
        sQLiteStatement.clearBindings();
        Long id = tRXAccountBalanceBean.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String address = tRXAccountBalanceBean.getAddress();
        if (address != null) {
            sQLiteStatement.bindString(2, address);
        }
        sQLiteStatement.bindDouble(3, tRXAccountBalanceBean.getTotalTrx());
        sQLiteStatement.bindDouble(4, tRXAccountBalanceBean.getTrxNum());
        String chainId = tRXAccountBalanceBean.getChainId();
        if (chainId != null) {
            sQLiteStatement.bindString(5, chainId);
        }
        sQLiteStatement.bindLong(6, tRXAccountBalanceBean.getAccountType());
        String netType = tRXAccountBalanceBean.getNetType();
        if (netType != null) {
            sQLiteStatement.bindString(7, netType);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int i) {
        if (cursor.isNull(i)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i));
    }

    @Override
    public TRXAccountBalanceBean readEntity(Cursor cursor, int i) {
        Long valueOf = cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i));
        int i2 = i + 1;
        String string = cursor.isNull(i2) ? null : cursor.getString(i2);
        double d = cursor.getDouble(i + 2);
        double d2 = cursor.getDouble(i + 3);
        int i3 = i + 4;
        String string2 = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = cursor.getInt(i + 5);
        int i5 = i + 6;
        return new TRXAccountBalanceBean(valueOf, string, d, d2, string2, i4, cursor.isNull(i5) ? null : cursor.getString(i5));
    }

    @Override
    public void readEntity(Cursor cursor, TRXAccountBalanceBean tRXAccountBalanceBean, int i) {
        tRXAccountBalanceBean.setId(cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i)));
        int i2 = i + 1;
        tRXAccountBalanceBean.setAddress(cursor.isNull(i2) ? null : cursor.getString(i2));
        tRXAccountBalanceBean.setTotalTrx(cursor.getDouble(i + 2));
        tRXAccountBalanceBean.setTrxNum(cursor.getDouble(i + 3));
        int i3 = i + 4;
        tRXAccountBalanceBean.setChainId(cursor.isNull(i3) ? null : cursor.getString(i3));
        tRXAccountBalanceBean.setAccountType(cursor.getInt(i + 5));
        int i4 = i + 6;
        tRXAccountBalanceBean.setNetType(cursor.isNull(i4) ? null : cursor.getString(i4));
    }

    @Override
    public final Long updateKeyAfterInsert(TRXAccountBalanceBean tRXAccountBalanceBean, long j) {
        tRXAccountBalanceBean.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override
    public Long getKey(TRXAccountBalanceBean tRXAccountBalanceBean) {
        if (tRXAccountBalanceBean != null) {
            return tRXAccountBalanceBean.getId();
        }
        return null;
    }

    @Override
    public boolean hasKey(TRXAccountBalanceBean tRXAccountBalanceBean) {
        return tRXAccountBalanceBean.getId() != null;
    }
}
