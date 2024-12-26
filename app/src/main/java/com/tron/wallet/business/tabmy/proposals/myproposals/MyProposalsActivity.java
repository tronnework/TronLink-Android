package com.tron.wallet.business.tabmy.proposals.myproposals;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.wallet.business.tabmy.proposals.myproposals.MyProposalsContract;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.databinding.AcRecyclerviewProposalsBinding;
import com.tronlinkpro.wallet.R;
import java.io.Serializable;
import java.util.List;
import org.tron.protos.Protocol;
public class MyProposalsActivity extends BaseActivity<MyProposalsPresenter, MyProposalsModel> implements MyProposalsContract.View {
    public static final String TYPE_FORM_APPROVED_PROPOSALS = "type_from_approved_proposals";
    public static final String TYPE_FORM_MY_PROPOSALS = "type_from_my_proposals";
    private AcRecyclerviewProposalsBinding binding;
    private String currentSelectAddress;
    ImageView ivShasta;
    LinearLayout llNodata;
    private List<WitnessOutput.DataBean> mWitnessList;
    private List<Protocol.Proposal> proposalsList;
    RecyclerView rcOriposals;
    TextView tvMakeProposal;
    TextView tvNodataContent;
    private String typeFrom;

    @Override
    public String getFromType() {
        return this.typeFrom;
    }

    @Override
    public RecyclerView getRcList() {
        return this.rcOriposals;
    }

    @Override
    public String getSelectAddress() {
        return this.currentSelectAddress;
    }

    @Override
    protected void setLayout() {
        AcRecyclerviewProposalsBinding inflate = AcRecyclerviewProposalsBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        setView(root, 3);
        Intent intent = getIntent();
        if (intent != null) {
            String stringExtra = intent.getStringExtra("typeFrom");
            this.typeFrom = stringExtra;
            if (stringExtra.equals(TYPE_FORM_MY_PROPOSALS)) {
                setHeaderBar(getString(R.string.my_proposals));
            } else {
                setHeaderBar(getString(R.string.approved_proposals));
            }
            this.currentSelectAddress = intent.getStringExtra("currentAddress");
            this.proposalsList = (List) intent.getSerializableExtra("proposals_list");
            this.mWitnessList = (List) intent.getSerializableExtra("witness_list");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.rcOriposals = this.binding.rcOriposals;
        this.ivShasta = this.binding.ivShasta;
        this.tvNodataContent = this.binding.tvNodataContent;
        this.llNodata = this.binding.llNodata;
        this.tvMakeProposal = this.binding.tvMakeProposal;
    }

    @Override
    protected void processData() {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.rcOriposals.getLayoutParams();
        if (this.typeFrom.equals(TYPE_FORM_MY_PROPOSALS)) {
            layoutParams.bottomMargin = 70;
            this.tvMakeProposal.setVisibility(View.VISIBLE);
        } else {
            layoutParams.bottomMargin = 10;
            this.tvMakeProposal.setVisibility(View.GONE);
        }
        this.rcOriposals.setLayoutParams(layoutParams);
        List<Protocol.Proposal> list = this.proposalsList;
        if (list == null || list.size() == 0) {
            dismissLoadingPage();
            this.rcOriposals.setVisibility(View.GONE);
            this.llNodata.setVisibility(View.VISIBLE);
            ((MyProposalsPresenter) this.mPresenter).addSome(this.proposalsList, this.currentSelectAddress, this.mWitnessList);
        } else {
            this.rcOriposals.setVisibility(View.VISIBLE);
            this.llNodata.setVisibility(View.GONE);
            ((MyProposalsPresenter) this.mPresenter).addSome(this.proposalsList, this.currentSelectAddress, this.mWitnessList);
        }
        this.tvMakeProposal.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                ((MyProposalsPresenter) mPresenter).makeProposal();
            }
        });
    }

    public static void start(Context context, String str, List<Protocol.Proposal> list, String str2, List<WitnessOutput.DataBean> list2) {
        Intent intent = new Intent(context, MyProposalsActivity.class);
        intent.putExtra("typeFrom", str);
        intent.putExtra("proposals_list", (Serializable) list);
        intent.putExtra("currentAddress", str2);
        intent.putExtra("witness_list", (Serializable) list2);
        context.startActivity(intent);
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        finish();
    }

    @Override
    public void showLoading(boolean z) {
        if (z) {
            showLoading(getString(R.string.loading));
        } else {
            dismissLoading();
        }
    }

    @Override
    public void showNoDataLayout(boolean z) {
        if (z) {
            this.rcOriposals.setVisibility(View.GONE);
            this.llNodata.setVisibility(View.VISIBLE);
            return;
        }
        this.rcOriposals.setVisibility(View.VISIBLE);
        this.llNodata.setVisibility(View.GONE);
    }
}
