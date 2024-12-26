package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class TestLedgerBinding implements ViewBinding {
    public final RecyclerView btList;
    public final TextView btnRefresh;
    public final View ivDivider;
    private final ConstraintLayout rootView;
    public final TextView tvBond;
    public final TextView tvChooseAddress;
    public final TextView tvGetAddress;
    public final TextView tvInfo;
    public final TextView tvOpen;
    public final TextView tvSign;
    public final TextView tvSignStr;

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    private TestLedgerBinding(ConstraintLayout constraintLayout, RecyclerView recyclerView, TextView textView, View view, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8) {
        this.rootView = constraintLayout;
        this.btList = recyclerView;
        this.btnRefresh = textView;
        this.ivDivider = view;
        this.tvBond = textView2;
        this.tvChooseAddress = textView3;
        this.tvGetAddress = textView4;
        this.tvInfo = textView5;
        this.tvOpen = textView6;
        this.tvSign = textView7;
        this.tvSignStr = textView8;
    }

    public static TestLedgerBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static TestLedgerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.test_ledger, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static TestLedgerBinding bind(View view) {
        int i = R.id.bt_list;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.bt_list);
        if (recyclerView != null) {
            i = R.id.btn_refresh;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.btn_refresh);
            if (textView != null) {
                i = R.id.iv_divider;
                View findChildViewById = ViewBindings.findChildViewById(view, R.id.iv_divider);
                if (findChildViewById != null) {
                    i = R.id.tv_bond;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_bond);
                    if (textView2 != null) {
                        i = R.id.tv_choose_address;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_choose_address);
                        if (textView3 != null) {
                            i = R.id.tv_get_address;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_get_address);
                            if (textView4 != null) {
                                i = R.id.tv_info;
                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_info);
                                if (textView5 != null) {
                                    i = R.id.tv_open;
                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_open);
                                    if (textView6 != null) {
                                        i = R.id.tv_sign;
                                        TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_sign);
                                        if (textView7 != null) {
                                            i = R.id.tv_sign_str;
                                            TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_sign_str);
                                            if (textView8 != null) {
                                                return new TestLedgerBinding((ConstraintLayout) view, recyclerView, textView, findChildViewById, textView2, textView3, textView4, textView5, textView6, textView7, textView8);
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
