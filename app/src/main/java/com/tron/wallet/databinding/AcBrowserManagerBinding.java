package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.tron.wallet.common.components.browser.BrowserTabBottomToolbar;
import com.tron.wallet.common.components.xtablayout.XTabLayoutV2;
import com.tronlinkpro.wallet.R;
public final class AcBrowserManagerBinding implements ViewBinding {
    public final BrowserTabBottomToolbar frBottomBar;
    public final ImageView ivBrowserBack;
    public final ImageView ivBrowserClear;
    public final ImageView ivBrowserHistoryClear;
    public final ImageView ivBrowserNew;
    public final LinearLayout liBrowserClear;
    public final LinearLayout liBrowserHistoryClear;
    public final LinearLayout liHistoryManager;
    public final LinearLayout liTabManager;
    public final RelativeLayout liTablayout;
    public final LinearLayout llRecordClear;
    private final RelativeLayout rootView;
    public final TextView tvBrowserClear;
    public final TextView tvBrowserHistoryClear;
    public final ViewPager2 vpContent;
    public final XTabLayoutV2 xTablayout;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcBrowserManagerBinding(RelativeLayout relativeLayout, BrowserTabBottomToolbar browserTabBottomToolbar, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, RelativeLayout relativeLayout2, LinearLayout linearLayout5, TextView textView, TextView textView2, ViewPager2 viewPager2, XTabLayoutV2 xTabLayoutV2) {
        this.rootView = relativeLayout;
        this.frBottomBar = browserTabBottomToolbar;
        this.ivBrowserBack = imageView;
        this.ivBrowserClear = imageView2;
        this.ivBrowserHistoryClear = imageView3;
        this.ivBrowserNew = imageView4;
        this.liBrowserClear = linearLayout;
        this.liBrowserHistoryClear = linearLayout2;
        this.liHistoryManager = linearLayout3;
        this.liTabManager = linearLayout4;
        this.liTablayout = relativeLayout2;
        this.llRecordClear = linearLayout5;
        this.tvBrowserClear = textView;
        this.tvBrowserHistoryClear = textView2;
        this.vpContent = viewPager2;
        this.xTablayout = xTabLayoutV2;
    }

    public static AcBrowserManagerBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcBrowserManagerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_browser_manager, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcBrowserManagerBinding bind(View view) {
        int i = R.id.fr_bottom_bar;
        BrowserTabBottomToolbar browserTabBottomToolbar = (BrowserTabBottomToolbar) ViewBindings.findChildViewById(view, R.id.fr_bottom_bar);
        if (browserTabBottomToolbar != null) {
            i = R.id.iv_browser_back;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_browser_back);
            if (imageView != null) {
                i = R.id.iv_browser_clear;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_browser_clear);
                if (imageView2 != null) {
                    i = R.id.iv_browser_history_clear;
                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_browser_history_clear);
                    if (imageView3 != null) {
                        i = R.id.iv_browser_new;
                        ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_browser_new);
                        if (imageView4 != null) {
                            i = R.id.li_browser_clear;
                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_browser_clear);
                            if (linearLayout != null) {
                                i = R.id.li_browser_history_clear;
                                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_browser_history_clear);
                                if (linearLayout2 != null) {
                                    i = R.id.li_history_manager;
                                    LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_history_manager);
                                    if (linearLayout3 != null) {
                                        i = R.id.li_tab_manager;
                                        LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_tab_manager);
                                        if (linearLayout4 != null) {
                                            i = R.id.li_tablayout;
                                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.li_tablayout);
                                            if (relativeLayout != null) {
                                                i = R.id.ll_record_clear;
                                                LinearLayout linearLayout5 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_record_clear);
                                                if (linearLayout5 != null) {
                                                    i = R.id.tv_browser_clear;
                                                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_browser_clear);
                                                    if (textView != null) {
                                                        i = R.id.tv_browser_history_clear;
                                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_browser_history_clear);
                                                        if (textView2 != null) {
                                                            i = R.id.vp_content;
                                                            ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, R.id.vp_content);
                                                            if (viewPager2 != null) {
                                                                i = R.id.xTablayout;
                                                                XTabLayoutV2 xTabLayoutV2 = (XTabLayoutV2) ViewBindings.findChildViewById(view, R.id.xTablayout);
                                                                if (xTabLayoutV2 != null) {
                                                                    return new AcBrowserManagerBinding((RelativeLayout) view, browserTabBottomToolbar, imageView, imageView2, imageView3, imageView4, linearLayout, linearLayout2, linearLayout3, linearLayout4, relativeLayout, linearLayout5, textView, textView2, viewPager2, xTabLayoutV2);
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
