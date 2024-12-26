package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class MsPopMoreBinding implements ViewBinding {
    public final ImageView ivDelete;
    public final ImageView ivEdit;
    public final LinearLayout llDelete;
    public final LinearLayout llEdit;
    private final RelativeLayout rootView;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private MsPopMoreBinding(RelativeLayout relativeLayout, ImageView imageView, ImageView imageView2, LinearLayout linearLayout, LinearLayout linearLayout2) {
        this.rootView = relativeLayout;
        this.ivDelete = imageView;
        this.ivEdit = imageView2;
        this.llDelete = linearLayout;
        this.llEdit = linearLayout2;
    }

    public static MsPopMoreBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static MsPopMoreBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ms_pop_more, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static MsPopMoreBinding bind(View view) {
        int i = R.id.iv_delete;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_delete);
        if (imageView != null) {
            i = R.id.iv_edit;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_edit);
            if (imageView2 != null) {
                i = R.id.ll_delete;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_delete);
                if (linearLayout != null) {
                    i = R.id.ll_edit;
                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_edit);
                    if (linearLayout2 != null) {
                        return new MsPopMoreBinding((RelativeLayout) view, imageView, imageView2, linearLayout, linearLayout2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
