package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class FgBackupMnemonicShadeBinding implements ViewBinding {
    public final Button btBackup;
    public final Button btShow;
    public final ImageView camera;
    public final LinearLayout llTip1;
    private final RelativeLayout rootView;
    public final TextView tvCenter;
    public final TextView tvTop;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FgBackupMnemonicShadeBinding(RelativeLayout relativeLayout, Button button, Button button2, ImageView imageView, LinearLayout linearLayout, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.btBackup = button;
        this.btShow = button2;
        this.camera = imageView;
        this.llTip1 = linearLayout;
        this.tvCenter = textView;
        this.tvTop = textView2;
    }

    public static FgBackupMnemonicShadeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FgBackupMnemonicShadeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fg_backup_mnemonic_shade, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FgBackupMnemonicShadeBinding bind(View view) {
        int i = R.id.bt_backup;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_backup);
        if (button != null) {
            i = R.id.bt_show;
            Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.bt_show);
            if (button2 != null) {
                i = R.id.camera;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.camera);
                if (imageView != null) {
                    i = R.id.ll_tip1;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_tip1);
                    if (linearLayout != null) {
                        i = R.id.tv_center;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_center);
                        if (textView != null) {
                            i = R.id.tv_top;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_top);
                            if (textView2 != null) {
                                return new FgBackupMnemonicShadeBinding((RelativeLayout) view, button, button2, imageView, linearLayout, textView, textView2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
