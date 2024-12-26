package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.tron.wallet.common.components.xtablayout.XTabLayoutV2;
import com.tronlinkpro.wallet.R;
public final class ActivityDelegateEnergy1Binding implements ViewBinding {
    public final Button btnNext;
    public final View divider;
    public final RelativeLayout headerLayout;
    public final ImageView ivBack;
    public final FragmentContainerView layoutSimilarAddress;
    public final RelativeLayout rlList;
    private final RelativeLayout rootView;
    public final FrameLayout searchResultView;
    public final XTabLayoutV2 tablayout;
    public final TextView tvAddressBook;
    public final TextView tvMainTitle;
    public final TextView tvMultiSign;
    public final TextView tvMultiWarning;
    public final TextView tvStep;
    public final ViewPager2 viewpager;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ActivityDelegateEnergy1Binding(RelativeLayout relativeLayout, Button button, View view, RelativeLayout relativeLayout2, ImageView imageView, FragmentContainerView fragmentContainerView, RelativeLayout relativeLayout3, FrameLayout frameLayout, XTabLayoutV2 xTabLayoutV2, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, ViewPager2 viewPager2) {
        this.rootView = relativeLayout;
        this.btnNext = button;
        this.divider = view;
        this.headerLayout = relativeLayout2;
        this.ivBack = imageView;
        this.layoutSimilarAddress = fragmentContainerView;
        this.rlList = relativeLayout3;
        this.searchResultView = frameLayout;
        this.tablayout = xTabLayoutV2;
        this.tvAddressBook = textView;
        this.tvMainTitle = textView2;
        this.tvMultiSign = textView3;
        this.tvMultiWarning = textView4;
        this.tvStep = textView5;
        this.viewpager = viewPager2;
    }

    public static ActivityDelegateEnergy1Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityDelegateEnergy1Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_delegate_energy_1, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityDelegateEnergy1Binding bind(View view) {
        int i = R.id.btn_next;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_next);
        if (button != null) {
            i = R.id.divider;
            View findChildViewById = ViewBindings.findChildViewById(view, R.id.divider);
            if (findChildViewById != null) {
                i = R.id.header_layout;
                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.header_layout);
                if (relativeLayout != null) {
                    i = R.id.iv_back;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_back);
                    if (imageView != null) {
                        i = R.id.layout_similar_address;
                        FragmentContainerView fragmentContainerView = (FragmentContainerView) ViewBindings.findChildViewById(view, R.id.layout_similar_address);
                        if (fragmentContainerView != null) {
                            i = R.id.rl_list;
                            RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_list);
                            if (relativeLayout2 != null) {
                                i = R.id.search_result_view;
                                FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.search_result_view);
                                if (frameLayout != null) {
                                    i = R.id.tablayout;
                                    XTabLayoutV2 xTabLayoutV2 = (XTabLayoutV2) ViewBindings.findChildViewById(view, R.id.tablayout);
                                    if (xTabLayoutV2 != null) {
                                        i = R.id.tv_address_book;
                                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address_book);
                                        if (textView != null) {
                                            i = R.id.tv_main_title;
                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_main_title);
                                            if (textView2 != null) {
                                                i = R.id.tv_multi_sign;
                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_multi_sign);
                                                if (textView3 != null) {
                                                    i = R.id.tv_multi_warning;
                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_multi_warning);
                                                    if (textView4 != null) {
                                                        i = R.id.tv_step;
                                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_step);
                                                        if (textView5 != null) {
                                                            i = R.id.viewpager;
                                                            ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, R.id.viewpager);
                                                            if (viewPager2 != null) {
                                                                return new ActivityDelegateEnergy1Binding((RelativeLayout) view, button, findChildViewById, relativeLayout, imageView, fragmentContainerView, relativeLayout2, frameLayout, xTabLayoutV2, textView, textView2, textView3, textView4, textView5, viewPager2);
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
