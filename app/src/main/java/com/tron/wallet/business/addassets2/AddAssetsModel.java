package com.tron.wallet.business.addassets2;

import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.addassets2.AddAssetsContract;
import com.tron.wallet.business.addassets2.bean.AssetsData;
import com.tron.wallet.business.addassets2.bean.TokenSortType;
import com.tron.wallet.business.addassets2.bean.asset.AssetsHomeData;
import com.tron.wallet.business.addassets2.repository.KVController;
import com.tron.wallet.business.addassets2.repository.NewAssetsController;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
public class AddAssetsModel implements AddAssetsContract.Model {
    @Override
    public AssetsData getNewAssets(String str) {
        return NewAssetsController.getInstance().getNewAssets(str);
    }

    @Override
    public Observable<TokenSortType> getTokenSortType(final String str) {
        return Observable.unsafeCreate(new ObservableSource<TokenSortType>() {
            @Override
            public void subscribe(Observer<? super TokenSortType> observer) {
                observer.onNext(KVController.getInstance().getTokenSortType(str));
                observer.onComplete();
            }
        }).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<AssetsHomeData> getFollowAssets() {
        return AssetsManager.getInstance().getFollowAssets();
    }
}
