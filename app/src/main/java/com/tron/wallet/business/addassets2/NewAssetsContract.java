package com.tron.wallet.business.addassets2;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.addassets2.bean.AssetsData;
import com.tron.wallet.business.addassets2.bean.FollowAssetsOutput;
import com.tron.wallet.common.bean.token.TokenBean;
import io.reactivex.Observable;
import java.util.List;
public interface NewAssetsContract {

    public interface Model extends BaseModel {
        void clearNewAssets(String str);

        Observable<FollowAssetsOutput> followAssets(List<TokenBean> list, List<TokenBean> list2);

        AssetsData getNewAssets(String str);

        void removeNewAssets(String str, TokenBean tokenBean);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        abstract void followAssets(TokenBean tokenBean, int i);

        abstract void getNewAssets();

        abstract void ignoreAllNewAssets();

        public abstract boolean isFirstEnter();

        abstract void showAssetsDetail(TokenBean tokenBean);
    }

    public interface View extends BaseView {
        void showAssetsAddedView();

        void showAssetsNoIgnore();

        void showNoDataView();

        void updateAssets(List<TokenBean> list);

        void updateAssetsPrice();

        void updateAssetsState(TokenBean tokenBean, int i);

        void updateComplete();
    }
}
