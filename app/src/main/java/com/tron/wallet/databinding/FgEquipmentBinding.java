package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class FgEquipmentBinding implements ViewBinding {
    public final RecyclerView equipmentList;
    public final EquipmentNoneViewBinding noDeviceLayout;
    private final RelativeLayout rootView;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FgEquipmentBinding(RelativeLayout relativeLayout, RecyclerView recyclerView, EquipmentNoneViewBinding equipmentNoneViewBinding) {
        this.rootView = relativeLayout;
        this.equipmentList = recyclerView;
        this.noDeviceLayout = equipmentNoneViewBinding;
    }

    public static FgEquipmentBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FgEquipmentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fg_equipment, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FgEquipmentBinding bind(View view) {
        int i = R.id.equipment_list;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.equipment_list);
        if (recyclerView != null) {
            i = R.id.no_device_layout;
            View findChildViewById = ViewBindings.findChildViewById(view, R.id.no_device_layout);
            if (findChildViewById != null) {
                return new FgEquipmentBinding((RelativeLayout) view, recyclerView, EquipmentNoneViewBinding.bind(findChildViewById));
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
