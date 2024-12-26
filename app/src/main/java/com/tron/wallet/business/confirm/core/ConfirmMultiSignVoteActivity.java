package com.tron.wallet.business.confirm.core;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.fragment.app.FragmentTransaction;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.fg.ConfirmVoteFragment;
import com.tron.wallet.business.confirm.parambuilder.BaseConfirmTransParamBuilder;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.confirm.parambuilder.bean.VoteParam;
import com.tron.wallet.business.confirm.vote.ConfirmMultiSignVoteContract;
import com.tron.wallet.business.confirm.vote.ConfirmMultiSignVoteModel;
import com.tron.wallet.business.confirm.vote.ConfirmMultiSignVotePresenter;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.common.bean.token.TransactionBean;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.databinding.AcConfirmNewBinding;
import com.tronlinkpro.wallet.R;
import java.util.HashMap;
import java.util.List;
import org.tron.protos.Protocol;
public class ConfirmMultiSignVoteActivity extends BaseActivity<ConfirmMultiSignVotePresenter, ConfirmMultiSignVoteModel> implements ConfirmMultiSignVoteContract.View {
    private FragmentTransaction beginTransaction;
    private AcConfirmNewBinding binding;
    FrameLayout confirm;
    FrameLayout containerBottom;
    FrameLayout containerTop;
    private BaseFragment fragment1;
    RelativeLayout mNoDataView;
    RelativeLayout mNoNetView;
    private BaseParam param;
    RelativeLayout root;

    public static void startActivity(Context context, BaseConfirmTransParamBuilder baseConfirmTransParamBuilder) {
        Intent intent = new Intent();
        intent.setClass(context, ConfirmMultiSignVoteActivity.class);
        baseConfirmTransParamBuilder.build(intent);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        AcConfirmNewBinding inflate = AcConfirmNewBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 0);
        mappingId();
        setClick();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.containerTop = this.binding.containerTop;
        this.containerBottom = this.binding.containerBottom;
        this.confirm = this.binding.confirm;
        this.root = this.binding.root;
        this.mNoNetView = this.binding.llNoData.getRoot();
        this.mNoDataView = this.binding.llNoData.getRoot();
    }

    @Override
    protected void processData() {
        showLoading(getString(R.string.loading));
        ((ConfirmMultiSignVotePresenter) this.mPresenter).addSome();
        ((ConfirmMultiSignVotePresenter) this.mPresenter).getData();
    }

    public void setV(View view, View view2, View view3) {
        view.setVisibility(View.VISIBLE);
        view2.setVisibility(View.GONE);
        view3.setVisibility(View.GONE);
    }

    @Override
    public void showLoading(boolean z) {
        if (z) {
            showLoadingDialog();
        } else {
            dismissLoadingDialog();
        }
    }

    @Override
    public void showOrHideNoData(boolean z) {
        this.mNoDataView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showOrHideNoNet(boolean z) {
        if (z) {
            this.mNoNetView.setVisibility(View.VISIBLE);
        } else {
            this.mNoNetView.setVisibility(View.GONE);
        }
    }

    public void setClick() {
        this.binding.llNoNet.getRoot().setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (view.getId() != R.id.ll_no_net) {
                    return;
                }
                mNoNetView.setVisibility(View.GONE);
                ConfirmMultiSignVoteActivity confirmMultiSignVoteActivity = ConfirmMultiSignVoteActivity.this;
                confirmMultiSignVoteActivity.showLoading(confirmMultiSignVoteActivity.getString(R.string.loading));
                ((ConfirmMultiSignVotePresenter) mPresenter).getData();
            }
        });
    }

    @Override
    public void gotoFragment(List<WitnessOutput.DataBean> list, HashMap<String, String> hashMap) {
        TransactionBean transactionBean;
        try {
            BaseParam baseParam = (BaseParam) getIntent().getParcelableExtra(BaseConfirmTransParamBuilder.INTENT_PARAM);
            this.param = baseParam;
            if (baseParam != null && (transactionBean = baseParam.getTransactionBean()) != null && transactionBean.getBytes() != null && transactionBean.getBytes().size() != 0) {
                Protocol.Transaction parseFrom = Protocol.Transaction.parseFrom(transactionBean.getBytes().get(0));
                if (parseFrom.getRawData() != null && parseFrom.getRawData().getContractCount() >= 1) {
                    Protocol.Transaction.Contract contract = parseFrom.getRawData().getContract(0);
                    this.fragment1 = null;
                    if (contract.getType() == Protocol.Transaction.Contract.ContractType.VoteWitnessContract) {
                        this.fragment1 = new ConfirmVoteFragment();
                        if (this.param instanceof VoteParam) {
                            TronConfig.currentPwdType = 9;
                            ((VoteParam) this.param).setShowVotingTetail(-1);
                            ((VoteParam) this.param).setNameWeightMap(hashMap);
                            ((VoteParam) this.param).setAddressWeightMap(new HashMap());
                            this.param.getConfirmExtraTitle().setConfirmTitle(getString(R.string.vote_detail));
                            ((ConfirmVoteFragment) this.fragment1).setParam((VoteParam) this.param);
                        }
                    }
                    FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
                    this.beginTransaction = beginTransaction;
                    BaseFragment baseFragment = this.fragment1;
                    if (baseFragment != null) {
                        beginTransaction.replace(R.id.container_top, baseFragment);
                        this.beginTransaction.disallowAddToBackStack();
                        this.beginTransaction.commit();
                        setV(this.containerTop, this.containerBottom, this.confirm);
                    }
                }
            }
        } catch (Exception e) {
            LogUtils.e(e);
            SentryUtil.captureException(e);
        }
    }
}
