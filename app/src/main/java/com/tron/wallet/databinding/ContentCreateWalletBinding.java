package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.business.walletmanager.createwallet.view.TimelineView;
import com.tronlinkpro.wallet.R;
public final class ContentCreateWalletBinding implements ViewBinding {
    public final FrameLayout frame;
    private final NestedScrollView rootView;
    public final NestedScrollView scrollCreateWallet;
    public final TimelineView timeLineLayout;

    @Override
    public NestedScrollView getRoot() {
        return this.rootView;
    }

    private ContentCreateWalletBinding(NestedScrollView nestedScrollView, FrameLayout frameLayout, NestedScrollView nestedScrollView2, TimelineView timelineView) {
        this.rootView = nestedScrollView;
        this.frame = frameLayout;
        this.scrollCreateWallet = nestedScrollView2;
        this.timeLineLayout = timelineView;
    }

    public static ContentCreateWalletBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ContentCreateWalletBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.content_create_wallet, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ContentCreateWalletBinding bind(View view) {
        int i = R.id.frame;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.frame);
        if (frameLayout != null) {
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            TimelineView timelineView = (TimelineView) ViewBindings.findChildViewById(view, R.id.time_line_layout);
            if (timelineView != null) {
                return new ContentCreateWalletBinding(nestedScrollView, frameLayout, nestedScrollView, timelineView);
            }
            i = R.id.time_line_layout;
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
