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
import com.tronlinkpro.wallet.R;
public final class PopAssetsBinding implements ViewBinding {
    public final ImageView cancle;
    public final LinearLayout llDialog;
    public final ImageView loadingview;
    public final RecyclerView rcList;
    public final Button reset;
    public final RelativeLayout resetRoot;
    public final RelativeLayout rlLoading;
    private final RelativeLayout rootView;
    public final LinearLayout top;
    public final TextView tvLoading;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private PopAssetsBinding(RelativeLayout relativeLayout, ImageView imageView, LinearLayout linearLayout, ImageView imageView2, RecyclerView recyclerView, Button button, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, LinearLayout linearLayout2, TextView textView) {
        this.rootView = relativeLayout;
        this.cancle = imageView;
        this.llDialog = linearLayout;
        this.loadingview = imageView2;
        this.rcList = recyclerView;
        this.reset = button;
        this.resetRoot = relativeLayout2;
        this.rlLoading = relativeLayout3;
        this.top = linearLayout2;
        this.tvLoading = textView;
    }

    public static PopAssetsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopAssetsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.pop_assets, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopAssetsBinding bind(View view) {
        int i = R.id.cancle;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.cancle);
        if (imageView != null) {
            i = R.id.ll_dialog;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_dialog);
            if (linearLayout != null) {
                i = R.id.loadingview;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.loadingview);
                if (imageView2 != null) {
                    i = R.id.rc_list;
                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rc_list);
                    if (recyclerView != null) {
                        i = R.id.reset;
                        Button button = (Button) ViewBindings.findChildViewById(view, R.id.reset);
                        if (button != null) {
                            i = R.id.reset_root;
                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.reset_root);
                            if (relativeLayout != null) {
                                i = R.id.rl_loading;
                                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_loading);
                                if (relativeLayout2 != null) {
                                    i = R.id.top;
                                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.top);
                                    if (linearLayout2 != null) {
                                        i = R.id.tv_loading;
                                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_loading);
                                        if (textView != null) {
                                            return new PopAssetsBinding((RelativeLayout) view, imageView, linearLayout, imageView2, recyclerView, button, relativeLayout, relativeLayout2, linearLayout2, textView);
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
