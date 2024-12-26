package com.tron.wallet.business.confirm.core.pending.fg.vote;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxBus;
import com.tron.wallet.business.confirm.core.pending.ConfirmPendingContract;
import com.tron.wallet.business.confirm.core.pending.ConfirmPendingModel;
import com.tron.wallet.business.confirm.core.pending.ConfirmPendingPresenter;
import com.tron.wallet.business.confirm.core.pending.Keys;
import com.tron.wallet.business.confirm.core.pending.State;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.confirm.parambuilder.bean.ProfitParam;
import com.tron.wallet.business.message.TransactionMessage;
import com.tron.wallet.business.transfer.TransactionDetailActivity;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.bean.token.TransactionHistoryBean;
import com.tron.wallet.common.bean.token.TransactionInfoBean;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.rb.RB;
import com.tron.wallet.databinding.FgVoteIncomePendingBinding;
import com.tronlinkpro.wallet.R;
import java.util.List;
import org.slf4j.Marker;
import org.tron.common.utils.ByteArray;
import org.tron.common.utils.TransactionUtils;
import org.tron.protos.Protocol;
public class VoteIncomePendingFragment extends BaseFragment<ConfirmPendingPresenter, ConfirmPendingModel> implements ConfirmPendingContract.View {
    View RlFail;
    View RlSuccess;
    private BaseParam baseParam;
    private FgVoteIncomePendingBinding binding;
    View btnBackToVoteHome;
    View btnDone;
    View btnTransactionInfo;
    ImageView ivResult;
    View main;
    TextView tvResult;
    TextView tvResultHint;

    public static VoteIncomePendingFragment getInstance(State state, BaseParam baseParam, byte[] bArr, int i) {
        VoteIncomePendingFragment voteIncomePendingFragment = new VoteIncomePendingFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Keys.BaseParam, baseParam);
        bundle.putInt(Keys.UnsuccessfulTransactions, i);
        if (!ByteArray.isEmpty(bArr)) {
            bundle.putByteArray(Keys.RPCReturn, bArr);
        }
        bundle.putSerializable(Keys.StateKey, state);
        voteIncomePendingFragment.setArguments(bundle);
        return voteIncomePendingFragment;
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
                AnalyticsHelper.logEvent(AnalyticsHelper.ConfirmResult.CLICK_VOTE_INCOME_RESULT_SUCCEEDED_0);
                openVoteActivity();
            }
        });
        this.btnBackToVoteHome.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                openVoteActivity();
            }
        });
        this.btnTransactionInfo.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                AnalyticsHelper.logEvent(AnalyticsHelper.ConfirmResult.CLICK_VOTE_INCOME_RESULT_SUCCEEDED_1);
                openTransactionInfo();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        RxBus.getInstance().post(Event.BackToVoteHome, "");
                        RxBus.getInstance().post(RB.RB_ACCOUNTS, "");
                    }
                }, 800L);
                exit();
            }
        });
    }

    public void openTransactionInfo() {
        String str;
        Protocol.Transaction parseFrom;
        String str2 = "";
        BaseParam baseParam = this.baseParam;
        if (baseParam instanceof ProfitParam) {
            ProfitParam profitParam = (ProfitParam) baseParam;
            String toAddress = profitParam.getToAddress();
            String valueOf = String.valueOf(profitParam.getAmount());
            long j = 0;
            try {
                List<byte[]> bytes = this.baseParam.getTransactionBean().getBytes();
                parseFrom = Protocol.Transaction.parseFrom(bytes.get(bytes.size() - 1));
                str = TransactionUtils.getHash(parseFrom);
            } catch (Exception e) {
                e = e;
                str = "";
            }
            try {
                Protocol.Transaction.Contract contract = parseFrom.getRawData().getContract(0);
                j = parseFrom.getRawData().getTimestamp();
                str2 = contract.getType().name();
            } catch (Exception e2) {
                e = e2;
                SentryUtil.captureException(new Exception("OpenTransactionInfoException", e));
                LogUtils.e(e);
                TransactionHistoryBean transactionHistoryBean = new TransactionHistoryBean();
                transactionHistoryBean.setHash(str);
                transactionHistoryBean.setContract_ret("WithdrawBalanceContract");
                transactionHistoryBean.setTo(toAddress);
                transactionHistoryBean.setFrom(toAddress);
                transactionHistoryBean.setContract_ret(TransactionMessage.TYPE_SUCCESS);
                transactionHistoryBean.setMark(Marker.ANY_NON_NULL_MARKER);
                transactionHistoryBean.setAmount(BigDecimalUtils.toString(BigDecimalUtils.mul_(Double.valueOf(Math.pow(10.0d, 6.0d)), Double.valueOf(profitParam.getAmount()))));
                transactionHistoryBean.setBlock_timestamp(j);
                transactionHistoryBean.setContract_type(str2);
                TokenBean tokenBean = new TokenBean();
                tokenBean.setType(0);
                tokenBean.setPrecision(6);
                TransactionDetailActivity.start(getIContext(), transactionHistoryBean, tokenBean, valueOf);
            }
            TransactionHistoryBean transactionHistoryBean2 = new TransactionHistoryBean();
            transactionHistoryBean2.setHash(str);
            transactionHistoryBean2.setContract_ret("WithdrawBalanceContract");
            transactionHistoryBean2.setTo(toAddress);
            transactionHistoryBean2.setFrom(toAddress);
            transactionHistoryBean2.setContract_ret(TransactionMessage.TYPE_SUCCESS);
            transactionHistoryBean2.setMark(Marker.ANY_NON_NULL_MARKER);
            transactionHistoryBean2.setAmount(BigDecimalUtils.toString(BigDecimalUtils.mul_(Double.valueOf(Math.pow(10.0d, 6.0d)), Double.valueOf(profitParam.getAmount()))));
            transactionHistoryBean2.setBlock_timestamp(j);
            transactionHistoryBean2.setContract_type(str2);
            TokenBean tokenBean2 = new TokenBean();
            tokenBean2.setType(0);
            tokenBean2.setPrecision(6);
            TransactionDetailActivity.start(getIContext(), transactionHistoryBean2, tokenBean2, valueOf);
        }
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FgVoteIncomePendingBinding inflate = FgVoteIncomePendingBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
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
        this.btnTransactionInfo = this.binding.btnTransactionInfo;
    }

    @Override
    public void onSuccess(TransactionInfoBean transactionInfoBean, BaseParam baseParam) {
        this.baseParam = baseParam;
        setVisibility(this.RlSuccess, this.RlFail);
        this.tvResult.setText(R.string.vote_income_result_success);
        this.ivResult.setImageResource(R.mipmap.ic_unstake_result_success);
    }

    @Override
    public void onFail(BaseParam baseParam, int i, String str) {
        this.baseParam = baseParam;
        setVisibility(this.RlFail, this.RlSuccess);
        this.tvResult.setText(R.string.vote_income_result_fail);
        if (StringTronUtil.isEmpty(str)) {
            this.tvResultHint.setVisibility(View.GONE);
        } else {
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
