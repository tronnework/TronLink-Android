package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.ptr.PtrHTFrameLayout;
import com.tronlinkpro.wallet.R;
public final class AcDappHistoryBinding implements ViewBinding {
    public final TextView btnDel;
    public final TextView btnSelect;
    public final ImageView ivCommonLeft;
    public final LinearLayout layoutEdit;
    public final PtrHTFrameLayout rcFrame;
    public final RecyclerView recyclerView;
    private final LinearLayout rootView;
    public final TextView tvRight;
    public final TextView tvTitle;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private AcDappHistoryBinding(LinearLayout linearLayout, TextView textView, TextView textView2, ImageView imageView, LinearLayout linearLayout2, PtrHTFrameLayout ptrHTFrameLayout, RecyclerView recyclerView, TextView textView3, TextView textView4) {
        this.rootView = linearLayout;
        this.btnDel = textView;
        this.btnSelect = textView2;
        this.ivCommonLeft = imageView;
        this.layoutEdit = linearLayout2;
        this.rcFrame = ptrHTFrameLayout;
        this.recyclerView = recyclerView;
        this.tvRight = textView3;
        this.tvTitle = textView4;
    }

    public static AcDappHistoryBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcDappHistoryBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_dapp_history, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcDappHistoryBinding bind(View view) {
        int i = R.id.btn_del;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.btn_del);
        if (textView != null) {
            i = R.id.btn_select;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.btn_select);
            if (textView2 != null) {
                i = R.id.iv_common_left;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_common_left);
                if (imageView != null) {
                    i = R.id.layout_edit;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.layout_edit);
                    if (linearLayout != null) {
                        i = R.id.rc_frame;
                        PtrHTFrameLayout ptrHTFrameLayout = (PtrHTFrameLayout) ViewBindings.findChildViewById(view, R.id.rc_frame);
                        if (ptrHTFrameLayout != null) {
                            i = R.id.recycler_view;
                            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.recycler_view);
                            if (recyclerView != null) {
                                i = R.id.tv_right;
                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_right);
                                if (textView3 != null) {
                                    i = R.id.tv_title;
                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                                    if (textView4 != null) {
                                        return new AcDappHistoryBinding((LinearLayout) view, textView, textView2, imageView, linearLayout, ptrHTFrameLayout, recyclerView, textView3, textView4);
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
