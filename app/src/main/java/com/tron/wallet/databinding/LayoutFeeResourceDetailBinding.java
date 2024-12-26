package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class LayoutFeeResourceDetailBinding implements ViewBinding {
    public final ConstraintLayout rlFee;
    public final RelativeLayout rlMemoFee;
    public final RelativeLayout rlMultiFee;
    public final RelativeLayout rlResourceBandwidth;
    public final RelativeLayout rlResourceEnergy;
    private final LinearLayout rootView;
    public final TextView tvLeftActiveAccount;
    public final TextView tvLeftDeductBw;
    public final TextView tvLeftDeductEnergy;
    public final TextView tvLeftMemoFee;
    public final TextView tvLeftMultiFee;
    public final TextView tvRightActiveAccount;
    public final TextView tvRightDeductBw;
    public final TextView tvRightDeductEnergy;
    public final TextView tvRightMemoFee;
    public final TextView tvRightMultiFee;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private LayoutFeeResourceDetailBinding(LinearLayout linearLayout, ConstraintLayout constraintLayout, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10) {
        this.rootView = linearLayout;
        this.rlFee = constraintLayout;
        this.rlMemoFee = relativeLayout;
        this.rlMultiFee = relativeLayout2;
        this.rlResourceBandwidth = relativeLayout3;
        this.rlResourceEnergy = relativeLayout4;
        this.tvLeftActiveAccount = textView;
        this.tvLeftDeductBw = textView2;
        this.tvLeftDeductEnergy = textView3;
        this.tvLeftMemoFee = textView4;
        this.tvLeftMultiFee = textView5;
        this.tvRightActiveAccount = textView6;
        this.tvRightDeductBw = textView7;
        this.tvRightDeductEnergy = textView8;
        this.tvRightMemoFee = textView9;
        this.tvRightMultiFee = textView10;
    }

    public static LayoutFeeResourceDetailBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutFeeResourceDetailBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_fee_resource_detail, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutFeeResourceDetailBinding bind(View view) {
        int i = R.id.rl_fee;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.rl_fee);
        if (constraintLayout != null) {
            i = R.id.rl_memo_fee;
            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_memo_fee);
            if (relativeLayout != null) {
                i = R.id.rl_multi_fee;
                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_multi_fee);
                if (relativeLayout2 != null) {
                    i = R.id.rl_resource_bandwidth;
                    RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_resource_bandwidth);
                    if (relativeLayout3 != null) {
                        i = R.id.rl_resource_energy;
                        RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_resource_energy);
                        if (relativeLayout4 != null) {
                            i = R.id.tv_left_active_account;
                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_left_active_account);
                            if (textView != null) {
                                i = R.id.tv_left_deduct_bw;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_left_deduct_bw);
                                if (textView2 != null) {
                                    i = R.id.tv_left_deduct_energy;
                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_left_deduct_energy);
                                    if (textView3 != null) {
                                        i = R.id.tv_left_memo_fee;
                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_left_memo_fee);
                                        if (textView4 != null) {
                                            i = R.id.tv_left_multi_fee;
                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_left_multi_fee);
                                            if (textView5 != null) {
                                                i = R.id.tv_right_active_account;
                                                TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_right_active_account);
                                                if (textView6 != null) {
                                                    i = R.id.tv_right_deduct_bw;
                                                    TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_right_deduct_bw);
                                                    if (textView7 != null) {
                                                        i = R.id.tv_right_deduct_energy;
                                                        TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_right_deduct_energy);
                                                        if (textView8 != null) {
                                                            i = R.id.tv_right_memo_fee;
                                                            TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_right_memo_fee);
                                                            if (textView9 != null) {
                                                                i = R.id.tv_right_multi_fee;
                                                                TextView textView10 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_right_multi_fee);
                                                                if (textView10 != null) {
                                                                    return new LayoutFeeResourceDetailBinding((LinearLayout) view, constraintLayout, relativeLayout, relativeLayout2, relativeLayout3, relativeLayout4, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10);
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
