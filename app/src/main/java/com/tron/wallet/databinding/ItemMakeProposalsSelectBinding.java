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
public final class ItemMakeProposalsSelectBinding implements ViewBinding {
    public final ImageView iv;
    public final ImageView ivRow;
    public final LinearLayout llProposalsContent;
    public final LinearLayout llSelect;
    public final RelativeLayout rlSelect;
    public final LinearLayout rlTop;
    private final RelativeLayout rootView;
    public final TextView tvLine;
    public final TextView tvReset;
    public final TextView tvSlect0;
    public final TextView tvSlect1;
    public final TextView tvStatue;
    public final TextView tvTop;
    public final TextView tvTopContent;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemMakeProposalsSelectBinding(RelativeLayout relativeLayout, ImageView imageView, ImageView imageView2, LinearLayout linearLayout, LinearLayout linearLayout2, RelativeLayout relativeLayout2, LinearLayout linearLayout3, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7) {
        this.rootView = relativeLayout;
        this.iv = imageView;
        this.ivRow = imageView2;
        this.llProposalsContent = linearLayout;
        this.llSelect = linearLayout2;
        this.rlSelect = relativeLayout2;
        this.rlTop = linearLayout3;
        this.tvLine = textView;
        this.tvReset = textView2;
        this.tvSlect0 = textView3;
        this.tvSlect1 = textView4;
        this.tvStatue = textView5;
        this.tvTop = textView6;
        this.tvTopContent = textView7;
    }

    public static ItemMakeProposalsSelectBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemMakeProposalsSelectBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_make_proposals_select, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemMakeProposalsSelectBinding bind(View view) {
        int i = R.id.iv;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv);
        if (imageView != null) {
            i = R.id.iv_row;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_row);
            if (imageView2 != null) {
                i = R.id.ll_proposals_content;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_proposals_content);
                if (linearLayout != null) {
                    i = R.id.ll_select;
                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_select);
                    if (linearLayout2 != null) {
                        i = R.id.rl_select;
                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_select);
                        if (relativeLayout != null) {
                            i = R.id.rl_top;
                            LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.rl_top);
                            if (linearLayout3 != null) {
                                i = R.id.tv_line;
                                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_line);
                                if (textView != null) {
                                    i = R.id.tv_reset;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_reset);
                                    if (textView2 != null) {
                                        i = R.id.tv_slect_0;
                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_slect_0);
                                        if (textView3 != null) {
                                            i = R.id.tv_slect_1;
                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_slect_1);
                                            if (textView4 != null) {
                                                i = R.id.tv_statue;
                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_statue);
                                                if (textView5 != null) {
                                                    i = R.id.tv_top;
                                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_top);
                                                    if (textView6 != null) {
                                                        i = R.id.tv_top_content;
                                                        TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_top_content);
                                                        if (textView7 != null) {
                                                            return new ItemMakeProposalsSelectBinding((RelativeLayout) view, imageView, imageView2, linearLayout, linearLayout2, relativeLayout, linearLayout3, textView, textView2, textView3, textView4, textView5, textView6, textView7);
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
