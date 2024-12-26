package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.tronlinkpro.wallet.R;
public final class LineBinding implements ViewBinding {
    private final View rootView;

    @Override
    public View getRoot() {
        return this.rootView;
    }

    private LineBinding(View view) {
        this.rootView = view;
    }

    public static LineBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LineBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.line, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LineBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        return new LineBinding(view);
    }
}
