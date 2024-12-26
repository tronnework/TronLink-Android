package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tron.wallet.common.components.LoadingView;
import com.tron.wallet.common.components.MultiSignPopupTextView;
import com.tronlinkpro.wallet.R;
public final class ItemSelectWalletNormalBinding implements ViewBinding {
    public final ImageView assetStatus;
    public final View bottomLine;
    public final MultiSignPopupTextView flagMultiSign;
    public final ImageView ivCopy;
    public final ImageView ivJump;
    public final LoadingView ivLoading;
    public final SimpleDraweeView ivType;
    public final RelativeLayout llContent;
    public final ConstraintLayout rlAddress;
    public final RelativeLayout rlInner;
    private final RelativeLayout rootView;
    public final RelativeLayout topCard;
    public final TextView tvAddress;
    public final TextView tvName;
    public final TextView tvPathIndex;
    public final TextView tvValue;
    public final View vLine;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemSelectWalletNormalBinding(RelativeLayout relativeLayout, ImageView imageView, View view, MultiSignPopupTextView multiSignPopupTextView, ImageView imageView2, ImageView imageView3, LoadingView loadingView, SimpleDraweeView simpleDraweeView, RelativeLayout relativeLayout2, ConstraintLayout constraintLayout, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, TextView textView, TextView textView2, TextView textView3, TextView textView4, View view2) {
        this.rootView = relativeLayout;
        this.assetStatus = imageView;
        this.bottomLine = view;
        this.flagMultiSign = multiSignPopupTextView;
        this.ivCopy = imageView2;
        this.ivJump = imageView3;
        this.ivLoading = loadingView;
        this.ivType = simpleDraweeView;
        this.llContent = relativeLayout2;
        this.rlAddress = constraintLayout;
        this.rlInner = relativeLayout3;
        this.topCard = relativeLayout4;
        this.tvAddress = textView;
        this.tvName = textView2;
        this.tvPathIndex = textView3;
        this.tvValue = textView4;
        this.vLine = view2;
    }

    public static ItemSelectWalletNormalBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemSelectWalletNormalBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_select_wallet_normal, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemSelectWalletNormalBinding bind(View view) {
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
                            i = R.id.iv_loading;
                            LoadingView loadingView = (LoadingView) ViewBindings.findChildViewById(view, R.id.iv_loading);
                            if (loadingView != null) {
                                i = R.id.iv_type;
                                SimpleDraweeView simpleDraweeView = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.iv_type);
                                if (simpleDraweeView != null) {
                                    i = R.id.ll_content;
                                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_content);
                                    if (relativeLayout != null) {
                                        i = R.id.rl_address;
                                        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.rl_address);
                                        if (constraintLayout != null) {
                                            RelativeLayout relativeLayout2 = (RelativeLayout) view;
                                            i = R.id.top_card;
                                            RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.top_card);
                                            if (relativeLayout3 != null) {
                                                i = R.id.tv_address;
                                                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address);
                                                if (textView != null) {
                                                    i = R.id.tv_name;
                                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name);
                                                    if (textView2 != null) {
                                                        i = R.id.tv_path_index;
                                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_path_index);
                                                        if (textView3 != null) {
                                                            i = R.id.tv_value;
                                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_value);
                                                            if (textView4 != null) {
                                                                i = R.id.v_line;
                                                                View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.v_line);
                                                                if (findChildViewById2 != null) {
                                                                    return new ItemSelectWalletNormalBinding(relativeLayout2, imageView, findChildViewById, multiSignPopupTextView, imageView2, imageView3, loadingView, simpleDraweeView, relativeLayout, constraintLayout, relativeLayout2, relativeLayout3, textView, textView2, textView3, textView4, findChildViewById2);
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
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
