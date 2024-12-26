package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.TokenLogoDraweeView;
import com.tronlinkpro.wallet.R;
public final class ItemTransferTopBinding implements ViewBinding {
    public final ImageView icTokenRightArrow;
    public final ImageView ivOfficialImage;
    public final LinearLayout llPrice;
    public final TextView price;
    public final RelativeLayout rlCurrentPrice;
    public final RelativeLayout rlPriceTrx;
    public final RelativeLayout rlTokenIcon;
    private final LinearLayout rootView;
    public final View splitLine;
    public final LinearLayout tokenHeaderRoot;
    public final TokenLogoDraweeView trx;
    public final TextView tvBalance;
    public final TextView tvCount;
    public final TextView tvCurrentPrice;
    public final TextView tvFreezeAmout;
    public final TextView tvNoteDetail;
    public final TextView tvPrice;
    public final TextView tvPriceTrx;
    public final TextView tvSumearnings;
    public final TextView tvYearnings;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private ItemTransferTopBinding(LinearLayout linearLayout, ImageView imageView, ImageView imageView2, LinearLayout linearLayout2, TextView textView, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, View view, LinearLayout linearLayout3, TokenLogoDraweeView tokenLogoDraweeView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10) {
        this.rootView = linearLayout;
        this.icTokenRightArrow = imageView;
        this.ivOfficialImage = imageView2;
        this.llPrice = linearLayout2;
        this.price = textView;
        this.rlCurrentPrice = relativeLayout;
        this.rlPriceTrx = relativeLayout2;
        this.rlTokenIcon = relativeLayout3;
        this.splitLine = view;
        this.tokenHeaderRoot = linearLayout3;
        this.trx = tokenLogoDraweeView;
        this.tvBalance = textView2;
        this.tvCount = textView3;
        this.tvCurrentPrice = textView4;
        this.tvFreezeAmout = textView5;
        this.tvNoteDetail = textView6;
        this.tvPrice = textView7;
        this.tvPriceTrx = textView8;
        this.tvSumearnings = textView9;
        this.tvYearnings = textView10;
    }

    public static ItemTransferTopBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemTransferTopBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_transfer_top, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemTransferTopBinding bind(View view) {
        int i = R.id.ic_token_right_arrow;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.ic_token_right_arrow);
        if (imageView != null) {
            i = R.id.iv_official_image;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_official_image);
            if (imageView2 != null) {
                i = R.id.ll_price;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_price);
                if (linearLayout != null) {
                    i = R.id.price;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.price);
                    if (textView != null) {
                        i = R.id.rl_current_price;
                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_current_price);
                        if (relativeLayout != null) {
                            i = R.id.rl_price_trx;
                            RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_price_trx);
                            if (relativeLayout2 != null) {
                                i = R.id.rl_token_icon;
                                RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_token_icon);
                                if (relativeLayout3 != null) {
                                    i = R.id.split_line;
                                    View findChildViewById = ViewBindings.findChildViewById(view, R.id.split_line);
                                    if (findChildViewById != null) {
                                        LinearLayout linearLayout2 = (LinearLayout) view;
                                        i = R.id.trx;
                                        TokenLogoDraweeView tokenLogoDraweeView = (TokenLogoDraweeView) ViewBindings.findChildViewById(view, R.id.trx);
                                        if (tokenLogoDraweeView != null) {
                                            i = R.id.tv_balance;
                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_balance);
                                            if (textView2 != null) {
                                                i = R.id.tv_count;
                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_count);
                                                if (textView3 != null) {
                                                    i = R.id.tv_current_price;
                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_current_price);
                                                    if (textView4 != null) {
                                                        i = R.id.tv_freeze_amout;
                                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_freeze_amout);
                                                        if (textView5 != null) {
                                                            i = R.id.tv_note_detail;
                                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_note_detail);
                                                            if (textView6 != null) {
                                                                i = R.id.tv_price;
                                                                TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_price);
                                                                if (textView7 != null) {
                                                                    i = R.id.tv_price_trx;
                                                                    TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_price_trx);
                                                                    if (textView8 != null) {
                                                                        i = R.id.tv_sumearnings;
                                                                        TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_sumearnings);
                                                                        if (textView9 != null) {
                                                                            i = R.id.tv_yearnings;
                                                                            TextView textView10 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_yearnings);
                                                                            if (textView10 != null) {
                                                                                return new ItemTransferTopBinding(linearLayout2, imageView, imageView2, linearLayout, textView, relativeLayout, relativeLayout2, relativeLayout3, findChildViewById, linearLayout2, tokenLogoDraweeView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10);
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
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
