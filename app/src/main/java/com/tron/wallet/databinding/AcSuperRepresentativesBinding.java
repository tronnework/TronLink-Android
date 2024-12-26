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
public final class AcSuperRepresentativesBinding implements ViewBinding {
    public final RelativeLayout b2;
    public final TextView btnVote;
    public final TextView btnVotedCancel;
    public final TextView btnVotedUpdate;
    public final LinearLayout btnsNoVote;
    public final LinearLayout btnsVoted;
    public final View divider;
    public final ImageView icSuperRepresentative;
    public final ImageView ivBack;
    public final ImageView ivCopy;
    public final ImageView ivUrlCopy;
    public final ImageView ivVoteAprTips;
    public final LinearLayout liButtons;
    public final LinearLayout llBack;
    public final LinearLayout llCopy;
    public final LinearLayout llVoted2;
    public final LinearLayout rlBottom;
    public final RelativeLayout rlHead;
    public final RelativeLayout rlTitle;
    public final RelativeLayout rlTop;
    public final RelativeLayout rlVotePercent;
    public final RelativeLayout rlYield;
    private final RelativeLayout rootView;
    public final TextView title;
    public final TextView tvAddress;
    public final TextView tvBlockRadio;
    public final TextView tvMultiWarning;
    public final TextView tvName;
    public final TextView tvPercentage;
    public final TextView tvProduced;
    public final TextView tvProfits;
    public final TextView tvRanking;
    public final TextView tvTotalVote;
    public final TextView tvUrl;
    public final TextView tvVoteSuper2;
    public final TextView tvVotedNumber;
    public final TextView tvVotingOnsiderations;
    public final TextView tvYield;
    public final TextView tvYield2;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcSuperRepresentativesBinding(RelativeLayout relativeLayout, RelativeLayout relativeLayout2, TextView textView, TextView textView2, TextView textView3, LinearLayout linearLayout, LinearLayout linearLayout2, View view, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, LinearLayout linearLayout3, LinearLayout linearLayout4, LinearLayout linearLayout5, LinearLayout linearLayout6, LinearLayout linearLayout7, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, RelativeLayout relativeLayout5, RelativeLayout relativeLayout6, RelativeLayout relativeLayout7, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14, TextView textView15, TextView textView16, TextView textView17, TextView textView18, TextView textView19) {
        this.rootView = relativeLayout;
        this.b2 = relativeLayout2;
        this.btnVote = textView;
        this.btnVotedCancel = textView2;
        this.btnVotedUpdate = textView3;
        this.btnsNoVote = linearLayout;
        this.btnsVoted = linearLayout2;
        this.divider = view;
        this.icSuperRepresentative = imageView;
        this.ivBack = imageView2;
        this.ivCopy = imageView3;
        this.ivUrlCopy = imageView4;
        this.ivVoteAprTips = imageView5;
        this.liButtons = linearLayout3;
        this.llBack = linearLayout4;
        this.llCopy = linearLayout5;
        this.llVoted2 = linearLayout6;
        this.rlBottom = linearLayout7;
        this.rlHead = relativeLayout3;
        this.rlTitle = relativeLayout4;
        this.rlTop = relativeLayout5;
        this.rlVotePercent = relativeLayout6;
        this.rlYield = relativeLayout7;
        this.title = textView4;
        this.tvAddress = textView5;
        this.tvBlockRadio = textView6;
        this.tvMultiWarning = textView7;
        this.tvName = textView8;
        this.tvPercentage = textView9;
        this.tvProduced = textView10;
        this.tvProfits = textView11;
        this.tvRanking = textView12;
        this.tvTotalVote = textView13;
        this.tvUrl = textView14;
        this.tvVoteSuper2 = textView15;
        this.tvVotedNumber = textView16;
        this.tvVotingOnsiderations = textView17;
        this.tvYield = textView18;
        this.tvYield2 = textView19;
    }

