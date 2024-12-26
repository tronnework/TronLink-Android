package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.tronlinkpro.wallet.R;
public final class AcNewSignTransactionBinding implements ViewBinding {
    public final LinearLayout llTooBig;
    public final RecyclerView rcBottom;
    public final RecyclerView rcTop;
    public final RelativeLayout rlTop;
    private final NestedScrollView rootView;
    public final Button toscan;
    public final TextView tv3501;
    public final TextView tv3502;
    public final TextView tvBigTip;
    public final TextView tvSignV2Hint;
    public final TextView tvTitle;
    public final ViewPager2 viewpager;

    @Override
    public NestedScrollView getRoot() {
        return this.rootView;
    }

    private AcNewSignTransactionBinding(NestedScrollView nestedScrollView, LinearLayout linearLayout, RecyclerView recyclerView, RecyclerView recyclerView2, RelativeLayout relativeLayout, Button button, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, ViewPager2 viewPager2) {
        this.rootView = nestedScrollView;
        this.llTooBig = linearLayout;
        this.rcBottom = recyclerView;
        this.rcTop = recyclerView2;
        this.rlTop = relativeLayout;
        this.toscan = button;
        this.tv3501 = textView;
        this.tv3502 = textView2;
        this.tvBigTip = textView3;
        this.tvSignV2Hint = textView4;
        this.tvTitle = textView5;
        this.viewpager = viewPager2;
    }

    public static AcNewSignTransactionBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcNewSignTransactionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_new_sign_transaction, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcNewSignTransactionBinding bind(View view) {
        int i = R.id.ll_too_big;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_too_big);
        if (linearLayout != null) {
            i = R.id.rc_bottom;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rc_bottom);
            if (recyclerView != null) {
                i = R.id.rc_top;
                RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rc_top);
                if (recyclerView2 != null) {
                    i = R.id.rl_top;
                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_top);
                    if (relativeLayout != null) {
                        i = R.id.toscan;
                        Button button = (Button) ViewBindings.findChildViewById(view, R.id.toscan);
                        if (button != null) {
                            i = R.id.tv_350_1;
                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_350_1);
                            if (textView != null) {
                                i = R.id.tv_350_2;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_350_2);
                                if (textView2 != null) {
                                    i = R.id.tv_big_tip;
                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_big_tip);
                                    if (textView3 != null) {
                                        i = R.id.tv_sign_v2_hint;
                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_sign_v2_hint);
                                        if (textView4 != null) {
                                            i = R.id.tv_title;
                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                                            if (textView5 != null) {
                                                i = R.id.viewpager;
                                                ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, R.id.viewpager);
                                                if (viewPager2 != null) {
                                                    return new AcNewSignTransactionBinding((NestedScrollView) view, linearLayout, recyclerView, recyclerView2, relativeLayout, button, textView, textView2, textView3, textView4, textView5, viewPager2);
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
