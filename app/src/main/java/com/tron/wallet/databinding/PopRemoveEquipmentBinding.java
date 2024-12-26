package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class PopRemoveEquipmentBinding implements ViewBinding {
    public final Button remove;
    private final RelativeLayout rootView;
    public final RelativeLayout rootview;
    public final TextView tvName;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private PopRemoveEquipmentBinding(RelativeLayout relativeLayout, Button button, RelativeLayout relativeLayout2, TextView textView) {
        this.rootView = relativeLayout;
        this.remove = button;
        this.rootview = relativeLayout2;
        this.tvName = textView;
    }

    public static PopRemoveEquipmentBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopRemoveEquipmentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.pop_remove_equipment, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopRemoveEquipmentBinding bind(View view) {
        int i = R.id.remove;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.remove);
        if (button != null) {
            RelativeLayout relativeLayout = (RelativeLayout) view;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name);
            if (textView != null) {
                return new PopRemoveEquipmentBinding(relativeLayout, button, relativeLayout, textView);
            }
            i = R.id.tv_name;
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
