package com.tron.wallet.business.tokendetail.mvp;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.common.bean.token.TokenBean;
public interface TokenDetailHeaderContract {

    public interface Model extends BaseModel {
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        abstract void addSome();

        abstract void addTimer();

        public abstract void requestHomeAssets();
    }

    public interface View extends BaseView {
        void bindHolder();

        TokenBean getToken();

        String getTokenType();

        boolean isIDestroyed();

        void refresh();

        void showPriceLoading(boolean z);
    }
}
