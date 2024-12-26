package com.tron.wallet.business.assetshome.nested;

import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.wallet.business.addassets2.HomeAssetsModel;
import com.tron.wallet.business.addassets2.bean.FollowAssetsOutput;
import com.tron.wallet.common.bean.token.TokenBean;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
public class NestedAssetsPresenter extends EmptyPresenter {
    private final HomeAssetsModel mModel = new HomeAssetsModel();

    public void unFollowAssets(String str, TokenBean tokenBean) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(tokenBean);
        this.mModel.followAssets(null, arrayList).subscribe(new IObserver(new ICallback<FollowAssetsOutput>() {
            @Override
            public void onFailure(String str2, String str3) {
            }

            @Override
            public void onResponse(String str2, FollowAssetsOutput followAssetsOutput) {
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "ignoreAllNewAssets"));
        this.mModel.removeTokenSortBean(str, tokenBean);
    }
}
