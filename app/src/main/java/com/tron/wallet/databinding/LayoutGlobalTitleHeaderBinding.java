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
import com.tron.wallet.common.components.DashUnderLineTextView;
import com.tron.wallet.common.components.ItemWarningTagView;
import com.tron.wallet.common.components.TokenLogoDraweeView;
import com.tronlinkpro.wallet.R;
public final class LayoutGlobalTitleHeaderBinding implements ViewBinding {
    public final ImageView addrssPopIcon;
    public final TextView approveInfo;
    public final LinearLayout approveInfoLayout;
    public final RelativeLayout approveLayout;
    public final TextView approveLayoutTips;
    public final TextView approveProject;
    public final TextView approvedAddress;
    public final RelativeLayout approvedAddressLayout;
    public final TextView dappApproveTokenName;
    public final DashUnderLineTextView dashLine;
    public final TextView errorHint;
    public final TokenLogoDraweeView ivApproveIcon;
    public final LinearLayout ivClose;
    public final SimpleDraweeView ivIcon;
    public final ImageView ivIconTag;
    public final ImageView ivOfficialImage;
    public final SimpleDraweeView ivSubTitleIcon;
    public final ItemWarningTagView rlScam;
    public final RelativeLayout rlTopTwo;
    private final LinearLayout rootView;
    public final ImageView scIcon;
    public final TextView tradeTitle;
    public final TextView tradedAddress;
    public final TextView tvChainName;
    public final TextView tvConfirmTitle;
    public final TextView tvInfoSubtitle;
    public final TextView tvInfoSubtitleDetail;
    public final TextView tvInfoTitle;
    public final TextView tvScamNotice;
    public final TextView tvWalletName;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private LayoutGlobalTitleHeaderBinding(LinearLayout linearLayout, ImageView imageView, TextView textView, LinearLayout linearLayout2, RelativeLayout relativeLayout, TextView textView2, TextView textView3, TextView textView4, RelativeLayout relativeLayout2, TextView textView5, DashUnderLineTextView dashUnderLineTextView, TextView textView6, TokenLogoDraweeView tokenLogoDraweeView, LinearLayout linearLayout3, SimpleDraweeView simpleDraweeView, ImageView imageView2, ImageView imageView3, SimpleDraweeView simpleDraweeView2, ItemWarningTagView itemWarningTagView, RelativeLayout relativeLayout3, ImageView imageView4, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14, TextView textView15) {
        this.rootView = linearLayout;
        this.addrssPopIcon = imageView;
        this.approveInfo = textView;
        this.approveInfoLayout = linearLayout2;
        this.approveLayout = relativeLayout;
        this.approveLayoutTips = textView2;
        this.approveProject = textView3;
        this.approvedAddress = textView4;
        this.approvedAddressLayout = relativeLayout2;
        this.dappApproveTokenName = textView5;
        this.dashLine = dashUnderLineTextView;
        this.errorHint = textView6;
        this.ivApproveIcon = tokenLogoDraweeView;
        this.ivClose = linearLayout3;
        this.ivIcon = simpleDraweeView;
        this.ivIconTag = imageView2;
        this.ivOfficialImage = imageView3;
        this.ivSubTitleIcon = simpleDraweeView2;
        this.rlScam = itemWarningTagView;
        this.rlTopTwo = relativeLayout3;
        this.scIcon = imageView4;
        this.tradeTitle = textView7;
        this.tradedAddress = textView8;
        this.tvChainName = textView9;
        this.tvConfirmTitle = textView10;
        this.tvInfoSubtitle = textView11;
        this.tvInfoSubtitleDetail = textView12;
        this.tvInfoTitle = textView13;
        this.tvScamNotice = textView14;
        this.tvWalletName = textView15;
    }

    public static LayoutGlobalTitleHeaderBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutGlobalTitleHeaderBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_global_title_header, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutGlobalTitleHeaderBinding bind(View view) {
        int i = R.id.addrss_pop_icon;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.addrss_pop_icon);
        if (imageView != null) {
            i = R.id.approve_info;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.approve_info);
            if (textView != null) {
                i = R.id.approve_info_layout;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.approve_info_layout);
                if (linearLayout != null) {
                    i = R.id.approve_layout;
                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.approve_layout);
                    if (relativeLayout != null) {
                        i = R.id.approve_layout_tips;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.approve_layout_tips);
                        if (textView2 != null) {
                            i = R.id.approve_project;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.approve_project);
                            if (textView3 != null) {
                                i = R.id.approved_address;
                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.approved_address);
                                if (textView4 != null) {
                                    i = R.id.approved_address_layout;
                                    RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.approved_address_layout);
                                    if (relativeLayout2 != null) {
                                        i = R.id.dapp_approve_token_name;
                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.dapp_approve_token_name);
                                        if (textView5 != null) {
                                            i = R.id.dash_line;
                                            DashUnderLineTextView dashUnderLineTextView = (DashUnderLineTextView) ViewBindings.findChildViewById(view, R.id.dash_line);
                                            if (dashUnderLineTextView != null) {
                                                i = R.id.error_hint;
                                                TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.error_hint);
                                                if (textView6 != null) {
                                                    i = R.id.iv_approve_icon;
                                                    TokenLogoDraweeView tokenLogoDraweeView = (TokenLogoDraweeView) ViewBindings.findChildViewById(view, R.id.iv_approve_icon);
                                                    if (tokenLogoDraweeView != null) {
                                                        i = R.id.iv_close;
                                                        LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.iv_close);
                                                        if (linearLayout2 != null) {
                                                            i = R.id.iv_icon;
                                                            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.iv_icon);
                                                            if (simpleDraweeView != null) {
                                                                i = R.id.iv_icon_tag;
                                                                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_icon_tag);
                                                                if (imageView2 != null) {
                                                                    i = R.id.iv_official_image;
                                                                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_official_image);
                                                                    if (imageView3 != null) {
                                                                        i = R.id.iv_sub_title_icon;
                                                                        SimpleDraweeView simpleDraweeView2 = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.iv_sub_title_icon);
                                                                        if (simpleDraweeView2 != null) {
                                                                            i = R.id.rl_scam;
                                                                            ItemWarningTagView itemWarningTagView = (ItemWarningTagView) ViewBindings.findChildViewById(view, R.id.rl_scam);
                                                                            if (itemWarningTagView != null) {
                                                                                i = R.id.rl_top_two;
                                                                                RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_top_two);
                                                                                if (relativeLayout3 != null) {
                                                                                    i = R.id.sc_icon;
                                                                                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.sc_icon);
                                                                                    if (imageView4 != null) {
                                                                                        i = R.id.trade_title;
                                                                                        TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.trade_title);
                                                                                        if (textView7 != null) {
                                                                                            i = R.id.traded_address;
                                                                                            TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.traded_address);
                                                                                            if (textView8 != null) {
                                                                                                i = R.id.tv_chain_name;
                                                                                                TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_chain_name);
                                                                                                if (textView9 != null) {
                                                                                                    i = R.id.tv_confirm_title;
                                                                                                    TextView textView10 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_confirm_title);
                                                                                                    if (textView10 != null) {
                                                                                                        i = R.id.tv_info_subtitle;
                                                                                                        TextView textView11 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_info_subtitle);
                                                                                                        if (textView11 != null) {
                                                                                                            i = R.id.tv_info_subtitle_detail;
                                                                                                            TextView textView12 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_info_subtitle_detail);
                                                                                                            if (textView12 != null) {
                                                                                                                i = R.id.tv_info_title;
                                                                                                                TextView textView13 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_info_title);
                                                                                                                if (textView13 != null) {
                                                                                                                    i = R.id.tv_scam_notice;
                                                                                                                    TextView textView14 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_scam_notice);
                                                                                                                    if (textView14 != null) {
                                                                                                                        i = R.id.tv_wallet_name;
                                                                                                                        TextView textView15 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_wallet_name);
                                                                                                                        if (textView15 != null) {
                                                                                                                            return new LayoutGlobalTitleHeaderBinding((LinearLayout) view, imageView, textView, linearLayout, relativeLayout, textView2, textView3, textView4, relativeLayout2, textView5, dashUnderLineTextView, textView6, tokenLogoDraweeView, linearLayout2, simpleDraweeView, imageView2, imageView3, simpleDraweeView2, itemWarningTagView, relativeLayout3, imageView4, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14, textView15);
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
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
