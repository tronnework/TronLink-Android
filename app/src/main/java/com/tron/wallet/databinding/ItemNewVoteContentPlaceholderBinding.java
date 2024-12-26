package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemNewVoteContentPlaceholderBinding implements ViewBinding {
    public final View divider;
    public final ImageView iv1;
    public final ImageView iv11;
    public final ImageView iv2;
    public final ImageView iv21;
    public final ImageView ivArrow;
    public final RelativeLayout ll1;
    public final RelativeLayout ll2;
    private final LinearLayout rootView;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private ItemNewVoteContentPlaceholderBinding(LinearLayout linearLayout, View view, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, RelativeLayout relativeLayout, RelativeLayout relativeLayout2) {
        this.rootView = linearLayout;
        this.divider = view;
        this.iv1 = imageView;
        this.iv11 = imageView2;
        this.iv2 = imageView3;
        this.iv21 = imageView4;
        this.ivArrow = imageView5;
        this.ll1 = relativeLayout;
        this.ll2 = relativeLayout2;
    }

    public static ItemNewVoteContentPlaceholderBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemNewVoteContentPlaceholderBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_new_vote_content_placeholder, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemNewVoteContentPlaceholderBinding bind(View view) {
        int i = R.id.divider;
        View findChildViewById = ViewBindings.findChildViewById(view, R.id.divider);
        if (findChildViewById != null) {
            i = R.id.iv1;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv1);
            if (imageView != null) {
                i = R.id.iv11;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv11);
                if (imageView2 != null) {
                    i = R.id.iv2;
                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv2);
                    if (imageView3 != null) {
                        i = R.id.iv21;
                        ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv21);
                        if (imageView4 != null) {
                            i = R.id.iv_arrow;
                            ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_arrow);
                            if (imageView5 != null) {
                                i = R.id.ll1;
                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll1);
                                if (relativeLayout != null) {
                                    i = R.id.ll2;
                                    RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll2);
                                    if (relativeLayout2 != null) {
                                        return new ItemNewVoteContentPlaceholderBinding((LinearLayout) view, findChildViewById, imageView, imageView2, imageView3, imageView4, imageView5, relativeLayout, relativeLayout2);
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
