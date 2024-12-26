package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class NotSupportBinding implements ViewBinding {
    public final LinearLayout llUnderstand;
    public final RelativeLayout rlNotSupport;
    private final RelativeLayout rootView;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private NotSupportBinding(RelativeLayout relativeLayout, LinearLayout linearLayout, RelativeLayout relativeLayout2) {
        this.rootView = relativeLayout;
        this.llUnderstand = linearLayout;
        this.rlNotSupport = relativeLayout2;
    }

    public static NotSupportBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static NotSupportBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.not_support, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static NotSupportBinding bind(View view) {
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_understand);
        if (linearLayout != null) {
            RelativeLayout relativeLayout = (RelativeLayout) view;
            return new NotSupportBinding(relativeLayout, linearLayout, relativeLayout);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.ll_understand)));
    }
}
