package com.tron.wallet.common.adapter.proposal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.common.adapter.holder.ProposalsContentHolder;
import com.tron.wallet.common.adapter.holder.ProposalsHeadHolder;
import com.tronlinkpro.wallet.R;
import java.util.HashMap;
import java.util.List;
import org.tron.protos.Protocol;
public class ProposalsAdapter extends RecyclerView.Adapter<BaseHolder> {
    private static final int TYPE_CONTENT = 1;
    private static final int TYPE_HEAD = 0;
    private String approvedProCount;
    private ProposalsContentHolder contentHolder;
    private String currentAddress;
    private ProposalsHeadHolder headerHolder;
    private List<Protocol.Proposal> mAllProposalList;
    private List<WitnessOutput.DataBean> mCommitteeList;
    private Context mContext;
    private int mCurrentType;
    private String mEditInput;
    private boolean mIsFirst;
    private String myProposalsCount;
    private HashMap<String, WitnessOutput.DataBean> nameMap;
    private ProposalClickListener proposalListener;

    public interface ProposalClickListener {

        public final class -CC {
            public static void $default$onAgreeClick(ProposalClickListener _this, Protocol.Proposal proposal, boolean z, HashMap hashMap) {
            }
        }

        void approvedProClick();

        void changeAddClick();

        void myProposalsClick();

        void onAgreeClick(Protocol.Proposal proposal, boolean z, HashMap<Long, String> hashMap);

        void onEditTextChange(String str);

        void onSeleted(int i);

        void onTimeEnd();
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public ProposalsAdapter(Context context, ProposalClickListener proposalClickListener, String str, String str2, String str3, List<Protocol.Proposal> list, int i, HashMap<String, WitnessOutput.DataBean> hashMap, List<WitnessOutput.DataBean> list2, boolean z) {
        this.mContext = context;
        this.proposalListener = proposalClickListener;
        this.myProposalsCount = str;
        this.approvedProCount = str2;
        this.currentAddress = str3;
        this.mAllProposalList = list;
        this.mCurrentType = i;
        this.nameMap = hashMap;
        this.mCommitteeList = list2;
        this.mIsFirst = z;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 0) {
            ProposalsHeadHolder proposalsHeadHolder = new ProposalsHeadHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_proposals_head, (ViewGroup) null), this.mContext, this.proposalListener, this.mCurrentType);
            this.headerHolder = proposalsHeadHolder;
            return proposalsHeadHolder;
        } else if (i != 1) {
            return null;
        } else {
            ProposalsContentHolder proposalsContentHolder = new ProposalsContentHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recyclerview_proposals, (ViewGroup) null), this.mContext, this.proposalListener, this.currentAddress);
            this.contentHolder = proposalsContentHolder;
            return proposalsContentHolder;
        }
    }

    @Override
    public void onBindViewHolder(BaseHolder baseHolder, int i) {
        if (baseHolder instanceof ProposalsHeadHolder) {
            ((ProposalsHeadHolder) baseHolder).bindHolder(this.currentAddress, this.myProposalsCount, this.approvedProCount);
        } else if (baseHolder instanceof ProposalsContentHolder) {
            HashMap<String, WitnessOutput.DataBean> hashMap = this.nameMap;
            if (hashMap != null && hashMap.size() > 0) {
                ((ProposalsContentHolder) baseHolder).bindHolder(this.mAllProposalList, this.currentAddress, this.nameMap, this.mCommitteeList, this.mEditInput, this.mIsFirst);
            } else {
                ((ProposalsContentHolder) baseHolder).bindHolder(this.mAllProposalList, this.currentAddress, this.mEditInput, this.mIsFirst);
            }
        }
    }

    public void notifyHeadData(String str, String str2, String str3) {
        this.currentAddress = str;
        this.myProposalsCount = str2;
        this.approvedProCount = str3;
        notifyItemChanged(0);
    }

    public void notifyData(List<Protocol.Proposal> list, String str, String str2) {
        this.mAllProposalList = list;
        this.currentAddress = str;
        this.mEditInput = str2;
        this.mIsFirst = false;
        notifyItemChanged(1, "1001");
    }

    @Override
    public int getItemViewType(int i) {
        if (i != 0) {
            if (i != 1) {
                return super.getItemViewType(i);
            }
            return 1;
        }
        return 0;
    }

    public void notify(HashMap<String, WitnessOutput.DataBean> hashMap, List<WitnessOutput.DataBean> list) {
        this.nameMap = hashMap;
        this.mCommitteeList = list;
        notifyItemChanged(1, "1002");
    }
}
