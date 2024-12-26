package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tronlinkpro.wallet.R;
public final class LedgerLoadingBarBinding implements ViewBinding {
    private final RelativeLayout rootView;
    public final SimpleDraweeView tvImage;
    public final TextView tvTips;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private LedgerLoadingBarBinding(RelativeLayout relativeLayout, SimpleDraweeView simpleDraweeView, TextView textView) {
        this.rootView = relativeLayout;
        this.tvImage = simpleDraweeView;
        this.tvTips = textView;
    }

    public static LedgerLoadingBarBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LedgerLoadingBarBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ledger_loading_bar, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LedgerLoadingBarBinding bind(View view) {
        int i = R.id.tv_image;
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.tv_image);
        if (simpleDraweeView != null) {
            i = R.id.tv_tips;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_tips);
            if (textView != null) {
                return new LedgerLoadingBarBinding((RelativeLayout) view, simpleDraweeView, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
