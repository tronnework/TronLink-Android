package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.tron.wallet.common.components.xtablayout.XTabLayoutV2;
import com.tronlinkpro.wallet.R;
public final class AcAssetsBinding implements ViewBinding {
    public final XTabLayoutV2 llTab;
    public final RelativeLayout root;
    private final RelativeLayout rootView;
    public final ViewPager2 viewpager;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcAssetsBinding(RelativeLayout relativeLayout, XTabLayoutV2 xTabLayoutV2, RelativeLayout relativeLayout2, ViewPager2 viewPager2) {
        this.rootView = relativeLayout;
        this.llTab = xTabLayoutV2;
        this.root = relativeLayout2;
        this.viewpager = viewPager2;
    }

    public static AcAssetsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcAssetsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_assets, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcAssetsBinding bind(View view) {
        int i = R.id.ll_tab;
        XTabLayoutV2 xTabLayoutV2 = (XTabLayoutV2) ViewBindings.findChildViewById(view, R.id.ll_tab);
        if (xTabLayoutV2 != null) {
            RelativeLayout relativeLayout = (RelativeLayout) view;
            ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, R.id.viewpager);
            if (viewPager2 != null) {
                return new AcAssetsBinding(relativeLayout, xTabLayoutV2, relativeLayout, viewPager2);
            }
            i = R.id.viewpager;
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
