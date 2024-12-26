package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.business.tabdapp.browser.BrowserWebView;
import com.tron.wallet.common.components.NoNetView;
import com.tronlinkpro.wallet.R;
public final class FragmentFinanceBinding implements ViewBinding {
    public final BrowserWebView browserView;
    public final RelativeLayout financeRoot;
    public final ImageView ivLoading;
    public final RelativeLayout loadingView;
    public final NoNetView noDataView;
    private final RelativeLayout rootView;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FragmentFinanceBinding(RelativeLayout relativeLayout, BrowserWebView browserWebView, RelativeLayout relativeLayout2, ImageView imageView, RelativeLayout relativeLayout3, NoNetView noNetView) {
        this.rootView = relativeLayout;
        this.browserView = browserWebView;
        this.financeRoot = relativeLayout2;
        this.ivLoading = imageView;
        this.loadingView = relativeLayout3;
        this.noDataView = noNetView;
    }

    public static FragmentFinanceBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentFinanceBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_finance, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentFinanceBinding bind(View view) {
        int i = R.id.browser_view;
        BrowserWebView browserWebView = (BrowserWebView) ViewBindings.findChildViewById(view, R.id.browser_view);
        if (browserWebView != null) {
            RelativeLayout relativeLayout = (RelativeLayout) view;
            i = R.id.iv_loading;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_loading);
            if (imageView != null) {
                i = R.id.loading_view;
                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.loading_view);
                if (relativeLayout2 != null) {
                    i = R.id.no_data_view;
                    NoNetView noNetView = (NoNetView) ViewBindings.findChildViewById(view, R.id.no_data_view);
                    if (noNetView != null) {
                        return new FragmentFinanceBinding(relativeLayout, browserWebView, relativeLayout, imageView, relativeLayout2, noNetView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
