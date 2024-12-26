package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class PopFailEquipmentBinding implements ViewBinding {
    public final ImageView ivClose;
    public final Button retry;
    private final RelativeLayout rootView;
    public final RelativeLayout rootview;
    public final TextView tvContent;
    public final TextView tvTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private PopFailEquipmentBinding(RelativeLayout relativeLayout, ImageView imageView, Button button, RelativeLayout relativeLayout2, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.ivClose = imageView;
        this.retry = button;
        this.rootview = relativeLayout2;
        this.tvContent = textView;
        this.tvTitle = textView2;
    }

    public static PopFailEquipmentBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopFailEquipmentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.pop_fail_equipment, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopFailEquipmentBinding bind(View view) {
        int i = R.id.iv_close;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_close);
        if (imageView != null) {
            i = R.id.retry;
            Button button = (Button) ViewBindings.findChildViewById(view, R.id.retry);
            if (button != null) {
                RelativeLayout relativeLayout = (RelativeLayout) view;
                i = R.id.tv_content;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_content);
                if (textView != null) {
                    i = R.id.tv_title;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                    if (textView2 != null) {
                        return new PopFailEquipmentBinding(relativeLayout, imageView, button, relativeLayout, textView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
