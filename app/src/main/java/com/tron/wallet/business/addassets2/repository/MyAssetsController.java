package com.tron.wallet.business.addassets2.repository;

import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.db.greendao.TokenBeanDao;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import org.greenrobot.greendao.query.WhereCondition;
public class MyAssetsController extends BaseController<TokenBean> {
    private static MyAssetsController instance;

    private MyAssetsController() {
    }

    public static MyAssetsController getInstance() {
        if (instance == null) {
            synchronized (MyAssetsController.class) {
                if (instance == null) {
                    instance = new MyAssetsController();
                }
            }
        }
        return instance;
    }

    public synchronized boolean setMyAssets(final String str, final List<TokenBean> list) {
        boolean z = true;
        try {
        } catch (Exception e) {
            randomReportSentry(e);
        }
        if (hasPending(str)) {
            return false;
        }
        if (list != null) {
            for (TokenBean tokenBean : list) {
                tokenBean.setUsageType(1);
                tokenBean.setAddress(str);
            }
        }
        z = ((Boolean) this.daoSession.callInTx(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                boolean deleteEntities = deleteEntities(TokenBeanDao.Properties.Address.eq(str), new WhereCondition[0]);
                if (deleteEntities) {
                    deleteEntities = insertMultiObject(list);
                }
                return Boolean.valueOf(deleteEntities);
            }
        })).booleanValue();
        return z;
    }

    public List<TokenBean> getMyAssets(String str) {
        return this.beanDao.queryBuilder().where(TokenBeanDao.Properties.Address.eq(str), TokenBeanDao.Properties.DoDb.notEq(2)).list();
    }

    public TokenBean getToken(String str, String str2) {
        List list;
        if (str2 == null) {
            list = this.beanDao.queryBuilder().where(TokenBeanDao.Properties.Address.eq(str), TokenBeanDao.Properties.Type.eq(0)).list();
        } else {
            list = this.beanDao.queryBuilder().where(TokenBeanDao.Properties.Address.eq(str), this.beanDao.queryBuilder().or(TokenBeanDao.Properties.ContractAddress.eq(str2), TokenBeanDao.Properties.Id.eq(str2), new WhereCondition[0])).list();
        }
        if (list == null || list.size() <= 0) {
            return null;
        }
        return (TokenBean) list.get(0);
    }

    public TokenBean getTokenBean(String str) {
        List list = this.beanDao.queryBuilder().where(this.beanDao.queryBuilder().or(TokenBeanDao.Properties.ContractAddress.eq(str), TokenBeanDao.Properties.Id.eq(str), new WhereCondition[0]), new WhereCondition[0]).list();
        if (list == null || list.size() <= 0) {
            return null;
        }
        return (TokenBean) list.get(0);
    }

    public synchronized void addTokens(List<TokenBean> list, String str) {
        if (list != null) {
            if (list.size() > 0) {
                for (TokenBean tokenBean : list) {
                    TokenBean findTokenBean = findTokenBean(tokenBean, str);
                    if (findTokenBean != null) {
                        tokenBean.setTokenId(findTokenBean.getTokenId());
                    } else {
                        tokenBean.setTokenId(null);
                    }
                    tokenBean.setAddress(str);
                    tokenBean.setUsageType(1);
                    tokenBean.setDoDb(1);
                }
                insertMultiObject(list);
            }
        }
    }

    public synchronized void toDeleteTokens(List<TokenBean> list, String str) {
        if (list != null) {
            if (list.size() > 0) {
                for (TokenBean tokenBean : list) {
                    TokenBean findTokenBean = findTokenBean(tokenBean, str);
                    if (findTokenBean != null) {
                        tokenBean.setTokenId(findTokenBean.getTokenId());
                    } else {
                        tokenBean.setTokenId(null);
                    }
                    tokenBean.setAddress(str);
                    tokenBean.setUsageType(1);
                    tokenBean.setDoDb(2);
                }
                insertMultiObject(list);
            }
        }
    }

    public synchronized boolean hasPending(String str) {
        return this.beanDao.queryBuilder().where(TokenBeanDao.Properties.Address.eq(str), TokenBeanDao.Properties.DoDb.gt(0)).count() > 0;
    }

    public List<TokenBean> getPendingTokens(String str) {
        return this.beanDao.queryBuilder().where(TokenBeanDao.Properties.Address.eq(str), TokenBeanDao.Properties.DoDb.gt(0)).list();
    }

    public synchronized void updateTokens(List<TokenBean> list, String str) {
        if (list != null) {
            if (list.size() > 0) {
                Iterator<TokenBean> it = list.iterator();
                while (it.hasNext()) {
                    TokenBean next = it.next();
                    TokenBean findTokenBean = findTokenBean(next, str);
                    if (findTokenBean != null) {
                        next.setTokenId(findTokenBean.getTokenId());
                    } else {
                        it.remove();
                    }
                }
                insertMultiObject(list);
            }
        }
    }

    public synchronized void deleteTokens(List<TokenBean> list, String str) {
        if (list != null) {
            if (list.size() > 0) {
                Iterator<TokenBean> it = list.iterator();
                while (it.hasNext()) {
                    TokenBean next = it.next();
                    TokenBean findTokenBean = findTokenBean(next, str);
                    if (findTokenBean != null) {
                        next.setTokenId(findTokenBean.getTokenId());
                    } else {
                        it.remove();
                    }
                }
                deleteMultiObject(list);
            }
        }
    }

    private TokenBean findTokenBean(TokenBean tokenBean, String str) {
        List list = this.beanDao.queryBuilder().where(TokenBeanDao.Properties.Id.eq(tokenBean.getId()), TokenBeanDao.Properties.ContractAddress.eq(tokenBean.getContractAddress()), TokenBeanDao.Properties.Address.eq(str)).list();
        if (list == null || list.size() <= 0) {
            return null;
        }
        return (TokenBean) list.get(0);
    }
}
