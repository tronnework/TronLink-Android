package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemMessageBinding implements ViewBinding {
    public final FrameLayout flIcon;
    public final ImageView ivIcon;
    public final ImageView ivIconPoint;
    private final LinearLayout rootView;
    public final TextView tvAddress;
    public final TextView tvClick;
    public final TextView tvContent;
    public final TextView tvTime;
    public final TextView tvTitle;
    public final View vLine;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private ItemMessageBinding(LinearLayout linearLayout, FrameLayout frameLayout, ImageView imageView, ImageView imageView2, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, View view) {
        this.rootView = linearLayout;
        this.flIcon = frameLayout;
        this.ivIcon = imageView;
        this.ivIconPoint = imageView2;
        this.tvAddress = textView;
        this.tvClick = textView2;
        this.tvContent = textView3;
        this.tvTime = textView4;
        this.tvTitle = textView5;
        this.vLine = view;
    }

    public static ItemMessageBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemMessageBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_message, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemMessageBinding bind(View view) {
        int i = R.id.fl_icon;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.fl_icon);
        if (frameLayout != null) {
            i = R.id.iv_icon;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_icon);
            if (imageView != null) {
                i = R.id.iv_icon_point;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_icon_point);
                if (imageView2 != null) {
                    i = R.id.tv_address;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address);
                    if (textView != null) {
                        i = R.id.tv_click;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_click);
                        if (textView2 != null) {
                            i = R.id.tv_content;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_content);
                            if (textView3 != null) {
                                i = R.id.tv_time;
                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_time);
                                if (textView4 != null) {
                                    i = R.id.tv_title;
                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                                    if (textView5 != null) {
                                        i = R.id.v_line;
                                        View findChildViewById = ViewBindings.findChildViewById(view, R.id.v_line);
                                        if (findChildViewById != null) {
                                            return new ItemMessageBinding((LinearLayout) view, frameLayout, imageView, imageView2, textView, textView2, textView3, textView4, textView5, findChildViewById);
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
