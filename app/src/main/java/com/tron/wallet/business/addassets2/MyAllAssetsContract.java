package com.tron.wallet.business.addassets2;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.addassets2.bean.AssetsData;
import io.reactivex.Observable;
public interface MyAllAssetsContract {

    public interface Model extends BaseModel {
        AssetsData getNewAssets(String str);

        Observable<Boolean> setSortFilterStatue(String str, boolean z, boolean z2);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        abstract void getNewAssets();

        abstract void updateFilterStatus(boolean z, boolean z2);
    }

    public interface View extends BaseView {
        void showNewAssetsTips(boolean z);

        void showNewCollectionsTips(boolean z);
    }
}
