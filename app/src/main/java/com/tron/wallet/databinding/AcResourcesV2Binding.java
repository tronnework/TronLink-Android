package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.tron.wallet.common.components.CircularProgressView;
import com.tron.wallet.common.components.ptr.PtrHTFrameLayout;
import com.tronlinkpro.wallet.R;
public final class AcResourcesV2Binding implements ViewBinding {
    public final TextView btGo;
    public final Button btSend;
    public final RelativeLayout headerLayout;
    public final ImageView ivBack;
    public final View ivBackground;
    public final ImageView ivHelp;
    public final LinearLayout llBtGo;
    public final LinearLayout llContent;
    public final RelativeLayout llHeaderBandwidth;
    public final RelativeLayout llHeaderEnergy;
    public final NestedScrollView llScroll;
    public final CircularProgressView progressBandwidth;
    public final CircularProgressView progressEnergy;
    public final PtrHTFrameLayout rcFrame;
    public final RelativeLayout root;
    private final RelativeLayout rootView;
    public final TextView tvBandwidth;
    public final TextView tvBandwidthTotal;
    public final TextView tvEnergy;
    public final TextView tvEnergyTotal;
    public final TextView tvMainTitle;
    public final TextView tvMultiSign;
    public final TextView tvMultiWarning;
    public final TextView tvTitleBandwidth;
    public final TextView tvTitleEnergy;
    public final ViewPager2 viewpager;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcResourcesV2Binding(RelativeLayout relativeLayout, TextView textView, Button button, RelativeLayout relativeLayout2, ImageView imageView, View view, ImageView imageView2, LinearLayout linearLayout, LinearLayout linearLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, NestedScrollView nestedScrollView, CircularProgressView circularProgressView, CircularProgressView circularProgressView2, PtrHTFrameLayout ptrHTFrameLayout, RelativeLayout relativeLayout5, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, ViewPager2 viewPager2) {
        this.rootView = relativeLayout;
        this.btGo = textView;
        this.btSend = button;
        this.headerLayout = relativeLayout2;
        this.ivBack = imageView;
        this.ivBackground = view;
        this.ivHelp = imageView2;
        this.llBtGo = linearLayout;
        this.llContent = linearLayout2;
        this.llHeaderBandwidth = relativeLayout3;
        this.llHeaderEnergy = relativeLayout4;
        this.llScroll = nestedScrollView;
        this.progressBandwidth = circularProgressView;
        this.progressEnergy = circularProgressView2;
        this.rcFrame = ptrHTFrameLayout;
        this.root = relativeLayout5;
        this.tvBandwidth = textView2;
        this.tvBandwidthTotal = textView3;
        this.tvEnergy = textView4;
        this.tvEnergyTotal = textView5;
        this.tvMainTitle = textView6;
        this.tvMultiSign = textView7;
        this.tvMultiWarning = textView8;
        this.tvTitleBandwidth = textView9;
        this.tvTitleEnergy = textView10;
        this.viewpager = viewPager2;
    }

    public static AcResourcesV2Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcResourcesV2Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_resources_v2, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcResourcesV2Binding bind(View view) {
        int i = R.id.bt_go;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.bt_go);
        if (textView != null) {
            i = R.id.bt_send;
            Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_send);
            if (button != null) {
                i = R.id.header_layout;
                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.header_layout);
                if (relativeLayout != null) {
                    i = R.id.iv_back;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_back);
                    if (imageView != null) {
                        i = R.id.iv_background;
                        View findChildViewById = ViewBindings.findChildViewById(view, R.id.iv_background);
                        if (findChildViewById != null) {
                            i = R.id.iv_help;
                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_help);
                            if (imageView2 != null) {
                                i = R.id.ll_bt_go;
                                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_bt_go);
                                if (linearLayout != null) {
                                    i = R.id.ll_content;
                                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_content);
                                    if (linearLayout2 != null) {
                                        i = R.id.ll_header_bandwidth;
                                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_header_bandwidth);
                                        if (relativeLayout2 != null) {
                                            i = R.id.ll_header_energy;
                                            RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_header_energy);
                                            if (relativeLayout3 != null) {
                                                i = R.id.ll_scroll;
                                                NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(view, R.id.ll_scroll);
                                                if (nestedScrollView != null) {
                                                    i = R.id.progress_bandwidth;
                                                    CircularProgressView circularProgressView = (CircularProgressView) ViewBindings.findChildViewById(view, R.id.progress_bandwidth);
                                                    if (circularProgressView != null) {
                                                        i = R.id.progress_energy;
                                                        CircularProgressView circularProgressView2 = (CircularProgressView) ViewBindings.findChildViewById(view, R.id.progress_energy);
                                                        if (circularProgressView2 != null) {
                                                            i = R.id.rc_frame;
                                                            PtrHTFrameLayout ptrHTFrameLayout = (PtrHTFrameLayout) ViewBindings.findChildViewById(view, R.id.rc_frame);
                                                            if (ptrHTFrameLayout != null) {
                                                                RelativeLayout relativeLayout4 = (RelativeLayout) view;
                                                                i = R.id.tv_bandwidth;
                                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_bandwidth);
                                                                if (textView2 != null) {
                                                                    i = R.id.tv_bandwidth_total;
                                                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_bandwidth_total);
                                                                    if (textView3 != null) {
                                                                        i = R.id.tv_energy;
                                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_energy);
                                                                        if (textView4 != null) {
                                                                            i = R.id.tv_energy_total;
                                                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_energy_total);
                                                                            if (textView5 != null) {
                                                                                i = R.id.tv_main_title;
                                                                                TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_main_title);
                                                                                if (textView6 != null) {
                                                                                    i = R.id.tv_multi_sign;
                                                                                    TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_multi_sign);
                                                                                    if (textView7 != null) {
                                                                                        i = R.id.tv_multi_warning;
                                                                                        TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_multi_warning);
                                                                                        if (textView8 != null) {
                                                                                            i = R.id.tv_title_bandwidth;
                                                                                            TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title_bandwidth);
                                                                                            if (textView9 != null) {
                                                                                                i = R.id.tv_title_energy;
                                                                                                TextView textView10 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title_energy);
                                                                                                if (textView10 != null) {
                                                                                                    i = R.id.viewpager;
                                                                                                    ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, R.id.viewpager);
                                                                                                    if (viewPager2 != null) {
                                                                                                        return new AcResourcesV2Binding(relativeLayout4, textView, button, relativeLayout, imageView, findChildViewById, imageView2, linearLayout, linearLayout2, relativeLayout2, relativeLayout3, nestedScrollView, circularProgressView, circularProgressView2, ptrHTFrameLayout, relativeLayout4, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, viewPager2);
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
