package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tron.wallet.common.components.CurrencyEditText;
import com.tron.wallet.common.components.ErrorEdiTextLayout;
import com.tron.wallet.common.components.TrimEditText;
import com.tronlinkpro.wallet.R;
public final class AcNewTransferBinding implements ViewBinding {
    public final ErrorEdiTextLayout eetAmout;
    public final ErrorEdiTextLayout eetReceiveAddress;
    public final ErrorEdiTextLayout eetSendAddress;
    public final TrimEditText etAddress;
    public final CurrencyEditText etCount;
    public final EditText etNote;
    public final TrimEditText etTransferAddress;
    public final FrameLayout flRecentAddresses;
    public final AppCompatImageView ivAddAddress;
    public final ImageView ivAsk;
    public final ImageView ivBa;
    public final ImageView ivDelete;
    public final ImageView ivNoteDelete;
    public final ImageView ivOneDelete;
    public final ImageView ivRa;
    public final ImageView ivRow;
    public final ImageView ivSa;
    public final ImageView ivScamNotice;
    public final ImageView ivSelectTag;
    public final ImageView ivSt;
    public final ImageView ivSt2;
    public final SimpleDraweeView ivTokenIcon;
    public final LinearLayout liAddAddress;
    public final LinearLayout llAdd;
    public final LinearLayout llBalanceAmout;
    public final LinearLayout llReceiveAddress;
    public final LinearLayout llSelectToken;
    public final LinearLayout llSendAddress;
    public final LinearLayout llShield;
    public final LinearLayout llTopFive;
    public final RelativeLayout llTopOne;
    public final RelativeLayout llTopThree;
    public final RelativeLayout llTopTr;
    public final LinearLayout llTopTwo;
    public final NestedScrollView nestScrollView;
    public final RecyclerView rcRecentAddressList;
    public final RelativeLayout rlAddressbookReceive;
    public final RelativeLayout rlAddressbookSend;
    public final RelativeLayout rlFee;
    public final RelativeLayout rlNote;
    public final RelativeLayout rlScam;
    public final RelativeLayout rlToken;
    private final RelativeLayout rootView;
    public final Button send;
    public final TextView shieldBalance;
    public final TextView singleLimit;
    public final TextView tv20No;
    public final TextView tvAddAddress;
    public final TextView tvAmountInfo;
    public final TextView tvAmountLine;
    public final TextView tvBalance;
    public final TextView tvFee;
    public final TextView tvId;
    public final TextView tvMax;
    public final TextView tvName;
    public final TextView tvNote;
    public final TextView tvScamNotice;
    public final TextView tvSl;
    public final TextView tvValue;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcNewTransferBinding(RelativeLayout relativeLayout, ErrorEdiTextLayout errorEdiTextLayout, ErrorEdiTextLayout errorEdiTextLayout2, ErrorEdiTextLayout errorEdiTextLayout3, TrimEditText trimEditText, CurrencyEditText currencyEditText, EditText editText, TrimEditText trimEditText2, FrameLayout frameLayout, AppCompatImageView appCompatImageView, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView imageView6, ImageView imageView7, ImageView imageView8, ImageView imageView9, ImageView imageView10, ImageView imageView11, ImageView imageView12, SimpleDraweeView simpleDraweeView, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, LinearLayout linearLayout5, LinearLayout linearLayout6, LinearLayout linearLayout7, LinearLayout linearLayout8, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, LinearLayout linearLayout9, NestedScrollView nestedScrollView, RecyclerView recyclerView, RelativeLayout relativeLayout5, RelativeLayout relativeLayout6, RelativeLayout relativeLayout7, RelativeLayout relativeLayout8, RelativeLayout relativeLayout9, RelativeLayout relativeLayout10, Button button, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14, TextView textView15) {
        this.rootView = relativeLayout;
        this.eetAmout = errorEdiTextLayout;
        this.eetReceiveAddress = errorEdiTextLayout2;
        this.eetSendAddress = errorEdiTextLayout3;
        this.etAddress = trimEditText;
        this.etCount = currencyEditText;
        this.etNote = editText;
        this.etTransferAddress = trimEditText2;
        this.flRecentAddresses = frameLayout;
        this.ivAddAddress = appCompatImageView;
        this.ivAsk = imageView;
        this.ivBa = imageView2;
        this.ivDelete = imageView3;
        this.ivNoteDelete = imageView4;
        this.ivOneDelete = imageView5;
        this.ivRa = imageView6;
        this.ivRow = imageView7;
        this.ivSa = imageView8;
        this.ivScamNotice = imageView9;
        this.ivSelectTag = imageView10;
        this.ivSt = imageView11;
        this.ivSt2 = imageView12;
        this.ivTokenIcon = simpleDraweeView;
        this.liAddAddress = linearLayout;
        this.llAdd = linearLayout2;
        this.llBalanceAmout = linearLayout3;
        this.llReceiveAddress = linearLayout4;
        this.llSelectToken = linearLayout5;
        this.llSendAddress = linearLayout6;
        this.llShield = linearLayout7;
        this.llTopFive = linearLayout8;
        this.llTopOne = relativeLayout2;
        this.llTopThree = relativeLayout3;
        this.llTopTr = relativeLayout4;
        this.llTopTwo = linearLayout9;
        this.nestScrollView = nestedScrollView;
        this.rcRecentAddressList = recyclerView;
        this.rlAddressbookReceive = relativeLayout5;
        this.rlAddressbookSend = relativeLayout6;
        this.rlFee = relativeLayout7;
        this.rlNote = relativeLayout8;
        this.rlScam = relativeLayout9;
        this.rlToken = relativeLayout10;
        this.send = button;
        this.shieldBalance = textView;
        this.singleLimit = textView2;
        this.tv20No = textView3;
        this.tvAddAddress = textView4;
        this.tvAmountInfo = textView5;
        this.tvAmountLine = textView6;
        this.tvBalance = textView7;
        this.tvFee = textView8;
        this.tvId = textView9;
        this.tvMax = textView10;
        this.tvName = textView11;
        this.tvNote = textView12;
        this.tvScamNotice = textView13;
        this.tvSl = textView14;
        this.tvValue = textView15;
    }

