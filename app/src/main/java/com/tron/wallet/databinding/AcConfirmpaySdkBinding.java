package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tronlinkpro.wallet.R;
public final class AcConfirmpaySdkBinding implements ViewBinding {
    public final Button btGo;
    public final Button cancle;
    public final EditText etPassword;
    public final SimpleDraweeView imageRight;
    public final ImageView ivArrow;
    public final ImageView ivFromLogo;
    public final LinearLayout llBottom;
    public final LinearLayout llInfo;
    public final RelativeLayout llSelected;
    public final Button quickBt;
    public final TextView quickText1;
    public final TextView quickText2;
    public final LinearLayout rlFrom;
    public final RelativeLayout rlRoot;
    public final LinearLayout rlTo;
    private final RelativeLayout rootView;
    public final RelativeLayout rootview;
    public final Button safeBt;
    public final TextView safeText1;
    public final TextView safeText2;
    public final TextView tvBlance;
    public final TextView tvCost;
    public final TextView tvCostTitle;
    public final TextView tvName;
    public final Button tvPay;
    public final TextView tvPayValue;
    public final TextView tvToaddress;
    public final TextView tvTotalblance;
    public final TextView tvUrl;
    public final View viewBorder;
    public final View viewOuter;
    public final Button whiteBt;
    public final TextView whiteText1;
    public final TextView whiteText2;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcConfirmpaySdkBinding(RelativeLayout relativeLayout, Button button, Button button2, EditText editText, SimpleDraweeView simpleDraweeView, ImageView imageView, ImageView imageView2, LinearLayout linearLayout, LinearLayout linearLayout2, RelativeLayout relativeLayout2, Button button3, TextView textView, TextView textView2, LinearLayout linearLayout3, RelativeLayout relativeLayout3, LinearLayout linearLayout4, RelativeLayout relativeLayout4, Button button4, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, Button button5, TextView textView9, TextView textView10, TextView textView11, TextView textView12, View view, View view2, Button button6, TextView textView13, TextView textView14) {
        this.rootView = relativeLayout;
        this.btGo = button;
        this.cancle = button2;
        this.etPassword = editText;
        this.imageRight = simpleDraweeView;
        this.ivArrow = imageView;
        this.ivFromLogo = imageView2;
        this.llBottom = linearLayout;
        this.llInfo = linearLayout2;
        this.llSelected = relativeLayout2;
        this.quickBt = button3;
        this.quickText1 = textView;
        this.quickText2 = textView2;
        this.rlFrom = linearLayout3;
        this.rlRoot = relativeLayout3;
        this.rlTo = linearLayout4;
        this.rootview = relativeLayout4;
        this.safeBt = button4;
        this.safeText1 = textView3;
        this.safeText2 = textView4;
        this.tvBlance = textView5;
        this.tvCost = textView6;
        this.tvCostTitle = textView7;
        this.tvName = textView8;
        this.tvPay = button5;
        this.tvPayValue = textView9;
        this.tvToaddress = textView10;
        this.tvTotalblance = textView11;
        this.tvUrl = textView12;
        this.viewBorder = view;
        this.viewOuter = view2;
        this.whiteBt = button6;
        this.whiteText1 = textView13;
        this.whiteText2 = textView14;
    }

