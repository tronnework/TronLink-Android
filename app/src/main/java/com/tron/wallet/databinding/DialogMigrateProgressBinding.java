package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.WhiteEnergyProgressView;
import com.tronlinkpro.wallet.R;
public final class DialogMigrateProgressBinding implements ViewBinding {
    public final ImageView ivIcon;
    public final WhiteEnergyProgressView progressView;
    private final ConstraintLayout rootView;
    public final TextView tvProgress;

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    private DialogMigrateProgressBinding(ConstraintLayout constraintLayout, ImageView imageView, WhiteEnergyProgressView whiteEnergyProgressView, TextView textView) {
        this.rootView = constraintLayout;
        this.ivIcon = imageView;
        this.progressView = whiteEnergyProgressView;
        this.tvProgress = textView;
    }

    public static DialogMigrateProgressBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogMigrateProgressBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.dialog_migrate_progress, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static DialogMigrateProgressBinding bind(View view) {
        int i = R.id.iv_icon;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_icon);
        if (imageView != null) {
            i = R.id.progress_view;
            WhiteEnergyProgressView whiteEnergyProgressView = (WhiteEnergyProgressView) ViewBindings.findChildViewById(view, R.id.progress_view);
            if (whiteEnergyProgressView != null) {
                i = R.id.tv_progress;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_progress);
                if (textView != null) {
                    return new DialogMigrateProgressBinding((ConstraintLayout) view, imageView, whiteEnergyProgressView, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
