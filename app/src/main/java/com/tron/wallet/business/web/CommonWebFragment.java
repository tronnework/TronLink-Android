package com.tron.wallet.business.web;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.tabdapp.browser.BrowserTabManager;
import com.tron.wallet.business.tabdapp.browser.controller.BrowserBookMarkManager;
import com.tron.wallet.business.tabdapp.home.DAppBrowserFragment;
import com.tron.wallet.business.tabdapp.home.DAppBrowserModel;
import com.tron.wallet.business.tabdapp.home.DAppBrowserPresenter;
import com.tron.wallet.business.tabdapp.home.utils.BrowserMenuPopupView;
import com.tron.wallet.business.tabdapp.home.utils.DAppUrlUtils;
import com.tron.wallet.business.web.CommonWebFragment;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.UIUtils;
import com.tronlinkpro.wallet.R;
import j$.util.Objects;
import java.util.function.Consumer;
public class CommonWebFragment extends DAppBrowserFragment<DAppBrowserPresenter, DAppBrowserModel> {
    private static final String KEY_INIT_URL = "key_init_url";
    private static final String KEY_INJECT_TRON_WEB = "key_inject_tron_web";
    private static final String KEY_IS_FINANCIAL = "key_is_financial";
    private static final String KEY_TITLE = "key_title";
    public static final int PAGE_COMMON_WEB = 3;
    private boolean injectTronWeb = true;
    private boolean isFinancial = false;
    private Consumer<String> titleChangeCallback;

    @Override
    protected boolean shouldRecordHistory() {
        return false;
    }

    public static CommonWebFragment getInstance(String str, String str2, boolean z) {
        CommonWebFragment commonWebFragment = new CommonWebFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TITLE, str);
        bundle.putString(KEY_INIT_URL, str2);
        bundle.putBoolean(KEY_INJECT_TRON_WEB, z);
        commonWebFragment.setArguments(bundle);
        commonWebFragment.setCommonWeb(true);
        return commonWebFragment;
    }

    public static CommonWebFragment getInstance(String str, String str2, boolean z, boolean z2) {
        CommonWebFragment commonWebFragment = new CommonWebFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TITLE, str);
        bundle.putString(KEY_INIT_URL, str2);
        bundle.putBoolean(KEY_INJECT_TRON_WEB, z);
        bundle.putBoolean(KEY_IS_FINANCIAL, z2);
        commonWebFragment.setArguments(bundle);
        commonWebFragment.setCommonWeb(true);
        return commonWebFragment;
    }

    @Override
    public boolean handleBackPressed() {
        if (this.browserView.back() || getActivity() == null) {
            return true;
        }
        getActivity().finish();
        return true;
    }

    @Override
    public void processData() {
        String str;
        String str2;
        super.processData();
        Bundle arguments = getArguments();
        if (arguments != null) {
            String string = arguments.getString(KEY_INIT_URL);
            String string2 = arguments.getString(KEY_TITLE);
            this.injectTronWeb = arguments.getBoolean(KEY_INJECT_TRON_WEB);
            this.isFinancial = arguments.getBoolean(KEY_IS_FINANCIAL);
            str = string;
            str2 = string2;
        } else {
            str = "";
            str2 = str;
        }
        this.titleView.setBackgroundView(getContext().getResources().getColor(R.color.gray_F7F));
        this.titleView.tvTitle.setText(R.string.loading_dapp);
        this.titleView.tvTitle.setVisibility(View.VISIBLE);
        this.titleView.tvTitle.setTextSize(16.0f);
        this.titleView.rlTitle.setVisibility(View.GONE);
        ViewGroup.LayoutParams layoutParams = this.titleView.tvTitle.getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            layoutParams2.addRule(0, this.titleView.ivRefresh.getId());
            layoutParams2.addRule(1, this.titleView.homeView.getId());
            int dip2px = UIUtils.dip2px(15.0f);
            layoutParams2.leftMargin = dip2px;
            layoutParams2.rightMargin = dip2px;
            this.titleView.tvTitle.setLayoutParams(layoutParams);
        }
        this.titleView.tabsView.setVisibility(View.GONE);
        this.titleView.homeView.setImageResource(R.mipmap.common_web_close);
        this.titleView.ivRefresh.setVisibility(View.VISIBLE);
        this.titleView.ivRefresh.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(final View view) {
                BrowserTabManager.getInstance().refresh();
                RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
                rotateAnimation.setDuration(500L);
                rotateAnimation.setRepeatCount(-1);
                view.startAnimation(rotateAnimation);
                rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationStart(Animation animation) {
                        view.setEnabled(false);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        view.setEnabled(true);
                    }
                });
            }
        });
        this.titleView.setClickMenuListener(new fun3()).setClickHomeListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (getActivity() != null) {
                    getActivity().finish();
                }
            }
        });
        this.titleChangeCallback = new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$processData$0((String) obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        };
        BrowserTabManager.getInstance().addTitleChangedObserver(this.titleChangeCallback);
        if (this.isFinancial) {
            this.titleView.setVisibility(View.GONE);
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        BrowserTabManager.getInstance().startURL(str, str2, true, true, this.injectTronWeb, this.isFinancial);
    }

    class fun3 extends NoDoubleClickListener2 {
        public static void lambda$onNoDoubleClick$0(Void r0) throws Exception {
        }

        fun3() {
        }

        @Override
        protected void onNoDoubleClick(View view) {
            boolean z;
            try {
                boolean isDebugAble = browserView.getWebView().isDebugAble();
                String url = browserView.getWebView().getUrl();
                if (!BrowserBookMarkManager.getInstance().isExists(url) && !BrowserBookMarkManager.getInstance().isExists(DAppUrlUtils.removeUrlSuffixParameter(url))) {
                    z = false;
                    BrowserMenuPopupView.showUp(getIContext(), titleView, 3, isDebugAble, browserView.getWebView().isInjectTronWeb(), z, getMenuCallback(new io.reactivex.functions.Consumer() {
                        @Override
                        public final void accept(Object obj) {
                            CommonWebFragment.3.lambda$onNoDoubleClick$0((Void) obj);
                        }
                    }));
                    AnalyticsHelper.logEvent(AnalyticsHelper.DAppWebEvent.CLICK_MORE);
                }
                z = true;
                BrowserMenuPopupView.showUp(getIContext(), titleView, 3, isDebugAble, browserView.getWebView().isInjectTronWeb(), z, getMenuCallback(new io.reactivex.functions.Consumer() {
                    @Override
                    public final void accept(Object obj) {
                        CommonWebFragment.3.lambda$onNoDoubleClick$0((Void) obj);
                    }
                }));
                AnalyticsHelper.logEvent(AnalyticsHelper.DAppWebEvent.CLICK_MORE);
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
    }

    public void lambda$processData$0(String str) {
        this.titleView.tvTitle.setText(str);
    }

    @Override
    public void onPageFinished(boolean z) {
        Animation animation;
        super.onPageFinished(z);
        try {
            if (this.titleView.ivRefresh != null && (animation = this.titleView.ivRefresh.getAnimation()) != null) {
                animation.cancel();
            }
            if (z) {
                this.titleView.tvTitle.setText(R.string.web_browser);
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.titleChangeCallback != null) {
            BrowserTabManager.getInstance().removeTitleObserver(this.titleChangeCallback);
        }
        BrowserTabManager.getInstance().releaseCurrentContent(this.browserView);
    }
}
