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
public final class ItemUnstakeResourceHolderBinding implements ViewBinding {
    public final ItemDecorationUnstakeGroupBinding groupDivider;
    public final ImageView ivIcon;
    public final ImageView ivSelect;
    public final RelativeLayout llContainer;
    private final RelativeLayout rootView;
    public final TextView tvAddress;
    public final TextView tvBalance;
    public final TextView tvExpireTime;
    public final TextView tvName;
    public final TextView tvTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemUnstakeResourceHolderBinding(RelativeLayout relativeLayout, ItemDecorationUnstakeGroupBinding itemDecorationUnstakeGroupBinding, ImageView imageView, ImageView imageView2, RelativeLayout relativeLayout2, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5) {
        this.rootView = relativeLayout;
        this.groupDivider = itemDecorationUnstakeGroupBinding;
        this.ivIcon = imageView;
        this.ivSelect = imageView2;
        this.llContainer = relativeLayout2;
        this.tvAddress = textView;
        this.tvBalance = textView2;
        this.tvExpireTime = textView3;
        this.tvName = textView4;
        this.tvTitle = textView5;
    }

    public static ItemUnstakeResourceHolderBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemUnstakeResourceHolderBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_unstake_resource_holder, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemUnstakeResourceHolderBinding bind(View view) {
        int i = R.id.group_divider;
        View findChildViewById = ViewBindings.findChildViewById(view, R.id.group_divider);
        if (findChildViewById != null) {
            ItemDecorationUnstakeGroupBinding bind = ItemDecorationUnstakeGroupBinding.bind(findChildViewById);
            i = R.id.iv_icon;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_icon);
            if (imageView != null) {
                i = R.id.iv_select;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_select);
                if (imageView2 != null) {
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
                                            return new ItemUnstakeResourceHolderBinding((RelativeLayout) view, bind, imageView, imageView2, relativeLayout, textView, textView2, textView3, textView4, textView5);
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
