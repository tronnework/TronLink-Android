package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.business.tabdapp.component.DAppTitleView;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.components.browser.BrowserContent;
import com.tronlinkpro.wallet.R;
public final class FragmentDappBrowserBinding implements ViewBinding {
    public final BrowserContent browserView;
    public final DAppTitleView dappTitleView;
    public final NoNetView noNetView;
    private final RelativeLayout rootView;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FragmentDappBrowserBinding(RelativeLayout relativeLayout, BrowserContent browserContent, DAppTitleView dAppTitleView, NoNetView noNetView) {
        this.rootView = relativeLayout;
        this.browserView = browserContent;
        this.dappTitleView = dAppTitleView;
        this.noNetView = noNetView;
    }

    public static FragmentDappBrowserBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentDappBrowserBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_dapp_browser, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentDappBrowserBinding bind(View view) {
        int i = R.id.browser_view;
        BrowserContent browserContent = (BrowserContent) ViewBindings.findChildViewById(view, R.id.browser_view);
        if (browserContent != null) {
            i = R.id.dapp_title_view;
            DAppTitleView dAppTitleView = (DAppTitleView) ViewBindings.findChildViewById(view, R.id.dapp_title_view);
            if (dAppTitleView != null) {
                i = R.id.no_net_view;
                NoNetView noNetView = (NoNetView) ViewBindings.findChildViewById(view, R.id.no_net_view);
                if (noNetView != null) {
                    return new FragmentDappBrowserBinding((RelativeLayout) view, browserContent, dAppTitleView, noNetView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
