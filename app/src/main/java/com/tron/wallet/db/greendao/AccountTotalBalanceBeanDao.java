package com.tron.wallet.db.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.tron.wallet.business.pull.PullConstants;
import com.tron.wallet.db.bean.AccountTotalBalanceBean;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
public class AccountTotalBalanceBeanDao extends AbstractDao<AccountTotalBalanceBean, Long> {
    public static final String TABLENAME = "ACCOUNT_TOTAL_BALANCE_BEAN";

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Address = new Property(1, String.class, "address", false, "ADDRESS");
        public static final Property TotalTrx = new Property(2, Double.TYPE, "totalTrx", false, "TOTAL_TRX");
        public static final Property TrxNum = new Property(3, Double.TYPE, "trxNum", false, "TRX_NUM");
        public static final Property ChainId = new Property(4, String.class, PullConstants.CHAIN_ID, false, "CHAIN_ID");
        public static final Property NetType = new Property(5, String.class, "netType", false, "NET_TYPE");
    }

    @Override
    public final boolean isEntityUpdateable() {
        return true;
    }

    public AccountTotalBalanceBeanDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public AccountTotalBalanceBeanDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"ACCOUNT_TOTAL_BALANCE_BEAN\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"ADDRESS\" TEXT,\"TOTAL_TRX\" REAL NOT NULL ,\"TRX_NUM\" REAL NOT NULL ,\"CHAIN_ID\" TEXT,\"NET_TYPE\" TEXT);");
        database.execSQL("CREATE UNIQUE INDEX " + str + "IDX_ACCOUNT_TOTAL_BALANCE_BEAN_ADDRESS ON \"ACCOUNT_TOTAL_BALANCE_BEAN\" (\"ADDRESS\" ASC);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"ACCOUNT_TOTAL_BALANCE_BEAN\"");
        database.execSQL(sb.toString());
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, AccountTotalBalanceBean accountTotalBalanceBean) {
        databaseStatement.clearBindings();
        Long id = accountTotalBalanceBean.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        String address = accountTotalBalanceBean.getAddress();
        if (address != null) {
            databaseStatement.bindString(2, address);
        }
        databaseStatement.bindDouble(3, accountTotalBalanceBean.getTotalTrx());
        databaseStatement.bindDouble(4, accountTotalBalanceBean.getTrxNum());
        String chainId = accountTotalBalanceBean.getChainId();
        if (chainId != null) {
            databaseStatement.bindString(5, chainId);
        }
        String netType = accountTotalBalanceBean.getNetType();
        if (netType != null) {
            databaseStatement.bindString(6, netType);
        }
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, AccountTotalBalanceBean accountTotalBalanceBean) {
        sQLiteStatement.clearBindings();
        Long id = accountTotalBalanceBean.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String address = accountTotalBalanceBean.getAddress();
        if (address != null) {
            sQLiteStatement.bindString(2, address);
        }
        sQLiteStatement.bindDouble(3, accountTotalBalanceBean.getTotalTrx());
        sQLiteStatement.bindDouble(4, accountTotalBalanceBean.getTrxNum());
        String chainId = accountTotalBalanceBean.getChainId();
        if (chainId != null) {
            sQLiteStatement.bindString(5, chainId);
        }
        String netType = accountTotalBalanceBean.getNetType();
        if (netType != null) {
            sQLiteStatement.bindString(6, netType);
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
    public AccountTotalBalanceBean readEntity(Cursor cursor, int i) {
        Long valueOf = cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i));
        int i2 = i + 1;
        String string = cursor.isNull(i2) ? null : cursor.getString(i2);
        double d = cursor.getDouble(i + 2);
        double d2 = cursor.getDouble(i + 3);
        int i3 = i + 4;
        int i4 = i + 5;
        return new AccountTotalBalanceBean(valueOf, string, d, d2, cursor.isNull(i3) ? null : cursor.getString(i3), cursor.isNull(i4) ? null : cursor.getString(i4));
    }

    @Override
    public void readEntity(Cursor cursor, AccountTotalBalanceBean accountTotalBalanceBean, int i) {
        accountTotalBalanceBean.setId(cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i)));
        int i2 = i + 1;
        accountTotalBalanceBean.setAddress(cursor.isNull(i2) ? null : cursor.getString(i2));
        accountTotalBalanceBean.setTotalTrx(cursor.getDouble(i + 2));
        accountTotalBalanceBean.setTrxNum(cursor.getDouble(i + 3));
        int i3 = i + 4;
        accountTotalBalanceBean.setChainId(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 5;
        accountTotalBalanceBean.setNetType(cursor.isNull(i4) ? null : cursor.getString(i4));
    }

    @Override
    public final Long updateKeyAfterInsert(AccountTotalBalanceBean accountTotalBalanceBean, long j) {
        accountTotalBalanceBean.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override
    public Long getKey(AccountTotalBalanceBean accountTotalBalanceBean) {
        if (accountTotalBalanceBean != null) {
            return accountTotalBalanceBean.getId();
        }
        return null;
    }

    @Override
    public boolean hasKey(AccountTotalBalanceBean accountTotalBalanceBean) {
        return accountTotalBalanceBean.getId() != null;
    }
}
