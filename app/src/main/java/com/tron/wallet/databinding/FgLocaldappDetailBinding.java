package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class FgLocaldappDetailBinding implements ViewBinding {
    public final ImageView ivMessageSignTips;
    public final RelativeLayout layoutTips;
    public final LinearLayout messageContent;
    public final TextView messageDescription;
    private final RelativeLayout rootView;
    public final ScrollView scrollViewMessageContent;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FgLocaldappDetailBinding(RelativeLayout relativeLayout, ImageView imageView, RelativeLayout relativeLayout2, LinearLayout linearLayout, TextView textView, ScrollView scrollView) {
        this.rootView = relativeLayout;
        this.ivMessageSignTips = imageView;
        this.layoutTips = relativeLayout2;
        this.messageContent = linearLayout;
        this.messageDescription = textView;
        this.scrollViewMessageContent = scrollView;
    }

    public static FgLocaldappDetailBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FgLocaldappDetailBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fg_localdapp_detail, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FgLocaldappDetailBinding bind(View view) {
        int i = R.id.iv_message_sign_tips;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_message_sign_tips);
        if (imageView != null) {
            i = R.id.layout_tips;
            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.layout_tips);
            if (relativeLayout != null) {
                i = R.id.message_content;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.message_content);
                if (linearLayout != null) {
                    i = R.id.message_description;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.message_description);
                    if (textView != null) {
                        i = R.id.scrollView_message_content;
                        ScrollView scrollView = (ScrollView) ViewBindings.findChildViewById(view, R.id.scrollView_message_content);
                        if (scrollView != null) {
                            return new FgLocaldappDetailBinding((RelativeLayout) view, imageView, relativeLayout, linearLayout, textView, scrollView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
