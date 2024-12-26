package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemStakeDetailResourceHolderBinding implements ViewBinding {
    public final ItemDecorationUnstakeGroupBinding groupDivider;
    public final ImageView ivIcon;
    public final RelativeLayout llContainer;
    private final RelativeLayout rootView;
    public final TextView tvAddress;
    public final TextView tvBalance;
    public final TextView tvExpireTime;
    public final TextView tvName;
    public final TextView tvTitle;
    public final TextView tvUnfreeze;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemStakeDetailResourceHolderBinding(RelativeLayout relativeLayout, ItemDecorationUnstakeGroupBinding itemDecorationUnstakeGroupBinding, ImageView imageView, RelativeLayout relativeLayout2, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6) {
        this.rootView = relativeLayout;
        this.groupDivider = itemDecorationUnstakeGroupBinding;
        this.ivIcon = imageView;
        this.llContainer = relativeLayout2;
        this.tvAddress = textView;
        this.tvBalance = textView2;
        this.tvExpireTime = textView3;
        this.tvName = textView4;
        this.tvTitle = textView5;
        this.tvUnfreeze = textView6;
    }

    public static ItemStakeDetailResourceHolderBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemStakeDetailResourceHolderBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_stake_detail_resource_holder, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemStakeDetailResourceHolderBinding bind(View view) {
        int i = R.id.group_divider;
        View findChildViewById = ViewBindings.findChildViewById(view, R.id.group_divider);
        if (findChildViewById != null) {
            ItemDecorationUnstakeGroupBinding bind = ItemDecorationUnstakeGroupBinding.bind(findChildViewById);
            i = R.id.iv_icon;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_icon);
            if (imageView != null) {
                i = R.id.ll_container;
                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_container);
                if (relativeLayout != null) {
                    i = R.id.tv_address;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address);
                    if (textView != null) {
                        i = R.id.tv_balance;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_balance);
                        if (textView2 != null) {
                            i = R.id.tv_expire_time;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_expire_time);
                            if (textView3 != null) {
                                i = R.id.tv_Name;
                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_Name);
                                if (textView4 != null) {
                                    i = R.id.tv_title;
                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                                    if (textView5 != null) {
                                        i = R.id.tv_unfreeze;
                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_unfreeze);
                                        if (textView6 != null) {
                                            return new ItemStakeDetailResourceHolderBinding((RelativeLayout) view, bind, imageView, relativeLayout, textView, textView2, textView3, textView4, textView5, textView6);
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
