package com.tron.wallet.business.voteconfirm;

import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import org.tron.protos.Protocol;
public interface SingleVoteContract {

    public static abstract class Presenter extends BasePresenter<EmptyModel, View> {
        public abstract void getData(String str, Protocol.Account account, WitnessOutput witnessOutput, WitnessOutput.DataBean dataBean, long j, boolean z);

        public abstract long getUsableVotes();

        public abstract double getYield(long j);

        public abstract void vote(long j, int i);
    }

    public interface View extends BaseView {
        void showLoading(boolean z);

        void showNoNetError();

        void updateVoteCount(long j, long j2);

        void updateVotedNumber(long j);
    }
}
