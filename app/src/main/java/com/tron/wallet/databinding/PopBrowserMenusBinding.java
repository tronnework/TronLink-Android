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
import com.tronlinkpro.wallet.R;
public final class PopBrowserMenusBinding implements ViewBinding {
    public final View bookmarkDivider;
    public final View dividerDappReport;
    public final View dividerDebug;
    public final View dividerOpenOutside;
    public final ImageView ivBrowserBookmark;
    public final ImageView ivBrowserDebug;
    public final RelativeLayout liBrowserBookmark;
    public final RelativeLayout liBrowserBookmarkPackage;
    public final RelativeLayout liBrowserClosePage;
    public final RelativeLayout liBrowserDappLinkManager;
    public final RelativeLayout liBrowserDappReport;
    public final RelativeLayout liBrowserDebugPage;
    public final RelativeLayout liBrowserHistory;
    public final RelativeLayout liBrowserNewtab;
    public final RelativeLayout liBrowserOpenOutside;
    public final RelativeLayout liBrowserRefresh;
    public final RelativeLayout liBrowserShare;
    public final RelativeLayout liBrowserSwitchWallet;
    public final View refreshDivider;
    private final LinearLayout rootView;
    public final TextView tvBrowserBookmark;
    public final TextView tvBrowserDebug;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private PopBrowserMenusBinding(LinearLayout linearLayout, View view, View view2, View view3, View view4, ImageView imageView, ImageView imageView2, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, RelativeLayout relativeLayout5, RelativeLayout relativeLayout6, RelativeLayout relativeLayout7, RelativeLayout relativeLayout8, RelativeLayout relativeLayout9, RelativeLayout relativeLayout10, RelativeLayout relativeLayout11, RelativeLayout relativeLayout12, View view5, TextView textView, TextView textView2) {
        this.rootView = linearLayout;
        this.bookmarkDivider = view;
        this.dividerDappReport = view2;
        this.dividerDebug = view3;
        this.dividerOpenOutside = view4;
        this.ivBrowserBookmark = imageView;
        this.ivBrowserDebug = imageView2;
        this.liBrowserBookmark = relativeLayout;
        this.liBrowserBookmarkPackage = relativeLayout2;
        this.liBrowserClosePage = relativeLayout3;
        this.liBrowserDappLinkManager = relativeLayout4;
        this.liBrowserDappReport = relativeLayout5;
        this.liBrowserDebugPage = relativeLayout6;
        this.liBrowserHistory = relativeLayout7;
        this.liBrowserNewtab = relativeLayout8;
        this.liBrowserOpenOutside = relativeLayout9;
        this.liBrowserRefresh = relativeLayout10;
        this.liBrowserShare = relativeLayout11;
        this.liBrowserSwitchWallet = relativeLayout12;
        this.refreshDivider = view5;
        this.tvBrowserBookmark = textView;
        this.tvBrowserDebug = textView2;
    }

    public static PopBrowserMenusBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopBrowserMenusBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.pop_browser_menus, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopBrowserMenusBinding bind(View view) {
        int i = R.id.bookmark_divider;
        View findChildViewById = ViewBindings.findChildViewById(view, R.id.bookmark_divider);
        if (findChildViewById != null) {
            i = R.id.divider_dapp_report;
            View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.divider_dapp_report);
            if (findChildViewById2 != null) {
                i = R.id.divider_debug;
                View findChildViewById3 = ViewBindings.findChildViewById(view, R.id.divider_debug);
                if (findChildViewById3 != null) {
                    i = R.id.divider_open_outside;
                    View findChildViewById4 = ViewBindings.findChildViewById(view, R.id.divider_open_outside);
                    if (findChildViewById4 != null) {
                        i = R.id.iv_browser_bookmark;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_browser_bookmark);
                        if (imageView != null) {
                            i = R.id.iv_browser_debug;
                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_browser_debug);
                            if (imageView2 != null) {
                                i = R.id.li_browser_bookmark;
                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.li_browser_bookmark);
                                if (relativeLayout != null) {
                                    i = R.id.li_browser_bookmark_package;
                                    RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.li_browser_bookmark_package);
                                    if (relativeLayout2 != null) {
                                        i = R.id.li_browser_close_page;
                                        RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.li_browser_close_page);
                                        if (relativeLayout3 != null) {
                                            i = R.id.li_browser_dapp_link_manager;
                                            RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.li_browser_dapp_link_manager);
                                            if (relativeLayout4 != null) {
                                                i = R.id.li_browser_dapp_report;
                                                RelativeLayout relativeLayout5 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.li_browser_dapp_report);
                                                if (relativeLayout5 != null) {
                                                    i = R.id.li_browser_debug_page;
                                                    RelativeLayout relativeLayout6 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.li_browser_debug_page);
                                                    if (relativeLayout6 != null) {
                                                        i = R.id.li_browser_history;
                                                        RelativeLayout relativeLayout7 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.li_browser_history);
                                                        if (relativeLayout7 != null) {
                                                            i = R.id.li_browser_newtab;
                                                            RelativeLayout relativeLayout8 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.li_browser_newtab);
                                                            if (relativeLayout8 != null) {
                                                                i = R.id.li_browser_open_outside;
                                                                RelativeLayout relativeLayout9 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.li_browser_open_outside);
                                                                if (relativeLayout9 != null) {
                                                                    i = R.id.li_browser_refresh;
                                                                    RelativeLayout relativeLayout10 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.li_browser_refresh);
                                                                    if (relativeLayout10 != null) {
                                                                        i = R.id.li_browser_share;
                                                                        RelativeLayout relativeLayout11 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.li_browser_share);
                                                                        if (relativeLayout11 != null) {
                                                                            i = R.id.li_browser_switch_wallet;
                                                                            RelativeLayout relativeLayout12 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.li_browser_switch_wallet);
                                                                            if (relativeLayout12 != null) {
                                                                                i = R.id.refresh_divider;
                                                                                View findChildViewById5 = ViewBindings.findChildViewById(view, R.id.refresh_divider);
                                                                                if (findChildViewById5 != null) {
                                                                                    i = R.id.tv_browser_bookmark;
                                                                                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_browser_bookmark);
                                                                                    if (textView != null) {
                                                                                        i = R.id.tv_browser_debug;
                                                                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_browser_debug);
                                                                                        if (textView2 != null) {
                                                                                            return new PopBrowserMenusBinding((LinearLayout) view, findChildViewById, findChildViewById2, findChildViewById3, findChildViewById4, imageView, imageView2, relativeLayout, relativeLayout2, relativeLayout3, relativeLayout4, relativeLayout5, relativeLayout6, relativeLayout7, relativeLayout8, relativeLayout9, relativeLayout10, relativeLayout11, relativeLayout12, findChildViewById5, textView, textView2);
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
