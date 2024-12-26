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
import com.facebook.drawee.view.SimpleDraweeView;
import com.tronlinkpro.wallet.R;
public final class ItemBrowserBookmarkBinding implements ViewBinding {
    public final SimpleDraweeView image;
    public final ImageView ivSwitch;
    public final LinearLayout liLeft;
    public final LinearLayout llText;
    private final RelativeLayout rootView;
    public final TextView tvSubtitle;
    public final TextView tvTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemBrowserBookmarkBinding(RelativeLayout relativeLayout, SimpleDraweeView simpleDraweeView, ImageView imageView, LinearLayout linearLayout, LinearLayout linearLayout2, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.image = simpleDraweeView;
        this.ivSwitch = imageView;
        this.liLeft = linearLayout;
        this.llText = linearLayout2;
        this.tvSubtitle = textView;
        this.tvTitle = textView2;
    }

    public static ItemBrowserBookmarkBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemBrowserBookmarkBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_browser_bookmark, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemBrowserBookmarkBinding bind(View view) {
        int i = R.id.image;
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.image);
        if (simpleDraweeView != null) {
            i = R.id.iv_switch;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_switch);
            if (imageView != null) {
                i = R.id.li_left;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_left);
                if (linearLayout != null) {
                    i = R.id.ll_text;
                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_text);
                    if (linearLayout2 != null) {
                        i = R.id.tv_subtitle;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_subtitle);
                        if (textView != null) {
                            i = R.id.tv_title;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                            if (textView2 != null) {
                                return new ItemBrowserBookmarkBinding((RelativeLayout) view, simpleDraweeView, imageView, linearLayout, linearLayout2, textView, textView2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
