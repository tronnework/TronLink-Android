package com.tron.wallet.business.tabdapp.browser.controller;

import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.addassets2.repository.BaseController;
import com.tron.wallet.business.tabdapp.browser.bean.BrowserSearchBean;
import com.tron.wallet.db.greendao.BrowserSearchBeanDao;
import java.util.List;
import org.greenrobot.greendao.query.WhereCondition;
public class BrowserSearchHistoryController extends BaseController<BrowserSearchBean> {
    private static BrowserSearchHistoryController instance;

    private BrowserSearchHistoryController() {
        super(true);
    }

    public static BrowserSearchHistoryController getInstance() {
        if (instance == null) {
            synchronized (BrowserSearchHistoryController.class) {
                if (instance == null) {
                    instance = new BrowserSearchHistoryController();
                }
            }
        }
        return instance;
    }

    public List<BrowserSearchBean> getBrowserHistorySortList() {
        return this.beanDao.queryBuilder().orderDesc(BrowserSearchBeanDao.Properties.Timestamp).list();
    }

    public List<BrowserSearchBean> getBrowserHistorySortList(int i, int i2) {
        return this.beanDao.queryBuilder().orderDesc(BrowserSearchBeanDao.Properties.Id).limit(i).offset(i2).list();
    }

    public boolean removeSingle(BrowserSearchBean browserSearchBean) {
        try {
            this.beanDao.queryBuilder().where(BrowserSearchBeanDao.Properties.Id.eq(browserSearchBean.id), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
            LogUtils.w("Controller", "removeSingle remove BrowserHistoryBean one :" + browserSearchBean.toString());
            return true;
        } catch (Exception e) {
            LogUtils.e(e);
            return false;
        }
    }

    public BrowserSearchBean queryifExist(String str) {
        return (BrowserSearchBean) this.beanDao.queryBuilder().where(BrowserSearchBeanDao.Properties.Key.eq(str), new WhereCondition[0]).unique();
    }
}
