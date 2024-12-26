package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.components.ptr.PtrHTFrameLayout;
import com.tronlinkpro.wallet.R;
public final class ActivityVoteSelectSrBinding implements ViewBinding {
    public final Button btNext;
    public final View divider;
    public final EditText etSearch;
    public final FrameLayout flRightLayout;
    public final ImageView ivClear;
    public final ImageView ivSort;
    public final LinearLayout liClearAll;
    public final LinearLayout liNoEnoughVote;
    public final LinearLayout liSelectedCount;
    public final NoNetView llError;
    public final LinearLayout llSearch;
    public final NoNetView noDataView;
    public final PtrHTFrameLayout plFrame;
    public final RelativeLayout placeHolderView;
    public final RecyclerView rcList;
    public final RelativeLayout rlBottom;
    public final RelativeLayout rlNoda;
    public final RelativeLayout rlSearch;
    private final RelativeLayout rootView;
    public final RelativeLayout searchHeader;
    public final TextView tvCancel;
    public final TextView tvMultiWarning;
    public final TextView tvSelectedCount;
    public final TextView voteCount;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ActivityVoteSelectSrBinding(RelativeLayout relativeLayout, Button button, View view, EditText editText, FrameLayout frameLayout, ImageView imageView, ImageView imageView2, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, NoNetView noNetView, LinearLayout linearLayout4, NoNetView noNetView2, PtrHTFrameLayout ptrHTFrameLayout, RelativeLayout relativeLayout2, RecyclerView recyclerView, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, RelativeLayout relativeLayout5, RelativeLayout relativeLayout6, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        this.rootView = relativeLayout;
        this.btNext = button;
        this.divider = view;
        this.etSearch = editText;
        this.flRightLayout = frameLayout;
        this.ivClear = imageView;
        this.ivSort = imageView2;
        this.liClearAll = linearLayout;
        this.liNoEnoughVote = linearLayout2;
        this.liSelectedCount = linearLayout3;
        this.llError = noNetView;
        this.llSearch = linearLayout4;
        this.noDataView = noNetView2;
        this.plFrame = ptrHTFrameLayout;
        this.placeHolderView = relativeLayout2;
        this.rcList = recyclerView;
        this.rlBottom = relativeLayout3;
        this.rlNoda = relativeLayout4;
        this.rlSearch = relativeLayout5;
        this.searchHeader = relativeLayout6;
        this.tvCancel = textView;
        this.tvMultiWarning = textView2;
        this.tvSelectedCount = textView3;
        this.voteCount = textView4;
    }

    public static ActivityVoteSelectSrBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityVoteSelectSrBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_vote_select_sr, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityVoteSelectSrBinding bind(View view) {
        int i = R.id.bt_next;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_next);
        if (button != null) {
            i = R.id.divider;
            View findChildViewById = ViewBindings.findChildViewById(view, R.id.divider);
            if (findChildViewById != null) {
                i = R.id.et_search;
                EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.et_search);
                if (editText != null) {
                    i = R.id.fl_right_layout;
                    FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.fl_right_layout);
                    if (frameLayout != null) {
                        i = R.id.iv_clear;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_clear);
                        if (imageView != null) {
                            i = R.id.iv_sort;
                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_sort);
                            if (imageView2 != null) {
                                i = R.id.li_clear_all;
                                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_clear_all);
                                if (linearLayout != null) {
                                    i = R.id.li_no_enough_vote;
                                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_no_enough_vote);
                                    if (linearLayout2 != null) {
                                        i = R.id.li_selected_count;
                                        LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_selected_count);
                                        if (linearLayout3 != null) {
                                            i = R.id.ll_error;
                                            NoNetView noNetView = (NoNetView) ViewBindings.findChildViewById(view, R.id.ll_error);
                                            if (noNetView != null) {
                                                i = R.id.ll_search;
                                                LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_search);
                                                if (linearLayout4 != null) {
                                                    i = R.id.no_data_view;
                                                    NoNetView noNetView2 = (NoNetView) ViewBindings.findChildViewById(view, R.id.no_data_view);
                                                    if (noNetView2 != null) {
                                                        i = R.id.pl_frame;
                                                        PtrHTFrameLayout ptrHTFrameLayout = (PtrHTFrameLayout) ViewBindings.findChildViewById(view, R.id.pl_frame);
                                                        if (ptrHTFrameLayout != null) {
                                                            i = R.id.place_holder_view;
                                                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.place_holder_view);
                                                            if (relativeLayout != null) {
                                                                i = R.id.rc_list;
                                                                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rc_list);
                                                                if (recyclerView != null) {
                                                                    i = R.id.rl_bottom;
                                                                    RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_bottom);
                                                                    if (relativeLayout2 != null) {
                                                                        i = R.id.rl_noda;
                                                                        RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_noda);
                                                                        if (relativeLayout3 != null) {
                                                                            i = R.id.rl_search;
                                                                            RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_search);
                                                                            if (relativeLayout4 != null) {
                                                                                i = R.id.search_header;
                                                                                RelativeLayout relativeLayout5 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.search_header);
                                                                                if (relativeLayout5 != null) {
                                                                                    i = R.id.tv_cancel;
                                                                                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_cancel);
                                                                                    if (textView != null) {
                                                                                        i = R.id.tv_multi_warning;
                                                                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_multi_warning);
                                                                                        if (textView2 != null) {
                                                                                            i = R.id.tv_selected_count;
                                                                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_selected_count);
                                                                                            if (textView3 != null) {
                                                                                                i = R.id.vote_count;
                                                                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.vote_count);
                                                                                                if (textView4 != null) {
                                                                                                    return new ActivityVoteSelectSrBinding((RelativeLayout) view, button, findChildViewById, editText, frameLayout, imageView, imageView2, linearLayout, linearLayout2, linearLayout3, noNetView, linearLayout4, noNetView2, ptrHTFrameLayout, relativeLayout, recyclerView, relativeLayout2, relativeLayout3, relativeLayout4, relativeLayout5, textView, textView2, textView3, textView4);
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
