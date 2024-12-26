package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class PopVoteSelectBinding implements ViewBinding {
    public final ImageView ivAll;
    public final ImageView ivMe;
    public final RelativeLayout rlAll;
    public final RelativeLayout rlMe;
    private final LinearLayout rootView;
    public final TextView tvAll;
    public final TextView tvMe;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private PopVoteSelectBinding(LinearLayout linearLayout, ImageView imageView, ImageView imageView2, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, TextView textView, TextView textView2) {
        this.rootView = linearLayout;
        this.ivAll = imageView;
        this.ivMe = imageView2;
        this.rlAll = relativeLayout;
        this.rlMe = relativeLayout2;
        this.tvAll = textView;
        this.tvMe = textView2;
    }

    public static PopVoteSelectBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopVoteSelectBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.pop_vote_select, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopVoteSelectBinding bind(View view) {
        int i = R.id.iv_all;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_all);
        if (imageView != null) {
            i = R.id.iv_me;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_me);
            if (imageView2 != null) {
                i = R.id.rl_all;
                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_all);
                if (relativeLayout != null) {
                    i = R.id.rl_me;
                    RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_me);
                    if (relativeLayout2 != null) {
                        i = R.id.tv_all;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_all);
                        if (textView != null) {
                            i = R.id.tv_me;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_me);
                            if (textView2 != null) {
                                return new PopVoteSelectBinding((LinearLayout) view, imageView, imageView2, relativeLayout, relativeLayout2, textView, textView2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
