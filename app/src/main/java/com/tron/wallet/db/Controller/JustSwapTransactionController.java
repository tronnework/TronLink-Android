package com.tron.wallet.db.Controller;

import android.content.Context;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.db.bean.JustSwapBean;
import com.tron.wallet.db.dao.SQLiteOpenHelper;
import com.tron.wallet.db.greendao.DaoMaster;
import com.tron.wallet.db.greendao.DaoSession;
import com.tron.wallet.db.greendao.JustSwapBeanDao;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.greendao.query.LazyList;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;
public class JustSwapTransactionController {
    private static final String DIC_DB_NAME = "transaction_history.db";
    public static final String TAG = "TransactionHistoryController";
    private static JustSwapBeanDao justSwapBeanDao;
    private static DaoMaster mDaomaster;
    private static DaoSession mDaosession;
    private static SQLiteOpenHelper mHelper;
    private Context mContext;

    public JustSwapTransactionController() {
        init(AppContextUtil.getContext());
    }

    public static JustSwapTransactionController getInstance() {
        return ManagerNested.daoManager;
    }

    public void init(Context context) {
        this.mContext = context;
        try {
            getDaoMaster();
            getDaoSession();
            justSwapBeanDao = mDaosession.getJustSwapBeanDao();
        } catch (Exception e) {
            SentryUtil.captureException(e);
        }
    }

    public DaoMaster getDaoMaster() {
        if (mDaomaster == null) {
            Context context = this.mContext;
            mHelper = new SQLiteOpenHelper(context, IRequest.ENVIRONMENT.toString() + DIC_DB_NAME, null);
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

    public void insertOrReplace(JustSwapBean justSwapBean) {
        if (checkDao()) {
            if (searchNotesByTxidAndAddress(justSwapBean.getAddress(), justSwapBean.getHash()) != null) {
                if (justSwapBean.getHash() != null) {
                    LogUtils.d("data exist:  " + justSwapBean.getHash());
                    return;
                }
                justSwapBeanDao.insertOrReplace(justSwapBean);
                return;
            }
            justSwapBeanDao.insertOrReplace(justSwapBean);
        }
    }

    public long insert(JustSwapBean justSwapBean) {
        LogUtils.d("insert JustSwapBean :  " + justSwapBean.toString());
        if (checkDao()) {
            return justSwapBeanDao.insert(justSwapBean);
        }
        return -1L;
    }

    public void update(JustSwapBean justSwapBean) {
        if (checkDao()) {
            LazyList<JustSwapBean> listLazyUncached = justSwapBeanDao.queryBuilder().where(JustSwapBeanDao.Properties.Hash.eq(justSwapBean.getHash()), new WhereCondition[0]).listLazyUncached();
            if (listLazyUncached != null && listLazyUncached.size() == 1) {
                LogUtils.i(TAG, "update ShieldTokenNoteTxBean " + listLazyUncached.size());
                if (listLazyUncached.get(0) != null) {
                    justSwapBeanDao.update(justSwapBean);
                    return;
                }
                return;
            }
            LogUtils.i("ShieldedAPIScan", "update ShieldTokenNoteTxBean query null ");
        }
    }

    public JustSwapBean searchNotesByTxidAndAddress(String str, String str2) {
        List<JustSwapBean> list;
        if (!checkDao() || (list = justSwapBeanDao.queryBuilder().where(JustSwapBeanDao.Properties.Hash.eq(str2), JustSwapBeanDao.Properties.Address.eq(str)).list()) == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    public List<JustSwapBean> getLastestNotesByAddress(String str) {
        return checkDao() ? justSwapBeanDao.queryBuilder().where(JustSwapBeanDao.Properties.Address.eq(str), new WhereCondition[0]).orderDesc(JustSwapBeanDao.Properties.Timestamp).limit(6).list() : new ArrayList();
    }

    public List<JustSwapBean> getNotesByAddress(String str, int i, int i2) {
        return checkDao() ? justSwapBeanDao.queryBuilder().where(JustSwapBeanDao.Properties.Address.eq(str), new WhereCondition[0]).orderDesc(JustSwapBeanDao.Properties.Timestamp).offset((i - 1) * i2).limit(i2).list() : new ArrayList();
    }

    public List<JustSwapBean> getPendingStateTransactions(String str) {
        return checkDao() ? justSwapBeanDao.queryBuilder().where(JustSwapBeanDao.Properties.Address.eq(str), JustSwapBeanDao.Properties.Status.eq(0)).list() : new ArrayList();
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

    private boolean checkDao() {
        if (justSwapBeanDao == null) {
            SentryUtil.captureException(new Exception("JustSwapDataDao init failed"));
            return false;
        }
        return true;
    }

    private static class ManagerNested {
        private static JustSwapTransactionController daoManager = new JustSwapTransactionController();

        private ManagerNested() {
        }
    }
}
