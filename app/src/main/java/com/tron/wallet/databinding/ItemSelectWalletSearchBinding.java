package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemSelectWalletSearchBinding implements ViewBinding {
    public final TextView etSearch;
    private final RelativeLayout rootView;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemSelectWalletSearchBinding(RelativeLayout relativeLayout, TextView textView) {
        this.rootView = relativeLayout;
        this.etSearch = textView;
    }

    public static ItemSelectWalletSearchBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemSelectWalletSearchBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_select_wallet_search, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemSelectWalletSearchBinding bind(View view) {
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.et_search);
        if (textView != null) {
            return new ItemSelectWalletSearchBinding((RelativeLayout) view, textView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.et_search)));
    }
}
