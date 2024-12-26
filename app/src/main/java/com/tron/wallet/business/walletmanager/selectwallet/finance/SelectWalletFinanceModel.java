package com.tron.wallet.business.walletmanager.selectwallet.finance;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.wallet.business.finance.bean.FinanceAccountBalanceInput;
import com.tron.wallet.business.finance.bean.FinanceAccountBalanceOutput;
import com.tron.wallet.business.finance.bean.FinanceDataSummaryInput;
import com.tron.wallet.business.finance.bean.FinanceDataSummaryOutput;
import com.tron.wallet.business.walletmanager.selectwallet.bean.FinanceSelectWalletBean;
import com.tron.wallet.business.walletmanager.selectwallet.bean.SelectWalletBean;
import com.tron.wallet.business.walletmanager.selectwallet.bean.WalletGroupType;
import com.tron.wallet.business.walletmanager.selectwallet.bean.WalletSortType;
import com.tron.wallet.business.walletmanager.selectwallet.finance.SelectWalletFinanceContract;
import com.tron.wallet.business.walletmanager.selectwallet.sort.WalletSortHelper;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.GsonUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.Controller.HDTronWalletController;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.bean.HdTronRelationshipBean;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import j$.util.Collection;
import j$.util.Objects;
import j$.util.function.Predicate$-CC;
import j$.util.stream.Collectors;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.Predicate;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.tron.walletserver.Wallet;
public class SelectWalletFinanceModel implements SelectWalletFinanceContract.Model {
    private List<FinanceSelectWalletBean> cachedWalletBeans;
    private FinanceDataSummaryOutput.DataSummary dataSummary;
    private String projectId;
    private String tokenId;
    private String tokenSymbol;
    private boolean showShieldWallet = false;
    private List<String> addressList = new ArrayList();
    private boolean clearCacheFlag = false;
    private int searchedTextColor = -22232;
    private int mode = 0;
    private boolean isUsd = SpAPI.THIS.isUsdPrice();

    public void lambda$getSearchObservable$0(List list) throws Exception {
        this.clearCacheFlag = false;
        this.cachedWalletBeans = list;
    }

    public void lambda$getSearchObservable$1(List list) throws Exception {
        this.clearCacheFlag = false;
        this.cachedWalletBeans = list;
    }

    @Override
    public void clearCache() {
        this.clearCacheFlag = true;
    }

    @Override
    public FinanceDataSummaryOutput.DataSummary getFinanceDataSummary() {
        return this.dataSummary;
    }

    @Override
    public void setConfig(int i, String str, String str2, String str3) {
        this.mode = i;
        this.tokenId = str;
        this.tokenSymbol = str2;
        this.projectId = str3;
    }

    @Override
    public void setSearchedTextColor(int i) {
        this.searchedTextColor = i;
    }

