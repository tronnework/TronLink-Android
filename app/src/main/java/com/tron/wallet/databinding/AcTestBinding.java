package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.business.tabmy.advancedfeatures.HorizontalOptionView;
import com.tronlinkpro.wallet.R;
public final class AcTestBinding implements ViewBinding {
    public final ImageView back15;
    public final RelativeLayout backview;
    public final HorizontalOptionView bip32;
    public final HorizontalOptionView changeAddress;
    public final HorizontalOptionView changeWalletType;
    public final TextView checkEmulatorResult;
    public final HorizontalOptionView checkToken;
    public final RelativeLayout cold;
    public final HorizontalOptionView collapsing;
    public final HorizontalOptionView commonUi;
    public final RelativeLayout costomView;
    public final HorizontalOptionView delegate;
    public final HorizontalOptionView eip712;
    public final HorizontalOptionView enterUnstake;
    public final FrameLayout frameSubContent;
    public final HorizontalOptionView getTrx;
    public final RelativeLayout hexDecode;
    public final ImageView ivCommonLeft;
    public final RelativeLayout kexuejia;
    public final LinearLayout llCommonLeft;
    public final HorizontalOptionView lottie;
    public final RelativeLayout newaddassets;
    public final RelativeLayout nft;
    public final RelativeLayout nodeSpeed;
    public final HorizontalOptionView openTronwebDisplay;
    public final RelativeLayout qrScan;
    public final RelativeLayout qrScan2;
    private final FrameLayout rootView;
    public final RelativeLayout shieldToken;
    public final HorizontalOptionView showCircleProgress;
    public final RelativeLayout sqliteData;
    public final HorizontalOptionView stake20;
    public final LinearLayout statusbar;
    public final TextView switchCold;
    public final HorizontalOptionView switchVersion;
    public final TextView tvCommonTitle;
    public final RelativeLayout uninstall;
    public final HorizontalOptionView upgradeHd;
    public final HorizontalOptionView upgradeHdCreate;
    public final HorizontalOptionView upgradeHdReset;
    public final RelativeLayout verify;

    @Override
    public FrameLayout getRoot() {
        return this.rootView;
    }

    private AcTestBinding(FrameLayout frameLayout, ImageView imageView, RelativeLayout relativeLayout, HorizontalOptionView horizontalOptionView, HorizontalOptionView horizontalOptionView2, HorizontalOptionView horizontalOptionView3, TextView textView, HorizontalOptionView horizontalOptionView4, RelativeLayout relativeLayout2, HorizontalOptionView horizontalOptionView5, HorizontalOptionView horizontalOptionView6, RelativeLayout relativeLayout3, HorizontalOptionView horizontalOptionView7, HorizontalOptionView horizontalOptionView8, HorizontalOptionView horizontalOptionView9, FrameLayout frameLayout2, HorizontalOptionView horizontalOptionView10, RelativeLayout relativeLayout4, ImageView imageView2, RelativeLayout relativeLayout5, LinearLayout linearLayout, HorizontalOptionView horizontalOptionView11, RelativeLayout relativeLayout6, RelativeLayout relativeLayout7, RelativeLayout relativeLayout8, HorizontalOptionView horizontalOptionView12, RelativeLayout relativeLayout9, RelativeLayout relativeLayout10, RelativeLayout relativeLayout11, HorizontalOptionView horizontalOptionView13, RelativeLayout relativeLayout12, HorizontalOptionView horizontalOptionView14, LinearLayout linearLayout2, TextView textView2, HorizontalOptionView horizontalOptionView15, TextView textView3, RelativeLayout relativeLayout13, HorizontalOptionView horizontalOptionView16, HorizontalOptionView horizontalOptionView17, HorizontalOptionView horizontalOptionView18, RelativeLayout relativeLayout14) {
        this.rootView = frameLayout;
        this.back15 = imageView;
        this.backview = relativeLayout;
        this.bip32 = horizontalOptionView;
        this.changeAddress = horizontalOptionView2;
        this.changeWalletType = horizontalOptionView3;
        this.checkEmulatorResult = textView;
        this.checkToken = horizontalOptionView4;
        this.cold = relativeLayout2;
        this.collapsing = horizontalOptionView5;
        this.commonUi = horizontalOptionView6;
        this.costomView = relativeLayout3;
        this.delegate = horizontalOptionView7;
        this.eip712 = horizontalOptionView8;
        this.enterUnstake = horizontalOptionView9;
        this.frameSubContent = frameLayout2;
        this.getTrx = horizontalOptionView10;
        this.hexDecode = relativeLayout4;
        this.ivCommonLeft = imageView2;
        this.kexuejia = relativeLayout5;
        this.llCommonLeft = linearLayout;
        this.lottie = horizontalOptionView11;
        this.newaddassets = relativeLayout6;
        this.nft = relativeLayout7;
        this.nodeSpeed = relativeLayout8;
        this.openTronwebDisplay = horizontalOptionView12;
        this.qrScan = relativeLayout9;
        this.qrScan2 = relativeLayout10;
        this.shieldToken = relativeLayout11;
        this.showCircleProgress = horizontalOptionView13;
        this.sqliteData = relativeLayout12;
        this.stake20 = horizontalOptionView14;
        this.statusbar = linearLayout2;
        this.switchCold = textView2;
        this.switchVersion = horizontalOptionView15;
        this.tvCommonTitle = textView3;
        this.uninstall = relativeLayout13;
        this.upgradeHd = horizontalOptionView16;
        this.upgradeHdCreate = horizontalOptionView17;
        this.upgradeHdReset = horizontalOptionView18;
        this.verify = relativeLayout14;
    }

