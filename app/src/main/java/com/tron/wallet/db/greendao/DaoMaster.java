package com.tron.wallet.db.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import org.greenrobot.greendao.AbstractDaoMaster;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseOpenHelper;
import org.greenrobot.greendao.database.StandardDatabase;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
public class DaoMaster extends AbstractDaoMaster {
    public static final int SCHEMA_VERSION = 52;

    public static void createAllTables(Database database, boolean z) {
        KVBeanDao.createTable(database, z);
        TokenSortBeanDao.createTable(database, z);
        AssetsHomeDataDao.createTable(database, z);
        AssetsHomePriceBeanDao.createTable(database, z);
        NftItemInfoDao.createTable(database, z);
        NftTokenBeanDao.createTable(database, z);
        NftTypeInfoDao.createTable(database, z);
        TransactionMessageDao.createTable(database, z);
        TokenCheckBeanDao.createTable(database, z);
        BrowserBookMarkBeanDao.createTable(database, z);
        BrowserHistoryBeanDao.createTable(database, z);
        BrowserSearchBeanDao.createTable(database, z);
        BrowserTabBeanDao.createTable(database, z);
        BrowserTabHistoryBeanDao.createTable(database, z);
        DAppVisitHistoryBeanDao.createTable(database, z);
        MostVisitDAppBeanDao.createTable(database, z);
        DAppDataBeanDao.createTable(database, z);
        BackupRecordBeanDao.createTable(database, z);
        BleRepoDeviceDao.createTable(database, z);
        ColdFailLogBeanDao.createTable(database, z);
        RecentSendBeanDao.createTable(database, z);
        RowsBeanDao.createTable(database, z);
        TokenBeanDao.createTable(database, z);
        UnAddedTokenBeanDao.createTable(database, z);
        DappBlackBeanDao.createTable(database, z);
        AccountTotalBalanceBeanDao.createTable(database, z);
        AddressDaoDao.createTable(database, z);
        AssetIssueContractDaoDao.createTable(database, z);
        DAppNonOfficialAuthorizedProjectBeanDao.createTable(database, z);
        DappAuthorizedProjectBeanDao.createTable(database, z);
        HdTronRelationshipBeanDao.createTable(database, z);
        JustSwapBeanDao.createTable(database, z);
        TRXAccountBalanceBeanDao.createTable(database, z);
        TranscationBeanDao.createTable(database, z);
    }

    public static void dropAllTables(Database database, boolean z) {
        KVBeanDao.dropTable(database, z);
        TokenSortBeanDao.dropTable(database, z);
        AssetsHomeDataDao.dropTable(database, z);
        AssetsHomePriceBeanDao.dropTable(database, z);
        NftItemInfoDao.dropTable(database, z);
        NftTokenBeanDao.dropTable(database, z);
        NftTypeInfoDao.dropTable(database, z);
        TransactionMessageDao.dropTable(database, z);
        TokenCheckBeanDao.dropTable(database, z);
        BrowserBookMarkBeanDao.dropTable(database, z);
        BrowserHistoryBeanDao.dropTable(database, z);
        BrowserSearchBeanDao.dropTable(database, z);
        BrowserTabBeanDao.dropTable(database, z);
        BrowserTabHistoryBeanDao.dropTable(database, z);
        DAppVisitHistoryBeanDao.dropTable(database, z);
        MostVisitDAppBeanDao.dropTable(database, z);
        DAppDataBeanDao.dropTable(database, z);
        BackupRecordBeanDao.dropTable(database, z);
        BleRepoDeviceDao.dropTable(database, z);
        ColdFailLogBeanDao.dropTable(database, z);
        RecentSendBeanDao.dropTable(database, z);
        RowsBeanDao.dropTable(database, z);
        TokenBeanDao.dropTable(database, z);
        UnAddedTokenBeanDao.dropTable(database, z);
        DappBlackBeanDao.dropTable(database, z);
        AccountTotalBalanceBeanDao.dropTable(database, z);
        AddressDaoDao.dropTable(database, z);
        AssetIssueContractDaoDao.dropTable(database, z);
        DAppNonOfficialAuthorizedProjectBeanDao.dropTable(database, z);
        DappAuthorizedProjectBeanDao.dropTable(database, z);
        HdTronRelationshipBeanDao.dropTable(database, z);
        JustSwapBeanDao.dropTable(database, z);
        TRXAccountBalanceBeanDao.dropTable(database, z);
        TranscationBeanDao.dropTable(database, z);
    }

