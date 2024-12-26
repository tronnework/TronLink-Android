package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class FgVoteComfirmListBinding implements ViewBinding {
    public final Button btSend;
    public final RecyclerView rcView;
    public final LinearLayout rlPassword;
    public final RelativeLayout rlThird;
    public final RelativeLayout rlTop;
    public final RelativeLayout root;
    private final RelativeLayout rootView;
    public final TextView tvAddress;
    public final TextView tvLeftAddress;
    public final TextView tvResource;
    public final TextView tvTotal;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FgVoteComfirmListBinding(RelativeLayout relativeLayout, Button button, RecyclerView recyclerView, LinearLayout linearLayout, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        this.rootView = relativeLayout;
        this.btSend = button;
        this.rcView = recyclerView;
        this.rlPassword = linearLayout;
        this.rlThird = relativeLayout2;
        this.rlTop = relativeLayout3;
        this.root = relativeLayout4;
        this.tvAddress = textView;
        this.tvLeftAddress = textView2;
        this.tvResource = textView3;
        this.tvTotal = textView4;
    }

    public static FgVoteComfirmListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FgVoteComfirmListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fg_vote_comfirm_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FgVoteComfirmListBinding bind(View view) {
        int i = R.id.bt_send;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_send);
        if (button != null) {
            i = R.id.rc_view;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rc_view);
            if (recyclerView != null) {
                i = R.id.rl_password;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.rl_password);
                if (linearLayout != null) {
                    i = R.id.rl_third;
                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_third);
                    if (relativeLayout != null) {
                        i = R.id.rl_top;
                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_top);
                        if (relativeLayout2 != null) {
                            RelativeLayout relativeLayout3 = (RelativeLayout) view;
                            i = R.id.tv_address;
                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address);
                            if (textView != null) {
                                i = R.id.tv_left_address;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_left_address);
                                if (textView2 != null) {
                                    i = R.id.tv_resource;
                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_resource);
                                    if (textView3 != null) {
                                        i = R.id.tv_total;
                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_total);
                                        if (textView4 != null) {
                                            return new FgVoteComfirmListBinding(relativeLayout3, button, recyclerView, linearLayout, relativeLayout, relativeLayout2, relativeLayout3, textView, textView2, textView3, textView4);
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
