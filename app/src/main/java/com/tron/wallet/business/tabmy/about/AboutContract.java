package com.tron.wallet.business.tabmy.about;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.tabmy.bean.CommunityOutput;
import io.reactivex.Observable;
public interface AboutContract {

    public interface Model extends BaseModel {
        Observable<CommunityOutput> getCommunityContent();
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void getCommunityContent();
    }

    public interface View extends BaseView {
        void goIntent(String str);

        void updateUrl(CommunityOutput.DataBean dataBean);
    }
}
