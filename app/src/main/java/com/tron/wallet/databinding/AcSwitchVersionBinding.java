package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class AcSwitchVersionBinding implements ViewBinding {
    public final RelativeLayout rlNile;
    public final RelativeLayout rlOnline;
    public final RelativeLayout rlPre;
    public final RelativeLayout rlShasta;
    public final RelativeLayout rlTest;
    private final LinearLayout rootView;
    public final ImageView selectNile;
    public final ImageView selectOnline;
    public final ImageView selectPre;
    public final ImageView selectShasta;
    public final ImageView selectTest;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private AcSwitchVersionBinding(LinearLayout linearLayout, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, RelativeLayout relativeLayout5, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5) {
        this.rootView = linearLayout;
        this.rlNile = relativeLayout;
        this.rlOnline = relativeLayout2;
        this.rlPre = relativeLayout3;
        this.rlShasta = relativeLayout4;
        this.rlTest = relativeLayout5;
        this.selectNile = imageView;
        this.selectOnline = imageView2;
        this.selectPre = imageView3;
        this.selectShasta = imageView4;
        this.selectTest = imageView5;
    }

    public static AcSwitchVersionBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcSwitchVersionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_switch_version, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcSwitchVersionBinding bind(View view) {
        int i = R.id.rl_nile;
        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_nile);
        if (relativeLayout != null) {
            i = R.id.rl_online;
            RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_online);
            if (relativeLayout2 != null) {
                i = R.id.rl_pre;
                RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_pre);
                if (relativeLayout3 != null) {
                    i = R.id.rl_shasta;
                    RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_shasta);
                    if (relativeLayout4 != null) {
                        i = R.id.rl_test;
                        RelativeLayout relativeLayout5 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_test);
                        if (relativeLayout5 != null) {
                            i = R.id.select_nile;
                            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.select_nile);
                            if (imageView != null) {
                                i = R.id.select_online;
                                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.select_online);
                                if (imageView2 != null) {
                                    i = R.id.select_pre;
                                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.select_pre);
                                    if (imageView3 != null) {
                                        i = R.id.select_shasta;
                                        ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.select_shasta);
                                        if (imageView4 != null) {
                                            i = R.id.select_test;
                                            ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, R.id.select_test);
                                            if (imageView5 != null) {
                                                return new AcSwitchVersionBinding((LinearLayout) view, relativeLayout, relativeLayout2, relativeLayout3, relativeLayout4, relativeLayout5, imageView, imageView2, imageView3, imageView4, imageView5);
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
