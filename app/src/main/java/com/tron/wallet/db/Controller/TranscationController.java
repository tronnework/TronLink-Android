package com.tron.wallet.db.Controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.tron.wallet.db.bean.TranscationBean;
import com.tron.wallet.db.greendao.DaoMaster;
import com.tron.wallet.db.greendao.DaoSession;
import com.tron.wallet.db.greendao.TranscationBeanDao;
import java.util.List;
import org.greenrobot.greendao.query.LazyList;
import org.greenrobot.greendao.query.WhereCondition;
public class TranscationController {
    private static volatile TranscationController mDbController;
    private Context context;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private DaoMaster.DevOpenHelper mHelper;
    private TranscationBeanDao transcationBeanDao;

    public static TranscationController getInstance(Context context) {
        if (mDbController == null) {
            synchronized (AddressController.class) {
                if (mDbController == null) {
                    mDbController = new TranscationController(context);
                }
            }
        }
        return mDbController;
    }

    public TranscationController(Context context) {
        this.context = context;
        this.mHelper = new DaoMaster.DevOpenHelper(context, "transcation.db", null);
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        this.mDaoMaster = daoMaster;
        DaoSession newSession = daoMaster.newSession();
        this.mDaoSession = newSession;
        this.transcationBeanDao = newSession.getTranscationBeanDao();
    }

    private SQLiteDatabase getReadableDatabase() {
        if (this.mHelper == null) {
            this.mHelper = new DaoMaster.DevOpenHelper(this.context, "transcation.db", null);
        }
        return this.mHelper.getReadableDatabase();
    }

    private SQLiteDatabase getWritableDatabase() {
        if (this.mHelper == null) {
            this.mHelper = new DaoMaster.DevOpenHelper(this.context, "transcation.db", null);
        }
        return this.mHelper.getWritableDatabase();
    }

    public void insertOrReplace(TranscationBean transcationBean) {
        this.transcationBeanDao.insertOrReplace(transcationBean);
    }

    public long insert(TranscationBean transcationBean) {
        return this.transcationBeanDao.insert(transcationBean);
    }

    public void update(TranscationBean transcationBean) {
        TranscationBean unique = this.transcationBeanDao.queryBuilder().where(TranscationBeanDao.Properties.Hash.eq(transcationBean.getHash()), new WhereCondition[0]).build().unique();
        if (unique != null) {
            unique.setStatus(transcationBean.getStatus());
            this.transcationBeanDao.update(unique);
        }
    }

    public List<TranscationBeanDao> searchByWhere(String str) {
        return (List) this.transcationBeanDao.queryBuilder().where(TranscationBeanDao.Properties.Status.eq(str), new WhereCondition[0]).build().unique();
    }

    public List<TranscationBean> searchAll() {
        return this.transcationBeanDao.queryBuilder().list();
    }

    public void delete(String str) {
        this.transcationBeanDao.queryBuilder().where(TranscationBeanDao.Properties.Hash.eq(str), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    public void clear() {
        this.transcationBeanDao.deleteAll();
    }

    public TranscationBean getTranscationByHash(String str) {
        LazyList<TranscationBean> listLazyUncached = this.transcationBeanDao.queryBuilder().where(TranscationBeanDao.Properties.Hash.eq(str), new WhereCondition[0]).listLazyUncached();
        if (listLazyUncached == null || listLazyUncached.size() <= 0 || listLazyUncached.get(0) == null) {
            return null;
        }
        return listLazyUncached.get(0);
    }
}
