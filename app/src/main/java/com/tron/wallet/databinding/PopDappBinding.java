package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tronlinkpro.wallet.R;
public final class PopDappBinding implements ViewBinding {
    public final Button cancle;
    public final FrameLayout confirm;
    public final Button go;
    public final SimpleDraweeView imageRight;
    public final LinearLayout ivCloseOne;
    public final ImageView ivDown;
    public final LinearLayout left;
    public final View line;
    public final LinearLayout llPassword;
    public final LinearLayout llSelected;
    public final Button ok;
    public final Button quickBt;
    public final TextView quickText1;
    public final TextView quickText2;
    public final LinearLayout right;
    public final RelativeLayout rlCost;
    public final RelativeLayout rlFunction;
    public final RelativeLayout rlLeft;
    public final RelativeLayout rlParams;
    public final RelativeLayout rlTop;
    private final ScrollView rootView;
    public final RelativeLayout rootview;
    public final Button safeBt;
    public final TextView safeText1;
    public final TextView safeText2;
    public final ImageView to;
    public final TextView tvApproveTips;
    public final TextView tvBlance;
    public final TextView tvCost;
    public final TextView tvCost2;
    public final TextView tvFunction;
    public final TextView tvFunction2;
    public final TextView tvLeftname;
    public final TextView tvName;
    public final TextView tvParams;
    public final TextView tvParams2;
    public final TextView tvTips;
    public final TextView tvToaddress;
    public final TextView tvTotalblance;
    public final TextView tvUrl;
    public final View vLine;
    public final Button whiteBt;
    public final TextView whiteText1;
    public final TextView whiteText2;

    @Override
    public ScrollView getRoot() {
        return this.rootView;
    }

    private PopDappBinding(ScrollView scrollView, Button button, FrameLayout frameLayout, Button button2, SimpleDraweeView simpleDraweeView, LinearLayout linearLayout, ImageView imageView, LinearLayout linearLayout2, View view, LinearLayout linearLayout3, LinearLayout linearLayout4, Button button3, Button button4, TextView textView, TextView textView2, LinearLayout linearLayout5, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, RelativeLayout relativeLayout5, RelativeLayout relativeLayout6, Button button5, TextView textView3, TextView textView4, ImageView imageView2, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14, TextView textView15, TextView textView16, TextView textView17, TextView textView18, View view2, Button button6, TextView textView19, TextView textView20) {
        this.rootView = scrollView;
        this.cancle = button;
        this.confirm = frameLayout;
        this.go = button2;
        this.imageRight = simpleDraweeView;
        this.ivCloseOne = linearLayout;
        this.ivDown = imageView;
        this.left = linearLayout2;
        this.line = view;
        this.llPassword = linearLayout3;
        this.llSelected = linearLayout4;
        this.ok = button3;
        this.quickBt = button4;
        this.quickText1 = textView;
        this.quickText2 = textView2;
        this.right = linearLayout5;
        this.rlCost = relativeLayout;
        this.rlFunction = relativeLayout2;
        this.rlLeft = relativeLayout3;
        this.rlParams = relativeLayout4;
        this.rlTop = relativeLayout5;
        this.rootview = relativeLayout6;
        this.safeBt = button5;
        this.safeText1 = textView3;
        this.safeText2 = textView4;
        this.to = imageView2;
        this.tvApproveTips = textView5;
        this.tvBlance = textView6;
        this.tvCost = textView7;
        this.tvCost2 = textView8;
        this.tvFunction = textView9;
        this.tvFunction2 = textView10;
        this.tvLeftname = textView11;
        this.tvName = textView12;
        this.tvParams = textView13;
        this.tvParams2 = textView14;
        this.tvTips = textView15;
        this.tvToaddress = textView16;
        this.tvTotalblance = textView17;
        this.tvUrl = textView18;
        this.vLine = view2;
        this.whiteBt = button6;
        this.whiteText1 = textView19;
        this.whiteText2 = textView20;
    }

