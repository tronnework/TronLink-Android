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
public final class ItemAssetsListBinding implements ViewBinding {
    public final TextView assetsCount;
    public final TextView assetsId;
    public final TextView assetsName;
    public final TextView assetsSubname;
    public final TextView assetsTag;
    public final TextView assetsValue;
    public final ImageView ivAssetsOfficial;
    public final ImageView ivDefitype;
    public final ImageView ivDivider;
    public final TokenLogoDraweeView ivLogo;
    public final ImageView ivNational;
    public final ImageView ivOfficialImage;
    public final ImageView ivScam;
    public final ImageView ivSwitch;
    public final LinearLayout llAssetTag;
    public final LinearLayout llAssetcount;
    public final ConstraintLayout llAssetname;
    public final LinearLayout llContent;
    public final RelativeLayout llLogo;
    public final RelativeLayout rlCheat;
    public final RelativeLayout rlInner;
    public final RelativeLayout rlMain;
    private final RelativeLayout rootView;
    public final TextView tvCheat;
    public final View vDisable;
    public final ImageView vNewAssetsTip;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemAssetsListBinding(RelativeLayout relativeLayout, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, ImageView imageView, ImageView imageView2, ImageView imageView3, TokenLogoDraweeView tokenLogoDraweeView, ImageView imageView4, ImageView imageView5, ImageView imageView6, ImageView imageView7, LinearLayout linearLayout, LinearLayout linearLayout2, ConstraintLayout constraintLayout, LinearLayout linearLayout3, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, RelativeLayout relativeLayout5, TextView textView7, View view, ImageView imageView8) {
        this.rootView = relativeLayout;
        this.assetsCount = textView;
        this.assetsId = textView2;
        this.assetsName = textView3;
        this.assetsSubname = textView4;
        this.assetsTag = textView5;
        this.assetsValue = textView6;
        this.ivAssetsOfficial = imageView;
        this.ivDefitype = imageView2;
        this.ivDivider = imageView3;
        this.ivLogo = tokenLogoDraweeView;
        this.ivNational = imageView4;
        this.ivOfficialImage = imageView5;
        this.ivScam = imageView6;
        this.ivSwitch = imageView7;
        this.llAssetTag = linearLayout;
        this.llAssetcount = linearLayout2;
        this.llAssetname = constraintLayout;
        this.llContent = linearLayout3;
        this.llLogo = relativeLayout2;
        this.rlCheat = relativeLayout3;
        this.rlInner = relativeLayout4;
        this.rlMain = relativeLayout5;
        this.tvCheat = textView7;
        this.vDisable = view;
        this.vNewAssetsTip = imageView8;
    }

    public static ItemAssetsListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemAssetsListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_assets_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemAssetsListBinding bind(View view) {
        int i = R.id.assets_count;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.assets_count);
        if (textView != null) {
            i = R.id.assets_id;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.assets_id);
            if (textView2 != null) {
                i = R.id.assets_name;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.assets_name);
                if (textView3 != null) {
                    i = R.id.assets_subname;
                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.assets_subname);
                    if (textView4 != null) {
                        i = R.id.assets_tag;
                        TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.assets_tag);
                        if (textView5 != null) {
                            i = R.id.assets_value;
                            TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.assets_value);
                            if (textView6 != null) {
                                i = R.id.iv_assets_official;
                                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_assets_official);
                                if (imageView != null) {
                                    i = R.id.iv_defitype;
                                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_defitype);
                                    if (imageView2 != null) {
                                        i = R.id.iv_divider;
                                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_divider);
                                        if (imageView3 != null) {
                                            i = R.id.iv_logo;
                                            TokenLogoDraweeView tokenLogoDraweeView = (TokenLogoDraweeView) ViewBindings.findChildViewById(view, R.id.iv_logo);
                                            if (tokenLogoDraweeView != null) {
                                                i = R.id.iv_national;
                                                ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_national);
                                                if (imageView4 != null) {
                                                    i = R.id.iv_official_image;
                                                    ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_official_image);
                                                    if (imageView5 != null) {
                                                        i = R.id.iv_scam;
                                                        ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_scam);
                                                        if (imageView6 != null) {
                                                            i = R.id.iv_switch;
                                                            ImageView imageView7 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_switch);
                                                            if (imageView7 != null) {
                                                                i = R.id.ll_asset_tag;
                                                                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_asset_tag);
                                                                if (linearLayout != null) {
                                                                    i = R.id.ll_assetcount;
                                                                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_assetcount);
                                                                    if (linearLayout2 != null) {
                                                                        i = R.id.ll_assetname;
                                                                        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.ll_assetname);
                                                                        if (constraintLayout != null) {
                                                                            i = R.id.ll_content;
                                                                            LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_content);
                                                                            if (linearLayout3 != null) {
                                                                                i = R.id.ll_logo;
                                                                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_logo);
                                                                                if (relativeLayout != null) {
                                                                                    i = R.id.rl_cheat;
                                                                                    RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_cheat);
                                                                                    if (relativeLayout2 != null) {
                                                                                        i = R.id.rl_inner;
                                                                                        RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_inner);
                                                                                        if (relativeLayout3 != null) {
                                                                                            RelativeLayout relativeLayout4 = (RelativeLayout) view;
                                                                                            i = R.id.tv_cheat;
                                                                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_cheat);
                                                                                            if (textView7 != null) {
                                                                                                i = R.id.v_disable;
                                                                                                View findChildViewById = ViewBindings.findChildViewById(view, R.id.v_disable);
                                                                                                if (findChildViewById != null) {
                                                                                                    i = R.id.v_new_assets_tip;
                                                                                                    ImageView imageView8 = (ImageView) ViewBindings.findChildViewById(view, R.id.v_new_assets_tip);
                                                                                                    if (imageView8 != null) {
                                                                                                        return new ItemAssetsListBinding(relativeLayout4, textView, textView2, textView3, textView4, textView5, textView6, imageView, imageView2, imageView3, tokenLogoDraweeView, imageView4, imageView5, imageView6, imageView7, linearLayout, linearLayout2, constraintLayout, linearLayout3, relativeLayout, relativeLayout2, relativeLayout3, relativeLayout4, textView7, findChildViewById, imageView8);
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
