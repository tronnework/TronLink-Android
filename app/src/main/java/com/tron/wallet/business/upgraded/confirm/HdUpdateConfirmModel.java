package com.tron.wallet.business.upgraded.confirm;

import com.alibaba.fastjson.JSON;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.upgraded.bean.UpgradeHdBean;
import com.tron.wallet.business.upgraded.bean.UpgradeHdListBean;
import com.tron.wallet.business.upgraded.confirm.HdUpdateConfirmContract;
import com.tron.wallet.common.bean.AccountBalanceOutput;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.Controller.HDTronWalletController;
import com.tron.wallet.db.bean.HdTronRelationshipBean;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
import j$.util.Collection;
import j$.util.Map;
import j$.util.Objects;
import j$.util.function.Predicate$-CC;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.tron.walletserver.WalletPath;
public class HdUpdateConfirmModel implements HdUpdateConfirmContract.Model {

    public enum SortType {
        Assets,
        Time,
        ChildNum
    }

    private static void sortByTime(List<UpgradeHdBean> list) {
        Collections.sort(list, new Comparator<UpgradeHdBean>() {
            @Override
            public int compare(UpgradeHdBean upgradeHdBean, UpgradeHdBean upgradeHdBean2) {
                return upgradeHdBean.getZeroAddressCreateTime() - upgradeHdBean2.getZeroAddressCreateTime() < 0 ? -1 : 1;
            }
        });
    }

    private static void sortByChildNum(List<UpgradeHdBean> list) {
        Collections.sort(list, new Comparator<UpgradeHdBean>() {
            @Override
            public int compare(UpgradeHdBean upgradeHdBean, UpgradeHdBean upgradeHdBean2) {
                return BigDecimalUtils.Equal(upgradeHdBean.getUpgradeHdLists(), upgradeHdBean2.getUpgradeHdLists()) ? upgradeHdBean.getZeroAddressCreateTime() - upgradeHdBean2.getZeroAddressCreateTime() < 0 ? -1 : 1 : BigDecimalUtils.MoreThan(Integer.valueOf(upgradeHdBean.getUpgradeHdLists().size()), Integer.valueOf(upgradeHdBean2.getUpgradeHdLists().size())) ? -1 : 1;
            }
        });
    }

