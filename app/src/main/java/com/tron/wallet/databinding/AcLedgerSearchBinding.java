package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tronlinkpro.wallet.R;
public final class AcLedgerSearchBinding implements ViewBinding {
    public final Button btnRetry;
    public final ImageView ivNotFound;
    public final ConstraintLayout llError;
    public final LinearLayout llErrorTips;
    public final ConstraintLayout llSearch;
    public final LinearLayout llSearchTimeoutTip;
    public final RelativeLayout rlSearchLedger;
    private final ConstraintLayout rootView;
    public final RecyclerView rvSearchLedgerList;
    public final SimpleDraweeView searchView;
    public final TextView tvNotFound;
    public final TextView tvSearchLedgerTip;
    public final TextView tvSearchLedgerTip1;
    public final TextView tvSearchLedgerTip2;
    public final TextView tvSearchLedgerTip3;
    public final TextView tvSearchLedgerTitle;

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    private AcLedgerSearchBinding(ConstraintLayout constraintLayout, Button button, ImageView imageView, ConstraintLayout constraintLayout2, LinearLayout linearLayout, ConstraintLayout constraintLayout3, LinearLayout linearLayout2, RelativeLayout relativeLayout, RecyclerView recyclerView, SimpleDraweeView simpleDraweeView, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6) {
        this.rootView = constraintLayout;
        this.btnRetry = button;
        this.ivNotFound = imageView;
        this.llError = constraintLayout2;
        this.llErrorTips = linearLayout;
        this.llSearch = constraintLayout3;
        this.llSearchTimeoutTip = linearLayout2;
        this.rlSearchLedger = relativeLayout;
        this.rvSearchLedgerList = recyclerView;
        this.searchView = simpleDraweeView;
        this.tvNotFound = textView;
        this.tvSearchLedgerTip = textView2;
        this.tvSearchLedgerTip1 = textView3;
        this.tvSearchLedgerTip2 = textView4;
        this.tvSearchLedgerTip3 = textView5;
        this.tvSearchLedgerTitle = textView6;
    }

    public static AcLedgerSearchBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcLedgerSearchBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_ledger_search, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcLedgerSearchBinding bind(View view) {
        int i = R.id.btn_retry;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_retry);
        if (button != null) {
            i = R.id.iv_not_found;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_not_found);
            if (imageView != null) {
                i = R.id.ll_error;
                ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.ll_error);
                if (constraintLayout != null) {
                    i = R.id.ll_error_tips;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_error_tips);
                    if (linearLayout != null) {
                        i = R.id.ll_search;
                        ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.ll_search);
                        if (constraintLayout2 != null) {
                            i = R.id.ll_search_timeout_tip;
                            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_search_timeout_tip);
                            if (linearLayout2 != null) {
                                i = R.id.rl_search_ledger;
                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_search_ledger);
                                if (relativeLayout != null) {
                                    i = R.id.rv_search_ledger_list;
                                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rv_search_ledger_list);
                                    if (recyclerView != null) {
                                        i = R.id.search_view;
                                        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.search_view);
                                        if (simpleDraweeView != null) {
                                            i = R.id.tv_not_found;
                                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_not_found);
                                            if (textView != null) {
                                                i = R.id.tv_search_ledger_tip;
                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_search_ledger_tip);
                                                if (textView2 != null) {
                                                    i = R.id.tv_search_ledger_tip1;
                                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_search_ledger_tip1);
                                                    if (textView3 != null) {
                                                        i = R.id.tv_search_ledger_tip2;
                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_search_ledger_tip2);
                                                        if (textView4 != null) {
                                                            i = R.id.tv_search_ledger_tip3;
                                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_search_ledger_tip3);
                                                            if (textView5 != null) {
                                                                i = R.id.tv_search_ledger_title;
                                                                TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_search_ledger_title);
                                                                if (textView6 != null) {
                                                                    return new AcLedgerSearchBinding((ConstraintLayout) view, button, imageView, constraintLayout, linearLayout, constraintLayout2, linearLayout2, relativeLayout, recyclerView, simpleDraweeView, textView, textView2, textView3, textView4, textView5, textView6);
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
