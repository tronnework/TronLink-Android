package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.CircularImageDraweeView;
import com.tron.wallet.common.components.CustomTokenNoFunctionView;
import com.tronlinkpro.wallet.R;
public final class NftListHeaderBinding implements ViewBinding {
    public final View divider;
    public final ImageView icArrow;
    public final ImageView ivCopy;
    public final CircularImageDraweeView ivLogo;
    public final RelativeLayout llName;
    public final CustomTokenNoFunctionView rcTips;
    public final RelativeLayout rlBg;
    private final LinearLayout rootView;
    public final TextView tvContractAddress;
    public final TextView tvContractAddressTitle;
    public final TextView tvName;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private NftListHeaderBinding(LinearLayout linearLayout, View view, ImageView imageView, ImageView imageView2, CircularImageDraweeView circularImageDraweeView, RelativeLayout relativeLayout, CustomTokenNoFunctionView customTokenNoFunctionView, RelativeLayout relativeLayout2, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = linearLayout;
        this.divider = view;
        this.icArrow = imageView;
        this.ivCopy = imageView2;
        this.ivLogo = circularImageDraweeView;
        this.llName = relativeLayout;
        this.rcTips = customTokenNoFunctionView;
        this.rlBg = relativeLayout2;
        this.tvContractAddress = textView;
        this.tvContractAddressTitle = textView2;
        this.tvName = textView3;
    }

    public static NftListHeaderBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static NftListHeaderBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.nft_list_header, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static NftListHeaderBinding bind(View view) {
        int i = R.id.divider;
        View findChildViewById = ViewBindings.findChildViewById(view, R.id.divider);
        if (findChildViewById != null) {
            i = R.id.ic_arrow;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.ic_arrow);
            if (imageView != null) {
                i = R.id.iv_copy;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_copy);
                if (imageView2 != null) {
                    i = R.id.iv_logo;
                    CircularImageDraweeView circularImageDraweeView = (CircularImageDraweeView) ViewBindings.findChildViewById(view, R.id.iv_logo);
                    if (circularImageDraweeView != null) {
                        i = R.id.ll_name;
                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_name);
                        if (relativeLayout != null) {
                            i = R.id.rc_tips;
                            CustomTokenNoFunctionView customTokenNoFunctionView = (CustomTokenNoFunctionView) ViewBindings.findChildViewById(view, R.id.rc_tips);
                            if (customTokenNoFunctionView != null) {
                                i = R.id.rl_bg;
                                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_bg);
                                if (relativeLayout2 != null) {
                                    i = R.id.tv_contract_address;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_contract_address);
                                    if (textView != null) {
                                        i = R.id.tv_contract_address_title;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_contract_address_title);
                                        if (textView2 != null) {
                                            i = R.id.tv_name;
                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name);
                                            if (textView3 != null) {
                                                return new NftListHeaderBinding((LinearLayout) view, findChildViewById, imageView, imageView2, circularImageDraweeView, relativeLayout, customTokenNoFunctionView, relativeLayout2, textView, textView2, textView3);
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
