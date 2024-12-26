package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.CommonTitleDescriptionEditView;
import com.tronlinkpro.wallet.R;
public final class ContentInputWalletBinding implements ViewBinding {
    public final CheckBox cbFour;
    public final CheckBox cbOne;
    public final CheckBox cbThree;
    public final CheckBox cbTwo;
    public final CommonTitleDescriptionEditView inputWalletName;
    public final CommonTitleDescriptionEditView inputWalletPassword;
    public final CommonTitleDescriptionEditView inputWalletPasswordAgain;
    public final LinearLayout leftBottom;
    public final LinearLayout leftTop;
    public final LinearLayout llNameError;
    public final LinearLayout llPasswordAgainError;
    public final LinearLayout rightTop;
    private final LinearLayout rootView;
    public final TextView tvFour;
    public final TextView tvNameError;
    public final TextView tvOne;
    public final TextView tvPasswordAgainError;
    public final TextView tvThree;
    public final TextView tvTwo;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private ContentInputWalletBinding(LinearLayout linearLayout, CheckBox checkBox, CheckBox checkBox2, CheckBox checkBox3, CheckBox checkBox4, CommonTitleDescriptionEditView commonTitleDescriptionEditView, CommonTitleDescriptionEditView commonTitleDescriptionEditView2, CommonTitleDescriptionEditView commonTitleDescriptionEditView3, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, LinearLayout linearLayout5, LinearLayout linearLayout6, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6) {
        this.rootView = linearLayout;
        this.cbFour = checkBox;
        this.cbOne = checkBox2;
        this.cbThree = checkBox3;
        this.cbTwo = checkBox4;
        this.inputWalletName = commonTitleDescriptionEditView;
        this.inputWalletPassword = commonTitleDescriptionEditView2;
        this.inputWalletPasswordAgain = commonTitleDescriptionEditView3;
        this.leftBottom = linearLayout2;
        this.leftTop = linearLayout3;
        this.llNameError = linearLayout4;
        this.llPasswordAgainError = linearLayout5;
        this.rightTop = linearLayout6;
        this.tvFour = textView;
        this.tvNameError = textView2;
        this.tvOne = textView3;
        this.tvPasswordAgainError = textView4;
        this.tvThree = textView5;
        this.tvTwo = textView6;
    }

    public static ContentInputWalletBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ContentInputWalletBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.content_input_wallet, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ContentInputWalletBinding bind(View view) {
        int i = R.id.cb_four;
        CheckBox checkBox = (CheckBox) ViewBindings.findChildViewById(view, R.id.cb_four);
        if (checkBox != null) {
            i = R.id.cb_one;
            CheckBox checkBox2 = (CheckBox) ViewBindings.findChildViewById(view, R.id.cb_one);
            if (checkBox2 != null) {
                i = R.id.cb_three;
                CheckBox checkBox3 = (CheckBox) ViewBindings.findChildViewById(view, R.id.cb_three);
                if (checkBox3 != null) {
                    i = R.id.cb_two;
                    CheckBox checkBox4 = (CheckBox) ViewBindings.findChildViewById(view, R.id.cb_two);
                    if (checkBox4 != null) {
                        i = R.id.input_wallet_name;
                        CommonTitleDescriptionEditView commonTitleDescriptionEditView = (CommonTitleDescriptionEditView) ViewBindings.findChildViewById(view, R.id.input_wallet_name);
                        if (commonTitleDescriptionEditView != null) {
                            i = R.id.input_wallet_password;
                            CommonTitleDescriptionEditView commonTitleDescriptionEditView2 = (CommonTitleDescriptionEditView) ViewBindings.findChildViewById(view, R.id.input_wallet_password);
                            if (commonTitleDescriptionEditView2 != null) {
                                i = R.id.input_wallet_password_again;
                                CommonTitleDescriptionEditView commonTitleDescriptionEditView3 = (CommonTitleDescriptionEditView) ViewBindings.findChildViewById(view, R.id.input_wallet_password_again);
                                if (commonTitleDescriptionEditView3 != null) {
                                    i = R.id.left_bottom;
                                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.left_bottom);
                                    if (linearLayout != null) {
                                        i = R.id.left_top;
                                        LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.left_top);
                                        if (linearLayout2 != null) {
                                            i = R.id.ll_name_error;
                                            LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_name_error);
                                            if (linearLayout3 != null) {
                                                i = R.id.ll_password_again_error;
                                                LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_password_again_error);
                                                if (linearLayout4 != null) {
                                                    i = R.id.right_top;
                                                    LinearLayout linearLayout5 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.right_top);
                                                    if (linearLayout5 != null) {
                                                        i = R.id.tv_four;
                                                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_four);
                                                        if (textView != null) {
                                                            i = R.id.tv_name_error;
                                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name_error);
                                                            if (textView2 != null) {
                                                                i = R.id.tv_one;
                                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_one);
                                                                if (textView3 != null) {
                                                                    i = R.id.tv_password_again_error;
                                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_password_again_error);
                                                                    if (textView4 != null) {
                                                                        i = R.id.tv_three;
                                                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_three);
                                                                        if (textView5 != null) {
                                                                            i = R.id.tv_two;
                                                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_two);
                                                                            if (textView6 != null) {
                                                                                return new ContentInputWalletBinding((LinearLayout) view, checkBox, checkBox2, checkBox3, checkBox4, commonTitleDescriptionEditView, commonTitleDescriptionEditView2, commonTitleDescriptionEditView3, linearLayout, linearLayout2, linearLayout3, linearLayout4, linearLayout5, textView, textView2, textView3, textView4, textView5, textView6);
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
