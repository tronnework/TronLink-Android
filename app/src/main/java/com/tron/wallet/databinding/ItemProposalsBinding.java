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
import com.tron.wallet.common.components.countdownview.CountdownView;
import com.tronlinkpro.wallet.R;
public final class ItemProposalsBinding implements ViewBinding {
    public final CountdownView cvCountdownView;
    public final ImageView ivAgree;
    public final ImageView ivBottom;
    public final ImageView ivLine;
    public final ImageView ivTop;
    public final LinearLayout llCommittee;
    public final TextView numAllVotes;
    public final TextView numValidVotes;
    public final RelativeLayout rlAgree;
    public final RelativeLayout rlTime;
    private final LinearLayout rootView;
    public final TextView titleAllVotes;
    public final TextView titleValidVotes;
    public final TextView tvCommitteeContent;
    public final TextView tvCreateTime;
    public final TextView tvProposalsId;
    public final TextView tvProposalsName;
    public final TextView tvProposalsState;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private ItemProposalsBinding(LinearLayout linearLayout, CountdownView countdownView, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, LinearLayout linearLayout2, TextView textView, TextView textView2, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9) {
        this.rootView = linearLayout;
        this.cvCountdownView = countdownView;
        this.ivAgree = imageView;
        this.ivBottom = imageView2;
        this.ivLine = imageView3;
        this.ivTop = imageView4;
        this.llCommittee = linearLayout2;
        this.numAllVotes = textView;
        this.numValidVotes = textView2;
        this.rlAgree = relativeLayout;
        this.rlTime = relativeLayout2;
        this.titleAllVotes = textView3;
        this.titleValidVotes = textView4;
        this.tvCommitteeContent = textView5;
        this.tvCreateTime = textView6;
        this.tvProposalsId = textView7;
        this.tvProposalsName = textView8;
        this.tvProposalsState = textView9;
    }

    public static ItemProposalsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemProposalsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_proposals, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemProposalsBinding bind(View view) {
        int i = R.id.cv_countdownView;
        CountdownView countdownView = (CountdownView) ViewBindings.findChildViewById(view, R.id.cv_countdownView);
        if (countdownView != null) {
            i = R.id.iv_agree;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_agree);
            if (imageView != null) {
                i = R.id.iv_bottom;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_bottom);
                if (imageView2 != null) {
                    i = R.id.iv_line;
                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_line);
                    if (imageView3 != null) {
                        i = R.id.iv_top;
                        ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_top);
                        if (imageView4 != null) {
                            i = R.id.ll_committee;
                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_committee);
                            if (linearLayout != null) {
                                i = R.id.num_all_votes;
                                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.num_all_votes);
                                if (textView != null) {
                                    i = R.id.num_valid_votes;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.num_valid_votes);
                                    if (textView2 != null) {
                                        i = R.id.rl_agree;
                                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_agree);
                                        if (relativeLayout != null) {
                                            i = R.id.rl_time;
                                            RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_time);
                                            if (relativeLayout2 != null) {
                                                i = R.id.title_all_votes;
                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.title_all_votes);
                                                if (textView3 != null) {
                                                    i = R.id.title_valid_votes;
                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.title_valid_votes);
                                                    if (textView4 != null) {
                                                        i = R.id.tv_committee_content;
                                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_committee_content);
                                                        if (textView5 != null) {
                                                            i = R.id.tv_create_time;
                                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_create_time);
                                                            if (textView6 != null) {
                                                                i = R.id.tv_proposals_id;
                                                                TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_proposals_id);
                                                                if (textView7 != null) {
                                                                    i = R.id.tv_proposals_name;
                                                                    TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_proposals_name);
                                                                    if (textView8 != null) {
                                                                        i = R.id.tv_proposals_state;
                                                                        TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_proposals_state);
                                                                        if (textView9 != null) {
                                                                            return new ItemProposalsBinding((LinearLayout) view, countdownView, imageView, imageView2, imageView3, imageView4, linearLayout, textView, textView2, relativeLayout, relativeLayout2, textView3, textView4, textView5, textView6, textView7, textView8, textView9);
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
