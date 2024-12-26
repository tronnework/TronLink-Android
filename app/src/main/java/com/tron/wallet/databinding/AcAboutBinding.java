package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.business.tabmy.advancedfeatures.HorizontalOptionView;
import com.tronlinkpro.wallet.R;
public final class AcAboutBinding implements ViewBinding {
    public final HorizontalOptionView log;
    public final RelativeLayout rlNewVersionTip;
    private final LinearLayout rootView;
    public final HorizontalOptionView telegram;
    public final TextView tvClientId;
    public final TextView tvVersion;
    public final TextView tvVersionTip;
    public final HorizontalOptionView twitter;
    public final HorizontalOptionView update;
    public final HorizontalOptionView userAgreement;
    public final HorizontalOptionView website;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private AcAboutBinding(LinearLayout linearLayout, HorizontalOptionView horizontalOptionView, RelativeLayout relativeLayout, HorizontalOptionView horizontalOptionView2, TextView textView, TextView textView2, TextView textView3, HorizontalOptionView horizontalOptionView3, HorizontalOptionView horizontalOptionView4, HorizontalOptionView horizontalOptionView5, HorizontalOptionView horizontalOptionView6) {
        this.rootView = linearLayout;
        this.log = horizontalOptionView;
        this.rlNewVersionTip = relativeLayout;
        this.telegram = horizontalOptionView2;
        this.tvClientId = textView;
        this.tvVersion = textView2;
        this.tvVersionTip = textView3;
        this.twitter = horizontalOptionView3;
        this.update = horizontalOptionView4;
        this.userAgreement = horizontalOptionView5;
        this.website = horizontalOptionView6;
    }

    public static AcAboutBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcAboutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_about, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcAboutBinding bind(View view) {
        int i = R.id.log;
        HorizontalOptionView horizontalOptionView = (HorizontalOptionView) ViewBindings.findChildViewById(view, R.id.log);
        if (horizontalOptionView != null) {
            i = R.id.rl_new_version_tip;
            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_new_version_tip);
            if (relativeLayout != null) {
                i = R.id.telegram;
                HorizontalOptionView horizontalOptionView2 = (HorizontalOptionView) ViewBindings.findChildViewById(view, R.id.telegram);
                if (horizontalOptionView2 != null) {
                    i = R.id.tv_clientId;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_clientId);
                    if (textView != null) {
                        i = R.id.tv_version;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_version);
                        if (textView2 != null) {
                            i = R.id.tv_version_tip;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_version_tip);
                            if (textView3 != null) {
                                i = R.id.twitter;
                                HorizontalOptionView horizontalOptionView3 = (HorizontalOptionView) ViewBindings.findChildViewById(view, R.id.twitter);
                                if (horizontalOptionView3 != null) {
                                    i = R.id.update;
                                    HorizontalOptionView horizontalOptionView4 = (HorizontalOptionView) ViewBindings.findChildViewById(view, R.id.update);
                                    if (horizontalOptionView4 != null) {
                                        i = R.id.user_agreement;
                                        HorizontalOptionView horizontalOptionView5 = (HorizontalOptionView) ViewBindings.findChildViewById(view, R.id.user_agreement);
                                        if (horizontalOptionView5 != null) {
                                            i = R.id.website;
                                            HorizontalOptionView horizontalOptionView6 = (HorizontalOptionView) ViewBindings.findChildViewById(view, R.id.website);
                                            if (horizontalOptionView6 != null) {
                                                return new AcAboutBinding((LinearLayout) view, horizontalOptionView, relativeLayout, horizontalOptionView2, textView, textView2, textView3, horizontalOptionView3, horizontalOptionView4, horizontalOptionView5, horizontalOptionView6);
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
