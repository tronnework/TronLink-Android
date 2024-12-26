package com.tron.tron_base.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.tron_base.R;
public final class HeaderBarBinding implements ViewBinding {
    public final RelativeLayout backview;
    public final View bottomLine;
    public final ImageView ivClose;
    public final ImageView ivCommonLeft;
    public final ImageView ivCommonTitle2;
    public final ImageView ivCommonTitle3;
    public final ImageView ivFavorite;
    public final ImageView ivQr;
    public final ImageView ivRefresh;
    public final ImageView ivShare;
    public final LinearLayout llClose;
    public final LinearLayout llCommonLeft;
    public final TextView middleTitle;
    public final RelativeLayout rlIconFavorite;
    public final RelativeLayout rlIconRight;
    public final RelativeLayout rlRight;
    public final RelativeLayout rlRightShare;
    public final RelativeLayout rootHeaderBar;
    private final RelativeLayout rootView;
    public final LinearLayout statusbar;
    public final TextView tvBgRight;
    public final TextView tvCommonRight;
    public final TextView tvCommonRight2;
    public final TextView tvCommonTitle;
    public final TextView tvCommonTitle2;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private HeaderBarBinding(RelativeLayout relativeLayout, RelativeLayout relativeLayout2, View view, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView imageView6, ImageView imageView7, ImageView imageView8, LinearLayout linearLayout, LinearLayout linearLayout2, TextView textView, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, RelativeLayout relativeLayout5, RelativeLayout relativeLayout6, RelativeLayout relativeLayout7, LinearLayout linearLayout3, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6) {
        this.rootView = relativeLayout;
        this.backview = relativeLayout2;
        this.bottomLine = view;
        this.ivClose = imageView;
        this.ivCommonLeft = imageView2;
        this.ivCommonTitle2 = imageView3;
        this.ivCommonTitle3 = imageView4;
        this.ivFavorite = imageView5;
        this.ivQr = imageView6;
        this.ivRefresh = imageView7;
        this.ivShare = imageView8;
        this.llClose = linearLayout;
        this.llCommonLeft = linearLayout2;
        this.middleTitle = textView;
        this.rlIconFavorite = relativeLayout3;
        this.rlIconRight = relativeLayout4;
        this.rlRight = relativeLayout5;
        this.rlRightShare = relativeLayout6;
        this.rootHeaderBar = relativeLayout7;
        this.statusbar = linearLayout3;
        this.tvBgRight = textView2;
        this.tvCommonRight = textView3;
        this.tvCommonRight2 = textView4;
        this.tvCommonTitle = textView5;
        this.tvCommonTitle2 = textView6;
    }

    public static HeaderBarBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HeaderBarBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.header_bar, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HeaderBarBinding bind(View view) {
        View findChildViewById;
        int i = R.id.backview;
        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, i);
        if (relativeLayout != null && (findChildViewById = ViewBindings.findChildViewById(view, (i = R.id.bottom_line))) != null) {
            i = R.id.iv_close;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
            if (imageView != null) {
                i = R.id.iv_common_left;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
                if (imageView2 != null) {
                    i = R.id.iv_common_title2;
                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, i);
                    if (imageView3 != null) {
                        i = R.id.iv_common_title3;
                        ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, i);
                        if (imageView4 != null) {
                            i = R.id.iv_favorite;
                            ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, i);
                            if (imageView5 != null) {
                                i = R.id.iv_qr;
                                ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(view, i);
                                if (imageView6 != null) {
                                    i = R.id.iv_refresh;
                                    ImageView imageView7 = (ImageView) ViewBindings.findChildViewById(view, i);
                                    if (imageView7 != null) {
                                        i = R.id.iv_share;
                                        ImageView imageView8 = (ImageView) ViewBindings.findChildViewById(view, i);
                                        if (imageView8 != null) {
                                            i = R.id.ll_close;
                                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                            if (linearLayout != null) {
                                                i = R.id.ll_common_left;
                                                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                if (linearLayout2 != null) {
                                                    i = R.id.middle_title;
                                                    TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                                                    if (textView != null) {
                                                        i = R.id.rl_icon_favorite;
                                                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, i);
                                                        if (relativeLayout2 != null) {
                                                            i = R.id.rl_icon_right;
                                                            RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, i);
                                                            if (relativeLayout3 != null) {
                                                                i = R.id.rl_right;
                                                                RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, i);
                                                                if (relativeLayout4 != null) {
                                                                    i = R.id.rl_right_share;
                                                                    RelativeLayout relativeLayout5 = (RelativeLayout) ViewBindings.findChildViewById(view, i);
                                                                    if (relativeLayout5 != null) {
                                                                        RelativeLayout relativeLayout6 = (RelativeLayout) view;
                                                                        i = R.id.statusbar;
                                                                        LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                                        if (linearLayout3 != null) {
                                                                            i = R.id.tv_bg_right;
                                                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                            if (textView2 != null) {
                                                                                i = R.id.tv_common_right;
                                                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                if (textView3 != null) {
                                                                                    i = R.id.tv_common_right2;
                                                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                    if (textView4 != null) {
                                                                                        i = R.id.tv_common_title;
                                                                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                        if (textView5 != null) {
                                                                                            i = R.id.tv_common_title2;
                                                                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                            if (textView6 != null) {
                                                                                                return new HeaderBarBinding(relativeLayout6, relativeLayout, findChildViewById, imageView, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, linearLayout, linearLayout2, textView, relativeLayout2, relativeLayout3, relativeLayout4, relativeLayout5, relativeLayout6, linearLayout3, textView2, textView3, textView4, textView5, textView6);
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
