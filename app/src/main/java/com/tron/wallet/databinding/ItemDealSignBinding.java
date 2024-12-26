package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.countdownview.CountdownView;
import com.tronlinkpro.wallet.R;
public final class ItemDealSignBinding implements ViewBinding {
    public final TextView allSignCount;
    public final Button btSign;
    public final TextView currentSignCount;
    public final CountdownView cvCountdownView;
    public final ImageView icProgress;
    public final LinearLayout llCountdown;
    public final RelativeLayout llHash;
    public final RelativeLayout llNote;
    public final RelativeLayout llReceive;
    public final LinearLayout llTransactionSuccessTime;
    public final RelativeLayout llType;
    public final TextView needSignCount;
    public final ProgressBar progressBar;
    public final TextView resourceType;
    public final RelativeLayout rlOriginator;
    public final RelativeLayout rlProgressState;
    public final LinearLayout rootView;
    private final LinearLayout rootView_;
    public final RecyclerView rvSignAddress;
    public final View signFailure;
    public final TextView timeRemain;
    public final Button transactionAction;
    public final TextView transactionFrom;
    public final TextView transactionHash;
    public final TextView transactionTime;
    public final TextView transactionTo;
    public final TextView tv1;
    public final TextView tvHashTitle;
    public final TextView tvInitiator;
    public final TextView tvNote;
    public final TextView tvNoteMore;
    public final TextView tvReceivables;
    public final TextView tvSubFrom;
    public final TextView tvSubTo;
    public final TextView tvTitle;
    public final TextView tvTransContent;
    public final TextView tvTransType;
    public final TextView tvType;

    @Override
    public LinearLayout getRoot() {
        return this.rootView_;
    }

    private ItemDealSignBinding(LinearLayout linearLayout, TextView textView, Button button, TextView textView2, CountdownView countdownView, ImageView imageView, LinearLayout linearLayout2, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, LinearLayout linearLayout3, RelativeLayout relativeLayout4, TextView textView3, ProgressBar progressBar, TextView textView4, RelativeLayout relativeLayout5, RelativeLayout relativeLayout6, LinearLayout linearLayout4, RecyclerView recyclerView, View view, TextView textView5, Button button2, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14, TextView textView15, TextView textView16, TextView textView17, TextView textView18, TextView textView19, TextView textView20, TextView textView21) {
        this.rootView_ = linearLayout;
        this.allSignCount = textView;
        this.btSign = button;
        this.currentSignCount = textView2;
        this.cvCountdownView = countdownView;
        this.icProgress = imageView;
        this.llCountdown = linearLayout2;
        this.llHash = relativeLayout;
        this.llNote = relativeLayout2;
        this.llReceive = relativeLayout3;
        this.llTransactionSuccessTime = linearLayout3;
        this.llType = relativeLayout4;
        this.needSignCount = textView3;
        this.progressBar = progressBar;
        this.resourceType = textView4;
        this.rlOriginator = relativeLayout5;
        this.rlProgressState = relativeLayout6;
        this.rootView = linearLayout4;
        this.rvSignAddress = recyclerView;
        this.signFailure = view;
        this.timeRemain = textView5;
        this.transactionAction = button2;
        this.transactionFrom = textView6;
        this.transactionHash = textView7;
        this.transactionTime = textView8;
        this.transactionTo = textView9;
        this.tv1 = textView10;
        this.tvHashTitle = textView11;
        this.tvInitiator = textView12;
        this.tvNote = textView13;
        this.tvNoteMore = textView14;
        this.tvReceivables = textView15;
        this.tvSubFrom = textView16;
        this.tvSubTo = textView17;
        this.tvTitle = textView18;
        this.tvTransContent = textView19;
        this.tvTransType = textView20;
        this.tvType = textView21;
    }

