package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class VoteCarBottomBinding implements ViewBinding {
    public final Button btnBatchVote;
    public final ConstraintLayout layoutCar;
    public final TextView notEnough;
    private final ConstraintLayout rootView;
    public final TextView totalVote;
    public final TextView tvTotalVoteCount;
    public final ImageView voteCarLogo;
    public final TextView voteSRAmount;

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    private VoteCarBottomBinding(ConstraintLayout constraintLayout, Button button, ConstraintLayout constraintLayout2, TextView textView, TextView textView2, TextView textView3, ImageView imageView, TextView textView4) {
        this.rootView = constraintLayout;
        this.btnBatchVote = button;
        this.layoutCar = constraintLayout2;
        this.notEnough = textView;
        this.totalVote = textView2;
        this.tvTotalVoteCount = textView3;
        this.voteCarLogo = imageView;
        this.voteSRAmount = textView4;
    }

    public static VoteCarBottomBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static VoteCarBottomBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.vote_car_bottom, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static VoteCarBottomBinding bind(View view) {
        int i = R.id.btn_batch_vote;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_batch_vote);
        if (button != null) {
            ConstraintLayout constraintLayout = (ConstraintLayout) view;
            i = R.id.not_enough;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.not_enough);
            if (textView != null) {
                i = R.id.total_vote;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.total_vote);
                if (textView2 != null) {
                    i = R.id.tv_total_vote_count;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_total_vote_count);
                    if (textView3 != null) {
                        i = R.id.vote_car_logo;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.vote_car_logo);
                        if (imageView != null) {
                            i = R.id.vote_SR_amount;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.vote_SR_amount);
                            if (textView4 != null) {
                                return new VoteCarBottomBinding(constraintLayout, button, constraintLayout, textView, textView2, textView3, imageView, textView4);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
