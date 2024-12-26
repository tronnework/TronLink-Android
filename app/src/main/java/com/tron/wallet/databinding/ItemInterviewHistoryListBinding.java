package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.TokenLogoDraweeView;
import com.tronlinkpro.wallet.R;
public final class ItemInterviewHistoryListBinding implements ViewBinding {
    public final ImageView ivDeleteView;
    public final TokenLogoDraweeView ivLogo;
    public final LinearLayout llContent;
    public final RelativeLayout llLogo;
    public final TextView name;
    public final RelativeLayout rlInner;
    public final RelativeLayout rlMain;
    private final RelativeLayout rootView;
    public final TextView url;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemInterviewHistoryListBinding(RelativeLayout relativeLayout, ImageView imageView, TokenLogoDraweeView tokenLogoDraweeView, LinearLayout linearLayout, RelativeLayout relativeLayout2, TextView textView, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, TextView textView2) {
        this.rootView = relativeLayout;
        this.ivDeleteView = imageView;
        this.ivLogo = tokenLogoDraweeView;
        this.llContent = linearLayout;
        this.llLogo = relativeLayout2;
        this.name = textView;
        this.rlInner = relativeLayout3;
        this.rlMain = relativeLayout4;
        this.url = textView2;
    }

    public static ItemInterviewHistoryListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemInterviewHistoryListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_interview_history_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemInterviewHistoryListBinding bind(View view) {
        int i = R.id.iv_delete_view;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_delete_view);
        if (imageView != null) {
            i = R.id.iv_logo;
            TokenLogoDraweeView tokenLogoDraweeView = (TokenLogoDraweeView) ViewBindings.findChildViewById(view, R.id.iv_logo);
            if (tokenLogoDraweeView != null) {
                i = R.id.ll_content;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_content);
                if (linearLayout != null) {
                    i = R.id.ll_logo;
                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_logo);
                    if (relativeLayout != null) {
                        i = R.id.name;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.name);
                        if (textView != null) {
                            i = R.id.rl_inner;
                            RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_inner);
                            if (relativeLayout2 != null) {
                                RelativeLayout relativeLayout3 = (RelativeLayout) view;
                                i = R.id.url;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.url);
                                if (textView2 != null) {
                                    return new ItemInterviewHistoryListBinding(relativeLayout3, imageView, tokenLogoDraweeView, linearLayout, relativeLayout, textView, relativeLayout2, relativeLayout3, textView2);
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
