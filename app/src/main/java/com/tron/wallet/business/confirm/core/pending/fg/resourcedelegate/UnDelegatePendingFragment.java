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
import com.tron.tron_base.frame.utils.RxBus;
import com.tron.wallet.business.confirm.core.pending.ConfirmPendingContract;
import com.tron.wallet.business.confirm.core.pending.ConfirmPendingModel;
import com.tron.wallet.business.confirm.core.pending.ConfirmPendingPresenter;
import com.tron.wallet.business.confirm.core.pending.Keys;
import com.tron.wallet.business.confirm.core.pending.State;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.confirm.parambuilder.bean.DelegatedResourceParam;
import com.tron.wallet.business.stakev2.managementv2.ResourceManagementV2Activity;
import com.tron.wallet.common.bean.token.TransactionInfoBean;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.SpannableUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.FgDelegatePendingBinding;
import com.tronlinkpro.wallet.R;
import org.tron.common.utils.ByteArray;
public class UnDelegatePendingFragment extends BaseFragment<ConfirmPendingPresenter, ConfirmPendingModel> implements ConfirmPendingContract.View {
    private FgDelegatePendingBinding binding;
    Button btAgain;
    Button btBack;
    Button btDone;
    ImageView ivResult;
    View llDetail;
    View llRoot;
    TextView tvInfo;
    TextView tvLeftFirst;
    TextView tvLeftSecond;
    TextView tvResult;
    TextView tvRightFirst;
    TextView tvRightSecond;

    public static UnDelegatePendingFragment getInstance(State state, BaseParam baseParam, byte[] bArr, int i) {
        UnDelegatePendingFragment unDelegatePendingFragment = new UnDelegatePendingFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Keys.BaseParam, baseParam);
        if (!ByteArray.isEmpty(bArr)) {
            bundle.putByteArray(Keys.RPCReturn, bArr);
        }
        bundle.putSerializable(Keys.StateKey, state);
        unDelegatePendingFragment.setArguments(bundle);
        return unDelegatePendingFragment;
    }

    @Override
    public void onSuccess(TransactionInfoBean transactionInfoBean, final BaseParam baseParam) {
        String resString;
        this.llRoot.setVisibility(View.VISIBLE);
        this.ivResult.setImageResource(R.mipmap.ic_unstake_result_success);
        this.tvResult.setText(R.string.resource_reclaim_success);
        this.tvInfo.setVisibility(View.GONE);
        DelegatedResourceParam delegatedResourceParam = (DelegatedResourceParam) baseParam;
        this.llDetail.setVisibility(View.VISIBLE);
        this.tvLeftFirst.setText(R.string.resource_reclaim_type);
        TextView textView = this.tvRightFirst;
        if (delegatedResourceParam.getResourceType() == 0) {
            resString = StringTronUtil.getResString(R.string.bandwidth);
        } else {
            resString = StringTronUtil.getResString(R.string.energy);
        }
        textView.setText(resString);
        this.tvLeftSecond.setText(R.string.reclaim_to_address);
        this.tvRightSecond.setText(SpannableUtils.getAddressSpannable(delegatedResourceParam.getExtraAddress(), getResources().getColor(R.color.black_6d)));
        this.btBack.setVisibility(View.GONE);
        this.btAgain.setVisibility(View.GONE);
        this.btDone.setVisibility(View.VISIBLE);
        this.btDone.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                ResourceManagementV2Activity.start(getContext(), ((DelegatedResourceParam) baseParam).getResourceType() == 0 ? 1 : 0);
                RxBus.getInstance().post(Event.BroadcastSuccess, "");
                exit();
            }
        });
    }

    @Override
    public void onFail(final BaseParam baseParam, int i, String str) {
        this.llRoot.setVisibility(View.VISIBLE);
        this.ivResult.setImageResource(R.mipmap.ic_unstake_result_fail_all);
        this.tvResult.setText(R.string.resource_reclaim_fail);
        this.tvInfo.setVisibility(View.VISIBLE);
        this.tvInfo.setText(str);
        this.btDone.setVisibility(View.GONE);
        this.btBack.setVisibility(View.VISIBLE);
        this.btBack.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                ResourceManagementV2Activity.start(getContext(), ((DelegatedResourceParam) baseParam).getResourceType() == 0 ? 1 : 0);
                RxBus.getInstance().post(Event.BroadcastSuccess, "");
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
        this.llDetail = this.binding.llDetail;
        this.tvLeftFirst = this.binding.tvLeftFirst;
        this.tvRightFirst = this.binding.tvRightFirst;
        this.tvLeftSecond = this.binding.tvLeftSecond;
        this.tvRightSecond = this.binding.tvRightSecond;
    }
}
