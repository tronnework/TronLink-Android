package com.tron.wallet.business.security.tokencheck.controller;

import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.addassets2.repository.BaseController;
import com.tron.wallet.business.security.tokencheck.bean.TokenCheckBean;
import com.tron.wallet.db.greendao.TokenCheckBeanDao;
import org.greenrobot.greendao.query.WhereCondition;
public class IgnoreTokenController extends BaseController<TokenCheckBean> {
    private static IgnoreTokenController instance;

    private IgnoreTokenController() {
        super(true);
    }

    public static IgnoreTokenController getInstance() {
        if (instance == null) {
            synchronized (IgnoreTokenController.class) {
                if (instance == null) {
                    instance = new IgnoreTokenController();
                }
            }
        }
        return instance;
    }

    public boolean removeSingle(TokenCheckBean tokenCheckBean) {
        try {
            this.beanDao.queryBuilder().where(TokenCheckBeanDao.Properties.Id.eq(tokenCheckBean.id), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
            LogUtils.w("Controller", "removeSingle remove TokenCheckBeanDao one :" + tokenCheckBean.toString());
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
