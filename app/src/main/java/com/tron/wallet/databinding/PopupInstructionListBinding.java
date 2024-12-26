package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class PopupInstructionListBinding implements ViewBinding {
    public final Button btnGot;
    public final ImageView iv;
    private final RelativeLayout rootView;
    public final RecyclerView rvList;
    public final TextView tv;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private PopupInstructionListBinding(RelativeLayout relativeLayout, Button button, ImageView imageView, RecyclerView recyclerView, TextView textView) {
        this.rootView = relativeLayout;
        this.btnGot = button;
        this.iv = imageView;
        this.rvList = recyclerView;
        this.tv = textView;
    }

    public static PopupInstructionListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopupInstructionListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.popup_instruction_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopupInstructionListBinding bind(View view) {
        int i = R.id.btn_got;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_got);
        if (button != null) {
            i = R.id.iv;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv);
            if (imageView != null) {
                i = R.id.rv_list;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rv_list);
                if (recyclerView != null) {
                    i = R.id.tv;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv);
                    if (textView != null) {
                        return new PopupInstructionListBinding((RelativeLayout) view, button, imageView, recyclerView, textView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
