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
public final class FragmentVoteHeaderBinding implements ViewBinding {
    public final TextView alreadyVoteTitle;
    public final TextView availableVoteRightTitle;
    public final RelativeLayout btnGetProfit;
    public final RelativeLayout btnVote;
    public final View centerDivider;
    public final View dashDivider;
    public final ImageView ivTips;
    public final View lineCenter;
    public final LinearLayout llAprContainer;
    public final LinearLayout llContainer;
    public final LinearLayout llVotingContainer;
    public final TextView profitTitle;
    public final RelativeLayout rlAprContainer;
    public final RelativeLayout rlProfitContainer;
    private final RelativeLayout rootView;
    public final RelativeLayout toPromote;
    public final RelativeLayout toStake;
    public final TextView totalVoteRightsTitle;
    public final TextView tvAlreadyVote;
    public final TextView tvApr;
    public final TextView tvAprTitle;
    public final TextView tvAvailableTrx;
    public final TextView tvAvailableVotes;
    public final TextView tvGetProfit;
    public final TextView tvProfit;
    public final TextView tvToPromote;
    public final TextView tvToStake;
    public final TextView tvTotalVoteRights;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FragmentVoteHeaderBinding(RelativeLayout relativeLayout, TextView textView, TextView textView2, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, View view, View view2, ImageView imageView, View view3, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, TextView textView3, RelativeLayout relativeLayout4, RelativeLayout relativeLayout5, RelativeLayout relativeLayout6, RelativeLayout relativeLayout7, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14) {
        this.rootView = relativeLayout;
        this.alreadyVoteTitle = textView;
        this.availableVoteRightTitle = textView2;
        this.btnGetProfit = relativeLayout2;
        this.btnVote = relativeLayout3;
        this.centerDivider = view;
        this.dashDivider = view2;
        this.ivTips = imageView;
        this.lineCenter = view3;
        this.llAprContainer = linearLayout;
        this.llContainer = linearLayout2;
        this.llVotingContainer = linearLayout3;
        this.profitTitle = textView3;
        this.rlAprContainer = relativeLayout4;
        this.rlProfitContainer = relativeLayout5;
        this.toPromote = relativeLayout6;
        this.toStake = relativeLayout7;
        this.totalVoteRightsTitle = textView4;
        this.tvAlreadyVote = textView5;
        this.tvApr = textView6;
        this.tvAprTitle = textView7;
        this.tvAvailableTrx = textView8;
        this.tvAvailableVotes = textView9;
        this.tvGetProfit = textView10;
        this.tvProfit = textView11;
        this.tvToPromote = textView12;
        this.tvToStake = textView13;
        this.tvTotalVoteRights = textView14;
    }

    public static FragmentVoteHeaderBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentVoteHeaderBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_vote_header, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentVoteHeaderBinding bind(View view) {
        int i = R.id.already_vote_title;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.already_vote_title);
        if (textView != null) {
            i = R.id.available_vote_right_title;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.available_vote_right_title);
            if (textView2 != null) {
                i = R.id.btn_get_profit;
                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.btn_get_profit);
                if (relativeLayout != null) {
                    i = R.id.btn_vote;
                    RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.btn_vote);
                    if (relativeLayout2 != null) {
                        i = R.id.center_divider;
                        View findChildViewById = ViewBindings.findChildViewById(view, R.id.center_divider);
                        if (findChildViewById != null) {
                            i = R.id.dash_divider;
                            View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.dash_divider);
                            if (findChildViewById2 != null) {
                                i = R.id.iv_tips;
                                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_tips);
                                if (imageView != null) {
                                    i = R.id.line_center;
                                    View findChildViewById3 = ViewBindings.findChildViewById(view, R.id.line_center);
                                    if (findChildViewById3 != null) {
                                        i = R.id.ll_apr_container;
                                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_apr_container);
                                        if (linearLayout != null) {
                                            i = R.id.ll_container;
                                            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_container);
                                            if (linearLayout2 != null) {
                                                i = R.id.ll_voting_container;
                                                LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_voting_container);
                                                if (linearLayout3 != null) {
                                                    i = R.id.profit_title;
                                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.profit_title);
                                                    if (textView3 != null) {
                                                        i = R.id.rl_apr_container;
                                                        RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_apr_container);
                                                        if (relativeLayout3 != null) {
                                                            i = R.id.rl_profit_container;
                                                            RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_profit_container);
                                                            if (relativeLayout4 != null) {
                                                                i = R.id.to_promote;
                                                                RelativeLayout relativeLayout5 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.to_promote);
                                                                if (relativeLayout5 != null) {
                                                                    i = R.id.to_stake;
                                                                    RelativeLayout relativeLayout6 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.to_stake);
                                                                    if (relativeLayout6 != null) {
                                                                        i = R.id.total_vote_rights_title;
                                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.total_vote_rights_title);
                                                                        if (textView4 != null) {
                                                                            i = R.id.tv_already_vote;
                                                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_already_vote);
                                                                            if (textView5 != null) {
                                                                                i = R.id.tv_apr;
                                                                                TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_apr);
                                                                                if (textView6 != null) {
                                                                                    i = R.id.tv_apr_title;
                                                                                    TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_apr_title);
                                                                                    if (textView7 != null) {
                                                                                        i = R.id.tv_available_trx;
                                                                                        TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_available_trx);
                                                                                        if (textView8 != null) {
                                                                                            i = R.id.tv_available_votes;
                                                                                            TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_available_votes);
                                                                                            if (textView9 != null) {
                                                                                                i = R.id.tv_get_profit;
                                                                                                TextView textView10 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_get_profit);
                                                                                                if (textView10 != null) {
                                                                                                    i = R.id.tv_profit;
                                                                                                    TextView textView11 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_profit);
                                                                                                    if (textView11 != null) {
                                                                                                        i = R.id.tv_to_promote;
                                                                                                        TextView textView12 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_to_promote);
                                                                                                        if (textView12 != null) {
                                                                                                            i = R.id.tv_to_stake;
                                                                                                            TextView textView13 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_to_stake);
                                                                                                            if (textView13 != null) {
                                                                                                                i = R.id.tv_total_vote_rights;
                                                                                                                TextView textView14 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_total_vote_rights);
                                                                                                                if (textView14 != null) {
                                                                                                                    return new FragmentVoteHeaderBinding((RelativeLayout) view, textView, textView2, relativeLayout, relativeLayout2, findChildViewById, findChildViewById2, imageView, findChildViewById3, linearLayout, linearLayout2, linearLayout3, textView3, relativeLayout3, relativeLayout4, relativeLayout5, relativeLayout6, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14);
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
