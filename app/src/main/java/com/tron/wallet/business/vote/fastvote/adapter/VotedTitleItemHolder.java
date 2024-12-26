package com.tron.wallet.business.vote.fastvote.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tron.wallet.business.vote.fastvote.FastVotePresenter;
import com.tron.wallet.business.vote.fastvote.VoteWitnessBean;
import com.tronlinkpro.wallet.R;
public class VotedTitleItemHolder extends BaseViewHolder {
    ImageView ivRight;
    boolean mExpand;
    LinearLayout root;

    public VotedTitleItemHolder(View view) {
        super(view);
        this.mExpand = false;
        mappingId(view);
    }

    public void onBind(final VoteWitnessBean voteWitnessBean, final FastVotePresenter.VotedTitleClickListener votedTitleClickListener) {
        toggleArrow(voteWitnessBean.isVisibility());
        this.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                votedTitleClickListener.invisibilityChange();
                toggleArrow(voteWitnessBean.isVisibility());
                mExpand = voteWitnessBean.isVisibility();
            }
        });
    }

    void toggleArrow(boolean z) {
        if (z) {
            this.ivRight.setImageResource(R.mipmap.ic_arrow_up);
        } else {
            this.ivRight.setImageResource(R.mipmap.ic_arrow_down);
        }
    }

    private void mappingId(View view) {
        this.root = (LinearLayout) view.findViewById(R.id.root);
        this.ivRight = (ImageView) view.findViewById(R.id.iv_tag);
    }
}
