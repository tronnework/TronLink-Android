package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.ErrorEdiTextLayout;
import com.tron.wallet.common.components.SignatureProgressView;
import com.tronlinkpro.wallet.R;
public final class FgConfirmMultisignBinding implements ViewBinding {
    public final ErrorEdiTextLayout errorEtInvalidTime;
    public final ImageView ivArrow;
    public final SignatureProgressView progressSignature;
    public final RecyclerView rlAddressWeight;
    public final RelativeLayout rlBottomNext;
    public final RecyclerView rlPermissionList;
    public final RelativeLayout rlPermissionSelect;
    private final RelativeLayout rootView;
    public final TextView tvBottomNext;
    public final TextView tvFailureTimeTitle;
    public final TextView tvH;
    public final EditText tvInvalidTime;
    public final TextView tvPermissionTitle;
    public final TextView tvSelectedName;
    public final TextView tvSignatureValue;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FgConfirmMultisignBinding(RelativeLayout relativeLayout, ErrorEdiTextLayout errorEdiTextLayout, ImageView imageView, SignatureProgressView signatureProgressView, RecyclerView recyclerView, RelativeLayout relativeLayout2, RecyclerView recyclerView2, RelativeLayout relativeLayout3, TextView textView, TextView textView2, TextView textView3, EditText editText, TextView textView4, TextView textView5, TextView textView6) {
        this.rootView = relativeLayout;
        this.errorEtInvalidTime = errorEdiTextLayout;
        this.ivArrow = imageView;
        this.progressSignature = signatureProgressView;
        this.rlAddressWeight = recyclerView;
        this.rlBottomNext = relativeLayout2;
        this.rlPermissionList = recyclerView2;
        this.rlPermissionSelect = relativeLayout3;
        this.tvBottomNext = textView;
        this.tvFailureTimeTitle = textView2;
        this.tvH = textView3;
        this.tvInvalidTime = editText;
        this.tvPermissionTitle = textView4;
        this.tvSelectedName = textView5;
        this.tvSignatureValue = textView6;
    }

    public static FgConfirmMultisignBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FgConfirmMultisignBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fg_confirm_multisign, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FgConfirmMultisignBinding bind(View view) {
        int i = R.id.errorEt_invalid_time;
        ErrorEdiTextLayout errorEdiTextLayout = (ErrorEdiTextLayout) ViewBindings.findChildViewById(view, R.id.errorEt_invalid_time);
        if (errorEdiTextLayout != null) {
            i = R.id.iv_arrow;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_arrow);
            if (imageView != null) {
                i = R.id.progress_signature;
                SignatureProgressView signatureProgressView = (SignatureProgressView) ViewBindings.findChildViewById(view, R.id.progress_signature);
                if (signatureProgressView != null) {
                    i = R.id.rl_address_weight;
                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rl_address_weight);
                    if (recyclerView != null) {
                        i = R.id.rl_bottom_next;
                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_bottom_next);
                        if (relativeLayout != null) {
                            i = R.id.rl_permission_list;
                            RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rl_permission_list);
                            if (recyclerView2 != null) {
                                i = R.id.rl_permission_select;
                                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_permission_select);
                                if (relativeLayout2 != null) {
                                    i = R.id.tv_bottom_next;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_bottom_next);
                                    if (textView != null) {
                                        i = R.id.tv_failure_time_title;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_failure_time_title);
                                        if (textView2 != null) {
                                            i = R.id.tv_h;
                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_h);
                                            if (textView3 != null) {
                                                i = R.id.tv_invalid_time;
                                                EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.tv_invalid_time);
                                                if (editText != null) {
                                                    i = R.id.tv_permission_title;
                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_permission_title);
                                                    if (textView4 != null) {
                                                        i = R.id.tv_selected_name;
                                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_selected_name);
                                                        if (textView5 != null) {
                                                            i = R.id.tv_signature_value;
                                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_signature_value);
                                                            if (textView6 != null) {
                                                                return new FgConfirmMultisignBinding((RelativeLayout) view, errorEdiTextLayout, imageView, signatureProgressView, recyclerView, relativeLayout, recyclerView2, relativeLayout2, textView, textView2, textView3, editText, textView4, textView5, textView6);
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
