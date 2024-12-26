package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class FgVoteCancelPendingBinding implements ViewBinding {
    public final Button btnAgain;
    public final Button btnBackToVoteHome;
    public final Button btnDone;
    public final Button btnVoteAgain;
    public final ImageView ivResult;
    public final LinearLayout llVoteContainer;
    public final RelativeLayout main;
    public final RelativeLayout rlFail;
    public final RelativeLayout rlSuccess;
    private final RelativeLayout rootView;
    public final TextView tvResult;
    public final TextView tvResultHint;
    public final TextView tvVoteInfo;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FgVoteCancelPendingBinding(RelativeLayout relativeLayout, Button button, Button button2, Button button3, Button button4, ImageView imageView, LinearLayout linearLayout, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = relativeLayout;
        this.btnAgain = button;
        this.btnBackToVoteHome = button2;
        this.btnDone = button3;
        this.btnVoteAgain = button4;
        this.ivResult = imageView;
        this.llVoteContainer = linearLayout;
        this.main = relativeLayout2;
        this.rlFail = relativeLayout3;
        this.rlSuccess = relativeLayout4;
        this.tvResult = textView;
        this.tvResultHint = textView2;
        this.tvVoteInfo = textView3;
    }

    public static FgVoteCancelPendingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FgVoteCancelPendingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fg_vote_cancel_pending, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FgVoteCancelPendingBinding bind(View view) {
        int i = R.id.btn_again;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_again);
        if (button != null) {
            i = R.id.btn_back_to_vote_home;
            Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.btn_back_to_vote_home);
            if (button2 != null) {
                i = R.id.btn_done;
                Button button3 = (Button) ViewBindings.findChildViewById(view, R.id.btn_done);
                if (button3 != null) {
                    i = R.id.btn_vote_again;
                    Button button4 = (Button) ViewBindings.findChildViewById(view, R.id.btn_vote_again);
                    if (button4 != null) {
                        i = R.id.iv_result;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_result);
                        if (imageView != null) {
                            i = R.id.ll_vote_container;
                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_vote_container);
                            if (linearLayout != null) {
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
                                                i = R.id.tv_vote_info;
                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_vote_info);
                                                if (textView3 != null) {
                                                    return new FgVoteCancelPendingBinding(relativeLayout, button, button2, button3, button4, imageView, linearLayout, relativeLayout, relativeLayout2, relativeLayout3, textView, textView2, textView3);
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
