package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.appbar.AppBarLayout;
import com.tron.wallet.common.components.BadgeButton;
import com.tron.wallet.common.components.ptr.PtrHTFrameLayout;
import com.tron.wallet.common.components.xtablayout.XTabLayout;
import com.tronlinkpro.wallet.R;
public final class FgAssetsBinding implements ViewBinding {
    public final AppBarLayout appbarLayout;
    public final CoordinatorLayout coordinatorLayout;
    public final View divider;
    public final ImageView ivAddAssets;
    public final ImageView ivSort;
    public final LlSigndealTipBinding llMneBackup;
    public final LlSigndealTipBinding llMultiPermission;
    public final LlSigndealTipBinding llMultiSign;
    public final LinearLayout llNewAssetsCount;
    public final LlSigndealTipBinding llSecAsk;
    public final XTabLayout llTab;
    public final PtrHTFrameLayout rcFrame;
    public final RecyclerView rcList;
    public final RelativeLayout rlBtnContainer;
    public final RelativeLayout rlSort;
    private final RelativeLayout rootView;
    public final BadgeButton textNewAssetsCount;
    public final TextView tvSortBy;
    public final ViewPager viewPagerContent;
    public final AssetWalletNameLayoutBinding walletNameLayout;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FgAssetsBinding(RelativeLayout relativeLayout, AppBarLayout appBarLayout, CoordinatorLayout coordinatorLayout, View view, ImageView imageView, ImageView imageView2, LlSigndealTipBinding llSigndealTipBinding, LlSigndealTipBinding llSigndealTipBinding2, LlSigndealTipBinding llSigndealTipBinding3, LinearLayout linearLayout, LlSigndealTipBinding llSigndealTipBinding4, XTabLayout xTabLayout, PtrHTFrameLayout ptrHTFrameLayout, RecyclerView recyclerView, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, BadgeButton badgeButton, TextView textView, ViewPager viewPager, AssetWalletNameLayoutBinding assetWalletNameLayoutBinding) {
        this.rootView = relativeLayout;
        this.appbarLayout = appBarLayout;
        this.coordinatorLayout = coordinatorLayout;
        this.divider = view;
        this.ivAddAssets = imageView;
        this.ivSort = imageView2;
        this.llMneBackup = llSigndealTipBinding;
        this.llMultiPermission = llSigndealTipBinding2;
        this.llMultiSign = llSigndealTipBinding3;
        this.llNewAssetsCount = linearLayout;
        this.llSecAsk = llSigndealTipBinding4;
        this.llTab = xTabLayout;
        this.rcFrame = ptrHTFrameLayout;
        this.rcList = recyclerView;
        this.rlBtnContainer = relativeLayout2;
        this.rlSort = relativeLayout3;
        this.textNewAssetsCount = badgeButton;
        this.tvSortBy = textView;
        this.viewPagerContent = viewPager;
        this.walletNameLayout = assetWalletNameLayoutBinding;
    }

    public static FgAssetsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FgAssetsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fg_assets, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FgAssetsBinding bind(View view) {
        int i = R.id.appbar_layout;
        AppBarLayout appBarLayout = (AppBarLayout) ViewBindings.findChildViewById(view, R.id.appbar_layout);
        if (appBarLayout != null) {
            i = R.id.coordinator_layout;
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) ViewBindings.findChildViewById(view, R.id.coordinator_layout);
            if (coordinatorLayout != null) {
                i = R.id.divider;
                View findChildViewById = ViewBindings.findChildViewById(view, R.id.divider);
                if (findChildViewById != null) {
                    i = R.id.iv_add_assets;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_add_assets);
                    if (imageView != null) {
                        i = R.id.iv_sort;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_sort);
                        if (imageView2 != null) {
                            i = R.id.ll_mne_backup;
                            View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.ll_mne_backup);
                            if (findChildViewById2 != null) {
                                LlSigndealTipBinding bind = LlSigndealTipBinding.bind(findChildViewById2);
                                i = R.id.ll_multi_permission;
                                View findChildViewById3 = ViewBindings.findChildViewById(view, R.id.ll_multi_permission);
                                if (findChildViewById3 != null) {
                                    LlSigndealTipBinding bind2 = LlSigndealTipBinding.bind(findChildViewById3);
                                    i = R.id.ll_multi_sign;
                                    View findChildViewById4 = ViewBindings.findChildViewById(view, R.id.ll_multi_sign);
                                    if (findChildViewById4 != null) {
                                        LlSigndealTipBinding bind3 = LlSigndealTipBinding.bind(findChildViewById4);
                                        i = R.id.ll_new_assets_count;
                                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_new_assets_count);
                                        if (linearLayout != null) {
                                            i = R.id.ll_sec_ask;
                                            View findChildViewById5 = ViewBindings.findChildViewById(view, R.id.ll_sec_ask);
                                            if (findChildViewById5 != null) {
                                                LlSigndealTipBinding bind4 = LlSigndealTipBinding.bind(findChildViewById5);
                                                i = R.id.ll_tab;
                                                XTabLayout xTabLayout = (XTabLayout) ViewBindings.findChildViewById(view, R.id.ll_tab);
                                                if (xTabLayout != null) {
                                                    i = R.id.rc_frame;
                                                    PtrHTFrameLayout ptrHTFrameLayout = (PtrHTFrameLayout) ViewBindings.findChildViewById(view, R.id.rc_frame);
                                                    if (ptrHTFrameLayout != null) {
                                                        i = R.id.rc_list;
                                                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rc_list);
                                                        if (recyclerView != null) {
                                                            i = R.id.rl_btn_container;
                                                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_btn_container);
                                                            if (relativeLayout != null) {
                                                                i = R.id.rl_sort;
                                                                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_sort);
                                                                if (relativeLayout2 != null) {
                                                                    i = R.id.text_new_assets_count;
                                                                    BadgeButton badgeButton = (BadgeButton) ViewBindings.findChildViewById(view, R.id.text_new_assets_count);
                                                                    if (badgeButton != null) {
                                                                        i = R.id.tv_sort_by;
                                                                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_sort_by);
                                                                        if (textView != null) {
                                                                            i = R.id.view_pager_content;
                                                                            ViewPager viewPager = (ViewPager) ViewBindings.findChildViewById(view, R.id.view_pager_content);
                                                                            if (viewPager != null) {
                                                                                i = R.id.wallet_name_layout;
                                                                                View findChildViewById6 = ViewBindings.findChildViewById(view, R.id.wallet_name_layout);
                                                                                if (findChildViewById6 != null) {
                                                                                    return new FgAssetsBinding((RelativeLayout) view, appBarLayout, coordinatorLayout, findChildViewById, imageView, imageView2, bind, bind2, bind3, linearLayout, bind4, xTabLayout, ptrHTFrameLayout, recyclerView, relativeLayout, relativeLayout2, badgeButton, textView, viewPager, AssetWalletNameLayoutBinding.bind(findChildViewById6));
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
