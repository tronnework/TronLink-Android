package com.tron.wallet.business.tabmy.about;

import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.wallet.business.tabmy.about.AboutContract;
import com.tron.wallet.business.tabmy.bean.CommunityOutput;
import com.tron.wallet.db.SpAPI;
import io.reactivex.disposables.Disposable;
public class AboutPresenter extends AboutContract.Presenter {
    @Override
    protected void onStart() {
    }

    @Override
    public void getCommunityContent() {
        ((AboutContract.Model) this.mModel).getCommunityContent().subscribe(new IObserver(new ICallback<CommunityOutput>() {
            @Override
            public void onResponse(String str, CommunityOutput communityOutput) {
                ((AboutContract.View) mView).dismissLoadingPage();
                if (communityOutput != null && communityOutput.code == 0 && communityOutput.data != null) {
                    SpAPI.THIS.setEnTeleUrl(communityOutput.data.telegram_en);
                    SpAPI.THIS.setZhTeleUrl(communityOutput.data.telegram_cn);
                    SpAPI.THIS.setTwitterUrl(communityOutput.data.twitter);
                    SpAPI.THIS.setWechatUrl(communityOutput.data.WeChat);
                    ((AboutContract.View) mView).updateUrl(communityOutput.data);
                    return;
                }
                ((AboutContract.View) mView).updateUrl(null);
            }

            @Override
            public void onFailure(String str, String str2) {
                ((AboutContract.View) mView).dismissLoadingPage();
                ((AboutContract.View) mView).updateUrl(null);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "communityContent"));
    }
}
