package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemNodeBinding implements ViewBinding {
    public final ConstraintLayout constraintLayout;
    public final TextView customLatency;
    public final TextView customLatencyDesc;
    public final TextView customNode;
    public final ImageView ivNodeEdit;
    public final ImageView ivNodeSelected;
    public final AppCompatImageView latencySpeedDot;
    public final TextView nodeIp;
    private final ConstraintLayout rootView;
    public final TextView tvNodePort;
    public final TextView tvNodePortDesc;
    public final TextView tvNodeTimeout;

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    private ItemNodeBinding(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, TextView textView, TextView textView2, TextView textView3, ImageView imageView, ImageView imageView2, AppCompatImageView appCompatImageView, TextView textView4, TextView textView5, TextView textView6, TextView textView7) {
        this.rootView = constraintLayout;
        this.constraintLayout = constraintLayout2;
        this.customLatency = textView;
        this.customLatencyDesc = textView2;
        this.customNode = textView3;
        this.ivNodeEdit = imageView;
        this.ivNodeSelected = imageView2;
        this.latencySpeedDot = appCompatImageView;
        this.nodeIp = textView4;
        this.tvNodePort = textView5;
        this.tvNodePortDesc = textView6;
        this.tvNodeTimeout = textView7;
    }

    public static ItemNodeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemNodeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_node, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemNodeBinding bind(View view) {
        ConstraintLayout constraintLayout = (ConstraintLayout) view;
        int i = R.id.custom_latency;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.custom_latency);
        if (textView != null) {
            i = R.id.custom_latency_desc;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.custom_latency_desc);
            if (textView2 != null) {
                i = R.id.custom_node;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.custom_node);
                if (textView3 != null) {
                    i = R.id.iv_node_edit;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_node_edit);
                    if (imageView != null) {
                        i = R.id.iv_node_selected;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_node_selected);
                        if (imageView2 != null) {
                            i = R.id.latency_speed_dot;
                            AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.latency_speed_dot);
                            if (appCompatImageView != null) {
                                i = R.id.node_ip;
                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.node_ip);
                                if (textView4 != null) {
                                    i = R.id.tv_node_port;
                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_node_port);
                                    if (textView5 != null) {
                                        i = R.id.tv_node_port_desc;
                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_node_port_desc);
                                        if (textView6 != null) {
                                            i = R.id.tv_node_timeout;
                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_node_timeout);
                                            if (textView7 != null) {
                                                return new ItemNodeBinding(constraintLayout, constraintLayout, textView, textView2, textView3, imageView, imageView2, appCompatImageView, textView4, textView5, textView6, textView7);
                                            }
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
