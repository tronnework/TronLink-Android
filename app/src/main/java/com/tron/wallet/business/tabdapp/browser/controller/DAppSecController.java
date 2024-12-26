package com.tron.wallet.business.tabdapp.browser.controller;

import com.tron.tron_base.frame.net.IRequestExtend;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.business.tabdapp.browser.bean.DAppSecBean;
import com.tron.wallet.business.tabdapp.browser.bean.DAppSecType;
import com.tron.wallet.business.tabdapp.browser.bean.ScamUrlBean;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.net.HttpApiExtend;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import java.util.HashMap;
import java.util.Map;
public class DAppSecController {
    private static DAppSecController instance;
    private Map<String, DAppSecType> secMap = new HashMap();
    private RxManager rxManager = new RxManager();

    private DAppSecController() {
    }

    public static DAppSecController getInstance() {
        if (instance == null) {
            synchronized (DAppSecController.class) {
                if (instance == null) {
                    instance = new DAppSecController();
                }
            }
        }
        return instance;
    }

    public DAppSecType getDAppSecType(String str) {
        DAppSecType dAppSecType = this.secMap.get(str);
        if (dAppSecType == null) {
            DAppSecType dAppSecType2 = DAppSecType.SEC_UNKNOWN;
            this.secMap.put(str, DAppSecType.SEC_UNKNOWN);
            asyncGetDAppSecType(str).subscribe(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    lambda$getDAppSecType$0((DAppSecBean) obj);
                }
            }, new Consumer() {
                @Override
                public final void accept(Object obj) {
                    LogUtils.e((Throwable) obj);
                }
            });
            return dAppSecType2;
        }
        return dAppSecType;
    }

    public void lambda$getDAppSecType$0(DAppSecBean dAppSecBean) throws Exception {
        this.rxManager.post(Event.EVENT_DAPP_CHECK_URL, dAppSecBean);
    }

    public Observable<DAppSecBean> asyncGetDAppSecType(final String str) {
        return ((HttpApiExtend) IRequestExtend.getAPI(HttpApiExtend.class)).checkScamUrl().map(new Function() {
            @Override
            public final Object apply(Object obj) {
                DAppSecBean lambda$asyncGetDAppSecType$2;
                lambda$asyncGetDAppSecType$2 = lambda$asyncGetDAppSecType$2(str, (ScamUrlBean) obj);
                return lambda$asyncGetDAppSecType$2;
            }
        }).compose(RxSchedulers.io_io());
    }

    public DAppSecBean lambda$asyncGetDAppSecType$2(String str, ScamUrlBean scamUrlBean) throws Exception {
        DAppSecBean dAppSecBean = new DAppSecBean();
        dAppSecBean.setHost(str);
        dAppSecBean.setSecType(scamUrlBean.isScam() ? DAppSecType.SEC_SCAM : DAppSecType.SEC_NORMAL);
        this.secMap.put(str, dAppSecBean.getSecType());
        return dAppSecBean;
    }
}
