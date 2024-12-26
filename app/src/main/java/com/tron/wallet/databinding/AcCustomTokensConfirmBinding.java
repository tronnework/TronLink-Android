package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.CustomTokenNoFunctionView;
import com.tronlinkpro.wallet.R;
public final class AcCustomTokensConfirmBinding implements ViewBinding {
    public final Button confirm;
    public final CustomTokenNoFunctionView llTips;
    public final ConstraintLayout pairedRoot;
    public final ItemAssetsListBinding rlTokenInfo;
    private final ConstraintLayout rootView;
    public final TextView tvTip;
    public final TextView tvTipError;

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    private AcCustomTokensConfirmBinding(ConstraintLayout constraintLayout, Button button, CustomTokenNoFunctionView customTokenNoFunctionView, ConstraintLayout constraintLayout2, ItemAssetsListBinding itemAssetsListBinding, TextView textView, TextView textView2) {
        this.rootView = constraintLayout;
        this.confirm = button;
        this.llTips = customTokenNoFunctionView;
        this.pairedRoot = constraintLayout2;
        this.rlTokenInfo = itemAssetsListBinding;
        this.tvTip = textView;
        this.tvTipError = textView2;
    }

    public static AcCustomTokensConfirmBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcCustomTokensConfirmBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_custom_tokens_confirm, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcCustomTokensConfirmBinding bind(View view) {
        int i = R.id.confirm;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.confirm);
        if (button != null) {
            i = R.id.ll_tips;
            CustomTokenNoFunctionView customTokenNoFunctionView = (CustomTokenNoFunctionView) ViewBindings.findChildViewById(view, R.id.ll_tips);
            if (customTokenNoFunctionView != null) {
                ConstraintLayout constraintLayout = (ConstraintLayout) view;
                i = R.id.rl_token_info;
                View findChildViewById = ViewBindings.findChildViewById(view, R.id.rl_token_info);
                if (findChildViewById != null) {
                    ItemAssetsListBinding bind = ItemAssetsListBinding.bind(findChildViewById);
                    i = R.id.tv_tip;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_tip);
                    if (textView != null) {
                        i = R.id.tv_tip_error;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_tip_error);
                        if (textView2 != null) {
                            return new AcCustomTokensConfirmBinding(constraintLayout, button, customTokenNoFunctionView, constraintLayout, bind, textView, textView2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
