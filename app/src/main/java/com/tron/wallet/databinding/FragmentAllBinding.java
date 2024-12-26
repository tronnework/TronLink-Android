package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.ptr.PtrHTFrameLayout;
import com.tronlinkpro.wallet.R;
public final class FragmentAllBinding implements ViewBinding {
    public final PtrHTFrameLayout rcFrame;
    public final RecyclerView rcList;
    private final RelativeLayout rootView;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FragmentAllBinding(RelativeLayout relativeLayout, PtrHTFrameLayout ptrHTFrameLayout, RecyclerView recyclerView) {
        this.rootView = relativeLayout;
        this.rcFrame = ptrHTFrameLayout;
        this.rcList = recyclerView;
    }

    public static FragmentAllBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentAllBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_all, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentAllBinding bind(View view) {
        int i = R.id.rc_frame;
        PtrHTFrameLayout ptrHTFrameLayout = (PtrHTFrameLayout) ViewBindings.findChildViewById(view, R.id.rc_frame);
        if (ptrHTFrameLayout != null) {
            i = R.id.rc_list;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rc_list);
            if (recyclerView != null) {
                return new FragmentAllBinding((RelativeLayout) view, ptrHTFrameLayout, recyclerView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
