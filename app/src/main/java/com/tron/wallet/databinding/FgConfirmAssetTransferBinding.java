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
public final class FgConfirmAssetTransferBinding implements ViewBinding {
    public final GlobalConfirmButton btnAssetConfirm;
    public final TextView dappContractTitle;
    public final GlobalTitleHeaderView headerView;
    public final ImageView icon;
    public final TextView noteDes;
    public final TextView receivingAddress;
    public final TextView receivingAddressTitle;
    public final TextView receivingName;
    public final GlobalFeeResourceView resourceView;
    public final RelativeLayout rlAddress;
    public final RelativeLayout rlAmount;
    public final RelativeLayout rlContract;
    public final LinearLayout rlContractAddress;
    public final RelativeLayout rlNote;
    public final RelativeLayout rlTokenId;
    public final RelativeLayout rlType;
    private final RelativeLayout rootView;
    public final ImageView scIcon;
    public final RelativeLayout scamTag;
    public final TextView transferOutAddress;
    public final TextView transferOutAddressTitle;
    public final TextView transferOutName;
    public final TextView tvAmount;
    public final TextView tvContractAddress;
    public final TextView tvName;
    public final TextView tvNote;
    public final TextView tvState;
    public final TextView tvTokenId;
    public final TextView tvType;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FgConfirmAssetTransferBinding(RelativeLayout relativeLayout, GlobalConfirmButton globalConfirmButton, TextView textView, GlobalTitleHeaderView globalTitleHeaderView, ImageView imageView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, GlobalFeeResourceView globalFeeResourceView, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, LinearLayout linearLayout, RelativeLayout relativeLayout5, RelativeLayout relativeLayout6, RelativeLayout relativeLayout7, ImageView imageView2, RelativeLayout relativeLayout8, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14, TextView textView15) {
        this.rootView = relativeLayout;
        this.btnAssetConfirm = globalConfirmButton;
        this.dappContractTitle = textView;
        this.headerView = globalTitleHeaderView;
        this.icon = imageView;
        this.noteDes = textView2;
        this.receivingAddress = textView3;
        this.receivingAddressTitle = textView4;
        this.receivingName = textView5;
        this.resourceView = globalFeeResourceView;
        this.rlAddress = relativeLayout2;
        this.rlAmount = relativeLayout3;
        this.rlContract = relativeLayout4;
        this.rlContractAddress = linearLayout;
        this.rlNote = relativeLayout5;
        this.rlTokenId = relativeLayout6;
        this.rlType = relativeLayout7;
        this.scIcon = imageView2;
        this.scamTag = relativeLayout8;
        this.transferOutAddress = textView6;
        this.transferOutAddressTitle = textView7;
        this.transferOutName = textView8;
        this.tvAmount = textView9;
        this.tvContractAddress = textView10;
        this.tvName = textView11;
        this.tvNote = textView12;
        this.tvState = textView13;
        this.tvTokenId = textView14;
        this.tvType = textView15;
    }

    public static FgConfirmAssetTransferBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FgConfirmAssetTransferBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fg_confirm_asset_transfer, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FgConfirmAssetTransferBinding bind(View view) {
        int i = R.id.btn_asset_confirm;
        GlobalConfirmButton globalConfirmButton = (GlobalConfirmButton) ViewBindings.findChildViewById(view, R.id.btn_asset_confirm);
        if (globalConfirmButton != null) {
            i = R.id.dapp_contract_title;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.dapp_contract_title);
            if (textView != null) {
                i = R.id.header_view;
                GlobalTitleHeaderView globalTitleHeaderView = (GlobalTitleHeaderView) ViewBindings.findChildViewById(view, R.id.header_view);
                if (globalTitleHeaderView != null) {
                    i = R.id.icon;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.icon);
                    if (imageView != null) {
                        i = R.id.note_des;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.note_des);
                        if (textView2 != null) {
                            i = R.id.receiving_address;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.receiving_address);
                            if (textView3 != null) {
                                i = R.id.receiving_address_title;
                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.receiving_address_title);
                                if (textView4 != null) {
                                    i = R.id.receiving_name;
                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.receiving_name);
                                    if (textView5 != null) {
                                        i = R.id.resource_view;
                                        GlobalFeeResourceView globalFeeResourceView = (GlobalFeeResourceView) ViewBindings.findChildViewById(view, R.id.resource_view);
                                        if (globalFeeResourceView != null) {
                                            i = R.id.rl_address;
                                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_address);
                                            if (relativeLayout != null) {
                                                i = R.id.rl_amount;
                                                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_amount);
                                                if (relativeLayout2 != null) {
                                                    i = R.id.rl_contract;
                                                    RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_contract);
                                                    if (relativeLayout3 != null) {
                                                        i = R.id.rl_contract_address;
                                                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.rl_contract_address);
                                                        if (linearLayout != null) {
                                                            i = R.id.rl_note;
                                                            RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_note);
                                                            if (relativeLayout4 != null) {
                                                                i = R.id.rl_token_id;
                                                                RelativeLayout relativeLayout5 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_token_id);
                                                                if (relativeLayout5 != null) {
                                                                    i = R.id.rl_type;
                                                                    RelativeLayout relativeLayout6 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_type);
                                                                    if (relativeLayout6 != null) {
                                                                        i = R.id.sc_icon;
                                                                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.sc_icon);
                                                                        if (imageView2 != null) {
                                                                            i = R.id.scam_tag;
                                                                            RelativeLayout relativeLayout7 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.scam_tag);
                                                                            if (relativeLayout7 != null) {
                                                                                i = R.id.transfer_out_address;
                                                                                TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.transfer_out_address);
                                                                                if (textView6 != null) {
                                                                                    i = R.id.transfer_out_address_title;
                                                                                    TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.transfer_out_address_title);
                                                                                    if (textView7 != null) {
                                                                                        i = R.id.transfer_out_name;
                                                                                        TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.transfer_out_name);
                                                                                        if (textView8 != null) {
                                                                                            i = R.id.tv_amount;
                                                                                            TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_amount);
                                                                                            if (textView9 != null) {
                                                                                                i = R.id.tv_contract_address;
                                                                                                TextView textView10 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_contract_address);
                                                                                                if (textView10 != null) {
                                                                                                    i = R.id.tv_name;
                                                                                                    TextView textView11 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name);
                                                                                                    if (textView11 != null) {
                                                                                                        i = R.id.tv_note;
                                                                                                        TextView textView12 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_note);
                                                                                                        if (textView12 != null) {
                                                                                                            i = R.id.tv_state;
                                                                                                            TextView textView13 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_state);
                                                                                                            if (textView13 != null) {
                                                                                                                i = R.id.tv_token_id;
                                                                                                                TextView textView14 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_token_id);
                                                                                                                if (textView14 != null) {
                                                                                                                    i = R.id.tv_type;
                                                                                                                    TextView textView15 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_type);
                                                                                                                    if (textView15 != null) {
                                                                                                                        return new FgConfirmAssetTransferBinding((RelativeLayout) view, globalConfirmButton, textView, globalTitleHeaderView, imageView, textView2, textView3, textView4, textView5, globalFeeResourceView, relativeLayout, relativeLayout2, relativeLayout3, linearLayout, relativeLayout4, relativeLayout5, relativeLayout6, imageView2, relativeLayout7, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14, textView15);
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
