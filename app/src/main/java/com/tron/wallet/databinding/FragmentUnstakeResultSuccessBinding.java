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
public final class FragmentUnstakeResultSuccessBinding implements ViewBinding {
    public final Button btnDoneSuccess;
    public final Button btnVoteAgain;
    public final ImageView ivResult;
    public final LinearLayout llResultContainer;
    public final LinearLayout llVoteContainer;
    public final RelativeLayout rlResourceFirst;
    public final RelativeLayout rlResourceSecond;
    private final RelativeLayout rootView;
    public final TextView tvLeftFirst;
    public final TextView tvLeftSecond;
    public final TextView tvResult;
    public final TextView tvRightFirst;
    public final TextView tvRightSecond;
    public final TextView tvVoteInfo;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FragmentUnstakeResultSuccessBinding(RelativeLayout relativeLayout, Button button, Button button2, ImageView imageView, LinearLayout linearLayout, LinearLayout linearLayout2, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6) {
        this.rootView = relativeLayout;
        this.btnDoneSuccess = button;
        this.btnVoteAgain = button2;
        this.ivResult = imageView;
        this.llResultContainer = linearLayout;
        this.llVoteContainer = linearLayout2;
        this.rlResourceFirst = relativeLayout2;
        this.rlResourceSecond = relativeLayout3;
        this.tvLeftFirst = textView;
        this.tvLeftSecond = textView2;
        this.tvResult = textView3;
        this.tvRightFirst = textView4;
        this.tvRightSecond = textView5;
        this.tvVoteInfo = textView6;
    }

    public static FragmentUnstakeResultSuccessBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentUnstakeResultSuccessBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_unstake_result_success, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentUnstakeResultSuccessBinding bind(View view) {
        int i = R.id.btn_done_success;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_done_success);
        if (button != null) {
            i = R.id.btn_vote_again;
            Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.btn_vote_again);
            if (button2 != null) {
                i = R.id.iv_result;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_result);
                if (imageView != null) {
                    i = R.id.ll_result_container;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_result_container);
                    if (linearLayout != null) {
                        i = R.id.ll_vote_container;
                        LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_vote_container);
                        if (linearLayout2 != null) {
                            i = R.id.rl_resource_first;
                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_resource_first);
                            if (relativeLayout != null) {
                                i = R.id.rl_resource_second;
                                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_resource_second);
                                if (relativeLayout2 != null) {
                                    i = R.id.tv_left_first;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_left_first);
                                    if (textView != null) {
                                        i = R.id.tv_left_second;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_left_second);
                                        if (textView2 != null) {
                                            i = R.id.tv_result;
                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_result);
                                            if (textView3 != null) {
                                                i = R.id.tv_right_first;
                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_right_first);
                                                if (textView4 != null) {
                                                    i = R.id.tv_right_second;
                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_right_second);
                                                    if (textView5 != null) {
                                                        i = R.id.tv_vote_info;
                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_vote_info);
                                                        if (textView6 != null) {
                                                            return new FragmentUnstakeResultSuccessBinding((RelativeLayout) view, button, button2, imageView, linearLayout, linearLayout2, relativeLayout, relativeLayout2, textView, textView2, textView3, textView4, textView5, textView6);
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
