package com.tron.wallet.business.tabdapp.browser.controller;

import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.addassets2.repository.BaseController;
import com.tron.wallet.business.tabdapp.browser.bean.BrowserHistoryBean;
import com.tron.wallet.db.greendao.BrowserHistoryBeanDao;
import java.util.Calendar;
import java.util.List;
import org.greenrobot.greendao.query.WhereCondition;
public class BrowserHistoryController extends BaseController<BrowserHistoryBean> {
    private static BrowserHistoryController instance;

    private BrowserHistoryController() {
        super(true);
    }

    public static BrowserHistoryController getInstance() {
        if (instance == null) {
            synchronized (BrowserHistoryController.class) {
                if (instance == null) {
                    instance = new BrowserHistoryController();
                }
            }
        }
        return instance;
    }

    public List<BrowserHistoryBean> getBrowserHistorySortList() {
        return this.beanDao.queryBuilder().orderDesc(BrowserHistoryBeanDao.Properties.Id).list();
    }

    public List<BrowserHistoryBean> getBrowserHistorySortList(int i, int i2) {
        return this.beanDao.queryBuilder().orderDesc(BrowserHistoryBeanDao.Properties.Timestamp).limit(i).offset(i2).list();
    }

    public boolean removeSingle(BrowserHistoryBean browserHistoryBean) {
        try {
            this.beanDao.queryBuilder().where(BrowserHistoryBeanDao.Properties.Id.eq(browserHistoryBean.id), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
            LogUtils.w("Controller", "removeSingle remove BrowserHistoryBean one :" + browserHistoryBean.toString());
            return true;
        } catch (Exception e) {
            LogUtils.e(e);
            return false;
        }
    }

    public BrowserHistoryBean getOldestSingle() {
        return (BrowserHistoryBean) this.beanDao.queryBuilder().orderAsc(BrowserHistoryBeanDao.Properties.Id).limit(1).unique();
    }

    public long getHistoryCount() {
        return this.beanDao.queryBuilder().count();
    }

    public void removeOldestOne() {
        BrowserHistoryBean oldestSingle = getOldestSingle();
        if (oldestSingle != null) {
            this.beanDao.delete(oldestSingle);
        }
    }

    public void clearAllData() {
        this.beanDao.deleteAll();
    }

    public BrowserHistoryBean queryInOneDay(String str) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(1), calendar.get(2), calendar.get(5), 0, 0, 0);
        return (BrowserHistoryBean) this.beanDao.queryBuilder().where(BrowserHistoryBeanDao.Properties.Url.eq(str), BrowserHistoryBeanDao.Properties.Timestamp.gt(Long.valueOf(calendar.getTimeInMillis()))).orderDesc(BrowserHistoryBeanDao.Properties.Id).limit(1).unique();
    }
}
