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
import com.tronlinkpro.wallet.R;
public final class AcMultiSelectAddressBinding implements ViewBinding {
    public final Button btnNext;
    public final FrameLayout frameData;
    public final FrameLayout frameSearch;
    public final RelativeLayout headerLayout;
    public final ImageView ivBack;
    public final ImageView ivTip2;
    public final RelativeLayout rlData;
    public final RelativeLayout rlMain;
    private final RelativeLayout rootView;
    public final View splitLine;
    public final TextView tvAccount;
    public final TextView tvMainTitle;
    public final TextView tvStep;
    public final TextView tvTutorial;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcMultiSelectAddressBinding(RelativeLayout relativeLayout, Button button, FrameLayout frameLayout, FrameLayout frameLayout2, RelativeLayout relativeLayout2, ImageView imageView, ImageView imageView2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, View view, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        this.rootView = relativeLayout;
        this.btnNext = button;
        this.frameData = frameLayout;
        this.frameSearch = frameLayout2;
        this.headerLayout = relativeLayout2;
        this.ivBack = imageView;
        this.ivTip2 = imageView2;
        this.rlData = relativeLayout3;
        this.rlMain = relativeLayout4;
        this.splitLine = view;
        this.tvAccount = textView;
        this.tvMainTitle = textView2;
        this.tvStep = textView3;
        this.tvTutorial = textView4;
    }

    public static AcMultiSelectAddressBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcMultiSelectAddressBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_multi_select_address, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcMultiSelectAddressBinding bind(View view) {
        int i = R.id.btn_next;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_next);
        if (button != null) {
            i = R.id.frame_data;
            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.frame_data);
            if (frameLayout != null) {
                i = R.id.frame_search;
                FrameLayout frameLayout2 = (FrameLayout) ViewBindings.findChildViewById(view, R.id.frame_search);
                if (frameLayout2 != null) {
                    i = R.id.header_layout;
                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.header_layout);
                    if (relativeLayout != null) {
                        i = R.id.iv_back;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_back);
                        if (imageView != null) {
                            i = R.id.iv_tip2;
                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_tip2);
                            if (imageView2 != null) {
                                i = R.id.rl_data;
                                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_data);
                                if (relativeLayout2 != null) {
                                    RelativeLayout relativeLayout3 = (RelativeLayout) view;
                                    i = R.id.split_line;
                                    View findChildViewById = ViewBindings.findChildViewById(view, R.id.split_line);
                                    if (findChildViewById != null) {
                                        i = R.id.tv_account;
                                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_account);
                                        if (textView != null) {
                                            i = R.id.tv_main_title;
                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_main_title);
                                            if (textView2 != null) {
                                                i = R.id.tv_step;
                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_step);
                                                if (textView3 != null) {
                                                    i = R.id.tv_tutorial;
                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_tutorial);
                                                    if (textView4 != null) {
                                                        return new AcMultiSelectAddressBinding(relativeLayout3, button, frameLayout, frameLayout2, relativeLayout, imageView, imageView2, relativeLayout2, relativeLayout3, findChildViewById, textView, textView2, textView3, textView4);
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
