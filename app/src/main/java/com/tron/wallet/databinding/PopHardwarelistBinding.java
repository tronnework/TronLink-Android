package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class PopHardwarelistBinding implements ViewBinding {
    public final ImageView ivCloseForHardwarelist;
    public final View line;
    public final RecyclerView rcHardware;
    public final RelativeLayout rlHardwarelist;
    private final RelativeLayout rootView;
    public final TextView tvSelectHardware;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private PopHardwarelistBinding(RelativeLayout relativeLayout, ImageView imageView, View view, RecyclerView recyclerView, RelativeLayout relativeLayout2, TextView textView) {
        this.rootView = relativeLayout;
        this.ivCloseForHardwarelist = imageView;
        this.line = view;
        this.rcHardware = recyclerView;
        this.rlHardwarelist = relativeLayout2;
        this.tvSelectHardware = textView;
    }

    public static PopHardwarelistBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopHardwarelistBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.pop_hardwarelist, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopHardwarelistBinding bind(View view) {
        int i = R.id.iv_close_for_hardwarelist;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_close_for_hardwarelist);
        if (imageView != null) {
            i = R.id.line;
            View findChildViewById = ViewBindings.findChildViewById(view, R.id.line);
            if (findChildViewById != null) {
                i = R.id.rc_hardware;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rc_hardware);
                if (recyclerView != null) {
                    RelativeLayout relativeLayout = (RelativeLayout) view;
                    i = R.id.tv_select_hardware;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_select_hardware);
                    if (textView != null) {
                        return new PopHardwarelistBinding(relativeLayout, imageView, findChildViewById, recyclerView, relativeLayout, textView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
