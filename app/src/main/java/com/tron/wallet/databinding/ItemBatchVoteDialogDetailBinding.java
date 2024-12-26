package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemBatchVoteDialogDetailBinding implements ViewBinding {
    public final TextView availVotes;
    public final View divider1;
    public final ImageView ivBack;
    public final VoteCarBottomBinding layoutCar;
    public final LinearLayout ll1;
    public final LinearLayout ll2;
    public final LinearLayout llBack;
    public final RecyclerView recyclerView;
    public final RelativeLayout rlHead;
    public final RelativeLayout rlTitle;
    public final RelativeLayout rootView;
    private final RelativeLayout rootView_;
    public final TextView title;
    public final TextView tvClearAll;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView_;
    }

    private ItemBatchVoteDialogDetailBinding(RelativeLayout relativeLayout, TextView textView, View view, ImageView imageView, VoteCarBottomBinding voteCarBottomBinding, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, RecyclerView recyclerView, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, TextView textView2, TextView textView3) {
        this.rootView_ = relativeLayout;
        this.availVotes = textView;
        this.divider1 = view;
        this.ivBack = imageView;
        this.layoutCar = voteCarBottomBinding;
        this.ll1 = linearLayout;
        this.ll2 = linearLayout2;
        this.llBack = linearLayout3;
        this.recyclerView = recyclerView;
        this.rlHead = relativeLayout2;
        this.rlTitle = relativeLayout3;
        this.rootView = relativeLayout4;
        this.title = textView2;
        this.tvClearAll = textView3;
    }

    public static ItemBatchVoteDialogDetailBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemBatchVoteDialogDetailBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_batch_vote_dialog_detail, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemBatchVoteDialogDetailBinding bind(View view) {
        int i = R.id.avail_votes;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.avail_votes);
        if (textView != null) {
            i = R.id.divider1;
            View findChildViewById = ViewBindings.findChildViewById(view, R.id.divider1);
            if (findChildViewById != null) {
                i = R.id.iv_back;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_back);
                if (imageView != null) {
                    i = R.id.layout_car;
                    View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.layout_car);
                    if (findChildViewById2 != null) {
                        VoteCarBottomBinding bind = VoteCarBottomBinding.bind(findChildViewById2);
                        i = R.id.ll1;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll1);
                        if (linearLayout != null) {
                            i = R.id.ll2;
                            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll2);
                            if (linearLayout2 != null) {
                                i = R.id.ll_back;
                                LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_back);
                                if (linearLayout3 != null) {
                                    i = R.id.recyclerView;
                                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.recyclerView);
                                    if (recyclerView != null) {
                                        i = R.id.rl_head;
                                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_head);
                                        if (relativeLayout != null) {
                                            i = R.id.rl_title;
                                            RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_title);
                                            if (relativeLayout2 != null) {
                                                RelativeLayout relativeLayout3 = (RelativeLayout) view;
                                                i = R.id.title;
                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.title);
                                                if (textView2 != null) {
                                                    i = R.id.tv_clear_all;
                                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_clear_all);
                                                    if (textView3 != null) {
                                                        return new ItemBatchVoteDialogDetailBinding(relativeLayout3, textView, findChildViewById, imageView, bind, linearLayout, linearLayout2, linearLayout3, recyclerView, relativeLayout, relativeLayout2, relativeLayout3, textView2, textView3);
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
