package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.NoNetView;
import com.tronlinkpro.wallet.R;
public final class PopupUnstakeBottomListBinding implements ViewBinding {
    public final Button btnCancelAllUnstake;
    public final ImageView ivCommonRight;
    public final ImageView ivPlaceHolder;
    public final LinearLayout liUnstakeBottom;
    public final NoNetView noDataView;
    public final NoNetView noMatchedView;
    public final RelativeLayout rlTitle;
    public final RelativeLayout root;
    private final RelativeLayout rootView;
    public final RecyclerView rvList;
    public final TextView tvTitle;
    public final TextView tvUnstakeTips;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private PopupUnstakeBottomListBinding(RelativeLayout relativeLayout, Button button, ImageView imageView, ImageView imageView2, LinearLayout linearLayout, NoNetView noNetView, NoNetView noNetView2, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RecyclerView recyclerView, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.btnCancelAllUnstake = button;
        this.ivCommonRight = imageView;
        this.ivPlaceHolder = imageView2;
        this.liUnstakeBottom = linearLayout;
        this.noDataView = noNetView;
        this.noMatchedView = noNetView2;
        this.rlTitle = relativeLayout2;
        this.root = relativeLayout3;
        this.rvList = recyclerView;
        this.tvTitle = textView;
        this.tvUnstakeTips = textView2;
    }

    public static PopupUnstakeBottomListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopupUnstakeBottomListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.popup_unstake_bottom_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopupUnstakeBottomListBinding bind(View view) {
        int i = R.id.btn_cancel_all_unstake;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_cancel_all_unstake);
        if (button != null) {
            i = R.id.iv_common_right;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_common_right);
            if (imageView != null) {
                i = R.id.iv_place_holder;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_place_holder);
                if (imageView2 != null) {
                    i = R.id.li_unstake_bottom;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_unstake_bottom);
                    if (linearLayout != null) {
                        i = R.id.no_data_view;
                        NoNetView noNetView = (NoNetView) ViewBindings.findChildViewById(view, R.id.no_data_view);
                        if (noNetView != null) {
                            i = R.id.no_matched_view;
                            NoNetView noNetView2 = (NoNetView) ViewBindings.findChildViewById(view, R.id.no_matched_view);
                            if (noNetView2 != null) {
                                i = R.id.rl_title;
                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_title);
                                if (relativeLayout != null) {
                                    RelativeLayout relativeLayout2 = (RelativeLayout) view;
                                    i = R.id.rv_list;
                                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rv_list);
                                    if (recyclerView != null) {
                                        i = R.id.tv_title;
                                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                                        if (textView != null) {
                                            i = R.id.tv_unstake_tips;
                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_unstake_tips);
                                            if (textView2 != null) {
                                                return new PopupUnstakeBottomListBinding(relativeLayout2, button, imageView, imageView2, linearLayout, noNetView, noNetView2, relativeLayout, relativeLayout2, recyclerView, textView, textView2);
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
