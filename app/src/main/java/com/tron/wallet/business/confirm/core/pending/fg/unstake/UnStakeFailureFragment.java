package com.tron.wallet.business.confirm.core.pending.fg.unstake;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.maintab.MainTabActivity;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.config.TronSetting;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.databinding.FragmentUnstakeResultFailBinding;
import com.tronlinkpro.wallet.R;
public class UnStakeFailureFragment extends BaseFragment<EmptyPresenter, EmptyModel> {
    public static final int ACTION_DONE = 1;
    public static final int ACTION_REDO = 0;
    private BaseParam baseParam;
    private FragmentUnstakeResultFailBinding binding;
    private int failedCount;
    RelativeLayout rlFailAll;
    RelativeLayout rlFailPartial;
    TextView tvFailInfo;

    public static UnStakeFailureFragment getInstance(BaseParam baseParam, int i) {
        UnStakeFailureFragment unStakeFailureFragment = new UnStakeFailureFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("key_trans_param", baseParam);
        bundle.putInt("key_failed_count", i);
        unStakeFailureFragment.setArguments(bundle);
        return unStakeFailureFragment;
    }

    @Override
    protected void processData() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }
        this.baseParam = (BaseParam) arguments.getParcelable("key_trans_param");
        this.failedCount = arguments.getInt("key_failed_count");
        try {
            int size = this.baseParam.getTransactionBean().getBytes().size();
            boolean z = this.failedCount == size;
            if (z) {
                AnalyticsHelper.logEvent(AnalyticsHelper.ConfirmResult.CLICK_UNSTAKE_RESULT_FAILED);
            } else {
                AnalyticsHelper.logEvent(AnalyticsHelper.ConfirmResult.CLICK_UNSTAKE_RESULT_FAILED_PART);
            }
            int i = 8;
            this.rlFailAll.setVisibility(z ? View.VISIBLE : View.GONE);
            RelativeLayout relativeLayout = this.rlFailPartial;
            if (!z) {
                i = 0;
            }
            relativeLayout.setVisibility(i);
            if (z) {
                return;
            }
            this.tvFailInfo.setText(String.format(getString(R.string.unstake_result_fail_partial), Integer.valueOf(size), Integer.valueOf(size - this.failedCount), Integer.valueOf(this.failedCount)));
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentUnstakeResultFailBinding inflate = FragmentUnstakeResultFailBinding.inflate(layoutInflater, viewGroup, false);
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
        this.rlFailPartial = this.binding.rlFailPartial;
        this.rlFailAll = this.binding.rlFailAll;
        this.tvFailInfo = this.binding.tvFailInfo;
    }

    public void setClick() {
        NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                if (id == R.id.btn_redo) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.ConfirmResult.CLICK_UNSTAKE_RESULT_FAILED_0);
                    ((EmptyPresenter) mPresenter).mRxManager.post(Event.BroadcastFail, 0);
                    exit();
                } else if (id == R.id.btn_back_home) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.ConfirmResult.CLICK_UNSTAKE_RESULT_FAILED_1);
                    if (!TronSetting.stakeV2) {
                        toMain();
                        ((EmptyPresenter) mPresenter).mRxManager.post(Event.BroadcastFail, 1);
                    } else {
                        ((EmptyPresenter) mPresenter).mRxManager.post(Event.BroadcastSuccess, 1);
                    }
                    exit();
                }
            }
        };
        this.binding.btnRedo.setOnClickListener(noDoubleClickListener);
        this.binding.btnBackHome.setOnClickListener(noDoubleClickListener);
    }

    public void toMain() {
        Intent intent = new Intent(this.mContext, MainTabActivity.class);
        intent.setFlags(67108864);
        if (getArguments() != null && getArguments().getBoolean(TronConfig.WALLET_DATA2, false)) {
            intent.putExtra(TronConfig.SHIELD_IS_TRC20, true);
        }
        go(intent);
    }
}
