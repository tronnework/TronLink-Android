package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class PopSeleted1Binding implements ViewBinding {
    public final LinearLayout content;
    public final View line;
    public final LinearLayout llCancle;
    public final LinearLayout llCopyUrl;
    public final LinearLayout llDebug;
    public final LinearLayout llSafariOpen;
    private final RelativeLayout rootView;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private PopSeleted1Binding(RelativeLayout relativeLayout, LinearLayout linearLayout, View view, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, LinearLayout linearLayout5) {
        this.rootView = relativeLayout;
        this.content = linearLayout;
        this.line = view;
        this.llCancle = linearLayout2;
        this.llCopyUrl = linearLayout3;
        this.llDebug = linearLayout4;
        this.llSafariOpen = linearLayout5;
    }

    public static PopSeleted1Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopSeleted1Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.pop_seleted1, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopSeleted1Binding bind(View view) {
        int i = R.id.content;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.content);
        if (linearLayout != null) {
            i = R.id.line;
            View findChildViewById = ViewBindings.findChildViewById(view, R.id.line);
            if (findChildViewById != null) {
                i = R.id.ll_cancle;
                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_cancle);
                if (linearLayout2 != null) {
                    i = R.id.ll_copy_url;
                    LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_copy_url);
                    if (linearLayout3 != null) {
                        i = R.id.ll_debug;
                        LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_debug);
                        if (linearLayout4 != null) {
                            i = R.id.ll_safari_open;
                            LinearLayout linearLayout5 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_safari_open);
                            if (linearLayout5 != null) {
                                return new PopSeleted1Binding((RelativeLayout) view, linearLayout, findChildViewById, linearLayout2, linearLayout3, linearLayout4, linearLayout5);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
