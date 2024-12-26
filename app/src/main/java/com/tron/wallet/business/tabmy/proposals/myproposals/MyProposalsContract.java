package com.tron.wallet.business.tabmy.proposals.myproposals;

import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import io.reactivex.Observable;
import java.util.List;
import org.tron.protos.Protocol;
public interface MyProposalsContract {

    public interface Model extends BaseModel {
        Observable<WitnessOutput> getWitnessList();
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        protected abstract void addSome(List<Protocol.Proposal> list, String str, List<WitnessOutput.DataBean> list2);

        public abstract void getData();

        protected abstract void initListener();

        public abstract void makeProposal();
    }

    public interface View extends BaseView {
        String getFromType();

        RecyclerView getRcList();

        String getSelectAddress();

        void showLoading(boolean z);

        void showNoDataLayout(boolean z);
    }
}
