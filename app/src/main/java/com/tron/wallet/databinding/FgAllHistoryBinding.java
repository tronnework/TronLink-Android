package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.components.ptr.PtrHTFrameLayoutV2;
import com.tronlinkpro.wallet.R;
public final class FgAllHistoryBinding implements ViewBinding {
    public final NestedScrollView llNodata;
    public final NoNetView netErrorView;
    public final LinearLayout placeholderView;
    public final PtrHTFrameLayoutV2 rcFrame;
    public final RecyclerView rcList;
    private final RelativeLayout rootView;
    public final TextView tvNoData;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FgAllHistoryBinding(RelativeLayout relativeLayout, NestedScrollView nestedScrollView, NoNetView noNetView, LinearLayout linearLayout, PtrHTFrameLayoutV2 ptrHTFrameLayoutV2, RecyclerView recyclerView, TextView textView) {
        this.rootView = relativeLayout;
        this.llNodata = nestedScrollView;
        this.netErrorView = noNetView;
        this.placeholderView = linearLayout;
        this.rcFrame = ptrHTFrameLayoutV2;
        this.rcList = recyclerView;
        this.tvNoData = textView;
    }

    public static FgAllHistoryBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FgAllHistoryBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fg_all_history, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FgAllHistoryBinding bind(View view) {
        int i = R.id.ll_nodata;
        NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(view, R.id.ll_nodata);
        if (nestedScrollView != null) {
            i = R.id.net_error_view;
            NoNetView noNetView = (NoNetView) ViewBindings.findChildViewById(view, R.id.net_error_view);
            if (noNetView != null) {
                i = R.id.placeholder_view;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.placeholder_view);
                if (linearLayout != null) {
                    i = R.id.rc_frame;
                    PtrHTFrameLayoutV2 ptrHTFrameLayoutV2 = (PtrHTFrameLayoutV2) ViewBindings.findChildViewById(view, R.id.rc_frame);
                    if (ptrHTFrameLayoutV2 != null) {
                        i = R.id.rc_list;
                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rc_list);
                        if (recyclerView != null) {
                            i = R.id.tv_no_data;
                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_no_data);
                            if (textView != null) {
                                return new FgAllHistoryBinding((RelativeLayout) view, nestedScrollView, noNetView, linearLayout, ptrHTFrameLayoutV2, recyclerView, textView);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
