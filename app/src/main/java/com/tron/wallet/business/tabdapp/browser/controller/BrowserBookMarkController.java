package com.tron.wallet.business.tabdapp.browser.controller;

import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.addassets2.repository.BaseController;
import com.tron.wallet.business.tabdapp.browser.bean.BrowserBookMarkBean;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.greendao.BrowserBookMarkBeanDao;
import java.util.List;
import org.greenrobot.greendao.query.WhereCondition;
public class BrowserBookMarkController extends BaseController<BrowserBookMarkBean> {
    private static BrowserBookMarkController instance;

    private BrowserBookMarkController() {
        super(true);
    }

    public static BrowserBookMarkController getInstance() {
        if (instance == null) {
            synchronized (BrowserBookMarkController.class) {
                if (instance == null) {
                    instance = new BrowserBookMarkController();
                }
            }
        }
        return instance;
    }

    public List<BrowserBookMarkBean> getBrowserBookMarkSortList() {
        return this.beanDao.queryBuilder().orderDesc(BrowserBookMarkBeanDao.Properties.Id).list();
    }

    public List<BrowserBookMarkBean> getBrowserBookMarkFirstSixList() {
        return this.beanDao.queryBuilder().orderDesc(BrowserBookMarkBeanDao.Properties.Id).limit(11).list();
    }

    public boolean haveExists(String str) {
        if (StringTronUtil.isEmpty(str)) {
            return false;
        }
        try {
            return this.beanDao.queryBuilder().where(BrowserBookMarkBeanDao.Properties.Url.eq(str), new WhereCondition[0]).list().size() > 0;
        } catch (Exception e) {
            LogUtils.e(e);
            return false;
        }
    }

    public boolean removeSingle(String str) {
        try {
            this.beanDao.queryBuilder().where(BrowserBookMarkBeanDao.Properties.Url.eq(str), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
            LogUtils.w("Controller", "removeSingle remove one url:" + str);
            return true;
        } catch (Exception e) {
            LogUtils.e(e);
            return false;
        }
    }

    public long getCount() {
        return this.beanDao.queryBuilder().count();
    }
}
