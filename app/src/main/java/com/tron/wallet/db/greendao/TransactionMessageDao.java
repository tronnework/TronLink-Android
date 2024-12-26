package com.tron.wallet.db.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.tron.wallet.business.addassets2.AssetsConfig;
import com.tron.wallet.business.message.TransactionMessage;
import com.tron.wallet.business.tokendetail.mvp.TokenDetailContentFragment;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
public class TransactionMessageDao extends AbstractDao<TransactionMessage, Long> {
    public static final String TABLENAME = "TRANSACTION_MESSAGE";

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property TxId = new Property(1, String.class, "txId", false, "TX_ID");
        public static final Property FromAddress = new Property(2, String.class, "fromAddress", false, "FROM_ADDRESS");
        public static final Property ToAddress = new Property(3, String.class, "toAddress", false, "TO_ADDRESS");
        public static final Property TokenAddress = new Property(4, String.class, "tokenAddress", false, "TOKEN_ADDRESS");
        public static final Property TokenSymbol = new Property(5, String.class, AssetsConfig.TOKEN_SYMBOL, false, "TOKEN_SYMBOL");
        public static final Property Amount = new Property(6, String.class, "amount", false, "AMOUNT");
        public static final Property Decimal = new Property(7, Long.TYPE, "decimal", false, "DECIMAL");
        public static final Property AssetId = new Property(8, String.class, "assetId", false, "ASSET_ID");
        public static final Property BlockNumber = new Property(9, Long.TYPE, "blockNumber", false, "BLOCK_NUMBER");
        public static final Property TransferTime = new Property(10, Long.TYPE, "transferTime", false, "TRANSFER_TIME");
        public static final Property Note = new Property(11, String.class, "note", false, "NOTE");
        public static final Property TokenType = new Property(12, Integer.TYPE, AssetsConfig.TOKEN_TYPE, false, TokenDetailContentFragment.TOKEN_TYPE);
        public static final Property Type = new Property(13, Integer.TYPE, "type", false, "TYPE");
        public static final Property State = new Property(14, Integer.TYPE, "state", false, "STATE");
        public static final Property Revert = new Property(15, Integer.TYPE, "revert", false, TransactionMessage.TYPE_REVERT);
        public static final Property Contract_ret = new Property(16, String.class, "contract_ret", false, "CONTRACT_RET");
    }

    @Override
    public final boolean isEntityUpdateable() {
        return true;
    }

    public TransactionMessageDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public TransactionMessageDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"TRANSACTION_MESSAGE\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"TX_ID\" TEXT,\"FROM_ADDRESS\" TEXT,\"TO_ADDRESS\" TEXT,\"TOKEN_ADDRESS\" TEXT,\"TOKEN_SYMBOL\" TEXT,\"AMOUNT\" TEXT,\"DECIMAL\" INTEGER NOT NULL ,\"ASSET_ID\" TEXT,\"BLOCK_NUMBER\" INTEGER NOT NULL ,\"TRANSFER_TIME\" INTEGER NOT NULL ,\"NOTE\" TEXT,\"TOKEN_TYPE\" INTEGER NOT NULL ,\"TYPE\" INTEGER NOT NULL ,\"STATE\" INTEGER NOT NULL ,\"REVERT\" INTEGER NOT NULL ,\"CONTRACT_RET\" TEXT);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"TRANSACTION_MESSAGE\"");
        database.execSQL(sb.toString());
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, TransactionMessage transactionMessage) {
        databaseStatement.clearBindings();
        Long id = transactionMessage.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        String txId = transactionMessage.getTxId();
        if (txId != null) {
            databaseStatement.bindString(2, txId);
        }
        String fromAddress = transactionMessage.getFromAddress();
        if (fromAddress != null) {
            databaseStatement.bindString(3, fromAddress);
        }
        String toAddress = transactionMessage.getToAddress();
        if (toAddress != null) {
            databaseStatement.bindString(4, toAddress);
        }
        String tokenAddress = transactionMessage.getTokenAddress();
        if (tokenAddress != null) {
            databaseStatement.bindString(5, tokenAddress);
        }
        String tokenSymbol = transactionMessage.getTokenSymbol();
        if (tokenSymbol != null) {
            databaseStatement.bindString(6, tokenSymbol);
        }
        String amount = transactionMessage.getAmount();
        if (amount != null) {
            databaseStatement.bindString(7, amount);
        }
        databaseStatement.bindLong(8, transactionMessage.getDecimal());
        String assetId = transactionMessage.getAssetId();
        if (assetId != null) {
            databaseStatement.bindString(9, assetId);
        }
        databaseStatement.bindLong(10, transactionMessage.getBlockNumber());
        databaseStatement.bindLong(11, transactionMessage.getTransferTime());
        String note = transactionMessage.getNote();
        if (note != null) {
            databaseStatement.bindString(12, note);
        }
        databaseStatement.bindLong(13, transactionMessage.getTokenType());
        databaseStatement.bindLong(14, transactionMessage.getType());
        databaseStatement.bindLong(15, transactionMessage.getState());
        databaseStatement.bindLong(16, transactionMessage.getRevert());
        String contract_ret = transactionMessage.getContract_ret();
        if (contract_ret != null) {
            databaseStatement.bindString(17, contract_ret);
        }
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, TransactionMessage transactionMessage) {
        sQLiteStatement.clearBindings();
        Long id = transactionMessage.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String txId = transactionMessage.getTxId();
        if (txId != null) {
            sQLiteStatement.bindString(2, txId);
        }
        String fromAddress = transactionMessage.getFromAddress();
        if (fromAddress != null) {
            sQLiteStatement.bindString(3, fromAddress);
        }
        String toAddress = transactionMessage.getToAddress();
        if (toAddress != null) {
            sQLiteStatement.bindString(4, toAddress);
        }
        String tokenAddress = transactionMessage.getTokenAddress();
        if (tokenAddress != null) {
            sQLiteStatement.bindString(5, tokenAddress);
        }
        String tokenSymbol = transactionMessage.getTokenSymbol();
        if (tokenSymbol != null) {
            sQLiteStatement.bindString(6, tokenSymbol);
        }
        String amount = transactionMessage.getAmount();
        if (amount != null) {
            sQLiteStatement.bindString(7, amount);
        }
        sQLiteStatement.bindLong(8, transactionMessage.getDecimal());
        String assetId = transactionMessage.getAssetId();
        if (assetId != null) {
            sQLiteStatement.bindString(9, assetId);
        }
        sQLiteStatement.bindLong(10, transactionMessage.getBlockNumber());
        sQLiteStatement.bindLong(11, transactionMessage.getTransferTime());
        String note = transactionMessage.getNote();
        if (note != null) {
            sQLiteStatement.bindString(12, note);
        }
        sQLiteStatement.bindLong(13, transactionMessage.getTokenType());
        sQLiteStatement.bindLong(14, transactionMessage.getType());
        sQLiteStatement.bindLong(15, transactionMessage.getState());
        sQLiteStatement.bindLong(16, transactionMessage.getRevert());
        String contract_ret = transactionMessage.getContract_ret();
        if (contract_ret != null) {
            sQLiteStatement.bindString(17, contract_ret);
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
    public TransactionMessage readEntity(Cursor cursor, int i) {
        Long valueOf = cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i));
        int i2 = i + 1;
        String string = cursor.isNull(i2) ? null : cursor.getString(i2);
        int i3 = i + 2;
        String string2 = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 3;
        String string3 = cursor.isNull(i4) ? null : cursor.getString(i4);
        int i5 = i + 4;
        String string4 = cursor.isNull(i5) ? null : cursor.getString(i5);
        int i6 = i + 5;
        String string5 = cursor.isNull(i6) ? null : cursor.getString(i6);
        int i7 = i + 6;
        String string6 = cursor.isNull(i7) ? null : cursor.getString(i7);
        long j = cursor.getLong(i + 7);
        int i8 = i + 8;
        String string7 = cursor.isNull(i8) ? null : cursor.getString(i8);
        long j2 = cursor.getLong(i + 9);
        long j3 = cursor.getLong(i + 10);
        int i9 = i + 11;
        String string8 = cursor.isNull(i9) ? null : cursor.getString(i9);
        int i10 = i + 16;
        return new TransactionMessage(valueOf, string, string2, string3, string4, string5, string6, j, string7, j2, j3, string8, cursor.getInt(i + 12), cursor.getInt(i + 13), cursor.getInt(i + 14), cursor.getInt(i + 15), cursor.isNull(i10) ? null : cursor.getString(i10));
    }

    @Override
    public void readEntity(Cursor cursor, TransactionMessage transactionMessage, int i) {
        transactionMessage.setId(cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i)));
        int i2 = i + 1;
        transactionMessage.setTxId(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 2;
        transactionMessage.setFromAddress(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 3;
        transactionMessage.setToAddress(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 4;
        transactionMessage.setTokenAddress(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 5;
        transactionMessage.setTokenSymbol(cursor.isNull(i6) ? null : cursor.getString(i6));
        int i7 = i + 6;
        transactionMessage.setAmount(cursor.isNull(i7) ? null : cursor.getString(i7));
        transactionMessage.setDecimal(cursor.getLong(i + 7));
        int i8 = i + 8;
        transactionMessage.setAssetId(cursor.isNull(i8) ? null : cursor.getString(i8));
        transactionMessage.setBlockNumber(cursor.getLong(i + 9));
        transactionMessage.setTransferTime(cursor.getLong(i + 10));
        int i9 = i + 11;
        transactionMessage.setNote(cursor.isNull(i9) ? null : cursor.getString(i9));
        transactionMessage.setTokenType(cursor.getInt(i + 12));
        transactionMessage.setType(cursor.getInt(i + 13));
        transactionMessage.setState(cursor.getInt(i + 14));
        transactionMessage.setRevert(cursor.getInt(i + 15));
        int i10 = i + 16;
        transactionMessage.setContract_ret(cursor.isNull(i10) ? null : cursor.getString(i10));
    }

    @Override
    public final Long updateKeyAfterInsert(TransactionMessage transactionMessage, long j) {
        transactionMessage.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override
    public Long getKey(TransactionMessage transactionMessage) {
        if (transactionMessage != null) {
            return transactionMessage.getId();
        }
        return null;
    }

    @Override
    public boolean hasKey(TransactionMessage transactionMessage) {
        return transactionMessage.getId() != null;
    }
}
