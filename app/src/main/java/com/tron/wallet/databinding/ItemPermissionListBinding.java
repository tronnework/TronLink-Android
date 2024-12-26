package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemPermissionListBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final TextView tvPermissionName;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private ItemPermissionListBinding(LinearLayout linearLayout, TextView textView) {
        this.rootView = linearLayout;
        this.tvPermissionName = textView;
    }

    public static ItemPermissionListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemPermissionListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_permission_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemPermissionListBinding bind(View view) {
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_permission_name);
        if (textView != null) {
            return new ItemPermissionListBinding((LinearLayout) view, textView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.tv_permission_name)));
    }
}
