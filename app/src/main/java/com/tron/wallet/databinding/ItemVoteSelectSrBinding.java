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
public final class ItemVoteSelectSrBinding implements ViewBinding {
    public final TextView aprTitle;
    public final View dashDivider;
    public final ImageView ivArrow;
    public final ImageView ivSelected;
    public final ImageView ivVoteAprTips;
    public final ImageView ivVoted;
    public final LinearLayout liOpRight;
    public final RelativeLayout rlFlagAlreadyVoted;
    public final RelativeLayout rlVotes;
    private final RelativeLayout rootView;
    public final RelativeLayout superBg;
    public final TextView tvApr;
    public final TextView tvRanking;
    public final TextView tvVoted;
    public final TextView tvVotedCount;
    public final TextView tvWitnessName;
    public final TextView voteCount;
    public final TextView voteCountTip;
    public final RelativeLayout voteSelectRoot;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemVoteSelectSrBinding(RelativeLayout relativeLayout, TextView textView, View view, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, LinearLayout linearLayout, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, RelativeLayout relativeLayout5) {
        this.rootView = relativeLayout;
        this.aprTitle = textView;
        this.dashDivider = view;
        this.ivArrow = imageView;
        this.ivSelected = imageView2;
        this.ivVoteAprTips = imageView3;
        this.ivVoted = imageView4;
        this.liOpRight = linearLayout;
        this.rlFlagAlreadyVoted = relativeLayout2;
        this.rlVotes = relativeLayout3;
        this.superBg = relativeLayout4;
        this.tvApr = textView2;
        this.tvRanking = textView3;
        this.tvVoted = textView4;
        this.tvVotedCount = textView5;
        this.tvWitnessName = textView6;
        this.voteCount = textView7;
        this.voteCountTip = textView8;
        this.voteSelectRoot = relativeLayout5;
    }

    public static ItemVoteSelectSrBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemVoteSelectSrBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_vote_select_sr, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemVoteSelectSrBinding bind(View view) {
        int i = R.id.apr_title;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.apr_title);
        if (textView != null) {
            i = R.id.dash_divider;
            View findChildViewById = ViewBindings.findChildViewById(view, R.id.dash_divider);
            if (findChildViewById != null) {
                i = R.id.iv_arrow;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_arrow);
                if (imageView != null) {
                    i = R.id.iv_selected;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_selected);
                    if (imageView2 != null) {
                        i = R.id.iv_vote_apr_tips;
                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_vote_apr_tips);
                        if (imageView3 != null) {
                            i = R.id.iv_voted;
                            ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_voted);
                            if (imageView4 != null) {
                                i = R.id.li_op_right;
                                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_op_right);
                                if (linearLayout != null) {
                                    i = R.id.rl_flag_already_voted;
                                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_flag_already_voted);
                                    if (relativeLayout != null) {
                                        i = R.id.rl_votes;
                                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_votes);
                                        if (relativeLayout2 != null) {
                                            i = R.id.super_bg;
                                            RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.super_bg);
                                            if (relativeLayout3 != null) {
                                                i = R.id.tv_apr;
                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_apr);
                                                if (textView2 != null) {
                                                    i = R.id.tv_ranking;
                                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_ranking);
                                                    if (textView3 != null) {
                                                        i = R.id.tv_voted;
                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_voted);
                                                        if (textView4 != null) {
                                                            i = R.id.tv_voted_count;
                                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_voted_count);
                                                            if (textView5 != null) {
                                                                i = R.id.tv_witness_name;
                                                                TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_witness_name);
                                                                if (textView6 != null) {
                                                                    i = R.id.vote_count;
                                                                    TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.vote_count);
                                                                    if (textView7 != null) {
                                                                        i = R.id.vote_count_tip;
                                                                        TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.vote_count_tip);
                                                                        if (textView8 != null) {
                                                                            RelativeLayout relativeLayout4 = (RelativeLayout) view;
                                                                            return new ItemVoteSelectSrBinding(relativeLayout4, textView, findChildViewById, imageView, imageView2, imageView3, imageView4, linearLayout, relativeLayout, relativeLayout2, relativeLayout3, textView2, textView3, textView4, textView5, textView6, textView7, textView8, relativeLayout4);
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
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
