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
public final class AcDappMyBinding implements ViewBinding {
    public final XTabLayoutV2 llTab;
    private final LinearLayout rootView;
    public final ViewPager2 viewpager;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private AcDappMyBinding(LinearLayout linearLayout, XTabLayoutV2 xTabLayoutV2, ViewPager2 viewPager2) {
        this.rootView = linearLayout;
        this.llTab = xTabLayoutV2;
        this.viewpager = viewPager2;
    }

    public static AcDappMyBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcDappMyBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_dapp_my, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcDappMyBinding bind(View view) {
        int i = R.id.ll_tab;
        XTabLayoutV2 xTabLayoutV2 = (XTabLayoutV2) ViewBindings.findChildViewById(view, R.id.ll_tab);
        if (xTabLayoutV2 != null) {
            i = R.id.viewpager;
            ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, R.id.viewpager);
            if (viewPager2 != null) {
                return new AcDappMyBinding((LinearLayout) view, xTabLayoutV2, viewPager2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
