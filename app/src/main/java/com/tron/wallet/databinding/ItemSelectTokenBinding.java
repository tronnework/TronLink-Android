package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tronlinkpro.wallet.R;
public final class ItemSelectTokenBinding implements ViewBinding {
    public final TextView balance;
    public final SimpleDraweeView ivLogo;
    public final ImageView ivSelectTag;
    private final RelativeLayout rootView;
    public final TextView tvName;
    public final TextView tvTokenID;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemSelectTokenBinding(RelativeLayout relativeLayout, TextView textView, SimpleDraweeView simpleDraweeView, ImageView imageView, TextView textView2, TextView textView3) {
        this.rootView = relativeLayout;
        this.balance = textView;
        this.ivLogo = simpleDraweeView;
        this.ivSelectTag = imageView;
        this.tvName = textView2;
        this.tvTokenID = textView3;
    }

    public static ItemSelectTokenBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemSelectTokenBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_select_token, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemSelectTokenBinding bind(View view) {
        int i = R.id.balance;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.balance);
        if (textView != null) {
            i = R.id.iv_logo;
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.iv_logo);
            if (simpleDraweeView != null) {
                i = R.id.iv_select_tag;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_select_tag);
                if (imageView != null) {
                    i = R.id.tv_name;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name);
                    if (textView2 != null) {
                        i = R.id.tv_tokenID;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_tokenID);
                        if (textView3 != null) {
                            return new ItemSelectTokenBinding((RelativeLayout) view, textView, simpleDraweeView, imageView, textView2, textView3);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
