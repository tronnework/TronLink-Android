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
public final class PopMultiImportBinding implements ViewBinding {
    public final Button btKnow;
    public final RelativeLayout rlMark;
    private final RelativeLayout rootView;
    public final TextView tvUrl;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private PopMultiImportBinding(RelativeLayout relativeLayout, Button button, RelativeLayout relativeLayout2, TextView textView) {
        this.rootView = relativeLayout;
        this.btKnow = button;
        this.rlMark = relativeLayout2;
        this.tvUrl = textView;
    }

    public static PopMultiImportBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopMultiImportBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.pop_multi_import, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopMultiImportBinding bind(View view) {
        int i = R.id.bt_know;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_know);
        if (button != null) {
            RelativeLayout relativeLayout = (RelativeLayout) view;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_url);
            if (textView != null) {
                return new PopMultiImportBinding(relativeLayout, button, relativeLayout, textView);
            }
            i = R.id.tv_url;
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
