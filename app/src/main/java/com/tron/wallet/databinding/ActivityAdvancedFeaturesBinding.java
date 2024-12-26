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
public final class ActivityAdvancedFeaturesBinding implements ViewBinding {
    public final TextView bottom;
    public final HorizontalOptionView commitProposal;
    public final HorizontalOptionView convertTool;
    public final HorizontalOptionView dapp;
    public final RelativeLayout exportWallet;
    private final LinearLayout rootView;
    public final TextView top;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private ActivityAdvancedFeaturesBinding(LinearLayout linearLayout, TextView textView, HorizontalOptionView horizontalOptionView, HorizontalOptionView horizontalOptionView2, HorizontalOptionView horizontalOptionView3, RelativeLayout relativeLayout, TextView textView2) {
        this.rootView = linearLayout;
        this.bottom = textView;
        this.commitProposal = horizontalOptionView;
        this.convertTool = horizontalOptionView2;
        this.dapp = horizontalOptionView3;
        this.exportWallet = relativeLayout;
        this.top = textView2;
    }

    public static ActivityAdvancedFeaturesBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityAdvancedFeaturesBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_advanced_features, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityAdvancedFeaturesBinding bind(View view) {
        int i = R.id.bottom;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.bottom);
        if (textView != null) {
            i = R.id.commit_proposal;
            HorizontalOptionView horizontalOptionView = (HorizontalOptionView) ViewBindings.findChildViewById(view, R.id.commit_proposal);
            if (horizontalOptionView != null) {
                i = R.id.convert_tool;
                HorizontalOptionView horizontalOptionView2 = (HorizontalOptionView) ViewBindings.findChildViewById(view, R.id.convert_tool);
                if (horizontalOptionView2 != null) {
                    i = R.id.dapp;
                    HorizontalOptionView horizontalOptionView3 = (HorizontalOptionView) ViewBindings.findChildViewById(view, R.id.dapp);
                    if (horizontalOptionView3 != null) {
                        i = R.id.export_wallet;
                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.export_wallet);
                        if (relativeLayout != null) {
                            i = R.id.top;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.top);
                            if (textView2 != null) {
                                return new ActivityAdvancedFeaturesBinding((LinearLayout) view, textView, horizontalOptionView, horizontalOptionView2, horizontalOptionView3, relativeLayout, textView2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
