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
public final class ActivityApproveCheckBinding implements ViewBinding {
    public final AppBarLayout appbarLayout;
    public final LinearLayout approveHeader;
    public final PtrHTFrameLayoutV2 pullToRefresh;
    public final CoordinatorLayout rcList;
    public final LinearLayout root;
    private final LinearLayout rootView;
    public final TextView tvAddressAmount;
    public final TextView tvAddressTitle;
    public final TextView tvContractAmount;
    public final TextView tvContractListTitle;
    public final TextView tvContractTitle;
    public final TextView tvRiskAmount;
    public final ViewPager2 vpContent;
    public final XTabLayoutV2 xTabLayout;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private ActivityApproveCheckBinding(LinearLayout linearLayout, AppBarLayout appBarLayout, LinearLayout linearLayout2, PtrHTFrameLayoutV2 ptrHTFrameLayoutV2, CoordinatorLayout coordinatorLayout, LinearLayout linearLayout3, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, ViewPager2 viewPager2, XTabLayoutV2 xTabLayoutV2) {
        this.rootView = linearLayout;
        this.appbarLayout = appBarLayout;
        this.approveHeader = linearLayout2;
        this.pullToRefresh = ptrHTFrameLayoutV2;
        this.rcList = coordinatorLayout;
        this.root = linearLayout3;
        this.tvAddressAmount = textView;
        this.tvAddressTitle = textView2;
        this.tvContractAmount = textView3;
        this.tvContractListTitle = textView4;
        this.tvContractTitle = textView5;
        this.tvRiskAmount = textView6;
        this.vpContent = viewPager2;
        this.xTabLayout = xTabLayoutV2;
    }

    public static ActivityApproveCheckBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityApproveCheckBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_approve_check, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityApproveCheckBinding bind(View view) {
        int i = R.id.appbar_layout;
        AppBarLayout appBarLayout = (AppBarLayout) ViewBindings.findChildViewById(view, R.id.appbar_layout);
        if (appBarLayout != null) {
            i = R.id.approve_header;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.approve_header);
            if (linearLayout != null) {
                i = R.id.pull_to_refresh;
                PtrHTFrameLayoutV2 ptrHTFrameLayoutV2 = (PtrHTFrameLayoutV2) ViewBindings.findChildViewById(view, R.id.pull_to_refresh);
                if (ptrHTFrameLayoutV2 != null) {
                    i = R.id.rc_list;
                    CoordinatorLayout coordinatorLayout = (CoordinatorLayout) ViewBindings.findChildViewById(view, R.id.rc_list);
                    if (coordinatorLayout != null) {
                        LinearLayout linearLayout2 = (LinearLayout) view;
                        i = R.id.tv_address_amount;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address_amount);
                        if (textView != null) {
                            i = R.id.tv_address_title;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address_title);
                            if (textView2 != null) {
                                i = R.id.tv_contract_amount;
                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_contract_amount);
                                if (textView3 != null) {
                                    i = R.id.tv_contract_list_title;
                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_contract_list_title);
                                    if (textView4 != null) {
                                        i = R.id.tv_contract_title;
                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_contract_title);
                                        if (textView5 != null) {
                                            i = R.id.tv_risk_amount;
                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_risk_amount);
                                            if (textView6 != null) {
                                                i = R.id.vp_content;
                                                ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, R.id.vp_content);
                                                if (viewPager2 != null) {
                                                    i = R.id.x_tab_layout;
                                                    XTabLayoutV2 xTabLayoutV2 = (XTabLayoutV2) ViewBindings.findChildViewById(view, R.id.x_tab_layout);
                                                    if (xTabLayoutV2 != null) {
                                                        return new ActivityApproveCheckBinding(linearLayout2, appBarLayout, linearLayout, ptrHTFrameLayoutV2, coordinatorLayout, linearLayout2, textView, textView2, textView3, textView4, textView5, textView6, viewPager2, xTabLayoutV2);
                                                    }
                                                }
                                            }
                                        }
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
