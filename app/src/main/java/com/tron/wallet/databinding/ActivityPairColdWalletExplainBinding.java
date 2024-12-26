package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ActivityPairColdWalletExplainBinding implements ViewBinding {
    public final TextView btnGo;
    public final ImageView iconColdPairExplain;
    public final LinearLayout llBtnGo;
    private final RelativeLayout rootView;
    public final RelativeLayout topCard;
    public final TextView tvWalletAddress;
    public final TextView tvWalletName;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ActivityPairColdWalletExplainBinding(RelativeLayout relativeLayout, TextView textView, ImageView imageView, LinearLayout linearLayout, RelativeLayout relativeLayout2, TextView textView2, TextView textView3) {
        this.rootView = relativeLayout;
        this.btnGo = textView;
        this.iconColdPairExplain = imageView;
        this.llBtnGo = linearLayout;
        this.topCard = relativeLayout2;
        this.tvWalletAddress = textView2;
        this.tvWalletName = textView3;
    }

    public static ActivityPairColdWalletExplainBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityPairColdWalletExplainBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_pair_cold_wallet_explain, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityPairColdWalletExplainBinding bind(View view) {
        int i = R.id.btn_go;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.btn_go);
        if (textView != null) {
            i = R.id.icon_cold_pair_explain;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.icon_cold_pair_explain);
            if (imageView != null) {
                i = R.id.ll_btn_go;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_btn_go);
                if (linearLayout != null) {
                    i = R.id.top_card;
                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.top_card);
                    if (relativeLayout != null) {
                        i = R.id.tv_wallet_address;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_wallet_address);
                        if (textView2 != null) {
                            i = R.id.tv_wallet_name;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_wallet_name);
                            if (textView3 != null) {
                                return new ActivityPairColdWalletExplainBinding((RelativeLayout) view, textView, imageView, linearLayout, relativeLayout, textView2, textView3);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
