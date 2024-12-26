package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class FragmentSwapBinding implements ViewBinding {
    public final NotSupportBinding ilNotSupport;
    private final RelativeLayout rootView;
    public final RecyclerView rvMain;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FragmentSwapBinding(RelativeLayout relativeLayout, NotSupportBinding notSupportBinding, RecyclerView recyclerView) {
        this.rootView = relativeLayout;
        this.ilNotSupport = notSupportBinding;
        this.rvMain = recyclerView;
    }

    public static FragmentSwapBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentSwapBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_swap, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentSwapBinding bind(View view) {
        int i = R.id.il_not_support;
        View findChildViewById = ViewBindings.findChildViewById(view, R.id.il_not_support);
        if (findChildViewById != null) {
            NotSupportBinding bind = NotSupportBinding.bind(findChildViewById);
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rv_main);
            if (recyclerView != null) {
                return new FragmentSwapBinding((RelativeLayout) view, bind, recyclerView);
            }
            i = R.id.rv_main;
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
