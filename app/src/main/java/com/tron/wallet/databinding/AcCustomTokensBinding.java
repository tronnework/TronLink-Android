package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.ErrorEdiTextLayout;
import com.tron.wallet.common.components.TrimEditText;
import com.tronlinkpro.wallet.R;
public final class AcCustomTokensBinding implements ViewBinding {
    public final Button btnNext;
    public final ErrorEdiTextLayout eetTokenAddress;
    public final ErrorEdiTextLayout eetTokenName;
    public final ErrorEdiTextLayout eetTokenSymbol;
    public final TrimEditText etTokenAddress;
    public final TrimEditText etTokenDecimal;
    public final TrimEditText etTokenName;
    public final TrimEditText etTokenSymbol;
    public final TrimEditText etTokenType;
    public final ImageView ivScan;
    public final LinearLayout llEditAddress;
    public final LinearLayout llEditDecimal;
    public final LinearLayout llEditName;
    public final LinearLayout llEditSymbol;
    public final RelativeLayout llEtTokenName;
    public final RelativeLayout llEtTokenSymbol;
    public final RelativeLayout llOthers;
    public final LinearLayout llTokenAddress;
    public final LinearLayout llTokenDecimal;
    public final LinearLayout llTokenName;
    public final LinearLayout llTokenSymbol;
    public final LinearLayout llTokenType;
    public final NestedScrollView nestScrollView;
    public final LinearLayout root;
    private final LinearLayout rootView;
    public final TextView tvDetails;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private AcCustomTokensBinding(LinearLayout linearLayout, Button button, ErrorEdiTextLayout errorEdiTextLayout, ErrorEdiTextLayout errorEdiTextLayout2, ErrorEdiTextLayout errorEdiTextLayout3, TrimEditText trimEditText, TrimEditText trimEditText2, TrimEditText trimEditText3, TrimEditText trimEditText4, TrimEditText trimEditText5, ImageView imageView, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, LinearLayout linearLayout5, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, LinearLayout linearLayout6, LinearLayout linearLayout7, LinearLayout linearLayout8, LinearLayout linearLayout9, LinearLayout linearLayout10, NestedScrollView nestedScrollView, LinearLayout linearLayout11, TextView textView) {
        this.rootView = linearLayout;
        this.btnNext = button;
        this.eetTokenAddress = errorEdiTextLayout;
        this.eetTokenName = errorEdiTextLayout2;
        this.eetTokenSymbol = errorEdiTextLayout3;
        this.etTokenAddress = trimEditText;
        this.etTokenDecimal = trimEditText2;
        this.etTokenName = trimEditText3;
        this.etTokenSymbol = trimEditText4;
        this.etTokenType = trimEditText5;
        this.ivScan = imageView;
        this.llEditAddress = linearLayout2;
        this.llEditDecimal = linearLayout3;
        this.llEditName = linearLayout4;
        this.llEditSymbol = linearLayout5;
        this.llEtTokenName = relativeLayout;
        this.llEtTokenSymbol = relativeLayout2;
        this.llOthers = relativeLayout3;
        this.llTokenAddress = linearLayout6;
        this.llTokenDecimal = linearLayout7;
        this.llTokenName = linearLayout8;
        this.llTokenSymbol = linearLayout9;
        this.llTokenType = linearLayout10;
        this.nestScrollView = nestedScrollView;
        this.root = linearLayout11;
        this.tvDetails = textView;
    }

