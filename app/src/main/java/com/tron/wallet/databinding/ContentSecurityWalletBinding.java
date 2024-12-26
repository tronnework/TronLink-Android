package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ContentSecurityWalletBinding implements ViewBinding {
    public final ImageView ivSecurityTitle;
    public final View line;
    private final RelativeLayout rootView;
    public final TextView securityContent;
    public final TextView securityContent2;
    public final TextView securityContent3;
    public final TextView securityContent4;
    public final RelativeLayout securityTitleLayout;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ContentSecurityWalletBinding(RelativeLayout relativeLayout, ImageView imageView, View view, TextView textView, TextView textView2, TextView textView3, TextView textView4, RelativeLayout relativeLayout2) {
        this.rootView = relativeLayout;
        this.ivSecurityTitle = imageView;
        this.line = view;
        this.securityContent = textView;
        this.securityContent2 = textView2;
        this.securityContent3 = textView3;
        this.securityContent4 = textView4;
        this.securityTitleLayout = relativeLayout2;
    }

    public static ContentSecurityWalletBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ContentSecurityWalletBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.content_security_wallet, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ContentSecurityWalletBinding bind(View view) {
        int i = R.id.iv_security_title;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_security_title);
        if (imageView != null) {
            i = R.id.line;
            View findChildViewById = ViewBindings.findChildViewById(view, R.id.line);
            if (findChildViewById != null) {
                i = R.id.security_content;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.security_content);
                if (textView != null) {
                    i = R.id.security_content_2;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.security_content_2);
                    if (textView2 != null) {
                        i = R.id.security_content_3;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.security_content_3);
                        if (textView3 != null) {
                            i = R.id.security_content_4;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.security_content_4);
                            if (textView4 != null) {
                                i = R.id.security_title_layout;
                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.security_title_layout);
                                if (relativeLayout != null) {
                                    return new ContentSecurityWalletBinding((RelativeLayout) view, imageView, findChildViewById, textView, textView2, textView3, textView4, relativeLayout);
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
