package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.ItemWarningTagView;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.components.ptr.PtrHTFrameLayout;
import com.tronlinkpro.wallet.R;
public final class ActivityNftTokenListBinding implements ViewBinding {
    public final ImageView ivLoading;
    public final ImageView ivTransfer;
    public final LinearLayout llBottomBar;
    public final LinearLayout llMarket;
    public final LlBlueDashedBinding llMenuLine1;
    public final LlBlueDashedBinding llMenuLine2;
    public final LinearLayout llReceive;
    public final LinearLayout llTransfer;
    public final NoNetView noNetView;
    public final PtrHTFrameLayout rcFrame;
    public final ItemWarningTagView rlScam;
    public final RecyclerView rootRv;
    private final RelativeLayout rootView;
    public final TextView tvTransfer;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ActivityNftTokenListBinding(RelativeLayout relativeLayout, ImageView imageView, ImageView imageView2, LinearLayout linearLayout, LinearLayout linearLayout2, LlBlueDashedBinding llBlueDashedBinding, LlBlueDashedBinding llBlueDashedBinding2, LinearLayout linearLayout3, LinearLayout linearLayout4, NoNetView noNetView, PtrHTFrameLayout ptrHTFrameLayout, ItemWarningTagView itemWarningTagView, RecyclerView recyclerView, TextView textView) {
        this.rootView = relativeLayout;
        this.ivLoading = imageView;
        this.ivTransfer = imageView2;
        this.llBottomBar = linearLayout;
        this.llMarket = linearLayout2;
        this.llMenuLine1 = llBlueDashedBinding;
        this.llMenuLine2 = llBlueDashedBinding2;
        this.llReceive = linearLayout3;
        this.llTransfer = linearLayout4;
        this.noNetView = noNetView;
        this.rcFrame = ptrHTFrameLayout;
        this.rlScam = itemWarningTagView;
        this.rootRv = recyclerView;
        this.tvTransfer = textView;
    }

    public static ActivityNftTokenListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityNftTokenListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_nft_token_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityNftTokenListBinding bind(View view) {
        int i = R.id.iv_loading;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_loading);
        if (imageView != null) {
            i = R.id.iv_transfer;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_transfer);
            if (imageView2 != null) {
                i = R.id.ll_bottom_bar;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_bottom_bar);
                if (linearLayout != null) {
                    i = R.id.ll_market;
                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_market);
                    if (linearLayout2 != null) {
                        i = R.id.ll_menu_line1;
                        View findChildViewById = ViewBindings.findChildViewById(view, R.id.ll_menu_line1);
                        if (findChildViewById != null) {
                            LlBlueDashedBinding bind = LlBlueDashedBinding.bind(findChildViewById);
                            i = R.id.ll_menu_line2;
                            View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.ll_menu_line2);
                            if (findChildViewById2 != null) {
                                LlBlueDashedBinding bind2 = LlBlueDashedBinding.bind(findChildViewById2);
                                i = R.id.ll_receive;
                                LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_receive);
                                if (linearLayout3 != null) {
                                    i = R.id.ll_transfer;
                                    LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_transfer);
                                    if (linearLayout4 != null) {
                                        i = R.id.no_net_view;
                                        NoNetView noNetView = (NoNetView) ViewBindings.findChildViewById(view, R.id.no_net_view);
                                        if (noNetView != null) {
                                            i = R.id.rc_frame;
                                            PtrHTFrameLayout ptrHTFrameLayout = (PtrHTFrameLayout) ViewBindings.findChildViewById(view, R.id.rc_frame);
                                            if (ptrHTFrameLayout != null) {
                                                i = R.id.rl_scam;
                                                ItemWarningTagView itemWarningTagView = (ItemWarningTagView) ViewBindings.findChildViewById(view, R.id.rl_scam);
                                                if (itemWarningTagView != null) {
                                                    i = R.id.root_rv;
                                                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.root_rv);
                                                    if (recyclerView != null) {
                                                        i = R.id.tv_transfer;
                                                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_transfer);
                                                        if (textView != null) {
                                                            return new ActivityNftTokenListBinding((RelativeLayout) view, imageView, imageView2, linearLayout, linearLayout2, bind, bind2, linearLayout3, linearLayout4, noNetView, ptrHTFrameLayout, itemWarningTagView, recyclerView, textView);
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