    @Override
    public boolean refreshDb(final UpgradeHdBean upgradeHdBean) {
        try {
            List<HdTronRelationshipBean> queryAllRelationBeans = HDTronWalletController.queryAllRelationBeans();
            Collection.-EL.stream(queryAllRelationBeans).forEach(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    HdUpdateConfirmModel.lambda$refreshDb$0(UpgradeHdBean.this, (HdTronRelationshipBean) obj);
                }

                public Consumer andThen(Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
            return HDTronWalletController.updateDb(queryAllRelationBeans);
        } catch (Exception e) {
            LogUtils.e(e);
            return false;
        }
    }

    public static void lambda$refreshDb$0(UpgradeHdBean upgradeHdBean, HdTronRelationshipBean hdTronRelationshipBean) {
        if (hdTronRelationshipBean.getRelationshipHDAddress().equals(upgradeHdBean.getRelationshipZeroAddress())) {
            hdTronRelationshipBean.setNonHd(false);
        } else {
            hdTronRelationshipBean.setNonHd(true);
        }
    }

    @Override
    public Map<String, AccountBalanceOutput.DataBean.BalanceListBean> toMap(List<AccountBalanceOutput.DataBean.BalanceListBean> list) {
        final HashMap hashMap = new HashMap();
        if (list != null && list.size() != 0) {
            Collection.-EL.stream(list).forEach(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    hashMap.put(r2.getAddress(), (AccountBalanceOutput.DataBean.BalanceListBean) obj);
                }

                public Consumer andThen(Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
        }
        return hashMap;
    }

    @Override
    public Observable<AccountBalanceOutput> getBalances(List<HdTronRelationshipBean> list) {
        HashMap hashMap = new HashMap();
        for (HdTronRelationshipBean hdTronRelationshipBean : list) {
            hashMap.put(hdTronRelationshipBean.getCurrentWalletAddress(), 5);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(hashMap);
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getAccountInfosList(list.get(0).getCurrentWalletAddress(), 2, RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSON.toJSONString(arrayList))).compose(RxSchedulers.io_main());
    }

    @Override
    public List<HdTronRelationshipBean> getHdAddressList() {
        try {
            return HDTronWalletController.queryAllRelationBeans();
        } catch (Exception e) {
            LogUtils.e(e);
            return null;
        }
    }

    public List<UpgradeHdBean> sortWallet(List<HdTronRelationshipBean> list, Map<String, AccountBalanceOutput.DataBean.BalanceListBean> map, SortType sortType) {
        return sortWallet(list, map, sortType, false);
    }

    @Override
    public List<UpgradeHdBean> sortWallet(List<HdTronRelationshipBean> list, final Map<String, AccountBalanceOutput.DataBean.BalanceListBean> map, SortType sortType, boolean z) {
        String str;
        final HashMap hashMap = new HashMap();
        final ArrayList arrayList = new ArrayList();
        final AtomicReference atomicReference = new AtomicReference();
        if (list != null) {
            try {
                if (list.size() > 0) {
                    Collection.-EL.stream(list).forEach(new Consumer() {
                        @Override
                        public final void accept(Object obj) {
                            HdUpdateConfirmModel.lambda$sortWallet$2(atomicReference, hashMap, map, (HdTronRelationshipBean) obj);
                        }

                        public Consumer andThen(Consumer consumer) {
                            return Objects.requireNonNull(consumer);
                        }
                    });
                    Map.-EL.forEach(hashMap, new BiConsumer() {
                        @Override
                        public final void accept(Object obj, Object obj2) {
                            lambda$sortWallet$3(arrayList, (String) obj, (UpgradeHdBean) obj2);
                        }

                        public BiConsumer andThen(BiConsumer biConsumer) {
                            return Objects.requireNonNull(biConsumer);
                        }
                    });
                    if (arrayList.size() != 0) {
                        int i = fun6.$SwitchMap$com$tron$wallet$business$upgraded$confirm$HdUpdateConfirmModel$SortType[sortType.ordinal()];
                        if (i == 1) {
                            sortByValue(arrayList);
                        } else if (i == 2) {
                            sortByTime(arrayList);
                            if (z) {
                                final String str2 = atomicReference.get() == null ? "" : (String) atomicReference.get();
                                if (!StringTronUtil.isEmpty(str2)) {
                                    Collection.-EL.removeIf(arrayList, new Predicate() {
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
                                            boolean equals;
                                            equals = ((UpgradeHdBean) obj).getRelationshipZeroAddress().equals(str2);
                                            return equals;
                                        }
                                    });
                                    arrayList.add(0, (UpgradeHdBean) hashMap.get(str2));
                                }
                            }
                        } else if (i == 3) {
                            sortByChildNum(arrayList);
                        }
                    }
                }
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
        if (arrayList.size() != 0) {
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                if (!z) {
                    str = "HD-" + (i2 + 1);
                } else if (i2 == 0) {
                    str = "HD";
                } else {
                    str = "HD-" + i2;
                }
                arrayList.get(i2).setTitle(str);
            }
            if (z && arrayList.size() > 1) {
                arrayList.get(1).setSelected(true);
            } else {
                arrayList.get(0).setSelected(true);
            }
        }
        return arrayList;
    }

    public static void lambda$sortWallet$2(AtomicReference atomicReference, java.util.Map map, java.util.Map map2, HdTronRelationshipBean hdTronRelationshipBean) {
        if (!hdTronRelationshipBean.getNonHd()) {
            atomicReference.set(hdTronRelationshipBean.getRelationshipHDAddress());
        }
        String relationshipHDAddress = hdTronRelationshipBean.getRelationshipHDAddress();
        UpgradeHdBean upgradeHdBean = (UpgradeHdBean) map.get(relationshipHDAddress);
        if (upgradeHdBean == null) {
            upgradeHdBean = new UpgradeHdBean();
        }
        long zeroAddressCreateTime = upgradeHdBean.getZeroAddressCreateTime();
        long txTotalNum = upgradeHdBean.getTxTotalNum();
        BigDecimal balance = upgradeHdBean.getBalance();
        List<UpgradeHdListBean> upgradeHdLists = upgradeHdBean.getUpgradeHdLists();
        if (upgradeHdLists == null) {
            upgradeHdLists = new ArrayList<>();
        }
        if (zeroAddressCreateTime == 0 || zeroAddressCreateTime > hdTronRelationshipBean.getCreateTime()) {
            upgradeHdBean.setZeroAddressCreateTime(hdTronRelationshipBean.getCreateTime());
        }
        UpgradeHdListBean upgradeHdListBean = new UpgradeHdListBean();
        if (map2 != null && map2.get(hdTronRelationshipBean.getCurrentWalletAddress()) != null) {
            AccountBalanceOutput.DataBean.BalanceListBean balanceListBean = (AccountBalanceOutput.DataBean.BalanceListBean) map2.get(hdTronRelationshipBean.getCurrentWalletAddress());
            upgradeHdListBean.setBalance(balanceListBean.getBalance());
            upgradeHdListBean.setTxNum(balanceListBean.getTxNum());
            upgradeHdBean.setTxTotalNum(txTotalNum + balanceListBean.getTxNum());
            if (balance != null) {
                upgradeHdBean.setBalance(BigDecimalUtils.sum_(balance, Double.valueOf(balanceListBean.getBalance())));
            } else if (balanceListBean.getBalance() != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                upgradeHdBean.setBalance(BigDecimalUtils.toBigDecimal(Double.valueOf(balanceListBean.getBalance())));
            }
        }
        upgradeHdListBean.setWalletName(hdTronRelationshipBean.getWalletName());
        upgradeHdListBean.setMnemonicPath(hdTronRelationshipBean.getMnemonicPath());
        upgradeHdListBean.setWalletAddress(hdTronRelationshipBean.getCurrentWalletAddress());
        upgradeHdLists.add(upgradeHdListBean);
        upgradeHdBean.setUpgradeHdLists(upgradeHdLists);
        upgradeHdBean.setRelationshipZeroAddress(relationshipHDAddress);
        upgradeHdBean.setNonHd(hdTronRelationshipBean.getNonHd());
        map.put(relationshipHDAddress, upgradeHdBean);
    }

    public void lambda$sortWallet$3(List list, String str, UpgradeHdBean upgradeHdBean) {
        sortByType(upgradeHdBean.getUpgradeHdLists());
        list.add(upgradeHdBean);
    }

    public static class fun6 {
        static final int[] $SwitchMap$com$tron$wallet$business$upgraded$confirm$HdUpdateConfirmModel$SortType;

        static {
            int[] iArr = new int[SortType.values().length];
            $SwitchMap$com$tron$wallet$business$upgraded$confirm$HdUpdateConfirmModel$SortType = iArr;
            try {
                iArr[SortType.Assets.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$upgraded$confirm$HdUpdateConfirmModel$SortType[SortType.Time.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$upgraded$confirm$HdUpdateConfirmModel$SortType[SortType.ChildNum.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private void sortByType(List<UpgradeHdListBean> list) {
        Collections.sort(list, new Comparator<UpgradeHdListBean>() {
            @Override
            public int compare(UpgradeHdListBean upgradeHdListBean, UpgradeHdListBean upgradeHdListBean2) {
                int i;
                int i2;
                WalletPath buildWalletPath = WalletPath.buildWalletPath(upgradeHdListBean.getMnemonicPath());
                WalletPath buildWalletPath2 = WalletPath.buildWalletPath(upgradeHdListBean2.getMnemonicPath());
                if (buildWalletPath.account != buildWalletPath2.account) {
                    i = buildWalletPath.account;
                    i2 = buildWalletPath2.account;
                } else if (buildWalletPath.change != buildWalletPath2.change) {
                    i = buildWalletPath.change;
                    i2 = buildWalletPath2.change;
                } else {
                    i = buildWalletPath.accountIndex;
                    i2 = buildWalletPath2.accountIndex;
                }
                return i - i2;
            }
        });
    }

    private void sortByValue(List<UpgradeHdBean> list) {
        Collections.sort(list, new Comparator<UpgradeHdBean>() {
            @Override
            public int compare(UpgradeHdBean upgradeHdBean, UpgradeHdBean upgradeHdBean2) {
                return BigDecimalUtils.Equal(upgradeHdBean.getBalance(), upgradeHdBean2.getBalance()) ? upgradeHdBean.getZeroAddressCreateTime() - upgradeHdBean2.getZeroAddressCreateTime() < 0 ? -1 : 1 : BigDecimalUtils.MoreThan(upgradeHdBean.getBalance(), upgradeHdBean2.getBalance()) ? -1 : 1;
            }
        });
    }

    private void sortByTxNum(List<UpgradeHdBean> list) {
        Collections.sort(list, new Comparator<UpgradeHdBean>() {
            @Override
            public int compare(UpgradeHdBean upgradeHdBean, UpgradeHdBean upgradeHdBean2) {
                return BigDecimalUtils.Equal(Long.valueOf(upgradeHdBean.getTxTotalNum()), Long.valueOf(upgradeHdBean2.getTxTotalNum())) ? upgradeHdBean.getZeroAddressCreateTime() - upgradeHdBean2.getZeroAddressCreateTime() < 0 ? -1 : 1 : BigDecimalUtils.MoreThan(Long.valueOf(upgradeHdBean.getTxTotalNum()), Long.valueOf(upgradeHdBean2.getTxTotalNum())) ? -1 : 1;
            }
        });
    }
}
