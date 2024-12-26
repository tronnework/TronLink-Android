package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class FgConfirmTransferBinding implements ViewBinding {
    public final Button btGo;
    public final FrameLayout flRoot;
    public final LinearLayout ivCloseOne;
    public final LinearLayout llEnergy;
    public final LinearLayout llMiddle;
    public final RelativeLayout rlAddress;
    public final RelativeLayout rlContent;
    public final RelativeLayout rlFee;
    public final RelativeLayout rlFromAddress;
    public final RelativeLayout rlMiddleThree;
    public final LinearLayout rlMiddleTwo;
    public final RelativeLayout rlNote;
    public final RelativeLayout rlRoot;
    public final RelativeLayout rlTop;
    private final RelativeLayout rootView;
    public final TextView tvAddress;
    public final TextView tvFee;
    public final TextView tvFeeAmountBw;
    public final TextView tvFromAddress;
    public final TextView tvFromMiddle;
    public final TextView tvMiddle;
    public final TextView tvNoBandwidth;
    public final TextView tvNoEnergy;
    public final TextView tvNote;
    public final TextView tvNoteInfo;
    public final TextView tvRealMoney;
    public final View vLine;
    public final View vMiddle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FgConfirmTransferBinding(RelativeLayout relativeLayout, Button button, FrameLayout frameLayout, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, RelativeLayout relativeLayout5, RelativeLayout relativeLayout6, LinearLayout linearLayout4, RelativeLayout relativeLayout7, RelativeLayout relativeLayout8, RelativeLayout relativeLayout9, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, View view, View view2) {
        this.rootView = relativeLayout;
        this.btGo = button;
        this.flRoot = frameLayout;
        this.ivCloseOne = linearLayout;
        this.llEnergy = linearLayout2;
        this.llMiddle = linearLayout3;
        this.rlAddress = relativeLayout2;
        this.rlContent = relativeLayout3;
        this.rlFee = relativeLayout4;
        this.rlFromAddress = relativeLayout5;
        this.rlMiddleThree = relativeLayout6;
        this.rlMiddleTwo = linearLayout4;
        this.rlNote = relativeLayout7;
        this.rlRoot = relativeLayout8;
        this.rlTop = relativeLayout9;
        this.tvAddress = textView;
        this.tvFee = textView2;
        this.tvFeeAmountBw = textView3;
        this.tvFromAddress = textView4;
        this.tvFromMiddle = textView5;
        this.tvMiddle = textView6;
        this.tvNoBandwidth = textView7;
        this.tvNoEnergy = textView8;
        this.tvNote = textView9;
        this.tvNoteInfo = textView10;
        this.tvRealMoney = textView11;
        this.vLine = view;
        this.vMiddle = view2;
    }

    public static FgConfirmTransferBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FgConfirmTransferBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fg_confirm_transfer, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FgConfirmTransferBinding bind(View view) {
        int i = R.id.bt_go;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_go);
        if (button != null) {
            i = R.id.fl_root;
            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.fl_root);
            if (frameLayout != null) {
                i = R.id.iv_close_one;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.iv_close_one);
                if (linearLayout != null) {
                    i = R.id.ll_energy;
                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_energy);
                    if (linearLayout2 != null) {
                        i = R.id.ll_middle;
                        LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_middle);
                        if (linearLayout3 != null) {
                            i = R.id.rl_address;
                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_address);
                            if (relativeLayout != null) {
                                i = R.id.rl_content;
                                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_content);
                                if (relativeLayout2 != null) {
                                    i = R.id.rl_fee;
                                    RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_fee);
                                    if (relativeLayout3 != null) {
                                        i = R.id.rl_from_address;
                                        RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_from_address);
                                        if (relativeLayout4 != null) {
                                            i = R.id.rl_middle_three;
                                            RelativeLayout relativeLayout5 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_middle_three);
                                            if (relativeLayout5 != null) {
                                                i = R.id.rl_middle_two;
                                                LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.rl_middle_two);
                                                if (linearLayout4 != null) {
                                                    i = R.id.rl_note;
                                                    RelativeLayout relativeLayout6 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_note);
                                                    if (relativeLayout6 != null) {
                                                        RelativeLayout relativeLayout7 = (RelativeLayout) view;
                                                        i = R.id.rl_top;
                                                        RelativeLayout relativeLayout8 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_top);
                                                        if (relativeLayout8 != null) {
                                                            i = R.id.tv_address;
                                                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address);
                                                            if (textView != null) {
                                                                i = R.id.tv_fee;
                                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_fee);
                                                                if (textView2 != null) {
                                                                    i = R.id.tv_fee_amount_bw;
                                                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_fee_amount_bw);
                                                                    if (textView3 != null) {
                                                                        i = R.id.tv_from_address;
                                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_from_address);
                                                                        if (textView4 != null) {
                                                                            i = R.id.tv_from_middle;
                                                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_from_middle);
                                                                            if (textView5 != null) {
                                                                                i = R.id.tv_middle;
                                                                                TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_middle);
                                                                                if (textView6 != null) {
                                                                                    i = R.id.tv_no_bandwidth;
                                                                                    TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_no_bandwidth);
                                                                                    if (textView7 != null) {
                                                                                        i = R.id.tv_no_energy;
                                                                                        TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_no_energy);
                                                                                        if (textView8 != null) {
                                                                                            i = R.id.tv_note;
                                                                                            TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_note);
                                                                                            if (textView9 != null) {
                                                                                                i = R.id.tv_note_info;
                                                                                                TextView textView10 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_note_info);
                                                                                                if (textView10 != null) {
                                                                                                    i = R.id.tv_real_money;
                                                                                                    TextView textView11 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_real_money);
                                                                                                    if (textView11 != null) {
                                                                                                        i = R.id.v_line;
                                                                                                        View findChildViewById = ViewBindings.findChildViewById(view, R.id.v_line);
                                                                                                        if (findChildViewById != null) {
                                                                                                            i = R.id.v_middle;
                                                                                                            View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.v_middle);
                                                                                                            if (findChildViewById2 != null) {
                                                                                                                return new FgConfirmTransferBinding(relativeLayout7, button, frameLayout, linearLayout, linearLayout2, linearLayout3, relativeLayout, relativeLayout2, relativeLayout3, relativeLayout4, relativeLayout5, linearLayout4, relativeLayout6, relativeLayout7, relativeLayout8, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, findChildViewById, findChildViewById2);
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
