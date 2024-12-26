package com.tron.wallet.business.tabmy.joincommunity;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.tabmy.bean.CommunityOutput;
import io.reactivex.Observable;
public interface JoinCommunityContract {

    public interface Model extends BaseModel {
        Observable<CommunityOutput> getCommunityContent();
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void getCommunityContent();
    }

    public interface View extends BaseView {
        void copyStr(String str);

        void goIntent(String str);

        void updateUi(CommunityOutput.DataBean dataBean);
    }
}
