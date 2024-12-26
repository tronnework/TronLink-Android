package com.tron.wallet.business.stakev2.stake.resource;

import com.tron.wallet.business.stakev2.stake.resource.UnDelegateContract;
import com.tron.wallet.common.config.Event;
import io.reactivex.functions.Consumer;
public class UnDelegatePresenter extends UnDelegateContract.Presenter {
    @Override
    protected void onStart() {
        this.mRxManager.on(Event.BroadcastSuccess, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$0(obj);
            }
        });
    }

    public void lambda$onStart$0(Object obj) throws Exception {
        if (this.mView != 0) {
            ((UnDelegateContract.View) this.mView).exit();
        }
    }
}
