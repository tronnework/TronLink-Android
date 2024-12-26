package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tron.wallet.common.components.CurrencyEditText;
import com.tron.wallet.common.components.ErrorBottomLayout;
import com.tron.wallet.common.components.ErrorView;
import com.tron.wallet.common.components.ItemWarningTagView;
import com.tronlinkpro.wallet.R;
public final class AcTransferSelectTokenBinding implements ViewBinding {
    public final TextView btNoteRemove;
    public final Button btSend;
    public final CurrencyEditText etCount;
    public final EditText etNote;
    public final ImageView ivAddNote;
    public final ImageView ivDelete;
    public final SimpleDraweeView ivNftItem;
    public final ImageView ivNftRow;
    public final ImageView ivOfficialImage;
    public final ImageView ivRow;
    public final SimpleDraweeView ivTokenIcon;
    public final LinearLayout llAddNote;
    public final RelativeLayout llAmount;
    public final ConstraintLayout llCollection;
    public final LinearLayout llContent;
    public final ErrorBottomLayout llErrAmount;
    public final ErrorBottomLayout llErrCollection;
    public final ErrorBottomLayout llErrSelectToken;
    public final LinearLayout llNoCollection;
    public final LinearLayout llNote;
    public final NestedScrollView llScroll;
    public final ConstraintLayout llToken;
    public final ItemWarningTagView rlScam;
    public final LinearLayout root;
    private final LinearLayout rootView;
    public final View splitLine;
    public final View splitLineNote;
    public final TextView tvAmountInfo;
    public final TextView tvAmountTitle;
    public final TextView tvBalance;
    public final TextView tvMax;
    public final TextView tvMultiTip;
    public final TextView tvName;
    public final TextView tvNftId;
    public final TextView tvNftName;
    public final TextView tvNoteCount;
    public final ErrorView tvNoteTip;
    public final TextView tvSymbol;
    public final TextView tvValue;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private AcTransferSelectTokenBinding(LinearLayout linearLayout, TextView textView, Button button, CurrencyEditText currencyEditText, EditText editText, ImageView imageView, ImageView imageView2, SimpleDraweeView simpleDraweeView, ImageView imageView3, ImageView imageView4, ImageView imageView5, SimpleDraweeView simpleDraweeView2, LinearLayout linearLayout2, RelativeLayout relativeLayout, ConstraintLayout constraintLayout, LinearLayout linearLayout3, ErrorBottomLayout errorBottomLayout, ErrorBottomLayout errorBottomLayout2, ErrorBottomLayout errorBottomLayout3, LinearLayout linearLayout4, LinearLayout linearLayout5, NestedScrollView nestedScrollView, ConstraintLayout constraintLayout2, ItemWarningTagView itemWarningTagView, LinearLayout linearLayout6, View view, View view2, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, ErrorView errorView, TextView textView11, TextView textView12) {
        this.rootView = linearLayout;
        this.btNoteRemove = textView;
        this.btSend = button;
        this.etCount = currencyEditText;
        this.etNote = editText;
        this.ivAddNote = imageView;
        this.ivDelete = imageView2;
        this.ivNftItem = simpleDraweeView;
        this.ivNftRow = imageView3;
        this.ivOfficialImage = imageView4;
        this.ivRow = imageView5;
        this.ivTokenIcon = simpleDraweeView2;
        this.llAddNote = linearLayout2;
        this.llAmount = relativeLayout;
        this.llCollection = constraintLayout;
        this.llContent = linearLayout3;
        this.llErrAmount = errorBottomLayout;
        this.llErrCollection = errorBottomLayout2;
        this.llErrSelectToken = errorBottomLayout3;
        this.llNoCollection = linearLayout4;
        this.llNote = linearLayout5;
        this.llScroll = nestedScrollView;
        this.llToken = constraintLayout2;
        this.rlScam = itemWarningTagView;
        this.root = linearLayout6;
        this.splitLine = view;
        this.splitLineNote = view2;
        this.tvAmountInfo = textView2;
        this.tvAmountTitle = textView3;
        this.tvBalance = textView4;
        this.tvMax = textView5;
        this.tvMultiTip = textView6;
        this.tvName = textView7;
        this.tvNftId = textView8;
        this.tvNftName = textView9;
        this.tvNoteCount = textView10;
        this.tvNoteTip = errorView;
        this.tvSymbol = textView11;
        this.tvValue = textView12;
    }

