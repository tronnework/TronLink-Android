package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemSignTransaction2Binding implements ViewBinding {
    public final RelativeLayout rlBg;
    private final RelativeLayout rootView;
    public final ImageView signOk;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemSignTransaction2Binding(RelativeLayout relativeLayout, RelativeLayout relativeLayout2, ImageView imageView) {
        this.rootView = relativeLayout;
        this.rlBg = relativeLayout2;
        this.signOk = imageView;
    }

    public static ItemSignTransaction2Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemSignTransaction2Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_sign_transaction_2, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemSignTransaction2Binding bind(View view) {
        int i = R.id.rl_bg;
        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_bg);
        if (relativeLayout != null) {
            i = R.id.sign_ok;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.sign_ok);
            if (imageView != null) {
                return new ItemSignTransaction2Binding((RelativeLayout) view, relativeLayout, imageView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
