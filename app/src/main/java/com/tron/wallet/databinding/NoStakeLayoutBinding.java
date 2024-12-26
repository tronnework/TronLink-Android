package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class NoStakeLayoutBinding implements ViewBinding {
    public final TextView btnStakeNoStake;
    public final ImageView iconStakeDot1;
    public final TextView iconStakeDot1Content;
    public final TextView iconStakeDot1Title;
    public final ImageView iconStakeDot2;
    public final TextView iconStakeDot2Content;
    public final TextView iconStakeDot2Title;
    public final ImageView iconStakeDot3;
    public final TextView iconStakeDot3Content;
    public final TextView iconStakeDot3Title;
    public final ImageView ivStakeNewIconBig;
    public final RelativeLayout layoutStakeDot1;
    public final RelativeLayout layoutStakeDot2;
    public final RelativeLayout layoutStakeDot3;
    public final TextView learnMoreNoStake;
    public final RelativeLayout rlNoStake;
    private final RelativeLayout rootView;
    public final TextView tvDotTitle;
    public final TextView tvJoinStakeDescription;
    public final TextView tvJoinStakeTitle;
    public final TextView tvStakeUpdateTips;
    public final View viewTopBg;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private NoStakeLayoutBinding(RelativeLayout relativeLayout, TextView textView, ImageView imageView, TextView textView2, TextView textView3, ImageView imageView2, TextView textView4, TextView textView5, ImageView imageView3, TextView textView6, TextView textView7, ImageView imageView4, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, TextView textView8, RelativeLayout relativeLayout5, TextView textView9, TextView textView10, TextView textView11, TextView textView12, View view) {
        this.rootView = relativeLayout;
        this.btnStakeNoStake = textView;
        this.iconStakeDot1 = imageView;
        this.iconStakeDot1Content = textView2;
        this.iconStakeDot1Title = textView3;
        this.iconStakeDot2 = imageView2;
        this.iconStakeDot2Content = textView4;
        this.iconStakeDot2Title = textView5;
        this.iconStakeDot3 = imageView3;
        this.iconStakeDot3Content = textView6;
        this.iconStakeDot3Title = textView7;
        this.ivStakeNewIconBig = imageView4;
        this.layoutStakeDot1 = relativeLayout2;
        this.layoutStakeDot2 = relativeLayout3;
        this.layoutStakeDot3 = relativeLayout4;
        this.learnMoreNoStake = textView8;
        this.rlNoStake = relativeLayout5;
        this.tvDotTitle = textView9;
        this.tvJoinStakeDescription = textView10;
        this.tvJoinStakeTitle = textView11;
        this.tvStakeUpdateTips = textView12;
        this.viewTopBg = view;
    }

    public static NoStakeLayoutBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static NoStakeLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.no_stake_layout, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static NoStakeLayoutBinding bind(View view) {
        int i = R.id.btn_stake_no_stake;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.btn_stake_no_stake);
        if (textView != null) {
            i = R.id.icon_stake_dot_1;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.icon_stake_dot_1);
            if (imageView != null) {
                i = R.id.icon_stake_dot_1_content;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.icon_stake_dot_1_content);
                if (textView2 != null) {
                    i = R.id.icon_stake_dot_1_title;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.icon_stake_dot_1_title);
                    if (textView3 != null) {
                        i = R.id.icon_stake_dot_2;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.icon_stake_dot_2);
                        if (imageView2 != null) {
                            i = R.id.icon_stake_dot_2_content;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.icon_stake_dot_2_content);
                            if (textView4 != null) {
                                i = R.id.icon_stake_dot_2_title;
                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.icon_stake_dot_2_title);
                                if (textView5 != null) {
                                    i = R.id.icon_stake_dot_3;
                                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.icon_stake_dot_3);
                                    if (imageView3 != null) {
                                        i = R.id.icon_stake_dot_3_content;
                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.icon_stake_dot_3_content);
                                        if (textView6 != null) {
                                            i = R.id.icon_stake_dot_3_title;
                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.icon_stake_dot_3_title);
                                            if (textView7 != null) {
                                                i = R.id.iv_stake_new_icon_big;
                                                ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_stake_new_icon_big);
                                                if (imageView4 != null) {
                                                    i = R.id.layout_stake_dot_1;
                                                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.layout_stake_dot_1);
                                                    if (relativeLayout != null) {
                                                        i = R.id.layout_stake_dot_2;
                                                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.layout_stake_dot_2);
                                                        if (relativeLayout2 != null) {
                                                            i = R.id.layout_stake_dot_3;
                                                            RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.layout_stake_dot_3);
                                                            if (relativeLayout3 != null) {
                                                                i = R.id.learn_more_no_stake;
                                                                TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.learn_more_no_stake);
                                                                if (textView8 != null) {
                                                                    RelativeLayout relativeLayout4 = (RelativeLayout) view;
                                                                    i = R.id.tv_dot_title;
                                                                    TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_dot_title);
                                                                    if (textView9 != null) {
                                                                        i = R.id.tv_join_stake_description;
                                                                        TextView textView10 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_join_stake_description);
                                                                        if (textView10 != null) {
                                                                            i = R.id.tv_join_stake_title;
                                                                            TextView textView11 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_join_stake_title);
                                                                            if (textView11 != null) {
                                                                                i = R.id.tv_stake_update_tips;
                                                                                TextView textView12 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_stake_update_tips);
                                                                                if (textView12 != null) {
                                                                                    i = R.id.view_top_bg;
                                                                                    View findChildViewById = ViewBindings.findChildViewById(view, R.id.view_top_bg);
                                                                                    if (findChildViewById != null) {
                                                                                        return new NoStakeLayoutBinding(relativeLayout4, textView, imageView, textView2, textView3, imageView2, textView4, textView5, imageView3, textView6, textView7, imageView4, relativeLayout, relativeLayout2, relativeLayout3, textView8, relativeLayout4, textView9, textView10, textView11, textView12, findChildViewById);
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
