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
import androidx.viewpager2.widget.ViewPager2;
import com.tronlinkpro.wallet.R;
public final class AcEmptytronwalletBinding implements ViewBinding {
    public final ImageView handBg;
    public final TextView indicator1;
    public final TextView indicator2;
    public final TextView indicator3;
    public final TextView indicator4;
    public final LinearLayout ivIndicator;
    public final LinearLayout llOthers;
    public final TextView palce1;
    public final TextView palce2;
    public final RelativeLayout rlAccount;
    public final RelativeLayout rlBottom;
    public final RelativeLayout rlCold;
    public final RelativeLayout rlContent;
    public final RelativeLayout rlCreate;
    public final RelativeLayout rlImport;
    public final RelativeLayout rlSamsungImport;
    public final RelativeLayout rlSwitch;
    public final RelativeLayout root;
    private final RelativeLayout rootView;
    public final TextView tvCold;
    public final TextView tvColdWallet;
    public final TextView tvCreate;
    public final TextView tvImport;
    public final RelativeLayout tvImportMigrate;
    public final TextView tvImportSamsung;
    public final TextView tvLedger;
    public final TextView tvMigrate;
    public final TextView tvObservation;
    public final TextView tvSwitch;
    public final ViewPager2 vpContent;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcEmptytronwalletBinding(RelativeLayout relativeLayout, ImageView imageView, TextView textView, TextView textView2, TextView textView3, TextView textView4, LinearLayout linearLayout, LinearLayout linearLayout2, TextView textView5, TextView textView6, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, RelativeLayout relativeLayout5, RelativeLayout relativeLayout6, RelativeLayout relativeLayout7, RelativeLayout relativeLayout8, RelativeLayout relativeLayout9, RelativeLayout relativeLayout10, TextView textView7, TextView textView8, TextView textView9, TextView textView10, RelativeLayout relativeLayout11, TextView textView11, TextView textView12, TextView textView13, TextView textView14, TextView textView15, ViewPager2 viewPager2) {
        this.rootView = relativeLayout;
        this.handBg = imageView;
        this.indicator1 = textView;
        this.indicator2 = textView2;
        this.indicator3 = textView3;
        this.indicator4 = textView4;
        this.ivIndicator = linearLayout;
        this.llOthers = linearLayout2;
        this.palce1 = textView5;
        this.palce2 = textView6;
        this.rlAccount = relativeLayout2;
        this.rlBottom = relativeLayout3;
        this.rlCold = relativeLayout4;
        this.rlContent = relativeLayout5;
        this.rlCreate = relativeLayout6;
        this.rlImport = relativeLayout7;
        this.rlSamsungImport = relativeLayout8;
        this.rlSwitch = relativeLayout9;
        this.root = relativeLayout10;
        this.tvCold = textView7;
        this.tvColdWallet = textView8;
        this.tvCreate = textView9;
        this.tvImport = textView10;
        this.tvImportMigrate = relativeLayout11;
        this.tvImportSamsung = textView11;
        this.tvLedger = textView12;
        this.tvMigrate = textView13;
        this.tvObservation = textView14;
        this.tvSwitch = textView15;
        this.vpContent = viewPager2;
    }

    public static AcEmptytronwalletBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcEmptytronwalletBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_emptytronwallet, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcEmptytronwalletBinding bind(View view) {
        int i = R.id.hand_bg;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.hand_bg);
        if (imageView != null) {
            i = R.id.indicator_1;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.indicator_1);
            if (textView != null) {
                i = R.id.indicator_2;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.indicator_2);
                if (textView2 != null) {
                    i = R.id.indicator_3;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.indicator_3);
                    if (textView3 != null) {
                        i = R.id.indicator_4;
                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.indicator_4);
                        if (textView4 != null) {
                            i = R.id.iv_indicator;
                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.iv_indicator);
                            if (linearLayout != null) {
                                i = R.id.ll_others;
                                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_others);
                                if (linearLayout2 != null) {
                                    i = R.id.palce_1;
                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.palce_1);
                                    if (textView5 != null) {
                                        i = R.id.palce_2;
                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.palce_2);
                                        if (textView6 != null) {
                                            i = R.id.rl_account;
                                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_account);
                                            if (relativeLayout != null) {
                                                i = R.id.rl_bottom;
                                                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_bottom);
                                                if (relativeLayout2 != null) {
                                                    i = R.id.rl_cold;
                                                    RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_cold);
                                                    if (relativeLayout3 != null) {
                                                        i = R.id.rl_content;
                                                        RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_content);
                                                        if (relativeLayout4 != null) {
                                                            i = R.id.rl_create;
                                                            RelativeLayout relativeLayout5 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_create);
                                                            if (relativeLayout5 != null) {
                                                                i = R.id.rl_import;
                                                                RelativeLayout relativeLayout6 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_import);
                                                                if (relativeLayout6 != null) {
                                                                    i = R.id.rl_samsung_import;
                                                                    RelativeLayout relativeLayout7 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_samsung_import);
                                                                    if (relativeLayout7 != null) {
                                                                        i = R.id.rl_switch;
                                                                        RelativeLayout relativeLayout8 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_switch);
                                                                        if (relativeLayout8 != null) {
                                                                            RelativeLayout relativeLayout9 = (RelativeLayout) view;
                                                                            i = R.id.tv_cold;
                                                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_cold);
                                                                            if (textView7 != null) {
                                                                                i = R.id.tv_cold_wallet;
                                                                                TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_cold_wallet);
                                                                                if (textView8 != null) {
                                                                                    i = R.id.tv_create;
                                                                                    TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_create);
                                                                                    if (textView9 != null) {
                                                                                        i = R.id.tv_import;
                                                                                        TextView textView10 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_import);
                                                                                        if (textView10 != null) {
                                                                                            i = R.id.tv_import_migrate;
                                                                                            RelativeLayout relativeLayout10 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.tv_import_migrate);
                                                                                            if (relativeLayout10 != null) {
                                                                                                i = R.id.tv_import_samsung;
                                                                                                TextView textView11 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_import_samsung);
                                                                                                if (textView11 != null) {
                                                                                                    i = R.id.tv_ledger;
                                                                                                    TextView textView12 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_ledger);
                                                                                                    if (textView12 != null) {
                                                                                                        i = R.id.tv_migrate;
                                                                                                        TextView textView13 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_migrate);
                                                                                                        if (textView13 != null) {
                                                                                                            i = R.id.tv_observation;
                                                                                                            TextView textView14 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_observation);
                                                                                                            if (textView14 != null) {
                                                                                                                i = R.id.tv_switch;
                                                                                                                TextView textView15 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_switch);
                                                                                                                if (textView15 != null) {
                                                                                                                    i = R.id.vp_content;
                                                                                                                    ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, R.id.vp_content);
                                                                                                                    if (viewPager2 != null) {
                                                                                                                        return new AcEmptytronwalletBinding(relativeLayout9, imageView, textView, textView2, textView3, textView4, linearLayout, linearLayout2, textView5, textView6, relativeLayout, relativeLayout2, relativeLayout3, relativeLayout4, relativeLayout5, relativeLayout6, relativeLayout7, relativeLayout8, relativeLayout9, textView7, textView8, textView9, textView10, relativeLayout10, textView11, textView12, textView13, textView14, textView15, viewPager2);
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
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
