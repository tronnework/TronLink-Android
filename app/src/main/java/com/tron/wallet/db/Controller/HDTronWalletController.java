package com.tron.wallet.db.Controller;

import android.util.Pair;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.db.bean.DappAuthorizedProjectBean;
import com.tron.wallet.db.bean.HdTronRelationshipBean;
import com.tron.wallet.db.dao.DaoUtils;
import com.tron.wallet.db.greendao.DaoSession;
import com.tron.wallet.db.greendao.HdTronRelationshipBeanDao;
import com.tron.wallet.db.wallet.WalletUtils;
import j$.util.Collection;
import j$.util.Objects;
import j$.util.function.Predicate$-CC;
import j$.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Predicate;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;
import org.tron.walletserver.Wallet;
import org.tron.walletserver.WalletPath;
public class HDTronWalletController {

    public enum Order {
        CreateTime,
        Path
    }

    private static int compareNum(long j, long j2) {
        int i = (j > j2 ? 1 : (j == j2 ? 0 : -1));
        if (i > 0) {
            return 1;
        }
        return i < 0 ? -1 : 0;
    }

    public static int queryHdWalletCounts() {
        return queryHdWalletCounts(queryAllRelationFilterNonHdBeans());
    }

    public static int queryHdWalletCounts(List<HdTronRelationshipBean> list) {
        HashSet hashSet = new HashSet();
        Set<String> walletNames = WalletUtils.getWalletNames();
        if (list == null || list.isEmpty()) {
            return 0;
        }
        for (HdTronRelationshipBean hdTronRelationshipBean : list) {
            if (deleteDirtyData(walletNames, hdTronRelationshipBean)) {
                break;
            } else if (!hashSet.contains(hdTronRelationshipBean.getRelationshipHDAddress())) {
                hashSet.add(hdTronRelationshipBean.getRelationshipHDAddress());
            }
        }
        return hashSet.size();
    }

    public static String getCurrentHdRelationshipAddress() {
        HdTronRelationshipBean currentHd = getCurrentHd();
        return currentHd == null ? "" : currentHd.getRelationshipHDAddress();
    }

    public static HdTronRelationshipBean getCurrentHd() {
        List<HdTronRelationshipBean> queryAllRelationFilterNonHdBeans = queryAllRelationFilterNonHdBeans();
        if (queryAllRelationFilterNonHdBeans == null || queryAllRelationFilterNonHdBeans.size() == 0) {
            return null;
        }
        return queryAllRelationFilterNonHdBeans.get(0);
    }

