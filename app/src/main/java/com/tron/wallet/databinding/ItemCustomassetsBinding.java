package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.SwitchView;
import com.tronlinkpro.wallet.R;
public final class ItemCustomassetsBinding implements ViewBinding {
    public final View line;
    public final RecyclerView rcList;
    public final RelativeLayout rlTop;
    public final RelativeLayout root;
    private final RelativeLayout rootView;
    public final SwitchView switchview;
    public final TextView tvHiddenPrompt;
    public final TextView tvTop;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemCustomassetsBinding(RelativeLayout relativeLayout, View view, RecyclerView recyclerView, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, SwitchView switchView, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.line = view;
        this.rcList = recyclerView;
        this.rlTop = relativeLayout2;
        this.root = relativeLayout3;
        this.switchview = switchView;
        this.tvHiddenPrompt = textView;
        this.tvTop = textView2;
    }

    public static ItemCustomassetsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemCustomassetsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_customassets, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemCustomassetsBinding bind(View view) {
        int i = R.id.line;
        View findChildViewById = ViewBindings.findChildViewById(view, R.id.line);
        if (findChildViewById != null) {
            i = R.id.rc_list;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rc_list);
            if (recyclerView != null) {
                i = R.id.rl_top;
                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_top);
                if (relativeLayout != null) {
                    RelativeLayout relativeLayout2 = (RelativeLayout) view;
                    i = R.id.switchview;
                    SwitchView switchView = (SwitchView) ViewBindings.findChildViewById(view, R.id.switchview);
                    if (switchView != null) {
                        i = R.id.tv_hidden_prompt;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_hidden_prompt);
                        if (textView != null) {
                            i = R.id.tv_top;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_top);
                            if (textView2 != null) {
                                return new ItemCustomassetsBinding(relativeLayout2, findChildViewById, recyclerView, relativeLayout, relativeLayout2, switchView, textView, textView2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
