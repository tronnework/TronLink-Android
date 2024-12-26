package com.tron.wallet.business.tabdapp.browser.controller;

import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.addassets2.repository.BaseController;
import com.tron.wallet.business.tabdapp.browser.bean.BrowserTabBean;
import com.tron.wallet.business.tabdapp.browser.bean.BrowserTabHistoryBean;
import com.tron.wallet.db.greendao.BrowserTabHistoryBeanDao;
import java.util.List;
import org.greenrobot.greendao.query.WhereCondition;
public class BrowserTabHistoryController extends BaseController<BrowserTabHistoryBean> {
    private static BrowserTabHistoryController instance;

    public BrowserTabBean getBrowserTabBean() {
        return null;
    }

    private BrowserTabHistoryController() {
        super(true);
    }

    public static BrowserTabHistoryController getInstance() {
        if (instance == null) {
            synchronized (BrowserTabHistoryController.class) {
                if (instance == null) {
                    instance = new BrowserTabHistoryController();
                }
            }
        }
        return instance;
    }

    public List<BrowserTabHistoryBean> getBrowserTabHistorySortList() {
        return this.beanDao.queryBuilder().orderAsc(BrowserTabHistoryBeanDao.Properties.Id).list();
    }

    public boolean removeSingle(BrowserTabHistoryBean browserTabHistoryBean) {
        try {
            this.beanDao.queryBuilder().where(BrowserTabHistoryBeanDao.Properties.Id.eq(browserTabHistoryBean.getId()), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
            LogUtils.w("Controller", "removeSingle remove BrowserHistoryBean one :" + browserTabHistoryBean.toString());
            return true;
        } catch (Exception e) {
            LogUtils.e(e);
            return false;
        }
    }

    public BrowserTabHistoryBean queryifExist(int i) {
        return (BrowserTabHistoryBean) this.beanDao.queryBuilder().where(BrowserTabHistoryBeanDao.Properties.TabIndex.eq(Integer.valueOf(i)), new WhereCondition[0]).unique();
    }
}
