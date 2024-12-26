package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class DgEtpassword2Binding implements ViewBinding {
    public final RelativeLayout bottom;
    public final ImageView cancle;
    public final RelativeLayout content;
    public final EditText etPassword;
    public final ImageView no;
    public final TextView no2;
    private final RelativeLayout rootView;
    public final ImageView see;
    public final Button sign;
    public final TextView title;
    public final TextView tvAddress;
    public final TextView tvCost;
    public final TextView tvSite;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private DgEtpassword2Binding(RelativeLayout relativeLayout, RelativeLayout relativeLayout2, ImageView imageView, RelativeLayout relativeLayout3, EditText editText, ImageView imageView2, TextView textView, ImageView imageView3, Button button, TextView textView2, TextView textView3, TextView textView4, TextView textView5) {
        this.rootView = relativeLayout;
        this.bottom = relativeLayout2;
        this.cancle = imageView;
        this.content = relativeLayout3;
        this.etPassword = editText;
        this.no = imageView2;
        this.no2 = textView;
        this.see = imageView3;
        this.sign = button;
        this.title = textView2;
        this.tvAddress = textView3;
        this.tvCost = textView4;
        this.tvSite = textView5;
    }

    public static DgEtpassword2Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DgEtpassword2Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.dg_etpassword2, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static DgEtpassword2Binding bind(View view) {
        int i = R.id.bottom;
        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.bottom);
        if (relativeLayout != null) {
            i = R.id.cancle;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.cancle);
            if (imageView != null) {
                i = R.id.content;
                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.content);
                if (relativeLayout2 != null) {
                    i = R.id.et_password;
                    EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.et_password);
                    if (editText != null) {
                        i = R.id.no;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.no);
                        if (imageView2 != null) {
                            i = R.id.no2;
                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.no2);
                            if (textView != null) {
                                i = R.id.see;
                                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.see);
                                if (imageView3 != null) {
                                    i = R.id.sign;
                                    Button button = (Button) ViewBindings.findChildViewById(view, R.id.sign);
                                    if (button != null) {
                                        i = R.id.title;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.title);
                                        if (textView2 != null) {
                                            i = R.id.tv_address;
                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address);
                                            if (textView3 != null) {
                                                i = R.id.tv_cost;
                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_cost);
                                                if (textView4 != null) {
                                                    i = R.id.tv_site;
                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_site);
                                                    if (textView5 != null) {
                                                        return new DgEtpassword2Binding((RelativeLayout) view, relativeLayout, imageView, relativeLayout2, editText, imageView2, textView, imageView3, button, textView2, textView3, textView4, textView5);
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
