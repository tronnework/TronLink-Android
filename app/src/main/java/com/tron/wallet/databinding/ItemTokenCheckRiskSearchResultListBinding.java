package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.business.security.tokencheck.view.TokenTagFlowLayout;
import com.tron.wallet.common.components.TokenLogoDraweeView;
import com.tronlinkpro.wallet.R;
public final class ItemTokenCheckRiskSearchResultListBinding implements ViewBinding {
    public final TextView assetsCount;
    public final TextView assetsName;
    public final TextView assetsPrice;
    public final TextView assetsTag;
    public final TokenLogoDraweeView ivLogo;
    public final LinearLayout llAssetTag;
    public final ConstraintLayout llAssetname;
    public final RelativeLayout llContent;
    public final RelativeLayout llLogo;
    public final TextView price;
    public final RelativeLayout rlAssetName;
    public final RelativeLayout rlInner;
    public final RelativeLayout rlMain;
    private final RelativeLayout rootView;
    public final TokenTagFlowLayout tags;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemTokenCheckRiskSearchResultListBinding(RelativeLayout relativeLayout, TextView textView, TextView textView2, TextView textView3, TextView textView4, TokenLogoDraweeView tokenLogoDraweeView, LinearLayout linearLayout, ConstraintLayout constraintLayout, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, TextView textView5, RelativeLayout relativeLayout4, RelativeLayout relativeLayout5, RelativeLayout relativeLayout6, TokenTagFlowLayout tokenTagFlowLayout) {
        this.rootView = relativeLayout;
        this.assetsCount = textView;
        this.assetsName = textView2;
        this.assetsPrice = textView3;
        this.assetsTag = textView4;
        this.ivLogo = tokenLogoDraweeView;
        this.llAssetTag = linearLayout;
        this.llAssetname = constraintLayout;
        this.llContent = relativeLayout2;
        this.llLogo = relativeLayout3;
        this.price = textView5;
        this.rlAssetName = relativeLayout4;
        this.rlInner = relativeLayout5;
        this.rlMain = relativeLayout6;
        this.tags = tokenTagFlowLayout;
    }

    public static ItemTokenCheckRiskSearchResultListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemTokenCheckRiskSearchResultListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_token_check_risk_search_result_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemTokenCheckRiskSearchResultListBinding bind(View view) {
        int i = R.id.assets_count;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.assets_count);
        if (textView != null) {
            i = R.id.assets_name;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.assets_name);
            if (textView2 != null) {
                i = R.id.assets_price;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.assets_price);
                if (textView3 != null) {
                    i = R.id.assets_tag;
                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.assets_tag);
                    if (textView4 != null) {
                        i = R.id.iv_logo;
                        TokenLogoDraweeView tokenLogoDraweeView = (TokenLogoDraweeView) ViewBindings.findChildViewById(view, R.id.iv_logo);
                        if (tokenLogoDraweeView != null) {
                            i = R.id.ll_asset_tag;
                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_asset_tag);
                            if (linearLayout != null) {
                                i = R.id.ll_assetname;
                                ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.ll_assetname);
                                if (constraintLayout != null) {
                                    i = R.id.ll_content;
                                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_content);
                                    if (relativeLayout != null) {
                                        i = R.id.ll_logo;
                                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_logo);
                                        if (relativeLayout2 != null) {
                                            i = R.id.price;
                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.price);
                                            if (textView5 != null) {
                                                i = R.id.rl_asset_name;
                                                RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_asset_name);
                                                if (relativeLayout3 != null) {
                                                    i = R.id.rl_inner;
                                                    RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_inner);
                                                    if (relativeLayout4 != null) {
                                                        RelativeLayout relativeLayout5 = (RelativeLayout) view;
                                                        i = R.id.tags;
                                                        TokenTagFlowLayout tokenTagFlowLayout = (TokenTagFlowLayout) ViewBindings.findChildViewById(view, R.id.tags);
                                                        if (tokenTagFlowLayout != null) {
                                                            return new ItemTokenCheckRiskSearchResultListBinding(relativeLayout5, textView, textView2, textView3, textView4, tokenLogoDraweeView, linearLayout, constraintLayout, relativeLayout, relativeLayout2, textView5, relativeLayout3, relativeLayout4, relativeLayout5, tokenTagFlowLayout);
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
