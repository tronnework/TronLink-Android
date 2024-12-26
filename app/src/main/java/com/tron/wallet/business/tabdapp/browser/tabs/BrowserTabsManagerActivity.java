package com.tron.wallet.business.tabdapp.browser.tabs;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewpager2.widget.ViewPager2;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.maintab.MainTabActivity;
import com.tron.wallet.business.tabdapp.browser.BrowserConstant;
import com.tron.wallet.business.tabdapp.browser.BrowserTabManager;
import com.tron.wallet.business.tabdapp.browser.tabs.BrowserTabManagerContract;
import com.tron.wallet.common.components.browser.BrowserTabBottomToolbar;
import com.tron.wallet.common.components.xtablayout.XTabLayoutV2;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.databinding.AcBrowserManagerBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
public class BrowserTabsManagerActivity extends BaseActivity<BrowserTabsManagerPresenter, BrowserTabsManagerModel> implements BrowserTabManagerContract.View {
    private AcBrowserManagerBinding binding;
    BrowserTabBottomToolbar browserTabBottomToolbar;
    BrowserTabManager browserTabManager;
    ImageView ivBrowserBack;
    ImageView ivBrowserClear;
    ImageView ivBrowserHistoryClear;
    ImageView ivBrowserNewTab;
    LinearLayout liBrowserClear;
    LinearLayout liBrowserHistoryClear;
    LinearLayout liHistoryManager;
    LinearLayout liTabManager;
    LinearLayout llRecordClear;
    TextView tvBrowserClear;
    TextView tvBrowserHistoryClear;
    ViewPager2 viewPager;
    XTabLayoutV2 xTabLayout;

    @Override
    public BrowserTabBottomToolbar getBrowserTabBottomToolbar() {
        return this.browserTabBottomToolbar;
    }

    @Override
    public ViewPager2 getViewPager() {
        return this.viewPager;
    }

