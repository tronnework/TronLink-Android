package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.MyWebView;
import com.tron.wallet.common.components.NoNetView;
import com.tronlinkpro.wallet.R;
public final class AcHelpBinding implements ViewBinding {
    public final NoNetView noNetView;
    private final RelativeLayout rootView;
    public final MyWebView webview;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcHelpBinding(RelativeLayout relativeLayout, NoNetView noNetView, MyWebView myWebView) {
        this.rootView = relativeLayout;
        this.noNetView = noNetView;
        this.webview = myWebView;
    }

    public static AcHelpBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcHelpBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_help, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcHelpBinding bind(View view) {
        int i = R.id.no_net_view;
        NoNetView noNetView = (NoNetView) ViewBindings.findChildViewById(view, R.id.no_net_view);
        if (noNetView != null) {
            i = R.id.webview;
            MyWebView myWebView = (MyWebView) ViewBindings.findChildViewById(view, R.id.webview);
            if (myWebView != null) {
                return new AcHelpBinding((RelativeLayout) view, noNetView, myWebView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
