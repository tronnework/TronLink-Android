package com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.common;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.tabdapp.bean.DappProjectIOutput;
import io.reactivex.Observable;
public interface DappCommonConfirmContract {

    public interface Model extends BaseModel {
        Observable<DappProjectIOutput> getProjectInfo(String str, String str2);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
    }

    public interface View extends BaseView {
    }
}
