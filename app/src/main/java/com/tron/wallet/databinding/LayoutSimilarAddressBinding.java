package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class LayoutSimilarAddressBinding implements ViewBinding {
    public final ImageView ivWarning;
    private final ConstraintLayout rootView;
    public final TextView tvInfo;
    public final AppCompatTextView tvSimilarAddressLink;
    public final AppCompatTextView tvSimilarAddressWarning;
    public final TextView tvSubContent;
    public final TextView tvTitle;

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    private LayoutSimilarAddressBinding(ConstraintLayout constraintLayout, ImageView imageView, TextView textView, AppCompatTextView appCompatTextView, AppCompatTextView appCompatTextView2, TextView textView2, TextView textView3) {
        this.rootView = constraintLayout;
        this.ivWarning = imageView;
        this.tvInfo = textView;
        this.tvSimilarAddressLink = appCompatTextView;
        this.tvSimilarAddressWarning = appCompatTextView2;
        this.tvSubContent = textView2;
        this.tvTitle = textView3;
    }

    public static LayoutSimilarAddressBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutSimilarAddressBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_similar_address, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutSimilarAddressBinding bind(View view) {
        int i = R.id.iv_warning;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_warning);
        if (imageView != null) {
            i = R.id.tv_info;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_info);
            if (textView != null) {
                i = R.id.tv_similar_address_link;
                AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tv_similar_address_link);
                if (appCompatTextView != null) {
                    i = R.id.tv_similar_address_warning;
                    AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tv_similar_address_warning);
                    if (appCompatTextView2 != null) {
                        i = R.id.tv_sub_content;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_sub_content);
                        if (textView2 != null) {
                            i = R.id.tv_title;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                            if (textView3 != null) {
                                return new LayoutSimilarAddressBinding((ConstraintLayout) view, imageView, textView, appCompatTextView, appCompatTextView2, textView2, textView3);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
