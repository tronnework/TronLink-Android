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
public final class ItemVoteWitnessListBinding implements ViewBinding {
    public final TextView alreadyVotedTitle;
    public final TextView aprTitle;
    public final View dashDivider;
    public final ImageView ivArrow;
    public final ImageView ivVoteAprTips;
    public final ImageView ivVoted;
    public final ImageView ivVotedItem;
    public final LinearLayout llContent;
    public final RelativeLayout rlFlagAlreadyVoted;
    private final RelativeLayout rootView;
    public final TextView tvApr;
    public final TextView tvRanking;
    public final TextView tvVotedCount;
    public final TextView tvVotesCount;
    public final TextView tvWitnessName;
    public final TextView votesTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemVoteWitnessListBinding(RelativeLayout relativeLayout, TextView textView, TextView textView2, View view, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, LinearLayout linearLayout, RelativeLayout relativeLayout2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8) {
        this.rootView = relativeLayout;
        this.alreadyVotedTitle = textView;
        this.aprTitle = textView2;
        this.dashDivider = view;
        this.ivArrow = imageView;
        this.ivVoteAprTips = imageView2;
        this.ivVoted = imageView3;
        this.ivVotedItem = imageView4;
        this.llContent = linearLayout;
        this.rlFlagAlreadyVoted = relativeLayout2;
        this.tvApr = textView3;
        this.tvRanking = textView4;
        this.tvVotedCount = textView5;
        this.tvVotesCount = textView6;
        this.tvWitnessName = textView7;
        this.votesTitle = textView8;
    }

    public static ItemVoteWitnessListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemVoteWitnessListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_vote_witness_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemVoteWitnessListBinding bind(View view) {
        int i = R.id.already_voted_title;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.already_voted_title);
        if (textView != null) {
            i = R.id.apr_title;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.apr_title);
            if (textView2 != null) {
                i = R.id.dash_divider;
                View findChildViewById = ViewBindings.findChildViewById(view, R.id.dash_divider);
                if (findChildViewById != null) {
                    i = R.id.iv_arrow;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_arrow);
                    if (imageView != null) {
                        i = R.id.iv_vote_apr_tips;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_vote_apr_tips);
                        if (imageView2 != null) {
                            i = R.id.iv_voted;
                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_voted);
                            if (imageView3 != null) {
                                i = R.id.iv_voted_item;
                                ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_voted_item);
                                if (imageView4 != null) {
                                    i = R.id.ll_content;
                                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_content);
                                    if (linearLayout != null) {
                                        i = R.id.rl_flag_already_voted;
                                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_flag_already_voted);
                                        if (relativeLayout != null) {
                                            i = R.id.tv_apr;
                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_apr);
                                            if (textView3 != null) {
                                                i = R.id.tv_ranking;
                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_ranking);
                                                if (textView4 != null) {
                                                    i = R.id.tv_voted_count;
                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_voted_count);
                                                    if (textView5 != null) {
                                                        i = R.id.tv_votes_count;
                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_votes_count);
                                                        if (textView6 != null) {
                                                            i = R.id.tv_witness_name;
                                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_witness_name);
                                                            if (textView7 != null) {
                                                                i = R.id.votes_title;
                                                                TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.votes_title);
                                                                if (textView8 != null) {
                                                                    return new ItemVoteWitnessListBinding((RelativeLayout) view, textView, textView2, findChildViewById, imageView, imageView2, imageView3, imageView4, linearLayout, relativeLayout, textView3, textView4, textView5, textView6, textView7, textView8);
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
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