    @Override
    public Observable<List<FinanceSelectWalletBean>> getSearchObservable(final String str, final WalletSortType walletSortType) {
        if (str == null) {
            str = "";
        }
        return Observable.just(str).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource lambda$getSearchObservable$2;
                lambda$getSearchObservable$2 = lambda$getSearchObservable$2((String) obj);
                return lambda$getSearchObservable$2;
            }
        }).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource lambda$getSearchObservable$4;
                lambda$getSearchObservable$4 = lambda$getSearchObservable$4(str, walletSortType, (List) obj);
                return lambda$getSearchObservable$4;
            }
        });
    }

    public ObservableSource lambda$getSearchObservable$2(String str) throws Exception {
        Observable<List<FinanceSelectWalletBean>> doOnNext;
        List<FinanceSelectWalletBean> list = this.cachedWalletBeans;
        if (list == null || this.clearCacheFlag) {
            Observable<List<Wallet>> allWalletObservable = getAllWalletObservable();
            int i = this.mode;
            if (i == 1 || i == 2) {
                doOnNext = getAccountBalanceWalletBean(allWalletObservable).doOnNext(new Consumer() {
                    @Override
                    public final void accept(Object obj) {
                        lambda$getSearchObservable$0((List) obj);
                    }
                });
            } else {
                doOnNext = getTotalAssetWalletBean(allWalletObservable).doOnNext(new Consumer() {
                    @Override
                    public final void accept(Object obj) {
                        lambda$getSearchObservable$1((List) obj);
                    }
                });
            }
        } else {
            doOnNext = Observable.just(list);
        }
        return findAllWallet(str, doOnNext);
    }

    public ObservableSource lambda$getSearchObservable$4(String str, WalletSortType walletSortType, List list) throws Exception {
        if (StringTronUtil.isEmpty(str)) {
            return sortWallets(list, walletSortType);
        }
        Collections.sort(list, new Comparator() {
            @Override
            public final int compare(Object obj, Object obj2) {
                return ((FinanceSelectWalletBean) obj2).getPriority().compareTo(((FinanceSelectWalletBean) obj).getPriority());
            }
        });
        return Observable.just(list);
    }

    private Observable<List<Wallet>> getAllWalletObservable() {
        return Observable.fromCallable(new Callable() {
            @Override
            public final Object call() {
                return SelectWalletFinanceModel.lambda$getAllWalletObservable$7();
            }
        });
    }

    public static List lambda$getAllWalletObservable$7() throws Exception {
        return (List) Collection.-EL.stream(WalletUtils.getPublicWalletNames()).map(new java.util.function.Function() {
            public java.util.function.Function andThen(java.util.function.Function function) {
                return Objects.requireNonNull(function);
            }

            @Override
            public final Object apply(Object obj) {
                Wallet wallet;
                wallet = WalletUtils.getWallet((String) obj);
                return wallet;
            }

            public java.util.function.Function compose(java.util.function.Function function) {
                return Objects.requireNonNull(function);
            }
        }).filter(new Predicate() {
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
                return SelectWalletFinanceModel.lambda$getAllWalletObservable$6((Wallet) obj);
            }
        }).collect(Collectors.toList());
    }

    public static boolean lambda$getAllWalletObservable$6(Wallet wallet) {
        return wallet.isWatchCold() || wallet.isLedgerHDWallet() || wallet.isSamsungWallet() || !wallet.isWatchOnly();
    }

    private Observable<List<FinanceSelectWalletBean>> getTotalAssetWalletBean(Observable<List<Wallet>> observable) {
        return observable.doOnNext(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$getTotalAssetWalletBean$8((List) obj);
            }
        }).zipWith(getTotalAssets(), new BiFunction() {
            @Override
            public final Object apply(Object obj, Object obj2) {
                List lambda$getTotalAssetWalletBean$11;
                lambda$getTotalAssetWalletBean$11 = lambda$getTotalAssetWalletBean$11((List) obj, (FinanceDataSummaryOutput) obj2);
                return lambda$getTotalAssetWalletBean$11;
            }
        });
    }

    public List lambda$getTotalAssetWalletBean$11(List list, final FinanceDataSummaryOutput financeDataSummaryOutput) throws Exception {
        final boolean z = (financeDataSummaryOutput == null || financeDataSummaryOutput.getData() == null || financeDataSummaryOutput.getData().getUsers() == null) ? false : true;
        this.dataSummary = z ? financeDataSummaryOutput.getData().getTotal() : null;
        final HashMap hashMap = new HashMap();
        List<HdTronRelationshipBean> queryAllRelationBeans = HDTronWalletController.queryAllRelationBeans();
        if (queryAllRelationBeans != null && queryAllRelationBeans.size() > 0) {
            Collection.-EL.stream(queryAllRelationBeans).forEach(new java.util.function.Consumer() {
                @Override
                public final void accept(Object obj) {
                    hashMap.put(r2.getCurrentWalletAddress(), (HdTronRelationshipBean) obj);
                }

                public java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
        }
        final ArrayList arrayList = new ArrayList();
        Collection.-EL.stream(list).forEach(new java.util.function.Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$getTotalAssetWalletBean$10(z, financeDataSummaryOutput, hashMap, arrayList, (Wallet) obj);
            }

            public java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        return arrayList;
    }

    public void lambda$getTotalAssetWalletBean$10(boolean z, FinanceDataSummaryOutput financeDataSummaryOutput, Map map, List list, Wallet wallet) {
        if (wallet != null) {
            FinanceDataSummaryOutput.DataByAccount dataByAccount = null;
            if (z) {
                Iterator<FinanceDataSummaryOutput.DataByAccount> it = financeDataSummaryOutput.getData().getUsers().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    FinanceDataSummaryOutput.DataByAccount next = it.next();
                    if (wallet.getAddress().equals(next.getAccountAddress())) {
                        dataByAccount = next;
                        break;
                    }
                }
                if (dataByAccount == null) {
                    return;
                }
            }
            FinanceSelectWalletBean financeSelectWalletBean = new FinanceSelectWalletBean();
            if (!this.showShieldWallet && !wallet.isShieldedWallet()) {
                financeSelectWalletBean.setWallet(wallet);
                financeSelectWalletBean.setGroupType(WalletGroupType.getGroupType(wallet));
                financeSelectWalletBean.setCreateTime(wallet.getCreateTime());
                if (financeSelectWalletBean.getGroupType() == WalletGroupType.HEAT) {
                    financeSelectWalletBean.setHdTronRelationshipBean((HdTronRelationshipBean) map.get(wallet.getAddress()));
                }
            } else if (this.showShieldWallet && wallet.isShieldedWallet()) {
                financeSelectWalletBean.setWallet(wallet);
                financeSelectWalletBean.setCreateTime(wallet.getCreateTime());
                financeSelectWalletBean.setGroupType(WalletGroupType.SHIELD);
            }
            financeSelectWalletBean.setUpdateResult(z);
            if (z) {
                financeSelectWalletBean.setAllAsset(this.isUsd ? dataByAccount.getTotalAssetsUsd() : dataByAccount.getTotalAssetsCny());
                financeSelectWalletBean.setFinanceAsset(this.isUsd ? dataByAccount.getFinancialAssetsUsd() : dataByAccount.getFinancialAssetsCny());
                financeSelectWalletBean.setBalance(StringTronUtil.parseDouble(dataByAccount.getTotalAssetsUsd()));
            }
            list.add(financeSelectWalletBean);
        }
    }

    private Observable<List<FinanceSelectWalletBean>> getAccountBalanceWalletBean(Observable<List<Wallet>> observable) {
        return observable.doOnNext(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$getAccountBalanceWalletBean$12((List) obj);
            }
        }).zipWith(getAccountBalance(), new BiFunction() {
            @Override
            public final Object apply(Object obj, Object obj2) {
                List lambda$getAccountBalanceWalletBean$15;
                lambda$getAccountBalanceWalletBean$15 = lambda$getAccountBalanceWalletBean$15((List) obj, (FinanceAccountBalanceOutput) obj2);
                return lambda$getAccountBalanceWalletBean$15;
            }
        });
    }

    public List lambda$getAccountBalanceWalletBean$15(List list, final FinanceAccountBalanceOutput financeAccountBalanceOutput) throws Exception {
        final boolean z = (financeAccountBalanceOutput == null || financeAccountBalanceOutput.getCode() == -1 || financeAccountBalanceOutput.getData() == null) ? false : true;
        final HashMap hashMap = new HashMap();
        List<HdTronRelationshipBean> queryAllRelationBeans = HDTronWalletController.queryAllRelationBeans();
        if (queryAllRelationBeans != null && queryAllRelationBeans.size() > 0) {
            Collection.-EL.stream(queryAllRelationBeans).forEach(new java.util.function.Consumer() {
                @Override
                public final void accept(Object obj) {
                    hashMap.put(r2.getCurrentWalletAddress(), (HdTronRelationshipBean) obj);
                }

                public java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
        }
        final ArrayList arrayList = new ArrayList();
        Collection.-EL.stream(list).forEach(new java.util.function.Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$getAccountBalanceWalletBean$14(z, financeAccountBalanceOutput, r4, hashMap, arrayList, (Wallet) obj);
            }

            public java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        return arrayList;
    }

    public void lambda$getAccountBalanceWalletBean$14(boolean z, FinanceAccountBalanceOutput financeAccountBalanceOutput, boolean z2, Map map, List list, Wallet wallet) {
        if (wallet != null) {
            FinanceAccountBalanceOutput.DataByAccount dataByAccount = null;
            if (z) {
                Iterator<FinanceAccountBalanceOutput.DataByAccount> it = financeAccountBalanceOutput.getData().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    FinanceAccountBalanceOutput.DataByAccount next = it.next();
                    if (wallet.getAddress().equals(next.getAccountAddress())) {
                        dataByAccount = next;
                        break;
                    }
                }
                if (!z2 && dataByAccount == null) {
                    return;
                }
                if (!z2 && dataByAccount != null && !BigDecimalUtils.MoreThan((Object) dataByAccount.getBalance(), (Object) 0)) {
                    return;
                }
            }
            FinanceSelectWalletBean financeSelectWalletBean = new FinanceSelectWalletBean();
            if (!this.showShieldWallet && !wallet.isShieldedWallet()) {
                financeSelectWalletBean.setWallet(wallet);
                financeSelectWalletBean.setGroupType(WalletGroupType.getGroupType(wallet));
                financeSelectWalletBean.setCreateTime(wallet.getCreateTime());
                if (financeSelectWalletBean.getGroupType() == WalletGroupType.HEAT) {
                    financeSelectWalletBean.setHdTronRelationshipBean((HdTronRelationshipBean) map.get(wallet.getAddress()));
                }
            } else if (this.showShieldWallet && wallet.isShieldedWallet()) {
                financeSelectWalletBean.setWallet(wallet);
                financeSelectWalletBean.setCreateTime(wallet.getCreateTime());
                financeSelectWalletBean.setGroupType(WalletGroupType.SHIELD);
            }
            financeSelectWalletBean.setUpdateResult(z);
            if (dataByAccount != null) {
                BigDecimal div_ = BigDecimalUtils.div_(dataByAccount.getBalance(), Double.valueOf(Math.pow(10.0d, dataByAccount.getPrecision())), 6);
                financeSelectWalletBean.setBalance(div_.doubleValue());
                financeSelectWalletBean.setFinanceBalance(div_.toPlainString());
                financeSelectWalletBean.setTokenName(dataByAccount.getTokenName());
                financeSelectWalletBean.setProjectId(this.projectId);
            } else {
                financeSelectWalletBean.setBalance(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                financeSelectWalletBean.setFinanceBalance("0");
                financeSelectWalletBean.setTokenName(this.tokenSymbol);
                financeSelectWalletBean.setProjectId(this.projectId);
            }
            list.add(financeSelectWalletBean);
        }
    }

    private Observable<List<FinanceSelectWalletBean>> findAllWallet(final String str, Observable<List<FinanceSelectWalletBean>> observable) {
        return observable.flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource lambda$findAllWallet$17;
                lambda$findAllWallet$17 = lambda$findAllWallet$17(str, (List) obj);
                return lambda$findAllWallet$17;
            }
        }).subscribeOn(Schedulers.io());
    }

    public ObservableSource lambda$findAllWallet$17(final String str, List list) throws Exception {
        final ArrayList arrayList = new ArrayList();
        Collection.-EL.stream(list).forEach(new java.util.function.Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$findAllWallet$16(str, arrayList, (FinanceSelectWalletBean) obj);
            }

            public java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        return Observable.just(arrayList);
    }

    public void lambda$findAllWallet$16(String str, List list, FinanceSelectWalletBean financeSelectWalletBean) {
        if (WalletSortHelper.matchKeyWord(str, financeSelectWalletBean, this.searchedTextColor)) {
            list.add(financeSelectWalletBean);
        }
    }

    private Observable<List<FinanceSelectWalletBean>> sortWallets(final List<FinanceSelectWalletBean> list, final WalletSortType walletSortType) {
        return Observable.just(list).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                return SelectWalletFinanceModel.lambda$sortWallets$22(list, walletSortType, (List) obj);
            }
        });
    }

    public static ObservableSource lambda$sortWallets$22(List list, final WalletSortType walletSortType, List list2) throws Exception {
        final ArrayList arrayList = new ArrayList();
        final HashMap hashMap = new HashMap();
        Collection.-EL.stream(list).forEach(new java.util.function.Consumer() {
            @Override
            public final void accept(Object obj) {
                SelectWalletFinanceModel.lambda$sortWallets$18(WalletSortType.this, hashMap, arrayList, (FinanceSelectWalletBean) obj);
            }

            public java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        WalletSortHelper.sort(arrayList, walletSortType);
        final ArrayList arrayList2 = new ArrayList();
        if (walletSortType == WalletSortType.SORT_BY_TYPE) {
            Collection.-EL.stream(arrayList).forEach(new java.util.function.Consumer() {
                @Override
                public final void accept(Object obj) {
                    SelectWalletFinanceModel.lambda$sortWallets$20(arrayList2, (SelectWalletBean) obj);
                }

                public java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
        } else {
            Collection.-EL.stream(arrayList).forEach(new java.util.function.Consumer() {
                @Override
                public final void accept(Object obj) {
                    arrayList2.add((FinanceSelectWalletBean) ((SelectWalletBean) obj));
                }

                public java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
        }
        return Observable.just(arrayList2);
    }

    public static void lambda$sortWallets$18(WalletSortType walletSortType, Map map, List list, FinanceSelectWalletBean financeSelectWalletBean) {
        if (walletSortType == WalletSortType.SORT_BY_TYPE && financeSelectWalletBean.getGroupType() == WalletGroupType.HEAT && financeSelectWalletBean.getHdTronRelationshipBean() != null && !financeSelectWalletBean.getHdTronRelationshipBean().getNonHd()) {
            String relationshipHDAddress = financeSelectWalletBean.getHdTronRelationshipBean().getRelationshipHDAddress();
            SelectWalletBean selectWalletBean = (SelectWalletBean) map.get(relationshipHDAddress);
            if (selectWalletBean == null) {
                selectWalletBean = new FinanceSelectWalletBean();
                selectWalletBean.setGroupType(WalletGroupType.HEAT);
                selectWalletBean.setHdGroup(true);
                selectWalletBean.setCreateTime(financeSelectWalletBean.getHdTronRelationshipBean().getCreateTime());
                map.put(relationshipHDAddress, selectWalletBean);
                list.add(selectWalletBean);
            } else if (financeSelectWalletBean.getHdTronRelationshipBean().getCreateTime() < selectWalletBean.getCreateTime()) {
                selectWalletBean.setCreateTime(financeSelectWalletBean.getHdTronRelationshipBean().getCreateTime());
            }
            selectWalletBean.setRelationWalletBean(financeSelectWalletBean);
            return;
        }
        list.add(financeSelectWalletBean);
    }

    public static void lambda$sortWallets$20(final List list, SelectWalletBean selectWalletBean) {
        if (selectWalletBean.getGroupType() == WalletGroupType.HEAT && selectWalletBean.isHdGroup()) {
            Collection.-EL.stream(selectWalletBean.getRelationWallets()).forEach(new java.util.function.Consumer() {
                @Override
                public final void accept(Object obj) {
                    list.add((FinanceSelectWalletBean) ((SelectWalletBean) obj));
                }

                public java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
        } else {
            list.add((FinanceSelectWalletBean) selectWalletBean);
        }
    }

    public void lambda$getTotalAssetWalletBean$8(List<Wallet> list) {
        this.addressList.clear();
        Collection.-EL.stream(list).forEach(new java.util.function.Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$getAddressInfo$23((Wallet) obj);
            }

            public java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
    }

    public void lambda$getAddressInfo$23(Wallet wallet) {
        this.addressList.add(wallet.getAddress());
    }

    private Observable<FinanceDataSummaryOutput> getTotalAssets() {
        return Observable.just(this.addressList).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource lambda$getTotalAssets$25;
                lambda$getTotalAssets$25 = lambda$getTotalAssets$25((List) obj);
                return lambda$getTotalAssets$25;
            }
        });
    }

    public ObservableSource lambda$getTotalAssets$25(List list) throws Exception {
        FinanceDataSummaryInput financeDataSummaryInput = new FinanceDataSummaryInput();
        financeDataSummaryInput.setWalletAddress(list);
        financeDataSummaryInput.setShowUsers(true);
        return getFinanceTotalAssetsList(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), GsonUtils.toGsonString(financeDataSummaryInput))).onErrorReturn(new Function() {
            @Override
            public final Object apply(Object obj) {
                return SelectWalletFinanceModel.lambda$getTotalAssets$24((Throwable) obj);
            }
        });
    }

    public static FinanceDataSummaryOutput lambda$getTotalAssets$24(Throwable th) throws Exception {
        FinanceDataSummaryOutput financeDataSummaryOutput = new FinanceDataSummaryOutput();
        financeDataSummaryOutput.setCode(-1);
        return financeDataSummaryOutput;
    }

    private Observable<FinanceAccountBalanceOutput> getAccountBalance() {
        return Observable.just(this.addressList).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource lambda$getAccountBalance$27;
                lambda$getAccountBalance$27 = lambda$getAccountBalance$27((List) obj);
                return lambda$getAccountBalance$27;
            }
        });
    }

    public ObservableSource lambda$getAccountBalance$27(List list) throws Exception {
        FinanceAccountBalanceInput financeAccountBalanceInput = new FinanceAccountBalanceInput();
        financeAccountBalanceInput.setWalletAddress(list);
        financeAccountBalanceInput.setTokenId(this.tokenId);
        financeAccountBalanceInput.setProjectId(this.projectId);
        return getFinanceAccountBalanceList(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), GsonUtils.toGsonString(financeAccountBalanceInput))).onErrorReturn(new Function() {
            @Override
            public final Object apply(Object obj) {
                return SelectWalletFinanceModel.lambda$getAccountBalance$26((Throwable) obj);
            }
        });
    }

    public static FinanceAccountBalanceOutput lambda$getAccountBalance$26(Throwable th) throws Exception {
        FinanceAccountBalanceOutput financeAccountBalanceOutput = new FinanceAccountBalanceOutput();
        financeAccountBalanceOutput.setCode(-1);
        return financeAccountBalanceOutput;
    }

    private Observable<FinanceDataSummaryOutput> getFinanceTotalAssetsList(RequestBody requestBody) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getTotalAssets(requestBody);
    }

    private Observable<FinanceAccountBalanceOutput> getFinanceAccountBalanceList(RequestBody requestBody) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getFinanceAccountBalance(requestBody);
    }
}
