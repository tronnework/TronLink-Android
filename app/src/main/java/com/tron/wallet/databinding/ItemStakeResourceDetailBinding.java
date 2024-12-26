package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemStakeResourceDetailBinding implements ViewBinding {
    public final LinearLayout contentRoot;
    public final LinearLayout liContentTrx;
    private final LinearLayout rootView;
    public final TextView tvDeadlineTime;
    public final TextView tvReceiveAddress;
    public final TextView tvReceiveAddressTitle;
    public final TextView tvResourceAmount;
    public final TextView tvResourceReclaim;
    public final TextView tvResourceToTrx;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private ItemStakeResourceDetailBinding(LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6) {
        this.rootView = linearLayout;
        this.contentRoot = linearLayout2;
        this.liContentTrx = linearLayout3;
        this.tvDeadlineTime = textView;
        this.tvReceiveAddress = textView2;
        this.tvReceiveAddressTitle = textView3;
        this.tvResourceAmount = textView4;
        this.tvResourceReclaim = textView5;
        this.tvResourceToTrx = textView6;
    }

    public static ItemStakeResourceDetailBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemStakeResourceDetailBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_stake_resource_detail, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemStakeResourceDetailBinding bind(View view) {
        LinearLayout linearLayout = (LinearLayout) view;
        int i = R.id.li_content_trx;
        LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_content_trx);
        if (linearLayout2 != null) {
            i = R.id.tv_deadline_time;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_deadline_time);
            if (textView != null) {
                i = R.id.tv_receive_address;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_receive_address);
                if (textView2 != null) {
                    i = R.id.tv_receive_address_title;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_receive_address_title);
                    if (textView3 != null) {
                        i = R.id.tv_resource_amount;
                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_resource_amount);
                        if (textView4 != null) {
                            i = R.id.tv_resource_reclaim;
                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_resource_reclaim);
                            if (textView5 != null) {
                                i = R.id.tv_resource_to_trx;
                                TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_resource_to_trx);
                                if (textView6 != null) {
                                    return new ItemStakeResourceDetailBinding(linearLayout, linearLayout, linearLayout2, textView, textView2, textView3, textView4, textView5, textView6);
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
