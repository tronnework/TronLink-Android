package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemUpgradeHdBinding implements ViewBinding {
    public final TextView assetsTitle;
    public final ImageView ivArrow;
    public final ImageView ivBg;
    public final ImageView ivSelectStatus;
    public final View line;
    public final View mask;
    public final View rcBgView;
    public final RecyclerView rcInnerContent;
    public final TextView relatedTitle;
    public final RelativeLayout rlArrow;
    public final RelativeLayout root;
    private final RelativeLayout rootView;
    public final RelativeLayout top;
    public final TextView tvAssets;
    public final TextView tvName;
    public final TextView tvRelated;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemUpgradeHdBinding(RelativeLayout relativeLayout, TextView textView, ImageView imageView, ImageView imageView2, ImageView imageView3, View view, View view2, View view3, RecyclerView recyclerView, TextView textView2, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, TextView textView3, TextView textView4, TextView textView5) {
        this.rootView = relativeLayout;
        this.assetsTitle = textView;
        this.ivArrow = imageView;
        this.ivBg = imageView2;
        this.ivSelectStatus = imageView3;
        this.line = view;
        this.mask = view2;
        this.rcBgView = view3;
        this.rcInnerContent = recyclerView;
        this.relatedTitle = textView2;
        this.rlArrow = relativeLayout2;
        this.root = relativeLayout3;
        this.top = relativeLayout4;
        this.tvAssets = textView3;
        this.tvName = textView4;
        this.tvRelated = textView5;
    }

    public static ItemUpgradeHdBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemUpgradeHdBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_upgrade_hd, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemUpgradeHdBinding bind(View view) {
        int i = R.id.assets_title;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.assets_title);
        if (textView != null) {
            i = R.id.iv_arrow;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_arrow);
            if (imageView != null) {
                i = R.id.iv_bg;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_bg);
                if (imageView2 != null) {
                    i = R.id.iv_select_status;
                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_select_status);
                    if (imageView3 != null) {
                        i = R.id.line;
                        View findChildViewById = ViewBindings.findChildViewById(view, R.id.line);
                        if (findChildViewById != null) {
                            i = R.id.mask;
                            View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.mask);
                            if (findChildViewById2 != null) {
                                i = R.id.rc_bg_view;
                                View findChildViewById3 = ViewBindings.findChildViewById(view, R.id.rc_bg_view);
                                if (findChildViewById3 != null) {
                                    i = R.id.rc_inner_content;
                                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rc_inner_content);
                                    if (recyclerView != null) {
                                        i = R.id.related_title;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.related_title);
                                        if (textView2 != null) {
                                            i = R.id.rl_arrow;
                                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_arrow);
                                            if (relativeLayout != null) {
                                                RelativeLayout relativeLayout2 = (RelativeLayout) view;
                                                i = R.id.top;
                                                RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.top);
                                                if (relativeLayout3 != null) {
                                                    i = R.id.tv_assets;
                                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_assets);
                                                    if (textView3 != null) {
                                                        i = R.id.tv_name;
                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name);
                                                        if (textView4 != null) {
                                                            i = R.id.tv_related;
                                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_related);
                                                            if (textView5 != null) {
                                                                return new ItemUpgradeHdBinding(relativeLayout2, textView, imageView, imageView2, imageView3, findChildViewById, findChildViewById2, findChildViewById3, recyclerView, textView2, relativeLayout, relativeLayout2, relativeLayout3, textView3, textView4, textView5);
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
