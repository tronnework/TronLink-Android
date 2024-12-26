package com.tron.wallet.business.nft.contract;

import android.content.Intent;
import androidx.viewpager2.widget.ViewPager2;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.common.components.CommonTabLayout;
import com.tron.wallet.common.components.ptr.PtrHTFrameLayoutV2;
public interface NftHistoryContract {

    public interface Model extends BaseModel {
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void addSome();

        abstract void netWorkChange();

        public abstract void setCanRefresh(boolean z);
    }

    public interface View extends BaseView {
        PtrHTFrameLayoutV2 getFrameLayout();

        Intent getIIntent();

        ViewPager2 getViewPager();

        CommonTabLayout getXTablayout();

        boolean isIDestroyed();
    }
}
