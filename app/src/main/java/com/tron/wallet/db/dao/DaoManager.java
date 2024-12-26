package com.tron.wallet.db.dao;

import android.content.Context;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.wallet.db.greendao.DaoMaster;
import com.tron.wallet.db.greendao.DaoSession;
import org.greenrobot.greendao.query.QueryBuilder;
public class DaoManager {
    private static final String DB_NAME;
    private static Context mContext;
    private static DaoMaster mDaomaster;
    private static DaoSession mDaosession;
    private static SQLiteOpenHelper mHelper;

    public void init(Context context) {
        mContext = context;
    }

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.RELEASE ? "" : IRequest.ENVIRONMENT.toString());
        sb.append("iTron.db");
        DB_NAME = sb.toString();
    }

    public static DaoManager getInstance() {
        return ManagerNested.daoManager;
    }

    public static DaoManager getInstance(Context context) {
        mContext = context;
        return ManagerNested.daoManager;
    }

    public DaoMaster getDaoMaster() {
        if (mDaomaster == null || mHelper == null) {
            mHelper = new SQLiteOpenHelper(mContext, DB_NAME, null);
            mDaomaster = new DaoMaster(mHelper.getWritableDatabase());
        }
        return mDaomaster;
    }

    public DaoSession getDaoSession() {
        if (mDaosession == null) {
            if (mDaomaster == null) {
                mDaomaster = getDaoMaster();
            }
            mDaosession = mDaomaster.newSession();
        }
        return mDaosession;
    }

    public void setDebug(boolean z) {
        QueryBuilder.LOG_SQL = z;
        QueryBuilder.LOG_VALUES = z;
    }

    public void closeHelper() {
        SQLiteOpenHelper sQLiteOpenHelper = mHelper;
        if (sQLiteOpenHelper != null) {
            sQLiteOpenHelper.close();
            mHelper = null;
        }
    }

    public void closeDaoSession() {
        DaoSession daoSession = mDaosession;
        if (daoSession != null) {
            daoSession.clear();
            mDaosession = null;
        }
    }

    public void closeDataBase() {
        closeHelper();
        closeDaoSession();
        mDaomaster = null;
    }

    private static class ManagerNested {
        private static DaoManager daoManager = new DaoManager();

        private ManagerNested() {
        }
    }
}
