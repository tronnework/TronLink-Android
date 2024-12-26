package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.tronlinkpro.wallet.R;
public final class ConfirmUnfreezeInnerRvBinding implements ViewBinding {
    private final RecyclerView rootView;

    @Override
    public RecyclerView getRoot() {
        return this.rootView;
    }

    private ConfirmUnfreezeInnerRvBinding(RecyclerView recyclerView) {
        this.rootView = recyclerView;
    }

    public static ConfirmUnfreezeInnerRvBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ConfirmUnfreezeInnerRvBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.confirm_unfreeze_inner_rv, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ConfirmUnfreezeInnerRvBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        return new ConfirmUnfreezeInnerRvBinding((RecyclerView) view);
    }
}
