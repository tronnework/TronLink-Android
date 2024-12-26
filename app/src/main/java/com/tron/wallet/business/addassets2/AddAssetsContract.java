package com.tron.wallet.business.addassets2;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.addassets2.bean.AssetsData;
import com.tron.wallet.business.addassets2.bean.TokenSortType;
import com.tron.wallet.business.addassets2.bean.asset.AssetsHomeData;
import io.reactivex.Observable;
public interface AddAssetsContract {

    public interface Model extends BaseModel {
        Observable<AssetsHomeData> getFollowAssets();

        AssetsData getNewAssets(String str);

        Observable<TokenSortType> getTokenSortType(String str);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        abstract void getAssetsSortType();
    }

    public interface View extends BaseView {
        void updateSortType(TokenSortType tokenSortType);
    }
}
