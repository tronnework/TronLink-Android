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
public final class PickerviewIncludeTopbarBinding implements ViewBinding {
    public final Button btnCancel;
    public final Button btnSubmit;
    private final RelativeLayout rootView;
    public final RelativeLayout rvTopbar;
    public final TextView tvTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private PickerviewIncludeTopbarBinding(RelativeLayout relativeLayout, Button button, Button button2, RelativeLayout relativeLayout2, TextView textView) {
        this.rootView = relativeLayout;
        this.btnCancel = button;
        this.btnSubmit = button2;
        this.rvTopbar = relativeLayout2;
        this.tvTitle = textView;
    }

    public static PickerviewIncludeTopbarBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PickerviewIncludeTopbarBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.pickerview_include_topbar, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PickerviewIncludeTopbarBinding bind(View view) {
        int i = R.id.btnCancel;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btnCancel);
        if (button != null) {
            i = R.id.btnSubmit;
            Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.btnSubmit);
            if (button2 != null) {
                RelativeLayout relativeLayout = (RelativeLayout) view;
                i = R.id.tvTitle;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tvTitle);
                if (textView != null) {
                    return new PickerviewIncludeTopbarBinding(relativeLayout, button, button2, relativeLayout, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
