package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemMessageCenterBinding implements ViewBinding {
    public final RelativeLayout itemLayout;
    public final ImageView ivIcon;
    public final RelativeLayout rlInfo;
    public final RelativeLayout rlTitle;
    private final RelativeLayout rootView;
    public final TextView tvAccountReceive;
    public final TextView tvAccountReceiveTitle;
    public final TextView tvDate;
    public final TextView tvOutAccount;
    public final TextView tvOutAccountTitle;
    public final TextView tvTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemMessageCenterBinding(RelativeLayout relativeLayout, RelativeLayout relativeLayout2, ImageView imageView, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6) {
        this.rootView = relativeLayout;
        this.itemLayout = relativeLayout2;
        this.ivIcon = imageView;
        this.rlInfo = relativeLayout3;
        this.rlTitle = relativeLayout4;
        this.tvAccountReceive = textView;
        this.tvAccountReceiveTitle = textView2;
        this.tvDate = textView3;
        this.tvOutAccount = textView4;
        this.tvOutAccountTitle = textView5;
        this.tvTitle = textView6;
    }

    public static ItemMessageCenterBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemMessageCenterBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_message_center, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemMessageCenterBinding bind(View view) {
        RelativeLayout relativeLayout = (RelativeLayout) view;
        int i = R.id.iv_icon;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_icon);
        if (imageView != null) {
            i = R.id.rl_info;
            RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_info);
            if (relativeLayout2 != null) {
                i = R.id.rl_title;
                RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_title);
                if (relativeLayout3 != null) {
                    i = R.id.tv_account_receive;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_account_receive);
                    if (textView != null) {
                        i = R.id.tv_account_receive_title;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_account_receive_title);
                        if (textView2 != null) {
                            i = R.id.tv_date;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_date);
                            if (textView3 != null) {
                                i = R.id.tv_out_account;
                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_out_account);
                                if (textView4 != null) {
                                    i = R.id.tv_out_account_title;
                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_out_account_title);
                                    if (textView5 != null) {
                                        i = R.id.tv_title;
                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                                        if (textView6 != null) {
                                            return new ItemMessageCenterBinding(relativeLayout, relativeLayout, imageView, relativeLayout2, relativeLayout3, textView, textView2, textView3, textView4, textView5, textView6);
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
