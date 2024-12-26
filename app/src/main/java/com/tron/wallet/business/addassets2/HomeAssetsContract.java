package com.tron.wallet.business.addassets2;

import android.util.SparseArray;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.addassets2.bean.FollowAssetsOutput;
import com.tron.wallet.business.addassets2.bean.TokenSortBean;
import com.tron.wallet.business.addassets2.bean.TokenSortType;
import com.tron.wallet.business.addassets2.bean.asset.AssetsHomeData;
import com.tron.wallet.business.addassets2.bean.asset.AssetsHomeOutput;
import com.tron.wallet.business.addassets2.bean.nft.NftAssetOutput;
import com.tron.wallet.business.addassets2.bean.nft.NftTokenBean;
import com.tron.wallet.common.bean.token.TokenBean;
import io.reactivex.Observable;
import java.util.List;
public interface HomeAssetsContract {

    public interface Model extends BaseModel {
        Observable<FollowAssetsOutput> followAssets(List<TokenBean> list, List<TokenBean> list2);

        Observable<AssetsHomeData> getFollowAssets(int i);

        Observable<List<NftTokenBean>> getFollowCollections(int i);

        Observable<TokenSortType> getTokenSortType(String str, int i);

        void removeTokenSortBean(String str, TokenBean tokenBean);

        Observable<AssetsHomeOutput> requestFollowAssets(String str);

        Observable<NftAssetOutput> requestFollowCollections(String str);

        Observable<Boolean> saveTokenSortBeanList(String str, List<TokenSortBean> list, int i);

        Observable<Boolean> setTokenSortType(String str, TokenSortType tokenSortType, int i);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        abstract void getHomeAssets();

        abstract TokenSortType getSortType();

        abstract void insertSortBean(SparseArray<TokenBean> sparseArray);

        abstract void requestHomeAssets();

        abstract void requestHomeAssets(boolean z);

        abstract void setInitSortType(int i);

        abstract void setIsHomeAssets(boolean z);

        abstract void setSortType(TokenSortType tokenSortType);

        abstract void showAssetsDetail(TokenBean tokenBean);

        abstract void unFollowAssets(TokenBean tokenBean, int i);
    }

    public interface View extends BaseView {
        void showNoNetView();

        void updateAssetsFollowState(TokenBean tokenBean, int i);

        void updateAssetsPrice();

        void updateComplete(boolean z);

        void updateHomeAssets(List<TokenBean> list);
    }
}
