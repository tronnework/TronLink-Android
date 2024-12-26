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
public final class ProgressPageTranslucentBinding implements ViewBinding {
    public final ImageView btLoadding;
    public final ImageView ivLoaderror;
    public final ImageView ivNodata;
    public final LinearLayout llDialog;
    public final ImageView loadingview;
    public final RelativeLayout rlLoading;
    public final RelativeLayout rlNodata;
    public final RelativeLayout rlReload;
    public final RelativeLayout rootProgressPage;
    private final RelativeLayout rootView;
    public final TextView tvLoading;
    public final TextView tvMsg;
    public final TextView tvReload;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ProgressPageTranslucentBinding(RelativeLayout relativeLayout, ImageView imageView, ImageView imageView2, ImageView imageView3, LinearLayout linearLayout, ImageView imageView4, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, RelativeLayout relativeLayout5, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = relativeLayout;
        this.btLoadding = imageView;
        this.ivLoaderror = imageView2;
        this.ivNodata = imageView3;
        this.llDialog = linearLayout;
        this.loadingview = imageView4;
        this.rlLoading = relativeLayout2;
        this.rlNodata = relativeLayout3;
        this.rlReload = relativeLayout4;
        this.rootProgressPage = relativeLayout5;
        this.tvLoading = textView;
        this.tvMsg = textView2;
        this.tvReload = textView3;
    }

    public static ProgressPageTranslucentBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ProgressPageTranslucentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.progress_page_translucent, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ProgressPageTranslucentBinding bind(View view) {
        int i = R.id.bt_loadding;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.bt_loadding);
        if (imageView != null) {
            i = R.id.iv_loaderror;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_loaderror);
            if (imageView2 != null) {
                i = R.id.iv_nodata;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_nodata);
                if (imageView3 != null) {
                    i = R.id.ll_dialog;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_dialog);
                    if (linearLayout != null) {
                        i = R.id.loadingview;
                        ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.loadingview);
                        if (imageView4 != null) {
                            i = R.id.rl_loading;
                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_loading);
                            if (relativeLayout != null) {
                                i = R.id.rl_nodata;
                                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_nodata);
                                if (relativeLayout2 != null) {
                                    i = R.id.rl_reload;
                                    RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_reload);
                                    if (relativeLayout3 != null) {
                                        RelativeLayout relativeLayout4 = (RelativeLayout) view;
                                        i = R.id.tv_loading;
                                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_loading);
                                        if (textView != null) {
                                            i = R.id.tv_msg;
                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_msg);
                                            if (textView2 != null) {
                                                i = R.id.tv_reload;
                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_reload);
                                                if (textView3 != null) {
                                                    return new ProgressPageTranslucentBinding(relativeLayout4, imageView, imageView2, imageView3, linearLayout, imageView4, relativeLayout, relativeLayout2, relativeLayout3, relativeLayout4, textView, textView2, textView3);
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
