package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tron.wallet.common.components.CurrencyEditText;
import com.tronlinkpro.wallet.R;
public final class AcStakeTrxBinding implements ViewBinding {
    public final TextView amountPercent100;
    public final TextView amountPercent25;
    public final TextView amountPercent50;
    public final TextView amountPercent75;
    public final Button btnNextStep;
    public final View divider;
    public final EditText edInputRes;
    public final CurrencyEditText etAmount;
    public final ImageView ivArrow;
    public final ImageView ivStakeEdit;
    public final ImageView ivStakeUnstakeArrow;
    public final ImageView ivTips;
    public final SimpleDraweeView ivTrx;
    public final LinearLayout liAvailAmount;
    public final LinearLayout liBottomOption;
    public final LinearLayout liErrorTips;
    public final LinearLayout llBg;
    public final LinearLayout llGetBg;
    public final RelativeLayout rlGetShow;
    public final RelativeLayout rlStaked;
    public final RelativeLayout rlTips;
    public final LinearLayout root;
    private final LinearLayout rootView;
    public final NestedScrollView scrollView;
    public final LinearLayout tablayout;
    public final TextView tvAutoDeductTips;
    public final TextView tvAvailableAmount;
    public final TextView tvErrorTips;
    public final TextView tvResourceGetAmount;
    public final TextView tvResourcePerDay;
    public final TextView tvResourcePerTransaction;
    public final TextView tvStakeAmount;
    public final TextView tvStakeBandwidth;
    public final TextView tvStakeEnergy;
    public final TextView tvStakeTips;
    public final TextView tvStakeTitle;
    public final TextView tvStakeUnstake;
    public final TextView tvUnderControlTips;
    public final TextView tvVoteGetAmount;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private AcStakeTrxBinding(LinearLayout linearLayout, TextView textView, TextView textView2, TextView textView3, TextView textView4, Button button, View view, EditText editText, CurrencyEditText currencyEditText, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, SimpleDraweeView simpleDraweeView, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, LinearLayout linearLayout5, LinearLayout linearLayout6, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, LinearLayout linearLayout7, NestedScrollView nestedScrollView, LinearLayout linearLayout8, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14, TextView textView15, TextView textView16, TextView textView17, TextView textView18) {
        this.rootView = linearLayout;
        this.amountPercent100 = textView;
        this.amountPercent25 = textView2;
        this.amountPercent50 = textView3;
        this.amountPercent75 = textView4;
        this.btnNextStep = button;
        this.divider = view;
        this.edInputRes = editText;
        this.etAmount = currencyEditText;
        this.ivArrow = imageView;
        this.ivStakeEdit = imageView2;
        this.ivStakeUnstakeArrow = imageView3;
        this.ivTips = imageView4;
        this.ivTrx = simpleDraweeView;
        this.liAvailAmount = linearLayout2;
        this.liBottomOption = linearLayout3;
        this.liErrorTips = linearLayout4;
        this.llBg = linearLayout5;
        this.llGetBg = linearLayout6;
        this.rlGetShow = relativeLayout;
        this.rlStaked = relativeLayout2;
        this.rlTips = relativeLayout3;
        this.root = linearLayout7;
        this.scrollView = nestedScrollView;
        this.tablayout = linearLayout8;
        this.tvAutoDeductTips = textView5;
        this.tvAvailableAmount = textView6;
        this.tvErrorTips = textView7;
        this.tvResourceGetAmount = textView8;
        this.tvResourcePerDay = textView9;
        this.tvResourcePerTransaction = textView10;
        this.tvStakeAmount = textView11;
        this.tvStakeBandwidth = textView12;
        this.tvStakeEnergy = textView13;
        this.tvStakeTips = textView14;
        this.tvStakeTitle = textView15;
        this.tvStakeUnstake = textView16;
        this.tvUnderControlTips = textView17;
        this.tvVoteGetAmount = textView18;
    }

