package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.ErrorEdiTextLayout;
import com.tronlinkpro.wallet.R;
public final class AcJustswapBinding implements ViewBinding {
    public final Button btSend;
    public final TextView btnBuy;
    public final TextView btnSell;
    public final Button confirm;
    public final ErrorEdiTextLayout eetContent;
    public final EditText etNewPassword;
    public final LinearLayout ivCloseTwo;
    public final LinearLayout llAddress;
    public final LinearLayout llCancelVoteError;
    public final LinearLayout name;
    public final RelativeLayout rlConfirm;
    public final LinearLayout rlPassword;
    public final RelativeLayout rlSecond;
    public final RelativeLayout rlThird;
    public final RelativeLayout rlTopTwo;
    private final RelativeLayout rootView;
    public final TextView tvCancelAll;
    public final TextView tvLeftAvailable;
    public final TextView tvLeftReceive;
    public final TextView tvNoBandwidth;
    public final TextView tvTitle;
    public final TextView tvToken;
    public final TextView tvTokenAddress;
    public final TextView tvTokenName;
    public final TextView tvTrx;
    public final View vMiddleTwo;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcJustswapBinding(RelativeLayout relativeLayout, Button button, TextView textView, TextView textView2, Button button2, ErrorEdiTextLayout errorEdiTextLayout, EditText editText, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, RelativeLayout relativeLayout2, LinearLayout linearLayout5, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, RelativeLayout relativeLayout5, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, View view) {
        this.rootView = relativeLayout;
        this.btSend = button;
        this.btnBuy = textView;
        this.btnSell = textView2;
        this.confirm = button2;
        this.eetContent = errorEdiTextLayout;
        this.etNewPassword = editText;
        this.ivCloseTwo = linearLayout;
        this.llAddress = linearLayout2;
        this.llCancelVoteError = linearLayout3;
        this.name = linearLayout4;
        this.rlConfirm = relativeLayout2;
        this.rlPassword = linearLayout5;
        this.rlSecond = relativeLayout3;
        this.rlThird = relativeLayout4;
        this.rlTopTwo = relativeLayout5;
        this.tvCancelAll = textView3;
        this.tvLeftAvailable = textView4;
        this.tvLeftReceive = textView5;
        this.tvNoBandwidth = textView6;
        this.tvTitle = textView7;
        this.tvToken = textView8;
        this.tvTokenAddress = textView9;
        this.tvTokenName = textView10;
        this.tvTrx = textView11;
        this.vMiddleTwo = view;
    }

    public static AcJustswapBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcJustswapBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_justswap, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcJustswapBinding bind(View view) {
        int i = R.id.bt_send;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_send);
        if (button != null) {
            i = R.id.btn_buy;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.btn_buy);
            if (textView != null) {
                i = R.id.btn_sell;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.btn_sell);
                if (textView2 != null) {
                    i = R.id.confirm;
                    Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.confirm);
                    if (button2 != null) {
                        i = R.id.eet_content;
                        ErrorEdiTextLayout errorEdiTextLayout = (ErrorEdiTextLayout) ViewBindings.findChildViewById(view, R.id.eet_content);
                        if (errorEdiTextLayout != null) {
                            i = R.id.et_new_password;
                            EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.et_new_password);
                            if (editText != null) {
                                i = R.id.iv_close_two;
                                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.iv_close_two);
                                if (linearLayout != null) {
                                    i = R.id.ll_address;
                                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_address);
                                    if (linearLayout2 != null) {
                                        i = R.id.ll_cancel_vote_error;
                                        LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_cancel_vote_error);
                                        if (linearLayout3 != null) {
                                            i = R.id.name;
                                            LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.name);
                                            if (linearLayout4 != null) {
                                                i = R.id.rl_confirm;
                                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_confirm);
                                                if (relativeLayout != null) {
                                                    i = R.id.rl_password;
                                                    LinearLayout linearLayout5 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.rl_password);
                                                    if (linearLayout5 != null) {
                                                        i = R.id.rl_second;
                                                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_second);
                                                        if (relativeLayout2 != null) {
                                                            i = R.id.rl_third;
                                                            RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_third);
                                                            if (relativeLayout3 != null) {
                                                                i = R.id.rl_top_two;
                                                                RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_top_two);
                                                                if (relativeLayout4 != null) {
                                                                    i = R.id.tv_cancel_all;
                                                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_cancel_all);
                                                                    if (textView3 != null) {
                                                                        i = R.id.tv_left_available;
                                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_left_available);
                                                                        if (textView4 != null) {
                                                                            i = R.id.tv_left_receive;
                                                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_left_receive);
                                                                            if (textView5 != null) {
                                                                                i = R.id.tv_no_bandwidth;
                                                                                TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_no_bandwidth);
                                                                                if (textView6 != null) {
                                                                                    i = R.id.tv_title;
                                                                                    TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                                                                                    if (textView7 != null) {
                                                                                        i = R.id.tv_token;
                                                                                        TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_token);
                                                                                        if (textView8 != null) {
                                                                                            i = R.id.tv_token_address;
                                                                                            TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_token_address);
                                                                                            if (textView9 != null) {
                                                                                                i = R.id.tv_token_name;
                                                                                                TextView textView10 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_token_name);
                                                                                                if (textView10 != null) {
                                                                                                    i = R.id.tv_trx;
                                                                                                    TextView textView11 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_trx);
                                                                                                    if (textView11 != null) {
                                                                                                        i = R.id.v_middle_two;
                                                                                                        View findChildViewById = ViewBindings.findChildViewById(view, R.id.v_middle_two);
                                                                                                        if (findChildViewById != null) {
                                                                                                            return new AcJustswapBinding((RelativeLayout) view, button, textView, textView2, button2, errorEdiTextLayout, editText, linearLayout, linearLayout2, linearLayout3, linearLayout4, relativeLayout, linearLayout5, relativeLayout2, relativeLayout3, relativeLayout4, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, findChildViewById);
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
