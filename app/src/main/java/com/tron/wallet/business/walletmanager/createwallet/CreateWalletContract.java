package com.tron.wallet.business.walletmanager.createwallet;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
public interface CreateWalletContract {

    public interface Model extends BaseModel {
        void saveWallet(String str, String str2);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        abstract void create(boolean z, String str, String str2);

        public abstract void jumpSecurity();

        @Override
        protected void onStart() {
        }

        public abstract void setSecurityAction();
    }

    public interface View extends BaseView {
        void showSecurityLayout();
    }
}
