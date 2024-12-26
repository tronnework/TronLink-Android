package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.ZFlowLayout;
import com.tronlinkpro.wallet.R;
public final class AcProposalsContentBinding implements ViewBinding {
    public final View line0;
    public final RelativeLayout llCommittee;
    public final LinearLayout llOperation;
    public final LinearLayout llProposalsName;
    public final TextView numAllVotes;
    public final TextView numValidVotes;
    public final RelativeLayout rlVoteDetail;
    private final RelativeLayout rootView;
    public final RecyclerView rvProposal;
    public final NestedScrollView scollContent;
    public final TextView titleAllVotes;
    public final TextView titleValidVotes;
    public final TextView tvCreateTime;
    public final TextView tvEndTime;
    public final TextView tvNoApprovers;
    public final TextView tvProposalsAgree;
    public final TextView tvProposalsCancle;
    public final TextView tvProposalsId;
    public final TextView tvProposalsName;
    public final TextView tvProposalsState;
    public final TextView tvProposalsUnclick;
    public final ZFlowLayout zflCandidates;
    public final ZFlowLayout zflPartners;
    public final ZFlowLayout zflRepresentatives;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcProposalsContentBinding(RelativeLayout relativeLayout, View view, RelativeLayout relativeLayout2, LinearLayout linearLayout, LinearLayout linearLayout2, TextView textView, TextView textView2, RelativeLayout relativeLayout3, RecyclerView recyclerView, NestedScrollView nestedScrollView, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, ZFlowLayout zFlowLayout, ZFlowLayout zFlowLayout2, ZFlowLayout zFlowLayout3) {
        this.rootView = relativeLayout;
        this.line0 = view;
        this.llCommittee = relativeLayout2;
        this.llOperation = linearLayout;
        this.llProposalsName = linearLayout2;
        this.numAllVotes = textView;
        this.numValidVotes = textView2;
        this.rlVoteDetail = relativeLayout3;
        this.rvProposal = recyclerView;
        this.scollContent = nestedScrollView;
        this.titleAllVotes = textView3;
        this.titleValidVotes = textView4;
        this.tvCreateTime = textView5;
        this.tvEndTime = textView6;
        this.tvNoApprovers = textView7;
        this.tvProposalsAgree = textView8;
        this.tvProposalsCancle = textView9;
        this.tvProposalsId = textView10;
        this.tvProposalsName = textView11;
        this.tvProposalsState = textView12;
        this.tvProposalsUnclick = textView13;
        this.zflCandidates = zFlowLayout;
        this.zflPartners = zFlowLayout2;
        this.zflRepresentatives = zFlowLayout3;
    }

    public static AcProposalsContentBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcProposalsContentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_proposals_content, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcProposalsContentBinding bind(View view) {
        int i = R.id.line0;
        View findChildViewById = ViewBindings.findChildViewById(view, R.id.line0);
        if (findChildViewById != null) {
            i = R.id.ll_committee;
            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_committee);
            if (relativeLayout != null) {
                i = R.id.ll_operation;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_operation);
                if (linearLayout != null) {
                    i = R.id.ll_proposals_name;
                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_proposals_name);
                    if (linearLayout2 != null) {
                        i = R.id.num_all_votes;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.num_all_votes);
                        if (textView != null) {
                            i = R.id.num_valid_votes;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.num_valid_votes);
                            if (textView2 != null) {
                                i = R.id.rl_vote_detail;
                                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_vote_detail);
                                if (relativeLayout2 != null) {
                                    i = R.id.rv_proposal;
                                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rv_proposal);
                                    if (recyclerView != null) {
                                        i = R.id.scoll_content;
                                        NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(view, R.id.scoll_content);
                                        if (nestedScrollView != null) {
                                            i = R.id.title_all_votes;
                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.title_all_votes);
                                            if (textView3 != null) {
                                                i = R.id.title_valid_votes;
                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.title_valid_votes);
                                                if (textView4 != null) {
                                                    i = R.id.tv_create_time;
                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_create_time);
                                                    if (textView5 != null) {
                                                        i = R.id.tv_end_time;
                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_end_time);
                                                        if (textView6 != null) {
                                                            i = R.id.tv_no_approvers;
                                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_no_approvers);
                                                            if (textView7 != null) {
                                                                i = R.id.tv_proposals_agree;
                                                                TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_proposals_agree);
                                                                if (textView8 != null) {
                                                                    i = R.id.tv_proposals_cancle;
                                                                    TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_proposals_cancle);
                                                                    if (textView9 != null) {
                                                                        i = R.id.tv_proposals_id;
                                                                        TextView textView10 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_proposals_id);
                                                                        if (textView10 != null) {
                                                                            i = R.id.tv_proposals_name;
                                                                            TextView textView11 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_proposals_name);
                                                                            if (textView11 != null) {
                                                                                i = R.id.tv_proposals_state;
                                                                                TextView textView12 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_proposals_state);
                                                                                if (textView12 != null) {
                                                                                    i = R.id.tv_proposals_unclick;
                                                                                    TextView textView13 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_proposals_unclick);
                                                                                    if (textView13 != null) {
                                                                                        i = R.id.zfl_candidates;
                                                                                        ZFlowLayout zFlowLayout = (ZFlowLayout) ViewBindings.findChildViewById(view, R.id.zfl_candidates);
                                                                                        if (zFlowLayout != null) {
                                                                                            i = R.id.zfl_partners;
                                                                                            ZFlowLayout zFlowLayout2 = (ZFlowLayout) ViewBindings.findChildViewById(view, R.id.zfl_partners);
                                                                                            if (zFlowLayout2 != null) {
                                                                                                i = R.id.zfl_representatives;
                                                                                                ZFlowLayout zFlowLayout3 = (ZFlowLayout) ViewBindings.findChildViewById(view, R.id.zfl_representatives);
                                                                                                if (zFlowLayout3 != null) {
                                                                                                    return new AcProposalsContentBinding((RelativeLayout) view, findChildViewById, relativeLayout, linearLayout, linearLayout2, textView, textView2, relativeLayout2, recyclerView, nestedScrollView, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, zFlowLayout, zFlowLayout2, zFlowLayout3);
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
