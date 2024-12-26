package com.tron.wallet.business.stakev2.stake.resource;

import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.wallet.common.components.DelegateResourceLockedView;
public interface DelegateContract {

    public static abstract class Presenter extends BasePresenter<EmptyModel, View> {
        public abstract void getResourceDate(String str, String str2, int i);

        public abstract void revertTheLockResourceSetting();
    }

    public interface View extends BaseView {
        DelegateResourceLockedView getDelegateResourceLockedView();

        void hideSoftKeyboard();

        void initDeleteGageView();

        void initSwitch();

        void revertInput();

        void updateDelegateViewData(long j, long j2);

        void updateLockTimeView(long j, long j2);

        void updateResourceNew(long j);

        void updateTheNextBtnStatus();
    }
}
