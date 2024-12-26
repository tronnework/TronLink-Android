package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class VoteEmptyDialogBinding implements ViewBinding {
    public final TextView btnUnfreeze;
    public final TextView confirm;
    public final TextView info1;
    public final ImageView ivClose;
    public final ConstraintLayout llAction;
    private final RelativeLayout rootView;
    public final TextView title;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private VoteEmptyDialogBinding(RelativeLayout relativeLayout, TextView textView, TextView textView2, TextView textView3, ImageView imageView, ConstraintLayout constraintLayout, TextView textView4) {
        this.rootView = relativeLayout;
        this.btnUnfreeze = textView;
        this.confirm = textView2;
        this.info1 = textView3;
        this.ivClose = imageView;
        this.llAction = constraintLayout;
        this.title = textView4;
    }

    public static VoteEmptyDialogBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static VoteEmptyDialogBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.vote_empty_dialog, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static VoteEmptyDialogBinding bind(View view) {
        int i = R.id.btn_unfreeze;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.btn_unfreeze);
        if (textView != null) {
            i = R.id.confirm;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.confirm);
            if (textView2 != null) {
                i = R.id.info_1;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.info_1);
                if (textView3 != null) {
                    i = R.id.iv_close;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_close);
                    if (imageView != null) {
                        i = R.id.ll_action;
                        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.ll_action);
                        if (constraintLayout != null) {
                            i = R.id.title;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.title);
                            if (textView4 != null) {
                                return new VoteEmptyDialogBinding((RelativeLayout) view, textView, textView2, textView3, imageView, constraintLayout, textView4);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
