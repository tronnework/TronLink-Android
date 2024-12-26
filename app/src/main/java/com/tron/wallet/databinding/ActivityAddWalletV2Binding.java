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
public final class ActivityAddWalletV2Binding implements ViewBinding {
    public final TextView addressTitle;
    public final RelativeLayout backview;
    public final TextView coldPairTitle;
    public final TextView createTitle;
    public final TextView edgerTitle;
    public final ImageView iconAddAddress;
    public final ImageView iconAddCreate;
    public final ImageView iconAddLedger;
    public final ImageView iconAddObserve;
    public final ImageView iconAddSamsung;
    public final ImageView iconColdPair;
    public final TextView importTitle;
    public final ImageView ivCommonLeft;
    public final ImageView ivMmIcon;
    public final ImageView ivTip;
    public final LinearLayout llCommonLeft;
    public final LinearLayout llCreateNew;
    public final TextView observationDes;
    public final TextView observationTitle;
    public final RelativeLayout rlBack;
    public final RelativeLayout rlColdPair;
    public final RelativeLayout rlCreate;
    public final RelativeLayout rlImport;
    public final RelativeLayout rlLedger;
    public final RelativeLayout rlObser;
    public final RelativeLayout rlRequestAddress;
    public final RelativeLayout rlSamsung;
    private final LinearLayout rootView;
    public final TextView samsungTitle;
    public final LinearLayout statusbar;
    public final TextView tvBigTip;
    public final TextView tvCommonTitle;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private ActivityAddWalletV2Binding(LinearLayout linearLayout, TextView textView, RelativeLayout relativeLayout, TextView textView2, TextView textView3, TextView textView4, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView imageView6, TextView textView5, ImageView imageView7, ImageView imageView8, ImageView imageView9, LinearLayout linearLayout2, LinearLayout linearLayout3, TextView textView6, TextView textView7, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, RelativeLayout relativeLayout5, RelativeLayout relativeLayout6, RelativeLayout relativeLayout7, RelativeLayout relativeLayout8, RelativeLayout relativeLayout9, TextView textView8, LinearLayout linearLayout4, TextView textView9, TextView textView10) {
        this.rootView = linearLayout;
        this.addressTitle = textView;
        this.backview = relativeLayout;
        this.coldPairTitle = textView2;
        this.createTitle = textView3;
        this.edgerTitle = textView4;
        this.iconAddAddress = imageView;
        this.iconAddCreate = imageView2;
        this.iconAddLedger = imageView3;
        this.iconAddObserve = imageView4;
        this.iconAddSamsung = imageView5;
        this.iconColdPair = imageView6;
        this.importTitle = textView5;
        this.ivCommonLeft = imageView7;
        this.ivMmIcon = imageView8;
        this.ivTip = imageView9;
        this.llCommonLeft = linearLayout2;
        this.llCreateNew = linearLayout3;
        this.observationDes = textView6;
        this.observationTitle = textView7;
        this.rlBack = relativeLayout2;
        this.rlColdPair = relativeLayout3;
        this.rlCreate = relativeLayout4;
        this.rlImport = relativeLayout5;
        this.rlLedger = relativeLayout6;
        this.rlObser = relativeLayout7;
        this.rlRequestAddress = relativeLayout8;
        this.rlSamsung = relativeLayout9;
        this.samsungTitle = textView8;
        this.statusbar = linearLayout4;
        this.tvBigTip = textView9;
        this.tvCommonTitle = textView10;
    }

