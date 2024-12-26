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
import com.tron.wallet.business.confirm.fg.component.GlobalFeeResourceView;
import com.tronlinkpro.wallet.R;
public final class FgDappConfirmTransactionDetailBinding implements ViewBinding {
    public final TextView approveAmountTitle;
    public final TextView dappContractTitle;
    public final ImageView icon;
    public final ImageView ivAmountEdit;
    public final TextView noteTitle;
    public final TextView receivingAddress;
    public final TextView receivingAddressTitle;
    public final TextView receivingName;
    public final GlobalFeeResourceView resourceView;
    public final RelativeLayout rlAddress;
    public final RelativeLayout rlAmount;
    public final RelativeLayout rlApproveAmount;
    public final RelativeLayout rlContract;
    public final LinearLayout rlContractAddress;
    public final RelativeLayout rlNote;
    public final RelativeLayout rlTokenId;
    private final RelativeLayout rootView;
    public final ImageView scIcon;
    public final RelativeLayout scamTag;
    public final TextView transferOutAddress;
    public final TextView transferOutAddressTitle;
    public final TextView transferOutName;
    public final TextView tvAmount;
    public final TextView tvApproveAmount;
    public final TextView tvApproveNoLimitAmount;
    public final TextView tvContractAddress;
    public final TextView tvName;
    public final TextView tvNote;
    public final TextView tvState;
    public final TextView tvTokenId;
    public final TextView unit;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FgDappConfirmTransactionDetailBinding(RelativeLayout relativeLayout, TextView textView, TextView textView2, ImageView imageView, ImageView imageView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, GlobalFeeResourceView globalFeeResourceView, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, RelativeLayout relativeLayout5, LinearLayout linearLayout, RelativeLayout relativeLayout6, RelativeLayout relativeLayout7, ImageView imageView3, RelativeLayout relativeLayout8, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14, TextView textView15, TextView textView16, TextView textView17, TextView textView18) {
        this.rootView = relativeLayout;
        this.approveAmountTitle = textView;
        this.dappContractTitle = textView2;
        this.icon = imageView;
        this.ivAmountEdit = imageView2;
        this.noteTitle = textView3;
        this.receivingAddress = textView4;
        this.receivingAddressTitle = textView5;
        this.receivingName = textView6;
        this.resourceView = globalFeeResourceView;
        this.rlAddress = relativeLayout2;
        this.rlAmount = relativeLayout3;
        this.rlApproveAmount = relativeLayout4;
        this.rlContract = relativeLayout5;
        this.rlContractAddress = linearLayout;
        this.rlNote = relativeLayout6;
        this.rlTokenId = relativeLayout7;
        this.scIcon = imageView3;
        this.scamTag = relativeLayout8;
        this.transferOutAddress = textView7;
        this.transferOutAddressTitle = textView8;
        this.transferOutName = textView9;
        this.tvAmount = textView10;
        this.tvApproveAmount = textView11;
        this.tvApproveNoLimitAmount = textView12;
        this.tvContractAddress = textView13;
        this.tvName = textView14;
        this.tvNote = textView15;
        this.tvState = textView16;
        this.tvTokenId = textView17;
        this.unit = textView18;
    }

    public static FgDappConfirmTransactionDetailBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FgDappConfirmTransactionDetailBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fg_dapp_confirm_transaction_detail, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FgDappConfirmTransactionDetailBinding bind(View view) {
        int i = R.id.approve_amount_title;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.approve_amount_title);
        if (textView != null) {
            i = R.id.dapp_contract_title;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.dapp_contract_title);
            if (textView2 != null) {
                i = R.id.icon;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.icon);
                if (imageView != null) {
                    i = R.id.iv_amount_edit;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_amount_edit);
                    if (imageView2 != null) {
                        i = R.id.note_title;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.note_title);
                        if (textView3 != null) {
                            i = R.id.receiving_address;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.receiving_address);
                            if (textView4 != null) {
                                i = R.id.receiving_address_title;
                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.receiving_address_title);
                                if (textView5 != null) {
                                    i = R.id.receiving_name;
                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.receiving_name);
                                    if (textView6 != null) {
                                        i = R.id.resource_view;
                                        GlobalFeeResourceView globalFeeResourceView = (GlobalFeeResourceView) ViewBindings.findChildViewById(view, R.id.resource_view);
                                        if (globalFeeResourceView != null) {
                                            i = R.id.rl_address;
                                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_address);
                                            if (relativeLayout != null) {
                                                i = R.id.rl_amount;
                                                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_amount);
                                                if (relativeLayout2 != null) {
                                                    i = R.id.rl_approve_amount;
                                                    RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_approve_amount);
                                                    if (relativeLayout3 != null) {
                                                        i = R.id.rl_contract;
                                                        RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_contract);
                                                        if (relativeLayout4 != null) {
                                                            i = R.id.rl_contract_address;
                                                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.rl_contract_address);
                                                            if (linearLayout != null) {
                                                                i = R.id.rl_note;
                                                                RelativeLayout relativeLayout5 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_note);
                                                                if (relativeLayout5 != null) {
                                                                    i = R.id.rl_token_id;
                                                                    RelativeLayout relativeLayout6 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_token_id);
                                                                    if (relativeLayout6 != null) {
                                                                        i = R.id.sc_icon;
                                                                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.sc_icon);
                                                                        if (imageView3 != null) {
                                                                            i = R.id.scam_tag;
                                                                            RelativeLayout relativeLayout7 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.scam_tag);
                                                                            if (relativeLayout7 != null) {
                                                                                i = R.id.transfer_out_address;
                                                                                TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.transfer_out_address);
                                                                                if (textView7 != null) {
                                                                                    i = R.id.transfer_out_address_title;
                                                                                    TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.transfer_out_address_title);
                                                                                    if (textView8 != null) {
                                                                                        i = R.id.transfer_out_name;
                                                                                        TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.transfer_out_name);
                                                                                        if (textView9 != null) {
                                                                                            i = R.id.tv_amount;
                                                                                            TextView textView10 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_amount);
                                                                                            if (textView10 != null) {
                                                                                                i = R.id.tv_approve_amount;
                                                                                                TextView textView11 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_approve_amount);
                                                                                                if (textView11 != null) {
                                                                                                    i = R.id.tv_approve_no_limit_amount;
                                                                                                    TextView textView12 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_approve_no_limit_amount);
                                                                                                    if (textView12 != null) {
                                                                                                        i = R.id.tv_contract_address;
                                                                                                        TextView textView13 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_contract_address);
                                                                                                        if (textView13 != null) {
                                                                                                            i = R.id.tv_name;
                                                                                                            TextView textView14 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name);
                                                                                                            if (textView14 != null) {
                                                                                                                i = R.id.tv_note;
                                                                                                                TextView textView15 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_note);
                                                                                                                if (textView15 != null) {
                                                                                                                    i = R.id.tv_state;
                                                                                                                    TextView textView16 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_state);
                                                                                                                    if (textView16 != null) {
                                                                                                                        i = R.id.tv_token_id;
                                                                                                                        TextView textView17 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_token_id);
                                                                                                                        if (textView17 != null) {
                                                                                                                            i = R.id.unit;
                                                                                                                            TextView textView18 = (TextView) ViewBindings.findChildViewById(view, R.id.unit);
                                                                                                                            if (textView18 != null) {
                                                                                                                                return new FgDappConfirmTransactionDetailBinding((RelativeLayout) view, textView, textView2, imageView, imageView2, textView3, textView4, textView5, textView6, globalFeeResourceView, relativeLayout, relativeLayout2, relativeLayout3, relativeLayout4, linearLayout, relativeLayout5, relativeLayout6, imageView3, relativeLayout7, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14, textView15, textView16, textView17, textView18);
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
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
