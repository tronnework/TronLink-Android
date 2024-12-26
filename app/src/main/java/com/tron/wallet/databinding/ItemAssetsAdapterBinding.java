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
import com.tron.wallet.common.components.TokenLogoDraweeView;
import com.tronlinkpro.wallet.R;
public final class ItemAssetsAdapterBinding implements ViewBinding {
    public final TextView assetsCount;
    public final TextView assetsCountHiddenView;
    public final TextView assetsName;
    public final TextView assetsPrice;
    public final TextView assetsPriceHiddenView;
    public final TextView assetsSubName;
    public final RelativeLayout bgLogo;
    public final ImageView ivDefitype;
    public final View ivLine;
    public final TokenLogoDraweeView ivLogo;
    public final ImageView ivNational;
    public final ImageView ivOfficialImage;
    public final ImageView ivRenzheng;
    public final ImageView ivScam;
    public final ConstraintLayout liContent;
    public final LinearLayout llTokenName;
    public final RelativeLayout rlInner;
    public final RelativeLayout rlMain;
    public final RelativeLayout rlStakeAvailable;
    public final ConstraintLayout rlTrx;
    private final RelativeLayout rootView;
    public final TextView tvAvailableTrx;
    public final TextView tvPriceAmount;
    public final TextView tvStakedTrx;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemAssetsAdapterBinding(RelativeLayout relativeLayout, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, RelativeLayout relativeLayout2, ImageView imageView, View view, TokenLogoDraweeView tokenLogoDraweeView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ConstraintLayout constraintLayout, LinearLayout linearLayout, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, RelativeLayout relativeLayout5, ConstraintLayout constraintLayout2, TextView textView7, TextView textView8, TextView textView9) {
        this.rootView = relativeLayout;
        this.assetsCount = textView;
        this.assetsCountHiddenView = textView2;
        this.assetsName = textView3;
        this.assetsPrice = textView4;
        this.assetsPriceHiddenView = textView5;
        this.assetsSubName = textView6;
        this.bgLogo = relativeLayout2;
        this.ivDefitype = imageView;
        this.ivLine = view;
        this.ivLogo = tokenLogoDraweeView;
        this.ivNational = imageView2;
        this.ivOfficialImage = imageView3;
        this.ivRenzheng = imageView4;
        this.ivScam = imageView5;
        this.liContent = constraintLayout;
        this.llTokenName = linearLayout;
        this.rlInner = relativeLayout3;
        this.rlMain = relativeLayout4;
        this.rlStakeAvailable = relativeLayout5;
        this.rlTrx = constraintLayout2;
        this.tvAvailableTrx = textView7;
        this.tvPriceAmount = textView8;
        this.tvStakedTrx = textView9;
    }

    public static ItemAssetsAdapterBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemAssetsAdapterBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_assets_adapter, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemAssetsAdapterBinding bind(View view) {
        int i = R.id.assets_count;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.assets_count);
        if (textView != null) {
            i = R.id.assets_count_hidden_view;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.assets_count_hidden_view);
            if (textView2 != null) {
                i = R.id.assets_name;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.assets_name);
                if (textView3 != null) {
                    i = R.id.assets_price;
                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.assets_price);
                    if (textView4 != null) {
                        i = R.id.assets_price_hidden_view;
                        TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.assets_price_hidden_view);
                        if (textView5 != null) {
                            i = R.id.assets_sub_name;
                            TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.assets_sub_name);
                            if (textView6 != null) {
                                i = R.id.bg_logo;
                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.bg_logo);
                                if (relativeLayout != null) {
                                    i = R.id.iv_defitype;
                                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_defitype);
                                    if (imageView != null) {
                                        i = R.id.iv_line;
                                        View findChildViewById = ViewBindings.findChildViewById(view, R.id.iv_line);
                                        if (findChildViewById != null) {
                                            i = R.id.iv_logo;
                                            TokenLogoDraweeView tokenLogoDraweeView = (TokenLogoDraweeView) ViewBindings.findChildViewById(view, R.id.iv_logo);
                                            if (tokenLogoDraweeView != null) {
                                                i = R.id.iv_national;
                                                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_national);
                                                if (imageView2 != null) {
                                                    i = R.id.iv_official_image;
                                                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_official_image);
                                                    if (imageView3 != null) {
                                                        i = R.id.iv_renzheng;
                                                        ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_renzheng);
                                                        if (imageView4 != null) {
                                                            i = R.id.iv_scam;
                                                            ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_scam);
                                                            if (imageView5 != null) {
                                                                i = R.id.li_content;
                                                                ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.li_content);
                                                                if (constraintLayout != null) {
                                                                    i = R.id.ll_token_name;
                                                                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_token_name);
                                                                    if (linearLayout != null) {
                                                                        i = R.id.rl_inner;
                                                                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_inner);
                                                                        if (relativeLayout2 != null) {
                                                                            RelativeLayout relativeLayout3 = (RelativeLayout) view;
                                                                            i = R.id.rl_stake_available;
                                                                            RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_stake_available);
                                                                            if (relativeLayout4 != null) {
                                                                                i = R.id.rl_trx;
                                                                                ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.rl_trx);
                                                                                if (constraintLayout2 != null) {
                                                                                    i = R.id.tv_available_trx;
                                                                                    TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_available_trx);
                                                                                    if (textView7 != null) {
                                                                                        i = R.id.tv_price_amount;
                                                                                        TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_price_amount);
                                                                                        if (textView8 != null) {
                                                                                            i = R.id.tv_staked_trx;
                                                                                            TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_staked_trx);
                                                                                            if (textView9 != null) {
                                                                                                return new ItemAssetsAdapterBinding(relativeLayout3, textView, textView2, textView3, textView4, textView5, textView6, relativeLayout, imageView, findChildViewById, tokenLogoDraweeView, imageView2, imageView3, imageView4, imageView5, constraintLayout, linearLayout, relativeLayout2, relativeLayout3, relativeLayout4, constraintLayout2, textView7, textView8, textView9);
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
