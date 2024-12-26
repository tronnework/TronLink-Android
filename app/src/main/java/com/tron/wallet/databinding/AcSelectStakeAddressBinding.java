package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.tron.wallet.common.components.ErrorView;
import com.tron.wallet.common.components.TrimEditText;
import com.tron.wallet.common.components.xtablayout.XTabLayoutV2;
import com.tronlinkpro.wallet.R;
public final class AcSelectStakeAddressBinding implements ViewBinding {
    public final Button btnNext;
    public final AppCompatCheckBox chkStakeAmount;
    public final View divider;
    public final ErrorView errorView;
    public final TrimEditText etInputAddress;
    public final AppCompatImageView ivAddAddress;
    public final ImageView ivDelete;
    public final ImageView ivScan;
    public final LinearLayout liAddAddress;
    public final RelativeLayout rlAddress;
    public final RelativeLayout rlButtons;
    public final RelativeLayout rlContainer;
    public final RelativeLayout rlInput;
    public final RelativeLayout rlList;
    private final RelativeLayout rootView;
    public final FrameLayout searchResultView;
    public final XTabLayoutV2 tablayout;
    public final TextView tvAddAddress;
    public final TextView tvDefaultAddress;
    public final TextView tvInputMask;
    public final TextView tvPaste;
    public final TextView tvTitle;
    public final TextView tvUnderControl;
    public final ViewPager2 viewpager;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcSelectStakeAddressBinding(RelativeLayout relativeLayout, Button button, AppCompatCheckBox appCompatCheckBox, View view, ErrorView errorView, TrimEditText trimEditText, AppCompatImageView appCompatImageView, ImageView imageView, ImageView imageView2, LinearLayout linearLayout, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, RelativeLayout relativeLayout5, RelativeLayout relativeLayout6, FrameLayout frameLayout, XTabLayoutV2 xTabLayoutV2, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, ViewPager2 viewPager2) {
        this.rootView = relativeLayout;
        this.btnNext = button;
        this.chkStakeAmount = appCompatCheckBox;
        this.divider = view;
        this.errorView = errorView;
        this.etInputAddress = trimEditText;
        this.ivAddAddress = appCompatImageView;
        this.ivDelete = imageView;
        this.ivScan = imageView2;
        this.liAddAddress = linearLayout;
        this.rlAddress = relativeLayout2;
        this.rlButtons = relativeLayout3;
        this.rlContainer = relativeLayout4;
        this.rlInput = relativeLayout5;
        this.rlList = relativeLayout6;
        this.searchResultView = frameLayout;
        this.tablayout = xTabLayoutV2;
        this.tvAddAddress = textView;
        this.tvDefaultAddress = textView2;
        this.tvInputMask = textView3;
        this.tvPaste = textView4;
        this.tvTitle = textView5;
        this.tvUnderControl = textView6;
        this.viewpager = viewPager2;
    }

    public static AcSelectStakeAddressBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcSelectStakeAddressBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_select_stake_address, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcSelectStakeAddressBinding bind(View view) {
        int i = R.id.btn_next;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_next);
        if (button != null) {
            i = R.id.chk_stake_amount;
            AppCompatCheckBox appCompatCheckBox = (AppCompatCheckBox) ViewBindings.findChildViewById(view, R.id.chk_stake_amount);
            if (appCompatCheckBox != null) {
                i = R.id.divider;
                View findChildViewById = ViewBindings.findChildViewById(view, R.id.divider);
                if (findChildViewById != null) {
                    i = R.id.error_view;
                    ErrorView errorView = (ErrorView) ViewBindings.findChildViewById(view, R.id.error_view);
                    if (errorView != null) {
                        i = R.id.et_input_address;
                        TrimEditText trimEditText = (TrimEditText) ViewBindings.findChildViewById(view, R.id.et_input_address);
                        if (trimEditText != null) {
                            i = R.id.iv_add_address;
                            AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.iv_add_address);
                            if (appCompatImageView != null) {
                                i = R.id.iv_delete;
                                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_delete);
                                if (imageView != null) {
                                    i = R.id.iv_scan;
                                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_scan);
                                    if (imageView2 != null) {
                                        i = R.id.li_add_address;
                                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_add_address);
                                        if (linearLayout != null) {
                                            RelativeLayout relativeLayout = (RelativeLayout) view;
                                            i = R.id.rl_buttons;
                                            RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_buttons);
                                            if (relativeLayout2 != null) {
                                                i = R.id.rl_container;
                                                RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_container);
                                                if (relativeLayout3 != null) {
                                                    i = R.id.rl_input;
                                                    RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_input);
                                                    if (relativeLayout4 != null) {
                                                        i = R.id.rl_list;
                                                        RelativeLayout relativeLayout5 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_list);
                                                        if (relativeLayout5 != null) {
                                                            i = R.id.search_result_view;
                                                            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.search_result_view);
                                                            if (frameLayout != null) {
                                                                i = R.id.tablayout;
                                                                XTabLayoutV2 xTabLayoutV2 = (XTabLayoutV2) ViewBindings.findChildViewById(view, R.id.tablayout);
                                                                if (xTabLayoutV2 != null) {
                                                                    i = R.id.tv_add_address;
                                                                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_add_address);
                                                                    if (textView != null) {
                                                                        i = R.id.tv_default_address;
                                                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_default_address);
                                                                        if (textView2 != null) {
                                                                            i = R.id.tv_input_mask;
                                                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_input_mask);
                                                                            if (textView3 != null) {
                                                                                i = R.id.tv_paste;
                                                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_paste);
                                                                                if (textView4 != null) {
                                                                                    i = R.id.tv_title;
                                                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                                                                                    if (textView5 != null) {
                                                                                        i = R.id.tv_under_control;
                                                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_under_control);
                                                                                        if (textView6 != null) {
                                                                                            i = R.id.viewpager;
                                                                                            ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, R.id.viewpager);
                                                                                            if (viewPager2 != null) {
                                                                                                return new AcSelectStakeAddressBinding(relativeLayout, button, appCompatCheckBox, findChildViewById, errorView, trimEditText, appCompatImageView, imageView, imageView2, linearLayout, relativeLayout, relativeLayout2, relativeLayout3, relativeLayout4, relativeLayout5, frameLayout, xTabLayoutV2, textView, textView2, textView3, textView4, textView5, textView6, viewPager2);
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
