package com.tron.wallet.business.vote.fastvote;

import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.business.vote.fastvote.VoteDataHolder;
import java.util.ArrayList;
import java.util.List;
import org.tron.protos.Protocol;
public interface FastVoteContract {

    public interface Model extends BaseModel {
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void ClearVoteData(int i);

        public abstract String getCurrentAddress();

        public abstract void init(long j, long j2);

        protected abstract void send(Protocol.Account account, int i, String str, VoteDataHolder.ViewCallback viewCallback);

        public abstract void updateRvList(ArrayList<VoteWitnessBean> arrayList, long j, ArrayList<WitnessOutput.DataBean> arrayList2, long j2);
    }

    public interface View extends BaseView {
        android.view.View getFooterView();

        String getLogPageTag();

        RecyclerView getRv();

        void showAlertPopWindow();

        void updateVoteApr(List<VoteWitnessBean> list, long j);

        void updateVoteCountLayout(long j);

        void updateVoteCountTips(boolean z);
    }
}
