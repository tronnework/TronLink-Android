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
import com.tronlinkpro.wallet.R;
public final class ItemAddressBookBinding implements ViewBinding {
    public final View ivLineBottom;
    public final ImageView ivScam;
    public final LinearLayout nameLayout;
    public final RelativeLayout rlAddress;
    public final RelativeLayout rlAddressCopy;
    public final ImageView rlAddressEdit;
    public final RelativeLayout rlItem;
    private final LinearLayout rootView;
    public final TextView tvAddress;
    public final TextView tvAddressName;
    public final TextView tvDescription;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private ItemAddressBookBinding(LinearLayout linearLayout, View view, ImageView imageView, LinearLayout linearLayout2, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, ImageView imageView2, RelativeLayout relativeLayout3, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = linearLayout;
        this.ivLineBottom = view;
        this.ivScam = imageView;
        this.nameLayout = linearLayout2;
        this.rlAddress = relativeLayout;
        this.rlAddressCopy = relativeLayout2;
        this.rlAddressEdit = imageView2;
        this.rlItem = relativeLayout3;
        this.tvAddress = textView;
        this.tvAddressName = textView2;
        this.tvDescription = textView3;
    }

    public static ItemAddressBookBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemAddressBookBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_address_book, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemAddressBookBinding bind(View view) {
        int i = R.id.iv_line_bottom;
        View findChildViewById = ViewBindings.findChildViewById(view, R.id.iv_line_bottom);
        if (findChildViewById != null) {
            i = R.id.iv_scam;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_scam);
            if (imageView != null) {
                i = R.id.name_layout;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.name_layout);
                if (linearLayout != null) {
                    i = R.id.rl_address;
                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_address);
                    if (relativeLayout != null) {
                        i = R.id.rl_address_copy;
                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_address_copy);
                        if (relativeLayout2 != null) {
                            i = R.id.rl_address_edit;
                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.rl_address_edit);
                            if (imageView2 != null) {
                                i = R.id.rl_item;
                                RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_item);
                                if (relativeLayout3 != null) {
                                    i = R.id.tv_address;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address);
                                    if (textView != null) {
                                        i = R.id.tv_address_name;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address_name);
                                        if (textView2 != null) {
                                            i = R.id.tv_description;
                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_description);
                                            if (textView3 != null) {
                                                return new ItemAddressBookBinding((LinearLayout) view, findChildViewById, imageView, linearLayout, relativeLayout, relativeLayout2, imageView2, relativeLayout3, textView, textView2, textView3);
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
