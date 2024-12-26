package com.tron.wallet.business.addassets2.repository;

import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.db.greendao.TokenBeanDao;
import java.util.List;
import java.util.concurrent.Callable;
import org.greenrobot.greendao.query.WhereCondition;
public class HotAssetsController extends BaseController<TokenBean> {
    private static HotAssetsController instance;

    private HotAssetsController() {
    }

    public static HotAssetsController getInstance() {
        if (instance == null) {
            synchronized (HotAssetsController.class) {
                if (instance == null) {
                    instance = new HotAssetsController();
                }
            }
        }
        return instance;
    }

    public boolean setHotAssets(final List<TokenBean> list) {
        if (list != null) {
            for (TokenBean tokenBean : list) {
                tokenBean.setUsageType(2);
            }
        }
        try {
            return ((Boolean) this.daoSession.callInTx(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    boolean deleteEntities = deleteEntities(TokenBeanDao.Properties.UsageType.eq(2), new WhereCondition[0]);
                    if (deleteEntities) {
                        deleteEntities = insertMultiObject(list);
                    }
                    return Boolean.valueOf(deleteEntities);
                }
            })).booleanValue();
        } catch (Exception e) {
            LogUtils.e(e);
            return true;
        }
    }

    public List<TokenBean> getHotAssets() {
        return this.beanDao.queryBuilder().where(TokenBeanDao.Properties.UsageType.eq(2), new WhereCondition[0]).list();
    }
}
