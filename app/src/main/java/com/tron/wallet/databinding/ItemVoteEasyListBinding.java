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
import com.tron.wallet.common.components.CurrencyEditText;
import com.tronlinkpro.wallet.R;
public final class ItemVoteEasyListBinding implements ViewBinding {
    public final TextView alreadyVotedTitle;
    public final TextView aprTitle;
    public final View dashDivider;
    public final CurrencyEditText etInput;
    public final ImageView ivVoteAprTips;
    public final LinearLayout llContent;
    public final RelativeLayout rlFlagAlreadyVoted;
    public final RelativeLayout rlInput;
    private final RelativeLayout rootView;
    public final TextView tvApr;
    public final TextView tvMax;
    public final TextView tvRanking;
    public final TextView tvVoteTipsBottom;
    public final TextView tvVotesCount;
    public final TextView tvWitnessName;
    public final ImageView voteMinus;
    public final ImageView votePlus;
    public final TextView votesTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemVoteEasyListBinding(RelativeLayout relativeLayout, TextView textView, TextView textView2, View view, CurrencyEditText currencyEditText, ImageView imageView, LinearLayout linearLayout, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, ImageView imageView2, ImageView imageView3, TextView textView9) {
        this.rootView = relativeLayout;
        this.alreadyVotedTitle = textView;
        this.aprTitle = textView2;
        this.dashDivider = view;
        this.etInput = currencyEditText;
        this.ivVoteAprTips = imageView;
        this.llContent = linearLayout;
        this.rlFlagAlreadyVoted = relativeLayout2;
        this.rlInput = relativeLayout3;
        this.tvApr = textView3;
        this.tvMax = textView4;
        this.tvRanking = textView5;
        this.tvVoteTipsBottom = textView6;
        this.tvVotesCount = textView7;
        this.tvWitnessName = textView8;
        this.voteMinus = imageView2;
        this.votePlus = imageView3;
        this.votesTitle = textView9;
    }

    public static ItemVoteEasyListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemVoteEasyListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_vote_easy_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemVoteEasyListBinding bind(View view) {
        int i = R.id.already_voted_title;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.already_voted_title);
        if (textView != null) {
            i = R.id.apr_title;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.apr_title);
            if (textView2 != null) {
                i = R.id.dash_divider;
                View findChildViewById = ViewBindings.findChildViewById(view, R.id.dash_divider);
                if (findChildViewById != null) {
                    i = R.id.et_input;
                    CurrencyEditText currencyEditText = (CurrencyEditText) ViewBindings.findChildViewById(view, R.id.et_input);
                    if (currencyEditText != null) {
                        i = R.id.iv_vote_apr_tips;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_vote_apr_tips);
                        if (imageView != null) {
                            i = R.id.ll_content;
                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_content);
                            if (linearLayout != null) {
                                i = R.id.rl_flag_already_voted;
                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_flag_already_voted);
                                if (relativeLayout != null) {
                                    i = R.id.rl_input;
                                    RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_input);
                                    if (relativeLayout2 != null) {
                                        i = R.id.tv_apr;
                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_apr);
                                        if (textView3 != null) {
                                            i = R.id.tv_max;
                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_max);
                                            if (textView4 != null) {
                                                i = R.id.tv_ranking;
                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_ranking);
                                                if (textView5 != null) {
                                                    i = R.id.tv_vote_tips_bottom;
                                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_vote_tips_bottom);
                                                    if (textView6 != null) {
                                                        i = R.id.tv_votes_count;
                                                        TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_votes_count);
                                                        if (textView7 != null) {
                                                            i = R.id.tv_witness_name;
                                                            TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_witness_name);
                                                            if (textView8 != null) {
                                                                i = R.id.vote_minus;
                                                                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.vote_minus);
                                                                if (imageView2 != null) {
                                                                    i = R.id.vote_plus;
                                                                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.vote_plus);
                                                                    if (imageView3 != null) {
                                                                        i = R.id.votes_title;
                                                                        TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.votes_title);
                                                                        if (textView9 != null) {
                                                                            return new ItemVoteEasyListBinding((RelativeLayout) view, textView, textView2, findChildViewById, currencyEditText, imageView, linearLayout, relativeLayout, relativeLayout2, textView3, textView4, textView5, textView6, textView7, textView8, imageView2, imageView3, textView9);
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
