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
import com.tron.wallet.business.security.components.SecurityHorizontalView;
import com.tron.wallet.business.security.components.SecurityItemView;
import com.tronlinkpro.wallet.R;
public final class ItemSecurityPage3Binding implements ViewBinding {
    public final SecurityItemView approveCheckClick;
    public final SecurityHorizontalView btnRestart;
    public final SecurityItemView environmentCheckClick;
    public final ImageView ivTopResultIcon;
    public final ImageView left;
    public final ImageView right;
    public final RelativeLayout rlPage31;
    public final RelativeLayout rlPage32;
    public final LinearLayout rootPage3;
    private final LinearLayout rootView;
    public final SecurityItemView tokenCheckClick;
    public final TextView tvRiskNumPage3;
    public final TextView tvRiskTopNumPage3;
    public final TextView tvRiskTopTextPage3;
    public final TextView tvUpdateTime;
    public final TextView tvWaringNumPage3;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private ItemSecurityPage3Binding(LinearLayout linearLayout, SecurityItemView securityItemView, SecurityHorizontalView securityHorizontalView, SecurityItemView securityItemView2, ImageView imageView, ImageView imageView2, ImageView imageView3, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, LinearLayout linearLayout2, SecurityItemView securityItemView3, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5) {
        this.rootView = linearLayout;
        this.approveCheckClick = securityItemView;
        this.btnRestart = securityHorizontalView;
        this.environmentCheckClick = securityItemView2;
        this.ivTopResultIcon = imageView;
        this.left = imageView2;
        this.right = imageView3;
        this.rlPage31 = relativeLayout;
        this.rlPage32 = relativeLayout2;
        this.rootPage3 = linearLayout2;
        this.tokenCheckClick = securityItemView3;
        this.tvRiskNumPage3 = textView;
        this.tvRiskTopNumPage3 = textView2;
        this.tvRiskTopTextPage3 = textView3;
        this.tvUpdateTime = textView4;
        this.tvWaringNumPage3 = textView5;
    }

    public static ItemSecurityPage3Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemSecurityPage3Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_security_page_3, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemSecurityPage3Binding bind(View view) {
        int i = R.id.approve_check_click;
        SecurityItemView securityItemView = (SecurityItemView) ViewBindings.findChildViewById(view, R.id.approve_check_click);
        if (securityItemView != null) {
            i = R.id.btn_restart;
            SecurityHorizontalView securityHorizontalView = (SecurityHorizontalView) ViewBindings.findChildViewById(view, R.id.btn_restart);
            if (securityHorizontalView != null) {
                i = R.id.environment_check_click;
                SecurityItemView securityItemView2 = (SecurityItemView) ViewBindings.findChildViewById(view, R.id.environment_check_click);
                if (securityItemView2 != null) {
                    i = R.id.iv_top_result_icon;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_top_result_icon);
                    if (imageView != null) {
                        i = R.id.left;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.left);
                        if (imageView2 != null) {
                            i = R.id.right;
                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.right);
                            if (imageView3 != null) {
                                i = R.id.rl_page_3_1;
                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_page_3_1);
                                if (relativeLayout != null) {
                                    i = R.id.rl_page_3_2;
                                    RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_page_3_2);
                                    if (relativeLayout2 != null) {
                                        LinearLayout linearLayout = (LinearLayout) view;
                                        i = R.id.token_check_click;
                                        SecurityItemView securityItemView3 = (SecurityItemView) ViewBindings.findChildViewById(view, R.id.token_check_click);
                                        if (securityItemView3 != null) {
                                            i = R.id.tv_risk_num_page_3;
                                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_risk_num_page_3);
                                            if (textView != null) {
                                                i = R.id.tv_risk_top_num_page_3;
                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_risk_top_num_page_3);
                                                if (textView2 != null) {
                                                    i = R.id.tv_risk_top_text_page_3;
                                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_risk_top_text_page_3);
                                                    if (textView3 != null) {
                                                        i = R.id.tv_update_time;
                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_update_time);
                                                        if (textView4 != null) {
                                                            i = R.id.tv_waring_num_page_3;
                                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_waring_num_page_3);
                                                            if (textView5 != null) {
                                                                return new ItemSecurityPage3Binding(linearLayout, securityItemView, securityHorizontalView, securityItemView2, imageView, imageView2, imageView3, relativeLayout, relativeLayout2, linearLayout, securityItemView3, textView, textView2, textView3, textView4, textView5);
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
