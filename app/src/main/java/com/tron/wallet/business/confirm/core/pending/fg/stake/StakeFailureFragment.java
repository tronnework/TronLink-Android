package com.tron.wallet.business.confirm.core.pending.fg.stake;

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
import com.tron.tron_base.frame.utils.RxBus;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.maintab.MainTabActivity;
import com.tron.wallet.business.transfer.TokenDetailActivity;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.config.CommonBundleKeys;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.config.TronSetting;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.databinding.FragmentStakeFailBinding;
import com.tronlinkpro.wallet.R;
public class StakeFailureFragment extends BaseFragment<EmptyPresenter, EmptyModel> {
    private BaseParam baseParam;
    private FragmentStakeFailBinding binding;
    RelativeLayout rlFailPartial;
    TextView tvFailInfo;

    public static StakeFailureFragment getInstance(BaseParam baseParam, int i, String str) {
        StakeFailureFragment stakeFailureFragment = new StakeFailureFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("key_trans_param", baseParam);
        bundle.putString(CommonBundleKeys.StakeResult.KEY_MSG, str);
        stakeFailureFragment.setArguments(bundle);
        return stakeFailureFragment;
    }

    @Override
    protected void processData() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }
        this.baseParam = (BaseParam) arguments.getParcelable("key_trans_param");
        this.tvFailInfo.setText(arguments.getString(CommonBundleKeys.StakeResult.KEY_MSG));
        AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.ConfirmResult.ENTER_STAKE_RESULT_FAILED);
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentStakeFailBinding inflate = FragmentStakeFailBinding.inflate(layoutInflater, viewGroup, false);
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
        this.tvFailInfo = this.binding.tvFailInfo;
    }

    public void setClick() {
        NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                if (id == R.id.btn_again) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.ConfirmResult.CLICK_STAKE_RESULT_FAILED_0);
                    exit();
                } else if (id == R.id.btn_back_home) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.ConfirmResult.CLICK_STAKE_RESULT_FAILED_1);
                    if (!TronSetting.stakeV2) {
                        toMain();
                    } else {
                        RxBus.getInstance().post(Event.BroadcastSuccess, "");
                    }
                    exit();
                }
            }
        };
        this.binding.btnAgain.setOnClickListener(noDoubleClickListener);
        this.binding.btnBackHome.setOnClickListener(noDoubleClickListener);
    }

    private void enterTokenDetail() {
        TokenBean tokenBean = new TokenBean();
        tokenBean.setName("TRX");
        tokenBean.type = 0;
        TokenDetailActivity.start(getContext(), tokenBean);
    }

    public void toMain() {
        Intent intent = new Intent(this.mContext, MainTabActivity.class);
        intent.setFlags(67108864);
        if (getArguments() != null && getArguments().getBoolean(TronConfig.WALLET_DATA2, false)) {
            intent.putExtra(TronConfig.SHIELD_IS_TRC20, true);
        }
        go(intent);
        exit();
    }
}
