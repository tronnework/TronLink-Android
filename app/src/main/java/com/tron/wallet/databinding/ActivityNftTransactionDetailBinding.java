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
import com.tron.wallet.common.components.EllipsizedTextView;
import com.tronlinkpro.wallet.R;
public final class ActivityNftTransactionDetailBinding implements ViewBinding {
    public final NftHistoryDetailTitleContentBinding includeContent;
    public final ImageView ivCode;
    public final ImageView ivContractType;
    public final ImageView ivHashCopy;
    public final ImageView ivRaCopy;
    public final ImageView ivRightIcon;
    public final ImageView ivSaCopy;
    public final LinearLayout llCode;
    public final LinearLayout llCodeCopy;
    public final LinearLayout llContent;
    public final LinearLayout llFailReason;
    public final ConstraintLayout llHash;
    public final LinearLayout llNote;
    public final ConstraintLayout llRa;
    public final ConstraintLayout llSa;
    public final NestedScrollView llScroll;
    public final TransactionFeeResourceView resourceInfoView2;
    public final ImageView rlState;
    public final RelativeLayout rlTransferMenu;
    private final RelativeLayout rootView;
    public final ImageView scamTag;
    public final ImageView scamTagSa;
    public final TextView tvBn;
    public final TextView tvBnTitle;
    public final TextView tvContractType;
    public final TextView tvNote;
    public final EllipsizedTextView tvRa;
    public final TextView tvReasonFailure;
    public final TextView tvReasonFailureTitle;
    public final TextView tvResource;
    public final TextView tvResourceTitle;
    public final EllipsizedTextView tvSa;
    public final TextView tvTime;
    public final TextView tvTimeTitle;
    public final TextView tvTitle;
    public final EllipsizedTextView tvTn;
    public final TextView tvVdd;
    public final TextView tvWalletNameRa;
    public final TextView tvWalletNameSa;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ActivityNftTransactionDetailBinding(RelativeLayout relativeLayout, NftHistoryDetailTitleContentBinding nftHistoryDetailTitleContentBinding, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView imageView6, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, ConstraintLayout constraintLayout, LinearLayout linearLayout5, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, NestedScrollView nestedScrollView, TransactionFeeResourceView transactionFeeResourceView, ImageView imageView7, RelativeLayout relativeLayout2, ImageView imageView8, ImageView imageView9, TextView textView, TextView textView2, TextView textView3, TextView textView4, EllipsizedTextView ellipsizedTextView, TextView textView5, TextView textView6, TextView textView7, TextView textView8, EllipsizedTextView ellipsizedTextView2, TextView textView9, TextView textView10, TextView textView11, EllipsizedTextView ellipsizedTextView3, TextView textView12, TextView textView13, TextView textView14) {
        this.rootView = relativeLayout;
        this.includeContent = nftHistoryDetailTitleContentBinding;
        this.ivCode = imageView;
        this.ivContractType = imageView2;
        this.ivHashCopy = imageView3;
        this.ivRaCopy = imageView4;
        this.ivRightIcon = imageView5;
        this.ivSaCopy = imageView6;
        this.llCode = linearLayout;
        this.llCodeCopy = linearLayout2;
        this.llContent = linearLayout3;
        this.llFailReason = linearLayout4;
        this.llHash = constraintLayout;
        this.llNote = linearLayout5;
        this.llRa = constraintLayout2;
        this.llSa = constraintLayout3;
        this.llScroll = nestedScrollView;
        this.resourceInfoView2 = transactionFeeResourceView;
        this.rlState = imageView7;
        this.rlTransferMenu = relativeLayout2;
        this.scamTag = imageView8;
        this.scamTagSa = imageView9;
        this.tvBn = textView;
        this.tvBnTitle = textView2;
        this.tvContractType = textView3;
        this.tvNote = textView4;
        this.tvRa = ellipsizedTextView;
        this.tvReasonFailure = textView5;
        this.tvReasonFailureTitle = textView6;
        this.tvResource = textView7;
        this.tvResourceTitle = textView8;
        this.tvSa = ellipsizedTextView2;
        this.tvTime = textView9;
        this.tvTimeTitle = textView10;
        this.tvTitle = textView11;
        this.tvTn = ellipsizedTextView3;
        this.tvVdd = textView12;
        this.tvWalletNameRa = textView13;
        this.tvWalletNameSa = textView14;
    }

    public static ActivityNftTransactionDetailBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityNftTransactionDetailBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_nft_transaction_detail, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityNftTransactionDetailBinding bind(View view) {
        int i = R.id.include_content;
        View findChildViewById = ViewBindings.findChildViewById(view, R.id.include_content);
        if (findChildViewById != null) {
            NftHistoryDetailTitleContentBinding bind = NftHistoryDetailTitleContentBinding.bind(findChildViewById);
            i = R.id.iv_code;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_code);
            if (imageView != null) {
                i = R.id.iv_contract_type;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_contract_type);
                if (imageView2 != null) {
                    i = R.id.iv_hash_copy;
                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_hash_copy);
                    if (imageView3 != null) {
                        i = R.id.iv_ra_copy;
                        ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_ra_copy);
                        if (imageView4 != null) {
                            i = R.id.iv_right_icon;
                            ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_right_icon);
                            if (imageView5 != null) {
                                i = R.id.iv_sa_copy;
                                ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_sa_copy);
                                if (imageView6 != null) {
                                    i = R.id.ll_code;
                                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_code);
                                    if (linearLayout != null) {
                                        i = R.id.ll_code_copy;
                                        LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_code_copy);
                                        if (linearLayout2 != null) {
                                            i = R.id.ll_content;
                                            LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_content);
                                            if (linearLayout3 != null) {
                                                i = R.id.ll_fail_reason;
                                                LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_fail_reason);
                                                if (linearLayout4 != null) {
                                                    i = R.id.ll_hash;
                                                    ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.ll_hash);
                                                    if (constraintLayout != null) {
                                                        i = R.id.ll_note;
                                                        LinearLayout linearLayout5 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_note);
                                                        if (linearLayout5 != null) {
                                                            i = R.id.ll_ra;
                                                            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.ll_ra);
                                                            if (constraintLayout2 != null) {
                                                                i = R.id.ll_sa;
                                                                ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.ll_sa);
                                                                if (constraintLayout3 != null) {
                                                                    i = R.id.ll_scroll;
                                                                    NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(view, R.id.ll_scroll);
                                                                    if (nestedScrollView != null) {
                                                                        i = R.id.resource_info_view2;
                                                                        TransactionFeeResourceView transactionFeeResourceView = (TransactionFeeResourceView) ViewBindings.findChildViewById(view, R.id.resource_info_view2);
                                                                        if (transactionFeeResourceView != null) {
                                                                            i = R.id.rl_state;
                                                                            ImageView imageView7 = (ImageView) ViewBindings.findChildViewById(view, R.id.rl_state);
                                                                            if (imageView7 != null) {
                                                                                i = R.id.rl_transfer_menu;
                                                                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_transfer_menu);
                                                                                if (relativeLayout != null) {
                                                                                    i = R.id.scam_tag;
                                                                                    ImageView imageView8 = (ImageView) ViewBindings.findChildViewById(view, R.id.scam_tag);
                                                                                    if (imageView8 != null) {
                                                                                        i = R.id.scam_tag_sa;
                                                                                        ImageView imageView9 = (ImageView) ViewBindings.findChildViewById(view, R.id.scam_tag_sa);
                                                                                        if (imageView9 != null) {
                                                                                            i = R.id.tv_bn;
                                                                                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_bn);
                                                                                            if (textView != null) {
                                                                                                i = R.id.tv_bn_title;
                                                                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_bn_title);
                                                                                                if (textView2 != null) {
                                                                                                    i = R.id.tv_contract_type;
                                                                                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_contract_type);
                                                                                                    if (textView3 != null) {
                                                                                                        i = R.id.tv_note;
                                                                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_note);
                                                                                                        if (textView4 != null) {
                                                                                                            i = R.id.tv_ra;
                                                                                                            EllipsizedTextView ellipsizedTextView = (EllipsizedTextView) ViewBindings.findChildViewById(view, R.id.tv_ra);
                                                                                                            if (ellipsizedTextView != null) {
                                                                                                                i = R.id.tv_reason_failure;
                                                                                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_reason_failure);
                                                                                                                if (textView5 != null) {
                                                                                                                    i = R.id.tv_reason_failure_title;
                                                                                                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_reason_failure_title);
                                                                                                                    if (textView6 != null) {
                                                                                                                        i = R.id.tv_resource;
                                                                                                                        TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_resource);
                                                                                                                        if (textView7 != null) {
                                                                                                                            i = R.id.tv_resource_title;
                                                                                                                            TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_resource_title);
                                                                                                                            if (textView8 != null) {
                                                                                                                                i = R.id.tv_sa;
                                                                                                                                EllipsizedTextView ellipsizedTextView2 = (EllipsizedTextView) ViewBindings.findChildViewById(view, R.id.tv_sa);
                                                                                                                                if (ellipsizedTextView2 != null) {
                                                                                                                                    i = R.id.tv_time;
                                                                                                                                    TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_time);
                                                                                                                                    if (textView9 != null) {
                                                                                                                                        i = R.id.tv_time_title;
                                                                                                                                        TextView textView10 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_time_title);
                                                                                                                                        if (textView10 != null) {
                                                                                                                                            i = R.id.tv_title;
                                                                                                                                            TextView textView11 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                                                                                                                                            if (textView11 != null) {
                                                                                                                                                i = R.id.tv_tn;
                                                                                                                                                EllipsizedTextView ellipsizedTextView3 = (EllipsizedTextView) ViewBindings.findChildViewById(view, R.id.tv_tn);
                                                                                                                                                if (ellipsizedTextView3 != null) {
                                                                                                                                                    i = R.id.tv_vdd;
                                                                                                                                                    TextView textView12 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_vdd);
                                                                                                                                                    if (textView12 != null) {
                                                                                                                                                        i = R.id.tv_wallet_name_ra;
                                                                                                                                                        TextView textView13 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_wallet_name_ra);
                                                                                                                                                        if (textView13 != null) {
                                                                                                                                                            i = R.id.tv_wallet_name_sa;
                                                                                                                                                            TextView textView14 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_wallet_name_sa);
                                                                                                                                                            if (textView14 != null) {
                                                                                                                                                                return new ActivityNftTransactionDetailBinding((RelativeLayout) view, bind, imageView, imageView2, imageView3, imageView4, imageView5, imageView6, linearLayout, linearLayout2, linearLayout3, linearLayout4, constraintLayout, linearLayout5, constraintLayout2, constraintLayout3, nestedScrollView, transactionFeeResourceView, imageView7, relativeLayout, imageView8, imageView9, textView, textView2, textView3, textView4, ellipsizedTextView, textView5, textView6, textView7, textView8, ellipsizedTextView2, textView9, textView10, textView11, ellipsizedTextView3, textView12, textView13, textView14);
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
