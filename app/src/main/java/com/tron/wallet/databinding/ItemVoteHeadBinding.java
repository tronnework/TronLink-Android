package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemVoteHeadBinding implements ViewBinding {
    public final ImageView ivRewards;
    public final LinearLayout llGetReward;
    public final LinearLayout llGetvote;
    public final LinearLayout llReward;
    public final LinearLayout llTp;
    public final LinearLayout llVoteCount;
    public final TextView myVote;
    public final TextView remainingTp;
    private final RelativeLayout rootView;
    public final TextView surplusAvailable;
    public final TextView totalVote;
    public final TextView trxReward;
    public final TextView tvAwards;
    public final TextView tvVotingReward;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemVoteHeadBinding(RelativeLayout relativeLayout, ImageView imageView, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, LinearLayout linearLayout5, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7) {
        this.rootView = relativeLayout;
        this.ivRewards = imageView;
        this.llGetReward = linearLayout;
        this.llGetvote = linearLayout2;
        this.llReward = linearLayout3;
        this.llTp = linearLayout4;
        this.llVoteCount = linearLayout5;
        this.myVote = textView;
        this.remainingTp = textView2;
        this.surplusAvailable = textView3;
        this.totalVote = textView4;
        this.trxReward = textView5;
        this.tvAwards = textView6;
        this.tvVotingReward = textView7;
    }

    public static ItemVoteHeadBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemVoteHeadBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_vote_head, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemVoteHeadBinding bind(View view) {
        int i = R.id.iv_rewards;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_rewards);
        if (imageView != null) {
            i = R.id.ll_getReward;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_getReward);
            if (linearLayout != null) {
                i = R.id.ll_getvote;
                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_getvote);
                if (linearLayout2 != null) {
                    i = R.id.ll_reward;
                    LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_reward);
                    if (linearLayout3 != null) {
                        i = R.id.ll_tp;
                        LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_tp);
                        if (linearLayout4 != null) {
                            i = R.id.ll_vote_count;
                            LinearLayout linearLayout5 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_vote_count);
                            if (linearLayout5 != null) {
                                i = R.id.my_vote;
                                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.my_vote);
                                if (textView != null) {
                                    i = R.id.remaining_tp;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.remaining_tp);
                                    if (textView2 != null) {
                                        i = R.id.surplus_available;
                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.surplus_available);
                                        if (textView3 != null) {
                                            i = R.id.total_vote;
                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.total_vote);
                                            if (textView4 != null) {
                                                i = R.id.trx_reward;
                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.trx_reward);
                                                if (textView5 != null) {
                                                    i = R.id.tv_awards;
                                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_awards);
                                                    if (textView6 != null) {
                                                        i = R.id.tv_voting_reward;
                                                        TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_voting_reward);
                                                        if (textView7 != null) {
                                                            return new ItemVoteHeadBinding((RelativeLayout) view, imageView, linearLayout, linearLayout2, linearLayout3, linearLayout4, linearLayout5, textView, textView2, textView3, textView4, textView5, textView6, textView7);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
