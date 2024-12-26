package com.tron.wallet.business.confirm.core.pending.fg.resourcedelegate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.tron.wallet.common.bean.token.TransactionInfoBean;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.databinding.FgDelegatePendingBinding;
import com.tronlinkpro.wallet.R;
import org.tron.common.utils.ByteArray;
public class WithdrawPendingFragment extends BaseFragment<ConfirmPendingPresenter, ConfirmPendingModel> implements ConfirmPendingContract.View {
    private FgDelegatePendingBinding binding;
    Button btAgain;
    Button btBack;
    Button btDone;
    ImageView ivResult;
    View llRoot;
    TextView tvInfo;
    TextView tvResult;

    public static WithdrawPendingFragment getInstance(State state, BaseParam baseParam, byte[] bArr, int i) {
        WithdrawPendingFragment withdrawPendingFragment = new WithdrawPendingFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Keys.BaseParam, baseParam);
        if (!ByteArray.isEmpty(bArr)) {
            bundle.putByteArray(Keys.RPCReturn, bArr);
        }
        bundle.putSerializable(Keys.StateKey, state);
        withdrawPendingFragment.setArguments(bundle);
        return withdrawPendingFragment;
    }

    @Override
    public void onSuccess(TransactionInfoBean transactionInfoBean, BaseParam baseParam) {
        this.llRoot.setVisibility(View.VISIBLE);
        this.ivResult.setImageResource(R.mipmap.ic_unstake_result_success);
        this.tvResult.setText(R.string.withdraw_success);
        this.tvInfo.setVisibility(View.GONE);
        this.btBack.setVisibility(View.GONE);
        this.btAgain.setVisibility(View.GONE);
        this.btDone.setVisibility(View.VISIBLE);
        this.btDone.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                exit();
            }
        });
    }

    @Override
    public void onFail(BaseParam baseParam, int i, String str) {
        this.llRoot.setVisibility(View.VISIBLE);
        this.ivResult.setImageResource(R.mipmap.ic_unstake_result_fail_all);
        this.tvResult.setText(R.string.withdraw_fail);
        this.tvInfo.setVisibility(View.VISIBLE);
        this.tvInfo.setVisibility(View.VISIBLE);
        this.tvInfo.setText(str);
        this.btDone.setVisibility(View.GONE);
        this.btBack.setVisibility(View.VISIBLE);
        this.btBack.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                exit();
            }
        });
        this.btAgain.setVisibility(View.VISIBLE);
        this.btAgain.setText(R.string.btn_result_again);
        this.btAgain.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                exit();
            }
        });
    }

    @Override
    public Bundle getIArguments() {
        return getArguments();
    }

    @Override
    protected void processData() {
        ((ConfirmPendingPresenter) this.mPresenter).initData();
        getArguments();
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FgDelegatePendingBinding inflate = FgDelegatePendingBinding.inflate(layoutInflater, viewGroup, false);
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
        this.llRoot = this.binding.llRoot;
        this.ivResult = this.binding.ivResult;
        this.tvResult = this.binding.tvResult;
        this.tvInfo = this.binding.tvInfo;
        this.btAgain = this.binding.btnAgain;
        this.btBack = this.binding.btnBackHome;
        this.btDone = this.binding.btnDone;
    }
}
