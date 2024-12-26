package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.ErrorView;
import com.tronlinkpro.wallet.R;
public final class ActivityUnstakeBinding implements ViewBinding {
    public final Button btnNext;
    public final ErrorView errorView;
    public final RelativeLayout headerLayout;
    public final ImageView ivBack;
    public final RelativeLayout rlBottom;
    private final RelativeLayout rootView;
    public final FrameLayout searchResultView;
    public final TextView tvMainTitle;
    public final TextView tvMultiSign;
    public final TextView tvMultiWarning;
    public final TextView tvSelectedCount;
    public final TextView tvStep;
    public final TextView tvTotalTrx;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ActivityUnstakeBinding(RelativeLayout relativeLayout, Button button, ErrorView errorView, RelativeLayout relativeLayout2, ImageView imageView, RelativeLayout relativeLayout3, FrameLayout frameLayout, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6) {
        this.rootView = relativeLayout;
        this.btnNext = button;
        this.errorView = errorView;
        this.headerLayout = relativeLayout2;
        this.ivBack = imageView;
        this.rlBottom = relativeLayout3;
        this.searchResultView = frameLayout;
        this.tvMainTitle = textView;
        this.tvMultiSign = textView2;
        this.tvMultiWarning = textView3;
        this.tvSelectedCount = textView4;
        this.tvStep = textView5;
        this.tvTotalTrx = textView6;
    }

    public static ActivityUnstakeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityUnstakeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_unstake, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityUnstakeBinding bind(View view) {
        int i = R.id.btn_next;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_next);
        if (button != null) {
            i = R.id.error_view;
            ErrorView errorView = (ErrorView) ViewBindings.findChildViewById(view, R.id.error_view);
            if (errorView != null) {
                i = R.id.header_layout;
                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.header_layout);
                if (relativeLayout != null) {
                    i = R.id.iv_back;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_back);
                    if (imageView != null) {
                        i = R.id.rl_bottom;
                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_bottom);
                        if (relativeLayout2 != null) {
                            i = R.id.search_result_view;
                            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.search_result_view);
                            if (frameLayout != null) {
                                i = R.id.tv_main_title;
                                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_main_title);
                                if (textView != null) {
                                    i = R.id.tv_multi_sign;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_multi_sign);
                                    if (textView2 != null) {
                                        i = R.id.tv_multi_warning;
                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_multi_warning);
                                        if (textView3 != null) {
                                            i = R.id.tv_selected_count;
                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_selected_count);
                                            if (textView4 != null) {
                                                i = R.id.tv_step;
                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_step);
                                                if (textView5 != null) {
                                                    i = R.id.tv_total_trx;
                                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_total_trx);
                                                    if (textView6 != null) {
                                                        return new ActivityUnstakeBinding((RelativeLayout) view, button, errorView, relativeLayout, imageView, relativeLayout2, frameLayout, textView, textView2, textView3, textView4, textView5, textView6);
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
