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
public final class LlColdTipBinding implements ViewBinding {
    public final ImageView ivColdClose;
    public final RelativeLayout rlColdTip;
    private final RelativeLayout rootView;
    public final TextView tvColdBackup;
    public final TextView tvColdTitle;
    public final TextView tvDesc;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private LlColdTipBinding(RelativeLayout relativeLayout, ImageView imageView, RelativeLayout relativeLayout2, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = relativeLayout;
        this.ivColdClose = imageView;
        this.rlColdTip = relativeLayout2;
        this.tvColdBackup = textView;
        this.tvColdTitle = textView2;
        this.tvDesc = textView3;
    }

    public static LlColdTipBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LlColdTipBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ll_cold_tip, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LlColdTipBinding bind(View view) {
        int i = R.id.iv_cold_close;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_cold_close);
        if (imageView != null) {
            RelativeLayout relativeLayout = (RelativeLayout) view;
            i = R.id.tv_cold_backup;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_cold_backup);
            if (textView != null) {
                i = R.id.tv_cold_title;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_cold_title);
                if (textView2 != null) {
                    i = R.id.tv_desc;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_desc);
                    if (textView3 != null) {
                        return new LlColdTipBinding(relativeLayout, imageView, relativeLayout, textView, textView2, textView3);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
