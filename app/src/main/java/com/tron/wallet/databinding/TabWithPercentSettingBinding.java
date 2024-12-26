package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.CurrencyEditText;
import com.tron.wallet.common.components.StakePercentView;
import com.tronlinkpro.wallet.R;
public final class TabWithPercentSettingBinding implements ViewBinding {
    public final RelativeLayout available;
    public final TextView availableCount;
    public final TextView availableTitle;
    public final CurrencyEditText etInput;
    public final RelativeLayout inStake;
    public final TextView inStakeCount;
    public final TextView inStakeTitle;
    public final LinearLayout liErrorTips;
    public final StakePercentView percentView;
    public final TextView resourceTab;
    private final RelativeLayout rootView;
    public final RelativeLayout tab;
    public final TextView trxTab;
    public final TextView tvErrorTips;
    public final TextView tvResult;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private TabWithPercentSettingBinding(RelativeLayout relativeLayout, RelativeLayout relativeLayout2, TextView textView, TextView textView2, CurrencyEditText currencyEditText, RelativeLayout relativeLayout3, TextView textView3, TextView textView4, LinearLayout linearLayout, StakePercentView stakePercentView, TextView textView5, RelativeLayout relativeLayout4, TextView textView6, TextView textView7, TextView textView8) {
        this.rootView = relativeLayout;
        this.available = relativeLayout2;
        this.availableCount = textView;
        this.availableTitle = textView2;
        this.etInput = currencyEditText;
        this.inStake = relativeLayout3;
        this.inStakeCount = textView3;
        this.inStakeTitle = textView4;
        this.liErrorTips = linearLayout;
        this.percentView = stakePercentView;
        this.resourceTab = textView5;
        this.tab = relativeLayout4;
        this.trxTab = textView6;
        this.tvErrorTips = textView7;
        this.tvResult = textView8;
    }

    public static TabWithPercentSettingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static TabWithPercentSettingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.tab_with_percent_setting, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static TabWithPercentSettingBinding bind(View view) {
        int i = R.id.available;
        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.available);
        if (relativeLayout != null) {
            i = R.id.available_count;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.available_count);
            if (textView != null) {
                i = R.id.available_title;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.available_title);
                if (textView2 != null) {
                    i = R.id.et_input;
                    CurrencyEditText currencyEditText = (CurrencyEditText) ViewBindings.findChildViewById(view, R.id.et_input);
                    if (currencyEditText != null) {
                        i = R.id.in_stake;
                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.in_stake);
                        if (relativeLayout2 != null) {
                            i = R.id.in_stake_count;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.in_stake_count);
                            if (textView3 != null) {
                                i = R.id.in_stake_title;
                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.in_stake_title);
                                if (textView4 != null) {
                                    i = R.id.li_error_tips;
                                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_error_tips);
                                    if (linearLayout != null) {
                                        i = R.id.percent_view;
                                        StakePercentView stakePercentView = (StakePercentView) ViewBindings.findChildViewById(view, R.id.percent_view);
                                        if (stakePercentView != null) {
                                            i = R.id.resource_tab;
                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.resource_tab);
                                            if (textView5 != null) {
                                                RelativeLayout relativeLayout3 = (RelativeLayout) view;
                                                i = R.id.trx_tab;
                                                TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.trx_tab);
                                                if (textView6 != null) {
                                                    i = R.id.tv_error_tips;
                                                    TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_error_tips);
                                                    if (textView7 != null) {
                                                        i = R.id.tv_result;
                                                        TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_result);
                                                        if (textView8 != null) {
                                                            return new TabWithPercentSettingBinding(relativeLayout3, relativeLayout, textView, textView2, currencyEditText, relativeLayout2, textView3, textView4, linearLayout, stakePercentView, textView5, relativeLayout3, textView6, textView7, textView8);
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
