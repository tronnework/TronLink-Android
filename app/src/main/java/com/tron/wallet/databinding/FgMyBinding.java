package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.business.tabmy.advancedfeatures.HorizontalOptionView;
import com.tron.wallet.common.components.BadgeButton;
import com.tronlinkpro.wallet.R;
public final class FgMyBinding implements ViewBinding {
    public final RelativeLayout about;
    public final RelativeLayout addressBook;
    public final RelativeLayout advancedFeatures;
    public final RelativeLayout announcement;
    public final RelativeLayout backupLayout;
    public final View bottomView;
    public final Button bt;
    public final View dd;
    public final RelativeLayout friendInvitation;
    public final RelativeLayout hardwareManagement;
    public final RelativeLayout help;
    public final ImageView icMyWallet;
    public final ImageView ivAbout;
    public final ImageView ivAddressBook;
    public final ImageView ivAdvancedFeatures;
    public final ImageView ivAnnouncement;
    public final ImageView ivArrowRight;
    public final ImageView ivBackupRecord;
    public final ImageView ivDotTip;
    public final ImageView ivFriendInvitation;
    public final ImageView ivHelp;
    public final ImageView ivHelpDotTip;
    public final ImageView ivLedger;
    public final ImageView ivSafetyAcademy;
    public final ImageView ivSetting;
    public final ImageView ivShieldWalletManager;
    public final ImageView ivTransaction;
    public final ImageView ivWalletManager;
    public final View lineAdvancedFeatures;
    public final RelativeLayout llHeader;
    public final LinearLayout llNewAssetsCount;
    public final FrameLayout rlBell;
    private final RelativeLayout rootView;
    public final RelativeLayout safetyAcademy;
    public final ScrollView scrollLayout;
    public final RelativeLayout setting;
    public final RelativeLayout shieldWalletManager;
    public final RelativeLayout transferHistory;
    public final TextView tvAbout;
    public final TextView tvAddressBook;
    public final TextView tvAdvancedFeatures;
    public final TextView tvBackupRecordsTitle;
    public final BadgeButton tvBell;
    public final TextView tvFriendInvitation;
    public final TextView tvHasNewVersion;
    public final TextView tvHelp;
    public final TextView tvLedger;
    public final TextView tvSecurityAcademy;
    public final TextView tvShieldWallet;
    public final HorizontalOptionView unitTest;
    public final View vAn;
    public final RelativeLayout walletManager;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FgMyBinding(RelativeLayout relativeLayout, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, RelativeLayout relativeLayout5, RelativeLayout relativeLayout6, View view, Button button, View view2, RelativeLayout relativeLayout7, RelativeLayout relativeLayout8, RelativeLayout relativeLayout9, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView imageView6, ImageView imageView7, ImageView imageView8, ImageView imageView9, ImageView imageView10, ImageView imageView11, ImageView imageView12, ImageView imageView13, ImageView imageView14, ImageView imageView15, ImageView imageView16, ImageView imageView17, View view3, RelativeLayout relativeLayout10, LinearLayout linearLayout, FrameLayout frameLayout, RelativeLayout relativeLayout11, ScrollView scrollView, RelativeLayout relativeLayout12, RelativeLayout relativeLayout13, RelativeLayout relativeLayout14, TextView textView, TextView textView2, TextView textView3, TextView textView4, BadgeButton badgeButton, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, HorizontalOptionView horizontalOptionView, View view4, RelativeLayout relativeLayout15) {
        this.rootView = relativeLayout;
        this.about = relativeLayout2;
        this.addressBook = relativeLayout3;
        this.advancedFeatures = relativeLayout4;
        this.announcement = relativeLayout5;
        this.backupLayout = relativeLayout6;
        this.bottomView = view;
        this.bt = button;
        this.dd = view2;
        this.friendInvitation = relativeLayout7;
        this.hardwareManagement = relativeLayout8;
        this.help = relativeLayout9;
        this.icMyWallet = imageView;
        this.ivAbout = imageView2;
        this.ivAddressBook = imageView3;
        this.ivAdvancedFeatures = imageView4;
        this.ivAnnouncement = imageView5;
        this.ivArrowRight = imageView6;
        this.ivBackupRecord = imageView7;
        this.ivDotTip = imageView8;
        this.ivFriendInvitation = imageView9;
        this.ivHelp = imageView10;
        this.ivHelpDotTip = imageView11;
        this.ivLedger = imageView12;
        this.ivSafetyAcademy = imageView13;
        this.ivSetting = imageView14;
        this.ivShieldWalletManager = imageView15;
        this.ivTransaction = imageView16;
        this.ivWalletManager = imageView17;
        this.lineAdvancedFeatures = view3;
        this.llHeader = relativeLayout10;
        this.llNewAssetsCount = linearLayout;
        this.rlBell = frameLayout;
        this.safetyAcademy = relativeLayout11;
        this.scrollLayout = scrollView;
        this.setting = relativeLayout12;
        this.shieldWalletManager = relativeLayout13;
        this.transferHistory = relativeLayout14;
        this.tvAbout = textView;
        this.tvAddressBook = textView2;
        this.tvAdvancedFeatures = textView3;
        this.tvBackupRecordsTitle = textView4;
        this.tvBell = badgeButton;
        this.tvFriendInvitation = textView5;
        this.tvHasNewVersion = textView6;
        this.tvHelp = textView7;
        this.tvLedger = textView8;
        this.tvSecurityAcademy = textView9;
        this.tvShieldWallet = textView10;
        this.unitTest = horizontalOptionView;
        this.vAn = view4;
        this.walletManager = relativeLayout15;
    }

