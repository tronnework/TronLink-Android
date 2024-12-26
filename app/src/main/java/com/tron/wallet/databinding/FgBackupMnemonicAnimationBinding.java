package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.SimpleDraweeViewGif;
import com.tronlinkpro.wallet.R;
public final class FgBackupMnemonicAnimationBinding implements ViewBinding {
    public final Button btBackup;
    public final SimpleDraweeViewGif gifView;
    private final RelativeLayout rootView;
    public final TextView tvTop;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FgBackupMnemonicAnimationBinding(RelativeLayout relativeLayout, Button button, SimpleDraweeViewGif simpleDraweeViewGif, TextView textView) {
        this.rootView = relativeLayout;
        this.btBackup = button;
        this.gifView = simpleDraweeViewGif;
        this.tvTop = textView;
    }

    public static FgBackupMnemonicAnimationBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FgBackupMnemonicAnimationBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fg_backup_mnemonic_animation, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FgBackupMnemonicAnimationBinding bind(View view) {
        int i = R.id.bt_backup;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_backup);
        if (button != null) {
            i = R.id.gif_view;
            SimpleDraweeViewGif simpleDraweeViewGif = (SimpleDraweeViewGif) ViewBindings.findChildViewById(view, R.id.gif_view);
            if (simpleDraweeViewGif != null) {
                i = R.id.tv_top;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_top);
                if (textView != null) {
                    return new FgBackupMnemonicAnimationBinding((RelativeLayout) view, button, simpleDraweeViewGif, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
