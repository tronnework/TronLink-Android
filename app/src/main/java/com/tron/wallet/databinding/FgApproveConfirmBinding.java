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
import com.tron.wallet.business.confirm.fg.component.GlobalConfirmButton;
import com.tron.wallet.business.confirm.fg.component.GlobalFeeResourceView;
import com.tron.wallet.business.confirm.fg.component.GlobalTitleHeaderView;
import com.tronlinkpro.wallet.R;
public final class FgApproveConfirmBinding implements ViewBinding {
    public final TextView approveAmountTitle;
    public final GlobalConfirmButton confirmApprove;
    public final TextView dappContractTitle;
    public final RelativeLayout headerTitleView;
    public final GlobalTitleHeaderView headerView;
    public final ImageView ivAmountEdit;
    public final ImageView ivBack;
    public final ImageView ivCloseRight;
    public final TextView noteTitle;
    public final GlobalFeeResourceView resourceView;
    public final RelativeLayout rlAmount;
    public final RelativeLayout rlApproveAmount;
    public final RelativeLayout rlContent;
    public final RelativeLayout rlContract;
    public final LinearLayout rlContractAddress;
    public final RelativeLayout rlNote;
    public final RelativeLayout rlTokenId;
    public final RelativeLayout root;
    private final RelativeLayout rootView;
    public final ImageView scIcon;
    public final TextView tvAmount;
    public final TextView tvApproveAmount;
    public final TextView tvApproveNoLimitAmount;
    public final TextView tvContractAddress;
    public final TextView tvName;
    public final TextView tvNote;
    public final TextView tvTokenId;
    public final TextView unit;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FgApproveConfirmBinding(RelativeLayout relativeLayout, TextView textView, GlobalConfirmButton globalConfirmButton, TextView textView2, RelativeLayout relativeLayout2, GlobalTitleHeaderView globalTitleHeaderView, ImageView imageView, ImageView imageView2, ImageView imageView3, TextView textView3, GlobalFeeResourceView globalFeeResourceView, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, RelativeLayout relativeLayout5, RelativeLayout relativeLayout6, LinearLayout linearLayout, RelativeLayout relativeLayout7, RelativeLayout relativeLayout8, RelativeLayout relativeLayout9, ImageView imageView4, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11) {
        this.rootView = relativeLayout;
        this.approveAmountTitle = textView;
        this.confirmApprove = globalConfirmButton;
        this.dappContractTitle = textView2;
        this.headerTitleView = relativeLayout2;
        this.headerView = globalTitleHeaderView;
        this.ivAmountEdit = imageView;
        this.ivBack = imageView2;
        this.ivCloseRight = imageView3;
        this.noteTitle = textView3;
        this.resourceView = globalFeeResourceView;
        this.rlAmount = relativeLayout3;
        this.rlApproveAmount = relativeLayout4;
        this.rlContent = relativeLayout5;
        this.rlContract = relativeLayout6;
        this.rlContractAddress = linearLayout;
        this.rlNote = relativeLayout7;
        this.rlTokenId = relativeLayout8;
        this.root = relativeLayout9;
        this.scIcon = imageView4;
        this.tvAmount = textView4;
        this.tvApproveAmount = textView5;
        this.tvApproveNoLimitAmount = textView6;
        this.tvContractAddress = textView7;
        this.tvName = textView8;
        this.tvNote = textView9;
        this.tvTokenId = textView10;
        this.unit = textView11;
    }

