package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemUpgradeHdListBinding implements ViewBinding {
    public final View line;
    public final RelativeLayout root;
    private final RelativeLayout rootView;
    public final TextView tvWalletAddress;
    public final TextView tvWalletAssets;
    public final TextView tvWalletName;
    public final View vTop;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemUpgradeHdListBinding(RelativeLayout relativeLayout, View view, RelativeLayout relativeLayout2, TextView textView, TextView textView2, TextView textView3, View view2) {
        this.rootView = relativeLayout;
        this.line = view;
        this.root = relativeLayout2;
        this.tvWalletAddress = textView;
        this.tvWalletAssets = textView2;
        this.tvWalletName = textView3;
        this.vTop = view2;
    }

    public static ItemUpgradeHdListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemUpgradeHdListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_upgrade_hd_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemUpgradeHdListBinding bind(View view) {
        int i = R.id.line;
        View findChildViewById = ViewBindings.findChildViewById(view, R.id.line);
        if (findChildViewById != null) {
            RelativeLayout relativeLayout = (RelativeLayout) view;
            i = R.id.tv_wallet_address;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_wallet_address);
            if (textView != null) {
                i = R.id.tv_wallet_assets;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_wallet_assets);
                if (textView2 != null) {
                    i = R.id.tv_wallet_name;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_wallet_name);
                    if (textView3 != null) {
                        i = R.id.v_top;
                        View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.v_top);
                        if (findChildViewById2 != null) {
                            return new ItemUpgradeHdListBinding(relativeLayout, findChildViewById, relativeLayout, textView, textView2, textView3, findChildViewById2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
