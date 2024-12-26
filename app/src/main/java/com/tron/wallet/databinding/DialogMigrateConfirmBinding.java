package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class DialogMigrateConfirmBinding implements ViewBinding {
    public final Button btnMigrate;
    public final CheckBox cbAgree;
    public final ImageView ivIcon;
    public final ImageView ivMigrateWarning;
    public final LinearLayout note0;
    public final LinearLayout note1;
    public final LinearLayout note2;
    public final LinearLayout rlBtns;
    private final RelativeLayout rootView;
    public final TextView tvCancel;
    public final TextView tvTitle;
    public final TextView tvWarning;
    public final TextView tvWarningSubtitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private DialogMigrateConfirmBinding(RelativeLayout relativeLayout, Button button, CheckBox checkBox, ImageView imageView, ImageView imageView2, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        this.rootView = relativeLayout;
        this.btnMigrate = button;
        this.cbAgree = checkBox;
        this.ivIcon = imageView;
        this.ivMigrateWarning = imageView2;
        this.note0 = linearLayout;
        this.note1 = linearLayout2;
        this.note2 = linearLayout3;
        this.rlBtns = linearLayout4;
        this.tvCancel = textView;
        this.tvTitle = textView2;
        this.tvWarning = textView3;
        this.tvWarningSubtitle = textView4;
    }

    public static DialogMigrateConfirmBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogMigrateConfirmBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.dialog_migrate_confirm, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static DialogMigrateConfirmBinding bind(View view) {
        int i = R.id.btn_migrate;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_migrate);
        if (button != null) {
            i = R.id.cb_agree;
            CheckBox checkBox = (CheckBox) ViewBindings.findChildViewById(view, R.id.cb_agree);
            if (checkBox != null) {
                i = R.id.iv_icon;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_icon);
                if (imageView != null) {
                    i = R.id.iv_migrate_warning;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_migrate_warning);
                    if (imageView2 != null) {
                        i = R.id.note_0;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.note_0);
                        if (linearLayout != null) {
                            i = R.id.note_1;
                            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.note_1);
                            if (linearLayout2 != null) {
                                i = R.id.note_2;
                                LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.note_2);
                                if (linearLayout3 != null) {
                                    i = R.id.rl_btns;
                                    LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.rl_btns);
                                    if (linearLayout4 != null) {
                                        i = R.id.tv_cancel;
                                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_cancel);
                                        if (textView != null) {
                                            i = R.id.tv_title;
                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                                            if (textView2 != null) {
                                                i = R.id.tv_warning;
                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_warning);
                                                if (textView3 != null) {
                                                    i = R.id.tv_warning_subtitle;
                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_warning_subtitle);
                                                    if (textView4 != null) {
                                                        return new DialogMigrateConfirmBinding((RelativeLayout) view, button, checkBox, imageView, imageView2, linearLayout, linearLayout2, linearLayout3, linearLayout4, textView, textView2, textView3, textView4);
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
