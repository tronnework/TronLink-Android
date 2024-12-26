package com.tron.wallet.business.vote.component;

import android.content.Context;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.confirm.parambuilder.BaseConfirmTransParamBuilder;
import com.tron.wallet.business.mutil.bean.MultiSignPermissionData;
import com.tron.wallet.business.vote.bean.FastAprBean;
import com.tron.wallet.business.vote.bean.RewardOutput;
import com.tron.wallet.business.vote.bean.VoteHeaderBean;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.business.vote.fg.VoteContentContract;
import com.tron.wallet.common.utils.DataStatHelper;
import io.reactivex.Observable;
import java.util.HashMap;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public interface Contract {

    public interface Config {
        public static final int DEFAULT_PAGE_INDEX = 1;
        public static final int DEFAULT_PAGE_SIZE = 30;
        public static final int FILTER_MY_VOTES = 1;
        public static final int NO_FILTER = 0;
    }

    public interface Model extends BaseModel {
        String checkCancelVoteError(Protocol.Account account, Wallet wallet);

        Protocol.Account ensureAccount(Context context, Protocol.Account account, String str) throws Exception;

        BaseConfirmTransParamBuilder getCancelVoteParamBuilder(boolean z, long j, long j2, MultiSignPermissionData multiSignPermissionData, String str, Wallet wallet, Protocol.Account account, HashMap<String, String> hashMap, HashMap<String, String> hashMap2, HashMap<String, String> hashMap3, DataStatHelper.StatAction statAction) throws Exception;

        WitnessOutput.DataBean getMaxVotedWitness(HashMap<String, String> hashMap, HashMap<String, String> hashMap2, WitnessOutput witnessOutput);

        VoteHeaderBean getVotingData(boolean z, Protocol.Account account, String str) throws Exception;

        Observable<RewardOutput> requestCanReward(String str);

        BaseConfirmTransParamBuilder requestGetProfit(String str, String str2, MultiSignPermissionData multiSignPermissionData, Protocol.Account account, double d) throws Exception;

        double requestProfitNumber(String str);

        Observable<WitnessOutput> requestWitnessList(String str, int i, int i2, int i3, int i4);
    }

    public interface OnPullToRefreshCallback {
        void onPullToRefresh();
    }

    public enum PermissionForStake {
        SUCCESS,
        NOT_ACTIVATE,
        NO_PERMISSION,
        OTHER_ERROR
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        abstract Observable<PermissionForStake> checkStakePermission();

        abstract void ensureAccount(Protocol.Account account, String str);

        abstract void getVotingData(boolean z, Protocol.Account account, String str);

        abstract void requestApr(String str);

        abstract void requestCancelAllVotes(boolean z, long j, String str, Protocol.Account account);

        abstract void requestGetProfit(String str, Protocol.Account account, double d);

        abstract void requestProfitNumber(String str);

        abstract void requestWitnessList(boolean z, String str, int i, int i2);
    }

    public interface SortType extends VoteContentContract.Presenter.SORT {
        public static final int ALL_VOTES = 7;
        public static final int APR = 6;
        public static final int MY_VOTES = 5;
    }

    public interface View extends BaseView {
        DataStatHelper.StatAction getStatAction();

        void onRequestAccountSuccess(Protocol.Account account);

        void onRequestAprComplete(FastAprBean fastAprBean);

        void onRequestCancelVoteComplete(boolean z, String str, BaseConfirmTransParamBuilder baseConfirmTransParamBuilder);

        void onRequestFail(String str);

        void onRequestProfitComplete(boolean z, int i);

        void onRequestProfitNumberComplete(double d, boolean z, String str);

        void onRequestWitnessSuccess(boolean z, WitnessOutput witnessOutput);

        void onVotingDataSuccess(VoteHeaderBean voteHeaderBean);
    }
}
