package com.tron.wallet.business.addassets2;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.addassets2.bean.AssetsData;
import com.tron.wallet.business.addassets2.bean.AssetsDataOutput;
import com.tron.wallet.business.addassets2.bean.FollowAssetsOutput;
import com.tron.wallet.business.addassets2.bean.asset.RecommendAssetsHomeOutput;
import com.tron.wallet.common.bean.token.TokenBean;
import io.reactivex.Observable;
import java.util.List;
public interface SearchAssetsContract {

    public interface Model extends BaseModel {
        Observable<FollowAssetsOutput> followAssets(List<TokenBean> list, List<TokenBean> list2);

        Observable<AssetsData> getHotAssets();

        Observable<RecommendAssetsHomeOutput> requestHotAssets();

        Observable<AssetsDataOutput> requestSearchAssets(String str, int i, int i2, int i3);

        Observable<Boolean> saveHotAssets(AssetsData assetsData);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        abstract void followAssets(TokenBean tokenBean, int i, boolean z);

        abstract void getHotAssets();

        abstract void reloadSearch();

        abstract void searchAssets(String str, int i, int i2);

        abstract void setFilterType(int i);

        abstract void showAssetsDetail(TokenBean tokenBean);
    }

    public interface View extends BaseView {
        void hideHotAssets();

        void loadMoreComplete(List<TokenBean> list);

        void loadMoreDone();

        void loadMoreFail();

        void showAssetsAddedView();

        void showHotAssets(List<TokenBean> list);

        void showNoNetView();

        void updateAssetsFollowState(TokenBean tokenBean, int i, boolean z);

        void updateAssetsPrice();

        void updateSearchedAssets(List<TokenBean> list);
    }
}
