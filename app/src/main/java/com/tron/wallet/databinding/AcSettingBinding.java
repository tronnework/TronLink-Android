package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.SwitchButton;
import com.tronlinkpro.wallet.R;
public final class AcSettingBinding implements ViewBinding {
    public final ImageView back15;
    public final ImageView back3;
    public final ImageView back4;
    public final ImageView back5;
    public final ImageView back6;
    public final RelativeLayout changeHd;
    public final RelativeLayout console;
    public final RelativeLayout dappConnection;
    public final ImageView ivArrow;
    public final ImageView ivDotTip;
    public final RelativeLayout language;
    public final LinearLayout llNodeRoot;
    public final RelativeLayout messageNotification;
    public final RelativeLayout migrate;
    public final RelativeLayout money;
    public final RelativeLayout network;
    public final TextView networkSettingTitle;
    public final RelativeLayout node;
    public final RelativeLayout rlMultiImage;
    public final LinearLayout root;
    private final ScrollView rootView;
    public final RelativeLayout server;
    public final TextView serverName;
    public final SwitchButton switchButton;
    public final RelativeLayout switchVersion;
    public final TextView tvLanguage;
    public final TextView tvMoney;
    public final TextView tvNetworkName;
    public final TextView tvNode;
    public final TextView tvNodeSpeed;

    @Override
    public ScrollView getRoot() {
        return this.rootView;
    }

    private AcSettingBinding(ScrollView scrollView, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, ImageView imageView6, ImageView imageView7, RelativeLayout relativeLayout4, LinearLayout linearLayout, RelativeLayout relativeLayout5, RelativeLayout relativeLayout6, RelativeLayout relativeLayout7, RelativeLayout relativeLayout8, TextView textView, RelativeLayout relativeLayout9, RelativeLayout relativeLayout10, LinearLayout linearLayout2, RelativeLayout relativeLayout11, TextView textView2, SwitchButton switchButton, RelativeLayout relativeLayout12, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7) {
        this.rootView = scrollView;
        this.back15 = imageView;
        this.back3 = imageView2;
        this.back4 = imageView3;
        this.back5 = imageView4;
        this.back6 = imageView5;
        this.changeHd = relativeLayout;
        this.console = relativeLayout2;
        this.dappConnection = relativeLayout3;
        this.ivArrow = imageView6;
        this.ivDotTip = imageView7;
        this.language = relativeLayout4;
        this.llNodeRoot = linearLayout;
        this.messageNotification = relativeLayout5;
        this.migrate = relativeLayout6;
        this.money = relativeLayout7;
        this.network = relativeLayout8;
        this.networkSettingTitle = textView;
        this.node = relativeLayout9;
        this.rlMultiImage = relativeLayout10;
        this.root = linearLayout2;
        this.server = relativeLayout11;
        this.serverName = textView2;
        this.switchButton = switchButton;
        this.switchVersion = relativeLayout12;
        this.tvLanguage = textView3;
        this.tvMoney = textView4;
        this.tvNetworkName = textView5;
        this.tvNode = textView6;
        this.tvNodeSpeed = textView7;
    }

    public static AcSettingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcSettingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_setting, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcSettingBinding bind(View view) {
        int i = R.id.back15;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.back15);
        if (imageView != null) {
            i = R.id.back3;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.back3);
            if (imageView2 != null) {
                i = R.id.back4;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.back4);
                if (imageView3 != null) {
                    i = R.id.back5;
                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.back5);
                    if (imageView4 != null) {
                        i = R.id.back6;
                        ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, R.id.back6);
                        if (imageView5 != null) {
                            i = R.id.change_hd;
                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.change_hd);
                            if (relativeLayout != null) {
                                i = R.id.console;
                                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.console);
                                if (relativeLayout2 != null) {
                                    i = R.id.dapp_connection;
                                    RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.dapp_connection);
                                    if (relativeLayout3 != null) {
                                        i = R.id.iv_arrow;
                                        ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_arrow);
                                        if (imageView6 != null) {
                                            i = R.id.iv_dot_tip;
                                            ImageView imageView7 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_dot_tip);
                                            if (imageView7 != null) {
                                                i = R.id.language;
                                                RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.language);
                                                if (relativeLayout4 != null) {
                                                    i = R.id.ll_node_root;
                                                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_node_root);
                                                    if (linearLayout != null) {
                                                        i = R.id.message_notification;
                                                        RelativeLayout relativeLayout5 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.message_notification);
                                                        if (relativeLayout5 != null) {
                                                            i = R.id.migrate;
                                                            RelativeLayout relativeLayout6 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.migrate);
                                                            if (relativeLayout6 != null) {
                                                                i = R.id.money;
                                                                RelativeLayout relativeLayout7 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.money);
                                                                if (relativeLayout7 != null) {
                                                                    i = R.id.network;
                                                                    RelativeLayout relativeLayout8 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.network);
                                                                    if (relativeLayout8 != null) {
                                                                        i = R.id.network_setting_title;
                                                                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.network_setting_title);
                                                                        if (textView != null) {
                                                                            i = R.id.node;
                                                                            RelativeLayout relativeLayout9 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.node);
                                                                            if (relativeLayout9 != null) {
                                                                                i = R.id.rl_multi_image;
                                                                                RelativeLayout relativeLayout10 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_multi_image);
                                                                                if (relativeLayout10 != null) {
                                                                                    i = R.id.root;
                                                                                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.root);
                                                                                    if (linearLayout2 != null) {
                                                                                        i = R.id.server;
                                                                                        RelativeLayout relativeLayout11 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.server);
                                                                                        if (relativeLayout11 != null) {
                                                                                            i = R.id.server_name;
                                                                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.server_name);
                                                                                            if (textView2 != null) {
                                                                                                i = R.id.switch_button;
                                                                                                SwitchButton switchButton = (SwitchButton) ViewBindings.findChildViewById(view, R.id.switch_button);
                                                                                                if (switchButton != null) {
                                                                                                    i = R.id.switch_version;
                                                                                                    RelativeLayout relativeLayout12 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.switch_version);
                                                                                                    if (relativeLayout12 != null) {
                                                                                                        i = R.id.tv_language;
                                                                                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_language);
                                                                                                        if (textView3 != null) {
                                                                                                            i = R.id.tv_money;
                                                                                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_money);
                                                                                                            if (textView4 != null) {
                                                                                                                i = R.id.tv_network_name;
                                                                                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_network_name);
                                                                                                                if (textView5 != null) {
                                                                                                                    i = R.id.tv_node;
                                                                                                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_node);
                                                                                                                    if (textView6 != null) {
                                                                                                                        i = R.id.tv_node_speed;
                                                                                                                        TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_node_speed);
                                                                                                                        if (textView7 != null) {
                                                                                                                            return new AcSettingBinding((ScrollView) view, imageView, imageView2, imageView3, imageView4, imageView5, relativeLayout, relativeLayout2, relativeLayout3, imageView6, imageView7, relativeLayout4, linearLayout, relativeLayout5, relativeLayout6, relativeLayout7, relativeLayout8, textView, relativeLayout9, relativeLayout10, linearLayout2, relativeLayout11, textView2, switchButton, relativeLayout12, textView3, textView4, textView5, textView6, textView7);
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
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
