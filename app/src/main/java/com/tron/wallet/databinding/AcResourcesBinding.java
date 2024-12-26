package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.CircularProgressView;
import com.tron.wallet.common.components.ptr.PtrHTFrameLayout;
import com.tronlinkpro.wallet.R;
public final class AcResourcesBinding implements ViewBinding {
    public final TextView btStake;
    public final TextView btUnstake;
    public final TextView btVote;
    public final RelativeLayout headerLayout;
    public final ImageView ivAvailableTrxTip;
    public final ImageView ivAvailableVotesTip;
    public final ImageView ivBack;
    public final ImageView ivBandwidthTip;
    public final ImageView ivEnergyTip;
    public final ImageView ivHelp;
    public final LinearLayout llContent;
    public final NestedScrollView llScroll;
    public final LinearLayout llVote;
    public final CircularProgressView progressBandwidth;
    public final CircularProgressView progressEnergy;
    public final PtrHTFrameLayout rcFrame;
    public final LinearLayout root;
    private final LinearLayout rootView;
    public final TextView tvBandwidth;
    public final TextView tvBandwidthTotal;
    public final TextView tvEnergy;
    public final TextView tvEnergyTotal;
    public final TextView tvMainTitle;
    public final TextView tvStakedDetails;
    public final TextView tvTitleBandwidth;
    public final TextView tvTitleEnergy;
    public final TextView tvTrxBalance;
    public final TextView tvVoteTip;
    public final TextView tvVotingApr;
    public final TextView tvVotingTps;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private AcResourcesBinding(LinearLayout linearLayout, TextView textView, TextView textView2, TextView textView3, RelativeLayout relativeLayout, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView imageView6, LinearLayout linearLayout2, NestedScrollView nestedScrollView, LinearLayout linearLayout3, CircularProgressView circularProgressView, CircularProgressView circularProgressView2, PtrHTFrameLayout ptrHTFrameLayout, LinearLayout linearLayout4, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14, TextView textView15) {
        this.rootView = linearLayout;
        this.btStake = textView;
        this.btUnstake = textView2;
        this.btVote = textView3;
        this.headerLayout = relativeLayout;
        this.ivAvailableTrxTip = imageView;
        this.ivAvailableVotesTip = imageView2;
        this.ivBack = imageView3;
        this.ivBandwidthTip = imageView4;
        this.ivEnergyTip = imageView5;
        this.ivHelp = imageView6;
        this.llContent = linearLayout2;
        this.llScroll = nestedScrollView;
        this.llVote = linearLayout3;
        this.progressBandwidth = circularProgressView;
        this.progressEnergy = circularProgressView2;
        this.rcFrame = ptrHTFrameLayout;
        this.root = linearLayout4;
        this.tvBandwidth = textView4;
        this.tvBandwidthTotal = textView5;
        this.tvEnergy = textView6;
        this.tvEnergyTotal = textView7;
        this.tvMainTitle = textView8;
        this.tvStakedDetails = textView9;
        this.tvTitleBandwidth = textView10;
        this.tvTitleEnergy = textView11;
        this.tvTrxBalance = textView12;
        this.tvVoteTip = textView13;
        this.tvVotingApr = textView14;
        this.tvVotingTps = textView15;
    }

    public static AcResourcesBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcResourcesBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_resources, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcResourcesBinding bind(View view) {
        int i = R.id.bt_stake;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.bt_stake);
        if (textView != null) {
            i = R.id.bt_unstake;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.bt_unstake);
            if (textView2 != null) {
                i = R.id.bt_vote;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.bt_vote);
                if (textView3 != null) {
                    i = R.id.header_layout;
                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.header_layout);
                    if (relativeLayout != null) {
                        i = R.id.iv_available_trx_tip;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_available_trx_tip);
                        if (imageView != null) {
                            i = R.id.iv_available_votes_tip;
                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_available_votes_tip);
                            if (imageView2 != null) {
                                i = R.id.iv_back;
                                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_back);
                                if (imageView3 != null) {
                                    i = R.id.iv_bandwidth_tip;
                                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_bandwidth_tip);
                                    if (imageView4 != null) {
                                        i = R.id.iv_energy_tip;
                                        ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_energy_tip);
                                        if (imageView5 != null) {
                                            i = R.id.iv_help;
                                            ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_help);
                                            if (imageView6 != null) {
                                                i = R.id.ll_content;
                                                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_content);
                                                if (linearLayout != null) {
                                                    i = R.id.ll_scroll;
                                                    NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(view, R.id.ll_scroll);
                                                    if (nestedScrollView != null) {
                                                        i = R.id.ll_vote;
                                                        LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_vote);
                                                        if (linearLayout2 != null) {
                                                            i = R.id.progress_bandwidth;
                                                            CircularProgressView circularProgressView = (CircularProgressView) ViewBindings.findChildViewById(view, R.id.progress_bandwidth);
                                                            if (circularProgressView != null) {
                                                                i = R.id.progress_energy;
                                                                CircularProgressView circularProgressView2 = (CircularProgressView) ViewBindings.findChildViewById(view, R.id.progress_energy);
                                                                if (circularProgressView2 != null) {
                                                                    i = R.id.rc_frame;
                                                                    PtrHTFrameLayout ptrHTFrameLayout = (PtrHTFrameLayout) ViewBindings.findChildViewById(view, R.id.rc_frame);
                                                                    if (ptrHTFrameLayout != null) {
                                                                        LinearLayout linearLayout3 = (LinearLayout) view;
                                                                        i = R.id.tv_bandwidth;
                                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_bandwidth);
                                                                        if (textView4 != null) {
                                                                            i = R.id.tv_bandwidth_total;
                                                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_bandwidth_total);
                                                                            if (textView5 != null) {
                                                                                i = R.id.tv_energy;
                                                                                TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_energy);
                                                                                if (textView6 != null) {
                                                                                    i = R.id.tv_energy_total;
                                                                                    TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_energy_total);
                                                                                    if (textView7 != null) {
                                                                                        i = R.id.tv_main_title;
                                                                                        TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_main_title);
                                                                                        if (textView8 != null) {
                                                                                            i = R.id.tv_staked_details;
                                                                                            TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_staked_details);
                                                                                            if (textView9 != null) {
                                                                                                i = R.id.tv_title_bandwidth;
                                                                                                TextView textView10 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title_bandwidth);
                                                                                                if (textView10 != null) {
                                                                                                    i = R.id.tv_title_energy;
                                                                                                    TextView textView11 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title_energy);
                                                                                                    if (textView11 != null) {
                                                                                                        i = R.id.tv_trx_balance;
                                                                                                        TextView textView12 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_trx_balance);
                                                                                                        if (textView12 != null) {
                                                                                                            i = R.id.tv_vote_tip;
                                                                                                            TextView textView13 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_vote_tip);
                                                                                                            if (textView13 != null) {
                                                                                                                i = R.id.tv_voting_apr;
                                                                                                                TextView textView14 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_voting_apr);
                                                                                                                if (textView14 != null) {
                                                                                                                    i = R.id.tv_voting_tps;
                                                                                                                    TextView textView15 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_voting_tps);
                                                                                                                    if (textView15 != null) {
                                                                                                                        return new AcResourcesBinding(linearLayout3, textView, textView2, textView3, relativeLayout, imageView, imageView2, imageView3, imageView4, imageView5, imageView6, linearLayout, nestedScrollView, linearLayout2, circularProgressView, circularProgressView2, ptrHTFrameLayout, linearLayout3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14, textView15);
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
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
