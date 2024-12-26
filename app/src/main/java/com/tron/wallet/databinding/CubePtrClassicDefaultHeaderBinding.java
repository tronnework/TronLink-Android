package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class CubePtrClassicDefaultHeaderBinding implements ViewBinding {
    public final ImageView ptrClassicHeaderRotateView;
    public final TextView ptrClassicHeaderRotateViewHeaderLastUpdate;
    public final LinearLayout ptrClassicHeaderRotateViewHeaderText;
    public final TextView ptrClassicHeaderRotateViewHeaderTitle;
    public final ImageView ptrClassicHeaderRotateViewProgressbar;
    private final LinearLayout rootView;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private CubePtrClassicDefaultHeaderBinding(LinearLayout linearLayout, ImageView imageView, TextView textView, LinearLayout linearLayout2, TextView textView2, ImageView imageView2) {
        this.rootView = linearLayout;
        this.ptrClassicHeaderRotateView = imageView;
        this.ptrClassicHeaderRotateViewHeaderLastUpdate = textView;
        this.ptrClassicHeaderRotateViewHeaderText = linearLayout2;
        this.ptrClassicHeaderRotateViewHeaderTitle = textView2;
        this.ptrClassicHeaderRotateViewProgressbar = imageView2;
    }

    public static CubePtrClassicDefaultHeaderBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static CubePtrClassicDefaultHeaderBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.cube_ptr_classic_default_header, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static CubePtrClassicDefaultHeaderBinding bind(View view) {
        int i = R.id.ptr_classic_header_rotate_view;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.ptr_classic_header_rotate_view);
        if (imageView != null) {
            i = R.id.ptr_classic_header_rotate_view_header_last_update;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.ptr_classic_header_rotate_view_header_last_update);
            if (textView != null) {
                i = R.id.ptr_classic_header_rotate_view_header_text;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ptr_classic_header_rotate_view_header_text);
                if (linearLayout != null) {
                    i = R.id.ptr_classic_header_rotate_view_header_title;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.ptr_classic_header_rotate_view_header_title);
                    if (textView2 != null) {
                        i = R.id.ptr_classic_header_rotate_view_progressbar;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.ptr_classic_header_rotate_view_progressbar);
                        if (imageView2 != null) {
                            return new CubePtrClassicDefaultHeaderBinding((LinearLayout) view, imageView, textView, linearLayout, textView2, imageView2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
