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
import com.tron.wallet.common.components.NoNetView;
import com.tronlinkpro.wallet.R;
public final class FragmentCancelUnstakeSuccessBinding implements ViewBinding {
    public final Button btnDoneSuccess;
    public final Button btnVoteAgain;
    public final View divider;
    public final View divider1;
    public final View divider2;
    public final ImageView ivAprTip;
    public final ImageView ivOtherVoteArrow;
    public final ImageView ivResult;
    public final LinearLayout liVoteResource;
    public final RecyclerView recyclerView;
    public final NoNetView reload;
    public final RelativeLayout rlLoading;
    public final RelativeLayout rlReVotes;
    public final RelativeLayout rlReload;
    public final RelativeLayout rlResourceFirst;
    public final RelativeLayout rlResourceSecond;
    public final RelativeLayout rlVoteContent;
    public final RelativeLayout rlVoteOption;
    private final RelativeLayout rootView;
    public final TextView tvAprValue;
    public final TextView tvLeftFirst;
    public final TextView tvLeftReVotes;
    public final TextView tvLeftSecond;
    public final TextView tvNextToProfit;
    public final TextView tvOtherVotes;
    public final TextView tvResult;
    public final TextView tvRightFirst;
    public final TextView tvRightReVotes;
    public final TextView tvRightSecond;
    public final TextView tvToVoteBelow;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FragmentCancelUnstakeSuccessBinding(RelativeLayout relativeLayout, Button button, Button button2, View view, View view2, View view3, ImageView imageView, ImageView imageView2, ImageView imageView3, LinearLayout linearLayout, RecyclerView recyclerView, NoNetView noNetView, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, RelativeLayout relativeLayout5, RelativeLayout relativeLayout6, RelativeLayout relativeLayout7, RelativeLayout relativeLayout8, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11) {
        this.rootView = relativeLayout;
        this.btnDoneSuccess = button;
        this.btnVoteAgain = button2;
        this.divider = view;
        this.divider1 = view2;
        this.divider2 = view3;
        this.ivAprTip = imageView;
        this.ivOtherVoteArrow = imageView2;
        this.ivResult = imageView3;
        this.liVoteResource = linearLayout;
        this.recyclerView = recyclerView;
        this.reload = noNetView;
        this.rlLoading = relativeLayout2;
        this.rlReVotes = relativeLayout3;
        this.rlReload = relativeLayout4;
        this.rlResourceFirst = relativeLayout5;
        this.rlResourceSecond = relativeLayout6;
        this.rlVoteContent = relativeLayout7;
        this.rlVoteOption = relativeLayout8;
        this.tvAprValue = textView;
        this.tvLeftFirst = textView2;
        this.tvLeftReVotes = textView3;
        this.tvLeftSecond = textView4;
        this.tvNextToProfit = textView5;
        this.tvOtherVotes = textView6;
        this.tvResult = textView7;
        this.tvRightFirst = textView8;
        this.tvRightReVotes = textView9;
        this.tvRightSecond = textView10;
        this.tvToVoteBelow = textView11;
    }

    public static FragmentCancelUnstakeSuccessBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentCancelUnstakeSuccessBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_cancel_unstake_success, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentCancelUnstakeSuccessBinding bind(View view) {
        int i = R.id.btn_done_success;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_done_success);
        if (button != null) {
            i = R.id.btn_vote_again;
            Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.btn_vote_again);
            if (button2 != null) {
                i = R.id.divider;
                View findChildViewById = ViewBindings.findChildViewById(view, R.id.divider);
                if (findChildViewById != null) {
                    i = R.id.divider1;
                    View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.divider1);
                    if (findChildViewById2 != null) {
                        i = R.id.divider2;
                        View findChildViewById3 = ViewBindings.findChildViewById(view, R.id.divider2);
                        if (findChildViewById3 != null) {
                            i = R.id.iv_apr_tip;
                            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_apr_tip);
                            if (imageView != null) {
                                i = R.id.iv_other_vote_arrow;
                                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_other_vote_arrow);
                                if (imageView2 != null) {
                                    i = R.id.iv_result;
                                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_result);
                                    if (imageView3 != null) {
                                        i = R.id.li_vote_resource;
                                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_vote_resource);
                                        if (linearLayout != null) {
                                            i = R.id.recycler_view;
                                            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.recycler_view);
                                            if (recyclerView != null) {
                                                i = R.id.reload;
                                                NoNetView noNetView = (NoNetView) ViewBindings.findChildViewById(view, R.id.reload);
                                                if (noNetView != null) {
                                                    i = R.id.rl_loading;
                                                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_loading);
                                                    if (relativeLayout != null) {
                                                        i = R.id.rl_re_votes;
                                                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_re_votes);
                                                        if (relativeLayout2 != null) {
                                                            i = R.id.rl_reload;
                                                            RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_reload);
                                                            if (relativeLayout3 != null) {
                                                                i = R.id.rl_resource_first;
                                                                RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_resource_first);
                                                                if (relativeLayout4 != null) {
                                                                    i = R.id.rl_resource_second;
                                                                    RelativeLayout relativeLayout5 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_resource_second);
                                                                    if (relativeLayout5 != null) {
                                                                        i = R.id.rl_vote_content;
                                                                        RelativeLayout relativeLayout6 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_vote_content);
                                                                        if (relativeLayout6 != null) {
                                                                            i = R.id.rl_vote_option;
                                                                            RelativeLayout relativeLayout7 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_vote_option);
                                                                            if (relativeLayout7 != null) {
                                                                                i = R.id.tv_apr_value;
                                                                                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_apr_value);
                                                                                if (textView != null) {
                                                                                    i = R.id.tv_left_first;
                                                                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_left_first);
                                                                                    if (textView2 != null) {
                                                                                        i = R.id.tv_left_re_votes;
                                                                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_left_re_votes);
                                                                                        if (textView3 != null) {
                                                                                            i = R.id.tv_left_second;
                                                                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_left_second);
                                                                                            if (textView4 != null) {
                                                                                                i = R.id.tv_next_to_profit;
                                                                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_next_to_profit);
                                                                                                if (textView5 != null) {
                                                                                                    i = R.id.tv_other_votes;
                                                                                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_other_votes);
                                                                                                    if (textView6 != null) {
                                                                                                        i = R.id.tv_result;
                                                                                                        TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_result);
                                                                                                        if (textView7 != null) {
                                                                                                            i = R.id.tv_right_first;
                                                                                                            TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_right_first);
                                                                                                            if (textView8 != null) {
                                                                                                                i = R.id.tv_right_re_votes;
                                                                                                                TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_right_re_votes);
                                                                                                                if (textView9 != null) {
                                                                                                                    i = R.id.tv_right_second;
                                                                                                                    TextView textView10 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_right_second);
                                                                                                                    if (textView10 != null) {
                                                                                                                        i = R.id.tv_to_vote_below;
                                                                                                                        TextView textView11 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_to_vote_below);
                                                                                                                        if (textView11 != null) {
                                                                                                                            return new FragmentCancelUnstakeSuccessBinding((RelativeLayout) view, button, button2, findChildViewById, findChildViewById2, findChildViewById3, imageView, imageView2, imageView3, linearLayout, recyclerView, noNetView, relativeLayout, relativeLayout2, relativeLayout3, relativeLayout4, relativeLayout5, relativeLayout6, relativeLayout7, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11);
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
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
