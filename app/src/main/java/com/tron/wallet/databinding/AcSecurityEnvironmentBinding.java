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
public final class AcSecurityEnvironmentBinding implements ViewBinding {
    public final SecurityItemView clipboardCheck;
    public final ImageView ivCommonLeft;
    public final ImageView ivCommonRight;
    public final LinearLayout llCommonLeft;
    public final RelativeLayout llHeader;
    public final SecurityItemView netCheck;
    public final SecurityItemView officialCheck;
    public final SecurityItemView rootCheck;
    private final LinearLayout rootView;
    public final SecurityItemView simulatorCheck;
    public final TextView tvCommonTitle;
    public final TextView tvRiskNum;
    public final TextView tvTopNum;
    public final TextView tvTopText;
    public final TextView tvWaringNum;
    public final View viewOfficialCheckLine;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private AcSecurityEnvironmentBinding(LinearLayout linearLayout, SecurityItemView securityItemView, ImageView imageView, ImageView imageView2, LinearLayout linearLayout2, RelativeLayout relativeLayout, SecurityItemView securityItemView2, SecurityItemView securityItemView3, SecurityItemView securityItemView4, SecurityItemView securityItemView5, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, View view) {
        this.rootView = linearLayout;
        this.clipboardCheck = securityItemView;
        this.ivCommonLeft = imageView;
        this.ivCommonRight = imageView2;
        this.llCommonLeft = linearLayout2;
        this.llHeader = relativeLayout;
        this.netCheck = securityItemView2;
        this.officialCheck = securityItemView3;
        this.rootCheck = securityItemView4;
        this.simulatorCheck = securityItemView5;
        this.tvCommonTitle = textView;
        this.tvRiskNum = textView2;
        this.tvTopNum = textView3;
        this.tvTopText = textView4;
        this.tvWaringNum = textView5;
        this.viewOfficialCheckLine = view;
    }

    public static AcSecurityEnvironmentBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcSecurityEnvironmentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_security_environment, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcSecurityEnvironmentBinding bind(View view) {
        int i = R.id.clipboard_check;
        SecurityItemView securityItemView = (SecurityItemView) ViewBindings.findChildViewById(view, R.id.clipboard_check);
        if (securityItemView != null) {
            i = R.id.iv_common_left;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_common_left);
            if (imageView != null) {
                i = R.id.iv_common_right;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_common_right);
                if (imageView2 != null) {
                    i = R.id.ll_common_left;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_common_left);
                    if (linearLayout != null) {
                        i = R.id.ll_header;
                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_header);
                        if (relativeLayout != null) {
                            i = R.id.net_check;
                            SecurityItemView securityItemView2 = (SecurityItemView) ViewBindings.findChildViewById(view, R.id.net_check);
                            if (securityItemView2 != null) {
                                i = R.id.official_check;
                                SecurityItemView securityItemView3 = (SecurityItemView) ViewBindings.findChildViewById(view, R.id.official_check);
                                if (securityItemView3 != null) {
                                    i = R.id.root_check;
                                    SecurityItemView securityItemView4 = (SecurityItemView) ViewBindings.findChildViewById(view, R.id.root_check);
                                    if (securityItemView4 != null) {
                                        i = R.id.simulator_check;
                                        SecurityItemView securityItemView5 = (SecurityItemView) ViewBindings.findChildViewById(view, R.id.simulator_check);
                                        if (securityItemView5 != null) {
                                            i = R.id.tv_common_title;
                                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_common_title);
                                            if (textView != null) {
                                                i = R.id.tv_risk_num;
                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_risk_num);
                                                if (textView2 != null) {
                                                    i = R.id.tv_top_num;
                                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_top_num);
                                                    if (textView3 != null) {
                                                        i = R.id.tv_top_text;
                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_top_text);
                                                        if (textView4 != null) {
                                                            i = R.id.tv_waring_num;
                                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_waring_num);
                                                            if (textView5 != null) {
                                                                i = R.id.view_official_check_line;
                                                                View findChildViewById = ViewBindings.findChildViewById(view, R.id.view_official_check_line);
                                                                if (findChildViewById != null) {
                                                                    return new AcSecurityEnvironmentBinding((LinearLayout) view, securityItemView, imageView, imageView2, linearLayout, relativeLayout, securityItemView2, securityItemView3, securityItemView4, securityItemView5, textView, textView2, textView3, textView4, textView5, findChildViewById);
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
