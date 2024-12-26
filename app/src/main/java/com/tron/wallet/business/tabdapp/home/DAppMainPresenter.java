package com.tron.wallet.business.tabdapp.home;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.SparseIntArray;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.tabdapp.home.DAppMainContract;
import com.tron.wallet.business.tabdapp.home.bean.DAppBannerOutput;
import com.tron.wallet.business.tabdapp.home.bean.DAppListOutput;
import com.tron.wallet.business.tabdapp.home.bean.DappBannerBean;
import com.tron.wallet.business.tabdapp.home.bean.DappBean;
import com.tron.wallet.business.tabdapp.home.utils.DAppUrlUtils;
import com.tron.wallet.common.config.Event;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import j$.util.Collection;
import j$.util.Objects;
import j$.util.function.Predicate$-CC;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;
public class DAppMainPresenter extends DAppMainContract.Presenter {
    public static final CopyOnWriteArrayList<DappBean> dAppListCache = new CopyOnWriteArrayList<>();
    private final SparseIntArray pageIndices = new SparseIntArray();
    private int currentTabId = 0;

    private int parseClassIdFromTabId(int i) {
        return 0;
    }

    @Override
    void getBanners() {
        ((DAppMainContract.Model) this.mModel).requestBanners().flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                return DAppMainPresenter.lambda$getBanners$0((DAppBannerOutput) obj);
            }
        }).subscribe(new IObserver(new ICallback<List<DappBannerBean>>() {
            @Override
            public void onResponse(String str, List<DappBannerBean> list) {
                ((DAppMainContract.View) mView).onRequestBannersComplete(true, list);
            }

            @Override
            public void onFailure(String str, String str2) {
                ((DAppMainContract.View) mView).onRequestBannersComplete(false, null);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                addDisposable(disposable);
            }
        }, "requestBanners"));
    }

    public static ObservableSource lambda$getBanners$0(DAppBannerOutput dAppBannerOutput) throws Exception {
        if (dAppBannerOutput.message == null || dAppBannerOutput.data == null) {
            return Observable.just(new ArrayList());
        }
        return Observable.just(dAppBannerOutput.data);
    }

    @Override
    public void getDappList(final int i) {
        this.currentTabId = i;
        final int i2 = this.pageIndices.get(i);
        ((DAppMainContract.View) this.mView).onStartRequestDappList();
        ((DAppMainContract.Model) this.mModel).requestDappList(parseClassIdFromTabId(i), i2).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                return DAppMainPresenter.lambda$getDappList$1((DAppListOutput) obj);
            }
        }).subscribe(new IObserver(new ICallback<List<DappBean>>() {
            @Override
            public void onResponse(String str, List<DappBean> list) {
                ((DAppMainContract.View) mView).onRequestDappListComplete(true, i, list);
                if (list.isEmpty()) {
                    return;
                }
                pageIndices.put(i2, pageIndices.get(i2) + 1);
                DAppMainPresenter.dAppListCache.clear();
                DAppMainPresenter.dAppListCache.addAll(list);
            }

            @Override
            public void onFailure(String str, String str2) {
                ((DAppMainContract.View) mView).onRequestDappListComplete(false, currentTabId, null);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                addDisposable(disposable);
            }
        }, "requestBanners"));
    }

    public static ObservableSource lambda$getDappList$1(DAppListOutput dAppListOutput) throws Exception {
        if (dAppListOutput.message == null || dAppListOutput.data == null) {
            return Observable.just(new ArrayList());
        }
        return Observable.just(dAppListOutput.data);
    }

    @Override
    public void refresh() {
        this.pageIndices.put(this.currentTabId, 0);
        getRecommendDapp();
        getBanners();
        getDappList(this.currentTabId);
    }

    @Override
    public void getRecommendDapp() {
        if (IRequest.isNile() || IRequest.isShasta()) {
            ((DAppMainContract.View) this.mView).onRecommendDappComplete(true, new ArrayList());
        } else {
            ((DAppMainContract.Model) this.mModel).getMostVisitDApps().subscribe(new IObserver(new ICallback<List<DappBean>>() {
                @Override
                public void onResponse(String str, List<DappBean> list) {
                    ((DAppMainContract.View) mView).onRecommendDappComplete((list == null || list.isEmpty()) ? false : true, list);
                }

                @Override
                public void onFailure(String str, String str2) {
                    ((DAppMainContract.View) mView).onRecommendDappComplete(false, null);
                }

                @Override
                public void onSubscribe(Disposable disposable) {
                    addDisposable(disposable);
                }
            }, "getRecommend"));
        }
    }

    @Override
    public DappBean insertVisitedDapp(DappBean dappBean) {
        DappBean findInCache = findInCache(dappBean);
        if (findInCache != null) {
            ((DAppMainContract.Model) this.mModel).insertVisitedDApp(findInCache);
            return findInCache;
        }
        ((DAppMainContract.Model) this.mModel).insertVisitedDApp(dappBean);
        return dappBean;
    }

    @Override
    public void getRecommendDapp(int i) {
        if (i == 0) {
            getRecommendDapp();
        } else if (i != 1) {
        } else {
            getBookDapp();
        }
    }

    @Override
    public void getBookDapp() {
        ((DAppMainContract.Model) this.mModel).getBookDapp().subscribe(new IObserver(new ICallback<List<DappBean>>() {
            @Override
            public void onResponse(String str, List<DappBean> list) {
                boolean z = true;
                ((DAppMainContract.View) mView).onBookDappComplete((list == null || list.isEmpty()) ? false : false, list, list.size() > 10);
            }

            @Override
            public void onFailure(String str, String str2) {
                ((DAppMainContract.View) mView).onBookDappComplete(false, null, false);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                addDisposable(disposable);
            }
        }, "getRecommend"));
    }

    @Override
    protected void onStart() {
        this.mRxManager.on(Event.IP_LOCATION_CHANGED, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$2(obj);
            }
        });
    }

    public void lambda$onStart$2(Object obj) throws Exception {
        LogUtils.d("NetBroadcastReceiver", "IP_LOCATION_CHANGED:  ");
        refresh();
    }

    public void goIntent(String str) {
        try {
            ((DAppMainContract.View) this.mView).getIContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public DappBean findInCache(final DappBean dappBean) {
        if (dappBean == null) {
            return null;
        }
        final AtomicReference atomicReference = new AtomicReference(dappBean);
        Collection.-EL.stream(dAppListCache).filter(new Predicate() {
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
                equals = TextUtils.equals(DAppUrlUtils.getHost(DappBean.this.getHomeUrl()), DAppUrlUtils.getHost(((DappBean) obj).getHomeUrl()));
                return equals;
            }
        }).findFirst().ifPresent(new java.util.function.Consumer() {
            @Override
            public final void accept(Object obj) {
                atomicReference.set((DappBean) obj);
            }

            public java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        return (DappBean) atomicReference.get();
    }
}
