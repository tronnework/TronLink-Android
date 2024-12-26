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
import com.tronlinkpro.wallet.R;
public final class PopupSelectWalletFinanceSummaryBinding implements ViewBinding {
    public final ImageView ivAllAssetSelect;
    public final ImageView ivAsk;
    public final ImageView ivCommonRight;
    public final ImageView ivPlaceHolder;
    public final ImageView ivPlaceHolderBody;
    public final RelativeLayout llAllAsset;
    public final LinearLayout llContent;
    public final RelativeLayout rlTitle;
    public final RelativeLayout root;
    private final RelativeLayout rootView;
    public final RecyclerView rvList;
    public final TextView tvFinanceAsset;
    public final TextView tvFinanceAssetAll;
    public final TextView tvFinanceAssetPercent;
    public final TextView tvFinanceAssetTitle;
    public final TextView tvTitle;
    public final TextView tvWalletList;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private PopupSelectWalletFinanceSummaryBinding(RelativeLayout relativeLayout, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, RelativeLayout relativeLayout2, LinearLayout linearLayout, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, RecyclerView recyclerView, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6) {
        this.rootView = relativeLayout;
        this.ivAllAssetSelect = imageView;
        this.ivAsk = imageView2;
        this.ivCommonRight = imageView3;
        this.ivPlaceHolder = imageView4;
        this.ivPlaceHolderBody = imageView5;
        this.llAllAsset = relativeLayout2;
        this.llContent = linearLayout;
        this.rlTitle = relativeLayout3;
        this.root = relativeLayout4;
        this.rvList = recyclerView;
        this.tvFinanceAsset = textView;
        this.tvFinanceAssetAll = textView2;
        this.tvFinanceAssetPercent = textView3;
        this.tvFinanceAssetTitle = textView4;
        this.tvTitle = textView5;
        this.tvWalletList = textView6;
    }

    public static PopupSelectWalletFinanceSummaryBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopupSelectWalletFinanceSummaryBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.popup_select_wallet_finance_summary, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopupSelectWalletFinanceSummaryBinding bind(View view) {
        int i = R.id.iv_all_asset_select;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_all_asset_select);
        if (imageView != null) {
            i = R.id.iv_ask;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_ask);
            if (imageView2 != null) {
                i = R.id.iv_common_right;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_common_right);
                if (imageView3 != null) {
                    i = R.id.iv_place_holder;
                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_place_holder);
                    if (imageView4 != null) {
                        i = R.id.iv_place_holder_body;
                        ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_place_holder_body);
                        if (imageView5 != null) {
                            i = R.id.ll_all_asset;
                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_all_asset);
                            if (relativeLayout != null) {
                                i = R.id.ll_content;
                                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_content);
                                if (linearLayout != null) {
                                    i = R.id.rl_title;
                                    RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_title);
                                    if (relativeLayout2 != null) {
                                        RelativeLayout relativeLayout3 = (RelativeLayout) view;
                                        i = R.id.rv_list;
                                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rv_list);
                                        if (recyclerView != null) {
                                            i = R.id.tv_finance_asset;
                                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_finance_asset);
                                            if (textView != null) {
                                                i = R.id.tv_finance_asset_all;
                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_finance_asset_all);
                                                if (textView2 != null) {
                                                    i = R.id.tv_finance_asset_percent;
                                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_finance_asset_percent);
                                                    if (textView3 != null) {
                                                        i = R.id.tv_finance_asset_title;
                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_finance_asset_title);
                                                        if (textView4 != null) {
                                                            i = R.id.tv_title;
                                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                                                            if (textView5 != null) {
                                                                i = R.id.tv_wallet_list;
                                                                TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_wallet_list);
                                                                if (textView6 != null) {
                                                                    return new PopupSelectWalletFinanceSummaryBinding(relativeLayout3, imageView, imageView2, imageView3, imageView4, imageView5, relativeLayout, linearLayout, relativeLayout2, relativeLayout3, recyclerView, textView, textView2, textView3, textView4, textView5, textView6);
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
