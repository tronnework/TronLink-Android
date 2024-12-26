package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.CircularImageDraweeView;
import com.tronlinkpro.wallet.R;
public final class ItemTokenSelectBinding implements ViewBinding {
    public final CheckBox checkbox;
    public final RelativeLayout itemLayout;
    public final LinearLayout llInfo;
    private final RelativeLayout rootView;
    public final CircularImageDraweeView tokenIcon;
    public final TextView tvTokenAddress;
    public final TextView tvTokenName;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemTokenSelectBinding(RelativeLayout relativeLayout, CheckBox checkBox, RelativeLayout relativeLayout2, LinearLayout linearLayout, CircularImageDraweeView circularImageDraweeView, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.checkbox = checkBox;
        this.itemLayout = relativeLayout2;
        this.llInfo = linearLayout;
        this.tokenIcon = circularImageDraweeView;
        this.tvTokenAddress = textView;
        this.tvTokenName = textView2;
    }

    public static ItemTokenSelectBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemTokenSelectBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_token_select, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemTokenSelectBinding bind(View view) {
        int i = R.id.checkbox;
        CheckBox checkBox = (CheckBox) ViewBindings.findChildViewById(view, R.id.checkbox);
        if (checkBox != null) {
            RelativeLayout relativeLayout = (RelativeLayout) view;
            i = R.id.ll_info;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_info);
            if (linearLayout != null) {
                i = R.id.token_icon;
                CircularImageDraweeView circularImageDraweeView = (CircularImageDraweeView) ViewBindings.findChildViewById(view, R.id.token_icon);
                if (circularImageDraweeView != null) {
                    i = R.id.tv_token_address;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_token_address);
                    if (textView != null) {
                        i = R.id.tv_token_name;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_token_name);
                        if (textView2 != null) {
                            return new ItemTokenSelectBinding(relativeLayout, checkBox, relativeLayout, linearLayout, circularImageDraweeView, textView, textView2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
