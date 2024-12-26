package com.tron.wallet.business.pull.component;

import android.content.Intent;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.pull.PullAction;
import com.tron.wallet.business.pull.PullParam;
import com.tron.wallet.business.pull.component.PullDetailPresenter;
public interface PullDetailContract {

    public interface Model extends BaseModel {
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        abstract PullDetailPresenter.SignTransactionParseResult getSignTransactionParseResult();

        abstract void onActivityResult(int i, int i2, Intent intent);

        abstract PullParam parseSignParam(String str);

        abstract void setParam(PullAction pullAction, PullParam pullParam);
    }

    public interface View extends BaseView {
        void setDetailView(PullDetailView pullDetailView);

        void showInvalidAccountView(String str);

        void showPullLoading(boolean z);
    }
}
