package com.tron.wallet.business.addassets2.repository;

import com.tron.wallet.business.addassets2.bean.nft.NftTokenBean;
import com.tron.wallet.db.greendao.NftTokenBeanDao;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import org.greenrobot.greendao.query.WhereCondition;
public class MyCollectionsController extends BaseController<NftTokenBean> {
    private static MyCollectionsController instance;

    private MyCollectionsController() {
    }

    public static MyCollectionsController getInstance() {
        if (instance == null) {
            synchronized (MyCollectionsController.class) {
                if (instance == null) {
                    instance = new MyCollectionsController();
                }
            }
        }
        return instance;
    }

    public synchronized boolean setMyAssets(final String str, final List<NftTokenBean> list) {
        boolean z;
        try {
        } catch (Exception e) {
            randomReportSentry(e);
            z = true;
        }
        if (hasPending(str)) {
            return false;
        }
        if (list != null) {
            for (NftTokenBean nftTokenBean : list) {
                nftTokenBean.setWalletAddress(str);
            }
        }
        z = ((Boolean) this.daoSession.callInTx(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                boolean deleteEntities = deleteEntities(NftTokenBeanDao.Properties.WalletAddress.eq(str), new WhereCondition[0]);
                if (deleteEntities) {
                    deleteEntities = insertMultiObject(list);
                }
                return Boolean.valueOf(deleteEntities);
            }
        })).booleanValue();
        return z;
    }

    public List<NftTokenBean> getMyAssets(String str) {
        return this.beanDao.queryBuilder().where(NftTokenBeanDao.Properties.WalletAddress.eq(str), NftTokenBeanDao.Properties.DoDb.notEq(2)).list();
    }

    public NftTokenBean getToken(String str, String str2) {
        List list = this.beanDao.queryBuilder().where(NftTokenBeanDao.Properties.WalletAddress.eq(str), NftTokenBeanDao.Properties.ContractAddress.eq(str2)).list();
        if (list == null || list.size() <= 0) {
            return null;
        }
        return (NftTokenBean) list.get(0);
    }

    public synchronized void addTokens(List<NftTokenBean> list, String str) {
        if (list != null) {
            if (list.size() > 0) {
                for (NftTokenBean nftTokenBean : list) {
                    NftTokenBean findTokenBean = findTokenBean(nftTokenBean, str);
                    if (findTokenBean != null) {
                        nftTokenBean.setIdx(findTokenBean.getIdx());
                    } else {
                        nftTokenBean.setIdx(null);
                    }
                    nftTokenBean.setWalletAddress(str);
                    nftTokenBean.setDoDb(1);
                }
                insertMultiObject(list);
            }
        }
    }

    public synchronized void toDeleteTokens(List<NftTokenBean> list, String str) {
        if (list != null) {
            if (list.size() > 0) {
                for (NftTokenBean nftTokenBean : list) {
                    NftTokenBean findTokenBean = findTokenBean(nftTokenBean, str);
                    if (findTokenBean != null) {
                        nftTokenBean.setIdx(findTokenBean.getIdx());
                    } else {
                        nftTokenBean.setIdx(null);
                    }
                    nftTokenBean.setWalletAddress(str);
                    nftTokenBean.setDoDb(2);
                }
                insertMultiObject(list);
            }
        }
    }

    public synchronized boolean hasPending(String str) {
        return this.beanDao.queryBuilder().where(NftTokenBeanDao.Properties.WalletAddress.eq(str), NftTokenBeanDao.Properties.DoDb.gt(0)).count() > 0;
    }

    public List<NftTokenBean> getPendingTokens(String str) {
        return this.beanDao.queryBuilder().where(NftTokenBeanDao.Properties.WalletAddress.eq(str), NftTokenBeanDao.Properties.DoDb.gt(0)).list();
    }

    public synchronized void updateTokens(List<NftTokenBean> list, String str) {
        if (list != null) {
            if (list.size() > 0) {
                Iterator<NftTokenBean> it = list.iterator();
                while (it.hasNext()) {
                    NftTokenBean next = it.next();
                    NftTokenBean findTokenBean = findTokenBean(next, str);
                    if (findTokenBean != null) {
                        next.setIdx(findTokenBean.getIdx());
                    } else {
                        it.remove();
                    }
                }
                insertMultiObject(list);
            }
        }
    }

    public synchronized void deleteTokens(List<NftTokenBean> list, String str) {
        if (list != null) {
            if (list.size() > 0) {
                Iterator<NftTokenBean> it = list.iterator();
                while (it.hasNext()) {
                    NftTokenBean next = it.next();
                    NftTokenBean findTokenBean = findTokenBean(next, str);
                    if (findTokenBean != null) {
                        next.setIdx(findTokenBean.getIdx());
                    } else {
                        it.remove();
                    }
                }
                deleteMultiObject(list);
            }
        }
    }

    private NftTokenBean findTokenBean(NftTokenBean nftTokenBean, String str) {
        List list = this.beanDao.queryBuilder().where(NftTokenBeanDao.Properties.Id.eq(nftTokenBean.getId()), NftTokenBeanDao.Properties.ContractAddress.eq(nftTokenBean.getContractAddress()), NftTokenBeanDao.Properties.WalletAddress.eq(str)).list();
        if (list == null || list.size() <= 0) {
            return null;
        }
        return (NftTokenBean) list.get(0);
    }
}
