package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.business.confirm.fg.component.TransactionFeeResourceView;
import com.tron.wallet.business.confirm.fg.component.TransactionResourceView;
import com.tron.wallet.common.components.EllipsizedTextView;
import com.tron.wallet.common.components.ItemWarningTagView;
import com.tronlinkpro.wallet.R;
public final class ActivityTransactionDetailBinding implements ViewBinding {
    public final ImageView ivCode;
    public final ImageView ivContractType;
    public final ImageView ivCopy1;
    public final ImageView ivCopy2;
    public final ImageView ivCopy3;
    public final ImageView ivCopy4;
    public final ImageView ivCopy5;
    public final ImageView ivHashCopy;
    public final ImageView ivRaCopy;
    public final ImageView ivRaSc;
    public final ImageView ivRightIcon;
    public final ImageView ivSaCopy;
    public final ImageView ivSaSc;
    public final ImageView ivScam;
    public final TransactionResourceView layoutTransactionDetailResource;
    public final LinearLayout liTvAmount;
    public final LinearLayout llAddressLayout;
    public final LinearLayout llCode;
    public final LinearLayout llCodeCopy;
    public final LinearLayout llContent;
    public final ConstraintLayout llHash;
    public final LinearLayout llNote;
    public final LinearLayout llRa;
    public final LinearLayout llRaTitle;
    public final LinearLayout llSa;
    public final LinearLayout llSaTitle;
    public final NestedScrollView llScroll;
    public final LinearLayout raLayout;
    public final TextView raTitle;
    public final TransactionFeeResourceView resourceInfoView2;
    public final ItemWarningTagView rlScam;
    public final RelativeLayout rlState;
    public final RelativeLayout rlTransferMenu;
    private final RelativeLayout rootView;
    public final LinearLayout saLayout;
    public final TextView saTitle;
    public final TextView scamGetMore;
    public final ImageView scamTag;
    public final ImageView scamTagSa;
    public final LinearLayout signAccountLayout;
    public final ConstraintLayout signAddressLayout1;
    public final ConstraintLayout signAddressLayout2;
    public final ConstraintLayout signAddressLayout3;
    public final ConstraintLayout signAddressLayout4;
    public final ConstraintLayout signAddressLayout5;
    public final TextView tipsContent;
    public final LinearLayout topScamLayout;
    public final LinearLayout transactionInfoBottom;
    public final TextView tvAmount;
    public final View tvAmountLine;
    public final TextView tvApprovedAmount;
    public final TextView tvApprovedAmountTitle;
    public final TextView tvBn;
    public final TextView tvBnTitle;
    public final TextView tvContractType;
    public final TextView tvContractTypeTop;
    public final TextView tvNote;
    public final EllipsizedTextView tvRa;
    public final TextView tvReasonFailure;
    public final TextView tvReasonFailureTitle;
    public final TextView tvResource;
    public final TextView tvResourceTitle;
    public final EllipsizedTextView tvSa;
    public final EllipsizedTextView tvSignAddress1;
    public final TextView tvSignAddress1Name;
    public final EllipsizedTextView tvSignAddress2;
    public final TextView tvSignAddress2Name;
    public final EllipsizedTextView tvSignAddress3;
    public final TextView tvSignAddress3Name;
    public final EllipsizedTextView tvSignAddress4;
    public final TextView tvSignAddress4Name;
    public final EllipsizedTextView tvSignAddress5;
    public final TextView tvSignAddress5Name;
    public final TextView tvState;
    public final TextView tvTime;
    public final TextView tvTimeTitle;
    public final EllipsizedTextView tvTn;
    public final TextView tvVdd;
    public final TextView tvWalletNameRa;
    public final TextView tvWalletNameSa;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ActivityTransactionDetailBinding(RelativeLayout relativeLayout, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView imageView6, ImageView imageView7, ImageView imageView8, ImageView imageView9, ImageView imageView10, ImageView imageView11, ImageView imageView12, ImageView imageView13, ImageView imageView14, TransactionResourceView transactionResourceView, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, LinearLayout linearLayout5, ConstraintLayout constraintLayout, LinearLayout linearLayout6, LinearLayout linearLayout7, LinearLayout linearLayout8, LinearLayout linearLayout9, LinearLayout linearLayout10, NestedScrollView nestedScrollView, LinearLayout linearLayout11, TextView textView, TransactionFeeResourceView transactionFeeResourceView, ItemWarningTagView itemWarningTagView, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, LinearLayout linearLayout12, TextView textView2, TextView textView3, ImageView imageView15, ImageView imageView16, LinearLayout linearLayout13, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, ConstraintLayout constraintLayout6, TextView textView4, LinearLayout linearLayout14, LinearLayout linearLayout15, TextView textView5, View view, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, EllipsizedTextView ellipsizedTextView, TextView textView13, TextView textView14, TextView textView15, TextView textView16, EllipsizedTextView ellipsizedTextView2, EllipsizedTextView ellipsizedTextView3, TextView textView17, EllipsizedTextView ellipsizedTextView4, TextView textView18, EllipsizedTextView ellipsizedTextView5, TextView textView19, EllipsizedTextView ellipsizedTextView6, TextView textView20, EllipsizedTextView ellipsizedTextView7, TextView textView21, TextView textView22, TextView textView23, TextView textView24, EllipsizedTextView ellipsizedTextView8, TextView textView25, TextView textView26, TextView textView27) {
        this.rootView = relativeLayout;
        this.ivCode = imageView;
        this.ivContractType = imageView2;
        this.ivCopy1 = imageView3;
        this.ivCopy2 = imageView4;
        this.ivCopy3 = imageView5;
        this.ivCopy4 = imageView6;
        this.ivCopy5 = imageView7;
        this.ivHashCopy = imageView8;
        this.ivRaCopy = imageView9;
        this.ivRaSc = imageView10;
        this.ivRightIcon = imageView11;
        this.ivSaCopy = imageView12;
        this.ivSaSc = imageView13;
        this.ivScam = imageView14;
        this.layoutTransactionDetailResource = transactionResourceView;
        this.liTvAmount = linearLayout;
        this.llAddressLayout = linearLayout2;
        this.llCode = linearLayout3;
        this.llCodeCopy = linearLayout4;
        this.llContent = linearLayout5;
        this.llHash = constraintLayout;
        this.llNote = linearLayout6;
        this.llRa = linearLayout7;
        this.llRaTitle = linearLayout8;
        this.llSa = linearLayout9;
        this.llSaTitle = linearLayout10;
        this.llScroll = nestedScrollView;
        this.raLayout = linearLayout11;
        this.raTitle = textView;
        this.resourceInfoView2 = transactionFeeResourceView;
        this.rlScam = itemWarningTagView;
        this.rlState = relativeLayout2;
        this.rlTransferMenu = relativeLayout3;
        this.saLayout = linearLayout12;
        this.saTitle = textView2;
        this.scamGetMore = textView3;
        this.scamTag = imageView15;
        this.scamTagSa = imageView16;
        this.signAccountLayout = linearLayout13;
        this.signAddressLayout1 = constraintLayout2;
        this.signAddressLayout2 = constraintLayout3;
        this.signAddressLayout3 = constraintLayout4;
        this.signAddressLayout4 = constraintLayout5;
        this.signAddressLayout5 = constraintLayout6;
        this.tipsContent = textView4;
        this.topScamLayout = linearLayout14;
        this.transactionInfoBottom = linearLayout15;
        this.tvAmount = textView5;
        this.tvAmountLine = view;
        this.tvApprovedAmount = textView6;
        this.tvApprovedAmountTitle = textView7;
        this.tvBn = textView8;
        this.tvBnTitle = textView9;
        this.tvContractType = textView10;
        this.tvContractTypeTop = textView11;
        this.tvNote = textView12;
        this.tvRa = ellipsizedTextView;
        this.tvReasonFailure = textView13;
        this.tvReasonFailureTitle = textView14;
        this.tvResource = textView15;
        this.tvResourceTitle = textView16;
        this.tvSa = ellipsizedTextView2;
        this.tvSignAddress1 = ellipsizedTextView3;
        this.tvSignAddress1Name = textView17;
        this.tvSignAddress2 = ellipsizedTextView4;
        this.tvSignAddress2Name = textView18;
        this.tvSignAddress3 = ellipsizedTextView5;
        this.tvSignAddress3Name = textView19;
        this.tvSignAddress4 = ellipsizedTextView6;
        this.tvSignAddress4Name = textView20;
        this.tvSignAddress5 = ellipsizedTextView7;
        this.tvSignAddress5Name = textView21;
        this.tvState = textView22;
        this.tvTime = textView23;
        this.tvTimeTitle = textView24;
        this.tvTn = ellipsizedTextView8;
        this.tvVdd = textView25;
        this.tvWalletNameRa = textView26;
        this.tvWalletNameSa = textView27;
    }

