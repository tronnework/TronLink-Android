package com.tron.wallet.db.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.tron.wallet.db.dao.MigrationHelper;
import com.tron.wallet.db.greendao.AccountTotalBalanceBeanDao;
import com.tron.wallet.db.greendao.AddressDaoDao;
import com.tron.wallet.db.greendao.AssetIssueContractDaoDao;
import com.tron.wallet.db.greendao.BleRepoDeviceDao;
import com.tron.wallet.db.greendao.BrowserBookMarkBeanDao;
import com.tron.wallet.db.greendao.BrowserHistoryBeanDao;
import com.tron.wallet.db.greendao.BrowserSearchBeanDao;
import com.tron.wallet.db.greendao.DAppNonOfficialAuthorizedProjectBeanDao;
import com.tron.wallet.db.greendao.DAppVisitHistoryBeanDao;
import com.tron.wallet.db.greendao.DaoMaster;
import com.tron.wallet.db.greendao.DappAuthorizedProjectBeanDao;
import com.tron.wallet.db.greendao.HdTronRelationshipBeanDao;
import com.tron.wallet.db.greendao.KVBeanDao;
import com.tron.wallet.db.greendao.MostVisitDAppBeanDao;
import com.tron.wallet.db.greendao.RowsBeanDao;
import com.tron.wallet.db.greendao.TRXAccountBalanceBeanDao;
import com.tron.wallet.db.greendao.TokenSortBeanDao;
import com.tron.wallet.db.greendao.TransactionMessageDao;
import org.greenrobot.greendao.database.Database;
public class SQLiteOpenHelper extends DaoMaster.OpenHelper {
    public SQLiteOpenHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory) {
        super(context, str, cursorFactory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        MigrationHelper.migrate(sQLiteDatabase, new MigrationHelper.ReCreateAllTableListener() {
            @Override
            public void onCreateAllTables(Database database, boolean z) {
                DaoMaster.createAllTables(database, true);
            }

            @Override
            public void onDropAllTables(Database database, boolean z) {
                DaoMaster.dropAllTables(database, true);
            }
        }, DAppNonOfficialAuthorizedProjectBeanDao.class, DappAuthorizedProjectBeanDao.class, AssetIssueContractDaoDao.class, AddressDaoDao.class, AccountTotalBalanceBeanDao.class, RowsBeanDao.class, KVBeanDao.class, TokenSortBeanDao.class, TransactionMessageDao.class, BleRepoDeviceDao.class, HdTronRelationshipBeanDao.class, BrowserBookMarkBeanDao.class, BrowserHistoryBeanDao.class, BrowserSearchBeanDao.class, MostVisitDAppBeanDao.class, DAppVisitHistoryBeanDao.class, TRXAccountBalanceBeanDao.class);
    }
}
