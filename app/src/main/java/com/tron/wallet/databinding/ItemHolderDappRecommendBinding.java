package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tronlinkpro.wallet.R;
public final class ItemHolderDappRecommendBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final SimpleDraweeView sdvImage;
    public final TextView tvName;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private ItemHolderDappRecommendBinding(LinearLayout linearLayout, SimpleDraweeView simpleDraweeView, TextView textView) {
        this.rootView = linearLayout;
        this.sdvImage = simpleDraweeView;
        this.tvName = textView;
    }

    public static ItemHolderDappRecommendBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemHolderDappRecommendBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_holder_dapp_recommend, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemHolderDappRecommendBinding bind(View view) {
        int i = R.id.sdv_image;
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.sdv_image);
        if (simpleDraweeView != null) {
            i = R.id.tv_name;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name);
            if (textView != null) {
                return new ItemHolderDappRecommendBinding((LinearLayout) view, simpleDraweeView, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