    public static ActivityTransactionDetailBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityTransactionDetailBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_transaction_detail, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityTransactionDetailBinding bind(View view) {
        int i = R.id.iv_code;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_code);
        if (imageView != null) {
            i = R.id.iv_contract_type;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_contract_type);
            if (imageView2 != null) {
                i = R.id.iv_copy_1;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_copy_1);
                if (imageView3 != null) {
                    i = R.id.iv_copy_2;
                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_copy_2);
                    if (imageView4 != null) {
                        i = R.id.iv_copy_3;
                        ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_copy_3);
                        if (imageView5 != null) {
                            i = R.id.iv_copy_4;
                            ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_copy_4);
                            if (imageView6 != null) {
                                i = R.id.iv_copy_5;
                                ImageView imageView7 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_copy_5);
                                if (imageView7 != null) {
                                    i = R.id.iv_hash_copy;
                                    ImageView imageView8 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_hash_copy);
                                    if (imageView8 != null) {
                                        i = R.id.iv_ra_copy;
                                        ImageView imageView9 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_ra_copy);
                                        if (imageView9 != null) {
                                            i = R.id.iv_ra_sc;
                                            ImageView imageView10 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_ra_sc);
                                            if (imageView10 != null) {
                                                i = R.id.iv_right_icon;
                                                ImageView imageView11 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_right_icon);
                                                if (imageView11 != null) {
                                                    i = R.id.iv_sa_copy;
                                                    ImageView imageView12 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_sa_copy);
                                                    if (imageView12 != null) {
                                                        i = R.id.iv_sa_sc;
                                                        ImageView imageView13 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_sa_sc);
                                                        if (imageView13 != null) {
                                                            i = R.id.iv_scam;
                                                            ImageView imageView14 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_scam);
                                                            if (imageView14 != null) {
                                                                i = R.id.layout_transaction_detail_resource;
                                                                TransactionResourceView transactionResourceView = (TransactionResourceView) ViewBindings.findChildViewById(view, R.id.layout_transaction_detail_resource);
                                                                if (transactionResourceView != null) {
                                                                    i = R.id.li_tv_amount;
                                                                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_tv_amount);
                                                                    if (linearLayout != null) {
                                                                        i = R.id.ll_address_layout;
                                                                        LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_address_layout);
                                                                        if (linearLayout2 != null) {
                                                                            i = R.id.ll_code;
                                                                            LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_code);
                                                                            if (linearLayout3 != null) {
                                                                                i = R.id.ll_code_copy;
                                                                                LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_code_copy);
                                                                                if (linearLayout4 != null) {
                                                                                    i = R.id.ll_content;
                                                                                    LinearLayout linearLayout5 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_content);
                                                                                    if (linearLayout5 != null) {
                                                                                        i = R.id.ll_hash;
                                                                                        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.ll_hash);
                                                                                        if (constraintLayout != null) {
                                                                                            i = R.id.ll_note;
                                                                                            LinearLayout linearLayout6 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_note);
                                                                                            if (linearLayout6 != null) {
                                                                                                i = R.id.ll_ra;
                                                                                                LinearLayout linearLayout7 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_ra);
                                                                                                if (linearLayout7 != null) {
                                                                                                    i = R.id.ll_ra_title;
                                                                                                    LinearLayout linearLayout8 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_ra_title);
                                                                                                    if (linearLayout8 != null) {
                                                                                                        i = R.id.ll_sa;
                                                                                                        LinearLayout linearLayout9 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_sa);
                                                                                                        if (linearLayout9 != null) {
                                                                                                            i = R.id.ll_sa_title;
                                                                                                            LinearLayout linearLayout10 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_sa_title);
                                                                                                            if (linearLayout10 != null) {
                                                                                                                i = R.id.ll_scroll;
                                                                                                                NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(view, R.id.ll_scroll);
                                                                                                                if (nestedScrollView != null) {
                                                                                                                    i = R.id.ra_layout;
                                                                                                                    LinearLayout linearLayout11 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ra_layout);
                                                                                                                    if (linearLayout11 != null) {
                                                                                                                        i = R.id.ra_title;
                                                                                                                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.ra_title);
                                                                                                                        if (textView != null) {
                                                                                                                            i = R.id.resource_info_view2;
                                                                                                                            TransactionFeeResourceView transactionFeeResourceView = (TransactionFeeResourceView) ViewBindings.findChildViewById(view, R.id.resource_info_view2);
                                                                                                                            if (transactionFeeResourceView != null) {
                                                                                                                                i = R.id.rl_scam;
                                                                                                                                ItemWarningTagView itemWarningTagView = (ItemWarningTagView) ViewBindings.findChildViewById(view, R.id.rl_scam);
                                                                                                                                if (itemWarningTagView != null) {
                                                                                                                                    i = R.id.rl_state;
                                                                                                                                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_state);
                                                                                                                                    if (relativeLayout != null) {
                                                                                                                                        i = R.id.rl_transfer_menu;
                                                                                                                                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_transfer_menu);
                                                                                                                                        if (relativeLayout2 != null) {
                                                                                                                                            i = R.id.sa_layout;
                                                                                                                                            LinearLayout linearLayout12 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.sa_layout);
                                                                                                                                            if (linearLayout12 != null) {
                                                                                                                                                i = R.id.sa_title;
                                                                                                                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.sa_title);
                                                                                                                                                if (textView2 != null) {
                                                                                                                                                    i = R.id.scam_get_more;
                                                                                                                                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.scam_get_more);
                                                                                                                                                    if (textView3 != null) {
                                                                                                                                                        i = R.id.scam_tag;
                                                                                                                                                        ImageView imageView15 = (ImageView) ViewBindings.findChildViewById(view, R.id.scam_tag);
                                                                                                                                                        if (imageView15 != null) {
                                                                                                                                                            i = R.id.scam_tag_sa;
                                                                                                                                                            ImageView imageView16 = (ImageView) ViewBindings.findChildViewById(view, R.id.scam_tag_sa);
                                                                                                                                                            if (imageView16 != null) {
                                                                                                                                                                i = R.id.sign_account_layout;
                                                                                                                                                                LinearLayout linearLayout13 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.sign_account_layout);
                                                                                                                                                                if (linearLayout13 != null) {
                                                                                                                                                                    i = R.id.sign_address_layout_1;
                                                                                                                                                                    ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.sign_address_layout_1);
                                                                                                                                                                    if (constraintLayout2 != null) {
                                                                                                                                                                        i = R.id.sign_address_layout_2;
                                                                                                                                                                        ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.sign_address_layout_2);
                                                                                                                                                                        if (constraintLayout3 != null) {
                                                                                                                                                                            i = R.id.sign_address_layout_3;
                                                                                                                                                                            ConstraintLayout constraintLayout4 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.sign_address_layout_3);
                                                                                                                                                                            if (constraintLayout4 != null) {
                                                                                                                                                                                i = R.id.sign_address_layout_4;
                                                                                                                                                                                ConstraintLayout constraintLayout5 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.sign_address_layout_4);
                                                                                                                                                                                if (constraintLayout5 != null) {
                                                                                                                                                                                    i = R.id.sign_address_layout_5;
                                                                                                                                                                                    ConstraintLayout constraintLayout6 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.sign_address_layout_5);
                                                                                                                                                                                    if (constraintLayout6 != null) {
                                                                                                                                                                                        i = R.id.tips_content;
                                                                                                                                                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tips_content);
                                                                                                                                                                                        if (textView4 != null) {
                                                                                                                                                                                            i = R.id.top_scam_layout;
                                                                                                                                                                                            LinearLayout linearLayout14 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.top_scam_layout);
                                                                                                                                                                                            if (linearLayout14 != null) {
                                                                                                                                                                                                i = R.id.transaction_info_bottom;
                                                                                                                                                                                                LinearLayout linearLayout15 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.transaction_info_bottom);
                                                                                                                                                                                                if (linearLayout15 != null) {
                                                                                                                                                                                                    i = R.id.tv_amount;
                                                                                                                                                                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_amount);
                                                                                                                                                                                                    if (textView5 != null) {
                                                                                                                                                                                                        i = R.id.tv_amount_line;
                                                                                                                                                                                                        View findChildViewById = ViewBindings.findChildViewById(view, R.id.tv_amount_line);
                                                                                                                                                                                                        if (findChildViewById != null) {
                                                                                                                                                                                                            i = R.id.tv_approved_amount;
                                                                                                                                                                                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_approved_amount);
                                                                                                                                                                                                            if (textView6 != null) {
                                                                                                                                                                                                                i = R.id.tv_approved_amount_title;
                                                                                                                                                                                                                TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_approved_amount_title);
                                                                                                                                                                                                                if (textView7 != null) {
                                                                                                                                                                                                                    i = R.id.tv_bn;
                                                                                                                                                                                                                    TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_bn);
                                                                                                                                                                                                                    if (textView8 != null) {
                                                                                                                                                                                                                        i = R.id.tv_bn_title;
                                                                                                                                                                                                                        TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_bn_title);
                                                                                                                                                                                                                        if (textView9 != null) {
                                                                                                                                                                                                                            i = R.id.tv_contract_type;
                                                                                                                                                                                                                            TextView textView10 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_contract_type);
                                                                                                                                                                                                                            if (textView10 != null) {
                                                                                                                                                                                                                                i = R.id.tv_contract_type_top;
                                                                                                                                                                                                                                TextView textView11 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_contract_type_top);
                                                                                                                                                                                                                                if (textView11 != null) {
                                                                                                                                                                                                                                    i = R.id.tv_note;
                                                                                                                                                                                                                                    TextView textView12 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_note);
                                                                                                                                                                                                                                    if (textView12 != null) {
                                                                                                                                                                                                                                        i = R.id.tv_ra;
                                                                                                                                                                                                                                        EllipsizedTextView ellipsizedTextView = (EllipsizedTextView) ViewBindings.findChildViewById(view, R.id.tv_ra);
                                                                                                                                                                                                                                        if (ellipsizedTextView != null) {
                                                                                                                                                                                                                                            i = R.id.tv_reason_failure;
                                                                                                                                                                                                                                            TextView textView13 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_reason_failure);
                                                                                                                                                                                                                                            if (textView13 != null) {
                                                                                                                                                                                                                                                i = R.id.tv_reason_failure_title;
                                                                                                                                                                                                                                                TextView textView14 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_reason_failure_title);
                                                                                                                                                                                                                                                if (textView14 != null) {
                                                                                                                                                                                                                                                    i = R.id.tv_resource;
                                                                                                                                                                                                                                                    TextView textView15 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_resource);
                                                                                                                                                                                                                                                    if (textView15 != null) {
                                                                                                                                                                                                                                                        i = R.id.tv_resource_title;
                                                                                                                                                                                                                                                        TextView textView16 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_resource_title);
                                                                                                                                                                                                                                                        if (textView16 != null) {
                                                                                                                                                                                                                                                            i = R.id.tv_sa;
                                                                                                                                                                                                                                                            EllipsizedTextView ellipsizedTextView2 = (EllipsizedTextView) ViewBindings.findChildViewById(view, R.id.tv_sa);
                                                                                                                                                                                                                                                            if (ellipsizedTextView2 != null) {
                                                                                                                                                                                                                                                                i = R.id.tv_sign_address_1;
                                                                                                                                                                                                                                                                EllipsizedTextView ellipsizedTextView3 = (EllipsizedTextView) ViewBindings.findChildViewById(view, R.id.tv_sign_address_1);
                                                                                                                                                                                                                                                                if (ellipsizedTextView3 != null) {
                                                                                                                                                                                                                                                                    i = R.id.tv_sign_address_1_name;
                                                                                                                                                                                                                                                                    TextView textView17 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_sign_address_1_name);
                                                                                                                                                                                                                                                                    if (textView17 != null) {
                                                                                                                                                                                                                                                                        i = R.id.tv_sign_address_2;
                                                                                                                                                                                                                                                                        EllipsizedTextView ellipsizedTextView4 = (EllipsizedTextView) ViewBindings.findChildViewById(view, R.id.tv_sign_address_2);
                                                                                                                                                                                                                                                                        if (ellipsizedTextView4 != null) {
                                                                                                                                                                                                                                                                            i = R.id.tv_sign_address_2_name;
                                                                                                                                                                                                                                                                            TextView textView18 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_sign_address_2_name);
                                                                                                                                                                                                                                                                            if (textView18 != null) {
                                                                                                                                                                                                                                                                                i = R.id.tv_sign_address_3;
                                                                                                                                                                                                                                                                                EllipsizedTextView ellipsizedTextView5 = (EllipsizedTextView) ViewBindings.findChildViewById(view, R.id.tv_sign_address_3);
                                                                                                                                                                                                                                                                                if (ellipsizedTextView5 != null) {
                                                                                                                                                                                                                                                                                    i = R.id.tv_sign_address_3_name;
                                                                                                                                                                                                                                                                                    TextView textView19 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_sign_address_3_name);
                                                                                                                                                                                                                                                                                    if (textView19 != null) {
                                                                                                                                                                                                                                                                                        i = R.id.tv_sign_address_4;
                                                                                                                                                                                                                                                                                        EllipsizedTextView ellipsizedTextView6 = (EllipsizedTextView) ViewBindings.findChildViewById(view, R.id.tv_sign_address_4);
                                                                                                                                                                                                                                                                                        if (ellipsizedTextView6 != null) {
                                                                                                                                                                                                                                                                                            i = R.id.tv_sign_address_4_name;
                                                                                                                                                                                                                                                                                            TextView textView20 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_sign_address_4_name);
                                                                                                                                                                                                                                                                                            if (textView20 != null) {
                                                                                                                                                                                                                                                                                                i = R.id.tv_sign_address_5;
                                                                                                                                                                                                                                                                                                EllipsizedTextView ellipsizedTextView7 = (EllipsizedTextView) ViewBindings.findChildViewById(view, R.id.tv_sign_address_5);
                                                                                                                                                                                                                                                                                                if (ellipsizedTextView7 != null) {
                                                                                                                                                                                                                                                                                                    i = R.id.tv_sign_address_5_name;
                                                                                                                                                                                                                                                                                                    TextView textView21 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_sign_address_5_name);
                                                                                                                                                                                                                                                                                                    if (textView21 != null) {
                                                                                                                                                                                                                                                                                                        i = R.id.tv_state;
                                                                                                                                                                                                                                                                                                        TextView textView22 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_state);
                                                                                                                                                                                                                                                                                                        if (textView22 != null) {
                                                                                                                                                                                                                                                                                                            i = R.id.tv_time;
                                                                                                                                                                                                                                                                                                            TextView textView23 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_time);
                                                                                                                                                                                                                                                                                                            if (textView23 != null) {
                                                                                                                                                                                                                                                                                                                i = R.id.tv_time_title;
                                                                                                                                                                                                                                                                                                                TextView textView24 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_time_title);
                                                                                                                                                                                                                                                                                                                if (textView24 != null) {
                                                                                                                                                                                                                                                                                                                    i = R.id.tv_tn;
                                                                                                                                                                                                                                                                                                                    EllipsizedTextView ellipsizedTextView8 = (EllipsizedTextView) ViewBindings.findChildViewById(view, R.id.tv_tn);
                                                                                                                                                                                                                                                                                                                    if (ellipsizedTextView8 != null) {
                                                                                                                                                                                                                                                                                                                        i = R.id.tv_vdd;
                                                                                                                                                                                                                                                                                                                        TextView textView25 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_vdd);
                                                                                                                                                                                                                                                                                                                        if (textView25 != null) {
                                                                                                                                                                                                                                                                                                                            i = R.id.tv_wallet_name_ra;
                                                                                                                                                                                                                                                                                                                            TextView textView26 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_wallet_name_ra);
                                                                                                                                                                                                                                                                                                                            if (textView26 != null) {
                                                                                                                                                                                                                                                                                                                                i = R.id.tv_wallet_name_sa;
                                                                                                                                                                                                                                                                                                                                TextView textView27 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_wallet_name_sa);
                                                                                                                                                                                                                                                                                                                                if (textView27 != null) {
                                                                                                                                                                                                                                                                                                                                    return new ActivityTransactionDetailBinding((RelativeLayout) view, imageView, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9, imageView10, imageView11, imageView12, imageView13, imageView14, transactionResourceView, linearLayout, linearLayout2, linearLayout3, linearLayout4, linearLayout5, constraintLayout, linearLayout6, linearLayout7, linearLayout8, linearLayout9, linearLayout10, nestedScrollView, linearLayout11, textView, transactionFeeResourceView, itemWarningTagView, relativeLayout, relativeLayout2, linearLayout12, textView2, textView3, imageView15, imageView16, linearLayout13, constraintLayout2, constraintLayout3, constraintLayout4, constraintLayout5, constraintLayout6, textView4, linearLayout14, linearLayout15, textView5, findChildViewById, textView6, textView7, textView8, textView9, textView10, textView11, textView12, ellipsizedTextView, textView13, textView14, textView15, textView16, ellipsizedTextView2, ellipsizedTextView3, textView17, ellipsizedTextView4, textView18, ellipsizedTextView5, textView19, ellipsizedTextView6, textView20, ellipsizedTextView7, textView21, textView22, textView23, textView24, ellipsizedTextView8, textView25, textView26, textView27);
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
