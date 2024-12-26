package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.flowlayout.TagFlowLayout;
import com.tronlinkpro.wallet.R;
public final class FragmentMsManagerBinding implements ViewBinding {
    public final TagFlowLayout idFlowlayout;
    public final ImageView ivGp;
    public final LinearLayout llKeys;
    public final LinearLayout llMore;
    public final LinearLayout llOp2;
    public final RelativeLayout rlMore;
    public final RelativeLayout rlOt;
    public final LinearLayout root;
    private final LinearLayout rootView;
    public final TextView tvOperationsDesc;
    public final TextView tvPermissionName;
    public final TextView tvTh;
    public final TextView tvUc;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private FragmentMsManagerBinding(LinearLayout linearLayout, TagFlowLayout tagFlowLayout, ImageView imageView, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, LinearLayout linearLayout5, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        this.rootView = linearLayout;
        this.idFlowlayout = tagFlowLayout;
        this.ivGp = imageView;
        this.llKeys = linearLayout2;
        this.llMore = linearLayout3;
        this.llOp2 = linearLayout4;
        this.rlMore = relativeLayout;
        this.rlOt = relativeLayout2;
        this.root = linearLayout5;
        this.tvOperationsDesc = textView;
        this.tvPermissionName = textView2;
        this.tvTh = textView3;
        this.tvUc = textView4;
    }

    public static FragmentMsManagerBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentMsManagerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_ms_manager, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentMsManagerBinding bind(View view) {
        int i = R.id.id_flowlayout;
        TagFlowLayout tagFlowLayout = (TagFlowLayout) ViewBindings.findChildViewById(view, R.id.id_flowlayout);
        if (tagFlowLayout != null) {
            i = R.id.iv_gp;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_gp);
            if (imageView != null) {
                i = R.id.ll_keys;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_keys);
                if (linearLayout != null) {
                    i = R.id.ll_more;
                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_more);
                    if (linearLayout2 != null) {
                        i = R.id.ll_op_2;
                        LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_op_2);
                        if (linearLayout3 != null) {
                            i = R.id.rl_more;
                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_more);
                            if (relativeLayout != null) {
                                i = R.id.rl_ot;
                                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_ot);
                                if (relativeLayout2 != null) {
                                    LinearLayout linearLayout4 = (LinearLayout) view;
                                    i = R.id.tv_operations_desc;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_operations_desc);
                                    if (textView != null) {
                                        i = R.id.tv_permission_name;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_permission_name);
                                        if (textView2 != null) {
                                            i = R.id.tv_th;
                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_th);
                                            if (textView3 != null) {
                                                i = R.id.tv_uc;
                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_uc);
                                                if (textView4 != null) {
                                                    return new FragmentMsManagerBinding(linearLayout4, tagFlowLayout, imageView, linearLayout, linearLayout2, linearLayout3, relativeLayout, relativeLayout2, linearLayout4, textView, textView2, textView3, textView4);
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
