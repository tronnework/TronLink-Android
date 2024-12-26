package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tronlinkpro.wallet.R;
public final class AcTokenReportBinding implements ViewBinding {
    public final Button btnSubmit;
    public final Button btnToTronscan;
    public final RelativeLayout ckAirdrop;
    public final TextView ckAirdropLabel;
    public final RelativeLayout ckApproveScam;
    public final RelativeLayout ckOther;
    public final RelativeLayout ckScam;
    public final RelativeLayout ckScamToken;
    public final RelativeLayout ckUnableTrade;
    public final EditText etContact;
    public final EditText etReportDesc;
    public final ImageView ivAirdrop;
    public final ImageView ivApproveScam;
    public final ImageView ivOther;
    public final ImageView ivScam;
    public final ImageView ivScamToken;
    public final SimpleDraweeView ivTokenReportIcon;
    public final ImageView ivUnableTrade;
    private final ScrollView rootView;
    public final TextView tvReportDesLabel;
    public final TextView tvReportLabel;
    public final TextView tvTokenReportName;

    @Override
    public ScrollView getRoot() {
        return this.rootView;
    }

    private AcTokenReportBinding(ScrollView scrollView, Button button, Button button2, RelativeLayout relativeLayout, TextView textView, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, RelativeLayout relativeLayout5, RelativeLayout relativeLayout6, EditText editText, EditText editText2, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, SimpleDraweeView simpleDraweeView, ImageView imageView6, TextView textView2, TextView textView3, TextView textView4) {
        this.rootView = scrollView;
        this.btnSubmit = button;
        this.btnToTronscan = button2;
        this.ckAirdrop = relativeLayout;
        this.ckAirdropLabel = textView;
        this.ckApproveScam = relativeLayout2;
        this.ckOther = relativeLayout3;
        this.ckScam = relativeLayout4;
        this.ckScamToken = relativeLayout5;
        this.ckUnableTrade = relativeLayout6;
        this.etContact = editText;
        this.etReportDesc = editText2;
        this.ivAirdrop = imageView;
        this.ivApproveScam = imageView2;
        this.ivOther = imageView3;
        this.ivScam = imageView4;
        this.ivScamToken = imageView5;
        this.ivTokenReportIcon = simpleDraweeView;
        this.ivUnableTrade = imageView6;
        this.tvReportDesLabel = textView2;
        this.tvReportLabel = textView3;
        this.tvTokenReportName = textView4;
    }

    public static AcTokenReportBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcTokenReportBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_token_report, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcTokenReportBinding bind(View view) {
        int i = R.id.btn_submit;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_submit);
        if (button != null) {
            i = R.id.btn_to_tronscan;
            Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.btn_to_tronscan);
            if (button2 != null) {
                i = R.id.ck_airdrop;
                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ck_airdrop);
                if (relativeLayout != null) {
                    i = R.id.ck_airdrop_label;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.ck_airdrop_label);
                    if (textView != null) {
                        i = R.id.ck_approve_scam;
                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ck_approve_scam);
                        if (relativeLayout2 != null) {
                            i = R.id.ck_other;
                            RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ck_other);
                            if (relativeLayout3 != null) {
                                i = R.id.ck_scam;
                                RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ck_scam);
                                if (relativeLayout4 != null) {
                                    i = R.id.ck_scam_token;
                                    RelativeLayout relativeLayout5 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ck_scam_token);
                                    if (relativeLayout5 != null) {
                                        i = R.id.ck_unable_trade;
                                        RelativeLayout relativeLayout6 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ck_unable_trade);
                                        if (relativeLayout6 != null) {
                                            i = R.id.et_contact;
                                            EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.et_contact);
                                            if (editText != null) {
                                                i = R.id.et_report_desc;
                                                EditText editText2 = (EditText) ViewBindings.findChildViewById(view, R.id.et_report_desc);
                                                if (editText2 != null) {
                                                    i = R.id.iv_airdrop;
                                                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_airdrop);
                                                    if (imageView != null) {
                                                        i = R.id.iv_approve_scam;
                                                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_approve_scam);
                                                        if (imageView2 != null) {
                                                            i = R.id.iv_other;
                                                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_other);
                                                            if (imageView3 != null) {
                                                                i = R.id.iv_scam;
                                                                ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_scam);
                                                                if (imageView4 != null) {
                                                                    i = R.id.iv_scam_token;
                                                                    ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_scam_token);
                                                                    if (imageView5 != null) {
                                                                        i = R.id.iv_token_report_icon;
                                                                        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.iv_token_report_icon);
                                                                        if (simpleDraweeView != null) {
                                                                            i = R.id.iv_unable_trade;
                                                                            ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_unable_trade);
                                                                            if (imageView6 != null) {
                                                                                i = R.id.tv_report_des_label;
                                                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_report_des_label);
                                                                                if (textView2 != null) {
                                                                                    i = R.id.tv_report_label;
                                                                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_report_label);
                                                                                    if (textView3 != null) {
                                                                                        i = R.id.tv_token_report_name;
                                                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_token_report_name);
                                                                                        if (textView4 != null) {
                                                                                            return new AcTokenReportBinding((ScrollView) view, button, button2, relativeLayout, textView, relativeLayout2, relativeLayout3, relativeLayout4, relativeLayout5, relativeLayout6, editText, editText2, imageView, imageView2, imageView3, imageView4, imageView5, simpleDraweeView, imageView6, textView2, textView3, textView4);
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
