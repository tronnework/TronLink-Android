package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class AcSelectwalletBinding implements ViewBinding {
    public final RelativeLayout headerLayout;
    public final ImageView ivAdd;
    public final ImageView ivBack;
    public final ImageView ivColdHard;
    public final ImageView ivNormal;
    public final ImageView ivRecently;
    public final ImageView ivSort;
    public final ImageView ivWatch;
    public final ImageView ivWatchCold;
    public final LinearLayout llAction;
    public final RecyclerView rcList;
    private final RelativeLayout rootView;
    public final TextView tvAdd;
    public final TextView tvMainTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcSelectwalletBinding(RelativeLayout relativeLayout, RelativeLayout relativeLayout2, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView imageView6, ImageView imageView7, ImageView imageView8, LinearLayout linearLayout, RecyclerView recyclerView, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.headerLayout = relativeLayout2;
        this.ivAdd = imageView;
        this.ivBack = imageView2;
        this.ivColdHard = imageView3;
        this.ivNormal = imageView4;
        this.ivRecently = imageView5;
        this.ivSort = imageView6;
        this.ivWatch = imageView7;
        this.ivWatchCold = imageView8;
        this.llAction = linearLayout;
        this.rcList = recyclerView;
        this.tvAdd = textView;
        this.tvMainTitle = textView2;
    }

    public static AcSelectwalletBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcSelectwalletBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_selectwallet, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcSelectwalletBinding bind(View view) {
        int i = R.id.header_layout;
        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.header_layout);
        if (relativeLayout != null) {
            i = R.id.iv_add;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_add);
            if (imageView != null) {
                i = R.id.iv_back;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_back);
                if (imageView2 != null) {
                    i = R.id.iv_cold_hard;
                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_cold_hard);
                    if (imageView3 != null) {
                        i = R.id.iv_normal;
                        ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_normal);
                        if (imageView4 != null) {
                            i = R.id.iv_recently;
                            ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_recently);
                            if (imageView5 != null) {
                                i = R.id.iv_sort;
                                ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_sort);
                                if (imageView6 != null) {
                                    i = R.id.iv_watch;
                                    ImageView imageView7 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_watch);
                                    if (imageView7 != null) {
                                        i = R.id.iv_watch_cold;
                                        ImageView imageView8 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_watch_cold);
                                        if (imageView8 != null) {
                                            i = R.id.ll_action;
                                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_action);
                                            if (linearLayout != null) {
                                                i = R.id.rc_list;
                                                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rc_list);
                                                if (recyclerView != null) {
                                                    i = R.id.tv_add;
                                                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_add);
                                                    if (textView != null) {
                                                        i = R.id.tv_main_title;
                                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_main_title);
                                                        if (textView2 != null) {
                                                            return new AcSelectwalletBinding((RelativeLayout) view, relativeLayout, imageView, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, linearLayout, recyclerView, textView, textView2);
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
