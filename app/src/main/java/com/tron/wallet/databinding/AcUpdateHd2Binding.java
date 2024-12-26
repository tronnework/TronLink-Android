package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class AcUpdateHd2Binding implements ViewBinding {
    public final Button btNext;
    public final NestedScrollView contentView;
    public final ItemUpgradeHdBinding itemUpgrade;
    public final ImageView ivLoading;
    public final LinearLayout loadingView;
    public final RecyclerView rcContent;
    private final RelativeLayout rootView;
    public final TextView tvCurrentHdTitle;
    public final TextView tvSelectHdTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcUpdateHd2Binding(RelativeLayout relativeLayout, Button button, NestedScrollView nestedScrollView, ItemUpgradeHdBinding itemUpgradeHdBinding, ImageView imageView, LinearLayout linearLayout, RecyclerView recyclerView, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.btNext = button;
        this.contentView = nestedScrollView;
        this.itemUpgrade = itemUpgradeHdBinding;
        this.ivLoading = imageView;
        this.loadingView = linearLayout;
        this.rcContent = recyclerView;
        this.tvCurrentHdTitle = textView;
        this.tvSelectHdTitle = textView2;
    }

    public static AcUpdateHd2Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcUpdateHd2Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_update_hd_2, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcUpdateHd2Binding bind(View view) {
        int i = R.id.bt_next;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_next);
        if (button != null) {
            i = R.id.content_view;
            NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(view, R.id.content_view);
            if (nestedScrollView != null) {
                i = R.id.item_upgrade;
                View findChildViewById = ViewBindings.findChildViewById(view, R.id.item_upgrade);
                if (findChildViewById != null) {
                    ItemUpgradeHdBinding bind = ItemUpgradeHdBinding.bind(findChildViewById);
                    i = R.id.iv_loading;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_loading);
                    if (imageView != null) {
                        i = R.id.loading_view;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.loading_view);
                        if (linearLayout != null) {
                            i = R.id.rc_content;
                            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rc_content);
                            if (recyclerView != null) {
                                i = R.id.tv_current_hd_title;
                                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_current_hd_title);
                                if (textView != null) {
                                    i = R.id.tv_select_hd_title;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_select_hd_title);
                                    if (textView2 != null) {
                                        return new AcUpdateHd2Binding((RelativeLayout) view, button, nestedScrollView, bind, imageView, linearLayout, recyclerView, textView, textView2);
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
