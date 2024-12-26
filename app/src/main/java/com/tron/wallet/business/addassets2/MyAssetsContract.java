package com.tron.wallet.business.addassets2;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.addassets2.bean.AssetsData;
import com.tron.wallet.business.addassets2.bean.AssetsDataOutput;
import com.tron.wallet.business.addassets2.bean.DelMyAssetsOutput;
import com.tron.wallet.business.addassets2.bean.FollowAssetsOutput;
import com.tron.wallet.business.addassets2.bean.NftDataOutput;
import com.tron.wallet.business.addassets2.bean.nft.NftTokenBean;
import com.tron.wallet.common.bean.token.TokenBean;
import io.reactivex.Observable;
import java.util.List;
public interface MyAssetsContract {

    public interface Model extends BaseModel {
        Observable<FollowAssetsOutput> followAssets(List<TokenBean> list, List<TokenBean> list2);

        Observable<AssetsData> getMyAssets();

        Observable<List<NftTokenBean>> getMyCollections();

        void removeTokenSortBean(String str, TokenBean tokenBean);

        Observable<DelMyAssetsOutput> requestDelMyAssets(List<TokenBean> list);

        Observable<DelMyAssetsOutput> requestDelMyCollections(List<NftTokenBean> list);

        Observable<AssetsDataOutput> requestMyAssets();

        Observable<NftDataOutput> requestMyCollections();

        Observable<Boolean> saveMyAssets(AssetsData assetsData);

        Observable<Boolean> saveMyCollections(List<NftTokenBean> list);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        abstract void deleteAssets(TokenBean tokenBean, int i);

        abstract void filterAndSort(boolean z, boolean z2);

        abstract void followAssets(TokenBean tokenBean, int i);

        abstract void getMyAssets();

        abstract void hideZeroAssets(boolean z);

        abstract void ignoreAllNewAssets();

        abstract void requestMyAssets();

        abstract void requestMyAssets(boolean z);

        abstract void setIsAssets(boolean z);

        abstract void showAssetsDetail(TokenBean tokenBean);

        abstract void unFollowAssets(TokenBean tokenBean, int i);
    }

    public interface View extends BaseView {
        void showAssetsAddedView();

        void showNoNetView();

        void updateAssetsFollowState(TokenBean tokenBean, int i, boolean z);

        void updateAssetsPrice();

        void updateComplete(boolean z);

        void updateMyAssets(List<TokenBean> list);
    }
}
