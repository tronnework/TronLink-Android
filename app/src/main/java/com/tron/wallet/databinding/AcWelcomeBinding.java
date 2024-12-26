package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.SimpleDraweeViewGif;
import com.tronlinkpro.wallet.R;
public final class AcWelcomeBinding implements ViewBinding {
    public final ImageView iv;
    public final ProgressBar progressBar;
    public final ConstraintLayout root;
    private final ConstraintLayout rootView;
    public final SimpleDraweeViewGif simpleDraweeView;
    public final TextView tvProgress;
    public final TextView tvSlogan;

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    private AcWelcomeBinding(ConstraintLayout constraintLayout, ImageView imageView, ProgressBar progressBar, ConstraintLayout constraintLayout2, SimpleDraweeViewGif simpleDraweeViewGif, TextView textView, TextView textView2) {
        this.rootView = constraintLayout;
        this.iv = imageView;
        this.progressBar = progressBar;
        this.root = constraintLayout2;
        this.simpleDraweeView = simpleDraweeViewGif;
        this.tvProgress = textView;
        this.tvSlogan = textView2;
    }

    public static AcWelcomeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcWelcomeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_welcome, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcWelcomeBinding bind(View view) {
        int i = R.id.iv;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv);
        if (imageView != null) {
            i = R.id.progress_bar;
            ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, R.id.progress_bar);
            if (progressBar != null) {
                ConstraintLayout constraintLayout = (ConstraintLayout) view;
                i = R.id.simple_drawee_view;
                SimpleDraweeViewGif simpleDraweeViewGif = (SimpleDraweeViewGif) ViewBindings.findChildViewById(view, R.id.simple_drawee_view);
                if (simpleDraweeViewGif != null) {
                    i = R.id.tv_progress;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_progress);
                    if (textView != null) {
                        i = R.id.tv_slogan;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_slogan);
                        if (textView2 != null) {
                            return new AcWelcomeBinding(constraintLayout, imageView, progressBar, constraintLayout, simpleDraweeViewGif, textView, textView2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
