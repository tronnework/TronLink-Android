package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.frescoimage.view.BigImageView;
import com.tronlinkpro.wallet.R;
public final class AcBigimageBinding implements ViewBinding {
    public final BigImageView bigimage;
    private final RelativeLayout rootView;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcBigimageBinding(RelativeLayout relativeLayout, BigImageView bigImageView) {
        this.rootView = relativeLayout;
        this.bigimage = bigImageView;
    }

    public static AcBigimageBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcBigimageBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_bigimage, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcBigimageBinding bind(View view) {
        BigImageView bigImageView = (BigImageView) ViewBindings.findChildViewById(view, R.id.bigimage);
        if (bigImageView != null) {
            return new AcBigimageBinding((RelativeLayout) view, bigImageView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.bigimage)));
    }
}
