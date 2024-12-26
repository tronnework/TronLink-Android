package com.tron.wallet.business.tokendetail.mvp;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.addassets2.bean.BaseOutput;
import io.reactivex.Observable;
import okhttp3.RequestBody;
public interface TokenReportContract {

    public interface Model extends BaseModel {
        Observable<BaseOutput> requestTokenReport(RequestBody requestBody);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void report(String str, int i, String str2, String str3, String str4, String str5);
    }

    public interface View extends BaseView {
        void reportSuccess();
    }
}
