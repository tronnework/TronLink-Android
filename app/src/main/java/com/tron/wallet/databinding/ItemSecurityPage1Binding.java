package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.business.security.components.SecurityHorizontalView;
import com.tronlinkpro.wallet.R;
public final class ItemSecurityPage1Binding implements ViewBinding {
    public final SecurityHorizontalView btnStart;
    public final LinearLayout rootPage1;
    private final LinearLayout rootView;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private ItemSecurityPage1Binding(LinearLayout linearLayout, SecurityHorizontalView securityHorizontalView, LinearLayout linearLayout2) {
        this.rootView = linearLayout;
        this.btnStart = securityHorizontalView;
        this.rootPage1 = linearLayout2;
    }

    public static ItemSecurityPage1Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemSecurityPage1Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_security_page_1, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemSecurityPage1Binding bind(View view) {
        SecurityHorizontalView securityHorizontalView = (SecurityHorizontalView) ViewBindings.findChildViewById(view, R.id.btn_start);
        if (securityHorizontalView != null) {
            LinearLayout linearLayout = (LinearLayout) view;
            return new ItemSecurityPage1Binding(linearLayout, securityHorizontalView, linearLayout);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.btn_start)));
    }
}
