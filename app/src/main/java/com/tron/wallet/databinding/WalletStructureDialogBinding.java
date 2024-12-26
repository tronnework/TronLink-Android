package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class WalletStructureDialogBinding implements ViewBinding {
    public final Button btnKnow;
    private final RelativeLayout rootView;
    public final TextView tvContent1;
    public final TextView tvContent2;
    public final TextView tvContent3;
    public final TextView tvContent4;
    public final TextView tvLearmHd;
    public final TextView tvTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private WalletStructureDialogBinding(RelativeLayout relativeLayout, Button button, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6) {
        this.rootView = relativeLayout;
        this.btnKnow = button;
        this.tvContent1 = textView;
        this.tvContent2 = textView2;
        this.tvContent3 = textView3;
        this.tvContent4 = textView4;
        this.tvLearmHd = textView5;
        this.tvTitle = textView6;
    }

    public static WalletStructureDialogBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static WalletStructureDialogBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.wallet_structure_dialog, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static WalletStructureDialogBinding bind(View view) {
        int i = R.id.btn_know;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_know);
        if (button != null) {
            i = R.id.tv_content1;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_content1);
            if (textView != null) {
                i = R.id.tv_content2;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_content2);
                if (textView2 != null) {
                    i = R.id.tv_content3;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_content3);
                    if (textView3 != null) {
                        i = R.id.tv_content4;
                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_content4);
                        if (textView4 != null) {
                            i = R.id.tv_learm_hd;
                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_learm_hd);
                            if (textView5 != null) {
                                i = R.id.tv_title;
                                TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                                if (textView6 != null) {
                                    return new WalletStructureDialogBinding((RelativeLayout) view, button, textView, textView2, textView3, textView4, textView5, textView6);
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
