package com.tron.wallet.business.confirm.core.pending.fg.vote;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.business.confirm.core.pending.ConfirmPendingContract;
import com.tron.wallet.business.confirm.core.pending.ConfirmPendingModel;
import com.tron.wallet.business.confirm.core.pending.ConfirmPendingPresenter;
import com.tron.wallet.business.confirm.core.pending.Keys;
import com.tron.wallet.business.confirm.core.pending.State;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.confirm.parambuilder.bean.VoteParam;
import com.tron.wallet.common.bean.token.TransactionInfoBean;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.DataStatHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.FgVotePendingBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import org.tron.common.utils.ByteArray;
import org.tron.walletserver.Wallet;
public class VotePendingFragment extends BaseFragment<ConfirmPendingPresenter, ConfirmPendingModel> implements ConfirmPendingContract.View {
    View RlFail;
    View RlSuccess;
    private BaseParam baseParam;
    private FgVotePendingBinding binding;
    View btnBackToVoteHome;
    View btnDone;
    ImageView ivResult;
    View main;
    TextView tvResult;
    TextView tvResultHint;

    public static VotePendingFragment getInstance(State state, BaseParam baseParam, byte[] bArr, int i) {
        VotePendingFragment votePendingFragment = new VotePendingFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Keys.BaseParam, baseParam);
        bundle.putInt(Keys.UnsuccessfulTransactions, i);
        if (!ByteArray.isEmpty(bArr)) {
            bundle.putByteArray(Keys.RPCReturn, bArr);
        }
        bundle.putSerializable(Keys.StateKey, state);
        votePendingFragment.setArguments(bundle);
        return votePendingFragment;
    }

    public void setClick() {
        this.binding.btnAgain.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (view.getId() != R.id.btn_again) {
                    return;
                }
                AnalyticsHelper.logEvent(AnalyticsHelper.ConfirmResult.CLICK_VOTE_RESULT_FAILED_0);
                mContext.finish();
            }
        });
    }

    public void openVoteActivity() {
        new RxManager().post(Event.VOTE_SUCCESS, 1);
        VoteIntentIntegrator.openVoteMainActivity(this.mContext, this.baseParam);
    }

    @Override
    protected void processData() {
        ((ConfirmPendingPresenter) this.mPresenter).initData();
        this.btnDone.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if ((baseParam instanceof VoteParam) && ((VoteParam) baseParam).isFromStakeSuccess()) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.ConfirmResult.CLICK_STAKE_RESULT_SUCCEEDED_VOTE_SUCCESS);
                }
                openVoteActivity();
            }
        });
        this.btnBackToVoteHome.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                AnalyticsHelper.logEvent(AnalyticsHelper.ConfirmResult.CLICK_VOTE_RESULT_FAILED_1);
                openVoteActivity();
            }
        });
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FgVotePendingBinding inflate = FgVotePendingBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        setClick();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    public void mappingId() {
        this.RlSuccess = this.binding.rlSuccess;
        this.RlFail = this.binding.rlFail;
        this.ivResult = this.binding.ivResult;
        this.tvResult = this.binding.tvResult;
        this.tvResultHint = this.binding.tvResultHint;
        this.main = this.binding.main;
        this.btnDone = this.binding.btnDone;
        this.btnBackToVoteHome = this.binding.btnBackToVoteHome;
    }

    @Override
    public void onSuccess(TransactionInfoBean transactionInfoBean, BaseParam baseParam) {
        this.baseParam = baseParam;
        setVisibility(this.RlSuccess, this.RlFail);
        this.tvResult.setText(R.string.vote_result_success);
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        if (selectedWallet != null && !TextUtils.isEmpty(selectedWallet.address)) {
            String address = selectedWallet.getAddress();
            SpAPI.THIS.setLastVoteApr(address, ((VoteParam) baseParam).getApr());
            LogUtils.e("setLastVoteApr", "LastVoteApr = " + SpAPI.THIS.getLastVoteApr(address));
        }
        this.ivResult.setImageResource(R.mipmap.ic_unstake_result_success);
        AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.VoteResult.ENTER_VoteResult_SUCCESS);
        if (baseParam instanceof VoteParam) {
            VoteParam voteParam = (VoteParam) baseParam;
            DataStatHelper.uploadVote(voteParam);
            if (voteParam.isFromStakeSuccess()) {
                AnalyticsHelper.logEvent(AnalyticsHelper.ConfirmResult.CLICK_STAKE_RESULT_SUCCEEDED_VOTE_SUCCESS_PAGE);
            }
        }
    }

    @Override
    public void onFail(BaseParam baseParam, int i, String str) {
        this.baseParam = baseParam;
        setVisibility(this.RlFail, this.RlSuccess);
        this.tvResult.setText(R.string.vote_result_fail);
        if (StringTronUtil.isEmpty(str)) {
            this.tvResultHint.setVisibility(View.GONE);
        } else {
            this.tvResultHint.setText(str);
        }
        this.ivResult.setImageResource(R.mipmap.ic_unstake_result_fail_all);
        AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.VoteResult.ENTER_VoteResult_FAILED);
    }

    @Override
    public Bundle getIArguments() {
        return getArguments();
    }

    public void setVisibility(View view, View view2) {
        this.main.setVisibility(View.VISIBLE);
        view.setVisibility(View.VISIBLE);
        view2.setVisibility(View.GONE);
    }
}
