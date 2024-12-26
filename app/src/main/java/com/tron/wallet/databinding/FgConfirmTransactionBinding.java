package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.ErrorEdiTextLayout;
import com.tronlinkpro.wallet.R;
public final class FgConfirmTransactionBinding implements ViewBinding {
    public final Button btSend;
    public final ErrorEdiTextLayout eetContent;
    public final EditText etNewPassword;
    public final FrameLayout flMain;
    public final LinearLayout ivCloseTwo;
    public final RelativeLayout rlBottom;
    public final RelativeLayout rlPassword;
    public final RelativeLayout rlTopTwo;
    public final RelativeLayout root;
    private final RelativeLayout rootView;
    public final Button tvCancle;
    public final TextView tvTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FgConfirmTransactionBinding(RelativeLayout relativeLayout, Button button, ErrorEdiTextLayout errorEdiTextLayout, EditText editText, FrameLayout frameLayout, LinearLayout linearLayout, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, RelativeLayout relativeLayout5, Button button2, TextView textView) {
        this.rootView = relativeLayout;
        this.btSend = button;
        this.eetContent = errorEdiTextLayout;
        this.etNewPassword = editText;
        this.flMain = frameLayout;
        this.ivCloseTwo = linearLayout;
        this.rlBottom = relativeLayout2;
        this.rlPassword = relativeLayout3;
        this.rlTopTwo = relativeLayout4;
        this.root = relativeLayout5;
        this.tvCancle = button2;
        this.tvTitle = textView;
    }

    public static FgConfirmTransactionBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FgConfirmTransactionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fg_confirm_transaction, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FgConfirmTransactionBinding bind(View view) {
        int i = R.id.bt_send;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_send);
        if (button != null) {
            i = R.id.eet_content;
            ErrorEdiTextLayout errorEdiTextLayout = (ErrorEdiTextLayout) ViewBindings.findChildViewById(view, R.id.eet_content);
            if (errorEdiTextLayout != null) {
                i = R.id.et_new_password;
                EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.et_new_password);
                if (editText != null) {
                    i = R.id.fl_main;
                    FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.fl_main);
                    if (frameLayout != null) {
                        i = R.id.iv_close_two;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.iv_close_two);
                        if (linearLayout != null) {
                            i = R.id.rl_bottom;
                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_bottom);
                            if (relativeLayout != null) {
                                i = R.id.rl_password;
                                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_password);
                                if (relativeLayout2 != null) {
                                    i = R.id.rl_top_two;
                                    RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_top_two);
                                    if (relativeLayout3 != null) {
                                        RelativeLayout relativeLayout4 = (RelativeLayout) view;
                                        i = R.id.tv_cancle;
                                        Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.tv_cancle);
                                        if (button2 != null) {
                                            i = R.id.tv_title;
                                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                                            if (textView != null) {
                                                return new FgConfirmTransactionBinding(relativeLayout4, button, errorEdiTextLayout, editText, frameLayout, linearLayout, relativeLayout, relativeLayout2, relativeLayout3, relativeLayout4, button2, textView);
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
