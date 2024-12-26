package com.tron.wallet.business.addassets2.repository;

import android.text.TextUtils;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.addassets2.bean.TokenSortBean;
import com.tron.wallet.business.addassets2.bean.nft.NftTokenBean;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.db.greendao.TokenSortBeanDao;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
public class FollowAssetsSortListController extends BaseController<TokenSortBean> {
    private static FollowAssetsSortListController instance;

    private FollowAssetsSortListController() {
    }

    public static FollowAssetsSortListController getInstance() {
        if (instance == null) {
            synchronized (FollowAssetsSortListController.class) {
                if (instance == null) {
                    instance = new FollowAssetsSortListController();
                }
            }
        }
        return instance;
    }

    public List<TokenSortBean> getTokenSortList(String str) {
        return this.beanDao.queryBuilder().where(TokenSortBeanDao.Properties.Address.eq(str), TokenSortBeanDao.Properties.Type.notEq(5)).orderDesc(TokenSortBeanDao.Properties.Index).list();
    }

    public List<TokenSortBean> getTokenSortList(String str, int i) {
        return this.beanDao.queryBuilder().where(TokenSortBeanDao.Properties.Address.eq(str), TokenSortBeanDao.Properties.Type.eq(Integer.valueOf(i))).orderDesc(TokenSortBeanDao.Properties.Index).list();
    }

    public <T> boolean setTokenSortListByTokenBeanList(String str, List<T> list, int i) {
        if (list == null || list.size() <= 1) {
            return true;
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < list.size(); i2++) {
            TokenSortBean tokenSortBean = new TokenSortBean();
            tokenSortBean.setType(i);
            tokenSortBean.setAddress(str);
            tokenSortBean.setIndex(i2);
            T t = list.get(i2);
            if (t instanceof TokenBean) {
                TokenBean tokenBean = (TokenBean) t;
                if (!TextUtils.isEmpty(tokenBean.getId())) {
                    tokenSortBean.setContractAddress(tokenBean.getId());
                } else if (!TextUtils.isEmpty(tokenBean.getContractAddress())) {
                    tokenSortBean.setContractAddress(tokenBean.getContractAddress());
                } else {
                    tokenSortBean.setContractAddress(tokenBean.getName());
                }
                arrayList.add(tokenSortBean);
            } else if (t instanceof NftTokenBean) {
                NftTokenBean nftTokenBean = (NftTokenBean) t;
                if (!TextUtils.isEmpty(nftTokenBean.getContractAddress())) {
                    tokenSortBean.setContractAddress(nftTokenBean.getContractAddress());
                } else {
                    tokenSortBean.setContractAddress(nftTokenBean.getName());
                }
                arrayList.add(tokenSortBean);
            }
        }
        return setTokenSortList(str, arrayList, i);
    }

    public boolean setTokenSortList(final String str, final List<TokenSortBean> list, final int i) {
        try {
            return ((Boolean) this.daoSession.callInTx(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    boolean deleteEntities = deleteEntities(TokenSortBeanDao.Properties.Address.eq(str), TokenSortBeanDao.Properties.Type.eq(Integer.valueOf(i)));
                    if (deleteEntities) {
                        deleteEntities = insertMultiObject(list);
                    }
                    return Boolean.valueOf(deleteEntities);
                }
            })).booleanValue();
        } catch (Exception e) {
            LogUtils.e(e);
            return false;
        }
    }

    public boolean removeSingle(String str, TokenBean tokenBean) {
        try {
            this.beanDao.queryBuilder().where(TokenSortBeanDao.Properties.Address.eq(str), this.beanDao.queryBuilder().or(TokenSortBeanDao.Properties.ContractAddress.eq(tokenBean.getContractAddress()), TokenSortBeanDao.Properties.ContractAddress.eq(tokenBean.getId()), TokenSortBeanDao.Properties.ContractAddress.eq(tokenBean.getName()))).buildDelete().executeDeleteWithoutDetachingEntities();
            LogUtils.w("SortController", "remove one " + tokenBean.getName());
            return true;
        } catch (Exception e) {
            LogUtils.e(e);
            return false;
        }
    }
}
