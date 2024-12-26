package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class DgModifyPermissionExitBinding implements ViewBinding {
    private final RelativeLayout rootView;
    public final RelativeLayout top;
    public final TextView tvCancle;
    public final TextView tvExit;
    public final TextView tvSubtitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private DgModifyPermissionExitBinding(RelativeLayout relativeLayout, RelativeLayout relativeLayout2, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = relativeLayout;
        this.top = relativeLayout2;
        this.tvCancle = textView;
        this.tvExit = textView2;
        this.tvSubtitle = textView3;
    }

    public static DgModifyPermissionExitBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DgModifyPermissionExitBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.dg_modify_permission_exit, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static DgModifyPermissionExitBinding bind(View view) {
        int i = R.id.top;
        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.top);
        if (relativeLayout != null) {
            i = R.id.tv_cancle;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_cancle);
            if (textView != null) {
                i = R.id.tv_exit;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_exit);
                if (textView2 != null) {
                    i = R.id.tv_subtitle;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_subtitle);
                    if (textView3 != null) {
                        return new DgModifyPermissionExitBinding((RelativeLayout) view, relativeLayout, textView, textView2, textView3);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
