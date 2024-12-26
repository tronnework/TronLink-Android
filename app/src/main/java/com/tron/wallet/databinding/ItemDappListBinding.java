package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tronlinkpro.wallet.R;
public final class ItemDappListBinding implements ViewBinding {
    public final RelativeLayout rl;
    private final RelativeLayout rootView;
    public final SimpleDraweeView simpleDraweeView0;
    public final TextView tvName0;
    public final TextView tvNameContent0;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemDappListBinding(RelativeLayout relativeLayout, RelativeLayout relativeLayout2, SimpleDraweeView simpleDraweeView, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.rl = relativeLayout2;
        this.simpleDraweeView0 = simpleDraweeView;
        this.tvName0 = textView;
        this.tvNameContent0 = textView2;
    }

    public static ItemDappListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemDappListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_dapp_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemDappListBinding bind(View view) {
        int i = R.id.rl;
        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl);
        if (relativeLayout != null) {
            i = R.id.simple_drawee_view_0;
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.simple_drawee_view_0);
            if (simpleDraweeView != null) {
                i = R.id.tv_name_0;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name_0);
                if (textView != null) {
                    i = R.id.tv_name_content_0;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name_content_0);
                    if (textView2 != null) {
                        return new ItemDappListBinding((RelativeLayout) view, relativeLayout, simpleDraweeView, textView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
