package com.tron.wallet.business.tabdapp.component;

import android.animation.ArgbEvaluator;
import android.animation.FloatEvaluator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.material.appbar.AppBarLayout;
import com.tron.wallet.business.tabdapp.browser.BrowserTabManager;
import com.tron.wallet.business.tabdapp.browser.search.BrowserSearchActivity;
import com.tron.wallet.business.tabdapp.browser.tabs.BrowserTab;
import com.tron.wallet.business.tabdapp.home.utils.DAppUrlUtils;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.CustomFontUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.LayoutDappTitleViewBinding;
import com.tron.wallet.db.SpAPI;
import com.tronlinkpro.wallet.R;
public class DAppTitleView extends RelativeLayout implements AppBarLayout.OnOffsetChangedListener {
    public static int webTitleHeight = UIUtils.dip2px(86.0f);
    ImageView backView;
    View backgroundView;
    private LayoutDappTitleViewBinding binding;
    private final ArgbEvaluator colorEvaluator;
    private String currentUrl;
    TextView etUrl;
    private final FloatEvaluator evaluator;
    public ImageView homeView;
    View ivLock;
    public View ivRefresh;
    ImageView menuView;
    public View rlTitle;
    private BrowserTabManager.Observer tabObserver;
    public TextView tabsView;
    public TextView tvTitle;

    public DAppTitleView(Context context) {
        this(context, null);
    }

    public DAppTitleView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DAppTitleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.evaluator = new FloatEvaluator();
        this.colorEvaluator = new ArgbEvaluator();
        mappingId(LayoutInflater.from(context).inflate(R.layout.layout_dapp_title_view, (ViewGroup) this, true));
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.tron.wallet.R.styleable.DAppTitleView);
        int i2 = obtainStyledAttributes.getInt(0, 0);
        obtainStyledAttributes.recycle();
        updatePageMode(i2);
    }

    public void mappingId(View view) {
        LayoutDappTitleViewBinding bind = LayoutDappTitleViewBinding.bind(view);
        this.backgroundView = bind.bgTitleView;
        this.backView = bind.ivBrowserBack;
        this.menuView = bind.ivMenu;
        this.etUrl = bind.etUrl;
        this.ivLock = bind.icLock;
        this.tvTitle = bind.tvWebTitle;
        this.rlTitle = bind.rlTitle;
        this.homeView = bind.ivBrowserMain;
        this.tabsView = bind.tvBrowserTab;
        this.ivRefresh = bind.ivRefresh;
    }

    private void updatePageMode(int i) {
        if (i == 0) {
            this.tvTitle.setVisibility(View.VISIBLE);
            this.tvTitle.setTextSize(20.0f);
            this.tvTitle.setText(getResources().getString(R.string.main_tab_discovery));
            this.tvTitle.setTypeface(CustomFontUtils.getTypeface(getContext(), 1));
            this.tvTitle.setGravity(3);
            this.rlTitle.setVisibility(View.GONE);
            this.backView.setVisibility(View.GONE);
            this.homeView.setVisibility(View.GONE);
            return;
        }
        this.tvTitle.setVisibility(View.GONE);
        this.rlTitle.setVisibility(View.VISIBLE);
        this.backView.setVisibility(View.VISIBLE);
        this.homeView.setVisibility(View.VISIBLE);
        this.tabsView.getBackground().setTint(getResources().getColor(R.color.black_23));
        this.menuView.getDrawable().setTint(getResources().getColor(R.color.black_23));
        this.rlTitle.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (SpAPI.THIS.getAppStatus().isMainland()) {
                    return;
                }
                BrowserSearchActivity.startActivity(getContext(), currentUrl);
                AnalyticsHelper.logEvent(AnalyticsHelper.DAppWebEvent.CLICK_TOP_SEARCH);
            }
        });
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        post(new Runnable() {
            @Override
            public final void run() {
                lambda$onAttachedToWindow$0();
            }
        });
        final BrowserTabManager browserTabManager = BrowserTabManager.getInstance();
        BrowserTabManager.Observer observer = new BrowserTabManager.Observer() {
            @Override
            public final void onTabChanged() {
                lambda$onAttachedToWindow$1(browserTabManager);
            }
        };
        this.tabObserver = observer;
        browserTabManager.addObserver(observer);
    }

    public void lambda$onAttachedToWindow$0() {
        webTitleHeight = getMeasuredHeight();
    }

    public void lambda$onAttachedToWindow$1(BrowserTabManager browserTabManager) {
        updateButtonStates();
        this.tabsView.setText(String.valueOf(browserTabManager.getTabCount()));
    }

    public void updateButtonStates() {
        BrowserTabManager browserTabManager = BrowserTabManager.getInstance();
        BrowserTab tabAt = browserTabManager.getTabAt(browserTabManager.getCurrentTabIndex());
        boolean z = tabAt.getViewType() == 0;
        if (!tabAt.isCanBackToMain()) {
            this.backView.setEnabled(browserTabManager.getWebView().canGoBack());
        } else {
            this.backView.setEnabled(!z);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.tabObserver != null) {
            BrowserTabManager.getInstance().removeObserver(this.tabObserver);
        }
        this.binding = null;
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        float min = Math.min(1.0f, Math.abs(i / Math.max(appBarLayout.getTotalScrollRange(), 1)) * 2.0f);
        this.backgroundView.setAlpha(Float.valueOf(Math.min(this.evaluator.evaluate(2.0f * min, (Number) 0, (Number) 1).floatValue(), 1.0f)).floatValue());
        int intValue = ((Integer) this.colorEvaluator.evaluate(min, -1, Integer.valueOf(getResources().getColor(R.color.black_23)))).intValue();
        this.tvTitle.setTextColor(intValue);
        this.tabsView.setTextColor(intValue);
        this.tabsView.getBackground().setTint(intValue);
        this.menuView.getDrawable().setTint(intValue);
    }

    public DAppTitleView setClickBackListener(View.OnClickListener onClickListener) {
        this.backView.setOnClickListener(onClickListener);
        return this;
    }

    public DAppTitleView setClickHomeListener(View.OnClickListener onClickListener) {
        this.homeView.setOnClickListener(onClickListener);
        return this;
    }

    public DAppTitleView setClickTabsListener(View.OnClickListener onClickListener) {
        this.tabsView.setOnClickListener(onClickListener);
        return this;
    }

    public DAppTitleView setClickMenuListener(View.OnClickListener onClickListener) {
        this.menuView.setOnClickListener(onClickListener);
        return this;
    }

    public void setData(String str) {
        this.currentUrl = str;
        if (str == null) {
            return;
        }
        this.etUrl.setText(DAppUrlUtils.getHost(str));
        this.ivLock.setVisibility(str.startsWith("https://") ? View.VISIBLE : View.GONE);
    }

    public void updateTabCount() {
        this.tabsView.setText(String.valueOf(BrowserTabManager.getInstance().getTabCount()));
    }

    public void setBackgroundView(int i) {
        this.backgroundView.setBackgroundColor(i);
    }
}
