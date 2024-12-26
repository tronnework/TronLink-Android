package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.MyWebView;
import com.tronlinkpro.wallet.R;
public final class AcGetTrxTestBinding implements ViewBinding {
    private final RelativeLayout rootView;
    public final MyWebView webView;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcGetTrxTestBinding(RelativeLayout relativeLayout, MyWebView myWebView) {
        this.rootView = relativeLayout;
        this.webView = myWebView;
    }

    public static AcGetTrxTestBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcGetTrxTestBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_get_trx_test, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcGetTrxTestBinding bind(View view) {
        MyWebView myWebView = (MyWebView) ViewBindings.findChildViewById(view, R.id.web_view);
        if (myWebView != null) {
            return new AcGetTrxTestBinding((RelativeLayout) view, myWebView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.web_view)));
    }
}