    public static ItemDealSignBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemDealSignBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_deal_sign, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemDealSignBinding bind(View view) {
        int i = R.id.all_sign_count;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.all_sign_count);
        if (textView != null) {
            i = R.id.bt_sign;
            Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_sign);
            if (button != null) {
                i = R.id.current_sign_count;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.current_sign_count);
                if (textView2 != null) {
                    i = R.id.cv_countdownView;
                    CountdownView countdownView = (CountdownView) ViewBindings.findChildViewById(view, R.id.cv_countdownView);
                    if (countdownView != null) {
                        i = R.id.ic_progress;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.ic_progress);
                        if (imageView != null) {
                            i = R.id.ll_countdown;
                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_countdown);
                            if (linearLayout != null) {
                                i = R.id.ll_hash;
                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_hash);
                                if (relativeLayout != null) {
                                    i = R.id.ll_note;
                                    RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_note);
                                    if (relativeLayout2 != null) {
                                        i = R.id.ll_receive;
                                        RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_receive);
                                        if (relativeLayout3 != null) {
                                            i = R.id.ll_transaction_success_time;
                                            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_transaction_success_time);
                                            if (linearLayout2 != null) {
                                                i = R.id.ll_type;
                                                RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_type);
                                                if (relativeLayout4 != null) {
                                                    i = R.id.need_sign_count;
                                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.need_sign_count);
                                                    if (textView3 != null) {
                                                        i = R.id.progress_bar;
                                                        ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, R.id.progress_bar);
                                                        if (progressBar != null) {
                                                            i = R.id.resource_type;
                                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.resource_type);
                                                            if (textView4 != null) {
                                                                i = R.id.rl_originator;
                                                                RelativeLayout relativeLayout5 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_originator);
                                                                if (relativeLayout5 != null) {
                                                                    i = R.id.rl_progress_state;
                                                                    RelativeLayout relativeLayout6 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_progress_state);
                                                                    if (relativeLayout6 != null) {
                                                                        LinearLayout linearLayout3 = (LinearLayout) view;
                                                                        i = R.id.rv_sign_address;
                                                                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rv_sign_address);
                                                                        if (recyclerView != null) {
                                                                            i = R.id.sign_failure;
                                                                            View findChildViewById = ViewBindings.findChildViewById(view, R.id.sign_failure);
                                                                            if (findChildViewById != null) {
                                                                                i = R.id.time_remain;
                                                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.time_remain);
                                                                                if (textView5 != null) {
                                                                                    i = R.id.transaction_action;
                                                                                    Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.transaction_action);
                                                                                    if (button2 != null) {
                                                                                        i = R.id.transaction_from;
                                                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.transaction_from);
                                                                                        if (textView6 != null) {
                                                                                            i = R.id.transaction_hash;
                                                                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.transaction_hash);
                                                                                            if (textView7 != null) {
                                                                                                i = R.id.transaction_time;
                                                                                                TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.transaction_time);
                                                                                                if (textView8 != null) {
                                                                                                    i = R.id.transaction_to;
                                                                                                    TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.transaction_to);
                                                                                                    if (textView9 != null) {
                                                                                                        i = R.id.tv_1;
                                                                                                        TextView textView10 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_1);
                                                                                                        if (textView10 != null) {
                                                                                                            i = R.id.tv_hash_title;
                                                                                                            TextView textView11 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_hash_title);
                                                                                                            if (textView11 != null) {
                                                                                                                i = R.id.tv_initiator;
                                                                                                                TextView textView12 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_initiator);
                                                                                                                if (textView12 != null) {
                                                                                                                    i = R.id.tv_note;
                                                                                                                    TextView textView13 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_note);
                                                                                                                    if (textView13 != null) {
                                                                                                                        i = R.id.tv_note_more;
                                                                                                                        TextView textView14 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_note_more);
                                                                                                                        if (textView14 != null) {
                                                                                                                            i = R.id.tv_receivables;
                                                                                                                            TextView textView15 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_receivables);
                                                                                                                            if (textView15 != null) {
                                                                                                                                i = R.id.tv_sub_from;
                                                                                                                                TextView textView16 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_sub_from);
                                                                                                                                if (textView16 != null) {
                                                                                                                                    i = R.id.tv_sub_to;
                                                                                                                                    TextView textView17 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_sub_to);
                                                                                                                                    if (textView17 != null) {
                                                                                                                                        i = R.id.tv_title;
                                                                                                                                        TextView textView18 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                                                                                                                                        if (textView18 != null) {
                                                                                                                                            i = R.id.tv_trans_content;
                                                                                                                                            TextView textView19 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_trans_content);
                                                                                                                                            if (textView19 != null) {
                                                                                                                                                i = R.id.tv_trans_type;
                                                                                                                                                TextView textView20 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_trans_type);
                                                                                                                                                if (textView20 != null) {
                                                                                                                                                    i = R.id.tv_type;
                                                                                                                                                    TextView textView21 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_type);
                                                                                                                                                    if (textView21 != null) {
                                                                                                                                                        return new ItemDealSignBinding(linearLayout3, textView, button, textView2, countdownView, imageView, linearLayout, relativeLayout, relativeLayout2, relativeLayout3, linearLayout2, relativeLayout4, textView3, progressBar, textView4, relativeLayout5, relativeLayout6, linearLayout3, recyclerView, findChildViewById, textView5, button2, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14, textView15, textView16, textView17, textView18, textView19, textView20, textView21);
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
