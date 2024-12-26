package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.CommonTitleDescriptionEditView;
import com.tron.wallet.common.components.ErrorEdiTextLayout;
import com.tron.wallet.common.components.ErrorImportMnemonicLayout;
import com.tron.wallet.common.components.TrimEditText;
import com.tron.wallet.common.components.flowlayout.TagFlowLayout;
import com.tron.wallet.common.components.mnemonicflowlayout.MnemonicTagFlowLayout;
import com.tron.wallet.common.components.mnemonicflowlayout.TagScrollView;
import com.tronlinkpro.wallet.R;
public final class ContentImportWalletBinding implements ViewBinding {
    public final TextView btnPaste;
    public final CheckBox cbFour;
    public final CheckBox cbOne;
    public final CheckBox cbThree;
    public final CheckBox cbTwo;
    public final ErrorEdiTextLayout eetContent;
    public final ErrorImportMnemonicLayout eetMnemonic;
    public final View emptyPlaceholder;
    public final TrimEditText etContent;
    public final TagFlowLayout flAssociational;
    public final MnemonicTagFlowLayout idFlowlayout;
    public final CommonTitleDescriptionEditView importContent;
    public final CommonTitleDescriptionEditView importWalletName;
    public final CommonTitleDescriptionEditView importWalletPassword;
    public final CommonTitleDescriptionEditView importWalletPasswordAgain;
    public final ImageView ivQr;
    public final LinearLayout leftBottom;
    public final LinearLayout leftTop;
    public final LinearLayout liAddWatchWallet;
    public final LinearLayout liImportWalletInfo;
    public final LinearLayout llContentError;
    public final LinearLayout llNameError;
    public final LinearLayout llPasswordAgainError;
    public final RelativeLayout mnemonicLayout;
    public final LinearLayout rightTop;
    public final RelativeLayout rlPassChecks;
    private final NestedScrollView rootView;
    public final NestedScrollView scrollAddWatchWallet;
    public final TagScrollView scrollview;
    public final TextView tvContentError;
    public final TextView tvFour;
    public final TextView tvHasHdTips;
    public final TextView tvKeyCount;
    public final TextView tvNameError;
    public final TextView tvOne;
    public final TextView tvPasswordAgainError;
    public final TextView tvThree;
    public final TextView tvTwo;

    @Override
    public NestedScrollView getRoot() {
        return this.rootView;
    }

    private ContentImportWalletBinding(NestedScrollView nestedScrollView, TextView textView, CheckBox checkBox, CheckBox checkBox2, CheckBox checkBox3, CheckBox checkBox4, ErrorEdiTextLayout errorEdiTextLayout, ErrorImportMnemonicLayout errorImportMnemonicLayout, View view, TrimEditText trimEditText, TagFlowLayout tagFlowLayout, MnemonicTagFlowLayout mnemonicTagFlowLayout, CommonTitleDescriptionEditView commonTitleDescriptionEditView, CommonTitleDescriptionEditView commonTitleDescriptionEditView2, CommonTitleDescriptionEditView commonTitleDescriptionEditView3, CommonTitleDescriptionEditView commonTitleDescriptionEditView4, ImageView imageView, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, LinearLayout linearLayout5, LinearLayout linearLayout6, LinearLayout linearLayout7, RelativeLayout relativeLayout, LinearLayout linearLayout8, RelativeLayout relativeLayout2, NestedScrollView nestedScrollView2, TagScrollView tagScrollView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10) {
        this.rootView = nestedScrollView;
        this.btnPaste = textView;
        this.cbFour = checkBox;
        this.cbOne = checkBox2;
        this.cbThree = checkBox3;
        this.cbTwo = checkBox4;
        this.eetContent = errorEdiTextLayout;
        this.eetMnemonic = errorImportMnemonicLayout;
        this.emptyPlaceholder = view;
        this.etContent = trimEditText;
        this.flAssociational = tagFlowLayout;
        this.idFlowlayout = mnemonicTagFlowLayout;
        this.importContent = commonTitleDescriptionEditView;
        this.importWalletName = commonTitleDescriptionEditView2;
        this.importWalletPassword = commonTitleDescriptionEditView3;
        this.importWalletPasswordAgain = commonTitleDescriptionEditView4;
        this.ivQr = imageView;
        this.leftBottom = linearLayout;
        this.leftTop = linearLayout2;
        this.liAddWatchWallet = linearLayout3;
        this.liImportWalletInfo = linearLayout4;
        this.llContentError = linearLayout5;
        this.llNameError = linearLayout6;
        this.llPasswordAgainError = linearLayout7;
        this.mnemonicLayout = relativeLayout;
        this.rightTop = linearLayout8;
        this.rlPassChecks = relativeLayout2;
        this.scrollAddWatchWallet = nestedScrollView2;
        this.scrollview = tagScrollView;
        this.tvContentError = textView2;
        this.tvFour = textView3;
        this.tvHasHdTips = textView4;
        this.tvKeyCount = textView5;
        this.tvNameError = textView6;
        this.tvOne = textView7;
        this.tvPasswordAgainError = textView8;
        this.tvThree = textView9;
        this.tvTwo = textView10;
    }

