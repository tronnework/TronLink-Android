package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class PopupDelBrowserHistoryBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final TextView tvDelete;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private PopupDelBrowserHistoryBinding(LinearLayout linearLayout, TextView textView) {
        this.rootView = linearLayout;
        this.tvDelete = textView;
    }

    public static PopupDelBrowserHistoryBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopupDelBrowserHistoryBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.popup_del_browser_history, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopupDelBrowserHistoryBinding bind(View view) {
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_delete);
        if (textView != null) {
            return new PopupDelBrowserHistoryBinding((LinearLayout) view, textView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.tv_delete)));
    }
}
