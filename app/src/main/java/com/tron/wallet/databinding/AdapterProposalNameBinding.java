package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.tronlinkpro.wallet.R;
public final class AdapterProposalNameBinding implements ViewBinding {
    private final TextView rootView;
    public final TextView tvContent;

    @Override
    public TextView getRoot() {
        return this.rootView;
    }

    private AdapterProposalNameBinding(TextView textView, TextView textView2) {
        this.rootView = textView;
        this.tvContent = textView2;
    }

    public static AdapterProposalNameBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AdapterProposalNameBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.adapter_proposal_name, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AdapterProposalNameBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        TextView textView = (TextView) view;
        return new AdapterProposalNameBinding(textView, textView);
    }
}
