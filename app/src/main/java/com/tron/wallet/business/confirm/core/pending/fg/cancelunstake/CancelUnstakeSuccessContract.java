package com.tron.wallet.business.confirm.core.pending.fg.cancelunstake;

import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.mutil.bean.MultiSignPermissionData;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.business.vote.fastvote.VoteWitnessBean;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.List;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
public interface CancelUnstakeSuccessContract {

    public interface Model extends BaseModel {
        Observable<Protocol.Account> getAccount(Protocol.Account account, String str);

        Observable<GrpcAPI.AccountResourceMessage> getAccountResourceMessage(GrpcAPI.AccountResourceMessage accountResourceMessage, String str);

        Observable<WitnessOutput> getWitnessList();

        Observable<WitnessOutput> requestWitnessList(String str, int i, int i2, int i3, int i4);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void VoteToProfit();

        public abstract void initVoteData();

        public abstract void startOtherVote();
    }

    public interface View extends BaseView {
        String getFormatApr();

        RecyclerView getRecyclerView();

        void onFail();

        void onSuccess(long j, ArrayList<WitnessOutput.DataBean> arrayList, Protocol.Account account, MultiSignPermissionData multiSignPermissionData);

        void showLoadFailed();

        void showVoteLoading();

        void showVotes();

        void updateVoteApr(List<VoteWitnessBean> list, long j);
    }
}
