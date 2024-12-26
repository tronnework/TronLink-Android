package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.tronlinkpro.wallet.R;
public final class BrowserSearchHistoryItemBinding implements ViewBinding {
    private final TextView rootView;
    public final TextView tvContent;

    @Override
    public TextView getRoot() {
        return this.rootView;
    }

    private BrowserSearchHistoryItemBinding(TextView textView, TextView textView2) {
        this.rootView = textView;
        this.tvContent = textView2;
    }

    public static BrowserSearchHistoryItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static BrowserSearchHistoryItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.browser_search_history_item, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static BrowserSearchHistoryItemBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        TextView textView = (TextView) view;
        return new BrowserSearchHistoryItemBinding(textView, textView);
    }
}
