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
import com.tron.wallet.common.components.LoadingView;
import com.tronlinkpro.wallet.R;
public final class ItemSwapMainRecordBinding implements ViewBinding {
    public final ImageView iconSwapRecord;
    public final LoadingView ivPending;
    public final View line;
    public final LinearLayout llPending;
    public final RelativeLayout rlInner;
    public final RelativeLayout rlName;
    public final RelativeLayout root;
    private final RelativeLayout rootView;
    public final TextView tokenConsumeCount;
    public final TextView tokenConsumeName;
    public final TextView tokenObtainCount;
    public final TextView tokenObtainName;
    public final RelativeLayout top;
    public final TextView tvDate;
    public final TextView tvFailed;
    public final TextView tvPending;
    public final TextView tvRate;
    public final TextView tvSuccess;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemSwapMainRecordBinding(RelativeLayout relativeLayout, ImageView imageView, LoadingView loadingView, View view, LinearLayout linearLayout, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, TextView textView, TextView textView2, TextView textView3, TextView textView4, RelativeLayout relativeLayout5, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9) {
        this.rootView = relativeLayout;
        this.iconSwapRecord = imageView;
        this.ivPending = loadingView;
        this.line = view;
        this.llPending = linearLayout;
        this.rlInner = relativeLayout2;
        this.rlName = relativeLayout3;
        this.root = relativeLayout4;
        this.tokenConsumeCount = textView;
        this.tokenConsumeName = textView2;
        this.tokenObtainCount = textView3;
        this.tokenObtainName = textView4;
        this.top = relativeLayout5;
        this.tvDate = textView5;
        this.tvFailed = textView6;
        this.tvPending = textView7;
        this.tvRate = textView8;
        this.tvSuccess = textView9;
    }

    public static ItemSwapMainRecordBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemSwapMainRecordBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_swap_main_record, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemSwapMainRecordBinding bind(View view) {
        int i = R.id.icon_swap_record;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.icon_swap_record);
        if (imageView != null) {
            i = R.id.iv_pending;
            LoadingView loadingView = (LoadingView) ViewBindings.findChildViewById(view, R.id.iv_pending);
            if (loadingView != null) {
                i = R.id.line;
                View findChildViewById = ViewBindings.findChildViewById(view, R.id.line);
                if (findChildViewById != null) {
                    i = R.id.ll_pending;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_pending);
                    if (linearLayout != null) {
                        i = R.id.rl_inner;
                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_inner);
                        if (relativeLayout != null) {
                            i = R.id.rl_name;
                            RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_name);
                            if (relativeLayout2 != null) {
                                RelativeLayout relativeLayout3 = (RelativeLayout) view;
                                i = R.id.token_consume_count;
                                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.token_consume_count);
                                if (textView != null) {
                                    i = R.id.token_consume_name;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.token_consume_name);
                                    if (textView2 != null) {
                                        i = R.id.token_obtain_count;
                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.token_obtain_count);
                                        if (textView3 != null) {
                                            i = R.id.token_obtain_name;
                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.token_obtain_name);
                                            if (textView4 != null) {
                                                i = R.id.top;
                                                RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.top);
                                                if (relativeLayout4 != null) {
                                                    i = R.id.tv_date;
                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_date);
                                                    if (textView5 != null) {
                                                        i = R.id.tv_failed;
                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_failed);
                                                        if (textView6 != null) {
                                                            i = R.id.tv_pending;
                                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_pending);
                                                            if (textView7 != null) {
                                                                i = R.id.tv_rate;
                                                                TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_rate);
                                                                if (textView8 != null) {
                                                                    i = R.id.tv_success;
                                                                    TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_success);
                                                                    if (textView9 != null) {
                                                                        return new ItemSwapMainRecordBinding(relativeLayout3, imageView, loadingView, findChildViewById, linearLayout, relativeLayout, relativeLayout2, relativeLayout3, textView, textView2, textView3, textView4, relativeLayout4, textView5, textView6, textView7, textView8, textView9);
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
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
