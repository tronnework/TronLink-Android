package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class PopRootRiskBinding implements ViewBinding {
    public final Button btKnow;
    public final RelativeLayout rlMark;
    private final RelativeLayout rootView;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private PopRootRiskBinding(RelativeLayout relativeLayout, Button button, RelativeLayout relativeLayout2) {
        this.rootView = relativeLayout;
        this.btKnow = button;
        this.rlMark = relativeLayout2;
    }

    public static PopRootRiskBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopRootRiskBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.pop_root_risk, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopRootRiskBinding bind(View view) {
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_know);
        if (button != null) {
            RelativeLayout relativeLayout = (RelativeLayout) view;
            return new PopRootRiskBinding(relativeLayout, button, relativeLayout);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.bt_know)));
    }
}
