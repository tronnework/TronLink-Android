package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class FgTransferPendingBinding implements ViewBinding {
    public final Button btnAgain;
    public final Button btnBackToHome;
    public final Button btnDone;
    public final Button btnTransactionInfo;
    public final ImageView ivResult;
    public final RelativeLayout main;
    public final RelativeLayout rlFail;
    public final RelativeLayout rlSuccess;
    private final RelativeLayout rootView;
    public final TextView tvResult;
    public final TextView tvResultHint;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FgTransferPendingBinding(RelativeLayout relativeLayout, Button button, Button button2, Button button3, Button button4, ImageView imageView, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.btnAgain = button;
        this.btnBackToHome = button2;
        this.btnDone = button3;
        this.btnTransactionInfo = button4;
        this.ivResult = imageView;
        this.main = relativeLayout2;
        this.rlFail = relativeLayout3;
        this.rlSuccess = relativeLayout4;
        this.tvResult = textView;
        this.tvResultHint = textView2;
    }

    public static FgTransferPendingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FgTransferPendingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fg_transfer_pending, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FgTransferPendingBinding bind(View view) {
        int i = R.id.btn_again;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_again);
        if (button != null) {
            i = R.id.btn_back_to_home;
            Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.btn_back_to_home);
            if (button2 != null) {
                i = R.id.btn_done;
                Button button3 = (Button) ViewBindings.findChildViewById(view, R.id.btn_done);
                if (button3 != null) {
                    i = R.id.btn_transaction_info;
                    Button button4 = (Button) ViewBindings.findChildViewById(view, R.id.btn_transaction_info);
                    if (button4 != null) {
                        i = R.id.iv_result;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_result);
                        if (imageView != null) {
                            RelativeLayout relativeLayout = (RelativeLayout) view;
                            i = R.id.rl_fail;
                            RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_fail);
                            if (relativeLayout2 != null) {
                                i = R.id.rl_success;
                                RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_success);
                                if (relativeLayout3 != null) {
                                    i = R.id.tv_result;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_result);
                                    if (textView != null) {
                                        i = R.id.tv_result_hint;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_result_hint);
                                        if (textView2 != null) {
                                            return new FgTransferPendingBinding(relativeLayout, button, button2, button3, button4, imageView, relativeLayout, relativeLayout2, relativeLayout3, textView, textView2);
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
