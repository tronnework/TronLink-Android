package com.tron.wallet.db.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.tron.wallet.business.addassets2.bean.TokenSortBean;
import com.tron.wallet.business.nft.contract.Contract;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
public class TokenSortBeanDao extends AbstractDao<TokenSortBean, Long> {
    public static final String TABLENAME = "TOKEN_SORT_BEAN";

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Type = new Property(1, Integer.TYPE, "type", false, "TYPE");
        public static final Property Index = new Property(2, Long.TYPE, "index", false, "INDEX");
        public static final Property ContractAddress = new Property(3, String.class, "contractAddress", false, Contract.View.KEY_CONTRACT_ADDRESS);
        public static final Property Address = new Property(4, String.class, "address", false, "ADDRESS");
    }

    @Override
    public final boolean isEntityUpdateable() {
        return true;
    }

    public TokenSortBeanDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public TokenSortBeanDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"TOKEN_SORT_BEAN\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"TYPE\" INTEGER NOT NULL ,\"INDEX\" INTEGER NOT NULL ,\"CONTRACT_ADDRESS\" TEXT,\"ADDRESS\" TEXT);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"TOKEN_SORT_BEAN\"");
        database.execSQL(sb.toString());
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, TokenSortBean tokenSortBean) {
        databaseStatement.clearBindings();
        Long id = tokenSortBean.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindLong(2, tokenSortBean.getType());
        databaseStatement.bindLong(3, tokenSortBean.getIndex());
        String contractAddress = tokenSortBean.getContractAddress();
        if (contractAddress != null) {
            databaseStatement.bindString(4, contractAddress);
        }
        String address = tokenSortBean.getAddress();
        if (address != null) {
            databaseStatement.bindString(5, address);
        }
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, TokenSortBean tokenSortBean) {
        sQLiteStatement.clearBindings();
        Long id = tokenSortBean.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindLong(2, tokenSortBean.getType());
        sQLiteStatement.bindLong(3, tokenSortBean.getIndex());
        String contractAddress = tokenSortBean.getContractAddress();
        if (contractAddress != null) {
            sQLiteStatement.bindString(4, contractAddress);
        }
        String address = tokenSortBean.getAddress();
        if (address != null) {
            sQLiteStatement.bindString(5, address);
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
    public TokenSortBean readEntity(Cursor cursor, int i) {
        Long valueOf = cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i));
        int i2 = cursor.getInt(i + 1);
        long j = cursor.getLong(i + 2);
        int i3 = i + 3;
        int i4 = i + 4;
        return new TokenSortBean(valueOf, i2, j, cursor.isNull(i3) ? null : cursor.getString(i3), cursor.isNull(i4) ? null : cursor.getString(i4));
    }

    @Override
    public void readEntity(Cursor cursor, TokenSortBean tokenSortBean, int i) {
        tokenSortBean.setId(cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i)));
        tokenSortBean.setType(cursor.getInt(i + 1));
        tokenSortBean.setIndex(cursor.getLong(i + 2));
        int i2 = i + 3;
        tokenSortBean.setContractAddress(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 4;
        tokenSortBean.setAddress(cursor.isNull(i3) ? null : cursor.getString(i3));
    }

    @Override
    public final Long updateKeyAfterInsert(TokenSortBean tokenSortBean, long j) {
        tokenSortBean.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override
    public Long getKey(TokenSortBean tokenSortBean) {
        if (tokenSortBean != null) {
            return tokenSortBean.getId();
        }
        return null;
    }

    @Override
    public boolean hasKey(TokenSortBean tokenSortBean) {
        return tokenSortBean.getId() != null;
    }
}
