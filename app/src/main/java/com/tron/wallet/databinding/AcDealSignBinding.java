package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.BadgeButton;
import com.tronlinkpro.wallet.R;
public final class AcDealSignBinding implements ViewBinding {
    public final FrameLayout content;
    public final LinearLayout llTabCount;
    public final BadgeButton redView;
    private final LinearLayout rootView;
    public final RelativeLayout signAlready;
    public final RelativeLayout signFailure;
    public final RelativeLayout signSuccess;
    public final RelativeLayout signWait;
    public final TextView tvLimit;
    public final TextView tvSignAlready;
    public final TextView tvSignFailure;
    public final TextView tvSignSuccess;
    public final TextView tvSignWait;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private AcDealSignBinding(LinearLayout linearLayout, FrameLayout frameLayout, LinearLayout linearLayout2, BadgeButton badgeButton, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5) {
        this.rootView = linearLayout;
        this.content = frameLayout;
        this.llTabCount = linearLayout2;
        this.redView = badgeButton;
        this.signAlready = relativeLayout;
        this.signFailure = relativeLayout2;
        this.signSuccess = relativeLayout3;
        this.signWait = relativeLayout4;
        this.tvLimit = textView;
        this.tvSignAlready = textView2;
        this.tvSignFailure = textView3;
        this.tvSignSuccess = textView4;
        this.tvSignWait = textView5;
    }

    public static AcDealSignBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcDealSignBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_deal_sign, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcDealSignBinding bind(View view) {
        int i = R.id.content;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.content);
        if (frameLayout != null) {
            i = R.id.ll_tab_count;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_tab_count);
            if (linearLayout != null) {
                i = R.id.redView;
                BadgeButton badgeButton = (BadgeButton) ViewBindings.findChildViewById(view, R.id.redView);
                if (badgeButton != null) {
                    i = R.id.sign_already;
                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.sign_already);
                    if (relativeLayout != null) {
                        i = R.id.sign_failure;
                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.sign_failure);
                        if (relativeLayout2 != null) {
                            i = R.id.sign_success;
                            RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.sign_success);
                            if (relativeLayout3 != null) {
                                i = R.id.sign_wait;
                                RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.sign_wait);
                                if (relativeLayout4 != null) {
                                    i = R.id.tv_limit;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_limit);
                                    if (textView != null) {
                                        i = R.id.tv_sign_already;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_sign_already);
                                        if (textView2 != null) {
                                            i = R.id.tv_sign_failure;
                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_sign_failure);
                                            if (textView3 != null) {
                                                i = R.id.tv_sign_success;
                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_sign_success);
                                                if (textView4 != null) {
                                                    i = R.id.tv_sign_wait;
                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_sign_wait);
                                                    if (textView5 != null) {
                                                        return new AcDealSignBinding((LinearLayout) view, frameLayout, linearLayout, badgeButton, relativeLayout, relativeLayout2, relativeLayout3, relativeLayout4, textView, textView2, textView3, textView4, textView5);
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
