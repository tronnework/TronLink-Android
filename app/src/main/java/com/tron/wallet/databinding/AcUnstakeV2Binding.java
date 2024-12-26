package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.CurrencyEditText;
import com.tron.wallet.common.components.StakeHeaderView;
import com.tron.wallet.common.components.StakePercentView;
import com.tronlinkpro.wallet.R;
public final class AcUnstakeV2Binding implements ViewBinding {
    public final LinearLayout available;
    public final AcUnstakeV2BottomBinding bottom;
    public final Button btNext;
    public final LinearLayout button;
    public final CurrencyEditText etInput;
    public final AcUnstakeV2HeaderBinding header;
    public final LinearLayout liErrorTips;
    public final NestedScrollView nestedScrollView;
    public final StakePercentView percentView;
    public final RelativeLayout rlUnStake;
    private final LinearLayout rootView;
    public final RelativeLayout tab;
    public final TextView toManager;
    public final TextView tvAvailableUnStake;
    public final TextView tvBottomHint;
    public final TextView tvControlTips;
    public final TextView tvDesc;
    public final TextView tvErrorTips;
    public final TextView tvUnAvailable;
    public final StakeHeaderView unStakeHeader;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private AcUnstakeV2Binding(LinearLayout linearLayout, LinearLayout linearLayout2, AcUnstakeV2BottomBinding acUnstakeV2BottomBinding, Button button, LinearLayout linearLayout3, CurrencyEditText currencyEditText, AcUnstakeV2HeaderBinding acUnstakeV2HeaderBinding, LinearLayout linearLayout4, NestedScrollView nestedScrollView, StakePercentView stakePercentView, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, StakeHeaderView stakeHeaderView) {
        this.rootView = linearLayout;
        this.available = linearLayout2;
        this.bottom = acUnstakeV2BottomBinding;
        this.btNext = button;
        this.button = linearLayout3;
        this.etInput = currencyEditText;
        this.header = acUnstakeV2HeaderBinding;
        this.liErrorTips = linearLayout4;
        this.nestedScrollView = nestedScrollView;
        this.percentView = stakePercentView;
        this.rlUnStake = relativeLayout;
        this.tab = relativeLayout2;
        this.toManager = textView;
        this.tvAvailableUnStake = textView2;
        this.tvBottomHint = textView3;
        this.tvControlTips = textView4;
        this.tvDesc = textView5;
        this.tvErrorTips = textView6;
        this.tvUnAvailable = textView7;
        this.unStakeHeader = stakeHeaderView;
    }

    public static AcUnstakeV2Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcUnstakeV2Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_unstake_v2, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcUnstakeV2Binding bind(View view) {
        int i = R.id.available;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.available);
        if (linearLayout != null) {
            i = R.id.bottom;
            View findChildViewById = ViewBindings.findChildViewById(view, R.id.bottom);
            if (findChildViewById != null) {
                AcUnstakeV2BottomBinding bind = AcUnstakeV2BottomBinding.bind(findChildViewById);
                i = R.id.bt_next;
                Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_next);
                if (button != null) {
                    i = R.id.button;
                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.button);
                    if (linearLayout2 != null) {
                        i = R.id.et_input;
                        CurrencyEditText currencyEditText = (CurrencyEditText) ViewBindings.findChildViewById(view, R.id.et_input);
                        if (currencyEditText != null) {
                            i = R.id.header;
                            View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.header);
                            if (findChildViewById2 != null) {
                                AcUnstakeV2HeaderBinding bind2 = AcUnstakeV2HeaderBinding.bind(findChildViewById2);
                                i = R.id.li_error_tips;
                                LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_error_tips);
                                if (linearLayout3 != null) {
                                    i = R.id.nested_scroll_view;
                                    NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(view, R.id.nested_scroll_view);
                                    if (nestedScrollView != null) {
                                        i = R.id.percent_view;
                                        StakePercentView stakePercentView = (StakePercentView) ViewBindings.findChildViewById(view, R.id.percent_view);
                                        if (stakePercentView != null) {
                                            i = R.id.rl_un_stake;
                                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_un_stake);
                                            if (relativeLayout != null) {
                                                i = R.id.tab;
                                                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.tab);
                                                if (relativeLayout2 != null) {
                                                    i = R.id.to_manager;
                                                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.to_manager);
                                                    if (textView != null) {
                                                        i = R.id.tv_available_un_stake;
                                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_available_un_stake);
                                                        if (textView2 != null) {
                                                            i = R.id.tv_bottom_hint;
                                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_bottom_hint);
                                                            if (textView3 != null) {
                                                                i = R.id.tv_control_tips;
                                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_control_tips);
                                                                if (textView4 != null) {
                                                                    i = R.id.tv_desc;
                                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_desc);
                                                                    if (textView5 != null) {
                                                                        i = R.id.tv_error_tips;
                                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_error_tips);
                                                                        if (textView6 != null) {
                                                                            i = R.id.tv_un_available;
                                                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_un_available);
                                                                            if (textView7 != null) {
                                                                                i = R.id.un_stake_header;
                                                                                StakeHeaderView stakeHeaderView = (StakeHeaderView) ViewBindings.findChildViewById(view, R.id.un_stake_header);
                                                                                if (stakeHeaderView != null) {
                                                                                    return new AcUnstakeV2Binding((LinearLayout) view, linearLayout, bind, button, linearLayout2, currencyEditText, bind2, linearLayout3, nestedScrollView, stakePercentView, relativeLayout, relativeLayout2, textView, textView2, textView3, textView4, textView5, textView6, textView7, stakeHeaderView);
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
