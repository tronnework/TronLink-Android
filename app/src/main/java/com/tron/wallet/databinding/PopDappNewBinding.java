package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.business.confirm.fg.component.DappConfirmButton;
import com.tronlinkpro.wallet.R;
public final class PopDappNewBinding implements ViewBinding {
    public final Button cancle;
    public final FrameLayout confirmContent;
    public final Button go;
    public final ConstraintLayout llApproveTips;
    public final LinearLayout llConfirmLayout;
    public final LinearLayout llSelected;
    public final DappConfirmButton ok;
    public final Button quickBt;
    public final TextView quickText1;
    public final TextView quickText2;
    private final ScrollView rootView;
    public final RelativeLayout rootview;
    public final Button safeBt;
    public final TextView safeText1;
    public final TextView safeText2;
    public final Button whiteBt;
    public final TextView whiteText1;
    public final TextView whiteText2;

    @Override
    public ScrollView getRoot() {
        return this.rootView;
    }

    private PopDappNewBinding(ScrollView scrollView, Button button, FrameLayout frameLayout, Button button2, ConstraintLayout constraintLayout, LinearLayout linearLayout, LinearLayout linearLayout2, DappConfirmButton dappConfirmButton, Button button3, TextView textView, TextView textView2, RelativeLayout relativeLayout, Button button4, TextView textView3, TextView textView4, Button button5, TextView textView5, TextView textView6) {
        this.rootView = scrollView;
        this.cancle = button;
        this.confirmContent = frameLayout;
        this.go = button2;
        this.llApproveTips = constraintLayout;
        this.llConfirmLayout = linearLayout;
        this.llSelected = linearLayout2;
        this.ok = dappConfirmButton;
        this.quickBt = button3;
        this.quickText1 = textView;
        this.quickText2 = textView2;
        this.rootview = relativeLayout;
        this.safeBt = button4;
        this.safeText1 = textView3;
        this.safeText2 = textView4;
        this.whiteBt = button5;
        this.whiteText1 = textView5;
        this.whiteText2 = textView6;
    }

    public static PopDappNewBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopDappNewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.pop_dapp_new, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopDappNewBinding bind(View view) {
        int i = R.id.cancle;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.cancle);
        if (button != null) {
            i = R.id.confirm_content;
            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.confirm_content);
            if (frameLayout != null) {
                i = R.id.go;
                Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.go);
                if (button2 != null) {
                    i = R.id.ll_approve_tips;
                    ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.ll_approve_tips);
                    if (constraintLayout != null) {
                        i = R.id.ll_confirm_layout;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_confirm_layout);
                        if (linearLayout != null) {
                            i = R.id.ll_selected;
                            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_selected);
                            if (linearLayout2 != null) {
                                i = R.id.ok;
                                DappConfirmButton dappConfirmButton = (DappConfirmButton) ViewBindings.findChildViewById(view, R.id.ok);
                                if (dappConfirmButton != null) {
                                    i = R.id.quick_bt;
                                    Button button3 = (Button) ViewBindings.findChildViewById(view, R.id.quick_bt);
                                    if (button3 != null) {
                                        i = R.id.quick_text1;
                                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.quick_text1);
                                        if (textView != null) {
                                            i = R.id.quick_text2;
                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.quick_text2);
                                            if (textView2 != null) {
                                                i = R.id.rootview;
                                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rootview);
                                                if (relativeLayout != null) {
                                                    i = R.id.safe_bt;
                                                    Button button4 = (Button) ViewBindings.findChildViewById(view, R.id.safe_bt);
                                                    if (button4 != null) {
                                                        i = R.id.safe_text1;
                                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.safe_text1);
                                                        if (textView3 != null) {
                                                            i = R.id.safe_text2;
                                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.safe_text2);
                                                            if (textView4 != null) {
                                                                i = R.id.white_bt;
                                                                Button button5 = (Button) ViewBindings.findChildViewById(view, R.id.white_bt);
                                                                if (button5 != null) {
                                                                    i = R.id.white_text1;
                                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.white_text1);
                                                                    if (textView5 != null) {
                                                                        i = R.id.white_text2;
                                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.white_text2);
                                                                        if (textView6 != null) {
                                                                            return new PopDappNewBinding((ScrollView) view, button, frameLayout, button2, constraintLayout, linearLayout, linearLayout2, dappConfirmButton, button3, textView, textView2, relativeLayout, button4, textView3, textView4, button5, textView5, textView6);
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
