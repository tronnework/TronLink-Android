package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class AcDownloadApkBinding implements ViewBinding {
    public final LinearLayout llLogo;
    public final RelativeLayout rlRoot;
    private final RelativeLayout rootView;
    public final TextView tvBottomDesc;
    public final TextView tvDesc;
    public final TextView tvGoogleplay;
    public final TextView tvLocalInstall;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcDownloadApkBinding(RelativeLayout relativeLayout, LinearLayout linearLayout, RelativeLayout relativeLayout2, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        this.rootView = relativeLayout;
        this.llLogo = linearLayout;
        this.rlRoot = relativeLayout2;
        this.tvBottomDesc = textView;
        this.tvDesc = textView2;
        this.tvGoogleplay = textView3;
        this.tvLocalInstall = textView4;
    }

    public static AcDownloadApkBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcDownloadApkBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_download_apk, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcDownloadApkBinding bind(View view) {
        int i = R.id.ll_logo;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_logo);
        if (linearLayout != null) {
            RelativeLayout relativeLayout = (RelativeLayout) view;
            i = R.id.tv_bottom_desc;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_bottom_desc);
            if (textView != null) {
                i = R.id.tv_desc;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_desc);
                if (textView2 != null) {
                    i = R.id.tv_googleplay;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_googleplay);
                    if (textView3 != null) {
                        i = R.id.tv_local_install;
                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_local_install);
                        if (textView4 != null) {
                            return new AcDownloadApkBinding(relativeLayout, linearLayout, relativeLayout, textView, textView2, textView3, textView4);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
