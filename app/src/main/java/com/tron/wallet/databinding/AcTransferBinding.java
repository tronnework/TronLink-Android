package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.AppBarLayout;
import com.tron.wallet.common.components.ItemWarningTagView;
import com.tron.wallet.common.components.ptr.PtrHTFrameLayoutV2;
import com.tronlinkpro.wallet.R;
public final class AcTransferBinding implements ViewBinding {
    public final AppBarLayout appbarLayout;
    public final ItemTokenDetailBottomBinding itemTokenDetailBottom;
    public final ImageView ivType;
    public final LinearLayout llBottomBar;
    public final LinearLayout llDeposit;
    public final LinearLayout llMarket;
    public final LlBlueDashedBinding llMenuLine1;
    public final LlBlueDashedBinding llMenuLine2;
    public final LinearLayout llReceive2;
    public final LinearLayout llTransfer2;
    public final LinearLayout llTransferLayout;
    public final ProgressPageTranslucentBinding loadingview;
    public final ImageView progressView;
    public final PtrHTFrameLayoutV2 pullToRefresh;
    public final CoordinatorLayout rcList;
    public final ItemWarningTagView rlScam;
    private final RelativeLayout rootView;
    public final FrameLayout tokenDetailHeader;
    public final TextView tvType;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcTransferBinding(RelativeLayout relativeLayout, AppBarLayout appBarLayout, ItemTokenDetailBottomBinding itemTokenDetailBottomBinding, ImageView imageView, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, LlBlueDashedBinding llBlueDashedBinding, LlBlueDashedBinding llBlueDashedBinding2, LinearLayout linearLayout4, LinearLayout linearLayout5, LinearLayout linearLayout6, ProgressPageTranslucentBinding progressPageTranslucentBinding, ImageView imageView2, PtrHTFrameLayoutV2 ptrHTFrameLayoutV2, CoordinatorLayout coordinatorLayout, ItemWarningTagView itemWarningTagView, FrameLayout frameLayout, TextView textView) {
        this.rootView = relativeLayout;
        this.appbarLayout = appBarLayout;
        this.itemTokenDetailBottom = itemTokenDetailBottomBinding;
        this.ivType = imageView;
        this.llBottomBar = linearLayout;
        this.llDeposit = linearLayout2;
        this.llMarket = linearLayout3;
        this.llMenuLine1 = llBlueDashedBinding;
        this.llMenuLine2 = llBlueDashedBinding2;
        this.llReceive2 = linearLayout4;
        this.llTransfer2 = linearLayout5;
        this.llTransferLayout = linearLayout6;
        this.loadingview = progressPageTranslucentBinding;
        this.progressView = imageView2;
        this.pullToRefresh = ptrHTFrameLayoutV2;
        this.rcList = coordinatorLayout;
        this.rlScam = itemWarningTagView;
        this.tokenDetailHeader = frameLayout;
        this.tvType = textView;
    }

    public static AcTransferBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcTransferBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_transfer, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcTransferBinding bind(View view) {
        int i = R.id.appbar_layout;
        AppBarLayout appBarLayout = (AppBarLayout) ViewBindings.findChildViewById(view, R.id.appbar_layout);
        if (appBarLayout != null) {
            i = R.id.item_token_detail_bottom;
            View findChildViewById = ViewBindings.findChildViewById(view, R.id.item_token_detail_bottom);
            if (findChildViewById != null) {
                ItemTokenDetailBottomBinding bind = ItemTokenDetailBottomBinding.bind(findChildViewById);
                i = R.id.iv_type;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_type);
                if (imageView != null) {
                    i = R.id.ll_bottom_bar;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_bottom_bar);
                    if (linearLayout != null) {
                        i = R.id.ll_deposit;
                        LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_deposit);
                        if (linearLayout2 != null) {
                            i = R.id.ll_market;
                            LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_market);
                            if (linearLayout3 != null) {
                                i = R.id.ll_menu_line1;
                                View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.ll_menu_line1);
                                if (findChildViewById2 != null) {
                                    LlBlueDashedBinding bind2 = LlBlueDashedBinding.bind(findChildViewById2);
                                    i = R.id.ll_menu_line2;
                                    View findChildViewById3 = ViewBindings.findChildViewById(view, R.id.ll_menu_line2);
                                    if (findChildViewById3 != null) {
                                        LlBlueDashedBinding bind3 = LlBlueDashedBinding.bind(findChildViewById3);
                                        i = R.id.ll_receive2;
                                        LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_receive2);
                                        if (linearLayout4 != null) {
                                            i = R.id.ll_transfer2;
                                            LinearLayout linearLayout5 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_transfer2);
                                            if (linearLayout5 != null) {
                                                i = R.id.ll_transfer_layout;
                                                LinearLayout linearLayout6 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_transfer_layout);
                                                if (linearLayout6 != null) {
                                                    i = R.id.loadingview;
                                                    View findChildViewById4 = ViewBindings.findChildViewById(view, R.id.loadingview);
                                                    if (findChildViewById4 != null) {
                                                        ProgressPageTranslucentBinding bind4 = ProgressPageTranslucentBinding.bind(findChildViewById4);
                                                        i = R.id.progress_view;
                                                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.progress_view);
                                                        if (imageView2 != null) {
                                                            i = R.id.pull_to_refresh;
                                                            PtrHTFrameLayoutV2 ptrHTFrameLayoutV2 = (PtrHTFrameLayoutV2) ViewBindings.findChildViewById(view, R.id.pull_to_refresh);
                                                            if (ptrHTFrameLayoutV2 != null) {
                                                                i = R.id.rc_list;
                                                                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) ViewBindings.findChildViewById(view, R.id.rc_list);
                                                                if (coordinatorLayout != null) {
                                                                    i = R.id.rl_scam;
                                                                    ItemWarningTagView itemWarningTagView = (ItemWarningTagView) ViewBindings.findChildViewById(view, R.id.rl_scam);
                                                                    if (itemWarningTagView != null) {
                                                                        i = R.id.token_detail_header;
                                                                        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.token_detail_header);
                                                                        if (frameLayout != null) {
                                                                            i = R.id.tv_type;
                                                                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_type);
                                                                            if (textView != null) {
                                                                                return new AcTransferBinding((RelativeLayout) view, appBarLayout, bind, imageView, linearLayout, linearLayout2, linearLayout3, bind2, bind3, linearLayout4, linearLayout5, linearLayout6, bind4, imageView2, ptrHTFrameLayoutV2, coordinatorLayout, itemWarningTagView, frameLayout, textView);
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
