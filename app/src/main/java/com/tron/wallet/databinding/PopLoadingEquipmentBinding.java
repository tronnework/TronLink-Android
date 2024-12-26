package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.business.ledger.manage.LedgerProgressView;
import com.tronlinkpro.wallet.R;
public final class PopLoadingEquipmentBinding implements ViewBinding {
    public final ImageView ivClose;
    public final LedgerProgressView loadingView;
    private final RelativeLayout rootView;
    public final RelativeLayout rootview;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private PopLoadingEquipmentBinding(RelativeLayout relativeLayout, ImageView imageView, LedgerProgressView ledgerProgressView, RelativeLayout relativeLayout2) {
        this.rootView = relativeLayout;
        this.ivClose = imageView;
        this.loadingView = ledgerProgressView;
        this.rootview = relativeLayout2;
    }

    public static PopLoadingEquipmentBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopLoadingEquipmentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.pop_loading_equipment, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopLoadingEquipmentBinding bind(View view) {
        int i = R.id.iv_close;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_close);
        if (imageView != null) {
            i = R.id.loading_view;
            LedgerProgressView ledgerProgressView = (LedgerProgressView) ViewBindings.findChildViewById(view, R.id.loading_view);
            if (ledgerProgressView != null) {
                RelativeLayout relativeLayout = (RelativeLayout) view;
                return new PopLoadingEquipmentBinding(relativeLayout, imageView, ledgerProgressView, relativeLayout);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
