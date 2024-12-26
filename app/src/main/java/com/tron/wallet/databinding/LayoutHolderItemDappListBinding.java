package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tronlinkpro.wallet.R;
public final class LayoutHolderItemDappListBinding implements ViewBinding {
    public final SimpleDraweeView image;
    public final LinearLayout llText;
    private final RelativeLayout rootView;
    public final TextView tvSubtitle;
    public final TextView tvTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private LayoutHolderItemDappListBinding(RelativeLayout relativeLayout, SimpleDraweeView simpleDraweeView, LinearLayout linearLayout, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.image = simpleDraweeView;
        this.llText = linearLayout;
        this.tvSubtitle = textView;
        this.tvTitle = textView2;
    }

    public static LayoutHolderItemDappListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutHolderItemDappListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_holder_item_dapp_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutHolderItemDappListBinding bind(View view) {
        int i = R.id.image;
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.image);
        if (simpleDraweeView != null) {
            i = R.id.ll_text;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_text);
            if (linearLayout != null) {
                i = R.id.tv_subtitle;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_subtitle);
                if (textView != null) {
                    i = R.id.tv_title;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                    if (textView2 != null) {
                        return new LayoutHolderItemDappListBinding((RelativeLayout) view, simpleDraweeView, linearLayout, textView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
