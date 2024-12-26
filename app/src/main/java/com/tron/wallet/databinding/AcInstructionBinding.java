package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class AcInstructionBinding implements ViewBinding {
    public final Button btnKnowIt;
    public final ImageView circle0Spe;
    public final ImageView circle1Spe;
    public final ImageView circle2;
    public final ImageView circle2Spe;
    public final ImageView circle3Spe;
    public final ImageView circle4Spe;
    public final ImageView circle5Spe;
    public final TextView content;
    public final TextView doc0Spe;
    public final TextView doc1;
    public final TextView doc1Spe;
    public final TextView doc2;
    public final TextView doc2Spe;
    public final TextView doc3Spe;
    public final TextView doc4Spe;
    public final TextView doc5Spe;
    public final View line1Spe;
    public final View line2;
    public final View line2Spe;
    public final View line3Spe;
    public final View line4Spe;
    public final View line5Spe;
    public final RelativeLayout rlContent0Spe;
    public final RelativeLayout rlContent1Spe;
    public final RelativeLayout rlContent2;
    public final RelativeLayout rlContent2Spe;
    public final RelativeLayout rlContent3Spe;
    public final RelativeLayout rlContent4Spe;
    public final RelativeLayout rlContent5Spe;
    public final LinearLayout rlContentSpe;
    private final LinearLayout rootView;
    public final TextView tvTitle;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private AcInstructionBinding(LinearLayout linearLayout, Button button, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView imageView6, ImageView imageView7, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, View view, View view2, View view3, View view4, View view5, View view6, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, RelativeLayout relativeLayout5, RelativeLayout relativeLayout6, RelativeLayout relativeLayout7, LinearLayout linearLayout2, TextView textView10) {
        this.rootView = linearLayout;
        this.btnKnowIt = button;
        this.circle0Spe = imageView;
        this.circle1Spe = imageView2;
        this.circle2 = imageView3;
        this.circle2Spe = imageView4;
        this.circle3Spe = imageView5;
        this.circle4Spe = imageView6;
        this.circle5Spe = imageView7;
        this.content = textView;
        this.doc0Spe = textView2;
        this.doc1 = textView3;
        this.doc1Spe = textView4;
        this.doc2 = textView5;
        this.doc2Spe = textView6;
        this.doc3Spe = textView7;
        this.doc4Spe = textView8;
        this.doc5Spe = textView9;
        this.line1Spe = view;
        this.line2 = view2;
        this.line2Spe = view3;
        this.line3Spe = view4;
        this.line4Spe = view5;
        this.line5Spe = view6;
        this.rlContent0Spe = relativeLayout;
        this.rlContent1Spe = relativeLayout2;
        this.rlContent2 = relativeLayout3;
        this.rlContent2Spe = relativeLayout4;
        this.rlContent3Spe = relativeLayout5;
        this.rlContent4Spe = relativeLayout6;
        this.rlContent5Spe = relativeLayout7;
        this.rlContentSpe = linearLayout2;
        this.tvTitle = textView10;
    }

    public static AcInstructionBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcInstructionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_instruction, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcInstructionBinding bind(View view) {
        int i = R.id.btn_know_it;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_know_it);
        if (button != null) {
            i = R.id.circle_0_spe;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.circle_0_spe);
            if (imageView != null) {
                i = R.id.circle_1_spe;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.circle_1_spe);
                if (imageView2 != null) {
                    i = R.id.circle_2;
                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.circle_2);
                    if (imageView3 != null) {
                        i = R.id.circle_2_spe;
                        ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.circle_2_spe);
                        if (imageView4 != null) {
                            i = R.id.circle_3_spe;
                            ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, R.id.circle_3_spe);
                            if (imageView5 != null) {
                                i = R.id.circle_4_spe;
                                ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(view, R.id.circle_4_spe);
                                if (imageView6 != null) {
                                    i = R.id.circle_5_spe;
                                    ImageView imageView7 = (ImageView) ViewBindings.findChildViewById(view, R.id.circle_5_spe);
                                    if (imageView7 != null) {
                                        i = R.id.content;
                                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.content);
                                        if (textView != null) {
                                            i = R.id.doc0_spe;
                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.doc0_spe);
                                            if (textView2 != null) {
                                                i = R.id.doc1;
                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.doc1);
                                                if (textView3 != null) {
                                                    i = R.id.doc1_spe;
                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.doc1_spe);
                                                    if (textView4 != null) {
                                                        i = R.id.doc2;
                                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.doc2);
                                                        if (textView5 != null) {
                                                            i = R.id.doc2_spe;
                                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.doc2_spe);
                                                            if (textView6 != null) {
                                                                i = R.id.doc3_spe;
                                                                TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.doc3_spe);
                                                                if (textView7 != null) {
                                                                    i = R.id.doc4_spe;
                                                                    TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.doc4_spe);
                                                                    if (textView8 != null) {
                                                                        i = R.id.doc5_spe;
                                                                        TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.doc5_spe);
                                                                        if (textView9 != null) {
                                                                            i = R.id.line1_spe;
                                                                            View findChildViewById = ViewBindings.findChildViewById(view, R.id.line1_spe);
                                                                            if (findChildViewById != null) {
                                                                                i = R.id.line2;
                                                                                View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.line2);
                                                                                if (findChildViewById2 != null) {
                                                                                    i = R.id.line2_spe;
                                                                                    View findChildViewById3 = ViewBindings.findChildViewById(view, R.id.line2_spe);
                                                                                    if (findChildViewById3 != null) {
                                                                                        i = R.id.line3_spe;
                                                                                        View findChildViewById4 = ViewBindings.findChildViewById(view, R.id.line3_spe);
                                                                                        if (findChildViewById4 != null) {
                                                                                            i = R.id.line4_spe;
                                                                                            View findChildViewById5 = ViewBindings.findChildViewById(view, R.id.line4_spe);
                                                                                            if (findChildViewById5 != null) {
                                                                                                i = R.id.line5_spe;
                                                                                                View findChildViewById6 = ViewBindings.findChildViewById(view, R.id.line5_spe);
                                                                                                if (findChildViewById6 != null) {
                                                                                                    i = R.id.rl_content0_spe;
                                                                                                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_content0_spe);
                                                                                                    if (relativeLayout != null) {
                                                                                                        i = R.id.rl_content1_spe;
                                                                                                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_content1_spe);
                                                                                                        if (relativeLayout2 != null) {
                                                                                                            i = R.id.rl_content2;
                                                                                                            RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_content2);
                                                                                                            if (relativeLayout3 != null) {
                                                                                                                i = R.id.rl_content2_spe;
                                                                                                                RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_content2_spe);
                                                                                                                if (relativeLayout4 != null) {
                                                                                                                    i = R.id.rl_content3_spe;
                                                                                                                    RelativeLayout relativeLayout5 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_content3_spe);
                                                                                                                    if (relativeLayout5 != null) {
                                                                                                                        i = R.id.rl_content4_spe;
                                                                                                                        RelativeLayout relativeLayout6 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_content4_spe);
                                                                                                                        if (relativeLayout6 != null) {
                                                                                                                            i = R.id.rl_content5_spe;
                                                                                                                            RelativeLayout relativeLayout7 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_content5_spe);
                                                                                                                            if (relativeLayout7 != null) {
                                                                                                                                i = R.id.rl_content_spe;
                                                                                                                                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.rl_content_spe);
                                                                                                                                if (linearLayout != null) {
                                                                                                                                    i = R.id.tv_title;
                                                                                                                                    TextView textView10 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                                                                                                                                    if (textView10 != null) {
                                                                                                                                        return new AcInstructionBinding((LinearLayout) view, button, imageView, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, findChildViewById, findChildViewById2, findChildViewById3, findChildViewById4, findChildViewById5, findChildViewById6, relativeLayout, relativeLayout2, relativeLayout3, relativeLayout4, relativeLayout5, relativeLayout6, relativeLayout7, linearLayout, textView10);
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
