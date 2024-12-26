package com.tron.wallet.business.security.tokencheck;

import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.addassets2.bean.DelMyAssetsOutput;
import com.tron.wallet.business.addassets2.bean.nft.NftTokenBean;
import com.tron.wallet.business.security.tokencheck.TokenCheckContract;
import com.tron.wallet.business.security.tokencheck.bean.TokenCheckBean;
import com.tron.wallet.business.security.tokencheck.controller.IgnoreTokenManager;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.db.wallet.WalletUtils;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
public class TokenCheckPresenter extends TokenCheckContract.Presenter {
    @Override
    protected void onStart() {
    }

    @Override
    public void deleteToken(TokenCheckBean tokenCheckBean) {
        TokenBean tokenBean = new TokenBean(tokenCheckBean);
        tokenBean.address = WalletUtils.getSelectedWallet().getAddress();
        if (tokenBean.getType() == 5) {
            ArrayList arrayList = new ArrayList();
            arrayList.add((tokenBean.getExtraData() == null || !(tokenBean.getExtraData() instanceof NftTokenBean)) ? NftTokenBean.covertFromTokenBean(tokenBean) : (NftTokenBean) tokenBean.getExtraData());
            ((TokenCheckContract.Model) this.mModel).requestDelMyCollections(arrayList).subscribe(new IObserver(new ICallback<DelMyAssetsOutput>() {
                @Override
                public void onResponse(String str, DelMyAssetsOutput delMyAssetsOutput) {
                    LogUtils.d("deleteToken", "result:" + delMyAssetsOutput);
                }

                @Override
                public void onFailure(String str, String str2) {
                    LogUtils.d("deleteToken", "result:" + str2);
                }

                @Override
                public void onSubscribe(Disposable disposable) {
                    mRxManager.add(disposable);
                }
            }, "deleteAssets"));
            return;
        }
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(tokenBean);
        ((TokenCheckContract.Model) this.mModel).requestDelMyAssets(arrayList2).subscribe(new IObserver(new ICallback<DelMyAssetsOutput>() {
            @Override
            public void onFailure(String str, String str2) {
            }

            @Override
            public void onResponse(String str, DelMyAssetsOutput delMyAssetsOutput) {
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "deleteAssets"));
    }

    @Override
    public void ignoreToken(TokenCheckBean tokenCheckBean) {
        IgnoreTokenManager.getInstance().add(tokenCheckBean);
    }
}
