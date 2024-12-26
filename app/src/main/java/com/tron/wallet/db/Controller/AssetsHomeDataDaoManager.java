package com.tron.wallet.db.Controller;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.cache.DiskLruCacheHelper;
import com.tron.wallet.business.addassets2.bean.asset.AssetsHomeData;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.bean.token.UnAddedTokenBean;
import com.tron.wallet.common.utils.GsonUtils;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.dao.BaseDao;
import com.tron.wallet.db.dao.DaoUtils;
import com.tron.wallet.db.greendao.AssetsHomeDataDao;
import com.tron.wallet.db.greendao.TokenBeanDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import org.greenrobot.greendao.query.WhereCondition;
public class AssetsHomeDataDaoManager {
    public static final String TAG = "AssetsHomeDataDaoManager";
    private static DiskLruCacheHelper diskLruCacheHelper;

    public static DiskLruCacheHelper getDiskLruCacheHelper(Context context) {
        if (diskLruCacheHelper == null && context != null) {
            try {
                diskLruCacheHelper = new DiskLruCacheHelper(context);
            } catch (IOException e) {
                LogUtils.e(e);
            }
        }
        return diskLruCacheHelper;
    }

    public static boolean clearAndAdd(Context context, AssetsHomeData assetsHomeData, String str) {
        try {
            getDiskLruCacheHelper(context).put(getKey(str), new Gson().toJson(assetsHomeData));
            clearAndAdd(AppContextUtil.getContext(), assetsHomeData, str, SpAPI.THIS.getCurrentChainId());
            return true;
        } catch (Exception e) {
            LogUtils.e(e);
            return false;
        }
    }

    public static synchronized boolean clearAndAdd(final Context context, final AssetsHomeData assetsHomeData, final String str, final String str2) {
        boolean z;
        synchronized (AssetsHomeDataDaoManager.class) {
            try {
                z = ((Boolean) DaoUtils.getInstance(context).daoSession.callInTx(new Callable() {
                    @Override
                    public final Object call() {
                        return AssetsHomeDataDaoManager.lambda$clearAndAdd$0(str, str2, assetsHomeData, context);
                    }
                })).booleanValue();
            } catch (Exception e) {
                LogUtils.e(e);
                z = false;
            }
        }
        return z;
    }

    public static Boolean lambda$clearAndAdd$0(String str, String str2, AssetsHomeData assetsHomeData, Context context) throws Exception {
        DaoUtils.getInstance().daoSession.queryBuilder(AssetsHomeData.class).where(AssetsHomeDataDao.Properties.Address.eq(str), AssetsHomeDataDao.Properties.NodeId.eq(str2)).buildDelete().executeDeleteWithoutDetachingEntities();
        DaoUtils.getInstance().daoSession.getDao(AssetsHomeData.class).detachAll();
        assetsHomeData.setNodeId(str2);
        assetsHomeData.setAddress(str);
        DaoUtils.getInstance(context).insertObject(assetsHomeData);
        if (hasPending(str)) {
            return false;
        }
        DaoUtils.getInstance().daoSession.queryBuilder(TokenBean.class).where(TokenBeanDao.Properties.Address.eq(str), TokenBeanDao.Properties.NodeId.eq(str2), TokenBeanDao.Properties.IsMainChain.eq(Boolean.valueOf("MainChain".equals(SpAPI.THIS.getCurrentChainName())))).buildDelete().executeDeleteWithoutDetachingEntities();
        DaoUtils.getInstance().daoSession.getDao(TokenBean.class).detachAll();
        String currentChainName = SpAPI.THIS.getCurrentChainName();
        CopyOnWriteArrayList<TokenBean> copyOnWriteArrayList = assetsHomeData.token;
        for (TokenBean tokenBean : copyOnWriteArrayList) {
            tokenBean.isSelected = true;
            tokenBean.nodeId = assetsHomeData.nodeId;
            tokenBean.address = str;
            tokenBean.isMainChain = "MainChain".equals(currentChainName);
            fillForShortName(tokenBean);
        }
        DaoUtils.getInstance(context).insertMultObject(copyOnWriteArrayList);
        return true;
    }

    public static synchronized boolean clearAndAddToken(final Context context, final List<TokenBean> list, final String str, final String str2) {
        synchronized (AssetsHomeDataDaoManager.class) {
            if (list != null) {
                if (list.size() != 0) {
                    try {
                        DaoUtils.getInstance(context).daoSession.runInTx(new Runnable() {
                            @Override
                            public final void run() {
                                AssetsHomeDataDaoManager.lambda$clearAndAddToken$1(context, list, str, str2);
                            }
                        });
                        return true;
                    } catch (Exception e) {
                        LogUtils.e(e);
                        return false;
                    }
                }
            }
            return false;
        }
    }

