package com.tron.wallet.business.tabdapp.browser.tabs;

import android.content.Intent;
import androidx.viewpager2.widget.ViewPager2;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.common.components.browser.BrowserTabBottomToolbar;
import com.tron.wallet.common.components.xtablayout.XTabLayoutV2;
public interface BrowserTabManagerContract {

    public interface Model extends BaseModel {
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void clearBrowserHistory();

        public abstract void clearBrowserTabs();

        public abstract void getData();

        public abstract void newBrowserTab();
    }

    public interface View extends BaseView {
        BrowserTabBottomToolbar getBrowserTabBottomToolbar();

        String getCurAddress();

        Intent getIIntent();

        ViewPager2 getViewPager();

        XTabLayoutV2 getXTablayout();

        void resetButtons();
    }
}
