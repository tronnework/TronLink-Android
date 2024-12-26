package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class AcUnstakeV2BottomBinding implements ViewBinding {
    public final ImageView arrowBottom;
    public final ImageView arrowTop;
    private final RelativeLayout rootView;
    public final TextView tvResDesc;
    public final TextView tvResNew;
    public final TextView tvResOld;
    public final TextView tvVoteNew;
    public final TextView tvVoteOld;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcUnstakeV2BottomBinding(RelativeLayout relativeLayout, ImageView imageView, ImageView imageView2, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5) {
        this.rootView = relativeLayout;
        this.arrowBottom = imageView;
        this.arrowTop = imageView2;
        this.tvResDesc = textView;
        this.tvResNew = textView2;
        this.tvResOld = textView3;
        this.tvVoteNew = textView4;
        this.tvVoteOld = textView5;
    }

    public static AcUnstakeV2BottomBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcUnstakeV2BottomBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_unstake_v2_bottom, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcUnstakeV2BottomBinding bind(View view) {
        int i = R.id.arrow_bottom;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.arrow_bottom);
        if (imageView != null) {
            i = R.id.arrow_top;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.arrow_top);
            if (imageView2 != null) {
                i = R.id.tv_res_desc;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_res_desc);
                if (textView != null) {
                    i = R.id.tv_res_new;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_res_new);
                    if (textView2 != null) {
                        i = R.id.tv_res_old;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_res_old);
                        if (textView3 != null) {
                            i = R.id.tv_vote_new;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_vote_new);
                            if (textView4 != null) {
                                i = R.id.tv_vote_old;
                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_vote_old);
                                if (textView5 != null) {
                                    return new AcUnstakeV2BottomBinding((RelativeLayout) view, imageView, imageView2, textView, textView2, textView3, textView4, textView5);
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
