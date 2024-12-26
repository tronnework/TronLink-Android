package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class AcSecurityHomeBinding implements ViewBinding {
    public final ImageView ivCommonLeft;
    public final ImageView ivCommonRight;
    public final ImageView ivTronscan;
    public final LinearLayout llCommonLeft;
    public final RelativeLayout llHeader;
    private final RelativeLayout rootView;
    public final TextView tvCommonTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcSecurityHomeBinding(RelativeLayout relativeLayout, ImageView imageView, ImageView imageView2, ImageView imageView3, LinearLayout linearLayout, RelativeLayout relativeLayout2, TextView textView) {
        this.rootView = relativeLayout;
        this.ivCommonLeft = imageView;
        this.ivCommonRight = imageView2;
        this.ivTronscan = imageView3;
        this.llCommonLeft = linearLayout;
        this.llHeader = relativeLayout2;
        this.tvCommonTitle = textView;
    }

    public static AcSecurityHomeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcSecurityHomeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_security_home, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcSecurityHomeBinding bind(View view) {
        int i = R.id.iv_common_left;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_common_left);
        if (imageView != null) {
            i = R.id.iv_common_right;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_common_right);
            if (imageView2 != null) {
                i = R.id.iv_tronscan;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_tronscan);
                if (imageView3 != null) {
                    i = R.id.ll_common_left;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_common_left);
                    if (linearLayout != null) {
                        i = R.id.ll_header;
                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_header);
                        if (relativeLayout != null) {
                            i = R.id.tv_common_title;
                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_common_title);
                            if (textView != null) {
                                return new AcSecurityHomeBinding((RelativeLayout) view, imageView, imageView2, imageView3, linearLayout, relativeLayout, textView);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