    public static AcStakeTrxBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcStakeTrxBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_stake_trx, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcStakeTrxBinding bind(View view) {
        int i = R.id.amount_percent_100;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.amount_percent_100);
        if (textView != null) {
            i = R.id.amount_percent_25;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.amount_percent_25);
            if (textView2 != null) {
                i = R.id.amount_percent_50;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.amount_percent_50);
                if (textView3 != null) {
                    i = R.id.amount_percent_75;
                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.amount_percent_75);
                    if (textView4 != null) {
                        i = R.id.btn_next_step;
                        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_next_step);
                        if (button != null) {
                            i = R.id.divider;
                            View findChildViewById = ViewBindings.findChildViewById(view, R.id.divider);
                            if (findChildViewById != null) {
                                i = R.id.ed_input_res;
                                EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.ed_input_res);
                                if (editText != null) {
                                    i = R.id.et_amount;
                                    CurrencyEditText currencyEditText = (CurrencyEditText) ViewBindings.findChildViewById(view, R.id.et_amount);
                                    if (currencyEditText != null) {
                                        i = R.id.iv_arrow;
                                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_arrow);
                                        if (imageView != null) {
                                            i = R.id.iv_stake_edit;
                                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_stake_edit);
                                            if (imageView2 != null) {
                                                i = R.id.iv_stake_unstake_arrow;
                                                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_stake_unstake_arrow);
                                                if (imageView3 != null) {
                                                    i = R.id.iv_tips;
                                                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_tips);
                                                    if (imageView4 != null) {
                                                        i = R.id.iv_trx;
                                                        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.iv_trx);
                                                        if (simpleDraweeView != null) {
                                                            i = R.id.li_avail_amount;
                                                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_avail_amount);
                                                            if (linearLayout != null) {
                                                                i = R.id.li_bottom_option;
                                                                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_bottom_option);
                                                                if (linearLayout2 != null) {
                                                                    i = R.id.li_error_tips;
                                                                    LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_error_tips);
                                                                    if (linearLayout3 != null) {
                                                                        i = R.id.ll_bg;
                                                                        LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_bg);
                                                                        if (linearLayout4 != null) {
                                                                            i = R.id.ll_get_bg;
                                                                            LinearLayout linearLayout5 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_get_bg);
                                                                            if (linearLayout5 != null) {
                                                                                i = R.id.rl_get_show;
                                                                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_get_show);
                                                                                if (relativeLayout != null) {
                                                                                    i = R.id.rl_staked;
                                                                                    RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_staked);
                                                                                    if (relativeLayout2 != null) {
                                                                                        i = R.id.rl_tips;
                                                                                        RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_tips);
                                                                                        if (relativeLayout3 != null) {
                                                                                            LinearLayout linearLayout6 = (LinearLayout) view;
                                                                                            i = R.id.scroll_view;
                                                                                            NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(view, R.id.scroll_view);
                                                                                            if (nestedScrollView != null) {
                                                                                                i = R.id.tablayout;
                                                                                                LinearLayout linearLayout7 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.tablayout);
                                                                                                if (linearLayout7 != null) {
                                                                                                    i = R.id.tv_auto_deduct_tips;
                                                                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_auto_deduct_tips);
                                                                                                    if (textView5 != null) {
                                                                                                        i = R.id.tv_available_amount;
                                                                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_available_amount);
                                                                                                        if (textView6 != null) {
                                                                                                            i = R.id.tv_error_tips;
                                                                                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_error_tips);
                                                                                                            if (textView7 != null) {
                                                                                                                i = R.id.tv_resource_get_amount;
                                                                                                                TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_resource_get_amount);
                                                                                                                if (textView8 != null) {
                                                                                                                    i = R.id.tv_resource_per_day;
                                                                                                                    TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_resource_per_day);
                                                                                                                    if (textView9 != null) {
                                                                                                                        i = R.id.tv_resource_per_transaction;
                                                                                                                        TextView textView10 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_resource_per_transaction);
                                                                                                                        if (textView10 != null) {
                                                                                                                            i = R.id.tv_stake_amount;
                                                                                                                            TextView textView11 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_stake_amount);
                                                                                                                            if (textView11 != null) {
                                                                                                                                i = R.id.tv_stake_bandwidth;
                                                                                                                                TextView textView12 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_stake_bandwidth);
                                                                                                                                if (textView12 != null) {
                                                                                                                                    i = R.id.tv_stake_energy;
                                                                                                                                    TextView textView13 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_stake_energy);
                                                                                                                                    if (textView13 != null) {
                                                                                                                                        i = R.id.tv_stake_tips;
                                                                                                                                        TextView textView14 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_stake_tips);
                                                                                                                                        if (textView14 != null) {
                                                                                                                                            i = R.id.tv_stake_title;
                                                                                                                                            TextView textView15 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_stake_title);
                                                                                                                                            if (textView15 != null) {
                                                                                                                                                i = R.id.tv_stake_unstake;
                                                                                                                                                TextView textView16 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_stake_unstake);
                                                                                                                                                if (textView16 != null) {
                                                                                                                                                    i = R.id.tv_under_control_tips;
                                                                                                                                                    TextView textView17 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_under_control_tips);
                                                                                                                                                    if (textView17 != null) {
                                                                                                                                                        i = R.id.tv_vote_get_amount;
                                                                                                                                                        TextView textView18 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_vote_get_amount);
                                                                                                                                                        if (textView18 != null) {
                                                                                                                                                            return new AcStakeTrxBinding(linearLayout6, textView, textView2, textView3, textView4, button, findChildViewById, editText, currencyEditText, imageView, imageView2, imageView3, imageView4, simpleDraweeView, linearLayout, linearLayout2, linearLayout3, linearLayout4, linearLayout5, relativeLayout, relativeLayout2, relativeLayout3, linearLayout6, nestedScrollView, linearLayout7, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14, textView15, textView16, textView17, textView18);
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
