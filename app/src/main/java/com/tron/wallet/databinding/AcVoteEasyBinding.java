package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class AcVoteEasyBinding implements ViewBinding {
    public final LinearLayout aprLayout;
    public final TextView availableVote;
    public final TextView bottomLine;
    public final TextView btNext;
    public final View divider;
    public final RelativeLayout fastVoteTips;
    public final ImageView ivApproveIconPop;
    public final LinearLayout liClearAll;
    public final ConstraintLayout llVote;
    public final RelativeLayout rlBottom;
    public final RelativeLayout root;
    private final RelativeLayout rootView;
    public final RecyclerView rvContent;
    public final TextView totalVote;
    public final TextView totalVoteTitle;
    public final TextView tvMultiWarning;
    public final TextView tvVoteTips;
    public final TextView tvVotingApr;
    public final RelativeLayout voteCountLayout;
    public final LinearLayout voteWarning;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcVoteEasyBinding(RelativeLayout relativeLayout, LinearLayout linearLayout, TextView textView, TextView textView2, TextView textView3, View view, RelativeLayout relativeLayout2, ImageView imageView, LinearLayout linearLayout2, ConstraintLayout constraintLayout, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, RecyclerView recyclerView, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, RelativeLayout relativeLayout5, LinearLayout linearLayout3) {
        this.rootView = relativeLayout;
        this.aprLayout = linearLayout;
        this.availableVote = textView;
        this.bottomLine = textView2;
        this.btNext = textView3;
        this.divider = view;
        this.fastVoteTips = relativeLayout2;
        this.ivApproveIconPop = imageView;
        this.liClearAll = linearLayout2;
        this.llVote = constraintLayout;
        this.rlBottom = relativeLayout3;
        this.root = relativeLayout4;
        this.rvContent = recyclerView;
        this.totalVote = textView4;
        this.totalVoteTitle = textView5;
        this.tvMultiWarning = textView6;
        this.tvVoteTips = textView7;
        this.tvVotingApr = textView8;
        this.voteCountLayout = relativeLayout5;
        this.voteWarning = linearLayout3;
    }

    public static AcVoteEasyBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcVoteEasyBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_vote_easy, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcVoteEasyBinding bind(View view) {
        int i = R.id.apr_layout;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.apr_layout);
        if (linearLayout != null) {
            i = R.id.available_vote;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.available_vote);
            if (textView != null) {
                i = R.id.bottom_line;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.bottom_line);
                if (textView2 != null) {
                    i = R.id.bt_next;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.bt_next);
                    if (textView3 != null) {
                        i = R.id.divider;
                        View findChildViewById = ViewBindings.findChildViewById(view, R.id.divider);
                        if (findChildViewById != null) {
                            i = R.id.fast_vote_tips;
                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.fast_vote_tips);
                            if (relativeLayout != null) {
                                i = R.id.iv_approve_icon_pop;
                                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_approve_icon_pop);
                                if (imageView != null) {
                                    i = R.id.li_clear_all;
                                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_clear_all);
                                    if (linearLayout2 != null) {
                                        i = R.id.ll_vote;
                                        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.ll_vote);
                                        if (constraintLayout != null) {
                                            i = R.id.rl_bottom;
                                            RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_bottom);
                                            if (relativeLayout2 != null) {
                                                RelativeLayout relativeLayout3 = (RelativeLayout) view;
                                                i = R.id.rv_content;
                                                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rv_content);
                                                if (recyclerView != null) {
                                                    i = R.id.total_vote;
                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.total_vote);
                                                    if (textView4 != null) {
                                                        i = R.id.total_vote_title;
                                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.total_vote_title);
                                                        if (textView5 != null) {
                                                            i = R.id.tv_multi_warning;
                                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_multi_warning);
                                                            if (textView6 != null) {
                                                                i = R.id.tv_vote_tips;
                                                                TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_vote_tips);
                                                                if (textView7 != null) {
                                                                    i = R.id.tv_voting_apr;
                                                                    TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_voting_apr);
                                                                    if (textView8 != null) {
                                                                        i = R.id.vote_count_layout;
                                                                        RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.vote_count_layout);
                                                                        if (relativeLayout4 != null) {
                                                                            i = R.id.vote_warning;
                                                                            LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.vote_warning);
                                                                            if (linearLayout3 != null) {
                                                                                return new AcVoteEasyBinding(relativeLayout3, linearLayout, textView, textView2, textView3, findChildViewById, relativeLayout, imageView, linearLayout2, constraintLayout, relativeLayout2, relativeLayout3, recyclerView, textView4, textView5, textView6, textView7, textView8, relativeLayout4, linearLayout3);
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
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
