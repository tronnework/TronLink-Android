package com.tron.wallet.business.walletmanager.selectwallet.controller;

import android.text.TextUtils;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.wallet.business.walletmanager.selectwallet.bean.SelectWalletBean;
import com.tron.wallet.business.walletmanager.selectwallet.bean.WalletGroupType;
import com.tron.wallet.business.walletmanager.selectwallet.bean.WalletSortType;
import com.tron.wallet.business.walletmanager.selectwallet.sort.WalletSortHelper;
import com.tron.wallet.common.bean.AccountBalanceOutput;
import com.tron.wallet.common.utils.GsonUtils;
import com.tron.wallet.db.Controller.HDTronWalletController;
import com.tron.wallet.db.Controller.TRXAccountBalanceController;
import com.tron.wallet.db.bean.HdTronRelationshipBean;
import com.tron.wallet.db.bean.TRXAccountBalanceBean;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import j$.util.Collection;
import j$.util.Objects;
import j$.util.function.Predicate$-CC;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.tron.walletserver.Wallet;
public class WalletController {
    public static Observable<List<SelectWalletBean>> getSortedWalletObservable(final Wallet wallet, final WalletSortType walletSortType, final boolean z, final boolean z2) {
        return Observable.create(new ObservableOnSubscribe<Set<String>>() {
            @Override
            public void subscribe(ObservableEmitter<Set<String>> observableEmitter) throws Exception {
                observableEmitter.onNext(WalletUtils.getPublicWalletNames());
                observableEmitter.onComplete();
            }
        }).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                return WalletController.lambda$getSortedWalletObservable$4(z, z2, wallet, (Set) obj);
            }
        }).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource sortWallets;
                sortWallets = WalletController.sortWallets((List) obj, WalletSortType.this);
                return sortWallets;
            }
        });
    }

    public static ObservableSource lambda$getSortedWalletObservable$4(boolean z, final boolean z2, final Wallet wallet, Set set) throws Exception {
        final ArrayList arrayList = new ArrayList();
        final List<TRXAccountBalanceBean> queryAll = TRXAccountBalanceController.getInstance(AppContextUtil.getContext()).queryAll();
        List arrayList2 = new ArrayList();
        if (z && !z2 && set.size() >= 6) {
            arrayList2 = RecentlyWalletController.getRecentlyWalletBeans(wallet, queryAll);
        }
        List list = arrayList2;
        final HashMap hashMap = new HashMap();
        List<HdTronRelationshipBean> queryAllRelationBeans = HDTronWalletController.queryAllRelationBeans();
        if (queryAllRelationBeans != null && queryAllRelationBeans.size() > 0) {
            Collection.-EL.stream(queryAllRelationBeans).forEach(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    hashMap.put(r2.getCurrentWalletAddress(), (HdTronRelationshipBean) obj);
                }

                public Consumer andThen(Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
        }
        Collection.-EL.stream(set).forEach(new Consumer() {
            @Override
            public final void accept(Object obj) {
                WalletController.lambda$getSortedWalletObservable$3(z2, wallet, queryAll, hashMap, arrayList, (String) obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        arrayList.addAll(0, list);
        return Observable.just(arrayList);
    }

    public static void lambda$getSortedWalletObservable$3(boolean z, Wallet wallet, List list, Map map, List list2, String str) {
        final Wallet wallet2 = WalletUtils.getWallet(str);
        if (wallet2 != null) {
            final SelectWalletBean selectWalletBean = new SelectWalletBean();
            boolean z2 = true;
            if (!z && !wallet2.isShieldedWallet()) {
                selectWalletBean.setWallet(wallet2);
                selectWalletBean.setSelected((wallet == null || !wallet2.getWalletName().equals(wallet.getWalletName())) ? false : false);
                if (list != null) {
                    Collection.-EL.stream(list).filter(new Predicate() {
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
                            equals = TextUtils.equals(((TRXAccountBalanceBean) obj).getAddress(), Wallet.this.getAddress());
                            return equals;
                        }
                    }).findFirst().ifPresent(new Consumer() {
                        @Override
                        public final void accept(Object obj) {
                            WalletController.lambda$getSortedWalletObservable$2(SelectWalletBean.this, (TRXAccountBalanceBean) obj);
                        }

                        public Consumer andThen(Consumer consumer) {
                            return Objects.requireNonNull(consumer);
                        }
                    });
                }
                selectWalletBean.setGroupType(WalletGroupType.getGroupType(wallet2));
                selectWalletBean.setCreateTime(wallet2.getCreateTime());
                if (selectWalletBean.getGroupType() == WalletGroupType.HEAT) {
                    selectWalletBean.setHdTronRelationshipBean((HdTronRelationshipBean) map.get(wallet2.getAddress()));
                }
                list2.add(selectWalletBean);
            } else if (z && wallet2.isShieldedWallet()) {
                selectWalletBean.setWallet(wallet2);
                selectWalletBean.setSelected((wallet == null || !wallet2.getWalletName().equals(wallet.getWalletName())) ? false : false);
                selectWalletBean.setCreateTime(wallet2.getCreateTime());
                selectWalletBean.setGroupType(WalletGroupType.SHIELD);
                list2.add(selectWalletBean);
            }
        }
    }

    public static void lambda$getSortedWalletObservable$2(SelectWalletBean selectWalletBean, TRXAccountBalanceBean tRXAccountBalanceBean) {
        selectWalletBean.setBalance(tRXAccountBalanceBean.getTotalTrx());
        selectWalletBean.setAccountType(tRXAccountBalanceBean.getAccountType());
    }

    public static void setAccountType(List<Map<String, Integer>> list, Wallet wallet) {
        int createType = wallet.getCreateType();
        int i = createType != -1 ? createType != 0 ? (createType == 1 || createType == 2 || createType == 3) ? 2 : 0 : 1 : 3;
        HashMap hashMap = new HashMap();
        hashMap.put(wallet.getAddress(), Integer.valueOf(i));
        list.add(hashMap);
    }

    private static List<Map<String, Integer>> buildAccountRequestList(List<SelectWalletBean> list) {
        final ArrayList arrayList = new ArrayList();
        Collection.-EL.stream(list).forEach(new Consumer() {
            @Override
            public final void accept(Object obj) {
                WalletController.lambda$buildAccountRequestList$7(arrayList, (SelectWalletBean) obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        return arrayList;
    }

    public static void lambda$buildAccountRequestList$7(final List list, SelectWalletBean selectWalletBean) {
        if (selectWalletBean.isHdGroup()) {
            List<SelectWalletBean> relationWallets = selectWalletBean.getRelationWallets();
            if (relationWallets != null) {
                Collection.-EL.stream(relationWallets).forEach(new Consumer() {
                    @Override
                    public final void accept(Object obj) {
                        WalletController.setAccountType(list, ((SelectWalletBean) obj).getWallet());
                    }

                    public Consumer andThen(Consumer consumer) {
                        return Objects.requireNonNull(consumer);
                    }
                });
                return;
            }
            return;
        }
        setAccountType(list, selectWalletBean.getWallet());
    }

    public static void setUpdateFail(List<SelectWalletBean> list) {
        Collection.-EL.stream(list).forEach(new Consumer() {
            @Override
            public final void accept(Object obj) {
                WalletController.lambda$setUpdateFail$9((SelectWalletBean) obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
    }

    public static void lambda$setUpdateFail$9(SelectWalletBean selectWalletBean) {
        if (selectWalletBean.isHdGroup()) {
            List<SelectWalletBean> relationWallets = selectWalletBean.getRelationWallets();
            if (relationWallets != null) {
                Collection.-EL.stream(relationWallets).forEach(new Consumer() {
                    @Override
                    public final void accept(Object obj) {
                        WalletController.lambda$setUpdateFail$8((SelectWalletBean) obj);
                    }

                    public Consumer andThen(Consumer consumer) {
                        return Objects.requireNonNull(consumer);
                    }
                });
                return;
            }
            return;
        }
        selectWalletBean.setUpdating(false);
        selectWalletBean.setUpdateResult(false);
    }

    public static void lambda$setUpdateFail$8(SelectWalletBean selectWalletBean) {
        selectWalletBean.setUpdating(false);
        selectWalletBean.setUpdateResult(false);
    }

    private static void setWalletAccountInfo(final SelectWalletBean selectWalletBean, List<AccountBalanceOutput.DataBean.BalanceListBean> list) {
        AccountBalanceOutput.DataBean.BalanceListBean balanceListBean = (AccountBalanceOutput.DataBean.BalanceListBean) Collection.-EL.stream(list).filter(new Predicate() {
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
                equals = SelectWalletBean.this.getWallet().getAddress().equals(((AccountBalanceOutput.DataBean.BalanceListBean) obj).getAddress());
                return equals;
            }
        }).findFirst().get();
        selectWalletBean.setAccountType(balanceListBean.getAccountType());
        if (selectWalletBean.getGroupType() == WalletGroupType.RECENTLY) {
            return;
        }
        if (balanceListBean != null) {
            selectWalletBean.setBalance(balanceListBean.getBalance());
            selectWalletBean.setUpdating(false);
            selectWalletBean.setUpdateResult(true);
            return;
        }
        selectWalletBean.setUpdating(false);
        selectWalletBean.setUpdateResult(false);
    }

    public static Observable<List<SelectWalletBean>> getAccountInfoObservable(final List<SelectWalletBean> list, final WalletSortType walletSortType) {
        RequestBody create = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), GsonUtils.toGsonString(buildAccountRequestList(list)));
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getAccountInfosList(WalletUtils.getSelectedPublicWallet().getAddress(), 1, create).doOnError(new io.reactivex.functions.Consumer() {
            @Override
            public final void accept(Object obj) {
                Throwable th = (Throwable) obj;
                WalletController.setUpdateFail(list);
            }
        }).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                return WalletController.lambda$getAccountInfoObservable$12(list, (AccountBalanceOutput) obj);
            }
        }).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource sortWallets;
                sortWallets = WalletController.sortWallets((List) obj, WalletSortType.this);
                return sortWallets;
            }
        });
    }

    public static ObservableSource lambda$getAccountInfoObservable$12(List list, AccountBalanceOutput accountBalanceOutput) throws Exception {
        if (accountBalanceOutput != null && accountBalanceOutput.getCode() == 0 && accountBalanceOutput.getData() != null && accountBalanceOutput.getData().getBalanceList() != null && accountBalanceOutput.getData().getBalanceList().size() != 0) {
            List<AccountBalanceOutput.DataBean.BalanceListBean> balanceList = accountBalanceOutput.getData().getBalanceList();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                SelectWalletBean selectWalletBean = (SelectWalletBean) it.next();
                if (selectWalletBean.isHdGroup()) {
                    for (SelectWalletBean selectWalletBean2 : selectWalletBean.getRelationWallets()) {
                        setWalletAccountInfo(selectWalletBean2, balanceList);
                    }
                } else {
                    setWalletAccountInfo(selectWalletBean, balanceList);
                }
            }
            TRXAccountBalanceController.getInstance(AppContextUtil.getContext()).insertMultObject(accountBalanceOutput.getData().getBalanceList());
        } else {
            setUpdateFail(list);
        }
        return Observable.just(list);
    }

    public static Observable<List<SelectWalletBean>> sortWallets(final List<SelectWalletBean> list, final WalletSortType walletSortType) {
        return Observable.just(list).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                return WalletController.lambda$sortWallets$15(list, walletSortType, (List) obj);
            }
        });
    }

    public static ObservableSource lambda$sortWallets$15(List list, final WalletSortType walletSortType, List list2) throws Exception {
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        final HashMap hashMap = new HashMap();
        Collection.-EL.stream(list).forEach(new Consumer() {
            @Override
            public final void accept(Object obj) {
                WalletController.lambda$sortWallets$14(arrayList, walletSortType, hashMap, arrayList2, (SelectWalletBean) obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        WalletSortHelper.sort(arrayList2, walletSortType);
        arrayList2.addAll(0, arrayList);
        return Observable.just(arrayList2);
    }

    public static void lambda$sortWallets$14(List list, WalletSortType walletSortType, Map map, List list2, SelectWalletBean selectWalletBean) {
        if (selectWalletBean.getGroupType() == WalletGroupType.RECENTLY) {
            list.add(selectWalletBean);
        } else if (walletSortType == WalletSortType.SORT_BY_TYPE && selectWalletBean.getGroupType() == WalletGroupType.HEAT && selectWalletBean.getHdTronRelationshipBean() != null && !selectWalletBean.isHdGroup() && !selectWalletBean.getHdTronRelationshipBean().getNonHd()) {
            String relationshipHDAddress = selectWalletBean.getHdTronRelationshipBean().getRelationshipHDAddress();
            SelectWalletBean selectWalletBean2 = (SelectWalletBean) map.get(relationshipHDAddress);
            if (selectWalletBean2 == null) {
                selectWalletBean2 = new SelectWalletBean();
                selectWalletBean2.setGroupType(WalletGroupType.HEAT);
                selectWalletBean2.setHdGroup(true);
                selectWalletBean2.setCreateTime(selectWalletBean.getHdTronRelationshipBean().getCreateTime());
                map.put(relationshipHDAddress, selectWalletBean2);
                list2.add(selectWalletBean2);
            } else if (selectWalletBean.getHdTronRelationshipBean().getCreateTime() < selectWalletBean2.getCreateTime()) {
                selectWalletBean2.setCreateTime(selectWalletBean.getHdTronRelationshipBean().getCreateTime());
            }
            selectWalletBean2.setRelationWalletBean(selectWalletBean);
        } else {
            list2.add(selectWalletBean);
        }
    }
}
