package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.tronlinkpro.wallet.R;
public final class TvErrTabBinding implements ViewBinding {
    private final TextView rootView;

    @Override
    public TextView getRoot() {
        return this.rootView;
    }

    private TvErrTabBinding(TextView textView) {
        this.rootView = textView;
    }

    public static TvErrTabBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static TvErrTabBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.tv_err_tab, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static TvErrTabBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        return new TvErrTabBinding((TextView) view);
    }
}
