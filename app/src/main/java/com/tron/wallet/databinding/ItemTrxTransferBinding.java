package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.LoadingView;
import com.tron.wallet.common.components.TransactionScamStatusImageView;
import com.tronlinkpro.wallet.R;
public final class ItemTrxTransferBinding implements ViewBinding {
    public final TextView address;
    public final LoadingView ivPending;
    public final TransactionScamStatusImageView ivStatusScam;
    public final TextView leftBracket;
    public final LinearLayout liAddress;
    public final LinearLayout liPending;
    public final LinearLayout llPending;
    public final TextView name;
    public final TextView rightBracket;
    public final RelativeLayout rlInner;
    public final LinearLayout rlRight;
    public final RelativeLayout root;
    private final RelativeLayout rootView;
    public final TextView shortAddress;
    public final TextView time;
    public final TextView tvContractTag;
    public final TextView tvCount;
    public final TextView tvPending;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemTrxTransferBinding(RelativeLayout relativeLayout, TextView textView, LoadingView loadingView, TransactionScamStatusImageView transactionScamStatusImageView, TextView textView2, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, TextView textView3, TextView textView4, RelativeLayout relativeLayout2, LinearLayout linearLayout4, RelativeLayout relativeLayout3, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9) {
        this.rootView = relativeLayout;
        this.address = textView;
        this.ivPending = loadingView;
        this.ivStatusScam = transactionScamStatusImageView;
        this.leftBracket = textView2;
        this.liAddress = linearLayout;
        this.liPending = linearLayout2;
        this.llPending = linearLayout3;
        this.name = textView3;
        this.rightBracket = textView4;
        this.rlInner = relativeLayout2;
        this.rlRight = linearLayout4;
        this.root = relativeLayout3;
        this.shortAddress = textView5;
        this.time = textView6;
        this.tvContractTag = textView7;
        this.tvCount = textView8;
        this.tvPending = textView9;
    }

    public static ItemTrxTransferBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemTrxTransferBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_trx_transfer, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemTrxTransferBinding bind(View view) {
        int i = R.id.address;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.address);
        if (textView != null) {
            i = R.id.iv_pending;
            LoadingView loadingView = (LoadingView) ViewBindings.findChildViewById(view, R.id.iv_pending);
            if (loadingView != null) {
                i = R.id.iv_status_scam;
                TransactionScamStatusImageView transactionScamStatusImageView = (TransactionScamStatusImageView) ViewBindings.findChildViewById(view, R.id.iv_status_scam);
                if (transactionScamStatusImageView != null) {
                    i = R.id.left_bracket;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.left_bracket);
                    if (textView2 != null) {
                        i = R.id.li_address;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_address);
                        if (linearLayout != null) {
                            i = R.id.li_pending;
                            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_pending);
                            if (linearLayout2 != null) {
                                i = R.id.ll_pending;
                                LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_pending);
                                if (linearLayout3 != null) {
                                    i = R.id.name;
                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.name);
                                    if (textView3 != null) {
                                        i = R.id.right_bracket;
                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.right_bracket);
                                        if (textView4 != null) {
                                            i = R.id.rl_inner;
                                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_inner);
                                            if (relativeLayout != null) {
                                                i = R.id.rl_right;
                                                LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.rl_right);
                                                if (linearLayout4 != null) {
                                                    RelativeLayout relativeLayout2 = (RelativeLayout) view;
                                                    i = R.id.short_address;
                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.short_address);
                                                    if (textView5 != null) {
                                                        i = R.id.time;
                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.time);
                                                        if (textView6 != null) {
                                                            i = R.id.tv_contract_tag;
                                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_contract_tag);
                                                            if (textView7 != null) {
                                                                i = R.id.tv_count;
                                                                TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_count);
                                                                if (textView8 != null) {
                                                                    i = R.id.tv_pending;
                                                                    TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_pending);
                                                                    if (textView9 != null) {
                                                                        return new ItemTrxTransferBinding(relativeLayout2, textView, loadingView, transactionScamStatusImageView, textView2, linearLayout, linearLayout2, linearLayout3, textView3, textView4, relativeLayout, linearLayout4, relativeLayout2, textView5, textView6, textView7, textView8, textView9);
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
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
