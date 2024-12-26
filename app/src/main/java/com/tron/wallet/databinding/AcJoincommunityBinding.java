package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.NoNetView;
import com.tronlinkpro.wallet.R;
public final class AcJoincommunityBinding implements ViewBinding {
    public final NoNetView noNetView;
    public final LinearLayout rl1;
    public final RelativeLayout rlEnArrow;
    public final RelativeLayout rlEnCopy;
    public final RelativeLayout rlTwitterArrow;
    public final RelativeLayout rlTwitterCopy;
    public final RelativeLayout rlWechatCopy;
    public final RelativeLayout rlZhArrow;
    public final RelativeLayout rlZhCopy;
    private final RelativeLayout rootView;
    public final TextView tvEn;
    public final TextView tvEn1;
    public final TextView tvTwitter;
    public final TextView tvTwitter1;
    public final TextView tvWechat;
    public final TextView tvWechat1;
    public final TextView tvZh;
    public final TextView tvZh1;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcJoincommunityBinding(RelativeLayout relativeLayout, NoNetView noNetView, LinearLayout linearLayout, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, RelativeLayout relativeLayout5, RelativeLayout relativeLayout6, RelativeLayout relativeLayout7, RelativeLayout relativeLayout8, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8) {
        this.rootView = relativeLayout;
        this.noNetView = noNetView;
        this.rl1 = linearLayout;
        this.rlEnArrow = relativeLayout2;
        this.rlEnCopy = relativeLayout3;
        this.rlTwitterArrow = relativeLayout4;
        this.rlTwitterCopy = relativeLayout5;
        this.rlWechatCopy = relativeLayout6;
        this.rlZhArrow = relativeLayout7;
        this.rlZhCopy = relativeLayout8;
        this.tvEn = textView;
        this.tvEn1 = textView2;
        this.tvTwitter = textView3;
        this.tvTwitter1 = textView4;
        this.tvWechat = textView5;
        this.tvWechat1 = textView6;
        this.tvZh = textView7;
        this.tvZh1 = textView8;
    }

    public static AcJoincommunityBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcJoincommunityBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_joincommunity, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcJoincommunityBinding bind(View view) {
        int i = R.id.no_net_view;
        NoNetView noNetView = (NoNetView) ViewBindings.findChildViewById(view, R.id.no_net_view);
        if (noNetView != null) {
            i = R.id.rl_1;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.rl_1);
            if (linearLayout != null) {
                i = R.id.rl_en_arrow;
                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_en_arrow);
                if (relativeLayout != null) {
                    i = R.id.rl_en_copy;
                    RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_en_copy);
                    if (relativeLayout2 != null) {
                        i = R.id.rl_twitter_arrow;
                        RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_twitter_arrow);
                        if (relativeLayout3 != null) {
                            i = R.id.rl_twitter_copy;
                            RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_twitter_copy);
                            if (relativeLayout4 != null) {
                                i = R.id.rl_wechat_copy;
                                RelativeLayout relativeLayout5 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_wechat_copy);
                                if (relativeLayout5 != null) {
                                    i = R.id.rl_zh_arrow;
                                    RelativeLayout relativeLayout6 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_zh_arrow);
                                    if (relativeLayout6 != null) {
                                        i = R.id.rl_zh_copy;
                                        RelativeLayout relativeLayout7 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_zh_copy);
                                        if (relativeLayout7 != null) {
                                            i = R.id.tv_en;
                                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_en);
                                            if (textView != null) {
                                                i = R.id.tv_en_1;
                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_en_1);
                                                if (textView2 != null) {
                                                    i = R.id.tv_twitter;
                                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_twitter);
                                                    if (textView3 != null) {
                                                        i = R.id.tv_twitter_1;
                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_twitter_1);
                                                        if (textView4 != null) {
                                                            i = R.id.tv_wechat;
                                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_wechat);
                                                            if (textView5 != null) {
                                                                i = R.id.tv_wechat_1;
                                                                TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_wechat_1);
                                                                if (textView6 != null) {
                                                                    i = R.id.tv_zh;
                                                                    TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_zh);
                                                                    if (textView7 != null) {
                                                                        i = R.id.tv_zh_1;
                                                                        TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_zh_1);
                                                                        if (textView8 != null) {
                                                                            return new AcJoincommunityBinding((RelativeLayout) view, noNetView, linearLayout, relativeLayout, relativeLayout2, relativeLayout3, relativeLayout4, relativeLayout5, relativeLayout6, relativeLayout7, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8);
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
