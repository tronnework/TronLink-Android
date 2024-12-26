package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tron.wallet.common.components.ErrorView;
import com.tronlinkpro.wallet.R;
public final class AcPullDetailBinding implements ViewBinding {
    public final TextView btnCancel;
    public final TextView btnConfirm;
    public final View divider;
    public final ImageView ivHeader;
    public final SimpleDraweeView ivImage;
    public final LinearLayout liPullContent;
    public final LinearLayout llAction;
    public final LinearLayout llActionId;
    public final FrameLayout llContentCustomContainer;
    public final FrameLayout llHeaderCustomContainer;
    public final LinearLayout llName;
    public final RelativeLayout rlHead;
    public final RelativeLayout rlInvalidFromAccount;
    private final RelativeLayout rootView;
    public final NestedScrollView scrollView;
    public final TextView tvActionId;
    public final TextView tvActionType;
    public final ErrorView tvContentTip;
    public final ErrorView tvHeaderTip;
    public final TextView tvInvalidFromAccount;
    public final TextView tvInvalidFromAddress;
    public final TextView tvName;
    public final TextView tvNetType;
    public final TextView tvRight;
    public final TextView tvTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcPullDetailBinding(RelativeLayout relativeLayout, TextView textView, TextView textView2, View view, ImageView imageView, SimpleDraweeView simpleDraweeView, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, FrameLayout frameLayout, FrameLayout frameLayout2, LinearLayout linearLayout4, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, NestedScrollView nestedScrollView, TextView textView3, TextView textView4, ErrorView errorView, ErrorView errorView2, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10) {
        this.rootView = relativeLayout;
        this.btnCancel = textView;
        this.btnConfirm = textView2;
        this.divider = view;
        this.ivHeader = imageView;
        this.ivImage = simpleDraweeView;
        this.liPullContent = linearLayout;
        this.llAction = linearLayout2;
        this.llActionId = linearLayout3;
        this.llContentCustomContainer = frameLayout;
        this.llHeaderCustomContainer = frameLayout2;
        this.llName = linearLayout4;
        this.rlHead = relativeLayout2;
        this.rlInvalidFromAccount = relativeLayout3;
        this.scrollView = nestedScrollView;
        this.tvActionId = textView3;
        this.tvActionType = textView4;
        this.tvContentTip = errorView;
        this.tvHeaderTip = errorView2;
        this.tvInvalidFromAccount = textView5;
        this.tvInvalidFromAddress = textView6;
        this.tvName = textView7;
        this.tvNetType = textView8;
        this.tvRight = textView9;
        this.tvTitle = textView10;
    }

    public static AcPullDetailBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcPullDetailBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_pull_detail, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcPullDetailBinding bind(View view) {
        int i = R.id.btn_cancel;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.btn_cancel);
        if (textView != null) {
            i = R.id.btn_confirm;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.btn_confirm);
            if (textView2 != null) {
                i = R.id.divider;
                View findChildViewById = ViewBindings.findChildViewById(view, R.id.divider);
                if (findChildViewById != null) {
                    i = R.id.iv_header;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_header);
                    if (imageView != null) {
                        i = R.id.iv_image;
                        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.iv_image);
                        if (simpleDraweeView != null) {
                            i = R.id.li_pull_content;
                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_pull_content);
                            if (linearLayout != null) {
                                i = R.id.ll_action;
                                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_action);
                                if (linearLayout2 != null) {
                                    i = R.id.ll_action_id;
                                    LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_action_id);
                                    if (linearLayout3 != null) {
                                        i = R.id.ll_content_custom_container;
                                        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.ll_content_custom_container);
                                        if (frameLayout != null) {
                                            i = R.id.ll_header_custom_container;
                                            FrameLayout frameLayout2 = (FrameLayout) ViewBindings.findChildViewById(view, R.id.ll_header_custom_container);
                                            if (frameLayout2 != null) {
                                                i = R.id.ll_name;
                                                LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_name);
                                                if (linearLayout4 != null) {
                                                    i = R.id.rl_head;
                                                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_head);
                                                    if (relativeLayout != null) {
                                                        i = R.id.rl_invalid_from_account;
                                                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_invalid_from_account);
                                                        if (relativeLayout2 != null) {
                                                            i = R.id.scroll_view;
                                                            NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(view, R.id.scroll_view);
                                                            if (nestedScrollView != null) {
                                                                i = R.id.tv_action_id;
                                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_action_id);
                                                                if (textView3 != null) {
                                                                    i = R.id.tv_action_type;
                                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_action_type);
                                                                    if (textView4 != null) {
                                                                        i = R.id.tv_content_tip;
                                                                        ErrorView errorView = (ErrorView) ViewBindings.findChildViewById(view, R.id.tv_content_tip);
                                                                        if (errorView != null) {
                                                                            i = R.id.tv_header_tip;
                                                                            ErrorView errorView2 = (ErrorView) ViewBindings.findChildViewById(view, R.id.tv_header_tip);
                                                                            if (errorView2 != null) {
                                                                                i = R.id.tv_invalid_from_account;
                                                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_invalid_from_account);
                                                                                if (textView5 != null) {
                                                                                    i = R.id.tv_invalid_from_address;
                                                                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_invalid_from_address);
                                                                                    if (textView6 != null) {
                                                                                        i = R.id.tv_name;
                                                                                        TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name);
                                                                                        if (textView7 != null) {
                                                                                            i = R.id.tv_net_type;
                                                                                            TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_net_type);
                                                                                            if (textView8 != null) {
                                                                                                i = R.id.tv_right;
                                                                                                TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_right);
                                                                                                if (textView9 != null) {
                                                                                                    i = R.id.tv_title;
                                                                                                    TextView textView10 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                                                                                                    if (textView10 != null) {
                                                                                                        return new AcPullDetailBinding((RelativeLayout) view, textView, textView2, findChildViewById, imageView, simpleDraweeView, linearLayout, linearLayout2, linearLayout3, frameLayout, frameLayout2, linearLayout4, relativeLayout, relativeLayout2, nestedScrollView, textView3, textView4, errorView, errorView2, textView5, textView6, textView7, textView8, textView9, textView10);
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
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
