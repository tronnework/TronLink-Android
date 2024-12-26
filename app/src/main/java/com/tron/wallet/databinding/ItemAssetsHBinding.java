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
public final class ItemAssetsHBinding implements ViewBinding {
    public final RelativeLayout center;
    public final TextView centerText;
    public final RelativeLayout left;
    public final TextView leftText;
    public final View line1;
    public final View line2;
    public final View line3;
    public final RelativeLayout right;
    public final TextView rightText;
    private final LinearLayout rootView;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private ItemAssetsHBinding(LinearLayout linearLayout, RelativeLayout relativeLayout, TextView textView, RelativeLayout relativeLayout2, TextView textView2, View view, View view2, View view3, RelativeLayout relativeLayout3, TextView textView3) {
        this.rootView = linearLayout;
        this.center = relativeLayout;
        this.centerText = textView;
        this.left = relativeLayout2;
        this.leftText = textView2;
        this.line1 = view;
        this.line2 = view2;
        this.line3 = view3;
        this.right = relativeLayout3;
        this.rightText = textView3;
    }

    public static ItemAssetsHBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemAssetsHBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_assets_h, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemAssetsHBinding bind(View view) {
        int i = R.id.center;
        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.center);
        if (relativeLayout != null) {
            i = R.id.center_text;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.center_text);
            if (textView != null) {
                i = R.id.left;
                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.left);
                if (relativeLayout2 != null) {
                    i = R.id.left_text;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.left_text);
                    if (textView2 != null) {
                        i = R.id.line1;
                        View findChildViewById = ViewBindings.findChildViewById(view, R.id.line1);
                        if (findChildViewById != null) {
                            i = R.id.line2;
                            View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.line2);
                            if (findChildViewById2 != null) {
                                i = R.id.line3;
                                View findChildViewById3 = ViewBindings.findChildViewById(view, R.id.line3);
                                if (findChildViewById3 != null) {
                                    i = R.id.right;
                                    RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.right);
                                    if (relativeLayout3 != null) {
                                        i = R.id.right_text;
                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.right_text);
                                        if (textView3 != null) {
                                            return new ItemAssetsHBinding((LinearLayout) view, relativeLayout, textView, relativeLayout2, textView2, findChildViewById, findChildViewById2, findChildViewById3, relativeLayout3, textView3);
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
