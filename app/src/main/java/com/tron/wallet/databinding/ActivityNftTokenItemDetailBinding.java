package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tron.wallet.common.components.ExpandableTextView;
import com.tronlinkpro.wallet.R;
public final class ActivityNftTokenItemDetailBinding implements ViewBinding {
    public final LinearLayout btnBack;
    public final Button btnTransfer;
    public final View divider;
    public final SimpleDraweeView image;
    public final ImageView ivCommonLeft;
    public final LinearLayout llName;
    public final TextView notTransferTag;
    private final NestedScrollView rootView;
    public final ExpandableTextView tvIntroduction;
    public final TextView tvName;
    public final TextView tvTitleIntro;
    public final TextView tvTokenId;

    @Override
    public NestedScrollView getRoot() {
        return this.rootView;
    }

    private ActivityNftTokenItemDetailBinding(NestedScrollView nestedScrollView, LinearLayout linearLayout, Button button, View view, SimpleDraweeView simpleDraweeView, ImageView imageView, LinearLayout linearLayout2, TextView textView, ExpandableTextView expandableTextView, TextView textView2, TextView textView3, TextView textView4) {
        this.rootView = nestedScrollView;
        this.btnBack = linearLayout;
        this.btnTransfer = button;
        this.divider = view;
        this.image = simpleDraweeView;
        this.ivCommonLeft = imageView;
        this.llName = linearLayout2;
        this.notTransferTag = textView;
        this.tvIntroduction = expandableTextView;
        this.tvName = textView2;
        this.tvTitleIntro = textView3;
        this.tvTokenId = textView4;
    }

    public static ActivityNftTokenItemDetailBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityNftTokenItemDetailBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_nft_token_item_detail, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityNftTokenItemDetailBinding bind(View view) {
        int i = R.id.btn_back;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.btn_back);
        if (linearLayout != null) {
            i = R.id.btn_transfer;
            Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_transfer);
            if (button != null) {
                i = R.id.divider;
                View findChildViewById = ViewBindings.findChildViewById(view, R.id.divider);
                if (findChildViewById != null) {
                    i = R.id.image;
                    SimpleDraweeView simpleDraweeView = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.image);
                    if (simpleDraweeView != null) {
                        i = R.id.iv_common_left;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_common_left);
                        if (imageView != null) {
                            i = R.id.ll_name;
                            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_name);
                            if (linearLayout2 != null) {
                                i = R.id.not_transfer_tag;
                                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.not_transfer_tag);
                                if (textView != null) {
                                    i = R.id.tv_introduction;
                                    ExpandableTextView expandableTextView = (ExpandableTextView) ViewBindings.findChildViewById(view, R.id.tv_introduction);
                                    if (expandableTextView != null) {
                                        i = R.id.tv_name;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name);
                                        if (textView2 != null) {
                                            i = R.id.tv_title_intro;
                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title_intro);
                                            if (textView3 != null) {
                                                i = R.id.tv_token_id;
                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_token_id);
                                                if (textView4 != null) {
                                                    return new ActivityNftTokenItemDetailBinding((NestedScrollView) view, linearLayout, button, findChildViewById, simpleDraweeView, imageView, linearLayout2, textView, expandableTextView, textView2, textView3, textView4);
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
