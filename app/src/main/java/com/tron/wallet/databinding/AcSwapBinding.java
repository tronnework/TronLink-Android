package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewbinding.ViewBinding;
import com.tronlinkpro.wallet.R;
public final class AcSwapBinding implements ViewBinding {
    private final FragmentContainerView rootView;

    @Override
    public FragmentContainerView getRoot() {
        return this.rootView;
    }

    private AcSwapBinding(FragmentContainerView fragmentContainerView) {
        this.rootView = fragmentContainerView;
    }

    public static AcSwapBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcSwapBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_swap, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcSwapBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        return new AcSwapBinding((FragmentContainerView) view);
    }
}
