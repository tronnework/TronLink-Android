package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class DgTipsUnfreezeBinding implements ViewBinding {
    public final LinearLayout llContent;
    private final RelativeLayout rootView;
    public final TextView title;
    public final RelativeLayout top;
    public final TextView tvCancle;
    public final TextView tvContent;
    public final TextView tvOk;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private DgTipsUnfreezeBinding(RelativeLayout relativeLayout, LinearLayout linearLayout, TextView textView, RelativeLayout relativeLayout2, TextView textView2, TextView textView3, TextView textView4) {
        this.rootView = relativeLayout;
        this.llContent = linearLayout;
        this.title = textView;
        this.top = relativeLayout2;
        this.tvCancle = textView2;
        this.tvContent = textView3;
        this.tvOk = textView4;
    }

    public static DgTipsUnfreezeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DgTipsUnfreezeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.dg_tips_unfreeze, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static DgTipsUnfreezeBinding bind(View view) {
        int i = R.id.ll_content;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_content);
        if (linearLayout != null) {
            i = R.id.title;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.title);
            if (textView != null) {
                i = R.id.top;
                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.top);
                if (relativeLayout != null) {
                    i = R.id.tv_cancle;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_cancle);
                    if (textView2 != null) {
                        i = R.id.tv_content;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_content);
                        if (textView3 != null) {
                            i = R.id.tv_ok;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_ok);
                            if (textView4 != null) {
                                return new DgTipsUnfreezeBinding((RelativeLayout) view, linearLayout, textView, relativeLayout, textView2, textView3, textView4);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
