package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.business.confirm.fg.component.GlobalTitleHeaderView;
import com.tronlinkpro.wallet.R;
public final class PopLocaldappBinding implements ViewBinding {
    public final ConstraintLayout content;
    public final GlobalTitleHeaderView headerView;
    public final ImageView ivMessageSignTips;
    public final RelativeLayout layoutTips;
    public final TextView messageContent;
    public final TextView messageDescription;
    public final Button ok;
    private final RelativeLayout rootView;
    public final RelativeLayout rootview;
    public final ScrollView scrollViewMessageContent;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private PopLocaldappBinding(RelativeLayout relativeLayout, ConstraintLayout constraintLayout, GlobalTitleHeaderView globalTitleHeaderView, ImageView imageView, RelativeLayout relativeLayout2, TextView textView, TextView textView2, Button button, RelativeLayout relativeLayout3, ScrollView scrollView) {
        this.rootView = relativeLayout;
        this.content = constraintLayout;
        this.headerView = globalTitleHeaderView;
        this.ivMessageSignTips = imageView;
        this.layoutTips = relativeLayout2;
        this.messageContent = textView;
        this.messageDescription = textView2;
        this.ok = button;
        this.rootview = relativeLayout3;
        this.scrollViewMessageContent = scrollView;
    }

    public static PopLocaldappBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopLocaldappBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.pop_localdapp, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopLocaldappBinding bind(View view) {
        int i = R.id.content;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.content);
        if (constraintLayout != null) {
            i = R.id.header_view;
            GlobalTitleHeaderView globalTitleHeaderView = (GlobalTitleHeaderView) ViewBindings.findChildViewById(view, R.id.header_view);
            if (globalTitleHeaderView != null) {
                i = R.id.iv_message_sign_tips;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_message_sign_tips);
                if (imageView != null) {
                    i = R.id.layout_tips;
                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.layout_tips);
                    if (relativeLayout != null) {
                        i = R.id.message_content;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.message_content);
                        if (textView != null) {
                            i = R.id.message_description;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.message_description);
                            if (textView2 != null) {
                                i = R.id.ok;
                                Button button = (Button) ViewBindings.findChildViewById(view, R.id.ok);
                                if (button != null) {
                                    i = R.id.rootview;
                                    RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rootview);
                                    if (relativeLayout2 != null) {
                                        i = R.id.scrollView_message_content;
                                        ScrollView scrollView = (ScrollView) ViewBindings.findChildViewById(view, R.id.scrollView_message_content);
                                        if (scrollView != null) {
                                            return new PopLocaldappBinding((RelativeLayout) view, constraintLayout, globalTitleHeaderView, imageView, relativeLayout, textView, textView2, button, relativeLayout2, scrollView);
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
