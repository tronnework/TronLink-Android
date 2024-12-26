package com.tron.wallet.business.tabswap.record;

import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.common.components.ptr.PtrFrameLayout;
import com.tron.wallet.db.bean.JustSwapBean;
import java.util.List;
public interface SwapRecordContract {

    public interface Model extends BaseModel {
        List<JustSwapBean> requestRecords(String str, int i, int i2);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        abstract void getData();

        public abstract void init();

        public abstract void onRefresh();
    }

    public interface View extends BaseView {
        android.view.View getHolderView();

        PtrFrameLayout getRcFrame();

        RecyclerView getRcList();

        void showEmptyView(boolean z);

        void showNoNetError(boolean z);

        void updateUI(boolean z, boolean z2);
    }
}
