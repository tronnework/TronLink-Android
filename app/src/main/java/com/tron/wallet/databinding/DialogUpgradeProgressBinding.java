package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class DialogUpgradeProgressBinding implements ViewBinding {
    public final LinearLayout llBtn;
    public final LinearLayout llWindow;
    public final ProgressBar progressbarDownload;
    private final RelativeLayout rootView;
    public final TextView tvCancel;
    public final TextView tvDesc;
    public final TextView tvRetry;
    public final TextView tvSwitchLine;
    public final TextView tvTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private DialogUpgradeProgressBinding(RelativeLayout relativeLayout, LinearLayout linearLayout, LinearLayout linearLayout2, ProgressBar progressBar, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5) {
        this.rootView = relativeLayout;
        this.llBtn = linearLayout;
        this.llWindow = linearLayout2;
        this.progressbarDownload = progressBar;
        this.tvCancel = textView;
        this.tvDesc = textView2;
        this.tvRetry = textView3;
        this.tvSwitchLine = textView4;
        this.tvTitle = textView5;
    }

    public static DialogUpgradeProgressBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogUpgradeProgressBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.dialog_upgrade_progress, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static DialogUpgradeProgressBinding bind(View view) {
        int i = R.id.ll_btn;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_btn);
        if (linearLayout != null) {
            i = R.id.ll_window;
            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_window);
            if (linearLayout2 != null) {
                i = R.id.progressbar_download;
                ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, R.id.progressbar_download);
                if (progressBar != null) {
                    i = R.id.tv_cancel;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_cancel);
                    if (textView != null) {
                        i = R.id.tv_desc;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_desc);
                        if (textView2 != null) {
                            i = R.id.tv_retry;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_retry);
                            if (textView3 != null) {
                                i = R.id.tv_switch_line;
                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_switch_line);
                                if (textView4 != null) {
                                    i = R.id.tv_title;
                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                                    if (textView5 != null) {
                                        return new DialogUpgradeProgressBinding((RelativeLayout) view, linearLayout, linearLayout2, progressBar, textView, textView2, textView3, textView4, textView5);
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
