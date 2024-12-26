package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tronlinkpro.wallet.R;
public final class PopScanBinding implements ViewBinding {
    public final TextView address;
    public final Button copy;
    public final SimpleDraweeView image;
    public final LinearLayout popLayout;
    public final ImageView qr;
    private final RelativeLayout rootView;
    public final TextView title;
    public final RelativeLayout top;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private PopScanBinding(RelativeLayout relativeLayout, TextView textView, Button button, SimpleDraweeView simpleDraweeView, LinearLayout linearLayout, ImageView imageView, TextView textView2, RelativeLayout relativeLayout2) {
        this.rootView = relativeLayout;
        this.address = textView;
        this.copy = button;
        this.image = simpleDraweeView;
        this.popLayout = linearLayout;
        this.qr = imageView;
        this.title = textView2;
        this.top = relativeLayout2;
    }

    public static PopScanBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopScanBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.pop_scan, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopScanBinding bind(View view) {
        int i = R.id.address;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.address);
        if (textView != null) {
            i = R.id.copy;
            Button button = (Button) ViewBindings.findChildViewById(view, R.id.copy);
            if (button != null) {
                i = R.id.image;
                SimpleDraweeView simpleDraweeView = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.image);
                if (simpleDraweeView != null) {
                    i = R.id.pop_layout;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.pop_layout);
                    if (linearLayout != null) {
                        i = R.id.qr;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.qr);
                        if (imageView != null) {
                            i = R.id.title;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.title);
                            if (textView2 != null) {
                                i = R.id.top;
                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.top);
                                if (relativeLayout != null) {
                                    return new PopScanBinding((RelativeLayout) view, textView, button, simpleDraweeView, linearLayout, imageView, textView2, relativeLayout);
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
