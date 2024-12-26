package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class BottomSheetDialogBinding implements ViewBinding {
    public final LinearLayout bottomSheet;
    public final FrameLayout container;
    public final CoordinatorLayout coordinator;
    private final FrameLayout rootView;
    public final View touchOutside;

    @Override
    public FrameLayout getRoot() {
        return this.rootView;
    }

    private BottomSheetDialogBinding(FrameLayout frameLayout, LinearLayout linearLayout, FrameLayout frameLayout2, CoordinatorLayout coordinatorLayout, View view) {
        this.rootView = frameLayout;
        this.bottomSheet = linearLayout;
        this.container = frameLayout2;
        this.coordinator = coordinatorLayout;
        this.touchOutside = view;
    }

    public static BottomSheetDialogBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static BottomSheetDialogBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.bottom_sheet_dialog, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static BottomSheetDialogBinding bind(View view) {
        int i = R.id.bottom_sheet;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.bottom_sheet);
        if (linearLayout != null) {
            FrameLayout frameLayout = (FrameLayout) view;
            i = R.id.coordinator;
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) ViewBindings.findChildViewById(view, R.id.coordinator);
            if (coordinatorLayout != null) {
                i = R.id.touch_outside;
                View findChildViewById = ViewBindings.findChildViewById(view, R.id.touch_outside);
                if (findChildViewById != null) {
                    return new BottomSheetDialogBinding(frameLayout, linearLayout, frameLayout, coordinatorLayout, findChildViewById);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
