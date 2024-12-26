package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.tron.wallet.common.components.xtablayout.XTabLayoutV2;
import com.tronlinkpro.wallet.R;
public final class AcTokencheckResultFragmentBinding implements ViewBinding {
    public final XTabLayoutV2 llTab;
    public final LinearLayout root;
    private final RelativeLayout rootView;
    public final ViewPager2 viewpager;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcTokencheckResultFragmentBinding(RelativeLayout relativeLayout, XTabLayoutV2 xTabLayoutV2, LinearLayout linearLayout, ViewPager2 viewPager2) {
        this.rootView = relativeLayout;
        this.llTab = xTabLayoutV2;
        this.root = linearLayout;
        this.viewpager = viewPager2;
    }

    public static AcTokencheckResultFragmentBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcTokencheckResultFragmentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_tokencheck_result_fragment, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcTokencheckResultFragmentBinding bind(View view) {
        int i = R.id.ll_tab;
        XTabLayoutV2 xTabLayoutV2 = (XTabLayoutV2) ViewBindings.findChildViewById(view, R.id.ll_tab);
        if (xTabLayoutV2 != null) {
            i = R.id.root;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.root);
            if (linearLayout != null) {
                i = R.id.viewpager;
                ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, R.id.viewpager);
                if (viewPager2 != null) {
                    return new AcTokencheckResultFragmentBinding((RelativeLayout) view, xTabLayoutV2, linearLayout, viewPager2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
