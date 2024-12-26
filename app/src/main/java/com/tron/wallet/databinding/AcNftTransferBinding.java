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
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tron.wallet.common.components.ErrorEdiTextLayout;
import com.tronlinkpro.wallet.R;
public final class AcNftTransferBinding implements ViewBinding {
    public final ErrorEdiTextLayout eetReceiveAddress;
    public final EditText etAddress;
    public final EditText etNote;
    public final FrameLayout flRecentAddresses;
    public final AppCompatImageView ivAddAddress;
    public final ImageView ivAddressScan;
    public final ImageView ivDelete;
    public final SimpleDraweeView ivLogo;
    public final ImageView ivRa;
    public final ImageView ivSt;
    public final ImageView ivSt2;
    public final LinearLayout liAddAddress;
    public final LinearLayout llAdd;
    public final LinearLayout llEnergy;
    public final LinearLayout llReceiveAddress;
    public final LinearLayout llSelectToken;
    public final LinearLayout llTopFour;
    public final RelativeLayout llTopOne;
    public final LinearLayout llTopTwo;
    public final RecyclerView rcRecentAddressList;
    public final RelativeLayout rlAddressbookReceive;
    public final RelativeLayout rlBg;
    public final RelativeLayout rlFee;
    public final RelativeLayout rlNote;
    public final RelativeLayout rlToken;
    public final RelativeLayout rlTopThree;
    private final RelativeLayout rootView;
    public final Button send;
    public final TextView tv20No;
    public final TextView tvAddAddress;
    public final TextView tvFee;
    public final TextView tvNftId;
    public final TextView tvNftName;
    public final TextView tvNoEnergy;
    public final TextView tvNote;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcNftTransferBinding(RelativeLayout relativeLayout, ErrorEdiTextLayout errorEdiTextLayout, EditText editText, EditText editText2, FrameLayout frameLayout, AppCompatImageView appCompatImageView, ImageView imageView, ImageView imageView2, SimpleDraweeView simpleDraweeView, ImageView imageView3, ImageView imageView4, ImageView imageView5, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, LinearLayout linearLayout5, LinearLayout linearLayout6, RelativeLayout relativeLayout2, LinearLayout linearLayout7, RecyclerView recyclerView, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, RelativeLayout relativeLayout5, RelativeLayout relativeLayout6, RelativeLayout relativeLayout7, RelativeLayout relativeLayout8, Button button, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7) {
        this.rootView = relativeLayout;
        this.eetReceiveAddress = errorEdiTextLayout;
        this.etAddress = editText;
        this.etNote = editText2;
        this.flRecentAddresses = frameLayout;
        this.ivAddAddress = appCompatImageView;
        this.ivAddressScan = imageView;
        this.ivDelete = imageView2;
        this.ivLogo = simpleDraweeView;
        this.ivRa = imageView3;
        this.ivSt = imageView4;
        this.ivSt2 = imageView5;
        this.liAddAddress = linearLayout;
        this.llAdd = linearLayout2;
        this.llEnergy = linearLayout3;
        this.llReceiveAddress = linearLayout4;
        this.llSelectToken = linearLayout5;
        this.llTopFour = linearLayout6;
        this.llTopOne = relativeLayout2;
        this.llTopTwo = linearLayout7;
        this.rcRecentAddressList = recyclerView;
        this.rlAddressbookReceive = relativeLayout3;
        this.rlBg = relativeLayout4;
        this.rlFee = relativeLayout5;
        this.rlNote = relativeLayout6;
        this.rlToken = relativeLayout7;
        this.rlTopThree = relativeLayout8;
        this.send = button;
        this.tv20No = textView;
        this.tvAddAddress = textView2;
        this.tvFee = textView3;
        this.tvNftId = textView4;
        this.tvNftName = textView5;
        this.tvNoEnergy = textView6;
        this.tvNote = textView7;
    }