    @Override
    public XTabLayoutV2 getXTablayout() {
        return this.xTabLayout;
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, BrowserTabsManagerActivity.class));
    }

    public static void start(Context context, int i) {
        Intent intent = new Intent(context, BrowserTabsManagerActivity.class);
        intent.putExtra(BrowserConstant.CHONTROL_INDEX, i);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        AcBrowserManagerBinding inflate = AcBrowserManagerBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 0);
        mappingId();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.xTabLayout = this.binding.xTablayout;
        this.viewPager = this.binding.vpContent;
        this.browserTabBottomToolbar = this.binding.frBottomBar;
        this.liTabManager = this.binding.liTabManager;
        this.liBrowserClear = this.binding.liBrowserClear;
        this.tvBrowserClear = this.binding.tvBrowserClear;
        this.ivBrowserClear = this.binding.ivBrowserClear;
        this.ivBrowserNewTab = this.binding.ivBrowserNew;
        this.ivBrowserBack = this.binding.ivBrowserBack;
        this.liHistoryManager = this.binding.liHistoryManager;
        this.liBrowserHistoryClear = this.binding.liBrowserHistoryClear;
        this.llRecordClear = this.binding.llRecordClear;
        this.ivBrowserHistoryClear = this.binding.ivBrowserHistoryClear;
        this.tvBrowserHistoryClear = this.binding.tvBrowserHistoryClear;
    }

    @Override
    protected void processData() {
        if (MainTabActivity.isDestroy) {
            go(MainTabActivity.class);
            finish();
            return;
        }
        ((BrowserTabsManagerPresenter) this.mPresenter).getData();
        this.browserTabManager = BrowserTabManager.getInstance();
        NoDoubleClickListener2 noDoubleClickListener2 = new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                switch (view.getId()) {
                    case R.id.iv_browser_back:
                        AnalyticsHelper.logEvent(AnalyticsHelper.DAppTabsEvent.CLICK_BACK);
                        finish();
                        return;
                    case R.id.iv_browser_clear:
                    case R.id.li_browser_clear:
                    case R.id.tv_browser_clear:
                        if (tvBrowserClear.getVisibility() == 0) {
                            AnalyticsHelper.logEvent(AnalyticsHelper.DAppTabsEvent.CLICK_CLEAR_CONFIRM);
                            tvBrowserClear.setVisibility(View.GONE);
                            liBrowserClear.setBackground(null);
                            liBrowserClear.setPadding(0, 0, 0, 0);
                            ivBrowserClear.setImageResource(R.mipmap.ic_browser_clear);
                            ((BrowserTabsManagerPresenter) mPresenter).clearBrowserTabs();
                            return;
                        }
                        AnalyticsHelper.logEvent(AnalyticsHelper.DAppTabsEvent.CLICK_CLEAR);
                        tvBrowserClear.setVisibility(View.VISIBLE);
                        ivBrowserClear.setImageResource(R.mipmap.ic_browser_clear_white);
                        liBrowserClear.setBackgroundResource(R.drawable.roundborder_red_r24);
                        return;
                    case R.id.iv_browser_history_clear:
                    case R.id.ll_record_clear:
                    case R.id.tv_browser_history_clear:
                        if (tvBrowserHistoryClear.getVisibility() == 0) {
                            AnalyticsHelper.logEvent("DappHistory_2_10");
                            ((BrowserTabsManagerPresenter) mPresenter).clearBrowserHistory();
                            tvBrowserHistoryClear.setVisibility(View.GONE);
                            llRecordClear.setPadding(0, 0, 0, 0);
                            ivBrowserHistoryClear.setImageResource(R.mipmap.ic_browser_clear);
                            llRecordClear.setBackground(null);
                            return;
                        }
                        AnalyticsHelper.logEvent("DappHistory_2_10");
                        tvBrowserHistoryClear.setVisibility(View.VISIBLE);
                        ivBrowserHistoryClear.setImageResource(R.mipmap.ic_browser_clear_white);
                        llRecordClear.setBackgroundResource(R.drawable.roundborder_red_r24);
                        return;
                    case R.id.iv_browser_new:
                        AnalyticsHelper.logEvent(AnalyticsHelper.DAppTabsEvent.CLICK_NEW_TAB);
                        ((BrowserTabsManagerPresenter) mPresenter).newBrowserTab();
                        return;
                    case R.id.li_history_manager:
                        tvBrowserHistoryClear.setVisibility(View.GONE);
                        llRecordClear.setPadding(0, 0, 0, 0);
                        ivBrowserHistoryClear.setImageResource(R.mipmap.ic_browser_clear);
                        llRecordClear.setBackground(null);
                        return;
                    case R.id.li_tab_manager:
                        tvBrowserClear.setVisibility(View.GONE);
                        liBrowserClear.setBackground(null);
                        liBrowserClear.setPadding(0, 0, 0, 0);
                        ivBrowserClear.setImageResource(R.mipmap.ic_browser_clear);
                        return;
                    case R.id.vp_content:
                        LogUtils.d("BrowserWebView", "vp_content onclicke");
                        return;
                    default:
                        return;
                }
            }
        };
        this.ivBrowserClear.setOnClickListener(noDoubleClickListener2);
        this.tvBrowserClear.setOnClickListener(noDoubleClickListener2);
        this.liBrowserClear.setOnClickListener(noDoubleClickListener2);
        this.liTabManager.setOnClickListener(noDoubleClickListener2);
        this.ivBrowserNewTab.setOnClickListener(noDoubleClickListener2);
        this.ivBrowserBack.setOnClickListener(noDoubleClickListener2);
        this.ivBrowserHistoryClear.setOnClickListener(noDoubleClickListener2);
        this.liHistoryManager.setOnClickListener(noDoubleClickListener2);
        this.tvBrowserHistoryClear.setOnClickListener(noDoubleClickListener2);
        this.llRecordClear.setOnClickListener(noDoubleClickListener2);
        this.viewPager.setOnClickListener(noDoubleClickListener2);
    }

    @Override
    public void resetButtons() {
        if (this.tvBrowserClear.getVisibility() == 0) {
            this.tvBrowserClear.setVisibility(View.GONE);
            this.liBrowserClear.setBackground(null);
            this.liBrowserClear.setPadding(0, 0, 0, 0);
            this.ivBrowserClear.setImageResource(R.mipmap.ic_browser_clear);
        }
        if (this.tvBrowserHistoryClear.getVisibility() == 0) {
            this.tvBrowserHistoryClear.setVisibility(View.GONE);
            this.llRecordClear.setPadding(0, 0, 0, 0);
            this.ivBrowserHistoryClear.setImageResource(R.mipmap.ic_browser_clear);
            this.llRecordClear.setBackground(null);
        }
    }

    @Override
    public String getCurAddress() {
        return WalletUtils.getSelectedWallet().getAddress();
    }

    @Override
    public Intent getIIntent() {
        return getIntent();
    }
}
