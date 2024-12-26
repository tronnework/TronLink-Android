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
import com.tron.wallet.business.ledger.manage.LedgerProgressView;
import com.tronlinkpro.wallet.R;
public final class PopLedgerConfirmGifBinding implements ViewBinding {
    public final ImageView ivCloseForGif;
    public final LinearLayout llHash;
    public final LedgerProgressView loadingView;
    public final RelativeLayout rlLedgerGif;
    private final RelativeLayout rootView;
    public final TextView tvHash;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private PopLedgerConfirmGifBinding(RelativeLayout relativeLayout, ImageView imageView, LinearLayout linearLayout, LedgerProgressView ledgerProgressView, RelativeLayout relativeLayout2, TextView textView) {
        this.rootView = relativeLayout;
        this.ivCloseForGif = imageView;
        this.llHash = linearLayout;
        this.loadingView = ledgerProgressView;
        this.rlLedgerGif = relativeLayout2;
        this.tvHash = textView;
    }

    public static PopLedgerConfirmGifBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopLedgerConfirmGifBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.pop_ledger_confirm_gif, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopLedgerConfirmGifBinding bind(View view) {
        int i = R.id.iv_close_for_gif;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_close_for_gif);
        if (imageView != null) {
            i = R.id.ll_hash;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_hash);
            if (linearLayout != null) {
                i = R.id.loading_view;
                LedgerProgressView ledgerProgressView = (LedgerProgressView) ViewBindings.findChildViewById(view, R.id.loading_view);
                if (ledgerProgressView != null) {
                    RelativeLayout relativeLayout = (RelativeLayout) view;
                    i = R.id.tv_hash;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_hash);
                    if (textView != null) {
                        return new PopLedgerConfirmGifBinding(relativeLayout, imageView, linearLayout, ledgerProgressView, relativeLayout, textView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
