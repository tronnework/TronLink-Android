package com.tron.wallet.business.vote.superdetail;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.confirm.parambuilder.BaseConfirmTransParamBuilder;
import com.tron.wallet.business.tabmy.proposals.bean.SRDetailBean;
import com.tron.wallet.business.vote.bean.SrTotalVotesResponse;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.common.utils.DataStatHelper;
import io.reactivex.Observable;
import java.util.HashMap;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public interface SuperDetailContract {

    public interface Model extends BaseModel {
        String checkCancelVoteError(Protocol.Account account, Wallet wallet);

        BaseConfirmTransParamBuilder getCancelVoteParamBuilder(boolean z, long j, long j2, String str, Wallet wallet, Protocol.Account account, HashMap<String, String> hashMap, HashMap<String, String> hashMap2, HashMap<String, String> hashMap3, long j3, String str2, DataStatHelper.StatAction statAction) throws Exception;

        Observable<SRDetailBean> getSrDetail(String str);

        Observable<SrTotalVotesResponse> getSrTotalVotes(String str);

        Observable<WitnessOutput> getWitnessList(String str, int i, int i2, int i3, int i4, boolean z);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void getData();

        public abstract void init(Protocol.Account account, WitnessOutput witnessOutput, WitnessOutput.DataBean dataBean);

        public abstract void vote();

        public abstract void voteCancel();

        public abstract void voteUpdate();
    }

    public interface View extends BaseView {
        void onRequestCancelVoteComplete(boolean z, String str, BaseConfirmTransParamBuilder baseConfirmTransParamBuilder);

        void updateUI(SRDetailBean sRDetailBean);
    }
}
