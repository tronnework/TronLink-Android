package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class FgConfirmCommonBinding implements ViewBinding {
    public final Button btSend;
    public final LinearLayout ivCloseTwo;
    public final LinearLayout llErrorContent;
    public final RelativeLayout rlFee;
    public final RelativeLayout rlMiddleThree;
    public final LinearLayout rlMiddleTwo;
    public final RelativeLayout rlPassword;
    public final RelativeLayout rlTopTwo;
    public final RelativeLayout rlType;
    public final RelativeLayout root;
    private final RelativeLayout rootView;
    public final RecyclerView rvText;
    public final TextView tvFee;
    public final TextView tvFeeAmountBw;
    public final TextView tvHint;
    public final TextView tvRealMoney;
    public final TextView tvTitle;
    public final TextView tvType;
    public final View vMiddle;
    public final View vMiddleTwo;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FgConfirmCommonBinding(RelativeLayout relativeLayout, Button button, LinearLayout linearLayout, LinearLayout linearLayout2, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, LinearLayout linearLayout3, RelativeLayout relativeLayout4, RelativeLayout relativeLayout5, RelativeLayout relativeLayout6, RelativeLayout relativeLayout7, RecyclerView recyclerView, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, View view, View view2) {
        this.rootView = relativeLayout;
        this.btSend = button;
        this.ivCloseTwo = linearLayout;
        this.llErrorContent = linearLayout2;
        this.rlFee = relativeLayout2;
        this.rlMiddleThree = relativeLayout3;
        this.rlMiddleTwo = linearLayout3;
        this.rlPassword = relativeLayout4;
        this.rlTopTwo = relativeLayout5;
        this.rlType = relativeLayout6;
        this.root = relativeLayout7;
        this.rvText = recyclerView;
        this.tvFee = textView;
        this.tvFeeAmountBw = textView2;
        this.tvHint = textView3;
        this.tvRealMoney = textView4;
        this.tvTitle = textView5;
        this.tvType = textView6;
        this.vMiddle = view;
        this.vMiddleTwo = view2;
    }

    public static FgConfirmCommonBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FgConfirmCommonBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fg_confirm_common, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FgConfirmCommonBinding bind(View view) {
        int i = R.id.bt_send;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_send);
        if (button != null) {
            i = R.id.iv_close_two;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.iv_close_two);
            if (linearLayout != null) {
                i = R.id.ll_error_content;
                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_error_content);
                if (linearLayout2 != null) {
                    i = R.id.rl_fee;
                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_fee);
                    if (relativeLayout != null) {
                        i = R.id.rl_middle_three;
                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_middle_three);
                        if (relativeLayout2 != null) {
                            i = R.id.rl_middle_two;
                            LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.rl_middle_two);
                            if (linearLayout3 != null) {
                                i = R.id.rl_password;
                                RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_password);
                                if (relativeLayout3 != null) {
                                    i = R.id.rl_top_two;
                                    RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_top_two);
                                    if (relativeLayout4 != null) {
                                        i = R.id.rl_type;
                                        RelativeLayout relativeLayout5 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_type);
                                        if (relativeLayout5 != null) {
                                            RelativeLayout relativeLayout6 = (RelativeLayout) view;
                                            i = R.id.rv_text;
                                            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rv_text);
                                            if (recyclerView != null) {
                                                i = R.id.tv_fee;
                                                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_fee);
                                                if (textView != null) {
                                                    i = R.id.tv_fee_amount_bw;
                                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_fee_amount_bw);
                                                    if (textView2 != null) {
                                                        i = R.id.tv_hint;
                                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_hint);
                                                        if (textView3 != null) {
                                                            i = R.id.tv_real_money;
                                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_real_money);
                                                            if (textView4 != null) {
                                                                i = R.id.tv_title;
                                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                                                                if (textView5 != null) {
                                                                    i = R.id.tv_type;
                                                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_type);
                                                                    if (textView6 != null) {
                                                                        i = R.id.v_middle;
                                                                        View findChildViewById = ViewBindings.findChildViewById(view, R.id.v_middle);
                                                                        if (findChildViewById != null) {
                                                                            i = R.id.v_middle_two;
                                                                            View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.v_middle_two);
                                                                            if (findChildViewById2 != null) {
                                                                                return new FgConfirmCommonBinding(relativeLayout6, button, linearLayout, linearLayout2, relativeLayout, relativeLayout2, linearLayout3, relativeLayout3, relativeLayout4, relativeLayout5, relativeLayout6, recyclerView, textView, textView2, textView3, textView4, textView5, textView6, findChildViewById, findChildViewById2);
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
