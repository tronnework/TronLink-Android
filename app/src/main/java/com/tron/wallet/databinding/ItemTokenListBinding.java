package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemTokenListBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final TextView tokenId;
    public final TextView tokenName;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private ItemTokenListBinding(LinearLayout linearLayout, TextView textView, TextView textView2) {
        this.rootView = linearLayout;
        this.tokenId = textView;
        this.tokenName = textView2;
    }

    public static ItemTokenListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemTokenListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_token_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemTokenListBinding bind(View view) {
        int i = R.id.token_id;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.token_id);
        if (textView != null) {
            i = R.id.token_name;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.token_name);
            if (textView2 != null) {
                return new ItemTokenListBinding((LinearLayout) view, textView, textView2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
