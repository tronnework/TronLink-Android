package com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.old;

import android.content.Intent;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import io.reactivex.Observable;
import okhttp3.RequestBody;
public interface DappConfirmContract {

    public interface Model extends BaseModel {
        Observable<Object> addDappRecord(RequestBody requestBody);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        abstract void addSome();
    }

    public interface View extends BaseView {
        Intent getIItent();
    }
}
