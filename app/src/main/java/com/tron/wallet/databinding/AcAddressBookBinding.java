package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class AcAddressBookBinding implements ViewBinding {
    public final Button btNext;
    public final ImageView ivNodata;
    public final RecyclerView rcList;
    public final RelativeLayout rlNoData;
    private final RelativeLayout rootView;
    public final TextView tvMsg;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcAddressBookBinding(RelativeLayout relativeLayout, Button button, ImageView imageView, RecyclerView recyclerView, RelativeLayout relativeLayout2, TextView textView) {
        this.rootView = relativeLayout;
        this.btNext = button;
        this.ivNodata = imageView;
        this.rcList = recyclerView;
        this.rlNoData = relativeLayout2;
        this.tvMsg = textView;
    }

    public static AcAddressBookBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcAddressBookBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_address_book, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcAddressBookBinding bind(View view) {
        int i = R.id.bt_next;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_next);
        if (button != null) {
            i = R.id.iv_nodata;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_nodata);
            if (imageView != null) {
                i = R.id.rc_list;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rc_list);
                if (recyclerView != null) {
                    i = R.id.rl_no_data;
                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_no_data);
                    if (relativeLayout != null) {
                        i = R.id.tv_msg;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_msg);
                        if (textView != null) {
                            return new AcAddressBookBinding((RelativeLayout) view, button, imageView, recyclerView, relativeLayout, textView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
