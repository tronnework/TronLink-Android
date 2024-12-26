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
public final class ActivityMigrateBinding implements ViewBinding {
    public final TextView aboutMigrateTitle;
    public final Button btnMigrate;
    public final ImageView ivDot;
    public final ImageView ivIcon;
    public final ImageView ivMigrateOut;
    public final ImageView ivMigrateWarning;
    public final LinearLayout note0;
    public final LinearLayout note1;
    public final LinearLayout note2;
    public final RelativeLayout rlInfo;
    public final RelativeLayout rlWarning;
    private final RelativeLayout rootView;
    public final TextView tvAboutInfo;
    public final TextView tvLabel;
    public final TextView tvNote0;
    public final TextView tvNote1;
    public final TextView tvNote2;
    public final TextView tvNote3;
    public final TextView tvTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ActivityMigrateBinding(RelativeLayout relativeLayout, TextView textView, Button button, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8) {
        this.rootView = relativeLayout;
        this.aboutMigrateTitle = textView;
        this.btnMigrate = button;
        this.ivDot = imageView;
        this.ivIcon = imageView2;
        this.ivMigrateOut = imageView3;
        this.ivMigrateWarning = imageView4;
        this.note0 = linearLayout;
        this.note1 = linearLayout2;
        this.note2 = linearLayout3;
        this.rlInfo = relativeLayout2;
        this.rlWarning = relativeLayout3;
        this.tvAboutInfo = textView2;
        this.tvLabel = textView3;
        this.tvNote0 = textView4;
        this.tvNote1 = textView5;
        this.tvNote2 = textView6;
        this.tvNote3 = textView7;
        this.tvTitle = textView8;
    }

    public static ActivityMigrateBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityMigrateBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_migrate, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityMigrateBinding bind(View view) {
        int i = R.id.about_migrate_title;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.about_migrate_title);
        if (textView != null) {
            i = R.id.btn_migrate;
            Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_migrate);
            if (button != null) {
                i = R.id.iv_dot;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_dot);
                if (imageView != null) {
                    i = R.id.iv_icon;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_icon);
                    if (imageView2 != null) {
                        i = R.id.iv_migrate_out;
                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_migrate_out);
                        if (imageView3 != null) {
                            i = R.id.iv_migrate_warning;
                            ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_migrate_warning);
                            if (imageView4 != null) {
                                i = R.id.note_0;
                                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.note_0);
                                if (linearLayout != null) {
                                    i = R.id.note_1;
                                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.note_1);
                                    if (linearLayout2 != null) {
                                        i = R.id.note_2;
                                        LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.note_2);
                                        if (linearLayout3 != null) {
                                            i = R.id.rl_info;
                                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_info);
                                            if (relativeLayout != null) {
                                                i = R.id.rl_warning;
                                                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_warning);
                                                if (relativeLayout2 != null) {
                                                    i = R.id.tv_about_info;
                                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_about_info);
                                                    if (textView2 != null) {
                                                        i = R.id.tv_label;
                                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_label);
                                                        if (textView3 != null) {
                                                            i = R.id.tv_note_0;
                                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_note_0);
                                                            if (textView4 != null) {
                                                                i = R.id.tv_note_1;
                                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_note_1);
                                                                if (textView5 != null) {
                                                                    i = R.id.tv_note_2;
                                                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_note_2);
                                                                    if (textView6 != null) {
                                                                        i = R.id.tv_note_3;
                                                                        TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_note_3);
                                                                        if (textView7 != null) {
                                                                            i = R.id.tv_title;
                                                                            TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                                                                            if (textView8 != null) {
                                                                                return new ActivityMigrateBinding((RelativeLayout) view, textView, button, imageView, imageView2, imageView3, imageView4, linearLayout, linearLayout2, linearLayout3, relativeLayout, relativeLayout2, textView2, textView3, textView4, textView5, textView6, textView7, textView8);
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
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
