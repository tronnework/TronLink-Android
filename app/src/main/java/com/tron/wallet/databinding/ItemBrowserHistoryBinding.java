package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tronlinkpro.wallet.R;
public final class ItemBrowserHistoryBinding implements ViewBinding {
    public final SimpleDraweeView image;
    public final LinearLayout liDate;
    public final LinearLayout llText;
    public final RelativeLayout rlItem;
    public final RelativeLayout root;
    private final RelativeLayout rootView;
    public final TextView tvDateStr;
    public final TextView tvSubtitle;
    public final TextView tvTime;
    public final TextView tvTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemBrowserHistoryBinding(RelativeLayout relativeLayout, SimpleDraweeView simpleDraweeView, LinearLayout linearLayout, LinearLayout linearLayout2, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        this.rootView = relativeLayout;
        this.image = simpleDraweeView;
        this.liDate = linearLayout;
        this.llText = linearLayout2;
        this.rlItem = relativeLayout2;
        this.root = relativeLayout3;
        this.tvDateStr = textView;
        this.tvSubtitle = textView2;
        this.tvTime = textView3;
        this.tvTitle = textView4;
    }

    public static ItemBrowserHistoryBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemBrowserHistoryBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_browser_history, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemBrowserHistoryBinding bind(View view) {
        int i = R.id.image;
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.image);
        if (simpleDraweeView != null) {
            i = R.id.li_date;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_date);
            if (linearLayout != null) {
                i = R.id.ll_text;
                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_text);
                if (linearLayout2 != null) {
                    i = R.id.rl_item;
                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_item);
                    if (relativeLayout != null) {
                        RelativeLayout relativeLayout2 = (RelativeLayout) view;
                        i = R.id.tv_date_str;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_date_str);
                        if (textView != null) {
                            i = R.id.tv_subtitle;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_subtitle);
                            if (textView2 != null) {
                                i = R.id.tv_time;
                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_time);
                                if (textView3 != null) {
                                    i = R.id.tv_title;
                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                                    if (textView4 != null) {
                                        return new ItemBrowserHistoryBinding(relativeLayout2, simpleDraweeView, linearLayout, linearLayout2, relativeLayout, relativeLayout2, textView, textView2, textView3, textView4);
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
