package com.tron.wallet.business.tabmy.proposals;

import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.common.components.ptr.PtrFrameLayout;
import io.reactivex.Observable;
public interface ProposalsContract {

    public interface Model extends BaseModel {
        Observable<WitnessOutput> getWitnessList();
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void addSome();

        public abstract void getData();

        protected abstract String getSelectAddress();

        protected abstract void initListener();

        protected abstract void makeProposal();

        protected abstract void onActivityResult(int i, int i2, Intent intent);

        protected abstract void onRefresh();
    }

    public interface View extends BaseView {
        PtrFrameLayout getRcFrame();

        RecyclerView getRcList();

        void showLoading(boolean z);
    }
}
