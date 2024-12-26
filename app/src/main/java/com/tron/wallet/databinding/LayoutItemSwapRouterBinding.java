package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.flowlayout.TagFlowLayout;
import com.tronlinkpro.wallet.R;
public final class LayoutItemSwapRouterBinding implements ViewBinding {
    public final View divider;
    public final TagFlowLayout flRouter;
    public final ImageView ivSelect;
    private final LinearLayout rootView;
    public final TextView tvOutputAmount;
    public final TextView tvSwapOut;
    public final TextView tvSymbol;
    public final TextView tvTitle;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private LayoutItemSwapRouterBinding(LinearLayout linearLayout, View view, TagFlowLayout tagFlowLayout, ImageView imageView, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        this.rootView = linearLayout;
        this.divider = view;
        this.flRouter = tagFlowLayout;
        this.ivSelect = imageView;
        this.tvOutputAmount = textView;
        this.tvSwapOut = textView2;
        this.tvSymbol = textView3;
        this.tvTitle = textView4;
    }

    public static LayoutItemSwapRouterBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutItemSwapRouterBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_item_swap_router, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutItemSwapRouterBinding bind(View view) {
        int i = R.id.divider;
        View findChildViewById = ViewBindings.findChildViewById(view, R.id.divider);
        if (findChildViewById != null) {
            i = R.id.fl_router;
            TagFlowLayout tagFlowLayout = (TagFlowLayout) ViewBindings.findChildViewById(view, R.id.fl_router);
            if (tagFlowLayout != null) {
                i = R.id.iv_select;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_select);
                if (imageView != null) {
                    i = R.id.tv_output_amount;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_output_amount);
                    if (textView != null) {
                        i = R.id.tv_swap_out;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_swap_out);
                        if (textView2 != null) {
                            i = R.id.tv_symbol;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_symbol);
                            if (textView3 != null) {
                                i = R.id.tv_title;
                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                                if (textView4 != null) {
                                    return new LayoutItemSwapRouterBinding((LinearLayout) view, findChildViewById, tagFlowLayout, imageView, textView, textView2, textView3, textView4);
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
