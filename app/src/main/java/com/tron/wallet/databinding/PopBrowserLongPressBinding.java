package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class PopBrowserLongPressBinding implements ViewBinding {
    public final FrameLayout frRoot;
    public final ImageView ivBgImage;
    public final LinearLayout liBrowserCopylink;
    public final RelativeLayout liBrowserInNewTab;
    public final LinearLayout liBrowserOpenDirection;
    public final LinearLayout liBrowserOutside;
    public final LinearLayout liBrowserUrl;
    public final LinearLayout liContent;
    private final FrameLayout rootView;
    public final TextView tvBrowserLongpressContent;

    @Override
    public FrameLayout getRoot() {
        return this.rootView;
    }

    private PopBrowserLongPressBinding(FrameLayout frameLayout, FrameLayout frameLayout2, ImageView imageView, LinearLayout linearLayout, RelativeLayout relativeLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, LinearLayout linearLayout5, TextView textView) {
        this.rootView = frameLayout;
        this.frRoot = frameLayout2;
        this.ivBgImage = imageView;
        this.liBrowserCopylink = linearLayout;
        this.liBrowserInNewTab = relativeLayout;
        this.liBrowserOpenDirection = linearLayout2;
        this.liBrowserOutside = linearLayout3;
        this.liBrowserUrl = linearLayout4;
        this.liContent = linearLayout5;
        this.tvBrowserLongpressContent = textView;
    }

    public static PopBrowserLongPressBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopBrowserLongPressBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.pop_browser_long_press, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopBrowserLongPressBinding bind(View view) {
        FrameLayout frameLayout = (FrameLayout) view;
        int i = R.id.iv_bg_image;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_bg_image);
        if (imageView != null) {
            i = R.id.li_browser_copylink;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_browser_copylink);
            if (linearLayout != null) {
                i = R.id.li_browser_in_new_tab;
                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.li_browser_in_new_tab);
                if (relativeLayout != null) {
                    i = R.id.li_browser_open_direction;
                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_browser_open_direction);
                    if (linearLayout2 != null) {
                        i = R.id.li_browser_outside;
                        LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_browser_outside);
                        if (linearLayout3 != null) {
                            i = R.id.li_browser_url;
                            LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_browser_url);
                            if (linearLayout4 != null) {
                                i = R.id.li_content;
                                LinearLayout linearLayout5 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_content);
                                if (linearLayout5 != null) {
                                    i = R.id.tv_browser_longpress_content;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_browser_longpress_content);
                                    if (textView != null) {
                                        return new PopBrowserLongPressBinding(frameLayout, frameLayout, imageView, linearLayout, relativeLayout, linearLayout2, linearLayout3, linearLayout4, linearLayout5, textView);
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
