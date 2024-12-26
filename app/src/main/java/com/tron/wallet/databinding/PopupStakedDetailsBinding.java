package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.WhiteEnergyProgressView;
import com.tronlinkpro.wallet.R;
public final class PopupStakedDetailsBinding implements ViewBinding {
    public final Button btnConfirm;
    public final LinearLayout llAction;
    public final LinearLayout llStakedForBandwidth;
    public final LinearLayout llStakedForEnergy;
    public final WhiteEnergyProgressView progressBandwidth;
    public final WhiteEnergyProgressView progressEnergy;
    private final LinearLayout rootView;
    public final TextView title;
    public final TextView tvBandwidth;
    public final TextView tvBandwidthOthers;
    public final TextView tvBandwidthSelf;
    public final TextView tvEnergy;
    public final TextView tvEnergyOthers;
    public final TextView tvEnergySelf;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private PopupStakedDetailsBinding(LinearLayout linearLayout, Button button, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, WhiteEnergyProgressView whiteEnergyProgressView, WhiteEnergyProgressView whiteEnergyProgressView2, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7) {
        this.rootView = linearLayout;
        this.btnConfirm = button;
        this.llAction = linearLayout2;
        this.llStakedForBandwidth = linearLayout3;
        this.llStakedForEnergy = linearLayout4;
        this.progressBandwidth = whiteEnergyProgressView;
        this.progressEnergy = whiteEnergyProgressView2;
        this.title = textView;
        this.tvBandwidth = textView2;
        this.tvBandwidthOthers = textView3;
        this.tvBandwidthSelf = textView4;
        this.tvEnergy = textView5;
        this.tvEnergyOthers = textView6;
        this.tvEnergySelf = textView7;
    }

    public static PopupStakedDetailsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopupStakedDetailsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.popup_staked_details, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopupStakedDetailsBinding bind(View view) {
        int i = R.id.btn_confirm;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_confirm);
        if (button != null) {
            i = R.id.ll_action;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_action);
            if (linearLayout != null) {
                i = R.id.ll_staked_for_bandwidth;
                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_staked_for_bandwidth);
                if (linearLayout2 != null) {
                    i = R.id.ll_staked_for_energy;
                    LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_staked_for_energy);
                    if (linearLayout3 != null) {
                        i = R.id.progress_bandwidth;
                        WhiteEnergyProgressView whiteEnergyProgressView = (WhiteEnergyProgressView) ViewBindings.findChildViewById(view, R.id.progress_bandwidth);
                        if (whiteEnergyProgressView != null) {
                            i = R.id.progress_energy;
                            WhiteEnergyProgressView whiteEnergyProgressView2 = (WhiteEnergyProgressView) ViewBindings.findChildViewById(view, R.id.progress_energy);
                            if (whiteEnergyProgressView2 != null) {
                                i = R.id.title;
                                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.title);
                                if (textView != null) {
                                    i = R.id.tv_bandwidth;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_bandwidth);
                                    if (textView2 != null) {
                                        i = R.id.tv_bandwidth_others;
                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_bandwidth_others);
                                        if (textView3 != null) {
                                            i = R.id.tv_bandwidth_self;
                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_bandwidth_self);
                                            if (textView4 != null) {
                                                i = R.id.tv_energy;
                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_energy);
                                                if (textView5 != null) {
                                                    i = R.id.tv_energy_others;
                                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_energy_others);
                                                    if (textView6 != null) {
                                                        i = R.id.tv_energy_self;
                                                        TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_energy_self);
                                                        if (textView7 != null) {
                                                            return new PopupStakedDetailsBinding((LinearLayout) view, button, linearLayout, linearLayout2, linearLayout3, whiteEnergyProgressView, whiteEnergyProgressView2, textView, textView2, textView3, textView4, textView5, textView6, textView7);
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
