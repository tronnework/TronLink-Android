package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tronlinkpro.wallet.R;
public final class ItemDappHomeScollItemBinding implements ViewBinding {
    public final RelativeLayout ll0;
    public final RelativeLayout ll1;
    public final RelativeLayout ll2;
    public final RelativeLayout rlBg0;
    public final RelativeLayout rlBg1;
    public final RelativeLayout rlBg2;
    private final LinearLayout rootView;
    public final SimpleDraweeView simpleDraweeView0;
    public final SimpleDraweeView simpleDraweeView1;
    public final SimpleDraweeView simpleDraweeView2;
    public final TextView tvName0;
    public final TextView tvName1;
    public final TextView tvName2;
    public final TextView tvNameContent0;
    public final TextView tvNameContent1;
    public final TextView tvNameContent2;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private ItemDappHomeScollItemBinding(LinearLayout linearLayout, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, RelativeLayout relativeLayout5, RelativeLayout relativeLayout6, SimpleDraweeView simpleDraweeView, SimpleDraweeView simpleDraweeView2, SimpleDraweeView simpleDraweeView3, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6) {
        this.rootView = linearLayout;
        this.ll0 = relativeLayout;
        this.ll1 = relativeLayout2;
        this.ll2 = relativeLayout3;
        this.rlBg0 = relativeLayout4;
        this.rlBg1 = relativeLayout5;
        this.rlBg2 = relativeLayout6;
        this.simpleDraweeView0 = simpleDraweeView;
        this.simpleDraweeView1 = simpleDraweeView2;
        this.simpleDraweeView2 = simpleDraweeView3;
        this.tvName0 = textView;
        this.tvName1 = textView2;
        this.tvName2 = textView3;
        this.tvNameContent0 = textView4;
        this.tvNameContent1 = textView5;
        this.tvNameContent2 = textView6;
    }

    public static ItemDappHomeScollItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemDappHomeScollItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_dapp_home_scoll_item, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemDappHomeScollItemBinding bind(View view) {
        int i = R.id.ll_0;
        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_0);
        if (relativeLayout != null) {
            i = R.id.ll_1;
            RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_1);
            if (relativeLayout2 != null) {
                i = R.id.ll_2;
                RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_2);
                if (relativeLayout3 != null) {
                    i = R.id.rl_bg_0;
                    RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_bg_0);
                    if (relativeLayout4 != null) {
                        i = R.id.rl_bg_1;
                        RelativeLayout relativeLayout5 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_bg_1);
                        if (relativeLayout5 != null) {
                            i = R.id.rl_bg_2;
                            RelativeLayout relativeLayout6 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_bg_2);
                            if (relativeLayout6 != null) {
                                i = R.id.simple_drawee_view_0;
                                SimpleDraweeView simpleDraweeView = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.simple_drawee_view_0);
                                if (simpleDraweeView != null) {
                                    i = R.id.simple_drawee_view_1;
                                    SimpleDraweeView simpleDraweeView2 = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.simple_drawee_view_1);
                                    if (simpleDraweeView2 != null) {
                                        i = R.id.simple_drawee_view_2;
                                        SimpleDraweeView simpleDraweeView3 = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.simple_drawee_view_2);
                                        if (simpleDraweeView3 != null) {
                                            i = R.id.tv_name_0;
                                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name_0);
                                            if (textView != null) {
                                                i = R.id.tv_name_1;
                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name_1);
                                                if (textView2 != null) {
                                                    i = R.id.tv_name_2;
                                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name_2);
                                                    if (textView3 != null) {
                                                        i = R.id.tv_name_content_0;
                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name_content_0);
                                                        if (textView4 != null) {
                                                            i = R.id.tv_name_content_1;
                                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name_content_1);
                                                            if (textView5 != null) {
                                                                i = R.id.tv_name_content_2;
                                                                TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name_content_2);
                                                                if (textView6 != null) {
                                                                    return new ItemDappHomeScollItemBinding((LinearLayout) view, relativeLayout, relativeLayout2, relativeLayout3, relativeLayout4, relativeLayout5, relativeLayout6, simpleDraweeView, simpleDraweeView2, simpleDraweeView3, textView, textView2, textView3, textView4, textView5, textView6);
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
