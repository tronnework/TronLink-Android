package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class IncludeVoteSearchBinding implements ViewBinding {
    public final TextView countDownTime;
    public final TextView reset;
    public final RelativeLayout rlVote;
    private final RelativeLayout rootView;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private IncludeVoteSearchBinding(RelativeLayout relativeLayout, TextView textView, TextView textView2, RelativeLayout relativeLayout2) {
        this.rootView = relativeLayout;
        this.countDownTime = textView;
        this.reset = textView2;
        this.rlVote = relativeLayout2;
    }

    public static IncludeVoteSearchBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static IncludeVoteSearchBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.include_vote_search, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static IncludeVoteSearchBinding bind(View view) {
        int i = R.id.count_down_time;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.count_down_time);
        if (textView != null) {
            i = R.id.reset;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.reset);
            if (textView2 != null) {
                i = R.id.rl_vote;
                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_vote);
                if (relativeLayout != null) {
                    return new IncludeVoteSearchBinding((RelativeLayout) view, textView, textView2, relativeLayout);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
