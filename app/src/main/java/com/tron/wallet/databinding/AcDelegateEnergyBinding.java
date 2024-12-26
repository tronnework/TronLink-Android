package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.DelegateResourceLockedView;
import com.tron.wallet.common.components.StakeHeaderView;
import com.tronlinkpro.wallet.R;
public final class AcDelegateEnergyBinding implements ViewBinding {
    public final TextView btnNext;
    public final DelegateResourceLockedView resourceLockSwitchLayout;
    private final RelativeLayout rootView;
    public final StakeHeaderView stakeHeader;
    public final TabWithPercentSettingBinding tab;
    public final TextView tvDesc;
    public final TextView tvMultiWarning;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcDelegateEnergyBinding(RelativeLayout relativeLayout, TextView textView, DelegateResourceLockedView delegateResourceLockedView, StakeHeaderView stakeHeaderView, TabWithPercentSettingBinding tabWithPercentSettingBinding, TextView textView2, TextView textView3) {
        this.rootView = relativeLayout;
        this.btnNext = textView;
        this.resourceLockSwitchLayout = delegateResourceLockedView;
        this.stakeHeader = stakeHeaderView;
        this.tab = tabWithPercentSettingBinding;
        this.tvDesc = textView2;
        this.tvMultiWarning = textView3;
    }

    public static AcDelegateEnergyBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcDelegateEnergyBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_delegate_energy, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcDelegateEnergyBinding bind(View view) {
        int i = R.id.btn_next;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.btn_next);
        if (textView != null) {
            i = R.id.resource_lock_switch_layout;
            DelegateResourceLockedView delegateResourceLockedView = (DelegateResourceLockedView) ViewBindings.findChildViewById(view, R.id.resource_lock_switch_layout);
            if (delegateResourceLockedView != null) {
                i = R.id.stake_header;
                StakeHeaderView stakeHeaderView = (StakeHeaderView) ViewBindings.findChildViewById(view, R.id.stake_header);
                if (stakeHeaderView != null) {
                    i = R.id.tab;
                    View findChildViewById = ViewBindings.findChildViewById(view, R.id.tab);
                    if (findChildViewById != null) {
                        TabWithPercentSettingBinding bind = TabWithPercentSettingBinding.bind(findChildViewById);
                        i = R.id.tv_desc;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_desc);
                        if (textView2 != null) {
                            i = R.id.tv_multi_warning;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_multi_warning);
                            if (textView3 != null) {
                                return new AcDelegateEnergyBinding((RelativeLayout) view, textView, delegateResourceLockedView, stakeHeaderView, bind, textView2, textView3);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
