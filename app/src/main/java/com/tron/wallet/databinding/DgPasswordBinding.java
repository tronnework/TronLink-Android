package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class DgPasswordBinding implements ViewBinding {
    public final Button cancle;
    public final RelativeLayout doublebutton;
    public final TextView innertitle;
    public final ImageView ivStatus;
    public final Button knew;
    private final RelativeLayout rootView;
    public final Button see;
    public final TextView title;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private DgPasswordBinding(RelativeLayout relativeLayout, Button button, RelativeLayout relativeLayout2, TextView textView, ImageView imageView, Button button2, Button button3, TextView textView2) {
        this.rootView = relativeLayout;
        this.cancle = button;
        this.doublebutton = relativeLayout2;
        this.innertitle = textView;
        this.ivStatus = imageView;
        this.knew = button2;
        this.see = button3;
        this.title = textView2;
    }

    public static DgPasswordBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DgPasswordBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.dg_password, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static DgPasswordBinding bind(View view) {
        int i = R.id.cancle;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.cancle);
        if (button != null) {
            i = R.id.doublebutton;
            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.doublebutton);
            if (relativeLayout != null) {
                i = R.id.innertitle;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.innertitle);
                if (textView != null) {
                    i = R.id.iv_status;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_status);
                    if (imageView != null) {
                        i = R.id.knew;
                        Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.knew);
                        if (button2 != null) {
                            i = R.id.see;
                            Button button3 = (Button) ViewBindings.findChildViewById(view, R.id.see);
                            if (button3 != null) {
                                i = R.id.title;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.title);
                                if (textView2 != null) {
                                    return new DgPasswordBinding((RelativeLayout) view, button, relativeLayout, textView, imageView, button2, button3, textView2);
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
