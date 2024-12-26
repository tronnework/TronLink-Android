package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class FgResourcesEnergyBinding implements ViewBinding {
    public final TextView btDelegate;
    public final TextView btEnergyView;
    public final TextView btRecycle;
    public final ImageView ivEnergyFromOther;
    public final ImageView ivEnergyFromSelf;
    public final ImageView ivStake2Tip;
    public final LinearLayout llBandwidthFree;
    public final LinearLayout llStake1Other;
    public final LinearLayout llStake1Self;
    public final LinearLayout llStake2Delegate;
    public final LinearLayout llStake2Other;
    public final LinearLayout llStake2Self;
    public final LinearLayout llStakeFromOtherDetail;
    public final LinearLayout llStakeFromSelfDetail;
    public final LinearLayout root;
    private final LinearLayout rootView;
    public final TextView tvBandwidthFree;
    public final TextView tvDelegateAvailable;
    public final TextView tvDelegated;
    public final TextView tvEnergyFromOther;
    public final TextView tvEnergyFromSelf;
    public final TextView tvEnergyManagement;
    public final TextView tvResourceDetail;
    public final TextView tvStake1OtherAmount;
    public final TextView tvStake1OtherView;
    public final TextView tvStake1Self;
    public final TextView tvStake2DelegateAmount;
    public final TextView tvStake2DelegateView;
    public final TextView tvStake2OtherAmount;
    public final TextView tvStake2OtherView;
    public final TextView tvStake2Self;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private FgResourcesEnergyBinding(LinearLayout linearLayout, TextView textView, TextView textView2, TextView textView3, ImageView imageView, ImageView imageView2, ImageView imageView3, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, LinearLayout linearLayout5, LinearLayout linearLayout6, LinearLayout linearLayout7, LinearLayout linearLayout8, LinearLayout linearLayout9, LinearLayout linearLayout10, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14, TextView textView15, TextView textView16, TextView textView17, TextView textView18) {
        this.rootView = linearLayout;
        this.btDelegate = textView;
        this.btEnergyView = textView2;
        this.btRecycle = textView3;
        this.ivEnergyFromOther = imageView;
        this.ivEnergyFromSelf = imageView2;
        this.ivStake2Tip = imageView3;
        this.llBandwidthFree = linearLayout2;
        this.llStake1Other = linearLayout3;
        this.llStake1Self = linearLayout4;
        this.llStake2Delegate = linearLayout5;
        this.llStake2Other = linearLayout6;
        this.llStake2Self = linearLayout7;
        this.llStakeFromOtherDetail = linearLayout8;
        this.llStakeFromSelfDetail = linearLayout9;
        this.root = linearLayout10;
        this.tvBandwidthFree = textView4;
        this.tvDelegateAvailable = textView5;
        this.tvDelegated = textView6;
        this.tvEnergyFromOther = textView7;
        this.tvEnergyFromSelf = textView8;
        this.tvEnergyManagement = textView9;
        this.tvResourceDetail = textView10;
        this.tvStake1OtherAmount = textView11;
        this.tvStake1OtherView = textView12;
        this.tvStake1Self = textView13;
        this.tvStake2DelegateAmount = textView14;
        this.tvStake2DelegateView = textView15;
        this.tvStake2OtherAmount = textView16;
        this.tvStake2OtherView = textView17;
        this.tvStake2Self = textView18;
    }

    public static FgResourcesEnergyBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FgResourcesEnergyBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fg_resources_energy, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FgResourcesEnergyBinding bind(View view) {
        int i = R.id.bt_delegate;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.bt_delegate);
        if (textView != null) {
            i = R.id.bt_energy_view;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.bt_energy_view);
            if (textView2 != null) {
                i = R.id.bt_recycle;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.bt_recycle);
                if (textView3 != null) {
                    i = R.id.iv_energy_from_other;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_energy_from_other);
                    if (imageView != null) {
                        i = R.id.iv_energy_from_self;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_energy_from_self);
                        if (imageView2 != null) {
                            i = R.id.iv_stake_2_tip;
                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_stake_2_tip);
                            if (imageView3 != null) {
                                i = R.id.ll_bandwidth_free;
                                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_bandwidth_free);
                                if (linearLayout != null) {
                                    i = R.id.ll_stake_1_other;
                                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_stake_1_other);
                                    if (linearLayout2 != null) {
                                        i = R.id.ll_stake_1_self;
                                        LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_stake_1_self);
                                        if (linearLayout3 != null) {
                                            i = R.id.ll_stake_2_delegate;
                                            LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_stake_2_delegate);
                                            if (linearLayout4 != null) {
                                                i = R.id.ll_stake_2_other;
                                                LinearLayout linearLayout5 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_stake_2_other);
                                                if (linearLayout5 != null) {
                                                    i = R.id.ll_stake_2_self;
                                                    LinearLayout linearLayout6 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_stake_2_self);
                                                    if (linearLayout6 != null) {
                                                        i = R.id.ll_stake_from_other_detail;
                                                        LinearLayout linearLayout7 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_stake_from_other_detail);
                                                        if (linearLayout7 != null) {
                                                            i = R.id.ll_stake_from_self_detail;
                                                            LinearLayout linearLayout8 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_stake_from_self_detail);
                                                            if (linearLayout8 != null) {
                                                                LinearLayout linearLayout9 = (LinearLayout) view;
                                                                i = R.id.tv_bandwidth_free;
                                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_bandwidth_free);
                                                                if (textView4 != null) {
                                                                    i = R.id.tv_delegate_available;
                                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_delegate_available);
                                                                    if (textView5 != null) {
                                                                        i = R.id.tv_delegated;
                                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_delegated);
                                                                        if (textView6 != null) {
                                                                            i = R.id.tv_energy_from_other;
                                                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_energy_from_other);
                                                                            if (textView7 != null) {
                                                                                i = R.id.tv_energy_from_self;
                                                                                TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_energy_from_self);
                                                                                if (textView8 != null) {
                                                                                    i = R.id.tv_energy_management;
                                                                                    TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_energy_management);
                                                                                    if (textView9 != null) {
                                                                                        i = R.id.tv_resource_detail;
                                                                                        TextView textView10 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_resource_detail);
                                                                                        if (textView10 != null) {
                                                                                            i = R.id.tv_stake_1_other_amount;
                                                                                            TextView textView11 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_stake_1_other_amount);
                                                                                            if (textView11 != null) {
                                                                                                i = R.id.tv_stake_1_other_view;
                                                                                                TextView textView12 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_stake_1_other_view);
                                                                                                if (textView12 != null) {
                                                                                                    i = R.id.tv_stake_1_self;
                                                                                                    TextView textView13 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_stake_1_self);
                                                                                                    if (textView13 != null) {
                                                                                                        i = R.id.tv_stake_2_delegate_amount;
                                                                                                        TextView textView14 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_stake_2_delegate_amount);
                                                                                                        if (textView14 != null) {
                                                                                                            i = R.id.tv_stake_2_delegate_view;
                                                                                                            TextView textView15 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_stake_2_delegate_view);
                                                                                                            if (textView15 != null) {
                                                                                                                i = R.id.tv_stake_2_other_amount;
                                                                                                                TextView textView16 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_stake_2_other_amount);
                                                                                                                if (textView16 != null) {
                                                                                                                    i = R.id.tv_stake_2_other_view;
                                                                                                                    TextView textView17 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_stake_2_other_view);
                                                                                                                    if (textView17 != null) {
                                                                                                                        i = R.id.tv_stake_2_self;
                                                                                                                        TextView textView18 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_stake_2_self);
                                                                                                                        if (textView18 != null) {
                                                                                                                            return new FgResourcesEnergyBinding(linearLayout9, textView, textView2, textView3, imageView, imageView2, imageView3, linearLayout, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6, linearLayout7, linearLayout8, linearLayout9, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14, textView15, textView16, textView17, textView18);
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
