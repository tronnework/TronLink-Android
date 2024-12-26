package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.components.xtablayout.XTabLayoutV2;
import com.tronlinkpro.wallet.R;
public final class ActivityDappListBinding implements ViewBinding {
    public final NoNetView netErrorView;
    public final RelativeLayout placeHolder;
    public final LinearLayout root;
    private final FrameLayout rootView;
    public final XTabLayoutV2 tablayout;
    public final ViewPager2 vpContent;

    @Override
    public FrameLayout getRoot() {
        return this.rootView;
    }

    private ActivityDappListBinding(FrameLayout frameLayout, NoNetView noNetView, RelativeLayout relativeLayout, LinearLayout linearLayout, XTabLayoutV2 xTabLayoutV2, ViewPager2 viewPager2) {
        this.rootView = frameLayout;
        this.netErrorView = noNetView;
        this.placeHolder = relativeLayout;
        this.root = linearLayout;
        this.tablayout = xTabLayoutV2;
        this.vpContent = viewPager2;
    }

    public static ActivityDappListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityDappListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_dapp_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityDappListBinding bind(View view) {
        int i = R.id.net_error_view;
        NoNetView noNetView = (NoNetView) ViewBindings.findChildViewById(view, R.id.net_error_view);
        if (noNetView != null) {
            i = R.id.place_holder;
            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.place_holder);
            if (relativeLayout != null) {
                i = R.id.root;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.root);
                if (linearLayout != null) {
                    i = R.id.tablayout;
                    XTabLayoutV2 xTabLayoutV2 = (XTabLayoutV2) ViewBindings.findChildViewById(view, R.id.tablayout);
                    if (xTabLayoutV2 != null) {
                        i = R.id.vp_content;
                        ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, R.id.vp_content);
                        if (viewPager2 != null) {
                            return new ActivityDappListBinding((FrameLayout) view, noNetView, relativeLayout, linearLayout, xTabLayoutV2, viewPager2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
