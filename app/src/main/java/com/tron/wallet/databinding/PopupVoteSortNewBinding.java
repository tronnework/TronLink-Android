package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class PopupVoteSortNewBinding implements ViewBinding {
    public final RadioGroup groupSortItem;
    public final ImageView ivToggle;
    public final RadioButton rbApr;
    public final RadioButton rbMyVote;
    public final RadioButton rbVotedCount;
    public final RelativeLayout rlContent;
    public final RelativeLayout rlTop;
    private final RelativeLayout rootView;
    public final TextView tvMyVoted;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private PopupVoteSortNewBinding(RelativeLayout relativeLayout, RadioGroup radioGroup, ImageView imageView, RadioButton radioButton, RadioButton radioButton2, RadioButton radioButton3, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, TextView textView) {
        this.rootView = relativeLayout;
        this.groupSortItem = radioGroup;
        this.ivToggle = imageView;
        this.rbApr = radioButton;
        this.rbMyVote = radioButton2;
        this.rbVotedCount = radioButton3;
        this.rlContent = relativeLayout2;
        this.rlTop = relativeLayout3;
        this.tvMyVoted = textView;
    }

    public static PopupVoteSortNewBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopupVoteSortNewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.popup_vote_sort_new, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopupVoteSortNewBinding bind(View view) {
        int i = R.id.group_sort_item;
        RadioGroup radioGroup = (RadioGroup) ViewBindings.findChildViewById(view, R.id.group_sort_item);
        if (radioGroup != null) {
            i = R.id.iv_toggle;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_toggle);
            if (imageView != null) {
                i = R.id.rb_apr;
                RadioButton radioButton = (RadioButton) ViewBindings.findChildViewById(view, R.id.rb_apr);
                if (radioButton != null) {
                    i = R.id.rb_my_vote;
                    RadioButton radioButton2 = (RadioButton) ViewBindings.findChildViewById(view, R.id.rb_my_vote);
                    if (radioButton2 != null) {
                        i = R.id.rb_voted_count;
                        RadioButton radioButton3 = (RadioButton) ViewBindings.findChildViewById(view, R.id.rb_voted_count);
                        if (radioButton3 != null) {
                            RelativeLayout relativeLayout = (RelativeLayout) view;
                            i = R.id.rl_top;
                            RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_top);
                            if (relativeLayout2 != null) {
                                i = R.id.tv_my_voted;
                                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_my_voted);
                                if (textView != null) {
                                    return new PopupVoteSortNewBinding(relativeLayout, radioGroup, imageView, radioButton, radioButton2, radioButton3, relativeLayout, relativeLayout2, textView);
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
