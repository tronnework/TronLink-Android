package com.tron.wallet.business.confirm.core.pending.fg.vote;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.wallet.business.confirm.core.pending.ConfirmPendingContract;
import com.tron.wallet.business.confirm.core.pending.ConfirmPendingModel;
import com.tron.wallet.business.confirm.core.pending.ConfirmPendingPresenter;
import com.tron.wallet.business.confirm.core.pending.Keys;
import com.tron.wallet.business.confirm.core.pending.State;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.confirm.parambuilder.bean.VoteParam;
import com.tron.wallet.common.bean.token.TransactionInfoBean;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.FgVoteCancelPendingBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.text.NumberFormat;
import java.util.Locale;
import org.tron.common.utils.ByteArray;
import org.tron.walletserver.Wallet;
public class VoteCancelPendingFragment extends BaseFragment<ConfirmPendingPresenter, ConfirmPendingModel> implements ConfirmPendingContract.View {
    View RlFail;
    View RlSuccess;
    private BaseParam baseParam;
    private FgVoteCancelPendingBinding binding;
    View btnBackToVoteHome;
    View btnDone;
    ImageView ivResult;
    View llVoteContainer;
    View main;
    TextView tvResult;
    TextView tvResultHint;
    TextView tvVoteInfo;

    public static VoteCancelPendingFragment getInstance(State state, BaseParam baseParam, byte[] bArr, int i) {
        VoteCancelPendingFragment voteCancelPendingFragment = new VoteCancelPendingFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Keys.BaseParam, baseParam);
        bundle.putInt(Keys.UnsuccessfulTransactions, i);
        if (!ByteArray.isEmpty(bArr)) {
            bundle.putByteArray(Keys.RPCReturn, bArr);
        }
        bundle.putSerializable(Keys.StateKey, state);
        voteCancelPendingFragment.setArguments(bundle);
        return voteCancelPendingFragment;
    }

    public void setClick() {
        this.binding.btnAgain.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (view.getId() != R.id.btn_again) {
                    return;
                }
                mContext.finish();
            }
        });
    }

    public void openVoteActivity() {
        VoteIntentIntegrator.openVoteMainActivity(this.mContext, this.baseParam);
    }

    @Override
    protected void processData() {
        ((ConfirmPendingPresenter) this.mPresenter).initData();
        this.btnDone.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                openVoteActivity();
            }
        });
        this.btnBackToVoteHome.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                openVoteActivity();
            }
        });
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FgVoteCancelPendingBinding inflate = FgVoteCancelPendingBinding.inflate(layoutInflater, viewGroup, false);
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
        this.tvVoteInfo = this.binding.tvVoteInfo;
        this.llVoteContainer = this.binding.llVoteContainer;
    }

    @Override
    public void onSuccess(TransactionInfoBean transactionInfoBean, BaseParam baseParam) {
        this.baseParam = baseParam;
        setVisibility(this.RlSuccess, this.RlFail);
        this.tvResult.setText(R.string.vote_cancel_result_success);
        this.ivResult.setImageResource(R.mipmap.ic_unstake_result_success);
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        if (selectedWallet != null && !StringTronUtil.isEmpty(selectedWallet.getAddress())) {
            SpAPI.THIS.setLastVoteApr(selectedWallet.getAddress(), "");
        }
        if (baseParam instanceof VoteParam) {
            NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
            numberInstance.setMaximumFractionDigits(6);
            VoteParam voteParam = (VoteParam) baseParam;
            this.tvVoteInfo.setVisibility(View.VISIBLE);
            this.llVoteContainer.setVisibility(View.VISIBLE);
            this.tvVoteInfo.setText(String.format(this.mContext.getString(R.string.vote_cancel_hint), numberInstance.format(voteParam.getVoteCancelled()), numberInstance.format(voteParam.getVotesAvailable())));
        }
    }

    @Override
    public void onFail(BaseParam baseParam, int i, String str) {
        this.baseParam = baseParam;
        setVisibility(this.RlFail, this.RlSuccess);
        this.tvResult.setText(R.string.vote_cancel_result_fail);
        AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.VoteResult.ENTER_VoteResult_CANDEL_FAILED);
        if (StringTronUtil.isEmpty(str)) {
            this.tvResultHint.setVisibility(View.GONE);
        } else {
            this.tvResultHint.setVisibility(View.VISIBLE);
            this.tvResultHint.setText(str);
        }
        this.ivResult.setImageResource(R.mipmap.ic_unstake_result_fail_all);
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