    public static void lambda$clearAndAddToken$1(Context context, List list, String str, String str2) {
        BaseDao daoUtils = DaoUtils.getInstance(context);
        daoUtils.deleteObjectWithWhere("TOKEN_BEAN", "WHERE " + TokenBeanDao.Properties.Type.columnName + " != '0' ", new String[0]);
        String currentChainName = SpAPI.THIS.getCurrentChainName();
        if (list.size() > 0) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                TokenBean tokenBean = (TokenBean) it.next();
                tokenBean.isSelected = true;
                tokenBean.address = str;
                tokenBean.nodeId = str2;
                tokenBean.isMainChain = "MainChain".equals(currentChainName);
                fillForShortName(tokenBean);
            }
        }
        DaoUtils.getInstance(context).insertMultObject(list);
    }

    private static void fillForShortName(TokenBean tokenBean) {
        if (tokenBean == null || !TextUtils.isEmpty(tokenBean.shortName)) {
            return;
        }
        tokenBean.shortName = tokenBean.name;
    }

    public static void fillForShortName2(UnAddedTokenBean unAddedTokenBean) {
        if (unAddedTokenBean == null || !TextUtils.isEmpty(unAddedTokenBean.shortName)) {
            return;
        }
        unAddedTokenBean.shortName = unAddedTokenBean.name;
    }

    public static TokenBean getToken(String str, String str2) {
        List<TokenBean> list;
        TokenBeanDao tokenBeanDao = DaoUtils.getInstance(AppContextUtil.getmApplication()).daoSession.getTokenBeanDao();
        if (str2 == null) {
            list = tokenBeanDao.queryBuilder().where(TokenBeanDao.Properties.Address.eq(str), TokenBeanDao.Properties.IsMainChain.eq(Boolean.valueOf("MainChain".equals(SpAPI.THIS.getCurrentChainName()))), TokenBeanDao.Properties.NodeId.eq(SpAPI.THIS.getCurrentChainId()), TokenBeanDao.Properties.Type.eq(0)).list();
        } else {
            list = tokenBeanDao.queryBuilder().where(TokenBeanDao.Properties.Address.eq(str), TokenBeanDao.Properties.IsMainChain.eq(Boolean.valueOf("MainChain".equals(SpAPI.THIS.getCurrentChainName()))), TokenBeanDao.Properties.NodeId.eq(SpAPI.THIS.getCurrentChainId()), tokenBeanDao.queryBuilder().or(TokenBeanDao.Properties.ContractAddress.eq(str2), TokenBeanDao.Properties.Id.eq(str2), new WhereCondition[0])).list();
        }
        if (list == null || list.size() <= 0) {
            return null;
        }
        return list.get(0);
    }

    @Deprecated
    public static void addToken(List<UnAddedTokenBean> list, String str) {
        addToken(AppContextUtil.getContext(), list, str, SpAPI.THIS.getCurrentChainId());
    }

    @Deprecated
    public static void addToken(final Context context, final List<UnAddedTokenBean> list, final String str, final String str2) {
        if (list == null || list.size() <= 0) {
            return;
        }
        DaoUtils.getInstance(context).daoSession.runInTx(new Runnable() {
            @Override
            public void run() {
                ArrayList arrayList = new ArrayList();
                List<UnAddedTokenBean> list2 = list;
                String currentChainName = SpAPI.THIS.getCurrentChainName();
                if (list2.size() > 0) {
                    for (UnAddedTokenBean unAddedTokenBean : list2) {
                        TokenBean tokenBean = new TokenBean(unAddedTokenBean);
                        tokenBean.address = str;
                        tokenBean.ispendinguploading = 1;
                        tokenBean.doDb = 1;
                        tokenBean.nodeId = str2;
                        AssetsHomeDataDaoManager.fillForShortName2(unAddedTokenBean);
                        tokenBean.isMainChain = "MainChain".equals(currentChainName);
                        arrayList.add(tokenBean);
                    }
                }
                DaoUtils.getInstance(context).insertMultObject(arrayList);
            }
        });
    }

    public static synchronized void addFollowedTokens(List<TokenBean> list, String str) {
        synchronized (AssetsHomeDataDaoManager.class) {
            if (list != null) {
                if (list.size() > 0) {
                    String currentChainName = SpAPI.THIS.getCurrentChainName();
                    String currentChainId = SpAPI.THIS.getCurrentChainId();
                    for (TokenBean tokenBean : list) {
                        TokenBean findTokenBean = findTokenBean(tokenBean, str, currentChainId);
                        if (findTokenBean != null) {
                            tokenBean.setTokenId(findTokenBean.getTokenId());
                        } else {
                            tokenBean.setTokenId(null);
                        }
                        tokenBean.address = str;
                        tokenBean.nodeId = currentChainId;
                        fillForShortName(tokenBean);
                        tokenBean.isMainChain = "MainChain".equals(currentChainName);
                        tokenBean.ispendinguploading = 1;
                        tokenBean.doDb = 1;
                    }
                    DaoUtils.getInstance().insertMultiObject(list, TokenBean.class);
                }
            }
        }
    }

    public static synchronized void updateUnFollowedTokens(List<TokenBean> list, String str) {
        synchronized (AssetsHomeDataDaoManager.class) {
            if (list != null) {
                if (list.size() > 0) {
                    String currentChainName = SpAPI.THIS.getCurrentChainName();
                    String currentChainId = SpAPI.THIS.getCurrentChainId();
                    for (TokenBean tokenBean : list) {
                        TokenBean findTokenBean = findTokenBean(tokenBean, str, currentChainId);
                        if (findTokenBean != null) {
                            tokenBean.setTokenId(findTokenBean.getTokenId());
                        } else {
                            tokenBean.setTokenId(null);
                        }
                        tokenBean.address = str;
                        tokenBean.nodeId = currentChainId;
                        fillForShortName(tokenBean);
                        tokenBean.isMainChain = "MainChain".equals(currentChainName);
                        tokenBean.ispendinguploading = 1;
                        tokenBean.doDb = 2;
                    }
                    DaoUtils.getInstance().insertMultiObject(list, TokenBean.class);
                }
            }
        }
    }

    public static synchronized void toDeleteTokens(List<TokenBean> list, String str) {
        synchronized (AssetsHomeDataDaoManager.class) {
            if (list != null) {
                if (list.size() > 0) {
                    ArrayList arrayList = new ArrayList();
                    String currentChainId = SpAPI.THIS.getCurrentChainId();
                    for (TokenBean tokenBean : list) {
                        TokenBean findTokenBean = findTokenBean(tokenBean, str, currentChainId);
                        if (findTokenBean != null) {
                            findTokenBean.ispendinguploading = 1;
                            findTokenBean.doDb = 2;
                            arrayList.add(findTokenBean);
                        }
                    }
                    DaoUtils.getInstance().insertMultiObject(arrayList, TokenBean.class);
                }
            }
        }
    }

    public static synchronized boolean hasPending(String str) {
        synchronized (AssetsHomeDataDaoManager.class) {
            List<TokenBean> list = DaoUtils.getInstance().daoSession.getTokenBeanDao().queryBuilder().where(TokenBeanDao.Properties.Address.eq(str), TokenBeanDao.Properties.Ispendinguploading.eq("1"), TokenBeanDao.Properties.IsMainChain.eq(Boolean.valueOf("MainChain".equals(SpAPI.THIS.getCurrentChainName()))), TokenBeanDao.Properties.NodeId.eq(SpAPI.THIS.getCurrentChainId())).orderAsc(TokenBeanDao.Properties.ShortName).list();
            if (list != null) {
                if (list.size() > 0) {
                    return true;
                }
            }
            return false;
        }
    }

    public static AssetsHomeData getHomeData(Context context, String str) {
        if (getDiskLruCacheHelper(context) == null) {
            return null;
        }
        try {
            return (AssetsHomeData) GsonUtils.gsonToBean(getDiskLruCacheHelper(context).getAsString(getKey(str)), AssetsHomeData.class);
        } catch (Exception e) {
            LogUtils.e(e);
            return null;
        }
    }

    public static AssetsHomeData getAddAssetsHomeData(Context context, String str) {
        if (getDiskLruCacheHelper(context) == null) {
            return null;
        }
        String currentChainId = SpAPI.THIS.getCurrentChainId();
        try {
            AssetsHomeData assetsHomeData = (AssetsHomeData) GsonUtils.gsonToBean(getDiskLruCacheHelper(context).getAsString(getKey(str)), AssetsHomeData.class);
            if (assetsHomeData != null) {
                assetsHomeData.token = getAllAddedTokenList(context, currentChainId, str);
                return assetsHomeData;
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
        return getHomeData(context, currentChainId, str);
    }

    public static AssetsHomeData getHomeData(Context context, String str, String str2) {
        BaseDao daoUtils = DaoUtils.getInstance(context);
        List QueryObject = daoUtils.QueryObject(AssetsHomeData.class, "WHERE " + AssetsHomeDataDao.Properties.Address.columnName + " = '" + str2 + "' AND " + AssetsHomeDataDao.Properties.NodeId.columnName + " = '" + str + "'", new String[0]);
        if (QueryObject == null || QueryObject.size() <= 0) {
            return null;
        }
        AssetsHomeData assetsHomeData = (AssetsHomeData) QueryObject.get(0);
        assetsHomeData.token = getAllAddedTokenList(context, str, str2);
        return assetsHomeData;
    }

    public static List<TokenBean> getTokenList(Context context, String str) {
        return DaoUtils.getInstance(context).daoSession.getTokenBeanDao().queryBuilder().where(TokenBeanDao.Properties.Address.eq(str), TokenBeanDao.Properties.IsMainChain.eq(Boolean.valueOf(SpAPI.THIS.getCurrentChainName().equals("MainChain"))), TokenBeanDao.Properties.NodeId.eq(SpAPI.THIS.getCurrentChainId())).list();
    }

    public static CopyOnWriteArrayList getAllAddedTokenList(Context context, String str, String str2) {
        List<TokenBean> list = DaoUtils.getInstance(context).daoSession.getTokenBeanDao().queryBuilder().where(TokenBeanDao.Properties.Address.eq(str2), TokenBeanDao.Properties.Top.eq(1), TokenBeanDao.Properties.DoDb.notEq(2), TokenBeanDao.Properties.IsMainChain.eq(Boolean.valueOf(SpAPI.THIS.getCurrentChainName().equals("MainChain"))), TokenBeanDao.Properties.NodeId.eq(str)).list();
        List<TokenBean> list2 = DaoUtils.getInstance(context).daoSession.getTokenBeanDao().queryBuilder().where(TokenBeanDao.Properties.Address.eq(str2), TokenBeanDao.Properties.DoDb.notEq(2), TokenBeanDao.Properties.Top.notEq(1), TokenBeanDao.Properties.IsMainChain.eq(Boolean.valueOf(SpAPI.THIS.getCurrentChainName().equals("MainChain"))), TokenBeanDao.Properties.NodeId.eq(str)).orderAsc(TokenBeanDao.Properties.ShortName).list();
        Iterator<TokenBean> it = list2.iterator();
        TokenBean tokenBean = null;
        while (it.hasNext()) {
            TokenBean next = it.next();
            next.isSelected = true;
            if (next.type == 0) {
                it.remove();
                tokenBean = next;
            }
        }
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        if (tokenBean != null) {
            copyOnWriteArrayList.add(tokenBean);
        }
        copyOnWriteArrayList.addAll(list);
        copyOnWriteArrayList.addAll(list2);
        return copyOnWriteArrayList;
    }

    public static synchronized void updateTokens(List<TokenBean> list, String str) {
        synchronized (AssetsHomeDataDaoManager.class) {
            if (list != null) {
                if (list.size() != 0) {
                    String currentChainId = SpAPI.THIS.getCurrentChainId();
                    Iterator<TokenBean> it = list.iterator();
                    while (it.hasNext()) {
                        TokenBean next = it.next();
                        TokenBean findTokenBean = findTokenBean(next, str, currentChainId);
                        if (findTokenBean != null) {
                            next.setTokenId(findTokenBean.getTokenId());
                        } else {
                            it.remove();
                        }
                    }
                    DaoUtils.getInstance().updateMultObject(list, TokenBean.class);
                }
            }
        }
    }

    public static synchronized void deleteTokens(List<TokenBean> list, String str) {
        synchronized (AssetsHomeDataDaoManager.class) {
            if (list != null) {
                if (list.size() != 0) {
                    String currentChainId = SpAPI.THIS.getCurrentChainId();
                    Iterator<TokenBean> it = list.iterator();
                    while (it.hasNext()) {
                        TokenBean next = it.next();
                        TokenBean findTokenBean = findTokenBean(next, str, currentChainId);
                        if (findTokenBean != null) {
                            next.setTokenId(findTokenBean.getTokenId());
                        } else {
                            it.remove();
                        }
                    }
                    DaoUtils.getInstance().deleteMultObject(list, TokenBean.class);
                }
            }
        }
    }

    private static TokenBean findTokenBean(TokenBean tokenBean, String str, String str2) {
        List list = DaoUtils.getInstance().daoSession.queryBuilder(TokenBean.class).where(TokenBeanDao.Properties.Id.eq(tokenBean.getId()), TokenBeanDao.Properties.ContractAddress.eq(tokenBean.getContractAddress()), TokenBeanDao.Properties.Address.eq(str), TokenBeanDao.Properties.NodeId.eq(str2), TokenBeanDao.Properties.IsMainChain.eq(Boolean.valueOf(SpAPI.THIS.getCurrentChainName().equals("MainChain")))).list();
        if (list == null || list.size() <= 0) {
            return null;
        }
        return (TokenBean) list.get(0);
    }

    private static String getKey(String str) {
        return IRequest.ENVIRONMENT.toString() + "_" + SpAPI.THIS.getCurrentChainName() + "_" + str;
    }
}
