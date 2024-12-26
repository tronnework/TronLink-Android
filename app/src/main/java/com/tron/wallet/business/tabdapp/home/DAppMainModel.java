package com.tron.wallet.business.tabdapp.home;

import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.tabdapp.browser.bean.BrowserBookMarkBean;
import com.tron.wallet.business.tabdapp.browser.bean.MostVisitDAppBean;
import com.tron.wallet.business.tabdapp.browser.controller.BrowserBookMarkController;
import com.tron.wallet.business.tabdapp.browser.controller.DAppVisitHistoryController;
import com.tron.wallet.business.tabdapp.browser.controller.MostVisitDAppController;
import com.tron.wallet.business.tabdapp.home.DAppMainContract;
import com.tron.wallet.business.tabdapp.home.bean.DAppBannerOutput;
import com.tron.wallet.business.tabdapp.home.bean.DAppListOutput;
import com.tron.wallet.business.tabdapp.home.bean.DappBean;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.ledger.bleclient.RxSchedulers2;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.functions.Function;
import j$.util.Collection;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import org.tron.walletserver.Wallet;
public class DAppMainModel implements DAppMainContract.Model {
    public static final int RECOMMEND_DAPP_COUNT = 10;
    final MostVisitDAppController mostVisitDAppController;

    public static void lambda$insertVisitedDApp$3(Boolean bool) throws Exception {
    }

    public DAppMainModel() {
        MostVisitDAppController mostVisitDAppController = MostVisitDAppController.getInstance();
        this.mostVisitDAppController = mostVisitDAppController;
        mostVisitDAppController.initCapacity();
    }

    @Override
    public Observable<DAppBannerOutput> requestBanners() {
        Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
        String address = selectedPublicWallet != null ? selectedPublicWallet.getAddress() : null;
        if (address == null) {
            return Observable.just(new DAppBannerOutput());
        }
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).requestDAppBanner(address).compose(RxSchedulers2.io_main());
    }

    @Override
    public Observable<DAppListOutput> requestDappList(int i, int i2) {
        Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
        String address = selectedPublicWallet != null ? selectedPublicWallet.getAddress() : null;
        if (address == null) {
            return Observable.just(new DAppListOutput());
        }
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).requestDAppList(i, address).compose(RxSchedulers2.io_main());
    }

    @Override
    public Observable<List<DappBean>> getMostVisitDApps() {
        MostVisitDAppController mostVisitDAppController = this.mostVisitDAppController;
        if (mostVisitDAppController == null) {
            return Observable.just(new ArrayList());
        }
        return mostVisitDAppController.getMostVisitDApps(10).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource fromCallable;
                fromCallable = Observable.fromCallable(new Callable() {
                    @Override
                    public final Object call() {
                        return DAppMainModel.lambda$getMostVisitDApps$1(r1);
                    }
                });
                return fromCallable;
            }
        }).compose(RxSchedulers2.io_main());
    }

    public static List lambda$getMostVisitDApps$1(List list) throws Exception {
        final ArrayList arrayList = new ArrayList();
        Collection.-EL.stream(list).forEach(new Consumer() {
            @Override
            public final void accept(Object obj) {
                arrayList.add(DAppMainModel.parseBean((MostVisitDAppBean) obj));
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        return arrayList;
    }

    @Override
    public void insertVisitedDApp(DappBean dappBean) {
        DAppVisitHistoryController.getInstance().insertVisitHistory(dappBean.getHomeUrl(), dappBean.getName(), dappBean.getImageUrl()).compose(RxSchedulers.io_main()).subscribe(new io.reactivex.functions.Consumer() {
            @Override
            public final void accept(Object obj) {
                DAppMainModel.lambda$insertVisitedDApp$3((Boolean) obj);
            }
        }, new io.reactivex.functions.Consumer() {
            @Override
            public final void accept(Object obj) {
                LogUtils.e(((Throwable) obj).toString());
            }
        });
    }

    @Override
    public ObservableSource<List<DappBean>> getBookDapp() {
        return Observable.unsafeCreate(new ObservableSource<List<DappBean>>() {
            @Override
            public void subscribe(Observer<? super List<DappBean>> observer) {
                List<BrowserBookMarkBean> browserBookMarkFirstSixList = BrowserBookMarkController.getInstance().getBrowserBookMarkFirstSixList();
                ArrayList arrayList = new ArrayList();
                for (BrowserBookMarkBean browserBookMarkBean : browserBookMarkFirstSixList) {
                    DappBean dappBean = new DappBean();
                    dappBean.setName(browserBookMarkBean.getTitle());
                    dappBean.setHomeUrl(browserBookMarkBean.getUrl());
                    dappBean.setImageUrl(browserBookMarkBean.getIconUrl());
                    dappBean.setAnonymous(browserBookMarkBean.isAnonymous());
                    arrayList.add(dappBean);
                }
                observer.onNext(arrayList);
                observer.onComplete();
            }
        }).compose(RxSchedulers.io_main());
    }

    public static DappBean parseBean(MostVisitDAppBean mostVisitDAppBean) {
        DappBean dappBean = new DappBean();
        dappBean.setHomeUrl(mostVisitDAppBean.getUrl());
        dappBean.setImageUrl(mostVisitDAppBean.getIcon());
        dappBean.setName(mostVisitDAppBean.getTitle());
        dappBean.setAnonymous(mostVisitDAppBean.getAnonymous());
        return dappBean;
    }
}
