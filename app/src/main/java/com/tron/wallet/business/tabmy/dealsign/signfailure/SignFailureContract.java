package com.tron.wallet.business.tabmy.dealsign.signfailure;

import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.common.bean.DealSignOutput;
import com.tron.wallet.common.components.ptr.PtrFrameLayout;
import io.reactivex.Observable;
public interface SignFailureContract {

    public interface Model extends BaseModel {
        Observable<DealSignOutput> getSignFailureTransfer(String str, int i, int i2, int i3, String str2);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void addSome();

        public abstract void getData(int i);
    }

    public interface View extends BaseView {
        String getAddress();

        android.view.View getNoNetView();

        PtrFrameLayout getRcFrame();

        RecyclerView getRcList();

        int getState();

        String getWalletName();
    }
}