    public static AcTestBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcTestBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_test, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcTestBinding bind(View view) {
        int i = R.id.back15;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.back15);
        if (imageView != null) {
            i = R.id.backview;
            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.backview);
            if (relativeLayout != null) {
                i = R.id.bip32;
                HorizontalOptionView horizontalOptionView = (HorizontalOptionView) ViewBindings.findChildViewById(view, R.id.bip32);
                if (horizontalOptionView != null) {
                    i = R.id.change_address;
                    HorizontalOptionView horizontalOptionView2 = (HorizontalOptionView) ViewBindings.findChildViewById(view, R.id.change_address);
                    if (horizontalOptionView2 != null) {
                        i = R.id.change_wallet_type;
                        HorizontalOptionView horizontalOptionView3 = (HorizontalOptionView) ViewBindings.findChildViewById(view, R.id.change_wallet_type);
                        if (horizontalOptionView3 != null) {
                            i = R.id.check_emulator_result;
                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.check_emulator_result);
                            if (textView != null) {
                                i = R.id.check_token;
                                HorizontalOptionView horizontalOptionView4 = (HorizontalOptionView) ViewBindings.findChildViewById(view, R.id.check_token);
                                if (horizontalOptionView4 != null) {
                                    i = R.id.cold;
                                    RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.cold);
                                    if (relativeLayout2 != null) {
                                        i = R.id.collapsing;
                                        HorizontalOptionView horizontalOptionView5 = (HorizontalOptionView) ViewBindings.findChildViewById(view, R.id.collapsing);
                                        if (horizontalOptionView5 != null) {
                                            i = R.id.common_ui;
                                            HorizontalOptionView horizontalOptionView6 = (HorizontalOptionView) ViewBindings.findChildViewById(view, R.id.common_ui);
                                            if (horizontalOptionView6 != null) {
                                                i = R.id.costom_view;
                                                RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.costom_view);
                                                if (relativeLayout3 != null) {
                                                    i = R.id.delegate;
                                                    HorizontalOptionView horizontalOptionView7 = (HorizontalOptionView) ViewBindings.findChildViewById(view, R.id.delegate);
                                                    if (horizontalOptionView7 != null) {
                                                        i = R.id.eip712;
                                                        HorizontalOptionView horizontalOptionView8 = (HorizontalOptionView) ViewBindings.findChildViewById(view, R.id.eip712);
                                                        if (horizontalOptionView8 != null) {
                                                            i = R.id.enter_unstake;
                                                            HorizontalOptionView horizontalOptionView9 = (HorizontalOptionView) ViewBindings.findChildViewById(view, R.id.enter_unstake);
                                                            if (horizontalOptionView9 != null) {
                                                                i = R.id.frame_sub_content;
                                                                FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.frame_sub_content);
                                                                if (frameLayout != null) {
                                                                    i = R.id.get_trx;
                                                                    HorizontalOptionView horizontalOptionView10 = (HorizontalOptionView) ViewBindings.findChildViewById(view, R.id.get_trx);
                                                                    if (horizontalOptionView10 != null) {
                                                                        i = R.id.hex_decode;
                                                                        RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.hex_decode);
                                                                        if (relativeLayout4 != null) {
                                                                            i = R.id.iv_common_left;
                                                                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_common_left);
                                                                            if (imageView2 != null) {
                                                                                i = R.id.kexuejia;
                                                                                RelativeLayout relativeLayout5 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.kexuejia);
                                                                                if (relativeLayout5 != null) {
                                                                                    i = R.id.ll_common_left;
                                                                                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_common_left);
                                                                                    if (linearLayout != null) {
                                                                                        i = R.id.lottie;
                                                                                        HorizontalOptionView horizontalOptionView11 = (HorizontalOptionView) ViewBindings.findChildViewById(view, R.id.lottie);
                                                                                        if (horizontalOptionView11 != null) {
                                                                                            i = R.id.newaddassets;
                                                                                            RelativeLayout relativeLayout6 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.newaddassets);
                                                                                            if (relativeLayout6 != null) {
                                                                                                i = R.id.nft;
                                                                                                RelativeLayout relativeLayout7 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.nft);
                                                                                                if (relativeLayout7 != null) {
                                                                                                    i = R.id.node_speed;
                                                                                                    RelativeLayout relativeLayout8 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.node_speed);
                                                                                                    if (relativeLayout8 != null) {
                                                                                                        i = R.id.open_tronweb_display;
                                                                                                        HorizontalOptionView horizontalOptionView12 = (HorizontalOptionView) ViewBindings.findChildViewById(view, R.id.open_tronweb_display);
                                                                                                        if (horizontalOptionView12 != null) {
                                                                                                            i = R.id.qr_scan;
                                                                                                            RelativeLayout relativeLayout9 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.qr_scan);
                                                                                                            if (relativeLayout9 != null) {
                                                                                                                i = R.id.qr_scan2;
                                                                                                                RelativeLayout relativeLayout10 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.qr_scan2);
                                                                                                                if (relativeLayout10 != null) {
                                                                                                                    i = R.id.shield_token;
                                                                                                                    RelativeLayout relativeLayout11 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.shield_token);
                                                                                                                    if (relativeLayout11 != null) {
                                                                                                                        i = R.id.show_circle_progress;
                                                                                                                        HorizontalOptionView horizontalOptionView13 = (HorizontalOptionView) ViewBindings.findChildViewById(view, R.id.show_circle_progress);
                                                                                                                        if (horizontalOptionView13 != null) {
                                                                                                                            i = R.id.sqlite_data;
                                                                                                                            RelativeLayout relativeLayout12 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.sqlite_data);
                                                                                                                            if (relativeLayout12 != null) {
                                                                                                                                i = R.id.stake20;
                                                                                                                                HorizontalOptionView horizontalOptionView14 = (HorizontalOptionView) ViewBindings.findChildViewById(view, R.id.stake20);
                                                                                                                                if (horizontalOptionView14 != null) {
                                                                                                                                    i = R.id.statusbar;
                                                                                                                                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.statusbar);
                                                                                                                                    if (linearLayout2 != null) {
                                                                                                                                        i = R.id.switch_cold;
                                                                                                                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.switch_cold);
                                                                                                                                        if (textView2 != null) {
                                                                                                                                            i = R.id.switch_version;
                                                                                                                                            HorizontalOptionView horizontalOptionView15 = (HorizontalOptionView) ViewBindings.findChildViewById(view, R.id.switch_version);
                                                                                                                                            if (horizontalOptionView15 != null) {
                                                                                                                                                i = R.id.tv_common_title;
                                                                                                                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_common_title);
                                                                                                                                                if (textView3 != null) {
                                                                                                                                                    i = R.id.uninstall;
                                                                                                                                                    RelativeLayout relativeLayout13 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.uninstall);
                                                                                                                                                    if (relativeLayout13 != null) {
                                                                                                                                                        i = R.id.upgrade_hd;
                                                                                                                                                        HorizontalOptionView horizontalOptionView16 = (HorizontalOptionView) ViewBindings.findChildViewById(view, R.id.upgrade_hd);
                                                                                                                                                        if (horizontalOptionView16 != null) {
                                                                                                                                                            i = R.id.upgrade_hd_create;
                                                                                                                                                            HorizontalOptionView horizontalOptionView17 = (HorizontalOptionView) ViewBindings.findChildViewById(view, R.id.upgrade_hd_create);
                                                                                                                                                            if (horizontalOptionView17 != null) {
                                                                                                                                                                i = R.id.upgrade_hd_reset;
                                                                                                                                                                HorizontalOptionView horizontalOptionView18 = (HorizontalOptionView) ViewBindings.findChildViewById(view, R.id.upgrade_hd_reset);
                                                                                                                                                                if (horizontalOptionView18 != null) {
                                                                                                                                                                    i = R.id.verify;
                                                                                                                                                                    RelativeLayout relativeLayout14 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.verify);
                                                                                                                                                                    if (relativeLayout14 != null) {
                                                                                                                                                                        return new AcTestBinding((FrameLayout) view, imageView, relativeLayout, horizontalOptionView, horizontalOptionView2, horizontalOptionView3, textView, horizontalOptionView4, relativeLayout2, horizontalOptionView5, horizontalOptionView6, relativeLayout3, horizontalOptionView7, horizontalOptionView8, horizontalOptionView9, frameLayout, horizontalOptionView10, relativeLayout4, imageView2, relativeLayout5, linearLayout, horizontalOptionView11, relativeLayout6, relativeLayout7, relativeLayout8, horizontalOptionView12, relativeLayout9, relativeLayout10, relativeLayout11, horizontalOptionView13, relativeLayout12, horizontalOptionView14, linearLayout2, textView2, horizontalOptionView15, textView3, relativeLayout13, horizontalOptionView16, horizontalOptionView17, horizontalOptionView18, relativeLayout14);
                                                                                                                                                                    }
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                        }
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
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