    public static ActivityAddWalletV2Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityAddWalletV2Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_add_wallet_v2, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityAddWalletV2Binding bind(View view) {
        int i = R.id.address_title;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.address_title);
        if (textView != null) {
            i = R.id.backview;
            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.backview);
            if (relativeLayout != null) {
                i = R.id.cold_pair_title;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.cold_pair_title);
                if (textView2 != null) {
                    i = R.id.create_title;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.create_title);
                    if (textView3 != null) {
                        i = R.id.edger_title;
                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.edger_title);
                        if (textView4 != null) {
                            i = R.id.icon_add_address;
                            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.icon_add_address);
                            if (imageView != null) {
                                i = R.id.icon_add_create;
                                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.icon_add_create);
                                if (imageView2 != null) {
                                    i = R.id.icon_add_ledger;
                                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.icon_add_ledger);
                                    if (imageView3 != null) {
                                        i = R.id.icon_add_observe;
                                        ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.icon_add_observe);
                                        if (imageView4 != null) {
                                            i = R.id.icon_add_samsung;
                                            ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, R.id.icon_add_samsung);
                                            if (imageView5 != null) {
                                                i = R.id.icon_cold_pair;
                                                ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(view, R.id.icon_cold_pair);
                                                if (imageView6 != null) {
                                                    i = R.id.import_title;
                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.import_title);
                                                    if (textView5 != null) {
                                                        i = R.id.iv_common_left;
                                                        ImageView imageView7 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_common_left);
                                                        if (imageView7 != null) {
                                                            i = R.id.iv_mm_icon;
                                                            ImageView imageView8 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_mm_icon);
                                                            if (imageView8 != null) {
                                                                i = R.id.iv_tip;
                                                                ImageView imageView9 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_tip);
                                                                if (imageView9 != null) {
                                                                    i = R.id.ll_common_left;
                                                                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_common_left);
                                                                    if (linearLayout != null) {
                                                                        i = R.id.ll_create_new;
                                                                        LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_create_new);
                                                                        if (linearLayout2 != null) {
                                                                            i = R.id.observation_des;
                                                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.observation_des);
                                                                            if (textView6 != null) {
                                                                                i = R.id.observation_title;
                                                                                TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.observation_title);
                                                                                if (textView7 != null) {
                                                                                    i = R.id.rl_back;
                                                                                    RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_back);
                                                                                    if (relativeLayout2 != null) {
                                                                                        i = R.id.rl_cold_pair;
                                                                                        RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_cold_pair);
                                                                                        if (relativeLayout3 != null) {
                                                                                            i = R.id.rl_create;
                                                                                            RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_create);
                                                                                            if (relativeLayout4 != null) {
                                                                                                i = R.id.rl_import;
                                                                                                RelativeLayout relativeLayout5 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_import);
                                                                                                if (relativeLayout5 != null) {
                                                                                                    i = R.id.rl_ledger;
                                                                                                    RelativeLayout relativeLayout6 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_ledger);
                                                                                                    if (relativeLayout6 != null) {
                                                                                                        i = R.id.rl_obser;
                                                                                                        RelativeLayout relativeLayout7 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_obser);
                                                                                                        if (relativeLayout7 != null) {
                                                                                                            i = R.id.rl_request_address;
                                                                                                            RelativeLayout relativeLayout8 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_request_address);
                                                                                                            if (relativeLayout8 != null) {
                                                                                                                i = R.id.rl_samsung;
                                                                                                                RelativeLayout relativeLayout9 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_samsung);
                                                                                                                if (relativeLayout9 != null) {
                                                                                                                    i = R.id.samsung_title;
                                                                                                                    TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.samsung_title);
                                                                                                                    if (textView8 != null) {
                                                                                                                        i = R.id.statusbar;
                                                                                                                        LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.statusbar);
                                                                                                                        if (linearLayout3 != null) {
                                                                                                                            i = R.id.tv_big_tip;
                                                                                                                            TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_big_tip);
                                                                                                                            if (textView9 != null) {
                                                                                                                                i = R.id.tv_common_title;
                                                                                                                                TextView textView10 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_common_title);
                                                                                                                                if (textView10 != null) {
                                                                                                                                    return new ActivityAddWalletV2Binding((LinearLayout) view, textView, relativeLayout, textView2, textView3, textView4, imageView, imageView2, imageView3, imageView4, imageView5, imageView6, textView5, imageView7, imageView8, imageView9, linearLayout, linearLayout2, textView6, textView7, relativeLayout2, relativeLayout3, relativeLayout4, relativeLayout5, relativeLayout6, relativeLayout7, relativeLayout8, relativeLayout9, textView8, linearLayout3, textView9, textView10);
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
