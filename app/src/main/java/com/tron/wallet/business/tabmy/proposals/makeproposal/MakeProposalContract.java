package com.tron.wallet.business.tabmy.proposals.makeproposal;

import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.common.bean.ChainparametersOutPut;
import io.reactivex.Observable;
public interface MakeProposalContract {

    public interface Model extends BaseModel {
        Observable<ChainparametersOutPut> getChainparameters();
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        protected abstract void addSome();

        protected abstract void confirm();

        protected abstract void getData();
    }

    public interface View extends BaseView {
        Intent getIIntent();

        RecyclerView getRc();

        void showLoading(boolean z);

        void showOrHideCreate(boolean z);
    }
}
