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
public final class LlBackupTipBinding implements ViewBinding {
    public final ImageView ivArrow;
    public final ImageView ivClose;
    public final RelativeLayout rlSafeTip;
    private final RelativeLayout rootView;
    public final TextView tvBackup;
    public final TextView tvDesc;
    public final TextView tvTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private LlBackupTipBinding(RelativeLayout relativeLayout, ImageView imageView, ImageView imageView2, RelativeLayout relativeLayout2, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = relativeLayout;
        this.ivArrow = imageView;
        this.ivClose = imageView2;
        this.rlSafeTip = relativeLayout2;
        this.tvBackup = textView;
        this.tvDesc = textView2;
        this.tvTitle = textView3;
    }

    public static LlBackupTipBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LlBackupTipBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ll_backup_tip, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LlBackupTipBinding bind(View view) {
        int i = R.id.iv_arrow;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_arrow);
        if (imageView != null) {
            i = R.id.iv_close;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_close);
            if (imageView2 != null) {
                RelativeLayout relativeLayout = (RelativeLayout) view;
                i = R.id.tv_backup;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_backup);
                if (textView != null) {
                    i = R.id.tv_desc;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_desc);
                    if (textView2 != null) {
                        i = R.id.tv_title;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                        if (textView3 != null) {
                            return new LlBackupTipBinding(relativeLayout, imageView, imageView2, relativeLayout, textView, textView2, textView3);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
