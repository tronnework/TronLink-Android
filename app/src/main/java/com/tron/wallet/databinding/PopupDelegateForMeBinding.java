package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.NoNetView;
import com.tronlinkpro.wallet.R;
public final class PopupDelegateForMeBinding implements ViewBinding {
    public final ImageView ivCommonRight;
    public final ImageView ivPlaceHolder;
    public final NoNetView noDataView;
    public final NoNetView noNetView;
    public final RelativeLayout rlTitle;
    public final RelativeLayout root;
    private final RelativeLayout rootView;
    public final RecyclerView rvList;
    public final TextView tvTitle;
    public final TextView tvTitleTag;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private PopupDelegateForMeBinding(RelativeLayout relativeLayout, ImageView imageView, ImageView imageView2, NoNetView noNetView, NoNetView noNetView2, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RecyclerView recyclerView, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.ivCommonRight = imageView;
        this.ivPlaceHolder = imageView2;
        this.noDataView = noNetView;
        this.noNetView = noNetView2;
        this.rlTitle = relativeLayout2;
        this.root = relativeLayout3;
        this.rvList = recyclerView;
        this.tvTitle = textView;
        this.tvTitleTag = textView2;
    }

    public static PopupDelegateForMeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopupDelegateForMeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.popup_delegate_for_me, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopupDelegateForMeBinding bind(View view) {
        int i = R.id.iv_common_right;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_common_right);
        if (imageView != null) {
            i = R.id.iv_place_holder;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_place_holder);
            if (imageView2 != null) {
                i = R.id.no_data_view;
                NoNetView noNetView = (NoNetView) ViewBindings.findChildViewById(view, R.id.no_data_view);
                if (noNetView != null) {
                    i = R.id.no_net_view;
                    NoNetView noNetView2 = (NoNetView) ViewBindings.findChildViewById(view, R.id.no_net_view);
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
                                    i = R.id.tv_title_tag;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title_tag);
                                    if (textView2 != null) {
                                        return new PopupDelegateForMeBinding(relativeLayout2, imageView, imageView2, noNetView, noNetView2, relativeLayout, relativeLayout2, recyclerView, textView, textView2);
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