    public static AcNftTransferBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcNftTransferBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_nft_transfer, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcNftTransferBinding bind(View view) {
        int i = R.id.eet_receive_address;
        ErrorEdiTextLayout errorEdiTextLayout = (ErrorEdiTextLayout) ViewBindings.findChildViewById(view, R.id.eet_receive_address);
        if (errorEdiTextLayout != null) {
            i = R.id.et_address;
            EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.et_address);
            if (editText != null) {
                i = R.id.et_note;
                EditText editText2 = (EditText) ViewBindings.findChildViewById(view, R.id.et_note);
                if (editText2 != null) {
                    i = R.id.fl_recent_addresses;
                    FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.fl_recent_addresses);
                    if (frameLayout != null) {
                        i = R.id.iv_add_address;
                        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.iv_add_address);
                        if (appCompatImageView != null) {
                            i = R.id.iv_address_scan;
                            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_address_scan);
                            if (imageView != null) {
                                i = R.id.iv_delete;
                                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_delete);
                                if (imageView2 != null) {
                                    i = R.id.iv_logo;
                                    SimpleDraweeView simpleDraweeView = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.iv_logo);
                                    if (simpleDraweeView != null) {
                                        i = R.id.iv_ra;
                                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_ra);
                                        if (imageView3 != null) {
                                            i = R.id.iv_st;
                                            ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_st);
                                            if (imageView4 != null) {
                                                i = R.id.iv_st2;
                                                ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_st2);
                                                if (imageView5 != null) {
                                                    i = R.id.li_add_address;
                                                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_add_address);
                                                    if (linearLayout != null) {
                                                        i = R.id.ll_add;
                                                        LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_add);
                                                        if (linearLayout2 != null) {
                                                            i = R.id.ll_energy;
                                                            LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_energy);
                                                            if (linearLayout3 != null) {
                                                                i = R.id.ll_receive_address;
                                                                LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_receive_address);
                                                                if (linearLayout4 != null) {
                                                                    i = R.id.ll_select_token;
                                                                    LinearLayout linearLayout5 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_select_token);
                                                                    if (linearLayout5 != null) {
                                                                        i = R.id.ll_top_four;
                                                                        LinearLayout linearLayout6 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_top_four);
                                                                        if (linearLayout6 != null) {
                                                                            i = R.id.ll_top_one;
                                                                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_top_one);
                                                                            if (relativeLayout != null) {
                                                                                i = R.id.ll_top_two;
                                                                                LinearLayout linearLayout7 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_top_two);
                                                                                if (linearLayout7 != null) {
                                                                                    i = R.id.rc_recent_address_list;
                                                                                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rc_recent_address_list);
                                                                                    if (recyclerView != null) {
                                                                                        i = R.id.rl_addressbook_receive;
                                                                                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_addressbook_receive);
                                                                                        if (relativeLayout2 != null) {
                                                                                            i = R.id.rl_bg;
                                                                                            RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_bg);
                                                                                            if (relativeLayout3 != null) {
                                                                                                i = R.id.rl_fee;
                                                                                                RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_fee);
                                                                                                if (relativeLayout4 != null) {
                                                                                                    i = R.id.rl_note;
                                                                                                    RelativeLayout relativeLayout5 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_note);
                                                                                                    if (relativeLayout5 != null) {
                                                                                                        i = R.id.rl_token;
                                                                                                        RelativeLayout relativeLayout6 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_token);
                                                                                                        if (relativeLayout6 != null) {
                                                                                                            i = R.id.rl_top_three;
                                                                                                            RelativeLayout relativeLayout7 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_top_three);
                                                                                                            if (relativeLayout7 != null) {
                                                                                                                i = R.id.send;
                                                                                                                Button button = (Button) ViewBindings.findChildViewById(view, R.id.send);
                                                                                                                if (button != null) {
                                                                                                                    i = R.id.tv_20_no;
                                                                                                                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_20_no);
                                                                                                                    if (textView != null) {
                                                                                                                        i = R.id.tv_add_address;
                                                                                                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_add_address);
                                                                                                                        if (textView2 != null) {
                                                                                                                            i = R.id.tv_fee;
                                                                                                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_fee);
                                                                                                                            if (textView3 != null) {
                                                                                                                                i = R.id.tv_nft_id;
                                                                                                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_nft_id);
                                                                                                                                if (textView4 != null) {
                                                                                                                                    i = R.id.tv_nft_name;
                                                                                                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_nft_name);
                                                                                                                                    if (textView5 != null) {
                                                                                                                                        i = R.id.tv_no_energy;
                                                                                                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_no_energy);
                                                                                                                                        if (textView6 != null) {
                                                                                                                                            i = R.id.tv_note;
                                                                                                                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_note);
                                                                                                                                            if (textView7 != null) {
                                                                                                                                                return new AcNftTransferBinding((RelativeLayout) view, errorEdiTextLayout, editText, editText2, frameLayout, appCompatImageView, imageView, imageView2, simpleDraweeView, imageView3, imageView4, imageView5, linearLayout, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6, relativeLayout, linearLayout7, recyclerView, relativeLayout2, relativeLayout3, relativeLayout4, relativeLayout5, relativeLayout6, relativeLayout7, button, textView, textView2, textView3, textView4, textView5, textView6, textView7);
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
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
