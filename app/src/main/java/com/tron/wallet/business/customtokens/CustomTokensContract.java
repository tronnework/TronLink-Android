package com.tron.wallet.business.customtokens;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.customtokens.bean.AddCustomTokenOutput;
import com.tron.wallet.business.customtokens.bean.CustomTokenBean;
import com.tron.wallet.business.customtokens.bean.QueryCustomTokenOutput;
import com.tron.wallet.business.customtokens.bean.SyncCustomTokenOutput;
import com.tron.wallet.common.bean.token.TokenBean;
import io.reactivex.Observable;
public interface CustomTokensContract {

    public interface Model extends BaseModel {
        Observable<AddCustomTokenOutput> addCustomToken(String str, TokenBean tokenBean);

        Observable<QueryCustomTokenOutput> queryCustomToken(String str, String str2);

        Observable<SyncCustomTokenOutput> syncCustomToken(String str, String str2);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        abstract void getToken(String str);
    }

    public interface View extends BaseView {
        void showNetError(String str);

        void updateToken(CustomTokenBean customTokenBean, String str);
    }
}
