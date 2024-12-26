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
import com.facebook.drawee.view.SimpleDraweeView;
import com.tron.wallet.common.components.ErrorEdiTextLayout;
import com.tron.wallet.common.components.SelectionWatcherEditText;
import com.tronlinkpro.wallet.R;
public final class SwapHeaderTokenBinding implements ViewBinding {
    public final RelativeLayout divider;
    public final ErrorEdiTextLayout errLayout;
    public final SelectionWatcherEditText etAmountFrom;
    public final SelectionWatcherEditText etAmountTo;
    public final SimpleDraweeView iconTokenFrom;
    public final SimpleDraweeView iconTokenTo;
    public final ImageView ivSelectFrom;
    public final ImageView ivSelectTo;
    public final ImageView ivTextEnd;
    public final ImageView ivTextEndFrom;
    public final RelativeLayout rlFrom;
    public final RelativeLayout rlTo;
    private final LinearLayout rootView;
    public final TextView tvAmountFrom;
    public final TextView tvAmountTo;
    public final TextView tvTokenNameFrom;
    public final TextView tvTokenNameTo;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private SwapHeaderTokenBinding(LinearLayout linearLayout, RelativeLayout relativeLayout, ErrorEdiTextLayout errorEdiTextLayout, SelectionWatcherEditText selectionWatcherEditText, SelectionWatcherEditText selectionWatcherEditText2, SimpleDraweeView simpleDraweeView, SimpleDraweeView simpleDraweeView2, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        this.rootView = linearLayout;
        this.divider = relativeLayout;
        this.errLayout = errorEdiTextLayout;
        this.etAmountFrom = selectionWatcherEditText;
        this.etAmountTo = selectionWatcherEditText2;
        this.iconTokenFrom = simpleDraweeView;
        this.iconTokenTo = simpleDraweeView2;
        this.ivSelectFrom = imageView;
        this.ivSelectTo = imageView2;
        this.ivTextEnd = imageView3;
        this.ivTextEndFrom = imageView4;
        this.rlFrom = relativeLayout2;
        this.rlTo = relativeLayout3;
        this.tvAmountFrom = textView;
        this.tvAmountTo = textView2;
        this.tvTokenNameFrom = textView3;
        this.tvTokenNameTo = textView4;
    }

    public static SwapHeaderTokenBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static SwapHeaderTokenBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.swap_header_token, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static SwapHeaderTokenBinding bind(View view) {
        int i = R.id.divider;
        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.divider);
        if (relativeLayout != null) {
            i = R.id.err_layout;
            ErrorEdiTextLayout errorEdiTextLayout = (ErrorEdiTextLayout) ViewBindings.findChildViewById(view, R.id.err_layout);
            if (errorEdiTextLayout != null) {
                i = R.id.et_amount_from;
                SelectionWatcherEditText selectionWatcherEditText = (SelectionWatcherEditText) ViewBindings.findChildViewById(view, R.id.et_amount_from);
                if (selectionWatcherEditText != null) {
                    i = R.id.et_amount_to;
                    SelectionWatcherEditText selectionWatcherEditText2 = (SelectionWatcherEditText) ViewBindings.findChildViewById(view, R.id.et_amount_to);
                    if (selectionWatcherEditText2 != null) {
                        i = R.id.icon_token_from;
                        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.icon_token_from);
                        if (simpleDraweeView != null) {
                            i = R.id.icon_token_to;
                            SimpleDraweeView simpleDraweeView2 = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.icon_token_to);
                            if (simpleDraweeView2 != null) {
                                i = R.id.iv_select_from;
                                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_select_from);
                                if (imageView != null) {
                                    i = R.id.iv_select_to;
                                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_select_to);
                                    if (imageView2 != null) {
                                        i = R.id.iv_text_end;
                                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_text_end);
                                        if (imageView3 != null) {
                                            i = R.id.iv_text_end_from;
                                            ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_text_end_from);
                                            if (imageView4 != null) {
                                                i = R.id.rl_from;
                                                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_from);
                                                if (relativeLayout2 != null) {
                                                    i = R.id.rl_to;
                                                    RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_to);
                                                    if (relativeLayout3 != null) {
                                                        i = R.id.tv_amount_from;
                                                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_amount_from);
                                                        if (textView != null) {
                                                            i = R.id.tv_amount_to;
                                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_amount_to);
                                                            if (textView2 != null) {
                                                                i = R.id.tv_token_name_from;
                                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_token_name_from);
                                                                if (textView3 != null) {
                                                                    i = R.id.tv_token_name_to;
                                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_token_name_to);
                                                                    if (textView4 != null) {
                                                                        return new SwapHeaderTokenBinding((LinearLayout) view, relativeLayout, errorEdiTextLayout, selectionWatcherEditText, selectionWatcherEditText2, simpleDraweeView, simpleDraweeView2, imageView, imageView2, imageView3, imageView4, relativeLayout2, relativeLayout3, textView, textView2, textView3, textView4);
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
