package com.tron.wallet.common.adapter.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.common.adapter.proposal.ProposalsAdapter;
import com.tron.wallet.common.adapter.proposal.ProposalsListAdapter;
import com.tronlinkpro.wallet.R;
import java.util.HashMap;
import java.util.List;
import org.tron.protos.Protocol;
public class ProposalsContentHolder extends BaseHolder {
    ImageView ivShasta;
    LinearLayout llNodata;
    private Context mContext;
    private String mCurrentAddress;
    private ProposalsAdapter.ProposalClickListener mProposalListener;
    private LinearLayoutManager manager;
    private ProposalsListAdapter proposalsListAdapter;
    RecyclerView rcOriposals;
    TextView tvNodataContent;

    public ProposalsContentHolder(View view, Context context, ProposalsAdapter.ProposalClickListener proposalClickListener, String str) {
        super(view);
        this.rcOriposals = (RecyclerView) view.findViewById(R.id.rc_oriposals);
        this.ivShasta = (ImageView) view.findViewById(R.id.iv_shasta);
        this.tvNodataContent = (TextView) view.findViewById(R.id.tv_nodata_content);
        this.llNodata = (LinearLayout) view.findViewById(R.id.ll_nodata);
        this.mContext = context;
        this.mProposalListener = proposalClickListener;
        this.mCurrentAddress = str;
    }

    public void bindHolder(List<Protocol.Proposal> list, String str, String str2, boolean z) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.mContext, 1, false);
        this.manager = linearLayoutManager;
        this.rcOriposals.setLayoutManager(linearLayoutManager);
        if (list == null || list.size() <= 0) {
            if (z) {
                return;
            }
            this.llNodata.setVisibility(View.VISIBLE);
            this.rcOriposals.setVisibility(View.GONE);
            return;
        }
        this.llNodata.setVisibility(View.GONE);
        this.rcOriposals.setVisibility(View.VISIBLE);
        ProposalsListAdapter proposalsListAdapter = this.proposalsListAdapter;
        if (proposalsListAdapter == null) {
            ProposalsListAdapter proposalsListAdapter2 = new ProposalsListAdapter(this.mContext, list, str, this.mProposalListener, str2);
            this.proposalsListAdapter = proposalsListAdapter2;
            this.rcOriposals.setAdapter(proposalsListAdapter2);
            return;
        }
        proposalsListAdapter.notifyData(list, str, str2);
    }

    public void bindHolder(List<Protocol.Proposal> list, String str, HashMap<String, WitnessOutput.DataBean> hashMap, List<WitnessOutput.DataBean> list2, String str2, boolean z) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.mContext, 1, false);
        this.manager = linearLayoutManager;
        this.rcOriposals.setLayoutManager(linearLayoutManager);
        if (list == null || list.size() <= 0) {
            if (z) {
                return;
            }
            this.llNodata.setVisibility(View.VISIBLE);
            this.rcOriposals.setVisibility(View.GONE);
            return;
        }
        this.llNodata.setVisibility(View.GONE);
        this.rcOriposals.setVisibility(View.VISIBLE);
        ProposalsListAdapter proposalsListAdapter = this.proposalsListAdapter;
        if (proposalsListAdapter == null) {
            ProposalsListAdapter proposalsListAdapter2 = new ProposalsListAdapter(this.mContext, list, str, this.mProposalListener, str2);
            this.proposalsListAdapter = proposalsListAdapter2;
            proposalsListAdapter2.setNameMap(hashMap, list2);
            this.rcOriposals.setAdapter(this.proposalsListAdapter);
            return;
        }
        proposalsListAdapter.setNameMap(hashMap, list2);
        this.proposalsListAdapter.notifyData(list, str, str2);
    }
}
