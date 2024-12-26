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
import com.tronlinkpro.wallet.R;
public final class PullItemSignBinding implements ViewBinding {
    public final ImageView ivArrowRight;
    public final ImageView ivContractAddressError;
    public final ImageView ivContractTag;
    public final ImageView ivReceiveAddressError;
    public final LinearLayout llPullHash;
    public final LinearLayout llPullNote;
    public final LinearLayout llPullReceive;
    public final LinearLayout llPullSend;
    public final RelativeLayout rlContract;
    public final RelativeLayout rlContractAddress;
    public final RelativeLayout rlTokenId;
    private final RelativeLayout rootView;
    public final TextView tvContractAddress;
    public final TextView tvContractAddressErrorTips;
    public final TextView tvContractAddressTitle;
    public final TextView tvContractName;
    public final TextView tvNote;
    public final TextView tvReceiveAddress;
    public final ImageView tvReceiveAddressContractTag;
    public final TextView tvReceiveAddressErrorTips;
    public final TextView tvReceiveAddressName;
    public final TextView tvReceiveHash;
    public final TextView tvSendAddress;
    public final TextView tvSendAddressName;
    public final TextView tvTokenId;
    public final TextView tvTokenidErrorTips;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private PullItemSignBinding(RelativeLayout relativeLayout, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, ImageView imageView5, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13) {
        this.rootView = relativeLayout;
        this.ivArrowRight = imageView;
        this.ivContractAddressError = imageView2;
        this.ivContractTag = imageView3;
        this.ivReceiveAddressError = imageView4;
        this.llPullHash = linearLayout;
        this.llPullNote = linearLayout2;
        this.llPullReceive = linearLayout3;
        this.llPullSend = linearLayout4;
        this.rlContract = relativeLayout2;
        this.rlContractAddress = relativeLayout3;
        this.rlTokenId = relativeLayout4;
        this.tvContractAddress = textView;
        this.tvContractAddressErrorTips = textView2;
        this.tvContractAddressTitle = textView3;
        this.tvContractName = textView4;
        this.tvNote = textView5;
        this.tvReceiveAddress = textView6;
        this.tvReceiveAddressContractTag = imageView5;
        this.tvReceiveAddressErrorTips = textView7;
        this.tvReceiveAddressName = textView8;
        this.tvReceiveHash = textView9;
        this.tvSendAddress = textView10;
        this.tvSendAddressName = textView11;
        this.tvTokenId = textView12;
        this.tvTokenidErrorTips = textView13;
    }

    public static PullItemSignBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PullItemSignBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.pull_item_sign, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PullItemSignBinding bind(View view) {
        int i = R.id.iv_arrow_right;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_arrow_right);
        if (imageView != null) {
            i = R.id.iv_contract_address_error;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_contract_address_error);
            if (imageView2 != null) {
                i = R.id.iv_contract_tag;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_contract_tag);
                if (imageView3 != null) {
                    i = R.id.iv_receive_address_error;
                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_receive_address_error);
                    if (imageView4 != null) {
                        i = R.id.ll_pull_hash;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_pull_hash);
                        if (linearLayout != null) {
                            i = R.id.ll_pull_note;
                            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_pull_note);
                            if (linearLayout2 != null) {
                                i = R.id.ll_pull_receive;
                                LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_pull_receive);
                                if (linearLayout3 != null) {
                                    i = R.id.ll_pull_send;
                                    LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_pull_send);
                                    if (linearLayout4 != null) {
                                        i = R.id.rl_contract;
                                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_contract);
                                        if (relativeLayout != null) {
                                            i = R.id.rl_contract_address;
                                            RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_contract_address);
                                            if (relativeLayout2 != null) {
                                                i = R.id.rl_token_id;
                                                RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_token_id);
                                                if (relativeLayout3 != null) {
                                                    i = R.id.tv_contract_address;
                                                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_contract_address);
                                                    if (textView != null) {
                                                        i = R.id.tv_contract_address_error_tips;
                                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_contract_address_error_tips);
                                                        if (textView2 != null) {
                                                            i = R.id.tv_contract_address_title;
                                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_contract_address_title);
                                                            if (textView3 != null) {
                                                                i = R.id.tv_contract_name;
                                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_contract_name);
                                                                if (textView4 != null) {
                                                                    i = R.id.tv_note;
                                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_note);
                                                                    if (textView5 != null) {
                                                                        i = R.id.tv_receive_address;
                                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_receive_address);
                                                                        if (textView6 != null) {
                                                                            i = R.id.tv_receive_address_contract_tag;
                                                                            ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, R.id.tv_receive_address_contract_tag);
                                                                            if (imageView5 != null) {
                                                                                i = R.id.tv_receive_address_error_tips;
                                                                                TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_receive_address_error_tips);
                                                                                if (textView7 != null) {
                                                                                    i = R.id.tv_receive_address_name;
                                                                                    TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_receive_address_name);
                                                                                    if (textView8 != null) {
                                                                                        i = R.id.tv_receive_hash;
                                                                                        TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_receive_hash);
                                                                                        if (textView9 != null) {
                                                                                            i = R.id.tv_send_address;
                                                                                            TextView textView10 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_send_address);
                                                                                            if (textView10 != null) {
                                                                                                i = R.id.tv_send_address_name;
                                                                                                TextView textView11 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_send_address_name);
                                                                                                if (textView11 != null) {
                                                                                                    i = R.id.tv_token_id;
                                                                                                    TextView textView12 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_token_id);
                                                                                                    if (textView12 != null) {
                                                                                                        i = R.id.tv_tokenid_error_tips;
                                                                                                        TextView textView13 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_tokenid_error_tips);
                                                                                                        if (textView13 != null) {
                                                                                                            return new PullItemSignBinding((RelativeLayout) view, imageView, imageView2, imageView3, imageView4, linearLayout, linearLayout2, linearLayout3, linearLayout4, relativeLayout, relativeLayout2, relativeLayout3, textView, textView2, textView3, textView4, textView5, textView6, imageView5, textView7, textView8, textView9, textView10, textView11, textView12, textView13);
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
