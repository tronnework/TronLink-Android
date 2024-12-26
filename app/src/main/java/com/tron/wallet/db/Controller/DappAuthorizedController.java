package com.tron.wallet.db.Controller;

import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.db.bean.DAppNonOfficialAuthorizedProjectBean;
import com.tron.wallet.db.bean.DappAuthorizedProjectBean;
import com.tron.wallet.db.dao.DaoUtils;
import com.tron.wallet.db.greendao.DAppNonOfficialAuthorizedProjectBeanDao;
import com.tron.wallet.db.greendao.DappAuthorizedProjectBeanDao;
import j$.util.Collection;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;
public class DappAuthorizedController {
    public static synchronized boolean insertMultiOfficialAuthorizedProject(final List<DappAuthorizedProjectBean> list) {
        boolean booleanValue;
        synchronized (DappAuthorizedController.class) {
            try {
                booleanValue = ((Boolean) DaoUtils.getLightInstance().daoSession.callInTx(new Callable() {
                    @Override
                    public final Object call() {
                        return DappAuthorizedController.lambda$insertMultiOfficialAuthorizedProject$1(list);
                    }
                })).booleanValue();
            } catch (Exception e) {
                LogUtils.e(e);
                return false;
            }
        }
        return booleanValue;
    }

    public static Boolean lambda$insertMultiOfficialAuthorizedProject$1(List list) throws Exception {
        Collection.-EL.stream(list).forEach(new Consumer() {
            @Override
            public final void accept(Object obj) {
                ((DappAuthorizedProjectBean) obj).setType(1);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        DaoUtils.getLightInstance().insertMultiObject(list, DappAuthorizedProjectBean.class);
        return true;
    }

    public static synchronized boolean insertNonOfficialAuthorizedProject(final DAppNonOfficialAuthorizedProjectBean dAppNonOfficialAuthorizedProjectBean, final String str) {
        synchronized (DappAuthorizedController.class) {
            try {
                if (queryIsAuthorized(dAppNonOfficialAuthorizedProjectBean.getUrl(), str)) {
                    return true;
                }
            } catch (Exception e) {
                LogUtils.e(e);
            }
            try {
                return ((Boolean) DaoUtils.getLightInstance().daoSession.callInTx(new Callable() {
                    @Override
                    public final Object call() {
                        return DappAuthorizedController.lambda$insertNonOfficialAuthorizedProject$2(DAppNonOfficialAuthorizedProjectBean.this, str);
                    }
                })).booleanValue();
            } catch (Exception e2) {
                LogUtils.e(e2);
                return false;
            }
        }
    }

    public static Boolean lambda$insertNonOfficialAuthorizedProject$2(DAppNonOfficialAuthorizedProjectBean dAppNonOfficialAuthorizedProjectBean, String str) throws Exception {
        dAppNonOfficialAuthorizedProjectBean.setType(2);
        dAppNonOfficialAuthorizedProjectBean.setWalletAddress(str);
        DaoUtils.getLightInstance().insertObject(dAppNonOfficialAuthorizedProjectBean);
        return true;
    }

    public static List<DAppNonOfficialAuthorizedProjectBean> queryNonOfficialAuthorizedProjectList(String str) {
        List<DAppNonOfficialAuthorizedProjectBean> list = null;
        try {
            DaoUtils.getLightInstance().daoSession.getDAppNonOfficialAuthorizedProjectBeanDao().detachAll();
            list = DaoUtils.getLightInstance().daoSession.queryBuilder(DAppNonOfficialAuthorizedProjectBean.class).where(DAppNonOfficialAuthorizedProjectBeanDao.Properties.WalletAddress.eq(str), new WhereCondition[0]).list();
            if (list != null) {
                Collections.reverse(list);
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
        return list;
    }

    public static List<DappAuthorizedProjectBean> queryAllDistinctAuthorizedProjectList() {
        try {
            DaoUtils.getLightInstance().daoSession.getDappAuthorizedProjectBeanDao().detachAll();
            QueryBuilder<DappAuthorizedProjectBean> queryBuilder = DaoUtils.getLightInstance().daoSession.getDappAuthorizedProjectBeanDao().queryBuilder();
            return queryBuilder.where(new WhereCondition.StringCondition(DappAuthorizedProjectBeanDao.Properties.Url.columnName + " IN (SELECT DISTINCT " + DappAuthorizedProjectBeanDao.Properties.Url.columnName + " FROM DAPP_AUTHORIZED_PROJECT_BEAN)"), new WhereCondition[0]).list();
        } catch (Exception e) {
            LogUtils.e(e);
            return new ArrayList();
        }
    }

    public static List<DAppNonOfficialAuthorizedProjectBean> queryAllDistinctAuthorizedProjectFromNonOfficialList() {
        try {
            DaoUtils.getLightInstance().daoSession.getDAppNonOfficialAuthorizedProjectBeanDao().detachAll();
            QueryBuilder<DAppNonOfficialAuthorizedProjectBean> queryBuilder = DaoUtils.getLightInstance().daoSession.getDAppNonOfficialAuthorizedProjectBeanDao().queryBuilder();
            return queryBuilder.where(new WhereCondition.StringCondition(DAppNonOfficialAuthorizedProjectBeanDao.Properties.Url.columnName + " IN (SELECT DISTINCT " + DAppNonOfficialAuthorizedProjectBeanDao.Properties.Url.columnName + " FROM DAPP_NON_OFFICIAL_AUTHORIZED_PROJECT_BEAN)"), new WhereCondition[0]).list();
        } catch (Exception e) {
            LogUtils.e(e);
            return new ArrayList();
        }
    }

    public static boolean queryIsAuthorized(String str, String str2) {
        try {
            WhereCondition eq = DappAuthorizedProjectBeanDao.Properties.Url.eq(str);
            WhereCondition eq2 = DAppNonOfficialAuthorizedProjectBeanDao.Properties.Url.eq(str);
            List list = DaoUtils.getLightInstance().daoSession.queryBuilder(DappAuthorizedProjectBean.class).where(DappAuthorizedProjectBeanDao.Properties.Type.eq(1), eq).build().list();
            List list2 = DaoUtils.getLightInstance().daoSession.queryBuilder(DAppNonOfficialAuthorizedProjectBean.class).where(DAppNonOfficialAuthorizedProjectBeanDao.Properties.WalletAddress.eq(str2), eq2).build().list();
            if (list == null || list.size() <= 0) {
                if (list2 == null) {
                    return false;
                }
                if (list2.size() <= 0) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            LogUtils.e(e);
            return false;
        }
    }

    public static boolean queryIsAuthorized(String str) {
        try {
            List list = DaoUtils.getLightInstance().daoSession.queryBuilder(DappAuthorizedProjectBean.class).where(DappAuthorizedProjectBeanDao.Properties.Url.eq(str), new WhereCondition[0]).build().list();
            if (list != null) {
                return list.size() > 0;
            }
            return false;
        } catch (Exception e) {
            LogUtils.e(e);
            return false;
        }
    }

    public static synchronized boolean deleteNonOfficialAuthorizedProjectList(List<DAppNonOfficialAuthorizedProjectBean> list) {
        boolean deleteMultObject;
        synchronized (DappAuthorizedController.class) {
            try {
                deleteMultObject = DaoUtils.getLightInstance().deleteMultObject(list, DAppNonOfficialAuthorizedProjectBean.class);
            } catch (Exception e) {
                LogUtils.e(e);
                return false;
            }
        }
        return deleteMultObject;
    }

    public static boolean isOfficial(String str) {
        try {
            List list = DaoUtils.getLightInstance().daoSession.queryBuilder(DappAuthorizedProjectBean.class).where(DappAuthorizedProjectBeanDao.Properties.Type.eq(1), DappAuthorizedProjectBeanDao.Properties.Url.eq(str)).build().list();
            if (list != null) {
                return list.size() > 0;
            }
            return false;
        } catch (Exception e) {
            LogUtils.e(e);
            return false;
        }
    }
}
