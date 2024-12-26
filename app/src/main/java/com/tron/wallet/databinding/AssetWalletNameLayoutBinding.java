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
public final class AssetWalletNameLayoutBinding implements ViewBinding {
    public final ImageView ivError;
    public final ImageView ivNodeError;
    public final ImageView ivServerNotice;
    public final ImageView ivWalletManager;
    public final ImageView ivWalletScan;
    public final ImageView ivWarning;
    public final LinearLayout llWalletname;
    public final RelativeLayout netLayout;
    public final RelativeLayout rlBlockSync;
    public final RelativeLayout rlNetNotice;
    public final RelativeLayout rlNodeNotice;
    public final RelativeLayout rlServerNotice;
    private final RelativeLayout rootView;
    public final TextView tvBlockAmount;
    public final TextView tvBlockDivider;
    public final TextView tvBlocking;
    public final TextView tvChainName;
    public final TextView tvCurrentBlock;
    public final TextView tvSwitchNode;
    public final TextView tvSwitchServer;
    public final TextView tvWalletname;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AssetWalletNameLayoutBinding(RelativeLayout relativeLayout, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView imageView6, LinearLayout linearLayout, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, RelativeLayout relativeLayout5, RelativeLayout relativeLayout6, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8) {
        this.rootView = relativeLayout;
        this.ivError = imageView;
        this.ivNodeError = imageView2;
        this.ivServerNotice = imageView3;
        this.ivWalletManager = imageView4;
        this.ivWalletScan = imageView5;
        this.ivWarning = imageView6;
        this.llWalletname = linearLayout;
        this.netLayout = relativeLayout2;
        this.rlBlockSync = relativeLayout3;
        this.rlNetNotice = relativeLayout4;
        this.rlNodeNotice = relativeLayout5;
        this.rlServerNotice = relativeLayout6;
        this.tvBlockAmount = textView;
        this.tvBlockDivider = textView2;
        this.tvBlocking = textView3;
        this.tvChainName = textView4;
        this.tvCurrentBlock = textView5;
        this.tvSwitchNode = textView6;
        this.tvSwitchServer = textView7;
        this.tvWalletname = textView8;
    }

    public static AssetWalletNameLayoutBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AssetWalletNameLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.asset_wallet_name_layout, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AssetWalletNameLayoutBinding bind(View view) {
        int i = R.id.iv_error;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_error);
        if (imageView != null) {
            i = R.id.iv_node_error;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_node_error);
            if (imageView2 != null) {
                i = R.id.iv_server_notice;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_server_notice);
                if (imageView3 != null) {
                    i = R.id.iv_wallet_manager;
                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_wallet_manager);
                    if (imageView4 != null) {
                        i = R.id.iv_wallet_scan;
                        ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_wallet_scan);
                        if (imageView5 != null) {
                            i = R.id.iv_warning;
                            ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_warning);
                            if (imageView6 != null) {
                                i = R.id.ll_walletname;
                                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_walletname);
                                if (linearLayout != null) {
                                    i = R.id.net_layout;
                                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.net_layout);
                                    if (relativeLayout != null) {
                                        i = R.id.rl_block_sync;
                                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_block_sync);
                                        if (relativeLayout2 != null) {
                                            i = R.id.rl_net_notice;
                                            RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_net_notice);
                                            if (relativeLayout3 != null) {
                                                i = R.id.rl_node_notice;
                                                RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_node_notice);
                                                if (relativeLayout4 != null) {
                                                    i = R.id.rl_server_notice;
                                                    RelativeLayout relativeLayout5 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_server_notice);
                                                    if (relativeLayout5 != null) {
                                                        i = R.id.tv_block_amount;
                                                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_block_amount);
                                                        if (textView != null) {
                                                            i = R.id.tv_block_divider;
                                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_block_divider);
                                                            if (textView2 != null) {
                                                                i = R.id.tv_blocking;
                                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_blocking);
                                                                if (textView3 != null) {
                                                                    i = R.id.tv_chain_name;
                                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_chain_name);
                                                                    if (textView4 != null) {
                                                                        i = R.id.tv_current_block;
                                                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_current_block);
                                                                        if (textView5 != null) {
                                                                            i = R.id.tv_switch_node;
                                                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_switch_node);
                                                                            if (textView6 != null) {
                                                                                i = R.id.tv_switch_server;
                                                                                TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_switch_server);
                                                                                if (textView7 != null) {
                                                                                    i = R.id.tv_walletname;
                                                                                    TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_walletname);
                                                                                    if (textView8 != null) {
                                                                                        return new AssetWalletNameLayoutBinding((RelativeLayout) view, imageView, imageView2, imageView3, imageView4, imageView5, imageView6, linearLayout, relativeLayout, relativeLayout2, relativeLayout3, relativeLayout4, relativeLayout5, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8);
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
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
