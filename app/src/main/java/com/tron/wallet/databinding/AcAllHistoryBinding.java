package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.tron.wallet.common.components.xtablayout.XTabLayoutV2;
import com.tronlinkpro.wallet.R;
public final class AcAllHistoryBinding implements ViewBinding {
    public final LinearLayout root;
    private final LinearLayout rootView;
    public final XTabLayoutV2 tablayout;
    public final ViewPager2 vpContent;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private AcAllHistoryBinding(LinearLayout linearLayout, LinearLayout linearLayout2, XTabLayoutV2 xTabLayoutV2, ViewPager2 viewPager2) {
        this.rootView = linearLayout;
        this.root = linearLayout2;
        this.tablayout = xTabLayoutV2;
        this.vpContent = viewPager2;
    }

    public static AcAllHistoryBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcAllHistoryBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_all_history, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcAllHistoryBinding bind(View view) {
        LinearLayout linearLayout = (LinearLayout) view;
        int i = R.id.tablayout;
        XTabLayoutV2 xTabLayoutV2 = (XTabLayoutV2) ViewBindings.findChildViewById(view, R.id.tablayout);
        if (xTabLayoutV2 != null) {
            i = R.id.vp_content;
            ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, R.id.vp_content);
            if (viewPager2 != null) {
                return new AcAllHistoryBinding(linearLayout, linearLayout, xTabLayoutV2, viewPager2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
