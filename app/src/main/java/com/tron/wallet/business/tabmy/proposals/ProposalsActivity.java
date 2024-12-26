package com.tron.wallet.business.tabmy.proposals;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.wallet.business.tabmy.proposals.ProposalsContract;
import com.tron.wallet.common.components.ptr.PtrFrameLayout;
import com.tron.wallet.common.components.ptr.iptr.HomePtrClassicFrameLayout;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.databinding.AcProposalsBinding;
import com.tronlinkpro.wallet.R;
public class ProposalsActivity extends BaseActivity<ProposalsPresenter, ProposalsModel> implements ProposalsContract.View {
    private static final String TAG = "ProposalsActivity";
    private AcProposalsBinding binding;
    ImageView ivLeft;
    LinearLayout llLeft;
    HomePtrClassicFrameLayout rcFrame;
    RecyclerView rcList;
    TextView tvCreatePropose;
    TextView tvTitle;

    @Override
    public PtrFrameLayout getRcFrame() {
        return this.rcFrame;
    }

    @Override
    public RecyclerView getRcList() {
        return this.rcList;
    }

    @Override
    protected void setLayout() {
        AcProposalsBinding inflate = AcProposalsBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        LinearLayout root = inflate.getRoot();
        mappingId();
        setClick();
        setView(root, 4);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.ivLeft = this.binding.header.ivLeft;
        this.llLeft = this.binding.header.llLeft;
        this.tvTitle = this.binding.header.tvTitle;
        this.tvCreatePropose = this.binding.header.tvCreatePropose;
        this.rcList = this.binding.rcList;
        this.rcFrame = this.binding.rcFrame;
    }

    @Override
    protected void processData() {
        showLoadingDialog();
        this.tvCreatePropose.setText(R.string.propose);
        this.tvTitle.setText(R.string.committee_proposals);
        ((ProposalsPresenter) this.mPresenter).addSome();
        ((ProposalsPresenter) this.mPresenter).getData();
    }

    public void setClick() {
        NoDoubleClickListener2 noDoubleClickListener2 = new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                if (id == R.id.ll_left) {
                    finish();
                } else if (id != R.id.tv_create_propose) {
                } else {
                    ((ProposalsPresenter) mPresenter).makeProposal();
                }
            }
        };
        this.binding.header.llLeft.setOnClickListener(noDoubleClickListener2);
        this.binding.header.tvCreatePropose.setOnClickListener(noDoubleClickListener2);
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        ((ProposalsPresenter) this.mPresenter).onActivityResult(i, i2, intent);
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        finish();
    }

    @Override
    public void showLoading(boolean z) {
        if (z) {
            showLoadingDialog(getString(R.string.loading));
        } else {
            dismissLoadingDialog();
        }
    }
}
