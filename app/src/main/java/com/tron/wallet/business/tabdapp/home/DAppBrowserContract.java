package com.tron.wallet.business.tabdapp.home;

import android.content.Intent;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.common.components.browser.BrowserContent;
public interface DAppBrowserContract {

    public interface Model extends BaseModel {
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void getData();

        abstract void onActivityResult(int i, int i2, Intent intent);
    }

    public interface View extends BaseView {
        BrowserContent getBrowserContent();
    }
}
