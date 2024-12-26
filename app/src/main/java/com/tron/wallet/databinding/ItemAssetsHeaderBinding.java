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
import com.tron.wallet.common.components.AssetsBannerPageView;
import com.tron.wallet.common.components.MultiSignPopupTextView;
import com.tron.wallet.common.components.WhiteEnergyProgressView;
import com.tronlinkpro.wallet.R;
public final class ItemAssetsHeaderBinding implements ViewBinding {
    public final ImageView assetStatus;
    public final AssetsBannerPageView assetsBannerView;
    public final RelativeLayout backupTipLayout;
    public final ImageView ivBackupClose;
    public final ImageView ivCopy;
    public final ImageView ivLoading;
    public final View ivSeparator;
    public final ImageView ivWatchClose;
    public final ConstraintLayout llAddress;
    public final LinearLayout llBrandwidth;
    public final RelativeLayout llEnergy;
    public final RelativeLayout llWatchTip;
    public final WhiteEnergyProgressView progressBrandwidth;
    public final WhiteEnergyProgressView progressEnergy;
    public final RelativeLayout rlAddress;
    public final RelativeLayout rlBandwidth;
    public final RelativeLayout rlInner;
    public final RelativeLayout rlWatch;
    private final RelativeLayout rootView;
    public final RelativeLayout topCard;
    public final TextView tvAddress;
    public final TextView tvBackUpTip;
    public final TextView tvBandwidth;
    public final TextView tvBandwidthProgress;
    public final TextView tvEnergy;
    public final TextView tvEnergyProgress;
    public final MultiSignPopupTextView tvFlagMultiSign;
    public final TextView tvMoneyValue;
    public final TextView tvTrx;
    public final TextView tvTrxValue;
    public final TextView tvWatchReminderTip;
    public final TextView tvWatchTag;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemAssetsHeaderBinding(RelativeLayout relativeLayout, ImageView imageView, AssetsBannerPageView assetsBannerPageView, RelativeLayout relativeLayout2, ImageView imageView2, ImageView imageView3, ImageView imageView4, View view, ImageView imageView5, ConstraintLayout constraintLayout, LinearLayout linearLayout, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, WhiteEnergyProgressView whiteEnergyProgressView, WhiteEnergyProgressView whiteEnergyProgressView2, RelativeLayout relativeLayout5, RelativeLayout relativeLayout6, RelativeLayout relativeLayout7, RelativeLayout relativeLayout8, RelativeLayout relativeLayout9, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, MultiSignPopupTextView multiSignPopupTextView, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11) {
        this.rootView = relativeLayout;
        this.assetStatus = imageView;
        this.assetsBannerView = assetsBannerPageView;
        this.backupTipLayout = relativeLayout2;
        this.ivBackupClose = imageView2;
        this.ivCopy = imageView3;
        this.ivLoading = imageView4;
        this.ivSeparator = view;
        this.ivWatchClose = imageView5;
        this.llAddress = constraintLayout;
        this.llBrandwidth = linearLayout;
        this.llEnergy = relativeLayout3;
        this.llWatchTip = relativeLayout4;
        this.progressBrandwidth = whiteEnergyProgressView;
        this.progressEnergy = whiteEnergyProgressView2;
        this.rlAddress = relativeLayout5;
        this.rlBandwidth = relativeLayout6;
        this.rlInner = relativeLayout7;
        this.rlWatch = relativeLayout8;
        this.topCard = relativeLayout9;
        this.tvAddress = textView;
        this.tvBackUpTip = textView2;
        this.tvBandwidth = textView3;
        this.tvBandwidthProgress = textView4;
        this.tvEnergy = textView5;
        this.tvEnergyProgress = textView6;
        this.tvFlagMultiSign = multiSignPopupTextView;
        this.tvMoneyValue = textView7;
        this.tvTrx = textView8;
        this.tvTrxValue = textView9;
        this.tvWatchReminderTip = textView10;
        this.tvWatchTag = textView11;
    }

