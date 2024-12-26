package com.tron.wallet.business.confirm.core.pending.fg.unstake;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.confirm.parambuilder.bean.FreezeUnFreezeParam;
import com.tron.wallet.business.stakev2.managementv2.ResourcesBean;
import com.tron.wallet.business.tronpower.management.ResourceManagementActivity;
import com.tron.wallet.business.vote.component.VoteMainActivity;
import com.tron.wallet.common.bean.ConfirmExtraText;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronSetting;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.SpannableUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.FragmentUnstakeResultSuccessBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.rpc.TronAPI;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import java.util.List;
import org.apache.commons.cli.HelpFormatter;
import org.tron.common.utils.TransactionUtils;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class UnStakeSuccessFragment extends BaseFragment<EmptyPresenter, EmptyModel> {
    private BaseParam baseParam;
    private FragmentUnstakeResultSuccessBinding binding;
    LinearLayout llResultContainer;
    LinearLayout llVoteContainer;
    RelativeLayout rlFirst;
    RelativeLayout rlSecond;
    TextView tvLeftFirst;
    TextView tvLeftSecond;
    TextView tvRightFirst;
    TextView tvRightSecond;
    TextView tvVoteInfo;

    public static UnStakeSuccessFragment getInstance(BaseParam baseParam) {
        UnStakeSuccessFragment unStakeSuccessFragment = new UnStakeSuccessFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("key_trans_param", baseParam);
        unStakeSuccessFragment.setArguments(bundle);
        return unStakeSuccessFragment;
    }

    @Override
    protected void processData() {
        if (getArguments() == null) {
            return;
        }
        this.baseParam = (BaseParam) getArguments().getParcelable("key_trans_param");
        if (checkNetEnvironment()) {
            this.llResultContainer.setVisibility(View.GONE);
            return;
        }
        try {
            requestResourcesInfo();
            updateReleasedResources();
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentUnstakeResultSuccessBinding inflate = FragmentUnstakeResultSuccessBinding.inflate(layoutInflater, viewGroup, false);
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
        this.tvVoteInfo = this.binding.tvVoteInfo;
        this.rlFirst = this.binding.rlResourceFirst;
        this.tvLeftFirst = this.binding.tvLeftFirst;
        this.tvRightFirst = this.binding.tvRightFirst;
        this.rlSecond = this.binding.rlResourceSecond;
        this.tvLeftSecond = this.binding.tvLeftSecond;
        this.tvRightSecond = this.binding.tvRightSecond;
        this.llResultContainer = this.binding.llResultContainer;
        this.llVoteContainer = this.binding.llVoteContainer;
    }

    public void setClick() {
        NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                if (id == R.id.btn_done_success) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.ConfirmResult.CLICK_UNSTAKE_RESULT_SUCCEEDED_0);
                    if (!TronSetting.stakeV2) {
                        ResourceManagementActivity.start(getContext(), false, ((FreezeUnFreezeParam) baseParam).getDoFreezeType() != 1 ? 0 : 1);
                    }
                    ((EmptyPresenter) mPresenter).mRxManager.post(Event.BroadcastSuccess2, "");
                    exit();
                } else if (id == R.id.btn_vote_again) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.ConfirmResult.CLICK_UNSTAKE_RESULT_SUCCEEDED_1);
                    Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
                    if (selectedPublicWallet == null) {
                        LogUtils.w("UnStakeSuccess", "UnState-to-vote failed cause selected wallet is null");
                        SentryUtil.captureException(new Exception("UnState-to-vote failed cause selected wallet is null"));
                        return;
                    }
                    VoteMainActivity.start(getContext(), null, false, selectedPublicWallet.getAddress(), selectedPublicWallet.getWalletName(), null);
                    ((EmptyPresenter) mPresenter).mRxManager.post(Event.BroadcastSuccess2, "");
                    exit();
                }
            }
        };
        this.binding.btnDoneSuccess.setOnClickListener(noDoubleClickListener);
        this.binding.btnVoteAgain.setOnClickListener(noDoubleClickListener);
    }

    private void requestResourcesInfo() {
        Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                lambda$requestResourcesInfo$0(observableEmitter);
            }
        }).compose(RxSchedulers.io_main()).subscribe(getObserver());
    }

    public void lambda$requestResourcesInfo$0(ObservableEmitter observableEmitter) throws Exception {
        observableEmitter.onNext(Long.valueOf(ResourcesBean.buildVotes(new ResourcesBean(), TronAPI.queryAccount(StringTronUtil.decode58Check(TransactionUtils.getOwner(Protocol.Transaction.parseFrom(this.baseParam.getTransactionBean().getBytes().get(0)))), false)).getAvailableVotingTps()));
        observableEmitter.onComplete();
    }

    private IObserver<Long> getObserver() {
        return new IObserver<>(new ICallback<Long>() {
            @Override
            public void onResponse(String str, Long l) {
                updateVoteInfo(l.longValue());
            }

            @Override
            public void onFailure(String str, String str2) {
                UnStakeSuccessFragment unStakeSuccessFragment = UnStakeSuccessFragment.this;
                unStakeSuccessFragment.toast(unStakeSuccessFragment.getString(R.string.network_unusable));
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                ((EmptyPresenter) mPresenter).addDisposable(disposable);
                onRequestBegin();
            }
        }, "UnStake#getReleasedResources");
    }

    public void onRequestBegin() {
        this.tvVoteInfo.setText(String.format(getString(R.string.unstake_result_clear_vote), HelpFormatter.DEFAULT_LONG_OPT_PREFIX));
    }

    public void updateVoteInfo(long j) {
        if (j <= 0) {
            return;
        }
        this.llVoteContainer.setVisibility(View.VISIBLE);
        String string = getString(TronSetting.stakeV2 ? R.string.unstake_result_clear_vote_v2 : R.string.unstake_result_clear_vote);
        String formatNumber6Truncate = StringTronUtil.formatNumber6Truncate(Long.valueOf(j));
        this.tvVoteInfo.setText(SpannableUtils.getTextStyleSpannable(String.format(string, formatNumber6Truncate), formatNumber6Truncate, 1));
    }

    private void updateReleasedResources() {
        BaseParam baseParam = this.baseParam;
        if (baseParam instanceof FreezeUnFreezeParam) {
            boolean isActives = baseParam.isActives();
            int i = ((FreezeUnFreezeParam) this.baseParam).releasedResourceTypeCount;
            List<ConfirmExtraText> textLists = this.baseParam.getTextLists();
            bindText(textLists.get(isActives ? 1 : 0), this.tvLeftFirst, this.tvRightFirst);
            if (i > 1) {
                bindText(textLists.get((isActives ? 1 : 0) + 1), this.tvLeftSecond, this.tvRightSecond);
            } else {
                this.rlSecond.setVisibility(View.GONE);
            }
        }
    }

    private void bindText(ConfirmExtraText confirmExtraText, TextView textView, TextView textView2) {
        int convertStringResource = convertStringResource(confirmExtraText.getLeft());
        if (convertStringResource > 0) {
            textView.setText(convertStringResource);
        }
        textView2.setText(confirmExtraText.getRight().replace("≈", ""));
    }

    private int convertStringResource(String str) {
        return ((FreezeUnFreezeParam) this.baseParam).doFreezeType == 0 ? R.string.unstake_result_released_energy : R.string.unstake_result_released_bandwidth;
    }

    private boolean checkNetEnvironment() {
        return !SpAPI.THIS.getCurIsMainChain() || IRequest.isShasta();
    }
}
