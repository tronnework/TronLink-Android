package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class FgLocaldappMetadataBinding implements ViewBinding {
    public final ImageView ivMessageSignTips;
    public final RelativeLayout layoutTips;
    public final TextView messageContent;
    public final TextView messageContentDomain;
    public final TextView messageContentMessage;
    public final TextView messageDescription;
    private final RelativeLayout rootView;
    public final ScrollView scrollViewMessageContent;
    public final TextView tvCopyHex;
    public final TextView tvDomainCopy;
    public final TextView tvMessageCopy;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FgLocaldappMetadataBinding(RelativeLayout relativeLayout, ImageView imageView, RelativeLayout relativeLayout2, TextView textView, TextView textView2, TextView textView3, TextView textView4, ScrollView scrollView, TextView textView5, TextView textView6, TextView textView7) {
        this.rootView = relativeLayout;
        this.ivMessageSignTips = imageView;
        this.layoutTips = relativeLayout2;
        this.messageContent = textView;
        this.messageContentDomain = textView2;
        this.messageContentMessage = textView3;
        this.messageDescription = textView4;
        this.scrollViewMessageContent = scrollView;
        this.tvCopyHex = textView5;
        this.tvDomainCopy = textView6;
        this.tvMessageCopy = textView7;
    }

    public static FgLocaldappMetadataBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FgLocaldappMetadataBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fg_localdapp_metadata, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FgLocaldappMetadataBinding bind(View view) {
        int i = R.id.iv_message_sign_tips;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_message_sign_tips);
        if (imageView != null) {
            i = R.id.layout_tips;
            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.layout_tips);
            if (relativeLayout != null) {
                i = R.id.message_content;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.message_content);
                if (textView != null) {
                    i = R.id.message_content_domain;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.message_content_domain);
                    if (textView2 != null) {
                        i = R.id.message_content_message;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.message_content_message);
                        if (textView3 != null) {
                            i = R.id.message_description;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.message_description);
                            if (textView4 != null) {
                                i = R.id.scrollView_message_content;
                                ScrollView scrollView = (ScrollView) ViewBindings.findChildViewById(view, R.id.scrollView_message_content);
                                if (scrollView != null) {
                                    i = R.id.tv_copy_hex;
                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_copy_hex);
                                    if (textView5 != null) {
                                        i = R.id.tv_domain_copy;
                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_domain_copy);
                                        if (textView6 != null) {
                                            i = R.id.tv_message_copy;
                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_message_copy);
                                            if (textView7 != null) {
                                                return new FgLocaldappMetadataBinding((RelativeLayout) view, imageView, relativeLayout, textView, textView2, textView3, textView4, scrollView, textView5, textView6, textView7);
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
