package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import com.tronlinkpro.wallet.R;
public final class ContentScrollingBinding implements ViewBinding {
    private final NestedScrollView rootView;

    @Override
    public NestedScrollView getRoot() {
        return this.rootView;
    }

    private ContentScrollingBinding(NestedScrollView nestedScrollView) {
        this.rootView = nestedScrollView;
    }

    public static ContentScrollingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ContentScrollingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.content_scrolling, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ContentScrollingBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        return new ContentScrollingBinding((NestedScrollView) view);
    }
}
