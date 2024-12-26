package com.tron.wallet.business.web;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
public interface CommonWebContract {

    public interface Model extends BaseModel {
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void setFrom(String str);
    }

    public interface View extends BaseView {
    }
}