    public static PopDappBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopDappBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.pop_dapp, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopDappBinding bind(View view) {
        int i = R.id.cancle;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.cancle);
        if (button != null) {
            i = R.id.confirm;
            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.confirm);
            if (frameLayout != null) {
                i = R.id.go;
                Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.go);
                if (button2 != null) {
                    i = R.id.image_right;
                    SimpleDraweeView simpleDraweeView = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.image_right);
                    if (simpleDraweeView != null) {
                        i = R.id.iv_close_one;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.iv_close_one);
                        if (linearLayout != null) {
                            i = R.id.iv_down;
                            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_down);
                            if (imageView != null) {
                                i = R.id.left;
                                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.left);
                                if (linearLayout2 != null) {
                                    i = R.id.line;
                                    View findChildViewById = ViewBindings.findChildViewById(view, R.id.line);
                                    if (findChildViewById != null) {
                                        i = R.id.ll_password;
                                        LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_password);
                                        if (linearLayout3 != null) {
                                            i = R.id.ll_selected;
                                            LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_selected);
                                            if (linearLayout4 != null) {
                                                i = R.id.ok;
                                                Button button3 = (Button) ViewBindings.findChildViewById(view, R.id.ok);
                                                if (button3 != null) {
                                                    i = R.id.quick_bt;
                                                    Button button4 = (Button) ViewBindings.findChildViewById(view, R.id.quick_bt);
                                                    if (button4 != null) {
                                                        i = R.id.quick_text1;
                                                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.quick_text1);
                                                        if (textView != null) {
                                                            i = R.id.quick_text2;
                                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.quick_text2);
                                                            if (textView2 != null) {
                                                                i = R.id.right;
                                                                LinearLayout linearLayout5 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.right);
                                                                if (linearLayout5 != null) {
                                                                    i = R.id.rl_cost;
                                                                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_cost);
                                                                    if (relativeLayout != null) {
                                                                        i = R.id.rl_function;
                                                                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_function);
                                                                        if (relativeLayout2 != null) {
                                                                            i = R.id.rl_left;
                                                                            RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_left);
                                                                            if (relativeLayout3 != null) {
                                                                                i = R.id.rl_params;
                                                                                RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_params);
                                                                                if (relativeLayout4 != null) {
                                                                                    i = R.id.rl_top;
                                                                                    RelativeLayout relativeLayout5 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_top);
                                                                                    if (relativeLayout5 != null) {
                                                                                        i = R.id.rootview;
                                                                                        RelativeLayout relativeLayout6 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rootview);
                                                                                        if (relativeLayout6 != null) {
                                                                                            i = R.id.safe_bt;
                                                                                            Button button5 = (Button) ViewBindings.findChildViewById(view, R.id.safe_bt);
                                                                                            if (button5 != null) {
                                                                                                i = R.id.safe_text1;
                                                                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.safe_text1);
                                                                                                if (textView3 != null) {
                                                                                                    i = R.id.safe_text2;
                                                                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.safe_text2);
                                                                                                    if (textView4 != null) {
                                                                                                        i = R.id.to;
                                                                                                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.to);
                                                                                                        if (imageView2 != null) {
                                                                                                            i = R.id.tv_approve_tips;
                                                                                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_approve_tips);
                                                                                                            if (textView5 != null) {
                                                                                                                i = R.id.tv_blance;
                                                                                                                TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_blance);
                                                                                                                if (textView6 != null) {
                                                                                                                    i = R.id.tv_cost;
                                                                                                                    TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_cost);
                                                                                                                    if (textView7 != null) {
                                                                                                                        i = R.id.tv_cost2;
                                                                                                                        TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_cost2);
                                                                                                                        if (textView8 != null) {
                                                                                                                            i = R.id.tv_function;
                                                                                                                            TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_function);
                                                                                                                            if (textView9 != null) {
                                                                                                                                i = R.id.tv_function2;
                                                                                                                                TextView textView10 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_function2);
                                                                                                                                if (textView10 != null) {
                                                                                                                                    i = R.id.tv_leftname;
                                                                                                                                    TextView textView11 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_leftname);
                                                                                                                                    if (textView11 != null) {
                                                                                                                                        i = R.id.tv_name;
                                                                                                                                        TextView textView12 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name);
                                                                                                                                        if (textView12 != null) {
                                                                                                                                            i = R.id.tv_params;
                                                                                                                                            TextView textView13 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_params);
                                                                                                                                            if (textView13 != null) {
                                                                                                                                                i = R.id.tv_params2;
                                                                                                                                                TextView textView14 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_params2);
                                                                                                                                                if (textView14 != null) {
                                                                                                                                                    i = R.id.tv_tips;
                                                                                                                                                    TextView textView15 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_tips);
                                                                                                                                                    if (textView15 != null) {
                                                                                                                                                        i = R.id.tv_toaddress;
                                                                                                                                                        TextView textView16 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_toaddress);
                                                                                                                                                        if (textView16 != null) {
                                                                                                                                                            i = R.id.tv_totalblance;
                                                                                                                                                            TextView textView17 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_totalblance);
                                                                                                                                                            if (textView17 != null) {
                                                                                                                                                                i = R.id.tv_url;
                                                                                                                                                                TextView textView18 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_url);
                                                                                                                                                                if (textView18 != null) {
                                                                                                                                                                    i = R.id.v_line;
                                                                                                                                                                    View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.v_line);
                                                                                                                                                                    if (findChildViewById2 != null) {
                                                                                                                                                                        i = R.id.white_bt;
                                                                                                                                                                        Button button6 = (Button) ViewBindings.findChildViewById(view, R.id.white_bt);
                                                                                                                                                                        if (button6 != null) {
                                                                                                                                                                            i = R.id.white_text1;
                                                                                                                                                                            TextView textView19 = (TextView) ViewBindings.findChildViewById(view, R.id.white_text1);
                                                                                                                                                                            if (textView19 != null) {
                                                                                                                                                                                i = R.id.white_text2;
                                                                                                                                                                                TextView textView20 = (TextView) ViewBindings.findChildViewById(view, R.id.white_text2);
                                                                                                                                                                                if (textView20 != null) {
                                                                                                                                                                                    return new PopDappBinding((ScrollView) view, button, frameLayout, button2, simpleDraweeView, linearLayout, imageView, linearLayout2, findChildViewById, linearLayout3, linearLayout4, button3, button4, textView, textView2, linearLayout5, relativeLayout, relativeLayout2, relativeLayout3, relativeLayout4, relativeLayout5, relativeLayout6, button5, textView3, textView4, imageView2, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14, textView15, textView16, textView17, textView18, findChildViewById2, button6, textView19, textView20);
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
