package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.appbar.AppBarLayout;
import com.tron.wallet.common.components.ptr.PtrHTFrameLayoutV2;
import com.tron.wallet.common.components.xtablayout.XTabLayoutV2;
import com.tronlinkpro.wallet.R;
public final class AcTokencheckBinding implements ViewBinding {
    public final AcTokenCheckHeadBinding acTokenCheckHead;
    public final AppBarLayout appbarLayout;
    public final XTabLayoutV2 llTab;
    public final PtrHTFrameLayoutV2 pullToRefresh;
    public final CoordinatorLayout rcList;
    public final LinearLayout root;
    private final LinearLayout rootView;
    public final TextView tokenCheckRiskTips;
    public final ViewPager2 viewpager;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private AcTokencheckBinding(LinearLayout linearLayout, AcTokenCheckHeadBinding acTokenCheckHeadBinding, AppBarLayout appBarLayout, XTabLayoutV2 xTabLayoutV2, PtrHTFrameLayoutV2 ptrHTFrameLayoutV2, CoordinatorLayout coordinatorLayout, LinearLayout linearLayout2, TextView textView, ViewPager2 viewPager2) {
        this.rootView = linearLayout;
        this.acTokenCheckHead = acTokenCheckHeadBinding;
        this.appbarLayout = appBarLayout;
        this.llTab = xTabLayoutV2;
        this.pullToRefresh = ptrHTFrameLayoutV2;
        this.rcList = coordinatorLayout;
        this.root = linearLayout2;
        this.tokenCheckRiskTips = textView;
        this.viewpager = viewPager2;
    }

    public static AcTokencheckBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcTokencheckBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_tokencheck, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcTokencheckBinding bind(View view) {
        int i = R.id.ac_token_check_head;
        View findChildViewById = ViewBindings.findChildViewById(view, R.id.ac_token_check_head);
        if (findChildViewById != null) {
            AcTokenCheckHeadBinding bind = AcTokenCheckHeadBinding.bind(findChildViewById);
            i = R.id.appbar_layout;
            AppBarLayout appBarLayout = (AppBarLayout) ViewBindings.findChildViewById(view, R.id.appbar_layout);
            if (appBarLayout != null) {
                i = R.id.ll_tab;
                XTabLayoutV2 xTabLayoutV2 = (XTabLayoutV2) ViewBindings.findChildViewById(view, R.id.ll_tab);
                if (xTabLayoutV2 != null) {
                    i = R.id.pull_to_refresh;
                    PtrHTFrameLayoutV2 ptrHTFrameLayoutV2 = (PtrHTFrameLayoutV2) ViewBindings.findChildViewById(view, R.id.pull_to_refresh);
                    if (ptrHTFrameLayoutV2 != null) {
                        i = R.id.rc_list;
                        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) ViewBindings.findChildViewById(view, R.id.rc_list);
                        if (coordinatorLayout != null) {
                            LinearLayout linearLayout = (LinearLayout) view;
                            i = R.id.token_check_risk_tips;
                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.token_check_risk_tips);
                            if (textView != null) {
                                i = R.id.viewpager;
                                ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, R.id.viewpager);
                                if (viewPager2 != null) {
                                    return new AcTokencheckBinding(linearLayout, bind, appBarLayout, xTabLayoutV2, ptrHTFrameLayoutV2, coordinatorLayout, linearLayout, textView, viewPager2);
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
