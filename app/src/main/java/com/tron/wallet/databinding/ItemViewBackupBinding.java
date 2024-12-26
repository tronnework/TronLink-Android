package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemViewBackupBinding implements ViewBinding {
    public final Button btnWaring;
    public final TextView editText;
    public final ImageView ivCamera;
    public final ImageView ivWarningBackground;
    public final NestedScrollView llContent;
    public final Button llCopy;
    public final Button llQrCode;
    public final RelativeLayout llWarning;
    private final ConstraintLayout rootView;
    public final TextView tvDesc;
    public final TextView tvTitle;
    public final TextView tvWarning;

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    private ItemViewBackupBinding(ConstraintLayout constraintLayout, Button button, TextView textView, ImageView imageView, ImageView imageView2, NestedScrollView nestedScrollView, Button button2, Button button3, RelativeLayout relativeLayout, TextView textView2, TextView textView3, TextView textView4) {
        this.rootView = constraintLayout;
        this.btnWaring = button;
        this.editText = textView;
        this.ivCamera = imageView;
        this.ivWarningBackground = imageView2;
        this.llContent = nestedScrollView;
        this.llCopy = button2;
        this.llQrCode = button3;
        this.llWarning = relativeLayout;
        this.tvDesc = textView2;
        this.tvTitle = textView3;
        this.tvWarning = textView4;
    }

    public static ItemViewBackupBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemViewBackupBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_view_backup, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemViewBackupBinding bind(View view) {
        int i = R.id.btn_waring;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_waring);
        if (button != null) {
            i = R.id.editText;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.editText);
            if (textView != null) {
                i = R.id.iv_camera;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_camera);
                if (imageView != null) {
                    i = R.id.iv_warning_background;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_warning_background);
                    if (imageView2 != null) {
                        i = R.id.ll_content;
                        NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(view, R.id.ll_content);
                        if (nestedScrollView != null) {
                            i = R.id.ll_copy;
                            Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.ll_copy);
                            if (button2 != null) {
                                i = R.id.ll_qr_code;
                                Button button3 = (Button) ViewBindings.findChildViewById(view, R.id.ll_qr_code);
                                if (button3 != null) {
                                    i = R.id.ll_warning;
                                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_warning);
                                    if (relativeLayout != null) {
                                        i = R.id.tv_desc;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_desc);
                                        if (textView2 != null) {
                                            i = R.id.tv_title;
                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                                            if (textView3 != null) {
                                                i = R.id.tv_warning;
                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_warning);
                                                if (textView4 != null) {
                                                    return new ItemViewBackupBinding((ConstraintLayout) view, button, textView, imageView, imageView2, nestedScrollView, button2, button3, relativeLayout, textView2, textView3, textView4);
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
