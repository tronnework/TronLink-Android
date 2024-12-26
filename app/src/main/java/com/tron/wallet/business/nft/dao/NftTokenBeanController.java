package com.tron.wallet.business.nft.dao;

import android.app.Application;
import android.content.Context;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.addassets2.bean.nft.NftTokenBean;
import com.tron.wallet.db.dao.DaoUtils;
import com.tron.wallet.db.greendao.NftTokenBeanDao;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import org.greenrobot.greendao.query.WhereCondition;
public class NftTokenBeanController {
    private static NftTokenBeanController instance;

    public static NftTokenBeanController getInstance() {
        if (instance == null) {
            synchronized (NftTokenBeanController.class) {
                if (instance == null) {
                    instance = new NftTokenBeanController();
                }
            }
        }
        return instance;
    }

    public synchronized boolean clearAndAdd(final List<NftTokenBean> list, final String str) {
        boolean z;
        try {
            final Application application = AppContextUtil.getmApplication();
            z = ((Boolean) DaoUtils.getInstance(application).daoSession.callInTx(new Callable() {
                @Override
                public final Object call() {
                    Boolean lambda$clearAndAdd$0;
                    lambda$clearAndAdd$0 = lambda$clearAndAdd$0(str, list, application);
                    return lambda$clearAndAdd$0;
                }
            })).booleanValue();
        } catch (Exception e) {
            LogUtils.e(e);
            z = false;
        }
        return z;
    }

    public Boolean lambda$clearAndAdd$0(String str, List list, Context context) throws Exception {
        if (hasPending(str)) {
            return false;
        }
        DaoUtils.getInstance().daoSession.queryBuilder(NftTokenBean.class).where(NftTokenBeanDao.Properties.WalletAddress.eq(str), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
        DaoUtils.getInstance().daoSession.getDao(NftTokenBean.class).detachAll();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            NftTokenBean nftTokenBean = (NftTokenBean) it.next();
            nftTokenBean.setSelected(true);
            nftTokenBean.setWalletAddress(str);
        }
        DaoUtils.getInstance(context).insertMultObject(list);
        return true;
    }

    public synchronized void addFollowedTokens(List<NftTokenBean> list, String str) {
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
                DaoUtils.getInstance().insertMultiObject(list, NftTokenBean.class);
            }
        }
    }

    public synchronized void updateUnFollowedTokens(List<NftTokenBean> list, String str) {
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
                DaoUtils.getInstance().insertMultiObject(list, NftTokenBean.class);
            }
        }
    }

    public synchronized void toDeleteTokens(List<NftTokenBean> list, String str) {
        if (list != null) {
            if (list.size() > 0) {
                ArrayList arrayList = new ArrayList();
                for (NftTokenBean nftTokenBean : list) {
                    NftTokenBean findTokenBean = findTokenBean(nftTokenBean, str);
                    if (findTokenBean != null) {
                        findTokenBean.setDoDb(2);
                        arrayList.add(findTokenBean);
                    }
                }
                DaoUtils.getInstance().insertMultiObject(arrayList, NftTokenBean.class);
            }
        }
    }

    public synchronized boolean hasPending(String str) {
        List<NftTokenBean> list = DaoUtils.getInstance().daoSession.getNftTokenBeanDao().queryBuilder().where(NftTokenBeanDao.Properties.WalletAddress.eq(str), NftTokenBeanDao.Properties.DoDb.gt(0)).list();
        if (list != null) {
            if (list.size() > 0) {
                return true;
            }
        }
        return false;
    }

    public NftTokenBean getToken(String str, String str2) {
        List<NftTokenBean> list = DaoUtils.getInstance(AppContextUtil.getmApplication()).daoSession.getNftTokenBeanDao().queryBuilder().where(NftTokenBeanDao.Properties.WalletAddress.eq(str), NftTokenBeanDao.Properties.ContractAddress.eq(str2)).list();
        if (list == null || list.size() <= 0) {
            return null;
        }
        return list.get(0);
    }

    public NftTokenBean getNFTToken(String str) {
        List<NftTokenBean> list = DaoUtils.getInstance(AppContextUtil.getmApplication()).daoSession.getNftTokenBeanDao().queryBuilder().where(NftTokenBeanDao.Properties.ContractAddress.eq(str), new WhereCondition[0]).list();
        if (list == null || list.size() <= 0) {
            return null;
        }
        return list.get(0);
    }

    public List<NftTokenBean> getTokenList(Context context, String str) {
        return DaoUtils.getInstance(context).daoSession.getNftTokenBeanDao().queryBuilder().where(NftTokenBeanDao.Properties.WalletAddress.eq(str), new WhereCondition[0]).list();
    }

    public synchronized List<NftTokenBean> getAllAddedTokenList(String str) {
        List<NftTokenBean> list;
        list = DaoUtils.getInstance(AppContextUtil.getmApplication()).daoSession.getNftTokenBeanDao().queryBuilder().where(NftTokenBeanDao.Properties.WalletAddress.eq(str), NftTokenBeanDao.Properties.DoDb.notEq(2)).list();
        for (NftTokenBean nftTokenBean : list) {
            nftTokenBean.setSelected(true);
        }
        return list;
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
                DaoUtils.getInstance().updateMultObject(list, NftTokenBean.class);
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
                DaoUtils.getInstance().deleteMultObject(list, NftTokenBean.class);
            }
        }
    }

    private NftTokenBean findTokenBean(NftTokenBean nftTokenBean, String str) {
        List list = DaoUtils.getInstance().daoSession.queryBuilder(NftTokenBean.class).where(NftTokenBeanDao.Properties.Id.eq(nftTokenBean.getId()), NftTokenBeanDao.Properties.ContractAddress.eq(nftTokenBean.getContractAddress()), NftTokenBeanDao.Properties.WalletAddress.eq(str)).list();
        if (list == null || list.size() <= 0) {
            return null;
        }
        return (NftTokenBean) list.get(0);
    }
}
