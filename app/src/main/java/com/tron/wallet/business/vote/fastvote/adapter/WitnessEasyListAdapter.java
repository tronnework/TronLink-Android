package com.tron.wallet.business.vote.fastvote.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.business.vote.fastvote.FastVotePresenter;
import com.tron.wallet.business.vote.fastvote.VoteWitnessBean;
import com.tron.wallet.common.components.CustomLoadMoreView;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.utils.UIUtils;
import com.tronlinkpro.wallet.R;
import java.util.HashMap;
import java.util.List;
public class WitnessEasyListAdapter extends BaseQuickAdapter<VoteWitnessBean, BaseViewHolder> {
    private static final int TYPE_NORMAL = 0;
    private static final int TYPE_VOTE_TITLE = 1;
    public long allVotes;
    public Context context;
    public boolean isFromMultiSign;
    FastVotePresenter.VoteCountChangeListener mVoteListCallBack;
    public HashMap<String, String> mVotes;
    String selectAddress;
    public long step;
    public long totalVotes;
    FastVotePresenter.VotedTitleClickListener votedTitleClickListener;

    public WitnessEasyListAdapter(Context context, boolean z, String str, HashMap<String, String> hashMap, long j, long j2, long j3, FastVotePresenter.VoteCountChangeListener voteCountChangeListener, FastVotePresenter.VotedTitleClickListener votedTitleClickListener) {
        super(0);
        this.context = context;
        this.isFromMultiSign = z;
        this.selectAddress = str;
        this.mVoteListCallBack = voteCountChangeListener;
        this.votedTitleClickListener = votedTitleClickListener;
        this.allVotes = j;
        this.step = j3;
        this.mVotes = hashMap;
        this.totalVotes = j2;
        setLoadMoreView(new CustomLoadMoreView());
        setEmptyView(createEmptyView(context));
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 1) {
            return new VotedTitleItemHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_voted_title, viewGroup, false));
        }
        return new WitnessEasyListItemHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_vote_easy_list, viewGroup, false));
    }

    @Override
    public int getItemViewType(int i) {
        return getData().get(i).isTitle() ? 1 : 0;
    }

    @Override
    public void convert(BaseViewHolder baseViewHolder, VoteWitnessBean voteWitnessBean) {
        if (baseViewHolder instanceof WitnessEasyListItemHolder) {
            ((WitnessEasyListItemHolder) baseViewHolder).onBind(voteWitnessBean, this.selectAddress, this.mVotes, this.allVotes, this.mVoteListCallBack, this);
        }
        if (baseViewHolder instanceof VotedTitleItemHolder) {
            ((VotedTitleItemHolder) baseViewHolder).onBind(voteWitnessBean, this.votedTitleClickListener);
        }
    }

    public void updateData(List<VoteWitnessBean> list) {
        setNewData(list);
        loadMoreComplete();
    }

    public void clearData(List<WitnessOutput.DataBean> list) {
        notifyDataSetChanged();
        loadMoreComplete();
    }

    private View createEmptyView(Context context) {
        NoNetView noNetView = new NoNetView(context);
        noNetView.setIcon(R.mipmap.ic_no_data_new).setReloadDescription(R.string.no_records).setReloadable(false);
        noNetView.setPadding(0, UIUtils.dip2px(60.0f), 0, 0);
        return noNetView;
    }

    public void updateVotedDate() {
        LogUtils.e("updateVotedDate");
    }
}
