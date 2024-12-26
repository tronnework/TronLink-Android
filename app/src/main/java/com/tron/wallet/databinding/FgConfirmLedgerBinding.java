package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class FgConfirmLedgerBinding implements ViewBinding {
    public final FrameLayout flMain;
    public final PopHardwareAddressFailBinding ilPopHardwareAddressFail;
    public final PopHardwareConnectFailBinding ilPopHardwareConnectFail;
    public final PopHardwarelistBinding ilPopHardwarelist;
    public final PopLedgerConfirmGifBinding ilPopLedgerConfirmGif;
    public final RelativeLayout ledgerView;
    private final RelativeLayout rootView;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FgConfirmLedgerBinding(RelativeLayout relativeLayout, FrameLayout frameLayout, PopHardwareAddressFailBinding popHardwareAddressFailBinding, PopHardwareConnectFailBinding popHardwareConnectFailBinding, PopHardwarelistBinding popHardwarelistBinding, PopLedgerConfirmGifBinding popLedgerConfirmGifBinding, RelativeLayout relativeLayout2) {
        this.rootView = relativeLayout;
        this.flMain = frameLayout;
        this.ilPopHardwareAddressFail = popHardwareAddressFailBinding;
        this.ilPopHardwareConnectFail = popHardwareConnectFailBinding;
        this.ilPopHardwarelist = popHardwarelistBinding;
        this.ilPopLedgerConfirmGif = popLedgerConfirmGifBinding;
        this.ledgerView = relativeLayout2;
    }

    public static FgConfirmLedgerBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FgConfirmLedgerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fg_confirm_ledger, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FgConfirmLedgerBinding bind(View view) {
        int i = R.id.fl_main;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.fl_main);
        if (frameLayout != null) {
            i = R.id.il_pop_hardware_address_fail;
            View findChildViewById = ViewBindings.findChildViewById(view, R.id.il_pop_hardware_address_fail);
            if (findChildViewById != null) {
                PopHardwareAddressFailBinding bind = PopHardwareAddressFailBinding.bind(findChildViewById);
                i = R.id.il_pop_hardware_connect_fail;
                View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.il_pop_hardware_connect_fail);
                if (findChildViewById2 != null) {
                    PopHardwareConnectFailBinding bind2 = PopHardwareConnectFailBinding.bind(findChildViewById2);
                    i = R.id.il_pop_hardwarelist;
                    View findChildViewById3 = ViewBindings.findChildViewById(view, R.id.il_pop_hardwarelist);
                    if (findChildViewById3 != null) {
                        PopHardwarelistBinding bind3 = PopHardwarelistBinding.bind(findChildViewById3);
                        i = R.id.il_pop_ledger_confirm_gif;
                        View findChildViewById4 = ViewBindings.findChildViewById(view, R.id.il_pop_ledger_confirm_gif);
                        if (findChildViewById4 != null) {
                            PopLedgerConfirmGifBinding bind4 = PopLedgerConfirmGifBinding.bind(findChildViewById4);
                            i = R.id.ledger_view;
                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ledger_view);
                            if (relativeLayout != null) {
                                return new FgConfirmLedgerBinding((RelativeLayout) view, frameLayout, bind, bind2, bind3, bind4, relativeLayout);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