    public static AcConfirmpaySdkBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcConfirmpaySdkBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_confirmpay_sdk, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcConfirmpaySdkBinding bind(View view) {
        int i = R.id.bt_go;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_go);
        if (button != null) {
            i = R.id.cancle;
            Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.cancle);
            if (button2 != null) {
                i = R.id.et_password;
                EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.et_password);
                if (editText != null) {
                    i = R.id.image_right;
                    SimpleDraweeView simpleDraweeView = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.image_right);
                    if (simpleDraweeView != null) {
                        i = R.id.iv_arrow;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_arrow);
                        if (imageView != null) {
                            i = R.id.iv_from_logo;
                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_from_logo);
                            if (imageView2 != null) {
                                i = R.id.ll_bottom;
                                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_bottom);
                                if (linearLayout != null) {
                                    i = R.id.ll_info;
                                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_info);
                                    if (linearLayout2 != null) {
                                        i = R.id.ll_selected;
                                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_selected);
                                        if (relativeLayout != null) {
                                            i = R.id.quick_bt;
                                            Button button3 = (Button) ViewBindings.findChildViewById(view, R.id.quick_bt);
                                            if (button3 != null) {
                                                i = R.id.quick_text1;
                                                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.quick_text1);
                                                if (textView != null) {
                                                    i = R.id.quick_text2;
                                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.quick_text2);
                                                    if (textView2 != null) {
                                                        i = R.id.rl_from;
                                                        LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.rl_from);
                                                        if (linearLayout3 != null) {
                                                            i = R.id.rl_root;
                                                            RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_root);
                                                            if (relativeLayout2 != null) {
                                                                i = R.id.rl_to;
                                                                LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.rl_to);
                                                                if (linearLayout4 != null) {
                                                                    RelativeLayout relativeLayout3 = (RelativeLayout) view;
                                                                    i = R.id.safe_bt;
                                                                    Button button4 = (Button) ViewBindings.findChildViewById(view, R.id.safe_bt);
                                                                    if (button4 != null) {
                                                                        i = R.id.safe_text1;
                                                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.safe_text1);
                                                                        if (textView3 != null) {
                                                                            i = R.id.safe_text2;
                                                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.safe_text2);
                                                                            if (textView4 != null) {
                                                                                i = R.id.tv_blance;
                                                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_blance);
                                                                                if (textView5 != null) {
                                                                                    i = R.id.tv_cost;
                                                                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_cost);
                                                                                    if (textView6 != null) {
                                                                                        i = R.id.tv_cost_title;
                                                                                        TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_cost_title);
                                                                                        if (textView7 != null) {
                                                                                            i = R.id.tv_name;
                                                                                            TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name);
                                                                                            if (textView8 != null) {
                                                                                                i = R.id.tv_pay;
                                                                                                Button button5 = (Button) ViewBindings.findChildViewById(view, R.id.tv_pay);
                                                                                                if (button5 != null) {
                                                                                                    i = R.id.tv_pay_value;
                                                                                                    TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_pay_value);
                                                                                                    if (textView9 != null) {
                                                                                                        i = R.id.tv_toaddress;
                                                                                                        TextView textView10 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_toaddress);
                                                                                                        if (textView10 != null) {
                                                                                                            i = R.id.tv_totalblance;
                                                                                                            TextView textView11 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_totalblance);
                                                                                                            if (textView11 != null) {
                                                                                                                i = R.id.tv_url;
                                                                                                                TextView textView12 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_url);
                                                                                                                if (textView12 != null) {
                                                                                                                    i = R.id.view_border;
                                                                                                                    View findChildViewById = ViewBindings.findChildViewById(view, R.id.view_border);
                                                                                                                    if (findChildViewById != null) {
                                                                                                                        i = R.id.view_outer;
                                                                                                                        View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.view_outer);
                                                                                                                        if (findChildViewById2 != null) {
                                                                                                                            i = R.id.white_bt;
                                                                                                                            Button button6 = (Button) ViewBindings.findChildViewById(view, R.id.white_bt);
                                                                                                                            if (button6 != null) {
                                                                                                                                i = R.id.white_text1;
                                                                                                                                TextView textView13 = (TextView) ViewBindings.findChildViewById(view, R.id.white_text1);
                                                                                                                                if (textView13 != null) {
                                                                                                                                    i = R.id.white_text2;
                                                                                                                                    TextView textView14 = (TextView) ViewBindings.findChildViewById(view, R.id.white_text2);
                                                                                                                                    if (textView14 != null) {
                                                                                                                                        return new AcConfirmpaySdkBinding(relativeLayout3, button, button2, editText, simpleDraweeView, imageView, imageView2, linearLayout, linearLayout2, relativeLayout, button3, textView, textView2, linearLayout3, relativeLayout2, linearLayout4, relativeLayout3, button4, textView3, textView4, textView5, textView6, textView7, textView8, button5, textView9, textView10, textView11, textView12, findChildViewById, findChildViewById2, button6, textView13, textView14);
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
