package com.tron.wallet.business.nft.dao;

import android.content.Context;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.db.dao.DaoManager;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;
public class DbController<T> {
    private static final String TAG = "DbController";
    private static final ConcurrentHashMap<Class<?>, DbController<?>> controllerMap = new ConcurrentHashMap<>();
    private AbstractDao<T, ?> dao;

    public static DbController<?> getInstance(Context context, Class<?> cls) {
        ConcurrentHashMap<Class<?>, DbController<?>> concurrentHashMap = controllerMap;
        DbController<?> dbController = concurrentHashMap.get(cls);
        if (dbController == null) {
            DbController<?> dbController2 = new DbController<>(context, cls);
            concurrentHashMap.put(cls, dbController2);
            return dbController2;
        }
        return dbController;
    }

    protected DbController(Context context, Class<T> cls) {
        try {
            this.dao = (AbstractDao<T, ?>) DaoManager.getInstance(context).getDaoSession().getDao(cls);
        } catch (Exception e) {
            LogUtils.w(TAG, "Failed to init databaseController");
            LogUtils.e(e);
        }
    }

    public List<T> queryAll(WhereCondition[] whereConditionArr) {
        AbstractDao<T, ?> abstractDao = this.dao;
        if (abstractDao == null) {
            return new ArrayList();
        }
        QueryBuilder<T> queryBuilder = abstractDao.queryBuilder();
        for (WhereCondition whereCondition : whereConditionArr) {
            queryBuilder.where(whereCondition, new WhereCondition[0]);
        }
        return queryBuilder.build().list();
    }

    public void insertOrReplace(final T t, ICallback<Boolean> iCallback) {
        Observable.unsafeCreate(new ObservableSource() {
            @Override
            public final void subscribe(Observer observer) {
                lambda$insertOrReplace$0(t, observer);
            }
        }).compose(RxSchedulers.io_main()).subscribe(new IObserver(iCallback, ""));
    }

    public void lambda$insertOrReplace$0(Object obj, Observer observer) {
        AbstractDao<T, ?> abstractDao = this.dao;
        if (abstractDao != null) {
            abstractDao.insertOrReplace(obj);
            observer.onNext(true);
        } else {
            observer.onNext(false);
        }
        observer.onComplete();
    }

    public void insertOrReplace(final List<T> list, ICallback<Boolean> iCallback) {
        Observable.unsafeCreate(new ObservableSource() {
            @Override
            public final void subscribe(Observer observer) {
                lambda$insertOrReplace$1(list, observer);
            }
        }).compose(RxSchedulers.io_main()).subscribe(new IObserver(iCallback, ""));
    }

    public void lambda$insertOrReplace$1(List list, Observer observer) {
        try {
            AbstractDao<T, ?> abstractDao = this.dao;
            if (abstractDao != null) {
                abstractDao.insertOrReplaceInTx(list);
                observer.onNext(true);
            }
            observer.onNext(false);
        } catch (Exception e) {
            LogUtils.e(e);
            observer.onNext(false);
        }
        observer.onComplete();
    }

    public void removeAll(WhereCondition[] whereConditionArr) {
        AbstractDao<T, ?> abstractDao = this.dao;
        if (abstractDao == null) {
            return;
        }
        QueryBuilder<T> queryBuilder = abstractDao.queryBuilder();
        for (WhereCondition whereCondition : whereConditionArr) {
            queryBuilder.where(whereCondition, new WhereCondition[0]);
        }
        queryBuilder.buildDelete().executeDeleteWithoutDetachingEntities();
    }
}
