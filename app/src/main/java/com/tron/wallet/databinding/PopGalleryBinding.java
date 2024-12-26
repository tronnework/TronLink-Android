package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class PopGalleryBinding implements ViewBinding {
    public final RelativeLayout cancle;
    private final RelativeLayout rootView;
    public final RelativeLayout save;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private PopGalleryBinding(RelativeLayout relativeLayout, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3) {
        this.rootView = relativeLayout;
        this.cancle = relativeLayout2;
        this.save = relativeLayout3;
    }

    public static PopGalleryBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopGalleryBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.pop_gallery, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopGalleryBinding bind(View view) {
        int i = R.id.cancle;
        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.cancle);
        if (relativeLayout != null) {
            i = R.id.save;
            RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.save);
            if (relativeLayout2 != null) {
                return new PopGalleryBinding((RelativeLayout) view, relativeLayout, relativeLayout2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
