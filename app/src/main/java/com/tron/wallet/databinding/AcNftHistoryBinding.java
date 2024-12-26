package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.ptr.PtrHTFrameLayoutV2;
import com.tronlinkpro.wallet.R;
public final class AcNftHistoryBinding implements ViewBinding {
    public final ImageView progressView;
    public final PtrHTFrameLayoutV2 pullToRefresh;
    public final CoordinatorLayout rcList;
    private final RelativeLayout rootView;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcNftHistoryBinding(RelativeLayout relativeLayout, ImageView imageView, PtrHTFrameLayoutV2 ptrHTFrameLayoutV2, CoordinatorLayout coordinatorLayout) {
        this.rootView = relativeLayout;
        this.progressView = imageView;
        this.pullToRefresh = ptrHTFrameLayoutV2;
        this.rcList = coordinatorLayout;
    }

    public static AcNftHistoryBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcNftHistoryBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_nft_history, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcNftHistoryBinding bind(View view) {
        int i = R.id.progress_view;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.progress_view);
        if (imageView != null) {
            i = R.id.pull_to_refresh;
            PtrHTFrameLayoutV2 ptrHTFrameLayoutV2 = (PtrHTFrameLayoutV2) ViewBindings.findChildViewById(view, R.id.pull_to_refresh);
            if (ptrHTFrameLayoutV2 != null) {
                i = R.id.rc_list;
                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) ViewBindings.findChildViewById(view, R.id.rc_list);
                if (coordinatorLayout != null) {
                    return new AcNftHistoryBinding((RelativeLayout) view, imageView, ptrHTFrameLayoutV2, coordinatorLayout);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
