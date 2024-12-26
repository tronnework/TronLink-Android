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
public final class FgConfirmVoteBinding implements ViewBinding {
    public final Button btGo;
    public final RecyclerView rcList;
    public final RelativeLayout rlTop;
    private final LinearLayout rootView;
    public final TextView tvAddress;
    public final TextView tvCostBandwidth;
    public final TextView tvEmpty;
    public final TextView tvNote;
    public final TextView tvSumvote;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private FgConfirmVoteBinding(LinearLayout linearLayout, Button button, RecyclerView recyclerView, RelativeLayout relativeLayout, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5) {
        this.rootView = linearLayout;
        this.btGo = button;
        this.rcList = recyclerView;
        this.rlTop = relativeLayout;
        this.tvAddress = textView;
        this.tvCostBandwidth = textView2;
        this.tvEmpty = textView3;
        this.tvNote = textView4;
        this.tvSumvote = textView5;
    }

    public static FgConfirmVoteBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FgConfirmVoteBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fg_confirm_vote, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FgConfirmVoteBinding bind(View view) {
        int i = R.id.bt_go;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_go);
        if (button != null) {
            i = R.id.rc_list;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rc_list);
            if (recyclerView != null) {
                i = R.id.rl_top;
                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_top);
                if (relativeLayout != null) {
                    i = R.id.tv_address;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address);
                    if (textView != null) {
                        i = R.id.tv_cost_bandwidth;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_cost_bandwidth);
                        if (textView2 != null) {
                            i = R.id.tv_empty;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_empty);
                            if (textView3 != null) {
                                i = R.id.tv_note;
                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_note);
                                if (textView4 != null) {
                                    i = R.id.tv_sumvote;
                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_sumvote);
                                    if (textView5 != null) {
                                        return new FgConfirmVoteBinding((LinearLayout) view, button, recyclerView, relativeLayout, textView, textView2, textView3, textView4, textView5);
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
