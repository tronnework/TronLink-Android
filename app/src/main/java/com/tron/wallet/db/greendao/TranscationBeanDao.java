package com.tron.wallet.db.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import androidx.core.app.NotificationCompat;
import com.tron.wallet.db.bean.TranscationBean;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
public class TranscationBeanDao extends AbstractDao<TranscationBean, String> {
    public static final String TABLENAME = "TRANSCATION_BEAN";

    public static class Properties {
        public static final Property Hash = new Property(0, String.class, "hash", true, "HASH");
        public static final Property Amount = new Property(1, String.class, "amount", false, "AMOUNT");
        public static final Property Block = new Property(2, Integer.TYPE, "block", false, "BLOCK");
        public static final Property Block_timestamp = new Property(3, Long.TYPE, "block_timestamp", false, "BLOCK_TIMESTAMP");
        public static final Property Confirmed = new Property(4, Integer.TYPE, "confirmed", false, "CONFIRMED");
        public static final Property Contract_type = new Property(5, String.class, "contract_type", false, "CONTRACT_TYPE");
        public static final Property Decimals = new Property(6, Integer.TYPE, "decimals", false, "DECIMALS");
        public static final Property Direction = new Property(7, Integer.TYPE, "direction", false, "DIRECTION");
        public static final Property From = new Property(8, String.class, "from", false, "FROM");
        public static final Property To = new Property(9, String.class, "to", false, "TO");
        public static final Property Issue_address = new Property(10, String.class, "issue_address", false, "ISSUE_ADDRESS");
        public static final Property Symbol = new Property(11, String.class, "symbol", false, "SYMBOL");
        public static final Property Token_name = new Property(12, String.class, "token_name", false, "TOKEN_NAME");
        public static final Property Status = new Property(13, Integer.TYPE, NotificationCompat.CATEGORY_STATUS, false, "STATUS");
    }

    @Override
    public final boolean isEntityUpdateable() {
        return true;
    }

