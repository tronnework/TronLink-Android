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
public final class FragmentUnstakeResultFailBinding implements ViewBinding {
    public final Button btnBackHome;
    public final Button btnRedo;
    public final ImageView ivResultFail;
    public final ImageView ivResultFailAll;
    public final RelativeLayout rlFailAll;
    public final RelativeLayout rlFailPartial;
    private final RelativeLayout rootView;
    public final TextView tvFailInfo;
    public final TextView tvResultFail;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FragmentUnstakeResultFailBinding(RelativeLayout relativeLayout, Button button, Button button2, ImageView imageView, ImageView imageView2, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.btnBackHome = button;
        this.btnRedo = button2;
        this.ivResultFail = imageView;
        this.ivResultFailAll = imageView2;
        this.rlFailAll = relativeLayout2;
        this.rlFailPartial = relativeLayout3;
        this.tvFailInfo = textView;
        this.tvResultFail = textView2;
    }

    public static FragmentUnstakeResultFailBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentUnstakeResultFailBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_unstake_result_fail, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentUnstakeResultFailBinding bind(View view) {
        int i = R.id.btn_back_home;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_back_home);
        if (button != null) {
            i = R.id.btn_redo;
            Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.btn_redo);
            if (button2 != null) {
                i = R.id.iv_result_fail;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_result_fail);
                if (imageView != null) {
                    i = R.id.iv_result_fail_all;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_result_fail_all);
                    if (imageView2 != null) {
                        i = R.id.rl_fail_all;
                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_fail_all);
                        if (relativeLayout != null) {
                            i = R.id.rl_fail_partial;
                            RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_fail_partial);
                            if (relativeLayout2 != null) {
                                i = R.id.tv_fail_info;
                                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_fail_info);
                                if (textView != null) {
                                    i = R.id.tv_result_fail;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_result_fail);
                                    if (textView2 != null) {
                                        return new FragmentUnstakeResultFailBinding((RelativeLayout) view, button, button2, imageView, imageView2, relativeLayout, relativeLayout2, textView, textView2);
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
