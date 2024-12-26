package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ActivityUnableAddressBinding implements ViewBinding {
    public final LedgerNoDeviceBinding ledgerNoDevice;
    private final RelativeLayout rootView;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ActivityUnableAddressBinding(RelativeLayout relativeLayout, LedgerNoDeviceBinding ledgerNoDeviceBinding) {
        this.rootView = relativeLayout;
        this.ledgerNoDevice = ledgerNoDeviceBinding;
    }

    public static ActivityUnableAddressBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityUnableAddressBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_unable_address, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityUnableAddressBinding bind(View view) {
        View findChildViewById = ViewBindings.findChildViewById(view, R.id.ledger_no_device);
        if (findChildViewById != null) {
            return new ActivityUnableAddressBinding((RelativeLayout) view, LedgerNoDeviceBinding.bind(findChildViewById));
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.ledger_no_device)));
    }
}
