package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.CustomTokenNoFunctionView;
import com.tron.wallet.common.components.TokenLogoDraweeView;
import com.tronlinkpro.wallet.R;
public final class ItemTokenHeadBinding implements ViewBinding {
    public final TextView assetsName;
    public final TextView assetsSubname;
    public final TextView assetsTag;
    public final View divider;
    public final TokenLogoDraweeView imgToken;
    public final ImageView ivAssetsOfficial;
    public final ImageView ivNational;
    public final ImageView ivOfficialImage;
    public final ImageView ivRankingTips;
    public final ImageView ivRightIcon;
    public final LinearLayout llAssetTag;
    public final ConstraintLayout llAssetname;
    public final LinearLayout llContent;
    public final RelativeLayout llLogo;
    public final LinearLayout llPrice;
    public final LinearLayout llTronscanRating;
    public final CustomTokenNoFunctionView rcTips;
    public final RelativeLayout rlInner;
    public final RelativeLayout rlTokenTop;
    public final RelativeLayout rlTronscan;
    private final LinearLayout rootView;
    public final TextView tvMarket;
    public final TextView tvPrice;
    public final TextView tvRanking;
    public final TextView tvVdd;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private ItemTokenHeadBinding(LinearLayout linearLayout, TextView textView, TextView textView2, TextView textView3, View view, TokenLogoDraweeView tokenLogoDraweeView, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, LinearLayout linearLayout2, ConstraintLayout constraintLayout, LinearLayout linearLayout3, RelativeLayout relativeLayout, LinearLayout linearLayout4, LinearLayout linearLayout5, CustomTokenNoFunctionView customTokenNoFunctionView, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, TextView textView4, TextView textView5, TextView textView6, TextView textView7) {
        this.rootView = linearLayout;
        this.assetsName = textView;
        this.assetsSubname = textView2;
        this.assetsTag = textView3;
        this.divider = view;
        this.imgToken = tokenLogoDraweeView;
        this.ivAssetsOfficial = imageView;
        this.ivNational = imageView2;
        this.ivOfficialImage = imageView3;
        this.ivRankingTips = imageView4;
        this.ivRightIcon = imageView5;
        this.llAssetTag = linearLayout2;
        this.llAssetname = constraintLayout;
        this.llContent = linearLayout3;
        this.llLogo = relativeLayout;
        this.llPrice = linearLayout4;
        this.llTronscanRating = linearLayout5;
        this.rcTips = customTokenNoFunctionView;
        this.rlInner = relativeLayout2;
        this.rlTokenTop = relativeLayout3;
        this.rlTronscan = relativeLayout4;
        this.tvMarket = textView4;
        this.tvPrice = textView5;
        this.tvRanking = textView6;
        this.tvVdd = textView7;
    }

    public static ItemTokenHeadBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemTokenHeadBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_token_head, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemTokenHeadBinding bind(View view) {
        int i = R.id.assets_name;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.assets_name);
        if (textView != null) {
            i = R.id.assets_subname;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.assets_subname);
            if (textView2 != null) {
                i = R.id.assets_tag;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.assets_tag);
                if (textView3 != null) {
                    i = R.id.divider;
                    View findChildViewById = ViewBindings.findChildViewById(view, R.id.divider);
                    if (findChildViewById != null) {
                        i = R.id.img_token;
                        TokenLogoDraweeView tokenLogoDraweeView = (TokenLogoDraweeView) ViewBindings.findChildViewById(view, R.id.img_token);
                        if (tokenLogoDraweeView != null) {
                            i = R.id.iv_assets_official;
                            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_assets_official);
                            if (imageView != null) {
                                i = R.id.iv_national;
                                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_national);
                                if (imageView2 != null) {
                                    i = R.id.iv_official_image;
                                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_official_image);
                                    if (imageView3 != null) {
                                        i = R.id.iv_ranking_tips;
                                        ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_ranking_tips);
                                        if (imageView4 != null) {
                                            i = R.id.iv_right_icon;
                                            ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_right_icon);
                                            if (imageView5 != null) {
                                                i = R.id.ll_asset_tag;
                                                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_asset_tag);
                                                if (linearLayout != null) {
                                                    i = R.id.ll_assetname;
                                                    ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.ll_assetname);
                                                    if (constraintLayout != null) {
                                                        i = R.id.ll_content;
                                                        LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_content);
                                                        if (linearLayout2 != null) {
                                                            i = R.id.ll_logo;
                                                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_logo);
                                                            if (relativeLayout != null) {
                                                                i = R.id.ll_price;
                                                                LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_price);
                                                                if (linearLayout3 != null) {
                                                                    i = R.id.ll_tronscan_rating;
                                                                    LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_tronscan_rating);
                                                                    if (linearLayout4 != null) {
                                                                        i = R.id.rc_tips;
                                                                        CustomTokenNoFunctionView customTokenNoFunctionView = (CustomTokenNoFunctionView) ViewBindings.findChildViewById(view, R.id.rc_tips);
                                                                        if (customTokenNoFunctionView != null) {
                                                                            i = R.id.rl_inner;
                                                                            RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_inner);
                                                                            if (relativeLayout2 != null) {
                                                                                i = R.id.rl_token_top;
                                                                                RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_token_top);
                                                                                if (relativeLayout3 != null) {
                                                                                    i = R.id.rl_tronscan;
                                                                                    RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_tronscan);
                                                                                    if (relativeLayout4 != null) {
                                                                                        i = R.id.tv_market;
                                                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_market);
                                                                                        if (textView4 != null) {
                                                                                            i = R.id.tv_price;
                                                                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_price);
                                                                                            if (textView5 != null) {
                                                                                                i = R.id.tv_ranking;
                                                                                                TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_ranking);
                                                                                                if (textView6 != null) {
                                                                                                    i = R.id.tv_vdd;
                                                                                                    TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_vdd);
                                                                                                    if (textView7 != null) {
                                                                                                        return new ItemTokenHeadBinding((LinearLayout) view, textView, textView2, textView3, findChildViewById, tokenLogoDraweeView, imageView, imageView2, imageView3, imageView4, imageView5, linearLayout, constraintLayout, linearLayout2, relativeLayout, linearLayout3, linearLayout4, customTokenNoFunctionView, relativeLayout2, relativeLayout3, relativeLayout4, textView4, textView5, textView6, textView7);
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
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
