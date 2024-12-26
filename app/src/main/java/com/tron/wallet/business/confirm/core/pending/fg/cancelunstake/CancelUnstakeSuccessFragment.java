package com.tron.wallet.business.confirm.core.pending.fg.cancelunstake;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.RxBus;
import com.tron.wallet.TronApplication;
import com.tron.wallet.business.confirm.core.pending.fg.cancelunstake.CancelUnstakeSuccessContract;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.confirm.parambuilder.bean.CancelUnStakeParam;
import com.tron.wallet.business.mutil.bean.MultiSignPermissionData;
import com.tron.wallet.business.stakev2.managementv2.ResourceManagementV2Activity;
import com.tron.wallet.business.stakev2.stake.StakeHomeActivity;
import com.tron.wallet.business.tronpower.management.ResourceManagementActivity;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.business.vote.fastvote.VoteWitnessBean;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronSetting;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.DataStatHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.NumUtils;
import com.tron.wallet.common.utils.PopupWindowUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.common.utils.VoteAprCalculator;
import com.tron.wallet.databinding.FragmentCancelUnstakeSuccessBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.tron.protos.Protocol;
public class CancelUnstakeSuccessFragment extends BaseFragment<CancelUnstakeSuccessPresenter, CancelUnstakeSuccessModel> implements CancelUnstakeSuccessContract.View {
    private BaseParam baseParam;
    private FragmentCancelUnstakeSuccessBinding binding;
    Button btnSuccess;
    Button btnVoteAgain;
    View divider;
    View divider2;
    private String formatApr;
    ImageView ivAprTip;
    View ivDivider;
    LinearLayout liVoteResource;
    RelativeLayout liVotes;
    private NumberFormat numberFormat;
    RecyclerView recyclerView;
    NoNetView reloadView;
    RelativeLayout rlFirst;
    RelativeLayout rlLoading;
    RelativeLayout rlReload;
    RelativeLayout rlSecond;
    RelativeLayout rlVoteContent;
    TextView tvAprValue;
    TextView tvLeftFirst;
    TextView tvLeftSecond;
    TextView tvNextToProfitTip;
    TextView tvOtherVotes;
    TextView tvRightFirst;
    TextView tvRightSecond;
    TextView tvRightToVote;
    TextView tvToVoteBelow;

    @Override
    public String getFormatApr() {
        return this.formatApr;
    }

    @Override
    public RecyclerView getRecyclerView() {
        return this.recyclerView;
    }

    @Override
    public void onFail() {
    }

    @Override
    public void onSuccess(long j, ArrayList<WitnessOutput.DataBean> arrayList, Protocol.Account account, MultiSignPermissionData multiSignPermissionData) {
    }

    public static CancelUnstakeSuccessFragment getInstance(BaseParam baseParam) {
        CancelUnstakeSuccessFragment cancelUnstakeSuccessFragment = new CancelUnstakeSuccessFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("key_trans_param", baseParam);
        cancelUnstakeSuccessFragment.setArguments(bundle);
        return cancelUnstakeSuccessFragment;
    }

