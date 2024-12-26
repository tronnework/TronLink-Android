package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class PopupSelectWalletSortTypeBinding implements ViewBinding {
    public final Button btConfirm;
    public final TextView btnSortClass;
    public final TextView btnSortTypeAll;
    public final TextView btnSortTypeDefault;
    public final TextView btnSortValue;
    public final ImageView ivBack;
    public final LinearLayout llSortExpend;
    public final ConstraintLayout llSortType;
    public final RelativeLayout rlTitle;
    public final RelativeLayout root;
    private final RelativeLayout rootView;
    public final TextView sortTips;
    public final TextView tvSortType;
    public final TextView tvTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private PopupSelectWalletSortTypeBinding(RelativeLayout relativeLayout, Button button, TextView textView, TextView textView2, TextView textView3, TextView textView4, ImageView imageView, LinearLayout linearLayout, ConstraintLayout constraintLayout, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, TextView textView5, TextView textView6, TextView textView7) {
        this.rootView = relativeLayout;
        this.btConfirm = button;
        this.btnSortClass = textView;
        this.btnSortTypeAll = textView2;
        this.btnSortTypeDefault = textView3;
        this.btnSortValue = textView4;
        this.ivBack = imageView;
        this.llSortExpend = linearLayout;
        this.llSortType = constraintLayout;
        this.rlTitle = relativeLayout2;
        this.root = relativeLayout3;
        this.sortTips = textView5;
        this.tvSortType = textView6;
        this.tvTitle = textView7;
    }

    public static PopupSelectWalletSortTypeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopupSelectWalletSortTypeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.popup_select_wallet_sort_type, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopupSelectWalletSortTypeBinding bind(View view) {
        int i = R.id.bt_confirm;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_confirm);
        if (button != null) {
            i = R.id.btn_sort_class;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.btn_sort_class);
            if (textView != null) {
                i = R.id.btn_sort_type_all;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.btn_sort_type_all);
                if (textView2 != null) {
                    i = R.id.btn_sort_type_default;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.btn_sort_type_default);
                    if (textView3 != null) {
                        i = R.id.btn_sort_value;
                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.btn_sort_value);
                        if (textView4 != null) {
                            i = R.id.iv_back;
                            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_back);
                            if (imageView != null) {
                                i = R.id.ll_sort_expend;
                                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_sort_expend);
                                if (linearLayout != null) {
                                    i = R.id.ll_sort_type;
                                    ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.ll_sort_type);
                                    if (constraintLayout != null) {
                                        i = R.id.rl_title;
                                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_title);
                                        if (relativeLayout != null) {
                                            RelativeLayout relativeLayout2 = (RelativeLayout) view;
                                            i = R.id.sort_tips;
                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.sort_tips);
                                            if (textView5 != null) {
                                                i = R.id.tv_sort_type;
                                                TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_sort_type);
                                                if (textView6 != null) {
                                                    i = R.id.tv_title;
                                                    TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                                                    if (textView7 != null) {
                                                        return new PopupSelectWalletSortTypeBinding(relativeLayout2, button, textView, textView2, textView3, textView4, imageView, linearLayout, constraintLayout, relativeLayout, relativeLayout2, textView5, textView6, textView7);
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
