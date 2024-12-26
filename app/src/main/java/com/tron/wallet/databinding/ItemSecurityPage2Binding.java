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
import com.tron.wallet.business.security.components.SecurityItemView;
import com.tronlinkpro.wallet.R;
public final class ItemSecurityPage2Binding implements ViewBinding {
    public final LinearLayout approveCheckLoading;
    public final SecurityItemView approveCheckShow;
    public final LinearLayout environmentCheckLoading;
    public final SecurityItemView environmentCheckShow;
    public final ImageView ivLoading1;
    public final ImageView ivLoading2;
    public final ImageView ivLoading3;
    public final ImageView ivLoading4;
    public final ImageView ivLoading5;
    public final ImageView left;
    public final ImageView right;
    public final RelativeLayout rlPage21;
    public final RelativeLayout rlPage22;
    public final LinearLayout rootPage2;
    private final LinearLayout rootView;
    public final LinearLayout tokenCheckLoading;
    public final SecurityItemView tokenCheckShow;
    public final TextView tvRiskNumPage2;
    public final TextView tvWaringNumPage2;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private ItemSecurityPage2Binding(LinearLayout linearLayout, LinearLayout linearLayout2, SecurityItemView securityItemView, LinearLayout linearLayout3, SecurityItemView securityItemView2, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView imageView6, ImageView imageView7, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, LinearLayout linearLayout4, LinearLayout linearLayout5, SecurityItemView securityItemView3, TextView textView, TextView textView2) {
        this.rootView = linearLayout;
        this.approveCheckLoading = linearLayout2;
        this.approveCheckShow = securityItemView;
        this.environmentCheckLoading = linearLayout3;
        this.environmentCheckShow = securityItemView2;
        this.ivLoading1 = imageView;
        this.ivLoading2 = imageView2;
        this.ivLoading3 = imageView3;
        this.ivLoading4 = imageView4;
        this.ivLoading5 = imageView5;
        this.left = imageView6;
        this.right = imageView7;
        this.rlPage21 = relativeLayout;
        this.rlPage22 = relativeLayout2;
        this.rootPage2 = linearLayout4;
        this.tokenCheckLoading = linearLayout5;
        this.tokenCheckShow = securityItemView3;
        this.tvRiskNumPage2 = textView;
        this.tvWaringNumPage2 = textView2;
    }

    public static ItemSecurityPage2Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemSecurityPage2Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_security_page_2, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemSecurityPage2Binding bind(View view) {
        int i = R.id.approve_check_loading;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.approve_check_loading);
        if (linearLayout != null) {
            i = R.id.approve_check_show;
            SecurityItemView securityItemView = (SecurityItemView) ViewBindings.findChildViewById(view, R.id.approve_check_show);
            if (securityItemView != null) {
                i = R.id.environment_check_loading;
                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.environment_check_loading);
                if (linearLayout2 != null) {
                    i = R.id.environment_check_show;
                    SecurityItemView securityItemView2 = (SecurityItemView) ViewBindings.findChildViewById(view, R.id.environment_check_show);
                    if (securityItemView2 != null) {
                        i = R.id.iv_loading_1;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_loading_1);
                        if (imageView != null) {
                            i = R.id.iv_loading_2;
                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_loading_2);
                            if (imageView2 != null) {
                                i = R.id.iv_loading_3;
                                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_loading_3);
                                if (imageView3 != null) {
                                    i = R.id.iv_loading_4;
                                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_loading_4);
                                    if (imageView4 != null) {
                                        i = R.id.iv_loading_5;
                                        ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_loading_5);
                                        if (imageView5 != null) {
                                            i = R.id.left;
                                            ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(view, R.id.left);
                                            if (imageView6 != null) {
                                                i = R.id.right;
                                                ImageView imageView7 = (ImageView) ViewBindings.findChildViewById(view, R.id.right);
                                                if (imageView7 != null) {
                                                    i = R.id.rl_page_2_1;
                                                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_page_2_1);
                                                    if (relativeLayout != null) {
                                                        i = R.id.rl_page_2_2;
                                                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_page_2_2);
                                                        if (relativeLayout2 != null) {
                                                            LinearLayout linearLayout3 = (LinearLayout) view;
                                                            i = R.id.token_check_loading;
                                                            LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.token_check_loading);
                                                            if (linearLayout4 != null) {
                                                                i = R.id.token_check_show;
                                                                SecurityItemView securityItemView3 = (SecurityItemView) ViewBindings.findChildViewById(view, R.id.token_check_show);
                                                                if (securityItemView3 != null) {
                                                                    i = R.id.tv_risk_num_page_2;
                                                                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_risk_num_page_2);
                                                                    if (textView != null) {
                                                                        i = R.id.tv_waring_num_page_2;
                                                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_waring_num_page_2);
                                                                        if (textView2 != null) {
                                                                            return new ItemSecurityPage2Binding(linearLayout3, linearLayout, securityItemView, linearLayout2, securityItemView2, imageView, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, relativeLayout, relativeLayout2, linearLayout3, linearLayout4, securityItemView3, textView, textView2);
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
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
