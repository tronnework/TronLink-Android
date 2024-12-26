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
public final class AcEmptywalletBinding implements ViewBinding {
    public final SimpleDraweeView ivThirdLogo;
    public final LinearLayout llLogo;
    public final RelativeLayout rlRoot;
    private final RelativeLayout rootView;
    public final TextView tvCreate;
    public final TextView tvDesc;
    public final TextView tvImport;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcEmptywalletBinding(RelativeLayout relativeLayout, SimpleDraweeView simpleDraweeView, LinearLayout linearLayout, RelativeLayout relativeLayout2, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = relativeLayout;
        this.ivThirdLogo = simpleDraweeView;
        this.llLogo = linearLayout;
        this.rlRoot = relativeLayout2;
        this.tvCreate = textView;
        this.tvDesc = textView2;
        this.tvImport = textView3;
    }

    public static AcEmptywalletBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcEmptywalletBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_emptywallet, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcEmptywalletBinding bind(View view) {
        int i = R.id.iv_third_logo;
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.iv_third_logo);
        if (simpleDraweeView != null) {
            i = R.id.ll_logo;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_logo);
            if (linearLayout != null) {
                RelativeLayout relativeLayout = (RelativeLayout) view;
                i = R.id.tv_create;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_create);
                if (textView != null) {
                    i = R.id.tv_desc;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_desc);
                    if (textView2 != null) {
                        i = R.id.tv_import;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_import);
                        if (textView3 != null) {
                            return new AcEmptywalletBinding(relativeLayout, simpleDraweeView, linearLayout, relativeLayout, textView, textView2, textView3);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
