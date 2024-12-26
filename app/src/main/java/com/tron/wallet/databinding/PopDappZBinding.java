package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class PopDappZBinding implements ViewBinding {
    public final EditText etPassword;
    public final ImageView ivClose;
    public final ImageView ivOptions1;
    public final ImageView ivOptions2;
    public final ImageView ivOptions3;
    public final View line1;
    public final View line2;
    public final LinearLayout llClose;
    public final LinearLayout llPassword;
    public final Button ok;
    public final RelativeLayout rl1;
    public final RelativeLayout rl2;
    public final RelativeLayout rl3;
    public final RelativeLayout rlOptions;
    private final RelativeLayout rootView;
    public final TextView tvAccount1;
    public final TextView tvAccount2;
    public final TextView tvBottomAddress;
    public final TextView tvOptions1;
    public final TextView tvOptions2;
    public final TextView tvOptions3;
    public final TextView tvTopAddress;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private PopDappZBinding(RelativeLayout relativeLayout, EditText editText, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, View view, View view2, LinearLayout linearLayout, LinearLayout linearLayout2, Button button, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, RelativeLayout relativeLayout5, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7) {
        this.rootView = relativeLayout;
        this.etPassword = editText;
        this.ivClose = imageView;
        this.ivOptions1 = imageView2;
        this.ivOptions2 = imageView3;
        this.ivOptions3 = imageView4;
        this.line1 = view;
        this.line2 = view2;
        this.llClose = linearLayout;
        this.llPassword = linearLayout2;
        this.ok = button;
        this.rl1 = relativeLayout2;
        this.rl2 = relativeLayout3;
        this.rl3 = relativeLayout4;
        this.rlOptions = relativeLayout5;
        this.tvAccount1 = textView;
        this.tvAccount2 = textView2;
        this.tvBottomAddress = textView3;
        this.tvOptions1 = textView4;
        this.tvOptions2 = textView5;
        this.tvOptions3 = textView6;
        this.tvTopAddress = textView7;
    }

    public static PopDappZBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopDappZBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.pop_dapp_z, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopDappZBinding bind(View view) {
        int i = R.id.et_password;
        EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.et_password);
        if (editText != null) {
            i = R.id.iv_close;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_close);
            if (imageView != null) {
                i = R.id.iv_options_1;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_options_1);
                if (imageView2 != null) {
                    i = R.id.iv_options_2;
                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_options_2);
                    if (imageView3 != null) {
                        i = R.id.iv_options_3;
                        ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_options_3);
                        if (imageView4 != null) {
                            i = R.id.line1;
                            View findChildViewById = ViewBindings.findChildViewById(view, R.id.line1);
                            if (findChildViewById != null) {
                                i = R.id.line2;
                                View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.line2);
                                if (findChildViewById2 != null) {
                                    i = R.id.ll_close;
                                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_close);
                                    if (linearLayout != null) {
                                        i = R.id.ll_password;
                                        LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_password);
                                        if (linearLayout2 != null) {
                                            i = R.id.ok;
                                            Button button = (Button) ViewBindings.findChildViewById(view, R.id.ok);
                                            if (button != null) {
                                                i = R.id.rl_1;
                                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_1);
                                                if (relativeLayout != null) {
                                                    i = R.id.rl_2;
                                                    RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_2);
                                                    if (relativeLayout2 != null) {
                                                        i = R.id.rl_3;
                                                        RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_3);
                                                        if (relativeLayout3 != null) {
                                                            i = R.id.rl_options;
                                                            RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_options);
                                                            if (relativeLayout4 != null) {
                                                                i = R.id.tv_account_1;
                                                                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_account_1);
                                                                if (textView != null) {
                                                                    i = R.id.tv_account_2;
                                                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_account_2);
                                                                    if (textView2 != null) {
                                                                        i = R.id.tv_bottom_address;
                                                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_bottom_address);
                                                                        if (textView3 != null) {
                                                                            i = R.id.tv_options_1;
                                                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_options_1);
                                                                            if (textView4 != null) {
                                                                                i = R.id.tv_options_2;
                                                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_options_2);
                                                                                if (textView5 != null) {
                                                                                    i = R.id.tv_options_3;
                                                                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_options_3);
                                                                                    if (textView6 != null) {
                                                                                        i = R.id.tv_top_address;
                                                                                        TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_top_address);
                                                                                        if (textView7 != null) {
                                                                                            return new PopDappZBinding((RelativeLayout) view, editText, imageView, imageView2, imageView3, imageView4, findChildViewById, findChildViewById2, linearLayout, linearLayout2, button, relativeLayout, relativeLayout2, relativeLayout3, relativeLayout4, textView, textView2, textView3, textView4, textView5, textView6, textView7);
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
