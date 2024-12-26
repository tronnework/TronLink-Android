package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.ErrorEdiTextLayout;
import com.tronlinkpro.wallet.R;
public final class ItemMakeProposalsBinding implements ViewBinding {
    public final TextView companyProposals;
    public final ErrorEdiTextLayout eetProposals;
    public final EditText etProposals;
    public final ImageView iv;
    public final LinearLayout llProposals;
    public final LinearLayout rlTop;
    private final RelativeLayout rootView;
    public final TextView tvLine;
    public final TextView tvReset;
    public final TextView tvTop;
    public final TextView tvTopContent;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemMakeProposalsBinding(RelativeLayout relativeLayout, TextView textView, ErrorEdiTextLayout errorEdiTextLayout, EditText editText, ImageView imageView, LinearLayout linearLayout, LinearLayout linearLayout2, TextView textView2, TextView textView3, TextView textView4, TextView textView5) {
        this.rootView = relativeLayout;
        this.companyProposals = textView;
        this.eetProposals = errorEdiTextLayout;
        this.etProposals = editText;
        this.iv = imageView;
        this.llProposals = linearLayout;
        this.rlTop = linearLayout2;
        this.tvLine = textView2;
        this.tvReset = textView3;
        this.tvTop = textView4;
        this.tvTopContent = textView5;
    }

    public static ItemMakeProposalsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemMakeProposalsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_make_proposals, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemMakeProposalsBinding bind(View view) {
        int i = R.id.company_proposals;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.company_proposals);
        if (textView != null) {
            i = R.id.eet_proposals;
            ErrorEdiTextLayout errorEdiTextLayout = (ErrorEdiTextLayout) ViewBindings.findChildViewById(view, R.id.eet_proposals);
            if (errorEdiTextLayout != null) {
                i = R.id.et_proposals;
                EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.et_proposals);
                if (editText != null) {
                    i = R.id.iv;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv);
                    if (imageView != null) {
                        i = R.id.ll_proposals;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_proposals);
                        if (linearLayout != null) {
                            i = R.id.rl_top;
                            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.rl_top);
                            if (linearLayout2 != null) {
                                i = R.id.tv_line;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_line);
                                if (textView2 != null) {
                                    i = R.id.tv_reset;
                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_reset);
                                    if (textView3 != null) {
                                        i = R.id.tv_top;
                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_top);
                                        if (textView4 != null) {
                                            i = R.id.tv_top_content;
                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_top_content);
                                            if (textView5 != null) {
                                                return new ItemMakeProposalsBinding((RelativeLayout) view, textView, errorEdiTextLayout, editText, imageView, linearLayout, linearLayout2, textView2, textView3, textView4, textView5);
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
