package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemBackupTip2Binding implements ViewBinding {
    public final LinearLayout llTip2;
    private final LinearLayout rootView;
    public final TextView tvKeepTitle;
    public final TextView tvTip;
    public final TextView tvTip21;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private ItemBackupTip2Binding(LinearLayout linearLayout, LinearLayout linearLayout2, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = linearLayout;
        this.llTip2 = linearLayout2;
        this.tvKeepTitle = textView;
        this.tvTip = textView2;
        this.tvTip21 = textView3;
    }

    public static ItemBackupTip2Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemBackupTip2Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_backup_tip2, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemBackupTip2Binding bind(View view) {
        LinearLayout linearLayout = (LinearLayout) view;
        int i = R.id.tv_keep_title;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_keep_title);
        if (textView != null) {
            i = R.id.tv_tip;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_tip);
            if (textView2 != null) {
                i = R.id.tv_tip2_1;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_tip2_1);
                if (textView3 != null) {
                    return new ItemBackupTip2Binding(linearLayout, linearLayout, textView, textView2, textView3);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
