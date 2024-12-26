package com.tron.wallet.business.addassets2.repository;

import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.db.greendao.DaoSession;
import io.reactivex.functions.Consumer;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Random;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.WhereCondition;
public abstract class BaseController<T> {
    private static final String TAG = "BaseDao";
    protected Class beanCls;
    protected AbstractDao<T, ?> beanDao;
    protected DaoSession daoSession;
    protected DbManager dbManager;
    protected RxManager rxManager;

    public BaseController() {
        this.rxManager = new RxManager();
        this.dbManager = DbFactory.getDbTronManager();
        initListener();
        init();
    }

    public BaseController(DbManager dbManager) {
        this.rxManager = new RxManager();
        this.dbManager = dbManager;
        init();
    }

    public BaseController(boolean z) {
        this.rxManager = new RxManager();
        if (!z) {
            this.dbManager = DbFactory.getDbTronManager();
            initListener();
        } else {
            this.dbManager = DbFactory.getDbTronGlobalManager();
        }
        init();
    }

    private void initListener() {
        this.rxManager.on(Event.SWITCH_CHAIN, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initListener$0(obj);
            }
        });
    }

    public void lambda$initListener$0(Object obj) throws Exception {
        KVController.getInstance().clearCache();
        this.dbManager = DbFactory.getDbTronManager();
        init();
    }

    protected void init() {
        this.daoSession = this.dbManager.getDaoSession();
        Class<? extends Object> cls = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.beanCls = cls;
        this.beanDao = (AbstractDao<T, ?>) this.daoSession.getDao(cls);
    }

    public boolean insertOrReplace(T t) {
        try {
            return this.beanDao.insertOrReplace(t) != -1;
        } catch (Exception e) {
            randomReportSentry(e);
            return false;
        }
    }

    public boolean insertMultiObject(List<T> list) {
        return insertMultiObject(list, false);
    }

    public boolean insertMultiObject(final List<T> list, final boolean z) {
        try {
            this.daoSession.runInTx(new Runnable() {
                @Override
                public final void run() {
                    lambda$insertMultiObject$1(z, list);
                }
            });
            return true;
        } catch (Exception e) {
            randomReportSentry(e);
            return false;
        }
    }

    public void lambda$insertMultiObject$1(boolean z, List list) {
        if (z) {
            this.daoSession.deleteAll(this.beanCls);
        }
        for (Object obj : list) {
            this.beanDao.insertOrReplace(obj);
        }
    }

    public boolean deleteAll() {
        try {
            this.beanDao.deleteAll();
            return true;
        } catch (Exception e) {
            randomReportSentry(e);
            return false;
        }
    }

    public void deleteEntity(T t) {
        try {
            this.beanDao.delete(t);
        } catch (Exception e) {
            randomReportSentry(e);
        }
    }

    public boolean deleteEntities(WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        try {
            this.beanDao.queryBuilder().where(whereCondition, whereConditionArr).buildDelete().executeDeleteWithoutDetachingEntities();
            this.beanDao.detachAll();
            return true;
        } catch (Exception e) {
            randomReportSentry(e);
            return false;
        }
    }

    public boolean deleteMultiObject(List<T> list) {
        if (list == null || list.isEmpty()) {
            return true;
        }
        try {
            this.beanDao.deleteInTx(list);
            return true;
        } catch (Exception e) {
            randomReportSentry(e);
            return false;
        }
    }

    public boolean save(T t) {
        try {
            this.beanDao.save(t);
            return true;
        } catch (Exception e) {
            randomReportSentry(e);
            return false;
        }
    }

    public List<T> queryObject(String str, String... strArr) {
        try {
            return this.beanDao.queryRaw(str, strArr);
        } catch (Exception e) {
            randomReportSentry(e);
            return null;
        }
    }

    public List<T> queryAll() {
        try {
            return this.beanDao.loadAll();
        } catch (Exception e) {
            randomReportSentry(e);
            return null;
        }
    }

    public List<T> queryAll(int i, int i2) {
        try {
            return this.beanDao.queryBuilder().orderDesc(new Property[0]).limit(i).offset(i2).list();
        } catch (Exception e) {
            randomReportSentry(e);
            return null;
        }
    }

    public void closeDb() {
        this.dbManager.closeDb();
        this.dbManager = null;
        this.daoSession = null;
    }

    public void randomReportSentry(Exception exc) {
        LogUtils.e(TAG, exc.toString());
        if (new Random(System.currentTimeMillis()).nextInt(100) <= 10) {
            SentryUtil.captureException(exc);
        }
    }

    public List<T> queryRaw(String str, String... strArr) {
        return this.beanDao.queryRaw(str, strArr);
    }
}
