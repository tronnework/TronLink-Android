package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.tronlinkpro.wallet.R;
public final class TextviewLayoutBinding implements ViewBinding {
    private final TextView rootView;
    public final TextView tv;

    @Override
    public TextView getRoot() {
        return this.rootView;
    }

    private TextviewLayoutBinding(TextView textView, TextView textView2) {
        this.rootView = textView;
        this.tv = textView2;
    }

    public static TextviewLayoutBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static TextviewLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.textview_layout, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static TextviewLayoutBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        TextView textView = (TextView) view;
        return new TextviewLayoutBinding(textView, textView);
    }
}
