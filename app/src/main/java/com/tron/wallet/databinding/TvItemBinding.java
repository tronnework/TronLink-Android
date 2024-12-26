package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.tronlinkpro.wallet.R;
public final class TvItemBinding implements ViewBinding {
    private final TextView rootView;

    @Override
    public TextView getRoot() {
        return this.rootView;
    }

    private TvItemBinding(TextView textView) {
        this.rootView = textView;
    }

    public static TvItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static TvItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.tv_item, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static TvItemBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        return new TvItemBinding((TextView) view);
    }
}
