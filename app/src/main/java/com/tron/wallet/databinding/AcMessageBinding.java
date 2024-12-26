package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class AcMessageBinding implements ViewBinding {
    public final FrameLayout content;
    public final ImageView ivAll;
    public final TextView ivAllCount;
    public final ImageView ivEventReward;
    public final ImageView ivOther;
    public final ImageView ivTransfer;
    public final LinearLayout llAll;
    public final LinearLayout llEventReward;
    public final LinearLayout llOther;
    public final LinearLayout llTransfer;
    public final LinearLayout rlAll;
    public final LinearLayout rlEventReward;
    public final LinearLayout rlOther;
    public final LinearLayout rlTransfer;
    private final LinearLayout rootView;
    public final TextView tvAll;
    public final TextView tvEventReward;
    public final TextView tvEventRewardCount;
    public final TextView tvOther;
    public final TextView tvOtherCount;
    public final TextView tvTransfer;
    public final TextView tvTransferCount;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private AcMessageBinding(LinearLayout linearLayout, FrameLayout frameLayout, ImageView imageView, TextView textView, ImageView imageView2, ImageView imageView3, ImageView imageView4, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, LinearLayout linearLayout5, LinearLayout linearLayout6, LinearLayout linearLayout7, LinearLayout linearLayout8, LinearLayout linearLayout9, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8) {
        this.rootView = linearLayout;
        this.content = frameLayout;
        this.ivAll = imageView;
        this.ivAllCount = textView;
        this.ivEventReward = imageView2;
        this.ivOther = imageView3;
        this.ivTransfer = imageView4;
        this.llAll = linearLayout2;
        this.llEventReward = linearLayout3;
        this.llOther = linearLayout4;
        this.llTransfer = linearLayout5;
        this.rlAll = linearLayout6;
        this.rlEventReward = linearLayout7;
        this.rlOther = linearLayout8;
        this.rlTransfer = linearLayout9;
        this.tvAll = textView2;
        this.tvEventReward = textView3;
        this.tvEventRewardCount = textView4;
        this.tvOther = textView5;
        this.tvOtherCount = textView6;
        this.tvTransfer = textView7;
        this.tvTransferCount = textView8;
    }

    public static AcMessageBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcMessageBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_message, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcMessageBinding bind(View view) {
        int i = R.id.content;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.content);
        if (frameLayout != null) {
            i = R.id.iv_all;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_all);
            if (imageView != null) {
                i = R.id.iv_all_count;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.iv_all_count);
                if (textView != null) {
                    i = R.id.iv_event_reward;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_event_reward);
                    if (imageView2 != null) {
                        i = R.id.iv_other;
                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_other);
                        if (imageView3 != null) {
                            i = R.id.iv_transfer;
                            ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_transfer);
                            if (imageView4 != null) {
                                i = R.id.ll_all;
                                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_all);
                                if (linearLayout != null) {
                                    i = R.id.ll_event_reward;
                                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_event_reward);
                                    if (linearLayout2 != null) {
                                        i = R.id.ll_other;
                                        LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_other);
                                        if (linearLayout3 != null) {
                                            i = R.id.ll_transfer;
                                            LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_transfer);
                                            if (linearLayout4 != null) {
                                                i = R.id.rl_all;
                                                LinearLayout linearLayout5 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.rl_all);
                                                if (linearLayout5 != null) {
                                                    i = R.id.rl_event_reward;
                                                    LinearLayout linearLayout6 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.rl_event_reward);
                                                    if (linearLayout6 != null) {
                                                        i = R.id.rl_other;
                                                        LinearLayout linearLayout7 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.rl_other);
                                                        if (linearLayout7 != null) {
                                                            i = R.id.rl_transfer;
                                                            LinearLayout linearLayout8 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.rl_transfer);
                                                            if (linearLayout8 != null) {
                                                                i = R.id.tv_all;
                                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_all);
                                                                if (textView2 != null) {
                                                                    i = R.id.tv_event_reward;
                                                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_event_reward);
                                                                    if (textView3 != null) {
                                                                        i = R.id.tv_event_reward_count;
                                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_event_reward_count);
                                                                        if (textView4 != null) {
                                                                            i = R.id.tv_other;
                                                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_other);
                                                                            if (textView5 != null) {
                                                                                i = R.id.tv_other_count;
                                                                                TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_other_count);
                                                                                if (textView6 != null) {
                                                                                    i = R.id.tv_transfer;
                                                                                    TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_transfer);
                                                                                    if (textView7 != null) {
                                                                                        i = R.id.tv_transfer_count;
                                                                                        TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_transfer_count);
                                                                                        if (textView8 != null) {
                                                                                            return new AcMessageBinding((LinearLayout) view, frameLayout, imageView, textView, imageView2, imageView3, imageView4, linearLayout, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6, linearLayout7, linearLayout8, textView2, textView3, textView4, textView5, textView6, textView7, textView8);
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
