package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.YellowEnergyProgressView;
import com.tronlinkpro.wallet.R;
public final class AcNewSignedTransactionBinding implements ViewBinding {
    public final ImageView ivQr2;
    public final RecyclerView rcTop;
    public final RelativeLayout rlProgress;
    public final RelativeLayout rlTop;
    private final RelativeLayout rootView;
    public final YellowEnergyProgressView scanProgress;
    public final Button toscan;
    public final TextView tv3501;
    public final TextView tvProgressCenter;
    public final TextView tvProgressRight;
    public final TextView tvTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcNewSignedTransactionBinding(RelativeLayout relativeLayout, ImageView imageView, RecyclerView recyclerView, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, YellowEnergyProgressView yellowEnergyProgressView, Button button, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        this.rootView = relativeLayout;
        this.ivQr2 = imageView;
        this.rcTop = recyclerView;
        this.rlProgress = relativeLayout2;
        this.rlTop = relativeLayout3;
        this.scanProgress = yellowEnergyProgressView;
        this.toscan = button;
        this.tv3501 = textView;
        this.tvProgressCenter = textView2;
        this.tvProgressRight = textView3;
        this.tvTitle = textView4;
    }

    public static AcNewSignedTransactionBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcNewSignedTransactionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_new_signed_transaction, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcNewSignedTransactionBinding bind(View view) {
        int i = R.id.iv_qr2;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_qr2);
        if (imageView != null) {
            i = R.id.rc_top;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rc_top);
            if (recyclerView != null) {
                i = R.id.rl_progress;
                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_progress);
                if (relativeLayout != null) {
                    i = R.id.rl_top;
                    RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_top);
                    if (relativeLayout2 != null) {
                        i = R.id.scan_progress;
                        YellowEnergyProgressView yellowEnergyProgressView = (YellowEnergyProgressView) ViewBindings.findChildViewById(view, R.id.scan_progress);
                        if (yellowEnergyProgressView != null) {
                            i = R.id.toscan;
                            Button button = (Button) ViewBindings.findChildViewById(view, R.id.toscan);
                            if (button != null) {
                                i = R.id.tv_350_1;
                                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_350_1);
                                if (textView != null) {
                                    i = R.id.tv_progress_center;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_progress_center);
                                    if (textView2 != null) {
                                        i = R.id.tv_progress_right;
                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_progress_right);
                                        if (textView3 != null) {
                                            i = R.id.tv_title;
                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                                            if (textView4 != null) {
                                                return new AcNewSignedTransactionBinding((RelativeLayout) view, imageView, recyclerView, relativeLayout, relativeLayout2, yellowEnergyProgressView, button, textView, textView2, textView3, textView4);
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
