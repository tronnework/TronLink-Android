package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.CommonTitleDescriptionEditView;
import com.tron.wallet.common.components.ErrorBottomLayout;
import com.tronlinkpro.wallet.R;
public final class ContentCreateAccountBinding implements ViewBinding {
    public final TextView center;
    public final CommonTitleDescriptionEditView inputWalletName;
    public final ErrorBottomLayout llErrAccount;
    public final LinearLayout llMore;
    public final LinearLayout llNameError;
    private final NestedScrollView rootView;
    public final RecyclerView rvList;
    public final NestedScrollView scrollCreateWallet;
    public final TextView tvHdAddress;
    public final TextView tvHdName;
    public final TextView tvNameError;
    public final TextView tvSwitchAccount;
    public final TextView tvSwitchHd;

    @Override
    public NestedScrollView getRoot() {
        return this.rootView;
    }

    private ContentCreateAccountBinding(NestedScrollView nestedScrollView, TextView textView, CommonTitleDescriptionEditView commonTitleDescriptionEditView, ErrorBottomLayout errorBottomLayout, LinearLayout linearLayout, LinearLayout linearLayout2, RecyclerView recyclerView, NestedScrollView nestedScrollView2, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6) {
        this.rootView = nestedScrollView;
        this.center = textView;
        this.inputWalletName = commonTitleDescriptionEditView;
        this.llErrAccount = errorBottomLayout;
        this.llMore = linearLayout;
        this.llNameError = linearLayout2;
        this.rvList = recyclerView;
        this.scrollCreateWallet = nestedScrollView2;
        this.tvHdAddress = textView2;
        this.tvHdName = textView3;
        this.tvNameError = textView4;
        this.tvSwitchAccount = textView5;
        this.tvSwitchHd = textView6;
    }

    public static ContentCreateAccountBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ContentCreateAccountBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.content_create_account, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ContentCreateAccountBinding bind(View view) {
        int i = R.id.center;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.center);
        if (textView != null) {
            i = R.id.input_wallet_name;
            CommonTitleDescriptionEditView commonTitleDescriptionEditView = (CommonTitleDescriptionEditView) ViewBindings.findChildViewById(view, R.id.input_wallet_name);
            if (commonTitleDescriptionEditView != null) {
                i = R.id.ll_err_account;
                ErrorBottomLayout errorBottomLayout = (ErrorBottomLayout) ViewBindings.findChildViewById(view, R.id.ll_err_account);
                if (errorBottomLayout != null) {
                    i = R.id.ll_more;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_more);
                    if (linearLayout != null) {
                        i = R.id.ll_name_error;
                        LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_name_error);
                        if (linearLayout2 != null) {
                            i = R.id.rv_list;
                            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rv_list);
                            if (recyclerView != null) {
                                NestedScrollView nestedScrollView = (NestedScrollView) view;
                                i = R.id.tv_hd_address;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_hd_address);
                                if (textView2 != null) {
                                    i = R.id.tv_hd_name;
                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_hd_name);
                                    if (textView3 != null) {
                                        i = R.id.tv_name_error;
                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name_error);
                                        if (textView4 != null) {
                                            i = R.id.tv_switch_account;
                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_switch_account);
                                            if (textView5 != null) {
                                                i = R.id.tv_switch_hd;
                                                TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_switch_hd);
                                                if (textView6 != null) {
                                                    return new ContentCreateAccountBinding(nestedScrollView, textView, commonTitleDescriptionEditView, errorBottomLayout, linearLayout, linearLayout2, recyclerView, nestedScrollView, textView2, textView3, textView4, textView5, textView6);
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
