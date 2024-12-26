package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.CurrencyEditText;
import com.tron.wallet.common.components.ErrorEdiTextLayout;
import com.tronlinkpro.wallet.R;
public final class AcDepositBinding implements ViewBinding {
    public final Button btGo;
    public final ErrorEdiTextLayout eetAmout;
    public final CurrencyEditText etCount;
    public final ImageView ivBa;
    public final ImageView ivRa;
    public final ImageView ivSa;
    public final LinearLayout llBalanceAmout;
    public final LinearLayout llChainName;
    public final LinearLayout llSendAddress;
    public final LinearLayout llTopOne;
    public final LinearLayout llTopThree;
    public final LinearLayout llTopTr;
    private final LinearLayout rootView;
    public final TextView tvAmountLine;
    public final TextView tvAmountName;
    public final TextView tvBalance;
    public final TextView tvChainName;
    public final TextView tvContent;
    public final TextView tvDepositType;
    public final TextView tvLineV;
    public final TextView tvMax;
    public final TextView tvName;
    public final TextView tvTokenid;
    public final TextView tvTypeAmount;
    public final TextView tvTypeChain;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private AcDepositBinding(LinearLayout linearLayout, Button button, ErrorEdiTextLayout errorEdiTextLayout, CurrencyEditText currencyEditText, ImageView imageView, ImageView imageView2, ImageView imageView3, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, LinearLayout linearLayout5, LinearLayout linearLayout6, LinearLayout linearLayout7, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12) {
        this.rootView = linearLayout;
        this.btGo = button;
        this.eetAmout = errorEdiTextLayout;
        this.etCount = currencyEditText;
        this.ivBa = imageView;
        this.ivRa = imageView2;
        this.ivSa = imageView3;
        this.llBalanceAmout = linearLayout2;
        this.llChainName = linearLayout3;
        this.llSendAddress = linearLayout4;
        this.llTopOne = linearLayout5;
        this.llTopThree = linearLayout6;
        this.llTopTr = linearLayout7;
        this.tvAmountLine = textView;
        this.tvAmountName = textView2;
        this.tvBalance = textView3;
        this.tvChainName = textView4;
        this.tvContent = textView5;
        this.tvDepositType = textView6;
        this.tvLineV = textView7;
        this.tvMax = textView8;
        this.tvName = textView9;
        this.tvTokenid = textView10;
        this.tvTypeAmount = textView11;
        this.tvTypeChain = textView12;
    }

    public static AcDepositBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcDepositBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_deposit, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcDepositBinding bind(View view) {
        int i = R.id.bt_go;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_go);
        if (button != null) {
            i = R.id.eet_amout;
            ErrorEdiTextLayout errorEdiTextLayout = (ErrorEdiTextLayout) ViewBindings.findChildViewById(view, R.id.eet_amout);
            if (errorEdiTextLayout != null) {
                i = R.id.et_count;
                CurrencyEditText currencyEditText = (CurrencyEditText) ViewBindings.findChildViewById(view, R.id.et_count);
                if (currencyEditText != null) {
                    i = R.id.iv_ba;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_ba);
                    if (imageView != null) {
                        i = R.id.iv_ra;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_ra);
                        if (imageView2 != null) {
                            i = R.id.iv_sa;
                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_sa);
                            if (imageView3 != null) {
                                i = R.id.ll_balance_amout;
                                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_balance_amout);
                                if (linearLayout != null) {
                                    i = R.id.ll_chain_name;
                                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_chain_name);
                                    if (linearLayout2 != null) {
                                        i = R.id.ll_send_address;
                                        LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_send_address);
                                        if (linearLayout3 != null) {
                                            i = R.id.ll_top_one;
                                            LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_top_one);
                                            if (linearLayout4 != null) {
                                                i = R.id.ll_top_three;
                                                LinearLayout linearLayout5 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_top_three);
                                                if (linearLayout5 != null) {
                                                    i = R.id.ll_top_tr;
                                                    LinearLayout linearLayout6 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_top_tr);
                                                    if (linearLayout6 != null) {
                                                        i = R.id.tv_amount_line;
                                                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_amount_line);
                                                        if (textView != null) {
                                                            i = R.id.tv_amount_name;
                                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_amount_name);
                                                            if (textView2 != null) {
                                                                i = R.id.tv_balance;
                                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_balance);
                                                                if (textView3 != null) {
                                                                    i = R.id.tv_chain_name;
                                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_chain_name);
                                                                    if (textView4 != null) {
                                                                        i = R.id.tv_content;
                                                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_content);
                                                                        if (textView5 != null) {
                                                                            i = R.id.tv_deposit_type;
                                                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_deposit_type);
                                                                            if (textView6 != null) {
                                                                                i = R.id.tv_line_v;
                                                                                TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_line_v);
                                                                                if (textView7 != null) {
                                                                                    i = R.id.tv_max;
                                                                                    TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_max);
                                                                                    if (textView8 != null) {
                                                                                        i = R.id.tv_name;
                                                                                        TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name);
                                                                                        if (textView9 != null) {
                                                                                            i = R.id.tv_tokenid;
                                                                                            TextView textView10 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_tokenid);
                                                                                            if (textView10 != null) {
                                                                                                i = R.id.tv_type_amount;
                                                                                                TextView textView11 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_type_amount);
                                                                                                if (textView11 != null) {
                                                                                                    i = R.id.tv_type_chain;
                                                                                                    TextView textView12 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_type_chain);
                                                                                                    if (textView12 != null) {
                                                                                                        return new AcDepositBinding((LinearLayout) view, button, errorEdiTextLayout, currencyEditText, imageView, imageView2, imageView3, linearLayout, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12);
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
