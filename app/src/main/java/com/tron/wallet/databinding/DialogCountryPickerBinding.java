package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class DialogCountryPickerBinding implements ViewBinding {
    public final EditText etSearch;
    private final LinearLayout rootView;
    public final RecyclerView rvCountry;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private DialogCountryPickerBinding(LinearLayout linearLayout, EditText editText, RecyclerView recyclerView) {
        this.rootView = linearLayout;
        this.etSearch = editText;
        this.rvCountry = recyclerView;
    }

    public static DialogCountryPickerBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogCountryPickerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.dialog_country_picker, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static DialogCountryPickerBinding bind(View view) {
        int i = R.id.et_search;
        EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.et_search);
        if (editText != null) {
            i = R.id.rv_country;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rv_country);
            if (recyclerView != null) {
                return new DialogCountryPickerBinding((LinearLayout) view, editText, recyclerView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
