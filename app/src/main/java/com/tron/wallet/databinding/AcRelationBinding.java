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
public final class AcRelationBinding implements ViewBinding {
    public final Button btCreate;
    public final ImageView ivCommonRight;
    public final RecyclerView list;
    public final NoNetView noDataView;
    public final RelativeLayout rlTitle;
    public final LinearLayout root;
    private final LinearLayout rootView;
    public final TextView tvTitle;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private AcRelationBinding(LinearLayout linearLayout, Button button, ImageView imageView, RecyclerView recyclerView, NoNetView noNetView, RelativeLayout relativeLayout, LinearLayout linearLayout2, TextView textView) {
        this.rootView = linearLayout;
        this.btCreate = button;
        this.ivCommonRight = imageView;
        this.list = recyclerView;
        this.noDataView = noNetView;
        this.rlTitle = relativeLayout;
        this.root = linearLayout2;
        this.tvTitle = textView;
    }

    public static AcRelationBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcRelationBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_relation, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcRelationBinding bind(View view) {
        int i = R.id.bt_create;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_create);
        if (button != null) {
            i = R.id.iv_common_right;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_common_right);
            if (imageView != null) {
                i = R.id.list;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.list);
                if (recyclerView != null) {
                    i = R.id.no_data_view;
                    NoNetView noNetView = (NoNetView) ViewBindings.findChildViewById(view, R.id.no_data_view);
                    if (noNetView != null) {
                        i = R.id.rl_title;
                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_title);
                        if (relativeLayout != null) {
                            LinearLayout linearLayout = (LinearLayout) view;
                            i = R.id.tv_title;
                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                            if (textView != null) {
                                return new AcRelationBinding(linearLayout, button, imageView, recyclerView, noNetView, relativeLayout, linearLayout, textView);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