    public static AcSuperRepresentativesBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcSuperRepresentativesBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_super_representatives, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcSuperRepresentativesBinding bind(View view) {
        int i = R.id.b2;
        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.b2);
        if (relativeLayout != null) {
            i = R.id.btn_vote;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.btn_vote);
            if (textView != null) {
                i = R.id.btn_voted_cancel;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.btn_voted_cancel);
                if (textView2 != null) {
                    i = R.id.btn_voted_update;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.btn_voted_update);
                    if (textView3 != null) {
                        i = R.id.btns_no_vote;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.btns_no_vote);
                        if (linearLayout != null) {
                            i = R.id.btns_voted;
                            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.btns_voted);
                            if (linearLayout2 != null) {
                                i = R.id.divider;
                                View findChildViewById = ViewBindings.findChildViewById(view, R.id.divider);
                                if (findChildViewById != null) {
                                    i = R.id.ic_super_representative;
                                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.ic_super_representative);
                                    if (imageView != null) {
                                        i = R.id.iv_back;
                                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_back);
                                        if (imageView2 != null) {
                                            i = R.id.iv_copy;
                                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_copy);
                                            if (imageView3 != null) {
                                                i = R.id.iv_url_copy;
                                                ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_url_copy);
                                                if (imageView4 != null) {
                                                    i = R.id.iv_vote_apr_tips;
                                                    ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_vote_apr_tips);
                                                    if (imageView5 != null) {
                                                        i = R.id.li_buttons;
                                                        LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_buttons);
                                                        if (linearLayout3 != null) {
                                                            i = R.id.ll_back;
                                                            LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_back);
                                                            if (linearLayout4 != null) {
                                                                i = R.id.ll_copy;
                                                                LinearLayout linearLayout5 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_copy);
                                                                if (linearLayout5 != null) {
                                                                    i = R.id.ll_voted2;
                                                                    LinearLayout linearLayout6 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_voted2);
                                                                    if (linearLayout6 != null) {
                                                                        i = R.id.rl_bottom;
                                                                        LinearLayout linearLayout7 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.rl_bottom);
                                                                        if (linearLayout7 != null) {
                                                                            i = R.id.rl_head;
                                                                            RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_head);
                                                                            if (relativeLayout2 != null) {
                                                                                i = R.id.rl_title;
                                                                                RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_title);
                                                                                if (relativeLayout3 != null) {
                                                                                    i = R.id.rl_top;
                                                                                    RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_top);
                                                                                    if (relativeLayout4 != null) {
                                                                                        i = R.id.rl_vote_percent;
                                                                                        RelativeLayout relativeLayout5 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_vote_percent);
                                                                                        if (relativeLayout5 != null) {
                                                                                            i = R.id.rl_yield;
                                                                                            RelativeLayout relativeLayout6 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_yield);
                                                                                            if (relativeLayout6 != null) {
                                                                                                i = R.id.title;
                                                                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.title);
                                                                                                if (textView4 != null) {
                                                                                                    i = R.id.tv_address;
                                                                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address);
                                                                                                    if (textView5 != null) {
                                                                                                        i = R.id.tv_block_radio;
                                                                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_block_radio);
                                                                                                        if (textView6 != null) {
                                                                                                            i = R.id.tv_multi_warning;
                                                                                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_multi_warning);
                                                                                                            if (textView7 != null) {
                                                                                                                i = R.id.tv_name;
                                                                                                                TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name);
                                                                                                                if (textView8 != null) {
                                                                                                                    i = R.id.tv_percentage;
                                                                                                                    TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_percentage);
                                                                                                                    if (textView9 != null) {
                                                                                                                        i = R.id.tv_produced;
                                                                                                                        TextView textView10 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_produced);
                                                                                                                        if (textView10 != null) {
                                                                                                                            i = R.id.tv_profits;
                                                                                                                            TextView textView11 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_profits);
                                                                                                                            if (textView11 != null) {
                                                                                                                                i = R.id.tv_ranking;
                                                                                                                                TextView textView12 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_ranking);
                                                                                                                                if (textView12 != null) {
                                                                                                                                    i = R.id.tv_total_vote;
                                                                                                                                    TextView textView13 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_total_vote);
                                                                                                                                    if (textView13 != null) {
                                                                                                                                        i = R.id.tv_url;
                                                                                                                                        TextView textView14 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_url);
                                                                                                                                        if (textView14 != null) {
                                                                                                                                            i = R.id.tv_vote_super_2;
                                                                                                                                            TextView textView15 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_vote_super_2);
                                                                                                                                            if (textView15 != null) {
                                                                                                                                                i = R.id.tv_voted_number;
                                                                                                                                                TextView textView16 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_voted_number);
                                                                                                                                                if (textView16 != null) {
                                                                                                                                                    i = R.id.tv_voting_onsiderations;
                                                                                                                                                    TextView textView17 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_voting_onsiderations);
                                                                                                                                                    if (textView17 != null) {
                                                                                                                                                        i = R.id.tv_yield;
                                                                                                                                                        TextView textView18 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_yield);
                                                                                                                                                        if (textView18 != null) {
                                                                                                                                                            i = R.id.tv_yield_2;
                                                                                                                                                            TextView textView19 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_yield_2);
                                                                                                                                                            if (textView19 != null) {
                                                                                                                                                                return new AcSuperRepresentativesBinding((RelativeLayout) view, relativeLayout, textView, textView2, textView3, linearLayout, linearLayout2, findChildViewById, imageView, imageView2, imageView3, imageView4, imageView5, linearLayout3, linearLayout4, linearLayout5, linearLayout6, linearLayout7, relativeLayout2, relativeLayout3, relativeLayout4, relativeLayout5, relativeLayout6, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14, textView15, textView16, textView17, textView18, textView19);
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
