package com.tron.wallet.business.tabdapp.home.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.AttachPopupView;
import com.lxj.xpopup.enums.PopupAnimation;
import com.lxj.xpopup.util.XPopupUtils;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.UIUtils;
import com.tronlinkpro.wallet.R;
public class BrowserMenuPopupView extends AttachPopupView {
    private final BaseMenuClickCallback callback;
    View dappReport;
    boolean isDebug;
    boolean isFav;
    private boolean isInjectTronWeb;
    boolean isReportUrl;
    View liBookmark;
    View liDebug;
    View liOpenOutside;
    View liRefresh;
    View liShare;
    private int pageType;

    @Override
    public int getImplLayoutId() {
        return R.layout.pop_browser_menus;
    }

    public static void showUp(Context context, View view, int i, boolean z, boolean z2, boolean z3, BaseMenuClickCallback baseMenuClickCallback) {
        showUp(context, view, i, z, z2, z3, false, baseMenuClickCallback);
    }

    public static void showUp(Context context, View view, int i, boolean z, boolean z2, boolean z3, boolean z4, BaseMenuClickCallback baseMenuClickCallback) {
        new XPopup.Builder(context).maxWidth(((XPopupUtils.getScreenWidth(context) * 3) / 4) - UIUtils.dip2px(15.0f)).dismissOnTouchOutside(true).popupAnimation(PopupAnimation.NoAnimation).offsetX(UIUtils.dip2px(15.0f)).offsetY(UIUtils.dip2px(15.0f)).atView(view).asCustom(new BrowserMenuPopupView(context, i, baseMenuClickCallback, z, z2, z3, z4)).show();
    }

    public BrowserMenuPopupView(Context context, int i, BaseMenuClickCallback baseMenuClickCallback) {
        super(context);
        this.pageType = i;
        this.callback = baseMenuClickCallback;
    }

    public BrowserMenuPopupView(Context context, int i, BaseMenuClickCallback baseMenuClickCallback, boolean z, boolean z2, boolean z3, boolean z4) {
        super(context);
        this.pageType = i;
        this.callback = baseMenuClickCallback;
        this.isDebug = z;
        this.isFav = z3;
        this.isReportUrl = z4;
        this.isInjectTronWeb = z2;
    }

