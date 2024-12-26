package com.tron.wallet.business.tabswap;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.tabswap.SwapMainFragment;
import com.tron.wallet.business.tabswap.bean.AppStatusOutput;
import com.tron.wallet.common.bean.Result2;
import io.reactivex.Observable;
import okhttp3.RequestBody;
public interface SwapMainContract {

    public interface Model extends BaseModel {
        Observable<Result2<AppStatusOutput>> getStatus(RequestBody requestBody);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
    }

    public interface View extends BaseView {
        void showPage(SwapMainFragment.TabStatus tabStatus, SwapMainFragment.PageStatus pageStatus);
    }
}
