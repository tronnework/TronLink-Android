package com.tron.wallet.business.tabdapp.browser.controller;

import com.tron.wallet.business.addassets2.repository.BaseController;
import com.tron.wallet.business.tabdapp.browser.bean.DAppVisitHistoryBean;
import com.tron.wallet.db.greendao.DAppVisitHistoryBeanDao;
import io.reactivex.Observable;
import java.util.List;
import java.util.concurrent.Callable;
import org.greenrobot.greendao.query.WhereCondition;
public class DAppVisitHistoryController extends BaseController<DAppVisitHistoryBean> {
    private static final int MAX_COUNT = 30;
    private static DAppVisitHistoryController instance;

    private DAppVisitHistoryController() {
        super(true);
    }

    public static DAppVisitHistoryController getInstance() {
        if (instance == null) {
            synchronized (DAppVisitHistoryController.class) {
                if (instance == null) {
                    instance = new DAppVisitHistoryController();
                }
            }
        }
        return instance;
    }

    public Observable<List<DAppVisitHistoryBean>> getDAppVisitHistory() {
        return Observable.fromCallable(new Callable() {
            @Override
            public final Object call() {
                List lambda$getDAppVisitHistory$0;
                lambda$getDAppVisitHistory$0 = lambda$getDAppVisitHistory$0();
                return lambda$getDAppVisitHistory$0;
            }
        });
    }

    public List lambda$getDAppVisitHistory$0() throws Exception {
        return this.beanDao.queryBuilder().orderDesc(DAppVisitHistoryBeanDao.Properties.Timestamp).limit(30).list();
    }

    public Observable<Boolean> insertVisitHistory(final String str, final String str2, final String str3) {
        return Observable.fromCallable(new Callable() {
            @Override
            public final Object call() {
                Boolean lambda$insertVisitHistory$1;
                lambda$insertVisitHistory$1 = lambda$insertVisitHistory$1(str, str2, str3);
                return lambda$insertVisitHistory$1;
            }
        });
    }

    public Boolean lambda$insertVisitHistory$1(String str, String str2, String str3) throws Exception {
        boolean insertOrReplace;
        synchronized (this) {
            DAppVisitHistoryBean dAppVisitHistoryBean = (DAppVisitHistoryBean) this.beanDao.queryBuilder().where(DAppVisitHistoryBeanDao.Properties.Url.eq(str), new WhereCondition[0]).unique();
            if (dAppVisitHistoryBean == null) {
                dAppVisitHistoryBean = new DAppVisitHistoryBean();
                dAppVisitHistoryBean.setUrl(str);
            }
            dAppVisitHistoryBean.setTimestamp(System.currentTimeMillis());
            dAppVisitHistoryBean.setTitle(str2);
            dAppVisitHistoryBean.setIcon(str3);
            insertOrReplace = insertOrReplace(dAppVisitHistoryBean);
            try {
                List list = this.beanDao.queryBuilder().orderDesc(DAppVisitHistoryBeanDao.Properties.Timestamp).offset(30).limit(30).list();
                if (list != null && list.size() > 0) {
                    deleteMultiObject(list);
                }
            } catch (Exception e) {
                randomReportSentry(e);
            }
        }
        return Boolean.valueOf(insertOrReplace);
    }

    public Observable<Boolean> deleteVisitHistory(final String str) {
        return Observable.fromCallable(new Callable() {
            @Override
            public final Object call() {
                Boolean lambda$deleteVisitHistory$2;
                lambda$deleteVisitHistory$2 = lambda$deleteVisitHistory$2(str);
                return lambda$deleteVisitHistory$2;
            }
        });
    }

    public Boolean lambda$deleteVisitHistory$2(String str) throws Exception {
        boolean deleteEntities;
        synchronized (this) {
            deleteEntities = deleteEntities(DAppVisitHistoryBeanDao.Properties.Url.eq(str), new WhereCondition[0]);
        }
        return Boolean.valueOf(deleteEntities);
    }

    public Observable<Boolean> clear() {
        return Observable.fromCallable(new Callable() {
            @Override
            public final Object call() {
                Boolean lambda$clear$3;
                lambda$clear$3 = lambda$clear$3();
                return lambda$clear$3;
            }
        });
    }

    public Boolean lambda$clear$3() throws Exception {
        boolean deleteAll;
        synchronized (this) {
            deleteAll = deleteAll();
        }
        return Boolean.valueOf(deleteAll);
    }
}
