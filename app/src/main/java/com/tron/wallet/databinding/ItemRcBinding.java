package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.components.ptr.PtrHTFrameLayout;
import com.tronlinkpro.wallet.R;
public final class ItemRcBinding implements ViewBinding {
    public final TextView btnCancel;
    public final TextView confirm;
    public final LinearLayout llContainer;
    public final NoNetView netErrorView;
    public final PtrHTFrameLayout rcFrame;
    public final ImageView rcHolderList;
    public final RecyclerView rcList;
    private final RelativeLayout rootView;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemRcBinding(RelativeLayout relativeLayout, TextView textView, TextView textView2, LinearLayout linearLayout, NoNetView noNetView, PtrHTFrameLayout ptrHTFrameLayout, ImageView imageView, RecyclerView recyclerView) {
        this.rootView = relativeLayout;
        this.btnCancel = textView;
        this.confirm = textView2;
        this.llContainer = linearLayout;
        this.netErrorView = noNetView;
        this.rcFrame = ptrHTFrameLayout;
        this.rcHolderList = imageView;
        this.rcList = recyclerView;
    }

    public static ItemRcBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemRcBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_rc, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemRcBinding bind(View view) {
        int i = R.id.btn_cancel;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.btn_cancel);
        if (textView != null) {
            i = R.id.confirm;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.confirm);
            if (textView2 != null) {
                i = R.id.ll_container;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_container);
                if (linearLayout != null) {
                    i = R.id.net_error_view;
                    NoNetView noNetView = (NoNetView) ViewBindings.findChildViewById(view, R.id.net_error_view);
                    if (noNetView != null) {
                        i = R.id.rc_frame;
                        PtrHTFrameLayout ptrHTFrameLayout = (PtrHTFrameLayout) ViewBindings.findChildViewById(view, R.id.rc_frame);
                        if (ptrHTFrameLayout != null) {
                            i = R.id.rc_holder_list;
                            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.rc_holder_list);
                            if (imageView != null) {
                                i = R.id.rc_list;
                                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rc_list);
                                if (recyclerView != null) {
                                    return new ItemRcBinding((RelativeLayout) view, textView, textView2, linearLayout, noNetView, ptrHTFrameLayout, imageView, recyclerView);
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
