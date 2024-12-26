package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class AcConfirmNewBinding implements ViewBinding {
    public final FrameLayout confirm;
    public final FrameLayout containerBottom;
    public final FrameLayout containerTop;
    public final EmptyViewBinding llNoData;
    public final NonetViewBinding llNoNet;
    public final RelativeLayout root;
    private final RelativeLayout rootView;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcConfirmNewBinding(RelativeLayout relativeLayout, FrameLayout frameLayout, FrameLayout frameLayout2, FrameLayout frameLayout3, EmptyViewBinding emptyViewBinding, NonetViewBinding nonetViewBinding, RelativeLayout relativeLayout2) {
        this.rootView = relativeLayout;
        this.confirm = frameLayout;
        this.containerBottom = frameLayout2;
        this.containerTop = frameLayout3;
        this.llNoData = emptyViewBinding;
        this.llNoNet = nonetViewBinding;
        this.root = relativeLayout2;
    }

    public static AcConfirmNewBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcConfirmNewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_confirm_new, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcConfirmNewBinding bind(View view) {
        int i = R.id.confirm;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.confirm);
        if (frameLayout != null) {
            i = R.id.container_bottom;
            FrameLayout frameLayout2 = (FrameLayout) ViewBindings.findChildViewById(view, R.id.container_bottom);
            if (frameLayout2 != null) {
                i = R.id.container_top;
                FrameLayout frameLayout3 = (FrameLayout) ViewBindings.findChildViewById(view, R.id.container_top);
                if (frameLayout3 != null) {
                    i = R.id.ll_no_data;
                    View findChildViewById = ViewBindings.findChildViewById(view, R.id.ll_no_data);
                    if (findChildViewById != null) {
                        EmptyViewBinding bind = EmptyViewBinding.bind(findChildViewById);
                        i = R.id.ll_no_net;
                        View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.ll_no_net);
                        if (findChildViewById2 != null) {
                            RelativeLayout relativeLayout = (RelativeLayout) view;
                            return new AcConfirmNewBinding(relativeLayout, frameLayout, frameLayout2, frameLayout3, bind, NonetViewBinding.bind(findChildViewById2), relativeLayout);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
