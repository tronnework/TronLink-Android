package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.tron.wallet.common.components.BannerRecyclerView;
import com.tron.wallet.common.components.indicator.DotViewPagerIndicator;
import com.tron.wallet.common.components.xtablayout.XTabLayoutV2;
import com.tronlinkpro.wallet.R;
public final class BrowserHeaderLayoutBinding implements ViewBinding {
    public final BannerRecyclerView bannerView;
    public final XTabLayoutV2 dappMenuTabs;
    public final ViewPager2 dappRecommendBookViewPager;
    public final DotViewPagerIndicator dotIndicator;
    public final ImageView ivBannerHolder;
    public final ImageView ivSearch;
    public final RelativeLayout llListContainer2;
    public final RelativeLayout rlRoot;
    private final RelativeLayout rootView;
    public final RelativeLayout searchView;
    public final RelativeLayout tabBook;
    public final TextView textDay;
    public final TextView tvSearch;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private BrowserHeaderLayoutBinding(RelativeLayout relativeLayout, BannerRecyclerView bannerRecyclerView, XTabLayoutV2 xTabLayoutV2, ViewPager2 viewPager2, DotViewPagerIndicator dotViewPagerIndicator, ImageView imageView, ImageView imageView2, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, RelativeLayout relativeLayout5, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.bannerView = bannerRecyclerView;
        this.dappMenuTabs = xTabLayoutV2;
        this.dappRecommendBookViewPager = viewPager2;
        this.dotIndicator = dotViewPagerIndicator;
        this.ivBannerHolder = imageView;
        this.ivSearch = imageView2;
        this.llListContainer2 = relativeLayout2;
        this.rlRoot = relativeLayout3;
        this.searchView = relativeLayout4;
        this.tabBook = relativeLayout5;
        this.textDay = textView;
        this.tvSearch = textView2;
    }

    public static BrowserHeaderLayoutBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static BrowserHeaderLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.browser_header_layout, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static BrowserHeaderLayoutBinding bind(View view) {
        int i = R.id.banner_view;
        BannerRecyclerView bannerRecyclerView = (BannerRecyclerView) ViewBindings.findChildViewById(view, R.id.banner_view);
        if (bannerRecyclerView != null) {
            i = R.id.dapp_menu_tabs;
            XTabLayoutV2 xTabLayoutV2 = (XTabLayoutV2) ViewBindings.findChildViewById(view, R.id.dapp_menu_tabs);
            if (xTabLayoutV2 != null) {
                i = R.id.dapp_recommend_book_view_pager;
                ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, R.id.dapp_recommend_book_view_pager);
                if (viewPager2 != null) {
                    i = R.id.dot_indicator;
                    DotViewPagerIndicator dotViewPagerIndicator = (DotViewPagerIndicator) ViewBindings.findChildViewById(view, R.id.dot_indicator);
                    if (dotViewPagerIndicator != null) {
                        i = R.id.iv_banner_holder;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_banner_holder);
                        if (imageView != null) {
                            i = R.id.iv_search;
                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_search);
                            if (imageView2 != null) {
                                i = R.id.ll_list_container2;
                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_list_container2);
                                if (relativeLayout != null) {
                                    RelativeLayout relativeLayout2 = (RelativeLayout) view;
                                    i = R.id.search_view;
                                    RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.search_view);
                                    if (relativeLayout3 != null) {
                                        i = R.id.tab_book;
                                        RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.tab_book);
                                        if (relativeLayout4 != null) {
                                            i = R.id.text_day;
                                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.text_day);
                                            if (textView != null) {
                                                i = R.id.tv_search;
                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_search);
                                                if (textView2 != null) {
                                                    return new BrowserHeaderLayoutBinding(relativeLayout2, bannerRecyclerView, xTabLayoutV2, viewPager2, dotViewPagerIndicator, imageView, imageView2, relativeLayout, relativeLayout2, relativeLayout3, relativeLayout4, textView, textView2);
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