    @Override
    protected void processData() {
        String format;
        if (getArguments() == null) {
            return;
        }
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        this.numberFormat = numberInstance;
        numberInstance.setMaximumFractionDigits(0);
        BaseParam baseParam = (BaseParam) getArguments().getParcelable("key_trans_param");
        this.baseParam = baseParam;
        if (baseParam instanceof CancelUnStakeParam) {
            CancelUnStakeParam cancelUnStakeParam = (CancelUnStakeParam) baseParam;
            double amount = cancelUnStakeParam.getAmount();
            if (cancelUnStakeParam.getEnergy() <= 0 || cancelUnStakeParam.getBandwidth() <= 0) {
                format = cancelUnStakeParam.getEnergy() > 0 ? String.format("≈%s %s", StringTronUtil.formatNumber6TruncatePlainScientific(cancelUnStakeParam.getEnergy()), StringTronUtil.getResString(R.string.energy)) : String.format("≈%s %s", StringTronUtil.formatNumber6TruncatePlainScientific(cancelUnStakeParam.getBandwidth()), StringTronUtil.getResString(R.string.bandwidth));
            } else {
                format = String.format("%s %s + %s %s", NumUtils.numFormatToK(cancelUnStakeParam.getEnergy()), StringTronUtil.getResString(R.string.energy), NumUtils.numFormatToK(cancelUnStakeParam.getBandwidth()), StringTronUtil.getResString(R.string.bandwidth));
            }
            this.tvRightFirst.setText(format);
            this.tvRightToVote.setText(this.numberFormat.format(amount));
            if (cancelUnStakeParam.getWithDrawAmount() <= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                this.rlSecond.setVisibility(View.GONE);
                this.divider2.setVisibility(View.GONE);
            } else {
                this.tvRightSecond.setText(StringTronUtil.formatNumber6TruncatePlainScientific(cancelUnStakeParam.getWithDrawAmount()));
            }
        }
        AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.ConfirmResult.ENTER_STAKE_RESULT_SUCCEEDED);
        showVoteLoading();
        this.reloadView.setOnReloadClickListenerWithOutAnimation(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showVoteLoading();
            }
        });
        TouchDelegateUtils.expandViewTouchDelegate(this.ivAprTip, 10);
    }

    @Override
    public void showVoteLoading() {
        this.rlLoading.setVisibility(View.VISIBLE);
        this.rlReload.setVisibility(View.GONE);
        this.rlVoteContent.setVisibility(View.GONE);
        this.btnVoteAgain.setEnabled(false);
        ((CancelUnstakeSuccessPresenter) this.mPresenter).initVoteData();
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentCancelUnstakeSuccessBinding inflate = FragmentCancelUnstakeSuccessBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        onClick();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    public void mappingId() {
        this.rlFirst = this.binding.rlResourceFirst;
        this.tvLeftFirst = this.binding.tvLeftFirst;
        this.tvRightFirst = this.binding.tvRightFirst;
        this.liVotes = this.binding.rlReVotes;
        this.liVoteResource = this.binding.liVoteResource;
        this.rlSecond = this.binding.rlResourceSecond;
        this.tvLeftSecond = this.binding.tvLeftSecond;
        this.tvRightToVote = this.binding.tvRightReVotes;
        this.ivDivider = this.binding.divider1;
        this.divider = this.binding.divider;
        this.divider2 = this.binding.divider2;
        this.tvRightSecond = this.binding.tvRightSecond;
        this.tvNextToProfitTip = this.binding.tvNextToProfit;
        this.tvToVoteBelow = this.binding.tvToVoteBelow;
        this.btnVoteAgain = this.binding.btnVoteAgain;
        this.btnSuccess = this.binding.btnDoneSuccess;
        this.reloadView = this.binding.reload;
        this.rlReload = this.binding.rlReload;
        this.rlVoteContent = this.binding.rlVoteContent;
        this.recyclerView = this.binding.recyclerView;
        this.tvAprValue = this.binding.tvAprValue;
        this.ivAprTip = this.binding.ivAprTip;
        this.tvOtherVotes = this.binding.tvOtherVotes;
        this.rlLoading = this.binding.rlLoading;
    }

    public void onClick() {
        NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                if (id != R.id.btn_done_success) {
                    if (id == R.id.iv_apr_tip) {
                        PopupWindowUtil.showPopupText(mContext, StringTronUtil.getResString(R.string.stake_v2_result_vote_estimate_apr_tips), ivAprTip, false);
                        return;
                    } else if (id == R.id.tv_other_votes) {
                        AnalyticsHelper.logEvent(AnalyticsHelper.ConfirmResult.CLICK_STAKE_RESULT_SUCCEEDED_CHOOSE_OTHER_SRs);
                        ((CancelUnstakeSuccessPresenter) mPresenter).startOtherVote();
                        return;
                    } else if (id == R.id.btn_vote_again) {
                        AnalyticsHelper.logEvent(AnalyticsHelper.ConfirmResult.CLICK_STAKE_RESULT_SUCCEEDED_VOTE_TO_PROFIT);
                        ((CancelUnstakeSuccessPresenter) mPresenter).VoteToProfit();
                        return;
                    } else {
                        return;
                    }
                }
                AnalyticsHelper.logEvent(AnalyticsHelper.ConfirmResult.CLICK_STAKE_RESULT_SUCCEEDED_0);
                AnalyticsHelper.logEvent(AnalyticsHelper.ConfirmResult.CLICK_STAKE_RESULT_SUCCEEDED_GIVE_UP_PROFIT);
                CancelUnStakeParam cancelUnStakeParam = (CancelUnStakeParam) baseParam;
                if (!TronSetting.stakeV2) {
                    ResourceManagementActivity.start(getContext(), false, 1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            RxBus.getInstance().post(Event.BroadcastSuccess, "");
                        }
                    }, 800L);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            RxBus.getInstance().post(Event.BroadcastSuccess, "");
                        }
                    }, 800L);
                    TronApplication tronApplication = (TronApplication) AppContextUtil.getmApplication();
                    if (!tronApplication.isResourceHomeIn()) {
                        StakeHomeActivity.start(mContext, null, false, DataStatHelper.StatAction.Stake, WalletUtils.getSelectedWallet().getAddress(), null);
                    } else {
                        ResourceManagementV2Activity.start(getContext(), 0);
                        tronApplication.destroyStakeHomeActivity();
                    }
                }
                exit();
            }
        };
        this.binding.getRoot().findViewById(R.id.btn_done_success).setOnClickListener(noDoubleClickListener);
        this.binding.getRoot().findViewById(R.id.tv_other_votes).setOnClickListener(noDoubleClickListener);
        this.binding.getRoot().findViewById(R.id.iv_apr_tip).setOnClickListener(noDoubleClickListener);
        this.binding.getRoot().findViewById(R.id.btn_vote_again).setOnClickListener(noDoubleClickListener);
    }

    @Override
    public void updateVoteApr(List<VoteWitnessBean> list, long j) {
        this.formatApr = BigDecimalUtils.toBigDecimal(VoteAprCalculator.calculateApr(list, j)).toPlainString();
        this.tvAprValue.post(new Runnable() {
            @Override
            public final void run() {
                lambda$updateVoteApr$0();
            }
        });
    }

    public void lambda$updateVoteApr$0() {
        if (!isAdded() || isDetached()) {
            return;
        }
        this.tvAprValue.setText(String.format(getString(R.string.stake_v2_result_vote_estimate_apr), VoteAprCalculator.formatAprPercent(Double.valueOf(this.formatApr).doubleValue())));
    }

    @Override
    public void showLoadFailed() {
        this.btnVoteAgain.setEnabled(false);
        this.rlReload.setVisibility(View.VISIBLE);
        this.rlLoading.setVisibility(View.GONE);
        this.rlVoteContent.setVisibility(View.GONE);
        this.reloadView.setReloadMarginTop(20.0f);
    }

    @Override
    public void showVotes() {
        this.btnVoteAgain.setEnabled(true);
        this.rlLoading.setVisibility(View.GONE);
        this.rlReload.setVisibility(View.GONE);
        this.rlVoteContent.setVisibility(View.VISIBLE);
    }
}
