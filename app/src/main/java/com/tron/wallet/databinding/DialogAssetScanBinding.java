package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.common.MaxHeightScrollView;
import com.tronlinkpro.wallet.R;
public final class DialogAssetScanBinding implements ViewBinding {
    public final MaxHeightScrollView nestedScrollView;
    private final RelativeLayout rootView;
    public final TextView tvCancle;
    public final TextView tvConfirm;
    public final TextView tvContent;
    public final TextView tvTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private DialogAssetScanBinding(RelativeLayout relativeLayout, MaxHeightScrollView maxHeightScrollView, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        this.rootView = relativeLayout;
        this.nestedScrollView = maxHeightScrollView;
        this.tvCancle = textView;
        this.tvConfirm = textView2;
        this.tvContent = textView3;
        this.tvTitle = textView4;
    }

    public static DialogAssetScanBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogAssetScanBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.dialog_asset_scan, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static DialogAssetScanBinding bind(View view) {
        int i = R.id.nested_scroll_view;
        MaxHeightScrollView maxHeightScrollView = (MaxHeightScrollView) ViewBindings.findChildViewById(view, R.id.nested_scroll_view);
        if (maxHeightScrollView != null) {
            i = R.id.tv_cancle;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_cancle);
            if (textView != null) {
                i = R.id.tv_confirm;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_confirm);
                if (textView2 != null) {
                    i = R.id.tv_content;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_content);
                    if (textView3 != null) {
                        i = R.id.tv_title;
                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                        if (textView4 != null) {
                            return new DialogAssetScanBinding((RelativeLayout) view, maxHeightScrollView, textView, textView2, textView3, textView4);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
