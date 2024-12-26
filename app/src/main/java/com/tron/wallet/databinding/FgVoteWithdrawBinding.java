package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class FgVoteWithdrawBinding implements ViewBinding {
    public final Button btSend;
    public final LinearLayout ivCloseTwo;
    public final LinearLayout rlPassword;
    public final RelativeLayout rlTopTwo;
    public final RelativeLayout root;
    private final RelativeLayout rootView;
    public final TextView tvAddress;
    public final TextView tvLeftAddress;
    public final TextView tvLeftReceive;
    public final TextView tvReceive;
    public final TextView tvResource;
    public final TextView tvTitle;
    public final View vMiddleTwo;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FgVoteWithdrawBinding(RelativeLayout relativeLayout, Button button, LinearLayout linearLayout, LinearLayout linearLayout2, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, View view) {
        this.rootView = relativeLayout;
        this.btSend = button;
        this.ivCloseTwo = linearLayout;
        this.rlPassword = linearLayout2;
        this.rlTopTwo = relativeLayout2;
        this.root = relativeLayout3;
        this.tvAddress = textView;
        this.tvLeftAddress = textView2;
        this.tvLeftReceive = textView3;
        this.tvReceive = textView4;
        this.tvResource = textView5;
        this.tvTitle = textView6;
        this.vMiddleTwo = view;
    }

    public static FgVoteWithdrawBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FgVoteWithdrawBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fg_vote_withdraw, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FgVoteWithdrawBinding bind(View view) {
        int i = R.id.bt_send;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_send);
        if (button != null) {
            i = R.id.iv_close_two;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.iv_close_two);
            if (linearLayout != null) {
                i = R.id.rl_password;
                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.rl_password);
                if (linearLayout2 != null) {
                    i = R.id.rl_top_two;
                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_top_two);
                    if (relativeLayout != null) {
                        RelativeLayout relativeLayout2 = (RelativeLayout) view;
                        i = R.id.tv_address;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address);
                        if (textView != null) {
                            i = R.id.tv_left_address;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_left_address);
                            if (textView2 != null) {
                                i = R.id.tv_left_receive;
                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_left_receive);
                                if (textView3 != null) {
                                    i = R.id.tv_receive;
                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_receive);
                                    if (textView4 != null) {
                                        i = R.id.tv_resource;
                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_resource);
                                        if (textView5 != null) {
                                            i = R.id.tv_title;
                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                                            if (textView6 != null) {
                                                i = R.id.v_middle_two;
                                                View findChildViewById = ViewBindings.findChildViewById(view, R.id.v_middle_two);
                                                if (findChildViewById != null) {
                                                    return new FgVoteWithdrawBinding(relativeLayout2, button, linearLayout, linearLayout2, relativeLayout, relativeLayout2, textView, textView2, textView3, textView4, textView5, textView6, findChildViewById);
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
