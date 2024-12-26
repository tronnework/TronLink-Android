package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class AcExportWatchWalletBinding implements ViewBinding {
    public final LinearLayout btExport;
    public final LinearLayout btImport;
    private final NestedScrollView rootView;

    @Override
    public NestedScrollView getRoot() {
        return this.rootView;
    }

    private AcExportWatchWalletBinding(NestedScrollView nestedScrollView, LinearLayout linearLayout, LinearLayout linearLayout2) {
        this.rootView = nestedScrollView;
        this.btExport = linearLayout;
        this.btImport = linearLayout2;
    }

    public static AcExportWatchWalletBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcExportWatchWalletBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_export_watch_wallet, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcExportWatchWalletBinding bind(View view) {
        int i = R.id.bt_export;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.bt_export);
        if (linearLayout != null) {
            i = R.id.bt_import;
            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.bt_import);
            if (linearLayout2 != null) {
                return new AcExportWatchWalletBinding((NestedScrollView) view, linearLayout, linearLayout2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
