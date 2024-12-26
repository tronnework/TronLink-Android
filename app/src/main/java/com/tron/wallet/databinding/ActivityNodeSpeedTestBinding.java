package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ActivityNodeSpeedTestBinding implements ViewBinding {
    public final Button btnAddNode;
    public final RecyclerView rcList;
    private final RelativeLayout rootView;
    public final TextView tvChainId;
    public final TextView tvFullNode;
    public final TextView tvSolidityNode;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ActivityNodeSpeedTestBinding(RelativeLayout relativeLayout, Button button, RecyclerView recyclerView, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = relativeLayout;
        this.btnAddNode = button;
        this.rcList = recyclerView;
        this.tvChainId = textView;
        this.tvFullNode = textView2;
        this.tvSolidityNode = textView3;
    }

    public static ActivityNodeSpeedTestBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityNodeSpeedTestBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_node_speed_test, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityNodeSpeedTestBinding bind(View view) {
        int i = R.id.btn_add_node;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_add_node);
        if (button != null) {
            i = R.id.rc_list;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rc_list);
            if (recyclerView != null) {
                i = R.id.tv_chain_id;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_chain_id);
                if (textView != null) {
                    i = R.id.tv_full_node;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_full_node);
                    if (textView2 != null) {
                        i = R.id.tv_solidity_node;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_solidity_node);
                        if (textView3 != null) {
                            return new ActivityNodeSpeedTestBinding((RelativeLayout) view, button, recyclerView, textView, textView2, textView3);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
