package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tron.wallet.common.components.MultiSignPopupTextView;
import com.tronlinkpro.wallet.R;
public final class ItemSelectWalletBinding implements ViewBinding {
    public final ImageView assetStatus;
    public final View bottomLine;
    public final MultiSignPopupTextView flagMultiSign;
    public final ImageView ivCopy;
    public final ImageView ivJump;
    public final SimpleDraweeView ivType;
    public final LinearLayout llContent;
    public final ConstraintLayout rlAddress;
    public final RelativeLayout rlInner;
    private final RelativeLayout rootView;
    public final RelativeLayout topCard;
    public final TextView tvAddress;
    public final TextView tvName;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemSelectWalletBinding(RelativeLayout relativeLayout, ImageView imageView, View view, MultiSignPopupTextView multiSignPopupTextView, ImageView imageView2, ImageView imageView3, SimpleDraweeView simpleDraweeView, LinearLayout linearLayout, ConstraintLayout constraintLayout, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.assetStatus = imageView;
        this.bottomLine = view;
        this.flagMultiSign = multiSignPopupTextView;
        this.ivCopy = imageView2;
        this.ivJump = imageView3;
        this.ivType = simpleDraweeView;
        this.llContent = linearLayout;
        this.rlAddress = constraintLayout;
        this.rlInner = relativeLayout2;
        this.topCard = relativeLayout3;
        this.tvAddress = textView;
        this.tvName = textView2;
    }

    public static ItemSelectWalletBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemSelectWalletBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_select_wallet, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemSelectWalletBinding bind(View view) {
        int i = R.id.asset_status;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.asset_status);
        if (imageView != null) {
            i = R.id.bottom_line;
            View findChildViewById = ViewBindings.findChildViewById(view, R.id.bottom_line);
            if (findChildViewById != null) {
                i = R.id.flag_multi_sign;
                MultiSignPopupTextView multiSignPopupTextView = (MultiSignPopupTextView) ViewBindings.findChildViewById(view, R.id.flag_multi_sign);
                if (multiSignPopupTextView != null) {
                    i = R.id.iv_copy;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_copy);
                    if (imageView2 != null) {
                        i = R.id.iv_jump;
                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_jump);
                        if (imageView3 != null) {
                            i = R.id.iv_type;
                            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.iv_type);
                            if (simpleDraweeView != null) {
                                i = R.id.ll_content;
                                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_content);
                                if (linearLayout != null) {
                                    i = R.id.rl_address;
                                    ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.rl_address);
                                    if (constraintLayout != null) {
                                        RelativeLayout relativeLayout = (RelativeLayout) view;
                                        i = R.id.top_card;
                                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.top_card);
                                        if (relativeLayout2 != null) {
                                            i = R.id.tv_address;
                                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address);
                                            if (textView != null) {
                                                i = R.id.tv_name;
                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name);
                                                if (textView2 != null) {
                                                    return new ItemSelectWalletBinding(relativeLayout, imageView, findChildViewById, multiSignPopupTextView, imageView2, imageView3, simpleDraweeView, linearLayout, constraintLayout, relativeLayout, relativeLayout2, textView, textView2);
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
