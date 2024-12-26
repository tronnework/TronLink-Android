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
public final class DgThirdInputDialogBinding implements ViewBinding {
    public final Button btnContinue;
    public final Button btnSwitchInput;
    public final CheckBox ck;
    public final ImageView ivTitle;
    public final LinearLayout llAction;
    public final LinearLayout llContent;
    public final LinearLayout llTitle;
    private final RelativeLayout rootView;
    public final TextView title;
    public final RelativeLayout top;
    public final TextView tvContent;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private DgThirdInputDialogBinding(RelativeLayout relativeLayout, Button button, Button button2, CheckBox checkBox, ImageView imageView, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, TextView textView, RelativeLayout relativeLayout2, TextView textView2) {
        this.rootView = relativeLayout;
        this.btnContinue = button;
        this.btnSwitchInput = button2;
        this.ck = checkBox;
        this.ivTitle = imageView;
        this.llAction = linearLayout;
        this.llContent = linearLayout2;
        this.llTitle = linearLayout3;
        this.title = textView;
        this.top = relativeLayout2;
        this.tvContent = textView2;
    }

    public static DgThirdInputDialogBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DgThirdInputDialogBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.dg_third_input_dialog, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static DgThirdInputDialogBinding bind(View view) {
        int i = R.id.btn_continue;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_continue);
        if (button != null) {
            i = R.id.btn_switch_input;
            Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.btn_switch_input);
            if (button2 != null) {
                i = R.id.ck;
                CheckBox checkBox = (CheckBox) ViewBindings.findChildViewById(view, R.id.ck);
                if (checkBox != null) {
                    i = R.id.iv_title;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_title);
                    if (imageView != null) {
                        i = R.id.ll_action;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_action);
                        if (linearLayout != null) {
                            i = R.id.ll_content;
                            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_content);
                            if (linearLayout2 != null) {
                                i = R.id.ll_title;
                                LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_title);
                                if (linearLayout3 != null) {
                                    i = R.id.title;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.title);
                                    if (textView != null) {
                                        i = R.id.top;
                                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.top);
                                        if (relativeLayout != null) {
                                            i = R.id.tv_content;
                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_content);
                                            if (textView2 != null) {
                                                return new DgThirdInputDialogBinding((RelativeLayout) view, button, button2, checkBox, imageView, linearLayout, linearLayout2, linearLayout3, textView, relativeLayout, textView2);
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
