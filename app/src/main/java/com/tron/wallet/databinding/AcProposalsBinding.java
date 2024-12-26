package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.ptr.iptr.HomePtrClassicFrameLayout;
import com.tronlinkpro.wallet.R;
public final class AcProposalsBinding implements ViewBinding {
    public final LayoutProposalHeadBinding header;
    public final HomePtrClassicFrameLayout rcFrame;
    public final RecyclerView rcList;
    private final LinearLayout rootView;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private AcProposalsBinding(LinearLayout linearLayout, LayoutProposalHeadBinding layoutProposalHeadBinding, HomePtrClassicFrameLayout homePtrClassicFrameLayout, RecyclerView recyclerView) {
        this.rootView = linearLayout;
        this.header = layoutProposalHeadBinding;
        this.rcFrame = homePtrClassicFrameLayout;
        this.rcList = recyclerView;
    }

    public static AcProposalsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcProposalsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_proposals, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcProposalsBinding bind(View view) {
        int i = R.id.header;
        View findChildViewById = ViewBindings.findChildViewById(view, R.id.header);
        if (findChildViewById != null) {
            LayoutProposalHeadBinding bind = LayoutProposalHeadBinding.bind(findChildViewById);
            HomePtrClassicFrameLayout homePtrClassicFrameLayout = (HomePtrClassicFrameLayout) ViewBindings.findChildViewById(view, R.id.rc_frame);
            if (homePtrClassicFrameLayout != null) {
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rc_list);
                if (recyclerView != null) {
                    return new AcProposalsBinding((LinearLayout) view, bind, homePtrClassicFrameLayout, recyclerView);
                }
                i = R.id.rc_list;
            } else {
                i = R.id.rc_frame;
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
