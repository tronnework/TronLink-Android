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
public final class FgVoteIncomePendingBinding implements ViewBinding {
    public final Button btnBackToVoteHome;
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

    private FgVoteIncomePendingBinding(RelativeLayout relativeLayout, Button button, Button button2, Button button3, ImageView imageView, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.btnBackToVoteHome = button;
        this.btnDone = button2;
        this.btnTransactionInfo = button3;
        this.ivResult = imageView;
        this.main = relativeLayout2;
        this.rlFail = relativeLayout3;
        this.rlSuccess = relativeLayout4;
        this.tvResult = textView;
        this.tvResultHint = textView2;
    }

    public static FgVoteIncomePendingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FgVoteIncomePendingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fg_vote_income_pending, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FgVoteIncomePendingBinding bind(View view) {
        int i = R.id.btn_back_to_vote_home;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_back_to_vote_home);
        if (button != null) {
            i = R.id.btn_done;
            Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.btn_done);
            if (button2 != null) {
                i = R.id.btn_transaction_info;
                Button button3 = (Button) ViewBindings.findChildViewById(view, R.id.btn_transaction_info);
                if (button3 != null) {
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
                                        return new FgVoteIncomePendingBinding(relativeLayout, button, button2, button3, imageView, relativeLayout, relativeLayout2, relativeLayout3, textView, textView2);
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
