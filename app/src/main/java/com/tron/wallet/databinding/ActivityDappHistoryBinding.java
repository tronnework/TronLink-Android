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
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.components.ptr.PtrHTFrameLayout;
import com.tronlinkpro.wallet.R;
public final class ActivityDappHistoryBinding implements ViewBinding {
    public final TextView btnDel;
    public final TextView btnSelect;
    public final LinearLayout layoutEdit;
    public final ItemNoHistoryBinding llNoData;
    public final NoNetView netErrorView;
    public final PtrHTFrameLayout rcFrame;
    public final ImageView rcHolderList;
    public final RecyclerView recyclerView;
    private final LinearLayout rootView;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private ActivityDappHistoryBinding(LinearLayout linearLayout, TextView textView, TextView textView2, LinearLayout linearLayout2, ItemNoHistoryBinding itemNoHistoryBinding, NoNetView noNetView, PtrHTFrameLayout ptrHTFrameLayout, ImageView imageView, RecyclerView recyclerView) {
        this.rootView = linearLayout;
        this.btnDel = textView;
        this.btnSelect = textView2;
        this.layoutEdit = linearLayout2;
        this.llNoData = itemNoHistoryBinding;
        this.netErrorView = noNetView;
        this.rcFrame = ptrHTFrameLayout;
        this.rcHolderList = imageView;
        this.recyclerView = recyclerView;
    }

    public static ActivityDappHistoryBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityDappHistoryBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_dapp_history, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityDappHistoryBinding bind(View view) {
        int i = R.id.btn_del;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.btn_del);
        if (textView != null) {
            i = R.id.btn_select;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.btn_select);
            if (textView2 != null) {
                i = R.id.layout_edit;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.layout_edit);
                if (linearLayout != null) {
                    i = R.id.ll_no_data;
                    View findChildViewById = ViewBindings.findChildViewById(view, R.id.ll_no_data);
                    if (findChildViewById != null) {
                        ItemNoHistoryBinding bind = ItemNoHistoryBinding.bind(findChildViewById);
                        i = R.id.net_error_view;
                        NoNetView noNetView = (NoNetView) ViewBindings.findChildViewById(view, R.id.net_error_view);
                        if (noNetView != null) {
                            i = R.id.rc_frame;
                            PtrHTFrameLayout ptrHTFrameLayout = (PtrHTFrameLayout) ViewBindings.findChildViewById(view, R.id.rc_frame);
                            if (ptrHTFrameLayout != null) {
                                i = R.id.rc_holder_list;
                                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.rc_holder_list);
                                if (imageView != null) {
                                    i = R.id.recycler_view;
                                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.recycler_view);
                                    if (recyclerView != null) {
                                        return new ActivityDappHistoryBinding((LinearLayout) view, textView, textView2, linearLayout, bind, noNetView, ptrHTFrameLayout, imageView, recyclerView);
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
