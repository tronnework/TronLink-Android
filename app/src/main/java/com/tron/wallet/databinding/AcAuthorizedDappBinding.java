package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class AcAuthorizedDappBinding implements ViewBinding {
    public final Button btCancel;
    public final ImageView ivCommonLeft;
    public final LinearLayout llCommonLeft;
    public final RelativeLayout llHeader;
    public final NodataviewBinding noDataView;
    private final RelativeLayout rootView;
    public final RecyclerView rvAuthorizedList;
    public final TextView tvCommonRight;
    public final TextView tvCommonTitle;
    public final TextView tvTextHint;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcAuthorizedDappBinding(RelativeLayout relativeLayout, Button button, ImageView imageView, LinearLayout linearLayout, RelativeLayout relativeLayout2, NodataviewBinding nodataviewBinding, RecyclerView recyclerView, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = relativeLayout;
        this.btCancel = button;
        this.ivCommonLeft = imageView;
        this.llCommonLeft = linearLayout;
        this.llHeader = relativeLayout2;
        this.noDataView = nodataviewBinding;
        this.rvAuthorizedList = recyclerView;
        this.tvCommonRight = textView;
        this.tvCommonTitle = textView2;
        this.tvTextHint = textView3;
    }

    public static AcAuthorizedDappBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcAuthorizedDappBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_authorized_dapp, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcAuthorizedDappBinding bind(View view) {
        int i = R.id.bt_cancel;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_cancel);
        if (button != null) {
            i = R.id.iv_common_left;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_common_left);
            if (imageView != null) {
                i = R.id.ll_common_left;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_common_left);
                if (linearLayout != null) {
                    i = R.id.ll_header;
                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_header);
                    if (relativeLayout != null) {
                        i = R.id.no_data_view;
                        View findChildViewById = ViewBindings.findChildViewById(view, R.id.no_data_view);
                        if (findChildViewById != null) {
                            NodataviewBinding bind = NodataviewBinding.bind(findChildViewById);
                            i = R.id.rv_authorized_list;
                            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rv_authorized_list);
                            if (recyclerView != null) {
                                i = R.id.tv_common_right;
                                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_common_right);
                                if (textView != null) {
                                    i = R.id.tv_common_title;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_common_title);
                                    if (textView2 != null) {
                                        i = R.id.tv_text_hint;
                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_text_hint);
                                        if (textView3 != null) {
                                            return new AcAuthorizedDappBinding((RelativeLayout) view, button, imageView, linearLayout, relativeLayout, bind, recyclerView, textView, textView2, textView3);
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
