package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.flowlayout.TagFlowLayout;
import com.tronlinkpro.wallet.R;
public final class AcSelectPermissionBinding implements ViewBinding {
    public final Button btConfirm;
    public final TagFlowLayout flAccountPermission;
    public final TagFlowLayout flBalancePermission;
    public final TagFlowLayout flBancorPermission;
    public final TagFlowLayout flContractPermission;
    public final TagFlowLayout flRepresentativesPermission;
    public final TagFlowLayout flResourcePermission;
    public final TagFlowLayout flSelectedPermission;
    public final TagFlowLayout flTrc10Permission;
    private final RelativeLayout rootView;
    public final TextView tvAccountPermission;
    public final TextView tvBalancePermission;
    public final TextView tvBancorPermission;
    public final TextView tvContractPermission;
    public final TextView tvRepresentativesPermission;
    public final TextView tvResourcePermission;
    public final TextView tvTrc10Permission;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcSelectPermissionBinding(RelativeLayout relativeLayout, Button button, TagFlowLayout tagFlowLayout, TagFlowLayout tagFlowLayout2, TagFlowLayout tagFlowLayout3, TagFlowLayout tagFlowLayout4, TagFlowLayout tagFlowLayout5, TagFlowLayout tagFlowLayout6, TagFlowLayout tagFlowLayout7, TagFlowLayout tagFlowLayout8, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7) {
        this.rootView = relativeLayout;
        this.btConfirm = button;
        this.flAccountPermission = tagFlowLayout;
        this.flBalancePermission = tagFlowLayout2;
        this.flBancorPermission = tagFlowLayout3;
        this.flContractPermission = tagFlowLayout4;
        this.flRepresentativesPermission = tagFlowLayout5;
        this.flResourcePermission = tagFlowLayout6;
        this.flSelectedPermission = tagFlowLayout7;
        this.flTrc10Permission = tagFlowLayout8;
        this.tvAccountPermission = textView;
        this.tvBalancePermission = textView2;
        this.tvBancorPermission = textView3;
        this.tvContractPermission = textView4;
        this.tvRepresentativesPermission = textView5;
        this.tvResourcePermission = textView6;
        this.tvTrc10Permission = textView7;
    }

    public static AcSelectPermissionBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcSelectPermissionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_select_permission, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcSelectPermissionBinding bind(View view) {
        int i = R.id.bt_confirm;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_confirm);
        if (button != null) {
            i = R.id.fl_account_permission;
            TagFlowLayout tagFlowLayout = (TagFlowLayout) ViewBindings.findChildViewById(view, R.id.fl_account_permission);
            if (tagFlowLayout != null) {
                i = R.id.fl_balance_permission;
                TagFlowLayout tagFlowLayout2 = (TagFlowLayout) ViewBindings.findChildViewById(view, R.id.fl_balance_permission);
                if (tagFlowLayout2 != null) {
                    i = R.id.fl_bancor_permission;
                    TagFlowLayout tagFlowLayout3 = (TagFlowLayout) ViewBindings.findChildViewById(view, R.id.fl_bancor_permission);
                    if (tagFlowLayout3 != null) {
                        i = R.id.fl_contract_permission;
                        TagFlowLayout tagFlowLayout4 = (TagFlowLayout) ViewBindings.findChildViewById(view, R.id.fl_contract_permission);
                        if (tagFlowLayout4 != null) {
                            i = R.id.fl_representatives_permission;
                            TagFlowLayout tagFlowLayout5 = (TagFlowLayout) ViewBindings.findChildViewById(view, R.id.fl_representatives_permission);
                            if (tagFlowLayout5 != null) {
                                i = R.id.fl_resource_permission;
                                TagFlowLayout tagFlowLayout6 = (TagFlowLayout) ViewBindings.findChildViewById(view, R.id.fl_resource_permission);
                                if (tagFlowLayout6 != null) {
                                    i = R.id.fl_selected_permission;
                                    TagFlowLayout tagFlowLayout7 = (TagFlowLayout) ViewBindings.findChildViewById(view, R.id.fl_selected_permission);
                                    if (tagFlowLayout7 != null) {
                                        i = R.id.fl_trc10_permission;
                                        TagFlowLayout tagFlowLayout8 = (TagFlowLayout) ViewBindings.findChildViewById(view, R.id.fl_trc10_permission);
                                        if (tagFlowLayout8 != null) {
                                            i = R.id.tv_account_permission;
                                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_account_permission);
                                            if (textView != null) {
                                                i = R.id.tv_balance_permission;
                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_balance_permission);
                                                if (textView2 != null) {
                                                    i = R.id.tv_bancor_permission;
                                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_bancor_permission);
                                                    if (textView3 != null) {
                                                        i = R.id.tv_contract_permission;
                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_contract_permission);
                                                        if (textView4 != null) {
                                                            i = R.id.tv_representatives_permission;
                                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_representatives_permission);
                                                            if (textView5 != null) {
                                                                i = R.id.tv_resource_permission;
                                                                TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_resource_permission);
                                                                if (textView6 != null) {
                                                                    i = R.id.tv_trc10_permission;
                                                                    TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_trc10_permission);
                                                                    if (textView7 != null) {
                                                                        return new AcSelectPermissionBinding((RelativeLayout) view, button, tagFlowLayout, tagFlowLayout2, tagFlowLayout3, tagFlowLayout4, tagFlowLayout5, tagFlowLayout6, tagFlowLayout7, tagFlowLayout8, textView, textView2, textView3, textView4, textView5, textView6, textView7);
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