    public static AcTransferSelectTokenBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcTransferSelectTokenBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_transfer_select_token, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcTransferSelectTokenBinding bind(View view) {
        int i = R.id.bt_note_remove;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.bt_note_remove);
        if (textView != null) {
            i = R.id.bt_send;
            Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_send);
            if (button != null) {
                i = R.id.et_count;
                CurrencyEditText currencyEditText = (CurrencyEditText) ViewBindings.findChildViewById(view, R.id.et_count);
                if (currencyEditText != null) {
                    i = R.id.et_note;
                    EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.et_note);
                    if (editText != null) {
                        i = R.id.iv_add_note;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_add_note);
                        if (imageView != null) {
                            i = R.id.iv_delete;
                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_delete);
                            if (imageView2 != null) {
                                i = R.id.iv_nft_item;
                                SimpleDraweeView simpleDraweeView = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.iv_nft_item);
                                if (simpleDraweeView != null) {
                                    i = R.id.iv_nft_row;
                                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_nft_row);
                                    if (imageView3 != null) {
                                        i = R.id.iv_official_image;
                                        ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_official_image);
                                        if (imageView4 != null) {
                                            i = R.id.iv_row;
                                            ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_row);
                                            if (imageView5 != null) {
                                                i = R.id.iv_token_icon;
                                                SimpleDraweeView simpleDraweeView2 = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.iv_token_icon);
                                                if (simpleDraweeView2 != null) {
                                                    i = R.id.ll_add_note;
                                                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_add_note);
                                                    if (linearLayout != null) {
                                                        i = R.id.ll_amount;
                                                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_amount);
                                                        if (relativeLayout != null) {
                                                            i = R.id.ll_collection;
                                                            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.ll_collection);
                                                            if (constraintLayout != null) {
                                                                i = R.id.ll_content;
                                                                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_content);
                                                                if (linearLayout2 != null) {
                                                                    i = R.id.ll_err_amount;
                                                                    ErrorBottomLayout errorBottomLayout = (ErrorBottomLayout) ViewBindings.findChildViewById(view, R.id.ll_err_amount);
                                                                    if (errorBottomLayout != null) {
                                                                        i = R.id.ll_err_collection;
                                                                        ErrorBottomLayout errorBottomLayout2 = (ErrorBottomLayout) ViewBindings.findChildViewById(view, R.id.ll_err_collection);
                                                                        if (errorBottomLayout2 != null) {
                                                                            i = R.id.ll_err_select_token;
                                                                            ErrorBottomLayout errorBottomLayout3 = (ErrorBottomLayout) ViewBindings.findChildViewById(view, R.id.ll_err_select_token);
                                                                            if (errorBottomLayout3 != null) {
                                                                                i = R.id.ll_no_collection;
                                                                                LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_no_collection);
                                                                                if (linearLayout3 != null) {
                                                                                    i = R.id.ll_note;
                                                                                    LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_note);
                                                                                    if (linearLayout4 != null) {
                                                                                        i = R.id.ll_scroll;
                                                                                        NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(view, R.id.ll_scroll);
                                                                                        if (nestedScrollView != null) {
                                                                                            i = R.id.ll_token;
                                                                                            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.ll_token);
                                                                                            if (constraintLayout2 != null) {
                                                                                                i = R.id.rl_scam;
                                                                                                ItemWarningTagView itemWarningTagView = (ItemWarningTagView) ViewBindings.findChildViewById(view, R.id.rl_scam);
                                                                                                if (itemWarningTagView != null) {
                                                                                                    LinearLayout linearLayout5 = (LinearLayout) view;
                                                                                                    i = R.id.split_line;
                                                                                                    View findChildViewById = ViewBindings.findChildViewById(view, R.id.split_line);
                                                                                                    if (findChildViewById != null) {
                                                                                                        i = R.id.split_line_note;
                                                                                                        View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.split_line_note);
                                                                                                        if (findChildViewById2 != null) {
                                                                                                            i = R.id.tv_amount_info;
                                                                                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_amount_info);
                                                                                                            if (textView2 != null) {
                                                                                                                i = R.id.tv_amount_title;
                                                                                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_amount_title);
                                                                                                                if (textView3 != null) {
                                                                                                                    i = R.id.tv_balance;
                                                                                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_balance);
                                                                                                                    if (textView4 != null) {
                                                                                                                        i = R.id.tv_max;
                                                                                                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_max);
                                                                                                                        if (textView5 != null) {
                                                                                                                            i = R.id.tv_multiTip;
                                                                                                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_multiTip);
                                                                                                                            if (textView6 != null) {
                                                                                                                                i = R.id.tv_name;
                                                                                                                                TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name);
                                                                                                                                if (textView7 != null) {
                                                                                                                                    i = R.id.tv_nft_id;
                                                                                                                                    TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_nft_id);
                                                                                                                                    if (textView8 != null) {
                                                                                                                                        i = R.id.tv_nft_name;
                                                                                                                                        TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_nft_name);
                                                                                                                                        if (textView9 != null) {
                                                                                                                                            i = R.id.tv_note_count;
                                                                                                                                            TextView textView10 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_note_count);
                                                                                                                                            if (textView10 != null) {
                                                                                                                                                i = R.id.tv_note_tip;
                                                                                                                                                ErrorView errorView = (ErrorView) ViewBindings.findChildViewById(view, R.id.tv_note_tip);
                                                                                                                                                if (errorView != null) {
                                                                                                                                                    i = R.id.tv_symbol;
                                                                                                                                                    TextView textView11 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_symbol);
                                                                                                                                                    if (textView11 != null) {
                                                                                                                                                        i = R.id.tv_value;
                                                                                                                                                        TextView textView12 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_value);
                                                                                                                                                        if (textView12 != null) {
                                                                                                                                                            return new AcTransferSelectTokenBinding(linearLayout5, textView, button, currencyEditText, editText, imageView, imageView2, simpleDraweeView, imageView3, imageView4, imageView5, simpleDraweeView2, linearLayout, relativeLayout, constraintLayout, linearLayout2, errorBottomLayout, errorBottomLayout2, errorBottomLayout3, linearLayout3, linearLayout4, nestedScrollView, constraintLayout2, itemWarningTagView, linearLayout5, findChildViewById, findChildViewById2, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, errorView, textView11, textView12);
                                                                                                                                                        }
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
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
