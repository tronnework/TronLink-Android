package com.tron.wallet.business.vote.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.common.adapter.holder.VoteContentHolder;
import com.tron.wallet.common.adapter.holder.VoteHeaderHolder;
import com.tron.wallet.common.adapter.holder.VoteSearchHolder;
import com.tronlinkpro.wallet.R;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
public class VoteItemAdapter extends RecyclerView.Adapter<BaseHolder> {
    private static final int TYPE_CONTENT = 1;
    private static final int TYPE_HEAD = 0;
    private static final int TYPE_SEAECH = 2;
    private long allVoteCount;
    private VoteContentHolder contentHolder;
    private int currentType;
    private VoteHeaderHolder headerHolder;
    private Context mContext;
    private String mSearchInput;
    private boolean mShowVoteEditText;
    private String mTrxReward;
    private HashMap<String, String> mVotes;
    private OnWitnessClickListener mWitnessListener;
    private List<WitnessOutput.DataBean> mWitnessesFilteredList;
    private List<WitnessOutput.DataBean> mWitnessesList;
    private VoteSearchHolder searchHolder;
    private boolean showFiltered;
    private long surplusCount;
    private long totalMyVote;
    private String countTime = "00:00:00";
    private boolean isSearchFrom = false;
    private NumberFormat numberFormat = NumberFormat.getIntegerInstance();

    public interface OnWitnessClickListener {
        void getReward(String str);

        void onEditTextChange(String str);

        void onSeleted(int i);

        void resetClick();

        void voteClick();
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public boolean isShowFiltered() {
        return this.showFiltered;
    }

    public void setShowFiltered(boolean z) {
        this.showFiltered = z;
    }

    public VoteItemAdapter(Context context, List<WitnessOutput.DataBean> list, List<WitnessOutput.DataBean> list2, boolean z, HashMap<String, String> hashMap, long j, long j2, long j3, OnWitnessClickListener onWitnessClickListener, String str, int i, String str2) {
        this.mContext = context;
        this.mVotes = hashMap;
        this.mWitnessesList = list;
        this.mWitnessesFilteredList = list2;
        this.showFiltered = z;
        this.allVoteCount = j;
        this.totalMyVote = j2;
        this.surplusCount = j3;
        this.mWitnessListener = onWitnessClickListener;
        this.mSearchInput = str;
        this.currentType = i;
        this.mTrxReward = str2;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 0) {
            VoteHeaderHolder voteHeaderHolder = new VoteHeaderHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_vote_head, (ViewGroup) null), this.mContext, this.mWitnessListener, this.mTrxReward);
            this.headerHolder = voteHeaderHolder;
            return voteHeaderHolder;
        } else if (i == 1) {
            VoteContentHolder voteContentHolder = new VoteContentHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_vote_content, (ViewGroup) null), this.mContext);
            this.contentHolder = voteContentHolder;
            return voteContentHolder;
        } else if (i != 2) {
            return null;
        } else {
            VoteSearchHolder voteSearchHolder = new VoteSearchHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.include_vote_search, (ViewGroup) null), this.mContext, this.mWitnessListener, this.currentType);
            this.searchHolder = voteSearchHolder;
            return voteSearchHolder;
        }
    }

    @Override
    public void onBindViewHolder(BaseHolder baseHolder, int i) {
        if (baseHolder instanceof VoteHeaderHolder) {
            ((VoteHeaderHolder) baseHolder).bindHolder(this.allVoteCount, this.totalMyVote, this.surplusCount);
        } else if (baseHolder instanceof VoteContentHolder) {
            ((VoteContentHolder) baseHolder).bind(this.mWitnessesList, this.mWitnessesFilteredList, this.mVotes, this.showFiltered, this.mSearchInput);
        } else if (baseHolder instanceof VoteSearchHolder) {
            ((VoteSearchHolder) baseHolder).bindHolder(this.countTime, this.mSearchInput);
        }
    }

    @Override
    public int getItemViewType(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    return super.getItemViewType(i);
                }
                return 1;
            }
            return 2;
        }
        return 0;
    }

    public HashMap<String, String> getmVotes() {
        VoteContentHolder voteContentHolder = this.contentHolder;
        if (voteContentHolder != null) {
            this.mVotes = voteContentHolder.getmVotes();
        }
        return this.mVotes;
    }

    public void setVoteDetail(long j, long j2, long j3) {
        this.allVoteCount = j;
        this.totalMyVote = j2;
        this.surplusCount = j3;
        notifyDataSetChanged();
    }

    public void notifyData(List<WitnessOutput.DataBean> list) {
        this.mWitnessesFilteredList = list;
        notifyItemChanged(2, "1001");
    }

    public void notifyData(HashMap<String, String> hashMap) {
        this.mVotes = hashMap;
        notifyItemChanged(2, "1002");
    }

    public void notifyData(List<WitnessOutput.DataBean> list, List<WitnessOutput.DataBean> list2) {
        this.mWitnessesList = list;
        this.mWitnessesFilteredList = list2;
        notifyItemChanged(2, "1003");
    }

    public void setCount(long j, long j2, long j3) {
        this.allVoteCount = j;
        this.totalMyVote = j2;
        this.surplusCount = j3;
        notifyItemChanged(0);
    }

    public void notifyCountTime(String str) {
        this.countTime = str;
        VoteSearchHolder voteSearchHolder = this.searchHolder;
        if (voteSearchHolder != null) {
            voteSearchHolder.updateCountTime(str);
        }
    }

    public void notifyVoteRrword(String str) {
        VoteHeaderHolder voteHeaderHolder = this.headerHolder;
        if (voteHeaderHolder != null) {
            voteHeaderHolder.updateVoteReword(str);
        }
    }

    public void notifySearch(String str, boolean z, HashMap<String, String> hashMap, List<WitnessOutput.DataBean> list, List<WitnessOutput.DataBean> list2, int i) {
        this.currentType = i;
        VoteSearchHolder voteSearchHolder = this.searchHolder;
        if (voteSearchHolder != null) {
            voteSearchHolder.updateCurrentType(i);
            this.searchHolder.updateSearchInput(str);
        }
        VoteContentHolder voteContentHolder = this.contentHolder;
        if (voteContentHolder != null) {
            voteContentHolder.notifySearchResult(str, z, hashMap, list, list2);
        }
    }

    public void setSearchFrom(boolean z) {
        this.isSearchFrom = z;
        VoteContentHolder voteContentHolder = this.contentHolder;
        if (voteContentHolder != null) {
            voteContentHolder.setSearchFrom(z);
        }
    }

    public void notifyData(List<WitnessOutput.DataBean> list, List<WitnessOutput.DataBean> list2, boolean z) {
        VoteContentHolder voteContentHolder = this.contentHolder;
        if (voteContentHolder != null) {
            voteContentHolder.notifyData(list, list2, z);
        }
    }
}
