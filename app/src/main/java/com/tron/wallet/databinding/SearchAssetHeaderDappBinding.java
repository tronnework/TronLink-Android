package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class SearchAssetHeaderDappBinding implements ViewBinding {
    public final ImageView addrssIcon;
    public final EditText etSearch;
    public final ImageView googleSearchIcon;
    public final ImageView ivClear;
    public final LinearLayout llSearch;
    public final LinearLayout llTronscanSearch;
    public final RelativeLayout rlAddrssAccess;
    public final RelativeLayout rlGoogleSearch;
    public final RelativeLayout rlSearch;
    private final RelativeLayout rootView;
    public final ImageView searchIcon;
    public final ImageView tronscanSearchIcon;
    public final TextView tvAddress;
    public final TextView tvAddressTitle;
    public final TextView tvCancel;
    public final TextView tvGoogleSearchIconTitle;
    public final TextView tvGoogleSearchKeyword;
    public final TextView tvTronscanSearchAddress;
    public final TextView tvTronscanSearchTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private SearchAssetHeaderDappBinding(RelativeLayout relativeLayout, ImageView imageView, EditText editText, ImageView imageView2, ImageView imageView3, LinearLayout linearLayout, LinearLayout linearLayout2, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, ImageView imageView4, ImageView imageView5, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7) {
        this.rootView = relativeLayout;
        this.addrssIcon = imageView;
        this.etSearch = editText;
        this.googleSearchIcon = imageView2;
        this.ivClear = imageView3;
        this.llSearch = linearLayout;
        this.llTronscanSearch = linearLayout2;
        this.rlAddrssAccess = relativeLayout2;
        this.rlGoogleSearch = relativeLayout3;
        this.rlSearch = relativeLayout4;
        this.searchIcon = imageView4;
        this.tronscanSearchIcon = imageView5;
        this.tvAddress = textView;
        this.tvAddressTitle = textView2;
        this.tvCancel = textView3;
        this.tvGoogleSearchIconTitle = textView4;
        this.tvGoogleSearchKeyword = textView5;
        this.tvTronscanSearchAddress = textView6;
        this.tvTronscanSearchTitle = textView7;
    }

    public static SearchAssetHeaderDappBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static SearchAssetHeaderDappBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.search_asset_header_dapp, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static SearchAssetHeaderDappBinding bind(View view) {
        int i = R.id.addrss_icon;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.addrss_icon);
        if (imageView != null) {
            i = R.id.et_search;
            EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.et_search);
            if (editText != null) {
                i = R.id.google_search_icon;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.google_search_icon);
                if (imageView2 != null) {
                    i = R.id.iv_clear;
                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_clear);
                    if (imageView3 != null) {
                        i = R.id.ll_search;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_search);
                        if (linearLayout != null) {
                            i = R.id.ll_tronscan_search;
                            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_tronscan_search);
                            if (linearLayout2 != null) {
                                i = R.id.rl_addrss_access;
                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_addrss_access);
                                if (relativeLayout != null) {
                                    i = R.id.rl_google_search;
                                    RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_google_search);
                                    if (relativeLayout2 != null) {
                                        i = R.id.rl_search;
                                        RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_search);
                                        if (relativeLayout3 != null) {
                                            i = R.id.search_icon;
                                            ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.search_icon);
                                            if (imageView4 != null) {
                                                i = R.id.tronscan_search_icon;
                                                ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, R.id.tronscan_search_icon);
                                                if (imageView5 != null) {
                                                    i = R.id.tv_address;
                                                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address);
                                                    if (textView != null) {
                                                        i = R.id.tv_address_title;
                                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address_title);
                                                        if (textView2 != null) {
                                                            i = R.id.tv_cancel;
                                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_cancel);
                                                            if (textView3 != null) {
                                                                i = R.id.tv_google_search_icon_title;
                                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_google_search_icon_title);
                                                                if (textView4 != null) {
                                                                    i = R.id.tv_google_search_keyword;
                                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_google_search_keyword);
                                                                    if (textView5 != null) {
                                                                        i = R.id.tv_tronscan_search_address;
                                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_tronscan_search_address);
                                                                        if (textView6 != null) {
                                                                            i = R.id.tv_tronscan_search_title;
                                                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_tronscan_search_title);
                                                                            if (textView7 != null) {
                                                                                return new SearchAssetHeaderDappBinding((RelativeLayout) view, imageView, editText, imageView2, imageView3, linearLayout, linearLayout2, relativeLayout, relativeLayout2, relativeLayout3, imageView4, imageView5, textView, textView2, textView3, textView4, textView5, textView6, textView7);
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