    public static AcNewTransferBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcNewTransferBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_new_transfer, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcNewTransferBinding bind(View view) {
        int i = R.id.eet_amout;
        ErrorEdiTextLayout errorEdiTextLayout = (ErrorEdiTextLayout) ViewBindings.findChildViewById(view, R.id.eet_amout);
        if (errorEdiTextLayout != null) {
            i = R.id.eet_receive_address;
            ErrorEdiTextLayout errorEdiTextLayout2 = (ErrorEdiTextLayout) ViewBindings.findChildViewById(view, R.id.eet_receive_address);
            if (errorEdiTextLayout2 != null) {
                i = R.id.eet_send_address;
                ErrorEdiTextLayout errorEdiTextLayout3 = (ErrorEdiTextLayout) ViewBindings.findChildViewById(view, R.id.eet_send_address);
                if (errorEdiTextLayout3 != null) {
                    i = R.id.et_address;
                    TrimEditText trimEditText = (TrimEditText) ViewBindings.findChildViewById(view, R.id.et_address);
                    if (trimEditText != null) {
                        i = R.id.et_count;
                        CurrencyEditText currencyEditText = (CurrencyEditText) ViewBindings.findChildViewById(view, R.id.et_count);
                        if (currencyEditText != null) {
                            i = R.id.et_note;
                            EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.et_note);
                            if (editText != null) {
                                i = R.id.et_transfer_address;
                                TrimEditText trimEditText2 = (TrimEditText) ViewBindings.findChildViewById(view, R.id.et_transfer_address);
                                if (trimEditText2 != null) {
                                    i = R.id.fl_recent_addresses;
                                    FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.fl_recent_addresses);
                                    if (frameLayout != null) {
                                        i = R.id.iv_add_address;
                                        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.iv_add_address);
                                        if (appCompatImageView != null) {
                                            i = R.id.iv_ask;
                                            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_ask);
                                            if (imageView != null) {
                                                i = R.id.iv_ba;
                                                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_ba);
                                                if (imageView2 != null) {
                                                    i = R.id.iv_delete;
                                                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_delete);
                                                    if (imageView3 != null) {
                                                        i = R.id.iv_note_delete;
                                                        ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_note_delete);
                                                        if (imageView4 != null) {
                                                            i = R.id.iv_one_delete;
                                                            ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_one_delete);
                                                            if (imageView5 != null) {
                                                                i = R.id.iv_ra;
                                                                ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_ra);
                                                                if (imageView6 != null) {
                                                                    i = R.id.iv_row;
                                                                    ImageView imageView7 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_row);
                                                                    if (imageView7 != null) {
                                                                        i = R.id.iv_sa;
                                                                        ImageView imageView8 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_sa);
                                                                        if (imageView8 != null) {
                                                                            i = R.id.iv_scam_notice;
                                                                            ImageView imageView9 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_scam_notice);
                                                                            if (imageView9 != null) {
                                                                                i = R.id.iv_select_tag;
                                                                                ImageView imageView10 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_select_tag);
                                                                                if (imageView10 != null) {
                                                                                    i = R.id.iv_st;
                                                                                    ImageView imageView11 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_st);
                                                                                    if (imageView11 != null) {
                                                                                        i = R.id.iv_st2;
                                                                                        ImageView imageView12 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_st2);
                                                                                        if (imageView12 != null) {
                                                                                            i = R.id.iv_token_icon;
                                                                                            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.iv_token_icon);
                                                                                            if (simpleDraweeView != null) {
                                                                                                i = R.id.li_add_address;
                                                                                                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_add_address);
                                                                                                if (linearLayout != null) {
                                                                                                    i = R.id.ll_add;
                                                                                                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_add);
                                                                                                    if (linearLayout2 != null) {
                                                                                                        i = R.id.ll_balance_amout;
                                                                                                        LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_balance_amout);
                                                                                                        if (linearLayout3 != null) {
                                                                                                            i = R.id.ll_receive_address;
                                                                                                            LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_receive_address);
                                                                                                            if (linearLayout4 != null) {
                                                                                                                i = R.id.ll_select_token;
                                                                                                                LinearLayout linearLayout5 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_select_token);
                                                                                                                if (linearLayout5 != null) {
                                                                                                                    i = R.id.ll_send_address;
                                                                                                                    LinearLayout linearLayout6 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_send_address);
                                                                                                                    if (linearLayout6 != null) {
                                                                                                                        i = R.id.ll_shield;
                                                                                                                        LinearLayout linearLayout7 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_shield);
                                                                                                                        if (linearLayout7 != null) {
                                                                                                                            i = R.id.ll_top_five;
                                                                                                                            LinearLayout linearLayout8 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_top_five);
                                                                                                                            if (linearLayout8 != null) {
                                                                                                                                i = R.id.ll_top_one;
                                                                                                                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_top_one);
                                                                                                                                if (relativeLayout != null) {
                                                                                                                                    i = R.id.ll_top_three;
                                                                                                                                    RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_top_three);
                                                                                                                                    if (relativeLayout2 != null) {
                                                                                                                                        i = R.id.ll_top_tr;
                                                                                                                                        RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_top_tr);
                                                                                                                                        if (relativeLayout3 != null) {
                                                                                                                                            i = R.id.ll_top_two;
                                                                                                                                            LinearLayout linearLayout9 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_top_two);
                                                                                                                                            if (linearLayout9 != null) {
                                                                                                                                                i = R.id.nest_scroll_view;
                                                                                                                                                NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(view, R.id.nest_scroll_view);
                                                                                                                                                if (nestedScrollView != null) {
                                                                                                                                                    i = R.id.rc_recent_address_list;
                                                                                                                                                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rc_recent_address_list);
                                                                                                                                                    if (recyclerView != null) {
                                                                                                                                                        i = R.id.rl_addressbook_receive;
                                                                                                                                                        RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_addressbook_receive);
                                                                                                                                                        if (relativeLayout4 != null) {
                                                                                                                                                            i = R.id.rl_addressbook_send;
                                                                                                                                                            RelativeLayout relativeLayout5 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_addressbook_send);
                                                                                                                                                            if (relativeLayout5 != null) {
                                                                                                                                                                i = R.id.rl_fee;
                                                                                                                                                                RelativeLayout relativeLayout6 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_fee);
                                                                                                                                                                if (relativeLayout6 != null) {
                                                                                                                                                                    i = R.id.rl_note;
                                                                                                                                                                    RelativeLayout relativeLayout7 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_note);
                                                                                                                                                                    if (relativeLayout7 != null) {
                                                                                                                                                                        i = R.id.rl_scam;
                                                                                                                                                                        RelativeLayout relativeLayout8 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_scam);
                                                                                                                                                                        if (relativeLayout8 != null) {
                                                                                                                                                                            i = R.id.rl_token;
                                                                                                                                                                            RelativeLayout relativeLayout9 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_token);
                                                                                                                                                                            if (relativeLayout9 != null) {
                                                                                                                                                                                i = R.id.send;
                                                                                                                                                                                Button button = (Button) ViewBindings.findChildViewById(view, R.id.send);
                                                                                                                                                                                if (button != null) {
                                                                                                                                                                                    i = R.id.shield_balance;
                                                                                                                                                                                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.shield_balance);
                                                                                                                                                                                    if (textView != null) {
                                                                                                                                                                                        i = R.id.single_limit;
                                                                                                                                                                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.single_limit);
                                                                                                                                                                                        if (textView2 != null) {
                                                                                                                                                                                            i = R.id.tv_20_no;
                                                                                                                                                                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_20_no);
                                                                                                                                                                                            if (textView3 != null) {
                                                                                                                                                                                                i = R.id.tv_add_address;
                                                                                                                                                                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_add_address);
                                                                                                                                                                                                if (textView4 != null) {
                                                                                                                                                                                                    i = R.id.tv_amount_info;
                                                                                                                                                                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_amount_info);
                                                                                                                                                                                                    if (textView5 != null) {
                                                                                                                                                                                                        i = R.id.tv_amount_line;
                                                                                                                                                                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_amount_line);
                                                                                                                                                                                                        if (textView6 != null) {
                                                                                                                                                                                                            i = R.id.tv_balance;
                                                                                                                                                                                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_balance);
                                                                                                                                                                                                            if (textView7 != null) {
                                                                                                                                                                                                                i = R.id.tv_fee;
                                                                                                                                                                                                                TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_fee);
                                                                                                                                                                                                                if (textView8 != null) {
                                                                                                                                                                                                                    i = R.id.tv_id;
                                                                                                                                                                                                                    TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_id);
                                                                                                                                                                                                                    if (textView9 != null) {
                                                                                                                                                                                                                        i = R.id.tv_max;
                                                                                                                                                                                                                        TextView textView10 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_max);
                                                                                                                                                                                                                        if (textView10 != null) {
                                                                                                                                                                                                                            i = R.id.tv_name;
                                                                                                                                                                                                                            TextView textView11 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name);
                                                                                                                                                                                                                            if (textView11 != null) {
                                                                                                                                                                                                                                i = R.id.tv_note;
                                                                                                                                                                                                                                TextView textView12 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_note);
                                                                                                                                                                                                                                if (textView12 != null) {
                                                                                                                                                                                                                                    i = R.id.tv_scam_notice;
                                                                                                                                                                                                                                    TextView textView13 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_scam_notice);
                                                                                                                                                                                                                                    if (textView13 != null) {
                                                                                                                                                                                                                                        i = R.id.tv_sl;
                                                                                                                                                                                                                                        TextView textView14 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_sl);
                                                                                                                                                                                                                                        if (textView14 != null) {
                                                                                                                                                                                                                                            i = R.id.tv_value;
                                                                                                                                                                                                                                            TextView textView15 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_value);
                                                                                                                                                                                                                                            if (textView15 != null) {
                                                                                                                                                                                                                                                return new AcNewTransferBinding((RelativeLayout) view, errorEdiTextLayout, errorEdiTextLayout2, errorEdiTextLayout3, trimEditText, currencyEditText, editText, trimEditText2, frameLayout, appCompatImageView, imageView, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9, imageView10, imageView11, imageView12, simpleDraweeView, linearLayout, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6, linearLayout7, linearLayout8, relativeLayout, relativeLayout2, relativeLayout3, linearLayout9, nestedScrollView, recyclerView, relativeLayout4, relativeLayout5, relativeLayout6, relativeLayout7, relativeLayout8, relativeLayout9, button, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14, textView15);
                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                }
                                                                                                                                                                                                                            }
                                                                                                                                                                                                                        }
                                                                                                                                                                                                                    }
                                                                                                                                                                                                                }
                                                                                                                                                                                                            }
                                                                                                                                                                                                        }
                                                                                                                                                                                                    }
                                                                                                                                                                                                }
                                                                                                                                                                                            }
                                                                                                                                                                                        }
                                                                                                                                                                                    }
                                                                                                                                                                                }
                                                                                                                                                                            }
                                                                                                                                                                        }
                                                                                                                                                                    }
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                        }
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
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
