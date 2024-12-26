package com.tron.wallet.business.tabdapp.browser.base.contract;

import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.tabdapp.browser.base.contract.BookMarkManagerContract;
import com.tron.wallet.business.tabdapp.browser.bean.BrowserBookMarkBean;
import com.tron.wallet.business.tabdapp.browser.controller.BrowserBookMarkManager;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.Collections;
import java.util.List;
public class BookMarkManagerModel implements BookMarkManagerContract.Model {
    @Override
    public void saveSortedDatas(final List<BrowserBookMarkBean> list) {
        Observable.just(BrowserBookMarkManager.getInstance()).subscribeOn(Schedulers.io()).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                BookMarkManagerModel.lambda$saveSortedDatas$0(list, (BrowserBookMarkManager) obj);
            }
        });
    }

    public static void lambda$saveSortedDatas$0(List list, BrowserBookMarkManager browserBookMarkManager) throws Exception {
        for (int i = 0; i < list.size(); i++) {
            ((BrowserBookMarkBean) list.get(i)).setId(null);
        }
        Collections.reverse(list);
        browserBookMarkManager.insertMultiObject(list, true);
    }

    @Override
    public boolean remveBookMark(BrowserBookMarkBean browserBookMarkBean, int i) {
        return BrowserBookMarkManager.getInstance().removeSingle(browserBookMarkBean.getUrl());
    }

    @Override
    public Observable<List<BrowserBookMarkBean>> queryBookMark() {
        final BrowserBookMarkManager browserBookMarkManager = BrowserBookMarkManager.getInstance();
        return Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                BookMarkManagerModel.lambda$queryBookMark$1(BrowserBookMarkManager.this, observableEmitter);
            }
        }).compose(RxSchedulers.io_main());
    }

    public static void lambda$queryBookMark$1(BrowserBookMarkManager browserBookMarkManager, ObservableEmitter observableEmitter) throws Exception {
        observableEmitter.onNext(browserBookMarkManager.queryBookMarks());
        observableEmitter.onComplete();
    }
}
