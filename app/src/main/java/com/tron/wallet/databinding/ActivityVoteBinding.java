package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.AppBarLayout;
import com.tron.wallet.common.components.ptr.PtrHTFrameLayout;
import com.tronlinkpro.wallet.R;
public final class ActivityVoteBinding implements ViewBinding {
    public final AppBarLayout appbarLayout;
    public final RelativeLayout aprTipLayout;
    public final TextView btnCancelAll;
    public final FragmentContainerView frameHeader;
    public final ImageView ivSort;
    public final LayoutCommonHeaderBinding layoutCommonHeader;
    public final LayoutVoteContentListBinding layoutVoteList;
    public final LayoutSearchViewBinding llSearchView;
    public final LinearLayout llSort;
    public final PtrHTFrameLayout ptrView;
    public final RelativeLayout rlSearch;
    public final LinearLayout root;
    private final LinearLayout rootView;
    public final TextView tvAprTip;
    public final TextView tvMultiWarning;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private ActivityVoteBinding(LinearLayout linearLayout, AppBarLayout appBarLayout, RelativeLayout relativeLayout, TextView textView, FragmentContainerView fragmentContainerView, ImageView imageView, LayoutCommonHeaderBinding layoutCommonHeaderBinding, LayoutVoteContentListBinding layoutVoteContentListBinding, LayoutSearchViewBinding layoutSearchViewBinding, LinearLayout linearLayout2, PtrHTFrameLayout ptrHTFrameLayout, RelativeLayout relativeLayout2, LinearLayout linearLayout3, TextView textView2, TextView textView3) {
        this.rootView = linearLayout;
        this.appbarLayout = appBarLayout;
        this.aprTipLayout = relativeLayout;
        this.btnCancelAll = textView;
        this.frameHeader = fragmentContainerView;
        this.ivSort = imageView;
        this.layoutCommonHeader = layoutCommonHeaderBinding;
        this.layoutVoteList = layoutVoteContentListBinding;
        this.llSearchView = layoutSearchViewBinding;
        this.llSort = linearLayout2;
        this.ptrView = ptrHTFrameLayout;
        this.rlSearch = relativeLayout2;
        this.root = linearLayout3;
        this.tvAprTip = textView2;
        this.tvMultiWarning = textView3;
    }

    public static ActivityVoteBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityVoteBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_vote, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityVoteBinding bind(View view) {
        int i = R.id.appbar_layout;
        AppBarLayout appBarLayout = (AppBarLayout) ViewBindings.findChildViewById(view, R.id.appbar_layout);
        if (appBarLayout != null) {
            i = R.id.apr_tip_layout;
            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.apr_tip_layout);
            if (relativeLayout != null) {
                i = R.id.btn_cancel_all;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.btn_cancel_all);
                if (textView != null) {
                    i = R.id.frame_header;
                    FragmentContainerView fragmentContainerView = (FragmentContainerView) ViewBindings.findChildViewById(view, R.id.frame_header);
                    if (fragmentContainerView != null) {
                        i = R.id.iv_sort;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_sort);
                        if (imageView != null) {
                            i = R.id.layout_common_header;
                            View findChildViewById = ViewBindings.findChildViewById(view, R.id.layout_common_header);
                            if (findChildViewById != null) {
                                LayoutCommonHeaderBinding bind = LayoutCommonHeaderBinding.bind(findChildViewById);
                                i = R.id.layout_vote_list;
                                View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.layout_vote_list);
                                if (findChildViewById2 != null) {
                                    LayoutVoteContentListBinding bind2 = LayoutVoteContentListBinding.bind(findChildViewById2);
                                    i = R.id.ll_search_view;
                                    View findChildViewById3 = ViewBindings.findChildViewById(view, R.id.ll_search_view);
                                    if (findChildViewById3 != null) {
                                        LayoutSearchViewBinding bind3 = LayoutSearchViewBinding.bind(findChildViewById3);
                                        i = R.id.ll_sort;
                                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_sort);
                                        if (linearLayout != null) {
                                            i = R.id.ptr_view;
                                            PtrHTFrameLayout ptrHTFrameLayout = (PtrHTFrameLayout) ViewBindings.findChildViewById(view, R.id.ptr_view);
                                            if (ptrHTFrameLayout != null) {
                                                i = R.id.rl_search;
                                                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_search);
                                                if (relativeLayout2 != null) {
                                                    LinearLayout linearLayout2 = (LinearLayout) view;
                                                    i = R.id.tv_apr_tip;
                                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_apr_tip);
                                                    if (textView2 != null) {
                                                        i = R.id.tv_multi_warning;
                                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_multi_warning);
                                                        if (textView3 != null) {
                                                            return new ActivityVoteBinding(linearLayout2, appBarLayout, relativeLayout, textView, fragmentContainerView, imageView, bind, bind2, bind3, linearLayout, ptrHTFrameLayout, relativeLayout2, linearLayout2, textView2, textView3);
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
