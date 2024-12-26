package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager.widget.ViewPager;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.components.StakeHeaderView;
import com.tron.wallet.common.components.xtablayout.XTabLayout;
import com.tronlinkpro.wallet.R;
public final class AcStakeManageDetailBinding implements ViewBinding {
    public final Button btDelegate;
    public final RelativeLayout contentRoot;
    public final View divider;
    public final ImageView ivBackground;
    public final ImageView ivPlaceHolder;
    public final LinearLayout liHeader;
    public final NoNetView noDataView;
    public final RelativeLayout rlHeader;
    private final RelativeLayout rootView;
    public final StakeHeaderView stakeHeader;
    public final TextView tvMultiWarning;
    public final TextView tvUsableAmount;
    public final TextView tvUsableTitle;
    public final TextView tvUsedAmount;
    public final TextView tvUsedTitle;
    public final ViewPager vpContent;
    public final XTabLayout xTablayout;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcStakeManageDetailBinding(RelativeLayout relativeLayout, Button button, RelativeLayout relativeLayout2, View view, ImageView imageView, ImageView imageView2, LinearLayout linearLayout, NoNetView noNetView, RelativeLayout relativeLayout3, StakeHeaderView stakeHeaderView, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, ViewPager viewPager, XTabLayout xTabLayout) {
        this.rootView = relativeLayout;
        this.btDelegate = button;
        this.contentRoot = relativeLayout2;
        this.divider = view;
        this.ivBackground = imageView;
        this.ivPlaceHolder = imageView2;
        this.liHeader = linearLayout;
        this.noDataView = noNetView;
        this.rlHeader = relativeLayout3;
        this.stakeHeader = stakeHeaderView;
        this.tvMultiWarning = textView;
        this.tvUsableAmount = textView2;
        this.tvUsableTitle = textView3;
        this.tvUsedAmount = textView4;
        this.tvUsedTitle = textView5;
        this.vpContent = viewPager;
        this.xTablayout = xTabLayout;
    }

    public static AcStakeManageDetailBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcStakeManageDetailBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_stake_manage_detail, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcStakeManageDetailBinding bind(View view) {
        int i = R.id.bt_delegate;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_delegate);
        if (button != null) {
            RelativeLayout relativeLayout = (RelativeLayout) view;
            i = R.id.divider;
            View findChildViewById = ViewBindings.findChildViewById(view, R.id.divider);
            if (findChildViewById != null) {
                i = R.id.iv_background;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_background);
                if (imageView != null) {
                    i = R.id.iv_place_holder;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_place_holder);
                    if (imageView2 != null) {
                        i = R.id.li_header;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_header);
                        if (linearLayout != null) {
                            i = R.id.no_data_view;
                            NoNetView noNetView = (NoNetView) ViewBindings.findChildViewById(view, R.id.no_data_view);
                            if (noNetView != null) {
                                i = R.id.rl_header;
                                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_header);
                                if (relativeLayout2 != null) {
                                    i = R.id.stake_header;
                                    StakeHeaderView stakeHeaderView = (StakeHeaderView) ViewBindings.findChildViewById(view, R.id.stake_header);
                                    if (stakeHeaderView != null) {
                                        i = R.id.tv_multi_warning;
                                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_multi_warning);
                                        if (textView != null) {
                                            i = R.id.tv_usable_amount;
                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_usable_amount);
                                            if (textView2 != null) {
                                                i = R.id.tv_usable_title;
                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_usable_title);
                                                if (textView3 != null) {
                                                    i = R.id.tv_used_amount;
                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_used_amount);
                                                    if (textView4 != null) {
                                                        i = R.id.tv_used_title;
                                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_used_title);
                                                        if (textView5 != null) {
                                                            i = R.id.vp_content;
                                                            ViewPager viewPager = (ViewPager) ViewBindings.findChildViewById(view, R.id.vp_content);
                                                            if (viewPager != null) {
                                                                i = R.id.xTablayout;
                                                                XTabLayout xTabLayout = (XTabLayout) ViewBindings.findChildViewById(view, R.id.xTablayout);
                                                                if (xTabLayout != null) {
                                                                    return new AcStakeManageDetailBinding(relativeLayout, button, relativeLayout, findChildViewById, imageView, imageView2, linearLayout, noNetView, relativeLayout2, stakeHeaderView, textView, textView2, textView3, textView4, textView5, viewPager, xTabLayout);
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
