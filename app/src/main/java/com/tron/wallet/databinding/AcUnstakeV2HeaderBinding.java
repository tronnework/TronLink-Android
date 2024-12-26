package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class AcUnstakeV2HeaderBinding implements ViewBinding {
    public final RelativeLayout rlTabBandwidth;
    public final RelativeLayout rlTabEnergy;
    private final LinearLayout rootView;
    public final TextView tvTabBandwidth;
    public final TextView tvTabEnergy;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private AcUnstakeV2HeaderBinding(LinearLayout linearLayout, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, TextView textView, TextView textView2) {
        this.rootView = linearLayout;
        this.rlTabBandwidth = relativeLayout;
        this.rlTabEnergy = relativeLayout2;
        this.tvTabBandwidth = textView;
        this.tvTabEnergy = textView2;
    }

    public static AcUnstakeV2HeaderBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcUnstakeV2HeaderBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_unstake_v2_header, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcUnstakeV2HeaderBinding bind(View view) {
        int i = R.id.rl_tab_bandwidth;
        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_tab_bandwidth);
        if (relativeLayout != null) {
            i = R.id.rl_tab_energy;
            RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_tab_energy);
            if (relativeLayout2 != null) {
                i = R.id.tv_tab_bandwidth;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_tab_bandwidth);
                if (textView != null) {
                    i = R.id.tv_tab_energy;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_tab_energy);
                    if (textView2 != null) {
                        return new AcUnstakeV2HeaderBinding((LinearLayout) view, relativeLayout, relativeLayout2, textView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
