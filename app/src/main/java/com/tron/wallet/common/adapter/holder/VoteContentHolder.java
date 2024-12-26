package com.tron.wallet.common.adapter.holder;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.wallet.business.vote.adapter.VoteItemListAdapter;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tronlinkpro.wallet.R;
import java.util.HashMap;
import java.util.List;
public class VoteContentHolder extends BaseHolder {
    private Context mContext;
    RecyclerView rcVote;
    RelativeLayout rlNoResult;
    private boolean showFiltered;
    private VoteItemListAdapter voteItemListAdapter;

    public VoteContentHolder(View view, Context context) {
        super(view);
        this.rcVote = (RecyclerView) view.findViewById(R.id.rc_vote);
        this.rlNoResult = (RelativeLayout) view.findViewById(R.id.rl_noResult);
        this.mContext = context;
    }

    public void bind(List<WitnessOutput.DataBean> list, List<WitnessOutput.DataBean> list2, HashMap<String, String> hashMap, boolean z, String str) {
        if (!TextUtils.isEmpty(str) && (list2 == null || list2.size() == 0)) {
            this.rlNoResult.setVisibility(View.VISIBLE);
            this.rcVote.setVisibility(View.GONE);
        } else if (!z ? !(list == null || list.size() == 0) : !(list2 == null || list2.size() == 0)) {
            this.rcVote.setVisibility(View.VISIBLE);
            this.rlNoResult.setVisibility(View.GONE);
            VoteItemListAdapter voteItemListAdapter = this.voteItemListAdapter;
            if (voteItemListAdapter == null) {
                this.voteItemListAdapter = new VoteItemListAdapter(this.mContext, list, list2, true, z, hashMap, str);
                this.rcVote.setLayoutManager(new LinearLayoutManager(this.mContext, 1, false));
                this.rcVote.setAdapter(this.voteItemListAdapter);
                return;
            }
            voteItemListAdapter.setShowFiltered(z);
            this.voteItemListAdapter.notifyData(list, list2);
        } else {
            this.rlNoResult.setVisibility(View.VISIBLE);
            this.rcVote.setVisibility(View.GONE);
        }
    }

    public HashMap<String, String> getmVotes() {
        VoteItemListAdapter voteItemListAdapter = this.voteItemListAdapter;
        if (voteItemListAdapter != null) {
            return voteItemListAdapter.getmVotes();
        }
        return null;
    }

    public void setShowFiltered(boolean z) {
        VoteItemListAdapter voteItemListAdapter = this.voteItemListAdapter;
        if (voteItemListAdapter != null) {
            voteItemListAdapter.setShowFiltered(z);
        }
    }

    public void notifyData(List<WitnessOutput.DataBean> list) {
        VoteItemListAdapter voteItemListAdapter = this.voteItemListAdapter;
        if (voteItemListAdapter != null) {
            voteItemListAdapter.notifyData(list);
        }
    }

    public void notifyData(HashMap<String, String> hashMap) {
        VoteItemListAdapter voteItemListAdapter = this.voteItemListAdapter;
        if (voteItemListAdapter != null) {
            voteItemListAdapter.notifyData(hashMap);
        }
    }

    public void notifyData(List<WitnessOutput.DataBean> list, List<WitnessOutput.DataBean> list2) {
        VoteItemListAdapter voteItemListAdapter = this.voteItemListAdapter;
        if (voteItemListAdapter != null) {
            voteItemListAdapter.notifyData(list, list2);
        }
    }

    public void setSearchFrom(boolean z) {
        VoteItemListAdapter voteItemListAdapter = this.voteItemListAdapter;
        if (voteItemListAdapter != null) {
            voteItemListAdapter.setSearchFrom(z);
        }
    }

    public void notifySearchResult(String str, boolean z, HashMap<String, String> hashMap, List<WitnessOutput.DataBean> list, List<WitnessOutput.DataBean> list2) {
        if (!z ? !(list == null || list.size() == 0) : !(list2 == null || list2.size() == 0)) {
            this.rlNoResult.setVisibility(View.GONE);
            this.rcVote.setVisibility(View.VISIBLE);
            VoteItemListAdapter voteItemListAdapter = this.voteItemListAdapter;
            if (voteItemListAdapter != null) {
                voteItemListAdapter.updateSearchSrc(str);
                this.voteItemListAdapter.setShowFiltered(z);
                this.voteItemListAdapter.setSearchFrom(true);
                this.voteItemListAdapter.notifyData(hashMap);
                this.voteItemListAdapter.notifyData(list, list2);
                return;
            }
            return;
        }
        this.rlNoResult.setVisibility(View.VISIBLE);
        this.rcVote.setVisibility(View.GONE);
    }

    public void notifyData(List<WitnessOutput.DataBean> list, List<WitnessOutput.DataBean> list2, boolean z) {
        VoteItemListAdapter voteItemListAdapter = this.voteItemListAdapter;
        if (voteItemListAdapter != null) {
            voteItemListAdapter.notifyData(list, list2, z);
        }
    }
}
