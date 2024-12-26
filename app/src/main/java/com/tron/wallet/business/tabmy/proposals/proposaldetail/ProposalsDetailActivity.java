package com.tron.wallet.business.tabmy.proposals.proposaldetail;

import android.content.Context;
import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.protobuf.ByteString;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.wallet.business.tabmy.proposals.proposaldetail.ProposalsDetailContract;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.common.adapter.proposal.ProposalContentAdapter;
import com.tron.wallet.common.components.ZFlowLayout;
import com.tron.wallet.databinding.AcProposalsContentBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class ProposalsDetailActivity extends BaseActivity<ProposalsDetailPresenter, ProposalsDetailModel> implements ProposalsDetailContract.View {
    public static final String PROPOSALS_CURRENTADDRESS = "proposals_currentAddress";
    public static final String PROPOSALS_DETAIL = "proposals_detail";
    public static final String PROPOSALS_FROMTYPE = "proposals_fromType";
    public static final String PROPOSALS_HASAGREE = "proposals_hasagree";
    public static final String PROPOSALS_ID = "proposals_id";
    public static final String TAG = "ProposalsDetailActivity";
    public static final String TYPE_DEAL = "type_deal";
    public static final String WITNESS_MAP = "witness_map";
    private Protocol.Account account;
    private List<ByteString> approvalsList;
    private AcProposalsContentBinding binding;
    private List<ByteString> candidatesList;
    private boolean hasAgree;
    private boolean isVoting;
    LinearLayout llOperation;
    LinearLayout llProposalsName;
    private Wallet mWallet;
    private List<WitnessOutput.DataBean> mWitnessList;
    TextView numAllVotes;
    TextView numValidVotes;
    private List<ByteString> partnersList;
    private Protocol.Proposal proposal;
    private ProposalContentAdapter proposalContentAdapter;
    private String proposerAddress;
    RelativeLayout rlVoteDetail;
    RecyclerView rvProposal;
    NestedScrollView scollContent;
    TextView titleAllVotes;
    TextView titleValidVotes;
    TextView tvCreateTime;
    TextView tvEndTime;
    TextView tvNoApprovers;
    TextView tvProposalsAgree;
    TextView tvProposalsCancle;
    TextView tvProposalsId;
    TextView tvProposalsName;
    TextView tvProposalsState;
    TextView tvProposalsUnclick;
    private HashMap<String, WitnessOutput.DataBean> witnessMap;
    ZFlowLayout zflCandidates;
    ZFlowLayout zflPartners;
    ZFlowLayout zflRepresentatives;
    private boolean isCancle = false;
    private boolean hasPer = true;

    @Override
    public HashMap<String, WitnessOutput.DataBean> getWitnessMap() {
        return this.witnessMap;
    }

    @Override
    protected void setLayout() {
        AcProposalsContentBinding inflate = AcProposalsContentBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        setView(root, 3);
        setHeaderBar(getString(R.string.proposal_detail));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.tvProposalsId = this.binding.tvProposalsId;
        this.tvProposalsState = this.binding.tvProposalsState;
        this.tvProposalsName = this.binding.tvProposalsName;
        this.llProposalsName = this.binding.llProposalsName;
        this.rvProposal = this.binding.rvProposal;
        this.titleAllVotes = this.binding.titleAllVotes;
        this.numAllVotes = this.binding.numAllVotes;
        this.numValidVotes = this.binding.numValidVotes;
        this.rlVoteDetail = this.binding.rlVoteDetail;
        this.tvCreateTime = this.binding.tvCreateTime;
        this.tvEndTime = this.binding.tvEndTime;
        this.zflRepresentatives = this.binding.zflRepresentatives;
        this.zflPartners = this.binding.zflPartners;
        this.zflCandidates = this.binding.zflCandidates;
        this.tvProposalsAgree = this.binding.tvProposalsAgree;
        this.tvProposalsCancle = this.binding.tvProposalsCancle;
        this.tvProposalsUnclick = this.binding.tvProposalsUnclick;
        this.llOperation = this.binding.llOperation;
        this.scollContent = this.binding.scollContent;
        this.titleValidVotes = this.binding.titleValidVotes;
        this.tvNoApprovers = this.binding.tvNoApprovers;
    }

    @Override
    protected void processData() {
        this.mWitnessList = new ArrayList();
        this.approvalsList = new ArrayList();
        this.partnersList = new ArrayList();
        this.candidatesList = new ArrayList();
        this.witnessMap = new HashMap<>();
        this.mWallet = WalletUtils.getSelectedWallet();
        this.tvNoApprovers.setVisibility(View.GONE);
        this.rvProposal.setLayoutManager(new LinearLayoutManager(this.mContext, 1, false));
        ((ProposalsDetailPresenter) this.mPresenter).addSome();
    }

    public static void start(Context context, Protocol.Proposal proposal, boolean z, String str, List<WitnessOutput.DataBean> list) {
        Intent intent = new Intent(context, ProposalsDetailActivity.class);
        intent.putExtra(PROPOSALS_DETAIL, proposal);
        intent.putExtra(PROPOSALS_HASAGREE, z);
        intent.putExtra(PROPOSALS_CURRENTADDRESS, str);
        intent.putExtra(WITNESS_MAP, (Serializable) list);
        context.startActivity(intent);
    }

    public static void start(Context context, String str, String str2, String str3) {
        Intent intent = new Intent(context, ProposalsDetailActivity.class);
        intent.putExtra(PROPOSALS_ID, str);
        intent.putExtra(PROPOSALS_CURRENTADDRESS, str2);
        intent.putExtra(PROPOSALS_FROMTYPE, str3);
        context.startActivity(intent);
    }

    @Override
    public Intent getIIntent() {
        return getIntent();
    }

    @Override
    public void initView(final org.tron.protos.Protocol.Account r20, final org.tron.protos.Protocol.Proposal r21, java.util.List<com.tron.wallet.business.vote.bean.WitnessOutput.DataBean> r22) {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.tabmy.proposals.proposaldetail.ProposalsDetailActivity.initView(org.tron.protos.Protocol$Account, org.tron.protos.Protocol$Proposal, java.util.List):void");
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        finish();
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            finish();
        }
    }
}
