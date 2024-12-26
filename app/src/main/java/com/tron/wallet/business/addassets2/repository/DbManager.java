package com.tron.wallet.business.addassets2.repository;

import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.wallet.db.dao.SQLiteOpenHelper;
import com.tron.wallet.db.greendao.DaoMaster;
import com.tron.wallet.db.greendao.DaoSession;
public class DbManager {
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private String dbName;
    private SQLiteOpenHelper mHelper;

    public DbManager(String str) {
        this.dbName = str;
    }

    public synchronized DaoMaster getDaoMaster() {
        if (this.daoMaster == null || this.mHelper == null) {
            this.mHelper = new SQLiteOpenHelper(AppContextUtil.getContext(), this.dbName, null);
            this.daoMaster = new DaoMaster(this.mHelper.getWritableDatabase());
        }
        return this.daoMaster;
    }

    public synchronized DaoSession getDaoSession() {
        if (this.daoSession == null) {
            if (this.daoMaster == null) {
                this.daoMaster = getDaoMaster();
            }
            this.daoSession = this.daoMaster.newSession();
        }
        return this.daoSession;
    }

    public void closeHelper() {
        SQLiteOpenHelper sQLiteOpenHelper = this.mHelper;
        if (sQLiteOpenHelper != null) {
            sQLiteOpenHelper.close();
            this.mHelper = null;
        }
    }

    public void closeDaoSession() {
        DaoSession daoSession = this.daoSession;
        if (daoSession != null) {
            daoSession.clear();
            this.daoSession = null;
        }
    }

    public void closeDb() {
        closeHelper();
        closeDaoSession();
        this.daoMaster = null;
    }
}
