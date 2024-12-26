package com.tron.wallet.business.tabmy.joincommunity;

import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.wallet.business.tabmy.bean.CommunityOutput;
import com.tron.wallet.business.tabmy.joincommunity.JoinCommunityContract;
import com.tron.wallet.db.SpAPI;
import io.reactivex.disposables.Disposable;
public class JoinCommunityPresenter extends JoinCommunityContract.Presenter {
    @Override
    protected void onStart() {
    }

    @Override
    public void getCommunityContent() {
        ((JoinCommunityContract.Model) this.mModel).getCommunityContent().subscribe(new IObserver(new ICallback<CommunityOutput>() {
            @Override
            public void onResponse(String str, CommunityOutput communityOutput) {
                ((JoinCommunityContract.View) mView).dismissLoadingPage();
                if (communityOutput != null && communityOutput.code == 0 && communityOutput.data != null) {
                    SpAPI.THIS.setEnTeleUrl(communityOutput.data.telegram_en);
                    SpAPI.THIS.setZhTeleUrl(communityOutput.data.telegram_cn);
                    SpAPI.THIS.setTwitterUrl(communityOutput.data.twitter);
                    SpAPI.THIS.setWechatUrl(communityOutput.data.WeChat);
                    ((JoinCommunityContract.View) mView).updateUi(communityOutput.data);
                    return;
                }
                ((JoinCommunityContract.View) mView).updateUi(null);
            }

            @Override
            public void onFailure(String str, String str2) {
                ((JoinCommunityContract.View) mView).dismissLoadingPage();
                ((JoinCommunityContract.View) mView).updateUi(null);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "111"));
    }
}
