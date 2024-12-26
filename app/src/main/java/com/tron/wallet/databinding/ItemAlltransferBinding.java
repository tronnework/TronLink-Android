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
public final class ItemAlltransferBinding implements ViewBinding {
    public final TextView confirm;
    public final View divider;
    public final LinearLayout llRight;
    public final LinearLayout llTop;
    public final RelativeLayout rlTime;
    public final RelativeLayout rootView;
    private final RelativeLayout rootView_;
    public final TextView tvAddress;
    public final TextView tvContractTitle;
    public final TextView tvOne;
    public final TextView tvOneSub;
    public final TextView tvThree;
    public final TextView tvTime;
    public final TextView tvTwo;
    public final TextView tvTwoSub;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView_;
    }

    private ItemAlltransferBinding(RelativeLayout relativeLayout, TextView textView, View view, LinearLayout linearLayout, LinearLayout linearLayout2, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9) {
        this.rootView_ = relativeLayout;
        this.confirm = textView;
        this.divider = view;
        this.llRight = linearLayout;
        this.llTop = linearLayout2;
        this.rlTime = relativeLayout2;
        this.rootView = relativeLayout3;
        this.tvAddress = textView2;
        this.tvContractTitle = textView3;
        this.tvOne = textView4;
        this.tvOneSub = textView5;
        this.tvThree = textView6;
        this.tvTime = textView7;
        this.tvTwo = textView8;
        this.tvTwoSub = textView9;
    }

    public static ItemAlltransferBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemAlltransferBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_alltransfer, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemAlltransferBinding bind(View view) {
        int i = R.id.confirm;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.confirm);
        if (textView != null) {
            i = R.id.divider;
            View findChildViewById = ViewBindings.findChildViewById(view, R.id.divider);
            if (findChildViewById != null) {
                i = R.id.ll_right;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_right);
                if (linearLayout != null) {
                    i = R.id.ll_top;
                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_top);
                    if (linearLayout2 != null) {
                        i = R.id.rl_time;
                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_time);
                        if (relativeLayout != null) {
                            RelativeLayout relativeLayout2 = (RelativeLayout) view;
                            i = R.id.tv_address;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address);
                            if (textView2 != null) {
                                i = R.id.tv_contract_title;
                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_contract_title);
                                if (textView3 != null) {
                                    i = R.id.tv_one;
                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_one);
                                    if (textView4 != null) {
                                        i = R.id.tv_one_sub;
                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_one_sub);
                                        if (textView5 != null) {
                                            i = R.id.tv_three;
                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_three);
                                            if (textView6 != null) {
                                                i = R.id.tv_time;
                                                TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_time);
                                                if (textView7 != null) {
                                                    i = R.id.tv_two;
                                                    TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_two);
                                                    if (textView8 != null) {
                                                        i = R.id.tv_two_sub;
                                                        TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_two_sub);
                                                        if (textView9 != null) {
                                                            return new ItemAlltransferBinding(relativeLayout2, textView, findChildViewById, linearLayout, linearLayout2, relativeLayout, relativeLayout2, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9);
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
