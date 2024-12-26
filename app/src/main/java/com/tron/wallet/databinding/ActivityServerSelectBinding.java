package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.SwitchButton;
import com.tronlinkpro.wallet.R;
public final class ActivityServerSelectBinding implements ViewBinding {
    public final TextView autoTitle;
    public final TextView delaytime1;
    public final TextView delaytime2;
    public final ImageView ivSelect1;
    public final ImageView ivSelect2;
    public final LinearLayout llChooseServer;
    private final LinearLayout rootView;
    public final RelativeLayout sigaporLayout;
    public final SwitchButton switchButton;
    public final TextView tips;
    public final TextView tvNodeName;
    public final TextView tvNodeName2;
    public final RelativeLayout usaLayout;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private ActivityServerSelectBinding(LinearLayout linearLayout, TextView textView, TextView textView2, TextView textView3, ImageView imageView, ImageView imageView2, LinearLayout linearLayout2, RelativeLayout relativeLayout, SwitchButton switchButton, TextView textView4, TextView textView5, TextView textView6, RelativeLayout relativeLayout2) {
        this.rootView = linearLayout;
        this.autoTitle = textView;
        this.delaytime1 = textView2;
        this.delaytime2 = textView3;
        this.ivSelect1 = imageView;
        this.ivSelect2 = imageView2;
        this.llChooseServer = linearLayout2;
        this.sigaporLayout = relativeLayout;
        this.switchButton = switchButton;
        this.tips = textView4;
        this.tvNodeName = textView5;
        this.tvNodeName2 = textView6;
        this.usaLayout = relativeLayout2;
    }

    public static ActivityServerSelectBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityServerSelectBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_server_select, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityServerSelectBinding bind(View view) {
        int i = R.id.auto_title;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.auto_title);
        if (textView != null) {
            i = R.id.delaytime1;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.delaytime1);
            if (textView2 != null) {
                i = R.id.delaytime2;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.delaytime2);
                if (textView3 != null) {
                    i = R.id.iv_select1;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_select1);
                    if (imageView != null) {
                        i = R.id.iv_select2;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_select2);
                        if (imageView2 != null) {
                            i = R.id.ll_choose_server;
                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_choose_server);
                            if (linearLayout != null) {
                                i = R.id.sigapor_layout;
                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.sigapor_layout);
                                if (relativeLayout != null) {
                                    i = R.id.switch_button;
                                    SwitchButton switchButton = (SwitchButton) ViewBindings.findChildViewById(view, R.id.switch_button);
                                    if (switchButton != null) {
                                        i = R.id.tips;
                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tips);
                                        if (textView4 != null) {
                                            i = R.id.tv_node_name;
                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_node_name);
                                            if (textView5 != null) {
                                                i = R.id.tv_node_name2;
                                                TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_node_name2);
                                                if (textView6 != null) {
                                                    i = R.id.usa_layout;
                                                    RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.usa_layout);
                                                    if (relativeLayout2 != null) {
                                                        return new ActivityServerSelectBinding((LinearLayout) view, textView, textView2, textView3, imageView, imageView2, linearLayout, relativeLayout, switchButton, textView4, textView5, textView6, relativeLayout2);
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