    public static FgMyBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FgMyBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fg_my, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FgMyBinding bind(View view) {
        int i = R.id.about;
        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.about);
        if (relativeLayout != null) {
            i = R.id.address_book;
            RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.address_book);
            if (relativeLayout2 != null) {
                i = R.id.advanced_features;
                RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.advanced_features);
                if (relativeLayout3 != null) {
                    i = R.id.announcement;
                    RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.announcement);
                    if (relativeLayout4 != null) {
                        i = R.id.backup_layout;
                        RelativeLayout relativeLayout5 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.backup_layout);
                        if (relativeLayout5 != null) {
                            i = R.id.bottom_view;
                            View findChildViewById = ViewBindings.findChildViewById(view, R.id.bottom_view);
                            if (findChildViewById != null) {
                                i = R.id.bt;
                                Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt);
                                if (button != null) {
                                    i = R.id.dd;
                                    View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.dd);
                                    if (findChildViewById2 != null) {
                                        i = R.id.friend_invitation;
                                        RelativeLayout relativeLayout6 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.friend_invitation);
                                        if (relativeLayout6 != null) {
                                            i = R.id.hardware_management;
                                            RelativeLayout relativeLayout7 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.hardware_management);
                                            if (relativeLayout7 != null) {
                                                i = R.id.help;
                                                RelativeLayout relativeLayout8 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.help);
                                                if (relativeLayout8 != null) {
                                                    i = R.id.ic_my_wallet;
                                                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.ic_my_wallet);
                                                    if (imageView != null) {
                                                        i = R.id.iv_about;
                                                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_about);
                                                        if (imageView2 != null) {
                                                            i = R.id.iv_address_book;
                                                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_address_book);
                                                            if (imageView3 != null) {
                                                                i = R.id.iv_advanced_features;
                                                                ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_advanced_features);
                                                                if (imageView4 != null) {
                                                                    i = R.id.iv_announcement;
                                                                    ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_announcement);
                                                                    if (imageView5 != null) {
                                                                        i = R.id.iv_arrow_right;
                                                                        ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_arrow_right);
                                                                        if (imageView6 != null) {
                                                                            i = R.id.iv_backup_record;
                                                                            ImageView imageView7 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_backup_record);
                                                                            if (imageView7 != null) {
                                                                                i = R.id.iv_dot_tip;
                                                                                ImageView imageView8 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_dot_tip);
                                                                                if (imageView8 != null) {
                                                                                    i = R.id.iv_friend_invitation;
                                                                                    ImageView imageView9 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_friend_invitation);
                                                                                    if (imageView9 != null) {
                                                                                        i = R.id.iv_help;
                                                                                        ImageView imageView10 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_help);
                                                                                        if (imageView10 != null) {
                                                                                            i = R.id.iv_help_dot_tip;
                                                                                            ImageView imageView11 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_help_dot_tip);
                                                                                            if (imageView11 != null) {
                                                                                                i = R.id.iv_ledger;
                                                                                                ImageView imageView12 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_ledger);
                                                                                                if (imageView12 != null) {
                                                                                                    i = R.id.iv_safety_academy;
                                                                                                    ImageView imageView13 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_safety_academy);
                                                                                                    if (imageView13 != null) {
                                                                                                        i = R.id.iv_setting;
                                                                                                        ImageView imageView14 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_setting);
                                                                                                        if (imageView14 != null) {
                                                                                                            i = R.id.iv_shield_wallet_manager;
                                                                                                            ImageView imageView15 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_shield_wallet_manager);
                                                                                                            if (imageView15 != null) {
                                                                                                                i = R.id.iv_transaction;
                                                                                                                ImageView imageView16 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_transaction);
                                                                                                                if (imageView16 != null) {
                                                                                                                    i = R.id.iv_wallet_manager;
                                                                                                                    ImageView imageView17 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_wallet_manager);
                                                                                                                    if (imageView17 != null) {
                                                                                                                        i = R.id.line_advanced_features;
                                                                                                                        View findChildViewById3 = ViewBindings.findChildViewById(view, R.id.line_advanced_features);
                                                                                                                        if (findChildViewById3 != null) {
                                                                                                                            i = R.id.ll_header;
                                                                                                                            RelativeLayout relativeLayout9 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_header);
                                                                                                                            if (relativeLayout9 != null) {
                                                                                                                                i = R.id.ll_new_assets_count;
                                                                                                                                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_new_assets_count);
                                                                                                                                if (linearLayout != null) {
                                                                                                                                    i = R.id.rl_bell;
                                                                                                                                    FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.rl_bell);
                                                                                                                                    if (frameLayout != null) {
                                                                                                                                        i = R.id.safety_academy;
                                                                                                                                        RelativeLayout relativeLayout10 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.safety_academy);
                                                                                                                                        if (relativeLayout10 != null) {
                                                                                                                                            i = R.id.scroll_layout;
                                                                                                                                            ScrollView scrollView = (ScrollView) ViewBindings.findChildViewById(view, R.id.scroll_layout);
                                                                                                                                            if (scrollView != null) {
                                                                                                                                                i = R.id.setting;
                                                                                                                                                RelativeLayout relativeLayout11 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.setting);
                                                                                                                                                if (relativeLayout11 != null) {
                                                                                                                                                    i = R.id.shield_wallet_manager;
                                                                                                                                                    RelativeLayout relativeLayout12 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.shield_wallet_manager);
                                                                                                                                                    if (relativeLayout12 != null) {
                                                                                                                                                        i = R.id.transfer_history;
                                                                                                                                                        RelativeLayout relativeLayout13 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.transfer_history);
                                                                                                                                                        if (relativeLayout13 != null) {
                                                                                                                                                            i = R.id.tv_about;
                                                                                                                                                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_about);
                                                                                                                                                            if (textView != null) {
                                                                                                                                                                i = R.id.tv_address_book;
                                                                                                                                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address_book);
                                                                                                                                                                if (textView2 != null) {
                                                                                                                                                                    i = R.id.tv_advanced_features;
                                                                                                                                                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_advanced_features);
                                                                                                                                                                    if (textView3 != null) {
                                                                                                                                                                        i = R.id.tv_backup_records_title;
                                                                                                                                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_backup_records_title);
                                                                                                                                                                        if (textView4 != null) {
                                                                                                                                                                            i = R.id.tv_bell;
                                                                                                                                                                            BadgeButton badgeButton = (BadgeButton) ViewBindings.findChildViewById(view, R.id.tv_bell);
                                                                                                                                                                            if (badgeButton != null) {
                                                                                                                                                                                i = R.id.tv_friend_invitation;
                                                                                                                                                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_friend_invitation);
                                                                                                                                                                                if (textView5 != null) {
                                                                                                                                                                                    i = R.id.tv_has_new_version;
                                                                                                                                                                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_has_new_version);
                                                                                                                                                                                    if (textView6 != null) {
                                                                                                                                                                                        i = R.id.tv_help;
                                                                                                                                                                                        TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_help);
                                                                                                                                                                                        if (textView7 != null) {
                                                                                                                                                                                            i = R.id.tv_ledger;
                                                                                                                                                                                            TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_ledger);
                                                                                                                                                                                            if (textView8 != null) {
                                                                                                                                                                                                i = R.id.tv_security_academy;
                                                                                                                                                                                                TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_security_academy);
                                                                                                                                                                                                if (textView9 != null) {
                                                                                                                                                                                                    i = R.id.tv_shield_wallet;
                                                                                                                                                                                                    TextView textView10 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_shield_wallet);
                                                                                                                                                                                                    if (textView10 != null) {
                                                                                                                                                                                                        i = R.id.unit_test;
                                                                                                                                                                                                        HorizontalOptionView horizontalOptionView = (HorizontalOptionView) ViewBindings.findChildViewById(view, R.id.unit_test);
                                                                                                                                                                                                        if (horizontalOptionView != null) {
                                                                                                                                                                                                            i = R.id.v_an;
                                                                                                                                                                                                            View findChildViewById4 = ViewBindings.findChildViewById(view, R.id.v_an);
                                                                                                                                                                                                            if (findChildViewById4 != null) {
                                                                                                                                                                                                                i = R.id.wallet_manager;
                                                                                                                                                                                                                RelativeLayout relativeLayout14 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.wallet_manager);
                                                                                                                                                                                                                if (relativeLayout14 != null) {
                                                                                                                                                                                                                    return new FgMyBinding((RelativeLayout) view, relativeLayout, relativeLayout2, relativeLayout3, relativeLayout4, relativeLayout5, findChildViewById, button, findChildViewById2, relativeLayout6, relativeLayout7, relativeLayout8, imageView, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9, imageView10, imageView11, imageView12, imageView13, imageView14, imageView15, imageView16, imageView17, findChildViewById3, relativeLayout9, linearLayout, frameLayout, relativeLayout10, scrollView, relativeLayout11, relativeLayout12, relativeLayout13, textView, textView2, textView3, textView4, badgeButton, textView5, textView6, textView7, textView8, textView9, textView10, horizontalOptionView, findChildViewById4, relativeLayout14);
                                                                                                                                                                                                                }
                                                                                                                                                                                                            }
                                                                                                                                                                                                        }
                                                                                                                                                                                                    }
                                                                                                                                                                                                }
                                                                                                                                                                                            }
                                                                                                                                                                                        }
                                                                                                                                                                                    }
                                                                                                                                                                                }
                                                                                                                                                                            }
                                                                                                                                                                        }
                                                                                                                                                                    }
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                        }
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
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
