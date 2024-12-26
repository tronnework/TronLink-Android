package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemAssetsHeaderMenuBinding implements ViewBinding {
    public final ImageView ivFreeze;
    public final ImageView ivJustSwap;
    public final ImageView ivReceive;
    public final ImageView ivSend;
    public final ImageView ivStakeNew;
    public final ImageView ivVote;
    public final ConstraintLayout llDoMenu;
    public final RelativeLayout rlFreezeUnfreeze;
    public final RelativeLayout rlJustSwap;
    public final RelativeLayout rlReceive;
    public final RelativeLayout rlSend;
    public final RelativeLayout rlVote;
    private final ConstraintLayout rootView;

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    private ItemAssetsHeaderMenuBinding(ConstraintLayout constraintLayout, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView imageView6, ConstraintLayout constraintLayout2, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, RelativeLayout relativeLayout5) {
        this.rootView = constraintLayout;
        this.ivFreeze = imageView;
        this.ivJustSwap = imageView2;
        this.ivReceive = imageView3;
        this.ivSend = imageView4;
        this.ivStakeNew = imageView5;
        this.ivVote = imageView6;
        this.llDoMenu = constraintLayout2;
        this.rlFreezeUnfreeze = relativeLayout;
        this.rlJustSwap = relativeLayout2;
        this.rlReceive = relativeLayout3;
        this.rlSend = relativeLayout4;
        this.rlVote = relativeLayout5;
    }

    public static ItemAssetsHeaderMenuBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemAssetsHeaderMenuBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_assets_header_menu, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemAssetsHeaderMenuBinding bind(View view) {
        int i = R.id.iv_freeze;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_freeze);
        if (imageView != null) {
            i = R.id.iv_just_swap;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_just_swap);
            if (imageView2 != null) {
                i = R.id.iv_receive;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_receive);
                if (imageView3 != null) {
                    i = R.id.iv_send;
                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_send);
                    if (imageView4 != null) {
                        i = R.id.iv_stake_new;
                        ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_stake_new);
                        if (imageView5 != null) {
                            i = R.id.iv_vote;
                            ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_vote);
                            if (imageView6 != null) {
                                ConstraintLayout constraintLayout = (ConstraintLayout) view;
                                i = R.id.rl_freeze_unfreeze;
                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_freeze_unfreeze);
                                if (relativeLayout != null) {
                                    i = R.id.rl_just_swap;
                                    RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_just_swap);
                                    if (relativeLayout2 != null) {
                                        i = R.id.rl_receive;
                                        RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_receive);
                                        if (relativeLayout3 != null) {
                                            i = R.id.rl_send;
                                            RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_send);
                                            if (relativeLayout4 != null) {
                                                i = R.id.rl_vote;
                                                RelativeLayout relativeLayout5 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_vote);
                                                if (relativeLayout5 != null) {
                                                    return new ItemAssetsHeaderMenuBinding(constraintLayout, imageView, imageView2, imageView3, imageView4, imageView5, imageView6, constraintLayout, relativeLayout, relativeLayout2, relativeLayout3, relativeLayout4, relativeLayout5);
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
