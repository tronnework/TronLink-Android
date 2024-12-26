package com.tron.wallet.business.confirm.core.pending.fg.transfer;

import android.content.Intent;
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
import com.tron.wallet.business.confirm.parambuilder.bean.NFTParam;
import com.tron.wallet.business.maintab.MainTabActivity;
import com.tron.wallet.business.nft.nfttransactiondetail.NftTransactionDetailActivity;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.bean.token.TransactionHistoryBean;
import com.tron.wallet.common.bean.token.TransactionInfoBean;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.rb.RB;
import com.tron.wallet.databinding.FgTransferPendingBinding;
import com.tronlinkpro.wallet.R;
import java.util.List;
import org.apache.commons.cli.HelpFormatter;
import org.tron.common.utils.ByteArray;
import org.tron.common.utils.TransactionUtils;
import org.tron.protos.Protocol;
public class TransferPendingFragment extends BaseFragment<ConfirmPendingPresenter, ConfirmPendingModel> implements ConfirmPendingContract.View {
    View RlFail;
    View RlSuccess;
    private BaseParam baseParam;
    private FgTransferPendingBinding binding;
    View btnBackToHome;
    View btnDone;
    View btnTransactionInfo;
    ImageView ivResult;
    View main;
    TextView tvResult;
    TextView tvResultHint;

    public static TransferPendingFragment getInstance(State state, BaseParam baseParam, byte[] bArr, int i) {
        TransferPendingFragment transferPendingFragment = new TransferPendingFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Keys.BaseParam, baseParam);
        bundle.putInt(Keys.UnsuccessfulTransactions, i);
        if (!ByteArray.isEmpty(bArr)) {
            bundle.putByteArray(Keys.RPCReturn, bArr);
        }
        bundle.putSerializable(Keys.StateKey, state);
        transferPendingFragment.setArguments(bundle);
        return transferPendingFragment;
    }

    public void setClick() {
        this.binding.btnAgain.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (view.getId() != R.id.btn_again) {
                    return;
                }
                AnalyticsHelper.logEvent(AnalyticsHelper.ConfirmResult.CLICK_TRANSFER_RESULT_FAILED_0);
                mContext.finish();
            }
        });
    }

    public void openNFTTransactionInfoActivity() {
        String str;
        Protocol.Transaction parseFrom;
        String str2 = "";
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
            j = parseFrom.getRawData().getTimestamp();
            str2 = parseFrom.getRawData().getContract(0).getType().name();
        } catch (Exception e2) {
            e = e2;
            LogUtils.e(e);
            NFTParam nFTParam = (NFTParam) this.baseParam;
            TransactionHistoryBean transactionHistoryBean = new TransactionHistoryBean();
            transactionHistoryBean.setHash(str);
            transactionHistoryBean.setFrom(nFTParam.getFromAddress());
            transactionHistoryBean.setTo(nFTParam.getToAddress());
            TokenBean tokenBean = nFTParam.getTokenBean();
            tokenBean.setId(nFTParam.getTokenId());
            transactionHistoryBean.setEvent_type("Transfer");
            transactionHistoryBean.setMark(HelpFormatter.DEFAULT_OPT_PREFIX);
            transactionHistoryBean.setBlock_timestamp(j);
            transactionHistoryBean.setContract_type(str2);
            NftTransactionDetailActivity.start(getIContext(), transactionHistoryBean, tokenBean);
        }
        NFTParam nFTParam2 = (NFTParam) this.baseParam;
        TransactionHistoryBean transactionHistoryBean2 = new TransactionHistoryBean();
        transactionHistoryBean2.setHash(str);
        transactionHistoryBean2.setFrom(nFTParam2.getFromAddress());
        transactionHistoryBean2.setTo(nFTParam2.getToAddress());
        TokenBean tokenBean2 = nFTParam2.getTokenBean();
        tokenBean2.setId(nFTParam2.getTokenId());
        transactionHistoryBean2.setEvent_type("Transfer");
        transactionHistoryBean2.setMark(HelpFormatter.DEFAULT_OPT_PREFIX);
        transactionHistoryBean2.setBlock_timestamp(j);
        transactionHistoryBean2.setContract_type(str2);
        NftTransactionDetailActivity.start(getIContext(), transactionHistoryBean2, tokenBean2);
    }

    public void openTransactionInfoActivity() {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.confirm.core.pending.fg.transfer.TransferPendingFragment.openTransactionInfoActivity():void");
    }

    @Override
    protected void processData() {
        ((ConfirmPendingPresenter) this.mPresenter).initData();
        this.btnDone.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                AnalyticsHelper.logEvent(AnalyticsHelper.ConfirmResult.CLICK_TRANSFER_RESULT_SUCCEEDED_0);
                openMainTabActivity();
            }
        });
        this.btnBackToHome.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                AnalyticsHelper.logEvent(AnalyticsHelper.ConfirmResult.CLICK_TRANSFER_RESULT_FAILED_1);
                openMainTabActivity();
            }
        });
        this.btnTransactionInfo.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                AnalyticsHelper.logEvent(AnalyticsHelper.ConfirmResult.CLICK_TRANSFER_RESULT_SUCCEEDED_1);
                if (baseParam.getType() == 21) {
                    openNFTTransactionInfoActivity();
                } else {
                    openTransactionInfoActivity();
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        RxBus.getInstance().post(Event.BackToHome, "");
                        RxBus.getInstance().post(RB.RB_ACCOUNTS, "");
                    }
                }, 800L);
                mContext.finish();
            }
        });
    }

    public void openMainTabActivity() {
        Intent intent = new Intent(this.mContext, MainTabActivity.class);
        intent.setFlags(32768);
        startActivity(intent);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                RxBus.getInstance().post(Event.BackToHome, "");
                RxBus.getInstance().post(RB.RB_ACCOUNTS, "");
            }
        }, 800L);
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FgTransferPendingBinding inflate = FgTransferPendingBinding.inflate(layoutInflater, viewGroup, false);
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
        this.btnBackToHome = this.binding.btnBackToHome;
        this.btnTransactionInfo = this.binding.btnTransactionInfo;
    }

    @Override
    public void onSuccess(TransactionInfoBean transactionInfoBean, BaseParam baseParam) {
        this.baseParam = baseParam;
        setVisibility(this.RlSuccess, this.RlFail);
        this.tvResult.setText(R.string.transfer_result_success_1);
        this.tvResultHint.setText(R.string.transfer_result_success_2);
        this.ivResult.setImageResource(R.mipmap.ic_unstake_result_success);
        AnalyticsHelper.logEvent(AnalyticsHelper.ConfirmResult.ENTER_TRANSFER_RESULT_SUCCEEDED);
    }

    @Override
    public void onFail(BaseParam baseParam, int i, String str) {
        this.baseParam = baseParam;
        setVisibility(this.RlFail, this.RlSuccess);
        this.tvResult.setText(R.string.transfer_result_fail);
        if (StringTronUtil.isEmpty(str)) {
            this.tvResultHint.setVisibility(View.GONE);
        } else {
            this.tvResultHint.setText(str);
        }
        this.ivResult.setImageResource(R.mipmap.ic_unstake_result_fail_all);
        AnalyticsHelper.logEvent(AnalyticsHelper.ConfirmResult.ENTER_TRANSFER_RESULT_FAILED);
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
