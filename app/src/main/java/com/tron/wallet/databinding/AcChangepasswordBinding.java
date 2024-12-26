package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.ErrorEdiTextLayout;
import com.tronlinkpro.wallet.R;
public final class AcChangepasswordBinding implements ViewBinding {
    public final ErrorEdiTextLayout errorConfirmPassword;
    public final ErrorEdiTextLayout errorNewpassword;
    public final ErrorEdiTextLayout errorOldpassword;
    public final EditText etConfirmPassword;
    public final EditText etNewpassword;
    public final EditText etOldpassword;
    public final ImageView ivConfirm;
    public final Button ok;
    private final LinearLayout rootView;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private AcChangepasswordBinding(LinearLayout linearLayout, ErrorEdiTextLayout errorEdiTextLayout, ErrorEdiTextLayout errorEdiTextLayout2, ErrorEdiTextLayout errorEdiTextLayout3, EditText editText, EditText editText2, EditText editText3, ImageView imageView, Button button) {
        this.rootView = linearLayout;
        this.errorConfirmPassword = errorEdiTextLayout;
        this.errorNewpassword = errorEdiTextLayout2;
        this.errorOldpassword = errorEdiTextLayout3;
        this.etConfirmPassword = editText;
        this.etNewpassword = editText2;
        this.etOldpassword = editText3;
        this.ivConfirm = imageView;
        this.ok = button;
    }

    public static AcChangepasswordBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcChangepasswordBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_changepassword, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcChangepasswordBinding bind(View view) {
        int i = R.id.error_confirm_password;
        ErrorEdiTextLayout errorEdiTextLayout = (ErrorEdiTextLayout) ViewBindings.findChildViewById(view, R.id.error_confirm_password);
        if (errorEdiTextLayout != null) {
            i = R.id.error_newpassword;
            ErrorEdiTextLayout errorEdiTextLayout2 = (ErrorEdiTextLayout) ViewBindings.findChildViewById(view, R.id.error_newpassword);
            if (errorEdiTextLayout2 != null) {
                i = R.id.error_oldpassword;
                ErrorEdiTextLayout errorEdiTextLayout3 = (ErrorEdiTextLayout) ViewBindings.findChildViewById(view, R.id.error_oldpassword);
                if (errorEdiTextLayout3 != null) {
                    i = R.id.et_confirm_password;
                    EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.et_confirm_password);
                    if (editText != null) {
                        i = R.id.et_newpassword;
                        EditText editText2 = (EditText) ViewBindings.findChildViewById(view, R.id.et_newpassword);
                        if (editText2 != null) {
                            i = R.id.et_oldpassword;
                            EditText editText3 = (EditText) ViewBindings.findChildViewById(view, R.id.et_oldpassword);
                            if (editText3 != null) {
                                i = R.id.iv_confirm;
                                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_confirm);
                                if (imageView != null) {
                                    i = R.id.ok;
                                    Button button = (Button) ViewBindings.findChildViewById(view, R.id.ok);
                                    if (button != null) {
                                        return new AcChangepasswordBinding((LinearLayout) view, errorEdiTextLayout, errorEdiTextLayout2, errorEdiTextLayout3, editText, editText2, editText3, imageView, button);
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