    public void setPageType(int i) {
        this.pageType = i;
        if (i == 0) {
            this.liBookmark.setVisibility(View.GONE);
            this.liShare.setVisibility(View.GONE);
            this.liOpenOutside.setVisibility(View.GONE);
            this.liDebug.setVisibility(View.GONE);
        } else if (i == 1) {
            this.liBookmark.setVisibility(View.VISIBLE);
            this.liShare.setVisibility(View.VISIBLE);
            this.liOpenOutside.setVisibility(View.VISIBLE);
            this.liDebug.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        View findViewById = findViewById(R.id.li_browser_newtab);
        this.liRefresh = findViewById(R.id.li_browser_refresh);
        this.liBookmark = findViewById(R.id.li_browser_bookmark);
        ImageView imageView = (ImageView) findViewById(R.id.iv_browser_bookmark);
        TextView textView = (TextView) findViewById(R.id.tv_browser_bookmark);
        View findViewById2 = findViewById(R.id.li_browser_history);
        View findViewById3 = findViewById(R.id.li_browser_bookmark_package);
        View findViewById4 = findViewById(R.id.li_browser_switch_wallet);
        View findViewById5 = findViewById(R.id.li_browser_dapp_link_manager);
        this.liShare = findViewById(R.id.li_browser_share);
        this.liOpenOutside = findViewById(R.id.li_browser_open_outside);
        this.liDebug = findViewById(R.id.li_browser_debug_page);
        ImageView imageView2 = (ImageView) findViewById(R.id.iv_browser_debug);
        TextView textView2 = (TextView) findViewById(R.id.tv_browser_debug);
        View findViewById6 = findViewById(R.id.li_browser_close_page);
        View findViewById7 = findViewById(R.id.divider_open_outside);
        this.dappReport = findViewById(R.id.li_browser_dapp_report);
        View findViewById8 = findViewById(R.id.divider_debug);
        if (this.pageType == 0) {
            this.liBookmark.setVisibility(View.GONE);
            this.liShare.setVisibility(View.GONE);
            this.liOpenOutside.setVisibility(View.GONE);
            this.liDebug.setVisibility(View.GONE);
            this.dappReport.setVisibility(View.GONE);
        } else {
            if (!this.isInjectTronWeb) {
                this.liDebug.setVisibility(View.GONE);
            } else {
                this.liDebug.setVisibility(View.VISIBLE);
            }
            if (this.pageType == 3) {
                this.liBookmark.setVisibility(View.GONE);
                this.liRefresh.setVisibility(View.GONE);
                findViewById.setVisibility(View.GONE);
                findViewById2.setVisibility(View.GONE);
                findViewById3.setVisibility(View.GONE);
                findViewById4.setVisibility(View.GONE);
                findViewById5.setVisibility(View.GONE);
                findViewById6.setVisibility(View.GONE);
                this.liShare.setVisibility(View.VISIBLE);
                this.liOpenOutside.setVisibility(View.VISIBLE);
                findViewById8.setVisibility(View.GONE);
                this.dappReport.setVisibility(View.GONE);
                if (!this.isInjectTronWeb) {
                    findViewById7.setVisibility(View.GONE);
                } else {
                    ViewGroup.LayoutParams layoutParams = findViewById7.getLayoutParams();
                    layoutParams.height = UIUtils.dip2px(1.0f);
                    findViewById7.setLayoutParams(layoutParams);
                    findViewById7.setVisibility(View.VISIBLE);
                }
            }
            if (IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.NILETEST || IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.SHASTA) {
                this.dappReport.setVisibility(View.GONE);
            }
        }
        if (this.isFav) {
            imageView.setImageResource(R.mipmap.ic_browser_add_bookmark_added);
            textView.setText(R.string.browser_remove_from_bookmark);
        } else {
            imageView.setImageResource(R.mipmap.ic_browser_add_bookmark);
            textView.setText(R.string.browser_add_to_bookmark);
        }
        if (this.isReportUrl) {
            this.dappReport.setVisibility(View.GONE);
        }
        if (this.isDebug) {
            imageView2.setImageResource(R.mipmap.ic_browser_debug_off);
            textView2.setText(R.string.browser_debug_off);
        } else {
            imageView2.setImageResource(R.mipmap.ic_browser_debug_on);
            textView2.setText(R.string.browser_debug_on);
        }
        if (this.callback == null) {
            return;
        }
        NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                switch (view.getId()) {
                    case R.id.li_browser_bookmark:
                        callback.onClickBookMark(isFav);
                        break;
                    case R.id.li_browser_bookmark_package:
                        callback.onClickBookMarkPackage(getContext());
                        break;
                    case R.id.li_browser_close_page:
                        callback.onClickClose();
                        break;
                    case R.id.li_browser_dapp_link_manager:
                        callback.onClickLinkManager(getContext());
                        break;
                    case R.id.li_browser_dapp_report:
                        callback.onClickDappReport();
                        break;
                    case R.id.li_browser_debug_page:
                        callback.onClickDebug(!isDebug);
                        break;
                    case R.id.li_browser_history:
                        callback.onClickHistory(getContext());
                        break;
                    case R.id.li_browser_newtab:
                        callback.onClickNewTab(getContext());
                        break;
                    case R.id.li_browser_open_outside:
                        callback.onClickOpenOutside();
                        break;
                    case R.id.li_browser_refresh:
                        callback.onClickRefresh();
                        break;
                    case R.id.li_browser_share:
                        callback.onClickShare();
                        break;
                    case R.id.li_browser_switch_wallet:
                        callback.onClickSwitchWallet();
                        break;
                }
                dismiss();
            }
        };
        findViewById.setOnClickListener(noDoubleClickListener);
        this.liRefresh.setOnClickListener(noDoubleClickListener);
        this.liBookmark.setOnClickListener(noDoubleClickListener);
        findViewById2.setOnClickListener(noDoubleClickListener);
        findViewById3.setOnClickListener(noDoubleClickListener);
        findViewById4.setOnClickListener(noDoubleClickListener);
        findViewById5.setOnClickListener(noDoubleClickListener);
        this.liShare.setOnClickListener(noDoubleClickListener);
        this.liOpenOutside.setOnClickListener(noDoubleClickListener);
        this.liDebug.setOnClickListener(noDoubleClickListener);
        findViewById6.setOnClickListener(noDoubleClickListener);
        this.dappReport.setOnClickListener(noDoubleClickListener);
    }
}
