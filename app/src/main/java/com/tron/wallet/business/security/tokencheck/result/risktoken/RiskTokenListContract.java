package com.tron.wallet.business.security.tokencheck.result.risktoken;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
public interface RiskTokenListContract {

    public interface Model extends BaseModel {
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void IgnoreToken();

        public abstract void deleteToken();

        public abstract void resetTokenList();

        public abstract void searchToken(String str);
    }

    public interface View extends BaseView {
        void updateTokenList();
    }
}
