package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.VerticalScrollView;
import com.tronlinkpro.wallet.R;
public final class PullItemLocalBinding implements ViewBinding {
    public final LinearLayout messageContent;
    public final LinearLayout messageContentLayout;
    private final LinearLayout rootView;
    public final VerticalScrollView scrollViewMessageContent;
    public final TextView tvPullAddress;
    public final TextView tvPullAddressName;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private PullItemLocalBinding(LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, VerticalScrollView verticalScrollView, TextView textView, TextView textView2) {
        this.rootView = linearLayout;
        this.messageContent = linearLayout2;
        this.messageContentLayout = linearLayout3;
        this.scrollViewMessageContent = verticalScrollView;
        this.tvPullAddress = textView;
        this.tvPullAddressName = textView2;
    }

    public static PullItemLocalBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PullItemLocalBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.pull_item_local, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PullItemLocalBinding bind(View view) {
        int i = R.id.message_content;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.message_content);
        if (linearLayout != null) {
            i = R.id.message_content_layout;
            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.message_content_layout);
            if (linearLayout2 != null) {
                i = R.id.scrollView_message_content;
                VerticalScrollView verticalScrollView = (VerticalScrollView) ViewBindings.findChildViewById(view, R.id.scrollView_message_content);
                if (verticalScrollView != null) {
                    i = R.id.tv_pull_address;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_pull_address);
                    if (textView != null) {
                        i = R.id.tv_pull_address_name;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_pull_address_name);
                        if (textView2 != null) {
                            return new PullItemLocalBinding((LinearLayout) view, linearLayout, linearLayout2, verticalScrollView, textView, textView2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
