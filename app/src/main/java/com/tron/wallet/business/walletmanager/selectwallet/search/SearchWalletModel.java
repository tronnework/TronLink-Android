package com.tron.wallet.business.walletmanager.selectwallet.search;

import android.text.TextUtils;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.wallet.business.walletmanager.selectwallet.bean.SelectWalletBean;
import com.tron.wallet.business.walletmanager.selectwallet.bean.WalletGroupType;
import com.tron.wallet.business.walletmanager.selectwallet.bean.WalletSortType;
import com.tron.wallet.business.walletmanager.selectwallet.controller.WalletController;
import com.tron.wallet.business.walletmanager.selectwallet.search.SearchWalletContract;
import com.tron.wallet.business.walletmanager.selectwallet.sort.WalletSortHelper;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.Controller.TRXAccountBalanceController;
import com.tron.wallet.db.bean.TRXAccountBalanceBean;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import j$.util.Collection;
import j$.util.Objects;
import j$.util.function.Predicate$-CC;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
public class SearchWalletModel implements SearchWalletContract.Model {
    private List<TRXAccountBalanceBean> accountBalance;
    private List<SelectWalletBean> cachedWalletBeans;
    private Object accountBalanceLock = new Object();
    private boolean clearCacheFlag = false;
    private int searchedTextColor = -22232;
    private WalletSortType walletSortType = WalletSortType.SORT_BY_TYPE;

    public void lambda$getSearchObservable$0(List list) throws Exception {
        this.clearCacheFlag = false;
        this.cachedWalletBeans = list;
    }

    @Override
    public void clearCache() {
        this.clearCacheFlag = true;
    }

    @Override
    public void setSearchedTextColor(int i) {
        this.searchedTextColor = i;
    }

    @Override
    public Observable<List<SelectWalletBean>> getSearchObservable(final String str, final WalletSortType walletSortType) {
        this.walletSortType = walletSortType;
        if (str == null) {
            str = "";
        }
        return Observable.just(str).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource lambda$getSearchObservable$1;
                lambda$getSearchObservable$1 = lambda$getSearchObservable$1(walletSortType, (String) obj);
                return lambda$getSearchObservable$1;
            }
        }).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                return SearchWalletModel.lambda$getSearchObservable$3(str, (List) obj);
            }
        });
    }

    public ObservableSource lambda$getSearchObservable$1(WalletSortType walletSortType, String str) throws Exception {
        Observable<List<SelectWalletBean>> doOnNext;
        List<SelectWalletBean> list = this.cachedWalletBeans;
        if (list == null || this.clearCacheFlag) {
            doOnNext = getWalletObservable(walletSortType).doOnNext(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    lambda$getSearchObservable$0((List) obj);
                }
            });
        } else {
            doOnNext = Observable.just(list);
        }
        return findAllWallet(str, doOnNext);
    }

    public static ObservableSource lambda$getSearchObservable$3(String str, List list) throws Exception {
        if (!StringTronUtil.isEmpty(str)) {
            Collections.sort(list, new Comparator() {
                @Override
                public final int compare(Object obj, Object obj2) {
                    return ((SelectWalletBean) obj2).getPriority().compareTo(((SelectWalletBean) obj).getPriority());
                }
            });
        }
        return Observable.just(list);
    }

    private Observable<List<SelectWalletBean>> getWalletObservable(final WalletSortType walletSortType) {
        return WalletController.getSortedWalletObservable(null, walletSortType, false, false).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                return SearchWalletModel.lambda$getWalletObservable$5(WalletSortType.this, (List) obj);
            }
        });
    }

    public static ObservableSource lambda$getWalletObservable$5(WalletSortType walletSortType, List list) throws Exception {
        if (walletSortType == WalletSortType.SORT_BY_TYPE) {
            final ArrayList arrayList = new ArrayList();
            Collection.-EL.stream(list).forEach(new java.util.function.Consumer() {
                @Override
                public final void accept(Object obj) {
                    SearchWalletModel.lambda$getWalletObservable$4(arrayList, (SelectWalletBean) obj);
                }

                public java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
            return Observable.just(arrayList);
        }
        return Observable.just(list);
    }

    public static void lambda$getWalletObservable$4(List list, SelectWalletBean selectWalletBean) {
        if (selectWalletBean.getGroupType() == WalletGroupType.HEAT && selectWalletBean.isHdGroup()) {
            list.addAll(selectWalletBean.getRelationWallets());
        } else {
            list.add(selectWalletBean);
        }
    }

    private Observable<List<SelectWalletBean>> findAllWallet(final String str, Observable<List<SelectWalletBean>> observable) {
        return observable.flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource lambda$findAllWallet$8;
                lambda$findAllWallet$8 = lambda$findAllWallet$8(str, (List) obj);
                return lambda$findAllWallet$8;
            }
        });
    }

    public ObservableSource lambda$findAllWallet$8(final String str, List list) throws Exception {
        final ArrayList arrayList = new ArrayList();
        Collection.-EL.stream(list).forEach(new java.util.function.Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$findAllWallet$7(str, arrayList, (SelectWalletBean) obj);
            }

            public java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        return Observable.just(arrayList);
    }

    public void lambda$findAllWallet$7(String str, List list, final SelectWalletBean selectWalletBean) {
        if (WalletSortHelper.matchKeyWord(str, selectWalletBean, this.searchedTextColor)) {
            selectWalletBean.setUpdating(false);
            selectWalletBean.setBalance(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
            list.add(selectWalletBean);
            queryTrxBalance(selectWalletBean.getWallet().getAddress(), new java.util.function.Consumer() {
                @Override
                public final void accept(Object obj) {
                    SearchWalletModel.lambda$findAllWallet$6(SelectWalletBean.this, (TRXAccountBalanceBean) obj);
                }

                public java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
        }
    }

    public static void lambda$findAllWallet$6(SelectWalletBean selectWalletBean, TRXAccountBalanceBean tRXAccountBalanceBean) {
        selectWalletBean.setBalance(tRXAccountBalanceBean.getTotalTrx());
        selectWalletBean.setAccountType(tRXAccountBalanceBean.getAccountType());
    }

    private void queryTrxBalance(final String str, java.util.function.Consumer<TRXAccountBalanceBean> consumer) {
        if (this.accountBalance == null) {
            synchronized (this.accountBalanceLock) {
                if (this.accountBalance == null) {
                    this.accountBalance = TRXAccountBalanceController.getInstance(AppContextUtil.getContext()).queryAll();
                }
            }
        }
        List<TRXAccountBalanceBean> list = this.accountBalance;
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
                    equals = TextUtils.equals(((TRXAccountBalanceBean) obj).getAddress(), str);
                    return equals;
                }
            }).findFirst().ifPresent(consumer);
        }
    }

    @Override
    public Observable<List<SelectWalletBean>> getAccountInfo() {
        return WalletController.getAccountInfoObservable(this.cachedWalletBeans, this.walletSortType);
    }
}