    public static DaoSession newDevSession(Context context, String str) {
        return new DaoMaster(new DevOpenHelper(context, str).getWritableDb()).newSession();
    }

    public DaoMaster(SQLiteDatabase sQLiteDatabase) {
        this(new StandardDatabase(sQLiteDatabase));
    }

    public DaoMaster(Database database) {
        super(database, 52);
        registerDaoClass(KVBeanDao.class);
        registerDaoClass(TokenSortBeanDao.class);
        registerDaoClass(AssetsHomeDataDao.class);
        registerDaoClass(AssetsHomePriceBeanDao.class);
        registerDaoClass(NftItemInfoDao.class);
        registerDaoClass(NftTokenBeanDao.class);
        registerDaoClass(NftTypeInfoDao.class);
        registerDaoClass(TransactionMessageDao.class);
        registerDaoClass(TokenCheckBeanDao.class);
        registerDaoClass(BrowserBookMarkBeanDao.class);
        registerDaoClass(BrowserHistoryBeanDao.class);
        registerDaoClass(BrowserSearchBeanDao.class);
        registerDaoClass(BrowserTabBeanDao.class);
        registerDaoClass(BrowserTabHistoryBeanDao.class);
        registerDaoClass(DAppVisitHistoryBeanDao.class);
        registerDaoClass(MostVisitDAppBeanDao.class);
        registerDaoClass(DAppDataBeanDao.class);
        registerDaoClass(BackupRecordBeanDao.class);
        registerDaoClass(BleRepoDeviceDao.class);
        registerDaoClass(ColdFailLogBeanDao.class);
        registerDaoClass(RecentSendBeanDao.class);
        registerDaoClass(RowsBeanDao.class);
        registerDaoClass(TokenBeanDao.class);
        registerDaoClass(UnAddedTokenBeanDao.class);
        registerDaoClass(DappBlackBeanDao.class);
        registerDaoClass(AccountTotalBalanceBeanDao.class);
        registerDaoClass(AddressDaoDao.class);
        registerDaoClass(AssetIssueContractDaoDao.class);
        registerDaoClass(DAppNonOfficialAuthorizedProjectBeanDao.class);
        registerDaoClass(DappAuthorizedProjectBeanDao.class);
        registerDaoClass(HdTronRelationshipBeanDao.class);
        registerDaoClass(JustSwapBeanDao.class);
        registerDaoClass(TRXAccountBalanceBeanDao.class);
        registerDaoClass(TranscationBeanDao.class);
    }

    @Override
    public DaoSession newSession() {
        return new DaoSession(this.db, IdentityScopeType.Session, this.daoConfigMap);
    }

    @Override
    public DaoSession newSession(IdentityScopeType identityScopeType) {
        return new DaoSession(this.db, identityScopeType, this.daoConfigMap);
    }

    public static abstract class OpenHelper extends DatabaseOpenHelper {
        public OpenHelper(Context context, String str) {
            super(context, str, 52);
        }

        public OpenHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory) {
            super(context, str, cursorFactory, 52);
        }

        @Override
        public void onCreate(Database database) {
            Log.i("greenDAO", "Creating tables for schema version 52");
            DaoMaster.createAllTables(database, false);
        }
    }

    public static class DevOpenHelper extends OpenHelper {
        public DevOpenHelper(Context context, String str) {
            super(context, str);
        }

        public DevOpenHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory) {
            super(context, str, cursorFactory);
        }

        @Override
        public void onUpgrade(Database database, int i, int i2) {
            Log.i("greenDAO", "Upgrading schema from version " + i + " to " + i2 + " by dropping all tables");
            DaoMaster.dropAllTables(database, true);
            onCreate(database);
        }
    }
}
