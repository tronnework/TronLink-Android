package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ActivityExportWalletResultBinding implements ViewBinding {
    public final Button btnDone;
    public final ImageView ivIcon;
    public final ImageView ivIconImport;
    public final LinearLayout llFailedList;
    public final LinearLayout llTitle;
    public final RelativeLayout rlResultExport;
    public final RelativeLayout rlResultImport;
    private final RelativeLayout rootView;
    public final RecyclerView rvFailed;
    public final TextView tvSubtitle;
    public final TextView tvSubtitleExport;
    public final TextView tvTitle;
    public final TextView tvTitleImport;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ActivityExportWalletResultBinding(RelativeLayout relativeLayout, Button button, ImageView imageView, ImageView imageView2, LinearLayout linearLayout, LinearLayout linearLayout2, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RecyclerView recyclerView, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        this.rootView = relativeLayout;
        this.btnDone = button;
        this.ivIcon = imageView;
        this.ivIconImport = imageView2;
        this.llFailedList = linearLayout;
        this.llTitle = linearLayout2;
        this.rlResultExport = relativeLayout2;
        this.rlResultImport = relativeLayout3;
        this.rvFailed = recyclerView;
        this.tvSubtitle = textView;
        this.tvSubtitleExport = textView2;
        this.tvTitle = textView3;
        this.tvTitleImport = textView4;
    }

    public static ActivityExportWalletResultBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityExportWalletResultBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_export_wallet_result, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityExportWalletResultBinding bind(View view) {
        int i = R.id.btn_done;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_done);
        if (button != null) {
            i = R.id.iv_icon;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_icon);
            if (imageView != null) {
                i = R.id.iv_icon_import;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_icon_import);
                if (imageView2 != null) {
                    i = R.id.ll_failed_list;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_failed_list);
                    if (linearLayout != null) {
                        i = R.id.ll_title;
                        LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_title);
                        if (linearLayout2 != null) {
                            i = R.id.rl_result_export;
                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_result_export);
                            if (relativeLayout != null) {
                                i = R.id.rl_result_import;
                                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_result_import);
                                if (relativeLayout2 != null) {
                                    i = R.id.rv_failed;
                                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rv_failed);
                                    if (recyclerView != null) {
                                        i = R.id.tv_subtitle;
                                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_subtitle);
                                        if (textView != null) {
                                            i = R.id.tv_subtitle_export;
                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_subtitle_export);
                                            if (textView2 != null) {
                                                i = R.id.tv_title;
                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                                                if (textView3 != null) {
                                                    i = R.id.tv_title_import;
                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title_import);
                                                    if (textView4 != null) {
                                                        return new ActivityExportWalletResultBinding((RelativeLayout) view, button, imageView, imageView2, linearLayout, linearLayout2, relativeLayout, relativeLayout2, recyclerView, textView, textView2, textView3, textView4);
                                                    }
                                                }
                                            }
                                        }
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
