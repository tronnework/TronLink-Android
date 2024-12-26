package com.tron.wallet.db.dao;

import android.content.Context;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.wallet.db.greendao.DaoMaster;
import com.tron.wallet.db.greendao.DaoSession;
import org.greenrobot.greendao.query.QueryBuilder;
public class LightDaoManager {
    private static final String LIGHT_DB_NAME = "light_tron.db";
    private static DaoMaster mDaomaster;
    private static DaoSession mDaosession;
    private static SQLiteOpenHelper mHelper;
    private Context mContext;

    public void init(Context context) {
        this.mContext = context;
    }

    public LightDaoManager() {
        init(AppContextUtil.getContext());
    }

    public static LightDaoManager getInstance() {
        return ManagerNested.daoManager;
    }

    public DaoMaster getDaoMaster() {
        if (mDaomaster == null) {
            mHelper = new SQLiteOpenHelper(this.mContext, LIGHT_DB_NAME, null);
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

    private void closeHelper() {
        SQLiteOpenHelper sQLiteOpenHelper = mHelper;
        if (sQLiteOpenHelper != null) {
            sQLiteOpenHelper.close();
            mHelper = null;
        }
    }

    private void closeDaoSession() {
        DaoSession daoSession = mDaosession;
        if (daoSession != null) {
            daoSession.clear();
            mDaosession = null;
        }
    }

    public void closeDataBase() {
        closeHelper();
        closeDaoSession();
    }

    private static class ManagerNested {
        private static LightDaoManager daoManager = new LightDaoManager();

        private ManagerNested() {
        }
    }
}
