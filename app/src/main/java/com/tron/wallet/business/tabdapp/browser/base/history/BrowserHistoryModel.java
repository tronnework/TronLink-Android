package com.tron.wallet.business.tabdapp.browser.base.history;

import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.tabdapp.browser.base.history.BrowserHistoryContract;
import com.tron.wallet.business.tabdapp.browser.bean.BrowserHistoryBean;
import com.tron.wallet.business.tabdapp.browser.controller.BrowserHistoryManager;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import java.util.List;
public class BrowserHistoryModel implements BrowserHistoryContract.Model {
    BrowserHistoryManager browserHistoryManager = BrowserHistoryManager.getInstance();

    @Override
    public boolean remveHistory(BrowserHistoryBean browserHistoryBean, int i) {
        return this.browserHistoryManager.removeSingle(browserHistoryBean);
    }

    @Override
    public List<BrowserHistoryBean> queryHistory() {
        return this.browserHistoryManager.queryHistory();
    }

    @Override
    public Observable<List<BrowserHistoryBean>> queryHistory(final int i, final int i2) {
        return Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                lambda$queryHistory$0(i, i2, observableEmitter);
            }
        }).compose(RxSchedulers.io_main());
    }

    public void lambda$queryHistory$0(int i, int i2, ObservableEmitter observableEmitter) throws Exception {
        observableEmitter.onNext(this.browserHistoryManager.queryHistory(i, i2));
        observableEmitter.onComplete();
    }

    @Override
    public void clearAllHistory() {
        this.browserHistoryManager.clearAllHistory();
    }
}
