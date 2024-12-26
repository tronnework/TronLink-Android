package com.tron.wallet.business.confirm.core.pending.fg.cancelunstake;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.stakev2.stake.StakeHomeActivity;
import com.tron.wallet.common.config.CommonBundleKeys;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronSetting;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.DataStatHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.databinding.FragmentCancelUnstakeFailBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
public class CancelUnstakeFailureFragment extends BaseFragment<EmptyPresenter, EmptyModel> {
    private BaseParam baseParam;
    private FragmentCancelUnstakeFailBinding binding;
    RelativeLayout rlFailPartial;
    TextView tvFailInfo;

    public static CancelUnstakeFailureFragment getInstance(BaseParam baseParam, int i, String str) {
        CancelUnstakeFailureFragment cancelUnstakeFailureFragment = new CancelUnstakeFailureFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("key_trans_param", baseParam);
        bundle.putString(CommonBundleKeys.StakeResult.KEY_MSG, str);
        cancelUnstakeFailureFragment.setArguments(bundle);
        return cancelUnstakeFailureFragment;
    }

    @Override
    protected void processData() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }
        new RxManager().post(Event.CANCEL_UNSTAKE_FAILED, "");
        this.baseParam = (BaseParam) arguments.getParcelable("key_trans_param");
        this.tvFailInfo.setText(arguments.getString(CommonBundleKeys.StakeResult.KEY_MSG));
        AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.ConfirmResult.ENTER_STAKE_RESULT_FAILED);
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentCancelUnstakeFailBinding inflate = FragmentCancelUnstakeFailBinding.inflate(layoutInflater, viewGroup, false);
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
        this.binding.btnBackHome.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (view.getId() == R.id.btn_back_home) {
                    if (!TronSetting.stakeV2) {
                        toStakeMain();
                    }
                    exit();
                }
            }
        });
    }

    public void toStakeMain() {
        StakeHomeActivity.start(this.mContext, null, false, DataStatHelper.StatAction.Stake, WalletUtils.getSelectedWallet().getAddress(), null);
    }
}
