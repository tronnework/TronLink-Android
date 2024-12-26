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
public final class AcChainSettingBinding implements ViewBinding {
    public final ImageView ivMainnetSelect;
    public final ImageView ivNileChainSelect;
    public final ImageView ivShastaSelect;
    public final RelativeLayout rlMainnet;
    public final RelativeLayout rlNile;
    public final RelativeLayout rlShasta;
    public final LinearLayout root;
    private final LinearLayout rootView;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private AcChainSettingBinding(LinearLayout linearLayout, ImageView imageView, ImageView imageView2, ImageView imageView3, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, LinearLayout linearLayout2) {
        this.rootView = linearLayout;
        this.ivMainnetSelect = imageView;
        this.ivNileChainSelect = imageView2;
        this.ivShastaSelect = imageView3;
        this.rlMainnet = relativeLayout;
        this.rlNile = relativeLayout2;
        this.rlShasta = relativeLayout3;
        this.root = linearLayout2;
    }

    public static AcChainSettingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcChainSettingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_chain_setting, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcChainSettingBinding bind(View view) {
        int i = R.id.iv_mainnet_select;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_mainnet_select);
        if (imageView != null) {
            i = R.id.iv_nile_chain_select;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_nile_chain_select);
            if (imageView2 != null) {
                i = R.id.iv_shasta_select;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_shasta_select);
                if (imageView3 != null) {
                    i = R.id.rl_mainnet;
                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_mainnet);
                    if (relativeLayout != null) {
                        i = R.id.rl_nile;
                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_nile);
                        if (relativeLayout2 != null) {
                            i = R.id.rl_shasta;
                            RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_shasta);
                            if (relativeLayout3 != null) {
                                LinearLayout linearLayout = (LinearLayout) view;
                                return new AcChainSettingBinding(linearLayout, imageView, imageView2, imageView3, relativeLayout, relativeLayout2, relativeLayout3, linearLayout);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
