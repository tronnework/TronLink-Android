package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class LayoutTransactionFeeResourceBinding implements ViewBinding {
    public final LinearLayout feeContent;
    public final ImageView ivArrowRight;
    public final ImageView ivTips;
    public final LinearLayout llFee;
    public final RelativeLayout rlAccountUpdatePermission;
    public final RelativeLayout rlActiveAccount;
    public final RelativeLayout rlBurnForBand;
    public final RelativeLayout rlBurnForEnergy;
    public final RelativeLayout rlCreateBancorTransaction;
    public final RelativeLayout rlCreateRepresentatives;
    public final RelativeLayout rlMemoFee;
    public final RelativeLayout rlMultisignTransaction;
    public final RelativeLayout rlTRC10Issue;
    private final LinearLayout rootView;
    public final TextView tvError;
    public final TextView tvFee;
    public final TextView tvFeeTitle;
    public final TextView tvLeftAccountUpdatePermission;
    public final TextView tvLeftActiveAccount;
    public final TextView tvLeftBurnForBand;
    public final TextView tvLeftBurnForEnergy;
    public final TextView tvLeftCreateBancorTransaction;
    public final TextView tvLeftCreateRepresentatives;
    public final TextView tvLeftMemoFee;
    public final TextView tvLeftMultisignTransaction;
    public final TextView tvLeftTRC10Issue;
    public final TextView tvRightAccountUpdatePermission;
    public final TextView tvRightActiveAccount;
    public final TextView tvRightBurnForBand;
    public final TextView tvRightBurnForEnergy;
    public final TextView tvRightCreateBancorTransaction;
    public final TextView tvRightCreateRepresentatives;
    public final TextView tvRightMemoFee;
    public final TextView tvRightMultisignTransaction;
    public final TextView tvRightTRC10Issue;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private LayoutTransactionFeeResourceBinding(LinearLayout linearLayout, LinearLayout linearLayout2, ImageView imageView, ImageView imageView2, LinearLayout linearLayout3, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, RelativeLayout relativeLayout5, RelativeLayout relativeLayout6, RelativeLayout relativeLayout7, RelativeLayout relativeLayout8, RelativeLayout relativeLayout9, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14, TextView textView15, TextView textView16, TextView textView17, TextView textView18, TextView textView19, TextView textView20, TextView textView21) {
        this.rootView = linearLayout;
        this.feeContent = linearLayout2;
        this.ivArrowRight = imageView;
        this.ivTips = imageView2;
        this.llFee = linearLayout3;
        this.rlAccountUpdatePermission = relativeLayout;
        this.rlActiveAccount = relativeLayout2;
        this.rlBurnForBand = relativeLayout3;
        this.rlBurnForEnergy = relativeLayout4;
        this.rlCreateBancorTransaction = relativeLayout5;
        this.rlCreateRepresentatives = relativeLayout6;
        this.rlMemoFee = relativeLayout7;
        this.rlMultisignTransaction = relativeLayout8;
        this.rlTRC10Issue = relativeLayout9;
        this.tvError = textView;
        this.tvFee = textView2;
        this.tvFeeTitle = textView3;
        this.tvLeftAccountUpdatePermission = textView4;
        this.tvLeftActiveAccount = textView5;
        this.tvLeftBurnForBand = textView6;
        this.tvLeftBurnForEnergy = textView7;
        this.tvLeftCreateBancorTransaction = textView8;
        this.tvLeftCreateRepresentatives = textView9;
        this.tvLeftMemoFee = textView10;
        this.tvLeftMultisignTransaction = textView11;
        this.tvLeftTRC10Issue = textView12;
        this.tvRightAccountUpdatePermission = textView13;
        this.tvRightActiveAccount = textView14;
        this.tvRightBurnForBand = textView15;
        this.tvRightBurnForEnergy = textView16;
        this.tvRightCreateBancorTransaction = textView17;
        this.tvRightCreateRepresentatives = textView18;
        this.tvRightMemoFee = textView19;
        this.tvRightMultisignTransaction = textView20;
        this.tvRightTRC10Issue = textView21;
    }

    public static LayoutTransactionFeeResourceBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutTransactionFeeResourceBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_transaction_fee_resource, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutTransactionFeeResourceBinding bind(View view) {
        int i = R.id.fee_content;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.fee_content);
        if (linearLayout != null) {
            i = R.id.iv_arrow_right;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_arrow_right);
            if (imageView != null) {
                i = R.id.iv_tips;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_tips);
                if (imageView2 != null) {
                    i = R.id.ll_fee;
                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_fee);
                    if (linearLayout2 != null) {
                        i = R.id.rl_account_update_permission;
                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_account_update_permission);
                        if (relativeLayout != null) {
                            i = R.id.rl_active_account;
                            RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_active_account);
                            if (relativeLayout2 != null) {
                                i = R.id.rl_burn_for_band;
                                RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_burn_for_band);
                                if (relativeLayout3 != null) {
                                    i = R.id.rl_burn_for_energy;
                                    RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_burn_for_energy);
                                    if (relativeLayout4 != null) {
                                        i = R.id.rl_create_bancor_transaction;
                                        RelativeLayout relativeLayout5 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_create_bancor_transaction);
                                        if (relativeLayout5 != null) {
                                            i = R.id.rl_create_representatives;
                                            RelativeLayout relativeLayout6 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_create_representatives);
                                            if (relativeLayout6 != null) {
                                                i = R.id.rl_memo_fee;
                                                RelativeLayout relativeLayout7 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_memo_fee);
                                                if (relativeLayout7 != null) {
                                                    i = R.id.rl_multisign_transaction;
                                                    RelativeLayout relativeLayout8 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_multisign_transaction);
                                                    if (relativeLayout8 != null) {
                                                        i = R.id.rl_TRC10_issue;
                                                        RelativeLayout relativeLayout9 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_TRC10_issue);
                                                        if (relativeLayout9 != null) {
                                                            i = R.id.tv_error;
                                                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_error);
                                                            if (textView != null) {
                                                                i = R.id.tv_fee;
                                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_fee);
                                                                if (textView2 != null) {
                                                                    i = R.id.tv_fee_title;
                                                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_fee_title);
                                                                    if (textView3 != null) {
                                                                        i = R.id.tv_left_account_update_permission;
                                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_left_account_update_permission);
                                                                        if (textView4 != null) {
                                                                            i = R.id.tv_left_active_account;
                                                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_left_active_account);
                                                                            if (textView5 != null) {
                                                                                i = R.id.tv_left_burn_for_band;
                                                                                TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_left_burn_for_band);
                                                                                if (textView6 != null) {
                                                                                    i = R.id.tv_left_burn_for_energy;
                                                                                    TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_left_burn_for_energy);
                                                                                    if (textView7 != null) {
                                                                                        i = R.id.tv_left_create_bancor_transaction;
                                                                                        TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_left_create_bancor_transaction);
                                                                                        if (textView8 != null) {
                                                                                            i = R.id.tv_left_create_representatives;
                                                                                            TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_left_create_representatives);
                                                                                            if (textView9 != null) {
                                                                                                i = R.id.tv_left_memo_fee;
                                                                                                TextView textView10 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_left_memo_fee);
                                                                                                if (textView10 != null) {
                                                                                                    i = R.id.tv_left_multisign_transaction;
                                                                                                    TextView textView11 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_left_multisign_transaction);
                                                                                                    if (textView11 != null) {
                                                                                                        i = R.id.tv_left_TRC10_issue;
                                                                                                        TextView textView12 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_left_TRC10_issue);
                                                                                                        if (textView12 != null) {
                                                                                                            i = R.id.tv_right_account_update_permission;
                                                                                                            TextView textView13 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_right_account_update_permission);
                                                                                                            if (textView13 != null) {
                                                                                                                i = R.id.tv_right_active_account;
                                                                                                                TextView textView14 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_right_active_account);
                                                                                                                if (textView14 != null) {
                                                                                                                    i = R.id.tv_right_burn_for_band;
                                                                                                                    TextView textView15 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_right_burn_for_band);
                                                                                                                    if (textView15 != null) {
                                                                                                                        i = R.id.tv_right_burn_for_energy;
                                                                                                                        TextView textView16 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_right_burn_for_energy);
                                                                                                                        if (textView16 != null) {
                                                                                                                            i = R.id.tv_right_create_bancor_transaction;
                                                                                                                            TextView textView17 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_right_create_bancor_transaction);
                                                                                                                            if (textView17 != null) {
                                                                                                                                i = R.id.tv_right_create_representatives;
                                                                                                                                TextView textView18 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_right_create_representatives);
                                                                                                                                if (textView18 != null) {
                                                                                                                                    i = R.id.tv_right_memo_fee;
                                                                                                                                    TextView textView19 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_right_memo_fee);
                                                                                                                                    if (textView19 != null) {
                                                                                                                                        i = R.id.tv_right_multisign_transaction;
                                                                                                                                        TextView textView20 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_right_multisign_transaction);
                                                                                                                                        if (textView20 != null) {
                                                                                                                                            i = R.id.tv_right_TRC10_issue;
                                                                                                                                            TextView textView21 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_right_TRC10_issue);
                                                                                                                                            if (textView21 != null) {
                                                                                                                                                return new LayoutTransactionFeeResourceBinding((LinearLayout) view, linearLayout, imageView, imageView2, linearLayout2, relativeLayout, relativeLayout2, relativeLayout3, relativeLayout4, relativeLayout5, relativeLayout6, relativeLayout7, relativeLayout8, relativeLayout9, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14, textView15, textView16, textView17, textView18, textView19, textView20, textView21);
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
