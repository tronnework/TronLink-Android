package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class AcTestOwnerverifyBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final TextView text;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private AcTestOwnerverifyBinding(LinearLayout linearLayout, TextView textView) {
        this.rootView = linearLayout;
        this.text = textView;
    }

    public static AcTestOwnerverifyBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcTestOwnerverifyBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_test_ownerverify, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcTestOwnerverifyBinding bind(View view) {
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.text);
        if (textView != null) {
            return new AcTestOwnerverifyBinding((LinearLayout) view, textView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.text)));
    }
}