    public static FgApproveConfirmBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FgApproveConfirmBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fg_approve_confirm, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FgApproveConfirmBinding bind(View view) {
        int i = R.id.approve_amount_title;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.approve_amount_title);
        if (textView != null) {
            i = R.id.confirm_approve;
            GlobalConfirmButton globalConfirmButton = (GlobalConfirmButton) ViewBindings.findChildViewById(view, R.id.confirm_approve);
            if (globalConfirmButton != null) {
                i = R.id.dapp_contract_title;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.dapp_contract_title);
                if (textView2 != null) {
                    i = R.id.header_title_view;
                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.header_title_view);
                    if (relativeLayout != null) {
                        i = R.id.header_view;
                        GlobalTitleHeaderView globalTitleHeaderView = (GlobalTitleHeaderView) ViewBindings.findChildViewById(view, R.id.header_view);
                        if (globalTitleHeaderView != null) {
                            i = R.id.iv_amount_edit;
                            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_amount_edit);
                            if (imageView != null) {
                                i = R.id.iv_back;
                                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_back);
                                if (imageView2 != null) {
                                    i = R.id.iv_close_right;
                                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_close_right);
                                    if (imageView3 != null) {
                                        i = R.id.note_title;
                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.note_title);
                                        if (textView3 != null) {
                                            i = R.id.resource_view;
                                            GlobalFeeResourceView globalFeeResourceView = (GlobalFeeResourceView) ViewBindings.findChildViewById(view, R.id.resource_view);
                                            if (globalFeeResourceView != null) {
                                                i = R.id.rl_amount;
                                                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_amount);
                                                if (relativeLayout2 != null) {
                                                    i = R.id.rl_approve_amount;
                                                    RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_approve_amount);
                                                    if (relativeLayout3 != null) {
                                                        i = R.id.rl_content;
                                                        RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_content);
                                                        if (relativeLayout4 != null) {
                                                            i = R.id.rl_contract;
                                                            RelativeLayout relativeLayout5 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_contract);
                                                            if (relativeLayout5 != null) {
                                                                i = R.id.rl_contract_address;
                                                                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.rl_contract_address);
                                                                if (linearLayout != null) {
                                                                    i = R.id.rl_note;
                                                                    RelativeLayout relativeLayout6 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_note);
                                                                    if (relativeLayout6 != null) {
                                                                        i = R.id.rl_token_id;
                                                                        RelativeLayout relativeLayout7 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_token_id);
                                                                        if (relativeLayout7 != null) {
                                                                            i = R.id.root;
                                                                            RelativeLayout relativeLayout8 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.root);
                                                                            if (relativeLayout8 != null) {
                                                                                i = R.id.sc_icon;
                                                                                ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.sc_icon);
                                                                                if (imageView4 != null) {
                                                                                    i = R.id.tv_amount;
                                                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_amount);
                                                                                    if (textView4 != null) {
                                                                                        i = R.id.tv_approve_amount;
                                                                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_approve_amount);
                                                                                        if (textView5 != null) {
                                                                                            i = R.id.tv_approve_no_limit_amount;
                                                                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_approve_no_limit_amount);
                                                                                            if (textView6 != null) {
                                                                                                i = R.id.tv_contract_address;
                                                                                                TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_contract_address);
                                                                                                if (textView7 != null) {
                                                                                                    i = R.id.tv_name;
                                                                                                    TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name);
                                                                                                    if (textView8 != null) {
                                                                                                        i = R.id.tv_note;
                                                                                                        TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_note);
                                                                                                        if (textView9 != null) {
                                                                                                            i = R.id.tv_token_id;
                                                                                                            TextView textView10 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_token_id);
                                                                                                            if (textView10 != null) {
                                                                                                                i = R.id.unit;
                                                                                                                TextView textView11 = (TextView) ViewBindings.findChildViewById(view, R.id.unit);
                                                                                                                if (textView11 != null) {
                                                                                                                    return new FgApproveConfirmBinding((RelativeLayout) view, textView, globalConfirmButton, textView2, relativeLayout, globalTitleHeaderView, imageView, imageView2, imageView3, textView3, globalFeeResourceView, relativeLayout2, relativeLayout3, relativeLayout4, relativeLayout5, linearLayout, relativeLayout6, relativeLayout7, relativeLayout8, imageView4, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11);
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
