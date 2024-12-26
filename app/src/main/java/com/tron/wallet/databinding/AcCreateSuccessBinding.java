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
public final class AcCreateSuccessBinding implements ViewBinding {
    public final LinearLayout bottom;
    public final Button btAgain;
    public final Button btStart;
    public final ImageView gifOk;
    public final LinearLayout llUnBackup;
    public final RelativeLayout rlBackedUp;
    private final RelativeLayout rootView;
    public final LinearLayout tips;
    public final TextView tvHint;
    public final TextView tvTop;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcCreateSuccessBinding(RelativeLayout relativeLayout, LinearLayout linearLayout, Button button, Button button2, ImageView imageView, LinearLayout linearLayout2, RelativeLayout relativeLayout2, LinearLayout linearLayout3, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.bottom = linearLayout;
        this.btAgain = button;
        this.btStart = button2;
        this.gifOk = imageView;
        this.llUnBackup = linearLayout2;
        this.rlBackedUp = relativeLayout2;
        this.tips = linearLayout3;
        this.tvHint = textView;
        this.tvTop = textView2;
    }

    public static AcCreateSuccessBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcCreateSuccessBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_create_success, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcCreateSuccessBinding bind(View view) {
        int i = R.id.bottom;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.bottom);
        if (linearLayout != null) {
            i = R.id.bt_again;
            Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_again);
            if (button != null) {
                i = R.id.bt_start;
                Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.bt_start);
                if (button2 != null) {
                    i = R.id.gif_ok;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.gif_ok);
                    if (imageView != null) {
                        i = R.id.ll_un_backup;
                        LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_un_backup);
                        if (linearLayout2 != null) {
                            i = R.id.rl_backed_up;
                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_backed_up);
                            if (relativeLayout != null) {
                                i = R.id.tips;
                                LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.tips);
                                if (linearLayout3 != null) {
                                    i = R.id.tv_hint;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_hint);
                                    if (textView != null) {
                                        i = R.id.tv_top;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_top);
                                        if (textView2 != null) {
                                            return new AcCreateSuccessBinding((RelativeLayout) view, linearLayout, button, button2, imageView, linearLayout2, relativeLayout, linearLayout3, textView, textView2);
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
