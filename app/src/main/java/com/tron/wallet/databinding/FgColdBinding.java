package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class FgColdBinding implements ViewBinding {
    public final Button btCopy;
    public final RelativeLayout flOfflineSign;
    public final LinearLayout flReceive;
    public final ImageView ivOfflineSign;
    public final ImageView ivOfflineSignQr;
    public final ImageView ivOfflineSignShasta;
    public final ImageView ivQr;
    public final ImageView ivReceive;
    public final ImageView ivReceiveShasta;
    public final ImageView ivShieldIcon;
    public final ImageView ivWalletManager;
    public final LlBackupTipBinding llBackTip;
    public final LlColdTipBinding llCodeTip;
    public final LlNonetTipBinding llNonetTip;
    public final LinearLayout llOfflineSign;
    public final LinearLayout llReceive;
    public final RelativeLayout rlHeader;
    public final RelativeLayout rlHeaderInner;
    private final RelativeLayout rootView;
    public final TextView tvAddress;
    public final TextView tvDesc;
    public final TextView tvOfflineSign;
    public final Button tvOfflineSignDesc;
    public final TextView tvReceive;
    public final TextView tvTitle;
    public final TextView tvWalletname;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FgColdBinding(RelativeLayout relativeLayout, Button button, RelativeLayout relativeLayout2, LinearLayout linearLayout, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView imageView6, ImageView imageView7, ImageView imageView8, LlBackupTipBinding llBackupTipBinding, LlColdTipBinding llColdTipBinding, LlNonetTipBinding llNonetTipBinding, LinearLayout linearLayout2, LinearLayout linearLayout3, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, TextView textView, TextView textView2, TextView textView3, Button button2, TextView textView4, TextView textView5, TextView textView6) {
        this.rootView = relativeLayout;
        this.btCopy = button;
        this.flOfflineSign = relativeLayout2;
        this.flReceive = linearLayout;
        this.ivOfflineSign = imageView;
        this.ivOfflineSignQr = imageView2;
        this.ivOfflineSignShasta = imageView3;
        this.ivQr = imageView4;
        this.ivReceive = imageView5;
        this.ivReceiveShasta = imageView6;
        this.ivShieldIcon = imageView7;
        this.ivWalletManager = imageView8;
        this.llBackTip = llBackupTipBinding;
        this.llCodeTip = llColdTipBinding;
        this.llNonetTip = llNonetTipBinding;
        this.llOfflineSign = linearLayout2;
        this.llReceive = linearLayout3;
        this.rlHeader = relativeLayout3;
        this.rlHeaderInner = relativeLayout4;
        this.tvAddress = textView;
        this.tvDesc = textView2;
        this.tvOfflineSign = textView3;
        this.tvOfflineSignDesc = button2;
        this.tvReceive = textView4;
        this.tvTitle = textView5;
        this.tvWalletname = textView6;
    }

    public static FgColdBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FgColdBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fg_cold, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FgColdBinding bind(View view) {
        int i = R.id.bt_copy;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_copy);
        if (button != null) {
            i = R.id.fl_offline_sign;
            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.fl_offline_sign);
            if (relativeLayout != null) {
                i = R.id.fl_receive;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.fl_receive);
                if (linearLayout != null) {
                    i = R.id.iv_offline_sign;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_offline_sign);
                    if (imageView != null) {
                        i = R.id.iv_offline_sign_qr;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_offline_sign_qr);
                        if (imageView2 != null) {
                            i = R.id.iv_offline_sign_shasta;
                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_offline_sign_shasta);
                            if (imageView3 != null) {
                                i = R.id.iv_qr;
                                ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_qr);
                                if (imageView4 != null) {
                                    i = R.id.iv_receive;
                                    ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_receive);
                                    if (imageView5 != null) {
                                        i = R.id.iv_receive_shasta;
                                        ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_receive_shasta);
                                        if (imageView6 != null) {
                                            i = R.id.iv_shield_icon;
                                            ImageView imageView7 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_shield_icon);
                                            if (imageView7 != null) {
                                                i = R.id.iv_wallet_manager;
                                                ImageView imageView8 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_wallet_manager);
                                                if (imageView8 != null) {
                                                    i = R.id.ll_back_tip;
                                                    View findChildViewById = ViewBindings.findChildViewById(view, R.id.ll_back_tip);
                                                    if (findChildViewById != null) {
                                                        LlBackupTipBinding bind = LlBackupTipBinding.bind(findChildViewById);
                                                        i = R.id.ll_code_tip;
                                                        View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.ll_code_tip);
                                                        if (findChildViewById2 != null) {
                                                            LlColdTipBinding bind2 = LlColdTipBinding.bind(findChildViewById2);
                                                            i = R.id.ll_nonet_tip;
                                                            View findChildViewById3 = ViewBindings.findChildViewById(view, R.id.ll_nonet_tip);
                                                            if (findChildViewById3 != null) {
                                                                LlNonetTipBinding bind3 = LlNonetTipBinding.bind(findChildViewById3);
                                                                i = R.id.ll_offline_sign;
                                                                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_offline_sign);
                                                                if (linearLayout2 != null) {
                                                                    i = R.id.ll_receive;
                                                                    LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_receive);
                                                                    if (linearLayout3 != null) {
                                                                        i = R.id.rl_header;
                                                                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_header);
                                                                        if (relativeLayout2 != null) {
                                                                            i = R.id.rl_header_inner;
                                                                            RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_header_inner);
                                                                            if (relativeLayout3 != null) {
                                                                                i = R.id.tv_address;
                                                                                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address);
                                                                                if (textView != null) {
                                                                                    i = R.id.tv_desc;
                                                                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_desc);
                                                                                    if (textView2 != null) {
                                                                                        i = R.id.tv_offline_sign;
                                                                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_offline_sign);
                                                                                        if (textView3 != null) {
                                                                                            i = R.id.tv_offline_sign_desc;
                                                                                            Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.tv_offline_sign_desc);
                                                                                            if (button2 != null) {
                                                                                                i = R.id.tv_receive;
                                                                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_receive);
                                                                                                if (textView4 != null) {
                                                                                                    i = R.id.tv_title;
                                                                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                                                                                                    if (textView5 != null) {
                                                                                                        i = R.id.tv_walletname;
                                                                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_walletname);
                                                                                                        if (textView6 != null) {
                                                                                                            return new FgColdBinding((RelativeLayout) view, button, relativeLayout, linearLayout, imageView, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, bind, bind2, bind3, linearLayout2, linearLayout3, relativeLayout2, relativeLayout3, textView, textView2, textView3, button2, textView4, textView5, textView6);
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
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