    public static AcCustomTokensBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcCustomTokensBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_custom_tokens, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcCustomTokensBinding bind(View view) {
        int i = R.id.btn_next;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_next);
        if (button != null) {
            i = R.id.eet_token_address;
            ErrorEdiTextLayout errorEdiTextLayout = (ErrorEdiTextLayout) ViewBindings.findChildViewById(view, R.id.eet_token_address);
            if (errorEdiTextLayout != null) {
                i = R.id.eet_token_name;
                ErrorEdiTextLayout errorEdiTextLayout2 = (ErrorEdiTextLayout) ViewBindings.findChildViewById(view, R.id.eet_token_name);
                if (errorEdiTextLayout2 != null) {
                    i = R.id.eet_token_symbol;
                    ErrorEdiTextLayout errorEdiTextLayout3 = (ErrorEdiTextLayout) ViewBindings.findChildViewById(view, R.id.eet_token_symbol);
                    if (errorEdiTextLayout3 != null) {
                        i = R.id.et_token_address;
                        TrimEditText trimEditText = (TrimEditText) ViewBindings.findChildViewById(view, R.id.et_token_address);
                        if (trimEditText != null) {
                            i = R.id.et_token_decimal;
                            TrimEditText trimEditText2 = (TrimEditText) ViewBindings.findChildViewById(view, R.id.et_token_decimal);
                            if (trimEditText2 != null) {
                                i = R.id.et_token_name;
                                TrimEditText trimEditText3 = (TrimEditText) ViewBindings.findChildViewById(view, R.id.et_token_name);
                                if (trimEditText3 != null) {
                                    i = R.id.et_token_symbol;
                                    TrimEditText trimEditText4 = (TrimEditText) ViewBindings.findChildViewById(view, R.id.et_token_symbol);
                                    if (trimEditText4 != null) {
                                        i = R.id.et_token_type;
                                        TrimEditText trimEditText5 = (TrimEditText) ViewBindings.findChildViewById(view, R.id.et_token_type);
                                        if (trimEditText5 != null) {
                                            i = R.id.iv_scan;
                                            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_scan);
                                            if (imageView != null) {
                                                i = R.id.ll_edit_address;
                                                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_edit_address);
                                                if (linearLayout != null) {
                                                    i = R.id.ll_edit_decimal;
                                                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_edit_decimal);
                                                    if (linearLayout2 != null) {
                                                        i = R.id.ll_edit_name;
                                                        LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_edit_name);
                                                        if (linearLayout3 != null) {
                                                            i = R.id.ll_edit_symbol;
                                                            LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_edit_symbol);
                                                            if (linearLayout4 != null) {
                                                                i = R.id.ll_et_token_name;
                                                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_et_token_name);
                                                                if (relativeLayout != null) {
                                                                    i = R.id.ll_et_token_symbol;
                                                                    RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_et_token_symbol);
                                                                    if (relativeLayout2 != null) {
                                                                        i = R.id.ll_others;
                                                                        RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_others);
                                                                        if (relativeLayout3 != null) {
                                                                            i = R.id.ll_token_address;
                                                                            LinearLayout linearLayout5 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_token_address);
                                                                            if (linearLayout5 != null) {
                                                                                i = R.id.ll_token_decimal;
                                                                                LinearLayout linearLayout6 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_token_decimal);
                                                                                if (linearLayout6 != null) {
                                                                                    i = R.id.ll_token_name;
                                                                                    LinearLayout linearLayout7 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_token_name);
                                                                                    if (linearLayout7 != null) {
                                                                                        i = R.id.ll_token_symbol;
                                                                                        LinearLayout linearLayout8 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_token_symbol);
                                                                                        if (linearLayout8 != null) {
                                                                                            i = R.id.ll_token_type;
                                                                                            LinearLayout linearLayout9 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_token_type);
                                                                                            if (linearLayout9 != null) {
                                                                                                i = R.id.nest_scroll_view;
                                                                                                NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(view, R.id.nest_scroll_view);
                                                                                                if (nestedScrollView != null) {
                                                                                                    LinearLayout linearLayout10 = (LinearLayout) view;
                                                                                                    i = R.id.tv_details;
                                                                                                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_details);
                                                                                                    if (textView != null) {
                                                                                                        return new AcCustomTokensBinding(linearLayout10, button, errorEdiTextLayout, errorEdiTextLayout2, errorEdiTextLayout3, trimEditText, trimEditText2, trimEditText3, trimEditText4, trimEditText5, imageView, linearLayout, linearLayout2, linearLayout3, linearLayout4, relativeLayout, relativeLayout2, relativeLayout3, linearLayout5, linearLayout6, linearLayout7, linearLayout8, linearLayout9, nestedScrollView, linearLayout10, textView);
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
