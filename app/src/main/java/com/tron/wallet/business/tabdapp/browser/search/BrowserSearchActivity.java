package com.tron.wallet.business.tabdapp.browser.search;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.wallet.business.tabdapp.browser.bean.BrowserSearchBean;
import com.tron.wallet.business.tabdapp.browser.bean.DAppVisitHistoryBean;
import com.tron.wallet.business.tabdapp.browser.search.BrowserInterviewHistoryListAdapter;
import com.tron.wallet.business.tabdapp.browser.search.BrowserSearchContract;
import com.tron.wallet.business.tabdapp.browser.search.BrowserSearchPresenter;
import com.tron.wallet.business.tabdapp.browser.search.BrowserSearchResultAdapter;
import com.tron.wallet.business.tabdapp.browser.search.SearchDappResultBean;
import com.tron.wallet.business.tabdapp.dappsearch.DappSearchPresenter;
import com.tron.wallet.business.tabdapp.home.utils.DAppAnalyseUtils;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.ConfirmCustomPopupView;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.ChainUtil;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.SoftHideKeyBoardUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.ActivityBrowserSearchResultBinding;
import com.tron.wallet.db.SpAPI;
import com.tronlinkpro.wallet.R;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class BrowserSearchActivity extends BaseActivity<BrowserSearchPresenter, BrowserSearchModel> implements BrowserSearchContract.View {
    private static final int MSG_SEARCH = 10001;
    private ActivityBrowserSearchResultBinding binding;
    private BrowserInterviewHistoryListAdapter browserInterviewHistoryListAdapter;
    HistoryFlowLayout historyFlowLayout;
    ImageView ivClear;
    View ivInterviewHistoryClear;
    ImageView ivSearch;
    View llSearch;
    LinearLayout mInterviewHistoryLayout;
    RecyclerView mInterviewHistoryRecyclerView;
    NoNetView mNoDataView;
    LinearLayout mNoResultLayout;
    RecyclerView mSearchDappRecyclerView;
    EditText mSearchEt;
    private List<BrowserSearchBean> mSearchHistoryList;
    LinearLayout mSearchResultLayout;
    NoNetView netErrorView;
    View nodateView;
    View searchAdderssAccessLayout;
    View searchGoogleLayout;
    View searchHistoryView;
    private BrowserSearchResultAdapter searchResultAdapter;
    View searchTronscanLayout;
    private String searchUrl = "";
    TextView tvGoogleSearchKeyword;
    TextView tvTronscanSearchKeyword;
    TextView tvUrlKeyword;

    @Override
    public void showNoDatasPage() {
    }

    @Override
    public void updateAssetsFollowState(TokenBean tokenBean, int i, boolean z) {
    }

    public static void startActivity(Context context) {
        startActivity(context, "");
    }

    public static void startActivity(Context context, String str) {
        Intent intent = new Intent();
        intent.setClass(context, BrowserSearchActivity.class);
        intent.putExtra("URL", str);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        ActivityBrowserSearchResultBinding inflate = ActivityBrowserSearchResultBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        setView(root, 0);
        setClick();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.mSearchResultLayout = this.binding.llSearchResult;
        this.mSearchDappRecyclerView = this.binding.rvDappResult;
        this.mInterviewHistoryRecyclerView = this.binding.rvInterviewHistory;
        this.mSearchEt = this.binding.searchHeader.etSearch;
        this.ivSearch = this.binding.searchHeader.searchIcon;
        this.searchAdderssAccessLayout = this.binding.searchHeader.rlAddrssAccess;
        this.tvUrlKeyword = this.binding.searchHeader.tvAddress;
        this.searchGoogleLayout = this.binding.searchHeader.rlGoogleSearch;
        this.tvGoogleSearchKeyword = this.binding.searchHeader.tvGoogleSearchKeyword;
        this.searchTronscanLayout = this.binding.searchHeader.llTronscanSearch;
        this.tvTronscanSearchKeyword = this.binding.searchHeader.tvTronscanSearchAddress;
        this.searchHistoryView = this.binding.searchHistory.rlSearchRoot;
        this.mNoResultLayout = this.binding.llNoresult;
        this.mInterviewHistoryLayout = this.binding.llInterviewHistory;
        this.ivInterviewHistoryClear = this.binding.ivInterviewHistoryClear;
        this.mNoDataView = this.binding.noDataView;
        this.ivClear = this.binding.searchHeader.ivClear;
        this.netErrorView = this.binding.netErrorView;
        this.llSearch = this.binding.searchHeader.llSearch;
        this.nodateView = this.binding.rlEmpty;
        this.historyFlowLayout = this.binding.searchHistory.searchHistoryFlowLayout;
    }

    @Override
    protected void processData() {
        loadAllDappUrl();
        loadSearchHisTory();
        loadInterviewHistory();
        this.mSearchDappRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext, 1, false));
        BrowserSearchResultAdapter browserSearchResultAdapter = new BrowserSearchResultAdapter(new BrowserSearchResultAdapter.ItemCallback() {
            @Override
            public void onItemClicked(SearchDappResultBean.SearchDappBean searchDappBean, int i) {
                AnalyticsHelper.logEvent(AnalyticsHelper.DAppSearchEvent.CLICK_SEARCH_RESULT_ITEM);
                ((BrowserSearchPresenter) mPresenter).loadNewPage(getHttpsUrl(searchDappBean.getHomeUrl()), searchDappBean.getName(), searchDappBean.getImageUrl(), searchDappBean.isFromHttp());
            }
        });
        this.searchResultAdapter = browserSearchResultAdapter;
        this.mSearchDappRecyclerView.setAdapter(browserSearchResultAdapter);
        ((SimpleItemAnimator) this.mSearchDappRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        setListener();
        initSearchEt();
        SoftHideKeyBoardUtil.showSoftInput(this, this.mSearchEt);
    }

    private void loadAllDappUrl() {
        ((BrowserSearchPresenter) this.mPresenter).loadAllDappUrl();
    }

    public String getHttpsUrl(String str) {
        if (str.startsWith("https://") || str.startsWith(ChainUtil.Request_HTTP) || str.startsWith("ftp://")) {
            return str;
        }
        if (isIPUrl(str)) {
            return ChainUtil.Request_HTTP + str;
        }
        return "https://" + str;
    }

    private void loadInterviewHistory() {
        ((BrowserSearchPresenter) this.mPresenter).getInterviewHistory();
    }

    private void initSearchEt() {
        ViewCompat.setTransitionName(this.llSearch, "llSearch");
        this.searchUrl = getIntent().getStringExtra("URL");
        this.mSearchEt.setInputType(1);
        this.mSearchEt.setImeOptions(6);
        if (StringTronUtil.isEmpty(this.searchUrl)) {
            return;
        }
        this.ivClear.setVisibility(View.VISIBLE);
        this.mSearchEt.setText(this.searchUrl);
        this.mSearchEt.setSelectAllOnFocus(true);
        this.searchHistoryView.setVisibility(View.GONE);
    }

    private void setListener() {
        this.mSearchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String trim = editable.toString().trim();
                if (StringTronUtil.isEmpty(trim)) {
                    ivClear.setVisibility(View.GONE);
                    hideSearchResultView();
                    searchAdderssAccessLayout.setVisibility(View.GONE);
                    searchGoogleLayout.setVisibility(View.GONE);
                    searchTronscanLayout.setVisibility(View.GONE);
                    tvUrlKeyword.setText("");
                    tvGoogleSearchKeyword.setText("");
                    tvTronscanSearchKeyword.setText("");
                    showHistoryList();
                    mInterviewHistoryLayout.setVisibility(View.VISIBLE);
                    ((BrowserSearchPresenter) mPresenter).getInterviewHistory();
                    return;
                }
                ivClear.setVisibility(View.VISIBLE);
                showPlaceHolder();
                if (isIPUrl(trim) || isHttpUrl(trim)) {
                    ivSearch.setImageDrawable(getResources().getDrawable(R.mipmap.ic_search_dapp_address));
                    showSearchMenu(true);
                } else {
                    ivSearch.setImageDrawable(getResources().getDrawable(R.mipmap.ic_search_dapp));
                    showSearchMenu(false);
                }
                ((BrowserSearchPresenter) mPresenter).getSearchDapp(trim);
            }
        });
        this.netErrorView.setOnReloadClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$setListener$0(view);
            }
        });
    }

    public void lambda$setListener$0(View view) {
        if (TextUtils.isEmpty(this.mSearchEt.getText().toString().trim())) {
            return;
        }
        ((BrowserSearchPresenter) this.mPresenter).getSearchHistory();
    }

    public void showSearchMenu(Boolean bool) {
        String trim = this.mSearchEt.getText().toString().trim();
        if (bool == null) {
            this.searchAdderssAccessLayout.setVisibility(View.GONE);
            this.searchGoogleLayout.setVisibility(View.GONE);
            this.searchTronscanLayout.setVisibility(View.GONE);
            this.tvUrlKeyword.setText("");
            this.tvGoogleSearchKeyword.setText("");
            this.tvTronscanSearchKeyword.setText("");
            this.mSearchEt.setText("");
        } else if (bool.booleanValue()) {
            this.searchAdderssAccessLayout.setVisibility(View.VISIBLE);
            this.tvUrlKeyword.setText(trim);
            this.searchGoogleLayout.setVisibility(View.GONE);
            this.searchTronscanLayout.setVisibility(View.GONE);
            this.tvGoogleSearchKeyword.setText("");
            this.tvTronscanSearchKeyword.setText("");
        } else if (StringTronUtil.isAddressValid(trim) || StringTronUtil.is64HexString(trim)) {
            this.searchGoogleLayout.setVisibility(View.GONE);
            this.searchTronscanLayout.setVisibility(View.VISIBLE);
            this.searchAdderssAccessLayout.setVisibility(View.GONE);
            this.tvGoogleSearchKeyword.setText("");
            this.tvUrlKeyword.setText("");
            this.tvTronscanSearchKeyword.setText(formatTheKeyWord(StringTronUtil.indentAddress(trim, 8)));
        } else {
            this.searchGoogleLayout.setVisibility(View.VISIBLE);
            this.searchTronscanLayout.setVisibility(View.GONE);
            this.searchAdderssAccessLayout.setVisibility(View.GONE);
            this.tvGoogleSearchKeyword.setText(formatTheKeyWord(StringTronUtil.indentAddress(trim, 8)));
            this.tvUrlKeyword.setText("");
            this.tvTronscanSearchKeyword.setText("");
        }
    }

    private SpannableString formatTheKeyWord(String str) {
        SpannableString spannableString = new SpannableString("\"" + str + "\"");
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.black)), 0, 1, 33);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.black)), str.length() + 1, str.length() + 2, 33);
        return spannableString;
    }

    public void setClick() {
        NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                switch (view.getId()) {
                    case R.id.et_search:
                        AnalyticsHelper.logEvent(AnalyticsHelper.DAppSearchEvent.CLICK_SEARCH_EDIT);
                        return;
                    case R.id.iv_clear:
                        AnalyticsHelper.logEvent(AnalyticsHelper.DAppSearchEvent.CLICK_SEARCH_EDIT_DELETE);
                        mSearchEt.setText("");
                        ivSearch.setImageDrawable(getResources().getDrawable(R.mipmap.ic_search_dapp));
                        showSearchMenu(null);
                        showHistoryList();
                        mInterviewHistoryLayout.setVisibility(View.VISIBLE);
                        return;
                    case R.id.iv_interview_history_clear:
                        AnalyticsHelper.logEvent(AnalyticsHelper.DAppSearchEvent.CLICK_VISITED_HISTORY_CLEAR);
                        showClearAllVisitHistoryPopWindow();
                        return;
                    case R.id.iv_search_history_clear:
                        AnalyticsHelper.logEvent(AnalyticsHelper.DAppSearchEvent.CLICK_SEARCH_HISTORY_CLEAR);
                        ((BrowserSearchPresenter) mPresenter).clearSearchHistory();
                        return;
                    case R.id.ll_tronscan_search:
                        AnalyticsHelper.logEvent(AnalyticsHelper.DAppSearchEvent.CLICK_DO_TRONSCAN_SEARCH);
                        ((BrowserSearchPresenter) mPresenter).doTronScanSearch(mSearchEt.getText().toString().trim());
                        return;
                    case R.id.rl_addrss_access:
                        BrowserSearchActivity browserSearchActivity = BrowserSearchActivity.this;
                        ((BrowserSearchPresenter) mPresenter).loadNewPage(browserSearchActivity.getHttpsUrl(browserSearchActivity.mSearchEt.getText().toString().trim()), "", "");
                        return;
                    case R.id.rl_google_search:
                        AnalyticsHelper.logEvent(AnalyticsHelper.DAppSearchEvent.CLICK_DO_GOOGLE_SEARCH);
                        ((BrowserSearchPresenter) mPresenter).doGoogleSearch(mSearchEt.getText().toString().trim());
                        return;
                    case R.id.tv_cancel:
                        AnalyticsHelper.logEvent(AnalyticsHelper.DAppSearchEvent.CLICK_SEARCH_CANCEL);
                        SoftHideKeyBoardUtil.closeKeybord(BrowserSearchActivity.this);
                        finishAfterTransition();
                        return;
                    default:
                        return;
                }
            }
        };
        this.binding.getRoot().findViewById(R.id.tv_cancel).setOnClickListener(noDoubleClickListener);
        this.binding.getRoot().findViewById(R.id.iv_clear).setOnClickListener(noDoubleClickListener);
        this.binding.getRoot().findViewById(R.id.rl_google_search).setOnClickListener(noDoubleClickListener);
        this.binding.getRoot().findViewById(R.id.rl_addrss_access).setOnClickListener(noDoubleClickListener);
        this.binding.getRoot().findViewById(R.id.ll_tronscan_search).setOnClickListener(noDoubleClickListener);
        this.binding.getRoot().findViewById(R.id.iv_search_history_clear).setOnClickListener(noDoubleClickListener);
        this.binding.getRoot().findViewById(R.id.iv_interview_history_clear).setOnClickListener(noDoubleClickListener);
        this.binding.getRoot().findViewById(R.id.et_search).setOnClickListener(noDoubleClickListener);
    }

    @Override
    public void hideInterviewHistoryView() {
        this.netErrorView.setVisibility(View.GONE);
        this.mSearchDappRecyclerView.setVisibility(View.VISIBLE);
        this.mSearchResultLayout.setVisibility(View.VISIBLE);
        this.mInterviewHistoryLayout.setVisibility(View.GONE);
        this.mNoResultLayout.setVisibility(View.GONE);
    }

    void hideSearchResultView() {
        this.netErrorView.setVisibility(View.GONE);
        this.mSearchDappRecyclerView.setVisibility(View.GONE);
        this.mNoResultLayout.setVisibility(View.GONE);
        this.mInterviewHistoryRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNoNetView() {
        this.mNoResultLayout.setVisibility(View.GONE);
        this.mSearchDappRecyclerView.setVisibility(View.GONE);
        this.mInterviewHistoryLayout.setVisibility(View.GONE);
        this.mInterviewHistoryRecyclerView.setVisibility(View.GONE);
        this.netErrorView.setVisibility(View.VISIBLE);
        this.netErrorView.hideLoading();
    }

    @Override
    public void updateSearchedResult(List<SearchDappResultBean.SearchDappBean> list) {
        if (TextUtils.isEmpty(this.mSearchEt.getText().toString().trim())) {
            hideSearchResultView();
            return;
        }
        hideInterviewHistoryView();
        this.searchHistoryView.setVisibility(View.GONE);
        this.searchResultAdapter.setNewData(list);
        this.mSearchDappRecyclerView.scrollToPosition(0);
    }

    void showPlaceHolder() {
        this.netErrorView.setVisibility(View.GONE);
        this.mSearchResultLayout.setVisibility(View.VISIBLE);
        this.mNoResultLayout.setVisibility(View.GONE);
        this.searchHistoryView.setVisibility(View.GONE);
        this.mSearchDappRecyclerView.setVisibility(View.GONE);
        this.mInterviewHistoryLayout.setVisibility(View.GONE);
        this.mInterviewHistoryRecyclerView.setVisibility(View.GONE);
    }

    public void loadSearchHisTory() {
        ((BrowserSearchPresenter) this.mPresenter).getSearchHistory();
    }

    @Override
    public void updateInterviewHistoryView(List<DAppVisitHistoryBean> list) {
        if (list == null || list.size() == 0) {
            this.nodateView.setVisibility(View.VISIBLE);
            this.ivInterviewHistoryClear.setVisibility(View.GONE);
            this.mInterviewHistoryRecyclerView.setVisibility(View.GONE);
            return;
        }
        this.nodateView.setVisibility(View.GONE);
        this.mInterviewHistoryLayout.setVisibility(View.VISIBLE);
        this.ivInterviewHistoryClear.setVisibility(View.VISIBLE);
        this.mInterviewHistoryRecyclerView.setVisibility(View.VISIBLE);
        this.mInterviewHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext, 1, false));
        BrowserInterviewHistoryListAdapter browserInterviewHistoryListAdapter = new BrowserInterviewHistoryListAdapter(this, new BrowserInterviewHistoryListAdapter.ItemCallback() {
            @Override
            public void onItemClicked(DAppVisitHistoryBean dAppVisitHistoryBean, int i) {
                AnalyticsHelper.logEvent(AnalyticsHelper.DAppSearchEvent.CLICK_VISITED_HISTORY);
                ((BrowserSearchPresenter) mPresenter).loadNewPageWithOutUnknownPop(getHttpsUrl(dAppVisitHistoryBean.getUrl()), dAppVisitHistoryBean.getTitle(), dAppVisitHistoryBean.getIcon(), false);
                DAppAnalyseUtils.reportDAppHost(AnalyticsHelper.DAppSearchEvent.DAPP_SEARCH_EVENT, dAppVisitHistoryBean.getUrl());
            }

            @Override
            public void deleteItem(DAppVisitHistoryBean dAppVisitHistoryBean, int i) {
                AnalyticsHelper.logEvent(AnalyticsHelper.DAppSearchEvent.CLICK_VISITED_HISTORY_DELETE);
                ((BrowserSearchPresenter) mPresenter).deleteInterviewHistoryItem(dAppVisitHistoryBean, i);
            }
        });
        this.browserInterviewHistoryListAdapter = browserInterviewHistoryListAdapter;
        this.mInterviewHistoryRecyclerView.setAdapter(browserInterviewHistoryListAdapter);
        ((SimpleItemAnimator) this.mInterviewHistoryRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        this.browserInterviewHistoryListAdapter.notifyDataChanged(list);
    }

    @Override
    public void updateSearchHistory(List<BrowserSearchBean> list) {
        this.mSearchHistoryList = list;
        if (list != null && list.size() > 0) {
            ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(-2, -2);
            marginLayoutParams.setMargins(0, 0, 0, 0);
            int screenWidth = (UIUtils.getScreenWidth(this) - (UIUtils.dip2px(15.0f) * 2)) - UIUtils.dip2px(20.0f);
            this.historyFlowLayout.removeAllViews();
            for (int i = 0; i < list.size(); i++) {
                TextView textView = (TextView) getLayoutInflater().inflate(R.layout.browser_search_history_item, (ViewGroup) null).findViewById(R.id.tv_content);
                textView.setText(list.get(i).getKey());
                textView.setMaxWidth(screenWidth / 2);
                textView.setMaxLines(1);
                textView.setEllipsize(TextUtils.TruncateAt.END);
                final String key = list.get(i).getKey();
                textView.setOnClickListener(new NoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        AnalyticsHelper.logEvent(AnalyticsHelper.DAppSearchEvent.CLICK_SEARCH_HISTORY_ITEM);
                        if (StringTronUtil.isAddressValid(key) || StringTronUtil.is64HexString(key)) {
                            ((BrowserSearchPresenter) mPresenter).doTronScanSearch(key);
                        } else {
                            ((BrowserSearchPresenter) mPresenter).doGoogleSearch(key);
                        }
                    }
                });
                this.historyFlowLayout.addView(textView, marginLayoutParams);
            }
            return;
        }
        this.searchHistoryView.setVisibility(View.GONE);
    }

    @Override
    public void updateInterviewHistoryView(DAppVisitHistoryBean dAppVisitHistoryBean, int i) {
        this.browserInterviewHistoryListAdapter.notifyDataRemoved(dAppVisitHistoryBean, i);
        if (this.browserInterviewHistoryListAdapter.getHistoryBeanList().size() == 0) {
            this.mInterviewHistoryRecyclerView.setVisibility(View.GONE);
            this.nodateView.setVisibility(View.VISIBLE);
            this.ivInterviewHistoryClear.setVisibility(View.GONE);
        }
    }

    public void showHistoryList() {
        List<BrowserSearchBean> list = this.mSearchHistoryList;
        if (list == null || list.size() <= 0) {
            return;
        }
        this.searchHistoryView.setVisibility(View.VISIBLE);
    }

    public boolean isHttpUrl(String str) {
        try {
            return Pattern.compile("([a-zA-Z0-9\\-]+\\.([a-zA-Z0-9\\-]{2,})(\\.[a-zA-Z0-9\\-]+)*(:\\d+)?(/[a-zA-Z0-9\\.\\-~!@#$%^&*+?:_/=<>]*)?)").matcher(str.replace("www.", "").replace("https://", "").replace(ChainUtil.Request_HTTP, "").replace("ftp://", "").trim()).matches();
        } catch (Throwable th) {
            SentryUtil.captureException(th);
            return false;
        }
    }

    public boolean isIPUrl(String str) {
        try {
            String replace = str.replace("www.", "").replace("https://", "").replace(ChainUtil.Request_HTTP, "").replace("ftp://", "");
            Pattern compile = Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\:(6553[0-5]|655[0-2]\\d|65[0-4]\\d{2}|6[0-4]\\d{3}|[0-5]\\d{4}|[1-9]\\d{0,3})$");
            Matcher matcher = Pattern.compile("((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)$").matcher(replace);
            Matcher matcher2 = compile.matcher(replace);
            if (!matcher.find()) {
                if (!matcher2.find()) {
                    return false;
                }
            }
            return true;
        } catch (Throwable th) {
            SentryUtil.captureException(th);
            return false;
        }
    }

    @Override
    public void showClearAllVisitHistoryPopWindow() {
        ConfirmCustomPopupView.getBuilder(this).setBtnStyle(1).setTitle(getString(R.string.confirm_clear_dapp_visit_history)).setTitleSize(16).setConfirmText(getString(R.string.clear_recents)).setCancelText(getString(R.string.cancel)).setAutoDismissOutSide(true).setConfirmListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$showClearAllVisitHistoryPopWindow$1(view);
            }
        }).setCancleListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                AnalyticsHelper.logEvent(AnalyticsHelper.DAppSearchEvent.CLICK_VISITED_HISTORY_CLEAR_CANCEL);
            }
        }).build().show();
    }

    public void lambda$showClearAllVisitHistoryPopWindow$1(View view) {
        AnalyticsHelper.logEvent(AnalyticsHelper.DAppSearchEvent.CLICK_VISITED_HISTORY_CLEAR_CONFIRM);
        ((BrowserSearchPresenter) this.mPresenter).clearInterviewHistory();
    }

    @Override
    public void showThirdAddressPopWindow(final String str, final BrowserSearchPresenter.SearchTipsPopCall searchTipsPopCall) {
        ConfirmCustomPopupView.getBuilder(this).setBtnStyle(1).setTitle(getString(R.string.dapp_name)).setTitleSize(16).setConfirmText(getString(R.string.go)).setCancelText(getString(R.string.cancel)).setAutoDismissOutSide(true).setConfirmListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                BrowserSearchActivity.lambda$showThirdAddressPopWindow$3(BrowserSearchPresenter.SearchTipsPopCall.this, str, view);
            }
        }).build().show();
    }

    public static void lambda$showThirdAddressPopWindow$3(BrowserSearchPresenter.SearchTipsPopCall searchTipsPopCall, String str, View view) {
        if (searchTipsPopCall != null) {
            searchTipsPopCall.callBack();
        }
        SpAPI.THIS.setDappName(str);
        SpAPI.THIS.setShowThirdAddressPopWindow(str);
    }

    @Override
    public void showUnknownAddressPopWindow(final String str, final BrowserSearchPresenter.SearchTipsPopCall searchTipsPopCall) {
        ConfirmCustomPopupView.getBuilder(this).setBtnStyle(1).setTitle(getString(R.string.asset_scan_unknown_link_title)).setInfo(String.format(getString(R.string.asset_scan_unknown_link_content), str)).setTitleSize(16).setConfirmText(getString(R.string.continue_visit)).setCancelText(getString(R.string.cancel)).setAutoDismissOutSide(true).setConfirmListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                BrowserSearchActivity.lambda$showUnknownAddressPopWindow$4(BrowserSearchPresenter.SearchTipsPopCall.this, str, view);
            }
        }).build().show();
    }

    public static void lambda$showUnknownAddressPopWindow$4(BrowserSearchPresenter.SearchTipsPopCall searchTipsPopCall, String str, View view) {
        if (searchTipsPopCall != null) {
            searchTipsPopCall.callBack();
        }
        SpAPI.THIS.setDappName(Uri.parse(DappSearchPresenter.getFixedUrl(str)).getHost());
    }
}
