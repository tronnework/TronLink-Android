package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.tronlinkpro.wallet.R;
public final class LayoutActivityScannerBinding implements ViewBinding {
    public final RelativeLayout llBack;
    private final RelativeLayout rootView;
    public final TextView tvPic;
    public final DecoratedBarcodeView zxingBarcodeScanner;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private LayoutActivityScannerBinding(RelativeLayout relativeLayout, RelativeLayout relativeLayout2, TextView textView, DecoratedBarcodeView decoratedBarcodeView) {
        this.rootView = relativeLayout;
        this.llBack = relativeLayout2;
        this.tvPic = textView;
        this.zxingBarcodeScanner = decoratedBarcodeView;
    }

    public static LayoutActivityScannerBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutActivityScannerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_activity_scanner, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutActivityScannerBinding bind(View view) {
        int i = R.id.ll_back;
        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_back);
        if (relativeLayout != null) {
            i = R.id.tv_pic;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_pic);
            if (textView != null) {
                i = R.id.zxing_barcode_scanner;
                DecoratedBarcodeView decoratedBarcodeView = (DecoratedBarcodeView) ViewBindings.findChildViewById(view, R.id.zxing_barcode_scanner);
                if (decoratedBarcodeView != null) {
                    return new LayoutActivityScannerBinding((RelativeLayout) view, relativeLayout, textView, decoratedBarcodeView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
