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
import com.tronlinkpro.wallet.R;
public final class FgDelegatePendingBinding implements ViewBinding {
    public final Button btnAgain;
    public final Button btnBackHome;
    public final Button btnDone;
    public final ImageView ivResult;
    public final LinearLayout llDetail;
    public final RelativeLayout llRoot;
    public final RelativeLayout rlResourceFirst;
    public final LinearLayout rlResourceSecond;
    public final RelativeLayout rlResultPartial;
    private final RelativeLayout rootView;
    public final TextView tvInfo;
    public final TextView tvLeftFirst;
    public final TextView tvLeftSecond;
    public final TextView tvResult;
    public final TextView tvRightFirst;
    public final TextView tvRightSecond;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FgDelegatePendingBinding(RelativeLayout relativeLayout, Button button, Button button2, Button button3, ImageView imageView, LinearLayout linearLayout, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, LinearLayout linearLayout2, RelativeLayout relativeLayout4, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6) {
        this.rootView = relativeLayout;
        this.btnAgain = button;
        this.btnBackHome = button2;
        this.btnDone = button3;
        this.ivResult = imageView;
        this.llDetail = linearLayout;
        this.llRoot = relativeLayout2;
        this.rlResourceFirst = relativeLayout3;
        this.rlResourceSecond = linearLayout2;
        this.rlResultPartial = relativeLayout4;
        this.tvInfo = textView;
        this.tvLeftFirst = textView2;
        this.tvLeftSecond = textView3;
        this.tvResult = textView4;
        this.tvRightFirst = textView5;
        this.tvRightSecond = textView6;
    }

    public static FgDelegatePendingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FgDelegatePendingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fg_delegate_pending, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FgDelegatePendingBinding bind(View view) {
        int i = R.id.btn_again;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_again);
        if (button != null) {
            i = R.id.btn_back_home;
            Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.btn_back_home);
            if (button2 != null) {
                i = R.id.btn_done;
                Button button3 = (Button) ViewBindings.findChildViewById(view, R.id.btn_done);
                if (button3 != null) {
                    i = R.id.iv_result;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_result);
                    if (imageView != null) {
                        i = R.id.ll_detail;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_detail);
                        if (linearLayout != null) {
                            RelativeLayout relativeLayout = (RelativeLayout) view;
                            i = R.id.rl_resource_first;
                            RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_resource_first);
                            if (relativeLayout2 != null) {
                                i = R.id.rl_resource_second;
                                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.rl_resource_second);
                                if (linearLayout2 != null) {
                                    i = R.id.rl_result_partial;
                                    RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_result_partial);
                                    if (relativeLayout3 != null) {
                                        i = R.id.tv_info;
                                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_info);
                                        if (textView != null) {
                                            i = R.id.tv_left_first;
                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_left_first);
                                            if (textView2 != null) {
                                                i = R.id.tv_left_second;
                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_left_second);
                                                if (textView3 != null) {
                                                    i = R.id.tv_result;
                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_result);
                                                    if (textView4 != null) {
                                                        i = R.id.tv_right_first;
                                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_right_first);
                                                        if (textView5 != null) {
                                                            i = R.id.tv_right_second;
                                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_right_second);
                                                            if (textView6 != null) {
                                                                return new FgDelegatePendingBinding(relativeLayout, button, button2, button3, imageView, linearLayout, relativeLayout, relativeLayout2, linearLayout2, relativeLayout3, textView, textView2, textView3, textView4, textView5, textView6);
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
