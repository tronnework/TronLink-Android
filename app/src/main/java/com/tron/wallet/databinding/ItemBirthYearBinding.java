package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemBirthYearBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final TextView tempValue;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private ItemBirthYearBinding(LinearLayout linearLayout, TextView textView) {
        this.rootView = linearLayout;
        this.tempValue = textView;
    }

    public static ItemBirthYearBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemBirthYearBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_birth_year, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemBirthYearBinding bind(View view) {
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tempValue);
        if (textView != null) {
            return new ItemBirthYearBinding((LinearLayout) view, textView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.tempValue)));
    }
}
