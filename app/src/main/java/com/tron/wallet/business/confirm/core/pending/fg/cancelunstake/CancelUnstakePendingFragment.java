package com.tron.wallet.business.confirm.core.pending.fg.cancelunstake;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.core.pending.ConfirmPendingContract;
import com.tron.wallet.business.confirm.core.pending.ConfirmPendingModel;
import com.tron.wallet.business.confirm.core.pending.ConfirmPendingPresenter;
import com.tron.wallet.business.confirm.core.pending.Keys;
import com.tron.wallet.business.confirm.core.pending.State;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.common.bean.token.TransactionInfoBean;
import org.tron.common.utils.ByteArray;
public class CancelUnstakePendingFragment extends BaseFragment<ConfirmPendingPresenter, ConfirmPendingModel> implements ConfirmPendingContract.View {
    private static final int containerViewId = 100386;
    protected BaseParam baseParam;
    protected State state;

    public static CancelUnstakePendingFragment getInstance(State state, BaseParam baseParam, byte[] bArr, int i) {
        CancelUnstakePendingFragment cancelUnstakePendingFragment = new CancelUnstakePendingFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Keys.BaseParam, baseParam);
        bundle.putInt(Keys.UnsuccessfulTransactions, i);
        if (!ByteArray.isEmpty(bArr)) {
            bundle.putByteArray(Keys.RPCReturn, bArr);
        }
        bundle.putSerializable(Keys.StateKey, state);
        cancelUnstakePendingFragment.setArguments(bundle);
        return cancelUnstakePendingFragment;
    }

    @Override
    public void onSuccess(TransactionInfoBean transactionInfoBean, BaseParam baseParam) {
        replaceFragment(CancelUnstakeSuccessFragment.getInstance(baseParam));
    }

    @Override
    public void onFail(BaseParam baseParam, int i, String str) {
        replaceFragment(CancelUnstakeFailureFragment.getInstance(baseParam, i, str));
    }

    @Override
    public Bundle getIArguments() {
        return getArguments();
    }

    @Override
    protected void processData() {
        ((ConfirmPendingPresenter) this.mPresenter).initData();
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentContainerView fragmentContainerView = new FragmentContainerView(layoutInflater.getContext());
        fragmentContainerView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        fragmentContainerView.setId(containerViewId);
        return fragmentContainerView;
    }

    private void replaceFragment(Fragment fragment) {
        try {
            getChildFragmentManager().beginTransaction().replace(containerViewId, fragment).commitAllowingStateLoss();
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }
}
