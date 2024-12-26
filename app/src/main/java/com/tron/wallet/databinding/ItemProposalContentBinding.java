package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemProposalContentBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final TextView tvCommitteeContent;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private ItemProposalContentBinding(LinearLayout linearLayout, TextView textView) {
        this.rootView = linearLayout;
        this.tvCommitteeContent = textView;
    }

    public static ItemProposalContentBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemProposalContentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_proposal_content, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemProposalContentBinding bind(View view) {
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_committee_content);
        if (textView != null) {
            return new ItemProposalContentBinding((LinearLayout) view, textView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.tv_committee_content)));
    }
}
