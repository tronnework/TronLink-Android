package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.appbar.AppBarLayout;
import com.tron.wallet.business.tabdapp.component.DAppTitleView;
import com.tron.wallet.common.components.ptr.PtrHTFrameLayout;
import com.tron.wallet.common.components.xtablayout.XTabLayoutV2;
import com.tronlinkpro.wallet.R;
public final class FragmentBrowserHomeBinding implements ViewBinding {
    public final AppBarLayout appBar;
    public final CoordinatorLayout coordinator;
    public final XTabLayoutV2 dappTabs;
    public final DAppTitleView dappTitleView;
    public final Toolbar dappToolbar;
    public final ViewPager2 dappViewPager;
    public final BrowserHeaderLayoutBinding headerContainer;
    public final RelativeLayout llListContainer;
    public final PtrHTFrameLayout rcFrame;
    private final PtrHTFrameLayout rootView;

    @Override
    public PtrHTFrameLayout getRoot() {
        return this.rootView;
    }

    private FragmentBrowserHomeBinding(PtrHTFrameLayout ptrHTFrameLayout, AppBarLayout appBarLayout, CoordinatorLayout coordinatorLayout, XTabLayoutV2 xTabLayoutV2, DAppTitleView dAppTitleView, Toolbar toolbar, ViewPager2 viewPager2, BrowserHeaderLayoutBinding browserHeaderLayoutBinding, RelativeLayout relativeLayout, PtrHTFrameLayout ptrHTFrameLayout2) {
        this.rootView = ptrHTFrameLayout;
        this.appBar = appBarLayout;
        this.coordinator = coordinatorLayout;
        this.dappTabs = xTabLayoutV2;
        this.dappTitleView = dAppTitleView;
        this.dappToolbar = toolbar;
        this.dappViewPager = viewPager2;
        this.headerContainer = browserHeaderLayoutBinding;
        this.llListContainer = relativeLayout;
        this.rcFrame = ptrHTFrameLayout2;
    }

    public static FragmentBrowserHomeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentBrowserHomeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_browser_home, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentBrowserHomeBinding bind(View view) {
        int i = R.id.app_bar;
        AppBarLayout appBarLayout = (AppBarLayout) ViewBindings.findChildViewById(view, R.id.app_bar);
        if (appBarLayout != null) {
            i = R.id.coordinator;
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) ViewBindings.findChildViewById(view, R.id.coordinator);
            if (coordinatorLayout != null) {
                i = R.id.dapp_tabs;
                XTabLayoutV2 xTabLayoutV2 = (XTabLayoutV2) ViewBindings.findChildViewById(view, R.id.dapp_tabs);
                if (xTabLayoutV2 != null) {
                    i = R.id.dapp_title_view;
                    DAppTitleView dAppTitleView = (DAppTitleView) ViewBindings.findChildViewById(view, R.id.dapp_title_view);
                    if (dAppTitleView != null) {
                        i = R.id.dapp_toolbar;
                        Toolbar toolbar = (Toolbar) ViewBindings.findChildViewById(view, R.id.dapp_toolbar);
                        if (toolbar != null) {
                            i = R.id.dapp_view_pager;
                            ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, R.id.dapp_view_pager);
                            if (viewPager2 != null) {
                                i = R.id.header_container;
                                View findChildViewById = ViewBindings.findChildViewById(view, R.id.header_container);
                                if (findChildViewById != null) {
                                    BrowserHeaderLayoutBinding bind = BrowserHeaderLayoutBinding.bind(findChildViewById);
                                    i = R.id.ll_list_container;
                                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_list_container);
                                    if (relativeLayout != null) {
                                        PtrHTFrameLayout ptrHTFrameLayout = (PtrHTFrameLayout) view;
                                        return new FragmentBrowserHomeBinding(ptrHTFrameLayout, appBarLayout, coordinatorLayout, xTabLayoutV2, dAppTitleView, toolbar, viewPager2, bind, relativeLayout, ptrHTFrameLayout);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
