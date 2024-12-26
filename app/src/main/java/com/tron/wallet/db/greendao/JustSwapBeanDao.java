package com.tron.wallet.db.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import androidx.core.app.NotificationCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.tron.wallet.db.bean.JustSwapBean;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
public class JustSwapBeanDao extends AbstractDao<JustSwapBean, Long> {
    public static final String TABLENAME = "JUST_SWAP_BEAN";

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property BlockNum = new Property(1, Long.TYPE, "blockNum", false, "BLOCK_NUM");
        public static final Property Hash = new Property(2, String.class, "hash", false, "HASH");
        public static final Property SymbolIn = new Property(3, String.class, "symbolIn", false, "SYMBOL_IN");
        public static final Property DecimalsIn = new Property(4, String.class, "decimalsIn", false, "DECIMALS_IN");
        public static final Property TokenAddressIn = new Property(5, String.class, "tokenAddressIn", false, "TOKEN_ADDRESS_IN");
        public static final Property Address = new Property(6, String.class, "address", false, "ADDRESS");
        public static final Property ContractInAddress = new Property(7, String.class, "contractInAddress", false, "CONTRACT_IN_ADDRESS");
        public static final Property AmountIn = new Property(8, String.class, "amountIn", false, "AMOUNT_IN");
        public static final Property Fee = new Property(9, String.class, "fee", false, "FEE");
        public static final Property Rate = new Property(10, String.class, "rate", false, "RATE");
        public static final Property Amountout = new Property(11, String.class, "amountout", false, "AMOUNTOUT");
        public static final Property ContractOutAddress = new Property(12, String.class, "contractOutAddress", false, "CONTRACT_OUT_ADDRESS");
        public static final Property ExchargeAddress = new Property(13, String.class, "exchargeAddress", false, "EXCHARGE_ADDRESS");
        public static final Property TokenAddressOut = new Property(14, String.class, "tokenAddressOut", false, "TOKEN_ADDRESS_OUT");
        public static final Property Method = new Property(15, String.class, FirebaseAnalytics.Param.METHOD, false, "METHOD");
        public static final Property SymbolOut = new Property(16, String.class, "symbolOut", false, "SYMBOL_OUT");
        public static final Property DecimalsOut = new Property(17, String.class, "decimalsOut", false, "DECIMALS_OUT");
        public static final Property MinReceived = new Property(18, String.class, "minReceived", false, "MIN_RECEIVED");
        public static final Property PriceImpact = new Property(19, String.class, "priceImpact", false, "PRICE_IMPACT");
        public static final Property Timestamp = new Property(20, Long.TYPE, "timestamp", false, "TIMESTAMP");
        public static final Property Status = new Property(21, Integer.TYPE, NotificationCompat.CATEGORY_STATUS, false, "STATUS");
    }

    @Override
    public final boolean isEntityUpdateable() {
        return true;
    }

    public JustSwapBeanDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public JustSwapBeanDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"JUST_SWAP_BEAN\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"BLOCK_NUM\" INTEGER NOT NULL ,\"HASH\" TEXT,\"SYMBOL_IN\" TEXT,\"DECIMALS_IN\" TEXT,\"TOKEN_ADDRESS_IN\" TEXT,\"ADDRESS\" TEXT,\"CONTRACT_IN_ADDRESS\" TEXT,\"AMOUNT_IN\" TEXT,\"FEE\" TEXT,\"RATE\" TEXT,\"AMOUNTOUT\" TEXT,\"CONTRACT_OUT_ADDRESS\" TEXT,\"EXCHARGE_ADDRESS\" TEXT,\"TOKEN_ADDRESS_OUT\" TEXT,\"METHOD\" TEXT,\"SYMBOL_OUT\" TEXT,\"DECIMALS_OUT\" TEXT,\"MIN_RECEIVED\" TEXT,\"PRICE_IMPACT\" TEXT,\"TIMESTAMP\" INTEGER NOT NULL ,\"STATUS\" INTEGER NOT NULL );");
        database.execSQL("CREATE UNIQUE INDEX " + str + "IDX_JUST_SWAP_BEAN_HASH_DESC ON \"JUST_SWAP_BEAN\" (\"HASH\" DESC);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"JUST_SWAP_BEAN\"");
        database.execSQL(sb.toString());
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, JustSwapBean justSwapBean) {
        databaseStatement.clearBindings();
        Long id = justSwapBean.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindLong(2, justSwapBean.getBlockNum());
        String hash = justSwapBean.getHash();
        if (hash != null) {
            databaseStatement.bindString(3, hash);
        }
        String symbolIn = justSwapBean.getSymbolIn();
        if (symbolIn != null) {
            databaseStatement.bindString(4, symbolIn);
        }
        String decimalsIn = justSwapBean.getDecimalsIn();
        if (decimalsIn != null) {
            databaseStatement.bindString(5, decimalsIn);
        }
        String tokenAddressIn = justSwapBean.getTokenAddressIn();
        if (tokenAddressIn != null) {
            databaseStatement.bindString(6, tokenAddressIn);
        }
        String address = justSwapBean.getAddress();
        if (address != null) {
            databaseStatement.bindString(7, address);
        }
        String contractInAddress = justSwapBean.getContractInAddress();
        if (contractInAddress != null) {
            databaseStatement.bindString(8, contractInAddress);
        }
        String amountIn = justSwapBean.getAmountIn();
        if (amountIn != null) {
            databaseStatement.bindString(9, amountIn);
        }
        String fee = justSwapBean.getFee();
        if (fee != null) {
            databaseStatement.bindString(10, fee);
        }
        String rate = justSwapBean.getRate();
        if (rate != null) {
            databaseStatement.bindString(11, rate);
        }
        String amountout = justSwapBean.getAmountout();
        if (amountout != null) {
            databaseStatement.bindString(12, amountout);
        }
        String contractOutAddress = justSwapBean.getContractOutAddress();
        if (contractOutAddress != null) {
            databaseStatement.bindString(13, contractOutAddress);
        }
        String exchargeAddress = justSwapBean.getExchargeAddress();
        if (exchargeAddress != null) {
            databaseStatement.bindString(14, exchargeAddress);
        }
        String tokenAddressOut = justSwapBean.getTokenAddressOut();
        if (tokenAddressOut != null) {
            databaseStatement.bindString(15, tokenAddressOut);
        }
        String method = justSwapBean.getMethod();
        if (method != null) {
            databaseStatement.bindString(16, method);
        }
        String symbolOut = justSwapBean.getSymbolOut();
        if (symbolOut != null) {
            databaseStatement.bindString(17, symbolOut);
        }
        String decimalsOut = justSwapBean.getDecimalsOut();
        if (decimalsOut != null) {
            databaseStatement.bindString(18, decimalsOut);
        }
        String minReceived = justSwapBean.getMinReceived();
        if (minReceived != null) {
            databaseStatement.bindString(19, minReceived);
        }
        String priceImpact = justSwapBean.getPriceImpact();
        if (priceImpact != null) {
            databaseStatement.bindString(20, priceImpact);
        }
        databaseStatement.bindLong(21, justSwapBean.getTimestamp());
        databaseStatement.bindLong(22, justSwapBean.getStatus());
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, JustSwapBean justSwapBean) {
        sQLiteStatement.clearBindings();
        Long id = justSwapBean.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindLong(2, justSwapBean.getBlockNum());
        String hash = justSwapBean.getHash();
        if (hash != null) {
            sQLiteStatement.bindString(3, hash);
        }
        String symbolIn = justSwapBean.getSymbolIn();
        if (symbolIn != null) {
            sQLiteStatement.bindString(4, symbolIn);
        }
        String decimalsIn = justSwapBean.getDecimalsIn();
        if (decimalsIn != null) {
            sQLiteStatement.bindString(5, decimalsIn);
        }
        String tokenAddressIn = justSwapBean.getTokenAddressIn();
        if (tokenAddressIn != null) {
            sQLiteStatement.bindString(6, tokenAddressIn);
        }
        String address = justSwapBean.getAddress();
        if (address != null) {
            sQLiteStatement.bindString(7, address);
        }
        String contractInAddress = justSwapBean.getContractInAddress();
        if (contractInAddress != null) {
            sQLiteStatement.bindString(8, contractInAddress);
        }
        String amountIn = justSwapBean.getAmountIn();
        if (amountIn != null) {
            sQLiteStatement.bindString(9, amountIn);
        }
        String fee = justSwapBean.getFee();
        if (fee != null) {
            sQLiteStatement.bindString(10, fee);
        }
        String rate = justSwapBean.getRate();
        if (rate != null) {
            sQLiteStatement.bindString(11, rate);
        }
        String amountout = justSwapBean.getAmountout();
        if (amountout != null) {
            sQLiteStatement.bindString(12, amountout);
        }
        String contractOutAddress = justSwapBean.getContractOutAddress();
        if (contractOutAddress != null) {
            sQLiteStatement.bindString(13, contractOutAddress);
        }
        String exchargeAddress = justSwapBean.getExchargeAddress();
        if (exchargeAddress != null) {
            sQLiteStatement.bindString(14, exchargeAddress);
        }
        String tokenAddressOut = justSwapBean.getTokenAddressOut();
        if (tokenAddressOut != null) {
            sQLiteStatement.bindString(15, tokenAddressOut);
        }
        String method = justSwapBean.getMethod();
        if (method != null) {
            sQLiteStatement.bindString(16, method);
        }
        String symbolOut = justSwapBean.getSymbolOut();
        if (symbolOut != null) {
            sQLiteStatement.bindString(17, symbolOut);
        }
        String decimalsOut = justSwapBean.getDecimalsOut();
        if (decimalsOut != null) {
            sQLiteStatement.bindString(18, decimalsOut);
        }
        String minReceived = justSwapBean.getMinReceived();
        if (minReceived != null) {
            sQLiteStatement.bindString(19, minReceived);
        }
        String priceImpact = justSwapBean.getPriceImpact();
        if (priceImpact != null) {
            sQLiteStatement.bindString(20, priceImpact);
        }
        sQLiteStatement.bindLong(21, justSwapBean.getTimestamp());
        sQLiteStatement.bindLong(22, justSwapBean.getStatus());
    }

    @Override
    public Long readKey(Cursor cursor, int i) {
        if (cursor.isNull(i)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i));
    }

    @Override
    public JustSwapBean readEntity(Cursor cursor, int i) {
        Long valueOf = cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i));
        long j = cursor.getLong(i + 1);
        int i2 = i + 2;
        String string = cursor.isNull(i2) ? null : cursor.getString(i2);
        int i3 = i + 3;
        String string2 = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 4;
        String string3 = cursor.isNull(i4) ? null : cursor.getString(i4);
        int i5 = i + 5;
        String string4 = cursor.isNull(i5) ? null : cursor.getString(i5);
        int i6 = i + 6;
        String string5 = cursor.isNull(i6) ? null : cursor.getString(i6);
        int i7 = i + 7;
        String string6 = cursor.isNull(i7) ? null : cursor.getString(i7);
        int i8 = i + 8;
        String string7 = cursor.isNull(i8) ? null : cursor.getString(i8);
        int i9 = i + 9;
        String string8 = cursor.isNull(i9) ? null : cursor.getString(i9);
        int i10 = i + 10;
        String string9 = cursor.isNull(i10) ? null : cursor.getString(i10);
        int i11 = i + 11;
        String string10 = cursor.isNull(i11) ? null : cursor.getString(i11);
        int i12 = i + 12;
        String string11 = cursor.isNull(i12) ? null : cursor.getString(i12);
        int i13 = i + 13;
        String string12 = cursor.isNull(i13) ? null : cursor.getString(i13);
        int i14 = i + 14;
        String string13 = cursor.isNull(i14) ? null : cursor.getString(i14);
        int i15 = i + 15;
        String string14 = cursor.isNull(i15) ? null : cursor.getString(i15);
        int i16 = i + 16;
        String string15 = cursor.isNull(i16) ? null : cursor.getString(i16);
        int i17 = i + 17;
        String string16 = cursor.isNull(i17) ? null : cursor.getString(i17);
        int i18 = i + 18;
        String string17 = cursor.isNull(i18) ? null : cursor.getString(i18);
        int i19 = i + 19;
        return new JustSwapBean(valueOf, j, string, string2, string3, string4, string5, string6, string7, string8, string9, string10, string11, string12, string13, string14, string15, string16, string17, cursor.isNull(i19) ? null : cursor.getString(i19), cursor.getLong(i + 20), cursor.getInt(i + 21));
    }

    @Override
    public void readEntity(Cursor cursor, JustSwapBean justSwapBean, int i) {
        justSwapBean.setId(cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i)));
        justSwapBean.setBlockNum(cursor.getLong(i + 1));
        int i2 = i + 2;
        justSwapBean.setHash(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 3;
        justSwapBean.setSymbolIn(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 4;
        justSwapBean.setDecimalsIn(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 5;
        justSwapBean.setTokenAddressIn(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 6;
        justSwapBean.setAddress(cursor.isNull(i6) ? null : cursor.getString(i6));
        int i7 = i + 7;
        justSwapBean.setContractInAddress(cursor.isNull(i7) ? null : cursor.getString(i7));
        int i8 = i + 8;
        justSwapBean.setAmountIn(cursor.isNull(i8) ? null : cursor.getString(i8));
        int i9 = i + 9;
        justSwapBean.setFee(cursor.isNull(i9) ? null : cursor.getString(i9));
        int i10 = i + 10;
        justSwapBean.setRate(cursor.isNull(i10) ? null : cursor.getString(i10));
        int i11 = i + 11;
        justSwapBean.setAmountout(cursor.isNull(i11) ? null : cursor.getString(i11));
        int i12 = i + 12;
        justSwapBean.setContractOutAddress(cursor.isNull(i12) ? null : cursor.getString(i12));
        int i13 = i + 13;
        justSwapBean.setExchargeAddress(cursor.isNull(i13) ? null : cursor.getString(i13));
        int i14 = i + 14;
        justSwapBean.setTokenAddressOut(cursor.isNull(i14) ? null : cursor.getString(i14));
        int i15 = i + 15;
        justSwapBean.setMethod(cursor.isNull(i15) ? null : cursor.getString(i15));
        int i16 = i + 16;
        justSwapBean.setSymbolOut(cursor.isNull(i16) ? null : cursor.getString(i16));
        int i17 = i + 17;
        justSwapBean.setDecimalsOut(cursor.isNull(i17) ? null : cursor.getString(i17));
        int i18 = i + 18;
        justSwapBean.setMinReceived(cursor.isNull(i18) ? null : cursor.getString(i18));
        int i19 = i + 19;
        justSwapBean.setPriceImpact(cursor.isNull(i19) ? null : cursor.getString(i19));
        justSwapBean.setTimestamp(cursor.getLong(i + 20));
        justSwapBean.setStatus(cursor.getInt(i + 21));
    }

    @Override
    public final Long updateKeyAfterInsert(JustSwapBean justSwapBean, long j) {
        justSwapBean.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override
    public Long getKey(JustSwapBean justSwapBean) {
        if (justSwapBean != null) {
            return justSwapBean.getId();
        }
        return null;
    }

    @Override
    public boolean hasKey(JustSwapBean justSwapBean) {
        return justSwapBean.getId() != null;
    }
}
