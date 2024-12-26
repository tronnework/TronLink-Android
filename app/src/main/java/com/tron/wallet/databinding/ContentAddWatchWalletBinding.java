package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.CommonTitleDescriptionEditView;
import com.tronlinkpro.wallet.R;
public final class ContentAddWatchWalletBinding implements ViewBinding {
    public final CommonTitleDescriptionEditView addWatchAddress;
    public final CommonTitleDescriptionEditView addWatchName;
    public final View emptyPlaceholder;
    public final LinearLayout liAddWatchWallet;
    public final LinearLayout llAddressError;
    public final LinearLayout llNameError;
    private final NestedScrollView rootView;
    public final NestedScrollView scrollAddWatchWallet;
    public final TextView tvAddressError;
    public final TextView tvNameError;

    @Override
    public NestedScrollView getRoot() {
        return this.rootView;
    }

    private ContentAddWatchWalletBinding(NestedScrollView nestedScrollView, CommonTitleDescriptionEditView commonTitleDescriptionEditView, CommonTitleDescriptionEditView commonTitleDescriptionEditView2, View view, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, NestedScrollView nestedScrollView2, TextView textView, TextView textView2) {
        this.rootView = nestedScrollView;
        this.addWatchAddress = commonTitleDescriptionEditView;
        this.addWatchName = commonTitleDescriptionEditView2;
        this.emptyPlaceholder = view;
        this.liAddWatchWallet = linearLayout;
        this.llAddressError = linearLayout2;
        this.llNameError = linearLayout3;
        this.scrollAddWatchWallet = nestedScrollView2;
        this.tvAddressError = textView;
        this.tvNameError = textView2;
    }

    public static ContentAddWatchWalletBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ContentAddWatchWalletBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.content_add_watch_wallet, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ContentAddWatchWalletBinding bind(View view) {
        int i = R.id.add_watch_address;
        CommonTitleDescriptionEditView commonTitleDescriptionEditView = (CommonTitleDescriptionEditView) ViewBindings.findChildViewById(view, R.id.add_watch_address);
        if (commonTitleDescriptionEditView != null) {
            i = R.id.add_watch_name;
            CommonTitleDescriptionEditView commonTitleDescriptionEditView2 = (CommonTitleDescriptionEditView) ViewBindings.findChildViewById(view, R.id.add_watch_name);
            if (commonTitleDescriptionEditView2 != null) {
                i = R.id.empty_placeholder;
                View findChildViewById = ViewBindings.findChildViewById(view, R.id.empty_placeholder);
                if (findChildViewById != null) {
                    i = R.id.li_add_watch_wallet;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_add_watch_wallet);
                    if (linearLayout != null) {
                        i = R.id.ll_address_error;
                        LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_address_error);
                        if (linearLayout2 != null) {
                            i = R.id.ll_name_error;
                            LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_name_error);
                            if (linearLayout3 != null) {
                                NestedScrollView nestedScrollView = (NestedScrollView) view;
                                i = R.id.tv_address_error;
                                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address_error);
                                if (textView != null) {
                                    i = R.id.tv_name_error;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name_error);
                                    if (textView2 != null) {
                                        return new ContentAddWatchWalletBinding(nestedScrollView, commonTitleDescriptionEditView, commonTitleDescriptionEditView2, findChildViewById, linearLayout, linearLayout2, linearLayout3, nestedScrollView, textView, textView2);
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
