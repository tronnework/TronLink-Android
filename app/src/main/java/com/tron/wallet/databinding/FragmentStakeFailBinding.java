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
public final class FragmentStakeFailBinding implements ViewBinding {
    public final Button btnAgain;
    public final Button btnBackHome;
    public final ImageView ivResultFail;
    public final RelativeLayout rlFailPartial;
    private final RelativeLayout rootView;
    public final TextView tvFailInfo;
    public final TextView tvResultFail;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FragmentStakeFailBinding(RelativeLayout relativeLayout, Button button, Button button2, ImageView imageView, RelativeLayout relativeLayout2, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.btnAgain = button;
        this.btnBackHome = button2;
        this.ivResultFail = imageView;
        this.rlFailPartial = relativeLayout2;
        this.tvFailInfo = textView;
        this.tvResultFail = textView2;
    }

    public static FragmentStakeFailBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentStakeFailBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_stake_fail, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentStakeFailBinding bind(View view) {
        int i = R.id.btn_again;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_again);
        if (button != null) {
            i = R.id.btn_back_home;
            Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.btn_back_home);
            if (button2 != null) {
                i = R.id.iv_result_fail;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_result_fail);
                if (imageView != null) {
                    i = R.id.rl_fail_partial;
                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_fail_partial);
                    if (relativeLayout != null) {
                        i = R.id.tv_fail_info;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_fail_info);
                        if (textView != null) {
                            i = R.id.tv_result_fail;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_result_fail);
                            if (textView2 != null) {
                                return new FragmentStakeFailBinding((RelativeLayout) view, button, button2, imageView, relativeLayout, textView, textView2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
