package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class AcTokenCheckHeadBinding implements ViewBinding {
    public final TextView commonTitle;
    public final TextView highRiskCount;
    public final TextView middleRiskCount;
    public final TextView riskTitle;
    private final RelativeLayout rootView;
    public final TextView topRiskCount;
    public final TextView totalRiskCountTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcTokenCheckHeadBinding(RelativeLayout relativeLayout, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6) {
        this.rootView = relativeLayout;
        this.commonTitle = textView;
        this.highRiskCount = textView2;
        this.middleRiskCount = textView3;
        this.riskTitle = textView4;
        this.topRiskCount = textView5;
        this.totalRiskCountTitle = textView6;
    }

    public static AcTokenCheckHeadBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcTokenCheckHeadBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_token_check_head, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcTokenCheckHeadBinding bind(View view) {
        int i = R.id.common_title;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.common_title);
        if (textView != null) {
            i = R.id.high_risk_count;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.high_risk_count);
            if (textView2 != null) {
                i = R.id.middle_risk_count;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.middle_risk_count);
                if (textView3 != null) {
                    i = R.id.risk_title;
                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.risk_title);
                    if (textView4 != null) {
                        i = R.id.top_risk_count;
                        TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.top_risk_count);
                        if (textView5 != null) {
                            i = R.id.total_risk_count_title;
                            TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.total_risk_count_title);
                            if (textView6 != null) {
                                return new AcTokenCheckHeadBinding((RelativeLayout) view, textView, textView2, textView3, textView4, textView5, textView6);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
