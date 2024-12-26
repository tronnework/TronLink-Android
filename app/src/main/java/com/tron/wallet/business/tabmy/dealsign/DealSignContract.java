package com.tron.wallet.business.tabmy.dealsign;

import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
public interface DealSignContract {

    public interface Model extends BaseModel {
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        protected abstract void addSome(FragmentManager fragmentManager);

        protected abstract void onCreate2(Bundle bundle, FragmentManager fragmentManager);

        protected abstract void onSaveInstanceState(Bundle bundle);

        protected abstract void onTabClick(int i);
    }

    public interface View extends BaseView {
        boolean getSocketConnect();

        String getWalletName();

        void setSocketConnect(boolean z);

        void showOrHideWaitSignCount(int i);
    }
}