    public TranscationBeanDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public TranscationBeanDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"TRANSCATION_BEAN\" (\"HASH\" TEXT PRIMARY KEY NOT NULL ,\"AMOUNT\" TEXT,\"BLOCK\" INTEGER NOT NULL ,\"BLOCK_TIMESTAMP\" INTEGER NOT NULL ,\"CONFIRMED\" INTEGER NOT NULL ,\"CONTRACT_TYPE\" TEXT,\"DECIMALS\" INTEGER NOT NULL ,\"DIRECTION\" INTEGER NOT NULL ,\"FROM\" TEXT,\"TO\" TEXT,\"ISSUE_ADDRESS\" TEXT,\"SYMBOL\" TEXT,\"TOKEN_NAME\" TEXT,\"STATUS\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"TRANSCATION_BEAN\"");
        database.execSQL(sb.toString());
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, TranscationBean transcationBean) {
        databaseStatement.clearBindings();
        String hash = transcationBean.getHash();
        if (hash != null) {
            databaseStatement.bindString(1, hash);
        }
        String amount = transcationBean.getAmount();
        if (amount != null) {
            databaseStatement.bindString(2, amount);
        }
        databaseStatement.bindLong(3, transcationBean.getBlock());
        databaseStatement.bindLong(4, transcationBean.getBlock_timestamp());
        databaseStatement.bindLong(5, transcationBean.getConfirmed());
        String contract_type = transcationBean.getContract_type();
        if (contract_type != null) {
            databaseStatement.bindString(6, contract_type);
        }
        databaseStatement.bindLong(7, transcationBean.getDecimals());
        databaseStatement.bindLong(8, transcationBean.getDirection());
        String from = transcationBean.getFrom();
        if (from != null) {
            databaseStatement.bindString(9, from);
        }
        String to = transcationBean.getTo();
        if (to != null) {
            databaseStatement.bindString(10, to);
        }
        String issue_address = transcationBean.getIssue_address();
        if (issue_address != null) {
            databaseStatement.bindString(11, issue_address);
        }
        String symbol = transcationBean.getSymbol();
        if (symbol != null) {
            databaseStatement.bindString(12, symbol);
        }
        String token_name = transcationBean.getToken_name();
        if (token_name != null) {
            databaseStatement.bindString(13, token_name);
        }
        databaseStatement.bindLong(14, transcationBean.getStatus());
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, TranscationBean transcationBean) {
        sQLiteStatement.clearBindings();
        String hash = transcationBean.getHash();
        if (hash != null) {
            sQLiteStatement.bindString(1, hash);
        }
        String amount = transcationBean.getAmount();
        if (amount != null) {
            sQLiteStatement.bindString(2, amount);
        }
        sQLiteStatement.bindLong(3, transcationBean.getBlock());
        sQLiteStatement.bindLong(4, transcationBean.getBlock_timestamp());
        sQLiteStatement.bindLong(5, transcationBean.getConfirmed());
        String contract_type = transcationBean.getContract_type();
        if (contract_type != null) {
            sQLiteStatement.bindString(6, contract_type);
        }
        sQLiteStatement.bindLong(7, transcationBean.getDecimals());
        sQLiteStatement.bindLong(8, transcationBean.getDirection());
        String from = transcationBean.getFrom();
        if (from != null) {
            sQLiteStatement.bindString(9, from);
        }
        String to = transcationBean.getTo();
        if (to != null) {
            sQLiteStatement.bindString(10, to);
        }
        String issue_address = transcationBean.getIssue_address();
        if (issue_address != null) {
            sQLiteStatement.bindString(11, issue_address);
        }
        String symbol = transcationBean.getSymbol();
        if (symbol != null) {
            sQLiteStatement.bindString(12, symbol);
        }
        String token_name = transcationBean.getToken_name();
        if (token_name != null) {
            sQLiteStatement.bindString(13, token_name);
        }
        sQLiteStatement.bindLong(14, transcationBean.getStatus());
    }

    @Override
    public String readKey(Cursor cursor, int i) {
        if (cursor.isNull(i)) {
            return null;
        }
        return cursor.getString(i);
    }

    @Override
    public TranscationBean readEntity(Cursor cursor, int i) {
        int i2 = i + 1;
        int i3 = i + 5;
        int i4 = i + 8;
        int i5 = i + 9;
        int i6 = i + 10;
        int i7 = i + 11;
        int i8 = i + 12;
        return new TranscationBean(cursor.isNull(i) ? null : cursor.getString(i), cursor.isNull(i2) ? null : cursor.getString(i2), cursor.getInt(i + 2), cursor.getLong(i + 3), cursor.getInt(i + 4), cursor.isNull(i3) ? null : cursor.getString(i3), cursor.getInt(i + 6), cursor.getInt(i + 7), cursor.isNull(i4) ? null : cursor.getString(i4), cursor.isNull(i5) ? null : cursor.getString(i5), cursor.isNull(i6) ? null : cursor.getString(i6), cursor.isNull(i7) ? null : cursor.getString(i7), cursor.isNull(i8) ? null : cursor.getString(i8), cursor.getInt(i + 13));
    }

    @Override
    public void readEntity(Cursor cursor, TranscationBean transcationBean, int i) {
        transcationBean.setHash(cursor.isNull(i) ? null : cursor.getString(i));
        int i2 = i + 1;
        transcationBean.setAmount(cursor.isNull(i2) ? null : cursor.getString(i2));
        transcationBean.setBlock(cursor.getInt(i + 2));
        transcationBean.setBlock_timestamp(cursor.getLong(i + 3));
        transcationBean.setConfirmed(cursor.getInt(i + 4));
        int i3 = i + 5;
        transcationBean.setContract_type(cursor.isNull(i3) ? null : cursor.getString(i3));
        transcationBean.setDecimals(cursor.getInt(i + 6));
        transcationBean.setDirection(cursor.getInt(i + 7));
        int i4 = i + 8;
        transcationBean.setFrom(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 9;
        transcationBean.setTo(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 10;
        transcationBean.setIssue_address(cursor.isNull(i6) ? null : cursor.getString(i6));
        int i7 = i + 11;
        transcationBean.setSymbol(cursor.isNull(i7) ? null : cursor.getString(i7));
        int i8 = i + 12;
        transcationBean.setToken_name(cursor.isNull(i8) ? null : cursor.getString(i8));
        transcationBean.setStatus(cursor.getInt(i + 13));
    }

    @Override
    public final String updateKeyAfterInsert(TranscationBean transcationBean, long j) {
        return transcationBean.getHash();
    }

    @Override
    public String getKey(TranscationBean transcationBean) {
        if (transcationBean != null) {
            return transcationBean.getHash();
        }
        return null;
    }

    @Override
    public boolean hasKey(TranscationBean transcationBean) {
        return transcationBean.getHash() != null;
    }
}
