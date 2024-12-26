package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.StakeHeaderView;
import com.tronlinkpro.wallet.R;
public final class AcUndelegateBandwidthBinding implements ViewBinding {
    public final TextView availableTextCount;
    public final TextView availableTextTitle;
    public final TextView btnNext;
    public final ImageView ivAvailableTips;
    public final ImageView ivUnavailableTips;
    public final RelativeLayout resourceLockSwitchLayout;
    private final RelativeLayout rootView;
    public final StakeHeaderView stakeHeader;
    public final TabWithPercentSettingBinding tab;
    public final TextView title;
    public final TextView tvMultiWarning;
    public final TextView unavailableTextCount;
    public final TextView unavailableTextTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcUndelegateBandwidthBinding(RelativeLayout relativeLayout, TextView textView, TextView textView2, TextView textView3, ImageView imageView, ImageView imageView2, RelativeLayout relativeLayout2, StakeHeaderView stakeHeaderView, TabWithPercentSettingBinding tabWithPercentSettingBinding, TextView textView4, TextView textView5, TextView textView6, TextView textView7) {
        this.rootView = relativeLayout;
        this.availableTextCount = textView;
        this.availableTextTitle = textView2;
        this.btnNext = textView3;
        this.ivAvailableTips = imageView;
        this.ivUnavailableTips = imageView2;
        this.resourceLockSwitchLayout = relativeLayout2;
        this.stakeHeader = stakeHeaderView;
        this.tab = tabWithPercentSettingBinding;
        this.title = textView4;
        this.tvMultiWarning = textView5;
        this.unavailableTextCount = textView6;
        this.unavailableTextTitle = textView7;
    }

    public static AcUndelegateBandwidthBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcUndelegateBandwidthBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_undelegate_bandwidth, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcUndelegateBandwidthBinding bind(View view) {
        int i = R.id.available_text_count;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.available_text_count);
        if (textView != null) {
            i = R.id.available_text_title;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.available_text_title);
            if (textView2 != null) {
                i = R.id.btn_next;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.btn_next);
                if (textView3 != null) {
                    i = R.id.iv_available_tips;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_available_tips);
                    if (imageView != null) {
                        i = R.id.iv_unavailable_tips;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_unavailable_tips);
                        if (imageView2 != null) {
                            i = R.id.resource_lock_switch_layout;
                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.resource_lock_switch_layout);
                            if (relativeLayout != null) {
                                i = R.id.stake_header;
                                StakeHeaderView stakeHeaderView = (StakeHeaderView) ViewBindings.findChildViewById(view, R.id.stake_header);
                                if (stakeHeaderView != null) {
                                    i = R.id.tab;
                                    View findChildViewById = ViewBindings.findChildViewById(view, R.id.tab);
                                    if (findChildViewById != null) {
                                        TabWithPercentSettingBinding bind = TabWithPercentSettingBinding.bind(findChildViewById);
                                        i = R.id.title;
                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.title);
                                        if (textView4 != null) {
                                            i = R.id.tv_multi_warning;
                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_multi_warning);
                                            if (textView5 != null) {
                                                i = R.id.unavailable_text_count;
                                                TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.unavailable_text_count);
                                                if (textView6 != null) {
                                                    i = R.id.unavailable_text_title;
                                                    TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.unavailable_text_title);
                                                    if (textView7 != null) {
                                                        return new AcUndelegateBandwidthBinding((RelativeLayout) view, textView, textView2, textView3, imageView, imageView2, relativeLayout, stakeHeaderView, bind, textView4, textView5, textView6, textView7);
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
