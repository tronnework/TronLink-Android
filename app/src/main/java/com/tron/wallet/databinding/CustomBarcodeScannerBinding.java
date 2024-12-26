package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.qr.CustBarcodeView;
import com.tron.wallet.common.components.qr.CustomViewfinderView;
import com.tronlinkpro.wallet.R;
public final class CustomBarcodeScannerBinding implements ViewBinding {
    private final View rootView;
    public final CustBarcodeView zxingBarcodeSurface;
    public final TextView zxingStatusView;
    public final CustomViewfinderView zxingViewfinderView;

    @Override
    public View getRoot() {
        return this.rootView;
    }

    private CustomBarcodeScannerBinding(View view, CustBarcodeView custBarcodeView, TextView textView, CustomViewfinderView customViewfinderView) {
        this.rootView = view;
        this.zxingBarcodeSurface = custBarcodeView;
        this.zxingStatusView = textView;
        this.zxingViewfinderView = customViewfinderView;
    }

    public static CustomBarcodeScannerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        if (viewGroup == null) {
            throw new NullPointerException("parent");
        }
        layoutInflater.inflate(R.layout.custom_barcode_scanner, viewGroup);
        return bind(viewGroup);
    }

    public static CustomBarcodeScannerBinding bind(View view) {
        int i = R.id.zxing_barcode_surface;
        CustBarcodeView custBarcodeView = (CustBarcodeView) ViewBindings.findChildViewById(view, R.id.zxing_barcode_surface);
        if (custBarcodeView != null) {
            i = R.id.zxing_status_view;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.zxing_status_view);
            if (textView != null) {
                i = R.id.zxing_viewfinder_view;
                CustomViewfinderView customViewfinderView = (CustomViewfinderView) ViewBindings.findChildViewById(view, R.id.zxing_viewfinder_view);
                if (customViewfinderView != null) {
                    return new CustomBarcodeScannerBinding(view, custBarcodeView, textView, customViewfinderView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
