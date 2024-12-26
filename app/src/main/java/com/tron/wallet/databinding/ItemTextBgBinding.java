package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemTextBgBinding implements ViewBinding {
    public final TextView position;
    private final RelativeLayout rootView;
    public final TextView text;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemTextBgBinding(RelativeLayout relativeLayout, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.position = textView;
        this.text = textView2;
    }

    public static ItemTextBgBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemTextBgBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_text_bg, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemTextBgBinding bind(View view) {
        int i = R.id.position;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.position);
        if (textView != null) {
            i = R.id.text;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.text);
            if (textView2 != null) {
                return new ItemTextBgBinding((RelativeLayout) view, textView, textView2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