    public static ContentImportWalletBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ContentImportWalletBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.content_import_wallet, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ContentImportWalletBinding bind(View view) {
        int i = R.id.btn_paste;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.btn_paste);
        if (textView != null) {
            i = R.id.cb_four;
            CheckBox checkBox = (CheckBox) ViewBindings.findChildViewById(view, R.id.cb_four);
            if (checkBox != null) {
                i = R.id.cb_one;
                CheckBox checkBox2 = (CheckBox) ViewBindings.findChildViewById(view, R.id.cb_one);
                if (checkBox2 != null) {
                    i = R.id.cb_three;
                    CheckBox checkBox3 = (CheckBox) ViewBindings.findChildViewById(view, R.id.cb_three);
                    if (checkBox3 != null) {
                        i = R.id.cb_two;
                        CheckBox checkBox4 = (CheckBox) ViewBindings.findChildViewById(view, R.id.cb_two);
                        if (checkBox4 != null) {
                            i = R.id.eet_content;
                            ErrorEdiTextLayout errorEdiTextLayout = (ErrorEdiTextLayout) ViewBindings.findChildViewById(view, R.id.eet_content);
                            if (errorEdiTextLayout != null) {
                                i = R.id.eet_mnemonic;
                                ErrorImportMnemonicLayout errorImportMnemonicLayout = (ErrorImportMnemonicLayout) ViewBindings.findChildViewById(view, R.id.eet_mnemonic);
                                if (errorImportMnemonicLayout != null) {
                                    i = R.id.empty_placeholder;
                                    View findChildViewById = ViewBindings.findChildViewById(view, R.id.empty_placeholder);
                                    if (findChildViewById != null) {
                                        i = R.id.et_content;
                                        TrimEditText trimEditText = (TrimEditText) ViewBindings.findChildViewById(view, R.id.et_content);
                                        if (trimEditText != null) {
                                            i = R.id.fl_associational;
                                            TagFlowLayout tagFlowLayout = (TagFlowLayout) ViewBindings.findChildViewById(view, R.id.fl_associational);
                                            if (tagFlowLayout != null) {
                                                i = R.id.id_flowlayout;
                                                MnemonicTagFlowLayout mnemonicTagFlowLayout = (MnemonicTagFlowLayout) ViewBindings.findChildViewById(view, R.id.id_flowlayout);
                                                if (mnemonicTagFlowLayout != null) {
                                                    i = R.id.import_content;
                                                    CommonTitleDescriptionEditView commonTitleDescriptionEditView = (CommonTitleDescriptionEditView) ViewBindings.findChildViewById(view, R.id.import_content);
                                                    if (commonTitleDescriptionEditView != null) {
                                                        i = R.id.import_wallet_name;
                                                        CommonTitleDescriptionEditView commonTitleDescriptionEditView2 = (CommonTitleDescriptionEditView) ViewBindings.findChildViewById(view, R.id.import_wallet_name);
                                                        if (commonTitleDescriptionEditView2 != null) {
                                                            i = R.id.import_wallet_password;
                                                            CommonTitleDescriptionEditView commonTitleDescriptionEditView3 = (CommonTitleDescriptionEditView) ViewBindings.findChildViewById(view, R.id.import_wallet_password);
                                                            if (commonTitleDescriptionEditView3 != null) {
                                                                i = R.id.import_wallet_password_again;
                                                                CommonTitleDescriptionEditView commonTitleDescriptionEditView4 = (CommonTitleDescriptionEditView) ViewBindings.findChildViewById(view, R.id.import_wallet_password_again);
                                                                if (commonTitleDescriptionEditView4 != null) {
                                                                    i = R.id.iv_qr;
                                                                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_qr);
                                                                    if (imageView != null) {
                                                                        i = R.id.left_bottom;
                                                                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.left_bottom);
                                                                        if (linearLayout != null) {
                                                                            i = R.id.left_top;
                                                                            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.left_top);
                                                                            if (linearLayout2 != null) {
                                                                                i = R.id.li_add_watch_wallet;
                                                                                LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_add_watch_wallet);
                                                                                if (linearLayout3 != null) {
                                                                                    i = R.id.li_import_wallet_info;
                                                                                    LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_import_wallet_info);
                                                                                    if (linearLayout4 != null) {
                                                                                        i = R.id.ll_content_error;
                                                                                        LinearLayout linearLayout5 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_content_error);
                                                                                        if (linearLayout5 != null) {
                                                                                            i = R.id.ll_name_error;
                                                                                            LinearLayout linearLayout6 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_name_error);
                                                                                            if (linearLayout6 != null) {
                                                                                                i = R.id.ll_password_again_error;
                                                                                                LinearLayout linearLayout7 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_password_again_error);
                                                                                                if (linearLayout7 != null) {
                                                                                                    i = R.id.mnemonic_layout;
                                                                                                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.mnemonic_layout);
                                                                                                    if (relativeLayout != null) {
                                                                                                        i = R.id.right_top;
                                                                                                        LinearLayout linearLayout8 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.right_top);
                                                                                                        if (linearLayout8 != null) {
                                                                                                            i = R.id.rl_pass_checks;
                                                                                                            RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_pass_checks);
                                                                                                            if (relativeLayout2 != null) {
                                                                                                                NestedScrollView nestedScrollView = (NestedScrollView) view;
                                                                                                                i = R.id.scrollview;
                                                                                                                TagScrollView tagScrollView = (TagScrollView) ViewBindings.findChildViewById(view, R.id.scrollview);
                                                                                                                if (tagScrollView != null) {
                                                                                                                    i = R.id.tv_content_error;
                                                                                                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_content_error);
                                                                                                                    if (textView2 != null) {
                                                                                                                        i = R.id.tv_four;
                                                                                                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_four);
                                                                                                                        if (textView3 != null) {
                                                                                                                            i = R.id.tv_has_hd_tips;
                                                                                                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_has_hd_tips);
                                                                                                                            if (textView4 != null) {
                                                                                                                                i = R.id.tv_key_count;
                                                                                                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_key_count);
                                                                                                                                if (textView5 != null) {
                                                                                                                                    i = R.id.tv_name_error;
                                                                                                                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name_error);
                                                                                                                                    if (textView6 != null) {
                                                                                                                                        i = R.id.tv_one;
                                                                                                                                        TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_one);
                                                                                                                                        if (textView7 != null) {
                                                                                                                                            i = R.id.tv_password_again_error;
                                                                                                                                            TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_password_again_error);
                                                                                                                                            if (textView8 != null) {
                                                                                                                                                i = R.id.tv_three;
                                                                                                                                                TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_three);
                                                                                                                                                if (textView9 != null) {
                                                                                                                                                    i = R.id.tv_two;
                                                                                                                                                    TextView textView10 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_two);
                                                                                                                                                    if (textView10 != null) {
                                                                                                                                                        return new ContentImportWalletBinding(nestedScrollView, textView, checkBox, checkBox2, checkBox3, checkBox4, errorEdiTextLayout, errorImportMnemonicLayout, findChildViewById, trimEditText, tagFlowLayout, mnemonicTagFlowLayout, commonTitleDescriptionEditView, commonTitleDescriptionEditView2, commonTitleDescriptionEditView3, commonTitleDescriptionEditView4, imageView, linearLayout, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6, linearLayout7, relativeLayout, linearLayout8, relativeLayout2, nestedScrollView, tagScrollView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10);
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
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
