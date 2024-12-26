package com.tron.wallet.db.Controller;

import com.tron.tron_base.frame.utils.ThreadPoolManager;
import com.tron.wallet.common.bean.ColdFailLogBean;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.db.dao.BaseDao;
import com.tron.wallet.db.dao.DaoUtils;
import com.tron.wallet.db.greendao.ColdFailLogBeanDao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public class ColdFailLogBeanDaoManager {
    public static final String TAG = "ColdFailLogBeanDaoManager";

    public static void clearAndAddErrorLog(final List<ColdFailLogBean> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        DaoUtils.getInstance().daoSession.runInTx(new Runnable() {
            @Override
            public void run() {
                DaoUtils.getInstance().deleteAll(ColdFailLogBean.class);
                DaoUtils.getInstance().insertMultObject(list);
            }
        });
    }

    public static void clear() {
        DaoUtils.getInstance().deleteAll(ColdFailLogBean.class);
    }

    public static List<ColdFailLogBean> getAllColdFailData() {
        List<ColdFailLogBean> QueryAll = DaoUtils.getInstance().QueryAll(ColdFailLogBean.class);
        if (QueryAll == null || QueryAll.size() <= 0) {
            return null;
        }
        return QueryAll;
    }

    public static List<ColdFailLogBean> getAllFailData() {
        List<ColdFailLogBean> list = DaoUtils.getInstance().daoSession.getColdFailLogBeanDao().queryBuilder().orderDesc(ColdFailLogBeanDao.Properties.Time).list();
        if (list == null || list.size() == 0) {
            return null;
        }
        return list;
    }

    public static void removeFailLogBean(final long j) {
        DaoUtils.getInstance().daoSession.runInTx(new Runnable() {
            @Override
            public void run() {
                BaseDao daoUtils = DaoUtils.getInstance();
                daoUtils.deleteObjectWithWhere(ColdFailLogBeanDao.TABLENAME, "WHERE " + ColdFailLogBeanDao.Properties.Time.columnName + " == '" + j + "' ", new String[0]);
            }
        });
    }

    public static void addErrorLog(final ColdFailLogBean coldFailLogBean) {
        if (coldFailLogBean == null) {
            return;
        }
        ThreadPoolManager.newInstance().addExecuteTask(new Runnable() {
            @Override
            public void run() {
                try {
                    List allColdFailData = ColdFailLogBeanDaoManager.getAllColdFailData();
                    if (allColdFailData == null) {
                        allColdFailData = new ArrayList();
                    }
                    allColdFailData.add(ColdFailLogBean.this);
                    ColdFailLogBeanDaoManager.sortList(allColdFailData);
                    if (allColdFailData.size() > 40) {
                        allColdFailData = allColdFailData.subList(0, 40);
                    }
                    ColdFailLogBeanDaoManager.clearAndAddErrorLog(allColdFailData);
                } catch (Exception e) {
                    SentryUtil.captureException(e);
                }
            }
        });
    }

    public static void sortList(List<ColdFailLogBean> list) {
        try {
            Collections.sort(list, new Comparator<ColdFailLogBean>() {
                @Override
                public int compare(ColdFailLogBean coldFailLogBean, ColdFailLogBean coldFailLogBean2) {
                    if (coldFailLogBean.time > coldFailLogBean2.time) {
                        return -1;
                    }
                    return coldFailLogBean.time < coldFailLogBean2.time ? 1 : 0;
                }
            });
        } catch (Exception e) {
            SentryUtil.captureException(e);
        }
    }
}