    public static List<Pair<Integer, HdTronRelationshipBean>> queryAllHD() {
        DaoUtils.getLightInstance().daoSession.getHdTronRelationshipBeanDao().detachAll();
        final HashMap hashMap = new HashMap();
        final Set<String> walletNames = WalletUtils.getWalletNames();
        Collection.-EL.stream(DaoUtils.getLightInstance().daoSession.queryBuilder(HdTronRelationshipBean.class).orderAsc(HdTronRelationshipBeanDao.Properties.CreateTime).list()).forEach(new Consumer() {
            @Override
            public final void accept(Object obj) {
                HDTronWalletController.lambda$queryAllHD$0(walletNames, hashMap, (HdTronRelationshipBean) obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        if (hashMap.size() > 0) {
            ArrayList arrayList = new ArrayList();
            for (String str : hashMap.keySet()) {
                Pair pair = (Pair) hashMap.get(str);
                if (!((HdTronRelationshipBean) pair.second).getNonHd()) {
                    arrayList.add(pair);
                }
            }
            return arrayList;
        }
        return null;
    }

    public static void lambda$queryAllHD$0(Set set, Map map, HdTronRelationshipBean hdTronRelationshipBean) {
        Pair pair;
        if (deleteDirtyData(set, hdTronRelationshipBean)) {
            return;
        }
        String relationshipHDAddress = hdTronRelationshipBean.getRelationshipHDAddress();
        WalletPath walletPath = hdTronRelationshipBean.getWalletPath();
        if (map.containsKey(relationshipHDAddress)) {
            Pair pair2 = (Pair) map.get(relationshipHDAddress);
            HdTronRelationshipBean hdTronRelationshipBean2 = (HdTronRelationshipBean) pair2.second;
            WalletPath walletPath2 = hdTronRelationshipBean2.getWalletPath();
            boolean z = false;
            if (walletPath.account <= walletPath2.account && walletPath.change <= walletPath2.change && walletPath.accountIndex <= walletPath2.accountIndex) {
                z = true;
            }
            int intValue = ((Integer) pair2.first).intValue() + 1;
            if (z) {
                pair = new Pair(Integer.valueOf(intValue), hdTronRelationshipBean);
            } else {
                pair = new Pair(Integer.valueOf(intValue), hdTronRelationshipBean2);
            }
        } else {
            pair = new Pair(1, hdTronRelationshipBean);
        }
        map.put(relationshipHDAddress, pair);
    }

    public static List<HdTronRelationshipBean> queryAllRelationBeans() {
        DaoUtils.getLightInstance().daoSession.getHdTronRelationshipBeanDao().detachAll();
        final Set<String> walletNames = WalletUtils.getWalletNames();
        return (List) Collection.-EL.stream(DaoUtils.getLightInstance().daoSession.queryBuilder(HdTronRelationshipBean.class).list()).filter(new Predicate() {
            public Predicate and(Predicate predicate) {
                return Objects.requireNonNull(predicate);
            }

            public Predicate negate() {
                return Predicate$-CC.$default$negate(this);
            }

            public Predicate or(Predicate predicate) {
                return Objects.requireNonNull(predicate);
            }

            @Override
            public final boolean test(Object obj) {
                return HDTronWalletController.lambda$queryAllRelationBeans$1(walletNames, (HdTronRelationshipBean) obj);
            }
        }).collect(Collectors.toList());
    }

    public static boolean lambda$queryAllRelationBeans$1(Set set, HdTronRelationshipBean hdTronRelationshipBean) {
        return !deleteDirtyData(set, hdTronRelationshipBean);
    }

    public static List<HdTronRelationshipBean> queryAllRelationFilterNonHdBeans() {
        DaoSession daoSession = DaoUtils.getLightInstance().daoSession;
        daoSession.getHdTronRelationshipBeanDao().detachAll();
        final Set<String> walletNames = WalletUtils.getWalletNames();
        return (List) Collection.-EL.stream(daoSession.queryBuilder(HdTronRelationshipBean.class).where(daoSession.queryBuilder(HdTronRelationshipBean.class).or(HdTronRelationshipBeanDao.Properties.NonHd.isNull(), HdTronRelationshipBeanDao.Properties.NonHd.notEq(true), new WhereCondition[0]), new WhereCondition[0]).list()).filter(new Predicate() {
            public Predicate and(Predicate predicate) {
                return Objects.requireNonNull(predicate);
            }

            public Predicate negate() {
                return Predicate$-CC.$default$negate(this);
            }

            public Predicate or(Predicate predicate) {
                return Objects.requireNonNull(predicate);
            }

            @Override
            public final boolean test(Object obj) {
                return HDTronWalletController.lambda$queryAllRelationFilterNonHdBeans$2(walletNames, (HdTronRelationshipBean) obj);
            }
        }).collect(Collectors.toList());
    }

    public static boolean lambda$queryAllRelationFilterNonHdBeans$2(Set set, HdTronRelationshipBean hdTronRelationshipBean) {
        return !deleteDirtyData(set, hdTronRelationshipBean);
    }

    private static boolean deleteDirtyData(Set<String> set, HdTronRelationshipBean hdTronRelationshipBean) {
        if (set == null || set.size() <= 0 || set.contains(hdTronRelationshipBean.getWalletName())) {
            return false;
        }
        deleteHdTronRelationshipBean(hdTronRelationshipBean.getWalletName());
        return true;
    }

    public static boolean updateDb(List<HdTronRelationshipBean> list) {
        if (list != null && list.size() != 0) {
            try {
                DaoUtils.getLightInstance().insertMultiObject(list, DappAuthorizedProjectBean.class);
                return true;
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
        return false;
    }

    public static HdTronRelationshipBean getHdWalletRelationShip(String str) {
        return (HdTronRelationshipBean) DaoUtils.getLightInstance().daoSession.queryBuilder(HdTronRelationshipBean.class).where(HdTronRelationshipBeanDao.Properties.WalletName.eq(str), new WhereCondition[0]).unique();
    }

    public static String queryRelationShipAddress(String str) {
        try {
            DaoUtils.getLightInstance().daoSession.getHdTronRelationshipBeanDao().detachAll();
            HdTronRelationshipBean hdTronRelationshipBean = (HdTronRelationshipBean) DaoUtils.getLightInstance().daoSession.queryBuilder(HdTronRelationshipBean.class).where(HdTronRelationshipBeanDao.Properties.WalletName.eq(str), new WhereCondition[0]).unique();
            return hdTronRelationshipBean != null ? hdTronRelationshipBean.getRelationshipHDAddress() : "";
        } catch (Exception e) {
            LogUtils.e(e);
            return "";
        }
    }

    public static List<String> queryWalletRelationship(String str) {
        final Set<String> walletNames = WalletUtils.getWalletNames();
        final ArrayList arrayList = new ArrayList();
        try {
            DaoUtils.getLightInstance().daoSession.getHdTronRelationshipBeanDao().detachAll();
            HdTronRelationshipBean hdTronRelationshipBean = (HdTronRelationshipBean) DaoUtils.getLightInstance().daoSession.queryBuilder(HdTronRelationshipBean.class).where(HdTronRelationshipBeanDao.Properties.WalletName.eq(str), new WhereCondition[0]).unique();
            if (hdTronRelationshipBean != null) {
                List list = DaoUtils.getLightInstance().daoSession.queryBuilder(HdTronRelationshipBean.class).where(HdTronRelationshipBeanDao.Properties.RelationshipHDAddress.eq(hdTronRelationshipBean.getRelationshipHDAddress()), new WhereCondition[0]).list();
                if (list != null && !list.isEmpty()) {
                    Collection.-EL.stream(list).forEach(new Consumer() {
                        @Override
                        public final void accept(Object obj) {
                            HDTronWalletController.lambda$queryWalletRelationship$3(walletNames, arrayList, (HdTronRelationshipBean) obj);
                        }

                        public Consumer andThen(Consumer consumer) {
                            return Objects.requireNonNull(consumer);
                        }
                    });
                }
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
        return arrayList;
    }

    public static void lambda$queryWalletRelationship$3(Set set, List list, HdTronRelationshipBean hdTronRelationshipBean) {
        if (set != null && !set.contains(hdTronRelationshipBean.getWalletName())) {
            deleteHdTronRelationshipBean(hdTronRelationshipBean.getWalletName());
        } else {
            list.add(hdTronRelationshipBean.getWalletName());
        }
    }

    public static List<Wallet> queryWalletRelationship2(String str, Order order) {
        List list;
        final ArrayList arrayList = new ArrayList();
        try {
            final Set<String> walletNames = WalletUtils.getWalletNames();
            DaoUtils.getLightInstance().daoSession.getHdTronRelationshipBeanDao().detachAll();
            HdTronRelationshipBean hdTronRelationshipBean = (HdTronRelationshipBean) DaoUtils.getLightInstance().daoSession.queryBuilder(HdTronRelationshipBean.class).where(HdTronRelationshipBeanDao.Properties.WalletName.eq(str), new WhereCondition[0]).unique();
            if (hdTronRelationshipBean != null) {
                QueryBuilder where = DaoUtils.getLightInstance().daoSession.queryBuilder(HdTronRelationshipBean.class).where(HdTronRelationshipBeanDao.Properties.RelationshipHDAddress.eq(hdTronRelationshipBean.getRelationshipHDAddress()), new WhereCondition[0]);
                if (Order.CreateTime == order) {
                    list = where.orderAsc(HdTronRelationshipBeanDao.Properties.CreateTime).list();
                } else {
                    list = where.list();
                    sortByPath(list);
                }
                if (list != null && !list.isEmpty()) {
                    Collection.-EL.stream(list).forEach(new Consumer() {
                        @Override
                        public final void accept(Object obj) {
                            HDTronWalletController.lambda$queryWalletRelationship2$4(walletNames, arrayList, (HdTronRelationshipBean) obj);
                        }

                        public Consumer andThen(Consumer consumer) {
                            return Objects.requireNonNull(consumer);
                        }
                    });
                }
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
        return arrayList;
    }

    public static void lambda$queryWalletRelationship2$4(Set set, List list, HdTronRelationshipBean hdTronRelationshipBean) {
        if (set != null) {
            try {
                if (!set.contains(hdTronRelationshipBean.getWalletName())) {
                    deleteHdTronRelationshipBean(hdTronRelationshipBean.getWalletName());
                    return;
                }
            } catch (Exception e) {
                LogUtils.e(e);
                return;
            }
        }
        list.add(WalletUtils.getWallet(hdTronRelationshipBean.getWalletName()));
    }

    public static synchronized boolean insertWallet(Wallet wallet, String str) {
        synchronized (HDTronWalletController.class) {
            if (wallet == null) {
                return false;
            }
            try {
                final HdTronRelationshipBean hdTronRelationshipBean = getHdTronRelationshipBean(wallet, str);
                return ((Boolean) DaoUtils.getLightInstance().daoSession.callInTx(new Callable() {
                    @Override
                    public final Object call() {
                        return DaoUtils.getLightInstance().insertObject(HdTronRelationshipBean.this);
                    }
                })).booleanValue();
            } catch (Exception e) {
                LogUtils.e(e);
                return false;
            }
        }
    }

    public static synchronized boolean insertWalletAndSyncNonHDFlag(Wallet wallet, String str) {
        synchronized (HDTronWalletController.class) {
            if (wallet == null) {
                return false;
            }
            try {
                final HdTronRelationshipBean hdTronRelationshipBean = getHdTronRelationshipBean(wallet, str);
                boolean booleanValue = ((Boolean) DaoUtils.getLightInstance().daoSession.callInTx(new Callable() {
                    @Override
                    public final Object call() {
                        return DaoUtils.getLightInstance().insertObject(HdTronRelationshipBean.this);
                    }
                })).booleanValue();
                List<HdTronRelationshipBean> list = DaoUtils.getLightInstance().daoSession.queryBuilder(HdTronRelationshipBean.class).where(HdTronRelationshipBeanDao.Properties.RelationshipHDAddress.eq(str), new WhereCondition[0]).list();
                if (list != null && list.size() > 1) {
                    boolean z = false;
                    for (HdTronRelationshipBean hdTronRelationshipBean2 : list) {
                        if (hdTronRelationshipBean2.getNonHd()) {
                            hdTronRelationshipBean2.setNonHd(false);
                            z = true;
                        }
                    }
                    if (z) {
                        DaoUtils.getLightInstance().insertMultiObject(list, HdTronRelationshipBean.class);
                    }
                }
                return booleanValue;
            } catch (Exception e) {
                LogUtils.e(e);
                return false;
            }
        }
    }

    public static synchronized boolean insertWallets(List<Wallet> list, final String str) {
        synchronized (HDTronWalletController.class) {
            if (list != null) {
                if (!list.isEmpty()) {
                    try {
                        final ArrayList arrayList = new ArrayList();
                        Collection.-EL.stream(list).forEach(new Consumer() {
                            @Override
                            public final void accept(Object obj) {
                                arrayList.add(HDTronWalletController.getHdTronRelationshipBean((Wallet) obj, str));
                            }

                            public Consumer andThen(Consumer consumer) {
                                return Objects.requireNonNull(consumer);
                            }
                        });
                        return DaoUtils.getLightInstance().insertMultObject(arrayList, false);
                    } catch (Exception e) {
                        LogUtils.e(e);
                        return false;
                    }
                }
            }
            return false;
        }
    }

    private static HdTronRelationshipBean getHdTronRelationshipBean(Wallet wallet, String str) {
        List list;
        boolean z = false;
        if (wallet.getCreateType() == 1 && ((list = DaoUtils.getLightInstance().daoSession.queryBuilder(HdTronRelationshipBean.class).where(HdTronRelationshipBeanDao.Properties.RelationshipHDAddress.eq(str), new WhereCondition[0]).orderAsc(HdTronRelationshipBeanDao.Properties.CreateTime).list()) == null || list.size() == 0 ? queryHdWalletCounts() > 0 : ((HdTronRelationshipBean) list.get(0)).getNonHd())) {
            z = true;
        }
        HdTronRelationshipBean hdTronRelationshipBean = new HdTronRelationshipBean();
        hdTronRelationshipBean.setCreateTime(wallet.getCreateTime());
        hdTronRelationshipBean.setCurrentWalletAddress(wallet.getAddress());
        hdTronRelationshipBean.setRelationshipHDAddress(str);
        hdTronRelationshipBean.setWalletName(wallet.getWalletName());
        hdTronRelationshipBean.setMnemonicPath(wallet.getMnemonicPathString());
        hdTronRelationshipBean.setNonHd(z);
        return hdTronRelationshipBean;
    }

    public static synchronized boolean insertWallets2(List<String> list, final String str) {
        synchronized (HDTronWalletController.class) {
            if (list != null) {
                if (!list.isEmpty()) {
                    try {
                        final ArrayList arrayList = new ArrayList();
                        final Set<String> walletNames = WalletUtils.getWalletNames();
                        Collection.-EL.stream(list).forEach(new Consumer() {
                            @Override
                            public final void accept(Object obj) {
                                HDTronWalletController.lambda$insertWallets2$9(walletNames, str, arrayList, (String) obj);
                            }

                            public Consumer andThen(Consumer consumer) {
                                return Objects.requireNonNull(consumer);
                            }
                        });
                        return DaoUtils.getLightInstance().insertMultObject(arrayList, false);
                    } catch (Exception e) {
                        LogUtils.e(e);
                        return false;
                    }
                }
            }
            return false;
        }
    }

    public static void lambda$insertWallets2$9(Set set, final String str, final List list, String str2) {
        List<Wallet> walletsForAddress;
        if (!set.contains(str2) || (walletsForAddress = WalletUtils.getWalletsForAddress(str2)) == null || walletsForAddress.isEmpty()) {
            return;
        }
        Collection.-EL.stream(walletsForAddress).forEach(new Consumer() {
            @Override
            public final void accept(Object obj) {
                HDTronWalletController.lambda$insertWallets2$8(str, list, (Wallet) obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
    }

    public static void lambda$insertWallets2$8(String str, List list, Wallet wallet) {
        if (WalletUtils.hasMnemonic(wallet.getWalletName())) {
            HdTronRelationshipBean hdTronRelationshipBean = new HdTronRelationshipBean();
            hdTronRelationshipBean.setCreateTime(wallet.getCreateTime());
            hdTronRelationshipBean.setCurrentWalletAddress(wallet.getAddress());
            hdTronRelationshipBean.setRelationshipHDAddress(str);
            hdTronRelationshipBean.setWalletName(wallet.getWalletName());
            hdTronRelationshipBean.setMnemonicPath(wallet.getMnemonicPathString());
            list.add(hdTronRelationshipBean);
        }
    }

    public static synchronized boolean deleteHdTronRelationshipBean(final String str) {
        boolean booleanValue;
        synchronized (HDTronWalletController.class) {
            try {
                booleanValue = ((Boolean) DaoUtils.getLightInstance().daoSession.callInTx(new Callable() {
                    @Override
                    public final Object call() {
                        return DaoUtils.getLightInstance().daoSession.queryBuilder(HdTronRelationshipBean.class).where(HdTronRelationshipBeanDao.Properties.WalletName.eq(str), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
                    }
                })).booleanValue();
            } catch (Exception e) {
                LogUtils.e(e);
                return false;
            }
        }
        return booleanValue;
    }

    public static synchronized boolean updateHdTronRelationshipBeanWalletName(final String str, final String str2) {
        boolean booleanValue;
        synchronized (HDTronWalletController.class) {
            try {
                booleanValue = ((Boolean) DaoUtils.getLightInstance().daoSession.callInTx(new Callable() {
                    @Override
                    public final Object call() {
                        return HDTronWalletController.lambda$updateHdTronRelationshipBeanWalletName$11(str, str2);
                    }
                })).booleanValue();
            } catch (Exception e) {
                LogUtils.e(e);
                return false;
            }
        }
        return booleanValue;
    }

    public static Boolean lambda$updateHdTronRelationshipBeanWalletName$11(String str, String str2) throws Exception {
        HdTronRelationshipBean hdTronRelationshipBean = (HdTronRelationshipBean) DaoUtils.getLightInstance().daoSession.queryBuilder(HdTronRelationshipBean.class).where(HdTronRelationshipBeanDao.Properties.WalletName.eq(str), new WhereCondition[0]).unique();
        hdTronRelationshipBean.setWalletName(str2);
        deleteHdTronRelationshipBean(str);
        DaoUtils.getLightInstance().insertObject(hdTronRelationshipBean);
        return true;
    }

    public static boolean hasHDWallet() {
        DaoSession daoSession = DaoUtils.getLightInstance().daoSession;
        return daoSession.queryBuilder(HdTronRelationshipBean.class).where(daoSession.queryBuilder(HdTronRelationshipBean.class).or(HdTronRelationshipBeanDao.Properties.NonHd.isNull(), HdTronRelationshipBeanDao.Properties.NonHd.notEq(true), new WhereCondition[0]), new WhereCondition[0]).count() > 0;
    }

    private static void sortByPath(List<HdTronRelationshipBean> list) {
        Collections.sort(list, new Comparator() {
            @Override
            public final int compare(Object obj, Object obj2) {
                return HDTronWalletController.lambda$sortByPath$12((HdTronRelationshipBean) obj, (HdTronRelationshipBean) obj2);
            }
        });
    }

    public static int lambda$sortByPath$12(HdTronRelationshipBean hdTronRelationshipBean, HdTronRelationshipBean hdTronRelationshipBean2) {
        WalletPath walletPath = hdTronRelationshipBean.getWalletPath();
        WalletPath walletPath2 = hdTronRelationshipBean2.getWalletPath();
        if (walletPath.getAccount() == walletPath2.getAccount()) {
            if (walletPath.getChange() == walletPath2.getChange()) {
                return compareNum(walletPath.getAccountIndex(), walletPath2.getAccountIndex());
            }
            return compareNum(walletPath.getChange(), walletPath2.getChange());
        }
        return compareNum(walletPath.getAccount(), walletPath2.getAccount());
    }
}
