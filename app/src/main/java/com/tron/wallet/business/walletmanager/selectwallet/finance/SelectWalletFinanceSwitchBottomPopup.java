package com.tron.wallet.business.walletmanager.selectwallet.finance;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BottomPopupView;
import com.lxj.xpopup.util.KeyboardUtils;
import com.tron.wallet.business.vote.util.BaseTextWatcher;
import com.tron.wallet.business.walletmanager.selectwallet.adapter.FinanceSelectWalletListAdapter;
import com.tron.wallet.business.walletmanager.selectwallet.bean.FinanceSelectWalletBean;
import com.tron.wallet.business.walletmanager.selectwallet.bean.SelectWalletBean;
import com.tron.wallet.business.walletmanager.selectwallet.controller.RecentlyWalletController;
import com.tron.wallet.business.walletmanager.selectwallet.finance.SelectWalletFinanceContract;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.CustomFontUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.SoftHideKeyBoardUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.PopupSelectWalletFinanceBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.util.Iterator;
import java.util.List;
import org.tron.walletserver.Wallet;
public class SelectWalletFinanceSwitchBottomPopup extends BottomPopupView implements SelectWalletFinanceContract.IView {
    private PopupSelectWalletFinanceBinding binding;
    EditText etSearch;
    private boolean firstEnter;
    private boolean hasSelected;
    ImageView ivClear;
    ImageView ivPlaceHolder;
    RecyclerView listView;
    View llRoot;
    View llSearch;
    private SelectWalletFinanceContract.Presenter mPresenter;
    NoNetView noDataView;
    NoNetView noMatchedView;
    private OnClickListener onClickListener;
    private OnDismissListener onDismissListener;
    private String projectId;
    private FinanceSelectWalletListAdapter resultAdapter;
    private Wallet selectedWallet;
    private String tokenId;
    private String tokenSymbol;

    public interface OnClickListener {
        void onClick(Wallet wallet);
    }

    public interface OnDismissListener {
        void onDismiss();
    }

    @Override
    public int getImplLayoutId() {
        return R.layout.popup_select_wallet_finance;
    }

    public SelectWalletFinanceSwitchBottomPopup(Context context, Wallet wallet, String str, String str2, String str3, OnClickListener onClickListener, OnDismissListener onDismissListener) {
        super(context);
        this.firstEnter = true;
        this.selectedWallet = wallet;
        this.tokenId = str;
        this.tokenSymbol = str2;
        this.projectId = str3;
        this.onClickListener = onClickListener;
        this.onDismissListener = onDismissListener;
        this.mPresenter = new SelectWalletFinancePresenter();
        SelectWalletFinanceModel selectWalletFinanceModel = new SelectWalletFinanceModel();
        selectWalletFinanceModel.setConfig(StringTronUtil.isEmpty(str3) ? 1 : 2, str, str2, str3);
        selectWalletFinanceModel.setSearchedTextColor(context.getResources().getColor(R.color.blue_3c));
        this.mPresenter.setVM(this, selectWalletFinanceModel);
        ((SelectWalletFinancePresenter) this.mPresenter).onStart();
        this.mPresenter.setSelectedWallet(this.selectedWallet);
    }

    public static SelectWalletFinanceSwitchBottomPopup showPopup(Context context, Wallet wallet, String str, String str2, String str3, OnClickListener onClickListener, OnDismissListener onDismissListener) {
        return (SelectWalletFinanceSwitchBottomPopup) new XPopup.Builder(context).autoOpenSoftInput(false).moveUpToKeyboard(false).enableDrag(false).asCustom(new SelectWalletFinanceSwitchBottomPopup(context, wallet, str, str2, str3, onClickListener, onDismissListener)).show();
    }

