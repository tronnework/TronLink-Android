package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tronlinkpro.wallet.R;
public final class AcLoginBinding implements ViewBinding {
    public final SimpleDraweeView ivIcon;
    public final LinearLayout llLogo;
    public final RelativeLayout rlRoot;
    private final RelativeLayout rootView;
    public final TextView tvBalance;
    public final TextView tvBalanceTitle;
    public final TextView tvCancel;
    public final TextView tvLogin;
    public final TextView tvWalletname;
    public final TextView tvWalletnameTitle;
    public final View viewBorder;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcLoginBinding(RelativeLayout relativeLayout, SimpleDraweeView simpleDraweeView, LinearLayout linearLayout, RelativeLayout relativeLayout2, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, View view) {
        this.rootView = relativeLayout;
        this.ivIcon = simpleDraweeView;
        this.llLogo = linearLayout;
        this.rlRoot = relativeLayout2;
        this.tvBalance = textView;
        this.tvBalanceTitle = textView2;
        this.tvCancel = textView3;
        this.tvLogin = textView4;
        this.tvWalletname = textView5;
        this.tvWalletnameTitle = textView6;
        this.viewBorder = view;
    }

    public static AcLoginBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcLoginBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_login, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcLoginBinding bind(View view) {
        int i = R.id.iv_icon;
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.iv_icon);
        if (simpleDraweeView != null) {
            i = R.id.ll_logo;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_logo);
            if (linearLayout != null) {
                RelativeLayout relativeLayout = (RelativeLayout) view;
                i = R.id.tv_balance;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_balance);
                if (textView != null) {
                    i = R.id.tv_balance_title;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_balance_title);
                    if (textView2 != null) {
                        i = R.id.tv_cancel;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_cancel);
                        if (textView3 != null) {
                            i = R.id.tv_login;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_login);
                            if (textView4 != null) {
                                i = R.id.tv_walletname;
                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_walletname);
                                if (textView5 != null) {
                                    i = R.id.tv_walletname_title;
                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_walletname_title);
                                    if (textView6 != null) {
                                        i = R.id.view_border;
                                        View findChildViewById = ViewBindings.findChildViewById(view, R.id.view_border);
                                        if (findChildViewById != null) {
                                            return new AcLoginBinding(relativeLayout, simpleDraweeView, linearLayout, relativeLayout, textView, textView2, textView3, textView4, textView5, textView6, findChildViewById);
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
