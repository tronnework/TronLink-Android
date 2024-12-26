package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.components.ptr.PtrHTFrameLayout;
import com.tronlinkpro.wallet.R;
public final class FragmentBrowserBookmarkBinding implements ViewBinding {
    public final NestedScrollView llNodata;
    public final NoNetView netErrorView;
    public final PtrHTFrameLayout rcFrame;
    public final ImageView rcHolderList;
    public final RecyclerView rcList;
    private final RelativeLayout rootView;
    public final TextView tvNoData;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FragmentBrowserBookmarkBinding(RelativeLayout relativeLayout, NestedScrollView nestedScrollView, NoNetView noNetView, PtrHTFrameLayout ptrHTFrameLayout, ImageView imageView, RecyclerView recyclerView, TextView textView) {
        this.rootView = relativeLayout;
        this.llNodata = nestedScrollView;
        this.netErrorView = noNetView;
        this.rcFrame = ptrHTFrameLayout;
        this.rcHolderList = imageView;
        this.rcList = recyclerView;
        this.tvNoData = textView;
    }

    public static FragmentBrowserBookmarkBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentBrowserBookmarkBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_browser_bookmark, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentBrowserBookmarkBinding bind(View view) {
        int i = R.id.ll_nodata;
        NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(view, R.id.ll_nodata);
        if (nestedScrollView != null) {
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
                            i = R.id.tv_no_data;
                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_no_data);
                            if (textView != null) {
                                return new FragmentBrowserBookmarkBinding((RelativeLayout) view, nestedScrollView, noNetView, ptrHTFrameLayout, imageView, recyclerView, textView);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
