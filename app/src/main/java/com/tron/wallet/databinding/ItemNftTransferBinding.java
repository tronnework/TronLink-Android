package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.LoadingView;
import com.tronlinkpro.wallet.R;
public final class ItemNftTransferBinding implements ViewBinding {
    public final TextView address;
    public final TextView addressTitle;
    public final LoadingView ivPending;
    public final LinearLayout llPending;
    public final RelativeLayout rlInner;
    public final RelativeLayout root;
    private final RelativeLayout rootView;
    public final TextView time;
    public final TextView tvId;
    public final TextView tvPending;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemNftTransferBinding(RelativeLayout relativeLayout, TextView textView, TextView textView2, LoadingView loadingView, LinearLayout linearLayout, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, TextView textView3, TextView textView4, TextView textView5) {
        this.rootView = relativeLayout;
        this.address = textView;
        this.addressTitle = textView2;
        this.ivPending = loadingView;
        this.llPending = linearLayout;
        this.rlInner = relativeLayout2;
        this.root = relativeLayout3;
        this.time = textView3;
        this.tvId = textView4;
        this.tvPending = textView5;
    }

    public static ItemNftTransferBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemNftTransferBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_nft_transfer, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemNftTransferBinding bind(View view) {
        int i = R.id.address;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.address);
        if (textView != null) {
            i = R.id.address_title;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.address_title);
            if (textView2 != null) {
                i = R.id.iv_pending;
                LoadingView loadingView = (LoadingView) ViewBindings.findChildViewById(view, R.id.iv_pending);
                if (loadingView != null) {
                    i = R.id.ll_pending;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_pending);
                    if (linearLayout != null) {
                        i = R.id.rl_inner;
                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_inner);
                        if (relativeLayout != null) {
                            RelativeLayout relativeLayout2 = (RelativeLayout) view;
                            i = R.id.time;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.time);
                            if (textView3 != null) {
                                i = R.id.tv_id;
                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_id);
                                if (textView4 != null) {
                                    i = R.id.tv_pending;
                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_pending);
                                    if (textView5 != null) {
                                        return new ItemNftTransferBinding(relativeLayout2, textView, textView2, loadingView, linearLayout, relativeLayout, relativeLayout2, textView3, textView4, textView5);
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
