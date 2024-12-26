package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ColdWalletDialogBinding implements ViewBinding {
    public final Button btnConfirm;
    public final Button btnKnow;
    public final LinearLayout llCold;
    private final RelativeLayout rootView;
    public final TextView tvCancle;
    public final TextView tvContent1;
    public final TextView tvNoteNotice;
    public final TextView tvTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ColdWalletDialogBinding(RelativeLayout relativeLayout, Button button, Button button2, LinearLayout linearLayout, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        this.rootView = relativeLayout;
        this.btnConfirm = button;
        this.btnKnow = button2;
        this.llCold = linearLayout;
        this.tvCancle = textView;
        this.tvContent1 = textView2;
        this.tvNoteNotice = textView3;
        this.tvTitle = textView4;
    }

    public static ColdWalletDialogBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ColdWalletDialogBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.cold_wallet_dialog, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ColdWalletDialogBinding bind(View view) {
        int i = R.id.btn_confirm;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_confirm);
        if (button != null) {
            i = R.id.btn_know;
            Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.btn_know);
            if (button2 != null) {
                i = R.id.ll_cold;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_cold);
                if (linearLayout != null) {
                    i = R.id.tv_cancle;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_cancle);
                    if (textView != null) {
                        i = R.id.tv_content1;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_content1);
                        if (textView2 != null) {
                            i = R.id.tv_note_notice;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_note_notice);
                            if (textView3 != null) {
                                i = R.id.tv_title;
                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                                if (textView4 != null) {
                                    return new ColdWalletDialogBinding((RelativeLayout) view, button, button2, linearLayout, textView, textView2, textView3, textView4);
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
