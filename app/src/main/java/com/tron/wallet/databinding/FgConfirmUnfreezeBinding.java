package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.business.confirm.fg.component.GlobalConfirmButton;
import com.tron.wallet.business.confirm.fg.component.GlobalTitleHeaderView;
import com.tronlinkpro.wallet.R;
public final class FgConfirmUnfreezeBinding implements ViewBinding {
    public final GlobalConfirmButton btnConfirm;
    public final GlobalTitleHeaderView headerView;
    public final RelativeLayout root;
    private final RelativeLayout rootView;
    public final RecyclerView rvExtras;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FgConfirmUnfreezeBinding(RelativeLayout relativeLayout, GlobalConfirmButton globalConfirmButton, GlobalTitleHeaderView globalTitleHeaderView, RelativeLayout relativeLayout2, RecyclerView recyclerView) {
        this.rootView = relativeLayout;
        this.btnConfirm = globalConfirmButton;
        this.headerView = globalTitleHeaderView;
        this.root = relativeLayout2;
        this.rvExtras = recyclerView;
    }

    public static FgConfirmUnfreezeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FgConfirmUnfreezeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fg_confirm_unfreeze, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FgConfirmUnfreezeBinding bind(View view) {
        int i = R.id.btn_confirm;
        GlobalConfirmButton globalConfirmButton = (GlobalConfirmButton) ViewBindings.findChildViewById(view, R.id.btn_confirm);
        if (globalConfirmButton != null) {
            i = R.id.header_view;
            GlobalTitleHeaderView globalTitleHeaderView = (GlobalTitleHeaderView) ViewBindings.findChildViewById(view, R.id.header_view);
            if (globalTitleHeaderView != null) {
                i = R.id.root;
                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.root);
                if (relativeLayout != null) {
                    i = R.id.rv_extras;
                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rv_extras);
                    if (recyclerView != null) {
                        return new FgConfirmUnfreezeBinding((RelativeLayout) view, globalConfirmButton, globalTitleHeaderView, relativeLayout, recyclerView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
