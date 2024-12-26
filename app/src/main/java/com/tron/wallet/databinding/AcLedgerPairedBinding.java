package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class AcLedgerPairedBinding implements ViewBinding {
    public final Button btGo;
    public final ConstraintLayout pairedRoot;
    public final ItemEquipmentBinding rlSelectedLedger;
    public final ImageView rlState;
    private final ConstraintLayout rootView;
    public final TextView tvHelp;
    public final TextView tvLedgerState;
    public final TextView tvLedgerStateTips;

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    private AcLedgerPairedBinding(ConstraintLayout constraintLayout, Button button, ConstraintLayout constraintLayout2, ItemEquipmentBinding itemEquipmentBinding, ImageView imageView, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = constraintLayout;
        this.btGo = button;
        this.pairedRoot = constraintLayout2;
        this.rlSelectedLedger = itemEquipmentBinding;
        this.rlState = imageView;
        this.tvHelp = textView;
        this.tvLedgerState = textView2;
        this.tvLedgerStateTips = textView3;
    }

    public static AcLedgerPairedBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcLedgerPairedBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_ledger_paired, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcLedgerPairedBinding bind(View view) {
        int i = R.id.bt_go;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_go);
        if (button != null) {
            ConstraintLayout constraintLayout = (ConstraintLayout) view;
            i = R.id.rl_selected_ledger;
            View findChildViewById = ViewBindings.findChildViewById(view, R.id.rl_selected_ledger);
            if (findChildViewById != null) {
                ItemEquipmentBinding bind = ItemEquipmentBinding.bind(findChildViewById);
                i = R.id.rl_state;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.rl_state);
                if (imageView != null) {
                    i = R.id.tv_help;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_help);
                    if (textView != null) {
                        i = R.id.tv_ledger_state;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_ledger_state);
                        if (textView2 != null) {
                            i = R.id.tv_ledger_state_tips;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_ledger_state_tips);
                            if (textView3 != null) {
                                return new AcLedgerPairedBinding(constraintLayout, button, constraintLayout, bind, imageView, textView, textView2, textView3);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
