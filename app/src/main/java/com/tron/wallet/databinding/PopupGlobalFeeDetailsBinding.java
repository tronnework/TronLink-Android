package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.DashUnderLineTextView;
import com.tronlinkpro.wallet.R;
public final class PopupGlobalFeeDetailsBinding implements ViewBinding {
    public final Button btnConfirm;
    public final ImageView ivFeeTips;
    public final ImageView ivTips;
    public final LinearLayout llAction;
    public final NestedScrollView llContent;
    public final View llFeeBottom;
    public final RelativeLayout rlFeeActive;
    public final RelativeLayout rlFeeByContract;
    public final RelativeLayout rlFeeConsume;
    public final RelativeLayout rlFeeMemo;
    public final RelativeLayout rlFeeMulti;
    public final RelativeLayout rlResourceConsume;
    public final RelativeLayout rlResourceConsumeContent;
    public final RelativeLayout rlResourceConsumeUser;
    public final RelativeLayout rlResourceCreator;
    public final RelativeLayout rlResourceUser;
    private final LinearLayout rootView;
    public final TextView title;
    public final TextView tvConsumeCreatorEnergy;
    public final TextView tvConsumeResourceBandwidth;
    public final DashUnderLineTextView tvConsumeResourceEnergy;
    public final TextView tvConsumeResourceMid;
    public final TextView tvConsumeUserBandwidth;
    public final DashUnderLineTextView tvConsumeUserEnergy;
    public final TextView tvConsumeUserMid;
    public final TextView tvConsumedFeeTotal;
    public final TextView tvFeeActive;
    public final TextView tvFeeActiveValue;
    public final TextView tvFeeByContactValue;
    public final TextView tvFeeByContract;
    public final TextView tvFeeConsumeLeft;
    public final TextView tvFeeMemo;
    public final TextView tvFeeMemoValue;
    public final TextView tvFeeMulti;
    public final TextView tvFeeMultiValue;
    public final TextView tvInfo;
    public final TextView tvInfoTitle;
    public final TextView tvResourceConsumeLeft;
    public final TextView tvResourceCreator;
    public final TextView tvResourceUser;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private PopupGlobalFeeDetailsBinding(LinearLayout linearLayout, Button button, ImageView imageView, ImageView imageView2, LinearLayout linearLayout2, NestedScrollView nestedScrollView, View view, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, RelativeLayout relativeLayout5, RelativeLayout relativeLayout6, RelativeLayout relativeLayout7, RelativeLayout relativeLayout8, RelativeLayout relativeLayout9, RelativeLayout relativeLayout10, TextView textView, TextView textView2, TextView textView3, DashUnderLineTextView dashUnderLineTextView, TextView textView4, TextView textView5, DashUnderLineTextView dashUnderLineTextView2, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14, TextView textView15, TextView textView16, TextView textView17, TextView textView18, TextView textView19, TextView textView20, TextView textView21) {
        this.rootView = linearLayout;
        this.btnConfirm = button;
        this.ivFeeTips = imageView;
        this.ivTips = imageView2;
        this.llAction = linearLayout2;
        this.llContent = nestedScrollView;
        this.llFeeBottom = view;
        this.rlFeeActive = relativeLayout;
        this.rlFeeByContract = relativeLayout2;
        this.rlFeeConsume = relativeLayout3;
        this.rlFeeMemo = relativeLayout4;
        this.rlFeeMulti = relativeLayout5;
        this.rlResourceConsume = relativeLayout6;
        this.rlResourceConsumeContent = relativeLayout7;
        this.rlResourceConsumeUser = relativeLayout8;
        this.rlResourceCreator = relativeLayout9;
        this.rlResourceUser = relativeLayout10;
        this.title = textView;
        this.tvConsumeCreatorEnergy = textView2;
        this.tvConsumeResourceBandwidth = textView3;
        this.tvConsumeResourceEnergy = dashUnderLineTextView;
        this.tvConsumeResourceMid = textView4;
        this.tvConsumeUserBandwidth = textView5;
        this.tvConsumeUserEnergy = dashUnderLineTextView2;
        this.tvConsumeUserMid = textView6;
        this.tvConsumedFeeTotal = textView7;
        this.tvFeeActive = textView8;
        this.tvFeeActiveValue = textView9;
        this.tvFeeByContactValue = textView10;
        this.tvFeeByContract = textView11;
        this.tvFeeConsumeLeft = textView12;
        this.tvFeeMemo = textView13;
        this.tvFeeMemoValue = textView14;
        this.tvFeeMulti = textView15;
        this.tvFeeMultiValue = textView16;
        this.tvInfo = textView17;
        this.tvInfoTitle = textView18;
        this.tvResourceConsumeLeft = textView19;
        this.tvResourceCreator = textView20;
        this.tvResourceUser = textView21;
    }

    public static PopupGlobalFeeDetailsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopupGlobalFeeDetailsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.popup_global_fee_details, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopupGlobalFeeDetailsBinding bind(View view) {
        int i = R.id.btn_confirm;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_confirm);
        if (button != null) {
            i = R.id.iv_fee_tips;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_fee_tips);
            if (imageView != null) {
                i = R.id.iv_tips;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_tips);
                if (imageView2 != null) {
                    i = R.id.ll_action;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_action);
                    if (linearLayout != null) {
                        i = R.id.ll_content;
                        NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(view, R.id.ll_content);
                        if (nestedScrollView != null) {
                            i = R.id.ll_fee_bottom;
                            View findChildViewById = ViewBindings.findChildViewById(view, R.id.ll_fee_bottom);
                            if (findChildViewById != null) {
                                i = R.id.rl_fee_active;
                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_fee_active);
                                if (relativeLayout != null) {
                                    i = R.id.rl_fee_by_contract;
                                    RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_fee_by_contract);
                                    if (relativeLayout2 != null) {
                                        i = R.id.rl_fee_consume;
                                        RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_fee_consume);
                                        if (relativeLayout3 != null) {
                                            i = R.id.rl_fee_memo;
                                            RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_fee_memo);
                                            if (relativeLayout4 != null) {
                                                i = R.id.rl_fee_multi;
                                                RelativeLayout relativeLayout5 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_fee_multi);
                                                if (relativeLayout5 != null) {
                                                    i = R.id.rl_resource_consume;
                                                    RelativeLayout relativeLayout6 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_resource_consume);
                                                    if (relativeLayout6 != null) {
                                                        i = R.id.rl_resource_consume_content;
                                                        RelativeLayout relativeLayout7 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_resource_consume_content);
                                                        if (relativeLayout7 != null) {
                                                            i = R.id.rl_resource_consume_user;
                                                            RelativeLayout relativeLayout8 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_resource_consume_user);
                                                            if (relativeLayout8 != null) {
                                                                i = R.id.rl_resource_creator;
                                                                RelativeLayout relativeLayout9 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_resource_creator);
                                                                if (relativeLayout9 != null) {
                                                                    i = R.id.rl_resource_user;
                                                                    RelativeLayout relativeLayout10 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_resource_user);
                                                                    if (relativeLayout10 != null) {
                                                                        i = R.id.title;
                                                                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.title);
                                                                        if (textView != null) {
                                                                            i = R.id.tv_consume_creator_energy;
                                                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_consume_creator_energy);
                                                                            if (textView2 != null) {
                                                                                i = R.id.tv_consume_resource_bandwidth;
                                                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_consume_resource_bandwidth);
                                                                                if (textView3 != null) {
                                                                                    i = R.id.tv_consume_resource_energy;
                                                                                    DashUnderLineTextView dashUnderLineTextView = (DashUnderLineTextView) ViewBindings.findChildViewById(view, R.id.tv_consume_resource_energy);
                                                                                    if (dashUnderLineTextView != null) {
                                                                                        i = R.id.tv_consume_resource_mid;
                                                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_consume_resource_mid);
                                                                                        if (textView4 != null) {
                                                                                            i = R.id.tv_consume_user_bandwidth;
                                                                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_consume_user_bandwidth);
                                                                                            if (textView5 != null) {
                                                                                                i = R.id.tv_consume_user_energy;
                                                                                                DashUnderLineTextView dashUnderLineTextView2 = (DashUnderLineTextView) ViewBindings.findChildViewById(view, R.id.tv_consume_user_energy);
                                                                                                if (dashUnderLineTextView2 != null) {
                                                                                                    i = R.id.tv_consume_user_mid;
                                                                                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_consume_user_mid);
                                                                                                    if (textView6 != null) {
                                                                                                        i = R.id.tv_consumed_fee_total;
                                                                                                        TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_consumed_fee_total);
                                                                                                        if (textView7 != null) {
                                                                                                            i = R.id.tv_fee_active;
                                                                                                            TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_fee_active);
                                                                                                            if (textView8 != null) {
                                                                                                                i = R.id.tv_fee_active_value;
                                                                                                                TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_fee_active_value);
                                                                                                                if (textView9 != null) {
                                                                                                                    i = R.id.tv_fee_by_contact_value;
                                                                                                                    TextView textView10 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_fee_by_contact_value);
                                                                                                                    if (textView10 != null) {
                                                                                                                        i = R.id.tv_fee_by_contract;
                                                                                                                        TextView textView11 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_fee_by_contract);
                                                                                                                        if (textView11 != null) {
                                                                                                                            i = R.id.tv_fee_consume_left;
                                                                                                                            TextView textView12 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_fee_consume_left);
                                                                                                                            if (textView12 != null) {
                                                                                                                                i = R.id.tv_fee_memo;
                                                                                                                                TextView textView13 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_fee_memo);
                                                                                                                                if (textView13 != null) {
                                                                                                                                    i = R.id.tv_fee_memo_value;
                                                                                                                                    TextView textView14 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_fee_memo_value);
                                                                                                                                    if (textView14 != null) {
                                                                                                                                        i = R.id.tv_fee_multi;
                                                                                                                                        TextView textView15 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_fee_multi);
                                                                                                                                        if (textView15 != null) {
                                                                                                                                            i = R.id.tv_fee_multi_value;
                                                                                                                                            TextView textView16 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_fee_multi_value);
                                                                                                                                            if (textView16 != null) {
                                                                                                                                                i = R.id.tv_info;
                                                                                                                                                TextView textView17 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_info);
                                                                                                                                                if (textView17 != null) {
                                                                                                                                                    i = R.id.tv_info_title;
                                                                                                                                                    TextView textView18 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_info_title);
                                                                                                                                                    if (textView18 != null) {
                                                                                                                                                        i = R.id.tv_resource_consume_left;
                                                                                                                                                        TextView textView19 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_resource_consume_left);
                                                                                                                                                        if (textView19 != null) {
                                                                                                                                                            i = R.id.tv_resource_creator;
                                                                                                                                                            TextView textView20 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_resource_creator);
                                                                                                                                                            if (textView20 != null) {
                                                                                                                                                                i = R.id.tv_resource_user;
                                                                                                                                                                TextView textView21 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_resource_user);
                                                                                                                                                                if (textView21 != null) {
                                                                                                                                                                    return new PopupGlobalFeeDetailsBinding((LinearLayout) view, button, imageView, imageView2, linearLayout, nestedScrollView, findChildViewById, relativeLayout, relativeLayout2, relativeLayout3, relativeLayout4, relativeLayout5, relativeLayout6, relativeLayout7, relativeLayout8, relativeLayout9, relativeLayout10, textView, textView2, textView3, dashUnderLineTextView, textView4, textView5, dashUnderLineTextView2, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14, textView15, textView16, textView17, textView18, textView19, textView20, textView21);
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
