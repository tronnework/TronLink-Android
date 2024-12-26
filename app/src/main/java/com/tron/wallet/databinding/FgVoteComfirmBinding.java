package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.ErrorEdiTextLayout;
import com.tronlinkpro.wallet.R;
public final class FgVoteComfirmBinding implements ViewBinding {
    public final Button btSend;
    public final ErrorEdiTextLayout eetContent;
    public final EditText etVoteAmount;
    public final LinearLayout ivCloseTwo;
    public final LinearLayout llCancelVoteError;
    public final RelativeLayout rlEstimated;
    public final RelativeLayout rlFirst;
    public final LinearLayout rlPassword;
    public final RelativeLayout rlSecond;
    public final RelativeLayout rlThird;
    public final RelativeLayout rlTopTwo;
    public final RelativeLayout root;
    private final RelativeLayout rootView;
    public final TextView tvAddress;
    public final TextView tvCancelAll;
    public final TextView tvEstimated;
    public final TextView tvLeftAddress;
    public final TextView tvLeftAvailable;
    public final TextView tvLeftReceive;
    public final TextView tvNoBandwidth;
    public final TextView tvReceive;
    public final TextView tvResource;
    public final TextView tvTitle;
    public final View vMiddleTwo;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FgVoteComfirmBinding(RelativeLayout relativeLayout, Button button, ErrorEdiTextLayout errorEdiTextLayout, EditText editText, LinearLayout linearLayout, LinearLayout linearLayout2, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, LinearLayout linearLayout3, RelativeLayout relativeLayout4, RelativeLayout relativeLayout5, RelativeLayout relativeLayout6, RelativeLayout relativeLayout7, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, View view) {
        this.rootView = relativeLayout;
        this.btSend = button;
        this.eetContent = errorEdiTextLayout;
        this.etVoteAmount = editText;
        this.ivCloseTwo = linearLayout;
        this.llCancelVoteError = linearLayout2;
        this.rlEstimated = relativeLayout2;
        this.rlFirst = relativeLayout3;
        this.rlPassword = linearLayout3;
        this.rlSecond = relativeLayout4;
        this.rlThird = relativeLayout5;
        this.rlTopTwo = relativeLayout6;
        this.root = relativeLayout7;
        this.tvAddress = textView;
        this.tvCancelAll = textView2;
        this.tvEstimated = textView3;
        this.tvLeftAddress = textView4;
        this.tvLeftAvailable = textView5;
        this.tvLeftReceive = textView6;
        this.tvNoBandwidth = textView7;
        this.tvReceive = textView8;
        this.tvResource = textView9;
        this.tvTitle = textView10;
        this.vMiddleTwo = view;
    }

    public static FgVoteComfirmBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FgVoteComfirmBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fg_vote_comfirm, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FgVoteComfirmBinding bind(View view) {
        int i = R.id.bt_send;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_send);
        if (button != null) {
            i = R.id.eet_content;
            ErrorEdiTextLayout errorEdiTextLayout = (ErrorEdiTextLayout) ViewBindings.findChildViewById(view, R.id.eet_content);
            if (errorEdiTextLayout != null) {
                i = R.id.et_vote_amount;
                EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.et_vote_amount);
                if (editText != null) {
                    i = R.id.iv_close_two;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.iv_close_two);
                    if (linearLayout != null) {
                        i = R.id.ll_cancel_vote_error;
                        LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_cancel_vote_error);
                        if (linearLayout2 != null) {
                            i = R.id.rl_estimated;
                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_estimated);
                            if (relativeLayout != null) {
                                i = R.id.rl_first;
                                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_first);
                                if (relativeLayout2 != null) {
                                    i = R.id.rl_password;
                                    LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.rl_password);
                                    if (linearLayout3 != null) {
                                        i = R.id.rl_second;
                                        RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_second);
                                        if (relativeLayout3 != null) {
                                            i = R.id.rl_third;
                                            RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_third);
                                            if (relativeLayout4 != null) {
                                                i = R.id.rl_top_two;
                                                RelativeLayout relativeLayout5 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_top_two);
                                                if (relativeLayout5 != null) {
                                                    RelativeLayout relativeLayout6 = (RelativeLayout) view;
                                                    i = R.id.tv_address;
                                                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address);
                                                    if (textView != null) {
                                                        i = R.id.tv_cancel_all;
                                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_cancel_all);
                                                        if (textView2 != null) {
                                                            i = R.id.tv_estimated;
                                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_estimated);
                                                            if (textView3 != null) {
                                                                i = R.id.tv_left_address;
                                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_left_address);
                                                                if (textView4 != null) {
                                                                    i = R.id.tv_left_available;
                                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_left_available);
                                                                    if (textView5 != null) {
                                                                        i = R.id.tv_left_receive;
                                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_left_receive);
                                                                        if (textView6 != null) {
                                                                            i = R.id.tv_no_bandwidth;
                                                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_no_bandwidth);
                                                                            if (textView7 != null) {
                                                                                i = R.id.tv_receive;
                                                                                TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_receive);
                                                                                if (textView8 != null) {
                                                                                    i = R.id.tv_resource;
                                                                                    TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_resource);
                                                                                    if (textView9 != null) {
                                                                                        i = R.id.tv_title;
                                                                                        TextView textView10 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                                                                                        if (textView10 != null) {
                                                                                            i = R.id.v_middle_two;
                                                                                            View findChildViewById = ViewBindings.findChildViewById(view, R.id.v_middle_two);
                                                                                            if (findChildViewById != null) {
                                                                                                return new FgVoteComfirmBinding(relativeLayout6, button, errorEdiTextLayout, editText, linearLayout, linearLayout2, relativeLayout, relativeLayout2, linearLayout3, relativeLayout3, relativeLayout4, relativeLayout5, relativeLayout6, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, findChildViewById);
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