    @Override
    public void onDismiss() {
        OnDismissListener onDismissListener;
        KeyboardUtils.hideSoftInput(getPopupContentView());
        if (!this.hasSelected && (onDismissListener = this.onDismissListener) != null) {
            onDismissListener.onDismiss();
        }
        super.onDismiss();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mPresenter.onDestroy();
        this.binding = null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.binding = PopupSelectWalletFinanceBinding.bind(getPopupImplView());
        mappingId();
        this.llRoot.setOnClickListener(getOnClickListener());
        this.binding.ivCommonRight.setOnClickListener(getOnClickListener());
        this.ivClear.setOnClickListener(getOnClickListener());
        this.etSearch.setInputType(1);
        this.etSearch.setImeOptions(6);
        this.listView.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        FinanceSelectWalletListAdapter financeSelectWalletListAdapter = new FinanceSelectWalletListAdapter();
        this.resultAdapter = financeSelectWalletListAdapter;
        financeSelectWalletListAdapter.setProjectId(this.projectId);
        this.resultAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Object item = baseQuickAdapter.getItem(i);
                if (item == null) {
                    return;
                }
                Wallet wallet = ((SelectWalletBean) item).getWallet();
                if (view.getId() == R.id.item_layout) {
                    RecentlyWalletController.setRecentlyWallet(wallet);
                    WalletUtils.setSelectedWallet(wallet.getWalletName());
                    SpAPI.THIS.setColdWalletSelected(wallet.getWalletName());
                    hasSelected = true;
                    if (onClickListener != null) {
                        onClickListener.onClick(wallet);
                    }
                    AnalyticsHelper.logEvent(AnalyticsHelper.SwitchWalletPage.CLICK_WALLET);
                    dismiss();
                }
            }
        });
        this.resultAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
            }
        }, this.listView);
        this.resultAdapter.setLoadMoreText(StringTronUtil.isEmpty(this.projectId) ? R.string.finance_select_wallet_footer_hint : R.string.finance_select_wallet_footer_hint_2);
        this.listView.setAdapter(this.resultAdapter);
        ((SimpleItemAnimator) this.listView.getItemAnimator()).setSupportsChangeAnimations(false);
        SpannableString spannableString = new SpannableString(getResources().getString(R.string.finance_search_wallet_hint));
        spannableString.setSpan(new StyleSpan(0), 0, spannableString.length(), 33);
        spannableString.setSpan(new AbsoluteSizeSpan(12, true), 0, spannableString.length(), 33);
        this.etSearch.setHint(spannableString);
        this.etSearch.setTypeface(CustomFontUtils.getTypeface(getContext(), 0));
        this.etSearch.setEnabled(false);
        this.etSearch.addTextChangedListener(new BaseTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                String obj = editable.toString();
                if (StringTronUtil.isEmpty(obj)) {
                    etSearch.setTypeface(CustomFontUtils.getTypeface(getContext(), 0));
                } else {
                    etSearch.setTypeface(CustomFontUtils.getTypeface(getContext(), 1));
                }
                if (!TextUtils.isEmpty(obj) && ivClear.getVisibility() == 8) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.TransferSelectTokenPage.CLICK_TRANSFER_SELECT_TOKEN_PAGE_SEARCH_TOKEN);
                }
                ivClear.setVisibility(TextUtils.isEmpty(obj) ? View.GONE : View.VISIBLE);
                firstEnter = false;
                mPresenter.search(editable.toString());
            }
        });
        this.etSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean z) {
                if (!z) {
                    SoftHideKeyBoardUtil.closeKeybord((Activity) getContext());
                } else {
                    AnalyticsHelper.logEvent(AnalyticsHelper.SwitchWalletPage.CLICK_SEARCH);
                }
            }
        });
        this.noDataView.setReloadDescription(StringTronUtil.isEmpty(this.projectId) ? R.string.finance_wallets_no_balance : R.string.finance_wallets_no_stored);
        this.mPresenter.search(null);
        AnalyticsHelper.logEvent(AnalyticsHelper.FinancePage.FINANCE_PAGE_SELECT_ACCOUNT_POPUP_SHOW);
    }

    private void mappingId() {
        this.llRoot = this.binding.root;
        this.llSearch = this.binding.rlSearch;
        this.listView = this.binding.rvList;
        this.etSearch = this.binding.etSearch;
        this.ivClear = this.binding.ivClear;
        this.noMatchedView = this.binding.noMatchedView;
        this.noDataView = this.binding.noDataView;
        this.ivPlaceHolder = this.binding.ivPlaceHolder;
    }

    @Override
    public void doAfterShow() {
        super.doAfterShow();
        this.etSearch.setEnabled(true);
    }

    private View.OnClickListener getOnClickListener() {
        return new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                if (id == R.id.iv_clear) {
                    etSearch.setTextKeepState("");
                } else if (id == R.id.iv_common_right) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.SwitchWalletPage.CLICK_CLOSE);
                    dismiss();
                } else if (id != R.id.root) {
                } else {
                    dismiss();
                }
            }
        };
    }

    @Override
    public Context getIContext() {
        return getContext();
    }

    @Override
    public void onSearchComplete(List<FinanceSelectWalletBean> list, String str) {
        this.ivPlaceHolder.setVisibility(View.GONE);
        this.resultAdapter.setNewData(list);
        if (list.size() <= 0) {
            this.listView.setVisibility(View.GONE);
            if (StringTronUtil.isEmpty(str)) {
                this.noDataView.setVisibility(View.VISIBLE);
                this.noMatchedView.setVisibility(View.GONE);
            } else {
                this.noDataView.setVisibility(View.GONE);
                this.noMatchedView.setVisibility(View.VISIBLE);
            }
        } else {
            this.listView.setVisibility(View.VISIBLE);
            this.noDataView.setVisibility(View.GONE);
            this.noMatchedView.setVisibility(View.GONE);
            if (this.firstEnter && StringTronUtil.isEmpty(str)) {
                Iterator<FinanceSelectWalletBean> it = list.iterator();
                int i = 0;
                while (it.hasNext() && !it.next().isSelected()) {
                    i++;
                }
                this.listView.scrollToPosition(i);
            } else {
                this.listView.scrollToPosition(0);
            }
        }
        this.resultAdapter.loadMoreComplete();
        this.resultAdapter.loadMoreEnd(false);
        this.firstEnter = false;
    }
}
