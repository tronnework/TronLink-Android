package com.tron.wallet.business.addassets2;

import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.ThreadPoolManager;
import com.tron.wallet.business.addassets2.HomeAssetsContract;
import com.tron.wallet.business.addassets2.bean.FollowAssetsOutput;
import com.tron.wallet.business.addassets2.bean.TokenSortBean;
import com.tron.wallet.business.addassets2.bean.TokenSortType;
import com.tron.wallet.business.addassets2.bean.asset.AssetsHomeData;
import com.tron.wallet.business.addassets2.bean.asset.AssetsHomeOutput;
import com.tron.wallet.business.addassets2.bean.nft.NftAssetOutput;
import com.tron.wallet.business.addassets2.bean.nft.NftTokenBean;
import com.tron.wallet.business.addassets2.repository.FollowAssetsSortListController;
import com.tron.wallet.business.addassets2.repository.KVController;
import com.tron.wallet.common.bean.token.TokenBean;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.List;
public class HomeAssetsModel implements HomeAssetsContract.Model {
    @Override
    public Observable<TokenSortType> getTokenSortType(final String str, int i) {
        return Observable.unsafeCreate(new ObservableSource<TokenSortType>() {
            @Override
            public void subscribe(Observer<? super TokenSortType> observer) {
                observer.onNext(KVController.getInstance().getTokenSortType(str));
                observer.onComplete();
            }
        }).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<Boolean> setTokenSortType(final String str, final TokenSortType tokenSortType, int i) {
        return Observable.unsafeCreate(new ObservableSource<Boolean>() {
            @Override
            public void subscribe(Observer<? super Boolean> observer) {
                KVController.getInstance().setTokenSortType(str, tokenSortType);
                observer.onNext(true);
                observer.onComplete();
            }
        }).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<AssetsHomeData> getFollowAssets(final int i) {
        return Observable.just(Integer.valueOf(i)).map(new Function() {
            @Override
            public final Object apply(Object obj) {
                return HomeAssetsModel.lambda$getFollowAssets$0(i, (Integer) obj);
            }
        });
    }

    public static AssetsHomeData lambda$getFollowAssets$0(int i, Integer num) throws Exception {
        if (i == -1) {
            return AssetsManager.getInstance().getSortedFollowAssets();
        }
        return AssetsManager.getInstance().getSortedFollowAssets(TokenSortType.getTypeByValue(i));
    }

    @Override
    public Observable<List<NftTokenBean>> getFollowCollections(final int i) {
        return Observable.just(Integer.valueOf(i)).map(new Function() {
            @Override
            public final Object apply(Object obj) {
                return HomeAssetsModel.lambda$getFollowCollections$1(i, (Integer) obj);
            }
        });
    }

    public static List lambda$getFollowCollections$1(int i, Integer num) throws Exception {
        if (i == -1) {
            return AssetsManager.getInstance().getSortedFollowCollections();
        }
        return AssetsManager.getInstance().getSortedFollowCollections(TokenSortType.getTypeByValue(i));
    }

    @Override
    public Observable<AssetsHomeOutput> requestFollowAssets(String str) {
        return AssetsManager.getInstance().requestFollowAssets(str);
    }

    @Override
    public Observable<NftAssetOutput> requestFollowCollections(String str) {
        return AssetsManager.getInstance().requestUserCollections(str);
    }

    @Override
    public Observable<FollowAssetsOutput> followAssets(List<TokenBean> list, List<TokenBean> list2) {
        return AssetsManager.getInstance().requestModifiedFollowAssets(list, list2);
    }

    @Override
    public void removeTokenSortBean(final String str, final TokenBean tokenBean) {
        ThreadPoolManager.newInstance().addExecuteTask(new Runnable() {
            @Override
            public final void run() {
                FollowAssetsSortListController.getInstance().removeSingle(str, tokenBean);
            }
        });
    }

    @Override
    public Observable<Boolean> saveTokenSortBeanList(final String str, final List<TokenSortBean> list, final int i) {
        return Observable.unsafeCreate(new ObservableSource<Boolean>() {
            @Override
            public void subscribe(Observer<? super Boolean> observer) {
                observer.onNext(Boolean.valueOf(FollowAssetsSortListController.getInstance().setTokenSortList(str, new ArrayList(list), i)));
                observer.onComplete();
            }
        }).compose(RxSchedulers.io_main());
    }
}
