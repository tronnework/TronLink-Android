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
import com.tron.wallet.common.components.MyWebView;
import com.tronlinkpro.wallet.R;
public final class ActivityUserPrivacyAgreementBinding implements ViewBinding {
    public final Button btAccept;
    public final ImageView ivCommonLeft;
    public final ImageView ivCommonRight;
    public final View line;
    public final RelativeLayout rlTitle;
    public final LinearLayout root;
    private final LinearLayout rootView;
    public final TextView tvTitle;
    public final MyWebView webview;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private ActivityUserPrivacyAgreementBinding(LinearLayout linearLayout, Button button, ImageView imageView, ImageView imageView2, View view, RelativeLayout relativeLayout, LinearLayout linearLayout2, TextView textView, MyWebView myWebView) {
        this.rootView = linearLayout;
        this.btAccept = button;
        this.ivCommonLeft = imageView;
        this.ivCommonRight = imageView2;
        this.line = view;
        this.rlTitle = relativeLayout;
        this.root = linearLayout2;
        this.tvTitle = textView;
        this.webview = myWebView;
    }

    public static ActivityUserPrivacyAgreementBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityUserPrivacyAgreementBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_user_privacy_agreement, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityUserPrivacyAgreementBinding bind(View view) {
        int i = R.id.bt_accept;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_accept);
        if (button != null) {
            i = R.id.iv_common_left;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_common_left);
            if (imageView != null) {
                i = R.id.iv_common_right;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_common_right);
                if (imageView2 != null) {
                    i = R.id.line;
                    View findChildViewById = ViewBindings.findChildViewById(view, R.id.line);
                    if (findChildViewById != null) {
                        i = R.id.rl_title;
                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_title);
                        if (relativeLayout != null) {
                            LinearLayout linearLayout = (LinearLayout) view;
                            i = R.id.tv_title;
                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                            if (textView != null) {
                                i = R.id.webview;
                                MyWebView myWebView = (MyWebView) ViewBindings.findChildViewById(view, R.id.webview);
                                if (myWebView != null) {
                                    return new ActivityUserPrivacyAgreementBinding(linearLayout, button, imageView, imageView2, findChildViewById, relativeLayout, linearLayout, textView, myWebView);
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