    public static ItemAssetsHeaderBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemAssetsHeaderBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_assets_header, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemAssetsHeaderBinding bind(View view) {
        int i = R.id.asset_status;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.asset_status);
        if (imageView != null) {
            i = R.id.assets_banner_view;
            AssetsBannerPageView assetsBannerPageView = (AssetsBannerPageView) ViewBindings.findChildViewById(view, R.id.assets_banner_view);
            if (assetsBannerPageView != null) {
                i = R.id.backup_tip_layout;
                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.backup_tip_layout);
                if (relativeLayout != null) {
                    i = R.id.iv_backup_close;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_backup_close);
                    if (imageView2 != null) {
                        i = R.id.iv_copy;
                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_copy);
                        if (imageView3 != null) {
                            i = R.id.iv_loading;
                            ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_loading);
                            if (imageView4 != null) {
                                i = R.id.iv_separator;
                                View findChildViewById = ViewBindings.findChildViewById(view, R.id.iv_separator);
                                if (findChildViewById != null) {
                                    i = R.id.iv_watch_close;
                                    ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_watch_close);
                                    if (imageView5 != null) {
                                        i = R.id.ll_address;
                                        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.ll_address);
                                        if (constraintLayout != null) {
                                            i = R.id.ll_brandwidth;
                                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_brandwidth);
                                            if (linearLayout != null) {
                                                i = R.id.ll_energy;
                                                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_energy);
                                                if (relativeLayout2 != null) {
                                                    i = R.id.ll_watch_tip;
                                                    RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_watch_tip);
                                                    if (relativeLayout3 != null) {
                                                        i = R.id.progress_brandwidth;
                                                        WhiteEnergyProgressView whiteEnergyProgressView = (WhiteEnergyProgressView) ViewBindings.findChildViewById(view, R.id.progress_brandwidth);
                                                        if (whiteEnergyProgressView != null) {
                                                            i = R.id.progress_energy;
                                                            WhiteEnergyProgressView whiteEnergyProgressView2 = (WhiteEnergyProgressView) ViewBindings.findChildViewById(view, R.id.progress_energy);
                                                            if (whiteEnergyProgressView2 != null) {
                                                                i = R.id.rl_address;
                                                                RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_address);
                                                                if (relativeLayout4 != null) {
                                                                    i = R.id.rl_bandwidth;
                                                                    RelativeLayout relativeLayout5 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_bandwidth);
                                                                    if (relativeLayout5 != null) {
                                                                        RelativeLayout relativeLayout6 = (RelativeLayout) view;
                                                                        i = R.id.rl_watch;
                                                                        RelativeLayout relativeLayout7 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_watch);
                                                                        if (relativeLayout7 != null) {
                                                                            i = R.id.top_card;
                                                                            RelativeLayout relativeLayout8 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.top_card);
                                                                            if (relativeLayout8 != null) {
                                                                                i = R.id.tv_address;
                                                                                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address);
                                                                                if (textView != null) {
                                                                                    i = R.id.tv_back_up_tip;
                                                                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_back_up_tip);
                                                                                    if (textView2 != null) {
                                                                                        i = R.id.tv_bandwidth;
                                                                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_bandwidth);
                                                                                        if (textView3 != null) {
                                                                                            i = R.id.tv_bandwidth_progress;
                                                                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_bandwidth_progress);
                                                                                            if (textView4 != null) {
                                                                                                i = R.id.tv_energy;
                                                                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_energy);
                                                                                                if (textView5 != null) {
                                                                                                    i = R.id.tv_energy_progress;
                                                                                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_energy_progress);
                                                                                                    if (textView6 != null) {
                                                                                                        i = R.id.tv_flag_multi_sign;
                                                                                                        MultiSignPopupTextView multiSignPopupTextView = (MultiSignPopupTextView) ViewBindings.findChildViewById(view, R.id.tv_flag_multi_sign);
                                                                                                        if (multiSignPopupTextView != null) {
                                                                                                            i = R.id.tv_money_value;
                                                                                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_money_value);
                                                                                                            if (textView7 != null) {
                                                                                                                i = R.id.tv_trx;
                                                                                                                TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_trx);
                                                                                                                if (textView8 != null) {
                                                                                                                    i = R.id.tv_trx_value;
                                                                                                                    TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_trx_value);
                                                                                                                    if (textView9 != null) {
                                                                                                                        i = R.id.tv_watch_reminder_tip;
                                                                                                                        TextView textView10 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_watch_reminder_tip);
                                                                                                                        if (textView10 != null) {
                                                                                                                            i = R.id.tv_watch_tag;
                                                                                                                            TextView textView11 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_watch_tag);
                                                                                                                            if (textView11 != null) {
                                                                                                                                return new ItemAssetsHeaderBinding(relativeLayout6, imageView, assetsBannerPageView, relativeLayout, imageView2, imageView3, imageView4, findChildViewById, imageView5, constraintLayout, linearLayout, relativeLayout2, relativeLayout3, whiteEnergyProgressView, whiteEnergyProgressView2, relativeLayout4, relativeLayout5, relativeLayout6, relativeLayout7, relativeLayout8, textView, textView2, textView3, textView4, textView5, textView6, multiSignPopupTextView, textView7, textView8, textView9, textView10, textView11);
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
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
