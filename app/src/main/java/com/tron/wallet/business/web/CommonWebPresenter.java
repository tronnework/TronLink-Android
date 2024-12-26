package com.tron.wallet.business.web;

import com.tron.wallet.business.web.CommonWebContract;
import com.tron.wallet.common.config.Event;
import io.reactivex.functions.Consumer;
public class CommonWebPresenter extends CommonWebContract.Presenter {
    private String from;

    @Override
    public void setFrom(String str) {
        this.from = str;
    }

    @Override
    protected void onStart() {
        this.mRxManager.on(Event.EVENT_SEC_ASK_DONE, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$0(obj);
            }
        });
    }

    public void lambda$onStart$0(Object obj) throws Exception {
        if (WebConstant.FROM_SEC_ASK.equals(this.from) && this.mView != 0 && (obj instanceof String) && "close".equals((String) obj)) {
            ((CommonWebContract.View) this.mView).exit();
        }
    }
}
